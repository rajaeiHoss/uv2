package com.google.android.gms.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.internal.zzp;

final class zzad implements zzp {
    zzad() {
    }

    public final boolean zzai(Status status) {
        return status.isSuccess() || status.getStatusCode() == 5;
    }
}
