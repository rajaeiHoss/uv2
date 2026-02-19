package freemarker.core;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.ArrayList;
import kotlin.text.Typography;

final class AssignmentInstruction extends TemplateElement {
    private Expression namespaceExp;
    private int scope;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    AssignmentInstruction(int i) {
        this.scope = i;
        this.nestedElements = new ArrayList(1);
    }

    /* access modifiers changed from: package-private */
    public void addAssignment(Assignment assignment) {
        this.nestedElements.add(assignment);
    }

    /* access modifiers changed from: package-private */
    public void setNamespaceExp(Expression expression) {
        this.namespaceExp = expression;
        for (int i = 0; i < this.nestedElements.size(); i++) {
            ((Assignment) this.nestedElements.get(i)).setNamespaceExp(expression);
        }
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        for (int i = 0; i < this.nestedElements.size(); i++) {
            environment.visit((TemplateElement) (Assignment) this.nestedElements.get(i));
        }
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(Assignment.getDirectiveName(this.scope));
        if (z) {
            stringBuffer.append(' ');
            for (int i = 0; i < this.nestedElements.size(); i++) {
                stringBuffer.append(((Assignment) this.nestedElements.get(i)).getCanonicalForm());
                if (i < this.nestedElements.size() - 1) {
                    stringBuffer.append(" ");
                }
            }
        } else {
            stringBuffer.append("-container");
        }
        if (this.namespaceExp != null) {
            stringBuffer.append(" in ");
            stringBuffer.append(this.namespaceExp.getCanonicalForm());
        }
        if (z) {
            stringBuffer.append("/>");
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return new Integer(this.scope);
        }
        if (i != 1) {
            return null;
        }
        return this.namespaceExp;
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.VARIABLE_SCOPE;
        }
        if (i != 1) {
            return null;
        }
        return ParameterRole.NAMESPACE;
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return Assignment.getDirectiveName(this.scope);
    }

    public TemplateElement postParseCleanup(boolean z) throws ParseException {
        super.postParseCleanup(z);
        if (this.nestedElements.size() != 1) {
            return this;
        }
        Assignment assignment = (Assignment) this.nestedElements.get(0);
        assignment.setLocation(getTemplate(), (TemplateObject) this, (TemplateObject) this);
        return assignment;
    }
}
