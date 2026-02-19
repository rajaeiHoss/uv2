package freemarker.template;

import freemarker.core.Environment;
import freemarker.core._ErrorDescriptionBuilder;

public class TemplateModelException extends TemplateException {
    public TemplateModelException() {
        this((String) null, (Exception) null);
    }

    public TemplateModelException(String str) {
        this(str, (Exception) null);
    }

    public TemplateModelException(Exception exc) {
        this((String) null, exc);
    }

    public TemplateModelException(Throwable th) {
        this((String) null, th);
    }

    public TemplateModelException(String str, Exception exc) {
        super(str, exc, (Environment) null);
    }

    public TemplateModelException(String str, Throwable th) {
        super(str, th, (Environment) null);
    }

    protected TemplateModelException(Throwable th, Environment environment, String str, boolean z) {
        super(str, th, environment);
    }

    protected TemplateModelException(Throwable th, Environment environment, _ErrorDescriptionBuilder _errordescriptionbuilder, boolean z) {
        super(th, environment, _errordescriptionbuilder, true);
    }
}
