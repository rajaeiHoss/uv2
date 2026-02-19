package freemarker.core;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.TemplateTransformModel;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

class Interpret extends BuiltIn {
    Interpret() {
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        Expression expression;
        TemplateModel eval = this.target.eval(environment);
        String str = "anonymous_interpreted";
        if (eval instanceof TemplateSequenceModel) {
            expression = (Expression) new DynamicKeyName(this.target, new NumberLiteral(new Integer(0))).copyLocationFrom(this.target);
            if (((TemplateSequenceModel) eval).size() > 1) {
                str = ((Expression) new DynamicKeyName(this.target, new NumberLiteral(new Integer(1))).copyLocationFrom(this.target)).evalAndCoerceToString(environment);
            }
        } else if (eval instanceof TemplateScalarModel) {
            expression = this.target;
        } else {
            throw new UnexpectedTypeException(this.target, eval, "sequence or string", environment);
        }
        String evalAndCoerceToString = expression.evalAndCoerceToString(environment);
        Template template = environment.getTemplate();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(template.getName() != null ? template.getName() : "nameless_template");
            stringBuffer.append("->");
            stringBuffer.append(str);
            Template template2 = new Template(stringBuffer.toString(), evalAndCoerceToString, template.getConfiguration());
            template2.setLocale(environment.getLocale());
            return new TemplateProcessorModel(template2);
        } catch (IOException e) {
            throw new _MiscTemplateException((Expression) this, (Throwable) e, environment, new Object[]{"Template parsing with \"?", this.key, "\" has failed with this error:\n\n", "---begin-message---\n", new _DelayedGetMessage(e), "\n---end-message---", "\n\nThe failed expression:"});
        }
    }

    private class TemplateProcessorModel implements TemplateTransformModel {
        private final Template template;

        TemplateProcessorModel(Template template2) {
            this.template = template2;
        }

        public Writer getWriter(final Writer writer, Map map) throws TemplateModelException, IOException {
            Environment currentEnvironment;
            boolean fastInvalidReferenceExceptions;
            try {
                currentEnvironment = Environment.getCurrentEnvironment();
                fastInvalidReferenceExceptions = currentEnvironment.setFastInvalidReferenceExceptions(false);
                currentEnvironment.include(this.template);
                currentEnvironment.setFastInvalidReferenceExceptions(fastInvalidReferenceExceptions);
                return new Writer(this, writer) {
                    private final /* synthetic */ TemplateProcessorModel this$1;

                    public void close() {
                    }

                    {
                        this.this$1 = r1;
                    }

                    public void flush() throws IOException {
                        writer.flush();
                    }

                    public void write(char[] cArr, int i, int i2) throws IOException {
                        writer.write(cArr, i, i2);
                    }
                };
            } catch (Exception e) {
                throw new _TemplateModelException((Throwable) e, new Object[]{"Template created with \"?", Interpret.this.key, "\" has stopped with this error:\n\n", "---begin-message---\n", new _DelayedGetMessage(e), "\n---end-message---"});
            } catch (Throwable th) {
                currentEnvironment.setFastInvalidReferenceExceptions(fastInvalidReferenceExceptions);
                throw th;
            }
        }
    }
}
