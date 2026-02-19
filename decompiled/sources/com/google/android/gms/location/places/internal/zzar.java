package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import java.util.Arrays;

public final class zzar implements PlacePhotoMetadata {
    private final int mIndex;
    private final int zzcmb;
    private final int zzcmc;
    private final String zziyv;
    private final CharSequence zziyw;

    public zzar(String str, int i, int i2, CharSequence charSequence, int i3) {
        this.zziyv = str;
        this.zzcmb = i;
        this.zzcmc = i2;
        this.zziyw = charSequence;
        this.mIndex = i3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzar)) {
            return false;
        }
        zzar zzar = (zzar) obj;
        return zzar.zzcmb == this.zzcmb && zzar.zzcmc == this.zzcmc && zzbg.equal(zzar.zziyv, this.zziyv) && zzbg.equal(zzar.zziyw, this.zziyw);
    }

    public final /* bridge */ /* synthetic */ PlacePhotoMetadata freeze() {
        return this;
    }

    public final CharSequence getAttributions() {
        return this.zziyw;
    }

    public final int getIndex() {
        return this.mIndex;
    }

    public final int getMaxHeight() {
        return this.zzcmc;
    }

    public final int getMaxWidth() {
        return this.zzcmb;
    }

    public final PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient googleApiClient) {
        return getScaledPhoto(googleApiClient, getMaxWidth(), getMaxHeight());
    }

    public final PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient googleApiClient, int i, int i2) {
        return ((zzh) Places.GeoDataApi).zza(googleApiClient, this, i, i2);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzcmb), Integer.valueOf(this.zzcmc), this.zziyv, this.zziyw});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String zzaxl() {
        return this.zziyv;
    }
}
