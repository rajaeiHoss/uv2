package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.HeadphoneStateResult;
import com.google.android.gms.awareness.state.HeadphoneState;
import com.google.android.gms.common.api.Status;

final class zzazm implements HeadphoneStateResult {
    private /* synthetic */ zzazy zzert;

    zzazm(zzazl zzazl, zzazy zzazy) {
        this.zzert = zzazy;
    }

    public final HeadphoneState getHeadphoneState() {
        if (this.zzert.zzadl() == null) {
            return null;
        }
        return this.zzert.zzadl().zzadh();
    }

    public final Status getStatus() {
        return this.zzert.getStatus();
    }
}
