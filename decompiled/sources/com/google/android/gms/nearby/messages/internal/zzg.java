package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzg extends zzc {
    public zzg(String str) {
        this(zzky(str));
    }

    public zzg(String str, String str2) {
        this(zzky(str), zzky(str2));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzg(byte[] bArr) {
        super(bArr);
        zzbq.checkArgument(bArr.length == 10 || bArr.length == 16, "Bytes must be a namespace (10 bytes), or a namespace plus instance (16 bytes).");
    }

    private zzg(byte[] r8, byte[] r9) {
        super(com.google.android.gms.common.util.zzb.zza(new byte[][]{r8, r9}));
        boolean z = r8.length == 10;
        StringBuilder sb = new StringBuilder(53);
        sb.append("Namespace length(");
        sb.append(r8.length);
        sb.append(" bytes) must be 10 bytes.");
        zzbq.checkArgument(z, sb.toString());
        boolean z2 = r9.length == 6;
        StringBuilder sb2 = new StringBuilder(51);
        sb2.append("Instance length(");
        sb2.append(r9.length);
        sb2.append(" bytes) must be 6 bytes.");
        zzbq.checkArgument(z2, sb2.toString());
    }

    public final String toString() {
        String hex = getHex();
        StringBuilder sb = new StringBuilder(String.valueOf(hex).length() + 26);
        sb.append("EddystoneUidPrefix{bytes=");
        sb.append(hex);
        sb.append('}');
        return sb.toString();
    }
}
