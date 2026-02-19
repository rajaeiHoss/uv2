package freemarker.core;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import freemarker.template.SimpleDate;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.utility.NumberUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import kotlin.jvm.internal.LongCompanionObject;

class NumericalBuiltins {
    private static final BigDecimal BIG_DECIMAL_LONG_MAX = BigDecimal.valueOf(LongCompanionObject.MAX_VALUE);
    private static final BigDecimal BIG_DECIMAL_LONG_MIN = BigDecimal.valueOf(Long.MIN_VALUE);
    /* access modifiers changed from: private */
    public static final BigDecimal BIG_DECIMAL_ONE = new BigDecimal("1");
    private static final BigInteger BIG_INTEGER_LONG_MAX = BigInteger.valueOf(LongCompanionObject.MAX_VALUE);
    private static final BigInteger BIG_INTEGER_LONG_MIN = BigInteger.valueOf(Long.MIN_VALUE);

    private NumericalBuiltins() {
    }

    private static abstract class NumberBuiltIn extends BuiltIn {
        /* access modifiers changed from: package-private */
        public abstract TemplateModel calculateResult(Number number, TemplateModel templateModel) throws TemplateModelException;

        private NumberBuiltIn() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            return calculateResult(this.target.modelToNumber(eval, environment), eval);
        }
    }

    static class byteBI extends NumberBuiltIn {
        byteBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) {
            if (number instanceof Byte) {
                return templateModel;
            }
            return new SimpleNumber((Number) new Byte(number.byteValue()));
        }
    }

    static class shortBI extends NumberBuiltIn {
        shortBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) {
            if (number instanceof Short) {
                return templateModel;
            }
            return new SimpleNumber((Number) new Short(number.shortValue()));
        }
    }

    static class intBI extends NumberBuiltIn {
        intBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) {
            if (number instanceof Integer) {
                return templateModel;
            }
            return new SimpleNumber(number.intValue());
        }
    }

    static class longBI extends BuiltIn {
        longBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if (!(eval instanceof TemplateNumberModel) && (eval instanceof TemplateDateModel)) {
                return new SimpleNumber(EvalUtil.modelToDate((TemplateDateModel) eval, this.target).getTime());
            }
            Number modelToNumber = this.target.modelToNumber(eval, environment);
            if (modelToNumber instanceof Long) {
                return eval;
            }
            return new SimpleNumber(modelToNumber.longValue());
        }
    }

    static class floatBI extends NumberBuiltIn {
        floatBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) {
            if (number instanceof Float) {
                return templateModel;
            }
            return new SimpleNumber(number.floatValue());
        }
    }

    static class doubleBI extends NumberBuiltIn {
        doubleBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) {
            if (number instanceof Double) {
                return templateModel;
            }
            return new SimpleNumber(number.doubleValue());
        }
    }

    static class floorBI extends NumberBuiltIn {
        floorBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) {
            return new SimpleNumber((Number) new BigDecimal(number.doubleValue()).divide(NumericalBuiltins.BIG_DECIMAL_ONE, 0, 3));
        }
    }

    static class ceilingBI extends NumberBuiltIn {
        ceilingBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) {
            return new SimpleNumber((Number) new BigDecimal(number.doubleValue()).divide(NumericalBuiltins.BIG_DECIMAL_ONE, 0, 2));
        }
    }

    static class roundBI extends NumberBuiltIn {
        private static final BigDecimal half = new BigDecimal("0.5");

        roundBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) {
            return new SimpleNumber((Number) new BigDecimal(number.doubleValue()).add(half).divide(NumericalBuiltins.BIG_DECIMAL_ONE, 0, 3));
        }
    }

    static class absBI extends NumberBuiltIn {
        absBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) throws TemplateModelException {
            if (number instanceof Integer) {
                int intValue = ((Integer) number).intValue();
                return intValue < 0 ? new SimpleNumber(-intValue) : templateModel;
            } else if (number instanceof BigDecimal) {
                BigDecimal bigDecimal = (BigDecimal) number;
                return bigDecimal.signum() < 0 ? new SimpleNumber((Number) bigDecimal.negate()) : templateModel;
            } else if (number instanceof Double) {
                double doubleValue = ((Double) number).doubleValue();
                return doubleValue < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? new SimpleNumber(-doubleValue) : templateModel;
            } else if (number instanceof Float) {
                float floatValue = ((Float) number).floatValue();
                return floatValue < 0.0f ? new SimpleNumber(-floatValue) : templateModel;
            } else if (number instanceof Long) {
                long longValue = ((Long) number).longValue();
                return longValue < 0 ? new SimpleNumber(-longValue) : templateModel;
            } else if (number instanceof Short) {
                short shortValue = ((Short) number).shortValue();
                return shortValue < 0 ? new SimpleNumber(-shortValue) : templateModel;
            } else if (number instanceof Byte) {
                byte byteValue = ((Byte) number).byteValue();
                return byteValue < 0 ? new SimpleNumber(-byteValue) : templateModel;
            } else if (number instanceof BigInteger) {
                BigInteger bigInteger = (BigInteger) number;
                return bigInteger.signum() < 0 ? new SimpleNumber((Number) bigInteger.negate()) : templateModel;
            } else {
                throw new _TemplateModelException(new Object[]{"Unsupported number class: ", number.getClass()});
            }
        }
    }

    static class is_nanBI extends NumberBuiltIn {
        is_nanBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) throws TemplateModelException {
            return NumberUtil.isNaN(number) ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    static class is_infiniteBI extends NumberBuiltIn {
        is_infiniteBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) throws TemplateModelException {
            return NumberUtil.isInfinite(number) ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE;
        }
    }

    /* access modifiers changed from: private */
    public static final long safeToLong(Number number) throws TemplateModelException {
        if (number instanceof Double) {
            double round = (double) Math.round(((Double) number).doubleValue());
            if (round <= 9.223372036854776E18d && round >= -9.223372036854776E18d) {
                return (long) round;
            }
            throw new _TemplateModelException(new Object[]{"Number doesn't fit into a 64 bit signed integer (long): ", new Double(round)});
        } else if (number instanceof Float) {
            float round2 = (float) Math.round(((Float) number).floatValue());
            if (round2 <= 9.223372E18f && round2 >= -9.223372E18f) {
                return (long) round2;
            }
            throw new _TemplateModelException(new Object[]{"Number doesn't fit into a 64 bit signed integer (long): ", new Float(round2)});
        } else if (number instanceof BigDecimal) {
            BigDecimal scale = ((BigDecimal) number).setScale(0, 4);
            if (scale.compareTo(BIG_DECIMAL_LONG_MAX) <= 0 && scale.compareTo(BIG_DECIMAL_LONG_MIN) >= 0) {
                return scale.longValue();
            }
            throw new _TemplateModelException(new Object[]{"Number doesn't fit into a 64 bit signed integer (long): ", scale});
        } else if (number instanceof BigInteger) {
            BigInteger bigInteger = (BigInteger) number;
            if (bigInteger.compareTo(BIG_INTEGER_LONG_MAX) <= 0 && bigInteger.compareTo(BIG_INTEGER_LONG_MIN) >= 0) {
                return bigInteger.longValue();
            }
            throw new _TemplateModelException(new Object[]{"Number doesn't fit into a 64 bit signed integer (long): ", bigInteger});
        } else if ((number instanceof Long) || (number instanceof Integer) || (number instanceof Byte) || (number instanceof Short)) {
            return number.longValue();
        } else {
            throw new _TemplateModelException(new Object[]{"Unsupported number type: ", number.getClass()});
        }
    }

    static class number_to_dateBI extends NumberBuiltIn {
        private final int dateType;

        number_to_dateBI(int i) {
            super();
            this.dateType = i;
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(Number number, TemplateModel templateModel) throws TemplateModelException {
            return new SimpleDate(new Date(NumericalBuiltins.safeToLong(number)), this.dateType);
        }
    }
}
