package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;
import java.util.List;

@Deprecated
public final class zzchw extends zzbgl {
    public static final Parcelable.Creator<zzchw> CREATOR = new zzchy();
    private final String zzehk;
    private final String zzivz;
    private final List<zzchu> zzizf;

    zzchw(String str, String str2, List<zzchu> list) {
        this.zzehk = str;
        this.zzivz = str2;
        this.zzizf = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzchw)) {
            return false;
        }
        zzchw zzchw = (zzchw) obj;
        return this.zzehk.equals(zzchw.zzehk) && this.zzivz.equals(zzchw.zzivz) && this.zzizf.equals(zzchw.zzizf);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzehk, this.zzivz, this.zzizf});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("accountName", this.zzehk).zzg("placeId", this.zzivz).zzg("placeAliases", this.zzizf).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzehk, false);
        zzbgo.zza(parcel, 2, this.zzivz, false);
        zzbgo.zzc(parcel, 6, this.zzizf, false);
        zzbgo.zzai(parcel, zze);
    }
}
