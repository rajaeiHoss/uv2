package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzf;
import com.google.firebase.auth.internal.zzk;

final class zzdzs extends zzebh<AuthResult, com.google.firebase.auth.internal.zza> implements zzebj {
    private final PhoneAuthCredential zzmqt;

    public zzdzs(PhoneAuthCredential phoneAuthCredential) {
        super(2);
        this.zzmqt = (PhoneAuthCredential) zzbq.checkNotNull(phoneAuthCredential, "credential cannot be null");
        this.zzmrq = this;
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zza(this.zzmri.zzbtn(), this.zzmqt, (zzeax) this.zzmrh);
    }

    public final void zza(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        boolean z = this.zzmrg == 2;
        int i = this.zzmrg;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        this.zzmrx = false;
        this.zzmrw = phoneAuthCredential;
        if (this.zzmrl != null) {
            this.zzmrl.onError(status);
        }
        zzax(status);
    }

    public final void zzbtu() {
        zzk zzb = zzdzh.zza(this.zzmpb, this.zzmrs);
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzb);
        zzbh(new zzf(zzb));
    }
}
