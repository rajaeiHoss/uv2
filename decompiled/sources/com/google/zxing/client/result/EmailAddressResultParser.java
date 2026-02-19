package com.google.zxing.client.result;

import androidx.core.net.MailTo;
import com.google.zxing.Result;
import java.util.Hashtable;

final class EmailAddressResultParser extends ResultParser {
    EmailAddressResultParser() {
    }

    public static EmailAddressParsedResult parse(Result result) {
        String str;
        String text = result.getText();
        String str2 = null;
        if (text == null) {
            return null;
        }
        if (text.startsWith(MailTo.MAILTO_SCHEME) || text.startsWith("MAILTO:")) {
            String substring = text.substring(7);
            int indexOf = substring.indexOf(63);
            if (indexOf >= 0) {
                substring = substring.substring(0, indexOf);
            }
            Hashtable parseNameValuePairs = parseNameValuePairs(text);
            if (parseNameValuePairs != null) {
                if (substring.length() == 0) {
                    substring = (String) parseNameValuePairs.get("to");
                }
                str2 = (String) parseNameValuePairs.get("subject");
                str = (String) parseNameValuePairs.get("body");
            } else {
                str = null;
            }
            return new EmailAddressParsedResult(substring, str2, str, text);
        } else if (!EmailDoCoMoResultParser.isBasicallyValidEmailAddress(text)) {
            return null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(MailTo.MAILTO_SCHEME);
            stringBuffer.append(text);
            return new EmailAddressParsedResult(text, (String) null, (String) null, stringBuffer.toString());
        }
    }
}
