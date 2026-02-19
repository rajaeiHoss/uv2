package com.google.android.gms.games.snapshot;

import android.os.Parcelable;

public final class zzd implements Parcelable.Creator<zze> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ zze createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r10)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
        L_0x000a:
            int r1 = r10.dataPosition()
            if (r1 >= r0) goto L_0x004e
            int r1 = r10.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            r8 = 1
            if (r2 == r8) goto L_0x0049
            r8 = 2
            if (r2 == r8) goto L_0x0044
            r8 = 4
            if (r2 == r8) goto L_0x003a
            r8 = 5
            if (r2 == r8) goto L_0x0030
            r8 = 6
            if (r2 == r8) goto L_0x002b
            com.google.android.gms.internal.zzbgm.zzb(r10, r1)
            goto L_0x000a
        L_0x002b:
            java.lang.Long r7 = com.google.android.gms.internal.zzbgm.zzj(r10, r1)
            goto L_0x000a
        L_0x0030:
            android.os.Parcelable$Creator<com.google.android.gms.common.data.BitmapTeleporter> r2 = com.google.android.gms.common.data.BitmapTeleporter.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r10, (int) r1, r2)
            r5 = r1
            com.google.android.gms.common.data.BitmapTeleporter r5 = (com.google.android.gms.common.data.BitmapTeleporter) r5
            goto L_0x000a
        L_0x003a:
            android.os.Parcelable$Creator r2 = android.net.Uri.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r10, (int) r1, r2)
            r6 = r1
            android.net.Uri r6 = (android.net.Uri) r6
            goto L_0x000a
        L_0x0044:
            java.lang.Long r4 = com.google.android.gms.internal.zzbgm.zzj(r10, r1)
            goto L_0x000a
        L_0x0049:
            java.lang.String r3 = com.google.android.gms.internal.zzbgm.zzq(r10, r1)
            goto L_0x000a
        L_0x004e:
            com.google.android.gms.internal.zzbgm.zzaf(r10, r0)
            com.google.android.gms.games.snapshot.zze r10 = new com.google.android.gms.games.snapshot.zze
            r2 = r10
            r2.<init>(r3, r4, r5, r6, r7)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.snapshot.zzd.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ zze[] newArray(int i) {
        return new zze[i];
    }
}
