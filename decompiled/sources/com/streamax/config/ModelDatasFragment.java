package com.streamax.config;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.base.CustomBaseAdapter;
import com.streamax.config.factory.FragmentFactory;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.List;

public class ModelDatasFragment extends ConfigFragment implements AdapterView.OnItemClickListener {
    public View mDevConfigList;
    public List<String> mListDatas;
    public ListView mLvDevConfig;

    /* access modifiers changed from: protected */
    public void init() {
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_Config);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        View inflate = this.mInflater.inflate(R.layout.config_info_lv, (ViewGroup) null);
        this.mDevConfigList = inflate;
        this.mLvDevConfig = (ListView) inflate.findViewById(R.id.config_info_lv_content);
        return this.mDevConfigList;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.mListDatas = getStrDatas(R.array.config_ModelList);
        this.mLvDevConfig.setAdapter(new DeviceInfoAdapter(this.mListDatas));
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnBack.setOnClickListener(this);
        this.mLvDevConfig.setOnItemClickListener(this);
    }

    private class DeviceInfoAdapter extends CustomBaseAdapter<String> {
        public DeviceInfoAdapter(List<String> list) {
            super(list);
        }

        /* access modifiers changed from: protected */
        public View initConvertView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(ModelDatasFragment.this.mContext, R.layout.view_lv_item, (ViewGroup) null);
                new ViewHolder(view);
            }
            ((ViewHolder) view.getTag()).mRbConfigName.setText(ModelDatasFragment.this.mListDatas.get(i));
            return view;
        }
    }

    private class ViewHolder {
        VsTextView mRbConfigName;

        public ViewHolder(View view) {
            this.mRbConfigName = (VsTextView) view.findViewById(R.id.view_lv_item_tv);
            view.setTag(this);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (i) {
            case 0:
                if (this.mApp.mRecordMaskInt > 0) {
                    nextPage(FragmentFactory.createFragment(i));
                    return;
                } else if (this.mApp.mRecordMaskInt == 0) {
                    showPopupWindow();
                    return;
                } else {
                    return;
                }
            case 1:
            case 3:
            case 5:
            case 6:
            case 7:
                nextPage(FragmentFactory.createFragment(i));
                return;
            case 2:
                if (this.mApp.mDatetimeMaskInt > 0) {
                    nextPage(FragmentFactory.createFragment(i));
                    return;
                } else if (this.mApp.mDatetimeMaskInt == 0) {
                    showPopupWindow();
                    return;
                } else {
                    return;
                }
            case 4:
                if (this.mApp.mAlarmMaskInt > 0) {
                    nextPage(FragmentFactory.createFragment(i));
                    return;
                } else if (this.mApp.mAlarmMaskInt == 0) {
                    showPopupWindow();
                    return;
                } else {
                    return;
                }
            case 8:
                if (this.mApp.mDiskMaskInt > 0) {
                    nextPage(FragmentFactory.createFragment(i));
                    return;
                } else if (this.mApp.mDiskMaskInt == 0) {
                    showPopupWindow();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.config_title_btn_back) {
            prePage();
        }
    }

    public void prePage() {
        this.mBtnBack.setVisibility(8);
        super.prePage();
    }
}
