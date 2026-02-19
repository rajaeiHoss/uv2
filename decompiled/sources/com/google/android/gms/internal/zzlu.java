package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzlu extends zzew implements zzlt {
    public zzlu() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IAdManager");
    }

    public static zzlt zzf(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        return queryLocalInterface instanceof zzlt ? (zzlt) queryLocalInterface : new zzlv(iBinder);
    }

    /* JADX WARNING: type inference failed for: r5v2 */
    /* JADX WARNING: type inference failed for: r5v3, types: [com.google.android.gms.internal.zzli] */
    /* JADX WARNING: type inference failed for: r5v8, types: [com.google.android.gms.internal.zzly] */
    /* JADX WARNING: type inference failed for: r5v13, types: [com.google.android.gms.internal.zzlf] */
    /* JADX WARNING: type inference failed for: r5v18, types: [com.google.android.gms.internal.zzme] */
    /* JADX WARNING: type inference failed for: r5v23 */
    /* JADX WARNING: type inference failed for: r5v24 */
    /* JADX WARNING: type inference failed for: r5v25 */
    /* JADX WARNING: type inference failed for: r5v26 */
    /* JADX WARNING: type inference failed for: r5v27 */
    /* JADX WARNING: type inference failed for: r5v28 */
    /* JADX WARNING: type inference failed for: r5v29 */
    /* JADX WARNING: type inference failed for: r5v30 */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c3, code lost:
        r4.writeNoException();
        r4.writeString(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0165, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x016f, code lost:
        r4.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0177, code lost:
        r4.writeNoException();
        com.google.android.gms.internal.zzex.zza(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x017d, code lost:
        return true;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r2, android.os.Parcel r3, android.os.Parcel r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            boolean r5 = r1.zza(r2, r3, r4, r5)
            r0 = 1
            if (r5 == 0) goto L_0x0008
            return r0
        L_0x0008:
            r5 = 0
            switch(r2) {
                case 1: goto L_0x0173;
                case 2: goto L_0x016c;
                case 3: goto L_0x0161;
                case 4: goto L_0x0154;
                case 5: goto L_0x0150;
                case 6: goto L_0x014c;
                case 7: goto L_0x012e;
                case 8: goto L_0x0110;
                case 9: goto L_0x010c;
                case 10: goto L_0x0107;
                case 11: goto L_0x0102;
                case 12: goto L_0x00f6;
                case 13: goto L_0x00e9;
                case 14: goto L_0x00dc;
                case 15: goto L_0x00cb;
                case 16: goto L_0x000c;
                case 17: goto L_0x000c;
                case 18: goto L_0x00bf;
                case 19: goto L_0x00b2;
                case 20: goto L_0x0093;
                case 21: goto L_0x0074;
                case 22: goto L_0x006b;
                case 23: goto L_0x0065;
                case 24: goto L_0x0058;
                case 25: goto L_0x004f;
                case 26: goto L_0x0049;
                case 27: goto L_0x000c;
                case 28: goto L_0x000c;
                case 29: goto L_0x003c;
                case 30: goto L_0x002f;
                case 31: goto L_0x0029;
                case 32: goto L_0x0023;
                case 33: goto L_0x001d;
                case 34: goto L_0x0014;
                case 35: goto L_0x000e;
                default: goto L_0x000c;
            }
        L_0x000c:
            r2 = 0
            return r2
        L_0x000e:
            java.lang.String r2 = r1.zzco()
            goto L_0x00c3
        L_0x0014:
            boolean r2 = com.google.android.gms.internal.zzex.zza(r3)
            r1.setImmersiveMode(r2)
            goto L_0x016f
        L_0x001d:
            com.google.android.gms.internal.zzli r2 = r1.zzcd()
            goto L_0x0177
        L_0x0023:
            com.google.android.gms.internal.zzly r2 = r1.zzcc()
            goto L_0x0177
        L_0x0029:
            java.lang.String r2 = r1.getAdUnitId()
            goto L_0x00c3
        L_0x002f:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzms> r2 = com.google.android.gms.internal.zzms.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            com.google.android.gms.internal.zzms r2 = (com.google.android.gms.internal.zzms) r2
            r1.zza((com.google.android.gms.internal.zzms) r2)
            goto L_0x016f
        L_0x003c:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzns> r2 = com.google.android.gms.internal.zzns.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            com.google.android.gms.internal.zzns r2 = (com.google.android.gms.internal.zzns) r2
            r1.zza((com.google.android.gms.internal.zzns) r2)
            goto L_0x016f
        L_0x0049:
            com.google.android.gms.internal.zzmm r2 = r1.getVideoController()
            goto L_0x0177
        L_0x004f:
            java.lang.String r2 = r3.readString()
            r1.setUserId(r2)
            goto L_0x016f
        L_0x0058:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.internal.zzafc r2 = com.google.android.gms.internal.zzafd.zzaa(r2)
            r1.zza((com.google.android.gms.internal.zzafc) r2)
            goto L_0x016f
        L_0x0065:
            boolean r2 = r1.isLoading()
            goto L_0x0165
        L_0x006b:
            boolean r2 = com.google.android.gms.internal.zzex.zza(r3)
            r1.setManualImpressionsEnabled(r2)
            goto L_0x016f
        L_0x0074:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x007b
            goto L_0x008e
        L_0x007b:
            java.lang.String r3 = "com.google.android.gms.ads.internal.client.ICorrelationIdProvider"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r5 = r3 instanceof com.google.android.gms.internal.zzme
            if (r5 == 0) goto L_0x0089
            r5 = r3
            com.google.android.gms.internal.zzme r5 = (com.google.android.gms.internal.zzme) r5
            goto L_0x008e
        L_0x0089:
            com.google.android.gms.internal.zzmg r5 = new com.google.android.gms.internal.zzmg
            r5.<init>(r2)
        L_0x008e:
            r1.zza((com.google.android.gms.internal.zzme) r5)
            goto L_0x016f
        L_0x0093:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x009a
            goto L_0x00ad
        L_0x009a:
            java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdClickListener"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r5 = r3 instanceof com.google.android.gms.internal.zzlf
            if (r5 == 0) goto L_0x00a8
            r5 = r3
            com.google.android.gms.internal.zzlf r5 = (com.google.android.gms.internal.zzlf) r5
            goto L_0x00ad
        L_0x00a8:
            com.google.android.gms.internal.zzlh r5 = new com.google.android.gms.internal.zzlh
            r5.<init>(r2)
        L_0x00ad:
            r1.zza((com.google.android.gms.internal.zzlf) r5)
            goto L_0x016f
        L_0x00b2:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.internal.zzpb r2 = com.google.android.gms.internal.zzpc.zzi(r2)
            r1.zza((com.google.android.gms.internal.zzpb) r2)
            goto L_0x016f
        L_0x00bf:
            java.lang.String r2 = r1.getMediationAdapterClassName()
        L_0x00c3:
            r4.writeNoException()
            r4.writeString(r2)
            goto L_0x017d
        L_0x00cb:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.internal.zzzd r2 = com.google.android.gms.internal.zzze.zzy(r2)
            java.lang.String r3 = r3.readString()
            r1.zza(r2, r3)
            goto L_0x016f
        L_0x00dc:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.internal.zzyx r2 = com.google.android.gms.internal.zzyy.zzw(r2)
            r1.zza((com.google.android.gms.internal.zzyx) r2)
            goto L_0x016f
        L_0x00e9:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzko> r2 = com.google.android.gms.internal.zzko.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            com.google.android.gms.internal.zzko r2 = (com.google.android.gms.internal.zzko) r2
            r1.zza((com.google.android.gms.internal.zzko) r2)
            goto L_0x016f
        L_0x00f6:
            com.google.android.gms.internal.zzko r2 = r1.zzbq()
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zzb(r4, r2)
            goto L_0x017d
        L_0x0102:
            r1.zzbs()
            goto L_0x016f
        L_0x0107:
            r1.stopLoading()
            goto L_0x016f
        L_0x010c:
            r1.showInterstitial()
            goto L_0x016f
        L_0x0110:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x0117
            goto L_0x012a
        L_0x0117:
            java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAppEventListener"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r5 = r3 instanceof com.google.android.gms.internal.zzly
            if (r5 == 0) goto L_0x0125
            r5 = r3
            com.google.android.gms.internal.zzly r5 = (com.google.android.gms.internal.zzly) r5
            goto L_0x012a
        L_0x0125:
            com.google.android.gms.internal.zzma r5 = new com.google.android.gms.internal.zzma
            r5.<init>(r2)
        L_0x012a:
            r1.zza((com.google.android.gms.internal.zzly) r5)
            goto L_0x016f
        L_0x012e:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x0135
            goto L_0x0148
        L_0x0135:
            java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdListener"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r5 = r3 instanceof com.google.android.gms.internal.zzli
            if (r5 == 0) goto L_0x0143
            r5 = r3
            com.google.android.gms.internal.zzli r5 = (com.google.android.gms.internal.zzli) r5
            goto L_0x0148
        L_0x0143:
            com.google.android.gms.internal.zzlk r5 = new com.google.android.gms.internal.zzlk
            r5.<init>(r2)
        L_0x0148:
            r1.zza((com.google.android.gms.internal.zzli) r5)
            goto L_0x016f
        L_0x014c:
            r1.resume()
            goto L_0x016f
        L_0x0150:
            r1.pause()
            goto L_0x016f
        L_0x0154:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzkk> r2 = com.google.android.gms.internal.zzkk.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            com.google.android.gms.internal.zzkk r2 = (com.google.android.gms.internal.zzkk) r2
            boolean r2 = r1.zzb(r2)
            goto L_0x0165
        L_0x0161:
            boolean r2 = r1.isReady()
        L_0x0165:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (boolean) r2)
            goto L_0x017d
        L_0x016c:
            r1.destroy()
        L_0x016f:
            r4.writeNoException()
            goto L_0x017d
        L_0x0173:
            com.google.android.gms.dynamic.IObjectWrapper r2 = r1.zzbp()
        L_0x0177:
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (android.os.IInterface) r2)
        L_0x017d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlu.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
