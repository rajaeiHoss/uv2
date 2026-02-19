package com.google.android.gms.fitness.request;

import android.os.Parcelable;

public final class zzap implements Parcelable.Creator<zzao> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ zzao createFromParcel(android.os.Parcel r25) {
        /*
            r24 = this;
            r0 = r25
            int r1 = com.google.android.gms.internal.zzbgm.zzd(r25)
            r2 = 0
            r3 = 0
            r5 = 0
            r12 = r3
            r14 = r12
            r17 = r14
            r21 = r17
            r7 = r5
            r8 = r7
            r9 = r8
            r16 = r9
            r20 = r16
            r23 = r20
            r10 = 0
            r11 = 0
            r19 = 0
        L_0x001d:
            int r2 = r25.dataPosition()
            if (r2 >= r1) goto L_0x0085
            int r2 = r25.readInt()
            r3 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r2
            switch(r3) {
                case 1: goto L_0x007b;
                case 2: goto L_0x0071;
                case 3: goto L_0x006c;
                case 4: goto L_0x0067;
                case 5: goto L_0x0062;
                case 6: goto L_0x005d;
                case 7: goto L_0x0058;
                case 8: goto L_0x004d;
                case 9: goto L_0x0048;
                case 10: goto L_0x0043;
                case 11: goto L_0x003c;
                case 12: goto L_0x0037;
                case 13: goto L_0x0032;
                default: goto L_0x002e;
            }
        L_0x002e:
            com.google.android.gms.internal.zzbgm.zzb(r0, r2)
            goto L_0x001d
        L_0x0032:
            android.os.IBinder r23 = com.google.android.gms.internal.zzbgm.zzr(r0, r2)
            goto L_0x001d
        L_0x0037:
            long r21 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x001d
        L_0x003c:
            android.os.Parcelable$Creator<com.google.android.gms.location.LocationRequest> r3 = com.google.android.gms.location.LocationRequest.CREATOR
            java.util.ArrayList r20 = com.google.android.gms.internal.zzbgm.zzc(r0, r2, r3)
            goto L_0x001d
        L_0x0043:
            int r19 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001d
        L_0x0048:
            long r17 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x001d
        L_0x004d:
            android.os.Parcelable$Creator r3 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r16 = r2
            android.app.PendingIntent r16 = (android.app.PendingIntent) r16
            goto L_0x001d
        L_0x0058:
            long r14 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x001d
        L_0x005d:
            long r12 = com.google.android.gms.internal.zzbgm.zzi(r0, r2)
            goto L_0x001d
        L_0x0062:
            int r11 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001d
        L_0x0067:
            int r10 = com.google.android.gms.internal.zzbgm.zzg(r0, r2)
            goto L_0x001d
        L_0x006c:
            android.os.IBinder r9 = com.google.android.gms.internal.zzbgm.zzr(r0, r2)
            goto L_0x001d
        L_0x0071:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataType> r3 = com.google.android.gms.fitness.data.DataType.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r8 = r2
            com.google.android.gms.fitness.data.DataType r8 = (com.google.android.gms.fitness.data.DataType) r8
            goto L_0x001d
        L_0x007b:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataSource> r3 = com.google.android.gms.fitness.data.DataSource.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r0, (int) r2, r3)
            r7 = r2
            com.google.android.gms.fitness.data.DataSource r7 = (com.google.android.gms.fitness.data.DataSource) r7
            goto L_0x001d
        L_0x0085:
            com.google.android.gms.internal.zzbgm.zzaf(r0, r1)
            com.google.android.gms.fitness.request.zzao r0 = new com.google.android.gms.fitness.request.zzao
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r14, r16, r17, r19, r20, r21, r23)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzap.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ zzao[] newArray(int i) {
        return new zzao[i];
    }
}
