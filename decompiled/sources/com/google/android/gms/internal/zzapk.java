package com.google.android.gms.internal;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.util.Map;

@zzabh
public final class zzapk extends zzapm {
    public zzapk(zzaof zzaof, boolean z) {
        super(zzaof, z);
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zza(webView, str, (Map<String, String>) null);
    }
}
