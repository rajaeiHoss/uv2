package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.util.zzg;
import com.google.firebase.crash.FirebaseCrash;

abstract class zzeck implements Runnable {
    protected final Context mContext;
    protected final FirebaseCrash.zza zzmvb;

    zzeck(Context context, FirebaseCrash.zza zza) {
        this.zzmvb = zza;
        this.mContext = context.getApplicationContext();
    }

    /* access modifiers changed from: protected */
    public abstract String getErrorMessage();

    public void run() {
        try {
            zzect zzbux = this.zzmvb.zzbux();
            if (zzbux != null) {
                if (zzbux.zzbuw()) {
                    zzd(zzbux);
                    return;
                }
            }
            if (zzbux != null) {
                Log.e("FirebaseCrash", "Firebase Crash Reporting not enabled");
            } else {
                Log.e("FirebaseCrash", "Crash api not available");
            }
        } catch (RemoteException | RuntimeException e) {
            zzg.zza(this.mContext, e);
            Log.e("FirebaseCrash", getErrorMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzd(zzect zzect) throws RemoteException;
}
