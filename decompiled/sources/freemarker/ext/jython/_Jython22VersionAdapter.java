package freemarker.ext.jython;

import org.python.core.PyJavaInstance;
import org.python.core.PyObject;

public class _Jython22VersionAdapter extends JythonVersionAdapter {
    static /* synthetic */ Class class$java$lang$Object;

    public boolean isPyInstance(Object obj) {
        return obj instanceof PyJavaInstance;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Object pyInstanceToJava(Object obj) {
        PyJavaInstance pyJavaInstance = (PyJavaInstance) obj;
        Class cls = class$java$lang$Object;
        if (cls == null) {
            cls = class$("java.lang.Object");
            class$java$lang$Object = cls;
        }
        return pyJavaInstance.__tojava__(cls);
    }

    public String getPythonClassName(PyObject pyObject) {
        return pyObject.getType().getFullName();
    }
}
