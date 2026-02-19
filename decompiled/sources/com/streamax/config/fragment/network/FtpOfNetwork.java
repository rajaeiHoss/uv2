package com.streamax.config.fragment.network;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.StringUtils;
import com.streamax.view.VsEditView;
import com.zycs.UView.R;
import org.json.JSONException;
import org.json.JSONObject;

public class FtpOfNetwork extends ConfigFragment implements BaseListener.GetListener, BaseListener.SetListener {
    public Button mBtnEnable;
    public VsEditView mEtFolder;
    public VsEditView mEtPort;
    public VsEditView mEtPwd;
    public VsEditView mEtServer;
    public VsEditView mEtUsername;
    private JSONObject mFtpObj;
    private JSONObject mFtpRes;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_network_ftp, (ViewGroup) null);
        this.mBtnEnable = (Button) this.mRootView.findViewById(R.id.network_ftp_btn_upload);
        this.mEtServer = (VsEditView) this.mRootView.findViewById(R.id.network_ftp_et_setver);
        this.mEtPort = (VsEditView) this.mRootView.findViewById(R.id.network_ftp_et_port);
        this.mEtUsername = (VsEditView) this.mRootView.findViewById(R.id.network_ftp_et_username);
        this.mEtPwd = (VsEditView) this.mRootView.findViewById(R.id.network_ftp_et_pwd);
        this.mEtFolder = (VsEditView) this.mRootView.findViewById(R.id.network_ftp_et_dir);
        this.mBtnSave = (Button) this.mRootView.findViewById(R.id.network_ftp_btn_save);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    private void refreshUi() {
        JSONObject jSONObject = this.mFtpRes;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("NWPM");
                if (jSONObject2 != null) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("FTPS");
                    this.mFtpObj = jSONObject3;
                    if (jSONObject3 != null) {
                        int i = jSONObject3.getInt("EN");
                        this.mBtnEnable.setBackgroundResource(i == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                        String string = this.mFtpObj.getString("SERVERIP");
                        int i2 = this.mFtpObj.getInt("PORT");
                        String string2 = this.mFtpObj.getString("LOGINUSER");
                        String string3 = this.mFtpObj.getString("LOGINPWD");
                        String string4 = this.mFtpObj.getString("SUBFOLDER");
                        int i3 = i == 0 ? 1 : 0;
                        setTvEnableAndContent(this.mEtServer, i3, string);
                        VsEditView vsEditView = this.mEtPort;
                        setTvEnableAndContent(vsEditView, i3, "" + i2);
                        setTvEnableAndContent(this.mEtUsername, i3, string2);
                        setTvEnableAndContent(this.mEtPwd, i3, string3);
                        setTvEnableAndContent(this.mEtFolder, i3, string4);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnEnable.setOnClickListener(this);
        this.mBtnSave.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mBtnUpdate.setVisibility(8);
        this.mTvTitle.setText(R.string.config_network_ftp_Title);
        this.mBtnBack.setOnClickListener(this);
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.config_title_btn_back /*2131362205*/:
                prePage();
                return;
            case R.id.network_ftp_btn_save /*2131362795*/:
                saveData();
                return;
            case R.id.network_ftp_btn_upload /*2131362796*/:
                setBtnStatus();
                return;
            default:
                return;
        }
    }

    public void saveData() {
        int intValue = parse2Int(this.mEtPort).intValue();
        if (intValue <= 0 || intValue > 65535) {
            toastSf((int) R.string.PortIsError);
            return;
        }
        JSONObject jSONObject = this.mFtpObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("SERVERIP", StringUtils.getString(this.mEtServer));
                this.mFtpObj.put("PORT", StringUtils.getString(this.mEtPort));
                this.mFtpObj.put("LOGINUSER", StringUtils.getString(this.mEtUsername));
                this.mFtpObj.put("LOGINPWD", StringUtils.getString(this.mEtPwd));
                this.mFtpObj.put("SUBFOLDER", StringUtils.getString(this.mEtFolder));
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void setBtnStatus() {
        JSONObject jSONObject = this.mFtpObj;
        if (jSONObject != null) {
            try {
                this.mFtpObj.put("EN", jSONObject.getInt("EN") == 0 ? 1 : 0);
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("FTPS", "?");
            jSONObject.put("NWPM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String str) {
        try {
            LogUtils.e("FtpOfNetwork", "getSuccess 1, result: " + str);
            this.mFtpRes = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mFtpRes;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        LogUtils.e("FtpOfNetwork", "setSuccess 1");
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }
}
