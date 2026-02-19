package com.streamax.client;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.zycs.UView.R;

public class ConfigActivity2 extends Activity {
    public static final String TAG = "ConfigActivity2";
    public MyApp mApp;
    private Button mBtnHome;
    private Button mBtnReload;
    public View mRootView;
    public String mUrl;
    public WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mApp = (MyApp) getApplication();
        View inflate = LayoutInflater.from(this).inflate(R.layout.configpage2, (ViewGroup) null);
        this.mRootView = inflate;
        setContentView(inflate);
        Integer valueOf = Integer.valueOf(getString(R.string.settinglanguage));
        this.mUrl = "https://www.baidu.com/";
        this.mUrl = String.format("http://%s/Login?%s|%s|%d", new Object[]{MyApp.serverip, MyApp.username, MyApp.password, valueOf});
        WebView webView = (WebView) this.mRootView.findViewById(R.id.webview);
        this.mWebView = webView;
        webView.setHorizontalScrollBarEnabled(false);
        this.mWebView.setHorizontalScrollbarOverlay(false);
        this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setSupportZoom(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        try {
            WebSettings.class.getMethod("setAppCacheEnabled", Boolean.TYPE).invoke(this.mWebView.getSettings(), Boolean.TRUE);
        } catch (Exception unused) {
        }
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                if (sslErrorHandler != null) {
                    sslErrorHandler.proceed();
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (webView == null || str == null) {
                    return false;
                }
                if (!str.startsWith("http") && !str.startsWith("https")) {
                    return true;
                }
                webView.loadUrl(str);
                return true;
            }
        });
        this.mWebView.loadUrl(this.mUrl);
        Button button = (Button) this.mRootView.findViewById(R.id.config2_title_button_home);
        this.mBtnHome = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConfigActivity2.this.mWebView.loadUrl(ConfigActivity2.this.mUrl);
            }
        });
        Button button2 = (Button) this.mRootView.findViewById(R.id.config2_title_button_reload);
        this.mBtnReload = button2;
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConfigActivity2.this.mWebView.reload();
            }
        });
    }
}
