package com.google.android.gms.drive.events;

import android.os.Parcelable;

public final class zzg implements Parcelable.Creator<CompletionEvent> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ CompletionEvent createFromParcel(android.os.Parcel r13) {
        /*
            r12 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r13)
            r1 = 0
            r2 = 0
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r11 = r9
            r10 = 0
        L_0x000e:
            int r1 = r13.dataPosition()
            if (r1 >= r0) goto L_0x005f
            int r1 = r13.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 2: goto L_0x0055;
                case 3: goto L_0x0050;
                case 4: goto L_0x0046;
                case 5: goto L_0x003c;
                case 6: goto L_0x0032;
                case 7: goto L_0x002d;
                case 8: goto L_0x0028;
                case 9: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.internal.zzbgm.zzb(r13, r1)
            goto L_0x000e
        L_0x0023:
            android.os.IBinder r11 = com.google.android.gms.internal.zzbgm.zzr(r13, r1)
            goto L_0x000e
        L_0x0028:
            int r10 = com.google.android.gms.internal.zzbgm.zzg(r13, r1)
            goto L_0x000e
        L_0x002d:
            java.util.ArrayList r9 = com.google.android.gms.internal.zzbgm.zzac(r13, r1)
            goto L_0x000e
        L_0x0032:
            android.os.Parcelable$Creator<com.google.android.gms.drive.metadata.internal.MetadataBundle> r2 = com.google.android.gms.drive.metadata.internal.MetadataBundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r8 = r1
            com.google.android.gms.drive.metadata.internal.MetadataBundle r8 = (com.google.android.gms.drive.metadata.internal.MetadataBundle) r8
            goto L_0x000e
        L_0x003c:
            android.os.Parcelable$Creator r2 = android.os.ParcelFileDescriptor.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r7 = r1
            android.os.ParcelFileDescriptor r7 = (android.os.ParcelFileDescriptor) r7
            goto L_0x000e
        L_0x0046:
            android.os.Parcelable$Creator r2 = android.os.ParcelFileDescriptor.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r6 = r1
            android.os.ParcelFileDescriptor r6 = (android.os.ParcelFileDescriptor) r6
            goto L_0x000e
        L_0x0050:
            java.lang.String r5 = com.google.android.gms.internal.zzbgm.zzq(r13, r1)
            goto L_0x000e
        L_0x0055:
            android.os.Parcelable$Creator<com.google.android.gms.drive.DriveId> r2 = com.google.android.gms.drive.DriveId.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r13, (int) r1, r2)
            r4 = r1
            com.google.android.gms.drive.DriveId r4 = (com.google.android.gms.drive.DriveId) r4
            goto L_0x000e
        L_0x005f:
            com.google.android.gms.internal.zzbgm.zzaf(r13, r0)
            com.google.android.gms.drive.events.CompletionEvent r13 = new com.google.android.gms.drive.events.CompletionEvent
            r3 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.drive.events.zzg.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ CompletionEvent[] newArray(int i) {
        return new CompletionEvent[i];
    }
}
