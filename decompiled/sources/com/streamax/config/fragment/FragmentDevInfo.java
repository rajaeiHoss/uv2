package com.streamax.config.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.view.VsEditView;
import com.zycs.UView.R;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentDevInfo extends ConfigFragment implements BaseListener.SetListener, BaseListener.GetListener {
    public JSONObject mDevInfoObj;
    public String mDevInfoReq;
    public VsEditView mEtDeviceId;
    public VsEditView mEtDeviceName;

    public BaseFragment getCurFragment() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_dev_info_Title);
        this.mBtnBack.setVisibility(0);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_dev_info, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    public void initChildView() {
        this.mEtDeviceName = (VsEditView) this.mRootView.findViewById(R.id.config_dev_info_et_deviceName);
        this.mEtDeviceId = (VsEditView) this.mRootView.findViewById(R.id.config_dev_info_et_deviceId);
        this.mBtnSave = (Button) this.mRootView.findViewById(R.id.config_dev_info_btn_save);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getSuccess("{\n\t\"DEVEMM\": \n\t{\n\t\t\"SSP\":\n\t\t{\n\t\t\t\"DEVN\": \"XVR\",         \n\t\t\t\"UID\": \"127\"            \n\t\t}\n\t}\n}");
        getConfig();
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void refreshUi() {
        JSONObject jSONObject = this.mDevInfoObj;
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
            String string = jSONObject3.getString("DEVN");
            String string2 = jSONObject3.getString("UID");
            setTvEnableAndContent(this.mEtDeviceName, 0, string);
            setTvEnableAndContent(this.mEtDeviceId, 0, string2);
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnSave.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.config_dev_info_btn_save) {
            saveData();
        } else if (id == R.id.config_title_btn_back) {
            prePage();
        }
    }

    public void prePage() {
        this.mBtnBack.setVisibility(8);
        super.prePage();
    }

    public boolean saveUi() {
        JSONObject jSONObject;
        JSONObject jSONObject2 = this.mDevInfoObj;
        if (jSONObject2 == null) {
            return false;
        }
        try {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("DEVEMM");
            if (jSONObject3 == null || (jSONObject = jSONObject3.getJSONObject("SSP")) == null) {
                return false;
            }
            jSONObject.put("DEVN", getString(this.mEtDeviceName));
            jSONObject.put("UID", getString(this.mEtDeviceId));
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    public void saveData() {
        if (saveUi()) {
            NetPresenter.getDefault().setConfig(this);
        }
    }

    public String requestForGetConfig() {
        this.mDevInfoReq = "";
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("SSP", "?");
            jSONObject.put("DEVEMM", jSONObject2);
            this.mDevInfoReq = jSONObject.toString();
        } catch (JSONException unused) {
        }
        return this.mDevInfoReq;
    }

    public void getSuccess(String str) {
        try {
            this.mDevInfoObj = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public void getFailure() {
        toastSf((int) R.string.config_info_get_failure);
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mDevInfoObj;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        LogUtils.e("DateTimeFragment", "setSuccess 1");
        refreshUi();
        toastSf((int) R.string.config_info_set_success);
    }

    public void setFailure() {
        toastSf((int) R.string.config_info_set_failure);
    }
}
