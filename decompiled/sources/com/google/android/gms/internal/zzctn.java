package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzctn extends zzbgl {
    public static final Parcelable.Creator<zzctn> CREATOR = new zzcto();
    private String zzjzi;

    private zzctn() {
    }

    public zzctn(String str) {
        this.zzjzi = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzctn) {
            return zzbg.equal(this.zzjzi, ((zzctn) obj).zzjzi);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzjzi});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzjzi, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzbdi() {
        return this.zzjzi;
    }
}
