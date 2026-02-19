package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.zza;

final class zzdzp extends zzebh<GetTokenResult, com.google.firebase.auth.internal.zza> {
    private final String zzmqq;

    public zzdzp(String str) {
        super(1);
        this.zzmqq = zzbq.zzh(str, "refresh token cannot be null");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zza(this.zzmqq, (zzeax) this.zzmrh);
    }

    public final void zzbtu() {
        if (TextUtils.isEmpty(this.zzmrr.zzbue())) {
            this.zzmrr.zzpf(this.zzmqq);
        }
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, this.zzmri);
        zzbh(new GetTokenResult(this.zzmrr.getAccessToken()));
    }
}
