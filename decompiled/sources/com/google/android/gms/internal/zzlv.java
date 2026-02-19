package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzlv extends zzev implements zzlt {
    zzlv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdManager");
    }

    public final void destroy() throws RemoteException {
        zzb(2, zzbc());
    }

    public final String getAdUnitId() throws RemoteException {
        Parcel zza = zza(31, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        Parcel zza = zza(18, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzmm getVideoController() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zzbc()
            r1 = 26
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.IVideoController"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzmm
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.internal.zzmm r1 = (com.google.android.gms.internal.zzmm) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.internal.zzmo r2 = new com.google.android.gms.internal.zzmo
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlv.getVideoController():com.google.android.gms.internal.zzmm");
    }

    public final boolean isLoading() throws RemoteException {
        Parcel zza = zza(23, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean isReady() throws RemoteException {
        Parcel zza = zza(3, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void pause() throws RemoteException {
        zzb(5, zzbc());
    }

    public final void resume() throws RemoteException {
        zzb(6, zzbc());
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, z);
        zzb(34, zzbc);
    }

    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, z);
        zzb(22, zzbc);
    }

    public final void setUserId(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzb(25, zzbc);
    }

    public final void showInterstitial() throws RemoteException {
        zzb(9, zzbc());
    }

    public final void stopLoading() throws RemoteException {
        zzb(10, zzbc());
    }

    public final void zza(zzafc zzafc) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzafc);
        zzb(24, zzbc);
    }

    public final void zza(zzko zzko) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzko);
        zzb(13, zzbc);
    }

    public final void zza(zzlf zzlf) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzlf);
        zzb(20, zzbc);
    }

    public final void zza(zzli zzli) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzli);
        zzb(7, zzbc);
    }

    public final void zza(zzly zzly) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzly);
        zzb(8, zzbc);
    }

    public final void zza(zzme zzme) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzme);
        zzb(21, zzbc);
    }

    public final void zza(zzms zzms) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzms);
        zzb(30, zzbc);
    }

    public final void zza(zzns zzns) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzns);
        zzb(29, zzbc);
    }

    public final void zza(zzpb zzpb) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzpb);
        zzb(19, zzbc);
    }

    public final void zza(zzyx zzyx) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzyx);
        zzb(14, zzbc);
    }

    public final void zza(zzzd zzzd, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzzd);
        zzbc.writeString(str);
        zzb(15, zzbc);
    }

    public final boolean zzb(zzkk zzkk) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzkk);
        Parcel zza = zza(4, zzbc);
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final IObjectWrapper zzbp() throws RemoteException {
        Parcel zza = zza(1, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }

    public final zzko zzbq() throws RemoteException {
        Parcel zza = zza(12, zzbc());
        zzko result = (zzko) zzex.zza(zza, zzko.CREATOR);
        zza.recycle();
        return result;
    }

    public final void zzbs() throws RemoteException {
        zzb(11, zzbc());
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzly zzcc() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zzbc()
            r1 = 32
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAppEventListener"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzly
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.internal.zzly r1 = (com.google.android.gms.internal.zzly) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.internal.zzma r2 = new com.google.android.gms.internal.zzma
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlv.zzcc():com.google.android.gms.internal.zzly");
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzli zzcd() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zzbc()
            r1 = 33
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdListener"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzli
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.internal.zzli r1 = (com.google.android.gms.internal.zzli) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.internal.zzlk r2 = new com.google.android.gms.internal.zzlk
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlv.zzcd():com.google.android.gms.internal.zzli");
    }

    public final String zzco() throws RemoteException {
        Parcel zza = zza(35, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }
}
