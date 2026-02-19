package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

public final class zzri extends zzev implements zzrg {
    zzri(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
    }

    public final void destroy() throws RemoteException {
        zzb(12, zzbc());
    }

    public final String getBody() throws RemoteException {
        Parcel zza = zza(5, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final String getCallToAction() throws RemoteException {
        Parcel zza = zza(7, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final Bundle getExtras() throws RemoteException {
        Parcel zza = zza(11, zzbc());
        Bundle bundle = (Bundle) zzex.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle;
    }

    public final String getHeadline() throws RemoteException {
        Parcel zza = zza(3, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final List getImages() throws RemoteException {
        Parcel zza = zza(4, zzbc());
        ArrayList zzb = zzex.zzb(zza);
        zza.recycle();
        return zzb;
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        Parcel zza = zza(19, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final String getPrice() throws RemoteException {
        Parcel zza = zza(10, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final double getStarRating() throws RemoteException {
        Parcel zza = zza(8, zzbc());
        double readDouble = zza.readDouble();
        zza.recycle();
        return readDouble;
    }

    public final String getStore() throws RemoteException {
        Parcel zza = zza(9, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final zzmm getVideoController() throws RemoteException {
        Parcel zza = zza(13, zzbc());
        zzmm zzh = zzmn.zzh(zza.readStrongBinder());
        zza.recycle();
        return zzh;
    }

    public final void performClick(Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(14, zzbc);
    }

    public final boolean recordImpression(Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) bundle);
        Parcel zza = zza(15, zzbc);
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void reportTouchEvent(Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(16, zzbc);
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzqs zzkc() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zzbc()
            r1 = 6
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0011
            r1 = 0
            goto L_0x0025
        L_0x0011:
            java.lang.String r2 = "com.google.android.gms.ads.internal.formats.client.INativeAdImage"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzqs
            if (r3 == 0) goto L_0x001f
            r1 = r2
            com.google.android.gms.internal.zzqs r1 = (com.google.android.gms.internal.zzqs) r1
            goto L_0x0025
        L_0x001f:
            com.google.android.gms.internal.zzqu r2 = new com.google.android.gms.internal.zzqu
            r2.<init>(r1)
            r1 = r2
        L_0x0025:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzri.zzkc():com.google.android.gms.internal.zzqs");
    }

    public final IObjectWrapper zzkd() throws RemoteException {
        Parcel zza = zza(2, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }

    public final IObjectWrapper zzkh() throws RemoteException {
        Parcel zza = zza(18, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzqo zzki() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.zzbc()
            r1 = 17
            android.os.Parcel r0 = r4.zza(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0012
            r1 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r2 = "com.google.android.gms.ads.internal.formats.client.IAttributionInfo"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzqo
            if (r3 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.internal.zzqo r1 = (com.google.android.gms.internal.zzqo) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.internal.zzqq r2 = new com.google.android.gms.internal.zzqq
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzri.zzki():com.google.android.gms.internal.zzqo");
    }
}
