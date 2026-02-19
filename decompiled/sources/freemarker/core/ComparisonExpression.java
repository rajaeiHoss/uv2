package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.TemplateException;

final class ComparisonExpression extends BooleanExpression {
    private final Expression left;
    private final String opString;
    private final int operation;
    private final Expression right;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    ComparisonExpression(Expression expression, Expression expression2, String str) {
        this.left = expression;
        this.right = expression2;
        String intern = str.intern();
        this.opString = intern;
        if (intern == "==" || intern == "=") {
            this.operation = 1;
        } else if (intern == "!=") {
            this.operation = 2;
        } else if (intern == "gt" || intern == "\\gt" || intern == ">" || intern == "&gt;") {
            this.operation = 4;
        } else if (intern == "gte" || intern == "\\gte" || intern == ">=" || intern == "&gt;=") {
            this.operation = 6;
        } else if (intern == "lt" || intern == "\\lt" || intern == "<" || intern == "&lt;") {
            this.operation = 3;
        } else if (intern == "lte" || intern == "\\lte" || intern == "<=" || intern == "&lt;=") {
            this.operation = 5;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unknown comparison operator ");
            stringBuffer.append(intern);
            throw new RuntimeException(stringBuffer.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean evalToBoolean(Environment environment) throws TemplateException {
        return EvalUtil.compare(this.left, this.operation, this.opString, this.right, this, environment);
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.left.getCanonicalForm());
        stringBuffer.append(' ');
        stringBuffer.append(this.opString);
        stringBuffer.append(' ');
        stringBuffer.append(this.right.getCanonicalForm());
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return this.opString;
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return this.constantValue != null || (this.left.isLiteral() && this.right.isLiteral());
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new ComparisonExpression(this.left.deepCloneWithIdentifierReplaced(str, expression, replacemenetState), this.right.deepCloneWithIdentifierReplaced(str, expression, replacemenetState), this.opString);
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        return i == 0 ? this.left : this.right;
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        return ParameterRole.forBinaryOperatorOperand(i);
    }
}
