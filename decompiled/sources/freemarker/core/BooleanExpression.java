package freemarker.core;

import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

abstract class BooleanExpression extends Expression {
    BooleanExpression() {
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        return evalToBoolean(environment) ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
    }
}
