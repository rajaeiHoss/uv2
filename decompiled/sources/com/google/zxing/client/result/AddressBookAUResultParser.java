package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Vector;

final class AddressBookAUResultParser extends ResultParser {
    AddressBookAUResultParser() {
    }

    private static String[] matchMultipleValuePrefix(String str, int i, String str2, boolean z) {
        Vector vector = null;
        for (int i2 = 1; i2 <= i; i2++) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(i2);
            stringBuffer.append(':');
            String matchSinglePrefixedField = matchSinglePrefixedField(stringBuffer.toString(), str2, (char) 13, z);
            if (matchSinglePrefixedField == null) {
                break;
            }
            if (vector == null) {
                vector = new Vector(i);
            }
            vector.addElement(matchSinglePrefixedField);
        }
        if (vector == null) {
            return null;
        }
        return toStringArray(vector);
    }

    public static AddressBookParsedResult parse(Result result) {
        String text = result.getText();
        String[] strArr = null;
        if (text == null || text.indexOf("MEMORY") < 0 || text.indexOf("\r\n") < 0) {
            return null;
        }
        String matchSinglePrefixedField = matchSinglePrefixedField("NAME1:", text, (char) 13, true);
        String matchSinglePrefixedField2 = matchSinglePrefixedField("NAME2:", text, (char) 13, true);
        String[] matchMultipleValuePrefix = matchMultipleValuePrefix("TEL", 3, text, true);
        String[] matchMultipleValuePrefix2 = matchMultipleValuePrefix("MAIL", 3, text, true);
        String matchSinglePrefixedField3 = matchSinglePrefixedField("MEMORY:", text, (char) 13, false);
        String matchSinglePrefixedField4 = matchSinglePrefixedField("ADD:", text, (char) 13, true);
        if (matchSinglePrefixedField4 != null) {
            strArr = new String[]{matchSinglePrefixedField4};
        }
        return new AddressBookParsedResult(maybeWrap(matchSinglePrefixedField), matchSinglePrefixedField2, matchMultipleValuePrefix, matchMultipleValuePrefix2, matchSinglePrefixedField3, strArr, (String) null, (String) null, (String) null, (String) null);
    }
}
