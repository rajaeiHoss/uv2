package freemarker.core;

import freemarker.template.TemplateException;
import freemarker.template.utility.StandardCompress;
import java.io.IOException;
import java.util.Map;

final class CompressedBlock extends TemplateElement {
    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#compress";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    CompressedBlock(TemplateElement templateElement) {
        this.nestedBlock = templateElement;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        if (this.nestedBlock != null) {
            environment.visitAndTransform(this.nestedBlock, StandardCompress.INSTANCE, (Map) null);
        }
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        if (!z) {
            return getNodeTypeSymbol();
        }
        String canonicalForm = this.nestedBlock != null ? this.nestedBlock.getCanonicalForm() : "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<");
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(">");
        stringBuffer.append(canonicalForm);
        stringBuffer.append("</");
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(">");
        return stringBuffer.toString();
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
        return this.nestedBlock == null || this.nestedBlock.isIgnorable();
    }
}
