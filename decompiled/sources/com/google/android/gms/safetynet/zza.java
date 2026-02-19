package com.google.android.gms.safetynet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zza extends zzbgl {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();
    private final String zzkjw;

    public zza(String str) {
        this.zzkjw = str;
    }

    public final String getJwsResult() {
        return this.zzkjw;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzkjw, false);
        zzbgo.zzai(parcel, zze);
    }
}
