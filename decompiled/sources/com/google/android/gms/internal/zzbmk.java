package com.google.android.gms.internal;

import android.os.Parcelable;

public final class zzbmk implements Parcelable.Creator<zzbmj> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final zzbmj createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r10)
            r1 = 0
            r2 = 0
            r4 = r1
            r6 = r4
            r7 = r6
            r8 = r7
            r5 = 0
        L_0x000b:
            int r1 = r10.dataPosition()
            if (r1 >= r0) goto L_0x004f
            int r1 = r10.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            r3 = 2
            if (r2 == r3) goto L_0x0045
            r3 = 3
            if (r2 == r3) goto L_0x0040
            r3 = 4
            if (r2 == r3) goto L_0x003b
            r3 = 5
            if (r2 == r3) goto L_0x0031
            r3 = 6
            if (r2 == r3) goto L_0x002c
            com.google.android.gms.internal.zzbgm.zzb(r10, r1)
            goto L_0x000b
        L_0x002c:
            java.lang.Integer r8 = com.google.android.gms.internal.zzbgm.zzh(r10, r1)
            goto L_0x000b
        L_0x0031:
            android.os.Parcelable$Creator<com.google.android.gms.drive.DriveId> r2 = com.google.android.gms.drive.DriveId.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r10, (int) r1, r2)
            r7 = r1
            com.google.android.gms.drive.DriveId r7 = (com.google.android.gms.drive.DriveId) r7
            goto L_0x000b
        L_0x003b:
            java.lang.String r6 = com.google.android.gms.internal.zzbgm.zzq(r10, r1)
            goto L_0x000b
        L_0x0040:
            int r5 = com.google.android.gms.internal.zzbgm.zzg(r10, r1)
            goto L_0x000b
        L_0x0045:
            android.os.Parcelable$Creator<com.google.android.gms.drive.metadata.internal.MetadataBundle> r2 = com.google.android.gms.drive.metadata.internal.MetadataBundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r10, (int) r1, r2)
            r4 = r1
            com.google.android.gms.drive.metadata.internal.MetadataBundle r4 = (com.google.android.gms.drive.metadata.internal.MetadataBundle) r4
            goto L_0x000b
        L_0x004f:
            com.google.android.gms.internal.zzbgm.zzaf(r10, r0)
            com.google.android.gms.internal.zzbmj r10 = new com.google.android.gms.internal.zzbmj
            r3 = r10
            r3.<init>(r4, r5, r6, r7, r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbmk.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final zzbmj[] newArray(int i) {
        return new zzbmj[i];
    }
}
