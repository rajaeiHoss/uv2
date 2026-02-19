package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzrz extends zzew implements zzry {
    public zzrz() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    public static zzry zzq(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
        return queryLocalInterface instanceof zzry ? (zzry) queryLocalInterface : new zzsa(iBinder);
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
        /*
            r2 = this;
            boolean r6 = r2.zza(r3, r4, r5, r6)
            r0 = 1
            if (r6 == 0) goto L_0x0008
            return r0
        L_0x0008:
            if (r3 != r0) goto L_0x0031
            android.os.IBinder r3 = r4.readStrongBinder()
            if (r3 != 0) goto L_0x0012
            r3 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r6 = "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd"
            android.os.IInterface r6 = r3.queryLocalInterface(r6)
            boolean r1 = r6 instanceof com.google.android.gms.internal.zzro
            if (r1 == 0) goto L_0x0020
            r3 = r6
            com.google.android.gms.internal.zzro r3 = (com.google.android.gms.internal.zzro) r3
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.internal.zzrq r6 = new com.google.android.gms.internal.zzrq
            r6.<init>(r3)
            r3 = r6
        L_0x0026:
            java.lang.String r4 = r4.readString()
            r2.zzb(r3, r4)
            r5.writeNoException()
            return r0
        L_0x0031:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzrz.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
