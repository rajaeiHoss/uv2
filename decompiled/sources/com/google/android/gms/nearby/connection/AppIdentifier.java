package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

@Deprecated
public final class AppIdentifier extends zzbgl {
    public static final Parcelable.Creator<AppIdentifier> CREATOR = new zzc();
    private final String zzjwc;

    public AppIdentifier(String str) {
        this.zzjwc = zzbq.zzh(str, "Missing application identifier value");
    }

    public final String getIdentifier() {
        return this.zzjwc;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getIdentifier(), false);
        zzbgo.zzai(parcel, zze);
    }
}
