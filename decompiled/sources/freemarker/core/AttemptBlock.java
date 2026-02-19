package freemarker.core;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.ArrayList;

final class AttemptBlock extends TemplateElement {
    private TemplateElement attemptBlock;
    private RecoveryBlock recoveryBlock;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#attempt";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public boolean isShownInStackTrace() {
        return false;
    }

    AttemptBlock(TemplateElement templateElement, RecoveryBlock recoveryBlock2) {
        this.attemptBlock = templateElement;
        this.recoveryBlock = recoveryBlock2;
        this.nestedElements = new ArrayList();
        this.nestedElements.add(templateElement);
        this.nestedElements.add(recoveryBlock2);
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        environment.visitAttemptRecover(this.attemptBlock, this.recoveryBlock);
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        if (!z) {
            return getNodeTypeSymbol();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<");
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(">");
        TemplateElement templateElement = this.attemptBlock;
        if (templateElement != null) {
            stringBuffer.append(templateElement.getCanonicalForm());
        }
        RecoveryBlock recoveryBlock2 = this.recoveryBlock;
        if (recoveryBlock2 != null) {
            stringBuffer.append(recoveryBlock2.getCanonicalForm());
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.recoveryBlock;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.ERROR_HANDLER;
        }
        throw new IndexOutOfBoundsException();
    }
}
