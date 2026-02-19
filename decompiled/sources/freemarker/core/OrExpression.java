package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.TemplateException;

final class OrExpression extends BooleanExpression {
    private final Expression lho;
    private final Expression rho;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "||";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    OrExpression(Expression expression, Expression expression2) {
        this.lho = expression;
        this.rho = expression2;
    }

    /* access modifiers changed from: package-private */
    public boolean evalToBoolean(Environment environment) throws TemplateException {
        return this.lho.evalToBoolean(environment) || this.rho.evalToBoolean(environment);
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.lho.getCanonicalForm());
        stringBuffer.append(" || ");
        stringBuffer.append(this.rho.getCanonicalForm());
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return this.constantValue != null || (this.lho.isLiteral() && this.rho.isLiteral());
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new OrExpression(this.lho.deepCloneWithIdentifierReplaced(str, expression, replacemenetState), this.rho.deepCloneWithIdentifierReplaced(str, expression, replacemenetState));
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.lho;
        }
        if (i == 1) {
            return this.rho;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        return ParameterRole.forBinaryOperatorOperand(i);
    }
}
