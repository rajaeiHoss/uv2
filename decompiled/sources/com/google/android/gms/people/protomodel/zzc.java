package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class zzc extends zzbgl implements zza {
    public static final Parcelable.Creator<zzc> CREATOR = new zzb();
    private final String zzetq;
    private final List<zzh> zzkfi;
    private final String zzkfj;
    private final Long zzkfk;
    private final Long zzkfl;
    private List<zzf> zzkfm;

    public zzc(String str, List<zzh> list, String str2, Long l, Long l2) {
        this.zzetq = str;
        this.zzkfi = list;
        this.zzkfj = str2;
        this.zzkfk = l;
        this.zzkfl = l2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zza)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zza zza = (zza) obj;
        return zzbg.equal(getDeviceId(), zza.getDeviceId()) && zzbg.equal(zzbdy(), zza.zzbdy()) && zzbg.equal(zzbdz(), zza.zzbdz()) && zzbg.equal(zzbea(), zza.zzbea()) && zzbg.equal(zzbeb(), zza.zzbeb());
    }

    public final zza freeze() {
        return this;
    }

    public final String getDeviceId() {
        return this.zzetq;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{getDeviceId(), zzbdy(), zzbdz(), zzbea(), zzbeb()});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzetq, false);
        zzbgo.zzc(parcel, 3, zzbdy(), false);
        zzbgo.zza(parcel, 4, this.zzkfj, false);
        zzbgo.zza(parcel, 5, this.zzkfk, false);
        zzbgo.zza(parcel, 6, this.zzkfl, false);
        zzbgo.zzai(parcel, zze);
    }

    public final List<zzf> zzbdy() {
        if (this.zzkfm == null && this.zzkfi != null) {
            this.zzkfm = new ArrayList(this.zzkfi.size());
            for (zzh add : this.zzkfi) {
                this.zzkfm.add(add);
            }
        }
        return this.zzkfm;
    }

    public final String zzbdz() {
        return this.zzkfj;
    }

    public final Long zzbea() {
        return this.zzkfk;
    }

    public final Long zzbeb() {
        return this.zzkfl;
    }
}
