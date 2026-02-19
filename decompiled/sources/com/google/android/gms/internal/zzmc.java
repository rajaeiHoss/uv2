package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzmc extends zzew implements zzmb {
    public zzmc() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IClientApi");
    }

    public static zzmb asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
        return queryLocalInterface instanceof zzmb ? (zzmb) queryLocalInterface : new zzmd(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IInterface iInterface;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                iInterface = createBannerAdManager(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), (zzko) zzex.zza(parcel, zzko.CREATOR), parcel.readString(), zzwg.zzu(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 2:
                iInterface = createInterstitialAdManager(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), (zzko) zzex.zza(parcel, zzko.CREATOR), parcel.readString(), zzwg.zzu(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 3:
                iInterface = createAdLoaderBuilder(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), parcel.readString(), zzwg.zzu(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 4:
                iInterface = getMobileAdsSettingsManager(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()));
                break;
            case 5:
                iInterface = createNativeAdViewDelegate(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), IObjectWrapper.zza.zzaq(parcel.readStrongBinder()));
                break;
            case 6:
                iInterface = createRewardedVideoAd(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), zzwg.zzu(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 7:
                iInterface = createInAppPurchaseManager(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()));
                break;
            case 8:
                iInterface = createAdOverlay(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()));
                break;
            case 9:
                iInterface = getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 10:
                iInterface = createSearchAdManager(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), (zzko) zzex.zza(parcel, zzko.CREATOR), parcel.readString(), parcel.readInt());
                break;
            case 11:
                iInterface = createNativeAdViewHolderDelegate(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), IObjectWrapper.zza.zzaq(parcel.readStrongBinder()), IObjectWrapper.zza.zzaq(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        zzex.zza(parcel2, iInterface);
        return true;
    }
}
