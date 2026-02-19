package com.google.android.gms.internal;

public abstract class zzwj extends zzew implements zzwi {
    public zzwj() {
        attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    /* JADX WARNING: type inference failed for: r12v3, types: [android.os.IInterface] */
    /* JADX WARNING: type inference failed for: r12v4, types: [android.os.IInterface] */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007a, code lost:
        r13.writeNoException();
        com.google.android.gms.internal.zzex.zzb(r13, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d7, code lost:
        r13.writeNoException();
        com.google.android.gms.internal.zzex.zza(r13, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01a6, code lost:
        r13.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01de, code lost:
        r13.writeNoException();
        com.google.android.gms.internal.zzex.zza(r13, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0222, code lost:
        return true;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTransact(int r11, android.os.Parcel r12, android.os.Parcel r13, int r14) throws android.os.RemoteException {
        /*
            r10 = this;
            boolean r14 = r10.zza(r11, r12, r13, r14)
            r0 = 1
            if (r14 == 0) goto L_0x0008
            return r0
        L_0x0008:
            r14 = 0
            java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener"
            switch(r11) {
                case 1: goto L_0x01e5;
                case 2: goto L_0x01da;
                case 3: goto L_0x01ab;
                case 4: goto L_0x01a3;
                case 5: goto L_0x019f;
                case 6: goto L_0x015e;
                case 7: goto L_0x0126;
                case 8: goto L_0x0121;
                case 9: goto L_0x011c;
                case 10: goto L_0x00f5;
                case 11: goto L_0x00e4;
                case 12: goto L_0x00df;
                case 13: goto L_0x00d3;
                case 14: goto L_0x008e;
                case 15: goto L_0x0088;
                case 16: goto L_0x0082;
                case 17: goto L_0x0076;
                case 18: goto L_0x0071;
                case 19: goto L_0x006c;
                case 20: goto L_0x0057;
                case 21: goto L_0x004a;
                case 22: goto L_0x0044;
                case 23: goto L_0x002b;
                case 24: goto L_0x0025;
                case 25: goto L_0x001c;
                case 26: goto L_0x0016;
                case 27: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            r11 = 0
            return r11
        L_0x0010:
            com.google.android.gms.internal.zzwx r11 = r10.zzmv()
            goto L_0x01de
        L_0x0016:
            com.google.android.gms.internal.zzmm r11 = r10.getVideoController()
            goto L_0x01de
        L_0x001c:
            boolean r11 = com.google.android.gms.internal.zzex.zza(r12)
            r10.setImmersiveMode(r11)
            goto L_0x01a6
        L_0x0025:
            com.google.android.gms.internal.zzro r11 = r10.zzmu()
            goto L_0x01de
        L_0x002b:
            android.os.IBinder r11 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r11 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r11)
            android.os.IBinder r14 = r12.readStrongBinder()
            com.google.android.gms.internal.zzafz r14 = com.google.android.gms.internal.zzaga.zzab(r14)
            java.util.ArrayList r12 = r12.createStringArrayList()
            r10.zza((com.google.android.gms.dynamic.IObjectWrapper) r11, (com.google.android.gms.internal.zzafz) r14, (java.util.List<java.lang.String>) r12)
            goto L_0x01a6
        L_0x0044:
            boolean r11 = r10.zzmt()
            goto L_0x00d7
        L_0x004a:
            android.os.IBinder r11 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r11 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r11)
            r10.zzg(r11)
            goto L_0x01a6
        L_0x0057:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzkk> r11 = com.google.android.gms.internal.zzkk.CREATOR
            android.os.Parcelable r11 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r12, r11)
            com.google.android.gms.internal.zzkk r11 = (com.google.android.gms.internal.zzkk) r11
            java.lang.String r14 = r12.readString()
            java.lang.String r12 = r12.readString()
            r10.zza((com.google.android.gms.internal.zzkk) r11, (java.lang.String) r14, (java.lang.String) r12)
            goto L_0x01a6
        L_0x006c:
            android.os.Bundle r11 = r10.zzms()
            goto L_0x007a
        L_0x0071:
            android.os.Bundle r11 = r10.getInterstitialAdapterInfo()
            goto L_0x007a
        L_0x0076:
            android.os.Bundle r11 = r10.zzmr()
        L_0x007a:
            r13.writeNoException()
            com.google.android.gms.internal.zzex.zzb(r13, r11)
            goto L_0x0222
        L_0x0082:
            com.google.android.gms.internal.zzwu r11 = r10.zzmq()
            goto L_0x01de
        L_0x0088:
            com.google.android.gms.internal.zzwr r11 = r10.zzmp()
            goto L_0x01de
        L_0x008e:
            android.os.IBinder r11 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r11)
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzkk> r11 = com.google.android.gms.internal.zzkk.CREATOR
            android.os.Parcelable r11 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r12, r11)
            r4 = r11
            com.google.android.gms.internal.zzkk r4 = (com.google.android.gms.internal.zzkk) r4
            java.lang.String r5 = r12.readString()
            java.lang.String r6 = r12.readString()
            android.os.IBinder r11 = r12.readStrongBinder()
            if (r11 != 0) goto L_0x00af
        L_0x00ad:
            r7 = r14
            goto L_0x00c0
        L_0x00af:
            android.os.IInterface r14 = r11.queryLocalInterface(r1)
            boolean r1 = r14 instanceof com.google.android.gms.internal.zzwl
            if (r1 == 0) goto L_0x00ba
            com.google.android.gms.internal.zzwl r14 = (com.google.android.gms.internal.zzwl) r14
            goto L_0x00ad
        L_0x00ba:
            com.google.android.gms.internal.zzwn r14 = new com.google.android.gms.internal.zzwn
            r14.<init>(r11)
            goto L_0x00ad
        L_0x00c0:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzqh> r11 = com.google.android.gms.internal.zzqh.CREATOR
            android.os.Parcelable r11 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r12, r11)
            r8 = r11
            com.google.android.gms.internal.zzqh r8 = (com.google.android.gms.internal.zzqh) r8
            java.util.ArrayList r9 = r12.createStringArrayList()
            r2 = r10
            r2.zza(r3, r4, r5, r6, r7, r8, r9)
            goto L_0x01a6
        L_0x00d3:
            boolean r11 = r10.isInitialized()
        L_0x00d7:
            r13.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r13, (boolean) r11)
            goto L_0x0222
        L_0x00df:
            r10.showVideo()
            goto L_0x01a6
        L_0x00e4:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzkk> r11 = com.google.android.gms.internal.zzkk.CREATOR
            android.os.Parcelable r11 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r12, r11)
            com.google.android.gms.internal.zzkk r11 = (com.google.android.gms.internal.zzkk) r11
            java.lang.String r12 = r12.readString()
            r10.zzc(r11, r12)
            goto L_0x01a6
        L_0x00f5:
            android.os.IBinder r11 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r11)
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzkk> r11 = com.google.android.gms.internal.zzkk.CREATOR
            android.os.Parcelable r11 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r12, r11)
            r3 = r11
            com.google.android.gms.internal.zzkk r3 = (com.google.android.gms.internal.zzkk) r3
            java.lang.String r4 = r12.readString()
            android.os.IBinder r11 = r12.readStrongBinder()
            com.google.android.gms.internal.zzafz r5 = com.google.android.gms.internal.zzaga.zzab(r11)
            java.lang.String r6 = r12.readString()
            r1 = r10
            r1.zza((com.google.android.gms.dynamic.IObjectWrapper) r2, (com.google.android.gms.internal.zzkk) r3, (java.lang.String) r4, (com.google.android.gms.internal.zzafz) r5, (java.lang.String) r6)
            goto L_0x01a6
        L_0x011c:
            r10.resume()
            goto L_0x01a6
        L_0x0121:
            r10.pause()
            goto L_0x01a6
        L_0x0126:
            android.os.IBinder r11 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r11)
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzkk> r11 = com.google.android.gms.internal.zzkk.CREATOR
            android.os.Parcelable r11 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r12, r11)
            r4 = r11
            com.google.android.gms.internal.zzkk r4 = (com.google.android.gms.internal.zzkk) r4
            java.lang.String r5 = r12.readString()
            java.lang.String r6 = r12.readString()
            android.os.IBinder r11 = r12.readStrongBinder()
            if (r11 != 0) goto L_0x0147
        L_0x0145:
            r7 = r14
            goto L_0x0159
        L_0x0147:
            android.os.IInterface r12 = r11.queryLocalInterface(r1)
            boolean r14 = r12 instanceof com.google.android.gms.internal.zzwl
            if (r14 == 0) goto L_0x0153
            r14 = r12
            com.google.android.gms.internal.zzwl r14 = (com.google.android.gms.internal.zzwl) r14
            goto L_0x0145
        L_0x0153:
            com.google.android.gms.internal.zzwn r14 = new com.google.android.gms.internal.zzwn
            r14.<init>(r11)
            goto L_0x0145
        L_0x0159:
            r2 = r10
            r2.zza((com.google.android.gms.dynamic.IObjectWrapper) r3, (com.google.android.gms.internal.zzkk) r4, (java.lang.String) r5, (java.lang.String) r6, (com.google.android.gms.internal.zzwl) r7)
            goto L_0x01a6
        L_0x015e:
            android.os.IBinder r11 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r11)
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzko> r11 = com.google.android.gms.internal.zzko.CREATOR
            android.os.Parcelable r11 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r12, r11)
            r4 = r11
            com.google.android.gms.internal.zzko r4 = (com.google.android.gms.internal.zzko) r4
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzkk> r11 = com.google.android.gms.internal.zzkk.CREATOR
            android.os.Parcelable r11 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r12, r11)
            r5 = r11
            com.google.android.gms.internal.zzkk r5 = (com.google.android.gms.internal.zzkk) r5
            java.lang.String r6 = r12.readString()
            java.lang.String r7 = r12.readString()
            android.os.IBinder r11 = r12.readStrongBinder()
            if (r11 != 0) goto L_0x0188
        L_0x0186:
            r8 = r14
            goto L_0x019a
        L_0x0188:
            android.os.IInterface r12 = r11.queryLocalInterface(r1)
            boolean r14 = r12 instanceof com.google.android.gms.internal.zzwl
            if (r14 == 0) goto L_0x0194
            r14 = r12
            com.google.android.gms.internal.zzwl r14 = (com.google.android.gms.internal.zzwl) r14
            goto L_0x0186
        L_0x0194:
            com.google.android.gms.internal.zzwn r14 = new com.google.android.gms.internal.zzwn
            r14.<init>(r11)
            goto L_0x0186
        L_0x019a:
            r2 = r10
            r2.zza(r3, r4, r5, r6, r7, r8)
            goto L_0x01a6
        L_0x019f:
            r10.destroy()
            goto L_0x01a6
        L_0x01a3:
            r10.showInterstitial()
        L_0x01a6:
            r13.writeNoException()
            goto L_0x0222
        L_0x01ab:
            android.os.IBinder r11 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r11 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r11)
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzkk> r2 = com.google.android.gms.internal.zzkk.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r12, r2)
            com.google.android.gms.internal.zzkk r2 = (com.google.android.gms.internal.zzkk) r2
            java.lang.String r3 = r12.readString()
            android.os.IBinder r12 = r12.readStrongBinder()
            if (r12 != 0) goto L_0x01c6
            goto L_0x01d6
        L_0x01c6:
            android.os.IInterface r14 = r12.queryLocalInterface(r1)
            boolean r1 = r14 instanceof com.google.android.gms.internal.zzwl
            if (r1 == 0) goto L_0x01d1
            com.google.android.gms.internal.zzwl r14 = (com.google.android.gms.internal.zzwl) r14
            goto L_0x01d6
        L_0x01d1:
            com.google.android.gms.internal.zzwn r14 = new com.google.android.gms.internal.zzwn
            r14.<init>(r12)
        L_0x01d6:
            r10.zza(r11, r2, r3, r14)
            goto L_0x01a6
        L_0x01da:
            com.google.android.gms.dynamic.IObjectWrapper r11 = r10.getView()
        L_0x01de:
            r13.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r13, (android.os.IInterface) r11)
            goto L_0x0222
        L_0x01e5:
            android.os.IBinder r11 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzaq(r11)
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzko> r11 = com.google.android.gms.internal.zzko.CREATOR
            android.os.Parcelable r11 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r12, r11)
            r4 = r11
            com.google.android.gms.internal.zzko r4 = (com.google.android.gms.internal.zzko) r4
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzkk> r11 = com.google.android.gms.internal.zzkk.CREATOR
            android.os.Parcelable r11 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r12, r11)
            r5 = r11
            com.google.android.gms.internal.zzkk r5 = (com.google.android.gms.internal.zzkk) r5
            java.lang.String r6 = r12.readString()
            android.os.IBinder r11 = r12.readStrongBinder()
            if (r11 != 0) goto L_0x020b
        L_0x0209:
            r7 = r14
            goto L_0x021d
        L_0x020b:
            android.os.IInterface r12 = r11.queryLocalInterface(r1)
            boolean r14 = r12 instanceof com.google.android.gms.internal.zzwl
            if (r14 == 0) goto L_0x0217
            r14 = r12
            com.google.android.gms.internal.zzwl r14 = (com.google.android.gms.internal.zzwl) r14
            goto L_0x0209
        L_0x0217:
            com.google.android.gms.internal.zzwn r14 = new com.google.android.gms.internal.zzwn
            r14.<init>(r11)
            goto L_0x0209
        L_0x021d:
            r2 = r10
            r2.zza((com.google.android.gms.dynamic.IObjectWrapper) r3, (com.google.android.gms.internal.zzko) r4, (com.google.android.gms.internal.zzkk) r5, (java.lang.String) r6, (com.google.android.gms.internal.zzwl) r7)
            goto L_0x01a6
        L_0x0222:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzwj.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
