package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzaj extends zzbgl {
    public static final Parcelable.Creator<zzaj> CREATOR = new zzak();
    private int zzitb;
    private int zzitc;
    private long zzitd;
    private long zzite;

    zzaj(int i, int i2, long j, long j2) {
        this.zzitb = i;
        this.zzitc = i2;
        this.zzitd = j;
        this.zzite = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzaj zzaj = (zzaj) obj;
            return this.zzitb == zzaj.zzitb && this.zzitc == zzaj.zzitc && this.zzitd == zzaj.zzitd && this.zzite == zzaj.zzite;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzitc), Integer.valueOf(this.zzitb), Long.valueOf(this.zzite), Long.valueOf(this.zzitd)});
    }

    public final String toString() {
        return "NetworkLocationStatus:" + " Wifi status: " + this.zzitb + " Cell status: " + this.zzitc + " elapsed time NS: " + this.zzite + " system time ms: " + this.zzitd;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzitb);
        zzbgo.zzc(parcel, 2, this.zzitc);
        zzbgo.zza(parcel, 3, this.zzitd);
        zzbgo.zza(parcel, 4, this.zzite);
        zzbgo.zzai(parcel, zze);
    }
}
