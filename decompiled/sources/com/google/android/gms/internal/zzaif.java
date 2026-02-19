package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzaif implements Callable<Void> {
    private /* synthetic */ Runnable zzdfk;

    zzaif(Runnable runnable) {
        this.zzdfk = runnable;
    }

    public final Void call() throws Exception {
        this.zzdfk.run();
        return null;
    }
}
