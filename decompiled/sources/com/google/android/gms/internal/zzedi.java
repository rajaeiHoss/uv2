package com.google.android.gms.internal;

final class zzedi implements Runnable {
    private /* synthetic */ String val$message;
    private /* synthetic */ Throwable zzmxw;

    zzedi(zzedh zzedh, String str, Throwable th) {
        this.val$message = str;
        this.zzmxw = th;
    }

    public final void run() {
        throw new RuntimeException(this.val$message, this.zzmxw);
    }
}
