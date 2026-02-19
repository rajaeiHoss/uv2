package freemarker.ext.ant;

import java.io.File;
import java.util.Map;
import org.apache.tools.ant.BuildException;

interface UnlinkedJythonOperations {
    void execute(File file, Map map) throws BuildException;

    void execute(String str, Map map) throws BuildException;
}
