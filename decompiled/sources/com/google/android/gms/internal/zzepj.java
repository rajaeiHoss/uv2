package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzepj implements Parcelable.Creator<zzepi> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzepi createFromParcel(android.os.Parcel r14) {
        /*
            r13 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r14)
            r1 = 0
            r2 = 0
            r3 = 0
            r6 = r1
            r7 = r6
            r11 = r7
            r12 = r11
            r9 = r3
            r8 = 0
        L_0x000e:
            int r1 = r14.dataPosition()
            if (r1 >= r0) goto L_0x0046
            int r1 = r14.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x0041;
                case 2: goto L_0x003c;
                case 3: goto L_0x0037;
                case 4: goto L_0x0032;
                case 5: goto L_0x002d;
                case 6: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.internal.zzbgm.zzb(r14, r1)
            goto L_0x000e
        L_0x0023:
            android.os.Parcelable$Creator r2 = android.net.Uri.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r2)
            r12 = r1
            android.net.Uri r12 = (android.net.Uri) r12
            goto L_0x000e
        L_0x002d:
            android.os.Bundle r11 = com.google.android.gms.internal.zzbgm.zzs(r14, r1)
            goto L_0x000e
        L_0x0032:
            long r9 = com.google.android.gms.internal.zzbgm.zzi(r14, r1)
            goto L_0x000e
        L_0x0037:
            int r8 = com.google.android.gms.internal.zzbgm.zzg(r14, r1)
            goto L_0x000e
        L_0x003c:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r14, r1)
            goto L_0x000e
        L_0x0041:
            java.lang.String r6 = com.google.android.gms.internal.zzbgm.zzq(r14, r1)
            goto L_0x000e
        L_0x0046:
            com.google.android.gms.internal.zzbgm.zzaf(r14, r0)
            com.google.android.gms.internal.zzepi r14 = new com.google.android.gms.internal.zzepi
            r5 = r14
            r5.<init>(r6, r7, r8, r9, r11, r12)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzepj.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzepi[] newArray(int i) {
        return new zzepi[i];
    }
}
