package freemarker.ext.jython;

import freemarker.ext.util.ModelCache;
import freemarker.ext.util.WrapperTemplateModel;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelAdapter;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.OptimizerUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import org.python.core.Py;
import org.python.core.PyInteger;
import org.python.core.PyLong;
import org.python.core.PyObject;
import org.python.core.PyString;

public class JythonWrapper implements ObjectWrapper {
    public static final JythonWrapper INSTANCE = new JythonWrapper();
    private static final Class PYOBJECT_CLASS;
    static /* synthetic */ Class class$org$python$core$PyObject;
    private boolean attributesShadowItems = true;
    private final ModelCache modelCache = new JythonModelCache(this);

    static {
        Class cls = class$org$python$core$PyObject;
        if (cls == null) {
            cls = class$("org.python.core.PyObject");
            class$org$python$core$PyObject = cls;
        }
        PYOBJECT_CLASS = cls;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public void setUseCache(boolean z) {
        this.modelCache.setUseCache(z);
    }

    public synchronized void setAttributesShadowItems(boolean z) {
        this.attributesShadowItems = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isAttributesShadowItems() {
        return this.attributesShadowItems;
    }

    public TemplateModel wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        return this.modelCache.getInstance(obj);
    }

    public PyObject unwrap(TemplateModel templateModel) throws TemplateModelException {
        if (templateModel instanceof AdapterTemplateModel) {
            return Py.java2py(((AdapterTemplateModel) templateModel).getAdaptedObject(PYOBJECT_CLASS));
        }
        if (templateModel instanceof WrapperTemplateModel) {
            return Py.java2py(((WrapperTemplateModel) templateModel).getWrappedObject());
        }
        if (templateModel instanceof TemplateScalarModel) {
            return new PyString(((TemplateScalarModel) templateModel).getAsString());
        }
        if (!(templateModel instanceof TemplateNumberModel)) {
            return new TemplateModelToJythonAdapter(templateModel);
        }
        Number asNumber = ((TemplateNumberModel) templateModel).getAsNumber();
        if (asNumber instanceof BigDecimal) {
            asNumber = OptimizerUtil.optimizeNumberRepresentation(asNumber);
        }
        if (asNumber instanceof BigInteger) {
            return new PyLong((BigInteger) asNumber);
        }
        return Py.java2py(asNumber);
    }

    private class TemplateModelToJythonAdapter extends PyObject implements TemplateModelAdapter {
        private final TemplateModel model;

        TemplateModelToJythonAdapter(TemplateModel templateModel) {
            this.model = templateModel;
        }

        public TemplateModel getTemplateModel() {
            return this.model;
        }

        public PyObject __finditem__(PyObject pyObject) {
            if (pyObject instanceof PyInteger) {
                return __finditem__(((PyInteger) pyObject).getValue());
            }
            return __finditem__(pyObject.toString());
        }

        public PyObject __finditem__(String str) {
            TemplateModel templateModel = this.model;
            if (templateModel instanceof TemplateHashModel) {
                try {
                    return JythonWrapper.this.unwrap(((TemplateHashModel) templateModel).get(str));
                } catch (TemplateModelException e) {
                    throw Py.JavaError(e);
                }
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("item lookup on non-hash model (");
                stringBuffer.append(getModelClass());
                stringBuffer.append(")");
                throw Py.TypeError(stringBuffer.toString());
            }
        }

        public PyObject __finditem__(int i) {
            TemplateModel templateModel = this.model;
            if (templateModel instanceof TemplateSequenceModel) {
                try {
                    return JythonWrapper.this.unwrap(((TemplateSequenceModel) templateModel).get(i));
                } catch (TemplateModelException e) {
                    throw Py.JavaError(e);
                }
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("item lookup on non-sequence model (");
                stringBuffer.append(getModelClass());
                stringBuffer.append(")");
                throw Py.TypeError(stringBuffer.toString());
            }
        }

        public PyObject __call__(PyObject[] pyObjectArr, String[] strArr) {
            TemplateModel templateModel = this.model;
            if (templateModel instanceof TemplateMethodModel) {
                boolean z = templateModel instanceof TemplateMethodModelEx;
                ArrayList arrayList = new ArrayList(pyObjectArr.length);
                int i = 0;
                while (i < pyObjectArr.length) {
                    try {
                        arrayList.add(z ? JythonWrapper.this.wrap(pyObjectArr[i]) : pyObjectArr[i] == null ? null : pyObjectArr[i].toString());
                        i++;
                    } catch (TemplateModelException e) {
                        throw Py.JavaError(e);
                    }
                }
                return JythonWrapper.this.unwrap((TemplateModel) ((TemplateMethodModelEx) this.model).exec(arrayList));
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("call of non-method model (");
            stringBuffer.append(getModelClass());
            stringBuffer.append(")");
            throw Py.TypeError(stringBuffer.toString());
        }

        public int __len__() {
            try {
                TemplateModel templateModel = this.model;
                if (templateModel instanceof TemplateSequenceModel) {
                    return ((TemplateSequenceModel) templateModel).size();
                }
                if (templateModel instanceof TemplateHashModelEx) {
                    return ((TemplateHashModelEx) templateModel).size();
                }
                return 0;
            } catch (TemplateModelException e) {
                throw Py.JavaError(e);
            }
        }

        public boolean __nonzero__() {
            try {
                TemplateModel templateModel = this.model;
                if (templateModel instanceof TemplateBooleanModel) {
                    return ((TemplateBooleanModel) templateModel).getAsBoolean();
                }
                if (templateModel instanceof TemplateSequenceModel) {
                    if (((TemplateSequenceModel) templateModel).size() > 0) {
                        return true;
                    }
                    return false;
                } else if (templateModel instanceof TemplateHashModel) {
                    return !((TemplateHashModelEx) templateModel).isEmpty();
                } else {
                    return false;
                }
            } catch (TemplateModelException e) {
                throw Py.JavaError(e);
            }
        }

        private String getModelClass() {
            TemplateModel templateModel = this.model;
            return templateModel == null ? "null" : templateModel.getClass().getName();
        }
    }
}
