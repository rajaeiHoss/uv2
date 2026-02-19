package com.google.android.gms.tagmanager;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.zzew;

public abstract class zzcu extends zzew implements zzct {
    public zzcu() {
        attachInterface(this, "com.google.android.gms.tagmanager.ITagManagerServiceProvider");
    }

    public static zzct asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.tagmanager.ITagManagerServiceProvider");
        return queryLocalInterface instanceof zzct ? (zzct) queryLocalInterface : new zzcv(iBinder);
    }

    /* JADX WARNING: type inference failed for: r8v4, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
        /*
            r4 = this;
            boolean r8 = r4.zza(r5, r6, r7, r8)
            r0 = 1
            if (r8 == 0) goto L_0x0008
            return r0
        L_0x0008:
            if (r5 != r0) goto L_0x0052
            android.os.IBinder r5 = r6.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r5)
            android.os.IBinder r8 = r6.readStrongBinder()
            r1 = 0
            if (r8 != 0) goto L_0x001b
            r2 = r1
            goto L_0x002d
        L_0x001b:
            java.lang.String r2 = "com.google.android.gms.tagmanager.IMeasurementProxy"
            android.os.IInterface r2 = r8.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.tagmanager.zzcn
            if (r3 == 0) goto L_0x0028
            com.google.android.gms.tagmanager.zzcn r2 = (com.google.android.gms.tagmanager.zzcn) r2
            goto L_0x002d
        L_0x0028:
            com.google.android.gms.tagmanager.zzcp r2 = new com.google.android.gms.tagmanager.zzcp
            r2.<init>(r8)
        L_0x002d:
            android.os.IBinder r6 = r6.readStrongBinder()
            if (r6 != 0) goto L_0x0034
            goto L_0x0047
        L_0x0034:
            java.lang.String r8 = "com.google.android.gms.tagmanager.ICustomEvaluatorProxy"
            android.os.IInterface r8 = r6.queryLocalInterface(r8)
            boolean r1 = r8 instanceof com.google.android.gms.tagmanager.zzce
            if (r1 == 0) goto L_0x0042
            r1 = r8
            com.google.android.gms.tagmanager.zzce r1 = (com.google.android.gms.tagmanager.zzce) r1
            goto L_0x0047
        L_0x0042:
            com.google.android.gms.tagmanager.zzcg r1 = new com.google.android.gms.tagmanager.zzcg
            r1.<init>(r6)
        L_0x0047:
            com.google.android.gms.internal.zzdah r5 = r4.getService(r5, r2, r1)
            r7.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r7, (android.os.IInterface) r5)
            return r0
        L_0x0052:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcu.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
