package com.streamax.config.fragment.network;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.zycs.UView.R;
import org.json.JSONException;
import org.json.JSONObject;

public class UpnpOfNetwork extends ConfigFragment implements BaseListener.GetListener, BaseListener.SetListener {
    public Button mBtnEnable;
    private JSONObject mUpnpObj;
    private JSONObject mUpnpRes;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_network_upnp, (ViewGroup) null);
        this.mBtnEnable = (Button) this.mRootView.findViewById(R.id.network_upnp_btn);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    public void refreshUi() {
        JSONObject jSONObject = this.mUpnpRes;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("NWSM");
                if (jSONObject2 != null) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("UPNP");
                    this.mUpnpObj = jSONObject3;
                    if (jSONObject3 != null) {
                        this.mBtnEnable.setBackgroundResource(jSONObject3.getInt("ENABLE") == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                    }
                }
            } catch (JSONException unused) {
                showErrorFragment();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnEnable.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mBtnUpdate.setVisibility(8);
        this.mTvTitle.setText(R.string.config_upnp_Title);
        this.mBtnBack.setOnClickListener(this);
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.config_title_btn_back) {
            prePage();
        } else if (id == R.id.network_upnp_btn) {
            saveUpnp();
        }
    }

    public void saveUpnp() {
        JSONObject jSONObject = this.mUpnpObj;
        if (jSONObject != null) {
            try {
                this.mUpnpObj.put("ENABLE", jSONObject.getInt("ENABLE") == 0 ? 1 : 0);
            } catch (JSONException unused) {
                showErrorFragment();
            }
            NetPresenter.getDefault().setConfig(this);
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("UPNP", "?");
            jSONObject.put("NWSM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String str) {
        try {
            this.mUpnpRes = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mUpnpRes;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        LogUtils.e("UpnpOfNetwork", "setSuccess 1");
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }
}
