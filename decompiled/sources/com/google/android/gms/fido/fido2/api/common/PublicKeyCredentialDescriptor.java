package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.List;

public class PublicKeyCredentialDescriptor extends zzbgl {
    public static final Parcelable.Creator<PublicKeyCredentialDescriptor> CREATOR = new zzl();
    private final List<Transport> zzdvg;
    private final PublicKeyCredentialType zzhfj;
    private final byte[] zzhfk;

    public PublicKeyCredentialDescriptor(String str, byte[] bArr, List<Transport> list) {
        zzbq.checkNotNull(str);
        try {
            this.zzhfj = PublicKeyCredentialType.fromString(str);
            this.zzhfk = (byte[]) zzbq.checkNotNull(bArr);
            this.zzdvg = list;
        } catch (PublicKeyCredentialType.UnsupportedPublicKeyCredTypeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        r3 = r5.zzdvg;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r5 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.Class r2 = r4.getClass()
            java.lang.Class r3 = r5.getClass()
            if (r2 == r3) goto L_0x0013
            return r1
        L_0x0013:
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor r5 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor) r5
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType r2 = r4.zzhfj
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType r3 = r5.zzhfj
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0020
            return r1
        L_0x0020:
            byte[] r2 = r4.zzhfk
            byte[] r3 = r5.zzhfk
            boolean r2 = java.util.Arrays.equals(r2, r3)
            if (r2 != 0) goto L_0x002b
            return r1
        L_0x002b:
            java.util.List<com.google.android.gms.fido.common.Transport> r2 = r4.zzdvg
            if (r2 != 0) goto L_0x0034
            java.util.List<com.google.android.gms.fido.common.Transport> r3 = r5.zzdvg
            if (r3 != 0) goto L_0x0034
            return r0
        L_0x0034:
            if (r2 == 0) goto L_0x004c
            java.util.List<com.google.android.gms.fido.common.Transport> r3 = r5.zzdvg
            if (r3 != 0) goto L_0x003b
            goto L_0x004c
        L_0x003b:
            boolean r2 = r2.containsAll(r3)
            if (r2 == 0) goto L_0x004c
            java.util.List<com.google.android.gms.fido.common.Transport> r5 = r5.zzdvg
            java.util.List<com.google.android.gms.fido.common.Transport> r2 = r4.zzdvg
            boolean r5 = r5.containsAll(r2)
            if (r5 == 0) goto L_0x004c
            return r0
        L_0x004c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor.equals(java.lang.Object):boolean");
    }

    public byte[] getId() {
        return this.zzhfk;
    }

    public List<Transport> getTransports() {
        return this.zzdvg;
    }

    public PublicKeyCredentialType getType() {
        return this.zzhfj;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhfj, Integer.valueOf(Arrays.hashCode(this.zzhfk)), this.zzdvg});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzhfj.toString(), false);
        zzbgo.zza(parcel, 3, getId(), false);
        zzbgo.zzc(parcel, 4, getTransports(), false);
        zzbgo.zzai(parcel, zze);
    }
}
