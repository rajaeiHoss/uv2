package freemarker.core;

import java.io.IOException;

final class FlushInstruction extends TemplateElement {
    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#flush";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    FlushInstruction() {
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws IOException {
        environment.getOut().flush();
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
