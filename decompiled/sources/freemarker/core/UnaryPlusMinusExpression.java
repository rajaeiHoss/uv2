package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateNumberModel;

final class UnaryPlusMinusExpression extends Expression {
    private static final Integer MINUS_ONE = new Integer(-1);
    private final int TYPE_MINUS = 0;
    private final int TYPE_PLUS = 1;
    private final boolean isMinus;
    private final Expression target;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    /* access modifiers changed from: package-private */
    public boolean isIgnorable() {
        return true;
    }

    UnaryPlusMinusExpression(Expression expression, boolean z) {
        this.target = expression;
        this.isMinus = z;
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        TemplateModel eval = this.target.eval(environment);
        try {
            TemplateNumberModel templateNumberModel = (TemplateNumberModel) eval;
            if (!this.isMinus) {
                return templateNumberModel;
            }
            this.target.assertNonNull(templateNumberModel, environment);
            return new SimpleNumber(ArithmeticEngine.CONSERVATIVE_ENGINE.multiply(MINUS_ONE, templateNumberModel.getAsNumber()));
        } catch (ClassCastException unused) {
            throw new NonNumericalException(this.target, eval, environment);
        }
    }

    public String getCanonicalForm() {
        String str = this.isMinus ? "-" : "+";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(this.target.getCanonicalForm());
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return this.isMinus ? "-..." : "+...";
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return this.target.isLiteral();
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new UnaryPlusMinusExpression(this.target.deepCloneWithIdentifierReplaced(str, expression, replacemenetState), this.isMinus);
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.target;
        }
        if (i == 1) {
            return new Integer(true ^ this.isMinus ? 1 : 0);
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.RIGHT_HAND_OPERAND;
        }
        if (i == 1) {
            return ParameterRole.AST_NODE_SUBTYPE;
        }
        throw new IndexOutOfBoundsException();
    }
}
