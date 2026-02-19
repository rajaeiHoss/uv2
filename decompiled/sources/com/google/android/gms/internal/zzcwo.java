package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzcwo implements Parcelable.Creator<zzcwn> {
    /* JADX WARNING: type inference failed for: r2v10, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v15, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v21, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v27, types: [android.os.Parcelable] */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00be, code lost:
        r3.add(java.lang.Integer.valueOf(r2));
        r15 = r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x014f, code lost:
        r3.add(java.lang.Integer.valueOf(r2));
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzcwn createFromParcel(android.os.Parcel r31) {
        /*
            r30 = this;
            r0 = r31
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r31)
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            r2 = 0
            r4 = 0
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r10 = r8
            r11 = r10
            r12 = r11
            r14 = r12
            r15 = r14
            r17 = r15
            r18 = r17
            r19 = r18
            r21 = r19
            r22 = r21
            r25 = r22
            r26 = r25
            r27 = r26
            r4 = 0
            r9 = 0
            r13 = 0
            r16 = 0
            r20 = 0
            r23 = 0
            r24 = 0
            r28 = 0
        L_0x0033:
            int r2 = r31.dataPosition()
            if (r2 >= r1) goto L_0x0158
            int r2 = r31.readInt()
            r29 = 65535(0xffff, float:9.1834E-41)
            r29 = r2 & r29
            switch(r29) {
                case 1: goto L_0x0148;
                case 2: goto L_0x0140;
                case 3: goto L_0x0133;
                case 4: goto L_0x012b;
                case 5: goto L_0x0123;
                case 6: goto L_0x011b;
                case 7: goto L_0x010e;
                case 8: goto L_0x0105;
                case 9: goto L_0x00fc;
                case 10: goto L_0x0045;
                case 11: goto L_0x0045;
                case 12: goto L_0x00f3;
                case 13: goto L_0x0045;
                case 14: goto L_0x00ea;
                case 15: goto L_0x00dd;
                case 16: goto L_0x00d3;
                case 17: goto L_0x0045;
                case 18: goto L_0x00c9;
                case 19: goto L_0x00b0;
                case 20: goto L_0x00a6;
                case 21: goto L_0x009c;
                case 22: goto L_0x0091;
                case 23: goto L_0x0086;
                case 24: goto L_0x007c;
                case 25: goto L_0x0072;
                case 26: goto L_0x0068;
                case 27: goto L_0x005e;
                case 28: goto L_0x0053;
                case 29: goto L_0x004b;
                default: goto L_0x0045;
            }
        L_0x0045:
            r29 = r15
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0033
        L_0x004b:
            boolean r28 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            r2 = 29
            goto L_0x014f
        L_0x0053:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcwn$zzg> r15 = com.google.android.gms.internal.zzcwn.zzg.CREATOR
            java.util.ArrayList r27 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r15)
            r2 = 28
            goto L_0x00be
        L_0x005e:
            r29 = r15
            java.lang.String r26 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            r2 = 27
            goto L_0x014f
        L_0x0068:
            r29 = r15
            java.lang.String r25 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            r2 = 26
            goto L_0x014f
        L_0x0072:
            r29 = r15
            int r24 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            r2 = 25
            goto L_0x014f
        L_0x007c:
            r29 = r15
            int r23 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            r2 = 24
            goto L_0x014f
        L_0x0086:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcwn$zzf> r15 = com.google.android.gms.internal.zzcwn.zzf.CREATOR
            java.util.ArrayList r22 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r15)
            r2 = 23
            goto L_0x00be
        L_0x0091:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcwn$zze> r15 = com.google.android.gms.internal.zzcwn.zze.CREATOR
            java.util.ArrayList r21 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r15)
            r2 = 22
            goto L_0x00be
        L_0x009c:
            r29 = r15
            int r20 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            r2 = 21
            goto L_0x014f
        L_0x00a6:
            r29 = r15
            java.lang.String r19 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            r2 = 20
            goto L_0x014f
        L_0x00b0:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcwn$zzd> r15 = com.google.android.gms.internal.zzcwn.zzd.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r15)
            r18 = r2
            com.google.android.gms.internal.zzcwn$zzd r18 = (com.google.android.gms.internal.zzcwn.zzd) r18
            r2 = 19
        L_0x00be:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3.add(r2)
            r15 = r29
            goto L_0x0033
        L_0x00c9:
            r29 = r15
            java.lang.String r17 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            r2 = 18
            goto L_0x014f
        L_0x00d3:
            r29 = r15
            boolean r16 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            r2 = 16
            goto L_0x014f
        L_0x00dd:
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcwn$zzc> r15 = com.google.android.gms.internal.zzcwn.zzc.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r15)
            r15 = r2
            com.google.android.gms.internal.zzcwn$zzc r15 = (com.google.android.gms.internal.zzcwn.zzc) r15
            r2 = 15
            goto L_0x014f
        L_0x00ea:
            r29 = r15
            java.lang.String r14 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            r2 = 14
            goto L_0x014f
        L_0x00f3:
            r29 = r15
            int r13 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            r2 = 12
            goto L_0x014f
        L_0x00fc:
            r29 = r15
            java.lang.String r12 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            r2 = 9
            goto L_0x014f
        L_0x0105:
            r29 = r15
            java.lang.String r11 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            r2 = 8
            goto L_0x014f
        L_0x010e:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcwn$zzb> r10 = com.google.android.gms.internal.zzcwn.zzb.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r10)
            r10 = r2
            com.google.android.gms.internal.zzcwn$zzb r10 = (com.google.android.gms.internal.zzcwn.zzb) r10
            r2 = 7
            goto L_0x014f
        L_0x011b:
            r29 = r15
            int r9 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            r2 = 6
            goto L_0x014f
        L_0x0123:
            r29 = r15
            java.lang.String r8 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            r2 = 5
            goto L_0x014f
        L_0x012b:
            r29 = r15
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            r2 = 4
            goto L_0x014f
        L_0x0133:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.zzcwn$zza> r6 = com.google.android.gms.internal.zzcwn.zza.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r6)
            r6 = r2
            com.google.android.gms.internal.zzcwn$zza r6 = (com.google.android.gms.internal.zzcwn.zza) r6
            r2 = 3
            goto L_0x014f
        L_0x0140:
            r29 = r15
            java.lang.String r5 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            r2 = 2
            goto L_0x014f
        L_0x0148:
            r29 = r15
            int r4 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            r2 = 1
        L_0x014f:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3.add(r2)
            goto L_0x0033
        L_0x0158:
            r29 = r15
            int r2 = r31.dataPosition()
            if (r2 != r1) goto L_0x0169
            com.google.android.gms.internal.zzcwn r0 = new com.google.android.gms.internal.zzcwn
            r2 = r0
            r15 = r29
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            return r0
        L_0x0169:
            com.google.android.gms.internal.zzbgn r2 = new com.google.android.gms.internal.zzbgn
            r3 = 37
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Overread allowed size end="
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r2.<init>(r1, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcwo.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzcwn[] newArray(int i) {
        return new zzcwn[i];
    }
}
