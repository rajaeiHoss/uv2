package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzafd extends zzew implements zzafc {
    public zzafd() {
        attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }

    public static zzafc zzaa(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        return queryLocalInterface instanceof zzafc ? (zzafc) queryLocalInterface : new zzafe(iBinder);
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
                case 1: goto L_0x0049;
                case 2: goto L_0x0045;
                case 3: goto L_0x0041;
                case 4: goto L_0x003d;
                case 5: goto L_0x001d;
                case 6: goto L_0x0019;
                case 7: goto L_0x0011;
                case 8: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r2 = 0
            return r2
        L_0x000d:
            r1.onRewardedVideoCompleted()
            goto L_0x004c
        L_0x0011:
            int r2 = r3.readInt()
            r1.onRewardedVideoAdFailedToLoad(r2)
            goto L_0x004c
        L_0x0019:
            r1.onRewardedVideoAdLeftApplication()
            goto L_0x004c
        L_0x001d:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x0025
            r2 = 0
            goto L_0x0039
        L_0x0025:
            java.lang.String r3 = "com.google.android.gms.ads.internal.reward.client.IRewardItem"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r5 = r3 instanceof com.google.android.gms.internal.zzaeu
            if (r5 == 0) goto L_0x0033
            r2 = r3
            com.google.android.gms.internal.zzaeu r2 = (com.google.android.gms.internal.zzaeu) r2
            goto L_0x0039
        L_0x0033:
            com.google.android.gms.internal.zzaew r3 = new com.google.android.gms.internal.zzaew
            r3.<init>(r2)
            r2 = r3
        L_0x0039:
            r1.zza(r2)
            goto L_0x004c
        L_0x003d:
            r1.onRewardedVideoAdClosed()
            goto L_0x004c
        L_0x0041:
            r1.onRewardedVideoStarted()
            goto L_0x004c
        L_0x0045:
            r1.onRewardedVideoAdOpened()
            goto L_0x004c
        L_0x0049:
            r1.onRewardedVideoAdLoaded()
        L_0x004c:
            r4.writeNoException()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzafd.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
