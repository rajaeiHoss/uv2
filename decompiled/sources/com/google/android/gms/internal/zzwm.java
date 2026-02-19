package com.google.android.gms.internal;

public abstract class zzwm extends zzew implements zzwl {
    public zzwm() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r2, android.os.Parcel r3, android.os.Parcel r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            boolean r5 = r1.zza(r2, r3, r4, r5)
            r0 = 1
            if (r5 == 0) goto L_0x0008
            return r0
        L_0x0008:
            switch(r2) {
                case 1: goto L_0x0069;
                case 2: goto L_0x0065;
                case 3: goto L_0x005d;
                case 4: goto L_0x0059;
                case 5: goto L_0x0055;
                case 6: goto L_0x0051;
                case 7: goto L_0x0031;
                case 8: goto L_0x002d;
                case 9: goto L_0x0021;
                case 10: goto L_0x0011;
                case 11: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r2 = 0
            return r2
        L_0x000d:
            r1.onVideoEnd()
            goto L_0x006c
        L_0x0011:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.internal.zzro r2 = com.google.android.gms.internal.zzrp.zzn(r2)
            java.lang.String r3 = r3.readString()
            r1.zzb(r2, r3)
            goto L_0x006c
        L_0x0021:
            java.lang.String r2 = r3.readString()
            java.lang.String r3 = r3.readString()
            r1.onAppEvent(r2, r3)
            goto L_0x006c
        L_0x002d:
            r1.onAdImpression()
            goto L_0x006c
        L_0x0031:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x0039
            r2 = 0
            goto L_0x004d
        L_0x0039:
            java.lang.String r3 = "com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r5 = r3 instanceof com.google.android.gms.internal.zzwo
            if (r5 == 0) goto L_0x0047
            r2 = r3
            com.google.android.gms.internal.zzwo r2 = (com.google.android.gms.internal.zzwo) r2
            goto L_0x004d
        L_0x0047:
            com.google.android.gms.internal.zzwq r3 = new com.google.android.gms.internal.zzwq
            r3.<init>(r2)
            r2 = r3
        L_0x004d:
            r1.zza(r2)
            goto L_0x006c
        L_0x0051:
            r1.onAdLoaded()
            goto L_0x006c
        L_0x0055:
            r1.onAdOpened()
            goto L_0x006c
        L_0x0059:
            r1.onAdLeftApplication()
            goto L_0x006c
        L_0x005d:
            int r2 = r3.readInt()
            r1.onAdFailedToLoad(r2)
            goto L_0x006c
        L_0x0065:
            r1.onAdClosed()
            goto L_0x006c
        L_0x0069:
            r1.onAdClicked()
        L_0x006c:
            r4.writeNoException()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzwm.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
