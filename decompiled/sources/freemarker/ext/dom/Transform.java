package freemarker.ext.dom;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class Transform {
    private Configuration cfg;
    private String encoding;
    private File ftlFile;
    private File inputFile;
    private Locale locale;
    private File outputFile;

    public static void main(String[] strArr) {
        try {
            transformFromArgs(strArr).transform();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            usage();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    Transform(File file, File file2, File file3, Locale locale2, String str) throws IOException {
        str = str == null ? System.getProperty("file.encoding") : str;
        locale2 = locale2 == null ? Locale.getDefault() : locale2;
        this.encoding = str;
        this.locale = locale2;
        this.inputFile = file;
        this.ftlFile = file2;
        this.outputFile = file3;
        File parentFile = file2.getAbsoluteFile().getParentFile();
        Configuration configuration = new Configuration();
        this.cfg = configuration;
        configuration.setDirectoryForTemplateLoading(parentFile);
    }

    /* access modifiers changed from: package-private */
    public void transform() throws Exception {
        Template template = this.cfg.getTemplate(this.ftlFile.getName(), this.locale);
        NodeModel parse = NodeModel.parse(this.inputFile);
        OutputStream outputStream = System.out;
        if (this.outputFile != null) {
            outputStream = new FileOutputStream(this.outputFile);
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, this.encoding);
        try {
            template.process((Object) null, outputStreamWriter, (ObjectWrapper) null, parse);
        } finally {
            if (this.outputFile != null) {
                outputStreamWriter.close();
            }
        }
    }

    static Transform transformFromArgs(String[] strArr) throws IOException {
        File file = null;
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        while (i < strArr.length) {
            int i2 = i + 1;
            String str6 = strArr[i];
            if (i2 < strArr.length) {
                int i3 = i2 + 1;
                String str7 = strArr[i2];
                if (str6.equals("-in")) {
                    if (str == null) {
                        str = str7;
                    } else {
                        throw new IllegalArgumentException("The input file should only be specified once");
                    }
                } else if (str6.equals("-ftl")) {
                    if (str2 == null) {
                        str2 = str7;
                    } else {
                        throw new IllegalArgumentException("The ftl file should only be specified once");
                    }
                } else if (str6.equals("-out")) {
                    if (str3 == null) {
                        str3 = str7;
                    } else {
                        throw new IllegalArgumentException("The output file should only be specified once");
                    }
                } else if (str6.equals("-locale")) {
                    if (str4 == null) {
                        str4 = str7;
                    } else {
                        throw new IllegalArgumentException("The locale should only be specified once");
                    }
                } else if (!str6.equals("-encoding")) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unknown input argument: ");
                    stringBuffer.append(str6);
                    throw new IllegalArgumentException(stringBuffer.toString());
                } else if (str5 == null) {
                    str5 = str7;
                } else {
                    throw new IllegalArgumentException("The encoding should only be specified once");
                }
                i = i3;
            } else {
                throw new IllegalArgumentException("");
            }
        }
        if (str == null) {
            throw new IllegalArgumentException("No input file specified.");
        } else if (str2 != null) {
            File absoluteFile = new File(str).getAbsoluteFile();
            File absoluteFile2 = new File(str2).getAbsoluteFile();
            if (!absoluteFile.exists()) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Input file does not exist: ");
                stringBuffer2.append(str);
                throw new IllegalArgumentException(stringBuffer2.toString());
            } else if (!absoluteFile2.exists()) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("FTL file does not exist: ");
                stringBuffer3.append(str2);
                throw new IllegalArgumentException(stringBuffer3.toString());
            } else if (!absoluteFile.isFile() || !absoluteFile.canRead()) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Input file must be a readable file: ");
                stringBuffer4.append(str);
                throw new IllegalArgumentException(stringBuffer4.toString());
            } else if (!absoluteFile2.isFile() || !absoluteFile2.canRead()) {
                StringBuffer stringBuffer5 = new StringBuffer();
                stringBuffer5.append("FTL file must be a readable file: ");
                stringBuffer5.append(str2);
                throw new IllegalArgumentException(stringBuffer5.toString());
            } else {
                if (str3 != null) {
                    file = new File(str3).getAbsoluteFile();
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists() || !parentFile.canWrite()) {
                        StringBuffer stringBuffer6 = new StringBuffer();
                        stringBuffer6.append("The output directory must exist and be writable: ");
                        stringBuffer6.append(parentFile);
                        throw new IllegalArgumentException(stringBuffer6.toString());
                    }
                }
                File file2 = file;
                Locale locale2 = Locale.getDefault();
                if (str4 != null) {
                    locale2 = localeFromString(str4);
                }
                return new Transform(absoluteFile, absoluteFile2, file2, locale2, str5);
            }
        } else {
            throw new IllegalArgumentException("No ftl file specified.");
        }
    }

    static Locale localeFromString(String str) {
        String str2;
        String str3 = "";
        if (str == null) {
            str = str3;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, "_-,");
        if (!stringTokenizer.hasMoreTokens()) {
            return Locale.getDefault();
        }
        String nextToken = stringTokenizer.nextToken();
        if (stringTokenizer.hasMoreTokens()) {
            String nextToken2 = stringTokenizer.nextToken();
            if (stringTokenizer.hasMoreTokens()) {
                str3 = stringTokenizer.nextToken();
            }
            str2 = str3;
            str3 = nextToken2;
        } else {
            str2 = str3;
        }
        return new Locale(nextToken, str3, str2);
    }

    static void usage() {
        System.err.println("Usage: java freemarker.ext.dom.Transform -in <xmlfile> -ftl <ftlfile> [-out <outfile>] [-locale <locale>] [-encoding <encoding>]");
        if (Environment.getCurrentEnvironment() == null) {
            System.exit(-1);
        }
    }
}
