package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzk;

final class zzeab extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    private final PhoneAuthCredential zzmqt;

    public zzeab(PhoneAuthCredential phoneAuthCredential) {
        super(2);
        zzbq.checkNotNull(phoneAuthCredential, "credential cannot be null");
        this.zzmqt = phoneAuthCredential.zzcl(false);
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zza(this.zzmqt, (zzeax) this.zzmrh);
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
