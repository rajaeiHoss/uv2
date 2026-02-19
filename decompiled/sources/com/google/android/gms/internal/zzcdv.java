package com.google.android.gms.internal;

import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import java.util.List;

public final class zzcdv extends zzbgl {
    public static final Parcelable.Creator<zzcdv> CREATOR = new zzcdw();
    private final String packageName;
    private final String title;
    private final PackageInfo zzcrw;
    @Deprecated
    private BitmapTeleporter zziok;
    private final List<zzcfd> zziol;
    private final List<zzcdx> zziom;
    private final int zzion;
    private final byte[] zzioo;
    private final List<zzcff> zziop;
    private final byte[] zzioq;
    @Deprecated
    private final Bitmap zzior;

    zzcdv(String str, String str2, BitmapTeleporter bitmapTeleporter, List<zzcfd> list, List<zzcdx> list2, List<zzcff> list3, int i, byte[] bArr, PackageInfo packageInfo, byte[] bArr2) {
        this.packageName = str;
        this.title = str2;
        this.zziok = bitmapTeleporter;
        this.zziol = list;
        this.zziom = list2;
        this.zzion = i;
        this.zzioo = bArr;
        this.zzcrw = packageInfo;
        this.zziop = list3;
        this.zzioq = bArr2;
        this.zzior = bitmapTeleporter == null ? null : bitmapTeleporter.zzalf();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.packageName, false);
        zzbgo.zza(parcel, 3, this.title, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zziok, i, false);
        zzbgo.zzc(parcel, 5, this.zziol, false);
        zzbgo.zzc(parcel, 6, this.zziom, false);
        zzbgo.zzc(parcel, 7, this.zzion);
        zzbgo.zza(parcel, 8, this.zzioo, false);
        zzbgo.zza(parcel, 9, (Parcelable) this.zzcrw, i, false);
        zzbgo.zzc(parcel, 11, this.zziop, false);
        zzbgo.zza(parcel, 12, this.zzioq, false);
        zzbgo.zzai(parcel, zze);
    }
}
