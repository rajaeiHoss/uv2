package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zza;

final class zzear extends zzebh<Void, com.google.firebase.auth.internal.zza> {
    private final UserProfileChangeRequest zzmra;

    public zzear(UserProfileChangeRequest userProfileChangeRequest) {
        super(2);
        this.zzmra = (UserProfileChangeRequest) zzbq.checkNotNull(userProfileChangeRequest, "request cannot be null");
    }

    public final void dispatch() throws RemoteException {
        this.zzmrj.zza(this.zzmri.zzbtn(), this.zzmra, (zzeax) this.zzmrh);
    }

    public final void zzbtu() {
        ((com.google.firebase.auth.internal.zza) this.zzmrk).zza(this.zzmrr, zzdzh.zza(this.zzmpb, this.zzmrs));
        zzbh(null);
    }
}
