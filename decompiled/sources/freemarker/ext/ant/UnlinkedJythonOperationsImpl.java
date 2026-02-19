package freemarker.ext.ant;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.tools.ant.BuildException;
import org.python.util.PythonInterpreter;

public class UnlinkedJythonOperationsImpl implements UnlinkedJythonOperations {
    public void execute(String str, Map map) throws BuildException {
        createInterpreter(map).exec(str);
    }

    public void execute(File file, Map map) throws BuildException {
        try {
            createInterpreter(map).execfile(file.getCanonicalPath());
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }

    private PythonInterpreter createInterpreter(Map map) {
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        for (Map.Entry entry : map.entrySet()) {
            pythonInterpreter.set((String) entry.getKey(), entry.getValue());
        }
        return pythonInterpreter;
    }
}
