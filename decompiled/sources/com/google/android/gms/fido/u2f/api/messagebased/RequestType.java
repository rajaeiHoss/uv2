package com.google.android.gms.fido.u2f.api.messagebased;

public enum RequestType {
    REGISTER("u2f_register_request"),
    SIGN("u2f_sign_request");
    
    private final String mValue;

    public static class UnsupportedRequestTypeException extends Exception {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public UnsupportedRequestTypeException(java.lang.String r3) {
            /*
                r2 = this;
                java.lang.String r3 = java.lang.String.valueOf(r3)
                int r0 = r3.length()
                java.lang.String r1 = "Unsupported request type "
                if (r0 == 0) goto L_0x0011
                java.lang.String r3 = r1.concat(r3)
                goto L_0x0016
            L_0x0011:
                java.lang.String r3 = new java.lang.String
                r3.<init>(r1)
            L_0x0016:
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.u2f.api.messagebased.RequestType.UnsupportedRequestTypeException.<init>(java.lang.String):void");
        }
    }

    private RequestType(String str) {
        this.mValue = str;
    }

    public static RequestType fromString(String str) throws UnsupportedRequestTypeException {
        for (RequestType requestType : values()) {
            if (str.equals(requestType.mValue)) {
                return requestType;
            }
        }
        throw new UnsupportedRequestTypeException(str);
    }

    public final String toString() {
        return this.mValue;
    }
}
