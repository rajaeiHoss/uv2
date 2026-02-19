package freemarker.template.utility;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    public static final int ACCURACY_HOURS = 4;
    public static final int ACCURACY_MILLISECONDS = 7;
    public static final int ACCURACY_MINUTES = 5;
    public static final int ACCURACY_SECONDS = 6;
    private static final TimeZoneOffsetCalculator TIME_ZONE_OFFSET_CALCULATOR = getTimeZoneOffsetCalculator();
    public static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    public interface DateToISO8601CalendarFactory {
        GregorianCalendar get(TimeZone timeZone, Date date);
    }

    interface TimeZoneOffsetCalculator {
        int getOffset(TimeZone timeZone, Date date);
    }

    private static TimeZoneOffsetCalculator getTimeZoneOffsetCalculator() {
        try {
            return (TimeZoneOffsetCalculator) Class.forName("freemarker.template.utility.J2SE14TimeZoneOffsetCalculator").newInstance();
        } catch (Throwable th) {
            return new TimeZoneOffsetCalculator() {
                public int getOffset(TimeZone timeZone, Date date) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Failed to create TimeZoneOffsetCalculator. Note that this feature requires at least Java 1.4.\nCause exception: ");
                    stringBuffer.append(th);
                    throw new RuntimeException(stringBuffer.toString());
                }
            };
        }
    }

    private DateUtil() {
    }

    public static TimeZone getTimeZone(String str) throws UnrecognizedTimeZoneException {
        if (!isGMTish(str)) {
            TimeZone timeZone = TimeZone.getTimeZone(str);
            if (!isGMTish(timeZone.getID())) {
                return timeZone;
            }
            throw new UnrecognizedTimeZoneException(str);
        } else if (str.equalsIgnoreCase("UTC")) {
            return UTC;
        } else {
            return TimeZone.getTimeZone(str);
        }
    }

    private static boolean isGMTish(String str) {
        if (str.length() < 3) {
            return false;
        }
        char charAt = str.charAt(0);
        char charAt2 = str.charAt(1);
        char charAt3 = str.charAt(2);
        if (((charAt != 'G' && charAt != 'g') || ((charAt2 != 'M' && charAt2 != 'm') || (charAt3 != 'T' && charAt3 != 't'))) && (((charAt != 'U' && charAt != 'u') || ((charAt2 != 'T' && charAt2 != 't') || (charAt3 != 'C' && charAt3 != 'c'))) && ((charAt != 'U' && charAt != 'u') || ((charAt2 != 'T' && charAt2 != 't') || charAt3 != '1')))) {
            return false;
        }
        if (str.length() == 3) {
            return true;
        }
        String substring = str.substring(3);
        if (substring.startsWith("+")) {
            if (substring.equals("+0") || substring.equals("+00") || substring.equals("+00:00")) {
                return true;
            }
            return false;
        } else if (substring.equals("-0") || substring.equals("-00") || substring.equals("-00:00")) {
            return true;
        } else {
            return false;
        }
    }

    public static String dateToISO8601String(Date date, boolean z, boolean z2, boolean z3, int i, TimeZone timeZone, DateToISO8601CalendarFactory dateToISO8601CalendarFactory) {
        int i2;
        int i3;
        int i4;
        Date date2 = date;
        int i5 = i;
        if (z2 || !z3) {
            TimeZone timeZone2 = timeZone == null ? UTC : timeZone;
            GregorianCalendar gregorianCalendar = dateToISO8601CalendarFactory.get(timeZone2, date2);
            int i6 = !z2 ? 10 : !z ? 18 : 29;
            char[] cArr = new char[i6];
            char c = '-';
            boolean z4 = true;
            if (z) {
                int i7 = gregorianCalendar.get(1);
                if (i7 > 0 && gregorianCalendar.get(0) == 0) {
                    i7 = (-i7) + 1;
                }
                int i8 = 4;
                if (i7 < 0 || i7 >= 9999) {
                    String valueOf = String.valueOf(i7);
                    char[] cArr2 = new char[((i6 - 4) + valueOf.length())];
                    int i9 = 0;
                    i8 = 0;
                    while (i9 < valueOf.length()) {
                        cArr2[i8] = valueOf.charAt(i9);
                        i9++;
                        i8++;
                    }
                    cArr = cArr2;
                } else {
                    cArr[0] = (char) ((i7 / 1000) + 48);
                    cArr[1] = (char) (((i7 % 1000) / 100) + 48);
                    cArr[2] = (char) (((i7 % 100) / 10) + 48);
                    cArr[3] = (char) ((i7 % 10) + 48);
                }
                cArr[i8] = '-';
                int append00 = append00(cArr, i8 + 1, gregorianCalendar.get(2) + 1);
                cArr[append00] = '-';
                i2 = append00(cArr, append00 + 1, gregorianCalendar.get(5));
                if (z2) {
                    cArr[i2] = 'T';
                    i2++;
                }
            } else {
                i2 = 0;
            }
            if (z2) {
                i2 = append00(cArr, i2, gregorianCalendar.get(11));
                if (i5 >= 5) {
                    cArr[i2] = ':';
                    i2 = append00(cArr, i2 + 1, gregorianCalendar.get(12));
                    if (i5 >= 6) {
                        cArr[i2] = ':';
                        i2 = append00(cArr, i2 + 1, gregorianCalendar.get(13));
                        if (i5 >= 7 && (i4 = gregorianCalendar.get(14)) != 0) {
                            if (i4 <= 999) {
                                int i10 = i2 + 1;
                                cArr[i2] = '.';
                                while (true) {
                                    i2 = i10 + 1;
                                    cArr[i10] = (char) ((i4 / 100) + 48);
                                    i4 = (i4 % 100) * 10;
                                    if (i4 == 0) {
                                        break;
                                    }
                                    i10 = i2;
                                }
                            } else {
                                throw new RuntimeException("Calendar.MILLISECOND > 999");
                            }
                        }
                    }
                }
            }
            if (z3) {
                if (timeZone2 == UTC) {
                    i3 = i2 + 1;
                    cArr[i2] = 'Z';
                } else {
                    int offset = TIME_ZONE_OFFSET_CALCULATOR.getOffset(timeZone2, date2);
                    if (offset < 0) {
                        offset = -offset;
                        z4 = false;
                    }
                    int i11 = offset / 1000;
                    int i12 = i11 % 60;
                    int i13 = i11 / 60;
                    int i14 = i13 % 60;
                    int i15 = i13 / 60;
                    if (i12 == 0 && i14 == 0 && i15 == 0) {
                        i3 = i2 + 1;
                        cArr[i2] = 'Z';
                    } else {
                        int i16 = i2 + 1;
                        if (z4) {
                            c = '+';
                        }
                        cArr[i2] = c;
                        int append002 = append00(cArr, i16, i15);
                        cArr[append002] = ':';
                        i2 = append00(cArr, append002 + 1, i14);
                        if (i12 != 0) {
                            cArr[i2] = ':';
                            i2 = append00(cArr, i2 + 1, i12);
                        }
                    }
                }
                i2 = i3;
            }
            return new String(cArr, 0, i2);
        }
        throw new IllegalArgumentException("ISO 8601:2004 doesn't specify any formats where the offset is shown but the time isn't.");
    }

    private static int append00(char[] cArr, int i, int i2) {
        int i3 = i + 1;
        cArr[i] = (char) ((i2 / 10) + 48);
        int i4 = i3 + 1;
        cArr[i3] = (char) ((i2 % 10) + 48);
        return i4;
    }

    public static final class TrivialDateToISO8601CalendarFactory implements DateToISO8601CalendarFactory {
        private GregorianCalendar calendar;

        public GregorianCalendar get(TimeZone timeZone, Date date) {
            GregorianCalendar gregorianCalendar = this.calendar;
            if (gregorianCalendar == null) {
                this.calendar = new GregorianCalendar(timeZone, Locale.US);
            } else {
                gregorianCalendar.setTimeZone(timeZone);
            }
            this.calendar.setTime(date);
            return this.calendar;
        }
    }
}
