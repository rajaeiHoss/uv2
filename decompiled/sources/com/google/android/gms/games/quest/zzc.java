package com.google.android.gms.games.quest;

import android.os.Parcelable;

public final class zzc implements Parcelable.Creator<QuestEntity> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ QuestEntity createFromParcel(android.os.Parcel r29) {
        /*
            r28 = this;
            r0 = r29
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r29)
            r2 = 0
            r3 = 0
            r5 = 0
            r9 = r3
            r14 = r9
            r16 = r14
            r21 = r16
            r23 = r21
            r7 = r5
            r8 = r7
            r11 = r8
            r12 = r11
            r13 = r12
            r18 = r13
            r19 = r18
            r20 = r19
            r27 = r20
            r25 = 0
            r26 = 0
        L_0x0023:
            int r2 = r29.dataPosition()
            if (r2 >= r1) goto L_0x009a
            int r2 = r29.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x0090;
                case 2: goto L_0x008b;
                case 3: goto L_0x0086;
                case 4: goto L_0x007c;
                case 5: goto L_0x0077;
                case 6: goto L_0x0072;
                case 7: goto L_0x006d;
                case 8: goto L_0x0068;
                case 9: goto L_0x005d;
                case 10: goto L_0x0058;
                case 11: goto L_0x0034;
                case 12: goto L_0x0053;
                case 13: goto L_0x004e;
                case 14: goto L_0x0049;
                case 15: goto L_0x0044;
                case 16: goto L_0x003f;
                case 17: goto L_0x0038;
                default: goto L_0x0034;
            }
        L_0x0034:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0023
        L_0x0038:
            android.os.Parcelable$Creator<com.google.android.gms.games.quest.MilestoneEntity> r3 = com.google.android.gms.games.quest.MilestoneEntity.CREATOR
            java.util.ArrayList r27 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x0023
        L_0x003f:
            int r26 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0023
        L_0x0044:
            int r25 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0023
        L_0x0049:
            long r23 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0023
        L_0x004e:
            long r21 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0023
        L_0x0053:
            java.lang.String r20 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0023
        L_0x0058:
            java.lang.String r19 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0023
        L_0x005d:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r18 = r2
            android.net.Uri r18 = (android.net.Uri) r18
            goto L_0x0023
        L_0x0068:
            long r16 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0023
        L_0x006d:
            long r14 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0023
        L_0x0072:
            java.lang.String r13 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0023
        L_0x0077:
            java.lang.String r12 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0023
        L_0x007c:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r11 = r2
            android.net.Uri r11 = (android.net.Uri) r11
            goto L_0x0023
        L_0x0086:
            long r9 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0023
        L_0x008b:
            java.lang.String r8 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0023
        L_0x0090:
            android.os.Parcelable$Creator<com.google.android.gms.games.GameEntity> r3 = com.google.android.gms.games.GameEntity.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r7 = r2
            com.google.android.gms.games.GameEntity r7 = (com.google.android.gms.games.GameEntity) r7
            goto L_0x0023
        L_0x009a:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.games.quest.QuestEntity r0 = new com.google.android.gms.games.quest.QuestEntity
            r6 = r0
            r6.<init>(r7, r8, r9, r11, r12, r13, r14, r16, r18, r19, r20, r21, r23, r25, r26, r27)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.quest.zzc.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ QuestEntity[] newArray(int i) {
        return new QuestEntity[i];
    }
}
