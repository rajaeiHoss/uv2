package freemarker.core;

import freemarker.core.Expression;
import freemarker.template.SimpleScalar;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import java.util.ArrayList;
import java.util.Collection;

final class DynamicKeyName extends Expression {
    private final Expression nameExpression;
    private final Expression target;

    /* access modifiers changed from: package-private */
    public String getNodeTypeSymbol() {
        return "...[...]";
    }

    /* access modifiers changed from: package-private */
    public int getParameterCount() {
        return 2;
    }

    DynamicKeyName(Expression expression, Expression expression2) {
        this.target = expression;
        this.nameExpression = expression2;
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        TemplateModel eval = this.target.eval(environment);
        if (eval != null) {
            Expression expression = this.nameExpression;
            if (expression instanceof Range) {
                return dealWithRangeKey(eval, (Range) expression, environment);
            }
            TemplateModel eval2 = expression.eval(environment);
            if (eval2 == null) {
                if (environment.isClassicCompatible()) {
                    eval2 = TemplateScalarModel.EMPTY_STRING;
                } else {
                    this.nameExpression.assertNonNull(eval2, environment);
                }
            }
            if (eval2 instanceof TemplateNumberModel) {
                return dealWithNumericalKey(eval, this.nameExpression.modelToNumber(eval2, environment).intValue(), environment);
            }
            if (eval2 instanceof TemplateScalarModel) {
                return dealWithStringKey(eval, EvalUtil.modelToString((TemplateScalarModel) eval2, this.nameExpression, environment), environment);
            }
            throw new UnexpectedTypeException(this.nameExpression, eval2, "number, range, or string", environment);
        } else if (environment.isClassicCompatible()) {
            return null;
        } else {
            throw InvalidReferenceException.getInstance(this.target, environment);
        }
    }

    private TemplateModel dealWithNumericalKey(TemplateModel templateModel, int i, Environment environment) throws TemplateException {
        int i2;
        if (templateModel instanceof TemplateSequenceModel) {
            TemplateSequenceModel templateSequenceModel = (TemplateSequenceModel) templateModel;
            try {
                i2 = templateSequenceModel.size();
            } catch (Exception unused) {
                i2 = Integer.MAX_VALUE;
            }
            if (i < i2) {
                return templateSequenceModel.get(i);
            }
            return null;
        }
        try {
            return new SimpleScalar(this.target.evalAndCoerceToString(environment).substring(i, i + 1));
        } catch (RuntimeException e) {
            throw new _MiscTemplateException((Throwable) e, environment);
        } catch (NonStringException unused2) {
            throw new UnexpectedTypeException(this.target, templateModel, "sequence or string (or something that's implicitly convertible to string)", environment);
        }
    }

    private TemplateModel dealWithStringKey(TemplateModel templateModel, String str, Environment environment) throws TemplateException {
        if (templateModel instanceof TemplateHashModel) {
            return ((TemplateHashModel) templateModel).get(str);
        }
        throw new UnexpectedTypeException(this.target, templateModel, "hash", environment);
    }

    private TemplateModel dealWithRangeKey(TemplateModel templateModel, Range range, Environment environment) throws TemplateException {
        TemplateModel templateModel2 = templateModel;
        Range range2 = range;
        Environment environment2 = environment;
        int intValue = range2.lho.evalToNumber(environment2).intValue();
        boolean hasRho = range.hasRho();
        int intValue2 = hasRho ? range2.rho.evalToNumber(environment2).intValue() : 0;
        if (templateModel2 instanceof TemplateSequenceModel) {
            TemplateSequenceModel templateSequenceModel = (TemplateSequenceModel) templateModel2;
            if (!hasRho) {
                intValue2 = templateSequenceModel.size() - 1;
            }
            if (intValue < 0) {
                throw new _MiscTemplateException(range2.lho, new Object[]{"Negative starting index ", new Integer(intValue), " for slicing range."});
            } else if (intValue2 < 0) {
                throw new _MiscTemplateException(range2.rho, new Object[]{"Negative ending index ", new Integer(intValue2), " for slicing range."});
            } else if (intValue >= templateSequenceModel.size()) {
                throw new _MiscTemplateException(range2.lho, new Object[]{"Left side index of range out of bounds, is ", new Integer(intValue), ", but the sequence has only ", new Integer(templateSequenceModel.size()), " element(s). ", "(Note that indices are 0 based, and ranges are inclusive)."});
            } else if (intValue2 < templateSequenceModel.size()) {
                ArrayList arrayList = new ArrayList(Math.abs(intValue - intValue2) + 1);
                if (intValue > intValue2) {
                    while (intValue >= intValue2) {
                        arrayList.add(templateSequenceModel.get(intValue));
                        intValue--;
                    }
                } else {
                    while (intValue <= intValue2) {
                        arrayList.add(templateSequenceModel.get(intValue));
                        intValue++;
                    }
                }
                return new SimpleSequence((Collection) arrayList);
            } else {
                throw new _MiscTemplateException(range2.rho, new Object[]{"Right side index of range out of bounds, is ", new Integer(intValue2), ", but the sequence has only ", new Integer(templateSequenceModel.size()), " element(s). ", "(Note that indices are 0 based, and ranges are inclusive)."});
            }
        } else {
            try {
                String evalAndCoerceToString = this.target.evalAndCoerceToString(environment2);
                if (!hasRho) {
                    intValue2 = evalAndCoerceToString.length() - 1;
                }
                if (intValue < 0) {
                    throw new _MiscTemplateException(range2.lho, new Object[]{"Negative starting index ", new Integer(intValue), " for slicing range."});
                } else if (intValue2 < 0) {
                    throw new _MiscTemplateException(range2.rho, new Object[]{"Negative ending index ", new Integer(intValue2), " for slicing range."});
                } else if (intValue > evalAndCoerceToString.length()) {
                    throw new _MiscTemplateException(range2.lho, new Object[]{"Left side of range out of bounds, is: ", new Integer(intValue), "\nbut the string has ", new Integer(evalAndCoerceToString.length()), " elements."});
                } else if (intValue2 < evalAndCoerceToString.length()) {
                    try {
                        return new SimpleScalar(evalAndCoerceToString.substring(intValue, intValue2 + 1));
                    } catch (RuntimeException e) {
                        throw new _MiscTemplateException((Throwable) e, new Object[]{"Unexpected exception: ", e});
                    }
                } else {
                    throw new _MiscTemplateException(range2.rho, new Object[]{"Right side of range out of bounds, is: ", new Integer(intValue2), "\nbut the string is only ", new Integer(evalAndCoerceToString.length()), " characters long."});
                }
            } catch (NonStringException unused) {
                Expression expression = this.target;
                throw new UnexpectedTypeException(expression, expression.eval(environment2), "string or something automatically convertible to string (number, date or boolean) or sequence", environment2);
            }
        }
    }

    public String getCanonicalForm() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.target.getCanonicalForm());
        stringBuffer.append("[");
        stringBuffer.append(this.nameExpression.getCanonicalForm());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean isLiteral() {
        return this.constantValue != null || (this.target.isLiteral() && this.nameExpression.isLiteral());
    }

    /* access modifiers changed from: package-private */
    public Object getParameterValue(int i) {
        return i == 0 ? this.target : this.nameExpression;
    }

    /* access modifiers changed from: package-private */
    public ParameterRole getParameterRole(int i) {
        return i == 0 ? ParameterRole.LEFT_HAND_OPERAND : ParameterRole.ENCLOSED_OPERAND;
    }

    /* access modifiers changed from: protected */
    public Expression deepCloneWithIdentifierReplaced_inner(String str, Expression expression, Expression.ReplacemenetState replacemenetState) {
        return new DynamicKeyName(this.target.deepCloneWithIdentifierReplaced(str, expression, replacemenetState), this.nameExpression.deepCloneWithIdentifierReplaced(str, expression, replacemenetState));
    }
}
