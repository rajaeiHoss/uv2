package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzew;

public abstract class zzco extends zzew implements zzcn {
    public zzco() {
        attachInterface(this, "com.google.android.gms.tagmanager.IMeasurementProxy");
    }

    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v2, types: [com.google.android.gms.tagmanager.zzck] */
    /* JADX WARNING: type inference failed for: r1v6, types: [com.google.android.gms.tagmanager.zzch] */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r8, android.os.Parcel r9, android.os.Parcel r10, int r11) throws android.os.RemoteException {
        /*
            r7 = this;
            boolean r11 = r7.zza(r8, r9, r10, r11)
            r0 = 1
            if (r11 == 0) goto L_0x0008
            return r0
        L_0x0008:
            r11 = 2
            if (r8 == r11) goto L_0x0061
            r11 = 11
            if (r8 == r11) goto L_0x0056
            r11 = 21
            r1 = 0
            if (r8 == r11) goto L_0x0038
            r11 = 22
            if (r8 == r11) goto L_0x001a
            r8 = 0
            return r8
        L_0x001a:
            android.os.IBinder r8 = r9.readStrongBinder()
            if (r8 != 0) goto L_0x0021
            goto L_0x0034
        L_0x0021:
            java.lang.String r9 = "com.google.android.gms.tagmanager.IMeasurementEventListener"
            android.os.IInterface r9 = r8.queryLocalInterface(r9)
            boolean r11 = r9 instanceof com.google.android.gms.tagmanager.zzch
            if (r11 == 0) goto L_0x002f
            r1 = r9
            com.google.android.gms.tagmanager.zzch r1 = (com.google.android.gms.tagmanager.zzch) r1
            goto L_0x0034
        L_0x002f:
            com.google.android.gms.tagmanager.zzcj r1 = new com.google.android.gms.tagmanager.zzcj
            r1.<init>(r8)
        L_0x0034:
            r7.zza((com.google.android.gms.tagmanager.zzch) r1)
            goto L_0x007a
        L_0x0038:
            android.os.IBinder r8 = r9.readStrongBinder()
            if (r8 != 0) goto L_0x003f
            goto L_0x0052
        L_0x003f:
            java.lang.String r9 = "com.google.android.gms.tagmanager.IMeasurementInterceptor"
            android.os.IInterface r9 = r8.queryLocalInterface(r9)
            boolean r11 = r9 instanceof com.google.android.gms.tagmanager.zzck
            if (r11 == 0) goto L_0x004d
            r1 = r9
            com.google.android.gms.tagmanager.zzck r1 = (com.google.android.gms.tagmanager.zzck) r1
            goto L_0x0052
        L_0x004d:
            com.google.android.gms.tagmanager.zzcm r1 = new com.google.android.gms.tagmanager.zzcm
            r1.<init>(r8)
        L_0x0052:
            r7.zza((com.google.android.gms.tagmanager.zzck) r1)
            goto L_0x007a
        L_0x0056:
            java.util.Map r8 = r7.zzbgl()
            r10.writeNoException()
            r10.writeMap(r8)
            goto L_0x007d
        L_0x0061:
            java.lang.String r2 = r9.readString()
            java.lang.String r3 = r9.readString()
            android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
            android.os.Parcelable r8 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r9, r8)
            r4 = r8
            android.os.Bundle r4 = (android.os.Bundle) r4
            long r5 = r9.readLong()
            r1 = r7
            r1.logEventInternalNoInterceptor(r2, r3, r4, r5)
        L_0x007a:
            r10.writeNoException()
        L_0x007d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzco.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
