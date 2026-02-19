package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzbjn extends zzbgl {
    public static final Parcelable.Creator<zzbjn> CREATOR = new zzbjo();
    private final Bundle zzgms;

    public zzbjn(Bundle bundle) {
        this.zzgms = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgms, false);
        zzbgo.zzai(parcel, zze);
    }

    public final Bundle zzaor() {
        return this.zzgms;
    }
}
