package com.google.android.gms.internal;

public abstract class zzbak extends zzew implements zzbaj {
    public zzbak() {
        attachInterface(this, "com.google.android.gms.cast.framework.internal.IMediaRouter");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [android.os.IInterface] */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005c, code lost:
        r5.writeNoException();
        com.google.android.gms.internal.zzex.zza(r5, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x007e, code lost:
        r5.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00aa, code lost:
        return true;
     */
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
            switch(r3) {
                case 1: goto L_0x0082;
                case 2: goto L_0x006f;
                case 3: goto L_0x0063;
                case 4: goto L_0x004c;
                case 5: goto L_0x0044;
                case 6: goto L_0x0040;
                case 7: goto L_0x003b;
                case 8: goto L_0x002b;
                case 9: goto L_0x001f;
                case 10: goto L_0x0011;
                case 11: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r3 = 0
            return r3
        L_0x000d:
            r2.zzaex()
            goto L_0x007e
        L_0x0011:
            r2.zzadx()
            r5.writeNoException()
            r3 = 12211278(0xba544e, float:1.7111645E-38)
            r5.writeInt(r3)
            goto L_0x00aa
        L_0x001f:
            java.lang.String r3 = r2.zzaew()
            r5.writeNoException()
            r5.writeString(r3)
            goto L_0x00aa
        L_0x002b:
            java.lang.String r3 = r4.readString()
            android.os.Bundle r3 = r2.zzfs(r3)
            r5.writeNoException()
            com.google.android.gms.internal.zzex.zzb(r5, r3)
            goto L_0x00aa
        L_0x003b:
            boolean r3 = r2.zzaev()
            goto L_0x005c
        L_0x0040:
            r2.zzaeu()
            goto L_0x007e
        L_0x0044:
            java.lang.String r3 = r4.readString()
            r2.zzfr(r3)
            goto L_0x007e
        L_0x004c:
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, r3)
            android.os.Bundle r3 = (android.os.Bundle) r3
            int r4 = r4.readInt()
            boolean r3 = r2.zzb(r3, r4)
        L_0x005c:
            r5.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r5, (boolean) r3)
            goto L_0x00aa
        L_0x0063:
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, r3)
            android.os.Bundle r3 = (android.os.Bundle) r3
            r2.zzi(r3)
            goto L_0x007e
        L_0x006f:
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, r3)
            android.os.Bundle r3 = (android.os.Bundle) r3
            int r4 = r4.readInt()
            r2.zza((android.os.Bundle) r3, (int) r4)
        L_0x007e:
            r5.writeNoException()
            goto L_0x00aa
        L_0x0082:
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, r3)
            android.os.Bundle r3 = (android.os.Bundle) r3
            android.os.IBinder r4 = r4.readStrongBinder()
            if (r4 != 0) goto L_0x0092
            r4 = 0
            goto L_0x00a6
        L_0x0092:
            java.lang.String r6 = "com.google.android.gms.cast.framework.internal.IMediaRouterCallback"
            android.os.IInterface r6 = r4.queryLocalInterface(r6)
            boolean r1 = r6 instanceof com.google.android.gms.internal.zzbal
            if (r1 == 0) goto L_0x00a0
            r4 = r6
            com.google.android.gms.internal.zzbal r4 = (com.google.android.gms.internal.zzbal) r4
            goto L_0x00a6
        L_0x00a0:
            com.google.android.gms.internal.zzbam r6 = new com.google.android.gms.internal.zzbam
            r6.<init>(r4)
            r4 = r6
        L_0x00a6:
            r2.zza((android.os.Bundle) r3, (com.google.android.gms.internal.zzbal) r4)
            goto L_0x007e
        L_0x00aa:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbak.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
