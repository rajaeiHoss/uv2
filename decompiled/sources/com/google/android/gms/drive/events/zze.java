package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class zze extends zzbgl {
    public static final Parcelable.Creator<zze> CREATOR = new zzf();
    private int zzehz;
    private int zzgro;
    private boolean zzgrp;
    private List<DriveSpace> zzgrq;
    private final Set<DriveSpace> zzgrr;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    zze(int i, int i2, boolean z, List<DriveSpace> list) {
        this(i, i2, z, list, list == null ? null : new HashSet(list));
    }

    private zze(int i, int i2, boolean z, List<DriveSpace> list, Set<DriveSpace> set) {
        this.zzehz = i;
        this.zzgro = i2;
        this.zzgrp = z;
        this.zzgrq = list;
        this.zzgrr = set;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (obj == this) {
                return true;
            }
            zze zze = (zze) obj;
            return zzbg.equal(this.zzgrr, zze.zzgrr) && this.zzgro == zze.zzgro && this.zzgrp == zze.zzgrp;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgrr, Integer.valueOf(this.zzgro), Boolean.valueOf(this.zzgrp)});
    }

    public final String toString() {
        return String.format(Locale.US, "ChangesAvailableOptions[ChangesSizeLimit=%d, Repeats=%s, Spaces=%s]", new Object[]{Integer.valueOf(this.zzgro), Boolean.valueOf(this.zzgrp), this.zzgrq});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zzc(parcel, 2, this.zzgro);
        zzbgo.zza(parcel, 3, this.zzgrp);
        zzbgo.zzc(parcel, 4, this.zzgrq, false);
        zzbgo.zzai(parcel, zze);
    }
}
