package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.util.zzg;
import com.google.firebase.crash.FirebaseCrash;

public final class zzecp extends zzeck {
    private final boolean zzmve;

    public zzecp(Context context, FirebaseCrash.zza zza, boolean z) {
        super(context, zza);
        this.zzmve = z;
    }

    /* access modifiers changed from: protected */
    public final String getErrorMessage() {
        boolean z = this.zzmve;
        StringBuilder sb = new StringBuilder(30);
        sb.append("Failed to setAppState to ");
        sb.append(z);
        return sb.toString();
    }

    public final void run() {
        try {
            zzect zzbux = this.zzmvb.zzbux();
            if (zzbux == null) {
                Log.e("FirebaseCrash", "Crash api not available");
            } else {
                zzd(zzbux);
            }
        } catch (RemoteException | RuntimeException e) {
            zzg.zza(this.mContext, e);
            Log.e("FirebaseCrash", getErrorMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzd(zzect zzect) throws RemoteException {
        zzect.zzcp(this.zzmve);
    }
}
