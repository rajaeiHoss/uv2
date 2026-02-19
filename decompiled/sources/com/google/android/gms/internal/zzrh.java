package com.google.android.gms.internal;

public abstract class zzrh extends zzew implements zzrg {
    public zzrh() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0053, code lost:
        r4.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0095, code lost:
        r4.writeNoException();
        r4.writeString(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a0, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a6, code lost:
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
                case 2: goto L_0x009c;
                case 3: goto L_0x0091;
                case 4: goto L_0x0086;
                case 5: goto L_0x0081;
                case 6: goto L_0x007c;
                case 7: goto L_0x0077;
                case 8: goto L_0x006c;
                case 9: goto L_0x0067;
                case 10: goto L_0x0062;
                case 11: goto L_0x0057;
                case 12: goto L_0x0050;
                case 13: goto L_0x004b;
                case 14: goto L_0x003f;
                case 15: goto L_0x002b;
                case 16: goto L_0x001f;
                case 17: goto L_0x0019;
                case 18: goto L_0x0013;
                case 19: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r2 = 0
            return r2
        L_0x000d:
            java.lang.String r2 = r1.getMediationAdapterClassName()
            goto L_0x0095
        L_0x0013:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzkh()
            goto L_0x00a0
        L_0x0019:
            com.google.android.gms.internal.zzqo r2 = r1.zzki()
            goto L_0x00a0
        L_0x001f:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            r1.reportTouchEvent(r2)
            goto L_0x0053
        L_0x002b:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            boolean r2 = r1.recordImpression(r2)
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (boolean) r2)
            goto L_0x00a6
        L_0x003f:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            r1.performClick(r2)
            goto L_0x0053
        L_0x004b:
            com.google.android.gms.internal.zzmm r2 = r1.getVideoController()
            goto L_0x00a0
        L_0x0050:
            r1.destroy()
        L_0x0053:
            r4.writeNoException()
            goto L_0x00a6
        L_0x0057:
            android.os.Bundle r2 = r1.getExtras()
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zzb(r4, r2)
            goto L_0x00a6
        L_0x0062:
            java.lang.String r2 = r1.getPrice()
            goto L_0x0095
        L_0x0067:
            java.lang.String r2 = r1.getStore()
            goto L_0x0095
        L_0x006c:
            double r2 = r1.getStarRating()
            r4.writeNoException()
            r4.writeDouble(r2)
            goto L_0x00a6
        L_0x0077:
            java.lang.String r2 = r1.getCallToAction()
            goto L_0x0095
        L_0x007c:
            com.google.android.gms.internal.zzqs r2 = r1.zzkc()
            goto L_0x00a0
        L_0x0081:
            java.lang.String r2 = r1.getBody()
            goto L_0x0095
        L_0x0086:
            java.util.List r2 = r1.getImages()
            r4.writeNoException()
            r4.writeList(r2)
            goto L_0x00a6
        L_0x0091:
            java.lang.String r2 = r1.getHeadline()
        L_0x0095:
            r4.writeNoException()
            r4.writeString(r2)
            goto L_0x00a6
        L_0x009c:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzkd()
        L_0x00a0:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (android.os.IInterface) r2)
        L_0x00a6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzrh.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
