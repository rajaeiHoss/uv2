package freemarker.core;

import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class UnexpectedTypeException extends TemplateException {
    public UnexpectedTypeException(Environment environment, String str) {
        super(str, environment);
    }

    UnexpectedTypeException(Environment environment, _ErrorDescriptionBuilder _errordescriptionbuilder) {
        super((Throwable) null, environment, _errordescriptionbuilder, true);
    }

    UnexpectedTypeException(Expression expression, TemplateModel templateModel, String str, Environment environment) throws InvalidReferenceException {
        super((Throwable) null, environment, newDesciptionBuilder(expression, templateModel, str, environment), true);
    }

    UnexpectedTypeException(Expression expression, TemplateModel templateModel, String str, String str2, Environment environment) throws InvalidReferenceException {
        super((Throwable) null, environment, newDesciptionBuilder(expression, templateModel, str, environment).tip(str2), true);
    }

    UnexpectedTypeException(Expression expression, TemplateModel templateModel, String str, String[] strArr, Environment environment) throws InvalidReferenceException {
        super((Throwable) null, environment, newDesciptionBuilder(expression, templateModel, str, environment).tips((Object[]) strArr), true);
    }

    private static _ErrorDescriptionBuilder newDesciptionBuilder(Expression expression, TemplateModel templateModel, String str, Environment environment) throws InvalidReferenceException {
        if (templateModel != null) {
            return new _ErrorDescriptionBuilder(unexpectedTypeErrorDescription(str, templateModel)).blame(expression).showBlamer(true);
        }
        throw InvalidReferenceException.getInstance(expression, environment);
    }

    private static Object[] unexpectedTypeErrorDescription(String str, TemplateModel templateModel) {
        return new Object[]{"Expected ", new _DelayedAOrAn(str), ", but this evaluated to ", new _DelayedAOrAn(new _DelayedFTLTypeDescription(templateModel)), ":"};
    }
}
