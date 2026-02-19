package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;

public final class zzd extends zzc implements zza {
    private final zze zzhub;

    public zzd(DataHolder dataHolder, int i, zze zze) {
        super(dataHolder, i);
        this.zzhub = zze;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return zzb.zza(this, obj);
    }

    public final zza freeze() {
        return new zzb(this);
    }

    public final int hashCode() {
        return zzb.zza(this);
    }

    public final String toString() {
        return zzb.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((zzb) ((zza) freeze())).writeToParcel(parcel, i);
    }

    public final String zzavh() {
        return getString(this.zzhub.zzidc);
    }

    public final String zzavi() {
        return getString(this.zzhub.zzidd);
    }

    public final long zzavj() {
        return getLong(this.zzhub.zzide);
    }

    public final Uri zzavk() {
        return zzgk(this.zzhub.zzidf);
    }

    public final Uri zzavl() {
        return zzgk(this.zzhub.zzidg);
    }

    public final Uri zzavm() {
        return zzgk(this.zzhub.zzidh);
    }
}
