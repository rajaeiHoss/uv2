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
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.mGroupDatas.size(); i++) {
                if (!arrayList.contains("" + this.mGroupDatas.get(i).dbId)) {
                    arrayList.add("" + this.mGroupDatas.get(i).dbId);
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                this.mFlagData01 = new ArrayList<>();
                for (int i3 = 0; i3 < this.mGroupDatas.size(); i3++) {
                    if (((String) arrayList.get(i2)).equals("" + this.mGroupDatas.get(i3).dbId) && this.mGroupDatas.get(i3).dbFlag == 1) {
                        ArrayList<String> arrayList2 = this.mFlagData01;
                        arrayList2.add("" + this.mGroupDatas.get(i3).dbChannel);
                    }
                }
                this.mFlagData02.add(this.mFlagData01);
            }
        } else {
            for (int i4 = 0; i4 < this.mDevDatas.size(); i4++) {
                ArrayList<String> arrayList3 = new ArrayList<>();
                this.mFlagData01 = arrayList3;
                this.mFlagData02.add(arrayList3);
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
        for (int i = 0; i < this.mDevDatas.size(); i++) {
            int intValue = ((Integer) this.mDevDatas.get(i).get("id")).intValue();
            if (MyApp.loginType == 0) {
                devInfoBean = this.mDbHelper.query(intValue);
            } else {
                devInfoBean = this.mApp.mWebService.query(intValue);
            }
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (i2 < devInfoBean.mChCounts) {
                StringBuilder sb = new StringBuilder();
                sb.append("通道:");
                i2++;
                sb.append(i2);
                arrayList.add(sb.toString());
            }
            this.mChildDatas.add(arrayList);
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
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            DevInfoBean devInfoBean = list.get(i);
            HashMap hashMap = new HashMap();
            hashMap.put("id", Integer.valueOf(devInfoBean.mDevId));
            hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, devInfoBean.mDevName);
            hashMap.put("info", String.format("%s:%s,%s:%s", new Object[]{getString(R.string.deviceip), devInfoBean.mDevIp, getString(R.string.mediaport), Integer.valueOf(devInfoBean.mMediaPort).toString()}));
            hashMap.put("img", Integer.valueOf(R.drawable.dvronline));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public void onClick(View view) {
        int i;
        DevInfoBean devInfoBean;
        DevInfoBean devInfoBean2;
        int i2;
        DevInfoBean devInfoBean3;
        int i3;
        DevInfoBean devInfoBean4;
        int i4;
        switch (view.getId()) {
            case R.id.dev_title_btn_left /*2131362303*/:
                FragmentUtils.fragmentReplace(this, new DevListFragment(), R.id.dev_fl);
                return;
            case R.id.dev_title_btn_right /*2131362304*/:
                String trim = this.mEtName.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    ToastSf.toastSf((Activity) this.mActivity, (int) R.string.group_GroupIsEmpty);
                    return;
                }
                if (MyApp.loginType == 0) {
                    i = 0;
                    for (int i5 = 0; i5 < this.mDevDatas.size(); i5++) {
                        DevInfoBean query = this.mDbHelper.query(((Integer) this.mDevDatas.get(i5).get("id")).intValue());
                        for (int i6 = 0; i6 < query.mChCounts; i6++) {
                            if (this.mFlagData02.get(i5).contains("" + i6)) {
                                i++;
                            }
                        }
                    }
                } else {
                    i = 0;
                    for (int i7 = 0; i7 < this.mDevDatas.size(); i7++) {
                        DevInfoBean query2 = this.mApp.mWebService.query(((Integer) this.mDevDatas.get(i7).get("id")).intValue());
                        for (int i8 = 0; i8 < query2.mChCounts; i8++) {
                            if (this.mFlagData02.get(i7).contains("" + i8)) {
                                i++;
                            }
                        }
                    }
                }
                if (i > 32) {
                    ToastSf.toastSf((Activity) this.mActivity, "selected channel more than 32");
                    return;
                }
                if (this.mFlag) {
                    if (MyApp.loginType == 0) {
                        for (int i9 = 0; i9 < this.mDevDatas.size(); i9++) {
                            int intValue = ((Integer) this.mDevDatas.get(i9).get("id")).intValue();
                            String str = (String) this.mDevDatas.get(i9).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
                            DevInfoBean query3 = this.mDbHelper.query(intValue);
                            int i10 = 0;
                            while (i10 < query3.mChCounts) {
                                if (this.mFlagData02.get(i9).contains("" + i10)) {
                                    i4 = i10;
                                    devInfoBean4 = query3;
                                    this.mDao.update(trim, intValue, str, i10, 1);
                                } else {
                                    i4 = i10;
                                    devInfoBean4 = query3;
                                    this.mDao.update(trim, intValue, str, i10, 0);
                                }
                                i10 = i4 + 1;
                                query3 = devInfoBean4;
                            }
                        }
                    } else {
                        for (int i11 = 0; i11 < this.mDevDatas.size(); i11++) {
                            int intValue2 = ((Integer) this.mDevDatas.get(i11).get("id")).intValue();
                            String str2 = (String) this.mDevDatas.get(i11).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
                            DevInfoBean query4 = this.mApp.mWebService.query(intValue2);
                            int i12 = 0;
                            while (i12 < query4.mChCounts) {
                                if (this.mFlagData02.get(i11).contains("" + i12)) {
                                    i3 = i12;
                                    devInfoBean3 = query4;
                                    this.mDao.add(trim, intValue2, str2, i12, 1);
                                } else {
                                    i3 = i12;
                                    devInfoBean3 = query4;
                                    this.mDao.add(trim, intValue2, str2, i12, 0);
                                }
                                i12 = i3 + 1;
                                query4 = devInfoBean3;
                            }
                        }
                    }
                    if (!this.mGroupName.equals(this.mEtName.getText().toString().trim())) {
                        for (int i13 = 0; i13 < this.mGroupDatas.size(); i13++) {
                            this.mDao.update(this.mEtName.getText().toString().trim(), this.mGroupName);
                        }
                    }
                } else if (!this.mDao.query(trim)) {
                    ToastSf.toastSf((Activity) this.mActivity, (int) R.string.group_GroupIsExist);
                    return;
                } else {
                    for (int i14 = 0; i14 < this.mDevDatas.size(); i14++) {
                        int intValue3 = ((Integer) this.mDevDatas.get(i14).get("id")).intValue();
                        String str3 = (String) this.mDevDatas.get(i14).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
                        if (MyApp.loginType == 0) {
                            devInfoBean = this.mDbHelper.query(intValue3);
                        } else {
                            devInfoBean = this.mApp.mWebService.query(intValue3);
                        }
                        DevInfoBean devInfoBean5 = devInfoBean;
                        int i15 = 0;
                        while (i15 < devInfoBean5.mChCounts) {
                            if (this.mFlagData02.get(i14).contains("" + i15)) {
                                i2 = i15;
                                devInfoBean2 = devInfoBean5;
                                this.mDao.add(trim, intValue3, str3, i15, 1);
                            } else {
                                i2 = i15;
                                devInfoBean2 = devInfoBean5;
                                this.mDao.add(trim, intValue3, str3, i15, 0);
                            }
                            i15 = i2 + 1;
                            devInfoBean5 = devInfoBean2;
                        }
                    }
                }
                FragmentUtils.fragmentReplace(this, new DevListFragment(), R.id.dev_fl);
                return;
            default:
                return;
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
