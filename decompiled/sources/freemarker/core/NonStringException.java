package freemarker.core;

import freemarker.template.TemplateModel;

public class NonStringException extends UnexpectedTypeException {
    private static final String DEFAULT_DESCRIPTION = "Expecting string or something automatically convertible to string (number, date or boolean) value here";
    static final String TYPES_USABLE_WHERE_STRING_IS_EXPECTED = "string or something automatically convertible to string (number, date or boolean)";

    public NonStringException(Environment environment) {
        super(environment, DEFAULT_DESCRIPTION);
    }

    public NonStringException(String str, Environment environment) {
        super(environment, str);
    }

    NonStringException(Environment environment, _ErrorDescriptionBuilder _errordescriptionbuilder) {
        super(environment, _errordescriptionbuilder);
    }

    NonStringException(Expression expression, TemplateModel templateModel, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, TYPES_USABLE_WHERE_STRING_IS_EXPECTED, environment);
    }

    NonStringException(Expression expression, TemplateModel templateModel, String str, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, TYPES_USABLE_WHERE_STRING_IS_EXPECTED, str, environment);
    }

    NonStringException(Expression expression, TemplateModel templateModel, String[] strArr, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, TYPES_USABLE_WHERE_STRING_IS_EXPECTED, strArr, environment);
    }
}
