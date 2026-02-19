package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import java.util.Arrays;

public class AuthenticatorAttestationResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator<AuthenticatorAttestationResponse> CREATOR = new zze();
    private final byte[] zzhek;
    private final byte[] zzhel;
    private final byte[] zzheo;

    public static final class Builder {
        private byte[] zzhek;
        private byte[] zzhel;
        private byte[] zzheo;

        public final AuthenticatorAttestationResponse build() {
            return new AuthenticatorAttestationResponse(this.zzhek, this.zzhel, this.zzheo);
        }

        public final Builder setAttestationObject(byte[] bArr) {
            this.zzheo = bArr;
            return this;
        }

        public final Builder setClientDataJSON(byte[] bArr) {
            this.zzhel = bArr;
            return this;
        }

        public final Builder setKeyHandle(byte[] bArr) {
            this.zzhek = bArr;
            return this;
        }
    }

    public AuthenticatorAttestationResponse(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.zzhek = (byte[]) zzbq.checkNotNull(bArr);
        this.zzhel = (byte[]) zzbq.checkNotNull(bArr2);
        this.zzheo = (byte[]) zzbq.checkNotNull(bArr3);
    }

    public static AuthenticatorAttestationResponse deserializeFromBytes(byte[] bArr) {
        return (AuthenticatorAttestationResponse) zzbgq.zza(bArr, CREATOR);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthenticatorAttestationResponse authenticatorAttestationResponse = (AuthenticatorAttestationResponse) obj;
        return Arrays.equals(this.zzhek, authenticatorAttestationResponse.zzhek) && Arrays.equals(this.zzhel, authenticatorAttestationResponse.zzhel) && Arrays.equals(this.zzheo, authenticatorAttestationResponse.zzheo);
    }

    public byte[] getAttestationObject() {
        return this.zzheo;
    }

    public byte[] getClientDataJSON() {
        return this.zzhel;
    }

    public byte[] getKeyHandle() {
        return this.zzhek;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(Arrays.hashCode(this.zzhel)), Integer.valueOf(Arrays.hashCode(this.zzheo))});
    }

    public byte[] serializeToBytes() {
        return zzbgq.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getKeyHandle(), false);
        zzbgo.zza(parcel, 3, getClientDataJSON(), false);
        zzbgo.zza(parcel, 4, getAttestationObject(), false);
        zzbgo.zzai(parcel, zze);
    }
}
