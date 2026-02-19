package com.google.zxing.client.result;

import com.google.zxing.Result;

final class URLTOResultParser {
    private URLTOResultParser() {
    }

    public static URIParsedResult parse(Result result) {
        int indexOf;
        String text = result.getText();
        String str = null;
        if (text == null || ((!text.startsWith("urlto:") && !text.startsWith("URLTO:")) || (indexOf = text.indexOf(58, 6)) < 0)) {
            return null;
        }
        if (indexOf > 6) {
            str = text.substring(6, indexOf);
        }
        return new URIParsedResult(text.substring(indexOf + 1), str);
    }
}
