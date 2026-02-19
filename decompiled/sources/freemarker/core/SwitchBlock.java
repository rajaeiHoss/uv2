package freemarker.core;

import freemarker.core.BreakInstruction;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.LinkedList;
import kotlin.text.Typography;

final class SwitchBlock extends TemplateElement {
    private Case defaultCase;
    private final Expression searched;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#switch";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    SwitchBlock(Expression expression) {
        this.searched = expression;
        this.nestedElements = new LinkedList();
    }

    /* access modifiers changed from: package-private */
    public void addCase(Case caseR) {
        if (caseR.condition == null) {
            this.defaultCase = caseR;
        }
        this.nestedElements.add(caseR);
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        Case caseR;
        boolean z;
        boolean z2 = false;
        for (Case caseR2 : this.nestedElements) {
            try {
                if (z2) {
                    z = true;
                } else {
                    z = caseR2.condition != null ? EvalUtil.compare(this.searched, 1, "case==", caseR2.condition, caseR2.condition, environment) : false;
                }
                if (z) {
                    environment.visitByHiddingParent(caseR2);
                    z2 = true;
                }
            } catch (BreakInstruction.Break unused) {
                return;
            }
        }
        if (!z2 && (caseR = this.defaultCase) != null) {
            environment.visitByHiddingParent(caseR);
        }
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(' ');
        stringBuffer.append(this.searched.getCanonicalForm());
        if (z) {
            stringBuffer.append(Typography.greater);
            for (int i = 0; i < this.nestedElements.size(); i++) {
                stringBuffer.append(((Case) this.nestedElements.get(i)).getCanonicalForm());
            }
            stringBuffer.append("</");
            stringBuffer.append(getNodeTypeSymbol());
            stringBuffer.append(Typography.greater);
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.searched;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.VALUE;
        }
        throw new IndexOutOfBoundsException();
    }
}
