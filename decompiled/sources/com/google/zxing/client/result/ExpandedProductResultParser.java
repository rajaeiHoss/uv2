package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.Hashtable;

final class ExpandedProductResultParser extends ResultParser {
    private ExpandedProductResultParser() {
    }

    private static String findAIvalue(int i, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str.charAt(i) != '(') {
            return "ERROR";
        }
        String substring = str.substring(i + 1);
        int i2 = 0;
        while (i2 < substring.length()) {
            char charAt = substring.charAt(i2);
            if (charAt == ')') {
                return stringBuffer.toString();
            }
            switch (charAt) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    stringBuffer.append(charAt);
                    i2++;
                default:
                    return "ERROR";
            }
        }
        return stringBuffer.toString();
    }

    private static String findValue(int i, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        String substring = str.substring(i);
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == '(') {
                if (!"ERROR".equals(findAIvalue(i2, substring))) {
                    break;
                }
                stringBuffer.append('(');
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    public static ExpandedProductParsedResult parse(Result result) {
        String text;
        int i;
        if (!BarcodeFormat.RSS_EXPANDED.equals(result.getBarcodeFormat()) || (text = result.getText()) == null) {
            return null;
        }
        Hashtable hashtable = new Hashtable();
        String str = "-";
        String str2 = str;
        String str3 = str2;
        String str4 = str3;
        String str5 = str4;
        String str6 = str5;
        String str7 = str6;
        String str8 = str7;
        String str9 = str8;
        String str10 = str9;
        String str11 = str10;
        String str12 = str11;
        String str13 = str12;
        int i2 = 0;
        while (i2 < text.length()) {
            String findAIvalue = findAIvalue(i2, text);
            String str14 = str12;
            if ("ERROR".equals(findAIvalue)) {
                return null;
            }
            int length = i2 + findAIvalue.length() + 2;
            String findValue = findValue(length, text);
            int length2 = length + findValue.length();
            String str15 = text;
            if ("00".equals(findAIvalue)) {
                i = length2;
                str2 = findValue;
            } else if ("01".equals(findAIvalue)) {
                i = length2;
                str = findValue;
            } else if ("10".equals(findAIvalue)) {
                i = length2;
                str3 = findValue;
            } else if ("11".equals(findAIvalue)) {
                i = length2;
                str4 = findValue;
            } else if ("13".equals(findAIvalue)) {
                i = length2;
                str5 = findValue;
            } else if ("15".equals(findAIvalue)) {
                i = length2;
                str6 = findValue;
            } else if ("17".equals(findAIvalue)) {
                i = length2;
                str7 = findValue;
            } else {
                i = length2;
                if ("3100".equals(findAIvalue) || "3101".equals(findAIvalue) || "3102".equals(findAIvalue) || "3103".equals(findAIvalue) || "3104".equals(findAIvalue) || "3105".equals(findAIvalue) || "3106".equals(findAIvalue) || "3107".equals(findAIvalue) || "3108".equals(findAIvalue) || "3109".equals(findAIvalue)) {
                    str10 = findAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.KILOGRAM;
                } else if ("3200".equals(findAIvalue) || "3201".equals(findAIvalue) || "3202".equals(findAIvalue) || "3203".equals(findAIvalue) || "3204".equals(findAIvalue) || "3205".equals(findAIvalue) || "3206".equals(findAIvalue) || "3207".equals(findAIvalue) || "3208".equals(findAIvalue) || "3209".equals(findAIvalue)) {
                    str10 = findAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.POUND;
                } else if ("3920".equals(findAIvalue) || "3921".equals(findAIvalue) || "3922".equals(findAIvalue) || "3923".equals(findAIvalue)) {
                    str11 = findValue;
                    str12 = findAIvalue.substring(3);
                    text = str15;
                    i2 = i;
                } else if (!"3930".equals(findAIvalue) && !"3931".equals(findAIvalue) && !"3932".equals(findAIvalue) && !"3933".equals(findAIvalue)) {
                    hashtable.put(findAIvalue, findValue);
                } else if (findValue.length() < 4) {
                    return null;
                } else {
                    str11 = findValue.substring(3);
                    str13 = findValue.substring(0, 3);
                    str12 = findAIvalue.substring(3);
                    text = str15;
                    i2 = i;
                }
                str8 = findValue;
                str12 = str14;
                text = str15;
                i2 = i;
            }
            str12 = str14;
            text = str15;
            i2 = i;
        }
        String str16 = str12;
        return new ExpandedProductParsedResult(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, hashtable);
    }
}
