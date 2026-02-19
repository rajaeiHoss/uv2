package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

abstract class zze extends zzi<Boolean> {
    private int statusCode;
    private Bundle zzgfj;
    private /* synthetic */ zzd zzgfk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected zze(zzd zzd, int i, Bundle bundle) {
        super(zzd, true);
        this.zzgfk = zzd;
        this.statusCode = i;
        this.zzgfj = bundle;
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzama();

    /* access modifiers changed from: protected */
    public final void zzamb() {
    }

    /* access modifiers changed from: protected */
    public abstract void zzj(ConnectionResult connectionResult);

    /* access modifiers changed from: protected */
    public final void zzw(Boolean bool) {
        if (bool == null) {
            this.zzgfk.zza(1, null);
            return;
        }
        int i = this.statusCode;
        if (i == 0) {
            if (!zzama()) {
                this.zzgfk.zza(1, null);
                zzj(new ConnectionResult(8, null));
            }
            return;
        }
        if (i == 10) {
            this.zzgfk.zza(1, null);
            throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
        }
        this.zzgfk.zza(1, null);
        PendingIntent pendingIntent = null;
        Bundle bundle = this.zzgfj;
        if (bundle != null) {
            pendingIntent = (PendingIntent) bundle.getParcelable("pendingIntent");
        }
        zzj(new ConnectionResult(this.statusCode, pendingIntent));
    }
}
