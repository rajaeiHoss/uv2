package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

final class Range extends Expression {
    final Expression lho;
    final Expression rho;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "..";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    Range(Expression expression, Expression expression2) {
        this.lho = expression;
        this.rho = expression2;
    }

    /* access modifiers changed from: package-private */
    public boolean hasRho() {
        return this.rho != null;
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        int intValue = this.lho.evalToNumber(environment).intValue();
        Expression expression = this.rho;
        if (expression != null) {
            return new NumericalRange(intValue, expression.evalToNumber(environment).intValue());
        }
        return new NumericalRange(intValue);
    }

    /* access modifiers changed from: package-private */
    public boolean evalToBoolean(Environment environment) throws TemplateException {
        throw new NonBooleanException(this, new NumericalRange(0, 0), environment);
    }

    public String getCanonicalForm() {
        Expression expression = this.rho;
        String canonicalForm = expression != null ? expression.getCanonicalForm() : "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.lho.getCanonicalForm());
        stringBuffer.append("..");
        stringBuffer.append(canonicalForm);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        Expression expression = this.rho;
        boolean z = expression == null || expression.isLiteral();
        if (this.constantValue != null || (this.lho.isLiteral() && z)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new Range(this.lho.deepCloneWithIdentifierReplaced(str, expression, replacemenetState), this.rho.deepCloneWithIdentifierReplaced(str, expression, replacemenetState));
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
