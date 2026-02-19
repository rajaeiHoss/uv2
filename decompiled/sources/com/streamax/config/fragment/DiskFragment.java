package com.streamax.config.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.listener.SuperListener;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetManager;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.AppProxy;
import com.streamax.utils.LogUtils;
import com.streamax.utils.UiUtils;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DiskFragment extends ConfigFragment implements BaseListener.GetListener, BaseListener.SetListener, BaseListener.GetStorageInfoListener {
    public static final int GetConfig_Disk = 1;
    public static final int GetConfig_Storage = 2;
    public static final int MAX_SVD = 99;
    public int mCurId;
    public AlertDialog mDialog;
    public JSONObject mDiskMgrObj;
    public int mGetConfigMode = 1;
    public List<Integer> mListIntDays = new ArrayList();
    public List<Integer> mListIntIndex = new ArrayList();
    public List<Integer> mListIntOverWrite = new ArrayList();
    public ArrayList<String> mListStrDays = new ArrayList<>();
    public ArrayList<String> mListStrIndex = new ArrayList<>();
    public ArrayList<String> mListStrOverWrite = new ArrayList<>();
    public LinearLayout mLlContent;
    public LinearLayout mLlMaybeHide;
    public JSONObject mPadmObj;
    public RelativeLayout mRlDays;
    public RelativeLayout mRlDevSerial;
    public RelativeLayout mRlDiskFormat;
    public RelativeLayout mRlOverWrite;
    public JSONObject mStorageObj;
    public TextView mTvCapacity;
    public TextView mTvDays;
    public TextView mTvDiskFormat;
    public TextView mTvId;
    public TextView mTvOm;
    public TextView mTvStatus;
    public TextView mTvStorageConfig;
    public View mViewDays;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mCurId = 0;
        this.mGetConfigMode = 1;
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_diskmanage, (ViewGroup) null);
        this.mTvStorageConfig = (TextView) this.mRootView.findViewById(R.id.disk_tv_storageConfigMode);
        this.mTvId = (TextView) this.mRootView.findViewById(R.id.disk_tv_id);
        this.mRlDevSerial = (RelativeLayout) this.mRootView.findViewById(R.id.disk_rl_dev_serial);
        this.mTvStatus = (TextView) this.mRootView.findViewById(R.id.disk_tv_status);
        this.mTvCapacity = (TextView) this.mRootView.findViewById(R.id.disk_tv_capacity);
        this.mTvOm = (TextView) this.mRootView.findViewById(R.id.disk_tv_method);
        this.mRlOverWrite = (RelativeLayout) this.mRootView.findViewById(R.id.disk_rl_overwrite);
        this.mRlDiskFormat = (RelativeLayout) this.mRootView.findViewById(R.id.disk_rl_disk_format);
        this.mLlContent = (LinearLayout) this.mRootView.findViewById(R.id.disk_ll_content);
        this.mViewDays = this.mRootView.findViewById(R.id.disk_view_days);
        this.mRlDays = (RelativeLayout) this.mRootView.findViewById(R.id.disk_rl_days);
        this.mTvDays = (TextView) this.mRootView.findViewById(R.id.disk_tv_days);
        this.mLlMaybeHide = (LinearLayout) this.mRootView.findViewById(R.id.disk_ll_maybeHide);
        this.mTvDiskFormat = (TextView) this.mRootView.findViewById(R.id.disk_tv_diskFormat);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    public void configureForStorage() {
        try {
            int i = this.mStorageObj.getInt("STORAGECOUNT");
            if (this.mCurId >= i) {
                showErrorFragment();
                return;
            }
            TextView textView = this.mTvId;
            textView.setText("" + (this.mCurId + 1));
            this.mListStrIndex.clear();
            this.mListIntIndex.clear();
            int i2 = 0;
            while (i2 < i) {
                ArrayList<String> arrayList = this.mListStrIndex;
                StringBuilder sb = new StringBuilder();
                sb.append("");
                i2++;
                sb.append(i2);
                arrayList.add(sb.toString());
            }
            this.mListIntIndex.add(new Integer(this.mCurId));
            JSONArray jSONArray = this.mStorageObj.getJSONArray("STORAGESTATUS");
            if (jSONArray != null) {
                if (this.mCurId < jSONArray.length()) {
                    int i3 = jSONArray.getInt(this.mCurId);
                    List<String> strDatas = getStrDatas(R.array.config_diskManage_DiskStatusSelector);
                    if (i3 < 0 || i3 < strDatas.size()) {
                        this.mTvStatus.setText(strDatas.get(i3));
                        JSONArray jSONArray2 = this.mStorageObj.getJSONArray("STORAGETOTALSIZE");
                        if (jSONArray2 != null) {
                            if (this.mCurId < jSONArray2.length()) {
                                JSONArray jSONArray3 = this.mStorageObj.getJSONArray("STORAGELASTSIZE");
                                if (jSONArray3 != null) {
                                    if (this.mCurId < jSONArray3.length()) {
                                        Long valueOf = Long.valueOf(jSONArray2.getLong(this.mCurId));
                                        Long valueOf2 = Long.valueOf(jSONArray3.getLong(this.mCurId));
                                        if (valueOf.longValue() >= 0 && valueOf2.longValue() >= 0) {
                                            if (valueOf.longValue() >= valueOf2.longValue()) {
                                                JSONArray jSONArray4 = this.mStorageObj.getJSONArray("STORAGEUNIT");
                                                if (jSONArray4 != null) {
                                                    if (this.mCurId < jSONArray4.length()) {
                                                        LogUtils.e("DiskFragment", "configureForStorage 3, total: " + valueOf + ", free: " + valueOf2 + ", unit: " + jSONArray4.getInt(this.mCurId));
                                                        this.mTvCapacity.setText(String.format("%.1fG/%.1fG", new Object[]{Float.valueOf(AppProxy.s2g(jSONArray4.getInt(this.mCurId), valueOf.longValue())), Float.valueOf(AppProxy.s2g(jSONArray4.getInt(this.mCurId), valueOf2.longValue()))}));
                                                        return;
                                                    }
                                                }
                                                showErrorFragment();
                                                return;
                                            }
                                        }
                                        showErrorFragment();
                                        return;
                                    }
                                }
                                showErrorFragment();
                                return;
                            }
                        }
                        showErrorFragment();
                        return;
                    }
                    showErrorFragment();
                    return;
                }
            }
            showErrorFragment();
        } catch (JSONException unused) {
        }
    }

    public void configureForOverWrite() {
        try {
            int i = this.mPadmObj.getInt("OM");
            List<String> strDatas = getStrDatas(R.array.config_diskManage_overWrite_OmSelector);
            if (i >= strDatas.size()) {
                showErrorFragment();
                return;
            }
            this.mTvOm.setText(strDatas.get(i));
            this.mListStrOverWrite.clear();
            this.mListIntOverWrite.clear();
            this.mListStrOverWrite.addAll(strDatas);
            this.mListIntOverWrite.add(new Integer(i));
            int i2 = this.mPadmObj.getInt("SVD");
            int i3 = 0;
            if (i == 1) {
                this.mViewDays.setVisibility(0);
                this.mRlDays.setVisibility(0);
            } else {
                this.mViewDays.setVisibility(8);
                this.mRlDays.setVisibility(8);
            }
            if (i2 >= 1 && i2 <= 99) {
                TextView textView = this.mTvDays;
                textView.setText(i2 + getString(R.string.config_diskManage_Days));
                this.mListStrDays.clear();
                this.mListIntDays.clear();
                while (i3 < 99) {
                    ArrayList<String> arrayList = this.mListStrDays;
                    StringBuilder sb = new StringBuilder();
                    i3++;
                    sb.append(i3);
                    sb.append("");
                    arrayList.add(sb.toString());
                }
                this.mListIntDays.add(new Integer(i2));
            }
        } catch (JSONException unused) {
        }
    }

    public void refreshUi() {
        if (this.mDiskMgrObj == null || this.mStorageObj == null) {
            showErrorFragment();
            return;
        }
        LogUtils.e("DiskFragment", "refreshUi 1");
        try {
            JSONObject jSONObject = this.mDiskMgrObj.getJSONObject("STORM");
            if (jSONObject == null) {
                showErrorFragment();
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("PDM");
            if (jSONObject2 == null) {
                showErrorFragment();
                return;
            }
            JSONObject jSONObject3 = jSONObject2.getJSONObject("PADM");
            this.mPadmObj = jSONObject3;
            if (jSONObject3 == null) {
                showErrorFragment();
                return;
            }
            int i = jSONObject2.getInt("M");
            List<String> strDatas = getStrDatas(R.array.config_diskManage_StorageConfigMode);
            if (i >= 0 && i < strDatas.size()) {
                this.mTvStorageConfig.setText(strDatas.get(i));
            }
            configureForStorage();
            if (i == 0) {
                this.mLlMaybeHide.setVisibility(0);
                configureForOverWrite();
            } else {
                this.mLlMaybeHide.setVisibility(8);
            }
            LogUtils.e("DiskFragment", "refreshUi 2");
        } catch (JSONException unused) {
        }
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mRlDevSerial.setOnClickListener(this);
        this.mRlOverWrite.setOnClickListener(this);
        this.mRlDays.setOnClickListener(this);
        this.mRlDiskFormat.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mBtnUpdate.setVisibility(8);
        this.mTvTitle.SetText((int) R.string.config_diskManage_Title);
        this.mBtnBack.setOnClickListener(this);
    }

    public void pushFragmentForIndex() {
        if (this.mStorageObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_diskManage_ID), "SelectFragmentForIndex", 0, this.mListStrIndex, this.mListIntIndex);
        }
    }

    public void pushFragmentForOverWrite() {
        if (this.mDiskMgrObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_diskManage_OverWrite), "SelectFragmentForOverWrite", 0, this.mListStrOverWrite, this.mListIntOverWrite);
        }
    }

    public void pushFragmentForDays() {
        if (this.mPadmObj != null) {
            try {
                pushOneNumberEditFragment(UiUtils.getString(R.string.config_diskManage_MaxDays), "OneNumberEditFragmentForDays", this.mPadmObj.getInt("SVD"), 1, 99);
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForFormat() {
        if (this.mStorageObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_diskManage_DiskFormat), "SelectFragmentForFormat", 1, this.mListStrIndex, (List<Integer>) null);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.config_title_btn_back) {
            switch (id) {
                case R.id.disk_rl_days /*2131362332*/:
                    pushFragmentForDays();
                    return;
                case R.id.disk_rl_dev_serial /*2131362333*/:
                    pushFragmentForIndex();
                    return;
                case R.id.disk_rl_disk_format /*2131362334*/:
                    pushFragmentForFormat();
                    return;
                case R.id.disk_rl_overwrite /*2131362335*/:
                    pushFragmentForOverWrite();
                    return;
                default:
                    return;
            }
        } else {
            prePage();
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("PDM", "?");
            jSONObject.put("STORM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String str) {
        try {
            int i = this.mGetConfigMode;
            if (i == 1) {
                this.mGetConfigMode = 2;
                this.mDiskMgrObj = new JSONObject(str);
                NetPresenter.getDefault().getStorageInfo(this);
            } else if (i == 2) {
                this.mGetConfigMode = 1;
                this.mStorageObj = new JSONObject(str);
                refreshUi();
            }
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mDiskMgrObj;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        LogUtils.e("DiskFragment", "setSuccess 1");
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForIndex(int i) {
        if (this.mCurId != i) {
            this.mCurId = i;
            configureForStorage();
        }
    }

    public void updateDateForOverWrite(int i) {
        JSONObject jSONObject = this.mPadmObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("OM", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForFormat(final List<Integer> list) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mConfigUi);
        builder.setTitle(R.string.config_diskManage_diskFormat_FormatRemind);
        builder.setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
            private int mIndexTotal;

            public void onClick(DialogInterface dialogInterface, int i) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (((Integer) list.get(i2)).intValue() < 32) {
                        this.mIndexTotal = (int) (((double) this.mIndexTotal) + Math.pow(2.0d, (double) ((Integer) list.get(i2)).intValue()));
                    }
                }
                DiskFragment.this.mDialog.hide();
                DiskFragment.this.showLoading();
                NetManager.getDefault().formatStorage(this.mIndexTotal, new SuperListener.SetConfigListener() {
                    public void onSuccess() {
                        DiskFragment.this.hideLoading();
                        DiskFragment.this.toastSuccess();
                    }

                    public void onFailure() {
                        DiskFragment.this.hideLoading();
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.group_Cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DiskFragment.this.mDialog.hide();
            }
        });
        this.mDialog = builder.show();
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForIndex")) {
            if (list.size() > 0) {
                updateDateForIndex(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForOverWrite")) {
            if (list.size() > 0) {
                updateDateForOverWrite(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForFormat") && list.size() > 0) {
            updateDateForFormat(list);
        }
    }

    public void updateDateForDays(int i) {
        JSONObject jSONObject = this.mPadmObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("SVD", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void saveOneNumberEdit(String str, int i) {
        if (str.equals("OneNumberEditFragmentForDays")) {
            updateDateForDays(i);
        }
    }
}
