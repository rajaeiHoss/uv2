package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class zzbdd extends zzbgl {
    public static final Parcelable.Creator<zzbdd> CREATOR = new zzbde();
    private String zzflu;

    public zzbdd() {
        this((String) null);
    }

    zzbdd(String str) {
        this.zzflu = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbdd)) {
            return false;
        }
        return zzbdw.zza(this.zzflu, ((zzbdd) obj).zzflu);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzflu});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzflu, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzagl() {
        return this.zzflu;
    }
}
