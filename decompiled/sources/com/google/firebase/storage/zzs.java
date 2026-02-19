package com.google.firebase.storage;

final class zzs implements Runnable {
    private /* synthetic */ StorageTask zzovk;

    zzs(StorageTask storageTask) {
        this.zzovk = storageTask;
    }

    public final void run() {
        try {
            this.zzovk.run();
        } finally {
            this.zzovk.zzcoc();
        }
    }
}
