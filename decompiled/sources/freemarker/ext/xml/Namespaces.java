package freemarker.ext.xml;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import java.util.HashMap;
import java.util.List;

class Namespaces implements TemplateMethodModel, Cloneable {
    private HashMap namespaces;
    private boolean shared = false;

    Namespaces() {
        HashMap hashMap = new HashMap();
        this.namespaces = hashMap;
        hashMap.put("", "");
        this.namespaces.put("xml", "http://www.w3.org/XML/1998/namespace");
    }

    public Object clone() {
        try {
            Namespaces namespaces2 = (Namespaces) super.clone();
            namespaces2.namespaces = (HashMap) this.namespaces.clone();
            namespaces2.shared = false;
            return namespaces2;
        } catch (CloneNotSupportedException unused) {
            throw new Error();
        }
    }

    public String translateNamespacePrefixToUri(String str) {
        String str2;
        synchronized (this.namespaces) {
            str2 = (String) this.namespaces.get(str);
        }
        return str2;
    }

    public Object exec(List list) throws TemplateModelException {
        if (list.size() == 2) {
            registerNamespace((String) list.get(0), (String) list.get(1));
            return TemplateScalarModel.EMPTY_STRING;
        }
        throw new TemplateModelException("_registerNamespace(prefix, uri) requires two arguments");
    }

    /* access modifiers changed from: package-private */
    public void registerNamespace(String str, String str2) {
        synchronized (this.namespaces) {
            this.namespaces.put(str, str2);
        }
    }

    /* access modifiers changed from: package-private */
    public void markShared() {
        if (!this.shared) {
            this.shared = true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isShared() {
        return this.shared;
    }
}
