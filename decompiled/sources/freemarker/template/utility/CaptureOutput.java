package freemarker.template.utility;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateTransformModel;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class CaptureOutput implements TemplateTransformModel {
    public Writer getWriter(Writer writer, Map map) throws TemplateModelException {
        final boolean z;
        final boolean z2;
        boolean z3;
        if (map != null) {
            final TemplateModel templateModel = (TemplateModel) map.get("namespace");
            Object obj = map.get("var");
            boolean z4 = false;
            if (obj == null) {
                obj = map.get("local");
                if (obj == null) {
                    obj = map.get("global");
                    z3 = true;
                } else {
                    z4 = true;
                    z3 = false;
                }
                if (obj != null) {
                    z2 = z4;
                    z = z3;
                } else {
                    throw new TemplateModelException("Must specify the name of the variable in which to capture the output with the 'var' or 'local' or 'global' parameter.");
                }
            } else {
                z2 = false;
                z = false;
            }
            if (map.size() == 2) {
                if (templateModel == null) {
                    throw new TemplateModelException("Second parameter can only be namespace");
                } else if (z2) {
                    throw new TemplateModelException("Cannot specify namespace for a local assignment");
                } else if (z) {
                    throw new TemplateModelException("Cannot specify namespace for a global assignment");
                } else if (!(templateModel instanceof Environment.Namespace)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("namespace parameter does not specify a namespace. It is a ");
                    stringBuffer.append(templateModel.getClass().getName());
                    throw new TemplateModelException(stringBuffer.toString());
                }
            } else if (map.size() != 1) {
                throw new TemplateModelException("Bad parameters. Use only one of 'var' or 'local' or 'global' parameters.");
            }
            if (obj instanceof TemplateScalarModel) {
                final String asString = ((TemplateScalarModel) obj).getAsString();
                if (asString != null) {
                    final StringBuffer stringBuffer2 = new StringBuffer();
                    final Environment currentEnvironment = Environment.getCurrentEnvironment();
                    final Writer writer2 = writer;
                    return new Writer() {
                        public void write(char[] cArr, int i, int i2) {
                            stringBuffer2.append(cArr, i, i2);
                        }

                        public void flush() throws IOException {
                            writer2.flush();
                        }

                        public void close() throws IOException {
                            SimpleScalar simpleScalar = new SimpleScalar(stringBuffer2.toString());
                            try {
                                if (z2) {
                                    currentEnvironment.setLocalVariable(asString, simpleScalar);
                                } else if (z) {
                                    currentEnvironment.setGlobalVariable(asString, simpleScalar);
                                } else {
                                    TemplateModel templateModel = templateModel;
                                    if (templateModel == null) {
                                        currentEnvironment.setVariable(asString, simpleScalar);
                                    } else {
                                        ((Environment.Namespace) templateModel).put(asString, (Object) simpleScalar);
                                    }
                                }
                            } catch (IllegalStateException e) {
                                StringBuffer stringBuffer = new StringBuffer();
                                stringBuffer.append("Could not set variable ");
                                stringBuffer.append(asString);
                                stringBuffer.append(": ");
                                stringBuffer.append(e.getMessage());
                                throw new IOException(stringBuffer.toString());
                            }
                        }
                    };
                }
                throw new TemplateModelException("'var' or 'local' or 'global' parameter evaluates to null string");
            }
            throw new TemplateModelException("'var' or 'local' or 'global' parameter doesn't evaluate to a string");
        }
        throw new TemplateModelException("Must specify the name of the variable in which to capture the output with the 'var' or 'local' or 'global' parameter.");
    }
}
