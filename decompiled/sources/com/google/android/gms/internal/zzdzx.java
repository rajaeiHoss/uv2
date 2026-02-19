package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzk;

final class zzdzx extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    private final EmailAuthCredential zzmqr;

    public zzdzx(EmailAuthCredential emailAuthCredential) {
        super(2);
        this.zzmqr = (EmailAuthCredential) zzbq.checkNotNull(emailAuthCredential, "Credential cannot be null");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zza(this.zzmqr, (zzeax) this.zzmrh);
    }

    public final void zzbtu() {
        zzk zzb = zzdzh.zza(this.zzmpb, this.zzmrs);
        if (this.zzmri.getUid().equalsIgnoreCase(zzb.getUid())) {
            ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzb);
            zzbh(null);
            return;
        }
        zzax(new Status(17024));
    }
}
