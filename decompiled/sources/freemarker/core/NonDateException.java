package freemarker.core;

import freemarker.template.TemplateModel;

public class NonDateException extends UnexpectedTypeException {
    public NonDateException(Environment environment) {
        super(environment, "Expecting date/time value here");
    }

    public NonDateException(String str, Environment environment) {
        super(environment, str);
    }

    NonDateException(Environment environment, _ErrorDescriptionBuilder _errordescriptionbuilder) {
        super(environment, _errordescriptionbuilder);
    }

    NonDateException(Expression expression, TemplateModel templateModel, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, "date", environment);
    }

    NonDateException(Expression expression, TemplateModel templateModel, String str, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, "date", str, environment);
    }

    NonDateException(Expression expression, TemplateModel templateModel, String[] strArr, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, "date", strArr, environment);
    }
}
