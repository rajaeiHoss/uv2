package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Map;

public final class zzeoq {
    private final String zzlnm;
    private final Map<String, Object> zznrb;

    private zzeoq(String str, Map<String, Object> map) {
        this.zzlnm = str;
        this.zznrb = map;
    }

    public static zzeoq zzqg(String str) {
        if (!str.startsWith("gauth|")) {
            return null;
        }
        try {
            Map<String, Object> zzqh = zzeor.zzqh(str.substring(6));
            return new zzeoq((String) zzqh.get("token"), (Map) zzqh.get("auth"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse gauth token", e);
        }
    }

    public final String getToken() {
        return this.zzlnm;
    }

    public final Map<String, Object> zzcdo() {
        return this.zznrb;
    }
}
