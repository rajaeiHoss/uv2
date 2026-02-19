package freemarker.core;

import freemarker.template.TemplateException;
import freemarker.template.utility.NumberUtil;
import freemarker.template.utility.OptimizerUtil;
import freemarker.template.utility.StringUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public abstract class ArithmeticEngine {
    public static final BigDecimalEngine BIGDECIMAL_ENGINE = new BigDecimalEngine();
    public static final ConservativeEngine CONSERVATIVE_ENGINE = new ConservativeEngine();
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$math$BigDecimal;
    static /* synthetic */ Class class$java$math$BigInteger;
    protected int maxScale = 12;
    protected int minScale = 12;
    protected int roundingPolicy = 4;

    public abstract Number add(Number number, Number number2) throws TemplateException;

    public abstract int compareNumbers(Number number, Number number2) throws TemplateException;

    public abstract Number divide(Number number, Number number2) throws TemplateException;

    public abstract Number modulus(Number number, Number number2) throws TemplateException;

    public abstract Number multiply(Number number, Number number2) throws TemplateException;

    public abstract Number subtract(Number number, Number number2) throws TemplateException;

    public abstract Number toNumber(String str);

    public void setMinScale(int i) {
        if (i >= 0) {
            this.minScale = i;
            return;
        }
        throw new IllegalArgumentException("minScale < 0");
    }

    public void setMaxScale(int i) {
        if (i >= this.minScale) {
            this.maxScale = i;
            return;
        }
        throw new IllegalArgumentException("maxScale < minScale");
    }

    public void setRoundingPolicy(int i) {
        if (i == 2 || i == 1 || i == 3 || i == 5 || i == 6 || i == 4 || i == 7 || i == 0) {
            this.roundingPolicy = i;
            return;
        }
        throw new IllegalArgumentException("invalid rounding policy");
    }

    public static class BigDecimalEngine extends ArithmeticEngine {
        public int compareNumbers(Number number, Number number2) {
            int signum = NumberUtil.getSignum(number);
            int signum2 = NumberUtil.getSignum(number2);
            if (signum != signum2) {
                if (signum < signum2) {
                    return -1;
                }
                return signum > signum2 ? 1 : 0;
            } else if (signum == 0 && signum2 == 0) {
                return 0;
            } else {
                return ArithmeticEngine.toBigDecimal(number).compareTo(ArithmeticEngine.toBigDecimal(number2));
            }
        }

        public Number add(Number number, Number number2) {
            return ArithmeticEngine.toBigDecimal(number).add(ArithmeticEngine.toBigDecimal(number2));
        }

        public Number subtract(Number number, Number number2) {
            return ArithmeticEngine.toBigDecimal(number).subtract(ArithmeticEngine.toBigDecimal(number2));
        }

        public Number multiply(Number number, Number number2) {
            BigDecimal multiply = ArithmeticEngine.toBigDecimal(number).multiply(ArithmeticEngine.toBigDecimal(number2));
            return multiply.scale() > this.maxScale ? multiply.setScale(this.maxScale, this.roundingPolicy) : multiply;
        }

        public Number divide(Number number, Number number2) {
            return divide(ArithmeticEngine.toBigDecimal(number), ArithmeticEngine.toBigDecimal(number2));
        }

        public Number modulus(Number number, Number number2) {
            return new Long(number.longValue() % number2.longValue());
        }

        public Number toNumber(String str) {
            return new BigDecimal(str);
        }

        private BigDecimal divide(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
            return bigDecimal.divide(bigDecimal2, Math.max(this.minScale, Math.max(bigDecimal.scale(), bigDecimal2.scale())), this.roundingPolicy);
        }
    }

    public static class ConservativeEngine extends ArithmeticEngine {
        private static final int BIGDECIMAL = 5;
        private static final int BIGINTEGER = 4;
        private static final int DOUBLE = 3;
        private static final int FLOAT = 2;
        private static final int INTEGER = 0;
        private static final int LONG = 1;
        private static final Map classCodes = createClassCodesMap();

        public int compareNumbers(Number number, Number number2) throws TemplateException {
            int commonClassCode = getCommonClassCode(number, number2);
            if (commonClassCode == 0) {
                int intValue = number.intValue();
                int intValue2 = number2.intValue();
                if (intValue < intValue2) {
                    return -1;
                }
                if (intValue == intValue2) {
                    return 0;
                }
                return 1;
            } else if (commonClassCode == 1) {
                int i = (number.longValue() > number2.longValue() ? 1 : (number.longValue() == number2.longValue() ? 0 : -1));
                if (i < 0) {
                    return -1;
                }
                if (i == 0) {
                    return 0;
                }
                return 1;
            } else if (commonClassCode == 2) {
                float floatValue = number.floatValue();
                float floatValue2 = number2.floatValue();
                if (floatValue < floatValue2) {
                    return -1;
                }
                if (floatValue == floatValue2) {
                    return 0;
                }
                return 1;
            } else if (commonClassCode == 3) {
                double doubleValue = number.doubleValue();
                double doubleValue2 = number2.doubleValue();
                if (doubleValue < doubleValue2) {
                    return -1;
                }
                if (doubleValue == doubleValue2) {
                    return 0;
                }
                return 1;
            } else if (commonClassCode == 4) {
                return toBigInteger(number).compareTo(toBigInteger(number2));
            } else {
                if (commonClassCode == 5) {
                    return ArithmeticEngine.toBigDecimal(number).compareTo(ArithmeticEngine.toBigDecimal(number2));
                }
                throw new Error();
            }
        }

        public Number add(Number number, Number number2) throws TemplateException {
            int commonClassCode = getCommonClassCode(number, number2);
            if (commonClassCode == 0) {
                int intValue = number.intValue();
                int intValue2 = number2.intValue();
                int i = intValue + intValue2;
                return ((i ^ intValue) >= 0 || (i ^ intValue2) >= 0) ? new Integer(i) : new Long(((long) intValue) + ((long) intValue2));
            } else if (commonClassCode == 1) {
                long longValue = number.longValue();
                long longValue2 = number2.longValue();
                long j = longValue + longValue2;
                return ((longValue ^ j) >= 0 || (j ^ longValue2) >= 0) ? new Long(j) : toBigInteger(number).add(toBigInteger(number2));
            } else if (commonClassCode == 2) {
                return new Float(number.floatValue() + number2.floatValue());
            } else {
                if (commonClassCode == 3) {
                    return new Double(number.doubleValue() + number2.doubleValue());
                }
                if (commonClassCode == 4) {
                    return toBigInteger(number).add(toBigInteger(number2));
                }
                if (commonClassCode == 5) {
                    return ArithmeticEngine.toBigDecimal(number).add(ArithmeticEngine.toBigDecimal(number2));
                }
                throw new Error();
            }
        }

        public Number subtract(Number number, Number number2) throws TemplateException {
            int commonClassCode = getCommonClassCode(number, number2);
            if (commonClassCode == 0) {
                int intValue = number.intValue();
                int intValue2 = number2.intValue();
                int i = intValue - intValue2;
                return ((i ^ intValue) >= 0 || ((~intValue2) ^ i) >= 0) ? new Integer(i) : new Long(((long) intValue) - ((long) intValue2));
            } else if (commonClassCode == 1) {
                long longValue = number.longValue();
                long longValue2 = number2.longValue();
                long j = longValue - longValue2;
                return ((longValue ^ j) >= 0 || ((~longValue2) ^ j) >= 0) ? new Long(j) : toBigInteger(number).subtract(toBigInteger(number2));
            } else if (commonClassCode == 2) {
                return new Float(number.floatValue() - number2.floatValue());
            } else {
                if (commonClassCode == 3) {
                    return new Double(number.doubleValue() - number2.doubleValue());
                }
                if (commonClassCode == 4) {
                    return toBigInteger(number).subtract(toBigInteger(number2));
                }
                if (commonClassCode == 5) {
                    return ArithmeticEngine.toBigDecimal(number).subtract(ArithmeticEngine.toBigDecimal(number2));
                }
                throw new Error();
            }
        }

        public Number multiply(Number number, Number number2) throws TemplateException {
            int commonClassCode = getCommonClassCode(number, number2);
            if (commonClassCode == 0) {
                int intValue = number.intValue();
                int intValue2 = number2.intValue();
                int i = intValue * intValue2;
                return (intValue == 0 || i / intValue == intValue2) ? new Integer(i) : new Long(((long) intValue) * ((long) intValue2));
            } else if (commonClassCode == 1) {
                long longValue = number.longValue();
                long longValue2 = number2.longValue();
                long j = longValue * longValue2;
                return (longValue == 0 || j / longValue == longValue2) ? new Long(j) : toBigInteger(number).multiply(toBigInteger(number2));
            } else if (commonClassCode == 2) {
                return new Float(number.floatValue() * number2.floatValue());
            } else {
                if (commonClassCode == 3) {
                    return new Double(number.doubleValue() * number2.doubleValue());
                }
                if (commonClassCode == 4) {
                    return toBigInteger(number).multiply(toBigInteger(number2));
                }
                if (commonClassCode == 5) {
                    BigDecimal multiply = ArithmeticEngine.toBigDecimal(number).multiply(ArithmeticEngine.toBigDecimal(number2));
                    return multiply.scale() > this.maxScale ? multiply.setScale(this.maxScale, this.roundingPolicy) : multiply;
                }
                throw new Error();
            }
        }

        public Number divide(Number number, Number number2) throws TemplateException {
            int commonClassCode = getCommonClassCode(number, number2);
            if (commonClassCode == 0) {
                int intValue = number.intValue();
                int intValue2 = number2.intValue();
                if (intValue % intValue2 == 0) {
                    return new Integer(intValue / intValue2);
                }
                return new Double(((double) intValue) / ((double) intValue2));
            } else if (commonClassCode == 1) {
                long longValue = number.longValue();
                long longValue2 = number2.longValue();
                if (longValue % longValue2 == 0) {
                    return new Long(longValue / longValue2);
                }
                return new Double(((double) longValue) / ((double) longValue2));
            } else if (commonClassCode == 2) {
                return new Float(number.floatValue() / number2.floatValue());
            } else {
                if (commonClassCode == 3) {
                    return new Double(number.doubleValue() / number2.doubleValue());
                }
                if (commonClassCode == 4) {
                    BigInteger bigInteger = toBigInteger(number);
                    BigInteger bigInteger2 = toBigInteger(number2);
                    BigInteger[] divideAndRemainder = bigInteger.divideAndRemainder(bigInteger2);
                    if (divideAndRemainder[1].equals(BigInteger.ZERO)) {
                        return divideAndRemainder[0];
                    }
                    return new BigDecimal(bigInteger).divide(new BigDecimal(bigInteger2), this.minScale, this.roundingPolicy);
                } else if (commonClassCode == 5) {
                    BigDecimal access$000 = ArithmeticEngine.toBigDecimal(number);
                    BigDecimal access$0002 = ArithmeticEngine.toBigDecimal(number2);
                    return access$000.divide(access$0002, Math.max(this.minScale, Math.max(access$000.scale(), access$0002.scale())), this.roundingPolicy);
                } else {
                    throw new Error();
                }
            }
        }

        public Number modulus(Number number, Number number2) throws TemplateException {
            int commonClassCode = getCommonClassCode(number, number2);
            if (commonClassCode == 0) {
                return new Integer(number.intValue() % number2.intValue());
            }
            if (commonClassCode == 1) {
                return new Long(number.longValue() % number2.longValue());
            }
            if (commonClassCode == 2) {
                return new Float(number.floatValue() % number2.floatValue());
            }
            if (commonClassCode == 3) {
                return new Double(number.doubleValue() % number2.doubleValue());
            }
            if (commonClassCode == 4) {
                return toBigInteger(number).mod(toBigInteger(number2));
            }
            if (commonClassCode != 5) {
                throw new Error();
            }
            throw new _MiscTemplateException("Can't calculate remainder on BigDecimals");
        }

        public Number toNumber(String str) {
            return OptimizerUtil.optimizeNumberRepresentation(new BigDecimal(str));
        }

        private static Map createClassCodesMap() {
            Class cls;
            Class cls2;
            Class cls3;
            Class cls4;
            Class cls5;
            Class cls6;
            Class cls7;
            Class cls8;
            HashMap hashMap = new HashMap(17);
            Integer num = new Integer(0);
            if (ArithmeticEngine.class$java$lang$Byte == null) {
                cls = ArithmeticEngine.class$("java.lang.Byte");
                ArithmeticEngine.class$java$lang$Byte = cls;
            } else {
                cls = ArithmeticEngine.class$java$lang$Byte;
            }
            hashMap.put(cls, num);
            if (ArithmeticEngine.class$java$lang$Short == null) {
                cls2 = ArithmeticEngine.class$("java.lang.Short");
                ArithmeticEngine.class$java$lang$Short = cls2;
            } else {
                cls2 = ArithmeticEngine.class$java$lang$Short;
            }
            hashMap.put(cls2, num);
            if (ArithmeticEngine.class$java$lang$Integer == null) {
                cls3 = ArithmeticEngine.class$("java.lang.Integer");
                ArithmeticEngine.class$java$lang$Integer = cls3;
            } else {
                cls3 = ArithmeticEngine.class$java$lang$Integer;
            }
            hashMap.put(cls3, num);
            if (ArithmeticEngine.class$java$lang$Long == null) {
                cls4 = ArithmeticEngine.class$("java.lang.Long");
                ArithmeticEngine.class$java$lang$Long = cls4;
            } else {
                cls4 = ArithmeticEngine.class$java$lang$Long;
            }
            hashMap.put(cls4, new Integer(1));
            if (ArithmeticEngine.class$java$lang$Float == null) {
                cls5 = ArithmeticEngine.class$("java.lang.Float");
                ArithmeticEngine.class$java$lang$Float = cls5;
            } else {
                cls5 = ArithmeticEngine.class$java$lang$Float;
            }
            hashMap.put(cls5, new Integer(2));
            if (ArithmeticEngine.class$java$lang$Double == null) {
                cls6 = ArithmeticEngine.class$("java.lang.Double");
                ArithmeticEngine.class$java$lang$Double = cls6;
            } else {
                cls6 = ArithmeticEngine.class$java$lang$Double;
            }
            hashMap.put(cls6, new Integer(3));
            if (ArithmeticEngine.class$java$math$BigInteger == null) {
                cls7 = ArithmeticEngine.class$("java.math.BigInteger");
                ArithmeticEngine.class$java$math$BigInteger = cls7;
            } else {
                cls7 = ArithmeticEngine.class$java$math$BigInteger;
            }
            hashMap.put(cls7, new Integer(4));
            if (ArithmeticEngine.class$java$math$BigDecimal == null) {
                cls8 = ArithmeticEngine.class$("java.math.BigDecimal");
                ArithmeticEngine.class$java$math$BigDecimal = cls8;
            } else {
                cls8 = ArithmeticEngine.class$java$math$BigDecimal;
            }
            hashMap.put(cls8, new Integer(5));
            return hashMap;
        }

        private static int getClassCode(Number number) throws TemplateException {
            try {
                return ((Integer) classCodes.get(number.getClass())).intValue();
            } catch (NullPointerException unused) {
                if (number == null) {
                    throw new _MiscTemplateException("The Number object was null.");
                }
                throw new _MiscTemplateException(new Object[]{"Unknown number type ", number.getClass().getName()});
            }
        }

        private static int getCommonClassCode(Number number, Number number2) throws TemplateException {
            int classCode = getClassCode(number);
            int classCode2 = getClassCode(number2);
            int i = classCode > classCode2 ? classCode : classCode2;
            if (i == 2) {
                if (classCode >= classCode2) {
                    classCode = classCode2;
                }
                if (classCode == 1) {
                    return 3;
                }
            } else if (i == 4) {
                if (classCode >= classCode2) {
                    classCode = classCode2;
                }
                if (classCode == 3 || classCode == 2) {
                    return 5;
                }
            }
            return i;
        }

        private static BigInteger toBigInteger(Number number) {
            return number instanceof BigInteger ? (BigInteger) number : new BigInteger(number.toString());
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public static BigDecimal toBigDecimal(Number number) {
        try {
            return number instanceof BigDecimal ? (BigDecimal) number : new BigDecimal(number.toString());
        } catch (NumberFormatException unused) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Can't parse this as BigDecimal number: ");
            stringBuffer.append(StringUtil.jQuote((Object) number));
            throw new NumberFormatException(stringBuffer.toString());
        }
    }
}
