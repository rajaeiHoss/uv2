package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbjb extends zzbgl {
    public static final Parcelable.Creator<zzbjb> CREATOR = new zzbjc();
    private final byte[] zzgmj;

    public zzbjb(byte[] bArr) {
        this.zzgmj = bArr;
    }

    public final byte[] getPayload() {
        return this.zzgmj;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgmj, false);
        zzbgo.zzai(parcel, zze);
    }
}
