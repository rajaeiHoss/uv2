package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzg;
import com.google.android.gms.wearable.internal.zzdf;

public class DataItemBuffer extends zzg<DataItem> implements Result {
    private final Status mStatus;

    public DataItemBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.mStatus = new Status(dataHolder.getStatusCode());
    }

    public Status getStatus() {
        return this.mStatus;
    }

    /* access modifiers changed from: protected */
    public final String zzalj() {
        return "path";
    }

    /* access modifiers changed from: protected */
    public final DataItem zzl(int i, int i2) {
        return new zzdf(this.zzfxb, i, i2);
    }
}
