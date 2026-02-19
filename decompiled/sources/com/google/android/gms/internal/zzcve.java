package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;

final class zzcve extends zzcvf<zzn<Status>> {
    private /* synthetic */ Status zzetp;

    zzcve(zzcvd zzcvd, Status status) {
        this.zzetp = status;
    }

    public final void zzu(zzn<Status> zzn) {
        if (this.zzetp.isSuccess()) {
            zzn.setResult(this.zzetp);
        } else {
            zzn.zzu(this.zzetp);
        }
    }
}
