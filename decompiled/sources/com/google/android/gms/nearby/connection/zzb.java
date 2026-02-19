package com.google.android.gms.nearby.connection;

import android.os.Parcelable;

public final class zzb implements Parcelable.Creator<AdvertisingOptions> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ AdvertisingOptions createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r11)
            r1 = 0
            r2 = 1
            r4 = r1
            r9 = r4
            r5 = 1
            r6 = 1
            r7 = 1
            r8 = 1
        L_0x000c:
            int r1 = r11.dataPosition()
            if (r1 >= r0) goto L_0x0044
            int r1 = r11.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x003a;
                case 2: goto L_0x0035;
                case 3: goto L_0x0030;
                case 4: goto L_0x002b;
                case 5: goto L_0x0026;
                case 6: goto L_0x0021;
                default: goto L_0x001d;
            }
        L_0x001d:
            com.google.android.gms.internal.zzbgm.zzb(r11, r1)
            goto L_0x000c
        L_0x0021:
            byte[] r9 = com.google.android.gms.internal.zzbgm.zzt(r11, r1)
            goto L_0x000c
        L_0x0026:
            boolean r8 = com.google.android.gms.internal.zzbgm.zzc(r11, r1)
            goto L_0x000c
        L_0x002b:
            boolean r7 = com.google.android.gms.internal.zzbgm.zzc(r11, r1)
            goto L_0x000c
        L_0x0030:
            boolean r6 = com.google.android.gms.internal.zzbgm.zzc(r11, r1)
            goto L_0x000c
        L_0x0035:
            boolean r5 = com.google.android.gms.internal.zzbgm.zzc(r11, r1)
            goto L_0x000c
        L_0x003a:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.connection.Strategy> r2 = com.google.android.gms.nearby.connection.Strategy.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r11, (int) r1, r2)
            r4 = r1
            com.google.android.gms.nearby.connection.Strategy r4 = (com.google.android.gms.nearby.connection.Strategy) r4
            goto L_0x000c
        L_0x0044:
            com.google.android.gms.internal.zzbgm.zzaf(r11, r0)
            com.google.android.gms.nearby.connection.AdvertisingOptions r11 = new com.google.android.gms.nearby.connection.AdvertisingOptions
            r3 = r11
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.nearby.connection.zzb.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ AdvertisingOptions[] newArray(int i) {
        return new AdvertisingOptions[i];
    }
}
