package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.firebase.crash.FirebaseCrash;

public final class zzecn extends zzeck {
    private final String zzmvc;

    public zzecn(Context context, FirebaseCrash.zza zza, String str) {
        super(context, zza);
        this.zzmvc = str;
    }

    /* access modifiers changed from: protected */
    public final String getErrorMessage() {
        return "Failed to log message";
    }

    public final /* bridge */ /* synthetic */ void run() {
        super.run();
    }

    /* access modifiers changed from: protected */
    public final void zzd(zzect zzect) throws RemoteException {
        zzect.log(this.zzmvc);
    }
}
