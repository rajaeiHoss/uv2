package com.streamax.client.ui.devlist.ui;

import androidx.fragment.app.FragmentTransaction;
import com.streamax.config.base.BaseUi;
import com.zycs.UView.R;

public class DevListUi extends BaseUi {
    /* access modifiers changed from: protected */
    public void init() {
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
    }

    /* access modifiers changed from: protected */
    public void initView() {
        setContentView((int) R.layout.dev_list_ui);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.dev_fl, new DevListFragment());
        beginTransaction.commit();
    }
}
