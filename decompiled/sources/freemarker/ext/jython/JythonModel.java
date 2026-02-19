package freemarker.ext.jython;

import freemarker.ext.util.ModelFactory;
import freemarker.ext.util.WrapperTemplateModel;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import java.util.Iterator;
import java.util.List;
import org.python.core.Py;
import org.python.core.PyException;
import org.python.core.PyObject;

public class JythonModel implements TemplateBooleanModel, TemplateScalarModel, TemplateHashModel, TemplateMethodModelEx, AdapterTemplateModel, WrapperTemplateModel {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new JythonModel((PyObject) obj, (JythonWrapper) objectWrapper);
        }
    };
    static /* synthetic */ Class class$java$lang$Object;
    protected final PyObject object;
    protected final JythonWrapper wrapper;

    public JythonModel(PyObject pyObject, JythonWrapper jythonWrapper) {
        this.object = pyObject;
        this.wrapper = jythonWrapper;
    }

    public boolean getAsBoolean() throws TemplateModelException {
        try {
            return this.object.__nonzero__();
        } catch (PyException e) {
            throw new TemplateModelException((Exception) e);
        }
    }

    public String getAsString() throws TemplateModelException {
        try {
            return this.object.toString();
        } catch (PyException e) {
            throw new TemplateModelException((Exception) e);
        }
    }

    public TemplateModel get(String str) throws TemplateModelException {
        PyObject pyObject;
        if (str != null) {
            str = str.intern();
        }
        try {
            if (this.wrapper.isAttributesShadowItems()) {
                pyObject = this.object.__findattr__(str);
                if (pyObject == null) {
                    pyObject = this.object.__finditem__(str);
                }
            } else {
                pyObject = this.object.__finditem__(str);
                if (pyObject == null) {
                    pyObject = this.object.__findattr__(str);
                }
            }
            return this.wrapper.wrap(pyObject);
        } catch (PyException e) {
            throw new TemplateModelException((Exception) e);
        }
    }

    public boolean isEmpty() throws TemplateModelException {
        try {
            return this.object.__len__() == 0;
        } catch (PyException e) {
            throw new TemplateModelException((Exception) e);
        }
    }

    public Object exec(List list) throws TemplateModelException {
        int size = list.size();
        if (size == 0) {
            return this.wrapper.wrap(this.object.__call__());
        }
        int i = 0;
        if (size != 1) {
            try {
                PyObject[] pyObjectArr = new PyObject[size];
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    pyObjectArr[i] = this.wrapper.unwrap((TemplateModel) it.next());
                    i++;
                }
                return this.wrapper.wrap(this.object.__call__(pyObjectArr));
            } catch (PyException e) {
                throw new TemplateModelException((Exception) e);
            }
        } else {
            JythonWrapper jythonWrapper = this.wrapper;
            return jythonWrapper.wrap(this.object.__call__(jythonWrapper.unwrap((TemplateModel) list.get(0))));
        }
    }

    public Object getAdaptedObject(Class cls) {
        PyObject pyObject = this.object;
        if (pyObject == null) {
            return null;
        }
        Object __tojava__ = pyObject.__tojava__(cls);
        if (__tojava__ != Py.NoConversion) {
            return __tojava__;
        }
        PyObject pyObject2 = this.object;
        Class cls2 = class$java$lang$Object;
        if (cls2 == null) {
            cls2 = class$("java.lang.Object");
            class$java$lang$Object = cls2;
        }
        return pyObject2.__tojava__(cls2);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Object getWrappedObject() {
        PyObject pyObject = this.object;
        if (pyObject == null) {
            return null;
        }
        Class cls = class$java$lang$Object;
        if (cls == null) {
            cls = class$("java.lang.Object");
            class$java$lang$Object = cls;
        }
        return pyObject.__tojava__(cls);
    }
}
