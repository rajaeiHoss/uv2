package com.streamax.config.fragment.network;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetManager;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.view.VsEditView;
import com.zycs.UView.R;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class LanOfNetwork extends ConfigFragment implements BaseListener.GetListener, BaseListener.SetListener {
    private Button mBtnDnsMode;
    public Button mBtnIpMode;
    public VsEditView mEtDg;
    private VsEditView mEtFd;
    public VsEditView mEtIp;
    private VsEditView mEtSd;
    public VsEditView mEtSm;
    private JSONObject mEthernet;
    private JSONObject mLanRes;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_network_lan_Title);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_network_lan, (ViewGroup) null);
        this.mBtnIpMode = (Button) this.mRootView.findViewById(R.id.network_lan_btn_staticip);
        this.mEtIp = (VsEditView) this.mRootView.findViewById(R.id.network_lan_et_ip);
        this.mEtSm = (VsEditView) this.mRootView.findViewById(R.id.network_lan_et_sm);
        this.mEtDg = (VsEditView) this.mRootView.findViewById(R.id.network_lan_et_dg);
        this.mBtnDnsMode = (Button) this.mRootView.findViewById(R.id.network_lan_btn_staticdns);
        this.mEtFd = (VsEditView) this.mRootView.findViewById(R.id.network_lan_et_firstdns);
        this.mEtSd = (VsEditView) this.mRootView.findViewById(R.id.network_lan_et_seconddns);
        this.mBtnSave = (Button) this.mRootView.findViewById(R.id.network_lan_btn_save);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnIpMode.setOnClickListener(this);
        this.mBtnDnsMode.setOnClickListener(this);
        this.mBtnSave.setOnClickListener(this);
    }

    private void configureIpMode() {
        String str;
        String str2;
        JSONObject jSONObject = this.mEthernet;
        if (jSONObject != null) {
            try {
                int i = jSONObject.getInt("IPMODE");
                this.mBtnIpMode.setBackgroundResource(i == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                JSONObject jSONObject2 = this.mEthernet.getJSONObject("PIP");
                String str3 = "";
                if (jSONObject2 != null) {
                    str3 = jSONObject2.getString("IPADDR");
                    str = jSONObject2.getString("SUBMASK");
                    str2 = jSONObject2.getString("GATEWAY");
                } else {
                    str2 = str3;
                    str = str2;
                }
                setTvEnableAndContent(this.mEtIp, i, str3);
                setTvEnableAndContent(this.mEtSm, i, str);
                setTvEnableAndContent(this.mEtDg, i, str2);
            } catch (JSONException unused) {
                showErrorFragment();
            }
        }
    }

    private void configureDnsMode() {
        String str;
        JSONObject jSONObject = this.mEthernet;
        if (jSONObject != null) {
            try {
                int i = jSONObject.getInt("DNSMODE");
                this.mBtnDnsMode.setBackgroundResource(i == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                JSONObject jSONObject2 = this.mEthernet.getJSONObject("DNS");
                String str2 = "";
                if (jSONObject2 != null) {
                    str2 = jSONObject2.getString("PDNS");
                    str = jSONObject2.getString("ADNS");
                } else {
                    str = str2;
                }
                setTvEnableAndContent(this.mEtFd, i, str2);
                setTvEnableAndContent(this.mEtSd, i, str);
            } catch (JSONException unused) {
            }
        }
    }

    private void refreshUi() {
        JSONObject jSONObject = this.mLanRes;
        if (jSONObject == null) {
            showErrorFragment();
            return;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("NWSM");
            if (jSONObject2 == null) {
                showErrorFragment();
                return;
            }
            JSONObject jSONObject3 = jSONObject2.getJSONObject("ETHERNET");
            this.mEthernet = jSONObject3;
            if (jSONObject3 == null) {
                showErrorFragment();
                return;
            }
            configureIpMode();
            if (this.mEthernet.getInt("IPMODE") == 0) {
                this.mEthernet.put("DNSMODE", 0);
            }
            configureDnsMode();
        } catch (JSONException unused) {
        }
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.config_title_btn_back) {
            switch (id) {
                case R.id.network_lan_btn_save /*2131362804*/:
                    saveData();
                    return;
                case R.id.network_lan_btn_staticdns /*2131362805*/:
                    switchDnsMode();
                    return;
                case R.id.network_lan_btn_staticip /*2131362806*/:
                    switchIpMode();
                    return;
                default:
                    return;
            }
        } else {
            prePage();
        }
    }

    public void saveData() {
        if (this.mEthernet != null) {
            try {
                Pattern compile = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");
                if (compile.matcher(getString(this.mEtIp)).matches() && compile.matcher(getString(this.mEtSm)).matches() && compile.matcher(getString(this.mEtDg)).matches() && compile.matcher(getString(this.mEtFd)).matches()) {
                    if (compile.matcher(getString(this.mEtSd)).matches()) {
                        JSONObject jSONObject = this.mEthernet.getJSONObject("PIP");
                        JSONObject jSONObject2 = this.mEthernet.getJSONObject("DNS");
                        if (jSONObject == null) {
                            return;
                        }
                        if (jSONObject2 != null) {
                            jSONObject.put("IPADDR", getString(this.mEtIp));
                            jSONObject.put("SUBMASK", getString(this.mEtSm));
                            jSONObject.put("GATEWAY", getString(this.mEtDg));
                            jSONObject2.put("PDNS", getString(this.mEtFd));
                            jSONObject2.put("ADNS", getString(this.mEtSd));
                            NetPresenter.getDefault().setConfig(this);
                            return;
                        }
                        return;
                    }
                }
                toastSf((int) R.string.AddrIsError);
            } catch (JSONException unused) {
            }
        }
    }

    private void switchIpMode() {
        JSONObject jSONObject = this.mEthernet;
        if (jSONObject != null) {
            try {
                this.mEthernet.put("IPMODE", jSONObject.getInt("IPMODE") == 0 ? 1 : 0);
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void switchDnsMode() {
        JSONObject jSONObject = this.mEthernet;
        if (jSONObject != null) {
            try {
                if (jSONObject.getInt("IPMODE") != 0) {
                    this.mEthernet.put("DNSMODE", this.mEthernet.getInt("DNSMODE") == 0 ? 1 : 0);
                    refreshUi();
                }
            } catch (JSONException unused) {
            }
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ETHERNET", "?");
            jSONObject.put("NWSM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String str) {
        try {
            this.mLanRes = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mLanRes;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        LogUtils.e("LanOfNetwork", "setSuccess 1");
        refreshUi();
        toastSelectDeviceConnect();
    }

    public void setFailure() {
        toastFailure();
    }

    public void toastSelectDeviceConnect() {
        if (ConfigFragment.dvrNet != null) {
            NetManager.unInitDvrNet(ConfigFragment.dvrNet);
            ConfigFragment.dvrNet = null;
        }
        toastSf((int) R.string.config_selectDeviceReconnect);
    }
}
