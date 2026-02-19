package freemarker.core;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;

final class Assignment extends TemplateElement {
    static final int GLOBAL = 3;
    static final int LOCAL = 2;
    static final int NAMESPACE = 1;
    private Expression namespaceExp;
    private int scope;
    private Expression value;
    private String variableName;

    static String getDirectiveName(int i) {
        return i == 2 ? "#local" : i == 3 ? "#global" : i == 1 ? "#assign" : "#{unknown_assignment_type}";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 4;
    }

    Assignment(String str, Expression expression, int i) {
        this.variableName = str;
        this.value = expression;
        this.scope = i;
    }

    /* access modifiers changed from: package-private */
    public void setNamespaceExp(Expression expression) {
        this.namespaceExp = expression;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException {
        Environment.Namespace namespace;
        Expression expression = this.namespaceExp;
        if (expression != null) {
            TemplateModel eval = expression.eval(environment);
            try {
                namespace = (Environment.Namespace) eval;
                if (namespace == null) {
                    throw InvalidReferenceException.getInstance(this.namespaceExp, environment);
                }
            } catch (ClassCastException unused) {
                throw new UnexpectedTypeException(this.namespaceExp, eval, "namespace", environment);
            }
        } else {
            namespace = null;
        }
        TemplateModel eval2 = this.value.eval(environment);
        if (eval2 == null) {
            if (environment.isClassicCompatible()) {
                eval2 = TemplateScalarModel.EMPTY_STRING;
            } else {
                throw InvalidReferenceException.getInstance(this.value, environment);
            }
        }
        int i = this.scope;
        if (i == 2) {
            environment.setLocalVariable(this.variableName, eval2);
            return;
        }
        if (namespace == null) {
            if (i == 3) {
                namespace = environment.getGlobalNamespace();
            } else if (i == 1) {
                namespace = environment.getCurrentNamespace();
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unexpected scope type: ");
                stringBuffer.append(this.scope);
                throw new RuntimeException(stringBuffer.toString());
            }
        }
        namespace.put(this.variableName, (Object) eval2);
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        String nodeTypeSymbol = this.parent instanceof AssignmentInstruction ? null : getNodeTypeSymbol();
        if (nodeTypeSymbol != null) {
            if (z) {
                stringBuffer.append("<");
            }
            stringBuffer.append(nodeTypeSymbol);
            stringBuffer.append(' ');
        }
        stringBuffer.append(this.variableName);
        stringBuffer.append(" = ");
        stringBuffer.append(this.value.getCanonicalForm());
        if (nodeTypeSymbol != null) {
            if (this.namespaceExp != null) {
                stringBuffer.append(" in ");
                stringBuffer.append(this.namespaceExp.getCanonicalForm());
            }
            if (z) {
                stringBuffer.append(">");
            }
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return getDirectiveName(this.scope);
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.variableName;
        }
        if (i == 1) {
            return this.value;
        }
        if (i == 2) {
            return new Integer(this.scope);
        }
        if (i == 3) {
            return this.namespaceExp;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.ASSIGNMENT_TARGET;
        }
        if (i == 1) {
            return ParameterRole.ASSIGNMENT_SOURCE;
        }
        if (i == 2) {
            return ParameterRole.VARIABLE_SCOPE;
        }
        if (i == 3) {
            return ParameterRole.NAMESPACE;
        }
        throw new IndexOutOfBoundsException();
    }
}
