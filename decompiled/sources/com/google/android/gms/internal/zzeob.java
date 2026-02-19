package com.google.android.gms.internal;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

final class zzeob extends ThreadLocal<CharsetDecoder> {
    zzeob() {
    }

    /* access modifiers changed from: protected */
    public final CharsetDecoder initialValue() {
        CharsetDecoder newDecoder = Charset.forName("UTF8").newDecoder();
        newDecoder.onMalformedInput(CodingErrorAction.REPORT);
        newDecoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        return newDecoder;
    }
}
