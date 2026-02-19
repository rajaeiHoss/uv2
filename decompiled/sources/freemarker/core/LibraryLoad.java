package freemarker.core;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import kotlin.text.Typography;

public final class LibraryLoad extends TemplateElement {
    private String namespace;
    private Expression templateName;
    private final String templatePath;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#import";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    LibraryLoad(Template template, Expression expression, String str) {
        this.namespace = str;
        String name = template.getName();
        String str2 = "";
        name = name == null ? str2 : name;
        int lastIndexOf = name.lastIndexOf(47);
        this.templatePath = lastIndexOf != -1 ? name.substring(0, lastIndexOf + 1) : str2;
        this.templateName = expression;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        String str;
        String evalAndCoerceToString = this.templateName.evalAndCoerceToString(environment);
        try {
            if (!environment.isClassicCompatible()) {
                if (evalAndCoerceToString.indexOf("://") <= 0) {
                    if (evalAndCoerceToString.length() <= 0 || evalAndCoerceToString.charAt(0) != '/') {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(this.templatePath);
                        stringBuffer.append(evalAndCoerceToString);
                        evalAndCoerceToString = stringBuffer.toString();
                    } else {
                        int indexOf = this.templatePath.indexOf("://");
                        if (indexOf > 0) {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append(this.templatePath.substring(0, indexOf + 2));
                            stringBuffer2.append(evalAndCoerceToString);
                            str = stringBuffer2.toString();
                        } else {
                            str = evalAndCoerceToString.substring(1);
                        }
                        evalAndCoerceToString = str;
                    }
                }
            }
            environment.importLib(environment.getTemplateForImporting(evalAndCoerceToString), this.namespace);
        } catch (ParseException e) {
            throw new _MiscTemplateException((Throwable) e, environment, new Object[]{"Error parsing imported template ", evalAndCoerceToString});
        } catch (IOException e2) {
            throw new _MiscTemplateException((Throwable) e2, environment, new Object[]{"Error reading imported template ", evalAndCoerceToString});
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
        stringBuffer.append(this.templateName);
        stringBuffer.append(" as ");
        stringBuffer.append(this.namespace);
        if (z) {
            stringBuffer.append("/>");
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.templateName;
        }
        if (i == 1) {
            return this.namespace;
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.TEMPLATE_NAME;
        }
        if (i == 1) {
            return ParameterRole.NAMESPACE;
        }
        throw new IndexOutOfBoundsException();
    }

    public String getTemplateName() {
        return this.templateName.toString();
    }
}
