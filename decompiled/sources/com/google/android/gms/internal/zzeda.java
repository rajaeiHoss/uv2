package com.google.android.gms.internal;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.FirebaseApp;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public final class zzeda implements zzegd {
    /* access modifiers changed from: private */
    public final ScheduledExecutorService zzmxn;
    private final FirebaseApp zzmxo;

    public zzeda(FirebaseApp firebaseApp, ScheduledExecutorService scheduledExecutorService) {
        this.zzmxo = firebaseApp;
        this.zzmxn = scheduledExecutorService;
    }

    public final void zza(zzegf zzegf) {
        this.zzmxo.zza((FirebaseApp.IdTokenListener) new zzedd(this, zzegf));
    }

    public final void zza(boolean z, zzege zzege) {
        this.zzmxo.getToken(z).addOnSuccessListener((Executor) this.zzmxn, new zzedc(this, zzege)).addOnFailureListener((Executor) this.zzmxn, (OnFailureListener) new zzedb(this, zzege));
    }
}
