package com.google.android.gms.drive.query.internal;

import android.os.Parcelable;

public final class zzh implements Parcelable.Creator<FilterHolder> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v8, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v9, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v10, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v11, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ FilterHolder createFromParcel(android.os.Parcel r13) {
        /*
            r12 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r13)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
        L_0x000e:
            int r1 = r13.dataPosition()
            if (r1 >= r0) goto L_0x007d
            int r1 = r13.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x0073;
                case 2: goto L_0x0069;
                case 3: goto L_0x005f;
                case 4: goto L_0x0055;
                case 5: goto L_0x004b;
                case 6: goto L_0x0041;
                case 7: goto L_0x0037;
                case 8: goto L_0x002d;
                case 9: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.internal.zzbgm.zzb(r13, r1)
            goto L_0x000e
        L_0x0023:
            android.os.Parcelable$Creator<com.google.android.gms.drive.query.internal.zzz> r2 = com.google.android.gms.drive.query.internal.zzz.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r11 = r1
            com.google.android.gms.drive.query.internal.zzz r11 = (com.google.android.gms.drive.query.internal.zzz) r11
            goto L_0x000e
        L_0x002d:
            android.os.Parcelable$Creator<com.google.android.gms.drive.query.internal.zzl> r2 = com.google.android.gms.drive.query.internal.zzl.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r10 = r1
            com.google.android.gms.drive.query.internal.zzl r10 = (com.google.android.gms.drive.query.internal.zzl) r10
            goto L_0x000e
        L_0x0037:
            com.google.android.gms.drive.query.internal.zzo r2 = com.google.android.gms.drive.query.internal.zzn.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r9 = r1
            com.google.android.gms.drive.query.internal.zzn r9 = (com.google.android.gms.drive.query.internal.zzn) r9
            goto L_0x000e
        L_0x0041:
            android.os.Parcelable$Creator<com.google.android.gms.drive.query.internal.zzt> r2 = com.google.android.gms.drive.query.internal.zzt.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r8 = r1
            com.google.android.gms.drive.query.internal.zzt r8 = (com.google.android.gms.drive.query.internal.zzt) r8
            goto L_0x000e
        L_0x004b:
            com.google.android.gms.drive.query.internal.zzq r2 = com.google.android.gms.drive.query.internal.zzp.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r7 = r1
            com.google.android.gms.drive.query.internal.zzp r7 = (com.google.android.gms.drive.query.internal.zzp) r7
            goto L_0x000e
        L_0x0055:
            android.os.Parcelable$Creator<com.google.android.gms.drive.query.internal.zzv> r2 = com.google.android.gms.drive.query.internal.zzv.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r6 = r1
            com.google.android.gms.drive.query.internal.zzv r6 = (com.google.android.gms.drive.query.internal.zzv) r6
            goto L_0x000e
        L_0x005f:
            android.os.Parcelable$Creator<com.google.android.gms.drive.query.internal.zzr> r2 = com.google.android.gms.drive.query.internal.zzr.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r5 = r1
            com.google.android.gms.drive.query.internal.zzr r5 = (com.google.android.gms.drive.query.internal.zzr) r5
            goto L_0x000e
        L_0x0069:
            android.os.Parcelable$Creator<com.google.android.gms.drive.query.internal.zzd> r2 = com.google.android.gms.drive.query.internal.zzd.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r4 = r1
            com.google.android.gms.drive.query.internal.zzd r4 = (com.google.android.gms.drive.query.internal.zzd) r4
            goto L_0x000e
        L_0x0073:
            com.google.android.gms.drive.query.internal.zzc r2 = com.google.android.gms.drive.query.internal.zzb.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r3 = r1
            com.google.android.gms.drive.query.internal.zzb r3 = (com.google.android.gms.drive.query.internal.zzb) r3
            goto L_0x000e
        L_0x007d:
            com.google.android.gms.internal.zzbgm.zzaf(r13, r0)
            com.google.android.gms.drive.query.internal.FilterHolder r13 = new com.google.android.gms.drive.query.internal.FilterHolder
            r2 = r13
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.drive.query.internal.zzh.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ FilterHolder[] newArray(int i) {
        return new FilterHolder[i];
    }
}
