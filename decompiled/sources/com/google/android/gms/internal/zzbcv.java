package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;
import java.util.Locale;

final class zzbcv implements zzben {
    private /* synthetic */ zzbcl zzfkz;
    private /* synthetic */ zzbcu zzflc;

    zzbcv(zzbcu zzbcu, zzbcl zzbcl) {
        this.zzflc = zzbcu;
        this.zzfkz = zzbcl;
    }

    public final void zza(long j, int i, Object obj) {
        if (obj == null) {
            try {
                this.zzflc.setResult(new zzbcw(new Status(i, (String) null, (PendingIntent) null), this.zzflc.zzflb));
            } catch (ClassCastException unused) {
                this.zzflc.setResult(zzbcu.zzm(new Status(13)));
            }
        } else {
            zzbcz zzbcz = (zzbcz) obj;
            zzbcy zzbcy = zzbcz.zzfkk;
            if (zzbcy == null || zzbdw.zza("1.0.0", zzbcy.getVersion())) {
                this.zzflc.setResult(new zzbcw(new Status(i, zzbcz.zzflk, (PendingIntent) null), this.zzflc.zzflb));
                return;
            }
            zzbcy unused2 = this.zzflc.zzfkt.zzfkk = null;
            this.zzflc.setResult(zzbcu.zzm(new Status(GameManagerClient.STATUS_INCORRECT_VERSION, String.format(Locale.ROOT, "Incorrect Game Manager SDK version. Receiver: %s Sender: %s", new Object[]{zzbcy.getVersion(), "1.0.0"}))));
        }
    }

    public final void zzx(long j) {
        this.zzflc.setResult(zzbcu.zzm(new Status(2103)));
    }
}
