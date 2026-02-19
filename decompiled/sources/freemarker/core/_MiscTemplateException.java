package freemarker.core;

import freemarker.template.TemplateException;

public class _MiscTemplateException extends TemplateException {
    public _MiscTemplateException(String str) {
        super(str, (Environment) null);
    }

    public _MiscTemplateException(Environment environment, String str) {
        super(str, environment);
    }

    public _MiscTemplateException(Throwable th, String str) {
        this(th, (Environment) null, str);
    }

    public _MiscTemplateException(Throwable th, Environment environment) {
        this(th, environment, (String) null);
    }

    public _MiscTemplateException(Throwable th) {
        this(th, (Environment) null, (String) null);
    }

    public _MiscTemplateException(Throwable th, Environment environment, String str) {
        super(str, th, environment);
    }

    public _MiscTemplateException(_ErrorDescriptionBuilder _errordescriptionbuilder) {
        this((Environment) null, _errordescriptionbuilder);
    }

    public _MiscTemplateException(Environment environment, _ErrorDescriptionBuilder _errordescriptionbuilder) {
        this((Throwable) null, environment, _errordescriptionbuilder);
    }

    public _MiscTemplateException(Throwable th, Environment environment, _ErrorDescriptionBuilder _errordescriptionbuilder) {
        super(th, environment, _errordescriptionbuilder, true);
    }

    public _MiscTemplateException(Object[] objArr) {
        this((Environment) null, objArr);
    }

    public _MiscTemplateException(Environment environment, Object[] objArr) {
        this((Throwable) null, environment, objArr);
    }

    public _MiscTemplateException(Throwable th, Object[] objArr) {
        this(th, (Environment) null, objArr);
    }

    public _MiscTemplateException(Throwable th, Environment environment, Object[] objArr) {
        super(th, environment, new _ErrorDescriptionBuilder(objArr), true);
    }

    public _MiscTemplateException(Expression expression, Object[] objArr) {
        this(expression, (Environment) null, objArr);
    }

    public _MiscTemplateException(Expression expression, Environment environment, Object[] objArr) {
        this(expression, (Throwable) null, environment, objArr);
    }

    public _MiscTemplateException(Expression expression, Throwable th, Environment environment, Object[] objArr) {
        super(th, environment, new _ErrorDescriptionBuilder(objArr).blame(expression), true);
    }

    public _MiscTemplateException(Expression expression, String str) {
        this(expression, (Environment) null, str);
    }

    public _MiscTemplateException(Expression expression, Environment environment, String str) {
        this(expression, (Throwable) null, environment, str);
    }

    public _MiscTemplateException(Expression expression, Throwable th, Environment environment, String str) {
        super(th, environment, new _ErrorDescriptionBuilder(str).blame(expression), true);
    }
}
