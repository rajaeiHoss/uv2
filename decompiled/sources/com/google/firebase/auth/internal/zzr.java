package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzccp;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;

final class zzr implements Runnable {
    private final String zzmtp;
    final /* synthetic */ zzq zzmua;

    zzr(zzq zzq, String str) {
        this.zzmua = zzq;
        this.zzmtp = zzbq.zzgv(str);
    }

    public final void run() {
        FirebaseApp instance = FirebaseApp.getInstance(this.zzmtp);
        FirebaseAuth instance2 = FirebaseAuth.getInstance(instance);
        zzv.initialize(instance.getApplicationContext());
        if (instance2.getCurrentUser() != null) {
            if (((Boolean) zzccp.zzaso().zzb(zzv.zzmue)).booleanValue()) {
                Task<GetTokenResult> zzcj = instance2.zzcj(true);
                zzq.zzehr.zza("Token refreshing started", new Object[0]);
                zzcj.addOnFailureListener(new zzs(this));
            }
        }
    }
}
