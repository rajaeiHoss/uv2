package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class zzcfi extends zzbgl {
    public static final Parcelable.Creator<zzcfi> CREATOR = new zzcfj();
    private String packageName;
    private BitmapTeleporter zziqm;
    private String zziqn;
    private String zziqo;
    private String zziqp;
    private ArrayList<String> zziqq;
    private zzcea zziqr;

    public zzcfi(BitmapTeleporter bitmapTeleporter, String str, String str2, String str3, String str4, Collection<String> collection, zzcea zzcea) {
        this.zziqm = bitmapTeleporter;
        this.zziqn = str;
        this.zziqo = str2;
        this.zziqp = str3;
        this.packageName = str4;
        ArrayList<String> arrayList = new ArrayList<>(collection == null ? 0 : collection.size());
        this.zziqq = arrayList;
        if (collection != null) {
            arrayList.addAll(collection);
        }
        this.zziqr = zzcea;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zziqm, i, false);
        zzbgo.zza(parcel, 3, this.zziqn, false);
        zzbgo.zza(parcel, 4, this.zziqo, false);
        zzbgo.zza(parcel, 5, this.zziqp, false);
        zzbgo.zza(parcel, 6, this.packageName, false);
        zzbgo.zzb(parcel, 7, Collections.unmodifiableList(this.zziqq), false);
        zzbgo.zza(parcel, 8, (Parcelable) this.zziqr, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
