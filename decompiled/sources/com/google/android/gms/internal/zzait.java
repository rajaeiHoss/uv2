package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

final class zzait implements Callable<Boolean> {
    private /* synthetic */ Context val$context;
    private /* synthetic */ WebSettings zzdfx;

    zzait(zzais zzais, Context context, WebSettings webSettings) {
        this.val$context = context;
        this.zzdfx = webSettings;
    }

    public final Boolean call() throws Exception {
        if (this.val$context.getCacheDir() != null) {
            try {
                String absolutePath = this.val$context.getCacheDir().getAbsolutePath();
                WebSettings webSettings = this.zzdfx;
                webSettings.getClass().getMethod("setAppCachePath", String.class).invoke(webSettings, absolutePath);
                webSettings.getClass().getMethod("setAppCacheMaxSize", long.class).invoke(webSettings, 0L);
                webSettings.getClass().getMethod("setAppCacheEnabled", boolean.class).invoke(webSettings, true);
            } catch (Exception unused) {
            }
        }
        this.zzdfx.setDatabasePath(this.val$context.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
        this.zzdfx.setDatabaseEnabled(true);
        this.zzdfx.setDomStorageEnabled(true);
        this.zzdfx.setDisplayZoomControls(false);
        this.zzdfx.setBuiltInZoomControls(true);
        this.zzdfx.setSupportZoom(true);
        this.zzdfx.setAllowContentAccess(false);
        return true;
    }
}
