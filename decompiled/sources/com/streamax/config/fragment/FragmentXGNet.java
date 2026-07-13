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
        int selectedAuthTypeIndex;
        if (this.mXgNetRes != null && this.mListModeName.size() == this.mListModeCase.size() && this.mListAuthTypeName.size() == this.mListAuthTypeCase.size()) {
            try {
                JSONObject networkConfig = this.mXgNetRes.getJSONObject("NWSM");
                if (networkConfig != null) {
                    JSONObject mobileNetConfig = networkConfig.getJSONObject("MOBILENET");
                    this.mMobileNetObj = mobileNetConfig;
                    if (mobileNetConfig != null) {
                        int switchValue = mobileNetConfig.getInt("SWITCH");
                        String modeValue = this.mMobileNetObj.getString("MODE");
                        String apnValue = this.mMobileNetObj.getString("APN");
                        String centerNumber = this.mMobileNetObj.getString("CENTERNUM");
                        String userName = this.mMobileNetObj.getString("USER");
                        String passwordValue = this.mMobileNetObj.getString(Intents.WifiConnect.PASSWORD);
                        String authTypeValue = this.mMobileNetObj.getString("AUTHTYPE");
                        this.mBtnEnableStatus.setBackgroundResource(switchValue == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                        int authTypeIndex = 0;
                        boolean authTypeEnabled = true;
                        this.mTvMode.SetEnabled(switchValue == 1);
                        this.mListStrMode.clear();
                        this.mListIntMode.clear();
                        int modeIndex = 0;
                        while (true) {
                            selectedAuthTypeIndex = -1;
                            if (modeIndex >= this.mListModeCase.size()) {
                                modeIndex = -1;
                                break;
                            } else if (modeValue.equalsIgnoreCase(this.mListModeCase.get(modeIndex))) {
                                break;
                            } else {
                                modeIndex++;
                            }
                        }
                        if (modeIndex >= 0) {
                            this.mTvMode.setText(this.mListModeName.get(modeIndex));
                            this.mListStrMode.addAll(this.mListModeName);
                            this.mListIntMode.add(new Integer(modeIndex));
                        }
                        int editDisabledFlag = switchValue == 0 ? 1 : 0;
                        setTvEnableAndContent(this.mEtApn, editDisabledFlag, apnValue);
                        setTvEnableAndContent(this.mEtCenterNum, editDisabledFlag, centerNumber);
                        setTvEnableAndContent(this.mEtUser, editDisabledFlag, userName);
                        setTvEnableAndContent(this.mEtPassword, editDisabledFlag, passwordValue);
                        VsTextView vsTextView = this.mTvAuthType;
                        if (switchValue != 1) {
                            authTypeEnabled = false;
                        }
                        vsTextView.SetEnabled(authTypeEnabled);
                        this.mListStrAuthType.clear();
                        this.mListIntAuthType.clear();
                        while (true) {
                            if (authTypeIndex >= this.mListAuthTypeCase.size()) {
                                break;
                            } else if (authTypeValue.equalsIgnoreCase(this.mListAuthTypeCase.get(authTypeIndex))) {
                                selectedAuthTypeIndex = authTypeIndex;
                                break;
                            } else {
                                authTypeIndex++;
                            }
                        }
                        if (selectedAuthTypeIndex >= 0) {
                            this.mTvAuthType.setText(this.mListAuthTypeName.get(selectedAuthTypeIndex));
                            this.mListStrAuthType.addAll(this.mListAuthTypeName);
                            this.mListIntAuthType.add(new Integer(selectedAuthTypeIndex));
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
        JSONObject mobileNetConfig = this.mMobileNetObj;
        if (mobileNetConfig != null) {
            try {
                this.mMobileNetObj.put("SWITCH", mobileNetConfig.getInt("SWITCH") == 0 ? 1 : 0);
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
        JSONObject mobileNetConfig = this.mMobileNetObj;
        if (mobileNetConfig == null) {
            return false;
        }
        try {
            mobileNetConfig.put("APN", getString(this.mEtApn));
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
            JSONObject requestRoot = new JSONObject();
            JSONObject networkConfig = new JSONObject();
            networkConfig.put("MOBILENET", "?");
            requestRoot.put("NWSM", networkConfig);
            return requestRoot.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String jsonResponse) {
        try {
            this.mXgNetRes = new JSONObject(jsonResponse);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public void getFailure() {
        toastSf((int) R.string.config_info_get_failure);
    }

    public String requestForSetConfig() {
        JSONObject xgNetConfig = this.mXgNetRes;
        if (xgNetConfig == null) {
            return "";
        }
        return xgNetConfig.toString();
    }

    public void setSuccess() {
        LogUtils.e("FragmentXGNet", "setSuccess 1");
        refreshUi();
        toastSf((int) R.string.config_info_set_success);
    }

    public void setFailure() {
        toastSf((int) R.string.config_info_set_failure);
    }

    public void updateDateForMode(int modeIndex) {
        if (this.mMobileNetObj != null && modeIndex < this.mListModeCase.size()) {
            try {
                this.mMobileNetObj.put("MODE", this.mListModeCase.get(modeIndex));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForAuthType(int authTypeIndex) {
        if (this.mMobileNetObj != null && authTypeIndex < this.mListAuthTypeCase.size()) {
            try {
                this.mMobileNetObj.put("AUTHTYPE", this.mListAuthTypeCase.get(authTypeIndex));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String fragmentTag, List<Integer> selectedIndexes) {
        if (fragmentTag.equals("SelectFragmentForMode")) {
            if (selectedIndexes.size() > 0) {
                updateDateForMode(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForAuthType") && selectedIndexes.size() > 0) {
            updateDateForAuthType(selectedIndexes.get(0).intValue());
        }
    }
}
