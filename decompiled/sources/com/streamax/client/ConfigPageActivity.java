package com.streamax.client;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dvr.net.DvrNet;
import com.streamax.utils.LogUtils;
import com.zycs.UView.R;

public class ConfigPageActivity extends LinearLayout {
    public static final String URL = "file:///android_asset/volis.html";
    public String ip;
    public MyApp mApp;
    public ConfigActivity mConfigActivity;
    public ProgressBar mProcessBar;
    public int mProgress;
    public TextView mTitleText;
    public String mUrl;
    public WebView mWebView;
    public int mediaport;
    public String password;
    public String remark;
    public String username;
    public int webport;

    public ConfigPageActivity(Context context) {
        super(context);
    }

    public ConfigPageActivity(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void SetActivity(ConfigActivity configActivity) {
        this.mConfigActivity = configActivity;
        this.mApp = (MyApp) configActivity.getApplication();
    }

    /* access modifiers changed from: protected */
    public void SetData(Bundle bundle) {
        this.ip = bundle.getString("ip");
        this.webport = bundle.getInt("webport");
        this.mediaport = bundle.getInt("mediaport");
        this.username = bundle.getString("username");
        this.password = bundle.getString("password");
        this.remark = bundle.getString("remark");
        FindViews();
        OpenUrl();
    }

    public void FindViews() {
        this.mWebView = (WebView) findViewById(R.id.configwebpage_webview);
        this.mProcessBar = (ProgressBar) findViewById(R.id.configwebpage_busyprogress);
        this.mTitleText = (TextView) findViewById(R.id.configwebpage_title_text);
        this.mTitleText.setText(String.format("%s:%d", new Object[]{this.ip, Integer.valueOf(this.webport)}));
        this.mWebView.setBackgroundColor(Color.argb(35, 35, 39, 44));
        this.mProcessBar.setMax(100);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                ConfigPageActivity.this.mProgress = i;
                ConfigPageActivity.this.mProcessBar.setProgress(ConfigPageActivity.this.mProgress);
                if (i == 100) {
                    ConfigPageActivity.this.mProcessBar.setVisibility(8);
                }
            }
        });
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return false;
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                Class<?> cls = getClass();
                LogUtils.log(cls, "errorCode:" + i);
                webView.loadUrl("file:///android_asset/volis.html");
                new AlertDialog.Builder(ConfigPageActivity.this.mConfigActivity).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.deviceoffline).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
                super.onReceivedError(webView, i, str, str2);
            }
        });
        ((Button) findViewById(R.id.configwebpage_title_back)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConfigPageActivity.this.OpenUrl();
            }
        });
    }

    public boolean IsHighDefination() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mConfigActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels > 480 && displayMetrics.heightPixels > 800;
    }

    public void OpenUrl() {
        String str;
        this.mProcessBar.setVisibility(0);
        this.mWebView.clearHistory();
        boolean IsHighDefination = IsHighDefination();
        if (MyApp.loginType != 0) {
            DvrNet dvrNet = new DvrNet();
            String str2 = this.ip;
            String str3 = str2;
            int intValue = ((Integer) dvrNet.GetDeviceHandle(str3, this.mediaport, this.username, this.password, this.mApp.getLocalMacAddress()).get("errorcode")).intValue();
            dvrNet.CloseDeviceHandle();
            if (intValue == -1) {
                if (!IsHighDefination) {
                    String str4 = this.remark;
                    str = String.format("http://%s:5559/nat%s/logincheck.rsp?username=%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, str4, this.username, this.password, str4, Integer.valueOf(this.mConfigActivity.getString(R.string.settinglanguage))});
                } else {
                    String str5 = this.remark;
                    str = String.format("http://%s:5559/nat%s/logincheck.rsp?username=%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, str5, this.username, this.password, str5, Integer.valueOf(this.mConfigActivity.getString(R.string.settinglanguage))});
                }
            } else if (!IsHighDefination) {
                str = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp", new Object[]{this.ip, Integer.valueOf(this.webport), this.username, this.password, Integer.valueOf(this.mConfigActivity.getString(R.string.settinglanguage)), this.ip, Integer.valueOf(this.webport)});
            } else {
                str = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp", new Object[]{this.ip, Integer.valueOf(this.webport), this.username, this.password, Integer.valueOf(this.mConfigActivity.getString(R.string.settinglanguage)), this.ip, Integer.valueOf(this.webport)});
            }
        } else if (!this.ip.contains(".")) {
            if (!IsHighDefination) {
                String str6 = this.ip;
                str = String.format("http://%s:5559/nat%s/logincheck.rsp?username=nat%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, str6, this.username, this.password, str6, Integer.valueOf(this.mConfigActivity.getString(R.string.settinglanguage))});
            } else {
                String str7 = this.ip;
                str = String.format("http://%s:5559/nat%s/logincheck.rsp?username=%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, str7, this.username, this.password, str7, Integer.valueOf(this.mConfigActivity.getString(R.string.settinglanguage))});
            }
        } else if (!IsHighDefination) {
            str = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp", new Object[]{this.ip, Integer.valueOf(this.webport), this.username, this.password, Integer.valueOf(this.mConfigActivity.getString(R.string.settinglanguage)), this.ip, Integer.valueOf(this.webport)});
        } else {
            str = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp", new Object[]{this.ip, Integer.valueOf(this.webport), this.username, this.password, Integer.valueOf(this.mConfigActivity.getString(R.string.settinglanguage)), this.ip, Integer.valueOf(this.webport)});
        }
        this.mProcessBar.bringToFront();
        this.mWebView.loadUrl(str);
    }
}
