package com.google.android.gms.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzw;
import com.hjq.permissions.Permission;

@zzabh
public class zzapc extends WebChromeClient {
    private final zzaof zzcct;

    public zzapc(zzaof zzaof) {
        this.zzcct = zzaof;
    }

    private static Context zza(WebView webView) {
        if (!(webView instanceof zzaof)) {
            return webView.getContext();
        }
        zzaof zzaof = (zzaof) webView;
        Activity zztj = zzaof.zztj();
        return zztj != null ? zztj : zzaof.getContext();
    }

    private final boolean zza(Context context, String str, String str2, String str3, String str4, JsResult jsResult, JsPromptResult jsPromptResult, boolean z) {
        AlertDialog create;
        zzw zzun;
        try {
            zzaof zzaof = this.zzcct;
            if (zzaof == null || zzaof.zzua() == null || this.zzcct.zzua().zzun() == null || (zzun = this.zzcct.zzua().zzun()) == null || zzun.zzcz()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(str2);
                if (z) {
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(1);
                    TextView textView = new TextView(context);
                    textView.setText(str3);
                    EditText editText = new EditText(context);
                    editText.setText(str4);
                    linearLayout.addView(textView);
                    linearLayout.addView(editText);
                    create = builder.setView(linearLayout).setPositiveButton(17039370, new zzapi(jsPromptResult, editText)).setNegativeButton(17039360, new zzaph(jsPromptResult)).setOnCancelListener(new zzapg(jsPromptResult)).create();
                } else {
                    create = builder.setMessage(str3).setPositiveButton(17039370, new zzapf(jsResult)).setNegativeButton(17039360, new zzape(jsResult)).setOnCancelListener(new zzapd(jsResult)).create();
                }
                create.show();
                return true;
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 11 + String.valueOf(str3).length());
            sb.append("window.");
            sb.append(str);
            sb.append("('");
            sb.append(str3);
            sb.append("')");
            zzun.zzt(sb.toString());
            return false;
        } catch (WindowManager.BadTokenException e) {
            zzahw.zzc("Fail to display Dialog.", e);
        }
        return false;
    }

    public final void onCloseWindow(WebView webView) {
        String str;
        if (!(webView instanceof zzaof)) {
            str = "Tried to close a WebView that wasn't an AdWebView.";
        } else {
            zzd zztw = ((zzaof) webView).zztw();
            if (zztw == null) {
                str = "Tried to close an AdWebView not associated with an overlay.";
            } else {
                zztw.close();
                return;
            }
        }
        zzahw.zzcz(str);
    }

    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String message = consoleMessage.message();
        String sourceId = consoleMessage.sourceId();
        int lineNumber = consoleMessage.lineNumber();
        StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 19 + String.valueOf(sourceId).length());
        sb.append("JS: ");
        sb.append(message);
        sb.append(" (");
        sb.append(sourceId);
        sb.append(":");
        sb.append(lineNumber);
        sb.append(")");
        String sb2 = sb.toString();
        if (sb2.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage);
        }
        int i = zzapj.zzdsf[consoleMessage.messageLevel().ordinal()];
        if (i == 1) {
            zzahw.e(sb2);
        } else if (i == 2) {
            zzahw.zzcz(sb2);
        } else if (i == 3 || i == 4 || i != 5) {
            zzahw.zzcy(sb2);
        } else {
            zzahw.zzby(sb2);
        }
        return super.onConsoleMessage(consoleMessage);
    }

    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebView.WebViewTransport webViewTransport = (WebView.WebViewTransport) message.obj;
        WebView webView2 = new WebView(webView.getContext());
        if (this.zzcct.zzua() instanceof WebViewClient) {
            webView2.setWebViewClient((WebViewClient) this.zzcct.zzua());
        }
        webViewTransport.setWebView(webView2);
        message.sendToTarget();
        return true;
    }

    public final void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        long j4 = 5242880 - j3;
        if (j4 <= 0) {
            quotaUpdater.updateQuota(j);
            return;
        }
        if (j != 0) {
            if (j2 == 0) {
                j = Math.min(j + Math.min(PlaybackStateCompat.ACTION_PREPARE_FROM_URI, j4), PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
            } else if (j2 <= Math.min(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED - j, j4)) {
                j += j2;
            }
            j2 = j;
        } else if (j2 > j4 || j2 > PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            j2 = 0;
        }
        quotaUpdater.updateQuota(j2);
    }

    public final void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        boolean z;
        if (callback != null) {
            zzbt.zzel();
            if (!zzaij.zzd(this.zzcct.getContext(), this.zzcct.getContext().getPackageName(), Permission.ACCESS_FINE_LOCATION)) {
                zzbt.zzel();
                if (!zzaij.zzd(this.zzcct.getContext(), this.zzcct.getContext().getPackageName(), Permission.ACCESS_COARSE_LOCATION)) {
                    z = false;
                    callback.invoke(str, z, true);
                }
            }
            z = true;
            callback.invoke(str, z, true);
        }
    }

    public final void onHideCustomView() {
        zzd zztw = this.zzcct.zztw();
        if (zztw == null) {
            zzahw.zzcz("Could not get ad overlay when hiding custom view.");
        } else {
            zztw.zzng();
        }
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "alert", str, str2, (String) null, jsResult, (JsPromptResult) null, false);
    }

    public final boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "onBeforeUnload", str, str2, (String) null, jsResult, (JsPromptResult) null, false);
    }

    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "confirm", str, str2, (String) null, jsResult, (JsPromptResult) null, false);
    }

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return zza(zza(webView), "prompt", str, str2, str3, (JsResult) null, jsPromptResult, true);
    }

    public final void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        long j3 = j + PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        if (5242880 - j2 < j3) {
            quotaUpdater.updateQuota(0);
        } else {
            quotaUpdater.updateQuota(j3);
        }
    }

    public final void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        zza(view, -1, customViewCallback);
    }

    /* access modifiers changed from: protected */
    public final void zza(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        zzd zztw = this.zzcct.zztw();
        if (zztw == null) {
            zzahw.zzcz("Could not get ad overlay when showing custom view.");
            customViewCallback.onCustomViewHidden();
            return;
        }
        zztw.zza(view, customViewCallback);
        zztw.setRequestedOrientation(i);
    }
}
