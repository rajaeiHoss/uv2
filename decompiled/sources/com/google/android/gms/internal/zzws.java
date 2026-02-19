package com.google.android.gms.internal;

public abstract class zzws extends zzew implements zzwr {
    public zzws() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0067, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0089, code lost:
        r4.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00ab, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c6, code lost:
        r4.writeNoException();
        r4.writeString(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00cc, code lost:
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
                case 2: goto L_0x00c2;
                case 3: goto L_0x00b7;
                case 4: goto L_0x00b2;
                case 5: goto L_0x00a7;
                case 6: goto L_0x00a2;
                case 7: goto L_0x0097;
                case 8: goto L_0x0092;
                case 9: goto L_0x008d;
                case 10: goto L_0x0086;
                case 11: goto L_0x007a;
                case 12: goto L_0x006e;
                case 13: goto L_0x0063;
                case 14: goto L_0x005e;
                case 15: goto L_0x0052;
                case 16: goto L_0x0046;
                case 17: goto L_0x0041;
                case 18: goto L_0x003b;
                case 19: goto L_0x0035;
                case 20: goto L_0x002f;
                case 21: goto L_0x0029;
                case 22: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r2 = 0
            return r2
        L_0x000d:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            android.os.IBinder r5 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r5)
            android.os.IBinder r3 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r3)
            r1.zzb(r2, r5, r3)
            goto L_0x0089
        L_0x0029:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzkh()
            goto L_0x00ab
        L_0x002f:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzmx()
            goto L_0x00ab
        L_0x0035:
            com.google.android.gms.internal.zzqo r2 = r1.zzki()
            goto L_0x00ab
        L_0x003b:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzmw()
            goto L_0x00ab
        L_0x0041:
            com.google.android.gms.internal.zzmm r2 = r1.getVideoController()
            goto L_0x00ab
        L_0x0046:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            r1.zzj(r2)
            goto L_0x0089
        L_0x0052:
            android.os.Bundle r2 = r1.getExtras()
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zzb(r4, r2)
            goto L_0x00cc
        L_0x005e:
            boolean r2 = r1.getOverrideClickHandling()
            goto L_0x0067
        L_0x0063:
            boolean r2 = r1.getOverrideImpressionRecording()
        L_0x0067:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (boolean) r2)
            goto L_0x00cc
        L_0x006e:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            r1.zzi(r2)
            goto L_0x0089
        L_0x007a:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            r1.zzh(r2)
            goto L_0x0089
        L_0x0086:
            r1.recordImpression()
        L_0x0089:
            r4.writeNoException()
            goto L_0x00cc
        L_0x008d:
            java.lang.String r2 = r1.getPrice()
            goto L_0x00c6
        L_0x0092:
            java.lang.String r2 = r1.getStore()
            goto L_0x00c6
        L_0x0097:
            double r2 = r1.getStarRating()
            r4.writeNoException()
            r4.writeDouble(r2)
            goto L_0x00cc
        L_0x00a2:
            java.lang.String r2 = r1.getCallToAction()
            goto L_0x00c6
        L_0x00a7:
            com.google.android.gms.internal.zzqs r2 = r1.zzkc()
        L_0x00ab:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (android.os.IInterface) r2)
            goto L_0x00cc
        L_0x00b2:
            java.lang.String r2 = r1.getBody()
            goto L_0x00c6
        L_0x00b7:
            java.util.List r2 = r1.getImages()
            r4.writeNoException()
            r4.writeList(r2)
            goto L_0x00cc
        L_0x00c2:
            java.lang.String r2 = r1.getHeadline()
        L_0x00c6:
            r4.writeNoException()
            r4.writeString(r2)
        L_0x00cc:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzws.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
