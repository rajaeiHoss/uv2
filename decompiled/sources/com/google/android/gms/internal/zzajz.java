package com.google.android.gms.internal;

import java.util.Map;

public final class zzajz extends zzr<Object> {
    private final zzamd<zzp> zzdhi;
    private final Map<String, String> zzdhj;
    private final zzaks zzdhk;

    public zzajz(String str, zzamd<zzp> zzamd) {
        this(str, (Map<String, String>) null, zzamd);
    }

    private zzajz(String str, Map<String, String> map, zzamd<zzp> zzamd) {
        super(0, str, new zzaka(zzamd));
        this.zzdhj = null;
        this.zzdhi = zzamd;
        zzaks zzaks = new zzaks();
        this.zzdhk = zzaks;
        zzaks.zza(str, "GET", (Map<String, ?>) null, (byte[]) null);
    }

    /* access modifiers changed from: protected */
    public final zzx<Object> zza(zzp zzp) {
        return zzx.zza((Object) zzp, zzap.zzb(zzp));
    }

    /* access modifiers changed from: protected */
    public final void zza(Object obj) {
        zzp zzp = (zzp) obj;
        this.zzdhk.zza((Map<String, ?>) zzp.zzab, zzp.statusCode);
        zzaks zzaks = this.zzdhk;
        byte[] bArr = zzp.data;
        if (zzaks.isEnabled() && bArr != null) {
            zzaks.zzf(bArr);
        }
        this.zzdhi.set(zzp);
    }
}
