package freemarker.ext.jython;

import org.python.core.PyObject;

public abstract class JythonVersionAdapter {
    public abstract String getPythonClassName(PyObject pyObject);

    public abstract boolean isPyInstance(Object obj);

    public abstract Object pyInstanceToJava(Object obj);
}
