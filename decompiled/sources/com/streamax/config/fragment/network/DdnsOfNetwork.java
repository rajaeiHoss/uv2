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

    /* JADX WARNING: Removed duplicated region for block: B:57:0x011e A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0120 A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0130 A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0131 A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0139 A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x013f A[Catch:{ JSONException -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0146 A[Catch:{ JSONException -> 0x0199 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void refreshUi() {
        /*
            r11 = this;
            java.lang.String r0 = "DdnsOfNetwork"
            java.lang.String r1 = "refreshUi 1"
            com.streamax.utils.LogUtils.e((java.lang.String) r0, (java.lang.String) r1)
            org.json.JSONObject r0 = r11.mDDNSRes
            if (r0 != 0) goto L_0x000c
            return
        L_0x000c:
            java.lang.String r1 = "NWSM"
            org.json.JSONObject r0 = r0.getJSONObject(r1)     // Catch:{ JSONException -> 0x0199 }
            org.json.JSONObject r1 = r11.mDDNSRes     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r2 = "GETDDNSLIST"
            org.json.JSONArray r1 = r1.getJSONArray(r2)     // Catch:{ JSONException -> 0x0199 }
            if (r0 == 0) goto L_0x0198
            if (r1 != 0) goto L_0x0020
            goto L_0x0198
        L_0x0020:
            java.lang.String r2 = "DDNS"
            org.json.JSONObject r0 = r0.getJSONObject(r2)     // Catch:{ JSONException -> 0x0199 }
            r11.mDDNSObj = r0     // Catch:{ JSONException -> 0x0199 }
            if (r0 != 0) goto L_0x002b
            return
        L_0x002b:
            java.lang.String r2 = "DDNSSWITCH"
            int r0 = r0.getInt(r2)     // Catch:{ JSONException -> 0x0199 }
            android.widget.Button r2 = r11.mBtnDdns     // Catch:{ JSONException -> 0x0199 }
            r3 = 2131231390(0x7f08029e, float:1.807886E38)
            r4 = 2131231391(0x7f08029f, float:1.8078862E38)
            if (r0 != 0) goto L_0x003f
            r5 = 2131231390(0x7f08029e, float:1.807886E38)
            goto L_0x0042
        L_0x003f:
            r5 = 2131231391(0x7f08029f, float:1.8078862E38)
        L_0x0042:
            r2.setBackgroundResource(r5)     // Catch:{ JSONException -> 0x0199 }
            org.json.JSONObject r2 = r11.mDDNSObj     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r5 = "DDNSTYPE"
            int r2 = r2.getInt(r5)     // Catch:{ JSONException -> 0x0199 }
            java.util.List<com.streamax.config.fragment.network.DdnsOfNetwork$ServerDesc> r5 = r11.mServerListDatas     // Catch:{ JSONException -> 0x0199 }
            r5.clear()     // Catch:{ JSONException -> 0x0199 }
            r5 = 0
            r6 = 0
        L_0x0054:
            int r7 = r1.length()     // Catch:{ JSONException -> 0x0199 }
            if (r6 >= r7) goto L_0x0092
            org.json.JSONObject r7 = r1.getJSONObject(r6)     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r8 = "TYPE"
            java.lang.String r8 = r7.getString(r8)     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r9 = "SELECT"
            java.lang.String r9 = r7.getString(r9)     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r10 = "VALUE"
            int r7 = r7.getInt(r10)     // Catch:{ JSONException -> 0x0199 }
            boolean r10 = r8.isEmpty()     // Catch:{ JSONException -> 0x0199 }
            if (r10 != 0) goto L_0x008f
            java.lang.String r10 = "false"
            boolean r9 = r9.equalsIgnoreCase(r10)     // Catch:{ JSONException -> 0x0199 }
            if (r9 != 0) goto L_0x008f
            if (r7 >= 0) goto L_0x0081
            goto L_0x008f
        L_0x0081:
            com.streamax.config.fragment.network.DdnsOfNetwork$ServerDesc r9 = new com.streamax.config.fragment.network.DdnsOfNetwork$ServerDesc     // Catch:{ JSONException -> 0x0199 }
            r9.<init>()     // Catch:{ JSONException -> 0x0199 }
            r9.name = r8     // Catch:{ JSONException -> 0x0199 }
            r9.value = r7     // Catch:{ JSONException -> 0x0199 }
            java.util.List<com.streamax.config.fragment.network.DdnsOfNetwork$ServerDesc> r7 = r11.mServerListDatas     // Catch:{ JSONException -> 0x0199 }
            r7.add(r9)     // Catch:{ JSONException -> 0x0199 }
        L_0x008f:
            int r6 = r6 + 1
            goto L_0x0054
        L_0x0092:
            java.util.ArrayList<java.lang.String> r1 = r11.mListStrServers     // Catch:{ JSONException -> 0x0199 }
            r1.clear()     // Catch:{ JSONException -> 0x0199 }
            java.util.List<java.lang.Integer> r1 = r11.mListIntServers     // Catch:{ JSONException -> 0x0199 }
            r1.clear()     // Catch:{ JSONException -> 0x0199 }
            r1 = -1
            java.lang.String r6 = ""
            r8 = r6
            r7 = 0
        L_0x00a1:
            java.util.List<com.streamax.config.fragment.network.DdnsOfNetwork$ServerDesc> r9 = r11.mServerListDatas     // Catch:{ JSONException -> 0x0199 }
            int r9 = r9.size()     // Catch:{ JSONException -> 0x0199 }
            if (r7 >= r9) goto L_0x00d2
            java.util.List<com.streamax.config.fragment.network.DdnsOfNetwork$ServerDesc> r9 = r11.mServerListDatas     // Catch:{ JSONException -> 0x0199 }
            java.lang.Object r9 = r9.get(r7)     // Catch:{ JSONException -> 0x0199 }
            com.streamax.config.fragment.network.DdnsOfNetwork$ServerDesc r9 = (com.streamax.config.fragment.network.DdnsOfNetwork.ServerDesc) r9     // Catch:{ JSONException -> 0x0199 }
            int r9 = r9.value     // Catch:{ JSONException -> 0x0199 }
            if (r9 != r2) goto L_0x00c0
            java.util.List<com.streamax.config.fragment.network.DdnsOfNetwork$ServerDesc> r1 = r11.mServerListDatas     // Catch:{ JSONException -> 0x0199 }
            java.lang.Object r1 = r1.get(r7)     // Catch:{ JSONException -> 0x0199 }
            com.streamax.config.fragment.network.DdnsOfNetwork$ServerDesc r1 = (com.streamax.config.fragment.network.DdnsOfNetwork.ServerDesc) r1     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r8 = r1.name     // Catch:{ JSONException -> 0x0199 }
            r1 = r7
        L_0x00c0:
            java.util.ArrayList<java.lang.String> r9 = r11.mListStrServers     // Catch:{ JSONException -> 0x0199 }
            java.util.List<com.streamax.config.fragment.network.DdnsOfNetwork$ServerDesc> r10 = r11.mServerListDatas     // Catch:{ JSONException -> 0x0199 }
            java.lang.Object r10 = r10.get(r7)     // Catch:{ JSONException -> 0x0199 }
            com.streamax.config.fragment.network.DdnsOfNetwork$ServerDesc r10 = (com.streamax.config.fragment.network.DdnsOfNetwork.ServerDesc) r10     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r10 = r10.name     // Catch:{ JSONException -> 0x0199 }
            r9.add(r10)     // Catch:{ JSONException -> 0x0199 }
            int r7 = r7 + 1
            goto L_0x00a1
        L_0x00d2:
            if (r1 < 0) goto L_0x00de
            java.util.List<java.lang.Integer> r7 = r11.mListIntServers     // Catch:{ JSONException -> 0x0199 }
            java.lang.Integer r9 = new java.lang.Integer     // Catch:{ JSONException -> 0x0199 }
            r9.<init>(r1)     // Catch:{ JSONException -> 0x0199 }
            r7.add(r9)     // Catch:{ JSONException -> 0x0199 }
        L_0x00de:
            com.streamax.view.VsTextView r1 = r11.mTvServer     // Catch:{ JSONException -> 0x0199 }
            r1.setText(r8)     // Catch:{ JSONException -> 0x0199 }
            com.streamax.view.VsTextView r1 = r11.mTvServer     // Catch:{ JSONException -> 0x0199 }
            r7 = 1
            if (r0 != r7) goto L_0x00ea
            r8 = 1
            goto L_0x00eb
        L_0x00ea:
            r8 = 0
        L_0x00eb:
            r1.setEnabled(r8)     // Catch:{ JSONException -> 0x0199 }
            int r1 = DDNS_TYPE_3322     // Catch:{ JSONException -> 0x0199 }
            r8 = 8
            if (r2 == r1) goto L_0x0110
            int r1 = DDNS_TYPE_DYNDNS     // Catch:{ JSONException -> 0x0199 }
            if (r2 == r1) goto L_0x0110
            int r1 = DDNS_TYPE_DNSEXIT     // Catch:{ JSONException -> 0x0199 }
            if (r2 == r1) goto L_0x0110
            int r1 = DDNS_TYPE_NOIP     // Catch:{ JSONException -> 0x0199 }
            if (r2 == r1) goto L_0x0110
            int r1 = DDNS_TYPE_CHANGEIP     // Catch:{ JSONException -> 0x0199 }
            if (r2 != r1) goto L_0x0105
            goto L_0x0110
        L_0x0105:
            android.widget.LinearLayout r1 = r11.mLlInfo1     // Catch:{ JSONException -> 0x0199 }
            r1.setVisibility(r5)     // Catch:{ JSONException -> 0x0199 }
            android.widget.LinearLayout r1 = r11.mLlInfo2     // Catch:{ JSONException -> 0x0199 }
            r1.setVisibility(r8)     // Catch:{ JSONException -> 0x0199 }
            goto L_0x011a
        L_0x0110:
            android.widget.LinearLayout r1 = r11.mLlInfo1     // Catch:{ JSONException -> 0x0199 }
            r1.setVisibility(r8)     // Catch:{ JSONException -> 0x0199 }
            android.widget.LinearLayout r1 = r11.mLlInfo2     // Catch:{ JSONException -> 0x0199 }
            r1.setVisibility(r5)     // Catch:{ JSONException -> 0x0199 }
        L_0x011a:
            android.widget.Button r1 = r11.mBtnDefault     // Catch:{ JSONException -> 0x0199 }
            if (r0 != r7) goto L_0x0120
            r2 = 1
            goto L_0x0121
        L_0x0120:
            r2 = 0
        L_0x0121:
            r1.setEnabled(r2)     // Catch:{ JSONException -> 0x0199 }
            org.json.JSONObject r1 = r11.mDDNSObj     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r2 = "DE81"
            int r1 = r1.getInt(r2)     // Catch:{ JSONException -> 0x0199 }
            android.widget.Button r2 = r11.mBtnDefault     // Catch:{ JSONException -> 0x0199 }
            if (r1 != 0) goto L_0x0131
            goto L_0x0134
        L_0x0131:
            r3 = 2131231391(0x7f08029f, float:1.8078862E38)
        L_0x0134:
            r2.setBackgroundResource(r3)     // Catch:{ JSONException -> 0x0199 }
            if (r1 != 0) goto L_0x013f
            android.widget.LinearLayout r1 = r11.mLlAddrPort     // Catch:{ JSONException -> 0x0199 }
            r1.setVisibility(r5)     // Catch:{ JSONException -> 0x0199 }
            goto L_0x0144
        L_0x013f:
            android.widget.LinearLayout r1 = r11.mLlAddrPort     // Catch:{ JSONException -> 0x0199 }
            r1.setVisibility(r8)     // Catch:{ JSONException -> 0x0199 }
        L_0x0144:
            if (r0 != 0) goto L_0x0147
            r5 = 1
        L_0x0147:
            com.streamax.view.VsEditView r0 = r11.mEtAddr     // Catch:{ JSONException -> 0x0199 }
            org.json.JSONObject r1 = r11.mDDNSObj     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r2 = "SNAME"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ JSONException -> 0x0199 }
            r11.setTvEnableAndContent(r0, r5, r1)     // Catch:{ JSONException -> 0x0199 }
            com.streamax.view.VsEditView r0 = r11.mEtPort     // Catch:{ JSONException -> 0x0199 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0199 }
            r1.<init>()     // Catch:{ JSONException -> 0x0199 }
            r1.append(r6)     // Catch:{ JSONException -> 0x0199 }
            org.json.JSONObject r2 = r11.mDDNSObj     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r3 = "PORT"
            int r2 = r2.getInt(r3)     // Catch:{ JSONException -> 0x0199 }
            r1.append(r2)     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x0199 }
            r11.setTvEnableAndContent(r0, r5, r1)     // Catch:{ JSONException -> 0x0199 }
            com.streamax.view.VsEditView r0 = r11.mEtHostName     // Catch:{ JSONException -> 0x0199 }
            org.json.JSONObject r1 = r11.mDDNSObj     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r2 = "HOSTNAME"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ JSONException -> 0x0199 }
            r11.setTvEnableAndContent(r0, r5, r1)     // Catch:{ JSONException -> 0x0199 }
            com.streamax.view.VsEditView r0 = r11.mEtUserName     // Catch:{ JSONException -> 0x0199 }
            org.json.JSONObject r1 = r11.mDDNSObj     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r2 = "USERID"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ JSONException -> 0x0199 }
            r11.setTvEnableAndContent(r0, r5, r1)     // Catch:{ JSONException -> 0x0199 }
            com.streamax.view.VsEditView r0 = r11.mEtPassword     // Catch:{ JSONException -> 0x0199 }
            org.json.JSONObject r1 = r11.mDDNSObj     // Catch:{ JSONException -> 0x0199 }
            java.lang.String r2 = "PWD"
            java.lang.String r1 = r1.getString(r2)     // Catch:{ JSONException -> 0x0199 }
            r11.setTvEnableAndContent(r0, r5, r1)     // Catch:{ JSONException -> 0x0199 }
            goto L_0x019c
        L_0x0198:
            return
        L_0x0199:
            r11.showErrorFragment()
        L_0x019c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamax.config.fragment.network.DdnsOfNetwork.refreshUi():void");
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

    public void getSuccess(String str) {
        try {
            this.mDDNSRes = new JSONObject(str);
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

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForServers") && list.size() > 0) {
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
