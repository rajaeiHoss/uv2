package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Parcelable;

public final class zzc implements Parcelable.Creator<TurnBasedMatchEntity> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ TurnBasedMatchEntity createFromParcel(android.os.Parcel r30) {
        /*
            r29 = this;
            r0 = r30
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r30)
            r2 = 0
            r4 = 0
            r5 = 0
            r10 = r2
            r13 = r10
            r7 = r5
            r8 = r7
            r9 = r8
            r12 = r9
            r15 = r12
            r19 = r15
            r20 = r19
            r21 = r20
            r22 = r21
            r24 = r22
            r27 = r24
            r28 = r27
            r16 = 0
            r17 = 0
            r18 = 0
            r23 = 0
            r25 = 0
            r26 = 0
        L_0x002b:
            int r2 = r30.dataPosition()
            if (r2 >= r1) goto L_0x00ab
            int r2 = r30.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x00a1;
                case 2: goto L_0x009c;
                case 3: goto L_0x0097;
                case 4: goto L_0x0092;
                case 5: goto L_0x008d;
                case 6: goto L_0x0088;
                case 7: goto L_0x0083;
                case 8: goto L_0x007e;
                case 9: goto L_0x003c;
                case 10: goto L_0x0079;
                case 11: goto L_0x0074;
                case 12: goto L_0x006f;
                case 13: goto L_0x0068;
                case 14: goto L_0x0063;
                case 15: goto L_0x005e;
                case 16: goto L_0x0059;
                case 17: goto L_0x0054;
                case 18: goto L_0x004f;
                case 19: goto L_0x004a;
                case 20: goto L_0x0045;
                case 21: goto L_0x0040;
                default: goto L_0x003c;
            }
        L_0x003c:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x002b
        L_0x0040:
            java.lang.String r28 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002b
        L_0x0045:
            java.lang.String r27 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002b
        L_0x004a:
            boolean r26 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x002b
        L_0x004f:
            int r25 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x002b
        L_0x0054:
            android.os.Bundle r24 = com.google.android.gms.internal.zzbgm.zzs(r0, r2)
            goto L_0x002b
        L_0x0059:
            int r23 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x002b
        L_0x005e:
            byte[] r22 = com.google.android.gms.internal.zzbgm.zzt(r0, r2)
            goto L_0x002b
        L_0x0063:
            java.lang.String r21 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002b
        L_0x0068:
            android.os.Parcelable$Creator<com.google.android.gms.games.multiplayer.ParticipantEntity> r3 = com.google.android.gms.games.multiplayer.ParticipantEntity.CREATOR
            java.util.ArrayList r20 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x002b
        L_0x006f:
            byte[] r19 = com.google.android.gms.internal.zzbgm.zzt(r0, r2)
            goto L_0x002b
        L_0x0074:
            int r18 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x002b
        L_0x0079:
            int r17 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x002b
        L_0x007e:
            int r16 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x002b
        L_0x0083:
            java.lang.String r15 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002b
        L_0x0088:
            long r13 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x002b
        L_0x008d:
            java.lang.String r12 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002b
        L_0x0092:
            long r10 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x002b
        L_0x0097:
            java.lang.String r9 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002b
        L_0x009c:
            java.lang.String r8 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x002b
        L_0x00a1:
            android.os.Parcelable$Creator<com.google.android.gms.games.GameEntity> r3 = com.google.android.gms.games.GameEntity.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r7 = r2
            com.google.android.gms.games.GameEntity r7 = (com.google.android.gms.games.GameEntity) r7
            goto L_0x002b
        L_0x00ab:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchEntity r0 = new com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchEntity
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r12, r13, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.multiplayer.turnbased.zzc.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ TurnBasedMatchEntity[] newArray(int i) {
        return new TurnBasedMatchEntity[i];
    }
}
