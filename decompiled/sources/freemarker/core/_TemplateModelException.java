package freemarker.core;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.ClassUtil;

public class _TemplateModelException extends TemplateModelException {
    public _TemplateModelException(String str) {
        super(str);
    }

    public _TemplateModelException(Throwable th, String str) {
        this(th, (Environment) null, str);
    }

    public _TemplateModelException(Environment environment, String str) {
        this((Throwable) null, environment, str);
    }

    public _TemplateModelException(Throwable th, Environment environment) {
        this(th, environment, (String) null);
    }

    public _TemplateModelException(Throwable th) {
        this(th, (Environment) null, (String) null);
    }

    public _TemplateModelException(Throwable th, Environment environment, String str) {
        super(th, environment, str, true);
    }

    public _TemplateModelException(_ErrorDescriptionBuilder _errordescriptionbuilder) {
        this((Environment) null, _errordescriptionbuilder);
    }

    public _TemplateModelException(Environment environment, _ErrorDescriptionBuilder _errordescriptionbuilder) {
        this((Throwable) null, environment, _errordescriptionbuilder);
    }

    public _TemplateModelException(Throwable th, Environment environment, _ErrorDescriptionBuilder _errordescriptionbuilder) {
        super(th, environment, _errordescriptionbuilder, true);
    }

    public _TemplateModelException(Object[] objArr) {
        this((Environment) null, objArr);
    }

    public _TemplateModelException(Environment environment, Object[] objArr) {
        this((Throwable) null, environment, objArr);
    }

    public _TemplateModelException(Throwable th, Object[] objArr) {
        this(th, (Environment) null, objArr);
    }

    public _TemplateModelException(Throwable th, Environment environment, Object[] objArr) {
        super(th, environment, new _ErrorDescriptionBuilder(objArr), true);
    }

    public _TemplateModelException(Expression expression, Object[] objArr) {
        this(expression, (Environment) null, objArr);
    }

    public _TemplateModelException(Expression expression, Environment environment, Object[] objArr) {
        this(expression, (Throwable) null, environment, objArr);
    }

    public _TemplateModelException(Expression expression, Throwable th, Environment environment, Object[] objArr) {
        super(th, environment, new _ErrorDescriptionBuilder(objArr).blame(expression), true);
    }

    public _TemplateModelException(Expression expression, String str) {
        this(expression, (Environment) null, str);
    }

    public _TemplateModelException(Expression expression, Environment environment, String str) {
        this(expression, (Throwable) null, environment, str);
    }

    public _TemplateModelException(Expression expression, Throwable th, Environment environment, String str) {
        super(th, environment, new _ErrorDescriptionBuilder(str).blame(expression), true);
    }

    static Object[] modelHasStoredNullDescription(Class cls, TemplateModel templateModel) {
        return new Object[]{"The FreeMarker value exists, but has nothing inside it; the TemplateModel object (class: ", templateModel.getClass().getName(), ") has returned a null instead of a ", ClassUtil.getShortClassName(cls), ". ", "This is possibly a bug in the non-FreeMarker code that builds the data-model."};
    }
}
