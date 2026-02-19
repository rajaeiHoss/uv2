package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.location.places.internal.zzd;

public class AutocompletePredictionBuffer extends AbstractDataBuffer<AutocompletePrediction> implements Result {
    public AutocompletePredictionBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    public AutocompletePrediction get(int i) {
        return new zzd(this.zzfxb, i);
    }

    public Status getStatus() {
        return PlacesStatusCodes.zzcm(this.zzfxb.getStatusCode());
    }

    public String toString() {
        return zzbg.zzx(this).zzg("status", getStatus()).toString();
    }
}
