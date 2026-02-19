package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbhj extends zzbgl {
    public static final Parcelable.Creator<zzbhj> CREATOR = new zzbhk();
    private int zzehz;
    private final zzbhl zzgii;

    zzbhj(int i, zzbhl zzbhl) {
        this.zzehz = i;
        this.zzgii = zzbhl;
    }

    private zzbhj(zzbhl zzbhl) {
        this.zzehz = 1;
        this.zzgii = zzbhl;
    }

    public static zzbhj zza(zzbhr<?, ?> zzbhr) {
        if (zzbhr instanceof zzbhl) {
            return new zzbhj((zzbhl) zzbhr);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgii, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final zzbhr<?, ?> zzand() {
        zzbhl zzbhl = this.zzgii;
        if (zzbhl != null) {
            return zzbhl;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
}
