package com.google.android.gms.games.multiplayer;

import android.os.Parcelable;

public class zza implements Parcelable.Creator<InvitationEntity> {
    public /* synthetic */ InvitationEntity[] newArray(int i) {
        return new InvitationEntity[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: zzq */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.games.multiplayer.InvitationEntity createFromParcel(android.os.Parcel r17) {
        /*
            r16 = this;
            r0 = r17
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r17)
            r2 = 0
            r3 = 0
            r4 = 0
            r7 = r3
            r8 = r7
            r12 = r8
            r13 = r12
            r9 = r4
            r11 = 0
            r14 = 0
            r15 = 0
        L_0x0012:
            int r2 = r17.dataPosition()
            if (r2 >= r1) goto L_0x005b
            int r2 = r17.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x0051;
                case 2: goto L_0x004c;
                case 3: goto L_0x0047;
                case 4: goto L_0x0042;
                case 5: goto L_0x0038;
                case 6: goto L_0x0031;
                case 7: goto L_0x002c;
                case 8: goto L_0x0027;
                default: goto L_0x0023;
            }
        L_0x0023:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0012
        L_0x0027:
            int r15 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0012
        L_0x002c:
            int r14 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0012
        L_0x0031:
            android.os.Parcelable$Creator<com.google.android.gms.games.multiplayer.ParticipantEntity> r3 = com.google.android.gms.games.multiplayer.ParticipantEntity.CREATOR
            java.util.ArrayList r13 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x0012
        L_0x0038:
            android.os.Parcelable$Creator<com.google.android.gms.games.multiplayer.ParticipantEntity> r3 = com.google.android.gms.games.multiplayer.ParticipantEntity.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r12 = r2
            com.google.android.gms.games.multiplayer.ParticipantEntity r12 = (com.google.android.gms.games.multiplayer.ParticipantEntity) r12
            goto L_0x0012
        L_0x0042:
            int r11 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0012
        L_0x0047:
            long r9 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0012
        L_0x004c:
            java.lang.String r8 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0012
        L_0x0051:
            android.os.Parcelable$Creator<com.google.android.gms.games.GameEntity> r3 = com.google.android.gms.games.GameEntity.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r7 = r2
            com.google.android.gms.games.GameEntity r7 = (com.google.android.gms.games.GameEntity) r7
            goto L_0x0012
        L_0x005b:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.games.multiplayer.InvitationEntity r0 = new com.google.android.gms.games.multiplayer.InvitationEntity
            r6 = r0
            r6.<init>(r7, r8, r9, r11, r12, r13, r14, r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.multiplayer.zza.createFromParcel(android.os.Parcel):com.google.android.gms.games.multiplayer.InvitationEntity");
    }
}
