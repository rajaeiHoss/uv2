package com.google.android.gms.internal;

public abstract class zzwv extends zzew implements zzwu {
    public zzwv() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0063, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0085, code lost:
        r4.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0097, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b2, code lost:
        r4.writeNoException();
        r4.writeString(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b8, code lost:
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
                case 2: goto L_0x00ae;
                case 3: goto L_0x00a3;
                case 4: goto L_0x009e;
                case 5: goto L_0x0093;
                case 6: goto L_0x008e;
                case 7: goto L_0x0089;
                case 8: goto L_0x0082;
                case 9: goto L_0x0076;
                case 10: goto L_0x006a;
                case 11: goto L_0x005f;
                case 12: goto L_0x005a;
                case 13: goto L_0x004f;
                case 14: goto L_0x0043;
                case 15: goto L_0x003e;
                case 16: goto L_0x0039;
                case 17: goto L_0x000b;
                case 18: goto L_0x000b;
                case 19: goto L_0x0034;
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
            goto L_0x0085
        L_0x0029:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzkh()
            goto L_0x0097
        L_0x002f:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzmx()
            goto L_0x0097
        L_0x0034:
            com.google.android.gms.internal.zzqo r2 = r1.zzki()
            goto L_0x0097
        L_0x0039:
            com.google.android.gms.internal.zzmm r2 = r1.getVideoController()
            goto L_0x0097
        L_0x003e:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzmw()
            goto L_0x0097
        L_0x0043:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            r1.zzj(r2)
            goto L_0x0085
        L_0x004f:
            android.os.Bundle r2 = r1.getExtras()
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zzb(r4, r2)
            goto L_0x00b8
        L_0x005a:
            boolean r2 = r1.getOverrideClickHandling()
            goto L_0x0063
        L_0x005f:
            boolean r2 = r1.getOverrideImpressionRecording()
        L_0x0063:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (boolean) r2)
            goto L_0x00b8
        L_0x006a:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            r1.zzi(r2)
            goto L_0x0085
        L_0x0076:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r2)
            r1.zzh(r2)
            goto L_0x0085
        L_0x0082:
            r1.recordImpression()
        L_0x0085:
            r4.writeNoException()
            goto L_0x00b8
        L_0x0089:
            java.lang.String r2 = r1.getAdvertiser()
            goto L_0x00b2
        L_0x008e:
            java.lang.String r2 = r1.getCallToAction()
            goto L_0x00b2
        L_0x0093:
            com.google.android.gms.internal.zzqs r2 = r1.zzkj()
        L_0x0097:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (android.os.IInterface) r2)
            goto L_0x00b8
        L_0x009e:
            java.lang.String r2 = r1.getBody()
            goto L_0x00b2
        L_0x00a3:
            java.util.List r2 = r1.getImages()
            r4.writeNoException()
            r4.writeList(r2)
            goto L_0x00b8
        L_0x00ae:
            java.lang.String r2 = r1.getHeadline()
        L_0x00b2:
            r4.writeNoException()
            r4.writeString(r2)
        L_0x00b8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzwv.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
