package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.tagmanager.ContainerHolder;

final class zzx extends Handler {
    private final ContainerHolder.ContainerAvailableListener zzknp;
    private /* synthetic */ zzv zzknq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzx(zzv zzv, ContainerHolder.ContainerAvailableListener containerAvailableListener, Looper looper) {
        super(looper);
        this.zzknq = zzv;
        this.zzknp = containerAvailableListener;
    }

    public final void handleMessage(Message message) {
        if (message.what != 1) {
            zzdj.e("Don't know how to handle this message.");
            return;
        }
        this.zzknp.onContainerAvailable(this.zzknq, (String) message.obj);
    }
}
