package freemarker.ext.ant;

import freemarker.template.utility.ClassUtil;
import java.io.File;
import java.util.Map;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Task;

public class JythonAntTask extends Task {
    private UnlinkedJythonOperations jythonOps;
    private String script = "";
    private File scriptFile;

    public void setFile(File file) throws BuildException {
        ensureJythonOpsExists();
        this.scriptFile = file;
    }

    public void addText(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.script);
        stringBuffer.append(str);
        this.script = stringBuffer.toString();
    }

    public void execute(Map map) throws BuildException {
        if (this.scriptFile != null) {
            ensureJythonOpsExists();
            this.jythonOps.execute(this.scriptFile, map);
        }
        if (this.script.trim().length() > 0) {
            ensureJythonOpsExists();
            this.jythonOps.execute(ProjectHelper.replaceProperties(this.project, this.script, this.project.getProperties()), map);
        }
    }

    private void ensureJythonOpsExists() {
        if (this.jythonOps == null) {
            try {
                try {
                    this.jythonOps = (UnlinkedJythonOperations) ClassUtil.forName("freemarker.ext.ant.UnlinkedJythonOperationsImpl").newInstance();
                } catch (Exception e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("An exception has been thrown when trying to create a freemarker.ext.ant.JythonAntTask object. The exception was: ");
                    stringBuffer.append(e);
                    throw new RuntimeException(stringBuffer.toString());
                }
            } catch (ClassNotFoundException e2) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("A ClassNotFoundException has been thrown when trying to get the freemarker.ext.ant.UnlinkedJythonOperationsImpl class. The error message was: ");
                stringBuffer2.append(e2.getMessage());
                throw new RuntimeException(stringBuffer2.toString());
            }
        }
    }
}
