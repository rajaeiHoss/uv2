package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzcuc implements Parcelable.Creator<zzcub> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzcub createFromParcel(android.os.Parcel r17) {
        /*
            r16 = this;
            r0 = r17
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r17)
            r2 = 0
            r4 = 0
            r5 = 0
            r7 = r2
            r13 = r7
            r10 = r4
            r11 = r10
            r12 = r11
            r15 = r12
            r9 = 0
        L_0x0011:
            int r2 = r17.dataPosition()
            if (r2 >= r1) goto L_0x0053
            int r2 = r17.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x004e;
                case 2: goto L_0x0049;
                case 3: goto L_0x0044;
                case 4: goto L_0x003a;
                case 5: goto L_0x0035;
                case 6: goto L_0x0030;
                case 7: goto L_0x0026;
                default: goto L_0x0022;
            }
        L_0x0022:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0011
        L_0x0026:
            android.os.Parcelable$Creator r3 = android.os.ParcelFileDescriptor.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r15 = r2
            android.os.ParcelFileDescriptor r15 = (android.os.ParcelFileDescriptor) r15
            goto L_0x0011
        L_0x0030:
            long r13 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0011
        L_0x0035:
            java.lang.String r12 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0011
        L_0x003a:
            android.os.Parcelable$Creator r3 = android.os.ParcelFileDescriptor.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r11 = r2
            android.os.ParcelFileDescriptor r11 = (android.os.ParcelFileDescriptor) r11
            goto L_0x0011
        L_0x0044:
            byte[] r10 = com.google.android.gms.internal.zzbgm.zzt(r0, r2)
            goto L_0x0011
        L_0x0049:
            int r9 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0011
        L_0x004e:
            long r7 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0011
        L_0x0053:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.internal.zzcub r0 = new com.google.android.gms.internal.zzcub
            r6 = r0
            r6.<init>(r7, r9, r10, r11, r12, r13, r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcuc.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzcub[] newArray(int i) {
        return new zzcub[i];
    }
}
