package com.google.android.gms.internal;

public abstract class zzsl extends zzew implements zzsk {
    public zzsl() {
        attachInterface(this, "com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004c, code lost:
        r4.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007d, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0098, code lost:
        r4.writeNoException();
        r4.writeString(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009e, code lost:
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
                case 2: goto L_0x0094;
                case 3: goto L_0x0089;
                case 4: goto L_0x0084;
                case 5: goto L_0x0079;
                case 6: goto L_0x0074;
                case 7: goto L_0x006f;
                case 8: goto L_0x0064;
                case 9: goto L_0x005f;
                case 10: goto L_0x005a;
                case 11: goto L_0x0055;
                case 12: goto L_0x0050;
                case 13: goto L_0x0049;
                case 14: goto L_0x0044;
                case 15: goto L_0x0038;
                case 16: goto L_0x0024;
                case 17: goto L_0x0018;
                case 18: goto L_0x0013;
                case 19: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            r2 = 0
            return r2
        L_0x000d:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzkh()
            goto L_0x007d
        L_0x0013:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzkd()
            goto L_0x007d
        L_0x0018:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            r1.reportTouchEvent(r2)
            goto L_0x004c
        L_0x0024:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            boolean r2 = r1.recordImpression(r2)
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (boolean) r2)
            goto L_0x009e
        L_0x0038:
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            r1.performClick(r2)
            goto L_0x004c
        L_0x0044:
            com.google.android.gms.internal.zzqo r2 = r1.zzki()
            goto L_0x007d
        L_0x0049:
            r1.destroy()
        L_0x004c:
            r4.writeNoException()
            goto L_0x009e
        L_0x0050:
            java.lang.String r2 = r1.getMediationAdapterClassName()
            goto L_0x0098
        L_0x0055:
            com.google.android.gms.internal.zzmm r2 = r1.getVideoController()
            goto L_0x007d
        L_0x005a:
            java.lang.String r2 = r1.getPrice()
            goto L_0x0098
        L_0x005f:
            java.lang.String r2 = r1.getStore()
            goto L_0x0098
        L_0x0064:
            double r2 = r1.getStarRating()
            r4.writeNoException()
            r4.writeDouble(r2)
            goto L_0x009e
        L_0x006f:
            java.lang.String r2 = r1.getAdvertiser()
            goto L_0x0098
        L_0x0074:
            java.lang.String r2 = r1.getCallToAction()
            goto L_0x0098
        L_0x0079:
            com.google.android.gms.internal.zzqs r2 = r1.zzkc()
        L_0x007d:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (android.os.IInterface) r2)
            goto L_0x009e
        L_0x0084:
            java.lang.String r2 = r1.getBody()
            goto L_0x0098
        L_0x0089:
            java.util.List r2 = r1.getImages()
            r4.writeNoException()
            r4.writeList(r2)
            goto L_0x009e
        L_0x0094:
            java.lang.String r2 = r1.getHeadline()
        L_0x0098:
            r4.writeNoException()
            r4.writeString(r2)
        L_0x009e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsl.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
