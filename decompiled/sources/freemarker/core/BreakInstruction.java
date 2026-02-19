package freemarker.core;

final class BreakInstruction extends TemplateElement {
    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#break";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    BreakInstruction() {
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) {
        throw Break.INSTANCE;
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

    static class Break extends RuntimeException {
        static final Break INSTANCE = new Break();

        private Break() {
        }
    }
}
