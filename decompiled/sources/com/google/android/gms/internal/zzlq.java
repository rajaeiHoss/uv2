package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

public final class zzlq extends zzev implements zzlo {
    zzlq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    public final void zza(PublisherAdViewOptions publisherAdViewOptions) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) publisherAdViewOptions);
        zzb(9, zzbc);
    }

    public final void zza(zzqh zzqh) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzqh);
        zzb(6, zzbc);
    }

    public final void zza(zzrs zzrs) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzrs);
        zzb(3, zzbc);
    }

    public final void zza(zzrv zzrv) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzrv);
        zzb(4, zzbc);
    }

    public final void zza(zzse zzse, zzko zzko) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzse);
        zzex.zza(zzbc, (Parcelable) zzko);
        zzb(8, zzbc);
    }

    public final void zza(zzsh zzsh) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzsh);
        zzb(10, zzbc);
    }

    public final void zza(String str, zzsb zzsb, zzry zzry) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzsb);
        zzex.zza(zzbc, (IInterface) zzry);
        zzb(5, zzbc);
    }

    public final void zzb(zzli zzli) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzli);
        zzb(2, zzbc);
    }

    public final void zzb(zzme zzme) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzme);
        zzb(7, zzbc);
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzll zzdi() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zzbc()
            r1 = 1
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0011
            r1 = 0
            goto L_0x0025
        L_0x0011:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdLoader"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzll
            if (r3 == 0) goto L_0x001f
            r1 = r2
            com.google.android.gms.internal.zzll r1 = (com.google.android.gms.internal.zzll) r1
            goto L_0x0025
        L_0x001f:
            com.google.android.gms.internal.zzln r2 = new com.google.android.gms.internal.zzln
            r2.<init>(r1)
            r1 = r2
        L_0x0025:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlq.zzdi():com.google.android.gms.internal.zzll");
    }
}
