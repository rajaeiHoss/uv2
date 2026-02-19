package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzbt;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzabh
public class zzapm extends zzaog {
    public zzapm(zzaof zzaof, boolean z) {
        super(zzaof, z);
    }

    /* access modifiers changed from: protected */
    public final WebResourceResponse zza(WebView webView, String str, Map<String, String> map) {
        if (!(webView instanceof zzaof)) {
            zzahw.zzcz("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return null;
        }
        zzaof zzaof = (zzaof) webView;
        if (this.zzaop != null) {
            this.zzaop.zza(str, map, 1);
        }
        if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
            return super.shouldInterceptRequest(webView, str);
        }
        if (zzaof.zzua() != null) {
            zzaof.zzua().zznj();
        }
        String str2 = (String) zzlc.zzio().zzd(zzaof.zzty().zzvl() ? zzoi.zzbnb : zzaof.zzud() ? zzoi.zzbna : zzoi.zzbmz);
        try {
            Context context = zzaof.getContext();
            String str3 = zzaof.zztl().zzcu;
            HashMap hashMap = new HashMap();
            hashMap.put("User-Agent", zzbt.zzel().zzl(context, str3));
            hashMap.put("Cache-Control", "max-stale=3600");
            String str4 = (String) new zzajr(context).zzb(str2, hashMap).get(60, TimeUnit.SECONDS);
            if (str4 == null) {
                return null;
            }
            return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(str4.getBytes("UTF-8")));
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            String valueOf = String.valueOf(e.getMessage());
            zzahw.zzcz(valueOf.length() != 0 ? "Could not fetch MRAID JS. ".concat(valueOf) : new String("Could not fetch MRAID JS. "));
            return null;
        }
    }
}
