package freemarker.core;

import freemarker.template.TemplateException;

public class _DelayedGetMessageWithoutStackTop extends _DelayedConversionToString {
    public _DelayedGetMessageWithoutStackTop(TemplateException templateException) {
        super(templateException);
    }

    /* access modifiers changed from: protected */
    public String doConversion(Object obj) {
        return ((TemplateException) obj).getMessageWithoutStackTop();
    }
}
