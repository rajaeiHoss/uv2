package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzmn extends zzew implements zzmm {
    public zzmn() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IVideoController");
    }

    public static zzmm zzh(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
        return queryLocalInterface instanceof zzmm ? (zzmm) queryLocalInterface : new zzmo(iBinder);
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [android.os.IInterface] */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        r4.writeNoException();
        r4.writeFloat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0066, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007c, code lost:
        r4.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007f, code lost:
        return true;
     */
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
                case 1: goto L_0x0079;
                case 2: goto L_0x0075;
                case 3: goto L_0x006d;
                case 4: goto L_0x0062;
                case 5: goto L_0x0057;
                case 6: goto L_0x004c;
                case 7: goto L_0x0047;
                case 8: goto L_0x0027;
                case 9: goto L_0x0022;
                case 10: goto L_0x001d;
                case 11: goto L_0x0012;
                case 12: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r2 = 0
            return r2
        L_0x000d:
            boolean r2 = r1.isClickToExpandEnabled()
            goto L_0x0066
        L_0x0012:
            com.google.android.gms.internal.zzmp r2 = r1.zzis()
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (android.os.IInterface) r2)
            goto L_0x007f
        L_0x001d:
            boolean r2 = r1.isCustomControlsEnabled()
            goto L_0x0066
        L_0x0022:
            float r2 = r1.getAspectRatio()
            goto L_0x0050
        L_0x0027:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x002f
            r2 = 0
            goto L_0x0043
        L_0x002f:
            java.lang.String r3 = "com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r5 = r3 instanceof com.google.android.gms.internal.zzmp
            if (r5 == 0) goto L_0x003d
            r2 = r3
            com.google.android.gms.internal.zzmp r2 = (com.google.android.gms.internal.zzmp) r2
            goto L_0x0043
        L_0x003d:
            com.google.android.gms.internal.zzmr r3 = new com.google.android.gms.internal.zzmr
            r3.<init>(r2)
            r2 = r3
        L_0x0043:
            r1.zza(r2)
            goto L_0x007c
        L_0x0047:
            float r2 = r1.zzir()
            goto L_0x0050
        L_0x004c:
            float r2 = r1.zziq()
        L_0x0050:
            r4.writeNoException()
            r4.writeFloat(r2)
            goto L_0x007f
        L_0x0057:
            int r2 = r1.getPlaybackState()
            r4.writeNoException()
            r4.writeInt(r2)
            goto L_0x007f
        L_0x0062:
            boolean r2 = r1.isMuted()
        L_0x0066:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (boolean) r2)
            goto L_0x007f
        L_0x006d:
            boolean r2 = com.google.android.gms.internal.zzex.zza(r3)
            r1.mute(r2)
            goto L_0x007c
        L_0x0075:
            r1.pause()
            goto L_0x007c
        L_0x0079:
            r1.play()
        L_0x007c:
            r4.writeNoException()
        L_0x007f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmn.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
