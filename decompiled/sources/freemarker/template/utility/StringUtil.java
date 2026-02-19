package freemarker.template.utility;

import freemarker.core.Environment;
import freemarker.core.ParseException;
import freemarker.template.Version;
import java.util.Locale;
import java.util.StringTokenizer;
import kotlin.text.Typography;

public class StringUtil {
    private static final char[] ESCAPES = createEscapes();
    private static final int ESC_BACKSLASH = 3;
    private static final int ESC_HEXA = 1;
    private static final int NO_ESC = 0;

    private static char toHexDigit(int i) {
        return (char) (i < 10 ? i + 48 : (i - 10) + 65);
    }

    public static String HTMLEnc(String str) {
        return XMLEncNA(str);
    }

    public static String XMLEnc(String str) {
        return XMLOrXHTMLEnc(str, "&apos;");
    }

    public static String XHTMLEnc(String str) {
        return XMLOrXHTMLEnc(str, "&#39;");
    }

    private static String XMLOrXHTMLEnc(String str, String str2) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '<' || charAt == '>' || charAt == '&' || charAt == '\"' || charAt == '\'') {
                StringBuffer stringBuffer = new StringBuffer(str.substring(0, i));
                if (charAt == '\"') {
                    stringBuffer.append("&quot;");
                } else if (charAt == '<') {
                    stringBuffer.append("&lt;");
                } else if (charAt == '>') {
                    stringBuffer.append("&gt;");
                } else if (charAt == '&') {
                    stringBuffer.append("&amp;");
                } else if (charAt == '\'') {
                    stringBuffer.append(str2);
                }
                int i2 = i + 1;
                int i3 = i2;
                while (i2 < length) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 == '<' || charAt2 == '>' || charAt2 == '&' || charAt2 == '\"' || charAt2 == '\'') {
                        stringBuffer.append(str.substring(i3, i2));
                        if (charAt2 == '\"') {
                            stringBuffer.append("&quot;");
                        } else if (charAt2 == '<') {
                            stringBuffer.append("&lt;");
                        } else if (charAt2 == '>') {
                            stringBuffer.append("&gt;");
                        } else if (charAt2 == '&') {
                            stringBuffer.append("&amp;");
                        } else if (charAt2 == '\'') {
                            stringBuffer.append(str2);
                        }
                        i3 = i2 + 1;
                    }
                    i2++;
                }
                if (i3 < length) {
                    stringBuffer.append(str.substring(i3));
                }
                return stringBuffer.toString();
            }
        }
        return str;
    }

    public static String XMLEncNA(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '<' || charAt == '>' || charAt == '&' || charAt == '\"') {
                StringBuffer stringBuffer = new StringBuffer(str.substring(0, i));
                if (charAt == '\"') {
                    stringBuffer.append("&quot;");
                } else if (charAt == '&') {
                    stringBuffer.append("&amp;");
                } else if (charAt == '<') {
                    stringBuffer.append("&lt;");
                } else if (charAt == '>') {
                    stringBuffer.append("&gt;");
                }
                int i2 = i + 1;
                int i3 = i2;
                while (i2 < length) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 == '<' || charAt2 == '>' || charAt2 == '&' || charAt2 == '\"') {
                        stringBuffer.append(str.substring(i3, i2));
                        if (charAt2 == '\"') {
                            stringBuffer.append("&quot;");
                        } else if (charAt2 == '&') {
                            stringBuffer.append("&amp;");
                        } else if (charAt2 == '<') {
                            stringBuffer.append("&lt;");
                        } else if (charAt2 == '>') {
                            stringBuffer.append("&gt;");
                        }
                        i3 = i2 + 1;
                    }
                    i2++;
                }
                if (i3 < length) {
                    stringBuffer.append(str.substring(i3));
                }
                return stringBuffer.toString();
            }
        }
        return str;
    }

    public static String XMLEncQAttr(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '<' || charAt == '&' || charAt == '\"') {
                StringBuffer stringBuffer = new StringBuffer(str.substring(0, i));
                if (charAt == '\"') {
                    stringBuffer.append("&quot;");
                } else if (charAt == '&') {
                    stringBuffer.append("&amp;");
                } else if (charAt == '<') {
                    stringBuffer.append("&lt;");
                }
                int i2 = i + 1;
                int i3 = i2;
                while (i2 < length) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 == '<' || charAt2 == '&' || charAt2 == '\"') {
                        stringBuffer.append(str.substring(i3, i2));
                        if (charAt2 == '\"') {
                            stringBuffer.append("&quot;");
                        } else if (charAt2 == '&') {
                            stringBuffer.append("&amp;");
                        } else if (charAt2 == '<') {
                            stringBuffer.append("&lt;");
                        }
                        i3 = i2 + 1;
                    }
                    i2++;
                }
                if (i3 < length) {
                    stringBuffer.append(str.substring(i3));
                }
                return stringBuffer.toString();
            }
        }
        return str;
    }

    public static String XMLEncNQG(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '<' || ((charAt == '>' && i > 1 && str.charAt(i - 1) == ']' && str.charAt(i - 2) == ']') || charAt == '&')) {
                StringBuffer stringBuffer = new StringBuffer(str.substring(0, i));
                if (charAt == '&') {
                    stringBuffer.append("&amp;");
                } else if (charAt == '<') {
                    stringBuffer.append("&lt;");
                } else if (charAt == '>') {
                    stringBuffer.append("&gt;");
                } else {
                    throw new RuntimeException("Bug: unexpected char");
                }
                int i2 = i + 1;
                int i3 = i2;
                while (i2 < length) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 == '<' || ((charAt2 == '>' && i2 > 1 && str.charAt(i2 - 1) == ']' && str.charAt(i2 - 2) == ']') || charAt2 == '&')) {
                        stringBuffer.append(str.substring(i3, i2));
                        if (charAt2 == '&') {
                            stringBuffer.append("&amp;");
                        } else if (charAt2 == '<') {
                            stringBuffer.append("&lt;");
                        } else if (charAt2 == '>') {
                            stringBuffer.append("&gt;");
                        } else {
                            throw new RuntimeException("Bug: unexpected char");
                        }
                        i3 = i2 + 1;
                    }
                    i2++;
                }
                if (i3 < length) {
                    stringBuffer.append(str.substring(i3));
                }
                return stringBuffer.toString();
            }
        }
        return str;
    }

    public static String RTFEnc(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\\' || charAt == '{' || charAt == '}') {
                StringBuffer stringBuffer = new StringBuffer(str.substring(0, i));
                if (charAt == '\\') {
                    stringBuffer.append("\\\\");
                } else if (charAt == '{') {
                    stringBuffer.append("\\{");
                } else if (charAt == '}') {
                    stringBuffer.append("\\}");
                }
                int i2 = i + 1;
                int i3 = i2;
                while (i2 < length) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 == '\\' || charAt2 == '{' || charAt2 == '}') {
                        stringBuffer.append(str.substring(i3, i2));
                        if (charAt2 == '\\') {
                            stringBuffer.append("\\\\");
                        } else if (charAt2 == '{') {
                            stringBuffer.append("\\{");
                        } else if (charAt2 == '}') {
                            stringBuffer.append("\\}");
                        }
                        i3 = i2 + 1;
                    }
                    i2++;
                }
                if (i3 < length) {
                    stringBuffer.append(str.substring(i3));
                }
                return stringBuffer.toString();
            }
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0087, code lost:
        if (r7 <= '*') goto L_0x0069;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0049 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String URLEnc(java.lang.String r16, java.lang.String r17) throws java.io.UnsupportedEncodingException {
        /*
            r0 = r16
            r1 = r17
            int r2 = r16.length()
            r4 = 0
        L_0x0009:
            r7 = 126(0x7e, float:1.77E-43)
            r8 = 33
            r9 = 46
            r10 = 45
            r11 = 57
            r12 = 95
            r13 = 90
            r14 = 122(0x7a, float:1.71E-43)
            r15 = 97
            r3 = 48
            r5 = 65
            if (r4 >= r2) goto L_0x0047
            char r6 = r0.charAt(r4)
            if (r6 < r15) goto L_0x0029
            if (r6 <= r14) goto L_0x0044
        L_0x0029:
            if (r6 < r5) goto L_0x002d
            if (r6 <= r13) goto L_0x0044
        L_0x002d:
            if (r6 < r3) goto L_0x0031
            if (r6 <= r11) goto L_0x0044
        L_0x0031:
            if (r6 == r12) goto L_0x0044
            if (r6 == r10) goto L_0x0044
            if (r6 == r9) goto L_0x0044
            if (r6 == r8) goto L_0x0044
            if (r6 == r7) goto L_0x0044
            r7 = 39
            if (r6 < r7) goto L_0x0047
            r7 = 42
            if (r6 <= r7) goto L_0x0044
            goto L_0x0047
        L_0x0044:
            int r4 = r4 + 1
            goto L_0x0009
        L_0x0047:
            if (r4 != r2) goto L_0x004a
            return r0
        L_0x004a:
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            int r7 = r2 / 3
            int r7 = r7 + r2
            int r7 = r7 + 2
            r6.<init>(r7)
            r7 = 0
            java.lang.String r8 = r0.substring(r7, r4)
            r6.append(r8)
            int r8 = r4 + 1
        L_0x005e:
            if (r8 >= r2) goto L_0x00dd
            char r7 = r0.charAt(r8)
            if (r7 < r15) goto L_0x006b
            if (r7 <= r14) goto L_0x0069
            goto L_0x006b
        L_0x0069:
            r3 = -1
            goto L_0x0091
        L_0x006b:
            if (r7 < r5) goto L_0x006f
            if (r7 <= r13) goto L_0x0069
        L_0x006f:
            if (r7 < r3) goto L_0x0073
            if (r7 <= r11) goto L_0x0069
        L_0x0073:
            if (r7 == r12) goto L_0x0069
            if (r7 == r10) goto L_0x0069
            if (r7 == r9) goto L_0x0069
            r3 = 33
            if (r7 == r3) goto L_0x0069
            r3 = 126(0x7e, float:1.77E-43)
            if (r7 == r3) goto L_0x0069
            r3 = 39
            if (r7 < r3) goto L_0x008a
            r3 = 42
            if (r7 > r3) goto L_0x008c
            goto L_0x0069
        L_0x008a:
            r3 = 42
        L_0x008c:
            r7 = -1
            if (r4 != r7) goto L_0x00d1
            r4 = r8
            goto L_0x00d1
        L_0x0091:
            if (r4 == r3) goto L_0x00ce
            java.lang.String r3 = r0.substring(r4, r8)
            byte[] r3 = r3.getBytes(r1)
            r4 = 0
        L_0x009c:
            int r9 = r3.length
            if (r4 >= r9) goto L_0x00cd
            r9 = 37
            r6.append(r9)
            byte r9 = r3[r4]
            r10 = r9 & 15
            int r9 = r9 >> 4
            r9 = r9 & 15
            r11 = 10
            if (r9 >= r11) goto L_0x00b3
            int r9 = r9 + 48
            goto L_0x00b6
        L_0x00b3:
            int r9 = r9 + -10
            int r9 = r9 + r5
        L_0x00b6:
            char r9 = (char) r9
            r6.append(r9)
            if (r10 >= r11) goto L_0x00bf
            int r10 = r10 + 48
            goto L_0x00c2
        L_0x00bf:
            int r10 = r10 + -10
            int r10 = r10 + r5
        L_0x00c2:
            char r9 = (char) r10
            r6.append(r9)
            int r4 = r4 + 1
            r10 = 45
            r11 = 57
            goto L_0x009c
        L_0x00cd:
            r4 = -1
        L_0x00ce:
            r6.append(r7)
        L_0x00d1:
            int r8 = r8 + 1
            r3 = 48
            r7 = 0
            r9 = 46
            r10 = 45
            r11 = 57
            goto L_0x005e
        L_0x00dd:
            r3 = -1
            if (r4 == r3) goto L_0x0116
            java.lang.String r0 = r0.substring(r4, r8)
            byte[] r0 = r0.getBytes(r1)
            r3 = 0
        L_0x00e9:
            int r1 = r0.length
            if (r3 >= r1) goto L_0x0116
            r1 = 37
            r6.append(r1)
            byte r2 = r0[r3]
            r4 = r2 & 15
            int r2 = r2 >> 4
            r2 = r2 & 15
            r7 = 10
            if (r2 >= r7) goto L_0x0100
            int r2 = r2 + 48
            goto L_0x0103
        L_0x0100:
            int r2 = r2 + -10
            int r2 = r2 + r5
        L_0x0103:
            char r2 = (char) r2
            r6.append(r2)
            if (r4 >= r7) goto L_0x010c
            int r4 = r4 + 48
            goto L_0x010f
        L_0x010c:
            int r4 = r4 + -10
            int r4 = r4 + r5
        L_0x010f:
            char r2 = (char) r4
            r6.append(r2)
            int r3 = r3 + 1
            goto L_0x00e9
        L_0x0116:
            java.lang.String r0 = r6.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.template.utility.StringUtil.URLEnc(java.lang.String, java.lang.String):java.lang.String");
    }

    private static char[] createEscapes() {
        char[] cArr = new char[93];
        for (int i = 0; i < 32; i++) {
            cArr[i] = 1;
        }
        cArr[92] = '\\';
        cArr[39] = '\'';
        cArr[34] = Typography.quote;
        cArr[60] = 'l';
        cArr[62] = 'g';
        cArr[38] = 'a';
        cArr[8] = 'b';
        cArr[9] = 't';
        cArr[10] = 'n';
        cArr[12] = 'f';
        cArr[13] = 'r';
        cArr[36] = Typography.dollar;
        return cArr;
    }

    public static String FTLStringLiteralEnc(String str) {
        int length = str.length();
        int length2 = ESCAPES.length;
        StringBuffer stringBuffer = null;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt < length2) {
                char c = ESCAPES[charAt];
                if (c != 0) {
                    if (c != 1) {
                        if (stringBuffer == null) {
                            stringBuffer = new StringBuffer(str.length() + 2);
                            stringBuffer.append(str.substring(0, i));
                        }
                        stringBuffer.append('\\');
                        stringBuffer.append(c);
                    } else {
                        if (stringBuffer == null) {
                            stringBuffer = new StringBuffer(str.length() + 3);
                            stringBuffer.append(str.substring(0, i));
                        }
                        stringBuffer.append("\\x00");
                        int i2 = (charAt >> 4) & 15;
                        char c2 = (char) (charAt & 15);
                        stringBuffer.append((char) (i2 < 10 ? i2 + 48 : (i2 - 10) + 65));
                        stringBuffer.append((char) (c2 < 10 ? c2 + '0' : (c2 - 10) + 65));
                    }
                } else if (stringBuffer != null) {
                    stringBuffer.append(charAt);
                }
            } else if (stringBuffer != null) {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer == null ? str : stringBuffer.toString();
    }

    public static String FTLStringLiteralDec(String str) throws ParseException {
        int i;
        int i2;
        int i3;
        int indexOf = str.indexOf(92);
        if (indexOf == -1) {
            return str;
        }
        int length = str.length() - 1;
        StringBuffer stringBuffer = new StringBuffer(length);
        int i4 = 0;
        do {
            stringBuffer.append(str.substring(i4, indexOf));
            if (indexOf < length) {
                char charAt = str.charAt(indexOf + 1);
                if (charAt == '\"') {
                    stringBuffer.append(Typography.quote);
                } else if (charAt == '\'') {
                    stringBuffer.append('\'');
                } else if (charAt == '\\') {
                    stringBuffer.append('\\');
                } else if (charAt == 'l') {
                    stringBuffer.append(Typography.less);
                } else if (charAt == 'n') {
                    stringBuffer.append(10);
                } else if (charAt == 'r') {
                    stringBuffer.append(13);
                } else if (charAt == 't') {
                    stringBuffer.append(9);
                } else if (charAt == 'x') {
                    int i5 = indexOf + 2;
                    int i6 = i5 + 3;
                    if (length <= i6) {
                        i6 = length;
                    }
                    int i7 = i5;
                    int i8 = 0;
                    while (i7 <= i6) {
                        char charAt2 = str.charAt(i7);
                        if (charAt2 < '0' || charAt2 > '9') {
                            if (charAt2 < 'a' || charAt2 > 'f') {
                                if (charAt2 < 'A' || charAt2 > 'F') {
                                    break;
                                }
                                i = i8 << 4;
                                i2 = charAt2 - 'A';
                            } else {
                                i = i8 << 4;
                                i2 = charAt2 - 'a';
                            }
                            i3 = i2 + 10;
                        } else {
                            i = i8 << 4;
                            i3 = charAt2 - '0';
                        }
                        i8 = i + i3;
                        i7++;
                    }
                    if (i5 < i7) {
                        stringBuffer.append((char) i8);
                        i4 = i7;
                        indexOf = str.indexOf(92, i4);
                    } else {
                        throw new ParseException("Invalid \\x escape in a string literal", 0, 0);
                    }
                } else if (charAt == '{') {
                    stringBuffer.append('{');
                } else if (charAt == 'a') {
                    stringBuffer.append(Typography.amp);
                } else if (charAt == 'b') {
                    stringBuffer.append(8);
                } else if (charAt == 'f') {
                    stringBuffer.append(12);
                } else if (charAt == 'g') {
                    stringBuffer.append(Typography.greater);
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Invalid escape sequence (\\");
                    stringBuffer2.append(charAt);
                    stringBuffer2.append(") in a string literal");
                    throw new ParseException(stringBuffer2.toString(), 0, 0);
                }
                i4 = indexOf + 2;
                indexOf = str.indexOf(92, i4);
            } else {
                throw new ParseException("The last character of string literal is backslash", 0, 0);
            }
        } while (indexOf != -1);
        stringBuffer.append(str.substring(i4));
        return stringBuffer.toString();
    }

    public static Locale deduceLocale(String str) {
        if (str == null) {
            return null;
        }
        Locale.getDefault();
        if (str.length() > 0 && str.charAt(0) == '\"') {
            str = str.substring(1, str.length() - 1);
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",_ ");
        String str2 = "";
        String nextToken = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : str2;
        if (stringTokenizer.hasMoreTokens()) {
            str2 = stringTokenizer.nextToken();
        }
        if (!stringTokenizer.hasMoreTokens()) {
            return new Locale(nextToken, str2);
        }
        return new Locale(nextToken, str2, stringTokenizer.nextToken());
    }

    public static String capitalize(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, " \t\r\n", true);
        StringBuffer stringBuffer = new StringBuffer(str.length());
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            stringBuffer.append(nextToken.substring(0, 1).toUpperCase());
            stringBuffer.append(nextToken.substring(1).toLowerCase());
        }
        return stringBuffer.toString();
    }

    public static boolean getYesNo(String str) {
        if (str.startsWith("\"")) {
            str = str.substring(1, str.length() - 1);
        }
        if (str.equalsIgnoreCase("n") || str.equalsIgnoreCase("no") || str.equalsIgnoreCase("f") || str.equalsIgnoreCase("false")) {
            return false;
        }
        if (str.equalsIgnoreCase("y") || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("t") || str.equalsIgnoreCase("true")) {
            return true;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Illegal boolean value: ");
        stringBuffer.append(str);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static String[] split(String str, char c) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        int i3 = 1;
        while (true) {
            int indexOf = str.indexOf(c, i2);
            if (indexOf == -1) {
                break;
            }
            i3++;
            i2 = indexOf + 1;
        }
        String[] strArr = new String[i3];
        int i4 = 0;
        while (i <= length) {
            int indexOf2 = str.indexOf(c, i);
            if (indexOf2 == -1) {
                indexOf2 = length;
            }
            strArr[i4] = str.substring(i, indexOf2);
            i = indexOf2 + 1;
            i4++;
        }
        return strArr;
    }

    public static String[] split(String str, String str2, boolean z) {
        String lowerCase = z ? str2.toLowerCase() : str2;
        String lowerCase2 = z ? str.toLowerCase() : str;
        int length = str.length();
        int length2 = str2.length();
        if (length2 != 0) {
            int i = 1;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int indexOf = lowerCase2.indexOf(lowerCase, i3);
                if (indexOf == -1) {
                    break;
                }
                i++;
                i3 = indexOf + length2;
            }
            String[] strArr = new String[i];
            int i4 = 0;
            while (i2 <= length) {
                int indexOf2 = lowerCase2.indexOf(lowerCase, i2);
                if (indexOf2 == -1) {
                    indexOf2 = length;
                }
                strArr[i4] = str.substring(i2, indexOf2);
                i2 = indexOf2 + length2;
                i4++;
            }
            return strArr;
        }
        throw new IllegalArgumentException("The separator string has 0 length");
    }

    public static String replace(String str, String str2, String str3) {
        return replace(str, str2, str3, false, false);
    }

    public static String replace(String str, String str2, String str3, boolean z, boolean z2) {
        int length = str2.length();
        int i = 0;
        if (length == 0) {
            int length2 = str3.length();
            if (length2 == 0) {
                return str;
            }
            if (z2) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str3);
                stringBuffer.append(str);
                return stringBuffer.toString();
            }
            int length3 = str.length();
            StringBuffer stringBuffer2 = new StringBuffer(((length3 + 1) * length2) + length3);
            stringBuffer2.append(str3);
            while (i < length3) {
                stringBuffer2.append(str.charAt(i));
                stringBuffer2.append(str3);
                i++;
            }
            return stringBuffer2.toString();
        }
        if (z) {
            str2 = str2.toLowerCase();
        }
        String lowerCase = z ? str.toLowerCase() : str;
        int indexOf = lowerCase.indexOf(str2);
        if (indexOf == -1) {
            return str;
        }
        StringBuffer stringBuffer3 = new StringBuffer(str.length() + (Math.max(str3.length() - length, 0) * 3));
        do {
            stringBuffer3.append(str.substring(i, indexOf));
            stringBuffer3.append(str3);
            i = indexOf + length;
            indexOf = lowerCase.indexOf(str2, i);
            if (indexOf == -1) {
                break;
            }
        } while (!z2);
        stringBuffer3.append(str.substring(i));
        return stringBuffer3.toString();
    }

    public static String chomp(String str) {
        if (str.endsWith("\r\n")) {
            return str.substring(0, str.length() - 2);
        }
        if (str.endsWith("\r") || str.endsWith("\n")) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    public static String emptyToNull(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return null;
        }
        return str;
    }

    public static String jQuote(Object obj) {
        return jQuote(obj != null ? obj.toString() : null);
    }

    public static String jQuote(String str) {
        if (str == null) {
            return "null";
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 4);
        stringBuffer.append(Typography.quote);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                stringBuffer.append("\\\"");
            } else if (charAt == '\\') {
                stringBuffer.append("\\\\");
            } else if (charAt >= ' ') {
                stringBuffer.append(charAt);
            } else if (charAt == 10) {
                stringBuffer.append("\\n");
            } else if (charAt == 13) {
                stringBuffer.append("\\r");
            } else if (charAt == 12) {
                stringBuffer.append("\\f");
            } else if (charAt == 8) {
                stringBuffer.append("\\b");
            } else if (charAt == 9) {
                stringBuffer.append("\\t");
            } else {
                stringBuffer.append("\\u00");
                stringBuffer.append(toHexDigit(charAt / 16));
                stringBuffer.append(toHexDigit(charAt & 15));
            }
        }
        stringBuffer.append(Typography.quote);
        return stringBuffer.toString();
    }

    public static String jQuoteNoXSS(Object obj) {
        return jQuoteNoXSS(obj != null ? obj.toString() : null);
    }

    public static String jQuoteNoXSS(String str) {
        if (str == null) {
            return "null";
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 4);
        stringBuffer.append(Typography.quote);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                stringBuffer.append("\\\"");
            } else if (charAt == '\\') {
                stringBuffer.append("\\\\");
            } else if (charAt == '<') {
                stringBuffer.append("\\u003C");
            } else if (charAt >= ' ') {
                stringBuffer.append(charAt);
            } else if (charAt == 10) {
                stringBuffer.append("\\n");
            } else if (charAt == 13) {
                stringBuffer.append("\\r");
            } else if (charAt == 12) {
                stringBuffer.append("\\f");
            } else if (charAt == 8) {
                stringBuffer.append("\\b");
            } else if (charAt == 9) {
                stringBuffer.append("\\t");
            } else {
                stringBuffer.append("\\u00");
                stringBuffer.append(toHexDigit(charAt / 16));
                stringBuffer.append(toHexDigit(charAt & 15));
            }
        }
        stringBuffer.append(Typography.quote);
        return stringBuffer.toString();
    }

    public static String javaStringEnc(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '\"' || charAt == '\\' || charAt < ' ') {
                StringBuffer stringBuffer = new StringBuffer(length + 4);
                stringBuffer.append(str.substring(0, i));
                while (true) {
                    if (charAt == '\"') {
                        stringBuffer.append("\\\"");
                    } else if (charAt == '\\') {
                        stringBuffer.append("\\\\");
                    } else if (charAt >= ' ') {
                        stringBuffer.append(charAt);
                    } else if (charAt == 10) {
                        stringBuffer.append("\\n");
                    } else if (charAt == 13) {
                        stringBuffer.append("\\r");
                    } else if (charAt == 12) {
                        stringBuffer.append("\\f");
                    } else if (charAt == 8) {
                        stringBuffer.append("\\b");
                    } else if (charAt == 9) {
                        stringBuffer.append("\\t");
                    } else {
                        stringBuffer.append("\\u00");
                        int i2 = charAt / 16;
                        stringBuffer.append((char) (i2 < 10 ? i2 + 48 : (i2 - 10) + 97));
                        char c = charAt & 15;
                        stringBuffer.append((char) (c < 10 ? c + '0' : (c - 10) + 97));
                    }
                    i++;
                    if (i >= length) {
                        return stringBuffer.toString();
                    }
                    charAt = str.charAt(i);
                }
            } else {
                i++;
            }
        }
        return str;
    }

    public static String javaScriptStringEnc(String str) {
        return jsStringEnc(str, false);
    }

    public static String jsonStringEnc(String str) {
        return jsStringEnc(str, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0065, code lost:
        if (r15 != false) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a3, code lost:
        if (r15 != false) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00c5, code lost:
        if (r4 <= 159) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00cb, code lost:
        if (r4 == 8233) goto L_0x0058;
     */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00d1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String jsStringEnc(java.lang.String r14, boolean r15) {
        /*
            java.lang.String r0 = "s"
            freemarker.template.utility.NullArgumentException.check(r0, r14)
            int r0 = r14.length()
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x000c:
            if (r3 >= r0) goto L_0x0148
            char r4 = r14.charAt(r3)
            r5 = 127(0x7f, float:1.78E-43)
            r6 = 62
            r7 = 92
            if (r4 <= r6) goto L_0x001e
            if (r4 >= r5) goto L_0x001e
            if (r4 != r7) goto L_0x013f
        L_0x001e:
            r8 = 32
            if (r4 == r8) goto L_0x013f
            r9 = 160(0xa0, float:2.24E-43)
            r10 = 8232(0x2028, float:1.1535E-41)
            if (r4 < r9) goto L_0x002a
            if (r4 < r10) goto L_0x013f
        L_0x002a:
            r9 = 31
            r11 = 3
            r12 = 1
            if (r4 > r9) goto L_0x005b
            r5 = 10
            if (r4 != r5) goto L_0x0038
            r11 = 110(0x6e, float:1.54E-43)
            goto L_0x00cf
        L_0x0038:
            r5 = 13
            if (r4 != r5) goto L_0x0040
            r11 = 114(0x72, float:1.6E-43)
            goto L_0x00cf
        L_0x0040:
            r5 = 12
            if (r4 != r5) goto L_0x0048
            r11 = 102(0x66, float:1.43E-43)
            goto L_0x00cf
        L_0x0048:
            r5 = 8
            if (r4 != r5) goto L_0x0050
            r11 = 98
            goto L_0x00cf
        L_0x0050:
            r5 = 9
            if (r4 != r5) goto L_0x0058
            r11 = 116(0x74, float:1.63E-43)
            goto L_0x00cf
        L_0x0058:
            r11 = 1
            goto L_0x00cf
        L_0x005b:
            r9 = 34
            if (r4 != r9) goto L_0x0061
            goto L_0x00cf
        L_0x0061:
            r9 = 39
            if (r4 != r9) goto L_0x0069
            if (r15 == 0) goto L_0x00cf
            goto L_0x00ce
        L_0x0069:
            if (r4 != r7) goto L_0x006d
            goto L_0x00cf
        L_0x006d:
            r9 = 47
            r13 = 60
            if (r4 != r9) goto L_0x007e
            if (r3 == 0) goto L_0x00cf
            int r9 = r3 + -1
            char r9 = r14.charAt(r9)
            if (r9 != r13) goto L_0x007e
            goto L_0x00cf
        L_0x007e:
            if (r4 != r6) goto L_0x00a6
            if (r3 != 0) goto L_0x0084
        L_0x0082:
            r5 = 1
            goto L_0x00a1
        L_0x0084:
            int r5 = r3 + -1
            char r5 = r14.charAt(r5)
            r6 = 93
            if (r5 == r6) goto L_0x0095
            r6 = 45
            if (r5 != r6) goto L_0x0093
            goto L_0x0095
        L_0x0093:
            r5 = 0
            goto L_0x00a1
        L_0x0095:
            if (r3 != r12) goto L_0x0098
            goto L_0x0082
        L_0x0098:
            int r6 = r3 + -2
            char r6 = r14.charAt(r6)
            if (r6 != r5) goto L_0x0093
            goto L_0x0082
        L_0x00a1:
            if (r5 == 0) goto L_0x00ce
            if (r15 == 0) goto L_0x00cf
            goto L_0x0058
        L_0x00a6:
            if (r4 != r13) goto L_0x00c1
            int r5 = r0 + -1
            if (r3 != r5) goto L_0x00ad
            goto L_0x0058
        L_0x00ad:
            int r5 = r3 + 1
            char r5 = r14.charAt(r5)
            r6 = 33
            if (r5 == r6) goto L_0x00be
            r6 = 63
            if (r5 != r6) goto L_0x00bc
            goto L_0x00be
        L_0x00bc:
            r5 = 0
            goto L_0x00bf
        L_0x00be:
            r5 = 1
        L_0x00bf:
            r11 = r5
            goto L_0x00cf
        L_0x00c1:
            if (r4 < r5) goto L_0x00c7
            r5 = 159(0x9f, float:2.23E-43)
            if (r4 <= r5) goto L_0x0058
        L_0x00c7:
            if (r4 == r10) goto L_0x0058
            r5 = 8233(0x2029, float:1.1537E-41)
            if (r4 != r5) goto L_0x00ce
            goto L_0x0058
        L_0x00ce:
            r11 = 0
        L_0x00cf:
            if (r11 == 0) goto L_0x013f
            if (r2 != 0) goto L_0x00e1
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            int r5 = r0 + 6
            r2.<init>(r5)
            java.lang.String r5 = r14.substring(r1, r3)
            r2.append(r5)
        L_0x00e1:
            r2.append(r7)
            if (r11 <= r8) goto L_0x00eb
            char r4 = (char) r11
            r2.append(r4)
            goto L_0x0144
        L_0x00eb:
            if (r11 != r12) goto L_0x013b
            if (r15 != 0) goto L_0x010b
            r5 = 256(0x100, float:3.59E-43)
            if (r4 >= r5) goto L_0x010b
            r5 = 120(0x78, float:1.68E-43)
            r2.append(r5)
            int r5 = r4 >> 4
            char r5 = toHexDigit(r5)
            r2.append(r5)
            r4 = r4 & 15
            char r4 = toHexDigit(r4)
            r2.append(r4)
            goto L_0x0144
        L_0x010b:
            r5 = 117(0x75, float:1.64E-43)
            r2.append(r5)
            int r5 = r4 >> 12
            r5 = r5 & 15
            char r5 = toHexDigit(r5)
            r2.append(r5)
            int r5 = r4 >> 8
            r5 = r5 & 15
            char r5 = toHexDigit(r5)
            r2.append(r5)
            int r5 = r4 >> 4
            r5 = r5 & 15
            char r5 = toHexDigit(r5)
            r2.append(r5)
            r4 = r4 & 15
            char r4 = toHexDigit(r4)
            r2.append(r4)
            goto L_0x0144
        L_0x013b:
            r2.append(r4)
            goto L_0x0144
        L_0x013f:
            if (r2 == 0) goto L_0x0144
            r2.append(r4)
        L_0x0144:
            int r3 = r3 + 1
            goto L_0x000c
        L_0x0148:
            if (r2 != 0) goto L_0x014b
            goto L_0x014f
        L_0x014b:
            java.lang.String r14 = r2.toString()
        L_0x014f:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.template.utility.StringUtil.jsStringEnc(java.lang.String, boolean):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0117 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map parseNameValuePairList(java.lang.String r12, java.lang.String r13) throws java.text.ParseException {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            int r1 = r12.length()
            r2 = 32
            r3 = 0
        L_0x000c:
            if (r3 >= r1) goto L_0x001c
            char r2 = r12.charAt(r3)
            boolean r4 = java.lang.Character.isWhitespace(r2)
            if (r4 != 0) goto L_0x0019
            goto L_0x001c
        L_0x0019:
            int r3 = r3 + 1
            goto L_0x000c
        L_0x001c:
            if (r3 != r1) goto L_0x001f
            return r0
        L_0x001f:
            r4 = r3
        L_0x0020:
            r5 = 95
            if (r4 >= r1) goto L_0x0034
            char r2 = r12.charAt(r4)
            boolean r6 = java.lang.Character.isLetterOrDigit(r2)
            if (r6 != 0) goto L_0x0031
            if (r2 == r5) goto L_0x0031
            goto L_0x0034
        L_0x0031:
            int r4 = r4 + 1
            goto L_0x0020
        L_0x0034:
            java.lang.String r6 = " at position "
            java.lang.String r7 = "."
            if (r3 == r4) goto L_0x0174
            java.lang.String r8 = r12.substring(r3, r4)
        L_0x003e:
            if (r4 >= r1) goto L_0x004e
            char r2 = r12.charAt(r4)
            boolean r9 = java.lang.Character.isWhitespace(r2)
            if (r9 != 0) goto L_0x004b
            goto L_0x004e
        L_0x004b:
            int r4 = r4 + 1
            goto L_0x003e
        L_0x004e:
            if (r4 != r1) goto L_0x006f
            if (r13 == 0) goto L_0x0055
        L_0x0052:
            r5 = r13
            goto L_0x010e
        L_0x0055:
            java.text.ParseException r12 = new java.text.ParseException
            java.lang.StringBuffer r13 = new java.lang.StringBuffer
            r13.<init>()
            java.lang.String r0 = "Expecting \":\", but reached the end of the string  at position "
            r13.append(r0)
            r13.append(r4)
            r13.append(r7)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13, r4)
            throw r12
        L_0x006f:
            r9 = 58
            r10 = 44
            if (r2 == r9) goto L_0x00a4
            if (r13 == 0) goto L_0x007c
            if (r2 != r10) goto L_0x007c
            int r4 = r4 + 1
            goto L_0x0052
        L_0x007c:
            java.text.ParseException r12 = new java.text.ParseException
            java.lang.StringBuffer r13 = new java.lang.StringBuffer
            r13.<init>()
            java.lang.String r0 = "Expecting \":\" here, but found "
            r13.append(r0)
            java.lang.String r0 = java.lang.String.valueOf(r2)
            java.lang.String r0 = jQuote((java.lang.String) r0)
            r13.append(r0)
            r13.append(r6)
            r13.append(r4)
            r13.append(r7)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13, r4)
            throw r12
        L_0x00a4:
            int r4 = r4 + 1
            if (r4 >= r1) goto L_0x00b2
            char r2 = r12.charAt(r4)
            boolean r9 = java.lang.Character.isWhitespace(r2)
            if (r9 != 0) goto L_0x00a4
        L_0x00b2:
            if (r4 == r1) goto L_0x015a
            r9 = r4
        L_0x00b5:
            if (r9 >= r1) goto L_0x00c7
            char r2 = r12.charAt(r9)
            boolean r11 = java.lang.Character.isLetterOrDigit(r2)
            if (r11 != 0) goto L_0x00c4
            if (r2 == r5) goto L_0x00c4
            goto L_0x00c7
        L_0x00c4:
            int r9 = r9 + 1
            goto L_0x00b5
        L_0x00c7:
            if (r4 == r9) goto L_0x0132
            java.lang.String r4 = r12.substring(r4, r9)
        L_0x00cd:
            if (r9 >= r1) goto L_0x00dd
            char r2 = r12.charAt(r9)
            boolean r5 = java.lang.Character.isWhitespace(r2)
            if (r5 != 0) goto L_0x00da
            goto L_0x00dd
        L_0x00da:
            int r9 = r9 + 1
            goto L_0x00cd
        L_0x00dd:
            if (r9 >= r1) goto L_0x010c
            if (r2 != r10) goto L_0x00e4
            int r9 = r9 + 1
            goto L_0x010c
        L_0x00e4:
            java.text.ParseException r12 = new java.text.ParseException
            java.lang.StringBuffer r13 = new java.lang.StringBuffer
            r13.<init>()
            java.lang.String r0 = "Excpecting \",\" or the end of the string here, but found "
            r13.append(r0)
            java.lang.String r0 = java.lang.String.valueOf(r2)
            java.lang.String r0 = jQuote((java.lang.String) r0)
            r13.append(r0)
            r13.append(r6)
            r13.append(r9)
            r13.append(r7)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13, r9)
            throw r12
        L_0x010c:
            r5 = r4
            r4 = r9
        L_0x010e:
            java.lang.Object r5 = r0.put(r8, r5)
            if (r5 != 0) goto L_0x0117
            r3 = r4
            goto L_0x000c
        L_0x0117:
            java.text.ParseException r12 = new java.text.ParseException
            java.lang.StringBuffer r13 = new java.lang.StringBuffer
            r13.<init>()
            java.lang.String r0 = "Dublicated key: "
            r13.append(r0)
            java.lang.String r0 = jQuote((java.lang.String) r8)
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13, r3)
            throw r12
        L_0x0132:
            java.text.ParseException r12 = new java.text.ParseException
            java.lang.StringBuffer r13 = new java.lang.StringBuffer
            r13.<init>()
            java.lang.String r0 = "Expecting letter, digit or \"_\" here, (the first character of the value) but found "
            r13.append(r0)
            java.lang.String r0 = java.lang.String.valueOf(r2)
            java.lang.String r0 = jQuote((java.lang.String) r0)
            r13.append(r0)
            r13.append(r6)
            r13.append(r9)
            r13.append(r7)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13, r9)
            throw r12
        L_0x015a:
            java.text.ParseException r12 = new java.text.ParseException
            java.lang.StringBuffer r13 = new java.lang.StringBuffer
            r13.<init>()
            java.lang.String r0 = "Expecting the value of the key here, but reached the end of the string  at position "
            r13.append(r0)
            r13.append(r4)
            r13.append(r7)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13, r4)
            throw r12
        L_0x0174:
            java.text.ParseException r12 = new java.text.ParseException
            java.lang.StringBuffer r13 = new java.lang.StringBuffer
            r13.<init>()
            java.lang.String r0 = "Expecting letter, digit or \"_\" here, (the first character of the key) but found "
            r13.append(r0)
            java.lang.String r0 = java.lang.String.valueOf(r2)
            java.lang.String r0 = jQuote((java.lang.String) r0)
            r13.append(r0)
            r13.append(r6)
            r13.append(r4)
            r13.append(r7)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13, r4)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.template.utility.StringUtil.parseNameValuePairList(java.lang.String, java.lang.String):java.util.Map");
    }

    public static boolean isXMLID(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (i == 0 && (charAt == '-' || charAt == '.' || Character.isDigit(charAt))) {
                return false;
            }
            if (!Character.isLetterOrDigit(charAt) && charAt != ':' && charAt != '_' && charAt != '-' && charAt != '.') {
                return false;
            }
        }
        return true;
    }

    public static boolean matchesName(String str, String str2, String str3, Environment environment) {
        String defaultNS = environment.getDefaultNS();
        if (defaultNS == null || !defaultNS.equals(str3)) {
            if (!"".equals(str3)) {
                String prefixForNamespace = environment.getPrefixForNamespace(str3);
                if (prefixForNamespace == null) {
                    return false;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(prefixForNamespace);
                stringBuffer.append(":");
                stringBuffer.append(str2);
                return str.equals(stringBuffer.toString());
            } else if (defaultNS != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("N:");
                stringBuffer2.append(str2);
                return str.equals(stringBuffer2.toString());
            } else if (str.equals(str2)) {
                return true;
            } else {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("N:");
                stringBuffer3.append(str2);
                if (str.equals(stringBuffer3.toString())) {
                    return true;
                }
                return false;
            }
        } else if (str.equals(str2)) {
            return true;
        } else {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("D:");
            stringBuffer4.append(str2);
            if (str.equals(stringBuffer4.toString())) {
                return true;
            }
            return false;
        }
    }

    public static String leftPad(String str, int i) {
        return leftPad(str, i, ' ');
    }

    public static String leftPad(String str, int i, char c) {
        int length = str.length();
        if (i <= length) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        int i2 = i - length;
        for (int i3 = 0; i3 < i2; i3++) {
            stringBuffer.append(c);
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static String leftPad(String str, int i, String str2) {
        int length = str.length();
        if (i <= length) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        int i2 = i - length;
        int length2 = str2.length();
        if (length2 != 0) {
            int i3 = i2 / length2;
            for (int i4 = 0; i4 < i3; i4++) {
                stringBuffer.append(str2);
            }
            int i5 = i2 % length2;
            for (int i6 = 0; i6 < i5; i6++) {
                stringBuffer.append(str2.charAt(i6));
            }
            stringBuffer.append(str);
            return stringBuffer.toString();
        }
        throw new IllegalArgumentException("The \"filling\" argument can't be 0 length string.");
    }

    public static String rightPad(String str, int i) {
        return rightPad(str, i, ' ');
    }

    public static String rightPad(String str, int i, char c) {
        int length = str.length();
        if (i <= length) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int i2 = i - length;
        for (int i3 = 0; i3 < i2; i3++) {
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }

    public static String rightPad(String str, int i, String str2) {
        int length = str.length();
        if (i <= length) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int i2 = i - length;
        int length2 = str2.length();
        if (length2 != 0) {
            int i3 = length % length2;
            int i4 = length2 - i3 <= i2 ? length2 : i3 + i2;
            for (int i5 = i3; i5 < i4; i5++) {
                stringBuffer.append(str2.charAt(i5));
            }
            int i6 = i2 - (i4 - i3);
            int i7 = i6 / length2;
            for (int i8 = 0; i8 < i7; i8++) {
                stringBuffer.append(str2);
            }
            int i9 = i6 % length2;
            for (int i10 = 0; i10 < i9; i10++) {
                stringBuffer.append(str2.charAt(i10));
            }
            return stringBuffer.toString();
        }
        throw new IllegalArgumentException("The \"filling\" argument can't be 0 length string.");
    }

    public static int versionStringToInt(String str) {
        return new Version(str).intValue();
    }

    public static String tryToString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj.toString();
        } catch (Throwable unused) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[toString() failed: ");
            stringBuffer.append(th.getClass().getName());
            stringBuffer.append("]");
            return stringBuffer.toString();
        }
    }
}
