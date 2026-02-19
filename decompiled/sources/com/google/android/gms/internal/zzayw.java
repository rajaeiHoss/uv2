package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.internal.zzai;
import java.util.ArrayList;
import java.util.List;

public final class zzayw extends zzbgl {
    public static final Parcelable.Creator<zzayw> CREATOR = new zzayy();
    private final ArrayList<zzai> zzerf;

    zzayw(ArrayList<zzai> arrayList) {
        this.zzerf = arrayList;
    }

    public final List<PlaceLikelihood> getPlaceLikelihoods() {
        return new ArrayList<PlaceLikelihood>(this.zzerf);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzerf, false);
        zzbgo.zzai(parcel, zze);
    }
}
