package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzeeu;

final class zzi extends zzbgl {
    public static final Parcelable.Creator<zzi> CREATOR = new zzj();
    private String host;
    private boolean secure;
    private String zzkal;

    public zzi(String str, String str2, boolean z) {
        this.host = str;
        this.zzkal = str2;
        this.secure = z;
    }

    public static zzeeu zza(zzi zzi) {
        return new zzeeu(zzi.host, zzi.zzkal, zzi.secure);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.host, false);
        zzbgo.zza(parcel, 3, this.zzkal, false);
        zzbgo.zza(parcel, 4, this.secure);
        zzbgo.zzai(parcel, zze);
    }
}
