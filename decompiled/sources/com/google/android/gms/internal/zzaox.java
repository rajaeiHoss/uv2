package com.google.android.gms.internal;

import com.google.android.gms.internal.zzapa;
import com.google.android.gms.internal.zzapr;
import com.google.android.gms.internal.zzapt;

@zzabh
public final class zzaox<WebViewT extends zzapa & zzapr & zzapt> {
    private final zzaoz zzdsa;
    private final WebViewT zzdsb;

    private zzaox(WebViewT webviewt, zzaoz zzaoz) {
        this.zzdsa = zzaoz;
        this.zzdsb = webviewt;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.gms.internal.zzaoz, com.google.android.gms.internal.zzaoy] */
    public static zzaox<zzaof> zzl(zzaof zzaof) {
        return new zzaox<>(zzaof, new zzaoy(zzaof));
    }
}
