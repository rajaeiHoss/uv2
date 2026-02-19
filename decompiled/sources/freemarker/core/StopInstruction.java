package freemarker.core;

import freemarker.template.TemplateException;
import kotlin.text.Typography;

final class StopInstruction extends TemplateElement {
    private Expression exp;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#stop";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    StopInstruction(Expression expression) {
        this.exp = expression;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException {
        if (this.exp == null) {
            throw new StopException(environment);
        }
        throw new StopException(environment, this.exp.evalAndCoerceToString(environment));
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        if (this.exp != null) {
            stringBuffer.append(' ');
            stringBuffer.append(this.exp.getCanonicalForm());
        }
        if (z) {
            stringBuffer.append("/>");
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.exp;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.MESSAGE;
        }
        throw new IndexOutOfBoundsException();
    }
}
