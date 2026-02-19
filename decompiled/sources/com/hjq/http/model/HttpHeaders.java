package com.hjq.http.model;

import com.hjq.http.EasyConfig;
import java.util.HashMap;
import java.util.Set;

public final class HttpHeaders {
    private HashMap<String, String> mHeaders = EasyConfig.getInstance().getHeaders();

    public void put(String str, String str2) {
        if (str != null && str2 != null) {
            if (this.mHeaders == EasyConfig.getInstance().getHeaders()) {
                this.mHeaders = new HashMap<>(this.mHeaders);
            }
            this.mHeaders.put(str, str2);
        }
    }

    public void remove(String str) {
        if (str != null) {
            if (this.mHeaders == EasyConfig.getInstance().getHeaders()) {
                this.mHeaders = new HashMap<>(this.mHeaders);
            }
            this.mHeaders.remove(str);
        }
    }

    public String get(String str) {
        return this.mHeaders.get(str);
    }

    public boolean isEmpty() {
        HashMap<String, String> hashMap = this.mHeaders;
        return hashMap == null || hashMap.isEmpty();
    }

    public Set<String> getNames() {
        return this.mHeaders.keySet();
    }

    public HashMap<String, String> getHeaders() {
        return this.mHeaders;
    }
}
