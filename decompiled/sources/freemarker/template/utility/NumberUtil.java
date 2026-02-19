package freemarker.template.utility;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberUtil {
    private NumberUtil() {
    }

    public static boolean isInfinite(Number number) {
        if (number instanceof Double) {
            return ((Double) number).isInfinite();
        }
        if (number instanceof Float) {
            return ((Float) number).isInfinite();
        }
        if (isNonFPNumberOfSupportedClass(number)) {
            return false;
        }
        throw new UnsupportedNumberClassException(number.getClass());
    }

    public static boolean isNaN(Number number) {
        if (number instanceof Double) {
            return ((Double) number).isNaN();
        }
        if (number instanceof Float) {
            return ((Float) number).isNaN();
        }
        if (isNonFPNumberOfSupportedClass(number)) {
            return false;
        }
        throw new UnsupportedNumberClassException(number.getClass());
    }

    public static int getSignum(Number number) throws ArithmeticException {
        if (number instanceof Integer) {
            int intValue = ((Integer) number).intValue();
            if (intValue > 0) {
                return 1;
            }
            if (intValue == 0) {
                return 0;
            }
            return -1;
        } else if (number instanceof BigDecimal) {
            return ((BigDecimal) number).signum();
        } else {
            if (number instanceof Double) {
                double doubleValue = ((Double) number).doubleValue();
                int i = (doubleValue > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : (doubleValue == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0 : -1));
                if (i > 0) {
                    return 1;
                }
                if (i == 0) {
                    return 0;
                }
                if (doubleValue < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return -1;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The signum of ");
                stringBuffer.append(doubleValue);
                stringBuffer.append(" is not defined.");
                throw new ArithmeticException(stringBuffer.toString());
            } else if (number instanceof Float) {
                float floatValue = ((Float) number).floatValue();
                int i2 = (floatValue > 0.0f ? 1 : (floatValue == 0.0f ? 0 : -1));
                if (i2 > 0) {
                    return 1;
                }
                if (i2 == 0) {
                    return 0;
                }
                if (floatValue < 0.0f) {
                    return -1;
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("The signum of ");
                stringBuffer2.append(floatValue);
                stringBuffer2.append(" is not defined.");
                throw new ArithmeticException(stringBuffer2.toString());
            } else if (number instanceof Long) {
                int i3 = (((Long) number).longValue() > 0 ? 1 : (((Long) number).longValue() == 0 ? 0 : -1));
                if (i3 > 0) {
                    return 1;
                }
                if (i3 == 0) {
                    return 0;
                }
                return -1;
            } else if (number instanceof Short) {
                short shortValue = ((Short) number).shortValue();
                if (shortValue > 0) {
                    return 1;
                }
                if (shortValue == 0) {
                    return 0;
                }
                return -1;
            } else if (number instanceof Byte) {
                byte byteValue = ((Byte) number).byteValue();
                if (byteValue > 0) {
                    return 1;
                }
                if (byteValue == 0) {
                    return 0;
                }
                return -1;
            } else if (number instanceof BigInteger) {
                return ((BigInteger) number).signum();
            } else {
                throw new UnsupportedNumberClassException(number.getClass());
            }
        }
    }

    private static boolean isNonFPNumberOfSupportedClass(Number number) {
        return (number instanceof Integer) || (number instanceof BigDecimal) || (number instanceof Long) || (number instanceof Short) || (number instanceof Byte) || (number instanceof BigInteger);
    }
}
