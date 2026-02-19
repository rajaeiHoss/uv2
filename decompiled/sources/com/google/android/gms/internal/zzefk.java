package com.google.android.gms.internal;

import java.util.List;

final class zzefk {
    private final Object data;
    private final List<String> zznbn;
    private final String zznbp;
    /* access modifiers changed from: private */
    public final zzefo zznbq;

    private zzefk(String str, List<String> list, Object obj, zzefo zzefo) {
        this.zznbp = str;
        this.zznbn = list;
        this.data = obj;
        this.zznbq = zzefo;
    }

    /* synthetic */ zzefk(String str, List list, Object obj, zzefo zzefo, zzeez zzeez) {
        this(str, list, obj, zzefo);
    }

    public final String getAction() {
        return this.zznbp;
    }

    public final Object getData() {
        return this.data;
    }

    public final List<String> getPath() {
        return this.zznbn;
    }

    public final zzefo zzbxc() {
        return this.zznbq;
    }
}
