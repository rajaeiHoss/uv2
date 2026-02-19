package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public final class zzchl extends zzbgl {
    public static final Parcelable.Creator<zzchl> CREATOR = new zzchm();
    static final List<zzcfs> zzitm = Collections.emptyList();
    private String mTag;
    private String zzeqs;
    private List<zzcfs> zzira;
    private LocationRequest zzium;
    private boolean zziun;
    private boolean zziuo;
    private boolean zziup;
    private boolean zziuq = true;

    zzchl(LocationRequest locationRequest, List<zzcfs> list, String str, boolean z, boolean z2, boolean z3, String str2) {
        this.zzium = locationRequest;
        this.zzira = list;
        this.mTag = str;
        this.zziun = z;
        this.zziuo = z2;
        this.zziup = z3;
        this.zzeqs = str2;
    }

    @Deprecated
    public static zzchl zza(LocationRequest locationRequest) {
        return new zzchl(locationRequest, zzitm, (String) null, false, false, false, (String) null);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzchl)) {
            return false;
        }
        zzchl zzchl = (zzchl) obj;
        return zzbg.equal(this.zzium, zzchl.zzium) && zzbg.equal(this.zzira, zzchl.zzira) && zzbg.equal(this.mTag, zzchl.mTag) && this.zziun == zzchl.zziun && this.zziuo == zzchl.zziuo && this.zziup == zzchl.zziup && zzbg.equal(this.zzeqs, zzchl.zzeqs);
    }

    public final int hashCode() {
        return this.zzium.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.zzium.toString());
        if (this.mTag != null) {
            sb.append(" tag=");
            sb.append(this.mTag);
        }
        if (this.zzeqs != null) {
            sb.append(" moduleId=");
            sb.append(this.zzeqs);
        }
        sb.append(" hideAppOps=");
        sb.append(this.zziun);
        sb.append(" clients=");
        sb.append(this.zzira);
        sb.append(" forceCoarseLocation=");
        sb.append(this.zziuo);
        if (this.zziup) {
            sb.append(" exemptFromBackgroundThrottle");
        }
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzium, i, false);
        zzbgo.zzc(parcel, 5, this.zzira, false);
        zzbgo.zza(parcel, 6, this.mTag, false);
        zzbgo.zza(parcel, 7, this.zziun);
        zzbgo.zza(parcel, 8, this.zziuo);
        zzbgo.zza(parcel, 9, this.zziup);
        zzbgo.zza(parcel, 10, this.zzeqs, false);
        zzbgo.zzai(parcel, zze);
    }
}
