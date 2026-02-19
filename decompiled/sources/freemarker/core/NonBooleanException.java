package freemarker.core;

import freemarker.template.TemplateModel;
import org.jivesoftware.smackx.FormField;

public class NonBooleanException extends UnexpectedTypeException {
    public NonBooleanException(Environment environment) {
        super(environment, "Expecting boolean value here");
    }

    public NonBooleanException(String str, Environment environment) {
        super(environment, str);
    }

    NonBooleanException(Environment environment, _ErrorDescriptionBuilder _errordescriptionbuilder) {
        super(environment, _errordescriptionbuilder);
    }

    NonBooleanException(Expression expression, TemplateModel templateModel, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, FormField.TYPE_BOOLEAN, environment);
    }

    NonBooleanException(Expression expression, TemplateModel templateModel, String str, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, FormField.TYPE_BOOLEAN, str, environment);
    }

    NonBooleanException(Expression expression, TemplateModel templateModel, String[] strArr, Environment environment) throws InvalidReferenceException {
        super(expression, templateModel, FormField.TYPE_BOOLEAN, strArr, environment);
    }
}
