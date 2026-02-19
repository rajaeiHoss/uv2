package com.google.firebase.storage;

import com.google.firebase.storage.StorageTask;

final class zzz implements Runnable {
    private /* synthetic */ Object zzowq;
    private /* synthetic */ zzx zzowr;
    private /* synthetic */ StorageTask.ProvideError zzows;

    zzz(zzx zzx, Object obj, StorageTask.ProvideError provideError) {
        this.zzowr = zzx;
        this.zzowq = obj;
        this.zzows = provideError;
    }

    public final void run() {
        this.zzowr.zzowp.zzi(this.zzowq, this.zzows);
    }
}
