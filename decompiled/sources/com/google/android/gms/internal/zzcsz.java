package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzcsz extends zzbgl {
    public static final Parcelable.Creator<zzcsz> CREATOR = new zzcta();
    private int quality;
    private String zzjxf;

    private zzcsz() {
    }

    public zzcsz(String str, int i) {
        this.zzjxf = str;
        this.quality = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcsz) {
            zzcsz zzcsz = (zzcsz) obj;
            return zzbg.equal(this.zzjxf, zzcsz.zzjxf) && zzbg.equal(Integer.valueOf(this.quality), Integer.valueOf(zzcsz.quality));
        }
        return false;
    }

    public final int getQuality() {
        return this.quality;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxf, Integer.valueOf(this.quality)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzjxf, false);
        zzbgo.zzc(parcel, 2, this.quality);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzbde() {
        return this.zzjxf;
    }
}
