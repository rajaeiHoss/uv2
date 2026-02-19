package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzmd extends zzev implements zzmb {
    zzmd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IClientApi");
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzlo createAdLoaderBuilder(com.google.android.gms.dynamic.IObjectWrapper r2, java.lang.String r3, com.google.android.gms.internal.zzwf r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r2)
            r0.writeString(r3)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r4)
            r0.writeInt(r5)
            r2 = 3
            android.os.Parcel r2 = r1.zza(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x001d
            r3 = 0
            goto L_0x0031
        L_0x001d:
            java.lang.String r4 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.zzlo
            if (r5 == 0) goto L_0x002b
            r3 = r4
            com.google.android.gms.internal.zzlo r3 = (com.google.android.gms.internal.zzlo) r3
            goto L_0x0031
        L_0x002b:
            com.google.android.gms.internal.zzlq r4 = new com.google.android.gms.internal.zzlq
            r4.<init>(r3)
            r3 = r4
        L_0x0031:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmd.createAdLoaderBuilder(com.google.android.gms.dynamic.IObjectWrapper, java.lang.String, com.google.android.gms.internal.zzwf, int):com.google.android.gms.internal.zzlo");
    }

    public final zzyq createAdOverlay(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        Parcel zza = zza(8, zzbc);
        zzyq zzv = zzyr.zzv(zza.readStrongBinder());
        zza.recycle();
        return zzv;
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzlt createBannerAdManager(com.google.android.gms.dynamic.IObjectWrapper r2, com.google.android.gms.internal.zzko r3, java.lang.String r4, com.google.android.gms.internal.zzwf r5, int r6) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r2)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.Parcelable) r3)
            r0.writeString(r4)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r5)
            r0.writeInt(r6)
            r2 = 1
            android.os.Parcel r2 = r1.zza(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x0020
            r3 = 0
            goto L_0x0034
        L_0x0020:
            java.lang.String r4 = "com.google.android.gms.ads.internal.client.IAdManager"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.zzlt
            if (r5 == 0) goto L_0x002e
            r3 = r4
            com.google.android.gms.internal.zzlt r3 = (com.google.android.gms.internal.zzlt) r3
            goto L_0x0034
        L_0x002e:
            com.google.android.gms.internal.zzlv r4 = new com.google.android.gms.internal.zzlv
            r4.<init>(r3)
            r3 = r4
        L_0x0034:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmd.createBannerAdManager(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.zzko, java.lang.String, com.google.android.gms.internal.zzwf, int):com.google.android.gms.internal.zzlt");
    }

    public final zzza createInAppPurchaseManager(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        Parcel zza = zza(7, zzbc);
        zzza zzx = zzzb.zzx(zza.readStrongBinder());
        zza.recycle();
        return zzx;
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzlt createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper r2, com.google.android.gms.internal.zzko r3, java.lang.String r4, com.google.android.gms.internal.zzwf r5, int r6) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r2)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.Parcelable) r3)
            r0.writeString(r4)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r5)
            r0.writeInt(r6)
            r2 = 2
            android.os.Parcel r2 = r1.zza(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x0020
            r3 = 0
            goto L_0x0034
        L_0x0020:
            java.lang.String r4 = "com.google.android.gms.ads.internal.client.IAdManager"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.zzlt
            if (r5 == 0) goto L_0x002e
            r3 = r4
            com.google.android.gms.internal.zzlt r3 = (com.google.android.gms.internal.zzlt) r3
            goto L_0x0034
        L_0x002e:
            com.google.android.gms.internal.zzlv r4 = new com.google.android.gms.internal.zzlv
            r4.<init>(r3)
            r3 = r4
        L_0x0034:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmd.createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.zzko, java.lang.String, com.google.android.gms.internal.zzwf, int):com.google.android.gms.internal.zzlt");
    }

    public final zzqw createNativeAdViewDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) iObjectWrapper2);
        Parcel zza = zza(5, zzbc);
        zzqw zzl = zzqx.zzl(zza.readStrongBinder());
        zza.recycle();
        return zzl;
    }

    public final zzrb createNativeAdViewHolderDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) iObjectWrapper2);
        zzex.zza(zzbc, (IInterface) iObjectWrapper3);
        Parcel zza = zza(11, zzbc);
        zzrb zzm = zzrc.zzm(zza.readStrongBinder());
        zza.recycle();
        return zzm;
    }

    public final zzaex createRewardedVideoAd(IObjectWrapper iObjectWrapper, zzwf zzwf, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) zzwf);
        zzbc.writeInt(i);
        Parcel zza = zza(6, zzbc);
        zzaex zzz = zzaey.zzz(zza.readStrongBinder());
        zza.recycle();
        return zzz;
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzlt createSearchAdManager(com.google.android.gms.dynamic.IObjectWrapper r2, com.google.android.gms.internal.zzko r3, java.lang.String r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r2)
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.Parcelable) r3)
            r0.writeString(r4)
            r0.writeInt(r5)
            r2 = 10
            android.os.Parcel r2 = r1.zza(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x001e
            r3 = 0
            goto L_0x0032
        L_0x001e:
            java.lang.String r4 = "com.google.android.gms.ads.internal.client.IAdManager"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.zzlt
            if (r5 == 0) goto L_0x002c
            r3 = r4
            com.google.android.gms.internal.zzlt r3 = (com.google.android.gms.internal.zzlt) r3
            goto L_0x0032
        L_0x002c:
            com.google.android.gms.internal.zzlv r4 = new com.google.android.gms.internal.zzlv
            r4.<init>(r3)
            r3 = r4
        L_0x0032:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmd.createSearchAdManager(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.zzko, java.lang.String, int):com.google.android.gms.internal.zzlt");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzmh getMobileAdsSettingsManager(com.google.android.gms.dynamic.IObjectWrapper r4) throws android.os.RemoteException {
        /*
            r3 = this;
            android.os.Parcel r0 = r3.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r4)
            r4 = 4
            android.os.Parcel r4 = r3.zza(r4, r0)
            android.os.IBinder r0 = r4.readStrongBinder()
            if (r0 != 0) goto L_0x0014
            r0 = 0
            goto L_0x0028
        L_0x0014:
            java.lang.String r1 = "com.google.android.gms.ads.internal.client.IMobileAdsSettingManager"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.zzmh
            if (r2 == 0) goto L_0x0022
            r0 = r1
            com.google.android.gms.internal.zzmh r0 = (com.google.android.gms.internal.zzmh) r0
            goto L_0x0028
        L_0x0022:
            com.google.android.gms.internal.zzmj r1 = new com.google.android.gms.internal.zzmj
            r1.<init>(r0)
            r0 = r1
        L_0x0028:
            r4.recycle()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmd.getMobileAdsSettingsManager(com.google.android.gms.dynamic.IObjectWrapper):com.google.android.gms.internal.zzmh");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzmh getMobileAdsSettingsManagerWithClientJarVersion(com.google.android.gms.dynamic.IObjectWrapper r3, int r4) throws android.os.RemoteException {
        /*
            r2 = this;
            android.os.Parcel r0 = r2.zzbc()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r0, (android.os.IInterface) r3)
            r0.writeInt(r4)
            r3 = 9
            android.os.Parcel r3 = r2.zza(r3, r0)
            android.os.IBinder r4 = r3.readStrongBinder()
            if (r4 != 0) goto L_0x0018
            r4 = 0
            goto L_0x002c
        L_0x0018:
            java.lang.String r0 = "com.google.android.gms.ads.internal.client.IMobileAdsSettingManager"
            android.os.IInterface r0 = r4.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.zzmh
            if (r1 == 0) goto L_0x0026
            r4 = r0
            com.google.android.gms.internal.zzmh r4 = (com.google.android.gms.internal.zzmh) r4
            goto L_0x002c
        L_0x0026:
            com.google.android.gms.internal.zzmj r0 = new com.google.android.gms.internal.zzmj
            r0.<init>(r4)
            r4 = r0
        L_0x002c:
            r3.recycle()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmd.getMobileAdsSettingsManagerWithClientJarVersion(com.google.android.gms.dynamic.IObjectWrapper, int):com.google.android.gms.internal.zzmh");
    }
}
