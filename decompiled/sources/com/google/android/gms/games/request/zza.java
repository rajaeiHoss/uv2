package com.google.android.gms.games.request;

import android.os.Parcelable;

public final class zza implements Parcelable.Creator<GameRequestEntity> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ GameRequestEntity createFromParcel(android.os.Parcel r20) {
        /*
            r19 = this;
            r0 = r20
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r20)
            r2 = 0
            r4 = 0
            r5 = 0
            r13 = r2
            r15 = r13
            r7 = r5
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r17 = r11
            r12 = 0
            r18 = 0
        L_0x0016:
            int r2 = r20.dataPosition()
            if (r2 >= r1) goto L_0x0069
            int r2 = r20.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x005f;
                case 2: goto L_0x0055;
                case 3: goto L_0x0050;
                case 4: goto L_0x004b;
                case 5: goto L_0x0044;
                case 6: goto L_0x0027;
                case 7: goto L_0x003f;
                case 8: goto L_0x0027;
                case 9: goto L_0x003a;
                case 10: goto L_0x0035;
                case 11: goto L_0x0030;
                case 12: goto L_0x002b;
                default: goto L_0x0027;
            }
        L_0x0027:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0016
        L_0x002b:
            int r18 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0016
        L_0x0030:
            android.os.Bundle r17 = com.google.android.gms.internal.zzbgm.zzs(r0, r2)
            goto L_0x0016
        L_0x0035:
            long r15 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0016
        L_0x003a:
            long r13 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0016
        L_0x003f:
            int r12 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0016
        L_0x0044:
            android.os.Parcelable$Creator<com.google.android.gms.games.PlayerEntity> r3 = com.google.android.gms.games.PlayerEntity.CREATOR
            java.util.ArrayList r11 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x0016
        L_0x004b:
            java.lang.String r10 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0016
        L_0x0050:
            byte[] r9 = com.google.android.gms.internal.zzbgm.zzt(r0, r2)
            goto L_0x0016
        L_0x0055:
            android.os.Parcelable$Creator<com.google.android.gms.games.PlayerEntity> r3 = com.google.android.gms.games.PlayerEntity.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r8 = r2
            com.google.android.gms.games.PlayerEntity r8 = (com.google.android.gms.games.PlayerEntity) r8
            goto L_0x0016
        L_0x005f:
            android.os.Parcelable$Creator<com.google.android.gms.games.GameEntity> r3 = com.google.android.gms.games.GameEntity.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r7 = r2
            com.google.android.gms.games.GameEntity r7 = (com.google.android.gms.games.GameEntity) r7
            goto L_0x0016
        L_0x0069:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.games.request.GameRequestEntity r0 = new com.google.android.gms.games.request.GameRequestEntity
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r15, r17, r18)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.request.zza.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ GameRequestEntity[] newArray(int i) {
        return new GameRequestEntity[i];
    }
}
