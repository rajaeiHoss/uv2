package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;

final class zzdci implements ThreadFactory {
    zzdci() {
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "google-tag-manager-scheduler-thread");
    }
}
