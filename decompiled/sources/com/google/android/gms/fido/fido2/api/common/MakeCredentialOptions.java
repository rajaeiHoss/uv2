package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import java.util.Arrays;
import java.util.List;

public class MakeCredentialOptions extends RequestOptions {
    public static final Parcelable.Creator<MakeCredentialOptions> CREATOR = new zzk();
    private final Integer zzgtd;
    private final PublicKeyCredentialEntity zzhfa;
    private final PublicKeyCredentialUserEntity zzhfb;
    private final byte[] zzhfc;
    private final List<PublicKeyCredentialParameters> zzhfd;
    private final Double zzhfe;
    private final List<PublicKeyCredentialDescriptor> zzhff;
    private final AuthenticatorSelectionCriteria zzhfg;
    private final TokenBindingIdValue zzhfh;
    private final AuthenticationExtensions zzhfi;

    public static final class Builder {
        private Integer zzgtd;
        private PublicKeyCredentialEntity zzhfa;
        private PublicKeyCredentialUserEntity zzhfb;
        private byte[] zzhfc;
        private List<PublicKeyCredentialParameters> zzhfd;
        private Double zzhfe;
        private List<PublicKeyCredentialDescriptor> zzhff;
        private AuthenticatorSelectionCriteria zzhfg;
        private TokenBindingIdValue zzhfh;

        public final MakeCredentialOptions build() {
            return new MakeCredentialOptions(this.zzhfa, this.zzhfb, this.zzhfc, this.zzhfd, this.zzhfe, this.zzhff, this.zzhfg, this.zzgtd, this.zzhfh, (AuthenticationExtensions) null);
        }

        public final Builder setAuthenticatorSelection(AuthenticatorSelectionCriteria authenticatorSelectionCriteria) {
            this.zzhfg = authenticatorSelectionCriteria;
            return this;
        }

        public final Builder setChallenge(byte[] bArr) {
            this.zzhfc = bArr;
            return this;
        }

        public final Builder setExcludeList(List<PublicKeyCredentialDescriptor> list) {
            this.zzhff = list;
            return this;
        }

        public final Builder setParameters(List<PublicKeyCredentialParameters> list) {
            this.zzhfd = list;
            return this;
        }

        public final Builder setRequestId(Integer num) {
            this.zzgtd = num;
            return this;
        }

        public final Builder setRp(PublicKeyCredentialEntity publicKeyCredentialEntity) {
            this.zzhfa = publicKeyCredentialEntity;
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

        public final Builder setUser(PublicKeyCredentialUserEntity publicKeyCredentialUserEntity) {
            this.zzhfb = publicKeyCredentialUserEntity;
            return this;
        }
    }

    public MakeCredentialOptions(PublicKeyCredentialEntity publicKeyCredentialEntity, PublicKeyCredentialUserEntity publicKeyCredentialUserEntity, byte[] bArr, List<PublicKeyCredentialParameters> list, Double d, List<PublicKeyCredentialDescriptor> list2, AuthenticatorSelectionCriteria authenticatorSelectionCriteria, Integer num, TokenBindingIdValue tokenBindingIdValue, AuthenticationExtensions authenticationExtensions) {
        this.zzhfa = (PublicKeyCredentialEntity) zzbq.checkNotNull(publicKeyCredentialEntity);
        this.zzhfb = (PublicKeyCredentialUserEntity) zzbq.checkNotNull(publicKeyCredentialUserEntity);
        this.zzhfc = (byte[]) zzbq.checkNotNull(bArr);
        this.zzhfd = (List) zzbq.checkNotNull(list);
        this.zzhfe = d;
        this.zzhff = list2;
        this.zzhfg = authenticatorSelectionCriteria;
        this.zzgtd = num;
        this.zzhfh = tokenBindingIdValue;
        this.zzhfi = authenticationExtensions;
    }

    public static MakeCredentialOptions deserializeFromBytes(byte[] bArr) {
        return (MakeCredentialOptions) zzbgq.zza(bArr, CREATOR);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0051, code lost:
        r2 = r4.zzhff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005b, code lost:
        r3 = r5.zzhff;
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
            com.google.android.gms.fido.fido2.api.common.MakeCredentialOptions r5 = (com.google.android.gms.fido.fido2.api.common.MakeCredentialOptions) r5
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialEntity r2 = r4.zzhfa
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialEntity r3 = r5.zzhfa
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x0098
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity r2 = r4.zzhfb
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity r3 = r5.zzhfb
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x0098
            byte[] r2 = r4.zzhfc
            byte[] r3 = r5.zzhfc
            boolean r2 = java.util.Arrays.equals(r2, r3)
            if (r2 == 0) goto L_0x0098
            java.lang.Double r2 = r4.zzhfe
            java.lang.Double r3 = r5.zzhfe
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x0098
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters> r2 = r4.zzhfd
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters> r3 = r5.zzhfd
            boolean r2 = r2.containsAll(r3)
            if (r2 == 0) goto L_0x0098
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters> r2 = r5.zzhfd
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters> r3 = r4.zzhfd
            boolean r2 = r2.containsAll(r3)
            if (r2 == 0) goto L_0x0098
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r2 = r4.zzhff
            if (r2 != 0) goto L_0x0059
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r3 = r5.zzhff
            if (r3 == 0) goto L_0x006f
        L_0x0059:
            if (r2 == 0) goto L_0x0098
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r3 = r5.zzhff
            if (r3 == 0) goto L_0x0098
            boolean r2 = r2.containsAll(r3)
            if (r2 == 0) goto L_0x0098
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r2 = r5.zzhff
            java.util.List<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r3 = r4.zzhff
            boolean r2 = r2.containsAll(r3)
            if (r2 == 0) goto L_0x0098
        L_0x006f:
            com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria r2 = r4.zzhfg
            com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria r3 = r5.zzhfg
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x0098
            java.lang.Integer r2 = r4.zzgtd
            java.lang.Integer r3 = r5.zzgtd
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x0098
            com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue r2 = r4.zzhfh
            com.google.android.gms.fido.fido2.api.common.TokenBindingIdValue r3 = r5.zzhfh
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x0098
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r2 = r4.zzhfi
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r5 = r5.zzhfi
            boolean r5 = com.google.android.gms.common.internal.zzbg.equal(r2, r5)
            if (r5 == 0) goto L_0x0098
            return r0
        L_0x0098:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.MakeCredentialOptions.equals(java.lang.Object):boolean");
    }

    public AuthenticatorSelectionCriteria getAuthenticatorSelection() {
        return this.zzhfg;
    }

    public byte[] getChallenge() {
        return this.zzhfc;
    }

    public List<PublicKeyCredentialDescriptor> getExcludeList() {
        return this.zzhff;
    }

    public List<PublicKeyCredentialParameters> getParameters() {
        return this.zzhfd;
    }

    public Integer getRequestId() {
        return this.zzgtd;
    }

    public PublicKeyCredentialEntity getRp() {
        return this.zzhfa;
    }

    public Double getTimeoutSeconds() {
        return this.zzhfe;
    }

    public TokenBindingIdValue getTokenBindingIdValue() {
        return this.zzhfh;
    }

    public PublicKeyCredentialUserEntity getUser() {
        return this.zzhfb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhfa, this.zzhfb, Integer.valueOf(Arrays.hashCode(this.zzhfc)), this.zzhfd, this.zzhfe, this.zzhff, this.zzhfg, this.zzgtd, this.zzhfh, this.zzhfi});
    }

    public byte[] serializeToBytes() {
        return zzbgq.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) getRp(), i, false);
        zzbgo.zza(parcel, 3, (Parcelable) getUser(), i, false);
        zzbgo.zza(parcel, 4, getChallenge(), false);
        zzbgo.zzc(parcel, 5, getParameters(), false);
        zzbgo.zza(parcel, 6, getTimeoutSeconds(), false);
        zzbgo.zzc(parcel, 7, getExcludeList(), false);
        zzbgo.zza(parcel, 8, (Parcelable) getAuthenticatorSelection(), i, false);
        zzbgo.zza(parcel, 9, getRequestId(), false);
        zzbgo.zza(parcel, 10, (Parcelable) getTokenBindingIdValue(), i, false);
        zzbgo.zza(parcel, 11, (Parcelable) this.zzhfi, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
