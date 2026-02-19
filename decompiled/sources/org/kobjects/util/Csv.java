package org.kobjects.util;

import java.util.Vector;
import kotlin.text.Typography;

public class Csv {
    public static String encode(String str, char c) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == c || charAt == '^') {
                stringBuffer.append(charAt);
                stringBuffer.append(charAt);
            } else if (charAt < ' ') {
                stringBuffer.append('^');
                stringBuffer.append((char) (charAt + '@'));
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    public static String encode(Object[] objArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < objArr.length; i++) {
            if (i != 0) {
                stringBuffer.append(',');
            }
            Object obj = objArr[i];
            if ((obj instanceof Number) || (obj instanceof Boolean)) {
                stringBuffer.append(obj.toString());
            } else {
                stringBuffer.append(Typography.quote);
                stringBuffer.append(encode(obj.toString(), Typography.quote));
                stringBuffer.append(Typography.quote);
            }
        }
        return stringBuffer.toString();
    }

    public static String[] decode(String str) {
        int i;
        Vector vector = new Vector();
        int length = str.length();
        int i2 = 0;
        while (true) {
            if (i2 >= length || str.charAt(i2) > ' ') {
                if (i2 >= length) {
                    break;
                } else if (str.charAt(i2) == '\"') {
                    int i3 = i2 + 1;
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        i = i3 + 1;
                        char charAt = str.charAt(i3);
                        if (charAt == '\"') {
                            if (i == length || str.charAt(i) != '\"') {
                                break;
                            }
                            i++;
                            stringBuffer.append('\"');
                            i3 = i;
                        } else if (charAt != '^' || i >= length) {
                            stringBuffer.append(charAt);
                            i3 = i;
                        } else {
                            i3 = i + 1;
                            char charAt2 = str.charAt(i);
                            if (charAt2 != '^') {
                                charAt2 = (char) (charAt2 - '@');
                            }
                            stringBuffer.append(charAt2);
                        }
                    }
                    vector.addElement(stringBuffer.toString());
                    while (i < length && str.charAt(i) <= ' ') {
                        i++;
                    }
                    if (i >= length) {
                        break;
                    } else if (str.charAt(i) == ',') {
                        i2 = i + 1;
                    } else {
                        throw new RuntimeException("Comma expected at " + i + " line: " + str);
                    }
                } else {
                    int indexOf = str.indexOf(44, i2);
                    if (indexOf == -1) {
                        vector.addElement(str.substring(i2).trim());
                        break;
                    }
                    vector.addElement(str.substring(i2, indexOf).trim());
                    i2 = indexOf + 1;
                }
            } else {
                i2++;
            }
        }
        int size = vector.size();
        String[] strArr = new String[size];
        for (int i4 = 0; i4 < size; i4++) {
            strArr[i4] = (String) vector.elementAt(i4);
        }
        return strArr;
    }
}
