package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import java.util.Arrays;
import java.util.List;

public class PublicKeyCredentialRequestOptions extends RequestOptions {
    public static final Parcelable.Creator<PublicKeyCredentialRequestOptions> CREATOR = new zzo();
    private final Integer zzgtd;
    private final byte[] zzhfc;
    private final Double zzhfe;
    private final TokenBindingIdValue zzhfh;
    private final AuthenticationExtensions zzhfi;
    private final String zzhfn;
    private final List<PublicKeyCredentialDescriptor> zzhfo;

    public static final class Builder {
        private Integer zzgtd;
        private byte[] zzhfc;
        private Double zzhfe;
        private TokenBindingIdValue zzhfh;
        private String zzhfn;
        private List<PublicKeyCredentialDescriptor> zzhfo;

        public final PublicKeyCredentialRequestOptions build() {
            return new PublicKeyCredentialRequestOptions(this.zzhfc, this.zzhfe, this.zzhfn, this.zzhfo, this.zzgtd, this.zzhfh, (AuthenticationExtensions) null);
        }

        public final Builder setAllowList(List<PublicKeyCredentialDescriptor> list) {
            this.zzhfo = list;
            return this;
        }

        public final Builder setChallenge(byte[] bArr) {
            this.zzhfc = bArr;
            return this;
        }

        public final Builder setRequestId(Integer num) {
            this.zzgtd = num;
            return this;
        }

        public final Builder setRpId(String str) {
            this.zzhfn = str;
            return this;
        }

        public final Builder setTimeoutSeconds(Double d) {
            this.zzhfe = d;
            return this;
        }

        public final Builder setTokenBindingIdValue(TokenBindingIdValue tokenBindingIdValue) {
            this.zzhfh = tokenBindingIdValue;
            return this;
        }
    }

    public PublicKeyCredentialRequestOptions(byte[] bArr, Double d, String str, List<PublicKeyCredentialDescriptor> list, Integer num, TokenBindingIdValue tokenBindingIdValue, AuthenticationExtensions authenticationExtensions) {
        this.zzhfc = (byte[]) zzbq.checkNotNull(bArr);
        this.zzhfe = d;
        this.zzhfn = (String) zzbq.checkNotNull(str);
        this.zzhfo = list;
        this.zzgtd = num;
        this.zzhfh = tokenBindingIdValue;
        this.zzhfi = authenticationExtensions;
    }

    public static PublicKeyCredentialRequestOptions deserializeFromBytes(byte[] bArr) {
        return (PublicKeyCredentialRequestOptions) zzbgq.zza(bArr, CREATOR);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        r2 = r4.zzhfo;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        r3 = r5.zzhfo;
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
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions r5 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions) r5
            byte[] r2 = r4.zzhfc
            byte[] r3 = r5.zzhfc
            boolean r2 = java.util.Arrays.equals(r2, r3)
            if (r2 == 0) goto L_0x0070
            java.lang.Double r2 = r4.zzhfe
            java.lang.Double r3 = r5.zzhfe
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x0070
            java.lang.String r2 = r4.zzhfn
            java.lang.String r3 = r5.zzhfn
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x0070
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r2 = r4.zzhfo
            if (r2 != 0) goto L_0x003b
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r3 = r5.zzhfo
            if (r3 == 0) goto L_0x0051
        L_0x003b:
            if (r2 == 0) goto L_0x0070
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r3 = r5.zzhfo
            if (r3 == 0) goto L_0x0070
            boolean r2 = r2.containsAll(r3)
            if (r2 == 0) goto L_0x0070
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r2 = r5.zzhfo
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r3 = r4.zzhfo
            boolean r2 = r2.containsAll(r3)
            if (r2 == 0) goto L_0x0070
        L_0x0051:
            java.lang.Integer r2 = r4.zzgtd
            java.lang.Integer r3 = r5.zzgtd
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x0070
            com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue r2 = r4.zzhfh
            com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue r3 = r5.zzhfh
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x0070
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r2 = r4.zzhfi
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r5 = r5.zzhfi
            boolean r5 = com.google.android.gms.common.internal.zzbg.equal(r2, r5)
            if (r5 == 0) goto L_0x0070
            return r0
        L_0x0070:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions.equals(java.lang.Object):boolean");
    }

    public List<PublicKeyCredentialDescriptor> getAllowList() {
        return this.zzhfo;
    }

    public byte[] getChallenge() {
        return this.zzhfc;
    }

    public Integer getRequestId() {
        return this.zzgtd;
    }

    public String getRpId() {
        return this.zzhfn;
    }

    public Double getTimeoutSeconds() {
        return this.zzhfe;
    }

    public TokenBindingIdValue getTokenBindingIdValue() {
        return this.zzhfh;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(Arrays.hashCode(this.zzhfc)), this.zzhfe, this.zzhfn, this.zzhfo, this.zzgtd, this.zzhfh, this.zzhfi});
    }

    public byte[] serializeToBytes() {
        return zzbgq.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getChallenge(), false);
        zzbgo.zza(parcel, 3, getTimeoutSeconds(), false);
        zzbgo.zza(parcel, 4, getRpId(), false);
        zzbgo.zzc(parcel, 5, getAllowList(), false);
        zzbgo.zza(parcel, 6, getRequestId(), false);
        zzbgo.zza(parcel, 7, (Parcelable) getTokenBindingIdValue(), i, false);
        zzbgo.zza(parcel, 8, (Parcelable) this.zzhfi, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
