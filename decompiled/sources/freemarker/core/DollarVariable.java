package freemarker.core;

import com.streamax.config.constant.Constants;
import freemarker.template.TemplateException;
import java.io.IOException;

final class DollarVariable extends TemplateElement {
    private final Expression escapedExpression;
    private final Expression expression;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "${...}";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public boolean heedsOpeningWhitespace() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean heedsTrailingWhitespace() {
        return true;
    }

    DollarVariable(Expression expression2, Expression expression3) {
        this.expression = expression2;
        this.escapedExpression = expression3;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        environment.getOut().write(this.escapedExpression.evalAndCoerceToString(environment));
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("${");
        stringBuffer.append(this.expression.getCanonicalForm());
        stringBuffer.append(Constants.JsonSstringSuffix);
        if (!z && this.expression != this.escapedExpression) {
            stringBuffer.append(" auto-escaped");
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.expression;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.CONTENT;
        }
        throw new IndexOutOfBoundsException();
    }
}
