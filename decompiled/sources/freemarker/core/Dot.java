package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;

final class Dot extends Expression {
    private final String key;
    private final Expression target;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return ".";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    Dot(Expression expression, String str) {
        this.target = expression;
        this.key = str;
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        TemplateModel eval = this.target.eval(environment);
        if (eval instanceof TemplateHashModel) {
            return ((TemplateHashModel) eval).get(this.key);
        }
        if (eval == null && environment.isClassicCompatible()) {
            return null;
        }
        throw new UnexpectedTypeException(this.target, eval, "hash", environment);
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.target.getCanonicalForm());
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(this.key);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return this.target.isLiteral();
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new Dot(this.target.deepCloneWithIdentifierReplaced(str, expression, replacemenetState), this.key);
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        return i == 0 ? this.target : this.key;
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        return ParameterRole.forBinaryOperatorOperand(i);
    }

    /* access modifiers changed from: package-private */
    public boolean onlyHasIdentifiers() {
        Expression expression = this.target;
        return (expression instanceof Identifier) || ((expression instanceof Dot) && ((Dot) expression).onlyHasIdentifiers());
    }
}
