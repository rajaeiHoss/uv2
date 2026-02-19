package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Message;

final class zzdbi implements zzdbh {
    private Handler handler;
    final /* synthetic */ zzdbe zzkyg;

    private zzdbi(zzdbe zzdbe) {
        this.zzkyg = zzdbe;
        this.handler = new Handler(zzdbe.zzktj.getMainLooper(), new zzdbj(this));
    }

    /* synthetic */ zzdbi(zzdbe zzdbe, zzdbf zzdbf) {
        this(zzdbe);
    }

    private final Message obtainMessage() {
        return this.handler.obtainMessage(1, zzdbe.zzkti);
    }

    public final void cancel() {
        this.handler.removeMessages(1, zzdbe.zzkti);
    }

    public final void zzbic() {
        this.handler.removeMessages(1, zzdbe.zzkti);
        this.handler.sendMessage(obtainMessage());
    }

    public final void zzs(long j) {
        this.handler.removeMessages(1, zzdbe.zzkti);
        this.handler.sendMessageDelayed(obtainMessage(), j);
    }
}
