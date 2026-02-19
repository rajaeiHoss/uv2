package freemarker.ext.beans;

import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelAdapter;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.utility.UndeclaredThrowableException;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashAdapter extends AbstractMap implements TemplateModelAdapter {
    private Set entrySet;
    private final TemplateHashModel model;
    /* access modifiers changed from: private */
    public final BeansWrapper wrapper;

    HashAdapter(TemplateHashModel templateHashModel, BeansWrapper beansWrapper) {
        this.model = templateHashModel;
        this.wrapper = beansWrapper;
    }

    public TemplateModel getTemplateModel() {
        return this.model;
    }

    public boolean isEmpty() {
        try {
            return this.model.isEmpty();
        } catch (TemplateModelException e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    public Object get(Object obj) {
        try {
            return this.wrapper.unwrap(this.model.get(String.valueOf(obj)));
        } catch (TemplateModelException e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    public boolean containsKey(Object obj) {
        if (get(obj) != null) {
            return true;
        }
        return super.containsKey(obj);
    }

    public Set entrySet() {
        Set set = this.entrySet;
        if (set != null) {
            return set;
        }
        AnonymousClass1 r0 = new AbstractSet() {
            public Iterator iterator() {
                try {
                    final TemplateModelIterator it = HashAdapter.this.getModelEx().keys().iterator();
                    return new Iterator() {
                        public boolean hasNext() {
                            try {
                                return it.hasNext();
                            } catch (TemplateModelException e) {
                                throw new UndeclaredThrowableException(e);
                            }
                        }

                        public Object next() {
                            try {
                                final Object unwrap = HashAdapter.this.wrapper.unwrap(it.next());
                                return new Map.Entry() {
                                    public Object getKey() {
                                        return unwrap;
                                    }

                                    public Object getValue() {
                                        return HashAdapter.this.get(unwrap);
                                    }

                                    public Object setValue(Object obj) {
                                        throw new UnsupportedOperationException();
                                    }

                                    public boolean equals(Object obj) {
                                        if (!(obj instanceof Map.Entry)) {
                                            return false;
                                        }
                                        Map.Entry entry = (Map.Entry) obj;
                                        Object key = getKey();
                                        Object key2 = entry.getKey();
                                        if (key == key2 || (key != null && key.equals(key2))) {
                                            Object value = getValue();
                                            Object value2 = entry.getValue();
                                            if (value == value2) {
                                                return true;
                                            }
                                            if (value == null || !value.equals(value2)) {
                                                return false;
                                            }
                                            return true;
                                        }
                                        return false;
                                    }

                                    public int hashCode() {
                                        Object value = getValue();
                                        Object obj = unwrap;
                                        int i = 0;
                                        int hashCode = obj == null ? 0 : obj.hashCode();
                                        if (value != null) {
                                            i = value.hashCode();
                                        }
                                        return hashCode ^ i;
                                    }
                                };
                            } catch (TemplateModelException e) {
                                throw new UndeclaredThrowableException(e);
                            }
                        }

                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    };
                } catch (TemplateModelException e) {
                    throw new UndeclaredThrowableException(e);
                }
            }

            public int size() {
                try {
                    return HashAdapter.this.getModelEx().size();
                } catch (TemplateModelException e) {
                    throw new UndeclaredThrowableException(e);
                }
            }
        };
        this.entrySet = r0;
        return r0;
    }

    /* access modifiers changed from: private */
    public TemplateHashModelEx getModelEx() {
        TemplateHashModel templateHashModel = this.model;
        if (templateHashModel instanceof TemplateHashModelEx) {
            return (TemplateHashModelEx) templateHashModel;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Operation supported only on TemplateHashModelEx. ");
        stringBuffer.append(this.model.getClass().getName());
        stringBuffer.append(" does not implement it though.");
        throw new UnsupportedOperationException(stringBuffer.toString());
    }
}
