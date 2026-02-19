package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzblr extends zzbgl {
    public static final Parcelable.Creator<zzblr> CREATOR = new zzbls();
    private ArrayList<zzbjp> zzgox;

    public zzblr() {
    }

    zzblr(ArrayList<zzbjp> arrayList) {
        this.zzgox = arrayList;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 3, this.zzgox, false);
        zzbgo.zzai(parcel, zze);
    }
}
