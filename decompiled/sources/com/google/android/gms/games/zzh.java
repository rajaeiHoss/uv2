package com.google.android.gms.games;

import android.os.Parcelable;

public class zzh implements Parcelable.Creator<GameEntity> {
    public /* synthetic */ GameEntity[] newArray(int i) {
        return new GameEntity[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: zzo */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.games.GameEntity createFromParcel(android.os.Parcel r31) {
        /*
            r30 = this;
            r0 = r31
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r31)
            r2 = 0
            r3 = 0
            r5 = r3
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r16 = r13
            r22 = r16
            r23 = r22
            r24 = r23
            r28 = r24
            r14 = 0
            r15 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r29 = 0
        L_0x002f:
            int r2 = r31.dataPosition()
            if (r2 >= r1) goto L_0x00d7
            int r2 = r31.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x00d1;
                case 2: goto L_0x00cb;
                case 3: goto L_0x00c5;
                case 4: goto L_0x00bf;
                case 5: goto L_0x00b9;
                case 6: goto L_0x00b3;
                case 7: goto L_0x00a8;
                case 8: goto L_0x009e;
                case 9: goto L_0x0094;
                case 10: goto L_0x008f;
                case 11: goto L_0x008a;
                case 12: goto L_0x0085;
                case 13: goto L_0x0080;
                case 14: goto L_0x007b;
                case 15: goto L_0x0076;
                case 16: goto L_0x0071;
                case 17: goto L_0x006c;
                case 18: goto L_0x0067;
                case 19: goto L_0x0062;
                case 20: goto L_0x005d;
                case 21: goto L_0x0058;
                case 22: goto L_0x0053;
                case 23: goto L_0x004e;
                case 24: goto L_0x0049;
                case 25: goto L_0x0044;
                default: goto L_0x0040;
            }
        L_0x0040:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x002f
        L_0x0044:
            boolean r29 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x002f
        L_0x0049:
            java.lang.String r28 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002f
        L_0x004e:
            boolean r27 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x002f
        L_0x0053:
            boolean r26 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x002f
        L_0x0058:
            boolean r25 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x002f
        L_0x005d:
            java.lang.String r24 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002f
        L_0x0062:
            java.lang.String r23 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002f
        L_0x0067:
            java.lang.String r22 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002f
        L_0x006c:
            boolean r21 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x002f
        L_0x0071:
            boolean r20 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x002f
        L_0x0076:
            int r19 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x002f
        L_0x007b:
            int r18 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x002f
        L_0x0080:
            int r17 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x002f
        L_0x0085:
            java.lang.String r16 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002f
        L_0x008a:
            boolean r15 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x002f
        L_0x008f:
            boolean r14 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x002f
        L_0x0094:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r13 = r2
            android.net.Uri r13 = (android.net.Uri) r13
            goto L_0x002f
        L_0x009e:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r12 = r2
            android.net.Uri r12 = (android.net.Uri) r12
            goto L_0x002f
        L_0x00a8:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r11 = r2
            android.net.Uri r11 = (android.net.Uri) r11
            goto L_0x002f
        L_0x00b3:
            java.lang.String r10 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002f
        L_0x00b9:
            java.lang.String r9 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002f
        L_0x00bf:
            java.lang.String r8 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002f
        L_0x00c5:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002f
        L_0x00cb:
            java.lang.String r6 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002f
        L_0x00d1:
            java.lang.String r5 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002f
        L_0x00d7:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.games.GameEntity r0 = new com.google.android.gms.games.GameEntity
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.zzh.createFromParcel(android.os.Parcel):com.google.android.gms.games.GameEntity");
    }
}
