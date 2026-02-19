package com.google.android.gms.internal;

import java.util.Map;

final class zzajw extends zzav {
    private /* synthetic */ byte[] zzdhf;
    private /* synthetic */ Map zzdhg;
    private /* synthetic */ zzaks zzdhh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzajw(zzajr zzajr, int i, String str, zzz zzz, zzy zzy, byte[] bArr, Map map, zzaks zzaks) {
        super(i, str, zzz, zzy);
        this.zzdhf = bArr;
        this.zzdhg = map;
        this.zzdhh = zzaks;
    }

    public final Map<String, String> getHeaders() throws zza {
        Map<String, String> map = this.zzdhg;
        return map == null ? super.getHeaders() : map;
    }

    public final byte[] zzf() throws zza {
        byte[] bArr = this.zzdhf;
        return bArr == null ? super.zzf() : bArr;
    }

    /* access modifiers changed from: protected */
    public final void zza(String str) {
        this.zzdhh.zzcw(str);
        super.zza(str);
    }
}
