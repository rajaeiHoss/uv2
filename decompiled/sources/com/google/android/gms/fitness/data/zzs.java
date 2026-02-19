package com.google.android.gms.fitness.data;

import android.os.Parcelable;

public final class zzs implements Parcelable.Creator<Goal> {
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ Goal createFromParcel(android.os.Parcel r14) {
        /*
            r13 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r14)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            r1 = 0
            r3 = 0
            r4 = 0
            r4 = r1
            r7 = r3
            r9 = r7
            r10 = r9
            r11 = r10
            r8 = 0
            r2 = r4
        L_0x0014:
            int r1 = r14.dataPosition()
            if (r1 >= r0) goto L_0x006c
            int r1 = r14.readInt()
            r12 = 65535(0xffff, float:9.1834E-41)
            r12 = r12 & r1
            switch(r12) {
                case 1: goto L_0x0067;
                case 2: goto L_0x0062;
                case 3: goto L_0x0056;
                case 4: goto L_0x004c;
                case 5: goto L_0x0047;
                case 6: goto L_0x003d;
                case 7: goto L_0x0033;
                case 8: goto L_0x0029;
                default: goto L_0x0025;
            }
        L_0x0025:
            com.google.android.gms.internal.zzbgm.zzb(r14, r1)
            goto L_0x0014
        L_0x0029:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.Goal$FrequencyObjective> r11 = com.google.android.gms.fitness.data.Goal.FrequencyObjective.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r11)
            r11 = r1
            com.google.android.gms.fitness.data.Goal$FrequencyObjective r11 = (com.google.android.gms.fitness.data.Goal.FrequencyObjective) r11
            goto L_0x0014
        L_0x0033:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.Goal$DurationObjective> r10 = com.google.android.gms.fitness.data.Goal.DurationObjective.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r10)
            r10 = r1
            com.google.android.gms.fitness.data.Goal$DurationObjective r10 = (com.google.android.gms.fitness.data.Goal.DurationObjective) r10
            goto L_0x0014
        L_0x003d:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.Goal$MetricObjective> r9 = com.google.android.gms.fitness.data.Goal.MetricObjective.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r9)
            r9 = r1
            com.google.android.gms.fitness.data.Goal$MetricObjective r9 = (com.google.android.gms.fitness.data.Goal.MetricObjective) r9
            goto L_0x0014
        L_0x0047:
            int r8 = com.google.android.gms.internal.zzbgm.zzg(r14, r1)
            goto L_0x0014
        L_0x004c:
            android.os.Parcelable$Creator<com.google.android.gms.fitness.data.Goal$Recurrence> r7 = com.google.android.gms.fitness.data.Goal.Recurrence.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r7)
            r7 = r1
            com.google.android.gms.fitness.data.Goal$Recurrence r7 = (com.google.android.gms.fitness.data.Goal.Recurrence) r7
            goto L_0x0014
        L_0x0056:
            java.lang.Class r12 = r13.getClass()
            java.lang.ClassLoader r12 = r12.getClassLoader()
            com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, (java.util.List) r6, (java.lang.ClassLoader) r12)
            goto L_0x0014
        L_0x0062:
            long r4 = com.google.android.gms.internal.zzbgm.zzi(r14, r1)
            goto L_0x0014
        L_0x0067:
            long r2 = com.google.android.gms.internal.zzbgm.zzi(r14, r1)
            goto L_0x0014
        L_0x006c:
            com.google.android.gms.internal.zzbgm.zzaf(r14, r0)
            com.google.android.gms.fitness.data.Goal r14 = new com.google.android.gms.fitness.data.Goal
            r1 = r14
            r1.<init>(r2, r4, r6, r7, r8, r9, r10, r11)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.zzs.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Goal[] newArray(int i) {
        return new Goal[i];
    }
}
