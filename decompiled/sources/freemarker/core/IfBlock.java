package freemarker.core;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.ArrayList;

final class IfBlock extends TemplateElement {
    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#if-#elseif-#else-container";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isShownInStackTrace() {
        return false;
    }

    IfBlock(ConditionalBlock conditionalBlock) {
        this.nestedElements = new ArrayList();
        addBlock(conditionalBlock);
    }

    /* access modifiers changed from: package-private */
    public void addBlock(ConditionalBlock conditionalBlock) {
        this.nestedElements.add(conditionalBlock);
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        int i = 0;
        while (i < this.nestedElements.size()) {
            ConditionalBlock conditionalBlock = (ConditionalBlock) this.nestedElements.get(i);
            Expression expression = conditionalBlock.condition;
            environment.replaceElemetStackTop(conditionalBlock);
            if (expression != null && !expression.evalToBoolean(environment)) {
                i++;
            } else if (conditionalBlock.nestedBlock != null) {
                environment.visit(conditionalBlock.nestedBlock);
                return;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public TemplateElement postParseCleanup(boolean z) throws ParseException {
        if (this.nestedElements.size() != 1) {
            return super.postParseCleanup(z);
        }
        ConditionalBlock conditionalBlock = (ConditionalBlock) this.nestedElements.get(0);
        conditionalBlock.isLonelyIf = true;
        conditionalBlock.setLocation(getTemplate(), (TemplateObject) conditionalBlock, (TemplateObject) this);
        return conditionalBlock.postParseCleanup(z);
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        if (!z) {
            return getNodeTypeSymbol();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.nestedElements.size(); i++) {
            stringBuffer.append(((ConditionalBlock) this.nestedElements.get(i)).dump(z));
        }
        stringBuffer.append("</#if>");
        return stringBuffer.toString();
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
