package com.google.android.gms.tagmanager;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.zzew;

public abstract class zzcr extends zzew implements zzcq {
    public zzcr() {
        attachInterface(this, "com.google.android.gms.tagmanager.ITagManagerApi");
    }

    public static zzcq asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.tagmanager.ITagManagerApi");
        return queryLocalInterface instanceof zzcq ? (zzcq) queryLocalInterface : new zzcs(iBinder);
    }

    /* JADX WARNING: type inference failed for: r13v3, types: [android.os.IInterface] */
    /* JADX WARNING: type inference failed for: r11v4, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r10, android.os.Parcel r11, android.os.Parcel r12, int r13) throws android.os.RemoteException {
        /*
            r9 = this;
            boolean r13 = r9.zza(r10, r11, r12, r13)
            r0 = 1
            if (r13 == 0) goto L_0x0008
            return r0
        L_0x0008:
            java.lang.String r13 = "com.google.android.gms.tagmanager.ICustomEvaluatorProxy"
            java.lang.String r1 = "com.google.android.gms.tagmanager.IMeasurementProxy"
            r2 = 0
            if (r10 == r0) goto L_0x007c
            r3 = 2
            if (r10 == r3) goto L_0x0068
            r3 = 3
            if (r10 == r3) goto L_0x0017
            r10 = 0
            return r10
        L_0x0017:
            android.os.Parcelable$Creator r10 = android.content.Intent.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r11, r10)
            r4 = r10
            android.content.Intent r4 = (android.content.Intent) r4
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r10)
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r6 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r10)
            android.os.IBinder r10 = r11.readStrongBinder()
            if (r10 != 0) goto L_0x0038
            r7 = r2
            goto L_0x0049
        L_0x0038:
            android.os.IInterface r1 = r10.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.tagmanager.zzcn
            if (r3 == 0) goto L_0x0043
            com.google.android.gms.tagmanager.zzcn r1 = (com.google.android.gms.tagmanager.zzcn) r1
            goto L_0x0048
        L_0x0043:
            com.google.android.gms.tagmanager.zzcp r1 = new com.google.android.gms.tagmanager.zzcp
            r1.<init>(r10)
        L_0x0048:
            r7 = r1
        L_0x0049:
            android.os.IBinder r10 = r11.readStrongBinder()
            if (r10 != 0) goto L_0x0051
        L_0x004f:
            r8 = r2
            goto L_0x0063
        L_0x0051:
            android.os.IInterface r11 = r10.queryLocalInterface(r13)
            boolean r13 = r11 instanceof com.google.android.gms.tagmanager.zzce
            if (r13 == 0) goto L_0x005d
            r2 = r11
            com.google.android.gms.tagmanager.zzce r2 = (com.google.android.gms.tagmanager.zzce) r2
            goto L_0x004f
        L_0x005d:
            com.google.android.gms.tagmanager.zzcg r2 = new com.google.android.gms.tagmanager.zzcg
            r2.<init>(r10)
            goto L_0x004f
        L_0x0063:
            r3 = r9
            r3.previewIntent(r4, r5, r6, r7, r8)
            goto L_0x00b7
        L_0x0068:
            android.os.Parcelable$Creator r10 = android.content.Intent.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r11, r10)
            android.content.Intent r10 = (android.content.Intent) r10
            android.os.IBinder r11 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r11 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r11)
            r9.preview(r10, r11)
            goto L_0x00b7
        L_0x007c:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r10)
            android.os.IBinder r3 = r11.readStrongBinder()
            if (r3 != 0) goto L_0x008c
            r1 = r2
            goto L_0x009c
        L_0x008c:
            android.os.IInterface r1 = r3.queryLocalInterface(r1)
            boolean r4 = r1 instanceof com.google.android.gms.tagmanager.zzcn
            if (r4 == 0) goto L_0x0097
            com.google.android.gms.tagmanager.zzcn r1 = (com.google.android.gms.tagmanager.zzcn) r1
            goto L_0x009c
        L_0x0097:
            com.google.android.gms.tagmanager.zzcp r1 = new com.google.android.gms.tagmanager.zzcp
            r1.<init>(r3)
        L_0x009c:
            android.os.IBinder r11 = r11.readStrongBinder()
            if (r11 != 0) goto L_0x00a3
            goto L_0x00b4
        L_0x00a3:
            android.os.IInterface r13 = r11.queryLocalInterface(r13)
            boolean r2 = r13 instanceof com.google.android.gms.tagmanager.zzce
            if (r2 == 0) goto L_0x00af
            r2 = r13
            com.google.android.gms.tagmanager.zzce r2 = (com.google.android.gms.tagmanager.zzce) r2
            goto L_0x00b4
        L_0x00af:
            com.google.android.gms.tagmanager.zzcg r2 = new com.google.android.gms.tagmanager.zzcg
            r2.<init>(r11)
        L_0x00b4:
            r9.initialize(r10, r1, r2)
        L_0x00b7:
            r12.writeNoException()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcr.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
