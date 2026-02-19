package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

@Deprecated
public final class zzchu extends zzbgl {
    public static final Parcelable.Creator<zzchu> CREATOR = new zzchv();
    private static zzchu zzizc = new zzchu("Home");
    private static zzchu zzizd = new zzchu("Work");
    private final String zzize;

    zzchu(String str) {
        this.zzize = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzchu)) {
            return false;
        }
        return zzbg.equal(this.zzize, ((zzchu) obj).zzize);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzize});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("alias", this.zzize).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzize, false);
        zzbgo.zzai(parcel, zze);
    }
}
