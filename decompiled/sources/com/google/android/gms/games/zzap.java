package com.google.android.gms.games;

import android.os.Parcelable;

public class zzap implements Parcelable.Creator<PlayerEntity> {
    public /* synthetic */ PlayerEntity[] newArray(int i) {
        return new PlayerEntity[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v7, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v8, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: zzp */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.games.PlayerEntity createFromParcel(android.os.Parcel r34) {
        /*
            r33 = this;
            r0 = r34
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r34)
            r2 = 0
            r4 = 0
            r5 = 0
            r11 = r2
            r14 = r11
            r30 = r14
            r7 = r5
            r8 = r7
            r9 = r8
            r10 = r9
            r16 = r10
            r17 = r16
            r18 = r17
            r19 = r18
            r20 = r19
            r23 = r20
            r24 = r23
            r25 = r24
            r26 = r25
            r27 = r26
            r28 = r27
            r13 = 0
            r21 = 0
            r22 = 0
            r29 = 0
            r32 = 0
        L_0x0031:
            int r2 = r34.dataPosition()
            if (r2 >= r1) goto L_0x00e2
            int r2 = r34.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x00dc;
                case 2: goto L_0x00d6;
                case 3: goto L_0x00cb;
                case 4: goto L_0x00c0;
                case 5: goto L_0x00ba;
                case 6: goto L_0x00b4;
                case 7: goto L_0x00ae;
                case 8: goto L_0x00a9;
                case 9: goto L_0x00a4;
                case 10: goto L_0x0042;
                case 11: goto L_0x0042;
                case 12: goto L_0x0042;
                case 13: goto L_0x0042;
                case 14: goto L_0x009f;
                case 15: goto L_0x0094;
                case 16: goto L_0x0089;
                case 17: goto L_0x0042;
                case 18: goto L_0x0084;
                case 19: goto L_0x007f;
                case 20: goto L_0x007a;
                case 21: goto L_0x0075;
                case 22: goto L_0x006a;
                case 23: goto L_0x0065;
                case 24: goto L_0x005a;
                case 25: goto L_0x0055;
                case 26: goto L_0x0050;
                case 27: goto L_0x004b;
                case 28: goto L_0x0046;
                default: goto L_0x0042;
            }
        L_0x0042:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0031
        L_0x0046:
            boolean r32 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x0031
        L_0x004b:
            long r30 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0031
        L_0x0050:
            int r29 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0031
        L_0x0055:
            java.lang.String r28 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0031
        L_0x005a:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r27 = r2
            android.net.Uri r27 = (android.net.Uri) r27
            goto L_0x0031
        L_0x0065:
            java.lang.String r26 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0031
        L_0x006a:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r25 = r2
            android.net.Uri r25 = (android.net.Uri) r25
            goto L_0x0031
        L_0x0075:
            java.lang.String r24 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0031
        L_0x007a:
            java.lang.String r23 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0031
        L_0x007f:
            boolean r22 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x0031
        L_0x0084:
            boolean r21 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x0031
        L_0x0089:
            android.os.Parcelable$Creator<com.google.android.gms.games.PlayerLevelInfo> r3 = com.google.android.gms.games.PlayerLevelInfo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r20 = r2
            com.google.android.gms.games.PlayerLevelInfo r20 = (com.google.android.gms.games.PlayerLevelInfo) r20
            goto L_0x0031
        L_0x0094:
            android.os.Parcelable$Creator<com.google.android.gms.games.internal.player.zzb> r3 = com.google.android.gms.games.internal.player.zzb.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r19 = r2
            com.google.android.gms.games.internal.player.zzb r19 = (com.google.android.gms.games.internal.player.zzb) r19
            goto L_0x0031
        L_0x009f:
            java.lang.String r18 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0031
        L_0x00a4:
            java.lang.String r17 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0031
        L_0x00a9:
            java.lang.String r16 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0031
        L_0x00ae:
            long r14 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0031
        L_0x00b4:
            int r13 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0031
        L_0x00ba:
            long r11 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0031
        L_0x00c0:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r10 = r2
            android.net.Uri r10 = (android.net.Uri) r10
            goto L_0x0031
        L_0x00cb:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r9 = r2
            android.net.Uri r9 = (android.net.Uri) r9
            goto L_0x0031
        L_0x00d6:
            java.lang.String r8 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0031
        L_0x00dc:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0031
        L_0x00e2:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.games.PlayerEntity r0 = new com.google.android.gms.games.PlayerEntity
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r13, r14, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r32)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.zzap.createFromParcel(android.os.Parcel):com.google.android.gms.games.PlayerEntity");
    }
}
