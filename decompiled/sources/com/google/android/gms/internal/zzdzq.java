package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzf;
import com.google.firebase.auth.internal.zzk;

final class zzdzq extends zzebh<AuthResult, com.google.firebase.auth.internal.zza> {
    private final EmailAuthCredential zzmqr;

    public zzdzq(EmailAuthCredential emailAuthCredential) {
        super(2);
        this.zzmqr = (EmailAuthCredential) zzbq.checkNotNull(emailAuthCredential, "credential cannot be null");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zza(this.zzmqr.getEmail(), this.zzmqr.getPassword(), this.zzmri.zzbtn(), this.zzmrh);
    }

    public final void zzbtu() {
        zzk zzb = zzdzh.zza(this.zzmpb, this.zzmrs);
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzb);
        zzbh(new zzf(zzb));
    }
}
