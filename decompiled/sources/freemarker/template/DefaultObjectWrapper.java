package freemarker.template;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.dom.NodeModel;
import java.lang.reflect.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.w3c.dom.Node;

public class DefaultObjectWrapper extends BeansWrapper {
    private static Class JYTHON_OBJ_CLASS;
    private static ObjectWrapper JYTHON_WRAPPER;
    private static Class W3C_DOM_NODE_CLASS;
    static final DefaultObjectWrapper instance = new DefaultObjectWrapper();

    static {
        try {
            W3C_DOM_NODE_CLASS = Class.forName("org.w3c.dom.Node");
        } catch (Exception unused) {
        }
        try {
            JYTHON_OBJ_CLASS = Class.forName("org.python.core.PyObject");
            JYTHON_WRAPPER = (ObjectWrapper) Class.forName("freemarker.ext.jython.JythonWrapper").getField("INSTANCE").get((Object) null);
        } catch (Exception unused2) {
        }
    }

    public TemplateModel wrap(Object obj) throws TemplateModelException {
        if (obj == null) {
            return super.wrap((Object) null);
        }
        if (obj instanceof TemplateModel) {
            return (TemplateModel) obj;
        }
        if (obj instanceof String) {
            return new SimpleScalar((String) obj);
        }
        if (obj instanceof Number) {
            return new SimpleNumber((Number) obj);
        }
        if (!(obj instanceof Date)) {
            if (obj.getClass().isArray()) {
                obj = convertArray(obj);
            }
            if (obj instanceof Collection) {
                return new SimpleSequence((Collection) obj, this);
            }
            if (obj instanceof Map) {
                return new SimpleHash((Map) obj, this);
            }
            if (obj instanceof Boolean) {
                return obj.equals(Boolean.TRUE) ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
            }
            if (obj instanceof Iterator) {
                return new SimpleCollection((Iterator) obj, (ObjectWrapper) this);
            }
            return handleUnknownType(obj);
        } else if (obj instanceof java.sql.Date) {
            return new SimpleDate((java.sql.Date) obj);
        } else {
            if (obj instanceof Time) {
                return new SimpleDate((Time) obj);
            }
            if (obj instanceof Timestamp) {
                return new SimpleDate((Timestamp) obj);
            }
            return new SimpleDate((Date) obj, getDefaultDateType());
        }
    }

    /* access modifiers changed from: protected */
    public TemplateModel handleUnknownType(Object obj) throws TemplateModelException {
        Class cls = W3C_DOM_NODE_CLASS;
        if (cls != null && cls.isInstance(obj)) {
            return wrapDomNode(obj);
        }
        if (JYTHON_WRAPPER == null || !JYTHON_OBJ_CLASS.isInstance(obj)) {
            return super.wrap(obj);
        }
        return JYTHON_WRAPPER.wrap(obj);
    }

    public TemplateModel wrapDomNode(Object obj) {
        return NodeModel.wrap((Node) obj);
    }

    /* access modifiers changed from: protected */
    public Object convertArray(Object obj) {
        int length = Array.getLength(obj);
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(Array.get(obj, i));
        }
        return arrayList;
    }
}
