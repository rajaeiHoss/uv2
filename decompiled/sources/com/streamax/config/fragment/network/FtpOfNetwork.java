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
        JSONObject ftpResponse = this.mFtpRes;
        if (ftpResponse != null) {
            try {
                JSONObject networkParams = ftpResponse.getJSONObject("NWPM");
                if (networkParams != null) {
                    JSONObject ftpConfig = networkParams.getJSONObject("FTPS");
                    this.mFtpObj = ftpConfig;
                    if (ftpConfig != null) {
                        int ftpEnabled = ftpConfig.getInt("EN");
                        this.mBtnEnable.setBackgroundResource(ftpEnabled == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                        String serverIp = this.mFtpObj.getString("SERVERIP");
                        int port = this.mFtpObj.getInt("PORT");
                        String username = this.mFtpObj.getString("LOGINUSER");
                        String password = this.mFtpObj.getString("LOGINPWD");
                        String subfolder = this.mFtpObj.getString("SUBFOLDER");
                        int editMode = ftpEnabled == 0 ? 1 : 0;
                        setTvEnableAndContent(this.mEtServer, editMode, serverIp);
                        VsEditView portView = this.mEtPort;
                        setTvEnableAndContent(portView, editMode, "" + port);
                        setTvEnableAndContent(this.mEtUsername, editMode, username);
                        setTvEnableAndContent(this.mEtPwd, editMode, password);
                        setTvEnableAndContent(this.mEtFolder, editMode, subfolder);
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
        int port = parse2Int(this.mEtPort).intValue();
        if (port <= 0 || port > 65535) {
            toastSf((int) R.string.PortIsError);
            return;
        }
        JSONObject ftpConfig = this.mFtpObj;
        if (ftpConfig != null) {
            try {
                ftpConfig.put("SERVERIP", StringUtils.getString(this.mEtServer));
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
        JSONObject ftpConfig = this.mFtpObj;
        if (ftpConfig != null) {
            try {
                this.mFtpObj.put("EN", ftpConfig.getInt("EN") == 0 ? 1 : 0);
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject request = new JSONObject();
            JSONObject networkParams = new JSONObject();
            networkParams.put("FTPS", "?");
            request.put("NWPM", networkParams);
            return request.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String responseJson) {
        try {
            LogUtils.e("FtpOfNetwork", "getSuccess 1, result: " + responseJson);
            this.mFtpRes = new JSONObject(responseJson);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject ftpResponse = this.mFtpRes;
        if (ftpResponse == null) {
            return "";
        }
        return ftpResponse.toString();
    }

    public void setSuccess() {
        LogUtils.e("FtpOfNetwork", "setSuccess 1");
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }
}
