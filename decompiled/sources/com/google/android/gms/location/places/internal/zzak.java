package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

public final class zzak extends zzaw implements PlaceLikelihood {
    public zzak(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public final /* synthetic */ PlaceLikelihood freeze() {
        return new zzai((PlaceEntity) zzbq.checkNotNull((PlaceEntity) getPlace().freeze()), getLikelihood());
    }

    public final float getLikelihood() {
        return zza("place_likelihood", -1.0f);
    }

    public final Place getPlace() {
        return new zzat(this.zzfxb, this.zzgch);
    }
}
