package freemarker.ext.jython;

import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import org.python.core.Py;
import org.python.core.PyException;
import org.python.core.PyObject;

public class JythonNumberModel extends JythonModel implements TemplateNumberModel {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new JythonNumberModel((PyObject) obj, (JythonWrapper) objectWrapper);
        }
    };
    static /* synthetic */ Class class$java$lang$Number;

    public JythonNumberModel(PyObject pyObject, JythonWrapper jythonWrapper) {
        super(pyObject, jythonWrapper);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Number getAsNumber() throws TemplateModelException {
        try {
            PyObject pyObject = this.object;
            Class cls = class$java$lang$Number;
            if (cls == null) {
                cls = class$("java.lang.Number");
                class$java$lang$Number = cls;
            }
            Object __tojava__ = pyObject.__tojava__(cls);
            if (__tojava__ != null) {
                if (__tojava__ != Py.NoConversion) {
                    return (Number) __tojava__;
                }
            }
            return new Double(this.object.__float__().getValue());
        } catch (PyException e) {
            throw new TemplateModelException((Exception) e);
        }
    }
}
