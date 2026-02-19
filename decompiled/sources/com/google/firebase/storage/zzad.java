package com.google.firebase.storage;

import com.google.android.gms.internal.zzfbh;
import com.google.android.gms.internal.zzfbn;

final class zzad implements Runnable {
    private /* synthetic */ zzfbn zzoxd;
    private /* synthetic */ UploadTask zzoxe;

    zzad(UploadTask uploadTask, zzfbn zzfbn) {
        this.zzoxe = uploadTask;
        this.zzoxd = zzfbn;
    }

    public final void run() {
        this.zzoxd.zze(zzfbh.zzh(this.zzoxe.zzotm.getStorage().getApp()), this.zzoxe.zzotm.getStorage().getApp().getApplicationContext());
    }
}
