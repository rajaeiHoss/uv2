package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzbcs implements zzben {
    private /* synthetic */ zzbcl zzfkz;
    private /* synthetic */ zzbcr zzfla;

    zzbcs(zzbcr zzbcr, zzbcl zzbcl) {
        this.zzfla = zzbcr;
        this.zzfkz = zzbcl;
    }

    public final void zza(long j, int i, Object obj) {
        if (obj == null) {
            try {
                this.zzfla.setResult(new zzbcx(new Status(i, (String) null, (PendingIntent) null), (String) null, j, (JSONObject) null));
            } catch (ClassCastException unused) {
                this.zzfla.setResult(zzbcr.zzl(new Status(13)));
            }
        } else {
            zzbcz zzbcz = (zzbcz) obj;
            String str = zzbcz.zzfld;
            if (i == 0 && str != null) {
                String unused2 = this.zzfla.zzfkt.zzfks = str;
            }
            this.zzfla.setResult(new zzbcx(new Status(i, zzbcz.zzflk, (PendingIntent) null), str, zzbcz.zzfle, zzbcz.zzflf));
        }
    }

    public final void zzx(long j) {
        this.zzfla.setResult(zzbcr.zzl(new Status(2103)));
    }
}
