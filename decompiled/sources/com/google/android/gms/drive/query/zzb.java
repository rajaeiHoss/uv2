package com.google.android.gms.drive.query;

import android.os.Parcelable;

public final class zzb implements Parcelable.Creator<Query> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ Query createFromParcel(android.os.Parcel r12) {
        /*
            r11 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r12)
            r1 = 0
            r2 = 0
            r4 = r2
            r5 = r4
            r6 = r5
            r7 = r6
            r9 = r7
            r8 = 0
            r10 = 0
        L_0x000d:
            int r1 = r12.dataPosition()
            if (r1 >= r0) goto L_0x0051
            int r1 = r12.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x0047;
                case 2: goto L_0x001e;
                case 3: goto L_0x0042;
                case 4: goto L_0x0038;
                case 5: goto L_0x0033;
                case 6: goto L_0x002e;
                case 7: goto L_0x0027;
                case 8: goto L_0x0022;
                default: goto L_0x001e;
            }
        L_0x001e:
            com.google.android.gms.internal.zzbgm.zzb(r12, r1)
            goto L_0x000d
        L_0x0022:
            boolean r10 = com.google.android.gms.internal.zzbgm.zzc(r12, r1)
            goto L_0x000d
        L_0x0027:
            android.os.Parcelable$Creator<com.google.android.gms.drive.DriveSpace> r2 = com.google.android.gms.drive.DriveSpace.CREATOR
            java.util.ArrayList r9 = com.google.android.gms.internal.zzbgm.zzc(r12, r1, r2)
            goto L_0x000d
        L_0x002e:
            boolean r8 = com.google.android.gms.internal.zzbgm.zzc(r12, r1)
            goto L_0x000d
        L_0x0033:
            java.util.ArrayList r7 = com.google.android.gms.internal.zzbgm.zzac(r12, r1)
            goto L_0x000d
        L_0x0038:
            android.os.Parcelable$Creator<com.google.android.gms.drive.query.SortOrder> r2 = com.google.android.gms.drive.query.SortOrder.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r12, (int) r1, r2)
            r6 = r1
            com.google.android.gms.drive.query.SortOrder r6 = (com.google.android.gms.drive.query.SortOrder) r6
            goto L_0x000d
        L_0x0042:
            java.lang.String r5 = com.google.android.gms.internal.zzbgm.zzq(r12, r1)
            goto L_0x000d
        L_0x0047:
            android.os.Parcelable$Creator<com.google.android.gms.drive.query.internal.zzr> r2 = com.google.android.gms.drive.query.internal.zzr.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r12, (int) r1, r2)
            r4 = r1
            com.google.android.gms.drive.query.internal.zzr r4 = (com.google.android.gms.drive.query.internal.zzr) r4
            goto L_0x000d
        L_0x0051:
            com.google.android.gms.internal.zzbgm.zzaf(r12, r0)
            com.google.android.gms.drive.query.Query r12 = new com.google.android.gms.drive.query.Query
            r3 = r12
            r3.<init>((com.google.android.gms.drive.query.internal.zzr) r4, (java.lang.String) r5, (com.google.android.gms.drive.query.SortOrder) r6, (java.util.List<java.lang.String>) r7, (boolean) r8, (java.util.List<com.google.android.gms.drive.DriveSpace>) r9, (boolean) r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.drive.query.zzb.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Query[] newArray(int i) {
        return new Query[i];
    }
}
