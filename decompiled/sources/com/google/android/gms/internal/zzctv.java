package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzctv extends zzbgl {
    public static final Parcelable.Creator<zzctv> CREATOR = new zzctw();
    private int zzjzm;

    private zzctv() {
    }

    public zzctv(int i) {
        this.zzjzm = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzctv) {
            return zzbg.equal(Integer.valueOf(this.zzjzm), Integer.valueOf(((zzctv) obj).zzjzm));
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzjzm)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzjzm);
        zzbgo.zzai(parcel, zze);
    }
}
