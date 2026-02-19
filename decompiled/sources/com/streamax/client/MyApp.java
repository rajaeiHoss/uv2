package com.streamax.client;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Process;
import android.util.DisplayMetrics;
import com.dvr.net.DvrNet;
import com.hjq.toast.ToastUtils;
import com.streamax.Configs;
import com.streamax.utils.LogUtils;
import com.streamax.utils.SpUtils;
import com.streamax.utils.StringUtils;
import com.zycs.UView.R;
import java.util.ArrayList;
import org.jivesoftware.smackx.workgroup.packet.UserID;

public class MyApp extends Application {
    public static int CONNTYPES = -1;
    public static String LastServerHostName = null;
    public static ArrayList<String> ServerHostNameDatas = new ArrayList<>();
    public static boolean autoLogin = false;
    public static Context context = null;
    public static DvrNet dvrNet = null;
    public static Handler handler = null;
    public static int loginType = 0;
    public static int mainThreadId = 0;
    public static int multiStreamType = 0;
    public static String password = null;
    public static int screenHeight = 0;
    public static int screenWidth = 0;
    public static String serverip = null;
    public static int singleStreamType = 0;
    public static int smartStreamStatus = 0;
    public static String username = null;
    public static boolean wifiStatus = true;
    public LiveViewUi liveViewActivity;
    public long mAlarmMaskInt;
    public int mAspectRadio = 3;
    public long mCommMaskInt;
    public long mDatetimeMaskInt;
    public DevInfoBean mDevInfo;
    public long mDeviceDefMaskInt;
    public long mDiskMaskInt;
    public int mEndTime;
    public String mEventServerIp = "";
    public int mEventServerPort;
    public String mGtServerIp = "";
    public final int mGtServerPort = 17891;
    public String mMsgServerIp = "";
    public int mMsgServerPort = 0;
    public int mNetType = 1;
    public long mNetworkMaskInt;
    public long mOperateMaskInt;
    public int mPtzSpeed = 50;
    public PushInfo mPushInfo;
    public long mRebootDeviceMaskInt;
    public long mRecordMaskInt;
    public String mRegId;
    public int mStartTime;
    public int mStreamType = 0;
    public long mSystemMaskInt;
    public String mUdtServerIp = "";
    public int mUdtServerPort = 0;
    public WebService mWebService;
    public MainActivity mainActivity;
    public boolean mbAutoPlay;
    public boolean mbCurise = true;
    public boolean mbLand = true;
    public boolean mbPush = true;
    public boolean mbRemember;
    public boolean mbSingle;
    public DbHelper mdbHelper = new DbHelper(this, DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);

    public void initDevice() {
        this.mDevInfo = new DevInfoBean();
        String[] split = serverip.split(":");
        if (split.length < 2) {
            this.mDevInfo.mDevIp = split[0];
            this.mDevInfo.mMediaPort = 9006;
        } else {
            this.mDevInfo.mDevIp = split[0];
            this.mDevInfo.mMediaPort = StringUtils.parse2Int(split[1]);
        }
        this.mDevInfo.mUsername = username;
        this.mDevInfo.mPwd = password;
        this.mDevInfo.mLinkMode = "LAN";
        this.mDevInfo.mChCounts = 4;
    }

    public String getLocalMacAddress() {
        String str = new String("00-00-00-00-00-00");
        WifiManager wifiManager = (WifiManager) getSystemService(Configs.Key.WifiStatus);
        if (wifiManager == null) {
            return str;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        String macAddress = connectionInfo.getMacAddress();
        if (macAddress == null) {
            return str;
        }
        String replace = macAddress.replace(":", "-");
        return replace.length() > 0 ? replace : str;
    }

    public void ReadConfig() {
        SharedPreferences sharedPreferences = getSharedPreferences("info", 0);
        this.mStreamType = sharedPreferences.getInt("streamtype", 0);
        this.mPtzSpeed = sharedPreferences.getInt("ptzspeed", 50);
        this.mbAutoPlay = sharedPreferences.getBoolean("autoplay", false);
        this.mbSingle = sharedPreferences.getBoolean("single", false);
        this.mNetType = sharedPreferences.getInt("nettype", 1);
        this.mAspectRadio = sharedPreferences.getInt("aspectradio", 3);
        this.mStartTime = sharedPreferences.getInt("starttime", 3);
        this.mEndTime = sharedPreferences.getInt("endtime", 3);
    }

    public void WriteConfig() {
        SharedPreferences sharedPreferences = getSharedPreferences("info", 0);
        sharedPreferences.edit().putInt("streamtype", this.mStreamType).commit();
        sharedPreferences.edit().putInt("ptzspeed", this.mPtzSpeed).commit();
        sharedPreferences.edit().putBoolean("single", this.mbSingle).commit();
        sharedPreferences.edit().putBoolean("autoplay", this.mbAutoPlay).commit();
        sharedPreferences.edit().putInt("nettype", this.mNetType).commit();
        sharedPreferences.edit().putInt("aspectradio", this.mAspectRadio).commit();
        sharedPreferences.edit().putInt("starttime", this.mStartTime).commit();
        sharedPreferences.edit().putInt("endtime", this.mEndTime).commit();
    }

    public void writeuser(boolean z, int i, String str, String str2, String str3) {
        SharedPreferences sharedPreferences = getSharedPreferences(UserID.ELEMENT_NAME, 0);
        sharedPreferences.edit().putBoolean("remmber", z).commit();
        if (i == 0) {
            return;
        }
        if (!z) {
            sharedPreferences.edit().remove("serverip").remove("username").remove("password").commit();
            return;
        }
        if (str != null && str.length() > 0) {
            sharedPreferences.edit().putString("serverip", str).commit();
        }
        if (str2 != null && str2.length() > 0) {
            sharedPreferences.edit().putString("username", str2).commit();
            sharedPreferences.edit().putString("password", str3).commit();
        }
    }

    public boolean readuser() {
        SharedPreferences sharedPreferences = getSharedPreferences(UserID.ELEMENT_NAME, 0);
        serverip = sharedPreferences.getString("serverip", "");
        username = sharedPreferences.getString("username", "");
        password = sharedPreferences.getString("password", "");
        this.mbRemember = sharedPreferences.getBoolean("remmber", false);
        return false;
    }

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ToastUtils.init(this, new ToastStyle());
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        handler = new Handler();
        mainThreadId = Process.myTid();
        getLoginType();
        getStreamData();
        getServerHostNames();
        getLastServerHostName();
        getLoginStatus();
        getWifiStatus();
    }

    public void getWifiStatus() {
        wifiStatus = SpUtils.getBoolean(Configs.Key.WifiStatus, false);
    }

    public void getLoginStatus() {
        autoLogin = SpUtils.getBoolean(Configs.Key.AutoLogin, false);
    }

    public void getLoginType() {
        loginType = SpUtils.getInt(Configs.Key.LoginType, 0);
    }

    public void getServerHostNames() {
        LogUtils.e("MyApp", "getServerHostNames 1");
        ServerHostNameDatas.add(StringUtils.getString(Integer.valueOf(R.string.normal)));
        ArrayList<String> listData = SpUtils.getListData();
        if (listData.size() > 0) {
            for (int i = 0; i < listData.size(); i++) {
                ServerHostNameDatas.add(listData.get(i));
            }
        } else {
            ServerHostNameDatas.add(SpUtils.getString(Configs.Key.ServerHostName1, "www.viewcan.net"));
            ServerHostNameDatas.add(SpUtils.getString(Configs.Key.ServerHostName2, "www.uviewcan.net"));
            ServerHostNameDatas.add(SpUtils.getString(Configs.Key.ServerHostName3, "www.eviewcan.net"));
        }
        setServerHostNames();
        LogUtils.e("MyApp", "getServerHostNames 2");
    }

    public static void setServerHostNames() {
        LogUtils.e("MyApp", "setServerHostNames 1");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < ServerHostNameDatas.size(); i++) {
            if (i > 0) {
                arrayList.add(ServerHostNameDatas.get(i));
            }
        }
        SpUtils.setListData(arrayList);
        LogUtils.e("MyApp", "setServerHostNames 2");
    }

    public void getLastServerHostName() {
        LogUtils.e("MyApp", "getLastServerHostName 1");
        int i = loginType;
        if (i == 0) {
            LastServerHostName = SpUtils.getString(Configs.Key.LastServerHostName, getResources().getConfiguration().locale.getLanguage().endsWith("zh") ? "www.viewcan.net" : "www.uviewcan.net");
        } else if (i > 0 && i < ServerHostNameDatas.size()) {
            LastServerHostName = ServerHostNameDatas.get(loginType);
        }
        LogUtils.e("MyApp", "getLastServerHostName 2, LastServerHostName: " + LastServerHostName);
    }

    public static void setLastServerHostName() {
        SpUtils.putString(Configs.Key.LastServerHostName, LastServerHostName);
    }

    public void setStreamData() {
        SpUtils.putInt(Configs.Key.SmartStreamStatus, smartStreamStatus);
        SpUtils.putInt(Configs.Key.SingleStreamType, singleStreamType);
        SpUtils.putInt(Configs.Key.MultiStreamType, multiStreamType);
    }

    public void getStreamData() {
        smartStreamStatus = SpUtils.getInt(Configs.Key.SmartStreamStatus, 0);
        singleStreamType = SpUtils.getInt(Configs.Key.SingleStreamType, 0);
        multiStreamType = SpUtils.getInt(Configs.Key.MultiStreamType, 1);
    }

    public void onLowMemory() {
        System.gc();
        super.onLowMemory();
    }

    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }
}
