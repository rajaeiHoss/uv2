package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

final class Identifier extends Expression {
    private final String name;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return false;
    }

    Identifier(String str) {
        this.name = str;
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        try {
            return environment.getVariable(this.name);
        } catch (NullPointerException e) {
            if (environment == null) {
                throw new _MiscTemplateException(new Object[]{"Variables are not available (certainly you are in a parse-time executed directive). The name of the variable you tried to read: ", this.name});
            }
            throw e;
        }
    }

    public String toString() {
        return this.name;
    }

    public String getCanonicalForm() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return getCanonicalForm();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        if (!this.name.equals(str)) {
            return new Identifier(this.name);
        }
        if (replacemenetState.replacementAlreadyInUse) {
            Expression deepCloneWithIdentifierReplaced = expression.deepCloneWithIdentifierReplaced((String) null, (Expression) null, replacemenetState);
            deepCloneWithIdentifierReplaced.copyLocationFrom(expression);
            return deepCloneWithIdentifierReplaced;
        }
        replacemenetState.replacementAlreadyInUse = true;
        return expression;
    }
}
