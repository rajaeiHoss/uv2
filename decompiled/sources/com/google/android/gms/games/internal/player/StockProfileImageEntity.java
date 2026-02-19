package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class StockProfileImageEntity extends zzc implements StockProfileImage {
    public static final Parcelable.Creator<StockProfileImageEntity> CREATOR = new zzf();
    private final String zzesp;
    private final Uri zzidr;

    public StockProfileImageEntity(String str, Uri uri) {
        this.zzesp = str;
        this.zzidr = uri;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof StockProfileImage)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        StockProfileImage stockProfileImage = (StockProfileImage) obj;
        return zzbg.equal(this.zzesp, stockProfileImage.getImageUrl()) && zzbg.equal(this.zzidr, stockProfileImage.zzavn());
    }

    public final StockProfileImage freeze() {
        return this;
    }

    public final String getImageUrl() {
        return this.zzesp;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzesp, this.zzidr});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("ImageId", this.zzesp).zzg("ImageUri", this.zzidr).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getImageUrl(), false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzidr, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final Uri zzavn() {
        return this.zzidr;
    }
}
