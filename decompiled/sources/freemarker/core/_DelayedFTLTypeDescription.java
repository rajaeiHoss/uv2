package freemarker.core;

import freemarker.template.TemplateModel;
import freemarker.template.utility.ClassUtil;

public class _DelayedFTLTypeDescription extends _DelayedConversionToString {
    public _DelayedFTLTypeDescription(TemplateModel templateModel) {
        super(templateModel);
    }

    /* access modifiers changed from: protected */
    public String doConversion(Object obj) {
        return ClassUtil.getFTLTypeDescription((TemplateModel) obj);
    }
}
