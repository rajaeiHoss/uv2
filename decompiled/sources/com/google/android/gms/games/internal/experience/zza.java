package com.google.android.gms.games.internal.experience;

import android.os.Parcelable;

public final class zza implements Parcelable.Creator<ExperienceEventEntity> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ ExperienceEventEntity createFromParcel(android.os.Parcel r22) {
        /*
            r21 = this;
            r0 = r22
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r22)
            r2 = 0
            r3 = 0
            r5 = 0
            r13 = r3
            r15 = r13
            r17 = r15
            r7 = r5
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r19 = 0
            r20 = 0
        L_0x0018:
            int r2 = r22.dataPosition()
            if (r2 >= r1) goto L_0x006e
            int r2 = r22.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x0069;
                case 2: goto L_0x005f;
                case 3: goto L_0x005a;
                case 4: goto L_0x0055;
                case 5: goto L_0x0050;
                case 6: goto L_0x0046;
                case 7: goto L_0x0041;
                case 8: goto L_0x003c;
                case 9: goto L_0x0037;
                case 10: goto L_0x0032;
                case 11: goto L_0x002d;
                default: goto L_0x0029;
            }
        L_0x0029:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0018
        L_0x002d:
            int r20 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0018
        L_0x0032:
            int r19 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0018
        L_0x0037:
            long r17 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0018
        L_0x003c:
            long r15 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0018
        L_0x0041:
            long r13 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0018
        L_0x0046:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r12 = r2
            android.net.Uri r12 = (android.net.Uri) r12
            goto L_0x0018
        L_0x0050:
            java.lang.String r11 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0018
        L_0x0055:
            java.lang.String r10 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0018
        L_0x005a:
            java.lang.String r9 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0018
        L_0x005f:
            android.os.Parcelable$Creator<com.google.android.gms.games.GameEntity> r3 = com.google.android.gms.games.GameEntity.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r8 = r2
            com.google.android.gms.games.GameEntity r8 = (com.google.android.gms.games.GameEntity) r8
            goto L_0x0018
        L_0x0069:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0018
        L_0x006e:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.games.internal.experience.ExperienceEventEntity r0 = new com.google.android.gms.games.internal.experience.ExperienceEventEntity
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r15, r17, r19, r20)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.internal.experience.zza.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ ExperienceEventEntity[] newArray(int i) {
        return new ExperienceEventEntity[i];
    }
}
