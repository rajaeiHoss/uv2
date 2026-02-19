package org.kobjects.util;

public class Strings {
    public static String replace(String str, String str2, String str3) {
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.substring(0, indexOf));
        while (true) {
            stringBuffer.append(str3);
            int length = indexOf + str2.length();
            int indexOf2 = str.indexOf(str2, length);
            if (indexOf2 == -1) {
                stringBuffer.append(str.substring(length));
                return stringBuffer.toString();
            }
            stringBuffer.append(str.substring(length, indexOf2));
            indexOf = indexOf2;
        }
    }

    public static String toAscii(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt <= ' ') {
                stringBuffer.append(' ');
            } else if (charAt < 127) {
                stringBuffer.append(charAt);
            } else if (charAt == 196) {
                stringBuffer.append("Ae");
            } else if (charAt == 214) {
                stringBuffer.append("Oe");
            } else if (charAt == 220) {
                stringBuffer.append("Ue");
            } else if (charAt == 223) {
                stringBuffer.append("ss");
            } else if (charAt == 228) {
                stringBuffer.append("ae");
            } else if (charAt == 246) {
                stringBuffer.append("oe");
            } else if (charAt != 252) {
                stringBuffer.append('?');
            } else {
                stringBuffer.append("ue");
            }
        }
        return stringBuffer.toString();
    }

    public static String fill(String str, int i, char c) {
        boolean z = i < 0;
        int abs = Math.abs(i);
        if (str.length() >= abs) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int length = abs - str.length(); length > 0; length--) {
            stringBuffer.append(c);
        }
        if (z) {
            stringBuffer.append(str);
            return stringBuffer.toString();
        }
        return str + stringBuffer.toString();
    }

    public static String beautify(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str.length() > 0) {
            stringBuffer.append(Character.toUpperCase(str.charAt(0)));
            for (int i = 1; i < str.length() - 1; i++) {
                char charAt = str.charAt(i);
                if (Character.isUpperCase(charAt) && Character.isLowerCase(str.charAt(i - 1)) && Character.isLowerCase(str.charAt(i + 1))) {
                    stringBuffer.append(" ");
                }
                stringBuffer.append(charAt);
            }
            if (str.length() > 1) {
                stringBuffer.append(str.charAt(str.length() - 1));
            }
        }
        return stringBuffer.toString();
    }

    public static String lTrim(String str, String str2) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (str2 != null) {
                if (str2.indexOf(charAt) == -1) {
                    break;
                }
            } else if (charAt > ' ') {
                break;
            }
            i++;
        }
        return i == 0 ? str : str.substring(i);
    }

    public static String rTrim(String str, String str2) {
        int length = str.length() - 1;
        while (length >= 0) {
            char charAt = str.charAt(length);
            if (str2 != null) {
                if (str2.indexOf(charAt) == -1) {
                    break;
                }
            } else if (charAt > ' ') {
                break;
            }
            length--;
        }
        return length == str.length() + -1 ? str : str.substring(0, length + 1);
    }
}
