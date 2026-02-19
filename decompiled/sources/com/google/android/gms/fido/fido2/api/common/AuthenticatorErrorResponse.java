package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fido.fido2.api.common.ErrorCode;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import java.util.Arrays;

public class AuthenticatorErrorResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator<AuthenticatorErrorResponse> CREATOR = new zzf();
    private final String mErrorMessage;
    private final ErrorCode zzhep;

    public static final class Builder {
        private String mErrorMessage;
        private ErrorCode zzhep;

        public final AuthenticatorErrorResponse build() {
            return new AuthenticatorErrorResponse(this.zzhep.getCode(), this.mErrorMessage);
        }

        public final Builder setErrorCode(ErrorCode errorCode) {
            this.zzhep = errorCode;
            return this;
        }

        public final Builder setErrorMessage(String str) {
            this.mErrorMessage = str;
            return this;
        }
    }

    AuthenticatorErrorResponse(int i, String str) {
        try {
            this.zzhep = ErrorCode.toErrorCode(i);
            this.mErrorMessage = str;
        } catch (ErrorCode.UnsupportedErrorCodeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static AuthenticatorErrorResponse deserializeFromBytes(byte[] bArr) {
        return (AuthenticatorErrorResponse) zzbgq.zza(bArr, CREATOR);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthenticatorErrorResponse authenticatorErrorResponse = (AuthenticatorErrorResponse) obj;
        return zzbg.equal(this.zzhep, authenticatorErrorResponse.zzhep) && zzbg.equal(this.mErrorMessage, authenticatorErrorResponse.mErrorMessage);
    }

    public byte[] getClientDataJSON() {
        throw new UnsupportedOperationException();
    }

    public ErrorCode getErrorCode() {
        return this.zzhep;
    }

    public int getErrorCodeAsInt() {
        return this.zzhep.getCode();
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhep, this.mErrorMessage});
    }

    public byte[] serializeToBytes() {
        return zzbgq.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, getErrorCodeAsInt());
        zzbgo.zza(parcel, 3, getErrorMessage(), false);
        zzbgo.zzai(parcel, zze);
    }
}
