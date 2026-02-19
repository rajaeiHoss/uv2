package com.google.android.gms.drive;

import android.os.Parcelable;

public final class zzd implements Parcelable.Creator<zzc> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ zzc createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r11)
            r1 = 0
            r2 = 0
            r4 = r2
            r7 = r4
            r9 = r7
            r5 = 0
            r6 = 0
            r8 = 0
        L_0x000c:
            int r1 = r11.dataPosition()
            if (r1 >= r0) goto L_0x0059
            int r1 = r11.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            r3 = 2
            if (r2 == r3) goto L_0x004f
            r3 = 3
            if (r2 == r3) goto L_0x004a
            r3 = 4
            if (r2 == r3) goto L_0x0045
            r3 = 5
            if (r2 == r3) goto L_0x003b
            r3 = 7
            if (r2 == r3) goto L_0x0036
            r3 = 8
            if (r2 == r3) goto L_0x0031
            com.google.android.gms.internal.zzbgm.zzb(r11, r1)
            goto L_0x000c
        L_0x0031:
            java.lang.String r9 = com.google.android.gms.internal.zzbgm.zzq(r11, r1)
            goto L_0x000c
        L_0x0036:
            boolean r8 = com.google.android.gms.internal.zzbgm.zzc(r11, r1)
            goto L_0x000c
        L_0x003b:
            android.os.Parcelable$Creator<com.google.android.gms.drive.DriveId> r2 = com.google.android.gms.drive.DriveId.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r11, (int) r1, r2)
            r7 = r1
            com.google.android.gms.drive.DriveId r7 = (com.google.android.gms.drive.DriveId) r7
            goto L_0x000c
        L_0x0045:
            int r6 = com.google.android.gms.internal.zzbgm.zzg(r11, r1)
            goto L_0x000c
        L_0x004a:
            int r5 = com.google.android.gms.internal.zzbgm.zzg(r11, r1)
            goto L_0x000c
        L_0x004f:
            android.os.Parcelable$Creator r2 = android.os.ParcelFileDescriptor.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r11, (int) r1, r2)
            r4 = r1
            android.os.ParcelFileDescriptor r4 = (android.os.ParcelFileDescriptor) r4
            goto L_0x000c
        L_0x0059:
            com.google.android.gms.internal.zzbgm.zzaf(r11, r0)
            com.google.android.gms.drive.zzc r11 = new com.google.android.gms.drive.zzc
            r3 = r11
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.drive.zzd.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ zzc[] newArray(int i) {
        return new zzc[i];
    }
}
