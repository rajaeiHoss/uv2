package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.TemplateException;
import java.io.IOException;
import kotlin.text.Typography;

class EscapeBlock extends TemplateElement {
    private Expression escapedExpr;
    private final Expression expr;
    private final String variable;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#escape";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    /* access modifiers changed from: package-private */
    public boolean isShownInStackTrace() {
        return false;
    }

    EscapeBlock(String str, Expression expression, Expression expression2) {
        this.variable = str;
        this.expr = expression;
        this.escapedExpr = expression2;
    }

    /* access modifiers changed from: package-private */
    public void setContent(TemplateElement templateElement) {
        this.nestedBlock = templateElement;
        this.escapedExpr = null;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        if (this.nestedBlock != null) {
            environment.visit(this.nestedBlock);
        }
    }

    /* access modifiers changed from: package-private */
    public Expression doEscape(Expression expression) {
        return this.escapedExpr.deepCloneWithIdentifierReplaced(this.variable, expression, new Expression.ReplacemenetState());
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(' ');
        stringBuffer.append(this.variable);
        stringBuffer.append(" as ");
        stringBuffer.append(this.expr.getCanonicalForm());
        if (z) {
            stringBuffer.append(Typography.greater);
            stringBuffer.append(this.nestedBlock.getCanonicalForm());
            stringBuffer.append("</");
            stringBuffer.append(getNodeTypeSymbol());
            stringBuffer.append(Typography.greater);
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.variable;
        }
        if (i == 1) {
            return this.expr;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.PLACEHOLDER_VARIABLE;
        }
        if (i == 1) {
            return ParameterRole.EXPRESSION_TEMPLATE;
        }
        throw new IndexOutOfBoundsException();
    }
}
