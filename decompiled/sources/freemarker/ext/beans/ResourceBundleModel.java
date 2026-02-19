package freemarker.ext.beans;

import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

public class ResourceBundleModel extends BeanModel implements TemplateMethodModelEx {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new ResourceBundleModel((ResourceBundle) obj, (BeansWrapper) objectWrapper);
        }
    };
    private Hashtable formats = null;

    public ResourceBundleModel(ResourceBundle resourceBundle, BeansWrapper beansWrapper) {
        super(resourceBundle, beansWrapper);
    }

    /* access modifiers changed from: protected */
    public TemplateModel invokeGenericGet(Map map, Class cls, String str) throws TemplateModelException {
        try {
            return wrap(((ResourceBundle) this.object).getObject(str));
        } catch (MissingResourceException unused) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No such key: ");
            stringBuffer.append(str);
            throw new TemplateModelException(stringBuffer.toString());
        }
    }

    public boolean isEmpty() {
        return !((ResourceBundle) this.object).getKeys().hasMoreElements() && super.isEmpty();
    }

    public int size() {
        return keySet().size();
    }

    /* access modifiers changed from: protected */
    public Set keySet() {
        Set keySet = super.keySet();
        Enumeration<String> keys = ((ResourceBundle) this.object).getKeys();
        while (keys.hasMoreElements()) {
            keySet.add(keys.nextElement());
        }
        return keySet;
    }

    public Object exec(List list) throws TemplateModelException {
        if (list.size() >= 1) {
            Iterator it = list.iterator();
            String obj = unwrap((TemplateModel) it.next()).toString();
            try {
                if (!it.hasNext()) {
                    return wrap(((ResourceBundle) this.object).getObject(obj));
                }
                int size = list.size() - 1;
                Object[] objArr = new Object[size];
                for (int i = 0; i < size; i++) {
                    objArr[i] = unwrap((TemplateModel) it.next());
                }
                return new StringModel(format(obj, objArr), this.wrapper);
            } catch (MissingResourceException unused) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("No such key: ");
                stringBuffer.append(obj);
                throw new TemplateModelException(stringBuffer.toString());
            } catch (Exception e) {
                throw new TemplateModelException(e.getMessage());
            }
        } else {
            throw new TemplateModelException("No message key was specified");
        }
    }

    public String format(String str, Object[] objArr) throws MissingResourceException {
        String format;
        if (this.formats == null) {
            this.formats = new Hashtable();
        }
        MessageFormat messageFormat = (MessageFormat) this.formats.get(str);
        if (messageFormat == null) {
            messageFormat = new MessageFormat(((ResourceBundle) this.object).getString(str));
            messageFormat.setLocale(getBundle().getLocale());
            this.formats.put(str, messageFormat);
        }
        synchronized (messageFormat) {
            format = messageFormat.format(objArr);
        }
        return format;
    }

    public ResourceBundle getBundle() {
        return (ResourceBundle) this.object;
    }
}
