package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzctt extends zzbgl {
    public static final Parcelable.Creator<zzctt> CREATOR = new zzctu();
    private int statusCode;
    private String zzjyd;

    private zzctt() {
    }

    public zzctt(int i, String str) {
        this.statusCode = i;
        this.zzjyd = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzctt) {
            zzctt zzctt = (zzctt) obj;
            return zzbg.equal(Integer.valueOf(this.statusCode), Integer.valueOf(zzctt.statusCode)) && zzbg.equal(this.zzjyd, zzctt.zzjyd);
        }
        return false;
    }

    public final String getLocalEndpointName() {
        return this.zzjyd;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.statusCode), this.zzjyd});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.statusCode);
        zzbgo.zza(parcel, 2, this.zzjyd, false);
        zzbgo.zzai(parcel, zze);
    }
}
