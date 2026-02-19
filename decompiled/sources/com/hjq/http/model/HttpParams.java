package com.hjq.http.model;

import com.hjq.http.EasyConfig;
import java.util.HashMap;
import java.util.Set;

public final class HttpParams {
    private boolean mMultipart;
    private HashMap<String, Object> mParams = EasyConfig.getInstance().getParams();

    public void put(String str, Object obj) {
        if (str != null && obj != null) {
            if (this.mParams == EasyConfig.getInstance().getParams()) {
                this.mParams = new HashMap<>(this.mParams);
            }
            this.mParams.put(str, obj);
        }
    }

    public void remove(String str) {
        if (str != null) {
            if (this.mParams == EasyConfig.getInstance().getParams()) {
                this.mParams = new HashMap<>(this.mParams);
            }
            this.mParams.remove(str);
        }
    }

    public Object get(String str) {
        return this.mParams.get(str);
    }

    public boolean isEmpty() {
        HashMap<String, Object> hashMap = this.mParams;
        return hashMap == null || hashMap.isEmpty();
    }

    public Set<String> getNames() {
        return this.mParams.keySet();
    }

    public HashMap<String, Object> getParams() {
        return this.mParams;
    }

    public boolean isMultipart() {
        return this.mMultipart;
    }

    public void setMultipart(boolean z) {
        this.mMultipart = z;
    }
}
