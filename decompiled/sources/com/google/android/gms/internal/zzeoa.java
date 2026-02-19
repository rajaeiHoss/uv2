package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

final class zzeoa implements zzenz {
    private static ThreadLocal<CharsetDecoder> zznpr = new zzeob();
    private static ThreadLocal<CharsetEncoder> zznps = new zzeoc();
    private StringBuilder zznpt = new StringBuilder();

    zzeoa() {
    }

    private static String zzap(byte[] bArr) {
        try {
            return zznpr.get().decode(ByteBuffer.wrap(bArr)).toString();
        } catch (CharacterCodingException unused) {
            return null;
        }
    }

    public final boolean zzao(byte[] bArr) {
        String zzap = zzap(bArr);
        if (zzap == null) {
            return false;
        }
        this.zznpt.append(zzap);
        return true;
    }

    public final zzeom zzcda() {
        return new zzeom(this.zznpt.toString());
    }
}
