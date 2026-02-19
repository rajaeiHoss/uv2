package com.streamax.client;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import androidx.media2.subtitle.Cea708CCParser;
import com.dvr.calendar.DayStyle;
import com.dvr.net.DvrNet;
import com.streamax.config.base.ConfigFragment;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.utils.AppProxy;
import com.zycs.UView.R;
import de.greenrobot.event.EventBus;
import java.util.Map;

public class MainActivity extends TabActivity {
    private final int[] TAB_IMAGE_ID = {R.id.maintoolbar_video_image, R.id.maintoolbar_play_image, R.id.maintoolbar_setup_image, R.id.maintoolbar_calibration_image, R.id.maintoolbar_about_image};
    private final int[] TAB_IMAGE_NORMATL = {R.drawable.main_nav_video, R.drawable.main_nav_play, R.drawable.main_nav_setup, R.drawable.main_nav_calibration, R.drawable.main_nav_about};
    private final int[] TAB_IMAGE_PRESSED = {R.drawable.main_nav_video_clicked, R.drawable.main_nav_play_clicked, R.drawable.main_nav_setup_clicked, R.drawable.main_nav_calibration_clicked, R.drawable.main_nav_about_clicked};
    private final int[] TAB_TEXT_ID = {R.id.maintoolbar_video_text, R.id.maintoolbar_play_text, R.id.maintoolbar_setup_text, R.id.maintoolbar_calibration_text, R.id.maintoolbar_about_text};
    public MyApp mApp;
    public Handler mHandler = AppProxy.getHandler();
    RegisterAncyncTask mRegisterTask = null;
    public TabHost mTabHost;
    /* access modifiers changed from: private */
    public LinearLayout[] mTabLinearLayout;
    public TabWidget mTabWidget;
    UdtAncyncTask m_udtTask = null;

    public void gcmRegister() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 9) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        }
        setContentView(R.layout.main_activity);
        getWindow().addFlags(128);
        this.mTabHost = getTabHost();
        this.mTabWidget = getTabWidget();
        MyApp myApp = (MyApp) getApplication();
        this.mApp = myApp;
        myApp.mainActivity = this;
        this.mApp.ReadConfig();
        if (this.mApp.mWebService == null) {
            this.mApp.mWebService = new WebService(MyApp.LastServerHostName, MyApp.username, MyApp.password);
        }
        this.m_udtTask = new UdtAncyncTask();
        this.mRegisterTask = new RegisterAncyncTask();
        LinearLayout[] linearLayoutArr = new LinearLayout[5];
        this.mTabLinearLayout = linearLayoutArr;
        linearLayoutArr[0] = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.videotab, (ViewGroup) null);
        TabHost tabHost = this.mTabHost;
        tabHost.addTab(tabHost.newTabSpec("video").setIndicator(this.mTabLinearLayout[0]).setContent(new Intent(this, LiveViewUi.class)));
        this.mTabLinearLayout[1] = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.playtab, (ViewGroup) null);
        TabHost tabHost2 = this.mTabHost;
        tabHost2.addTab(tabHost2.newTabSpec("play").setIndicator(this.mTabLinearLayout[1]).setContent(new Intent(this, PlayActivity.class)));
        this.mTabLinearLayout[2] = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.setuptab, (ViewGroup) null);
        TabHost tabHost3 = this.mTabHost;
        tabHost3.addTab(tabHost3.newTabSpec("setup").setIndicator(this.mTabLinearLayout[2]).setContent(new Intent(this, ConfigActivity2.class)));
        this.mTabLinearLayout[3] = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.calibrationtab, (ViewGroup) null);
        TabHost tabHost4 = this.mTabHost;
        tabHost4.addTab(tabHost4.newTabSpec("calibration").setIndicator(this.mTabLinearLayout[3]).setContent(new Intent(this, CalibrationActivity.class)));
        this.mTabLinearLayout[4] = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.abouttab, (ViewGroup) null);
        TabHost tabHost5 = this.mTabHost;
        tabHost5.addTab(tabHost5.newTabSpec("about").setIndicator(this.mTabLinearLayout[4]).setContent(new Intent(this, AboutActivity.class)));
        SetTabWidgetBackground(0, true);
        this.mTabHost.setCurrentTab(0);
        this.mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String str) {
                for (int i = 0; i < MainActivity.this.mTabWidget.getChildCount(); i++) {
                    if (MainActivity.this.mTabHost.getCurrentTab() == i) {
                        MainActivity.this.SetTabWidgetBackground(i, true);
                    } else {
                        MainActivity.this.mTabLinearLayout[i].setBackgroundColor(-1);
                        MainActivity.this.SetTabWidgetBackground(i, false);
                    }
                }
            }
        });
        EventBus.getDefault().post(this.mApp.mDevInfo);
    }

    private void getDeviceInfo() {
        new Thread(new Runnable() {
            public void run() {
                DvrNet dvrNet = new DvrNet();
                Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(dvrNet, MainActivity.this.mApp.mDevInfo, MainActivity.this.mApp);
                int intValue = connDeviceProxy != null ? ((Integer) connDeviceProxy.get("errorcode")).intValue() : -1;
                final int i = dvrNet.nChannelCount;
                if (intValue == 0) {
                    dvrNet.CloseDeviceHandle();
                }
                if (intValue == 0 && i > 1) {
                    MainActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            MainActivity.this.mApp.mDevInfo.mChCounts = i;
                            EventBus.getDefault().post(MainActivity.this.mApp.mDevInfo);
                        }
                    });
                }
            }
        }).start();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            this.mTabWidget.setVisibility(8);
        } else if (configuration.orientation == 1) {
            this.mTabWidget.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void SetTabWidgetBackground(int i, boolean z) {
        ((ImageView) findViewById(this.TAB_IMAGE_ID[i])).setImageResource(z ? this.TAB_IMAGE_PRESSED[i] : this.TAB_IMAGE_NORMATL[i]);
        ((TextView) findViewById(this.TAB_TEXT_ID[i])).setTextColor(z ? Color.argb(200, 41, Cea708CCParser.Const.CODE_C1_DF2, 231) : DayStyle.iColorBkg);
    }

    class UdtAncyncTask extends AsyncTask<Void, Void, Void> {
        UdtAncyncTask() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            if (MainActivity.this.mApp.mWebService == null) {
                MainActivity.this.mApp.mWebService = new WebService(MyApp.LastServerHostName, MyApp.username, MyApp.password);
            }
            Map<String, String> GetServer = MainActivity.this.mApp.mWebService.GetServer();
            String str = GetServer.get("nat_ip");
            String str2 = GetServer.get("nat_port");
            if (!(str == null || str2 == null)) {
                MainActivity.this.mApp.mUdtServerIp = str;
                MainActivity.this.mApp.mUdtServerPort = Integer.valueOf(str2).intValue();
            }
            String str3 = GetServer.get("event_ip");
            String str4 = GetServer.get("event_port");
            if (!(str3 == null || str4 == null)) {
                MainActivity.this.mApp.mEventServerIp = str3;
                MainActivity.this.mApp.mEventServerPort = Integer.valueOf(str4).intValue();
            }
            String str5 = GetServer.get("gt_ip");
            String str6 = GetServer.get("gt_port");
            if (!(str5 == null || str6 == null)) {
                MainActivity.this.mApp.mGtServerIp = str5;
            }
            String str7 = GetServer.get("msg_ip");
            String str8 = GetServer.get("msg_port");
            if (str7 == null || str8 == null) {
                return null;
            }
            MainActivity.this.mApp.mMsgServerIp = str7;
            MainActivity.this.mApp.mMsgServerPort = Integer.valueOf(str8).intValue();
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            MainActivity.this.m_udtTask = null;
            super.onPostExecute(voidR);
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }
    }

    class RegisterAncyncTask extends AsyncTask<Void, Void, Void> {
        RegisterAncyncTask() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            MainActivity.this.gcmRegister();
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            MainActivity.this.mRegisterTask = null;
            super.onPostExecute(voidR);
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (ConfigFragment.mPopupWindow == null || !ConfigFragment.mPopupWindow.isShowing()) {
            new AlertDialog.Builder(this).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.confirm_exit).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finish();
                }
            }).show();
            return true;
        }
        ConfigFragment.mPopupWindow.dismiss();
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (ConfigFragment.mPopupWindow == null || !ConfigFragment.mPopupWindow.isShowing()) {
            new AlertDialog.Builder(this).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.confirm_exit).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finish();
                }
            }).show();
            return false;
        }
        ConfigFragment.mPopupWindow.dismiss();
        return false;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        this.mApp.ReadConfig();
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (ConfigFragment.mPopupWindow != null && ConfigFragment.mPopupWindow.isShowing()) {
            ConfigFragment.mPopupWindow.dismiss();
            ConfigFragment.mPopupWindow = null;
        }
        this.mApp.setStreamData();
        this.mApp.WriteConfig();
        super.onDestroy();
        System.exit(0);
    }
}
