package com.streamax.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dvr.net.DvrNet;
import com.google.android.gms.plus.PlusShare;
import com.google.gson.Gson;
import com.streamax.Configs;
import com.streamax.client.DevInfoBean;
import com.streamax.client.MyApp;
import com.streamax.client.DbHelper;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.bean.DeviceJsonBean;
import com.streamax.config.fragment.WebViewFragment;
import com.streamax.config.network.NetManager;
import com.streamax.config.utils.FragmentUtils;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.utils.AppProxy;
import com.streamax.utils.SpUtils;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceListFragment extends ConfigFragment {
    public String JsonParam = "{\"MODULE\":\"CONFIGMODEL\",\"OPERATION\":\"GET\",\"PARAMETER\":{\"NEWCONF\":{\"EN\":\"?\"}},\"SESSION\":\"696d6858-4dca-4388-831d-522b6fbf6ebf\"}";
    public DeviceListAdapter mAdapter;
    public MyApp mApp;
    public List<Map<String, Object>> mData;
    public DevInfoBean mDevInfo;
    public int mDeviceId = -1;
    public DeviceJsonBean mDeviceJsonBean;
    public List<DevInfoBean> mDeviceList = new ArrayList();
    public View mDeviceListView;
    public DvrNet mDvrNet;
    public int mErrorCode;
    public ListView mLvDevList;
    public Map<String, Object> mMap;
    public ProgressBar mPbLoading;
    public DbHelper mdbHelper;

    public void onClick(View view) {
    }

    /* access modifiers changed from: protected */
    public void prePage() {
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mDeviceJsonBean = new DeviceJsonBean();
        this.mGson = new Gson();
        this.mDvrNet = null;
    }

    /* access modifiers changed from: protected */
    public View initView() {
        initConfigTitleView();
        View inflate = this.mInflater.inflate(R.layout.config_info_device_list, (ViewGroup) null);
        this.mDeviceListView = inflate;
        this.mLvDevList = (ListView) inflate.findViewById(R.id.config_devname_lv);
        this.mPbLoading = (ProgressBar) this.mDeviceListView.findViewById(R.id.config_devname_pb_load);
        return this.mDeviceListView;
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.configure);
        this.mBtnBack.setVisibility(8);
        this.mBtnUpdate.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        loadDeviceModelList();
        if (ConfigFragment.dvrNet != null) {
            NetManager.unInitDvrNet(ConfigFragment.dvrNet);
            ConfigFragment.dvrNet = null;
        }
        this.mLvDevList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BaseFragment.currentDeviceId = ((ViewHolder) view.getTag()).id;
                DeviceListFragment.this.loadDevConfigList(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public void switchModelDatasFragment() {
        this.mPbLoading.setVisibility(8);
        this.mLvDevList.setVisibility(0);
        ConfigFragment.username = this.mDevInfo.mUsername;
        SpUtils.putString(Configs.Key.CurDeviceName, this.mDevInfo.mUsername);
        ConfigFragment.dvrNet = this.mDvrNet;
        nextPage(new ModelDatasFragment());
    }

    /* access modifiers changed from: private */
    public void switchWebViewFragment(View view) {
        this.mPbLoading.setVisibility(8);
        this.mLvDevList.setVisibility(0);
        WebViewFragment webViewFragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ip", this.mDevInfo.mDevIp);
        bundle.putInt("webport", this.mDevInfo.mWebPort);
        bundle.putInt("mediaport", this.mDevInfo.mMediaPort);
        bundle.putString("username", this.mDevInfo.mUsername);
        bundle.putString("password", this.mDevInfo.mPwd);
        bundle.putString("remark", this.mDevInfo.mRemark);
        webViewFragment.setArguments(bundle);
        FragmentUtils.fragmentReplace(this, webViewFragment);
    }

    /* access modifiers changed from: private */
    public void loadDevConfigList(final View view) {
        this.mApp = (MyApp) this.mConfigUi.getApplication();
        if (this.mdbHelper == null) {
            this.mdbHelper = new DbHelper(this.mConfigUi, DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        }
        this.mDeviceId = currentDeviceId;
        if (MyApp.loginType == 0) {
            this.mDevInfo = this.mdbHelper.query(this.mDeviceId);
        } else {
            this.mDevInfo = this.mApp.mWebService.query(this.mDeviceId);
        }
        this.mLvDevList.setVisibility(8);
        this.mPbLoading.setVisibility(0);
        if (this.mDevInfo != null) {
            this.mDeviceJsonBean.setMODULE("CONFIGMODEL");
            this.mDeviceJsonBean.setOPERATION("GET");
            this.mDeviceJsonBean.setSESSION("696d6858-4dca-4388-831d-522b6fbf6ebf");
            DeviceJsonBean.NEWCONF newconf = new DeviceJsonBean.NEWCONF(this.mDevInfo.mUsername, this.mDevInfo.mPwd, "?", "?");
            DeviceJsonBean.PARAMETER parameter = new DeviceJsonBean.PARAMETER();
            parameter.setNEWCONF(newconf);
            this.mDeviceJsonBean.setPARAMETER(parameter);
        }
        new Thread(new Runnable() {
            public void run() {
                if (ConfigFragment.dvrNet != null) {
                    NetManager.unInitDvrNet(ConfigFragment.dvrNet);
                    ConfigFragment.dvrNet = null;
                }
                DeviceListFragment.this.mDvrNet = new DvrNet();
                DeviceListFragment.this.mErrorCode = -1;
                Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(DeviceListFragment.this.mDvrNet, DeviceListFragment.this.mDevInfo, DeviceListFragment.this.mApp);
                if (connDeviceProxy != null) {
                    DeviceListFragment.this.mErrorCode = ((Integer) connDeviceProxy.get("errorcode")).intValue();
                }
                if (DeviceListFragment.this.mErrorCode == 0) {
                    final String GetConfig = DeviceListFragment.this.mDvrNet.GetConfig(DeviceListFragment.this.mDeviceJsonBean.toString());
                    Log.e("getConfig", "" + GetConfig);
                    DeviceListFragment.this.mHandler.post(new Runnable() {
                        public void run() {
                            try {
                                if (TextUtils.isEmpty(GetConfig)) {
                                    DeviceListFragment.this.switchWebViewFragment(view);
                                    Log.e("switchWebViewFragment", "getConfig->isEmpty");
                                    return;
                                }
                                JSONObject jSONObject = new JSONObject(GetConfig).getJSONObject("NEWCONF");
                                if (jSONObject == null) {
                                    DeviceListFragment.this.switchWebViewFragment(view);
                                    Log.e("switchWebViewFragment", "NEWCONF->null");
                                    return;
                                }
                                String string = jSONObject.getString("EN");
                                if (string != null) {
                                    if (!string.isEmpty()) {
                                        if (Integer.parseInt(string) > 0) {
                                            DeviceListFragment.this.getOperateMaskInt(jSONObject.getString("OPERATEMASK"));
                                            DeviceListFragment.this.switchModelDatasFragment();
                                            return;
                                        }
                                        DeviceListFragment.this.switchWebViewFragment(view);
                                        Log.e("switchWebViewFragment", "strEn<=0");
                                        return;
                                    }
                                }
                                DeviceListFragment.this.switchWebViewFragment(view);
                                Log.e("switchWebViewFragment", "strEn->null");
                            } catch (JSONException unused) {
                                DeviceListFragment.this.switchModelDatasFragment();
                                DeviceListFragment.this.getOperateMaskInt((String) null);
                                Log.e("switchWebViewFragment", "JSONException");
                            }
                        }
                    });
                    return;
                }
                if (DeviceListFragment.this.mDvrNet != null) {
                    DeviceListFragment.this.mDvrNet.CloseDeviceHandle();
                    DeviceListFragment.this.mDvrNet = null;
                }
                DeviceListFragment.this.mHandler.post(new Runnable() {
                    public void run() {
                        DeviceListFragment.this.mPbLoading.setVisibility(8);
                        DeviceListFragment.this.mLvDevList.setVisibility(0);
                        int i = DeviceListFragment.this.mErrorCode;
                        if (i == 5) {
                            DeviceListFragment.this.toastSf((int) R.string.usernameorpassworderror);
                        } else if (i == 24) {
                            DeviceListFragment.this.toastSf((int) R.string.moreuseronline);
                        } else if (i == 63) {
                            DeviceListFragment.this.toastSf((int) R.string.macillegal);
                        } else if (i == 64) {
                            DeviceListFragment.this.toastSf((int) R.string.permissiondenied);
                        }
                    }
                });
            }
        }).start();
    }

    private void loadDeviceModelList() {
        this.mApp = (MyApp) getActivity().getApplication();
        this.mdbHelper = new DbHelper(AppProxy.getContext(), DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.mData = getData();
        DeviceListAdapter deviceListAdapter = new DeviceListAdapter(AppProxy.getContext());
        this.mAdapter = deviceListAdapter;
        this.mLvDevList.setAdapter(deviceListAdapter);
    }

    private List<Map<String, Object>> getData() {
        this.mDeviceList.clear();
        if (MyApp.loginType == 0) {
            this.mDeviceList = this.mdbHelper.getlist();
        } else {
            this.mDeviceList = this.mApp.mWebService.GetTerminalInfoAndroid(true);
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mDeviceList.size(); i++) {
            DevInfoBean devInfoBean = this.mDeviceList.get(i);
            HashMap hashMap = new HashMap();
            hashMap.put("id", Integer.valueOf(devInfoBean.mDevId));
            hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, devInfoBean.mDevName);
            hashMap.put("info", String.format("%s:%s,%s:%s", new Object[]{getString(R.string.deviceip), devInfoBean.mDevIp, getString(R.string.mediaport), Integer.valueOf(devInfoBean.mMediaPort).toString()}));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public class DeviceListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public long getItemId(int i) {
            return (long) i;
        }

        public DeviceListAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            if (DeviceListFragment.this.mData == null) {
                return 0;
            }
            return DeviceListFragment.this.mData.size();
        }

        public Object getItem(int i) {
            if (DeviceListFragment.this.mData == null) {
                return null;
            }
            return DeviceListFragment.this.mData.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = this.mInflater.inflate(R.layout.config_info_device_list_item, (ViewGroup) null);
                viewHolder.devname = (TextView) view2.findViewById(R.id.config_tv_devicename);
                viewHolder.devinfo = (TextView) view2.findViewById(R.id.config_tv_deviceinfo);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.devname.setText((String) DeviceListFragment.this.mData.get(i).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
            viewHolder.devinfo.setText((String) DeviceListFragment.this.mData.get(i).get("info"));
            viewHolder.id = ((Integer) DeviceListFragment.this.mData.get(i).get("id")).intValue();
            return view2;
        }
    }

    public final class ViewHolder {
        public TextView devinfo;
        public TextView devname;
        public int id;

        public ViewHolder() {
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void getOperateMaskInt(String str) {
        if (str == null || "".equals(str) || str.isEmpty()) {
            this.mApp.mOperateMaskInt = 0;
        } else {
            this.mApp.mOperateMaskInt = Long.parseLong(str);
        }
        MyApp myApp = this.mApp;
        myApp.mRecordMaskInt = 256 & myApp.mOperateMaskInt;
        MyApp myApp2 = this.mApp;
        myApp2.mNetworkMaskInt = PlaybackStateCompat.ACTION_PLAY_FROM_URI & myApp2.mOperateMaskInt;
        MyApp myApp3 = this.mApp;
        myApp3.mDatetimeMaskInt = 64 & myApp3.mOperateMaskInt;
        MyApp myApp4 = this.mApp;
        myApp4.mAlarmMaskInt = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM & myApp4.mOperateMaskInt;
        MyApp myApp5 = this.mApp;
        myApp5.mCommMaskInt = 512 & myApp5.mOperateMaskInt;
        MyApp myApp6 = this.mApp;
        myApp6.mSystemMaskInt = 32 & myApp6.mOperateMaskInt;
        MyApp myApp7 = this.mApp;
        myApp7.mDiskMaskInt = 4 & myApp7.mOperateMaskInt;
        MyApp myApp8 = this.mApp;
        myApp8.mDeviceDefMaskInt = 8 & myApp8.mOperateMaskInt;
        MyApp myApp9 = this.mApp;
        myApp9.mRebootDeviceMaskInt = 2 & myApp9.mOperateMaskInt;
        Log.e("mOperateMaskInt", "" + this.mApp.mOperateMaskInt);
        Log.e("mRecordMaskInt", "" + this.mApp.mRecordMaskInt);
        Log.e("mNetworkMaskInt", "" + this.mApp.mNetworkMaskInt);
        Log.e("mDatetimeMaskInt", "" + this.mApp.mDatetimeMaskInt);
        Log.e("mAlarmMaskInt", "" + this.mApp.mAlarmMaskInt);
        Log.e("mCommMaskInt", "" + this.mApp.mCommMaskInt);
        Log.e("mSystemMaskInt", "" + this.mApp.mSystemMaskInt);
        Log.e("mDiskMaskInt", "" + this.mApp.mDiskMaskInt);
        Log.e("mDeviceDefMaskInt", "" + this.mApp.mDeviceDefMaskInt);
        Log.e("mRebootDeviceMaskInt", "" + this.mApp.mRebootDeviceMaskInt);
    }
}
