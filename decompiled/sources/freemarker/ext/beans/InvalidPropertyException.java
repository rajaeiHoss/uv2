package freemarker.ext.beans;

import freemarker.template.TemplateModelException;

public class InvalidPropertyException extends TemplateModelException {
    public InvalidPropertyException(String str) {
        super(str);
    }
}
