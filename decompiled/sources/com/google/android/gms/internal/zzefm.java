package com.google.android.gms.internal;

import java.util.Map;

final class zzefm {
    private String zznbp;
    /* access modifiers changed from: private */
    public zzefo zznbq;
    private Map<String, Object> zznbv;
    private boolean zznbw;

    private zzefm(String str, Map<String, Object> map, zzefo zzefo) {
        this.zznbp = str;
        this.zznbv = map;
        this.zznbq = zzefo;
    }

    /* synthetic */ zzefm(String str, Map map, zzefo zzefo, zzeez zzeez) {
        this(str, map, zzefo);
    }

    public final String getAction() {
        return this.zznbp;
    }

    public final zzefo zzbxc() {
        return this.zznbq;
    }

    public final Map<String, Object> zzbxg() {
        return this.zznbv;
    }

    public final void zzbxh() {
        this.zznbw = true;
    }

    public final boolean zzbxi() {
        return this.zznbw;
    }
}
