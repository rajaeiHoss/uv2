package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.TemplateException;

final class NotExpression extends BooleanExpression {
    private final Expression target;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "!";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    NotExpression(Expression expression) {
        this.target = expression;
    }

    /* access modifiers changed from: package-private */
    public boolean evalToBoolean(Environment environment) throws TemplateException {
        return !this.target.evalToBoolean(environment);
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("!");
        stringBuffer.append(this.target.getCanonicalForm());
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return this.target.isLiteral();
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new NotExpression(this.target.deepCloneWithIdentifierReplaced(str, expression, replacemenetState));
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.target;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.RIGHT_HAND_OPERAND;
        }
        throw new IndexOutOfBoundsException();
    }
}
