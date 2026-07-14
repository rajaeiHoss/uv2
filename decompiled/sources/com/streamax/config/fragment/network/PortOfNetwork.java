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
        JSONObject portConfig;
        JSONObject portResponse = this.mPortRes;
        if (portResponse != null) {
            try {
                JSONObject networkSettings = portResponse.getJSONObject("NWSM");
                if (networkSettings != null && (portConfig = networkSettings.getJSONObject("PORT")) != null) {
                    JSONArray portList = portConfig.getJSONArray("PORTLIST");
                    this.mPortArr = portList;
                    if (portList != null) {
                        if (portList.length() >= 1) {
                            VsEditView webPortView = this.mEtWp;
                            webPortView.SetText(String.valueOf(this.mPortArr.get(0)));
                        }
                        if (this.mPortArr.length() >= 2) {
                            VsEditView mediaPortView = this.mEtMp;
                            mediaPortView.SetText(String.valueOf(this.mPortArr.get(1)));
                        }
                        if (this.mPortArr.length() >= 3) {
                            VsEditView rtspPortView = this.mEtRp;
                            rtspPortView.SetText(String.valueOf(this.mPortArr.get(2)));
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
        int webPortLength = getStrLen(this.mEtWp);
        int mediaPortLength = getStrLen(this.mEtMp);
        int rtspPortLength = getStrLen(this.mEtRp);
        if (webPortLength == 0 || mediaPortLength == 0 || rtspPortLength == 0) {
            toastSf((int) R.string.PortIsError);
            return;
        }
        int webPort = parse2Int(this.mEtWp).intValue();
        int mediaPort = parse2Int(this.mEtMp).intValue();
        int rtspPort = parse2Int(this.mEtRp).intValue();
        if (webPort == mediaPort || webPort == rtspPort || mediaPort == rtspPort || webPort <= 0 || mediaPort <= 0 || rtspPort <= 0) {
            toastSf((int) R.string.PortIsError);
            return;
        }
        JSONArray portList = this.mPortArr;
        if (portList != null) {
            try {
                portList.put(0, parse2Int(this.mEtWp));
                this.mPortArr.put(1, parse2Int(this.mEtMp));
                this.mPortArr.put(2, parse2Int(this.mEtRp));
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void onFocusChange(View view, boolean hasFocus) {
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

    public void setRegion(final EditText editText, final int maxPort) {
        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence text, int start, int before, int count) {
                if (maxPort > 1) {
                    String portText = text.toString();
                    if (maxPort != -1 && !portText.isEmpty()) {
                        int portValue = Integer.parseInt(portText);
                        if (portValue > maxPort) {
                            text = String.valueOf(maxPort);
                            editText.setText(text);
                        }
                        editText.setSelection(text.length());
                    }
                }
            }

            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.equals("") && maxPort != -1) {
                    int value;
                    try {
                        value = Integer.parseInt(editable.toString());
                    } catch (NumberFormatException unused) {
                        value = 0;
                    }
                    int max = maxPort;
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
            JSONObject request = new JSONObject();
            JSONObject networkSettings = new JSONObject();
            networkSettings.put("PORT", "?");
            request.put("NWSM", networkSettings);
            return request.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String responseJson) {
        try {
            this.mPortRes = new JSONObject(responseJson);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject portResponse = this.mPortRes;
        if (portResponse == null) {
            return "";
        }
        return portResponse.toString();
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
