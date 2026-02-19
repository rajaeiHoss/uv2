package com.streamax.config.fragment.network;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import com.google.android.gms.nearby.messages.Strategy;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.UiUtils;
import com.streamax.view.VsEditView;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EmailOfNetwork extends ConfigFragment implements BaseListener.GetListener, BaseListener.SetListener {
    public Button mBtnEmail;
    private JSONObject mEmailObj;
    private JSONObject mEmailRes;
    public VsEditView mEtCc0;
    public VsEditView mEtCc1;
    public VsEditView mEtCc2;
    public VsEditView mEtCc3;
    public VsEditView mEtCc4;
    public VsEditView mEtName;
    public VsEditView mEtPort;
    public VsEditView mEtPwd;
    public VsEditView mEtSmtp;
    public List<Integer> mListIntInterval = new ArrayList();
    public List<Integer> mListIntSSL = new ArrayList();
    public ArrayList<String> mListStrInterval = new ArrayList<>();
    public ArrayList<String> mListStrSSL = new ArrayList<>();
    public ScrollView mSvContent;
    public VsTextView mTvSSL;
    public VsTextView mTvSstg;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mBtnUpdate.setVisibility(8);
        this.mTvTitle.setText(R.string.config_network_email_Title);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_network_email, (ViewGroup) null);
        this.mSvContent = (ScrollView) this.mRootView.findViewById(R.id.network_email_sv_content);
        this.mBtnEmail = (Button) this.mRootView.findViewById(R.id.network_email_btn_email);
        this.mTvSstg = (VsTextView) this.mRootView.findViewById(R.id.network_email_tv_sstg);
        this.mEtSmtp = (VsEditView) this.mRootView.findViewById(R.id.network_email_et_smtp);
        this.mEtPort = (VsEditView) this.mRootView.findViewById(R.id.network_email_et_port);
        this.mTvSSL = (VsTextView) this.mRootView.findViewById(R.id.network_email_tv_safelink);
        this.mEtName = (VsEditView) this.mRootView.findViewById(R.id.network_email_et_username);
        this.mEtPwd = (VsEditView) this.mRootView.findViewById(R.id.network_email_et_pwd);
        this.mEtCc0 = (VsEditView) this.mRootView.findViewById(R.id.network_email_et_recipient);
        this.mEtCc1 = (VsEditView) this.mRootView.findViewById(R.id.network_email_et_copysend1);
        this.mEtCc2 = (VsEditView) this.mRootView.findViewById(R.id.network_email_et_copysend2);
        this.mEtCc3 = (VsEditView) this.mRootView.findViewById(R.id.network_email_et_copysend3);
        this.mEtCc4 = (VsEditView) this.mRootView.findViewById(R.id.network_email_et_copysend4);
        this.mBtnSave = (Button) this.mRootView.findViewById(R.id.network_email_btn_save);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    public void refreshUi() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        int i;
        String str7;
        if (this.mEmailRes != null) {
            LogUtils.e("EmailOfNetwork", "setSuccess 2, mEmailRes: " + this.mEmailRes.toString());
            try {
                JSONObject jSONObject = this.mEmailRes.getJSONObject("NWSM");
                if (jSONObject != null) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("EMAIL");
                    this.mEmailObj = jSONObject2;
                    if (jSONObject2 != null) {
                        int i2 = jSONObject2.getInt("EMAILSWITCH");
                        int i3 = this.mEmailObj.getInt("SENDINTERVAL");
                        String string = this.mEmailObj.getString("SMTPSERVER");
                        int i4 = this.mEmailObj.getInt("SSERVERPORT");
                        int i5 = this.mEmailObj.getInt("SSLSWITCH");
                        String string2 = this.mEmailObj.getString("SENDERNAME");
                        String string3 = this.mEmailObj.getString("SENDERPWD");
                        JSONArray jSONArray = this.mEmailObj.getJSONArray("RECVLIST");
                        if (jSONArray != null) {
                            if (jSONArray.length() >= 1) {
                                str7 = jSONArray.getString(0);
                            } else {
                                str7 = "";
                            }
                            if (jSONArray.length() >= 2) {
                                str2 = jSONArray.getString(1);
                            } else {
                                str2 = "";
                            }
                            if (jSONArray.length() >= 3) {
                                str = jSONArray.getString(2);
                            } else {
                                str = "";
                            }
                            if (jSONArray.length() >= 4) {
                                str3 = jSONArray.getString(3);
                            } else {
                                str3 = "";
                            }
                            String str8 = str7;
                            if (jSONArray.length() >= 5) {
                                str5 = jSONArray.getString(4);
                            } else {
                                str5 = "";
                            }
                            str4 = str8;
                        } else {
                            str5 = "";
                            str4 = str5;
                            str3 = str4;
                            str2 = str3;
                            str = str2;
                        }
                        this.mBtnEmail.setBackgroundResource(i2 == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                        int i6 = i2 == 0 ? 1 : 0;
                        String str9 = str5;
                        this.mTvSstg.SetEnabled(i2 == 1);
                        this.mListStrInterval.clear();
                        this.mListIntInterval.clear();
                        List<String> strDatas = getStrDatas(R.array.config_network_email_interval_selector);
                        int size = strDatas.size() - 1;
                        if (i3 == 0) {
                            str6 = str3;
                            i = 0;
                        } else {
                            int i7 = size;
                            if (i3 <= 0 || i3 > 30) {
                                str6 = str3;
                                i = (i3 <= 30 || i3 > 60) ? (i3 <= 60 || i3 > 180) ? (i3 <= 180 || i3 > 300) ? (i3 <= 300 || i3 > 600) ? i7 : 5 : 4 : 3 : 2;
                            } else {
                                str6 = str3;
                                i = 1;
                            }
                        }
                        if (i >= 0 && i < strDatas.size()) {
                            this.mTvSstg.setText(strDatas.get(i));
                            this.mListStrInterval.addAll(strDatas);
                            this.mListIntInterval.add(new Integer(i));
                        }
                        setTvEnableAndContent(this.mEtSmtp, i6, string);
                        setTvEnableAndContent(this.mEtPort, i6, "" + i4);
                        VsTextView vsTextView = this.mTvSSL;
                        boolean z = true;
                        if (i2 != 1) {
                            z = false;
                        }
                        vsTextView.SetEnabled(z);
                        this.mListStrSSL.clear();
                        this.mListIntSSL.clear();
                        List<String> strDatas2 = getStrDatas(R.array.config_network_email_ssl_selector);
                        if (i5 >= 0 && i5 < strDatas2.size()) {
                            this.mTvSSL.setText(strDatas2.get(i5));
                            this.mListStrSSL.addAll(strDatas2);
                            this.mListIntSSL.add(new Integer(i5));
                        }
                        setTvEnableAndContent(this.mEtName, i6, string2);
                        setTvEnableAndContent(this.mEtPwd, i6, string3);
                        setTvEnableAndContent(this.mEtCc0, i6, str4);
                        setTvEnableAndContent(this.mEtCc1, i6, str2);
                        setTvEnableAndContent(this.mEtCc2, i6, str);
                        setTvEnableAndContent(this.mEtCc3, i6, str6);
                        setTvEnableAndContent(this.mEtCc4, i6, str9);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnEmail.setOnClickListener(this);
        this.mTvSstg.setOnClickListener(this);
        this.mTvSSL.setOnClickListener(this);
        this.mBtnSave.setOnClickListener(this);
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void pushFragmentForInterval() {
        if (this.mEmailObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_network_email_Sstg), "SelectFragmentForInterval", 0, this.mListStrInterval, this.mListIntInterval);
        }
    }

    public void pushFragmentForSSL() {
        if (this.mEmailObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_network_email_Safelink), "SelectFragmentForSSL", 0, this.mListStrSSL, this.mListIntSSL);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.config_title_btn_back /*2131362205*/:
                prePage();
                return;
            case R.id.network_email_btn_email /*2131362781*/:
                setEmailBtnStatus();
                return;
            case R.id.network_email_btn_save /*2131362782*/:
                saveData();
                return;
            case R.id.network_email_tv_safelink /*2131362793*/:
                pushFragmentForSSL();
                return;
            case R.id.network_email_tv_sstg /*2131362794*/:
                pushFragmentForInterval();
                return;
            default:
                return;
        }
    }

    public void setEmailBtnStatus() {
        JSONObject jSONObject = this.mEmailObj;
        if (jSONObject != null) {
            try {
                this.mEmailObj.put("EMAILSWITCH", jSONObject.getInt("EMAILSWITCH") == 0 ? 1 : 0);
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void setSslBtnStatus() {
        JSONObject jSONObject = this.mEmailObj;
        if (jSONObject != null) {
            try {
                this.mEmailObj.put("SSLSWITCH", jSONObject.getInt("SSLSWITCH") == 0 ? 1 : 0);
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public boolean saveUi() {
        if (getStrLen(this.mEtPort) == 0) {
            toastSf((int) R.string.PortIsError);
            return false;
        }
        JSONObject jSONObject = this.mEmailObj;
        if (jSONObject == null) {
            return false;
        }
        try {
            jSONObject.put("SMTPSERVER", getString(this.mEtSmtp));
            this.mEmailObj.put("SSERVERPORT", Integer.parseInt(getString(this.mEtPort)));
            this.mEmailObj.put("SENDERNAME", getString(this.mEtName));
            this.mEmailObj.put("SENDERPWD", getString(this.mEtPwd));
            JSONArray jSONArray = this.mEmailObj.getJSONArray("RECVLIST");
            if (jSONArray != null) {
                jSONArray.put(0, getString(this.mEtCc0));
                jSONArray.put(1, getString(this.mEtCc1));
                jSONArray.put(2, getString(this.mEtCc2));
                jSONArray.put(3, getString(this.mEtCc3));
                jSONArray.put(4, getString(this.mEtCc4));
            }
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
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("EMAIL", "?");
            jSONObject.put("NWSM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String str) {
        try {
            this.mEmailRes = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mEmailRes;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        LogUtils.e("EmailOfNetwork", "setSuccess 1");
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForInterval(int i) {
        JSONObject jSONObject = this.mEmailObj;
        if (jSONObject != null) {
            int i2 = 600;
            if (i == 0) {
                i2 = 0;
            } else if (i == 1) {
                i2 = 30;
            } else if (i == 2) {
                i2 = 60;
            } else if (i == 3) {
                i2 = 180;
            } else if (i == 4) {
                i2 = Strategy.TTL_SECONDS_DEFAULT;
            }
            try {
                jSONObject.put("SENDINTERVAL", i2);
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForSSL(int i) {
        JSONObject jSONObject = this.mEmailObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("SSLSWITCH", i);
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForInterval")) {
            if (list.size() > 0) {
                updateDateForInterval(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForSSL") && list.size() > 0) {
            updateDateForSSL(list.get(0).intValue());
        }
    }
}
