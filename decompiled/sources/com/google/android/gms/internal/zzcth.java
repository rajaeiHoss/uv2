package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzcth extends zzbgl {
    public static final Parcelable.Creator<zzcth> CREATOR = new zzcti();
    private int statusCode;
    private String zzjxf;
    private byte[] zzjxg;

    private zzcth() {
    }

    public zzcth(String str, int i, byte[] bArr) {
        this.zzjxf = str;
        this.statusCode = i;
        this.zzjxg = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcth) {
            zzcth zzcth = (zzcth) obj;
            return zzbg.equal(this.zzjxf, zzcth.zzjxf) && zzbg.equal(Integer.valueOf(this.statusCode), Integer.valueOf(zzcth.statusCode)) && Arrays.equals(this.zzjxg, zzcth.zzjxg);
        }
        return false;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxf, Integer.valueOf(this.statusCode), Integer.valueOf(Arrays.hashCode(this.zzjxg))});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzjxf, false);
        zzbgo.zzc(parcel, 2, this.statusCode);
        zzbgo.zza(parcel, 3, this.zzjxg, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzbde() {
        return this.zzjxf;
    }
}
