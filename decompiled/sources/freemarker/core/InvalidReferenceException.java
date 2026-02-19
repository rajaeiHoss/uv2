package freemarker.core;

import freemarker.template.TemplateException;

public class InvalidReferenceException extends TemplateException {
    static final InvalidReferenceException FAST_INSTANCE = new InvalidReferenceException("Invalid reference. Details are unavilable, as this should have been handled by an FTL construct. If it wasn't, that's problably a bug in FreeMarker.", (Environment) null);
    private static final String[] TIP = {"If the failing expression is known to be legally null/missing, either specify a default value with myOptionalVar!myDefault, or use ", "<#if myOptionalVar??>", "when-present", "<#else>", "when-missing", "</#if>", ". (These only cover the last step of the expression; to cover the whole expression, use parenthessis: (myOptionVar.foo)!myDefault, (myOptionVar.foo)??"};

    public InvalidReferenceException(Environment environment) {
        super("Invalid reference", environment);
    }

    public InvalidReferenceException(String str, Environment environment) {
        super(str, environment);
    }

    InvalidReferenceException(_ErrorDescriptionBuilder _errordescriptionbuilder, Environment environment) {
        super((Throwable) null, environment, _errordescriptionbuilder, true);
    }

    static InvalidReferenceException getInstance(Expression expression, Environment environment) {
        if (environment != null && environment.getFastInvalidReferenceExceptions()) {
            return FAST_INSTANCE;
        }
        if (expression != null) {
            return new InvalidReferenceException(new _ErrorDescriptionBuilder("The following has evaluated to null or missing:").blame(expression).tip((Object[]) TIP), environment);
        }
        return new InvalidReferenceException(environment);
    }
}
