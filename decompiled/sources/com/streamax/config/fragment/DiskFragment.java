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
            int storageCount = this.mStorageObj.getInt("STORAGECOUNT");
            if (this.mCurId >= storageCount) {
                showErrorFragment();
                return;
            }
            TextView textView = this.mTvId;
            textView.setText("" + (this.mCurId + 1));
            this.mListStrIndex.clear();
            this.mListIntIndex.clear();
            int diskDisplayIndex = 0;
            while (diskDisplayIndex < storageCount) {
                ArrayList<String> arrayList = this.mListStrIndex;
                StringBuilder sb = new StringBuilder();
                sb.append("");
                diskDisplayIndex++;
                sb.append(diskDisplayIndex);
                arrayList.add(sb.toString());
            }
            this.mListIntIndex.add(new Integer(this.mCurId));
            JSONArray storageStatusArray = this.mStorageObj.getJSONArray("STORAGESTATUS");
            if (storageStatusArray != null) {
                if (this.mCurId < storageStatusArray.length()) {
                    int storageStatus = storageStatusArray.getInt(this.mCurId);
                    List<String> statusLabels = getStrDatas(R.array.config_diskManage_DiskStatusSelector);
                    if (storageStatus < 0 || storageStatus < statusLabels.size()) {
                        this.mTvStatus.setText(statusLabels.get(storageStatus));
                        JSONArray totalSizeArray = this.mStorageObj.getJSONArray("STORAGETOTALSIZE");
                        if (totalSizeArray != null) {
                            if (this.mCurId < totalSizeArray.length()) {
                                JSONArray freeSizeArray = this.mStorageObj.getJSONArray("STORAGELASTSIZE");
                                if (freeSizeArray != null) {
                                    if (this.mCurId < freeSizeArray.length()) {
                                        Long totalSize = Long.valueOf(totalSizeArray.getLong(this.mCurId));
                                        Long freeSize = Long.valueOf(freeSizeArray.getLong(this.mCurId));
                                        if (totalSize.longValue() >= 0 && freeSize.longValue() >= 0) {
                                            if (totalSize.longValue() >= freeSize.longValue()) {
                                                JSONArray storageUnitArray = this.mStorageObj.getJSONArray("STORAGEUNIT");
                                                if (storageUnitArray != null) {
                                                    if (this.mCurId < storageUnitArray.length()) {
                                                        LogUtils.e("DiskFragment", "configureForStorage 3, total: " + totalSize + ", free: " + freeSize + ", unit: " + storageUnitArray.getInt(this.mCurId));
                                                        this.mTvCapacity.setText(String.format("%.1fG/%.1fG", new Object[]{Float.valueOf(AppProxy.s2g(storageUnitArray.getInt(this.mCurId), totalSize.longValue())), Float.valueOf(AppProxy.s2g(storageUnitArray.getInt(this.mCurId), freeSize.longValue()))}));
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
            int overwriteMode = this.mPadmObj.getInt("OM");
            List<String> overwriteModeLabels = getStrDatas(R.array.config_diskManage_overWrite_OmSelector);
            if (overwriteMode >= overwriteModeLabels.size()) {
                showErrorFragment();
                return;
            }
            this.mTvOm.setText(overwriteModeLabels.get(overwriteMode));
            this.mListStrOverWrite.clear();
            this.mListIntOverWrite.clear();
            this.mListStrOverWrite.addAll(overwriteModeLabels);
            this.mListIntOverWrite.add(new Integer(overwriteMode));
            int maxSaveDays = this.mPadmObj.getInt("SVD");
            int dayIndex = 0;
            if (overwriteMode == 1) {
                this.mViewDays.setVisibility(0);
                this.mRlDays.setVisibility(0);
            } else {
                this.mViewDays.setVisibility(8);
                this.mRlDays.setVisibility(8);
            }
            if (maxSaveDays >= 1 && maxSaveDays <= 99) {
                TextView textView = this.mTvDays;
                textView.setText(maxSaveDays + getString(R.string.config_diskManage_Days));
                this.mListStrDays.clear();
                this.mListIntDays.clear();
                while (dayIndex < 99) {
                    ArrayList<String> arrayList = this.mListStrDays;
                    StringBuilder sb = new StringBuilder();
                    dayIndex++;
                    sb.append(dayIndex);
                    sb.append("");
                    arrayList.add(sb.toString());
                }
                this.mListIntDays.add(new Integer(maxSaveDays));
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
            JSONObject storageManager = this.mDiskMgrObj.getJSONObject("STORM");
            if (storageManager == null) {
                showErrorFragment();
                return;
            }
            JSONObject diskManager = storageManager.getJSONObject("PDM");
            if (diskManager == null) {
                showErrorFragment();
                return;
            }
            JSONObject overwriteConfig = diskManager.getJSONObject("PADM");
            this.mPadmObj = overwriteConfig;
            if (overwriteConfig == null) {
                showErrorFragment();
                return;
            }
            int storageConfigMode = diskManager.getInt("M");
            List<String> storageConfigLabels = getStrDatas(R.array.config_diskManage_StorageConfigMode);
            if (storageConfigMode >= 0 && storageConfigMode < storageConfigLabels.size()) {
                this.mTvStorageConfig.setText(storageConfigLabels.get(storageConfigMode));
            }
            configureForStorage();
            if (storageConfigMode == 0) {
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
            JSONObject requestRoot = new JSONObject();
            JSONObject storageManager = new JSONObject();
            storageManager.put("PDM", "?");
            requestRoot.put("STORM", storageManager);
            return requestRoot.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String jsonResponse) {
        try {
            int configMode = this.mGetConfigMode;
            if (configMode == 1) {
                this.mGetConfigMode = 2;
                this.mDiskMgrObj = new JSONObject(jsonResponse);
                NetPresenter.getDefault().getStorageInfo(this);
            } else if (configMode == 2) {
                this.mGetConfigMode = 1;
                this.mStorageObj = new JSONObject(jsonResponse);
                refreshUi();
            }
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject diskManagerConfig = this.mDiskMgrObj;
        if (diskManagerConfig == null) {
            return "";
        }
        return diskManagerConfig.toString();
    }

    public void setSuccess() {
        LogUtils.e("DiskFragment", "setSuccess 1");
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForIndex(int diskIndex) {
        if (this.mCurId != diskIndex) {
            this.mCurId = diskIndex;
            configureForStorage();
        }
    }

    public void updateDateForOverWrite(int overwriteMode) {
        JSONObject overwriteConfig = this.mPadmObj;
        if (overwriteConfig != null) {
            try {
                overwriteConfig.put("OM", overwriteMode);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForFormat(final List<Integer> selectedIndexes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mConfigUi);
        builder.setTitle(R.string.config_diskManage_diskFormat_FormatRemind);
        builder.setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
            private int mIndexTotal;

            public void onClick(DialogInterface dialogInterface, int which) {
                for (int selectedIndex = 0; selectedIndex < selectedIndexes.size(); selectedIndex++) {
                    if (((Integer) selectedIndexes.get(selectedIndex)).intValue() < 32) {
                        this.mIndexTotal = (int) (((double) this.mIndexTotal) + Math.pow(2.0d, (double) ((Integer) selectedIndexes.get(selectedIndex)).intValue()));
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
            public void onClick(DialogInterface dialogInterface, int which) {
                DiskFragment.this.mDialog.hide();
            }
        });
        this.mDialog = builder.show();
    }

    public void saveSelect(String fragmentTag, List<Integer> selectedIndexes) {
        if (fragmentTag.equals("SelectFragmentForIndex")) {
            if (selectedIndexes.size() > 0) {
                updateDateForIndex(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForOverWrite")) {
            if (selectedIndexes.size() > 0) {
                updateDateForOverWrite(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForFormat") && selectedIndexes.size() > 0) {
            updateDateForFormat(selectedIndexes);
        }
    }

    public void updateDateForDays(int maxSaveDays) {
        JSONObject overwriteConfig = this.mPadmObj;
        if (overwriteConfig != null) {
            try {
                overwriteConfig.put("SVD", maxSaveDays);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void saveOneNumberEdit(String fragmentTag, int value) {
        if (fragmentTag.equals("OneNumberEditFragmentForDays")) {
            updateDateForDays(value);
        }
    }
}
