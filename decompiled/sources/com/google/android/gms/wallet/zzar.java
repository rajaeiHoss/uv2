package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzar extends zzbgl {
    public static final Parcelable.Creator<zzar> CREATOR = new zzas();
    private String zzloh;

    private zzar() {
    }

    zzar(String str) {
        this.zzloh = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzloh, false);
        zzbgo.zzai(parcel, zze);
    }
}
