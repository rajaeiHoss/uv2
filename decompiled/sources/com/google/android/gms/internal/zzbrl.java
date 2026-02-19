package com.google.android.gms.internal;

import android.content.IntentSender;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzbrl extends zzev implements zzbrk {
    zzbrl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.drive.internal.IDriveService");
    }

    public final IntentSender zza(zzbmj zzbmj) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbmj);
        Parcel zza = zza(11, zzbc);
        IntentSender intentSender = (IntentSender) zzex.zza(zza, IntentSender.CREATOR);
        zza.recycle();
        return intentSender;
    }

    public final IntentSender zza(zzbtc zzbtc) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbtc);
        Parcel zza = zza(10, zzbc);
        IntentSender intentSender = (IntentSender) zzex.zza(zza, IntentSender.CREATOR);
        zza.recycle();
        return intentSender;
    }

    public final zzbqy zza(zzbsz zzbsz, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbsz);
        zzex.zza(zzbc, (IInterface) zzbrm);
        Parcel zza = zza(7, zzbc);
        zzbqy zzbqyVar = (zzbqy) zzex.zza(zza, zzbqy.CREATOR);
        zza.recycle();
        return zzbqyVar;
    }

    public final void zza(zzbly zzbly, zzbro zzbro, String str, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbly);
        zzex.zza(zzbc, (IInterface) zzbro);
        zzbc.writeString((String) null);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(14, zzbc);
    }

    public final void zza(zzbmb zzbmb, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbmb);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(18, zzbc);
    }

    public final void zza(zzbmd zzbmd, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbmd);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(8, zzbc);
    }

    public final void zza(zzbmg zzbmg, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbmg);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(4, zzbc);
    }

    public final void zza(zzbml zzbml, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbml);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(5, zzbc);
    }

    public final void zza(zzbmn zzbmn, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbmn);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(6, zzbc);
    }

    public final void zza(zzbmq zzbmq, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbmq);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(24, zzbc);
    }

    public final void zza(zzbms zzbms) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbms);
        zzb(16, zzbc);
    }

    public final void zza(zzbrg zzbrg, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbrg);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(1, zzbc);
    }

    public final void zza(zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(9, zzbc);
    }

    public final void zza(zzbrt zzbrt, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbrt);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(13, zzbc);
    }

    public final void zza(zzbtg zzbtg, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbtg);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(2, zzbc);
    }

    public final void zza(zzbti zzbti, zzbro zzbro, String str, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbti);
        zzex.zza(zzbc, (IInterface) zzbro);
        zzbc.writeString((String) null);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(15, zzbc);
    }

    public final void zza(zzbtk zzbtk, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbtk);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(36, zzbc);
    }

    public final void zza(zzbtm zzbtm, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbtm);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(28, zzbc);
    }

    public final void zza(zzbtr zzbtr, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbtr);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(17, zzbc);
    }

    public final void zza(zzbtt zzbtt, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbtt);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(38, zzbc);
    }

    public final void zza(zzbtv zzbtv, zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbtv);
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(3, zzbc);
    }

    public final void zzb(zzbrm zzbrm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbrm);
        zzb(35, zzbc);
    }
}
