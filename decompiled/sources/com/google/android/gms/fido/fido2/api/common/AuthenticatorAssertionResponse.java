package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import java.util.Arrays;

public class AuthenticatorAssertionResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator<AuthenticatorAssertionResponse> CREATOR = new zzd();
    private final byte[] zzhek;
    private final byte[] zzhel;
    private final byte[] zzhem;
    private final byte[] zzhen;

    public static final class Builder {
        private byte[] zzhek;
        private byte[] zzhel;
        private byte[] zzhem;
        private byte[] zzhen;

        public final AuthenticatorAssertionResponse build() {
            return new AuthenticatorAssertionResponse(this.zzhek, this.zzhel, this.zzhem, this.zzhen);
        }

        public final Builder setAuthenticatorData(byte[] bArr) {
            this.zzhem = bArr;
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

        public final Builder setSignature(byte[] bArr) {
            this.zzhen = bArr;
            return this;
        }
    }

    AuthenticatorAssertionResponse(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        this.zzhek = (byte[]) zzbq.checkNotNull(bArr);
        this.zzhel = (byte[]) zzbq.checkNotNull(bArr2);
        this.zzhem = (byte[]) zzbq.checkNotNull(bArr3);
        this.zzhen = (byte[]) zzbq.checkNotNull(bArr4);
    }

    public static AuthenticatorAssertionResponse deserializeFromBytes(byte[] bArr) {
        return (AuthenticatorAssertionResponse) zzbgq.zza(bArr, CREATOR);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthenticatorAssertionResponse authenticatorAssertionResponse = (AuthenticatorAssertionResponse) obj;
        return Arrays.equals(this.zzhek, authenticatorAssertionResponse.zzhek) && Arrays.equals(this.zzhel, authenticatorAssertionResponse.zzhel) && Arrays.equals(this.zzhem, authenticatorAssertionResponse.zzhem) && Arrays.equals(this.zzhen, authenticatorAssertionResponse.zzhen);
    }

    public byte[] getAuthenticatorData() {
        return this.zzhem;
    }

    public byte[] getClientDataJSON() {
        return this.zzhel;
    }

    public byte[] getKeyHandle() {
        return this.zzhek;
    }

    public byte[] getSignature() {
        return this.zzhen;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(Arrays.hashCode(this.zzhek)), Integer.valueOf(Arrays.hashCode(this.zzhel)), Integer.valueOf(Arrays.hashCode(this.zzhem)), Integer.valueOf(Arrays.hashCode(this.zzhen))});
    }

    public byte[] serializeToBytes() {
        return zzbgq.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getKeyHandle(), false);
        zzbgo.zza(parcel, 3, getClientDataJSON(), false);
        zzbgo.zza(parcel, 4, getAuthenticatorData(), false);
        zzbgo.zza(parcel, 5, getSignature(), false);
        zzbgo.zzai(parcel, zze);
    }
}
