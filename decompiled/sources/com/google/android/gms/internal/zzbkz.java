package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.awareness.fence.zza;

public final class zzbkz extends zzbgl {
    public static final Parcelable.Creator<zzbkz> CREATOR = new zzbla();
    private int type;
    private PendingIntent zzekk;
    private String zzgno;
    private zzbkc zzgny;
    public zzbkr zzgnz;
    public final zza zzgoa;
    private long zzgob;
    private long zzgoc;

    zzbkz(int i, zzbkc zzbkc, IBinder iBinder, PendingIntent pendingIntent, String str, long j, long j2) {
        zzbkr zzbkr;
        this.type = i;
        this.zzgny = zzbkc;
        if (iBinder == null || iBinder == null) {
            zzbkr = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.contextmanager.fence.internal.IContextFenceListener");
            zzbkr = queryLocalInterface instanceof zzbkr ? (zzbkr) queryLocalInterface : new zzbkt(iBinder);
        }
        this.zzgnz = zzbkr;
        this.zzgoa = null;
        this.zzekk = pendingIntent;
        this.zzgno = str;
        this.zzgob = j;
        this.zzgoc = j2;
    }

    private zzbkz(int i, zzbkc zzbkc, zza zza, PendingIntent pendingIntent, String str, long j, long j2) {
        this.type = i;
        this.zzgny = zzbkc;
        this.zzgnz = null;
        this.zzgoa = null;
        this.zzekk = pendingIntent;
        this.zzgno = str;
        this.zzgob = -1;
        this.zzgoc = -1;
    }

    public static final zzbkz zza(PendingIntent pendingIntent) {
        return new zzbkz(4, (zzbkc) null, (zza) null, pendingIntent, (String) null, -1, -1);
    }

    public static final zzbkz zza(String str, long j, zzbke zzbke, PendingIntent pendingIntent) {
        return new zzbkz(2, new zzbkc(str, 0, zzbke), (zza) null, pendingIntent, (String) null, -1, -1);
    }

    public static final zzbkz zzhd(String str) {
        return new zzbkz(5, (zzbkc) null, (zza) null, (PendingIntent) null, str, -1, -1);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.type);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzgny, i, false);
        zzbkr zzbkr = this.zzgnz;
        zzbgo.zza(parcel, 4, zzbkr == null ? null : zzbkr.asBinder(), false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzekk, i, false);
        zzbgo.zza(parcel, 6, this.zzgno, false);
        zzbgo.zza(parcel, 7, this.zzgob);
        zzbgo.zza(parcel, 8, this.zzgoc);
        zzbgo.zzai(parcel, zze);
    }
}
