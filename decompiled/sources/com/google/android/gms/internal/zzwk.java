package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public final class zzwk extends zzev implements zzwi {
    zzwk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    public final void destroy() throws RemoteException {
        zzb(5, zzbc());
    }

    public final Bundle getInterstitialAdapterInfo() throws RemoteException {
        Parcel zza = zza(18, zzbc());
        Bundle bundle = (Bundle) zzex.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle;
    }

    public final zzmm getVideoController() throws RemoteException {
        Parcel zza = zza(26, zzbc());
        zzmm zzh = zzmn.zzh(zza.readStrongBinder());
        zza.recycle();
        return zzh;
    }

    public final IObjectWrapper getView() throws RemoteException {
        Parcel zza = zza(2, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }

    public final boolean isInitialized() throws RemoteException {
        Parcel zza = zza(13, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void pause() throws RemoteException {
        zzb(8, zzbc());
    }

    public final void resume() throws RemoteException {
        zzb(9, zzbc());
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, z);
        zzb(25, zzbc);
    }

    public final void showInterstitial() throws RemoteException {
        zzb(4, zzbc());
    }

    public final void showVideo() throws RemoteException {
        zzb(12, zzbc());
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzafz zzafz, List<String> list) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) zzafz);
        zzbc.writeStringList(list);
        zzb(23, zzbc);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, zzafz zzafz, String str2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (Parcelable) zzkk);
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzafz);
        zzbc.writeString(str2);
        zzb(10, zzbc);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, zzwl zzwl) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (Parcelable) zzkk);
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzwl);
        zzb(3, zzbc);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, String str2, zzwl zzwl) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (Parcelable) zzkk);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (IInterface) zzwl);
        zzb(7, zzbc);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, String str2, zzwl zzwl, zzqh zzqh, List<String> list) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (Parcelable) zzkk);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (IInterface) zzwl);
        zzex.zza(zzbc, (Parcelable) zzqh);
        zzbc.writeStringList(list);
        zzb(14, zzbc);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzko zzko, zzkk zzkk, String str, zzwl zzwl) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (Parcelable) zzko);
        zzex.zza(zzbc, (Parcelable) zzkk);
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzwl);
        zzb(1, zzbc);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzko zzko, zzkk zzkk, String str, String str2, zzwl zzwl) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (Parcelable) zzko);
        zzex.zza(zzbc, (Parcelable) zzkk);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (IInterface) zzwl);
        zzb(6, zzbc);
    }

    public final void zza(zzkk zzkk, String str, String str2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzkk);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzb(20, zzbc);
    }

    public final void zzc(zzkk zzkk, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzkk);
        zzbc.writeString(str);
        zzb(11, zzbc);
    }

    public final void zzg(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzb(21, zzbc);
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzwr zzmp() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zzbc()
            r1 = 15
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzwr
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.internal.zzwr r1 = (com.google.android.gms.internal.zzwr) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.internal.zzwt r2 = new com.google.android.gms.internal.zzwt
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzwk.zzmp():com.google.android.gms.internal.zzwr");
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzwu zzmq() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zzbc()
            r1 = 16
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzwu
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.internal.zzwu r1 = (com.google.android.gms.internal.zzwu) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.internal.zzww r2 = new com.google.android.gms.internal.zzww
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzwk.zzmq():com.google.android.gms.internal.zzwu");
    }

    public final Bundle zzmr() throws RemoteException {
        Parcel zza = zza(17, zzbc());
        Bundle bundle = (Bundle) zzex.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle;
    }

    public final Bundle zzms() throws RemoteException {
        Parcel zza = zza(19, zzbc());
        Bundle bundle = (Bundle) zzex.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle;
    }

    public final boolean zzmt() throws RemoteException {
        Parcel zza = zza(22, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final zzro zzmu() throws RemoteException {
        Parcel zza = zza(24, zzbc());
        zzro zzn = zzrp.zzn(zza.readStrongBinder());
        zza.recycle();
        return zzn;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzwx zzmv() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zzbc()
            r1 = 27
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzwx
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.internal.zzwx r1 = (com.google.android.gms.internal.zzwx) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.internal.zzwz r2 = new com.google.android.gms.internal.zzwz
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzwk.zzmv():com.google.android.gms.internal.zzwx");
    }
}
