package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

final class zzdj extends Handler {
    private /* synthetic */ zzdh zzgbp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzdj(zzdh zzdh, Looper looper) {
        super(looper);
        this.zzgbp = zzdh;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            PendingResult pendingResult = (PendingResult) message.obj;
            synchronized (this.zzgbp.zzfvc) {
                if (pendingResult == null) {
                    this.zzgbp.zzgbi.zzd(new Status(13, "Transform returned null"));
                } else if (pendingResult instanceof zzct) {
                    this.zzgbp.zzgbi.zzd(((zzct) pendingResult).getStatus());
                } else {
                    this.zzgbp.zzgbi.zza(pendingResult);
                }
            }
        } else if (i != 1) {
            int i2 = message.what;
            StringBuilder sb = new StringBuilder(70);
            sb.append("TransformationResultHandler received unknown message type: ");
            sb.append(i2);
            Log.e("TransformedResultImpl", sb.toString());
        } else {
            RuntimeException runtimeException = (RuntimeException) message.obj;
            String valueOf = String.valueOf(runtimeException.getMessage());
            Log.e("TransformedResultImpl", valueOf.length() != 0 ? "Runtime exception on the transformation worker thread: ".concat(valueOf) : new String("Runtime exception on the transformation worker thread: "));
            throw runtimeException;
        }
    }
}
