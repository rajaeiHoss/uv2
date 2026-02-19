package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import java.util.Arrays;

public final class zzai extends zzbgl implements PlaceLikelihood {
    public static final Parcelable.Creator<zzai> CREATOR = new zzaj();
    private PlaceEntity zziyf;
    private float zziyg;

    zzai(PlaceEntity placeEntity, float f) {
        this.zziyf = placeEntity;
        this.zziyg = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzai)) {
            return false;
        }
        zzai zzai = (zzai) obj;
        return this.zziyf.equals(zzai.zziyf) && this.zziyg == zzai.zziyg;
    }

    public final /* bridge */ /* synthetic */ PlaceLikelihood freeze() {
        return this;
    }

    public final float getLikelihood() {
        return this.zziyg;
    }

    public final Place getPlace() {
        return this.zziyf;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zziyf, Float.valueOf(this.zziyg)});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("place", this.zziyf).zzg("likelihood", Float.valueOf(this.zziyg)).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zziyf, i, false);
        zzbgo.zza(parcel, 2, this.zziyg);
        zzbgo.zzai(parcel, zze);
    }
}
