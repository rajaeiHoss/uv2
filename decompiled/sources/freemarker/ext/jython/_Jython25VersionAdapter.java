package freemarker.ext.jython;

import org.python.core.PyInstance;
import org.python.core.PyObject;

public class _Jython25VersionAdapter extends JythonVersionAdapter {
    static /* synthetic */ Class class$java$lang$Object;

    public boolean isPyInstance(Object obj) {
        return obj instanceof PyInstance;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Object pyInstanceToJava(Object obj) {
        PyInstance pyInstance = (PyInstance) obj;
        Class cls = class$java$lang$Object;
        if (cls == null) {
            cls = class$("java.lang.Object");
            class$java$lang$Object = cls;
        }
        return pyInstance.__tojava__(cls);
    }

    public String getPythonClassName(PyObject pyObject) {
        return pyObject.getType().getName();
    }
}
