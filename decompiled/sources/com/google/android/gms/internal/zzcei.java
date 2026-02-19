package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzcei implements Parcelable.Creator<zzceh> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzceh createFromParcel(android.os.Parcel r21) {
        /*
            r20 = this;
            r0 = r21
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r21)
            r2 = 0
            r3 = 0
            r6 = r3
            r8 = r6
            r9 = r8
            r10 = r9
            r11 = r10
            r13 = r11
            r14 = r13
            r17 = r14
            r18 = r17
            r19 = r18
            r5 = 0
            r7 = 0
            r12 = 0
            r15 = 0
            r16 = 0
        L_0x001b:
            int r2 = r21.dataPosition()
            if (r2 >= r1) goto L_0x008f
            int r2 = r21.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 2: goto L_0x008a;
                case 3: goto L_0x0085;
                case 4: goto L_0x0080;
                case 5: goto L_0x0076;
                case 6: goto L_0x006c;
                case 7: goto L_0x002c;
                case 8: goto L_0x0062;
                case 9: goto L_0x0058;
                case 10: goto L_0x0053;
                case 11: goto L_0x004e;
                case 12: goto L_0x0049;
                case 13: goto L_0x0044;
                case 14: goto L_0x003f;
                case 15: goto L_0x003a;
                case 16: goto L_0x0035;
                case 17: goto L_0x0030;
                default: goto L_0x002c;
            }
        L_0x002c:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x001b
        L_0x0030:
            byte[] r18 = com.google.android.gms.internal.zzbgm.zzt(r0, r2)
            goto L_0x001b
        L_0x0035:
            int r16 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001b
        L_0x003a:
            android.os.Bundle r19 = com.google.android.gms.internal.zzbgm.zzs(r0, r2)
            goto L_0x001b
        L_0x003f:
            java.lang.String r17 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001b
        L_0x0044:
            int r15 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001b
        L_0x0049:
            java.lang.String r14 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001b
        L_0x004e:
            byte[] r13 = com.google.android.gms.internal.zzbgm.zzt(r0, r2)
            goto L_0x001b
        L_0x0053:
            boolean r12 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x001b
        L_0x0058:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcfd> r3 = com.google.android.gms.internal.zzcfd.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r11 = r2
            com.google.android.gms.internal.zzcfd r11 = (com.google.android.gms.internal.zzcfd) r11
            goto L_0x001b
        L_0x0062:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcdv> r3 = com.google.android.gms.internal.zzcdv.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r10 = r2
            com.google.android.gms.internal.zzcdv r10 = (com.google.android.gms.internal.zzcdv) r10
            goto L_0x001b
        L_0x006c:
            android.os.Parcelable$Creator r3 = android.content.Intent.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r9 = r2
            android.content.Intent r9 = (android.content.Intent) r9
            goto L_0x001b
        L_0x0076:
            android.os.Parcelable$Creator r3 = android.content.Intent.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r8 = r2
            android.content.Intent r8 = (android.content.Intent) r8
            goto L_0x001b
        L_0x0080:
            boolean r7 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x001b
        L_0x0085:
            java.lang.String r6 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001b
        L_0x008a:
            int r5 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001b
        L_0x008f:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.internal.zzceh r0 = new com.google.android.gms.internal.zzceh
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcei.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzceh[] newArray(int i) {
        return new zzceh[i];
    }
}
