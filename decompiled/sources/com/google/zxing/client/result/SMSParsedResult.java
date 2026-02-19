package com.google.zxing.client.result;

import kotlin.text.Typography;

public final class SMSParsedResult extends ParsedResult {
    private final String body;
    private final String[] numbers;
    private final String subject;
    private final String[] vias;

    public SMSParsedResult(String str, String str2, String str3, String str4) {
        super(ParsedResultType.SMS);
        this.numbers = new String[]{str};
        this.vias = new String[]{str2};
        this.subject = str3;
        this.body = str4;
    }

    public SMSParsedResult(String[] strArr, String[] strArr2, String str, String str2) {
        super(ParsedResultType.SMS);
        this.numbers = strArr;
        this.vias = strArr2;
        this.subject = str;
        this.body = str2;
    }

    public String getBody() {
        return this.body;
    }

    public String getDisplayResult() {
        StringBuffer stringBuffer = new StringBuffer(100);
        maybeAppend(this.numbers, stringBuffer);
        maybeAppend(this.subject, stringBuffer);
        maybeAppend(this.body, stringBuffer);
        return stringBuffer.toString();
    }

    public String[] getNumbers() {
        return this.numbers;
    }

    public String getSMSURI() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sms:");
        boolean z = false;
        boolean z2 = true;
        for (int i = 0; i < this.numbers.length; i++) {
            if (z2) {
                z2 = false;
            } else {
                stringBuffer.append(',');
            }
            stringBuffer.append(this.numbers[i]);
            if (this.vias[i] != null) {
                stringBuffer.append(";via=");
                stringBuffer.append(this.vias[i]);
            }
        }
        boolean z3 = this.body != null;
        if (this.subject != null) {
            z = true;
        }
        if (z3 || z) {
            stringBuffer.append('?');
            if (z3) {
                stringBuffer.append("body=");
                stringBuffer.append(this.body);
            }
            if (z) {
                if (z3) {
                    stringBuffer.append(Typography.amp);
                }
                stringBuffer.append("subject=");
                stringBuffer.append(this.subject);
            }
        }
        return stringBuffer.toString();
    }

    public String getSubject() {
        return this.subject;
    }

    public String[] getVias() {
        return this.vias;
    }
}
