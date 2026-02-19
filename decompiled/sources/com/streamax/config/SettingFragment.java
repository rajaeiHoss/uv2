package com.streamax.config;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dvr.net.DvrNet;
import com.streamax.Configs;
import com.streamax.client.DevInfoBean;
import com.streamax.client.MyApp;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.factory.FragmentProduce;
import com.streamax.config.network.NetManager;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.utils.SpUtils;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.Map;

public class SettingFragment extends ConfigFragment {
    public DevInfoBean mDevInfo;
    public DvrNet mDvrNet;
    public int mErrorCode;
    public Map<String, Object> mMap;
    public TextView mTvCms;
    public TextView mTvDevInfo;
    public TextView mTvNgNet;
    public TextView mTvSysTime;

    /* access modifiers changed from: protected */
    public void init() {
        this.mDvrNet = null;
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        VsTextView vsTextView = this.mTvTitle;
        MyApp myApp = this.mApp;
        vsTextView.setText(MyApp.serverip);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.mDevInfo = new DevInfoBean();
        MyApp myApp = this.mApp;
        String[] split = MyApp.serverip.split(":");
        if (split.length < 2) {
            this.mDevInfo.mDevIp = split[0];
            this.mDevInfo.mMediaPort = 9006;
        } else {
            this.mDevInfo.mDevIp = split[0];
            this.mDevInfo.mMediaPort = parse2Int(split[1]).intValue();
        }
        DevInfoBean devInfoBean = this.mDevInfo;
        MyApp myApp2 = this.mApp;
        devInfoBean.mUsername = MyApp.username;
        DevInfoBean devInfoBean2 = this.mDevInfo;
        MyApp myApp3 = this.mApp;
        devInfoBean2.mPwd = MyApp.password;
        this.mDevInfo.mLinkMode = "LAN";
        new Thread(new Runnable() {
            public void run() {
                if (ConfigFragment.dvrNet != null) {
                    NetManager.unInitDvrNet(ConfigFragment.dvrNet);
                    ConfigFragment.dvrNet = null;
                }
                SettingFragment.this.mDvrNet = new DvrNet();
                SettingFragment.this.mErrorCode = -1;
                Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(SettingFragment.this.mDvrNet, SettingFragment.this.mDevInfo, SettingFragment.this.mApp);
                if (connDeviceProxy != null) {
                    SettingFragment.this.mErrorCode = ((Integer) connDeviceProxy.get("errorcode")).intValue();
                }
                if (SettingFragment.this.mErrorCode == 0) {
                    SettingFragment.this.mHandler.post(new Runnable() {
                        public void run() {
                            ConfigFragment.username = SettingFragment.this.mDevInfo.mUsername;
                            SpUtils.putString(Configs.Key.CurDeviceName, SettingFragment.this.mDevInfo.mUsername);
                            ConfigFragment.dvrNet = SettingFragment.this.mDvrNet;
                        }
                    });
                    return;
                }
                if (SettingFragment.this.mDvrNet != null) {
                    SettingFragment.this.mDvrNet.CloseDeviceHandle();
                    SettingFragment.this.mDvrNet = null;
                }
                SettingFragment.this.mHandler.post(new Runnable() {
                    public void run() {
                        int i = SettingFragment.this.mErrorCode;
                        if (i == 5) {
                            SettingFragment.this.toastSf((int) R.string.usernameorpassworderror);
                        } else if (i == 24) {
                            SettingFragment.this.toastSf((int) R.string.moreuseronline);
                        } else if (i == 63) {
                            SettingFragment.this.toastSf((int) R.string.macillegal);
                        } else if (i == 64) {
                            SettingFragment.this.toastSf((int) R.string.permissiondenied);
                        }
                    }
                });
            }
        }).start();
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_setting, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    public void initChildView() {
        this.mTvDevInfo = (TextView) this.mRootView.findViewById(R.id.config_info_setting_dev_info);
        this.mTvSysTime = (TextView) this.mRootView.findViewById(R.id.config_info_setting_sys_time);
        this.mTvNgNet = (TextView) this.mRootView.findViewById(R.id.config_info_setting_xg_net);
        this.mTvCms = (TextView) this.mRootView.findViewById(R.id.config_info_setting_cms);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mTvDevInfo.setOnClickListener(this);
        this.mTvSysTime.setOnClickListener(this);
        this.mTvNgNet.setOnClickListener(this);
        this.mTvCms.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.config_info_setting_cms /*2131362130*/:
                nextPage(FragmentProduce.createFragment(3), 8);
                return;
            case R.id.config_info_setting_dev_info /*2131362132*/:
                nextPage(FragmentProduce.createFragment(0), 8);
                return;
            case R.id.config_info_setting_sys_time /*2131362135*/:
                nextPage(FragmentProduce.createFragment(1), 8);
                return;
            case R.id.config_info_setting_xg_net /*2131362136*/:
                nextPage(FragmentProduce.createFragment(2), 8);
                return;
            default:
                return;
        }
    }
}
