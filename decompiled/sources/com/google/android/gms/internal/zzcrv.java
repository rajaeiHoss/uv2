package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzcrv extends zzbgl {
    public static final Parcelable.Creator<zzcrv> CREATOR = new zzcrw();
    private String zzjxf;

    private zzcrv() {
    }

    public zzcrv(String str) {
        this.zzjxf = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzcrv) {
            return zzbg.equal(this.zzjxf, ((zzcrv) obj).zzjxf);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjxf});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzjxf, false);
        zzbgo.zzai(parcel, zze);
    }
}
