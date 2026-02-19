package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Map;

final class zzczx {
    private final long zzedk;
    private final long zzkpu;
    private final long zzkpv;
    private String zzkpw;
    private String zzkwo;
    private Map<String, String> zzkwp;
    private String zzkwq;

    zzczx(long j, long j2, long j3) {
        this.zzkpu = j;
        this.zzedk = j2;
        this.zzkpv = j3;
    }

    /* access modifiers changed from: package-private */
    public final void zzad(Map<String, String> map) {
        this.zzkwp = map;
    }

    /* access modifiers changed from: package-private */
    public final long zzbgr() {
        return this.zzkpu;
    }

    /* access modifiers changed from: package-private */
    public final long zzbgs() {
        return this.zzkpv;
    }

    /* access modifiers changed from: package-private */
    public final String zzbgt() {
        return this.zzkpw;
    }

    /* access modifiers changed from: package-private */
    public final String zzbis() {
        return this.zzkwo;
    }

    /* access modifiers changed from: package-private */
    public final Map<String, String> zzbit() {
        return this.zzkwp;
    }

    /* access modifiers changed from: package-private */
    public final String zzbiu() {
        return this.zzkwq;
    }

    /* access modifiers changed from: package-private */
    public final void zzlv(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.zzkpw = str;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzmn(String str) {
        this.zzkwo = str;
    }

    /* access modifiers changed from: package-private */
    public final void zzmo(String str) {
        this.zzkwq = str;
    }
}
