package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateNumberModel;

final class NumberLiteral extends Expression implements TemplateNumberModel {
    private final Number value;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return true;
    }

    public NumberLiteral(Number number) {
        this.value = number;
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) {
        return new SimpleNumber(this.value);
    }

    public String evalAndCoerceToString(Environment environment) {
        return environment.formatNumber(this.value);
    }

    public Number getAsNumber() {
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("the number: '");
        stringBuffer.append(this.value);
        stringBuffer.append("'");
        return stringBuffer.toString();
    }

    public String getCanonicalForm() {
        return this.value.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return getCanonicalForm();
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new NumberLiteral(this.value);
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
