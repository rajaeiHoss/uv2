package com.streamax.config.fragment;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.base.CustomBaseAdapter;
import com.streamax.config.fragment.video.StreamOfVideo;
import com.streamax.utils.AppProxy;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.List;

public class VideoFragment extends ConfigFragment implements AdapterView.OnItemClickListener {
    public ListView mLvContent;
    public List<String> mModelDatas;

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_video_Title);
        this.mBtnBack.setOnClickListener(this);
        this.mBtnUpdate.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_lv, (ViewGroup) null);
        this.mLvContent = (ListView) this.mRootView.findViewById(R.id.config_info_lv_content);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        if (dvrNet != null) {
            if (dvrNet.nDevClass == 2 || dvrNet.nDevClass == 1) {
                this.mModelDatas = getStrDatas(R.array.StreamListForThree);
            } else {
                this.mModelDatas = getStrDatas(R.array.StreamListForTwo);
            }
            this.mLvContent.setAdapter(new CustomAdapter(this.mModelDatas));
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mLvContent.setOnItemClickListener(this);
    }

    public class CustomAdapter extends CustomBaseAdapter<String> {
        public CustomAdapter(List<String> list) {
            super(list);
        }

        /* access modifiers changed from: protected */
        public View initConvertView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(VideoFragment.this.mContext, R.layout.view_lv_item, (ViewGroup) null);
                new ViewHolder(view);
            }
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.mTvStream.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, (Drawable) null, AppProxy.getResources().getDrawable(R.drawable.jt_you_pre), (Drawable) null);
            viewHolder.mTvStream.setText((CharSequence) this.mDataSource.get(i));
            return view;
        }
    }

    public class ViewHolder {
        VsTextView mTvStream;

        public ViewHolder(View view) {
            this.mTvStream = (VsTextView) view.findViewById(R.id.view_lv_item_tv);
            view.setTag(this);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.config_title_btn_back) {
            prePage();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i == 0) {
            if (this.mApp.mRecordMaskInt > 0) {
                StreamOfVideo streamOfVideo = new StreamOfVideo();
                streamOfVideo.mStreamType = 0;
                streamOfVideo.mStreamTitle = this.mModelDatas.get(i);
                nextPage(streamOfVideo);
            } else if (this.mApp.mRecordMaskInt == 0) {
                showPopupWindow();
            }
        } else if (i == 1) {
            if (this.mApp.mNetworkMaskInt > 0) {
                StreamOfVideo streamOfVideo2 = new StreamOfVideo();
                streamOfVideo2.mStreamType = 1;
                streamOfVideo2.mStreamTitle = this.mModelDatas.get(i);
                nextPage(streamOfVideo2);
            } else if (this.mApp.mNetworkMaskInt == 0) {
                showPopupWindow();
            }
        } else if (i != 2) {
        } else {
            if (this.mApp.mNetworkMaskInt > 0) {
                StreamOfVideo streamOfVideo3 = new StreamOfVideo();
                streamOfVideo3.mStreamType = 2;
                streamOfVideo3.mStreamTitle = this.mModelDatas.get(i);
                nextPage(streamOfVideo3);
            } else if (this.mApp.mNetworkMaskInt == 0) {
                showPopupWindow();
            }
        }
    }
}
