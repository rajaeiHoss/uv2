package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzrp extends zzew implements zzro {
    public zzrp() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    public static zzro zzn(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        return queryLocalInterface instanceof zzro ? (zzro) queryLocalInterface : new zzrq(iBinder);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003e, code lost:
        r4.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005a, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0069, code lost:
        r4.writeNoException();
        r4.writeString(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006f, code lost:
        return true;
     */
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
                case 1: goto L_0x0061;
                case 2: goto L_0x0052;
                case 3: goto L_0x0047;
                case 4: goto L_0x0042;
                case 5: goto L_0x0037;
                case 6: goto L_0x0033;
                case 7: goto L_0x002e;
                case 8: goto L_0x002a;
                case 9: goto L_0x0025;
                case 10: goto L_0x0012;
                case 11: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r2 = 0
            return r2
        L_0x000d:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzkd()
            goto L_0x005a
        L_0x0012:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            boolean r2 = r1.zzf(r2)
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (boolean) r2)
            goto L_0x006f
        L_0x0025:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzkk()
            goto L_0x005a
        L_0x002a:
            r1.destroy()
            goto L_0x003e
        L_0x002e:
            com.google.android.gms.internal.zzmm r2 = r1.getVideoController()
            goto L_0x005a
        L_0x0033:
            r1.recordImpression()
            goto L_0x003e
        L_0x0037:
            java.lang.String r2 = r3.readString()
            r1.performClick(r2)
        L_0x003e:
            r4.writeNoException()
            goto L_0x006f
        L_0x0042:
            java.lang.String r2 = r1.getCustomTemplateId()
            goto L_0x0069
        L_0x0047:
            java.util.List r2 = r1.getAvailableAssetNames()
            r4.writeNoException()
            r4.writeStringList(r2)
            goto L_0x006f
        L_0x0052:
            java.lang.String r2 = r3.readString()
            com.google.android.gms.internal.zzqs r2 = r1.zzaq(r2)
        L_0x005a:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (android.os.IInterface) r2)
            goto L_0x006f
        L_0x0061:
            java.lang.String r2 = r3.readString()
            java.lang.String r2 = r1.zzap(r2)
        L_0x0069:
            r4.writeNoException()
            r4.writeString(r2)
        L_0x006f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzrp.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
