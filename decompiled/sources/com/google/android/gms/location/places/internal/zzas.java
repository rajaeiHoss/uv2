package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;

public final class zzas extends zzaw implements PlacePhotoMetadata {
    private final String zziyv = getString("photo_fife_url");

    public zzas(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public final /* synthetic */ PlacePhotoMetadata freeze() {
        return new zzar(this.zziyv, getMaxWidth(), getMaxHeight(), getAttributions(), this.zzgch);
    }

    public final CharSequence getAttributions() {
        return zzad("photo_attributions", (String) null);
    }

    public final int getMaxHeight() {
        return zzy("photo_max_height", 0);
    }

    public final int getMaxWidth() {
        return zzy("photo_max_width", 0);
    }

    public final PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient googleApiClient) {
        return getScaledPhoto(googleApiClient, getMaxWidth(), getMaxHeight());
    }

    public final PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient googleApiClient, int i, int i2) {
        return ((PlacePhotoMetadata) freeze()).getScaledPhoto(googleApiClient, i, i2);
    }
}
