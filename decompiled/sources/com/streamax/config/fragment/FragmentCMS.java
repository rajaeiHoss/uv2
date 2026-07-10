package com.streamax.config.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.UiUtils;
import com.streamax.view.VsEditView;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentCMS extends ConfigFragment implements BaseListener.SetListener, BaseListener.GetListener {
    public JSONArray mCmsObj;
    public JSONObject mCmsRes;
    public VsEditView mEtServerHost1;
    public VsEditView mEtServerHost2;
    public VsEditView mEtServerPort1;
    public VsEditView mEtServerPort2;
    public List<Integer> mListIntProtocol1 = new ArrayList();
    public List<Integer> mListIntProtocol2 = new ArrayList();
    public List<Integer> mListIntVisitType1 = new ArrayList();
    public List<Integer> mListIntVisitType2 = new ArrayList();
    public ArrayList<String> mListProtocolCase = new ArrayList<>();
    public ArrayList<String> mListProtocolName = new ArrayList<>();
    public ArrayList<String> mListStrProtocol1 = new ArrayList<>();
    public ArrayList<String> mListStrProtocol2 = new ArrayList<>();
    public ArrayList<String> mListStrVisitType1 = new ArrayList<>();
    public ArrayList<String> mListStrVisitType2 = new ArrayList<>();
    public ArrayList<String> mListVisitTypeCase = new ArrayList<>();
    public ArrayList<String> mListVisitTypeName = new ArrayList<>();
    public RelativeLayout mRlProtocol1;
    public RelativeLayout mRlProtocol2;
    public RelativeLayout mRlVisitType1;
    public RelativeLayout mRlVisitType2;
    public TextView mTvProtocol1;
    public TextView mTvProtocol2;
    public TextView mTvVisitType1;
    public TextView mTvVisitType2;

    public BaseFragment getCurFragment() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mListProtocolName.addAll(getStrDatas(R.array.config_cms_ProtocolName));
        this.mListProtocolCase.addAll(getStrDatas(R.array.config_cms_ProtocolCase));
        this.mListVisitTypeName.addAll(getStrDatas(R.array.config_cms_VisitTypeName));
        this.mListVisitTypeCase.addAll(getStrDatas(R.array.config_cms_VisitTypeCase));
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_cms_Title);
        this.mBtnBack.setVisibility(0);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_cms, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    public void initChildView() {
        this.mRlProtocol1 = (RelativeLayout) this.mRootView.findViewById(R.id.config_cms_rl_protocol1);
        this.mTvProtocol1 = (TextView) this.mRootView.findViewById(R.id.config_cms_tv_protocol1);
        this.mRlVisitType1 = (RelativeLayout) this.mRootView.findViewById(R.id.config_cms_rl_visitType1);
        this.mTvVisitType1 = (TextView) this.mRootView.findViewById(R.id.config_cms_tv_visitType1);
        this.mEtServerHost1 = (VsEditView) this.mRootView.findViewById(R.id.config_cms_et_serverHost1);
        this.mEtServerPort1 = (VsEditView) this.mRootView.findViewById(R.id.config_cms_et_serverPort1);
        this.mRlProtocol2 = (RelativeLayout) this.mRootView.findViewById(R.id.config_cms_rl_protocol2);
        this.mTvProtocol2 = (TextView) this.mRootView.findViewById(R.id.config_cms_tv_protocol2);
        this.mRlVisitType2 = (RelativeLayout) this.mRootView.findViewById(R.id.config_cms_rl_visitType2);
        this.mTvVisitType2 = (TextView) this.mRootView.findViewById(R.id.config_cms_tv_visitType2);
        this.mEtServerHost2 = (VsEditView) this.mRootView.findViewById(R.id.config_cms_et_serverHost2);
        this.mEtServerPort2 = (VsEditView) this.mRootView.findViewById(R.id.config_cms_et_serverPort2);
        this.mBtnSave = (Button) this.mRootView.findViewById(R.id.config_cms_btn_save);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getSuccess("{\n\t\"NWSM\" :\n\t{\n\t\t\"MCMS\" : \n\t\t[\n\t\t\t{\n\t\t\t\t\t\t\"PROTOCOL\":\"TTX_Private\",\n\t\t\t\t\t\t\"VISITTYPE\":\"IP\",\n\t\t\t\t\t\t\"SERVER\":\"120.079.058.001\",\n\t\t\t\t\t\t\"PORT\":6608\n\t\t\t},\n\t\t\t{\n\t\t\t\t\t\t\"PROTOCOL\":\"TTX_Private\",\n\t\t\t\t\t\t\"VISITTYPE\":\"IP\",\n\t\t\t\t\t\t\"SERVER\":\"\",\n\t\t\t\t\t\t\"PORT\":6608\n\t\t\t}\n\t\t]\n\t}\n}");
        getConfig();
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void refreshUi() {
        JSONObject cmsResponse = this.mCmsRes;
        if (cmsResponse != null) {
            try {
                JSONObject networkConfig = cmsResponse.getJSONObject("NWSM");
                if (networkConfig != null) {
                    JSONArray cmsConfigs = networkConfig.getJSONArray("MCMS");
                    this.mCmsObj = cmsConfigs;
                    if (cmsConfigs != null) {
                        if (cmsConfigs.length() >= 1) {
                            JSONObject primaryCms = this.mCmsObj.getJSONObject(0);
                            String protocol = primaryCms.getString("PROTOCOL");
                            String visitType = primaryCms.getString("VISITTYPE");
                            String server = primaryCms.getString("SERVER");
                            int port = primaryCms.getInt("PORT");
                            this.mListStrProtocol1.clear();
                            this.mListIntProtocol1.clear();
                            int protocolIndex = findCaseIndex(protocol, this.mListProtocolCase);
                            if (protocolIndex >= 0) {
                                this.mTvProtocol1.setText(this.mListProtocolName.get(protocolIndex));
                                this.mListStrProtocol1.addAll(this.mListProtocolName);
                                this.mListIntProtocol1.add(new Integer(protocolIndex));
                            }
                            this.mListStrVisitType1.clear();
                            this.mListIntVisitType1.clear();
                            int visitTypeIndex = findCaseIndex(visitType, this.mListVisitTypeCase);
                            if (visitTypeIndex >= 0) {
                                this.mTvVisitType1.setText(this.mListVisitTypeName.get(visitTypeIndex));
                                this.mListStrVisitType1.addAll(this.mListVisitTypeName);
                                this.mListIntVisitType1.add(new Integer(visitTypeIndex));
                            }
                            int enableFlag = protocolIndex <= 0 ? 1 : 0;
                            this.mTvVisitType1.setEnabled(protocolIndex > 0);
                            setTvEnableAndContent(this.mEtServerHost1, enableFlag, server);
                            VsEditView vsEditView = this.mEtServerPort1;
                            setTvEnableAndContent(vsEditView, enableFlag, "" + port);
                        }
                        if (this.mCmsObj.length() >= 2) {
                            JSONObject secondaryCms = this.mCmsObj.getJSONObject(1);
                            String protocol = secondaryCms.getString("PROTOCOL");
                            String visitType = secondaryCms.getString("VISITTYPE");
                            String server = secondaryCms.getString("SERVER");
                            int port = secondaryCms.getInt("PORT");
                            this.mListStrProtocol2.clear();
                            this.mListIntProtocol2.clear();
                            int protocolIndex = findCaseIndex(protocol, this.mListProtocolCase);
                            if (protocolIndex >= 0) {
                                this.mTvProtocol2.setText(this.mListProtocolName.get(protocolIndex));
                                this.mListStrProtocol2.addAll(this.mListProtocolName);
                                this.mListIntProtocol2.add(new Integer(protocolIndex));
                            }
                            this.mListStrVisitType2.clear();
                            this.mListIntVisitType2.clear();
                            int visitTypeIndex = findCaseIndex(visitType, this.mListVisitTypeCase);
                            if (visitTypeIndex >= 0) {
                                this.mTvVisitType2.setText(this.mListVisitTypeName.get(visitTypeIndex));
                                this.mListStrVisitType2.addAll(this.mListVisitTypeName);
                                this.mListIntVisitType2.add(new Integer(visitTypeIndex));
                            }
                            int enableFlag = protocolIndex <= 0 ? 1 : 0;
                            TextView textView = this.mTvVisitType2;
                            textView.setEnabled(protocolIndex > 0);
                            setTvEnableAndContent(this.mEtServerHost2, enableFlag, server);
                            VsEditView vsEditView2 = this.mEtServerPort2;
                            setTvEnableAndContent(vsEditView2, enableFlag, "" + port);
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    private int findCaseIndex(String value, ArrayList<String> cases) {
        for (int caseIndex = 0; caseIndex < cases.size(); caseIndex++) {
            if (value.equalsIgnoreCase(cases.get(caseIndex))) {
                return caseIndex;
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mTvProtocol1.setOnClickListener(this);
        this.mTvVisitType1.setOnClickListener(this);
        this.mTvProtocol2.setOnClickListener(this);
        this.mTvVisitType2.setOnClickListener(this);
        this.mBtnSave.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.config_cms_btn_save) {
            saveData();
        } else if (id != R.id.config_title_btn_back) {
            switch (id) {
                case R.id.config_cms_tv_protocol1 /*2131362075*/:
                    pushFragmentForProtocol(1);
                    return;
                case R.id.config_cms_tv_protocol2 /*2131362076*/:
                    pushFragmentForProtocol(2);
                    return;
                case R.id.config_cms_tv_visitType1 /*2131362077*/:
                    pushFragmentForVisitType(1);
                    return;
                case R.id.config_cms_tv_visitType2 /*2131362078*/:
                    pushFragmentForVisitType(2);
                    return;
                default:
                    return;
            }
        } else {
            prePage();
        }
    }

    public void prePage() {
        this.mBtnBack.setVisibility(8);
        super.prePage();
    }

    public void pushFragmentForProtocol(int i) {
        if (this.mCmsObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_cms_Protocol), i == 1 ? "SelectFragmentForProtocol1" : "SelectFragmentForProtocol2", 0, i == 1 ? this.mListStrProtocol1 : this.mListStrProtocol2, i == 1 ? this.mListIntProtocol1 : this.mListIntProtocol2);
        }
    }

    public void pushFragmentForVisitType(int i) {
        if (this.mCmsObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_cms_VisitType), i == 1 ? "SelectFragmentForVisitType1" : "SelectFragmentForVisitType2", 0, i == 1 ? this.mListStrVisitType1 : this.mListStrVisitType2, i == 1 ? this.mListIntVisitType1 : this.mListIntVisitType2);
        }
    }

    public boolean saveUi() {
        JSONArray jSONArray = this.mCmsObj;
        if (jSONArray == null) {
            return false;
        }
        try {
            if (jSONArray.length() >= 1) {
                int intValue = parse2Int(this.mEtServerPort1).intValue();
                if (intValue > 0) {
                    if (intValue <= 65535) {
                        JSONObject jSONObject = this.mCmsObj.getJSONObject(0);
                        jSONObject.put("SERVER", getString(this.mEtServerHost1));
                        jSONObject.put("PORT", Integer.parseInt(getString(this.mEtServerPort1)));
                    }
                }
                return false;
            }
            if (this.mCmsObj.length() >= 2) {
                int intValue2 = parse2Int(this.mEtServerPort2).intValue();
                if (intValue2 > 0) {
                    if (intValue2 <= 65535) {
                        JSONObject jSONObject2 = this.mCmsObj.getJSONObject(1);
                        jSONObject2.put("SERVER", getString(this.mEtServerHost2));
                        jSONObject2.put("PORT", Integer.parseInt(getString(this.mEtServerPort2)));
                    }
                }
                return false;
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
            networkConfig.put("MCMS", "?");
            request.put("NWSM", networkConfig);
            return request.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String response) {
        try {
            this.mCmsRes = new JSONObject(response);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public void getFailure() {
        toastSf((int) R.string.config_info_get_failure);
    }

    public String requestForSetConfig() {
        JSONObject cmsResponse = this.mCmsRes;
        if (cmsResponse == null) {
            return "";
        }
        return cmsResponse.toString();
    }

    public void setSuccess() {
        LogUtils.e("DateTimeFragment", "setSuccess 1");
        refreshUi();
        toastSf((int) R.string.config_info_set_success);
    }

    public void setFailure() {
        toastSf((int) R.string.config_info_set_failure);
    }

    public void updateDateForProtocol(int cmsIndex, int selectedIndex) {
        JSONArray cmsConfigs = this.mCmsObj;
        if (cmsConfigs != null && cmsIndex < cmsConfigs.length() && selectedIndex < this.mListProtocolCase.size()) {
            try {
                this.mCmsObj.getJSONObject(cmsIndex).put("PROTOCOL", this.mListProtocolCase.get(selectedIndex));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForVisitType(int cmsIndex, int selectedIndex) {
        JSONArray cmsConfigs = this.mCmsObj;
        if (cmsConfigs != null && cmsIndex < cmsConfigs.length() && selectedIndex < this.mListVisitTypeCase.size()) {
            try {
                this.mCmsObj.getJSONObject(cmsIndex).put("VISITTYPE", this.mListVisitTypeCase.get(selectedIndex));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String fragmentTag, List<Integer> selectedIndexes) {
        if (fragmentTag.equals("SelectFragmentForProtocol1") && selectedIndexes.size() > 0) {
            updateDateForProtocol(0, selectedIndexes.get(0).intValue());
        }
        if (fragmentTag.equals("SelectFragmentForProtocol2")) {
            if (selectedIndexes.size() > 0) {
                updateDateForProtocol(1, selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForVisitType1")) {
            if (selectedIndexes.size() > 0) {
                updateDateForVisitType(0, selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForVisitType2") && selectedIndexes.size() > 0) {
            updateDateForVisitType(1, selectedIndexes.get(0).intValue());
        }
    }
}
