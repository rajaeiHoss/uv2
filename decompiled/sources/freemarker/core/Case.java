package freemarker.core;

import freemarker.template.TemplateException;
import java.io.IOException;
import kotlin.text.Typography;

final class Case extends TemplateElement {
    final int TYPE_CASE = 0;
    final int TYPE_DEFAULT = 1;
    Expression condition;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    Case(Expression expression, TemplateElement templateElement) {
        this.condition = expression;
        this.nestedBlock = templateElement;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        if (this.nestedBlock != null) {
            environment.visitByHiddingParent(this.nestedBlock);
        }
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        if (this.condition != null) {
            stringBuffer.append(' ');
            stringBuffer.append(this.condition.getCanonicalForm());
        }
        if (z) {
            stringBuffer.append(Typography.greater);
            if (this.nestedBlock != null) {
                stringBuffer.append(this.nestedBlock.getCanonicalForm());
            }
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return this.condition != null ? "#case" : "#default";
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.condition;
        }
        int i2 = 1;
        if (i == 1) {
            if (this.condition != null) {
                i2 = 0;
            }
            return new Integer(i2);
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.CONDITION;
        }
        if (i == 1) {
            return ParameterRole.AST_NODE_SUBTYPE;
        }
        throw new IndexOutOfBoundsException();
    }
}
