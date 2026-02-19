package com.streamax.config.fragment.network;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetManager;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.view.VsEditView;
import com.zycs.UView.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PortOfNetwork extends ConfigFragment implements View.OnFocusChangeListener, BaseListener.GetListener, BaseListener.SetListener {
    public static final int MAX = 65535;
    public VsEditView mEtHps;
    public VsEditView mEtMp;
    public VsEditView mEtRp;
    public VsEditView mEtWp;
    private JSONArray mPortArr;
    private JSONObject mPortRes;
    public ScrollView mSvContent;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_network_port, (ViewGroup) null);
        this.mSvContent = (ScrollView) this.mRootView.findViewById(R.id.network_port_sv_content);
        this.mEtWp = (VsEditView) this.mRootView.findViewById(R.id.network_port_et_webport);
        this.mEtMp = (VsEditView) this.mRootView.findViewById(R.id.network_port_et_mediaport);
        this.mEtRp = (VsEditView) this.mRootView.findViewById(R.id.network_port_et_rtspport);
        this.mEtHps = (VsEditView) this.mRootView.findViewById(R.id.network_port_et_httpsport);
        this.mBtnSave = (Button) this.mRootView.findViewById(R.id.network_port_btn_save);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    public void refreshUi() {
        JSONObject jSONObject;
        JSONObject jSONObject2 = this.mPortRes;
        if (jSONObject2 != null) {
            try {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("NWSM");
                if (jSONObject3 != null && (jSONObject = jSONObject3.getJSONObject("PORT")) != null) {
                    JSONArray jSONArray = jSONObject.getJSONArray("PORTLIST");
                    this.mPortArr = jSONArray;
                    if (jSONArray != null) {
                        if (jSONArray.length() >= 1) {
                            VsEditView vsEditView = this.mEtWp;
                            vsEditView.SetText(String.valueOf(this.mPortArr.get(0)));
                        }
                        if (this.mPortArr.length() >= 2) {
                            VsEditView vsEditView2 = this.mEtMp;
                            vsEditView2.SetText(String.valueOf(this.mPortArr.get(1)));
                        }
                        if (this.mPortArr.length() >= 3) {
                            VsEditView vsEditView3 = this.mEtRp;
                            vsEditView3.SetText(String.valueOf(this.mPortArr.get(2)));
                        }
                    }
                }
            } catch (JSONException unused) {
                showErrorFragment();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnSave.setOnClickListener(this);
        this.mEtWp.setOnFocusChangeListener(this);
        this.mEtMp.setOnFocusChangeListener(this);
        this.mEtRp.setOnFocusChangeListener(this);
        this.mEtHps.setOnFocusChangeListener(this);
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mBtnUpdate.setVisibility(8);
        this.mTvTitle.setText(R.string.config_network_port_Title);
        this.mBtnBack.setOnClickListener(this);
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.config_title_btn_back) {
            prePage();
        } else if (id == R.id.network_port_btn_save) {
            saveData();
        }
    }

    public void saveData() {
        int strLen = getStrLen(this.mEtWp);
        int strLen2 = getStrLen(this.mEtMp);
        int strLen3 = getStrLen(this.mEtRp);
        if (strLen == 0 || strLen2 == 0 || strLen3 == 0) {
            toastSf((int) R.string.PortIsError);
            return;
        }
        int intValue = parse2Int(this.mEtWp).intValue();
        int intValue2 = parse2Int(this.mEtMp).intValue();
        int intValue3 = parse2Int(this.mEtRp).intValue();
        if (intValue == intValue2 || intValue == intValue3 || intValue2 == intValue3 || intValue <= 0 || intValue2 <= 0 || intValue3 <= 0) {
            toastSf((int) R.string.PortIsError);
            return;
        }
        JSONArray jSONArray = this.mPortArr;
        if (jSONArray != null) {
            try {
                jSONArray.put(0, parse2Int(this.mEtWp));
                this.mPortArr.put(1, parse2Int(this.mEtMp));
                this.mPortArr.put(2, parse2Int(this.mEtRp));
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void onFocusChange(View view, boolean z) {
        switch (view.getId()) {
            case R.id.network_port_et_httpsport /*2131362815*/:
                setRegion(this.mEtHps, 65535);
                return;
            case R.id.network_port_et_mediaport /*2131362816*/:
                setRegion(this.mEtMp, 65535);
                return;
            case R.id.network_port_et_rtspport /*2131362817*/:
                setRegion(this.mEtRp, 65535);
                return;
            case R.id.network_port_et_webport /*2131362818*/:
                setRegion(this.mEtWp, 65535);
                return;
            default:
                return;
        }
    }

    public void setRegion(final EditText editText, final int i) {
        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (i > 1) {
                    String charSequence2 = charSequence.toString();
                    if (i != -1 && !charSequence2.isEmpty()) {
                        int parseInt = Integer.parseInt(charSequence2);
                        int i4 = i;
                        if (parseInt > i4) {
                            charSequence = String.valueOf(i4);
                            editText.setText(charSequence);
                        }
                        editText.setSelection(charSequence.length());
                    }
                }
            }

            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.equals("") && i != -1) {
                    int value;
                    try {
                        value = Integer.parseInt(editable.toString());
                    } catch (NumberFormatException unused) {
                        value = 0;
                    }
                    int max = i;
                    if (value > max) {
                        editText.setText(String.valueOf(max));
                        editText.setSelection(String.valueOf(max).length());
                    }
                }
            }
        });
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("PORT", "?");
            jSONObject.put("NWSM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String str) {
        try {
            this.mPortRes = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mPortRes;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        LogUtils.e("PortOfNetwork", "setSuccess 1");
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
