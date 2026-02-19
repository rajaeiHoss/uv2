package freemarker.core;

import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans._BeansAPI;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import java.util.Date;

class EvalUtil {
    static final int CMP_OP_EQUALS = 1;
    static final int CMP_OP_GREATER_THAN = 4;
    static final int CMP_OP_GREATER_THAN_EQUALS = 6;
    static final int CMP_OP_LESS_THAN = 3;
    static final int CMP_OP_LESS_THAN_EQUALS = 5;
    static final int CMP_OP_NOT_EQUALS = 2;
    private static final String DATE_OF_THE_COMPARISON_IS_OF_TYPE_UNKNOWN = "date of the comparison is of UNKNOWN type (it's not known if it's date-only, time-only, or date-time), and thus can't be used in a comparison.";
    static /* synthetic */ Class class$java$lang$Number;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$util$Date;

    private static String cmpOpToString(int i, String str) {
        if (str != null) {
            return str;
        }
        switch (i) {
            case 1:
                return "equals";
            case 2:
                return "not-equals";
            case 3:
                return "less-than";
            case 4:
                return "greater-than";
            case 5:
                return "less-than-equals";
            case 6:
                return "greater-than-equals";
            default:
                return "???";
        }
    }

    private EvalUtil() {
    }

    static String modelToString(TemplateScalarModel templateScalarModel, Expression expression, Environment environment) throws TemplateModelException {
        String asString = templateScalarModel.getAsString();
        if (asString != null) {
            return asString;
        }
        if (environment == null) {
            environment = Environment.getCurrentEnvironment();
        }
        if (environment != null && environment.isClassicCompatible()) {
            return "";
        }
        Class cls = class$java$lang$String;
        if (cls == null) {
            cls = class$("java.lang.String");
            class$java$lang$String = cls;
        }
        throw newModelHasStoredNullException(cls, templateScalarModel, expression);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static Number modelToNumber(TemplateNumberModel templateNumberModel, Expression expression) throws TemplateModelException {
        Number asNumber = templateNumberModel.getAsNumber();
        if (asNumber != null) {
            return asNumber;
        }
        Class cls = class$java$lang$Number;
        if (cls == null) {
            cls = class$("java.lang.Number");
            class$java$lang$Number = cls;
        }
        throw newModelHasStoredNullException(cls, templateNumberModel, expression);
    }

    static Date modelToDate(TemplateDateModel templateDateModel, Expression expression) throws TemplateModelException, TemplateException {
        Date asDate = templateDateModel.getAsDate();
        if (asDate != null) {
            return asDate;
        }
        Class cls = class$java$util$Date;
        if (cls == null) {
            cls = class$("java.util.Date");
            class$java$util$Date = cls;
        }
        throw newModelHasStoredNullException(cls, templateDateModel, expression);
    }

    private static TemplateModelException newModelHasStoredNullException(Class cls, TemplateModel templateModel, Expression expression) {
        return new _TemplateModelException(expression, _TemplateModelException.modelHasStoredNullDescription(cls, templateModel));
    }

    static boolean compare(Expression expression, int i, String str, Expression expression2, Expression expression3, Environment environment) throws TemplateException {
        Environment environment2 = environment;
        return compare(expression.eval(environment2), expression, i, str, expression2.eval(environment2), expression2, expression3, false, false, false, environment2);
    }

    static boolean compare(TemplateModel templateModel, int i, TemplateModel templateModel2, Environment environment) throws TemplateException {
        return compare(templateModel, (Expression) null, i, (String) null, templateModel2, (Expression) null, (Expression) null, false, false, false, environment);
    }

    static boolean compareLenient(TemplateModel templateModel, int i, TemplateModel templateModel2, Environment environment) throws TemplateException {
        return compare(templateModel, (Expression) null, i, (String) null, templateModel2, (Expression) null, (Expression) null, true, false, false, environment);
    }

    static boolean compare(TemplateModel templateModel, Expression expression, int i, String str, TemplateModel templateModel2, Expression expression2, Expression expression3, boolean z, boolean z2, boolean z3, Environment environment) throws TemplateException {
        TemplateModel templateModel3;
        TemplateModel templateModel4;
        int i2;
        String str2;
        Expression expression4 = expression;
        int i3 = i;
        Expression expression5 = expression2;
        Expression expression6 = expression3;
        Environment environment2 = environment;
        if (templateModel != null) {
            templateModel3 = templateModel;
        } else if (environment2 != null && environment.isClassicCompatible()) {
            templateModel3 = TemplateScalarModel.EMPTY_STRING;
        } else if (z2) {
            return false;
        } else {
            if (expression4 != null) {
                throw InvalidReferenceException.getInstance(expression4, environment2);
            }
            throw new _MiscTemplateException(expression6, environment2, "The left operand of the comparison was undefined or null.");
        }
        if (templateModel2 != null) {
            templateModel4 = templateModel2;
        } else if (environment2 != null && environment.isClassicCompatible()) {
            templateModel4 = TemplateScalarModel.EMPTY_STRING;
        } else if (z3) {
            return false;
        } else {
            if (expression5 != null) {
                throw InvalidReferenceException.getInstance(expression5, environment2);
            }
            throw new _MiscTemplateException(expression6, environment2, "The right operand of the comparison was undefined or null.");
        }
        if ((templateModel3 instanceof TemplateNumberModel) && (templateModel4 instanceof TemplateNumberModel)) {
            try {
                i2 = (environment2 != null ? environment.getArithmeticEngine() : expression4 != null ? expression.getTemplate().getArithmeticEngine() : ArithmeticEngine.BIGDECIMAL_ENGINE).compareNumbers(modelToNumber((TemplateNumberModel) templateModel3, expression4), modelToNumber((TemplateNumberModel) templateModel4, expression5));
            } catch (RuntimeException e) {
                RuntimeException runtimeException = e;
                throw new _MiscTemplateException(expression6, (Throwable) runtimeException, environment2, new Object[]{"Unexpected error while comparing two numbers: ", runtimeException});
            }
        } else if ((templateModel3 instanceof TemplateDateModel) && (templateModel4 instanceof TemplateDateModel)) {
            TemplateDateModel templateDateModel = (TemplateDateModel) templateModel3;
            TemplateDateModel templateDateModel2 = (TemplateDateModel) templateModel4;
            int dateType = templateDateModel.getDateType();
            int dateType2 = templateDateModel2.getDateType();
            if (dateType == 0 || dateType2 == 0) {
                if (dateType == 0) {
                    str2 = "left";
                } else {
                    str2 = "right";
                    expression4 = expression5;
                }
                if (expression4 == null) {
                    expression4 = expression6;
                }
                throw new _MiscTemplateException(expression4, environment2, new Object[]{"The ", str2, " ", DATE_OF_THE_COMPARISON_IS_OF_TYPE_UNKNOWN});
            } else if (dateType == dateType2) {
                i2 = modelToDate(templateDateModel, expression4).compareTo(modelToDate(templateDateModel2, expression5));
            } else {
                throw new _MiscTemplateException(expression6, environment2, new Object[]{"Can't compare dates of different types. Left date type is ", TemplateDateModel.TYPE_NAMES.get(dateType), ", right date type is ", TemplateDateModel.TYPE_NAMES.get(dateType2), "."});
            }
        } else if (!(templateModel3 instanceof TemplateScalarModel) || !(templateModel4 instanceof TemplateScalarModel)) {
            if (!(templateModel3 instanceof TemplateBooleanModel) || !(templateModel4 instanceof TemplateBooleanModel)) {
                if (environment.isClassicCompatible()) {
                    i2 = environment.getCollator().compare(expression4.evalAndCoerceToString(environment2), expression5.evalAndCoerceToString(environment2));
                } else {
                    if (z) {
                        if (i3 == 1) {
                            return false;
                        }
                        if (i3 == 2) {
                            return true;
                        }
                    }
                    throw new _MiscTemplateException(expression6, environment2, new Object[]{"Can't compare values of these types. ", "Allowed comparisons are between two numbers, two strings, two dates, or two booleans.\n", "Left hand operand is ", new _DelayedAOrAn(new _DelayedFTLTypeDescription(templateModel3)), ".\n", "Right hand operand is ", new _DelayedAOrAn(new _DelayedFTLTypeDescription(templateModel4)), "."});
                }
            } else if (i3 == 1 || i3 == 2) {
                i2 = (((TemplateBooleanModel) templateModel3).getAsBoolean() ? 1 : 0) - (((TemplateBooleanModel) templateModel4).getAsBoolean() ? 1 : 0);
            } else {
                throw new _MiscTemplateException(expression6, environment2, new Object[]{"Can't use operator \"", cmpOpToString(i, str), "\" on boolean values."});
            }
        } else if (i3 == 1 || i3 == 2) {
            i2 = environment.getCollator().compare(modelToString((TemplateScalarModel) templateModel3, expression4, environment2), modelToString((TemplateScalarModel) templateModel4, expression5, environment2));
        } else {
            throw new _MiscTemplateException(expression6, environment2, new Object[]{"Can't use operator \"", cmpOpToString(i, str), "\" on string values."});
        }
        switch (i3) {
            case 1:
                if (i2 == 0) {
                    return true;
                }
                return false;
            case 2:
                if (i2 != 0) {
                    return true;
                }
                return false;
            case 3:
                if (i2 < 0) {
                    return true;
                }
                return false;
            case 4:
                if (i2 > 0) {
                    return true;
                }
                return false;
            case 5:
                if (i2 <= 0) {
                    return true;
                }
                return false;
            case 6:
                if (i2 >= 0) {
                    return true;
                }
                return false;
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unsupported comparator operator code: ");
                stringBuffer.append(i3);
                throw new RuntimeException(stringBuffer.toString());
        }
    }

    static String coerceModelToString(TemplateModel templateModel, Expression expression, String str, Environment environment) throws TemplateException {
        if (templateModel instanceof TemplateNumberModel) {
            return environment.formatNumber(modelToNumber((TemplateNumberModel) templateModel, expression));
        }
        if (templateModel instanceof TemplateDateModel) {
            TemplateDateModel templateDateModel = (TemplateDateModel) templateModel;
            return environment.formatDate(modelToDate(templateDateModel, expression), templateDateModel.getDateType());
        } else if (templateModel instanceof TemplateScalarModel) {
            return modelToString((TemplateScalarModel) templateModel, expression, environment);
        } else {
            if (templateModel == null) {
                if (environment.isClassicCompatible()) {
                    return "";
                }
                if (expression != null) {
                    throw InvalidReferenceException.getInstance(expression, environment);
                }
                throw new InvalidReferenceException("Null/missing value (no more informatoin avilable)", environment);
            } else if (templateModel instanceof TemplateBooleanModel) {
                boolean asBoolean = ((TemplateBooleanModel) templateModel).getAsBoolean();
                int classicCompatibleAsInt = environment.getClassicCompatibleAsInt();
                if (classicCompatibleAsInt == 0) {
                    return environment.formatBoolean(asBoolean, false);
                }
                if (classicCompatibleAsInt == 1) {
                    if (asBoolean) {
                        return "true";
                    }
                    return "";
                } else if (classicCompatibleAsInt != 2) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unsupported classic_compatible variation: ");
                    stringBuffer.append(classicCompatibleAsInt);
                    throw new RuntimeException(stringBuffer.toString());
                } else if (templateModel instanceof BeanModel) {
                    return _BeansAPI.getAsClassicCompatibleString((BeanModel) templateModel);
                } else {
                    if (asBoolean) {
                        return "true";
                    }
                    return "";
                }
            } else if (environment.isClassicCompatible() && (templateModel instanceof BeanModel)) {
                return _BeansAPI.getAsClassicCompatibleString((BeanModel) templateModel);
            } else {
                if (str == null || (!(templateModel instanceof TemplateSequenceModel) && !(templateModel instanceof TemplateCollectionModel))) {
                    throw new NonStringException(expression, templateModel, environment);
                }
                throw new NonStringException(expression, templateModel, str, environment);
            }
        }
    }
}
