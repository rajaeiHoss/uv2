package com.streamax.config.fragment.network;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetManager;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.UiUtils;
import com.streamax.view.VsEditView;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DdnsOfNetwork extends ConfigFragment implements BaseListener.GetListener, BaseListener.SetListener {
    public static int DDNS_TYPE_3322 = 0;
    public static int DDNS_TYPE_CHANGEIP = 15;
    public static int DDNS_TYPE_DNSEXIT = 4;
    public static int DDNS_TYPE_DYNDNS = 1;
    public static int DDNS_TYPE_NOIP = 13;
    public Button mBtnDdns;
    public Button mBtnDefault;
    public Button mBtnSave;
    private JSONObject mDDNSObj;
    private JSONObject mDDNSRes;
    public VsEditView mEtAddr;
    public VsEditView mEtHostName;
    public VsEditView mEtPassword;
    public VsEditView mEtPort;
    public VsEditView mEtUserName;
    public List<Integer> mListIntServers = new ArrayList();
    public ArrayList<String> mListStrServers = new ArrayList<>();
    public LinearLayout mLlAddrPort;
    public LinearLayout mLlInfo1;
    public LinearLayout mLlInfo2;
    public RelativeLayout mRlServer;
    public List<ServerDesc> mServerListDatas = new ArrayList();
    public VsTextView mTvServer;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    public class ServerDesc {
        String name;
        int value;

        public ServerDesc() {
        }
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_network_ddns, (ViewGroup) null);
        this.mBtnDdns = (Button) this.mRootView.findViewById(R.id.network_ddns_btn_ddns);
        this.mRlServer = (RelativeLayout) this.mRootView.findViewById(R.id.network_ddns_server_rl_server);
        this.mTvServer = (VsTextView) this.mRootView.findViewById(R.id.network_ddns_server_tv_server);
        this.mLlInfo1 = (LinearLayout) this.mRootView.findViewById(R.id.network_ddns_info1_ll);
        this.mBtnDefault = (Button) this.mRootView.findViewById(R.id.network_ddns_info1_btn_default);
        this.mLlAddrPort = (LinearLayout) this.mRootView.findViewById(R.id.network_ddns_info1_ll_addrport);
        this.mEtAddr = (VsEditView) this.mRootView.findViewById(R.id.network_ddns_info1_et_saddr);
        this.mEtPort = (VsEditView) this.mRootView.findViewById(R.id.network_ddns_info1_et_sport);
        this.mLlInfo2 = (LinearLayout) this.mRootView.findViewById(R.id.network_ddns_info2_ll);
        this.mEtHostName = (VsEditView) this.mRootView.findViewById(R.id.network_ddns_info2_et_hostname);
        this.mEtUserName = (VsEditView) this.mRootView.findViewById(R.id.network_ddns_info2_et_username);
        this.mEtPassword = (VsEditView) this.mRootView.findViewById(R.id.network_ddns_info2_et_password);
        this.mBtnSave = (Button) this.mRootView.findViewById(R.id.network_ddns_btn_save);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    private boolean usesAccountDdnsFields(int ddnsType) {
        return ddnsType == DDNS_TYPE_3322 || ddnsType == DDNS_TYPE_DYNDNS || ddnsType == DDNS_TYPE_DNSEXIT || ddnsType == DDNS_TYPE_NOIP
                || ddnsType == DDNS_TYPE_CHANGEIP;
    }

    public void refreshUi() {
        LogUtils.e("DdnsOfNetwork", "refreshUi 1");
        JSONObject ddnsResponse = this.mDDNSRes;
        if (ddnsResponse == null) {
            return;
        }
        try {
            JSONObject networkSettings = ddnsResponse.getJSONObject("NWSM");
            JSONArray serverList = this.mDDNSRes.getJSONArray("GETDDNSLIST");
            if (networkSettings == null || serverList == null) {
                return;
            }
            this.mDDNSObj = networkSettings.getJSONObject("DDNS");
            if (this.mDDNSObj == null) {
                return;
            }
            int ddnsSwitch = this.mDDNSObj.getInt("DDNSSWITCH");
            this.mBtnDdns.setBackgroundResource(ddnsSwitch == 0 ? R.drawable.switch_close : R.drawable.switch_open);
            int ddnsType = this.mDDNSObj.getInt("DDNSTYPE");

            this.mServerListDatas.clear();
            for (int serverIndex = 0; serverIndex < serverList.length(); serverIndex++) {
                JSONObject serverObj = serverList.getJSONObject(serverIndex);
                String serverName = serverObj.getString("TYPE");
                String serverSelect = serverObj.getString("SELECT");
                int serverValue = serverObj.getInt("VALUE");
                if (!serverName.isEmpty() && !serverSelect.equalsIgnoreCase("false") && serverValue >= 0) {
                    ServerDesc serverDesc = new ServerDesc();
                    serverDesc.name = serverName;
                    serverDesc.value = serverValue;
                    this.mServerListDatas.add(serverDesc);
                }
            }

            this.mListStrServers.clear();
            this.mListIntServers.clear();
            int selectedServerIndex = -1;
            String selectedServerName = "";
            for (int serverIndex = 0; serverIndex < this.mServerListDatas.size(); serverIndex++) {
                ServerDesc serverDesc = this.mServerListDatas.get(serverIndex);
                if (serverDesc.value == ddnsType) {
                    selectedServerIndex = serverIndex;
                    selectedServerName = serverDesc.name;
                }
                this.mListStrServers.add(serverDesc.name);
            }
            if (selectedServerIndex >= 0) {
                this.mListIntServers.add(Integer.valueOf(selectedServerIndex));
            }

            this.mTvServer.setText(selectedServerName);
            this.mTvServer.setEnabled(ddnsSwitch == 1);
            if (usesAccountDdnsFields(ddnsType)) {
                this.mLlInfo1.setVisibility(8);
                this.mLlInfo2.setVisibility(0);
            } else {
                this.mLlInfo1.setVisibility(0);
                this.mLlInfo2.setVisibility(8);
            }

            this.mBtnDefault.setEnabled(ddnsSwitch == 1);
            int defaultServerSwitch = this.mDDNSObj.getInt("DE81");
            this.mBtnDefault.setBackgroundResource(defaultServerSwitch == 0 ? R.drawable.switch_close : R.drawable.switch_open);
            this.mLlAddrPort.setVisibility(defaultServerSwitch == 0 ? 0 : 8);

            int editDisabled = ddnsSwitch == 0 ? 1 : 0;
            setTvEnableAndContent(this.mEtAddr, editDisabled, this.mDDNSObj.getString("SNAME"));
            setTvEnableAndContent(this.mEtPort, editDisabled, "" + this.mDDNSObj.getInt("PORT"));
            setTvEnableAndContent(this.mEtHostName, editDisabled, this.mDDNSObj.getString("HOSTNAME"));
            setTvEnableAndContent(this.mEtUserName, editDisabled, this.mDDNSObj.getString("USERID"));
            setTvEnableAndContent(this.mEtPassword, editDisabled, this.mDDNSObj.getString("PWD"));
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnDdns.setOnClickListener(this);
        this.mRlServer.setOnClickListener(this);
        this.mBtnDefault.setOnClickListener(this);
        this.mBtnSave.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mBtnUpdate.setVisibility(8);
        this.mTvTitle.setText(R.string.config_network_ddns_Title);
        this.mBtnBack.setOnClickListener(this);
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void pushFragmentForServers() {
        if (this.mDDNSObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_network_ddns_Server), "SelectFragmentForServers", 0, this.mListStrServers, this.mListIntServers);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.config_title_btn_back) {
            prePage();
        } else if (id != R.id.network_ddns_server_rl_server) {
            switch (id) {
                case R.id.network_ddns_btn_ddns /*2131362765*/:
                    setSwitchDdns();
                    return;
                case R.id.network_ddns_btn_save /*2131362766*/:
                    saveData();
                    return;
                case R.id.network_ddns_info1_btn_default /*2131362767*/:
                    setSwitchDefault();
                    return;
                default:
                    return;
            }
        } else {
            pushFragmentForServers();
        }
    }

    public void saveData() {
        LogUtils.e("DdnsOfNetwork", "saveData 1");
        JSONObject jSONObject = this.mDDNSObj;
        if (jSONObject != null) {
            try {
                int i = jSONObject.getInt("DDNSTYPE");
                if (!(i == DDNS_TYPE_3322 || i == DDNS_TYPE_DYNDNS || i == DDNS_TYPE_DNSEXIT || i == DDNS_TYPE_NOIP)) {
                    if (i != DDNS_TYPE_CHANGEIP) {
                        if (!Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$").matcher(getString(this.mEtAddr)).matches()) {
                            toastSf((int) R.string.AddrIsError);
                            return;
                        }
                        int intValue = parse2Int(this.mEtPort).intValue();
                        if (intValue <= 0 || intValue > 65535) {
                            toastSf((int) R.string.PortIsError);
                            return;
                        }
                    }
                }
                this.mDDNSObj.put("SNAME", getString(this.mEtAddr));
                this.mDDNSObj.put("PORT", parse2Int(this.mEtPort));
                this.mDDNSObj.put("HOSTNAME", getString(this.mEtHostName));
                this.mDDNSObj.put("USERID", getString(this.mEtUserName));
                this.mDDNSObj.put("PWD", getString(this.mEtPassword));
                LogUtils.e("DdnsOfNetwork", "saveData 2");
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void setSwitchDdns() {
        JSONObject jSONObject = this.mDDNSObj;
        if (jSONObject != null) {
            try {
                int i = 1;
                if (jSONObject.getInt("DDNSSWITCH") == 1) {
                    i = 0;
                }
                this.mDDNSObj.put("DDNSSWITCH", i);
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void setSwitchDefault() {
        JSONObject jSONObject = this.mDDNSObj;
        if (jSONObject != null) {
            try {
                int i = 1;
                if (jSONObject.getInt("DE81") == 1) {
                    i = 0;
                }
                this.mDDNSObj.put("DE81", i);
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("DDNS", "?");
            jSONObject.put("NWSM", jSONObject2);
            jSONObject.put("GETDDNSLIST", "?");
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String response) {
        try {
            this.mDDNSRes = new JSONObject(response);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mDDNSRes;
        if (jSONObject == null) {
            return "";
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("NWSM");
            if (jSONObject2 == null) {
                return "";
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("NWSM", jSONObject2);
            LogUtils.e("DdnsOfNetwork", "requestForSetConfig 1, nwsmObj: " + jSONObject3.toString());
            return jSONObject3.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void setSuccess() {
        LogUtils.e("DdnsOfNetwork", "setSuccess 1");
        toastSuccess();
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForServers(int i) {
        if (this.mDDNSObj != null) {
            try {
                if (i < this.mServerListDatas.size()) {
                    this.mDDNSObj.put("DDNSTYPE", this.mServerListDatas.get(i).value);
                    refreshUi();
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String fragmentName, List<Integer> list) {
        if (fragmentName.equals("SelectFragmentForServers") && list.size() > 0) {
            updateDateForServers(list.get(0).intValue());
        }
    }

    public void toastSelectDeviceConnect() {
        if (ConfigFragment.dvrNet != null) {
            NetManager.unInitDvrNet(ConfigFragment.dvrNet);
            ConfigFragment.dvrNet = null;
        }
        toastSf((int) R.string.config_selectDeviceReconnect);
    }
}
