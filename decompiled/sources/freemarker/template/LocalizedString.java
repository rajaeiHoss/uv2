package freemarker.template;

import freemarker.core.Environment;
import java.util.Locale;

public abstract class LocalizedString implements TemplateScalarModel {
    public abstract String getLocalizedString(Locale locale) throws TemplateModelException;

    public String getAsString() throws TemplateModelException {
        return getLocalizedString(Environment.getCurrentEnvironment().getLocale());
    }
}
