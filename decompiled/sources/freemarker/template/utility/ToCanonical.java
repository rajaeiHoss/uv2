package freemarker.template.utility;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

public class ToCanonical {
    static Configuration config = Configuration.getDefaultConfiguration();

    public static void main(String[] strArr) {
        config.setWhitespaceStripping(false);
        if (strArr.length == 0) {
            usage();
        }
        for (String file : strArr) {
            File file2 = new File(file);
            if (!file2.exists()) {
                PrintStream printStream = System.err;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("File ");
                stringBuffer.append(file2);
                stringBuffer.append(" doesn't exist.");
                printStream.println(stringBuffer.toString());
            }
            try {
                convertFile(file2);
            } catch (Exception e) {
                PrintStream printStream2 = System.err;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Error converting file: ");
                stringBuffer2.append(file2);
                printStream2.println(stringBuffer2.toString());
                e.printStackTrace();
            }
        }
    }

    static void convertFile(File file) throws IOException {
        File absoluteFile = file.getAbsoluteFile();
        File parentFile = absoluteFile.getParentFile();
        String name = absoluteFile.getName();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(name);
        stringBuffer.append(".canonical");
        File file2 = new File(parentFile, stringBuffer.toString());
        config.setDirectoryForTemplateLoading(parentFile);
        Template template = config.getTemplate(name);
        FileWriter fileWriter = new FileWriter(file2);
        try {
            template.dump((Writer) fileWriter);
        } finally {
            fileWriter.close();
        }
    }

    static void usage() {
        System.err.println("Usage: java freemarker.template.utility.ToCanonical <filename(s)>");
    }
}
