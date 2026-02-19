package com.streamax.config.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.streamax.adapter.SuperBaseAdapter;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.factory.FragmentFactory;
import com.streamax.config.utils.ImageUtils;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.List;

public class NetworkFragment extends ConfigFragment implements AdapterView.OnItemClickListener {
    public ListView mLvContent;
    public List<String> mModelDatas;

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_network_Title);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_lv, (ViewGroup) null);
        this.mLvContent = (ListView) this.mRootView.findViewById(R.id.config_info_lv_content);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.mModelDatas = getStrDatas(R.array.config_netWork_ModelDatas);
        this.mLvContent.setAdapter(new ModelAdapter(this.mModelDatas));
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mLvContent.setOnItemClickListener(this);
    }

    public class ModelAdapter extends SuperBaseAdapter<String> {
        public ModelAdapter(List<String> list) {
            super(list);
        }

        /* access modifiers changed from: protected */
        public View initConvertView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(NetworkFragment.this.mContext, R.layout.view_lv_item, (ViewGroup) null);
                new Holder(view);
            }
            Holder holder = (Holder) view.getTag();
            holder.mTvView.setText((CharSequence) this.mDataSource.get(i));
            ImageUtils.showIconForTv(holder.mTvView, R.drawable.jt_you_pre, 3);
            return view;
        }

        public class Holder {
            VsTextView mTvView;

            public Holder(View view) {
                this.mTvView = (VsTextView) view.findViewById(R.id.view_lv_item_tv);
                view.setTag(this);
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.config_title_btn_back) {
            prePage();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i == 0 || i == 1) {
            if (this.mApp.mNetworkMaskInt > 0) {
                nextPage(FragmentFactory.NetworkFactory.createFragment(i));
            } else if (this.mApp.mNetworkMaskInt == 0) {
                showPopupWindow();
            }
        } else if (i != 2 && i != 3 && i != 4 && i != 5) {
        } else {
            if (this.mApp.mCommMaskInt > 0) {
                nextPage(FragmentFactory.NetworkFactory.createFragment(i));
            } else if (this.mApp.mCommMaskInt == 0) {
                showPopupWindow();
            }
        }
    }

    public void showUnHiddenView() {
        super.showUnHiddenView();
    }
}
