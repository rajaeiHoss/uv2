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
        String loginUrl;
        this.mApp = (MyApp) this.mConfigUi.getApplication();
        this.mWvContent.getSettings().setJavaScriptEnabled(true);
        this.mPbLoading.setMax(100);
        this.mWvContent.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int progress) {
                WebViewFragment.this.mPbLoading.setProgress(progress);
                if (progress == 100) {
                    WebViewFragment.this.mPbLoading.setVisibility(8);
                    WebViewFragment.this.mWvContent.setVisibility(0);
                }
            }
        });
        this.mWvContent.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String requestedUrl) {
                webView.loadUrl(requestedUrl);
                return false;
            }

            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                webView.loadUrl("file:///android_asset/volis.html");
                new AlertDialog.Builder(WebViewFragment.this.mConfigUi).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.deviceoffline).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                    }
                }).show();
                super.onReceivedError(webView, errorCode, description, failingUrl);
            }
        });
        boolean isHighDefinition = IsHighDefination();
        this.mWvContent.clearHistory();
        if (MyApp.loginType != 0) {
            DvrNet dvrNet = new DvrNet();
            String deviceIp = this.mIp;
            int deviceHandleErrorCode = ((Integer) dvrNet.GetDeviceHandle(deviceIp, this.mMediaport, this.mUsername, this.mPwd, this.mApp.getLocalMacAddress()).get("errorcode")).intValue();
            dvrNet.CloseDeviceHandle();
            if (deviceHandleErrorCode == -1) {
                if (!isHighDefinition) {
                    String natRemark = this.mRemark;
                    loginUrl = String.format("http://%s:5559/nat%s/logincheck.rsp?username=%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, natRemark, this.mUsername, this.mPwd, natRemark, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage))});
                } else {
                    String natRemark = this.mRemark;
                    loginUrl = String.format("http://%s:5559/nat%s/logincheck.rsp?username=%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, natRemark, this.mUsername, this.mPwd, natRemark, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage))});
                }
            } else if (!isHighDefinition) {
                loginUrl = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp", new Object[]{this.mIp, Integer.valueOf(this.mWebport), this.mUsername, this.mPwd, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage)), this.mIp, Integer.valueOf(this.mWebport)});
            } else {
                loginUrl = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp", new Object[]{this.mIp, Integer.valueOf(this.mWebport), this.mUsername, this.mPwd, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage)), this.mIp, Integer.valueOf(this.mWebport)});
            }
        } else if (!this.mIp.contains(".")) {
            if (!isHighDefinition) {
                String natDeviceId = this.mIp;
                loginUrl = String.format("http://%s:5559/nat%s/logincheck.rsp?username=nat%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, natDeviceId, this.mUsername, this.mPwd, natDeviceId, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage))});
            } else {
                String natDeviceId = this.mIp;
                loginUrl = String.format("http://%s:5559/nat%s/logincheck.rsp?username=%s&userpwd=%s&url=/nat%s/WAP/mobileTelephone_left.rsp&language=%d", new Object[]{this.mApp.mUdtServerIp, natDeviceId, this.mUsername, this.mPwd, natDeviceId, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage))});
            }
        } else if (!isHighDefinition) {
            loginUrl = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp%%23MOBLeft.rsp", new Object[]{this.mIp, Integer.valueOf(this.mWebport), this.mUsername, this.mPwd, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage)), this.mIp, Integer.valueOf(this.mWebport)});
        } else {
            loginUrl = String.format("http://%s:%d/logincheck.rsp?username=%s&userpwd=%s&language=%d&url=http://%s:%d/WAP/mobileTelephone_left.rsp", new Object[]{this.mIp, Integer.valueOf(this.mWebport), this.mUsername, this.mPwd, Integer.valueOf(this.mConfigUi.getString(R.string.settinglanguage)), this.mIp, Integer.valueOf(this.mWebport)});
        }
        Class<?> fragmentClass = getClass();
        LogUtils.log(fragmentClass, "url:" + loginUrl);
        this.mWvContent.loadUrl(loginUrl);
    }

    public boolean IsHighDefination() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mConfigUi.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels > 480 && displayMetrics.heightPixels > 800;
    }
}
