package com.google.android.gms.fitness.data;

import android.os.Parcelable;

public final class zze implements Parcelable.Creator<Bucket> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ Bucket createFromParcel(android.os.Parcel r17) {
        /*
            r16 = this;
            r0 = r17
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r17)
            r2 = 0
            r3 = 0
            r5 = 0
            r11 = r2
            r13 = r11
            r7 = r3
            r9 = r7
            r12 = 0
            r14 = 0
            r15 = 0
        L_0x0011:
            int r2 = r17.dataPosition()
            if (r2 >= r1) goto L_0x0050
            int r2 = r17.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x004b;
                case 2: goto L_0x0046;
                case 3: goto L_0x003c;
                case 4: goto L_0x0037;
                case 5: goto L_0x0030;
                case 6: goto L_0x002b;
                case 7: goto L_0x0026;
                default: goto L_0x0022;
            }
        L_0x0022:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0011
        L_0x0026:
            boolean r15 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x0011
        L_0x002b:
            int r14 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0011
        L_0x0030:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataSet> r3 = com.google.android.gms.fitness.data.DataSet.CREATOR
            java.util.ArrayList r13 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x0011
        L_0x0037:
            int r12 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0011
        L_0x003c:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.Session> r3 = com.google.android.gms.fitness.data.Session.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r11 = r2
            com.google.android.gms.fitness.data.Session r11 = (com.google.android.gms.fitness.data.Session) r11
            goto L_0x0011
        L_0x0046:
            long r9 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0011
        L_0x004b:
            long r7 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0011
        L_0x0050:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.fitness.data.Bucket r0 = new com.google.android.gms.fitness.data.Bucket
            r6 = r0
            r6.<init>(r7, r9, r11, r12, r13, r14, r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.zze.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Bucket[] newArray(int i) {
        return new Bucket[i];
    }
}
