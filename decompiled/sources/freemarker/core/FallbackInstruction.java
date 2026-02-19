package freemarker.core;

import freemarker.template.TemplateException;
import java.io.IOException;

final class FallbackInstruction extends TemplateElement {
    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#fallback";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    FallbackInstruction() {
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws IOException, TemplateException {
        environment.fallback();
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        if (!z) {
            return getNodeTypeSymbol();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<");
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append("/>");
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
