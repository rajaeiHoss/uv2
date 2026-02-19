package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzh extends zzbgl implements zzf {
    public static final Parcelable.Creator<zzh> CREATOR = new zzg();
    private final String zzdwr;
    private final Integer zzkfp;

    public zzh(String str, Integer num) {
        this.zzdwr = str;
        this.zzkfp = num;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzf)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzf zzf = (zzf) obj;
        return zzbg.equal(getSource(), zzf.getSource()) && zzbg.equal(zzbed(), zzf.zzbed());
    }

    public final zzf freeze() {
        return this;
    }

    public final String getSource() {
        return this.zzdwr;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{getSource(), zzbed()});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzdwr, false);
        zzbgo.zza(parcel, 3, this.zzkfp, false);
        zzbgo.zzai(parcel, zze);
    }

    public final Integer zzbed() {
        return this.zzkfp;
    }
}
