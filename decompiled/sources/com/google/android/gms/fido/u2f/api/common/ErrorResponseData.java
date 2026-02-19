package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class ErrorResponseData extends ResponseData {
    public static final Parcelable.Creator<ErrorResponseData> CREATOR = new zzf();
    public static final String JSON_ERROR_CODE = "errorCode";
    public static final String JSON_ERROR_MESSAGE = "errorMessage";
    private final String mErrorMessage;
    private final ErrorCode zzhgg;

    ErrorResponseData(int i, String str) {
        this.zzhgg = ErrorCode.toErrorCode(i);
        this.mErrorMessage = str;
    }

    public ErrorResponseData(ErrorCode errorCode) {
        this.zzhgg = (ErrorCode) zzbq.checkNotNull(errorCode);
        this.mErrorMessage = null;
    }

    public ErrorResponseData(ErrorCode errorCode, String str) {
        this.zzhgg = (ErrorCode) zzbq.checkNotNull(errorCode);
        this.mErrorMessage = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ErrorResponseData errorResponseData = (ErrorResponseData) obj;
            return zzbg.equal(this.zzhgg, errorResponseData.zzhgg) && zzbg.equal(this.mErrorMessage, errorResponseData.mErrorMessage);
        }
        return false;
    }

    public ErrorCode getErrorCode() {
        return this.zzhgg;
    }

    public int getErrorCodeAsInt() {
        return this.zzhgg.getCode();
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhgg, this.mErrorMessage});
    }

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", this.zzhgg.getCode());
            String str = this.mErrorMessage;
            if (str != null) {
                jSONObject.put("errorMessage", str);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        if (this.mErrorMessage == null) {
            return String.format(Locale.ENGLISH, "{errorCode: %d}", new Object[]{Integer.valueOf(this.zzhgg.getCode())});
        }
        return String.format(Locale.ENGLISH, "{errorCode: %d, errorMessage: %s}", new Object[]{Integer.valueOf(this.zzhgg.getCode()), this.mErrorMessage});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, getErrorCodeAsInt());
        zzbgo.zza(parcel, 3, getErrorMessage(), false);
        zzbgo.zzai(parcel, zze);
    }
}
