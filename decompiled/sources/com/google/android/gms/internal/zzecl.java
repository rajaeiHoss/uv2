package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.firebase.crash.FirebaseCrash;

public final class zzecl extends zzeck {
    private final long zzfol;
    private final String zzmvc;
    private final Bundle zzmvd;

    public zzecl(Context context, FirebaseCrash.zza zza, String str, long j, Bundle bundle) {
        super(context, zza);
        this.zzmvc = str;
        this.zzfol = j;
        this.zzmvd = bundle;
    }

    /* access modifiers changed from: protected */
    public final String getErrorMessage() {
        return "Failed to log analytics event";
    }

    public final /* bridge */ /* synthetic */ void run() {
        super.run();
    }

    /* access modifiers changed from: protected */
    public final void zzd(zzect zzect) throws RemoteException {
        zzect.zza(this.zzmvc, this.zzfol, this.zzmvd);
    }
}
