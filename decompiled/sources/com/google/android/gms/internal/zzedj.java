package com.google.android.gms.internal;

import com.google.firebase.FirebaseApp;

final class zzedj implements FirebaseApp.zza {
    private /* synthetic */ zzeew zzmxx;

    zzedj(zzedg zzedg, zzeew zzeew) {
        this.zzmxx = zzeew;
    }

    public final void zzbj(boolean z) {
        if (z) {
            this.zzmxx.interrupt("app_in_background");
        } else {
            this.zzmxx.resume("app_in_background");
        }
    }
}
