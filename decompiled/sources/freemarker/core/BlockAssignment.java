package freemarker.core;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateTransformModel;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import kotlin.text.Typography;

final class BlockAssignment extends TemplateElement {
    /* access modifiers changed from: private */
    public final Expression namespaceExp;
    /* access modifiers changed from: private */
    public final int scope;
    /* access modifiers changed from: private */
    public final String varName;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 3;
    }

    /* access modifiers changed from: package-private */
    public boolean isIgnorable() {
        return false;
    }

    BlockAssignment(TemplateElement templateElement, String str, int i, Expression expression) {
        this.nestedBlock = templateElement;
        this.varName = str;
        this.namespaceExp = expression;
        this.scope = i;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        if (this.nestedBlock != null) {
            environment.visitAndTransform(this.nestedBlock, new CaptureOutput(environment), (Map) null);
            return;
        }
        SimpleScalar simpleScalar = new SimpleScalar("");
        Expression expression = this.namespaceExp;
        if (expression != null) {
            ((Environment.Namespace) expression.eval(environment)).put(this.varName, (Object) simpleScalar);
            return;
        }
        int i = this.scope;
        if (i == 1) {
            environment.setVariable(this.varName, simpleScalar);
        } else if (i == 3) {
            environment.setGlobalVariable(this.varName, simpleScalar);
        } else if (i == 2) {
            environment.setLocalVariable(this.varName, simpleScalar);
        }
    }

    private class CaptureOutput implements TemplateTransformModel {
        /* access modifiers changed from: private */
        public final Environment env;
        /* access modifiers changed from: private */
        public final Environment.Namespace fnsModel;

        CaptureOutput(Environment environment) throws TemplateException {
            TemplateModel templateModel;
            this.env = environment;
            if (BlockAssignment.this.namespaceExp != null) {
                templateModel = BlockAssignment.this.namespaceExp.eval(environment);
                if (!(templateModel instanceof Environment.Namespace)) {
                    throw new UnexpectedTypeException(BlockAssignment.this.namespaceExp, templateModel, "namespace", environment);
                }
            } else {
                templateModel = null;
            }
            this.fnsModel = (Environment.Namespace) templateModel;
        }

        public Writer getWriter(Writer writer, Map map) {
            return new StringWriter() {
                public void close() {
                    SimpleScalar simpleScalar = new SimpleScalar(toString());
                    int access$200 = BlockAssignment.this.scope;
                    if (access$200 != 1) {
                        if (access$200 == 2) {
                            CaptureOutput.this.env.setLocalVariable(BlockAssignment.this.varName, simpleScalar);
                        } else if (access$200 == 3) {
                            CaptureOutput.this.env.setGlobalVariable(BlockAssignment.this.varName, simpleScalar);
                        }
                    } else if (CaptureOutput.this.fnsModel != null) {
                        CaptureOutput.this.fnsModel.put(BlockAssignment.this.varName, (Object) simpleScalar);
                    } else {
                        CaptureOutput.this.env.setVariable(BlockAssignment.this.varName, simpleScalar);
                    }
                }
            };
        }
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append("<");
        }
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(' ');
        stringBuffer.append(this.varName);
        if (this.namespaceExp != null) {
            stringBuffer.append(" in ");
            stringBuffer.append(this.namespaceExp.getCanonicalForm());
        }
        if (z) {
            stringBuffer.append(Typography.greater);
            stringBuffer.append(this.nestedBlock == null ? "" : this.nestedBlock.getCanonicalForm());
            stringBuffer.append("</");
            stringBuffer.append(getNodeTypeSymbol());
            stringBuffer.append(Typography.greater);
        } else {
            stringBuffer.append(" = .nested_output");
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return Assignment.getDirectiveName(this.scope);
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.varName;
        }
        if (i == 1) {
            return new Integer(this.scope);
        }
        if (i == 2) {
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
            return ParameterRole.VARIABLE_SCOPE;
        }
        if (i == 2) {
            return ParameterRole.NAMESPACE;
        }
        throw new IndexOutOfBoundsException();
    }
}
