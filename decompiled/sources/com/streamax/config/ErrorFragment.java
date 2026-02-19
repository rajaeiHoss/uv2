package com.streamax.config;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.streamax.Configs;
import com.streamax.config.base.ConfigFragment;
import com.zycs.UView.R;

public class ErrorFragment extends ConfigFragment {
    private Button mBtnRegetData;

    public void onClick(View view) {
        if (view.getId() == R.id.error_btn_retry) {
            fragmentTag = Configs.Tag.TagNull;
            prePage();
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnRegetData.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.layout_error, (ViewGroup) null);
        this.mBtnRegetData = (Button) this.mRootView.findViewById(R.id.error_btn_retry);
        return this.mRootView;
    }
}
