package freemarker.core;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.ArrayList;

final class MixedContent extends TemplateElement {
    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#mixed_content";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isShownInStackTrace() {
        return false;
    }

    MixedContent() {
        this.nestedElements = new ArrayList();
    }

    /* access modifiers changed from: package-private */
    public void addElement(TemplateElement templateElement) {
        this.nestedElements.add(templateElement);
    }

    /* access modifiers changed from: package-private */
    public TemplateElement postParseCleanup(boolean z) throws ParseException {
        super.postParseCleanup(z);
        return this.nestedElements.size() == 1 ? (TemplateElement) this.nestedElements.get(0) : this;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        for (int i = 0; i < this.nestedElements.size(); i++) {
            environment.visit((TemplateElement) this.nestedElements.get(i));
        }
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        if (z) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < this.nestedElements.size(); i++) {
                stringBuffer.append(((TemplateElement) this.nestedElements.get(i)).getCanonicalForm());
            }
            return stringBuffer.toString();
        } else if (this.parent == null) {
            return "root";
        } else {
            return getNodeTypeSymbol();
        }
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public boolean isIgnorable() {
        return this.nestedElements == null || this.nestedElements.size() == 0;
    }
}
