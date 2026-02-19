package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.utility.StringUtil;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

final class StringLiteral extends Expression implements TemplateScalarModel {
    private TemplateElement dynamicValue;
    private final String value;

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 1;
    }

    StringLiteral(String str) {
        this.value = str;
    }

    /* access modifiers changed from: package-private */
    public void checkInterpolation() throws ParseException {
        if (this.value.length() <= 3) {
            return;
        }
        if (this.value.indexOf("${") >= 0 || this.value.indexOf("#{") >= 0) {
            FMParserTokenManager fMParserTokenManager = new FMParserTokenManager(new SimpleCharStream((Reader) new StringReader(this.value), this.beginLine, this.beginColumn + 1, this.value.length()));
            fMParserTokenManager.onlyTextOutput = true;
            FMParser fMParser = new FMParser(fMParserTokenManager);
            fMParser.setTemplate(getTemplate());
            try {
                this.dynamicValue = fMParser.FreeMarkerText();
                this.constantValue = null;
            } catch (ParseException e) {
                e.setTemplateName(getTemplate().getName());
                throw e;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        return new SimpleScalar(evalAndCoerceToString(environment));
    }

    public String getAsString() {
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public boolean isSingleInterpolationLiteral() {
        TemplateElement templateElement = this.dynamicValue;
        return templateElement != null && templateElement.getChildCount() == 1 && (this.dynamicValue.getChildAt(0) instanceof DollarVariable);
    }

    /* access modifiers changed from: package-private */
    public String evalAndCoerceToString(Environment environment) throws TemplateException {
        if (this.dynamicValue == null) {
            return this.value;
        }
        TemplateExceptionHandler templateExceptionHandler = environment.getTemplateExceptionHandler();
        environment.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try {
            String renderElementToString = environment.renderElementToString(this.dynamicValue);
            environment.setTemplateExceptionHandler(templateExceptionHandler);
            return renderElementToString;
        } catch (IOException e) {
            throw new _MiscTemplateException((Throwable) e, environment);
        } catch (Throwable th) {
            environment.setTemplateExceptionHandler(templateExceptionHandler);
            throw th;
        }
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\"");
        stringBuffer.append(StringUtil.FTLStringLiteralEnc(this.value));
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return this.dynamicValue == null ? getCanonicalForm() : "dynamic \"...\"";
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return this.dynamicValue == null;
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        StringLiteral stringLiteral = new StringLiteral(this.value);
        stringLiteral.dynamicValue = this.dynamicValue;
        return stringLiteral;
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.dynamicValue;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.EMBEDDED_TEMPLATE;
        }
        throw new IndexOutOfBoundsException();
    }
}
