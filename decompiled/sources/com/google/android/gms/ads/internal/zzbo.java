package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzoi;

final class zzbo extends WebViewClient {
    private /* synthetic */ zzbn zzasj;

    zzbo(zzbn zzbn) {
        this.zzasj = zzbn;
    }

    public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        if (this.zzasj.zzapd != null) {
            try {
                this.zzasj.zzapd.onAdFailedToLoad(0);
            } catch (RemoteException e) {
                zzahw.zzc("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith(this.zzasj.zzec())) {
            return false;
        }
        if (str.startsWith((String) zzlc.zzio().zzd(zzoi.zzbtm))) {
            if (this.zzasj.zzapd != null) {
                try {
                    this.zzasj.zzapd.onAdFailedToLoad(3);
                } catch (RemoteException e) {
                    zzahw.zzc("Could not call AdListener.onAdFailedToLoad().", e);
                }
            }
            this.zzasj.zzk(0);
            return true;
        }
        if (str.startsWith((String) zzlc.zzio().zzd(zzoi.zzbtn))) {
            if (this.zzasj.zzapd != null) {
                try {
                    this.zzasj.zzapd.onAdFailedToLoad(0);
                } catch (RemoteException e2) {
                    zzahw.zzc("Could not call AdListener.onAdFailedToLoad().", e2);
                }
            }
            this.zzasj.zzk(0);
            return true;
        }
        if (str.startsWith((String) zzlc.zzio().zzd(zzoi.zzbto))) {
            if (this.zzasj.zzapd != null) {
                try {
                    this.zzasj.zzapd.onAdLoaded();
                } catch (RemoteException e3) {
                    zzahw.zzc("Could not call AdListener.onAdLoaded().", e3);
                }
            }
            this.zzasj.zzk(this.zzasj.zzv(str));
            return true;
        } else if (str.startsWith("gmsg://")) {
            return true;
        } else {
            if (this.zzasj.zzapd != null) {
                try {
                    this.zzasj.zzapd.onAdLeftApplication();
                } catch (RemoteException e4) {
                    zzahw.zzc("Could not call AdListener.onAdLeftApplication().", e4);
                }
            }
            this.zzasj.zzx(this.zzasj.zzw(str));
            return true;
        }
    }
}
