package freemarker.ext.jython;

import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import org.python.core.PyException;
import org.python.core.PyObject;

public class JythonHashModel extends JythonModel implements TemplateHashModelEx {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new JythonHashModel((PyObject) obj, (JythonWrapper) objectWrapper);
        }
    };
    private static final String KEYS = "keys";
    private static final String KEYSET = "keySet";
    private static final String VALUES = "values";

    public JythonHashModel(PyObject pyObject, JythonWrapper jythonWrapper) {
        super(pyObject, jythonWrapper);
    }

    public int size() throws TemplateModelException {
        try {
            return this.object.__len__();
        } catch (PyException e) {
            throw new TemplateModelException((Exception) e);
        }
    }

    public TemplateCollectionModel keys() throws TemplateModelException {
        try {
            PyObject __findattr__ = this.object.__findattr__(KEYS);
            if (__findattr__ == null) {
                __findattr__ = this.object.__findattr__(KEYSET);
            }
            if (__findattr__ != null) {
                return (TemplateCollectionModel) this.wrapper.wrap(__findattr__.__call__());
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("'?keys' is not supported as there is no 'keys' nor 'keySet' attribute on an instance of ");
            stringBuffer.append(JythonVersionAdapterHolder.INSTANCE.getPythonClassName(this.object));
            throw new TemplateModelException(stringBuffer.toString());
        } catch (PyException e) {
            throw new TemplateModelException((Exception) e);
        }
    }

    public TemplateCollectionModel values() throws TemplateModelException {
        try {
            PyObject __findattr__ = this.object.__findattr__(VALUES);
            if (__findattr__ != null) {
                return (TemplateCollectionModel) this.wrapper.wrap(__findattr__.__call__());
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("'?values' is not supported as there is no 'values' attribute on an instance of ");
            stringBuffer.append(JythonVersionAdapterHolder.INSTANCE.getPythonClassName(this.object));
            throw new TemplateModelException(stringBuffer.toString());
        } catch (PyException e) {
            throw new TemplateModelException((Exception) e);
        }
    }
}
