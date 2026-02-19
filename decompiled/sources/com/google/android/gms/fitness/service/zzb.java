package com.google.android.gms.fitness.service;

import android.os.Parcelable;

public final class zzb implements Parcelable.Creator<FitnessSensorServiceRequest> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ FitnessSensorServiceRequest createFromParcel(android.os.Parcel r12) {
        /*
            r11 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r12)
            r1 = 0
            r3 = 0
            r7 = r1
            r9 = r7
            r5 = r3
            r6 = r5
        L_0x000b:
            int r1 = r12.dataPosition()
            if (r1 >= r0) goto L_0x0042
            int r1 = r12.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            r3 = 1
            if (r2 == r3) goto L_0x0038
            r3 = 2
            if (r2 == r3) goto L_0x0033
            r3 = 3
            if (r2 == r3) goto L_0x002e
            r3 = 4
            if (r2 == r3) goto L_0x0029
            com.google.android.gms.internal.zzbgm.zzb(r12, r1)
            goto L_0x000b
        L_0x0029:
            long r9 = com.google.android.gms.internal.zzbgm.zzi(r12, r1)
            goto L_0x000b
        L_0x002e:
            long r7 = com.google.android.gms.internal.zzbgm.zzi(r12, r1)
            goto L_0x000b
        L_0x0033:
            android.os.IBinder r6 = com.google.android.gms.internal.zzbgm.zzr(r12, r1)
            goto L_0x000b
        L_0x0038:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.DataSource> r2 = com.google.android.gms.fitness.data.DataSource.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r12, (int) r1, r2)
            r5 = r1
            com.google.android.gms.fitness.data.DataSource r5 = (com.google.android.gms.fitness.data.DataSource) r5
            goto L_0x000b
        L_0x0042:
            com.google.android.gms.internal.zzbgm.zzaf(r12, r0)
            com.google.android.gms.fitness.service.FitnessSensorServiceRequest r12 = new com.google.android.gms.fitness.service.FitnessSensorServiceRequest
            r4 = r12
            r4.<init>(r5, r6, r7, r9)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.service.zzb.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ FitnessSensorServiceRequest[] newArray(int i) {
        return new FitnessSensorServiceRequest[i];
    }
}
