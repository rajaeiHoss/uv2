package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzat;

public class PlaceBuffer extends AbstractDataBuffer<Place> implements Result {
    private final Status mStatus;
    private final String zzivi;

    public PlaceBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.mStatus = PlacesStatusCodes.zzcm(dataHolder.getStatusCode());
        this.zzivi = (dataHolder == null || dataHolder.zzahs() == null) ? null : dataHolder.zzahs().getString("com.google.android.gms.location.places.PlaceBuffer.ATTRIBUTIONS_EXTRA_KEY");
    }

    public Place get(int i) {
        return new zzat(this.zzfxb, i);
    }

    public CharSequence getAttributions() {
        return this.zzivi;
    }

    public Status getStatus() {
        return this.mStatus;
    }
}
