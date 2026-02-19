package com.google.android.gms.internal;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import java.util.Set;

public class zzais extends zzair {
    public final boolean zza(DownloadManager.Request request) {
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(1);
        return true;
    }

    public boolean zza(Context context, WebSettings webSettings) {
        super.zza(context, webSettings);
        return ((Boolean) zzakg.zza(context, new zzait(this, context, webSettings))).booleanValue();
    }

    public final boolean zza(Window window) {
        window.setFlags(16777216, 16777216);
        return true;
    }

    public zzaog zzc(zzaof zzaof, boolean z) {
        return new zzapk(zzaof, z);
    }

    public final Set<String> zzg(Uri uri) {
        return uri.getQueryParameterNames();
    }

    public WebChromeClient zzj(zzaof zzaof) {
        return new zzapc(zzaof);
    }

    public final boolean zzx(View view) {
        view.setLayerType(0, (Paint) null);
        return true;
    }

    public final boolean zzy(View view) {
        view.setLayerType(1, (Paint) null);
        return true;
    }
}
