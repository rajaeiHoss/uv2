package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthCredential;

final class zzdzu<ResultT, CallbackT> extends zzdzf<zzeau, ResultT> implements zzebg<ResultT> {
    private TaskCompletionSource<ResultT> zzejm;
    private final String zzmqu;
    private zzebh<ResultT, CallbackT> zzmqv;

    public zzdzu(zzebh<ResultT, CallbackT> zzebh, String str) {
        this.zzmqv = zzebh;
        zzebh.zzmrm = this;
        this.zzmqu = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzeau zzeau, TaskCompletionSource<ResultT> taskCompletionSource) throws RemoteException {
        this.zzejm = taskCompletionSource;
        zzebh<ResultT, CallbackT> zzebh = this.zzmqv;
        zzebh.zzmrj = zzeau.zzbtv();
        zzebh.dispatch();
    }

    public final void zza(ResultT resultt, Status status) {
        zzbq.checkNotNull(this.zzejm, "doExecute must be called before onComplete");
        if (status == null) {
            this.zzejm.setResult(resultt);
        } else if (this.zzmqv.zzmrw != null) {
            PhoneAuthCredential phoneAuthCredential = this.zzmqv.zzmrw;
            try {
                phoneAuthCredential = (PhoneAuthCredential) phoneAuthCredential.clone();
            } catch (CloneNotSupportedException unused) {
            }
            this.zzejm.setException(zzeaw.zzb(status, phoneAuthCredential));
            this.zzmqv.zzmrw = null;
        } else {
            this.zzejm.setException(zzeaw.zzaw(status));
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzbtt() {
        return this.zzmqu;
    }
}
