package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzdai extends zzew implements zzdah {
    public zzdai() {
        attachInterface(this, "com.google.android.gms.tagmanager.internal.ITagManagerService");
    }

    public static zzdah zzbr(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.tagmanager.internal.ITagManagerService");
        return queryLocalInterface instanceof zzdah ? (zzdah) queryLocalInterface : new zzdaj(iBinder);
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) throws android.os.RemoteException {
        /*
            r8 = this;
            boolean r12 = r8.zza(r9, r10, r11, r12)
            r0 = 1
            if (r12 == 0) goto L_0x0008
            return r0
        L_0x0008:
            if (r9 == r0) goto L_0x006c
            r12 = 2
            if (r9 == r12) goto L_0x0040
            r12 = 3
            if (r9 == r12) goto L_0x003c
            r12 = 101(0x65, float:1.42E-43)
            if (r9 == r12) goto L_0x001e
            r10 = 102(0x66, float:1.43E-43)
            if (r9 == r10) goto L_0x001a
            r9 = 0
            return r9
        L_0x001a:
            r8.dispatch()
            goto L_0x007b
        L_0x001e:
            java.lang.String r2 = r10.readString()
            android.os.Parcelable$Creator r9 = android.os.Bundle.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r10, r9)
            r3 = r9
            android.os.Bundle r3 = (android.os.Bundle) r3
            java.lang.String r4 = r10.readString()
            long r5 = r10.readLong()
            boolean r7 = com.google.android.gms.internal.zzex.zza(r10)
            r1 = r8
            r1.zza(r2, r3, r4, r5, r7)
            goto L_0x007b
        L_0x003c:
            r8.zzbiv()
            goto L_0x007b
        L_0x0040:
            java.lang.String r9 = r10.readString()
            java.lang.String r12 = r10.readString()
            java.lang.String r1 = r10.readString()
            android.os.IBinder r10 = r10.readStrongBinder()
            if (r10 != 0) goto L_0x0054
            r10 = 0
            goto L_0x0068
        L_0x0054:
            java.lang.String r2 = "com.google.android.gms.tagmanager.internal.ITagManagerLoadContainerCallback"
            android.os.IInterface r2 = r10.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzdae
            if (r3 == 0) goto L_0x0062
            r10 = r2
            com.google.android.gms.internal.zzdae r10 = (com.google.android.gms.internal.zzdae) r10
            goto L_0x0068
        L_0x0062:
            com.google.android.gms.internal.zzdag r2 = new com.google.android.gms.internal.zzdag
            r2.<init>(r10)
            r10 = r2
        L_0x0068:
            r8.zza(r9, r12, r1, r10)
            goto L_0x007b
        L_0x006c:
            java.lang.String r9 = r10.readString()
            java.lang.String r12 = r10.readString()
            java.lang.String r10 = r10.readString()
            r8.zzn(r9, r12, r10)
        L_0x007b:
            r11.writeNoException()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdai.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
