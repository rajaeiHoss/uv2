package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzaey extends zzew implements zzaex {
    public zzaey() {
        attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    public static zzaex zzz(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        return queryLocalInterface instanceof zzaex ? (zzaex) queryLocalInterface : new zzaez(iBinder);
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
            if (r2 == r0) goto L_0x0096
            r5 = 2
            if (r2 == r5) goto L_0x0092
            r5 = 3
            if (r2 == r5) goto L_0x0072
            r5 = 34
            if (r2 == r5) goto L_0x006a
            switch(r2) {
                case 5: goto L_0x005f;
                case 6: goto L_0x005b;
                case 7: goto L_0x0057;
                case 8: goto L_0x0053;
                case 9: goto L_0x0047;
                case 10: goto L_0x003b;
                case 11: goto L_0x002e;
                case 12: goto L_0x0022;
                case 13: goto L_0x0019;
                default: goto L_0x0017;
            }
        L_0x0017:
            r2 = 0
            return r2
        L_0x0019:
            java.lang.String r2 = r3.readString()
            r1.setUserId(r2)
            goto L_0x00a1
        L_0x0022:
            java.lang.String r2 = r1.getMediationAdapterClassName()
            r4.writeNoException()
            r4.writeString(r2)
            goto L_0x00a4
        L_0x002e:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            r1.zzd(r2)
            goto L_0x00a1
        L_0x003b:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            r1.zzc(r2)
            goto L_0x00a1
        L_0x0047:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            r1.zzb(r2)
            goto L_0x00a1
        L_0x0053:
            r1.destroy()
            goto L_0x00a1
        L_0x0057:
            r1.resume()
            goto L_0x00a1
        L_0x005b:
            r1.pause()
            goto L_0x00a1
        L_0x005f:
            boolean r2 = r1.isLoaded()
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (boolean) r2)
            goto L_0x00a4
        L_0x006a:
            boolean r2 = com.google.android.gms.internal.zzex.zza(r3)
            r1.setImmersiveMode(r2)
            goto L_0x00a1
        L_0x0072:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x007a
            r2 = 0
            goto L_0x008e
        L_0x007a:
            java.lang.String r3 = "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r5 = r3 instanceof com.google.android.gms.internal.zzafc
            if (r5 == 0) goto L_0x0088
            r2 = r3
            com.google.android.gms.internal.zzafc r2 = (com.google.android.gms.internal.zzafc) r2
            goto L_0x008e
        L_0x0088:
            com.google.android.gms.internal.zzafe r3 = new com.google.android.gms.internal.zzafe
            r3.<init>(r2)
            r2 = r3
        L_0x008e:
            r1.zza((com.google.android.gms.internal.zzafc) r2)
            goto L_0x00a1
        L_0x0092:
            r1.show()
            goto L_0x00a1
        L_0x0096:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzafi> r2 = com.google.android.gms.internal.zzafi.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            com.google.android.gms.internal.zzafi r2 = (com.google.android.gms.internal.zzafi) r2
            r1.zza((com.google.android.gms.internal.zzafi) r2)
        L_0x00a1:
            r4.writeNoException()
        L_0x00a4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaey.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
