package com.streamax.client.ui.devlist.ui;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.plus.PlusShare;
import com.streamax.client.DevInfoBean;
import com.streamax.client.MyApp;
import com.streamax.client.DbHelper;
import com.streamax.client.ui.devlist.CustomExpandAdapter;
import com.streamax.client.ui.devlist.CustomExpandLv;
import com.streamax.client.ui.devlist.db.GroupBean;
import com.streamax.client.ui.devlist.db.GroupDao;
import com.streamax.client.ui.devlist.db.GroupDaoForNormal;
import com.streamax.client.ui.devlist.db.GroupDaoForServer;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.utils.FragmentUtils;
import com.streamax.utils.AppProxy;
import com.streamax.utils.ToastSf;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DevGroupFragment extends BaseFragment {
    private FragmentActivity mActivity;
    private CustomExpandAdapter mAdapter;
    private MyApp mApp;
    private Button mBtnLeft;
    private Button mBtnRight;
    private ArrayList<ArrayList<String>> mChildDatas;
    private View mChildView;
    private GroupDao mDao;
    private DbHelper mDbHelper;
    private List<Map<String, Object>> mDevDatas;
    private EditText mEtName;
    private boolean mFlag;
    private ArrayList<String> mFlagData01;
    private List<ArrayList<String>> mFlagData02 = new ArrayList();
    private List<GroupBean> mGroupDatas;
    private String mGroupName;
    private View mHeaderView;
    private CustomExpandLv mLv;

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.mDbHelper = new DbHelper(AppProxy.getContext(), DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        Bundle arguments = getArguments();
        this.mFlag = arguments.getBoolean("flag");
        this.mGroupName = arguments.getString("groupName");
    }

    /* access modifiers changed from: protected */
    public View initView() {
        View inflate = this.mInflater.inflate(R.layout.dev_group_ui, (ViewGroup) null);
        this.mChildView = inflate;
        this.mEtName = (EditText) inflate.findViewById(R.id.dev_group_et_name);
        CustomExpandLv customExpandLv = (CustomExpandLv) this.mChildView.findViewById(R.id.dev_group_lv);
        this.mLv = customExpandLv;
        customExpandLv.requestDisallowInterceptTouchEvent(false);
        return this.mChildView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        FragmentActivity activity = getActivity();
        this.mActivity = activity;
        this.mApp = (MyApp) activity.getApplication();
        if (MyApp.loginType == 0) {
            this.mDao = new GroupDaoForNormal();
        } else {
            this.mDao = new GroupDaoForServer();
        }
        initWidgetInParent();
        this.mDevDatas = getData();
        if (this.mFlag) {
            this.mGroupDatas = this.mDao.getGroupDatasByName(this.mGroupName);
            ArrayList<String> groupedDeviceIds = new ArrayList<>();
            for (int groupIndex = 0; groupIndex < this.mGroupDatas.size(); groupIndex++) {
                String deviceId = "" + this.mGroupDatas.get(groupIndex).dbId;
                if (!groupedDeviceIds.contains(deviceId)) {
                    groupedDeviceIds.add(deviceId);
                }
            }
            for (int deviceIndex = 0; deviceIndex < groupedDeviceIds.size(); deviceIndex++) {
                this.mFlagData01 = new ArrayList<>();
                String deviceId = groupedDeviceIds.get(deviceIndex);
                for (int groupIndex = 0; groupIndex < this.mGroupDatas.size(); groupIndex++) {
                    GroupBean groupBean = this.mGroupDatas.get(groupIndex);
                    if (deviceId.equals("" + groupBean.dbId) && groupBean.dbFlag == 1) {
                        this.mFlagData01.add("" + groupBean.dbChannel);
                    }
                }
                this.mFlagData02.add(this.mFlagData01);
            }
        } else {
            for (int deviceIndex = 0; deviceIndex < this.mDevDatas.size(); deviceIndex++) {
                ArrayList<String> selectedChannels = new ArrayList<>();
                this.mFlagData01 = selectedChannels;
                this.mFlagData02.add(selectedChannels);
            }
        }
        initLayout();
    }

    private void initWidgetInParent() {
        this.mBtnLeft = (Button) this.mActivity.findViewById(R.id.dev_title_btn_left);
        this.mBtnRight = (Button) this.mActivity.findViewById(R.id.dev_title_btn_right);
        this.mBtnLeft.setText(R.string.cancel);
        this.mBtnRight.setText(R.string.done);
    }

    private void initLayout() {
        DevInfoBean devInfoBean;
        this.mEtName.setText(this.mGroupName);
        this.mChildDatas = new ArrayList<>();
        for (int deviceIndex = 0; deviceIndex < this.mDevDatas.size(); deviceIndex++) {
            int deviceId = getDeviceId(deviceIndex);
            if (MyApp.loginType == 0) {
                devInfoBean = this.mDbHelper.query(deviceId);
            } else {
                devInfoBean = this.mApp.mWebService.query(deviceId);
            }
            ArrayList<String> channelLabels = new ArrayList<>();
            int channelNumber = 0;
            while (channelNumber < devInfoBean.mChCounts) {
                StringBuilder sb = new StringBuilder();
                sb.append("通道:");
                channelNumber++;
                sb.append(channelNumber);
                channelLabels.add(sb.toString());
            }
            this.mChildDatas.add(channelLabels);
        }
        this.mAdapter = new CustomExpandAdapter(this.mChildDatas, this.mDevDatas, this.mLv, this.mFlagData02);
        this.mLv.setGroupIndicator((Drawable) null);
        View inflate = View.inflate(AppProxy.getContext(), R.layout.dev_head, (ViewGroup) null);
        this.mHeaderView = inflate;
        this.mLv.setHeaderView(inflate);
        this.mLv.setAdapter(this.mAdapter);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnLeft.setOnClickListener(this);
        this.mBtnRight.setOnClickListener(this);
    }

    private List<Map<String, Object>> getData() {
        List<DevInfoBean> list;
        if (MyApp.loginType == 0) {
            list = this.mDbHelper.getlist();
        } else {
            list = this.mApp.mWebService.GetTerminalInfoAndroid(true);
        }
        ArrayList<Map<String, Object>> devices = new ArrayList<>();
        for (int deviceIndex = 0; deviceIndex < list.size(); deviceIndex++) {
            DevInfoBean devInfoBean = list.get(deviceIndex);
            HashMap<String, Object> deviceRow = new HashMap<>();
            deviceRow.put("id", Integer.valueOf(devInfoBean.mDevId));
            deviceRow.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, devInfoBean.mDevName);
            deviceRow.put("info", String.format("%s:%s,%s:%s", new Object[]{getString(R.string.deviceip), devInfoBean.mDevIp, getString(R.string.mediaport), Integer.valueOf(devInfoBean.mMediaPort).toString()}));
            deviceRow.put("img", Integer.valueOf(R.drawable.dvronline));
            devices.add(deviceRow);
        }
        return devices;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dev_title_btn_left /*2131362303*/:
                FragmentUtils.fragmentReplace(this, new DevListFragment(), R.id.dev_fl);
                return;
            case R.id.dev_title_btn_right /*2131362304*/:
                String newGroupName = this.mEtName.getText().toString().trim();
                if (TextUtils.isEmpty(newGroupName)) {
                    ToastSf.toastSf((Activity) this.mActivity, (int) R.string.group_GroupIsEmpty);
                    return;
                }
                if (countSelectedChannels() > 32) {
                    ToastSf.toastSf((Activity) this.mActivity, "selected channel more than 32");
                    return;
                }
                if (this.mFlag) {
                    saveEditedGroup(newGroupName);
                } else if (!this.mDao.query(newGroupName)) {
                    ToastSf.toastSf((Activity) this.mActivity, (int) R.string.group_GroupIsExist);
                    return;
                } else {
                    saveNewGroup(newGroupName);
                }
                FragmentUtils.fragmentReplace(this, new DevListFragment(), R.id.dev_fl);
                return;
            default:
                return;
        }
    }

    private void saveEditedGroup(String newGroupName) {
        if (MyApp.loginType == 0) {
            for (int deviceIndex = 0; deviceIndex < this.mDevDatas.size(); deviceIndex++) {
                int deviceId = getDeviceId(deviceIndex);
                String deviceName = getDeviceName(deviceIndex);
                DevInfoBean deviceInfo = this.mDbHelper.query(deviceId);
                for (int channelIndex = 0; channelIndex < deviceInfo.mChCounts; channelIndex++) {
                    this.mDao.update(newGroupName, deviceId, deviceName, channelIndex, getChannelFlag(deviceIndex, channelIndex));
                }
            }
        } else {
            for (int deviceIndex = 0; deviceIndex < this.mDevDatas.size(); deviceIndex++) {
                int deviceId = getDeviceId(deviceIndex);
                String deviceName = getDeviceName(deviceIndex);
                DevInfoBean deviceInfo = this.mApp.mWebService.query(deviceId);
                for (int channelIndex = 0; channelIndex < deviceInfo.mChCounts; channelIndex++) {
                    this.mDao.add(newGroupName, deviceId, deviceName, channelIndex, getChannelFlag(deviceIndex, channelIndex));
                }
            }
        }
        if (!this.mGroupName.equals(newGroupName)) {
            for (int groupIndex = 0; groupIndex < this.mGroupDatas.size(); groupIndex++) {
                this.mDao.update(newGroupName, this.mGroupName);
            }
        }
    }

    private void saveNewGroup(String newGroupName) {
        for (int deviceIndex = 0; deviceIndex < this.mDevDatas.size(); deviceIndex++) {
            int deviceId = getDeviceId(deviceIndex);
            String deviceName = getDeviceName(deviceIndex);
            DevInfoBean deviceInfo;
            if (MyApp.loginType == 0) {
                deviceInfo = this.mDbHelper.query(deviceId);
            } else {
                deviceInfo = this.mApp.mWebService.query(deviceId);
            }
            for (int channelIndex = 0; channelIndex < deviceInfo.mChCounts; channelIndex++) {
                this.mDao.add(newGroupName, deviceId, deviceName, channelIndex, getChannelFlag(deviceIndex, channelIndex));
            }
        }
    }

    private int countSelectedChannels() {
        int selectedCount = 0;
        for (int deviceIndex = 0; deviceIndex < this.mDevDatas.size(); deviceIndex++) {
            DevInfoBean deviceInfo;
            if (MyApp.loginType == 0) {
                deviceInfo = this.mDbHelper.query(getDeviceId(deviceIndex));
            } else {
                deviceInfo = this.mApp.mWebService.query(getDeviceId(deviceIndex));
            }
            for (int channelIndex = 0; channelIndex < deviceInfo.mChCounts; channelIndex++) {
                if (isChannelSelected(deviceIndex, channelIndex)) {
                    selectedCount++;
                }
            }
        }
        return selectedCount;
    }

    private int getChannelFlag(int deviceIndex, int channelIndex) {
        return isChannelSelected(deviceIndex, channelIndex) ? 1 : 0;
    }

    private boolean isChannelSelected(int deviceIndex, int channelIndex) {
        return this.mFlagData02.get(deviceIndex).contains("" + channelIndex);
    }

    private int getDeviceId(int deviceIndex) {
        return ((Integer) this.mDevDatas.get(deviceIndex).get("id")).intValue();
    }

    private String getDeviceName(int deviceIndex) {
        return (String) this.mDevDatas.get(deviceIndex).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
