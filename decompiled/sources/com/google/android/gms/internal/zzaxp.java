package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.Credential;

public final class zzaxp extends zzbgl {
    public static final Parcelable.Creator<zzaxp> CREATOR = new zzaxq();
    private final Credential zzelr;

    public zzaxp(Credential credential) {
        this.zzelr = credential;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzelr, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
