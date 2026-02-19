package com.google.android.gms.tagmanager;

import java.util.Arrays;

final class zzay {
    final String zzbkr;
    final byte[] zzkpc;

    zzay(String str, byte[] bArr) {
        this.zzbkr = str;
        this.zzkpc = bArr;
    }

    public final String toString() {
        String str = this.zzbkr;
        int hashCode = Arrays.hashCode(this.zzkpc);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 54);
        sb.append("KeyAndSerialized: key = ");
        sb.append(str);
        sb.append(" serialized hash = ");
        sb.append(hashCode);
        return sb.toString();
    }
}
