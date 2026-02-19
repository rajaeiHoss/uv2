package com.google.firebase.database.connection.idl;

import com.google.android.gms.internal.zzew;

public abstract class zzl extends zzew implements zzk {
    public zzl() {
        attachInterface(this, "com.google.firebase.database.connection.idl.IConnectionAuthTokenProvider");
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
            boolean r3 = com.google.android.gms.internal.zzex.zza(r4)
            android.os.IBinder r4 = r4.readStrongBinder()
            if (r4 != 0) goto L_0x0016
            r4 = 0
            goto L_0x002a
        L_0x0016:
            java.lang.String r6 = "com.google.firebase.database.connection.idl.IGetTokenCallback"
            android.os.IInterface r6 = r4.queryLocalInterface(r6)
            boolean r1 = r6 instanceof com.google.firebase.database.connection.idl.zzn
            if (r1 == 0) goto L_0x0024
            r4 = r6
            com.google.firebase.database.connection.idl.zzn r4 = (com.google.firebase.database.connection.idl.zzn) r4
            goto L_0x002a
        L_0x0024:
            com.google.firebase.database.connection.idl.zzp r6 = new com.google.firebase.database.connection.idl.zzp
            r6.<init>(r4)
            r4 = r6
        L_0x002a:
            r2.zza(r3, r4)
            r5.writeNoException()
            return r0
        L_0x0031:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.connection.idl.zzl.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
