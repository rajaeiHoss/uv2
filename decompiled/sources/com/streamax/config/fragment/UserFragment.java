package com.streamax.config.fragment;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.listener.SuperListener;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetManager;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.StringUtils;
import com.zycs.UView.R;
import org.json.JSONException;
import org.json.JSONObject;

public class UserFragment extends ConfigFragment implements BaseListener.GetListener {
    public Button mBtnConfirm;
    public EditText mEtConfirm;
    public EditText mEtPwd;
    public JSONObject mUserRes;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    public String requestForGetConfig() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_changePwd_Title);
        this.mBtnBack.setOnClickListener(this);
        this.mBtnUpdate.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_user, (ViewGroup) null);
        this.mEtPwd = (EditText) this.mRootView.findViewById(R.id.config_user_et_pwd);
        this.mEtConfirm = (EditText) this.mRootView.findViewById(R.id.config_user_et_pwd_confirm);
        this.mBtnConfirm = (Button) this.mRootView.findViewById(R.id.config_user_btn_confirm);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getUserData();
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnConfirm.setOnClickListener(this);
    }

    public void getUserData() {
        NetPresenter.getDefault().getUserRigth(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.config_title_btn_back) {
            prePage();
        } else if (id == R.id.config_user_btn_confirm) {
            savePassword();
        }
    }

    public void savePassword() {
        String string = StringUtils.getString(this.mEtPwd);
        String string2 = StringUtils.getString(this.mEtConfirm);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            toastSf((int) R.string.config_PasswordIsEmpty);
        } else if (!string.equals(string2)) {
            toastSf((int) R.string.config_TwicePasswordIsNotEquals);
        } else {
            JSONObject jSONObject = this.mUserRes;
            if (jSONObject != null) {
                try {
                    jSONObject.put("PW", string);
                    showLoading();
                    NetManager.getDefault().setUserRigth(username, this.mUserRes.toString(), new SuperListener.SetConfigListener() {
                        public void onSuccess() {
                            UserFragment.this.hideLoading();
                            UserFragment.this.toastSelectDeviceConnect();
                        }

                        public void onFailure() {
                            UserFragment.this.hideLoading();
                        }
                    });
                } catch (JSONException unused) {
                }
            }
        }
    }

    public void getSuccess(String str) {
        LogUtils.e("UserFragment", "getSuccess 1, result: " + str);
        try {
            this.mUserRes = new JSONObject(str);
        } catch (JSONException unused) {
            showErrorFragment();
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
