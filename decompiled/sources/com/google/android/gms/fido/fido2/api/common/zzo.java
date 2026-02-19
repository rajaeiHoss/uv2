package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcelable;

public final class zzo implements Parcelable.Creator<PublicKeyCredentialRequestOptions> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ PublicKeyCredentialRequestOptions createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r11)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
        L_0x000c:
            int r1 = r11.dataPosition()
            if (r1 >= r0) goto L_0x0050
            int r1 = r11.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 2: goto L_0x004b;
                case 3: goto L_0x0046;
                case 4: goto L_0x0041;
                case 5: goto L_0x003a;
                case 6: goto L_0x0035;
                case 7: goto L_0x002b;
                case 8: goto L_0x0021;
                default: goto L_0x001d;
            }
        L_0x001d:
            com.google.android.gms.internal.zzbgm.zzb(r11, r1)
            goto L_0x000c
        L_0x0021:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions> r2 = com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r11, (int) r1, r2)
            r9 = r1
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r9 = (com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions) r9
            goto L_0x000c
        L_0x002b:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue> r2 = com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r11, (int) r1, r2)
            r8 = r1
            com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue r8 = (com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue) r8
            goto L_0x000c
        L_0x0035:
            java.lang.Integer r7 = com.google.android.gms.internal.zzbgm.zzh(r11, r1)
            goto L_0x000c
        L_0x003a:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r2 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor.CREATOR
            java.util.ArrayList r6 = com.google.android.gms.internal.zzbgm.zzc(r11, r1, r2)
            goto L_0x000c
        L_0x0041:
            java.lang.String r5 = com.google.android.gms.internal.zzbgm.zzq(r11, r1)
            goto L_0x000c
        L_0x0046:
            java.lang.Double r4 = com.google.android.gms.internal.zzbgm.zzo(r11, r1)
            goto L_0x000c
        L_0x004b:
            byte[] r3 = com.google.android.gms.internal.zzbgm.zzt(r11, r1)
            goto L_0x000c
        L_0x0050:
            com.google.android.gms.internal.zzbgm.zzaf(r11, r0)
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions r11 = new com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.zzo.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ PublicKeyCredentialRequestOptions[] newArray(int i) {
        return new PublicKeyCredentialRequestOptions[i];
    }
}
