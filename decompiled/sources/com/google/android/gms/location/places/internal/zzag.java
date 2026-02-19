package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Collections;
import java.util.List;

public final class zzag extends zzbgl {
    public static final Parcelable.Creator<zzag> CREATOR = new zzah();
    private final List<Integer> zziuz;
    private final String zziva;
    private final Uri zzivb;
    private final float zzixt;
    private final int zzixu;

    zzag(List<Integer> list, String str, Uri uri, float f, int i) {
        this.zziuz = Collections.unmodifiableList(list);
        this.zziva = str;
        this.zzivb = uri;
        this.zzixt = f;
        this.zzixu = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zziuz, false);
        zzbgo.zza(parcel, 2, this.zziva, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzivb, i, false);
        zzbgo.zza(parcel, 4, this.zzixt);
        zzbgo.zzc(parcel, 5, this.zzixu);
        zzbgo.zzai(parcel, zze);
    }
}
