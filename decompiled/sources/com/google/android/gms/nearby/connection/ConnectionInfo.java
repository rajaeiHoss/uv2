package com.google.android.gms.nearby.connection;

public final class ConnectionInfo {
    private final String zzjwe;
    private final String zzjwf;
    private final boolean zzjwg;

    public ConnectionInfo(String str, String str2, boolean z) {
        this.zzjwe = str;
        this.zzjwf = str2;
        this.zzjwg = z;
    }

    public final String getAuthenticationToken() {
        return this.zzjwf;
    }

    public final String getEndpointName() {
        return this.zzjwe;
    }

    public final boolean isIncomingConnection() {
        return this.zzjwg;
    }
}
