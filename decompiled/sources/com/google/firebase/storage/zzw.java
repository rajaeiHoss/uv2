package com.google.firebase.storage;

import java.io.InputStream;
import java.util.concurrent.Callable;

final class zzw implements Callable<InputStream> {
    private /* synthetic */ StreamDownloadTask zzowd;

    zzw(StreamDownloadTask streamDownloadTask) {
        this.zzowd = streamDownloadTask;
    }

    public final InputStream call() throws Exception {
        return this.zzowd.zzcoe();
    }
}
