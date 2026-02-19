package freemarker.core;

import freemarker.core.Expression;
import freemarker.core.Macro;
import freemarker.template.Configuration;
import freemarker.template.SimpleDate;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.util.Date;

final class BuiltinVariable extends Expression {
    static final String CURRENT_NODE = "current_node";
    static final String DATA_MODEL = "data_model";
    static final String ERROR = "error";
    static final String GLOBALS = "globals";
    static final String LANG = "lang";
    static final String LOCALE = "locale";
    static final String LOCALS = "locals";
    static final String MAIN = "main";
    static final String NAMESPACE = "namespace";
    static final String NODE = "node";
    static final String NOW = "now";
    static final String OUTPUT_ENCODING = "output_encoding";
    static final String PASS = "pass";
    static final String TEMPLATE_NAME = "template_name";
    static final String URL_ESCAPING_CHARSET = "url_escaping_charset";
    static final String VARS = "vars";
    static final String VERSION = "version";
    private final String name;

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return this;
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return false;
    }

    BuiltinVariable(String str) throws ParseException {
        String intern = str.intern();
        this.name = intern;
        if (intern != TEMPLATE_NAME && intern != NAMESPACE && intern != MAIN && intern != GLOBALS && intern != LOCALS && intern != LANG && intern != "locale" && intern != DATA_MODEL && intern != CURRENT_NODE && intern != NODE && intern != PASS && intern != VARS && intern != VERSION && intern != "output_encoding" && intern != "url_escaping_charset" && intern != "error" && intern != NOW) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unknown built-in variable: ");
            stringBuffer.append(intern);
            throw new ParseException(stringBuffer.toString(), this);
        }
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        String str = this.name;
        if (str == NAMESPACE) {
            return environment.getCurrentNamespace();
        }
        if (str == MAIN) {
            return environment.getMainNamespace();
        }
        if (str == GLOBALS) {
            return environment.getGlobalVariables();
        }
        if (str == LOCALS) {
            Macro.Context currentMacroContext = environment.getCurrentMacroContext();
            if (currentMacroContext == null) {
                return null;
            }
            return currentMacroContext.getLocals();
        } else if (str == DATA_MODEL) {
            return environment.getDataModel();
        } else {
            if (str == VARS) {
                return new VarsHash(environment);
            }
            if (str == "locale") {
                return new SimpleScalar(environment.getLocale().toString());
            }
            if (str == LANG) {
                return new SimpleScalar(environment.getLocale().getLanguage());
            }
            if (str == CURRENT_NODE || str == NODE) {
                return environment.getCurrentVisitorNode();
            }
            if (str == TEMPLATE_NAME) {
                return new SimpleScalar(environment.getTemplate().getName());
            }
            if (str == PASS) {
                return Macro.DO_NOTHING_MACRO;
            }
            if (str == VERSION) {
                return new SimpleScalar(Configuration.getVersionNumber());
            }
            if (str == "output_encoding") {
                String outputEncoding = environment.getOutputEncoding();
                if (outputEncoding != null) {
                    return new SimpleScalar(outputEncoding);
                }
                return null;
            } else if (str == "url_escaping_charset") {
                String uRLEscapingCharset = environment.getURLEscapingCharset();
                if (uRLEscapingCharset != null) {
                    return new SimpleScalar(uRLEscapingCharset);
                }
                return null;
            } else if (str == "error") {
                return new SimpleScalar(environment.getCurrentRecoveredErrorMessage());
            } else {
                if (str == NOW) {
                    return new SimpleDate(new Date(), 3);
                }
                throw new _MiscTemplateException((Expression) this, new Object[]{"Invalid built-in variable: ", this.name});
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(".");
        stringBuffer.append(this.name);
        return stringBuffer.toString();
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(".");
        stringBuffer.append(this.name);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return getCanonicalForm();
    }

    static class VarsHash implements TemplateHashModel {
        Environment env;

        public boolean isEmpty() {
            return false;
        }

        VarsHash(Environment environment) {
            this.env = environment;
        }

        public TemplateModel get(String str) throws TemplateModelException {
            return this.env.getVariable(str);
        }
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
