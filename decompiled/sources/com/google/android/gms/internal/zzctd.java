package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzctd extends zzbgl {
    public static final Parcelable.Creator<zzctd> CREATOR = new zzcte();
    private String zzjxf;
    private byte[] zzjxg;
    private String zzjzh;

    private zzctd() {
    }

    public zzctd(String str, String str2, byte[] bArr) {
        this.zzjxf = str;
        this.zzjzh = str2;
        this.zzjxg = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzctd) {
            zzctd zzctd = (zzctd) obj;
            return zzbg.equal(this.zzjxf, zzctd.zzjxf) && zzbg.equal(this.zzjzh, zzctd.zzjzh) && Arrays.equals(this.zzjxg, zzctd.zzjxg);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxf, this.zzjzh, Integer.valueOf(Arrays.hashCode(this.zzjxg))});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzjxf, false);
        zzbgo.zza(parcel, 2, this.zzjzh, false);
        zzbgo.zza(parcel, 3, this.zzjxg, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzbde() {
        return this.zzjxf;
    }

    public final String zzbdf() {
        return this.zzjzh;
    }

    public final byte[] zzbdh() {
        return this.zzjxg;
    }
}
