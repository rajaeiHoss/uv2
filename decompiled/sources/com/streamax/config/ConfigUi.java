package com.streamax.config;

import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.streamax.config.base.BaseUi;
import com.zycs.UView.R;

public class ConfigUi extends BaseUi {
    Button mTitleBtnBack;
    Button mTitleBtnUpdate;
    TextView mTitleTvDeviceIp;

    /* access modifiers changed from: protected */
    public void initEvent() {
    }

    /* access modifiers changed from: protected */
    public void initView() {
        setContentView((int) R.layout.config_ui);
        this.mTitleBtnBack = (Button) findViewById(R.id.config_title_btn_back);
        this.mTitleTvDeviceIp = (TextView) findViewById(R.id.config_title_tv_name);
        this.mTitleBtnUpdate = (Button) findViewById(R.id.config_title_btn_update);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        loadDeviceListPage();
    }

    public void loadDeviceListPage() {
        getSupportFragmentManager().beginTransaction().add((int) R.id.config_framelayout, (Fragment) new SettingFragment()).commit();
    }
}
