package freemarker.core;

import freemarker.template.Template;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import kotlin.text.Typography;

final class PropertySetting extends TemplateElement {
    private final String key;
    private final Expression value;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#setting";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    PropertySetting(String str, Expression expression) {
        this.key = str;
        this.value = expression;
    }

    /* access modifiers changed from: package-private */
    public void setLocation(Template template, int i, int i2, int i3, int i4) throws ParseException {
        super.setLocation(template, i, i2, i3, i4);
        if (!this.key.equals(Configurable.LOCALE_KEY) && !this.key.equals(Configurable.NUMBER_FORMAT_KEY) && !this.key.equals(Configurable.TIME_FORMAT_KEY) && !this.key.equals(Configurable.DATE_FORMAT_KEY) && !this.key.equals(Configurable.DATETIME_FORMAT_KEY) && !this.key.equals(Configurable.TIME_ZONE_KEY) && !this.key.equals(Configurable.BOOLEAN_FORMAT_KEY) && !this.key.equals(Configurable.CLASSIC_COMPATIBLE_KEY) && !this.key.equals(Configurable.URL_ESCAPING_CHARSET_KEY)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid setting name, or it's not allowed to change the value of the setting with FTL: ");
            stringBuffer.append(this.key);
            throw new ParseException(stringBuffer.toString(), template, i2, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException {
        String str;
        TemplateModel eval = this.value.eval(environment);
        if (eval instanceof TemplateScalarModel) {
            str = ((TemplateScalarModel) eval).getAsString();
        } else if (eval instanceof TemplateBooleanModel) {
            str = ((TemplateBooleanModel) eval).getAsBoolean() ? "true" : "false";
        } else if (eval instanceof TemplateNumberModel) {
            str = ((TemplateNumberModel) eval).getAsNumber().toString();
        } else {
            str = this.value.evalAndCoerceToString(environment);
        }
        environment.setSetting(this.key, str);
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.less);
        }
        stringBuffer.append(getNodeTypeSymbol());
        stringBuffer.append(' ');
        stringBuffer.append(this.key);
        stringBuffer.append('=');
        stringBuffer.append(this.value.getCanonicalForm());
        if (z) {
            stringBuffer.append("/>");
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.key;
        }
        if (i == 1) {
            return this.value;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.ITEM_KEY;
        }
        if (i == 1) {
            return ParameterRole.ITEM_VALUE;
        }
        throw new IndexOutOfBoundsException();
    }
}
