package freemarker.core;

import kotlin.text.Typography;

final class TrimInstruction extends TemplateElement {
    private final int TYPE_LT = 1;
    private final int TYPE_NT = 3;
    private final int TYPE_RT = 2;
    private final int TYPE_T = 0;
    final boolean left;
    final boolean right;

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) {
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public boolean isIgnorable() {
        return true;
    }

    TrimInstruction(boolean z, boolean z2) {
        this.left = z;
        this.right = z2;
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        if (z) {
            stringBuffer.append("/>");
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        boolean z = this.left;
        if (z && this.right) {
            return "#t";
        }
        if (z) {
            return "#lt";
        }
        return this.right ? "#rt" : "#nt";
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            boolean z = this.left;
            return new Integer((!z || !this.right) ? z ? 1 : this.right ? 2 : 3 : 0);
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.AST_NODE_SUBTYPE;
        }
        throw new IndexOutOfBoundsException();
    }
}
