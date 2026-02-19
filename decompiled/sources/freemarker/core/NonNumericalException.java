package freemarker.core;

import freemarker.template.TemplateModel;

public class NonNumericalException extends UnexpectedTypeException {
    public NonNumericalException(Environment environment) {
        super(environment, "Expecting numerical value here");
    }

    public NonNumericalException(String str, Environment environment) {
        super(environment, str);
    }

    NonNumericalException(_ErrorDescriptionBuilder _errordescriptionbuilder, Environment environment) {
        super(environment, _errordescriptionbuilder);
    }

    NonNumericalException(Expression expression, TemplateModel templateModel, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, "number", environment);
    }

    NonNumericalException(Expression expression, TemplateModel templateModel, String str, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, "number", str, environment);
    }

    NonNumericalException(Expression expression, TemplateModel templateModel, String[] strArr, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, "number", strArr, environment);
    }

    static NonNumericalException newMalformedNumberException(Expression expression, String str, Environment environment) {
        return new NonNumericalException(new _ErrorDescriptionBuilder(new Object[]{"Can't convert this string to number: ", new _DelayedJQuote(str)}).blame(expression), environment);
    }
}
