package com.streamax.config.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.listener.SuperListener;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetManager;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.UiUtils;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemSetFragment extends ConfigFragment implements BaseListener.GetListener, BaseListener.SetListener, BaseListener.RebootListener {
    public static int[][] mResetbitArray = {new int[]{2}, new int[]{16, 22, 25, 26, 37, 41, 43, 47}, new int[]{33}, new int[]{21, 22, 23, 24, 27, 28, 29, 30, 31, 32, 55, 59}, new int[]{8, 9, 10, 46, 62}, new int[]{1, 7}, new int[]{17, 18, 64}, new int[]{0}, new int[]{3, 4, 5, 6, 11, 12, 13, 19, 20}, new int[]{38, 45, 54}};
    public Button mBtnReboot;
    public AlertDialog mDialog;
    public List<Integer> mListIntReset = new ArrayList();
    public List<Integer> mListIntVSA = new ArrayList();
    public ArrayList<String> mListStrReset = new ArrayList<>();
    public ArrayList<String> mListStrVSA = new ArrayList<>();
    public RelativeLayout mRlReset;
    public RelativeLayout mRlVf;
    public JSONObject mSystemSetRes;
    public TextView mTvVf;
    public int mVsa;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_systemset, (ViewGroup) null);
        this.mTvVf = (TextView) this.mRootView.findViewById(R.id.systemset_tv_videoformat);
        this.mRlVf = (RelativeLayout) this.mRootView.findViewById(R.id.systemset_rl_videoformat);
        this.mRlReset = (RelativeLayout) this.mRootView.findViewById(R.id.systemset_rl_reset);
        this.mBtnReboot = (Button) this.mRootView.findViewById(R.id.systemset_btn_reboot);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    public void configureForVSA() {
        List<String> strDatas = getStrDatas(R.array.config_systemSet_videoFormat_VfSelector);
        if (this.mVsa >= strDatas.size()) {
            showErrorFragment();
            return;
        }
        this.mTvVf.setText(strDatas.get(this.mVsa));
        this.mListStrVSA.clear();
        this.mListIntVSA.clear();
        this.mListStrVSA.addAll(strDatas);
        this.mListIntVSA.add(new Integer(this.mVsa));
    }

    public void configureForReset() {
        this.mListStrReset.clear();
        this.mListIntReset.clear();
        this.mListStrReset.add(UiUtils.getString(R.string.config_ResetSystem));
        this.mListStrReset.add(UiUtils.getString(R.string.config_ResetDisplay));
        this.mListStrReset.add(UiUtils.getString(R.string.config_ResetDeviceMaintenance));
        this.mListStrReset.add(UiUtils.getString(R.string.config_ResetAlarm));
        this.mListStrReset.add(UiUtils.getString(R.string.config_ResetCOMM));
        this.mListStrReset.add(UiUtils.getString(R.string.config_ResetDataTime));
        this.mListStrReset.add(UiUtils.getString(R.string.config_ResetRecord));
        this.mListStrReset.add(UiUtils.getString(R.string.config_ResetUser));
        this.mListStrReset.add(UiUtils.getString(R.string.config_ResetNetwork));
        if (dvrNet.nDevClass != 1) {
            this.mListStrReset.add(UiUtils.getString(R.string.config_ResetRemoteDevice));
        }
    }

    public void configureForFragment() {
        configureForVSA();
        configureForReset();
    }

    public void refreshUi() {
        JSONObject jSONObject = this.mSystemSetRes;
        if (jSONObject == null) {
            showErrorFragment();
            return;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("DEVEMM");
            if (jSONObject2 == null) {
                showErrorFragment();
                return;
            }
            JSONObject jSONObject3 = jSONObject2.getJSONObject("SSP");
            if (jSONObject3 == null) {
                showErrorFragment();
                return;
            }
            this.mVsa = jSONObject3.getInt("VSA");
            configureForFragment();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mRlVf.setOnClickListener(this);
        this.mRlReset.setOnClickListener(this);
        this.mBtnReboot.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mBtnUpdate.setVisibility(8);
        this.mTvTitle.setText(R.string.config_systemSet_Title);
        this.mBtnBack.setOnClickListener(this);
    }

    public void pushFragmentForVSA() {
        if (this.mSystemSetRes != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_systemSet_VideoFormat), "SelectFragmentForVSA", 0, this.mListStrVSA, this.mListIntVSA);
        }
    }

    public void pushFragmentForReset() {
        if (this.mSystemSetRes != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_systemSet_Reset), "SelectFragmentForReset", 1, this.mListStrReset, (List<Integer>) null);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.config_title_btn_back) {
            switch (id) {
                case R.id.systemset_btn_reboot /*2131363138*/:
                    if (this.mApp.mRebootDeviceMaskInt > 0) {
                        reboot();
                        return;
                    } else {
                        showPopupWindow();
                        return;
                    }
                case R.id.systemset_rl_reset /*2131363139*/:
                    if (this.mApp.mDeviceDefMaskInt > 0) {
                        pushFragmentForReset();
                        return;
                    } else if (this.mApp.mDeviceDefMaskInt == 0) {
                        showPopupWindow();
                        return;
                    } else {
                        return;
                    }
                case R.id.systemset_rl_videoformat /*2131363140*/:
                    if (this.mApp.mSystemMaskInt > 0) {
                        pushFragmentForVSA();
                        return;
                    } else if (this.mApp.mSystemMaskInt == 0) {
                        showPopupWindow();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        } else {
            prePage();
        }
    }

    public void reboot() {
        if (this.mSystemSetRes != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mConfigUi);
            builder.setTitle(R.string.config_systemSet_RebootRemind);
            builder.setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    LogUtils.e("SystemSetFragment", "reboot 1");
                    SystemSetFragment.this.mDialog.hide();
                    NetPresenter.getDefault().rebootDevice(SystemSetFragment.this);
                }
            });
            builder.setNegativeButton(R.string.group_Cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SystemSetFragment.this.mDialog.hide();
                }
            });
            this.mDialog = builder.show();
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("SSP", "?");
            jSONObject.put("DEVEMM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String str) {
        try {
            this.mSystemSetRes = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public void rebootSuccess() {
        toastDeviceReboot();
    }

    public void rebootFailure() {
        toastFailure();
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mSystemSetRes;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        refreshUi();
        toastDeviceReboot();
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForVSA(int i) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = this.mSystemSetRes;
        if (jSONObject2 != null) {
            try {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("DEVEMM");
                if (jSONObject3 != null && (jSONObject = jSONObject3.getJSONObject("SSP")) != null) {
                    this.mVsa = i;
                    jSONObject.put("VSA", i);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForReset(List<Integer> list) {
        int intValue;
        byte[] bArr = new byte[512];
        int i = 0;
        while (i < list.size() && (intValue = list.get(i).intValue()) <= mResetbitArray.length) {
            int i2 = 0;
            while (true) {
                int[][] iArr = mResetbitArray;
                if (i2 >= iArr[intValue].length || iArr[intValue][i2] > 512) {
                    break;
                } else {
                    bArr[iArr[intValue][i2]] = 1;
                    i2++;
                }
            }
            i++;
        }
        NetManager.getDefault().setRestoreDefault3(bArr, 512, new SuperListener.SetConfigListener() {
            public void onFailure() {
            }

            public void onSuccess() {
            }
        });
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForVSA")) {
            if (list.size() > 0) {
                updateDateForVSA(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForReset") && list.size() > 0) {
            updateDateForReset(list);
        }
    }

    public void toastDeviceReboot() {
        if (ConfigFragment.dvrNet != null) {
            NetManager.unInitDvrNet(ConfigFragment.dvrNet);
            ConfigFragment.dvrNet = null;
        }
        toastSf((int) R.string.config_deviceReboot);
    }
}
