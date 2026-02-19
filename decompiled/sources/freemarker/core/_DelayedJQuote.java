package freemarker.core;

import freemarker.template.utility.StringUtil;

public class _DelayedJQuote extends _DelayedConversionToString {
    public _DelayedJQuote(Object obj) {
        super(obj);
    }

    /* access modifiers changed from: protected */
    public String doConversion(Object obj) {
        return StringUtil.jQuote(obj);
    }
}
