package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzv extends zzbgl {
    public static final Parcelable.Creator<zzv> CREATOR = new zzw();
    private String zzgqy;
    private int zzgqz;
    private String zzgra;
    private String zzgrb;
    private int zzgrc;
    private boolean zzgrd;

    public zzv(String str, int i, String str2, String str3, int i2, boolean z) {
        this.zzgqy = str;
        this.zzgqz = i;
        this.zzgra = str2;
        this.zzgrb = str3;
        this.zzgrc = i2;
        this.zzgrd = z;
    }

    private static boolean zzcr(int i) {
        switch (i) {
            case 256:
            case 257:
            case 258:
                return true;
            default:
                return false;
        }
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (obj == this) {
                return true;
            }
            zzv zzv = (zzv) obj;
            return zzbg.equal(this.zzgqy, zzv.zzgqy) && this.zzgqz == zzv.zzgqz && this.zzgrc == zzv.zzgrc && this.zzgrd == zzv.zzgrd;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgqy, Integer.valueOf(this.zzgqz), Integer.valueOf(this.zzgrc), Boolean.valueOf(this.zzgrd)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        boolean z = false;
        zzbgo.zza(parcel, 2, !zzcr(this.zzgqz) ? null : this.zzgqy, false);
        int i2 = -1;
        zzbgo.zzc(parcel, 3, !zzcr(this.zzgqz) ? -1 : this.zzgqz);
        zzbgo.zza(parcel, 4, this.zzgra, false);
        zzbgo.zza(parcel, 5, this.zzgrb, false);
        int i3 = this.zzgrc;
        if (i3 == 0 || i3 == 1 || i3 == 2 || i3 == 3) {
            z = true;
        }
        if (z) {
            i2 = i3;
        }
        zzbgo.zzc(parcel, 6, i2);
        zzbgo.zza(parcel, 7, this.zzgrd);
        zzbgo.zzai(parcel, zze);
    }
}
