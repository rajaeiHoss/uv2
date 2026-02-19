package freemarker.core;

import freemarker.template.TemplateException;
import java.io.IOException;
import kotlin.text.Typography;

class NoEscapeBlock extends TemplateElement {
    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#noescape";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    NoEscapeBlock(TemplateElement templateElement) {
        this.nestedBlock = templateElement;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        if (this.nestedBlock != null) {
            environment.visit(this.nestedBlock);
        }
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        if (!z) {
            return getNodeTypeSymbol();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<");
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(Typography.greater);
        stringBuffer.append(this.nestedBlock.getCanonicalForm());
        stringBuffer.append("</");
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(Typography.greater);
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
}
