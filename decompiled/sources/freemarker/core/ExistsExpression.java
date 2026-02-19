package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

class ExistsExpression extends Expression {
    protected final Expression exp;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "??";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return false;
    }

    ExistsExpression(Expression expression) {
        this.exp = expression;
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        TemplateModel templateModel;
        Expression expression = this.exp;
        if (expression instanceof ParentheticalExpression) {
            boolean fastInvalidReferenceExceptions = environment.setFastInvalidReferenceExceptions(true);
            try {
                templateModel = this.exp.eval(environment);
            } catch (InvalidReferenceException unused) {
                templateModel = null;
            } catch (Throwable th) {
                environment.setFastInvalidReferenceExceptions(fastInvalidReferenceExceptions);
                throw th;
            }
            environment.setFastInvalidReferenceExceptions(fastInvalidReferenceExceptions);
        } else {
            templateModel = expression.eval(environment);
        }
        return templateModel == null ? TemplateBooleanModel.FALSE : TemplateBooleanModel.TRUE;
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new ExistsExpression(this.exp.deepCloneWithIdentifierReplaced(str, expression, replacemenetState));
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.exp.getCanonicalForm());
        stringBuffer.append(getNodeTypeSymbol());
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        return this.exp;
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        return ParameterRole.LEFT_HAND_OPERAND;
    }
}
