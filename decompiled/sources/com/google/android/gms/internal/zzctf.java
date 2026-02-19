package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzctf extends zzbgl {
    public static final Parcelable.Creator<zzctf> CREATOR = new zzctg();
    private int statusCode;
    private String zzjxf;
    private byte[] zzjxg;

    private zzctf() {
    }

    public zzctf(String str, int i, byte[] bArr) {
        this.zzjxf = str;
        this.statusCode = i;
        this.zzjxg = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzctf) {
            zzctf zzctf = (zzctf) obj;
            return zzbg.equal(this.zzjxf, zzctf.zzjxf) && zzbg.equal(Integer.valueOf(this.statusCode), Integer.valueOf(zzctf.statusCode)) && Arrays.equals(this.zzjxg, zzctf.zzjxg);
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

    public final byte[] zzbdh() {
        return this.zzjxg;
    }
}
