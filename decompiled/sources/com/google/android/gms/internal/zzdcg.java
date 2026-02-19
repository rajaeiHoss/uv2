package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;

final class zzdcg implements ThreadFactory {
    zzdcg() {
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "google-tag-manager-background-thread");
    }
}
