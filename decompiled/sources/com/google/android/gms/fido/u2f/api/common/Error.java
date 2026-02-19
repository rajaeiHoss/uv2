package com.google.android.gms.fido.u2f.api.common;

import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class Error {
    public static final String JSON_ERROR_CODE = "errorCode";
    public static final String JSON_ERROR_MESSAGE = "errorMessage";
    private final String mErrorMessage;
    private final ErrorCode zzhgg;

    public Error(ErrorCode errorCode) {
        this.zzhgg = errorCode;
        this.mErrorMessage = null;
    }

    public Error(ErrorCode errorCode, String str) {
        this.zzhgg = errorCode;
        this.mErrorMessage = str;
    }

    public ErrorCode getErrorCode() {
        return this.zzhgg;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
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
}
