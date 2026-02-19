package freemarker.ext.ant;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.SimpleScalar;
import freemarker.template.Template;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateNodeModel;
import freemarker.template.utility.ClassUtil;
import freemarker.template.utility.SecurityUtilities;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.MatchingTask;

public class FreemarkerXmlTask extends MatchingTask {
    private File baseDir;
    private DocumentBuilder builder;
    private final DocumentBuilderFactory builderFactory;
    private Configuration cfg = new Configuration();
    private File destDir;
    private String encoding;
    private String extension = ".html";
    private boolean incremental = true;
    private String models;
    private final Map modelsMap;
    private Template parsedTemplate;
    private JythonAntTask prepareEnvironment;
    private JythonAntTask prepareModel;
    private String projectAttribute = null;
    private File projectFile = null;
    private long projectFileLastModified = 0;
    private TemplateNodeModel projectNode;
    private TemplateModel projectTemplate;
    private TemplateModel propertiesTemplate;
    private File templateDir;
    private String templateEncoding;
    private long templateFileLastModified = 0;
    private String templateName;
    private TemplateModel userPropertiesTemplate;
    private boolean validation;

    public FreemarkerXmlTask() {
        String systemProperty = SecurityUtilities.getSystemProperty("file.encoding");
        this.encoding = systemProperty;
        this.templateEncoding = systemProperty;
        this.validation = false;
        this.models = "";
        this.modelsMap = new HashMap();
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        this.builderFactory = newInstance;
        newInstance.setNamespaceAware(true);
    }

    public void setBasedir(File file) {
        this.baseDir = file;
    }

    public void setDestdir(File file) {
        this.destDir = file;
    }

    public void setExtension(String str) {
        this.extension = str;
    }

    public void setTemplate(String str) {
        this.templateName = str;
    }

    public void setTemplateDir(File file) throws BuildException {
        this.templateDir = file;
        try {
            this.cfg.setDirectoryForTemplateLoading(file);
        } catch (Exception e) {
            throw new BuildException(e);
        }
    }

    public void setProjectfile(String str) {
        this.projectAttribute = str;
    }

    public void setIncremental(String str) {
        this.incremental = !str.equalsIgnoreCase("false") && !str.equalsIgnoreCase("no") && !str.equalsIgnoreCase("off");
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public void setTemplateEncoding(String str) {
        this.templateEncoding = str;
    }

    public void setValidation(boolean z) {
        this.validation = z;
    }

    public void setModels(String str) {
        this.models = str;
    }

    public void execute() throws BuildException {
        File file;
        if (this.baseDir == null) {
            this.baseDir = getProject().getBaseDir();
        }
        if (this.destDir != null) {
            if (this.templateDir == null) {
                if (this.templateName != null) {
                    file = new File(this.templateName);
                    if (!file.isAbsolute()) {
                        file = new File(getProject().getBaseDir(), this.templateName);
                    }
                    this.templateDir = file.getParentFile();
                    this.templateName = file.getName();
                } else {
                    this.templateDir = this.baseDir;
                    file = null;
                }
                setTemplateDir(this.templateDir);
            } else if (this.templateName == null) {
                file = null;
            } else if (!new File(this.templateName).isAbsolute()) {
                file = new File(this.templateDir, this.templateName);
            } else {
                throw new BuildException("Do not specify an absolute location for the template as well as a templateDir");
            }
            if (file != null) {
                this.templateFileLastModified = file.lastModified();
            }
            try {
                String str = this.templateName;
                if (str != null) {
                    this.parsedTemplate = this.cfg.getTemplate(str, this.templateEncoding);
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Transforming into: ");
                stringBuffer.append(this.destDir.getAbsolutePath());
                log(stringBuffer.toString(), 2);
                String str2 = this.projectAttribute;
                if (str2 != null && str2.length() > 0) {
                    File file2 = new File(this.baseDir, this.projectAttribute);
                    this.projectFile = file2;
                    if (file2.isFile()) {
                        this.projectFileLastModified = this.projectFile.lastModified();
                    } else {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Project file is defined, but could not be located: ");
                        stringBuffer2.append(this.projectFile.getAbsolutePath());
                        log(stringBuffer2.toString(), 2);
                        this.projectFile = null;
                    }
                }
                generateModels();
                DirectoryScanner directoryScanner = getDirectoryScanner(this.baseDir);
                this.propertiesTemplate = wrapMap(this.project.getProperties());
                this.userPropertiesTemplate = wrapMap(this.project.getUserProperties());
                this.builderFactory.setValidating(this.validation);
                try {
                    this.builder = this.builderFactory.newDocumentBuilder();
                    String[] includedFiles = directoryScanner.getIncludedFiles();
                    for (String process : includedFiles) {
                        process(this.baseDir, process, this.destDir);
                    }
                } catch (ParserConfigurationException e) {
                    throw new BuildException("Could not create document builder", e, getLocation());
                }
            } catch (IOException e2) {
                throw new BuildException(e2.toString());
            }
        } else {
            throw new BuildException("destdir attribute must be set!", getLocation());
        }
    }

    public void addConfiguredJython(JythonAntTask jythonAntTask) {
        this.prepareEnvironment = jythonAntTask;
    }

    public void addConfiguredPrepareModel(JythonAntTask jythonAntTask) {
        this.prepareModel = jythonAntTask;
    }

    public void addConfiguredPrepareEnvironment(JythonAntTask jythonAntTask) {
        this.prepareEnvironment = jythonAntTask;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void process(java.io.File r10, java.lang.String r11, java.io.File r12) throws org.apache.tools.ant.BuildException {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ SAXParseException -> 0x016c, all -> 0x013b }
            r2.<init>(r10, r11)     // Catch:{ SAXParseException -> 0x016c, all -> 0x013b }
            java.io.File r10 = new java.io.File     // Catch:{ SAXParseException -> 0x0138, all -> 0x013b }
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ SAXParseException -> 0x0138, all -> 0x013b }
            r3.<init>()     // Catch:{ SAXParseException -> 0x0138, all -> 0x013b }
            r4 = 46
            int r4 = r11.lastIndexOf(r4)     // Catch:{ SAXParseException -> 0x0138, all -> 0x013b }
            java.lang.String r4 = r11.substring(r0, r4)     // Catch:{ SAXParseException -> 0x0138, all -> 0x013b }
            r3.append(r4)     // Catch:{ SAXParseException -> 0x0138, all -> 0x013b }
            java.lang.String r4 = r9.extension     // Catch:{ SAXParseException -> 0x0138, all -> 0x013b }
            r3.append(r4)     // Catch:{ SAXParseException -> 0x0138, all -> 0x013b }
            java.lang.String r3 = r3.toString()     // Catch:{ SAXParseException -> 0x0138, all -> 0x013b }
            r10.<init>(r12, r3)     // Catch:{ SAXParseException -> 0x0138, all -> 0x013b }
            boolean r12 = r9.incremental     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            if (r12 == 0) goto L_0x004b
            long r3 = r2.lastModified()     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            long r5 = r10.lastModified()     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            int r12 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r12 > 0) goto L_0x004b
            long r3 = r9.templateFileLastModified     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            long r5 = r10.lastModified()     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            int r12 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r12 > 0) goto L_0x004b
            long r3 = r9.projectFileLastModified     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            long r5 = r10.lastModified()     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            int r12 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r12 <= 0) goto L_0x0127
        L_0x004b:
            r9.ensureDirectoryFor(r10)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.lang.StringBuffer r12 = new java.lang.StringBuffer     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r12.<init>()     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.lang.String r1 = "Input:  "
            r12.append(r1)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r12.append(r11)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.lang.String r11 = r12.toString()     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r12 = 2
            r9.log(r11, r12)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            freemarker.template.TemplateModel r11 = r9.projectTemplate     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            if (r11 != 0) goto L_0x0086
            java.io.File r11 = r9.projectFile     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            if (r11 == 0) goto L_0x0086
            javax.xml.parsers.DocumentBuilder r1 = r9.builder     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            org.w3c.dom.Document r11 = r1.parse(r11)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            freemarker.ext.xml.NodeListModel r1 = new freemarker.ext.xml.NodeListModel     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            javax.xml.parsers.DocumentBuilder r3 = r9.builder     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.io.File r4 = r9.projectFile     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            org.w3c.dom.Document r3 = r3.parse(r4)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r1.<init>(r3)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r9.projectTemplate = r1     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            freemarker.ext.dom.NodeModel r11 = freemarker.ext.dom.NodeModel.wrap(r11)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r9.projectNode = r11     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
        L_0x0086:
            javax.xml.parsers.DocumentBuilder r11 = r9.builder     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            org.w3c.dom.Document r11 = r11.parse(r2)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            freemarker.ext.xml.NodeListModel r1 = new freemarker.ext.xml.NodeListModel     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r1.<init>(r11)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            freemarker.ext.dom.NodeModel r3 = freemarker.ext.dom.NodeModel.wrap(r11)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r4.<init>()     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.lang.String r5 = "document"
            r4.put(r5, r1)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r9.insertDefaults(r4)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r6.<init>(r10)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.lang.String r7 = r9.encoding     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r5.<init>(r6, r7)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r1.<init>(r5)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            freemarker.template.Template r5 = r9.parsedTemplate     // Catch:{ all -> 0x0130 }
            if (r5 == 0) goto L_0x0128
            freemarker.ext.ant.JythonAntTask r5 = r9.prepareModel     // Catch:{ all -> 0x0130 }
            java.lang.String r6 = "project"
            java.lang.String r7 = "doc"
            if (r5 == 0) goto L_0x00de
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ all -> 0x0130 }
            r5.<init>()     // Catch:{ all -> 0x0130 }
            java.lang.String r8 = "model"
            r5.put(r8, r4)     // Catch:{ all -> 0x0130 }
            r5.put(r7, r11)     // Catch:{ all -> 0x0130 }
            freemarker.template.TemplateNodeModel r8 = r9.projectNode     // Catch:{ all -> 0x0130 }
            if (r8 == 0) goto L_0x00d9
            freemarker.ext.dom.NodeModel r8 = (freemarker.ext.dom.NodeModel) r8     // Catch:{ all -> 0x0130 }
            org.w3c.dom.Node r8 = r8.getNode()     // Catch:{ all -> 0x0130 }
            r5.put(r6, r8)     // Catch:{ all -> 0x0130 }
        L_0x00d9:
            freemarker.ext.ant.JythonAntTask r8 = r9.prepareModel     // Catch:{ all -> 0x0130 }
            r8.execute(r5)     // Catch:{ all -> 0x0130 }
        L_0x00de:
            freemarker.template.Template r5 = r9.parsedTemplate     // Catch:{ all -> 0x0130 }
            freemarker.core.Environment r4 = r5.createProcessingEnvironment(r4, r1)     // Catch:{ all -> 0x0130 }
            r4.setCurrentVisitorNode(r3)     // Catch:{ all -> 0x0130 }
            freemarker.ext.ant.JythonAntTask r3 = r9.prepareEnvironment     // Catch:{ all -> 0x0130 }
            if (r3 == 0) goto L_0x010a
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ all -> 0x0130 }
            r3.<init>()     // Catch:{ all -> 0x0130 }
            java.lang.String r5 = "env"
            r3.put(r5, r4)     // Catch:{ all -> 0x0130 }
            r3.put(r7, r11)     // Catch:{ all -> 0x0130 }
            freemarker.template.TemplateNodeModel r11 = r9.projectNode     // Catch:{ all -> 0x0130 }
            if (r11 == 0) goto L_0x0105
            freemarker.ext.dom.NodeModel r11 = (freemarker.ext.dom.NodeModel) r11     // Catch:{ all -> 0x0130 }
            org.w3c.dom.Node r11 = r11.getNode()     // Catch:{ all -> 0x0130 }
            r3.put(r6, r11)     // Catch:{ all -> 0x0130 }
        L_0x0105:
            freemarker.ext.ant.JythonAntTask r11 = r9.prepareEnvironment     // Catch:{ all -> 0x0130 }
            r11.execute(r3)     // Catch:{ all -> 0x0130 }
        L_0x010a:
            r4.process()     // Catch:{ all -> 0x0130 }
            r1.flush()     // Catch:{ all -> 0x0130 }
            r1.close()     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.lang.StringBuffer r11 = new java.lang.StringBuffer     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r11.<init>()     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.lang.String r1 = "Output: "
            r11.append(r1)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r11.append(r10)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            java.lang.String r11 = r11.toString()     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            r9.log(r11, r12)     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
        L_0x0127:
            return
        L_0x0128:
            org.apache.tools.ant.BuildException r11 = new org.apache.tools.ant.BuildException     // Catch:{ all -> 0x0130 }
            java.lang.String r12 = "No template file specified in build script or in XML file"
            r11.<init>(r12)     // Catch:{ all -> 0x0130 }
            throw r11     // Catch:{ all -> 0x0130 }
        L_0x0130:
            r11 = move-exception
            r1.close()     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
            throw r11     // Catch:{ SAXParseException -> 0x0138, all -> 0x0135 }
        L_0x0135:
            r11 = move-exception
            r1 = r10
            goto L_0x013c
        L_0x0138:
            r10 = move-exception
            r1 = r2
            goto L_0x016d
        L_0x013b:
            r11 = move-exception
        L_0x013c:
            if (r1 == 0) goto L_0x015f
            boolean r10 = r1.delete()
            if (r10 != 0) goto L_0x015f
            boolean r10 = r1.exists()
            if (r10 == 0) goto L_0x015f
            java.lang.StringBuffer r10 = new java.lang.StringBuffer
            r10.<init>()
            java.lang.String r12 = "Failed to delete "
            r10.append(r12)
            r10.append(r1)
            java.lang.String r10 = r10.toString()
            r12 = 1
            r9.log(r10, r12)
        L_0x015f:
            r11.printStackTrace()
            org.apache.tools.ant.BuildException r10 = new org.apache.tools.ant.BuildException
            org.apache.tools.ant.Location r12 = r9.getLocation()
            r10.<init>(r11, r12)
            throw r10
        L_0x016c:
            r10 = move-exception
        L_0x016d:
            java.lang.Exception r11 = r10.getException()
            if (r11 == 0) goto L_0x0178
            java.lang.Exception r11 = r10.getException()
            goto L_0x0179
        L_0x0178:
            r11 = r10
        L_0x0179:
            java.lang.StringBuffer r12 = new java.lang.StringBuffer
            r12.<init>()
            java.lang.String r2 = "XML parsing error in "
            r12.append(r2)
            java.lang.String r1 = r1.getAbsolutePath()
            r12.append(r1)
            java.lang.String r12 = r12.toString()
            r9.log(r12, r0)
            java.lang.StringBuffer r12 = new java.lang.StringBuffer
            r12.<init>()
            java.lang.String r0 = "Line number "
            r12.append(r0)
            int r0 = r10.getLineNumber()
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            r9.log(r12)
            java.lang.StringBuffer r12 = new java.lang.StringBuffer
            r12.<init>()
            java.lang.String r0 = "Column number "
            r12.append(r0)
            int r10 = r10.getColumnNumber()
            r12.append(r10)
            java.lang.String r10 = r12.toString()
            r9.log(r10)
            org.apache.tools.ant.BuildException r10 = new org.apache.tools.ant.BuildException
            org.apache.tools.ant.Location r12 = r9.getLocation()
            r10.<init>(r11, r12)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.ant.FreemarkerXmlTask.process(java.io.File, java.lang.String, java.io.File):void");
    }

    private void generateModels() {
        String str;
        StringTokenizer stringTokenizer = new StringTokenizer(this.models, ",; ");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf(61);
            if (indexOf == -1) {
                int lastIndexOf = nextToken.lastIndexOf(46);
                if (lastIndexOf == -1) {
                    str = nextToken;
                } else {
                    str = nextToken.substring(lastIndexOf + 1);
                }
            } else {
                String substring = nextToken.substring(0, indexOf);
                nextToken = nextToken.substring(indexOf + 1);
                str = substring;
            }
            try {
                this.modelsMap.put(str, ClassUtil.forName(nextToken).newInstance());
            } catch (Exception e) {
                throw new BuildException(e);
            }
        }
    }

    private void ensureDirectoryFor(File file) throws BuildException {
        File file2 = new File(file.getParent());
        if (!file2.exists() && !file2.mkdirs()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to create directory: ");
            stringBuffer.append(file2.getAbsolutePath());
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
    }

    private static TemplateModel wrapMap(Map map) {
        SimpleHash simpleHash = new SimpleHash();
        for (Map.Entry entry : map.entrySet()) {
            simpleHash.put(String.valueOf(entry.getKey()), (Object) new SimpleScalar(String.valueOf(entry.getValue())));
        }
        return simpleHash;
    }

    /* access modifiers changed from: protected */
    public void insertDefaults(Map map) {
        map.put("properties", this.propertiesTemplate);
        map.put("userProperties", this.userPropertiesTemplate);
        TemplateModel templateModel = this.projectTemplate;
        if (templateModel != null) {
            map.put("project", templateModel);
            map.put("project_node", this.projectNode);
        }
        if (this.modelsMap.size() > 0) {
            for (Map.Entry entry : this.modelsMap.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
    }
}
