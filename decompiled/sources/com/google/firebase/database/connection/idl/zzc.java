package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzeeu;
import com.google.android.gms.internal.zzemo;
import java.io.File;
import java.util.List;

public final class zzc extends zzbgl {
    public static final Parcelable.Creator<zzc> CREATOR = new zze();
    final boolean zzmzx;
    final String zzmzz;
    final String zznaa;
    final zzi zzncp;
    final int zzncq;
    final List<String> zzncr;
    final String zzncs;

    public zzc(zzeeu zzeeu, zzemo zzemo, List<String> list, boolean z, String str, String str2, File file) {
        int i = zzd.zznct[zzemo.ordinal()];
        int i2 = 4;
        if (i == 1) {
            i2 = 1;
        } else if (i == 2) {
            i2 = 2;
        } else if (i == 3) {
            i2 = 3;
        } else if (i != 4) {
            i2 = 0;
        }
        this.zzncp = new zzi(zzeeu.getHost(), zzeeu.getNamespace(), zzeeu.isSecure());
        this.zzncq = i2;
        this.zzncr = null;
        this.zzmzx = z;
        this.zzncs = str;
        this.zzmzz = str2;
        this.zznaa = file.getAbsolutePath();
    }

    public zzc(zzi zzi, int i, List<String> list, boolean z, String str, String str2, String str3) {
        this.zzncp = zzi;
        this.zzncq = i;
        this.zzncr = list;
        this.zzmzx = z;
        this.zzncs = str;
        this.zzmzz = str2;
        this.zznaa = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzncp, i, false);
        zzbgo.zzc(parcel, 3, this.zzncq);
        zzbgo.zzb(parcel, 4, this.zzncr, false);
        zzbgo.zza(parcel, 5, this.zzmzx);
        zzbgo.zza(parcel, 6, this.zzncs, false);
        zzbgo.zza(parcel, 7, this.zzmzz, false);
        zzbgo.zza(parcel, 8, this.zznaa, false);
        zzbgo.zzai(parcel, zze);
    }
}
