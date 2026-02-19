package com.google.firebase.storage;

import com.google.firebase.storage.StorageTask;

final class zzaa implements Runnable {
    private /* synthetic */ zzx zzowr;
    private /* synthetic */ StorageTask.ProvideError zzows;
    private /* synthetic */ Object zzowt;

    zzaa(zzx zzx, Object obj, StorageTask.ProvideError provideError) {
        this.zzowr = zzx;
        this.zzowt = obj;
        this.zzows = provideError;
    }

    public final void run() {
        this.zzowr.zzowp.zzi(this.zzowt, this.zzows);
    }
}
