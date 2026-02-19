package freemarker.core;

import freemarker.template.TemplateException;
import kotlin.text.Typography;

public final class ReturnInstruction extends TemplateElement {
    private Expression exp;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#return";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    ReturnInstruction(Expression expression) {
        this.exp = expression;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException {
        Expression expression = this.exp;
        if (expression != null) {
            environment.setLastReturnValue(expression.eval(environment));
        }
        if (nextSibling() != null) {
            throw Return.INSTANCE;
        } else if (!(getParent() instanceof Macro) && !(getParent().getParent() instanceof Macro)) {
            throw Return.INSTANCE;
        }
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

    public static class Return extends RuntimeException {
        static final Return INSTANCE = new Return();

        private Return() {
        }
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
            return ParameterRole.VALUE;
        }
        throw new IndexOutOfBoundsException();
    }
}
