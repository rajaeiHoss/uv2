package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.util.zzg;
import com.google.firebase.crash.FirebaseCrash;

public final class zzecq extends zzeck {
    private final boolean zzmvf;

    public zzecq(Context context, FirebaseCrash.zza zza, boolean z) {
        super(context, zza);
        this.zzmvf = z;
    }

    /* access modifiers changed from: protected */
    public final String getErrorMessage() {
        boolean z = this.zzmvf;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Failed to set crash enabled to ");
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
        zzect.zzcq(this.zzmvf);
    }
}
