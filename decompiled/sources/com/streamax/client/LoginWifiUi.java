package com.streamax.client;

import androidx.fragment.app.FragmentTransaction;
import com.streamax.config.base.BaseUi;
import com.zycs.UView.R;

public class LoginWifiUi extends BaseUi {
    public int mPosition;

    /* access modifiers changed from: protected */
    public void initEvent() {
    }

    /* access modifiers changed from: protected */
    public void initView() {
        setContentView((int) R.layout.loginwifi_list_ui);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.mPosition = getIntent().getIntExtra("position", 0);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.lt_fl, new LoginWifiListFragment());
        beginTransaction.commit();
    }
}
