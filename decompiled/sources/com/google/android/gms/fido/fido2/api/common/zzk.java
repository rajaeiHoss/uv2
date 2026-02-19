package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcelable;

public final class zzk implements Parcelable.Creator<MakeCredentialOptions> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ MakeCredentialOptions createFromParcel(android.os.Parcel r14) {
        /*
            r13 = this;
            int r0 = com.google.android.gms.internal.zzbgm.zzd(r14)
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
            r12 = r11
        L_0x000f:
            int r1 = r14.dataPosition()
            if (r1 >= r0) goto L_0x0073
            int r1 = r14.readInt()
            r2 = 65535(0xffff, float:9.1834E-41)
            r2 = r2 & r1
            switch(r2) {
                case 2: goto L_0x0069;
                case 3: goto L_0x005f;
                case 4: goto L_0x005a;
                case 5: goto L_0x0053;
                case 6: goto L_0x004e;
                case 7: goto L_0x0047;
                case 8: goto L_0x003d;
                case 9: goto L_0x0038;
                case 10: goto L_0x002e;
                case 11: goto L_0x0024;
                default: goto L_0x0020;
            }
        L_0x0020:
            com.google.android.gms.internal.zzbgm.zzb(r14, r1)
            goto L_0x000f
        L_0x0024:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions> r2 = com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r2)
            r12 = r1
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r12 = (com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions) r12
            goto L_0x000f
        L_0x002e:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue> r2 = com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r2)
            r11 = r1
            com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue r11 = (com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue) r11
            goto L_0x000f
        L_0x0038:
            java.lang.Integer r10 = com.google.android.gms.internal.zzbgm.zzh(r14, r1)
            goto L_0x000f
        L_0x003d:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria> r2 = com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r2)
            r9 = r1
            com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria r9 = (com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria) r9
            goto L_0x000f
        L_0x0047:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r2 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor.CREATOR
            java.util.ArrayList r8 = com.google.android.gms.internal.zzbgm.zzc(r14, r1, r2)
            goto L_0x000f
        L_0x004e:
            java.lang.Double r7 = com.google.android.gms.internal.zzbgm.zzo(r14, r1)
            goto L_0x000f
        L_0x0053:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters> r2 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters.CREATOR
            java.util.ArrayList r6 = com.google.android.gms.internal.zzbgm.zzc(r14, r1, r2)
            goto L_0x000f
        L_0x005a:
            byte[] r5 = com.google.android.gms.internal.zzbgm.zzt(r14, r1)
            goto L_0x000f
        L_0x005f:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity> r2 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r2)
            r4 = r1
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity r4 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity) r4
            goto L_0x000f
        L_0x0069:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialEntity> r2 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialEntity.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.zzbgm.zza((android.os.Parcel) r14, (int) r1, r2)
            r3 = r1
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialEntity r3 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialEntity) r3
            goto L_0x000f
        L_0x0073:
            com.google.android.gms.internal.zzbgm.zzaf(r14, r0)
            com.google.android.gms.fido.fido2.api.common.MakeCredentialOptions r14 = new com.google.android.gms.fido.fido2.api.common.MakeCredentialOptions
            r2 = r14
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.zzk.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ MakeCredentialOptions[] newArray(int i) {
        return new MakeCredentialOptions[i];
    }
}
