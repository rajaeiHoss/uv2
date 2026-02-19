package com.google.android.gms.games.multiplayer;

import android.os.Parcelable;

public class zzc implements Parcelable.Creator<ParticipantEntity> {
    public /* synthetic */ ParticipantEntity[] newArray(int i) {
        return new ParticipantEntity[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: zzr */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.games.multiplayer.ParticipantEntity createFromParcel(android.os.Parcel r18) {
        /*
            r17 = this;
            r0 = r18
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r18)
            r2 = 0
            r3 = 0
            r5 = r3
            r6 = r5
            r7 = r6
            r8 = r7
            r10 = r8
            r12 = r10
            r14 = r12
            r15 = r14
            r16 = r15
            r9 = 0
            r11 = 0
            r13 = 0
        L_0x0015:
            int r2 = r18.dataPosition()
            if (r2 >= r1) goto L_0x007a
            int r2 = r18.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x0075;
                case 2: goto L_0x0070;
                case 3: goto L_0x0066;
                case 4: goto L_0x005c;
                case 5: goto L_0x0057;
                case 6: goto L_0x0052;
                case 7: goto L_0x004d;
                case 8: goto L_0x0043;
                case 9: goto L_0x003e;
                case 10: goto L_0x0034;
                case 11: goto L_0x002f;
                case 12: goto L_0x002a;
                default: goto L_0x0026;
            }
        L_0x0026:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0015
        L_0x002a:
            java.lang.String r16 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0015
        L_0x002f:
            java.lang.String r15 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0015
        L_0x0034:
            android.os.Parcelable$Creator<com.google.android.gms.games.multiplayer.ParticipantResult> r3 = com.google.android.gms.games.multiplayer.ParticipantResult.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r14 = r2
            com.google.android.gms.games.multiplayer.ParticipantResult r14 = (com.google.android.gms.games.multiplayer.ParticipantResult) r14
            goto L_0x0015
        L_0x003e:
            int r13 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0015
        L_0x0043:
            android.os.Parcelable$Creator<com.google.android.gms.games.PlayerEntity> r3 = com.google.android.gms.games.PlayerEntity.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r12 = r2
            com.google.android.gms.games.PlayerEntity r12 = (com.google.android.gms.games.PlayerEntity) r12
            goto L_0x0015
        L_0x004d:
            boolean r11 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x0015
        L_0x0052:
            java.lang.String r10 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0015
        L_0x0057:
            int r9 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0015
        L_0x005c:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r8 = r2
            android.net.Uri r8 = (android.net.Uri) r8
            goto L_0x0015
        L_0x0066:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r7 = r2
            android.net.Uri r7 = (android.net.Uri) r7
            goto L_0x0015
        L_0x0070:
            java.lang.String r6 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0015
        L_0x0075:
            java.lang.String r5 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0015
        L_0x007a:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.games.multiplayer.ParticipantEntity r0 = new com.google.android.gms.games.multiplayer.ParticipantEntity
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.multiplayer.zzc.createFromParcel(android.os.Parcel):com.google.android.gms.games.multiplayer.ParticipantEntity");
    }
}
