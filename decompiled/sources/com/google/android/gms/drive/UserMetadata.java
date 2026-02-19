package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class UserMetadata extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<UserMetadata> CREATOR = new zzx();
    private String zzemi;
    private String zzgrh;
    private String zzgri;
    private boolean zzgrj;
    private String zzgrk;

    public UserMetadata(String str, String str2, String str3, boolean z, String str4) {
        this.zzgrh = str;
        this.zzemi = str2;
        this.zzgri = str3;
        this.zzgrj = z;
        this.zzgrk = str4;
    }

    public String toString() {
        return String.format("Permission ID: '%s', Display Name: '%s', Picture URL: '%s', Authenticated User: %b, Email: '%s'", new Object[]{this.zzgrh, this.zzemi, this.zzgri, Boolean.valueOf(this.zzgrj), this.zzgrk});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgrh, false);
        zzbgo.zza(parcel, 3, this.zzemi, false);
        zzbgo.zza(parcel, 4, this.zzgri, false);
        zzbgo.zza(parcel, 5, this.zzgrj);
        zzbgo.zza(parcel, 6, this.zzgrk, false);
        zzbgo.zzai(parcel, zze);
    }
}
