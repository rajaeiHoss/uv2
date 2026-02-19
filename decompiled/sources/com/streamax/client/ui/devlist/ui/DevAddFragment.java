package com.streamax.client.ui.devlist.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.Zxing.app.CaptureActivity;
import com.dvr.net.DvrNet;
import com.streamax.client.DevInfoBean;
import com.streamax.client.MyApp;
import com.streamax.client.SegmentedRadioGroup;
import com.streamax.client.DbHelper;
import com.streamax.client.ui.devlist.db.GroupBean;
import com.streamax.client.ui.devlist.db.GroupDao;
import com.streamax.client.ui.devlist.db.GroupDaoForNormal;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.utils.FragmentUtils;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.utils.AppProxy;
import com.streamax.utils.ToastSf;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DevAddFragment extends BaseFragment {
    private FragmentActivity mActivity;
    private MyApp mApp;
    private Button mBtnCancel;
    private Button mBtnSave;
    private View mChildView;
    private GroupDao mDao;
    private DbHelper mDbHelper;
    private int mDevId;
    private String mDevName;
    private EditText mEtDevIp;
    private EditText mEtDevName;
    private EditText mEtMediaPort;
    private EditText mEtPwd;
    private EditText mEtUsername;
    private EditText mEtWebPort;
    private boolean mFlag;
    private List<GroupBean> mGroupDatas;
    private List<String> mGroupNameDatas;
    private ImageView mIvScan;
    private LinearLayout mLlTitle;
    private TextView mTvName;
    public SegmentedRadioGroup segChannelCount;

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.mDbHelper = new DbHelper(AppProxy.getContext(), DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mFlag = arguments.getBoolean("flag");
            String string = arguments.getString("devName");
            this.mDevName = string;
            this.mDevId = this.mDbHelper.getIdByName(string);
        }
    }

    /* access modifiers changed from: protected */
    public View initView() {
        View inflate = this.mInflater.inflate(R.layout.devsetting, (ViewGroup) null);
        this.mChildView = inflate;
        this.mEtDevName = (EditText) inflate.findViewById(R.id.dev_add_et_devname);
        this.mEtDevIp = (EditText) this.mChildView.findViewById(R.id.dev_add_et_devip);
        this.mIvScan = (ImageView) this.mChildView.findViewById(R.id.dev_add_iv_scan);
        this.mEtMediaPort = (EditText) this.mChildView.findViewById(R.id.dev_add_et_mediaport);
        this.mEtWebPort = (EditText) this.mChildView.findViewById(R.id.dev_add_et_webport);
        this.mEtUsername = (EditText) this.mChildView.findViewById(R.id.dev_add_et_username);
        this.mEtPwd = (EditText) this.mChildView.findViewById(R.id.dev_add_et_password);
        LinearLayout linearLayout = (LinearLayout) this.mChildView.findViewById(R.id.dev_add_ll_title);
        this.mLlTitle = linearLayout;
        linearLayout.setVisibility(8);
        this.segChannelCount = (SegmentedRadioGroup) this.mChildView.findViewById(R.id.segment_text);
        return this.mChildView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.mActivity = getActivity();
        this.mDao = new GroupDaoForNormal();
        initWidget();
        this.mApp = (MyApp) this.mActivity.getApplication();
        if (this.mFlag) {
            DevInfoBean query = this.mDbHelper.query(this.mDevName);
            this.mEtDevName.setText(query.mDevName);
            if (query.mLinkMode.equals("NAT")) {
                EditText editText = this.mEtDevIp;
                editText.setText("NAT:" + query.mDevIp);
            } else if (query.mLinkMode.equals("MSG")) {
                EditText editText2 = this.mEtDevIp;
                editText2.setText("MSG:" + query.mDevIp);
            } else {
                this.mEtDevIp.setText(query.mDevIp);
            }
            EditText editText3 = this.mEtMediaPort;
            editText3.setText("" + query.mMediaPort);
            EditText editText4 = this.mEtWebPort;
            editText4.setText("" + query.mWebPort);
            this.mEtUsername.setText(query.mUsername);
            this.mEtPwd.setText(query.mPwd);
            int i = query.mChCounts;
            if (i == 1) {
                this.segChannelCount.check(R.id.channel_1_text);
            } else if (i == 4) {
                this.segChannelCount.check(R.id.channel_4_text);
            } else if (i == 8) {
                this.segChannelCount.check(R.id.channel_8_text);
            } else if (i == 16) {
                this.segChannelCount.check(R.id.channel_16_text);
            } else if (i == 24) {
                this.segChannelCount.check(R.id.channel_24_text);
            } else if (i == 32) {
                this.segChannelCount.check(R.id.channel_32_text);
            }
        }
    }

    private void initWidget() {
        this.mBtnCancel = (Button) this.mActivity.findViewById(R.id.dev_title_btn_left);
        this.mBtnSave = (Button) this.mActivity.findViewById(R.id.dev_title_btn_right);
        this.mTvName = (TextView) this.mActivity.findViewById(R.id.dev_title_tv_name);
        this.mBtnCancel.setText(R.string.cancel);
        this.mBtnSave.setText(R.string.group_Save);
        this.mTvName.setText(R.string.devicemanager);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnCancel.setOnClickListener(this);
        this.mBtnSave.setOnClickListener(this);
        this.mIvScan.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dev_add_iv_scan /*2131362281*/:
                startActivityForResult(new Intent(this.mActivity, CaptureActivity.class), 0);
                return;
            case R.id.dev_title_btn_left /*2131362303*/:
                FragmentUtils.fragmentReplace(this, new DevListFragment(), R.id.dev_fl);
                return;
            case R.id.dev_title_btn_right /*2131362304*/:
                saveDev();
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == -1) {
            this.mEtDevIp.setText(intent.getStringExtra("RESULT"));
        }
    }

    private void saveDev() {
        DevInfoBean saveData = saveData();
        if (isEditTextEmpty()) {
            ToastSf.toastSf((Activity) this.mActivity, (int) R.string.complete_information);
            return;
        }
        if (this.mFlag) {
            if (this.mDbHelper.Update(this.mDevId, saveData)) {
                List<GroupBean> groupDatas = this.mDao.getGroupDatas();
                this.mGroupDatas = groupDatas;
                if (groupDatas != null && groupDatas.size() > 0) {
                    this.mGroupNameDatas = new ArrayList();
                    List<GroupBean> list = this.mGroupDatas;
                    if (list != null && list.size() > 0) {
                        for (int i = 0; i < this.mGroupDatas.size(); i++) {
                            if (!this.mGroupNameDatas.contains(this.mGroupDatas.get(i).dbGroupName)) {
                                this.mGroupNameDatas.add(this.mGroupDatas.get(i).dbGroupName);
                            }
                        }
                    }
                    int chCount = this.mDao.getChCount(this.mGroupNameDatas.get(0), this.mDevId);
                    if (chCount < saveData.mChCounts) {
                        for (int i2 = 0; i2 < this.mGroupNameDatas.size(); i2++) {
                            int i3 = 0;
                            while (i3 < saveData.mChCounts) {
                                if (i3 < chCount) {
                                    this.mDao.updateDevNameById(this.mDevId, saveData.mDevName);
                                    i3 = chCount - 1;
                                } else {
                                    this.mDao.add(this.mGroupNameDatas.get(i2), this.mDevId, saveData.mDevName, i3, 0);
                                }
                                i3++;
                            }
                        }
                    } else if (chCount > saveData.mChCounts) {
                        for (int i4 = 0; i4 < this.mGroupNameDatas.size(); i4++) {
                            int i5 = 0;
                            while (i5 < chCount) {
                                if (i5 < saveData.mChCounts) {
                                    this.mDao.updateDevNameById(this.mDevId, saveData.mDevName);
                                    i5 = saveData.mChCounts - 1;
                                } else {
                                    this.mDao.delete(this.mDevId, i5);
                                }
                                i5++;
                            }
                        }
                    } else {
                        this.mDao.updateDevNameById(this.mDevId, saveData.mDevName);
                    }
                }
            }
        } else if (this.mDbHelper.queryDeviceName(this.mEtDevName.getText().toString().trim())) {
            ToastSf.toastSf((Activity) this.mActivity, (int) R.string.group_DeviceNameIsExist);
            return;
        } else if (this.mDbHelper.insert(saveData)) {
            groupAddDev(saveData);
        }
        FragmentUtils.fragmentReplace(this, new DevListFragment(), R.id.dev_fl);
    }

    private void groupAddDev(DevInfoBean devInfoBean) {
        this.mGroupDatas = this.mDao.getGroupDatas();
        this.mGroupNameDatas = new ArrayList();
        List<GroupBean> list = this.mGroupDatas;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.mGroupDatas.size(); i++) {
                GroupBean groupBean = this.mGroupDatas.get(i);
                if (!this.mGroupNameDatas.contains(groupBean.dbGroupName)) {
                    this.mGroupNameDatas.add(groupBean.dbGroupName);
                }
            }
            for (int i2 = 0; i2 < this.mGroupNameDatas.size(); i2++) {
                for (int i3 = 0; i3 < devInfoBean.mChCounts; i3++) {
                    this.mDao.add(this.mGroupNameDatas.get(i2), this.mDevId, devInfoBean.mDevName, i3, 0);
                }
            }
        }
    }

    private DevInfoBean saveData() {
        DevInfoBean devInfoBean = new DevInfoBean();
        devInfoBean.mDevName = trimString(this.mEtDevName);
        String trimString = trimString(this.mEtDevIp);
        Log.e("devIp", trimString + "");
        int i = 4;
        if (trimString != null) {
            if (trimString.startsWith("NAT:")) {
                devInfoBean.mLinkMode = "NAT";
                devInfoBean.mDevIp = trimString.substring(4);
                if (devInfoBean.mDevIp == null) {
                    devInfoBean.mDevIp = "";
                }
            } else if (trimString.startsWith("MSG:")) {
                devInfoBean.mLinkMode = "MSG";
                devInfoBean.mDevIp = trimString.substring(4);
                if (devInfoBean.mDevIp == null) {
                    devInfoBean.mDevIp = "";
                }
            } else {
                devInfoBean.mLinkMode = "";
                devInfoBean.mDevIp = trimString;
            }
        }
        devInfoBean.mRemark = devInfoBean.mDevIp;
        devInfoBean.mMediaPort = Integer.valueOf(trimString(this.mEtMediaPort)).intValue();
        devInfoBean.mWebPort = Integer.valueOf(trimString(this.mEtWebPort)).intValue();
        devInfoBean.mUsername = trimString(this.mEtUsername);
        devInfoBean.mPwd = trimString(this.mEtPwd);
        int i2 = 0;
        switch (this.segChannelCount.getCheckedRadioButtonId()) {
            case R.id.channel_16_text /*2131361987*/:
                i = 16;
                break;
            case R.id.channel_1_text /*2131361988*/:
                i = 1;
                break;
            case R.id.channel_24_text /*2131361989*/:
                i = 24;
                break;
            case R.id.channel_32_text /*2131361990*/:
                i = 32;
                break;
            case R.id.channel_4_text /*2131361991*/:
                break;
            case R.id.channel_8_text /*2131361992*/:
                i = 8;
                break;
            case R.id.channel_auto_text /*2131361993*/:
                DvrNet dvrNet = new DvrNet();
                Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(dvrNet, devInfoBean, this.mApp);
                if (connDeviceProxy != null && Integer.valueOf(connDeviceProxy.get("errorcode").toString()).intValue() == 0) {
                    i2 = Integer.valueOf(connDeviceProxy.get("channel").toString()).intValue();
                }
                if (i2 == 0) {
                    ToastSf.toastSf((Activity) this.mActivity, getString(R.string.getchannelcountfailed));
                    i2 = 1;
                }
                dvrNet.CloseDeviceHandle();
                i = i2;
                break;
            default:
                i = 0;
                break;
        }
        devInfoBean.mChCounts = i;
        return devInfoBean;
    }

    private String trimString(EditText editText) {
        return editText.getText().toString().trim();
    }

    private boolean isEditTextEmpty() {
        if (!TextUtils.isEmpty(this.mEtDevName.getText()) && !TextUtils.isEmpty(this.mEtDevIp.getText()) && !TextUtils.isEmpty(this.mEtMediaPort.getText()) && !TextUtils.isEmpty(this.mEtWebPort.getText()) && !TextUtils.isEmpty(this.mEtUsername.getText())) {
            return false;
        }
        return true;
    }
}
