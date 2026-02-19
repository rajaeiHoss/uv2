package com.streamax.config.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.dvr.net.DvrNet;
import com.streamax.client.MyApp;
import com.streamax.config.DeviceListFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.utils.FragmentUtils;
import com.streamax.utils.LogUtils;
import com.zycs.UView.R;

public class WebViewFragment extends ConfigFragment {
    public static final String URL = "file:///android_asset/volis.html";
    private MyApp mApp;
    private String mIp;
    private int mMediaport;
    /* access modifiers changed from: private */
    public ProgressBar mPbLoading;
    private String mPwd;
    private String mRemark;
    private String mUsername;
    private int mWebport;
    /* access modifiers changed from: private */
    public WebView mWvContent;

    public void onClick(View view) {
    }

    /* access modifiers changed from: protected */
    public void prePage() {
    }

    /* access modifiers changed from: protected */
    public void init() {
        Bundle arguments = getArguments();
        this.mIp = arguments.getString("ip");
        this.mUsername = arguments.getString("username");
        this.mPwd = arguments.getString("password");
        this.mRemark = arguments.getString("remark");
        this.mWebport = arguments.getInt("webport");
        this.mMediaport = arguments.getInt("mediaport");
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.webview_ui, (ViewGroup) null);
        this.mWvContent = (WebView) this.mRootView.findViewById(R.id.config_wv_content);
        this.mPbLoading = (ProgressBar) this.mRootView.findViewById(R.id.config_wv_pb_load);
        this.mWvContent.setBackgroundColor(Color.argb(35, 35, 39, 44));
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(String.format("%s:%d", new Object[]{this.mIp, Integer.valueOf(this.mWebport)}));
        this.mBtnBack.setVisibility(0);
        this.mBtnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentUtils.fragmentReplace(WebViewFragment.this, new DeviceListFragment());
            }
        });
        this.mBtnUpdate.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        String str;
        this.mApp = (MyApp) this.mConfigUi.getApplication();
        this.mWvContent.getSettings().setJavaScriptEnabled(true);
        this.mPbLoading.setMax(100);
        this.mWvContent.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                WebViewFragment.this.mPbLoading.setProgress(i);
                if (i == 100) {
                    WebViewFragment.this.mPbLoading.setVisibility(8);
                    WebViewFragment.this.mWvContent.setVisibility(0);
                }
            }
        });
        this.mWvContent.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return false;
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                webView.loadUrl("file:///android_asset/volis.html");
                new AlertDialog.Builder(WebViewFragment.this.mConfigUi).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.deviceoffline).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
                super.onReceivedError(webView, i, str, str2);
            }
        });
        boolean IsHighDefination = IsHighDefination();
        this.mWvContent.clearHistory();
        if (MyApp.loginType != 0) {
            DvrNet dvrNet = new DvrNet();
            String str2 = this.mIp;
            String str3 = str2;
            int intValue = ((Integer) dvrNet.GetDeviceHandle(str3, this.mMediaport, this.mUsername, this.mPwd, this.mApp.getLocalMacAddress()).get("errorcode")).intValue();
            dvrNet.CloseDeviceHandle();
            if (intValue == -1) {
                if (!IsHighDefination) {
                    String str4 = this.mRemark;
                    str = String.format("http://%s:5559/nat%s/logincheck.rsp?username=%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, str4, this.mUsername, this.mPwd, str4, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage))});
                } else {
                    String str5 = this.mRemark;
                    str = String.format("http://%s:5559/nat%s/logincheck.rsp?username=%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, str5, this.mUsername, this.mPwd, str5, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage))});
                }
            } else if (!IsHighDefination) {
                str = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp", new Object[]{this.mIp, Integer.valueOf(this.mWebport), this.mUsername, this.mPwd, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage)), this.mIp, Integer.valueOf(this.mWebport)});
            } else {
                str = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp", new Object[]{this.mIp, Integer.valueOf(this.mWebport), this.mUsername, this.mPwd, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage)), this.mIp, Integer.valueOf(this.mWebport)});
            }
        } else if (!this.mIp.contains(".")) {
            if (!IsHighDefination) {
                String str6 = this.mIp;
                str = String.format("http://%s:5559/nat%s/logincheck.rsp?username=nat%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, str6, this.mUsername, this.mPwd, str6, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage))});
            } else {
                String str7 = this.mIp;
                str = String.format("http://%s:5559/nat%s/logincheck.rsp?username=%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, str7, this.mUsername, this.mPwd, str7, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage))});
            }
        } else if (!IsHighDefination) {
            str = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp", new Object[]{this.mIp, Integer.valueOf(this.mWebport), this.mUsername, this.mPwd, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage)), this.mIp, Integer.valueOf(this.mWebport)});
        } else {
            str = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp", new Object[]{this.mIp, Integer.valueOf(this.mWebport), this.mUsername, this.mPwd, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage)), this.mIp, Integer.valueOf(this.mWebport)});
        }
        Class<?> cls = getClass();
        LogUtils.log(cls, "url:" + str);
        this.mWvContent.loadUrl(str);
    }

    public boolean IsHighDefination() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mConfigUi.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels > 480 && displayMetrics.heightPixels > 800;
    }
}
