package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzeel;
import com.google.android.gms.internal.zzeet;
import java.util.ArrayList;
import java.util.List;

final class zza extends zzbgl {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();
    private List<String> zzmzh;
    private List<String> zzmzi;

    public zza(List<String> list, List<String> list2) {
        this.zzmzh = list;
        this.zzmzi = list2;
    }

    public static zzeel zza(zza zza) {
        ArrayList arrayList = new ArrayList(zza.zzmzh.size());
        for (String zzps : zza.zzmzh) {
            arrayList.add(zzeet.zzps(zzps));
        }
        return new zzeel(arrayList, zza.zzmzi);
    }

    public static zza zza(zzeel zzeel) {
        List<List<String>> zzbwi = zzeel.zzbwi();
        ArrayList arrayList = new ArrayList(zzbwi.size());
        for (List<String> zzau : zzbwi) {
            arrayList.add(zzeet.zzau(zzau));
        }
        return new zza(arrayList, zzeel.zzbwj());
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzb(parcel, 2, this.zzmzh, false);
        zzbgo.zzb(parcel, 3, this.zzmzi, false);
        zzbgo.zzai(parcel, zze);
    }
}
