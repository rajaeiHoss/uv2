package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzcul implements Parcelable.Creator<zzcuk> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzcuk createFromParcel(android.os.Parcel r14) {
        /*
            r13 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r14)
            r1 = 0
            r2 = 0
            r5 = r1
            r6 = r5
            r7 = r6
            r8 = r7
            r11 = r8
            r12 = r11
            r9 = r2
        L_0x000e:
            int r1 = r14.dataPosition()
            if (r1 >= r0) goto L_0x004b
            int r1 = r14.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x0046;
                case 2: goto L_0x0041;
                case 3: goto L_0x003c;
                case 4: goto L_0x0037;
                case 5: goto L_0x0032;
                case 6: goto L_0x0028;
                case 7: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.internal.zzbgm.zzb(r14, r1)
            goto L_0x000e
        L_0x0023:
            android.os.IBinder r12 = com.google.android.gms.internal.zzbgm.zzr(r14, r1)
            goto L_0x000e
        L_0x0028:
            android.os.Parcelable$Creator<com.google.android.gms.nearby.connection.AdvertisingOptions> r2 = com.google.android.gms.nearby.connection.AdvertisingOptions.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r2)
            r11 = r1
            com.google.android.gms.nearby.connection.AdvertisingOptions r11 = (com.google.android.gms.nearby.connection.AdvertisingOptions) r11
            goto L_0x000e
        L_0x0032:
            long r9 = com.google.android.gms.internal.zzbgm.zzi(r14, r1)
            goto L_0x000e
        L_0x0037:
            java.lang.String r8 = com.google.android.gms.internal.zzbgm.zzq(r14, r1)
            goto L_0x000e
        L_0x003c:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r14, r1)
            goto L_0x000e
        L_0x0041:
            android.os.IBinder r6 = com.google.android.gms.internal.zzbgm.zzr(r14, r1)
            goto L_0x000e
        L_0x0046:
            android.os.IBinder r5 = com.google.android.gms.internal.zzbgm.zzr(r14, r1)
            goto L_0x000e
        L_0x004b:
            com.google.android.gms.internal.zzbgm.zzaf(r14, r0)
            com.google.android.gms.internal.zzcuk r14 = new com.google.android.gms.internal.zzcuk
            r4 = r14
            r4.<init>((android.os.IBinder) r5, (android.os.IBinder) r6, (java.lang.String) r7, (java.lang.String) r8, (long) r9, (com.google.android.gms.nearby.connection.AdvertisingOptions) r11, (android.os.IBinder) r12)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcul.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzcuk[] newArray(int i) {
        return new zzcuk[i];
    }
}
