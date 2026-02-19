package com.google.android.gms.internal;

public final class zzeeu {
    private final String host;
    private final boolean secure;
    private final String zzkal;

    public zzeeu(String str, String str2, boolean z) {
        this.host = str;
        this.zzkal = str2;
        this.secure = z;
    }

    public final String getHost() {
        return this.host;
    }

    public final String getNamespace() {
        return this.zzkal;
    }

    public final boolean isSecure() {
        return this.secure;
    }

    public final String toString() {
        String str = this.secure ? "s" : "";
        String str2 = this.host;
        StringBuilder sb = new StringBuilder(str.length() + 7 + String.valueOf(str2).length());
        sb.append("http");
        sb.append(str);
        sb.append("://");
        sb.append(str2);
        return sb.toString();
    }
}
