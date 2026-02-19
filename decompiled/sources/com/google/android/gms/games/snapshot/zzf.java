package com.google.android.gms.games.snapshot;

import android.os.Parcelable;

public final class zzf implements Parcelable.Creator<SnapshotMetadataEntity> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ SnapshotMetadataEntity createFromParcel(android.os.Parcel r26) {
        /*
            r25 = this;
            r0 = r26
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r26)
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r15 = r2
            r17 = r15
            r22 = r17
            r8 = r4
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r20 = r14
            r24 = r20
            r19 = 0
            r21 = 0
        L_0x001f:
            int r2 = r26.dataPosition()
            if (r2 >= r1) goto L_0x0089
            int r2 = r26.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x007f;
                case 2: goto L_0x0075;
                case 3: goto L_0x0070;
                case 4: goto L_0x0030;
                case 5: goto L_0x0066;
                case 6: goto L_0x0061;
                case 7: goto L_0x005c;
                case 8: goto L_0x0057;
                case 9: goto L_0x0052;
                case 10: goto L_0x004d;
                case 11: goto L_0x0048;
                case 12: goto L_0x0043;
                case 13: goto L_0x003e;
                case 14: goto L_0x0039;
                case 15: goto L_0x0034;
                default: goto L_0x0030;
            }
        L_0x0030:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x001f
        L_0x0034:
            java.lang.String r24 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001f
        L_0x0039:
            long r22 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x001f
        L_0x003e:
            boolean r21 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x001f
        L_0x0043:
            java.lang.String r20 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001f
        L_0x0048:
            float r19 = com.google.android.gms.internal.zzbgm.zzl(r0, r2)
            goto L_0x001f
        L_0x004d:
            long r17 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x001f
        L_0x0052:
            long r15 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x001f
        L_0x0057:
            java.lang.String r14 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001f
        L_0x005c:
            java.lang.String r13 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001f
        L_0x0061:
            java.lang.String r12 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001f
        L_0x0066:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r11 = r2
            android.net.Uri r11 = (android.net.Uri) r11
            goto L_0x001f
        L_0x0070:
            java.lang.String r10 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x001f
        L_0x0075:
            android.os.Parcelable$Creator<com.google.android.gms.games.PlayerEntity> r3 = com.google.android.gms.games.PlayerEntity.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r9 = r2
            com.google.android.gms.games.PlayerEntity r9 = (com.google.android.gms.games.PlayerEntity) r9
            goto L_0x001f
        L_0x007f:
            android.os.Parcelable$Creator<com.google.android.gms.games.GameEntity> r3 = com.google.android.gms.games.GameEntity.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r8 = r2
            com.google.android.gms.games.GameEntity r8 = (com.google.android.gms.games.GameEntity) r8
            goto L_0x001f
        L_0x0089:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.games.snapshot.SnapshotMetadataEntity r0 = new com.google.android.gms.games.snapshot.SnapshotMetadataEntity
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r17, r19, r20, r21, r22, r24)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.snapshot.zzf.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ SnapshotMetadataEntity[] newArray(int i) {
        return new SnapshotMetadataEntity[i];
    }
}
