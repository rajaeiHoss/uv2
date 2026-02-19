package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzx extends zzbgl {
    public static final Parcelable.Creator<zzx> CREATOR = new zzy();
    public static final zzx zzhcb = new zzx("=");
    public static final zzx zzhcc = new zzx("<");
    public static final zzx zzhcd = new zzx("<=");
    public static final zzx zzhce = new zzx(">");
    public static final zzx zzhcf = new zzx(">=");
    public static final zzx zzhcg = new zzx("and");
    public static final zzx zzhch = new zzx("or");
    private static zzx zzhci = new zzx("not");
    public static final zzx zzhcj = new zzx("contains");
    private String mTag;

    zzx(String str) {
        this.mTag = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.mTag;
        String str2 = ((zzx) obj).mTag;
        if (str == null) {
            if (str2 != null) {
                return false;
            }
        } else if (!str.equals(str2)) {
            return false;
        }
        return true;
    }

    public final String getTag() {
        return this.mTag;
    }

    public final int hashCode() {
        String str = this.mTag;
        return (str == null ? 0 : str.hashCode()) + 31;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.mTag, false);
        zzbgo.zzai(parcel, zze);
    }
}
