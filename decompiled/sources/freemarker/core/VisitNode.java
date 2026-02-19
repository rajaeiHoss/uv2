package freemarker.core;

import freemarker.core.Environment;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateNodeModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import java.io.IOException;
import kotlin.text.Typography;

final class VisitNode extends TemplateElement {
    Expression namespaces;
    Expression targetNode;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#visit";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    VisitNode(Expression expression, Expression expression2) {
        this.targetNode = expression;
        this.namespaces = expression2;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws IOException, TemplateException {
        TemplateModel eval = this.targetNode.eval(environment);
        if (eval instanceof TemplateNodeModel) {
            Expression expression = this.namespaces;
            SimpleSequence eval2 = expression == null ? null : expression.eval(environment);
            Expression expression2 = this.namespaces;
            if (expression2 instanceof StringLiteral) {
                eval2 = environment.importLib(((TemplateScalarModel) eval2).getAsString(), (String) null);
            } else if (expression2 instanceof ListLiteral) {
                eval2 = ((ListLiteral) expression2).evaluateStringsToNamespaces(environment);
            }
            if (eval2 != null) {
                if (eval2 instanceof Environment.Namespace) {
                    SimpleSequence simpleSequence = new SimpleSequence(1);
                    simpleSequence.add((Object) eval2);
                    eval2 = simpleSequence;
                } else if (!(eval2 instanceof TemplateSequenceModel)) {
                    if (this.namespaces != null) {
                        throw new UnexpectedTypeException(this.namespaces, eval2, "sequence", environment);
                    }
                    throw new _MiscTemplateException(environment, "Expecting a sequence of namespaces after \"using\"");
                }
            }
            environment.visit((TemplateNodeModel) eval, (TemplateSequenceModel) eval2);
            return;
        }
        throw new UnexpectedTypeException(this.targetNode, eval, "node", environment);
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(' ');
        stringBuffer.append(this.targetNode.getCanonicalForm());
        if (this.namespaces != null) {
            stringBuffer.append(" using ");
            stringBuffer.append(this.namespaces.getCanonicalForm());
        }
        if (z) {
            stringBuffer.append("/>");
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.targetNode;
        }
        if (i == 1) {
            return this.namespaces;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.NODE;
        }
        if (i == 1) {
            return ParameterRole.NAMESPACE;
        }
        throw new IndexOutOfBoundsException();
    }
}
