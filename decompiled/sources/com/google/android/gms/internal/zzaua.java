package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzaua extends zzbgl {
    public static final Parcelable.Creator<zzaua> CREATOR = new zzaub();
    private String packageName;
    private String zzefj;
    private String zzefk;

    public zzaua(String str, String str2, String str3) {
        this.packageName = str;
        this.zzefj = str2;
        this.zzefk = str3;
    }

    public final String toString() {
        return String.format("DocumentId[packageName=%s, corpusName=%s, uri=%s]", new Object[]{this.packageName, this.zzefj, this.zzefk});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.packageName, false);
        zzbgo.zza(parcel, 2, this.zzefj, false);
        zzbgo.zza(parcel, 3, this.zzefk, false);
        zzbgo.zzai(parcel, zze);
    }
}
