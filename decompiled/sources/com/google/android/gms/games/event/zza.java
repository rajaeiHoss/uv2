package com.google.android.gms.games.event;

import android.os.Parcelable;

public final class zza implements Parcelable.Creator<EventEntity> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ EventEntity createFromParcel(android.os.Parcel r18) {
        /*
            r17 = this;
            r0 = r18
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r18)
            r2 = 0
            r3 = 0
            r5 = 0
            r7 = r2
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r15 = r12
            r13 = r3
            r16 = 0
        L_0x0014:
            int r2 = r18.dataPosition()
            if (r2 >= r1) goto L_0x0060
            int r2 = r18.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x005b;
                case 2: goto L_0x0056;
                case 3: goto L_0x0051;
                case 4: goto L_0x0047;
                case 5: goto L_0x0042;
                case 6: goto L_0x0038;
                case 7: goto L_0x0033;
                case 8: goto L_0x002e;
                case 9: goto L_0x0029;
                default: goto L_0x0025;
            }
        L_0x0025:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0014
        L_0x0029:
            boolean r16 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x0014
        L_0x002e:
            java.lang.String r15 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0014
        L_0x0033:
            long r13 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x0014
        L_0x0038:
            android.os.Parcelable$Creator<com.google.android.gms.games.PlayerEntity> r3 = com.google.android.gms.games.PlayerEntity.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r12 = r2
            com.google.android.gms.games.PlayerEntity r12 = (com.google.android.gms.games.PlayerEntity) r12
            goto L_0x0014
        L_0x0042:
            java.lang.String r11 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0014
        L_0x0047:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r10 = r2
            android.net.Uri r10 = (android.net.Uri) r10
            goto L_0x0014
        L_0x0051:
            java.lang.String r9 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0014
        L_0x0056:
            java.lang.String r8 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0014
        L_0x005b:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0014
        L_0x0060:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.games.event.EventEntity r0 = new com.google.android.gms.games.event.EventEntity
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r15, r16)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.event.zza.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ EventEntity[] newArray(int i) {
        return new EventEntity[i];
    }
}
