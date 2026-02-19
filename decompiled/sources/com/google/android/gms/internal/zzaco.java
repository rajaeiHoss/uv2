package com.google.android.gms.internal;

public abstract class zzaco extends zzew implements zzacn {
    public zzaco() {
        attachInterface(this, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.gms.internal.zzacq] */
    /* JADX WARNING: type inference failed for: r1v6, types: [com.google.android.gms.internal.zzact] */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
        /*
            r2 = this;
            boolean r6 = r2.zza(r3, r4, r5, r6)
            r0 = 1
            if (r6 == 0) goto L_0x0008
            return r0
        L_0x0008:
            if (r3 == r0) goto L_0x0062
            r6 = 2
            r1 = 0
            if (r3 == r6) goto L_0x0039
            r6 = 4
            if (r3 == r6) goto L_0x0013
            r3 = 0
            return r3
        L_0x0013:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzacy> r3 = com.google.android.gms.internal.zzacy.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, r3)
            com.google.android.gms.internal.zzacy r3 = (com.google.android.gms.internal.zzacy) r3
            android.os.IBinder r4 = r4.readStrongBinder()
            if (r4 != 0) goto L_0x0022
            goto L_0x0035
        L_0x0022:
            java.lang.String r6 = "com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener"
            android.os.IInterface r6 = r4.queryLocalInterface(r6)
            boolean r1 = r6 instanceof com.google.android.gms.internal.zzact
            if (r1 == 0) goto L_0x0030
            r1 = r6
            com.google.android.gms.internal.zzact r1 = (com.google.android.gms.internal.zzact) r1
            goto L_0x0035
        L_0x0030:
            com.google.android.gms.internal.zzacu r1 = new com.google.android.gms.internal.zzacu
            r1.<init>(r4)
        L_0x0035:
            r2.zza((com.google.android.gms.internal.zzacy) r3, (com.google.android.gms.internal.zzact) r1)
            goto L_0x005e
        L_0x0039:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzacf> r3 = com.google.android.gms.internal.zzacf.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, r3)
            com.google.android.gms.internal.zzacf r3 = (com.google.android.gms.internal.zzacf) r3
            android.os.IBinder r4 = r4.readStrongBinder()
            if (r4 != 0) goto L_0x0048
            goto L_0x005b
        L_0x0048:
            java.lang.String r6 = "com.google.android.gms.ads.internal.request.IAdResponseListener"
            android.os.IInterface r6 = r4.queryLocalInterface(r6)
            boolean r1 = r6 instanceof com.google.android.gms.internal.zzacq
            if (r1 == 0) goto L_0x0056
            r1 = r6
            com.google.android.gms.internal.zzacq r1 = (com.google.android.gms.internal.zzacq) r1
            goto L_0x005b
        L_0x0056:
            com.google.android.gms.internal.zzacs r1 = new com.google.android.gms.internal.zzacs
            r1.<init>(r4)
        L_0x005b:
            r2.zza((com.google.android.gms.internal.zzacf) r3, (com.google.android.gms.internal.zzacq) r1)
        L_0x005e:
            r5.writeNoException()
            goto L_0x0074
        L_0x0062:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzacf> r3 = com.google.android.gms.internal.zzacf.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, r3)
            com.google.android.gms.internal.zzacf r3 = (com.google.android.gms.internal.zzacf) r3
            com.google.android.gms.internal.zzacj r3 = r2.zzb(r3)
            r5.writeNoException()
            com.google.android.gms.internal.zzex.zzb(r5, r3)
        L_0x0074:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaco.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
