package com.google.firebase.auth.internal;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.common.api.internal.zzk;
import com.google.android.gms.common.api.internal.zzl;
import com.google.android.gms.internal.zzebw;
import com.google.firebase.FirebaseApp;

public final class zzy implements FirebaseApp.zzb {
    private volatile int zzmug;
    /* access modifiers changed from: private */
    public final zzq zzmuh;
    /* access modifiers changed from: private */
    public volatile boolean zzmui;

    private zzy(Context context, zzq zzq) {
        this.zzmui = false;
        this.zzmug = 0;
        this.zzmuh = zzq;
        zzk.zza((Application) context.getApplicationContext());
        zzk.zzaij().zza((zzl) new zzz(this));
    }

    public zzy(FirebaseApp firebaseApp) {
        this(firebaseApp.getApplicationContext(), new zzq(firebaseApp));
    }

    /* access modifiers changed from: private */
    public final boolean zzbur() {
        return this.zzmug > 0 && !this.zzmui;
    }

    public final void cancel() {
        this.zzmuh.cancel();
    }

    public final void zzc(zzebw zzebw) {
        if (zzebw != null) {
            long zzbuf = zzebw.zzbuf();
            if (zzbuf <= 0) {
                zzbuf = 3600;
            }
            zzq zzq = this.zzmuh;
            zzq.zzmtx = zzebw.zzbug() + (zzbuf * 1000);
            zzq.zzmty = -1;
            if (zzbur()) {
                this.zzmuh.zzbun();
            }
        }
    }

    public final void zzha(int i) {
        if (i > 0 && this.zzmug == 0) {
            this.zzmug = i;
            if (zzbur()) {
                this.zzmuh.zzbun();
            }
        } else if (i == 0 && this.zzmug != 0) {
            this.zzmuh.cancel();
        }
        this.zzmug = i;
    }
}
