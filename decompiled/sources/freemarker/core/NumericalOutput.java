package freemarker.core;

import com.streamax.config.constant.Constants;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

final class NumericalOutput extends TemplateElement {
    private final Expression expression;
    private volatile FormatHolder formatCache;
    private final boolean hasFormat;
    private final int maxFracDigits;
    private final int minFracDigits;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "#{...}";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 3;
    }

    /* access modifiers changed from: package-private */
    public boolean heedsOpeningWhitespace() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean heedsTrailingWhitespace() {
        return true;
    }

    NumericalOutput(Expression expression2) {
        this.expression = expression2;
        this.hasFormat = false;
        this.minFracDigits = 0;
        this.maxFracDigits = 0;
    }

    NumericalOutput(Expression expression2, int i, int i2) {
        this.expression = expression2;
        this.hasFormat = true;
        this.minFracDigits = i;
        this.maxFracDigits = i2;
    }

    /* access modifiers changed from: package-private */
    public void accept(Environment environment) throws TemplateException, IOException {
        Number evalToNumber = this.expression.evalToNumber(environment);
        FormatHolder formatHolder = this.formatCache;
        if (formatHolder == null || !formatHolder.locale.equals(environment.getLocale())) {
            synchronized (this) {
                formatHolder = this.formatCache;
                if (formatHolder == null || !formatHolder.locale.equals(environment.getLocale())) {
                    NumberFormat numberInstance = NumberFormat.getNumberInstance(environment.getLocale());
                    if (this.hasFormat) {
                        numberInstance.setMinimumFractionDigits(this.minFracDigits);
                        numberInstance.setMaximumFractionDigits(this.maxFracDigits);
                    } else {
                        numberInstance.setMinimumFractionDigits(0);
                        numberInstance.setMaximumFractionDigits(50);
                    }
                    numberInstance.setGroupingUsed(false);
                    this.formatCache = new FormatHolder(numberInstance, environment.getLocale());
                    formatHolder = this.formatCache;
                }
            }
        }
        environment.getOut().write(formatHolder.format.format(evalToNumber));
    }

    /* access modifiers changed from: protected */
    public String dump(boolean z) {
        StringBuffer stringBuffer = new StringBuffer("#{");
        stringBuffer.append(this.expression.getCanonicalForm());
        if (this.hasFormat) {
            stringBuffer.append(" ; ");
            stringBuffer.append("m");
            stringBuffer.append(this.minFracDigits);
            stringBuffer.append("M");
            stringBuffer.append(this.maxFracDigits);
        }
        stringBuffer.append(Constants.JsonSstringSuffix);
        return stringBuffer.toString();
    }

    private static class FormatHolder {
        final NumberFormat format;
        final Locale locale;

        FormatHolder(NumberFormat numberFormat, Locale locale2) {
            this.format = numberFormat;
            this.locale = locale2;
        }
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        if (i == 0) {
            return this.expression;
        }
        if (i == 1) {
            return new Integer(this.minFracDigits);
        }
        if (i == 2) {
            return new Integer(this.maxFracDigits);
        }
        throw new IndexOutOfBoundsException();
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        if (i == 0) {
            return ParameterRole.CONTENT;
        }
        if (i == 1) {
            return ParameterRole.MINIMUM_DECIMALS;
        }
        if (i == 2) {
            return ParameterRole.MAXIMUM_DECIMALS;
        }
        throw new IndexOutOfBoundsException();
    }
}
