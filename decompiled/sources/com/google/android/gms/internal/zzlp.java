package com.google.android.gms.internal;

public abstract class zzlp extends zzew implements zzlo {
    public zzlp() {
        attachInterface(this, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    /* JADX WARNING: type inference failed for: r5v2 */
    /* JADX WARNING: type inference failed for: r5v3, types: [com.google.android.gms.internal.zzli] */
    /* JADX WARNING: type inference failed for: r5v10, types: [com.google.android.gms.internal.zzme] */
    /* JADX WARNING: type inference failed for: r5v16 */
    /* JADX WARNING: type inference failed for: r5v17 */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: type inference failed for: r5v19 */
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
                case 1: goto L_0x00b7;
                case 2: goto L_0x0099;
                case 3: goto L_0x008a;
                case 4: goto L_0x007e;
                case 5: goto L_0x0066;
                case 6: goto L_0x005a;
                case 7: goto L_0x003c;
                case 8: goto L_0x0028;
                case 9: goto L_0x001b;
                case 10: goto L_0x000e;
                default: goto L_0x000c;
            }
        L_0x000c:
            r2 = 0
            return r2
        L_0x000e:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.internal.zzsh r2 = com.google.android.gms.internal.zzsi.zzt(r2)
            r1.zza((com.google.android.gms.internal.zzsh) r2)
            goto L_0x0095
        L_0x001b:
            android.os.Parcelable$Creator<com.google.android.gms.ads.formats.PublisherAdViewOptions> r2 = com.google.android.gms.ads.formats.PublisherAdViewOptions.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            com.google.android.gms.ads.formats.PublisherAdViewOptions r2 = (com.google.android.gms.ads.formats.PublisherAdViewOptions) r2
            r1.zza((com.google.android.gms.ads.formats.PublisherAdViewOptions) r2)
            goto L_0x0095
        L_0x0028:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.internal.zzse r2 = com.google.android.gms.internal.zzsf.zzs(r2)
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzko> r5 = com.google.android.gms.internal.zzko.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r5)
            com.google.android.gms.internal.zzko r3 = (com.google.android.gms.internal.zzko) r3
            r1.zza(r2, r3)
            goto L_0x0095
        L_0x003c:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x0043
            goto L_0x0056
        L_0x0043:
            java.lang.String r3 = "com.google.android.gms.ads.internal.client.ICorrelationIdProvider"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r5 = r3 instanceof com.google.android.gms.internal.zzme
            if (r5 == 0) goto L_0x0051
            r5 = r3
            com.google.android.gms.internal.zzme r5 = (com.google.android.gms.internal.zzme) r5
            goto L_0x0056
        L_0x0051:
            com.google.android.gms.internal.zzmg r5 = new com.google.android.gms.internal.zzmg
            r5.<init>(r2)
        L_0x0056:
            r1.zzb((com.google.android.gms.internal.zzme) r5)
            goto L_0x0095
        L_0x005a:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzqh> r2 = com.google.android.gms.internal.zzqh.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzex.zza((android.os.Parcel) r3, r2)
            com.google.android.gms.internal.zzqh r2 = (com.google.android.gms.internal.zzqh) r2
            r1.zza((com.google.android.gms.internal.zzqh) r2)
            goto L_0x0095
        L_0x0066:
            java.lang.String r2 = r3.readString()
            android.os.IBinder r5 = r3.readStrongBinder()
            com.google.android.gms.internal.zzsb r5 = com.google.android.gms.internal.zzsc.zzr(r5)
            android.os.IBinder r3 = r3.readStrongBinder()
            com.google.android.gms.internal.zzry r3 = com.google.android.gms.internal.zzrz.zzq(r3)
            r1.zza(r2, r5, r3)
            goto L_0x0095
        L_0x007e:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.internal.zzrv r2 = com.google.android.gms.internal.zzrw.zzp(r2)
            r1.zza((com.google.android.gms.internal.zzrv) r2)
            goto L_0x0095
        L_0x008a:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.internal.zzrs r2 = com.google.android.gms.internal.zzrt.zzo(r2)
            r1.zza((com.google.android.gms.internal.zzrs) r2)
        L_0x0095:
            r4.writeNoException()
            goto L_0x00c1
        L_0x0099:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x00a0
            goto L_0x00b3
        L_0x00a0:
            java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdListener"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r5 = r3 instanceof com.google.android.gms.internal.zzli
            if (r5 == 0) goto L_0x00ae
            r5 = r3
            com.google.android.gms.internal.zzli r5 = (com.google.android.gms.internal.zzli) r5
            goto L_0x00b3
        L_0x00ae:
            com.google.android.gms.internal.zzlk r5 = new com.google.android.gms.internal.zzlk
            r5.<init>(r2)
        L_0x00b3:
            r1.zzb((com.google.android.gms.internal.zzli) r5)
            goto L_0x0095
        L_0x00b7:
            com.google.android.gms.internal.zzll r2 = r1.zzdi()
            r4.writeNoException()
            com.google.android.gms.internal.zzex.zza((android.os.Parcel) r4, (android.os.IInterface) r2)
        L_0x00c1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlp.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
