package com.google.android.gms.fido.u2f.api.messagebased;

import com.google.android.gms.fido.u2f.api.messagebased.RequestType;

public enum ResponseType {
    REGISTER("u2f_register_response"),
    SIGN("u2f_sign_response");
    
    private final String mValue;

    private ResponseType(String str) {
        this.mValue = str;
    }

    public static ResponseType getResponseTypeForRequestType(RequestType requestType) throws RequestType.UnsupportedRequestTypeException {
        if (requestType != null) {
            int i = zza.zzhha[requestType.ordinal()];
            if (i == 1) {
                return REGISTER;
            }
            if (i == 2) {
                return SIGN;
            }
            throw new RequestType.UnsupportedRequestTypeException(requestType.toString());
        }
        throw new RequestType.UnsupportedRequestTypeException((String) null);
    }

    public final String toString() {
        return this.mValue;
    }
}
