package com.streamax.client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dvr.net.DvrNet;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;
import com.streamax.Configs;
import com.streamax.client.ui.Constants;
import com.streamax.config.base.BaseUi;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.utils.SpUtils;
import com.streamax.utils.StringUtils;
import com.wifi.net.WifiService;
import com.wifi.net.listener.OnWifiConnectListener;
import com.wifi.net.listener.OnWifiEnabledListener;
import com.zycs.UView.R;
import de.greenrobot.event.EventBus;
import java.util.List;
import java.util.Map;

public class Login2Activity extends BaseUi implements OnWifiConnectListener, OnWifiEnabledListener {
    public boolean bConfiguration;
    /* access modifiers changed from: private */
    public boolean isChecked = false;
    public MyApp mApp;
    public DbHelper mDbHelper;
    public TextView mEtPassword;
    public TextView mEtServerIP;
    public TextView mEtUsername;
    public Button mLoginButton;
    public TextView mLoginWifiTv;
    public ProgressBar mProgressBar;
    private SharedPreferences mSp;
    private WifiService mWifiService;
    public Button mtbRemmber;

    /* access modifiers changed from: protected */
    public void initEvent() {
    }

    public void onWiFiConnectFailure(String ssid) {
    }

    public void onWiFiConnectLog(String logMessage) {
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mDbHelper = new DbHelper(this.mContext, DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.mSp = this.mContext.getSharedPreferences(Constants.SpName, 0);
        this.mApp = (MyApp) getApplication();
        MyApp.loginType = 1;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        if (Build.VERSION.SDK_INT >= 9) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        }
        setContentView((int) R.layout.login2);
        findViews();
    }

    /* access modifiers changed from: protected */
    public void initData() {
        WifiService instance = WifiService.getInstance(getApplicationContext());
        this.mWifiService = instance;
        instance.setOnWifiEnabledListener(this);
        this.mWifiService.setOnWifiConnectListener(this);
        XXPermissions.with((Context) this).permission(Permission.ACCESS_COARSE_LOCATION).permission(Permission.ACCESS_FINE_LOCATION).request(new OnPermissionCallback() {
            public void onDenied(List<String> deniedPermissions, boolean doNotAskAgain) {
            }

            public void onGranted(List<String> grantedPermissions, boolean allGranted) {
                if (allGranted) {
                    Login2Activity.this.setWifiName();
                }
            }
        });
    }

    public void findViews() {
        TextView textView = (TextView) findViewById(R.id.Login_type_tv);
        this.mLoginWifiTv = textView;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new Intent(Login2Activity.this, LoginTypeUi.class).putExtra("position", 0);
                Login2Activity.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            }
        });
        this.mEtUsername = (TextView) findViewById(R.id.login_username_edit);
        this.mEtPassword = (TextView) findViewById(R.id.login_password_edit);
        TextView textView2 = (TextView) findViewById(R.id.login_serverip_edit);
        this.mEtServerIP = textView2;
        textView2.setText(MyApp.LastServerHostName);
        this.mEtServerIP.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence != null) {
                    MyApp.ServerHostNameDatas.set(MyApp.loginType, charSequence.toString());
                }
            }
        });
        Button button = (Button) findViewById(R.id.login_remember_toggleButton);
        this.mtbRemmber = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Login2Activity login2Activity = Login2Activity.this;
                boolean unused = login2Activity.isChecked = !login2Activity.isChecked;
                Login2Activity.this.mtbRemmber.setBackgroundResource(!Login2Activity.this.isChecked ? R.drawable.switch_close : R.drawable.switch_open);
            }
        });
        this.mProgressBar = (ProgressBar) findViewById(R.id.loginprogress);
        Button button2 = (Button) findViewById(R.id.login_control_login);
        this.mLoginButton = button2;
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String charSequence = Login2Activity.this.mEtServerIP.getText().toString();
                String charSequence2 = Login2Activity.this.mEtUsername.getText().toString();
                String charSequence3 = Login2Activity.this.mEtPassword.getText().toString();
                if (charSequence.isEmpty() || charSequence2.isEmpty()) {
                    ToastUtils.show((CharSequence) Login2Activity.this.mResources.getString(R.string.login2_server_or_user_empty_Title));
                } else {
                    Login2Activity.this.loginDevice(charSequence, charSequence2, charSequence3);
                }
            }
        });
        if (this.mApp.mbRemember) {
            this.mEtServerIP.setText(MyApp.serverip);
            this.mEtUsername.setText(MyApp.username);
            this.mEtPassword.setText(MyApp.password);
        } else {
            MyApp.loginType = 1;
            this.mEtServerIP.setText("192.168.100.1");
            this.mEtUsername.setText("admin");
        }
        boolean rememberAccount = this.mApp.mbRemember;
        this.isChecked = rememberAccount;
        this.mtbRemmber.setBackgroundResource(!rememberAccount ? R.drawable.switch_close : R.drawable.switch_open);
    }

    public DevInfoBean getDeviceInfo(String serverAddress, String username, String password) {
        DevInfoBean devInfoBean = new DevInfoBean();
        String[] split = serverAddress.split(":");
        if (split.length < 2) {
            devInfoBean.mDevIp = split[0];
            devInfoBean.mMediaPort = 9006;
        } else {
            devInfoBean.mDevIp = split[0];
            devInfoBean.mMediaPort = StringUtils.parse2Int(split[1]);
        }
        devInfoBean.mUsername = username;
        devInfoBean.mPwd = password;
        devInfoBean.mLinkMode = "LAN";
        devInfoBean.mChCounts = 4;
        return devInfoBean;
    }

    /* access modifiers changed from: private */
    public void loginDevice(final String serverAddress, final String username, final String password) {
        new Thread(new Runnable() {
            public void run() {
                final DevInfoBean deviceInfo = Login2Activity.this.getDeviceInfo(serverAddress, username, password);
                DvrNet dvrNet = new DvrNet();
                Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(dvrNet, deviceInfo, Login2Activity.this.mApp);
                int intValue = connDeviceProxy != null ? ((Integer) connDeviceProxy.get("errorcode")).intValue() : -1;
                final int channelCount = dvrNet.nChannelCount;
                Log.d("Login2Activity", "loginDevice result errorCode=" + intValue + " channelCount=" + channelCount);
                dvrNet.CloseDeviceHandle();
                if (intValue != 0 || channelCount <= 0) {
                    Login2Activity.this.mHandler.post(new Runnable() {
                        public void run() {
                            ToastUtils.show((CharSequence) Login2Activity.this.mResources.getString(R.string.login_fail));
                        }
                    });
                } else {
                    Login2Activity.this.mHandler.post(new Runnable() {
                        public void run() {
                            Login2Activity.this.mApp.mDevInfo = deviceInfo;
                            Login2Activity.this.mApp.mDevInfo.mChCounts = channelCount;
                            MyApp.serverip = serverAddress;
                            MyApp.username = username;
                            MyApp.password = password;
                            SpUtils.putInt(Configs.Key.LoginType, MyApp.loginType);
                            Login2Activity.this.mApp.writeuser(Login2Activity.this.isChecked, MyApp.loginType, serverAddress, username, password);
                            Login2Activity.this.startActivity(new Intent(Login2Activity.this, MainActivity.class));
                            Login2Activity.this.finish();
                        }
                    });
                }
            }
        }).start();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 0 && resultCode == -1) {
            this.mEtUsername.setText(intent.getStringExtra("RESULT"));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            this.bConfiguration = true;
        } else if (configuration.orientation == 1) {
            this.bConfiguration = false;
        }
    }

    public void onWiFiConnectSuccess(String ssid) {
        setWifiName();
    }

    public void onWifiEnabled(boolean enabled) {
        setWifiName();
    }

    public void setWifiName() {
        WifiInfo currentWifi;
        WifiService wifiService = this.mWifiService;
        String wifiSsid = "";
        if (!(wifiService == null || (currentWifi = wifiService.getCurrentWifi()) == null)) {
            wifiSsid = currentWifi.getSSID().replaceAll("\"", wifiSsid);
        }
        if (wifiSsid.isEmpty() || "<unknown ssid>" == wifiSsid) {
            this.mLoginWifiTv.setText(R.string.please_select_wifi);
        } else {
            this.mLoginWifiTv.setText(wifiSsid);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setWifiName();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        this.mWifiService.removeOnWifiEnabledListener();
        this.mWifiService.removeOnWifiConnectListener();
        super.onDestroy();
    }
}
