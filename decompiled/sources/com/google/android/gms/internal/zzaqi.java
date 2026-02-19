package com.google.android.gms.internal;

import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.google.android.gms.common.util.zzs;

@zzabh
final class zzaqi {
    private static Boolean zzdsv;

    private zzaqi() {
    }

    static void zza(WebView webView, String str) {
        if (!zzs.zzanv() || !zzb(webView)) {
            String valueOf = String.valueOf(str);
            webView.loadUrl(valueOf.length() != 0 ? "javascript:".concat(valueOf) : new String("javascript:"));
            return;
        }
        webView.evaluateJavascript(str, (ValueCallback) null);
    }

    private static boolean zzb(WebView webView) {
        boolean booleanValue;
        synchronized (zzaqi.class) {
            if (zzdsv == null) {
                try {
                    webView.evaluateJavascript("(function(){})()", (ValueCallback) null);
                    zzdsv = true;
                } catch (IllegalStateException unused) {
                    zzdsv = false;
                }
            }
            booleanValue = zzdsv.booleanValue();
        }
        return booleanValue;
    }
}
