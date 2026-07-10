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
        if (this.mEmailRes != null) {
            LogUtils.e("EmailOfNetwork", "setSuccess 2, mEmailRes: " + this.mEmailRes.toString());
            try {
                JSONObject networkConfig = this.mEmailRes.getJSONObject("NWSM");
                if (networkConfig == null) {
                    return;
                }
                this.mEmailObj = networkConfig.getJSONObject("EMAIL");
                if (this.mEmailObj != null) {
                    int emailSwitch = this.mEmailObj.getInt("EMAILSWITCH");
                    int sendInterval = this.mEmailObj.getInt("SENDINTERVAL");
                    String smtpServer = this.mEmailObj.getString("SMTPSERVER");
                    int smtpPort = this.mEmailObj.getInt("SSERVERPORT");
                    int sslSwitch = this.mEmailObj.getInt("SSLSWITCH");
                    String senderName = this.mEmailObj.getString("SENDERNAME");
                    String senderPassword = this.mEmailObj.getString("SENDERPWD");
                    JSONArray recipients = this.mEmailObj.getJSONArray("RECVLIST");
                    String primaryRecipient = getRecipient(recipients, 0);
                    String copyRecipient1 = getRecipient(recipients, 1);
                    String copyRecipient2 = getRecipient(recipients, 2);
                    String copyRecipient3 = getRecipient(recipients, 3);
                    String copyRecipient4 = getRecipient(recipients, 4);
                    this.mBtnEmail.setBackgroundResource(emailSwitch == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                    int editEnableFlag = emailSwitch == 0 ? 1 : 0;
                    this.mTvSstg.SetEnabled(emailSwitch == 1);
                    this.mListStrInterval.clear();
                    this.mListIntInterval.clear();
                    List<String> intervalLabels = getStrDatas(R.array.config_network_email_interval_selector);
                    int intervalIndex = getIntervalIndex(sendInterval, intervalLabels.size() - 1);
                    if (intervalIndex >= 0 && intervalIndex < intervalLabels.size()) {
                        this.mTvSstg.setText(intervalLabels.get(intervalIndex));
                        this.mListStrInterval.addAll(intervalLabels);
                        this.mListIntInterval.add(Integer.valueOf(intervalIndex));
                    }
                    setTvEnableAndContent(this.mEtSmtp, editEnableFlag, smtpServer);
                    setTvEnableAndContent(this.mEtPort, editEnableFlag, "" + smtpPort);
                    this.mTvSSL.SetEnabled(emailSwitch == 1);
                    this.mListStrSSL.clear();
                    this.mListIntSSL.clear();
                    List<String> sslLabels = getStrDatas(R.array.config_network_email_ssl_selector);
                    if (sslSwitch >= 0 && sslSwitch < sslLabels.size()) {
                        this.mTvSSL.setText(sslLabels.get(sslSwitch));
                        this.mListStrSSL.addAll(sslLabels);
                        this.mListIntSSL.add(Integer.valueOf(sslSwitch));
                    }
                    setTvEnableAndContent(this.mEtName, editEnableFlag, senderName);
                    setTvEnableAndContent(this.mEtPwd, editEnableFlag, senderPassword);
                    setTvEnableAndContent(this.mEtCc0, editEnableFlag, primaryRecipient);
                    setTvEnableAndContent(this.mEtCc1, editEnableFlag, copyRecipient1);
                    setTvEnableAndContent(this.mEtCc2, editEnableFlag, copyRecipient2);
                    setTvEnableAndContent(this.mEtCc3, editEnableFlag, copyRecipient3);
                    setTvEnableAndContent(this.mEtCc4, editEnableFlag, copyRecipient4);
                }
            } catch (JSONException unused) {
            }
        }
    }

    private String getRecipient(JSONArray recipients, int recipientIndex) throws JSONException {
        if (recipients == null || recipients.length() <= recipientIndex) {
            return "";
        }
        return recipients.getString(recipientIndex);
    }

    private int getIntervalIndex(int sendInterval, int fallbackIndex) {
        if (sendInterval == 0) {
            return 0;
        }
        if (sendInterval > 0 && sendInterval <= 30) {
            return 1;
        }
        if (sendInterval > 30 && sendInterval <= 60) {
            return 2;
        }
        if (sendInterval > 60 && sendInterval <= 180) {
            return 3;
        }
        if (sendInterval > 180 && sendInterval <= Strategy.TTL_SECONDS_DEFAULT) {
            return 4;
        }
        if (sendInterval > Strategy.TTL_SECONDS_DEFAULT && sendInterval <= 600) {
            return 5;
        }
        return fallbackIndex;
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
        JSONObject emailConfig = this.mEmailObj;
        if (emailConfig == null) {
            return false;
        }
        try {
            emailConfig.put("SMTPSERVER", getString(this.mEtSmtp));
            this.mEmailObj.put("SSERVERPORT", Integer.parseInt(getString(this.mEtPort)));
            this.mEmailObj.put("SENDERNAME", getString(this.mEtName));
            this.mEmailObj.put("SENDERPWD", getString(this.mEtPwd));
            JSONArray recipients = this.mEmailObj.getJSONArray("RECVLIST");
            if (recipients != null) {
                recipients.put(0, getString(this.mEtCc0));
                recipients.put(1, getString(this.mEtCc1));
                recipients.put(2, getString(this.mEtCc2));
                recipients.put(3, getString(this.mEtCc3));
                recipients.put(4, getString(this.mEtCc4));
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
            JSONObject request = new JSONObject();
            JSONObject networkConfig = new JSONObject();
            networkConfig.put("EMAIL", "?");
            request.put("NWSM", networkConfig);
            return request.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String response) {
        try {
            this.mEmailRes = new JSONObject(response);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject emailResponse = this.mEmailRes;
        if (emailResponse == null) {
            return "";
        }
        return emailResponse.toString();
    }

    public void setSuccess() {
        LogUtils.e("EmailOfNetwork", "setSuccess 1");
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForInterval(int selectedIndex) {
        JSONObject emailConfig = this.mEmailObj;
        if (emailConfig != null) {
            int sendInterval = 600;
            if (selectedIndex == 0) {
                sendInterval = 0;
            } else if (selectedIndex == 1) {
                sendInterval = 30;
            } else if (selectedIndex == 2) {
                sendInterval = 60;
            } else if (selectedIndex == 3) {
                sendInterval = 180;
            } else if (selectedIndex == 4) {
                sendInterval = Strategy.TTL_SECONDS_DEFAULT;
            }
            try {
                emailConfig.put("SENDINTERVAL", sendInterval);
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForSSL(int selectedIndex) {
        JSONObject emailConfig = this.mEmailObj;
        if (emailConfig != null) {
            try {
                emailConfig.put("SSLSWITCH", selectedIndex);
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String fragmentTag, List<Integer> selectedIndexes) {
        if (fragmentTag.equals("SelectFragmentForInterval")) {
            if (selectedIndexes.size() > 0) {
                updateDateForInterval(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForSSL") && selectedIndexes.size() > 0) {
            updateDateForSSL(selectedIndexes.get(0).intValue());
        }
    }
}
