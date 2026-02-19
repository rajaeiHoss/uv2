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
        JSONObject jSONObject = this.mCmsRes;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("NWSM");
                if (jSONObject2 != null) {
                    JSONArray jSONArray = jSONObject2.getJSONArray("MCMS");
                    this.mCmsObj = jSONArray;
                    if (jSONArray != null) {
                        int i = -1;
                        boolean z = false;
                        if (jSONArray.length() >= 1) {
                            JSONObject jSONObject3 = this.mCmsObj.getJSONObject(0);
                            String string = jSONObject3.getString("PROTOCOL");
                            String string2 = jSONObject3.getString("VISITTYPE");
                            String string3 = jSONObject3.getString("SERVER");
                            int i2 = jSONObject3.getInt("PORT");
                            this.mListStrProtocol1.clear();
                            this.mListIntProtocol1.clear();
                            int i3 = 0;
                            while (true) {
                                if (i3 >= this.mListProtocolCase.size()) {
                                    i3 = -1;
                                    break;
                                } else if (string.equalsIgnoreCase(this.mListProtocolCase.get(i3))) {
                                    break;
                                } else {
                                    i3++;
                                }
                            }
                            if (i3 >= 0) {
                                this.mTvProtocol1.setText(this.mListProtocolName.get(i3));
                                this.mListStrProtocol1.addAll(this.mListProtocolName);
                                this.mListIntProtocol1.add(new Integer(i3));
                            }
                            this.mListStrVisitType1.clear();
                            this.mListIntVisitType1.clear();
                            int i4 = 0;
                            while (true) {
                                if (i4 >= this.mListVisitTypeCase.size()) {
                                    i4 = -1;
                                    break;
                                } else if (string2.equalsIgnoreCase(this.mListVisitTypeCase.get(i4))) {
                                    break;
                                } else {
                                    i4++;
                                }
                            }
                            if (i4 >= 0) {
                                this.mTvVisitType1.setText(this.mListVisitTypeName.get(i4));
                                this.mListStrVisitType1.addAll(this.mListVisitTypeName);
                                this.mListIntVisitType1.add(new Integer(i4));
                            }
                            int i5 = i3 <= 0 ? 1 : 0;
                            this.mTvVisitType1.setEnabled(i3 > 0);
                            setTvEnableAndContent(this.mEtServerHost1, i5, string3);
                            VsEditView vsEditView = this.mEtServerPort1;
                            setTvEnableAndContent(vsEditView, i5, "" + i2);
                        }
                        if (this.mCmsObj.length() >= 2) {
                            JSONObject jSONObject4 = this.mCmsObj.getJSONObject(1);
                            String string4 = jSONObject4.getString("PROTOCOL");
                            String string5 = jSONObject4.getString("VISITTYPE");
                            String string6 = jSONObject4.getString("SERVER");
                            int i6 = jSONObject4.getInt("PORT");
                            this.mListStrProtocol2.clear();
                            this.mListIntProtocol2.clear();
                            int i7 = 0;
                            while (true) {
                                if (i7 >= this.mListProtocolCase.size()) {
                                    i7 = -1;
                                    break;
                                } else if (string4.equalsIgnoreCase(this.mListProtocolCase.get(i7))) {
                                    break;
                                } else {
                                    i7++;
                                }
                            }
                            if (i7 >= 0) {
                                this.mTvProtocol2.setText(this.mListProtocolName.get(i7));
                                this.mListStrProtocol2.addAll(this.mListProtocolName);
                                this.mListIntProtocol2.add(new Integer(i7));
                            }
                            this.mListStrVisitType2.clear();
                            this.mListIntVisitType2.clear();
                            int i8 = 0;
                            while (true) {
                                if (i8 >= this.mListVisitTypeCase.size()) {
                                    break;
                                } else if (string5.equalsIgnoreCase(this.mListVisitTypeCase.get(i8))) {
                                    i = i8;
                                    break;
                                } else {
                                    i8++;
                                }
                            }
                            if (i >= 0) {
                                this.mTvVisitType2.setText(this.mListVisitTypeName.get(i));
                                this.mListStrVisitType2.addAll(this.mListVisitTypeName);
                                this.mListIntVisitType2.add(new Integer(i));
                            }
                            int i9 = i7 <= 0 ? 1 : 0;
                            TextView textView = this.mTvVisitType2;
                            if (i7 > 0) {
                                z = true;
                            }
                            textView.setEnabled(z);
                            setTvEnableAndContent(this.mEtServerHost2, i9, string6);
                            VsEditView vsEditView2 = this.mEtServerPort2;
                            setTvEnableAndContent(vsEditView2, i9, "" + i6);
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
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
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("MCMS", "?");
            jSONObject.put("NWSM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String str) {
        try {
            this.mCmsRes = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public void getFailure() {
        toastSf((int) R.string.config_info_get_failure);
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mCmsRes;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        LogUtils.e("DateTimeFragment", "setSuccess 1");
        refreshUi();
        toastSf((int) R.string.config_info_set_success);
    }

    public void setFailure() {
        toastSf((int) R.string.config_info_set_failure);
    }

    public void updateDateForProtocol(int i, int i2) {
        JSONArray jSONArray = this.mCmsObj;
        if (jSONArray != null && i < jSONArray.length() && i2 < this.mListProtocolCase.size()) {
            try {
                this.mCmsObj.getJSONObject(i).put("PROTOCOL", this.mListProtocolCase.get(i2));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForVisitType(int i, int i2) {
        JSONArray jSONArray = this.mCmsObj;
        if (jSONArray != null && i < jSONArray.length() && i2 < this.mListVisitTypeCase.size()) {
            try {
                this.mCmsObj.getJSONObject(i).put("VISITTYPE", this.mListVisitTypeCase.get(i2));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForProtocol1") && list.size() > 0) {
            updateDateForProtocol(0, list.get(0).intValue());
        }
        if (str.equals("SelectFragmentForProtocol2")) {
            if (list.size() > 0) {
                updateDateForProtocol(1, list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForVisitType1")) {
            if (list.size() > 0) {
                updateDateForVisitType(0, list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForVisitType2") && list.size() > 0) {
            updateDateForVisitType(1, list.get(0).intValue());
        }
    }
}
