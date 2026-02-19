package com.google.android.gms.internal;

public abstract class zzwy extends zzew implements zzwx {
    public zzwy() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0052, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a1, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00bc, code lost:
        r4.writeNoException();
        r4.writeString(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00c2, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0044, code lost:
        r4.writeNoException();
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
                case 2: goto L_0x00b8;
                case 3: goto L_0x00ad;
                case 4: goto L_0x00a8;
                case 5: goto L_0x009d;
                case 6: goto L_0x0098;
                case 7: goto L_0x0093;
                case 8: goto L_0x0088;
                case 9: goto L_0x0083;
                case 10: goto L_0x007e;
                case 11: goto L_0x0079;
                case 12: goto L_0x0074;
                case 13: goto L_0x006f;
                case 14: goto L_0x006a;
                case 15: goto L_0x0065;
                case 16: goto L_0x005a;
                case 17: goto L_0x004e;
                case 18: goto L_0x0049;
                case 19: goto L_0x0041;
                case 20: goto L_0x0035;
                case 21: goto L_0x0019;
                case 22: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r2 = 0
            return r2
        L_0x000d:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            r1.zzj(r2)
            goto L_0x0044
        L_0x0019:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            android.os.IBinder r5 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r5)
            android.os.IBinder r3 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r3)
            r1.zzb(r2, r5, r3)
            goto L_0x0044
        L_0x0035:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            r1.zzh(r2)
            goto L_0x0044
        L_0x0041:
            r1.recordImpression()
        L_0x0044:
            r4.writeNoException()
            goto L_0x00c2
        L_0x0049:
            boolean r2 = r1.getOverrideClickHandling()
            goto L_0x0052
        L_0x004e:
            boolean r2 = r1.getOverrideImpressionRecording()
        L_0x0052:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (boolean) r2)
            goto L_0x00c2
        L_0x005a:
            android.os.Bundle r2 = r1.getExtras()
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zzb(r4, r2)
            goto L_0x00c2
        L_0x0065:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzkh()
            goto L_0x00a1
        L_0x006a:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzmx()
            goto L_0x00a1
        L_0x006f:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzmw()
            goto L_0x00a1
        L_0x0074:
            com.google.android.gms.internal.zzqo r2 = r1.zzki()
            goto L_0x00a1
        L_0x0079:
            com.google.android.gms.internal.zzmm r2 = r1.getVideoController()
            goto L_0x00a1
        L_0x007e:
            java.lang.String r2 = r1.getPrice()
            goto L_0x00bc
        L_0x0083:
            java.lang.String r2 = r1.getStore()
            goto L_0x00bc
        L_0x0088:
            double r2 = r1.getStarRating()
            r4.writeNoException()
            r4.writeDouble(r2)
            goto L_0x00c2
        L_0x0093:
            java.lang.String r2 = r1.getAdvertiser()
            goto L_0x00bc
        L_0x0098:
            java.lang.String r2 = r1.getCallToAction()
            goto L_0x00bc
        L_0x009d:
            com.google.android.gms.internal.zzqs r2 = r1.zzkc()
        L_0x00a1:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (android.os.IInterface) r2)
            goto L_0x00c2
        L_0x00a8:
            java.lang.String r2 = r1.getBody()
            goto L_0x00bc
        L_0x00ad:
            java.util.List r2 = r1.getImages()
            r4.writeNoException()
            r4.writeList(r2)
            goto L_0x00c2
        L_0x00b8:
            java.lang.String r2 = r1.getHeadline()
        L_0x00bc:
            r4.writeNoException()
            r4.writeString(r2)
        L_0x00c2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzwy.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
