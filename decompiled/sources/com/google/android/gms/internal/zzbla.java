package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzbla implements Parcelable.Creator<zzbkz> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzbkz createFromParcel(android.os.Parcel r17) {
        /*
            r16 = this;
            r0 = r17
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r17)
            r2 = 0
            r4 = 0
            r5 = 0
            r12 = r2
            r14 = r12
            r8 = r4
            r9 = r8
            r10 = r9
            r11 = r10
            r7 = 0
        L_0x0011:
            int r2 = r17.dataPosition()
            if (r2 >= r1) goto L_0x0053
            int r2 = r17.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 2: goto L_0x004e;
                case 3: goto L_0x0044;
                case 4: goto L_0x003f;
                case 5: goto L_0x0035;
                case 6: goto L_0x0030;
                case 7: goto L_0x002b;
                case 8: goto L_0x0026;
                default: goto L_0x0022;
            }
        L_0x0022:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0011
        L_0x0026:
            long r14 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0011
        L_0x002b:
            long r12 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0011
        L_0x0030:
            java.lang.String r11 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0011
        L_0x0035:
            android.os.Parcelable$Creator r3 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r10 = r2
            android.app.PendingIntent r10 = (android.app.PendingIntent) r10
            goto L_0x0011
        L_0x003f:
            android.os.IBinder r9 = com.google.android.gms.internal.zzbgm.zzr(r0, r2)
            goto L_0x0011
        L_0x0044:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzbkc> r3 = com.google.android.gms.internal.zzbkc.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r8 = r2
            com.google.android.gms.internal.zzbkc r8 = (com.google.android.gms.internal.zzbkc) r8
            goto L_0x0011
        L_0x004e:
            int r7 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0011
        L_0x0053:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.internal.zzbkz r0 = new com.google.android.gms.internal.zzbkz
            r6 = r0
            r6.<init>((int) r7, (com.google.android.gms.internal.zzbkc) r8, (android.os.IBinder) r9, (android.app.PendingIntent) r10, (java.lang.String) r11, (long) r12, (long) r14)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbla.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzbkz[] newArray(int i) {
        return new zzbkz[i];
    }
}
