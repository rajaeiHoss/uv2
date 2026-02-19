package com.streamax.config.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.Zxing.decoding.Intents;
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
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentXGNet extends ConfigFragment implements BaseListener.SetListener, BaseListener.GetListener {
    public Button mBtnEnableStatus;
    public VsEditView mEtApn;
    public VsEditView mEtCenterNum;
    public VsEditView mEtPassword;
    public VsEditView mEtUser;
    public ArrayList<String> mListAuthTypeCase = new ArrayList<>();
    public ArrayList<String> mListAuthTypeName = new ArrayList<>();
    public List<Integer> mListIntAuthType = new ArrayList();
    public List<Integer> mListIntMode = new ArrayList();
    public ArrayList<String> mListModeCase = new ArrayList<>();
    public ArrayList<String> mListModeName = new ArrayList<>();
    public ArrayList<String> mListStrAuthType = new ArrayList<>();
    public ArrayList<String> mListStrMode = new ArrayList<>();
    public JSONObject mMobileNetObj;
    public RelativeLayout mRlAuthType;
    public RelativeLayout mRlMode;
    public VsTextView mTvAuthType;
    public VsTextView mTvMode;
    public JSONObject mXgNetRes;

    public BaseFragment getCurFragment() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mListModeName.addAll(getStrDatas(R.array.config_xgNet_ModeName));
        this.mListModeCase.addAll(getStrDatas(R.array.config_xgNet_ModeCase));
        this.mListAuthTypeName.addAll(getStrDatas(R.array.config_xgNet_AuthTypeName));
        this.mListAuthTypeCase.addAll(getStrDatas(R.array.config_xgNet_AuthTypeCase));
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_xgNet_Title);
        this.mBtnBack.setVisibility(0);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_xg_net, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    public void initChildView() {
        this.mBtnEnableStatus = (Button) this.mRootView.findViewById(R.id.config_xgNet_btn_enable);
        this.mRlMode = (RelativeLayout) this.mRootView.findViewById(R.id.config_xgNet_rl_mode);
        this.mTvMode = (VsTextView) this.mRootView.findViewById(R.id.config_xgNet_tv_mode);
        this.mEtApn = (VsEditView) this.mRootView.findViewById(R.id.config_xgNet_et_apn);
        this.mEtCenterNum = (VsEditView) this.mRootView.findViewById(R.id.config_xgNet_et_centerNum);
        this.mEtUser = (VsEditView) this.mRootView.findViewById(R.id.config_xgNet_et_user);
        this.mEtPassword = (VsEditView) this.mRootView.findViewById(R.id.config_xgNet_et_password);
        this.mRlAuthType = (RelativeLayout) this.mRootView.findViewById(R.id.config_xgNet_rl_authType);
        this.mTvAuthType = (VsTextView) this.mRootView.findViewById(R.id.config_xgNet_tv_authType);
        this.mBtnSave = (Button) this.mRootView.findViewById(R.id.config_xgNet_btn_save);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getSuccess("{\n\t\"NWSM\":\n\t{\n\t\t\"MOBILENET\":\n\t\t{\n\t\t\t\"SWITCH\" : 1,            \n\t\t\t\"MODE\" : \"Hybrid\", \n\t\t\t\"APN\": \"Ctnet\",\n\t\t\t\"CENTERNUM\": \"*99#\",\n\t\t\t\"USER\": \"12\",\n\t\t\t\"PASSWORD\": \"35\",\n\t\t\t\"AUTHTYPE\": \"None\"\n\t\t}\n\t}\n}");
        getConfig();
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void refreshUi() {
        int i;
        if (this.mXgNetRes != null && this.mListModeName.size() == this.mListModeCase.size() && this.mListAuthTypeName.size() == this.mListAuthTypeCase.size()) {
            try {
                JSONObject jSONObject = this.mXgNetRes.getJSONObject("NWSM");
                if (jSONObject != null) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("MOBILENET");
                    this.mMobileNetObj = jSONObject2;
                    if (jSONObject2 != null) {
                        int i2 = jSONObject2.getInt("SWITCH");
                        String string = this.mMobileNetObj.getString("MODE");
                        String string2 = this.mMobileNetObj.getString("APN");
                        String string3 = this.mMobileNetObj.getString("CENTERNUM");
                        String string4 = this.mMobileNetObj.getString("USER");
                        String string5 = this.mMobileNetObj.getString(Intents.WifiConnect.PASSWORD);
                        String string6 = this.mMobileNetObj.getString("AUTHTYPE");
                        this.mBtnEnableStatus.setBackgroundResource(i2 == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                        int i3 = 0;
                        boolean z = true;
                        this.mTvMode.SetEnabled(i2 == 1);
                        this.mListStrMode.clear();
                        this.mListIntMode.clear();
                        int i4 = 0;
                        while (true) {
                            i = -1;
                            if (i4 >= this.mListModeCase.size()) {
                                i4 = -1;
                                break;
                            } else if (string.equalsIgnoreCase(this.mListModeCase.get(i4))) {
                                break;
                            } else {
                                i4++;
                            }
                        }
                        if (i4 >= 0) {
                            this.mTvMode.setText(this.mListModeName.get(i4));
                            this.mListStrMode.addAll(this.mListModeName);
                            this.mListIntMode.add(new Integer(i4));
                        }
                        int i5 = i2 == 0 ? 1 : 0;
                        setTvEnableAndContent(this.mEtApn, i5, string2);
                        setTvEnableAndContent(this.mEtCenterNum, i5, string3);
                        setTvEnableAndContent(this.mEtUser, i5, string4);
                        setTvEnableAndContent(this.mEtPassword, i5, string5);
                        VsTextView vsTextView = this.mTvAuthType;
                        if (i2 != 1) {
                            z = false;
                        }
                        vsTextView.SetEnabled(z);
                        this.mListStrAuthType.clear();
                        this.mListIntAuthType.clear();
                        while (true) {
                            if (i3 >= this.mListAuthTypeCase.size()) {
                                break;
                            } else if (string6.equalsIgnoreCase(this.mListAuthTypeCase.get(i3))) {
                                i = i3;
                                break;
                            } else {
                                i3++;
                            }
                        }
                        if (i >= 0) {
                            this.mTvAuthType.setText(this.mListAuthTypeName.get(i));
                            this.mListStrAuthType.addAll(this.mListAuthTypeName);
                            this.mListIntAuthType.add(new Integer(i));
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnEnableStatus.setOnClickListener(this);
        this.mTvMode.setOnClickListener(this);
        this.mTvAuthType.setOnClickListener(this);
        this.mBtnSave.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.config_title_btn_back /*2131362205*/:
                prePage();
                return;
            case R.id.config_xgNet_btn_enable /*2131362217*/:
                setEnableBtnStatus();
                return;
            case R.id.config_xgNet_btn_save /*2131362218*/:
                saveData();
                return;
            case R.id.config_xgNet_tv_authType /*2131362225*/:
                pushFragmentForAuthType();
                return;
            case R.id.config_xgNet_tv_mode /*2131362226*/:
                pushFragmentForMode();
                return;
            default:
                return;
        }
    }

    public void prePage() {
        this.mBtnBack.setVisibility(8);
        super.prePage();
    }

    public void setEnableBtnStatus() {
        JSONObject jSONObject = this.mMobileNetObj;
        if (jSONObject != null) {
            try {
                this.mMobileNetObj.put("SWITCH", jSONObject.getInt("SWITCH") == 0 ? 1 : 0);
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForMode() {
        if (this.mMobileNetObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_xgNet_Mode), "SelectFragmentForMode", 0, this.mListStrMode, this.mListIntMode);
        }
    }

    public void pushFragmentForAuthType() {
        if (this.mMobileNetObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_xgNet_AuthType), "SelectFragmentForAuthType", 0, this.mListStrAuthType, this.mListIntAuthType);
        }
    }

    public boolean saveUi() {
        JSONObject jSONObject = this.mMobileNetObj;
        if (jSONObject == null) {
            return false;
        }
        try {
            jSONObject.put("APN", getString(this.mEtApn));
            this.mMobileNetObj.put("CENTERNUM", getString(this.mEtCenterNum));
            this.mMobileNetObj.put("USER", getString(this.mEtUser));
            this.mMobileNetObj.put(Intents.WifiConnect.PASSWORD, getString(this.mEtPassword));
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
            jSONObject2.put("MOBILENET", "?");
            jSONObject.put("NWSM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String str) {
        try {
            this.mXgNetRes = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public void getFailure() {
        toastSf((int) R.string.config_info_get_failure);
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mXgNetRes;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        LogUtils.e("FragmentXGNet", "setSuccess 1");
        refreshUi();
        toastSf((int) R.string.config_info_set_success);
    }

    public void setFailure() {
        toastSf((int) R.string.config_info_set_failure);
    }

    public void updateDateForMode(int i) {
        if (this.mMobileNetObj != null && i < this.mListModeCase.size()) {
            try {
                this.mMobileNetObj.put("MODE", this.mListModeCase.get(i));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForAuthType(int i) {
        if (this.mMobileNetObj != null && i < this.mListAuthTypeCase.size()) {
            try {
                this.mMobileNetObj.put("AUTHTYPE", this.mListAuthTypeCase.get(i));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForMode")) {
            if (list.size() > 0) {
                updateDateForMode(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForAuthType") && list.size() > 0) {
            updateDateForAuthType(list.get(0).intValue());
        }
    }
}
