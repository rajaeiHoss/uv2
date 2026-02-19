package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

final class ParentheticalExpression extends Expression {
    private final Expression nested;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "(...)";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    ParentheticalExpression(Expression expression) {
        this.nested = expression;
    }

    /* access modifiers changed from: package-private */
    public boolean evalToBoolean(Environment environment) throws TemplateException {
        return this.nested.evalToBoolean(environment);
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(");
        stringBuffer.append(this.nested.getCanonicalForm());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        return this.nested.eval(environment);
    }

    public boolean isLiteral() {
        return this.nested.isLiteral();
    }

    /* access modifiers changed from: package-private */
    public Expression getNestedExpression() {
        return this.nested;
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new ParentheticalExpression(this.nested.deepCloneWithIdentifierReplaced(str, expression, replacemenetState));
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.nested;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.ENCLOSED_OPERAND;
        }
        throw new IndexOutOfBoundsException();
    }
}
