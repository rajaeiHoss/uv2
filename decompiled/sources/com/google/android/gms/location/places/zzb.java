package com.google.android.gms.location.places;

import android.os.Parcelable;

public final class zzb implements Parcelable.Creator<AddPlaceRequest> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ AddPlaceRequest createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r10)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
        L_0x000b:
            int r1 = r10.dataPosition()
            if (r1 >= r0) goto L_0x0048
            int r1 = r10.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 1: goto L_0x0043;
                case 2: goto L_0x0039;
                case 3: goto L_0x0034;
                case 4: goto L_0x002f;
                case 5: goto L_0x002a;
                case 6: goto L_0x0020;
                default: goto L_0x001c;
            }
        L_0x001c:
            com.google.android.gms.internal.zzbgm.zzb(r10, r1)
            goto L_0x000b
        L_0x0020:
            android.os.Parcelable$Creator r2 = android.net.Uri.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r10, (int) r1, r2)
            r8 = r1
            android.net.Uri r8 = (android.net.Uri) r8
            goto L_0x000b
        L_0x002a:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r10, r1)
            goto L_0x000b
        L_0x002f:
            java.util.ArrayList r6 = com.google.android.gms.internal.zzbgm.zzab(r10, r1)
            goto L_0x000b
        L_0x0034:
            java.lang.String r5 = com.google.android.gms.internal.zzbgm.zzq(r10, r1)
            goto L_0x000b
        L_0x0039:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r2 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r10, (int) r1, r2)
            r4 = r1
            com.google.android.gms.maps.model.LatLng r4 = (com.google.android.gms.maps.model.LatLng) r4
            goto L_0x000b
        L_0x0043:
            java.lang.String r3 = com.google.android.gms.internal.zzbgm.zzq(r10, r1)
            goto L_0x000b
        L_0x0048:
            com.google.android.gms.internal.zzbgm.zzaf(r10, r0)
            com.google.android.gms.location.places.AddPlaceRequest r10 = new com.google.android.gms.location.places.AddPlaceRequest
            r2 = r10
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.places.zzb.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ AddPlaceRequest[] newArray(int i) {
        return new AddPlaceRequest[i];
    }
}
