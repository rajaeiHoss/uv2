package com.google.firebase.storage;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzv implements ThreadFactory {
    private final AtomicInteger zzijw = new AtomicInteger(1);
    private final String zzovz;

    zzv(String str) {
        this.zzovz = str;
    }

    public final Thread newThread(Runnable runnable) {
        String str = this.zzovz;
        int andIncrement = this.zzijw.getAndIncrement();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 27);
        sb.append("FirebaseStorage-");
        sb.append(str);
        sb.append(andIncrement);
        Thread thread = new Thread(runnable, sb.toString());
        thread.setDaemon(false);
        thread.setPriority(9);
        return thread;
    }
}
