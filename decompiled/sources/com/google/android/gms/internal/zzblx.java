package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzblx implements Parcelable.Creator<zzblw> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzblw createFromParcel(android.os.Parcel r14) {
        /*
            r13 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r14)
            r1 = 0
            r3 = 0
            r4 = 0
            r9 = r1
            r11 = r9
            r7 = r4
            r6 = 0
            r8 = 0
        L_0x000d:
            int r1 = r14.dataPosition()
            if (r1 >= r0) goto L_0x004c
            int r1 = r14.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            r3 = 2
            if (r2 == r3) goto L_0x0047
            r3 = 3
            if (r2 == r3) goto L_0x003d
            r3 = 4
            if (r2 == r3) goto L_0x0038
            r3 = 5
            if (r2 == r3) goto L_0x0033
            r3 = 6
            if (r2 == r3) goto L_0x002e
            com.google.android.gms.internal.zzbgm.zzb(r14, r1)
            goto L_0x000d
        L_0x002e:
            long r11 = com.google.android.gms.internal.zzbgm.zzi(r14, r1)
            goto L_0x000d
        L_0x0033:
            long r9 = com.google.android.gms.internal.zzbgm.zzi(r14, r1)
            goto L_0x000d
        L_0x0038:
            int r8 = com.google.android.gms.internal.zzbgm.zzg(r14, r1)
            goto L_0x000d
        L_0x003d:
            android.os.Parcelable$Creator<com.google.android.gms.drive.DriveId> r2 = com.google.android.gms.drive.DriveId.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r2)
            r7 = r1
            com.google.android.gms.drive.DriveId r7 = (com.google.android.gms.drive.DriveId) r7
            goto L_0x000d
        L_0x0047:
            int r6 = com.google.android.gms.internal.zzbgm.zzg(r14, r1)
            goto L_0x000d
        L_0x004c:
            com.google.android.gms.internal.zzbgm.zzaf(r14, r0)
            com.google.android.gms.internal.zzblw r14 = new com.google.android.gms.internal.zzblw
            r5 = r14
            r5.<init>(r6, r7, r8, r9, r11)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzblx.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzblw[] newArray(int i) {
        return new zzblw[i];
    }
}
