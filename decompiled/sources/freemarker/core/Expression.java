package freemarker.core;

import freemarker.ext.beans.BeanModel;
import freemarker.template.Template;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;

public abstract class Expression extends TemplateObject {
    TemplateModel constantValue;

    /* access modifiers changed from: package-private */
    public abstract TemplateModel _eval(Environment environment) throws TemplateException;

    /* access modifiers changed from: protected */
    public abstract Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, ReplacemenetState replacemenetState);

    /* access modifiers changed from: package-private */
    public abstract boolean isLiteral();

    /* access modifiers changed from: package-private */
    public void setLocation(Template template, int i, int i2, int i3, int i4) throws ParseException {
        super.setLocation(template, i, i2, i3, i4);
        if (isLiteral()) {
            try {
                this.constantValue = _eval((Environment) null);
            } catch (Exception unused) {
            }
        }
    }

    public final TemplateModel getAsTemplateModel(Environment environment) throws TemplateException {
        return eval(environment);
    }

    /* access modifiers changed from: package-private */
    public final TemplateModel eval(Environment environment) throws TemplateException {
        TemplateModel templateModel = this.constantValue;
        return templateModel != null ? templateModel : _eval(environment);
    }

    /* access modifiers changed from: package-private */
    public String evalAndCoerceToString(Environment environment) throws TemplateException {
        return EvalUtil.coerceModelToString(eval(environment), this, (String) null, environment);
    }

    /* access modifiers changed from: package-private */
    public String evalAndCoerceToString(Environment environment, String str) throws TemplateException {
        return EvalUtil.coerceModelToString(eval(environment), this, str, environment);
    }

    static String coerceModelToString(TemplateModel templateModel, Expression expression, Environment environment) throws TemplateException {
        return EvalUtil.coerceModelToString(templateModel, expression, (String) null, environment);
    }

    /* access modifiers changed from: package-private */
    public Number evalToNumber(Environment environment) throws TemplateException {
        return modelToNumber(eval(environment), environment);
    }

    /* access modifiers changed from: package-private */
    public Number modelToNumber(TemplateModel templateModel, Environment environment) throws TemplateException {
        if (templateModel instanceof TemplateNumberModel) {
            return EvalUtil.modelToNumber((TemplateNumberModel) templateModel, this);
        }
        throw new NonNumericalException(this, templateModel, environment);
    }

    /* access modifiers changed from: package-private */
    public boolean evalToBoolean(Environment environment) throws TemplateException {
        return modelToBoolean(eval(environment), environment);
    }

    /* access modifiers changed from: package-private */
    public boolean modelToBoolean(TemplateModel templateModel, Environment environment) throws TemplateException {
        if (templateModel instanceof TemplateBooleanModel) {
            return ((TemplateBooleanModel) templateModel).getAsBoolean();
        }
        if (environment.isClassicCompatible()) {
            return templateModel != null && !isEmpty(templateModel);
        }
        throw new NonBooleanException(this, templateModel, environment);
    }

    /* access modifiers changed from: package-private */
    public final Expression deepCloneWithIdentifierReplaced(String str, Expression expression, ReplacemenetState replacemenetState) {
        Expression deepCloneWithIdentifierReplaced_inner = deepCloneWithIdentifierReplaced_inner(str, expression, replacemenetState);
        if (deepCloneWithIdentifierReplaced_inner.beginLine == 0) {
            deepCloneWithIdentifierReplaced_inner.copyLocationFrom(this);
        }
        return deepCloneWithIdentifierReplaced_inner;
    }

    static class ReplacemenetState {
        boolean replacementAlreadyInUse;

        ReplacemenetState() {
        }
    }

    static boolean isEmpty(TemplateModel templateModel) throws TemplateModelException {
        if (templateModel instanceof BeanModel) {
            return ((BeanModel) templateModel).isEmpty();
        }
        if (templateModel instanceof TemplateSequenceModel) {
            if (((TemplateSequenceModel) templateModel).size() == 0) {
                return true;
            }
            return false;
        } else if (templateModel instanceof TemplateScalarModel) {
            String asString = ((TemplateScalarModel) templateModel).getAsString();
            if (asString == null || asString.length() == 0) {
                return true;
            }
            return false;
        } else if (templateModel == null) {
            return true;
        } else {
            if (templateModel instanceof TemplateCollectionModel) {
                return !((TemplateCollectionModel) templateModel).iterator().hasNext();
            }
            if (templateModel instanceof TemplateHashModel) {
                return ((TemplateHashModel) templateModel).isEmpty();
            }
            return !(templateModel instanceof TemplateNumberModel) && !(templateModel instanceof TemplateDateModel) && !(templateModel instanceof TemplateBooleanModel);
        }
    }

    /* access modifiers changed from: package-private */
    public void assertNonNull(TemplateModel templateModel, Environment environment) throws InvalidReferenceException {
        if (templateModel == null) {
            throw InvalidReferenceException.getInstance(this, environment);
        }
    }
}
