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
        JSONObject ethernetConfig = this.mEthernet;
        if (ethernetConfig != null) {
            try {
                int ipMode = ethernetConfig.getInt("IPMODE");
                this.mBtnIpMode.setBackgroundResource(ipMode == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                JSONObject pipConfig = this.mEthernet.getJSONObject("PIP");
                String ipAddress = "";
                String subnetMask;
                String gateway;
                if (pipConfig != null) {
                    ipAddress = pipConfig.getString("IPADDR");
                    subnetMask = pipConfig.getString("SUBMASK");
                    gateway = pipConfig.getString("GATEWAY");
                } else {
                    gateway = ipAddress;
                    subnetMask = gateway;
                }
                setTvEnableAndContent(this.mEtIp, ipMode, ipAddress);
                setTvEnableAndContent(this.mEtSm, ipMode, subnetMask);
                setTvEnableAndContent(this.mEtDg, ipMode, gateway);
            } catch (JSONException unused) {
                showErrorFragment();
            }
        }
    }

    private void configureDnsMode() {
        JSONObject ethernetConfig = this.mEthernet;
        if (ethernetConfig != null) {
            try {
                int dnsMode = ethernetConfig.getInt("DNSMODE");
                this.mBtnDnsMode.setBackgroundResource(dnsMode == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                JSONObject dnsConfig = this.mEthernet.getJSONObject("DNS");
                String primaryDns = "";
                String secondaryDns;
                if (dnsConfig != null) {
                    primaryDns = dnsConfig.getString("PDNS");
                    secondaryDns = dnsConfig.getString("ADNS");
                } else {
                    secondaryDns = primaryDns;
                }
                setTvEnableAndContent(this.mEtFd, dnsMode, primaryDns);
                setTvEnableAndContent(this.mEtSd, dnsMode, secondaryDns);
            } catch (JSONException unused) {
            }
        }
    }

    private void refreshUi() {
        JSONObject lanResponse = this.mLanRes;
        if (lanResponse == null) {
            showErrorFragment();
            return;
        }
        try {
            JSONObject networkSection = lanResponse.getJSONObject("NWSM");
            if (networkSection == null) {
                showErrorFragment();
                return;
            }
            JSONObject ethernetConfig = networkSection.getJSONObject("ETHERNET");
            this.mEthernet = ethernetConfig;
            if (ethernetConfig == null) {
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
                Pattern ipAddressPattern = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");
                if (ipAddressPattern.matcher(getString(this.mEtIp)).matches() && ipAddressPattern.matcher(getString(this.mEtSm)).matches() && ipAddressPattern.matcher(getString(this.mEtDg)).matches() && ipAddressPattern.matcher(getString(this.mEtFd)).matches()) {
                    if (ipAddressPattern.matcher(getString(this.mEtSd)).matches()) {
                        JSONObject pipConfig = this.mEthernet.getJSONObject("PIP");
                        JSONObject dnsConfig = this.mEthernet.getJSONObject("DNS");
                        if (pipConfig == null) {
                            return;
                        }
                        if (dnsConfig != null) {
                            pipConfig.put("IPADDR", getString(this.mEtIp));
                            pipConfig.put("SUBMASK", getString(this.mEtSm));
                            pipConfig.put("GATEWAY", getString(this.mEtDg));
                            dnsConfig.put("PDNS", getString(this.mEtFd));
                            dnsConfig.put("ADNS", getString(this.mEtSd));
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
        JSONObject ethernetConfig = this.mEthernet;
        if (ethernetConfig != null) {
            try {
                this.mEthernet.put("IPMODE", ethernetConfig.getInt("IPMODE") == 0 ? 1 : 0);
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void switchDnsMode() {
        JSONObject ethernetConfig = this.mEthernet;
        if (ethernetConfig != null) {
            try {
                if (ethernetConfig.getInt("IPMODE") != 0) {
                    this.mEthernet.put("DNSMODE", this.mEthernet.getInt("DNSMODE") == 0 ? 1 : 0);
                    refreshUi();
                }
            } catch (JSONException unused) {
            }
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject requestJson = new JSONObject();
            JSONObject networkSection = new JSONObject();
            networkSection.put("ETHERNET", "?");
            requestJson.put("NWSM", networkSection);
            return requestJson.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String response) {
        try {
            this.mLanRes = new JSONObject(response);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject lanResponse = this.mLanRes;
        if (lanResponse == null) {
            return "";
        }
        return lanResponse.toString();
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
