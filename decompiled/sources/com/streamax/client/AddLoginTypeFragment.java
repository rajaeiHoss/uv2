package com.streamax.client;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.utils.FragmentUtils;
import com.streamax.utils.AppProxy;
import com.streamax.utils.ToastSf;
import com.zycs.UView.R;
import de.greenrobot.event.EventBus;

public class AddLoginTypeFragment extends BaseFragment {
    private boolean isAdd;
    /* access modifiers changed from: private */
    public FragmentActivity mActivity;
    private Button mBtnLeft;
    private Button mBtnRight;
    private EditText mEtservername;
    private int mPosition;
    private String mServerName;
    private TextView mTvSave;
    private TextView mTvTitle;
    private TextView mTvdefault;

    /* access modifiers changed from: protected */
    public void init() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mPosition = arguments.getInt("position", -1);
            this.mServerName = arguments.getString("servername", "");
            this.isAdd = arguments.getBoolean("isAdd", false);
        }
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mChildView = this.mInflater.inflate(R.layout.server_add, (ViewGroup) null);
        initChildView();
        return this.mChildView;
    }

    private void initChildView() {
        this.mEtservername = (EditText) this.mChildView.findViewById(R.id.server_content);
        this.mTvdefault = (TextView) this.mChildView.findViewById(R.id.tvdefault);
        this.mTvSave = (TextView) this.mChildView.findViewById(R.id.tvsave);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.mActivity = getActivity();
        getWidget();
    }

    private void getWidget() {
        this.mBtnLeft = (Button) this.mActivity.findViewById(R.id.lt_title_btn_left);
        this.mBtnRight = (Button) this.mActivity.findViewById(R.id.lt_title_btn_right);
        TextView textView = (TextView) this.mActivity.findViewById(R.id.lt_title_tv_name);
        this.mTvTitle = textView;
        textView.setText(R.string.addserver);
        this.mBtnLeft.setText(R.string.returnback);
        this.mBtnRight.setVisibility(8);
        if (!TextUtils.isEmpty(this.mServerName)) {
            this.mEtservername.setText(this.mServerName);
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnLeft.setOnClickListener(this);
        this.mBtnRight.setOnClickListener(this);
        this.mTvdefault.setOnClickListener(this);
        this.mTvSave.setOnClickListener(this);
    }

    public void onClick(View view) {
        String trim = this.mEtservername.getText().toString().trim();
        int id = view.getId();
        if (id == R.id.lt_title_btn_left) {
            FragmentUtils.fragmentReplace(this, new LoginTypeListFragment(), R.id.lt_fl);
            this.mBtnRight.setVisibility(0);
            this.mTvTitle.setText(R.string.logintype);
        } else if (id != R.id.tvdefault) {
            if (id == R.id.tvsave) {
                if (TextUtils.isEmpty(this.mEtservername.getText().toString().trim())) {
                    ToastSf.toastSf((Activity) this.mActivity, (int) R.string.servernull);
                } else if (!MyApp.ServerHostNameDatas.contains(trim)) {
                    Log.e("isAdd", "" + this.isAdd);
                    if (this.isAdd) {
                        MyApp.ServerHostNameDatas.add(trim);
                    } else {
                        int i = this.mPosition;
                        if (i > 0 && i < MyApp.ServerHostNameDatas.size()) {
                            MyApp.ServerHostNameDatas.set(this.mPosition, trim);
                        }
                    }
                    MyApp.setServerHostNames();
                    EventBus.getDefault().post(new EditLoginTypeEvent());
                    FragmentUtils.fragmentReplace(this, new LoginTypeListFragment(), R.id.lt_fl);
                    hideIputKeyboard();
                    this.mBtnRight.setVisibility(0);
                    this.mTvTitle.setText(R.string.logintype);
                } else {
                    ToastSf.toastSf((Activity) this.mActivity, (int) R.string.servernameexits);
                }
            }
        } else if (MyApp.ServerHostNameDatas.contains(trim)) {
            MyApp.LastServerHostName = trim;
            FragmentUtils.fragmentReplace(this, new LoginTypeListFragment(), R.id.lt_fl);
            this.mBtnRight.setVisibility(0);
            this.mTvTitle.setText(R.string.logintype);
        } else {
            ToastSf.toastSf((Activity) this.mActivity, (int) R.string.failure);
        }
    }

    public void hideIputKeyboard() {
        this.mActivity.runOnUiThread(new Runnable() {
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) AppProxy.getContext().getSystemService("input_method");
                if (AddLoginTypeFragment.this.mActivity.getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(AddLoginTypeFragment.this.mActivity.getCurrentFocus().getWindowToken(), 2);
                    AddLoginTypeFragment.this.mActivity.getWindow().setSoftInputMode(2);
                }
            }
        });
    }
}
