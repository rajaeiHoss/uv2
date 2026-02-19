package com.google.android.gms.location.places.internal;

import android.os.Parcelable;

public final class zzaf implements Parcelable.Creator<PlaceEntity> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v7, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v8, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ PlaceEntity createFromParcel(android.os.Parcel r28) {
        /*
            r27 = this;
            r0 = r28
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r28)
            r2 = 0
            r3 = 0
            r4 = 0
            r6 = r4
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r17 = r15
            r18 = r17
            r19 = r18
            r23 = r19
            r24 = r23
            r25 = r24
            r26 = r25
            r16 = 0
            r20 = 0
            r21 = 0
            r22 = 0
        L_0x0029:
            int r2 = r28.dataPosition()
            if (r2 >= r1) goto L_0x00cf
            int r2 = r28.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x00c9;
                case 2: goto L_0x00c3;
                case 3: goto L_0x00b7;
                case 4: goto L_0x00ac;
                case 5: goto L_0x00a6;
                case 6: goto L_0x009b;
                case 7: goto L_0x0096;
                case 8: goto L_0x008b;
                case 9: goto L_0x0086;
                case 10: goto L_0x0081;
                case 11: goto L_0x007c;
                case 12: goto L_0x003a;
                case 13: goto L_0x0077;
                case 14: goto L_0x0072;
                case 15: goto L_0x006d;
                case 16: goto L_0x0068;
                case 17: goto L_0x0063;
                case 18: goto L_0x003a;
                case 19: goto L_0x005e;
                case 20: goto L_0x0059;
                case 21: goto L_0x004e;
                case 22: goto L_0x0043;
                case 23: goto L_0x003e;
                default: goto L_0x003a;
            }
        L_0x003a:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x0029
        L_0x003e:
            java.lang.String r26 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0029
        L_0x0043:
            android.os.Parcelable$Creator<com.google.android.gms.location.places.internal.zzag> r3 = com.google.android.gms.location.places.internal.zzag.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r25 = r2
            com.google.android.gms.location.places.internal.zzag r25 = (com.google.android.gms.location.places.internal.zzag) r25
            goto L_0x0029
        L_0x004e:
            android.os.Parcelable$Creator<com.google.android.gms.location.places.internal.zzan> r3 = com.google.android.gms.location.places.internal.zzan.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r24 = r2
            com.google.android.gms.location.places.internal.zzan r24 = (com.google.android.gms.location.places.internal.zzan) r24
            goto L_0x0029
        L_0x0059:
            java.util.ArrayList r7 = com.google.android.gms.internal.zzbgm.zzab(r0, r2)
            goto L_0x0029
        L_0x005e:
            java.lang.String r10 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0029
        L_0x0063:
            java.util.ArrayList r14 = com.google.android.gms.internal.zzbgm.zzac(r0, r2)
            goto L_0x0029
        L_0x0068:
            java.lang.String r13 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0029
        L_0x006d:
            java.lang.String r12 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0029
        L_0x0072:
            java.lang.String r11 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0029
        L_0x0077:
            java.util.ArrayList r8 = com.google.android.gms.internal.zzbgm.zzab(r0, r2)
            goto L_0x0029
        L_0x007c:
            int r22 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x0029
        L_0x0081:
            float r21 = com.google.android.gms.internal.zzbgm.zzl(r0, r2)
            goto L_0x0029
        L_0x0086:
            boolean r20 = com.google.android.gms.internal.zzbgm.zzc(r0, r2)
            goto L_0x0029
        L_0x008b:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r19 = r2
            android.net.Uri r19 = (android.net.Uri) r19
            goto L_0x0029
        L_0x0096:
            java.lang.String r18 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0029
        L_0x009b:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLngBounds> r3 = com.google.android.gms.maps.model.LatLngBounds.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r17 = r2
            com.google.android.gms.maps.model.LatLngBounds r17 = (com.google.android.gms.maps.model.LatLngBounds) r17
            goto L_0x0029
        L_0x00a6:
            float r16 = com.google.android.gms.internal.zzbgm.zzl(r0, r2)
            goto L_0x0029
        L_0x00ac:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r3 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r15 = r2
            com.google.android.gms.maps.model.LatLng r15 = (com.google.android.gms.maps.model.LatLng) r15
            goto L_0x0029
        L_0x00b7:
            android.os.Parcelable$Creator<com.google.android.gms.location.places.internal.zzal> r3 = com.google.android.gms.location.places.internal.zzal.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r23 = r2
            com.google.android.gms.location.places.internal.zzal r23 = (com.google.android.gms.location.places.internal.zzal) r23
            goto L_0x0029
        L_0x00c3:
            android.os.Bundle r9 = com.google.android.gms.internal.zzbgm.zzs(r0, r2)
            goto L_0x0029
        L_0x00c9:
            java.lang.String r6 = com.google.android.gms.internal.zzbgm.zzq(r0, r2)
            goto L_0x0029
        L_0x00cf:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.location.places.internal.PlaceEntity r0 = new com.google.android.gms.location.places.internal.PlaceEntity
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.places.internal.zzaf.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ PlaceEntity[] newArray(int i) {
        return new PlaceEntity[i];
    }
}
