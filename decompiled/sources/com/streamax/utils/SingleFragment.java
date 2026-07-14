package com.streamax.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.streamax.adapter.SingleAdapter;
import com.streamax.config.base.ConfigFragment;
import com.zycs.UView.R;
import java.util.List;

public class SingleFragment extends ConfigFragment implements AdapterView.OnItemClickListener {
    private SingleAdapter mAdapter;
    private List<String> mDatas;
    private SingleFragmentInterface mInterface;
    private ListView mListView;
    private String mName;
    private String mTitle;

    public interface SingleFragmentInterface {
        void saveSingleFragment(String name, int position);
    }

    public SingleFragment SetInterface(String name, SingleFragmentInterface singleFragmentInterface) {
        this.mName = name;
        this.mInterface = singleFragmentInterface;
        return this;
    }

    public SingleFragment setDatas(List<String> list) {
        this.mDatas = list;
        return this;
    }

    public SingleFragment setDatas(int stringArrayId) {
        this.mDatas = StringUtils.getStrDatas(stringArrayId);
        return this;
    }

    public SingleFragment setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public SingleFragment setTitle(int titleResId) {
        this.mTitle = StringUtils.getString(Integer.valueOf(titleResId));
        return this;
    }

    public SingleFragment setPosition(int position) {
        itemPosition = position;
        return this;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.mTvTitle.setText(this.mTitle);
        SingleAdapter singleAdapter = this.mAdapter;
        if (singleAdapter == null) {
            this.mListView.setAdapter(new SingleAdapter(this.mContext, this.mDatas, itemPosition));
        } else {
            singleAdapter.notifyDataSetChanged();
        }
    }

    public SingleFragment refreshAdapter() {
        SingleAdapter singleAdapter = this.mAdapter;
        if (singleAdapter == null) {
            this.mListView.setAdapter(new SingleAdapter(this.mContext, this.mDatas, itemPosition));
        } else {
            singleAdapter.notifyDataSetChanged();
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.fragment_single, (ViewGroup) null);
        this.mListView = (ListView) this.mRootView.findViewById(R.id.singleListView);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mListView.setOnItemClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.config_title_btn_back) {
            prePage();
        }
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        itemPosition = position;
        SingleFragmentInterface singleFragmentInterface = this.mInterface;
        if (singleFragmentInterface != null) {
            singleFragmentInterface.saveSingleFragment(this.mName, position);
        }
        prePage();
    }
}
