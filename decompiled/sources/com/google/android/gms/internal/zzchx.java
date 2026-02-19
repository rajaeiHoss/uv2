package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.places.PlacesStatusCodes;

@Deprecated
public final class zzchx extends zzd<zzchw> implements Result {
    private final Status mStatus;

    public zzchx(DataHolder dataHolder) {
        this(dataHolder, PlacesStatusCodes.zzcm(dataHolder.getStatusCode()));
    }

    private zzchx(DataHolder dataHolder, Status status) {
        super(dataHolder, zzchw.CREATOR);
        zzbq.checkArgument(dataHolder == null || dataHolder.getStatusCode() == status.getStatusCode());
        this.mStatus = status;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
