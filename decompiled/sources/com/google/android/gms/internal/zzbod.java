package com.google.android.gms.internal;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbod implements ResultCallback<Status> {
    zzbod(zzboa zzboa) {
    }

    public final void onResult(Status status) {
        if (!status.isSuccess()) {
            zzboa.zzgpv.zzw("DriveContentsImpl", "Error discarding contents");
        } else {
            zzboa.zzgpv.zzu("DriveContentsImpl", "Contents discarded");
        }
    }
}
