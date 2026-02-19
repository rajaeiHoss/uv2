package com.google.android.gms.instantapps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

@Deprecated
public final class zzi extends zzbgl {
    public static final Parcelable.Creator<zzi> CREATOR = new zzj();

    public final void writeToParcel(Parcel parcel, int i) {
        zzbgo.zzai(parcel, zzbgo.zze(parcel));
    }
}
