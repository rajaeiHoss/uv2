package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

final class ArithmeticExpression extends Expression {
    private static final char[] OPERATOR_IMAGES = {'-', '*', '/', '%'};
    static final int TYPE_DIVISION = 2;
    static final int TYPE_MODULO = 3;
    static final int TYPE_MULTIPLICATION = 1;
    static final int TYPE_SUBSTRACTION = 0;
    private final Expression lho;
    private final int operator;
    private final Expression rho;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 3;
    }

    ArithmeticExpression(Expression expression, Expression expression2, int i) {
        this.lho = expression;
        this.rho = expression2;
        this.operator = i;
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        Number evalToNumber = this.lho.evalToNumber(environment);
        Number evalToNumber2 = this.rho.evalToNumber(environment);
        ArithmeticEngine arithmeticEngine = environment != null ? environment.getArithmeticEngine() : getTemplate().getArithmeticEngine();
        int i = this.operator;
        if (i == 0) {
            return new SimpleNumber(arithmeticEngine.subtract(evalToNumber, evalToNumber2));
        }
        if (i == 1) {
            return new SimpleNumber(arithmeticEngine.multiply(evalToNumber, evalToNumber2));
        }
        if (i == 2) {
            return new SimpleNumber(arithmeticEngine.divide(evalToNumber, evalToNumber2));
        }
        if (i == 3) {
            return new SimpleNumber(arithmeticEngine.modulus(evalToNumber, evalToNumber2));
        }
        throw new _MiscTemplateException((Expression) this, new Object[]{"Unknown operation: ", new Integer(this.operator)});
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.lho.getCanonicalForm());
        stringBuffer.append(' ');
        stringBuffer.append(OPERATOR_IMAGES[this.operator]);
        stringBuffer.append(' ');
        stringBuffer.append(this.rho.getCanonicalForm());
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return String.valueOf(OPERATOR_IMAGES[this.operator]);
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return this.constantValue != null || (this.lho.isLiteral() && this.rho.isLiteral());
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new ArithmeticExpression(this.lho.deepCloneWithIdentifierReplaced(str, expression, replacemenetState), this.rho.deepCloneWithIdentifierReplaced(str, expression, replacemenetState), this.operator);
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.lho;
        }
        if (i == 1) {
            return this.rho;
        }
        if (i == 2) {
            return new Integer(this.operator);
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.LEFT_HAND_OPERAND;
        }
        if (i == 1) {
            return ParameterRole.RIGHT_HAND_OPERAND;
        }
        if (i == 2) {
            return ParameterRole.AST_NODE_SUBTYPE;
        }
        throw new IndexOutOfBoundsException();
    }
}
