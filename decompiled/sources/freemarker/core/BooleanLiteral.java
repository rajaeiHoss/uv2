package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateModel;

final class BooleanLiteral extends Expression {
    private final boolean val;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return true;
    }

    public BooleanLiteral(boolean z) {
        this.val = z;
    }

    static TemplateBooleanModel getTemplateModel(boolean z) {
        return z ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
    }

    /* access modifiers changed from: package-private */
    public boolean evalToBoolean(Environment environment) {
        return this.val;
    }

    public String getCanonicalForm() {
        return this.val ? "true" : "false";
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return getCanonicalForm();
    }

    public String toString() {
        return this.val ? "true" : "false";
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) {
        return this.val ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new BooleanLiteral(this.val);
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        throw new IndexOutOfBoundsException();
    }
}
