package com.streamax.config.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.base.CustomBaseAdapter;
import com.streamax.config.utils.ImageUtils;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;

public class SelectFragment extends ConfigFragment {
    /* access modifiers changed from: private */
    public CustomAdapter mAdapter;
    /* access modifiers changed from: private */
    public ArrayList<String> mAlDatas = new ArrayList<>();
    public Button mBtnSave;
    public int mCheckBox;
    public String mFragName;
    public String mFragTitle;
    private SelectInterface mInterface;
    private ListView mLvContent;
    public List<Integer> mSelectList = new ArrayList();
    private String mText;
    public TextView mTvAll;

    public void SetSelectInterface(SelectInterface selectInterface) {
        this.mInterface = selectInterface;
    }

    public void SetTextViewData(String str) {
        this.mText = str;
    }

    public void SetListViewData(List<String> list) {
        this.mAlDatas.clear();
        if (list != null) {
            for (String add : list) {
                this.mAlDatas.add(add);
            }
        }
    }

    public void SetListViewSelect(List<Integer> list) {
        this.mSelectList.clear();
        if (list != null) {
            for (Integer add : list) {
                this.mSelectList.add(add);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        initTitleView();
    }

    public void initTitleView() {
        super.initConfigTitleView();
        this.mBtnBack.setVisibility(0);
        this.mTvTitle.setText(this.mFragTitle);
        this.mTvTitle.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mChildView = this.mInflater.inflate(R.layout.config_select, (ViewGroup) null);
        this.mTvAll = (TextView) this.mChildView.findViewById(R.id.config_select_tv_all);
        this.mLvContent = (ListView) this.mChildView.findViewById(R.id.config_select_lv);
        this.mBtnSave = (Button) this.mChildView.findViewById(R.id.config_select_btn_save);
        return this.mChildView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        CustomAdapter customAdapter = new CustomAdapter(this.mAlDatas);
        this.mAdapter = customAdapter;
        this.mLvContent.setAdapter(customAdapter);
        if (this.mCheckBox == 1) {
            this.mTvAll.setText(this.mText);
            this.mTvAll.setVisibility(0);
            this.mBtnSave.setVisibility(0);
            return;
        }
        this.mTvAll.setVisibility(8);
        this.mBtnSave.setVisibility(8);
    }

    class CustomAdapter extends CustomBaseAdapter<String> {
        public CustomAdapter(List<String> list) {
            super(list);
        }

        /* access modifiers changed from: protected */
        public View initConvertView(int i, View view, ViewGroup viewGroup) {
            final Integer num = new Integer(i);
            if (view == null) {
                view = View.inflate(SelectFragment.this.mContext, R.layout.view_lv_item, (ViewGroup) null);
                new ViewHolder(view);
            }
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (SelectFragment.this.mSelectList.contains(num)) {
                if (SelectFragment.this.mCheckBox == 1) {
                    ImageUtils.showIcon(viewHolder.mRbForCh, R.drawable.cb_selected);
                } else {
                    ImageUtils.showIcon(viewHolder.mRbForCh, R.drawable.rb_signle_checked);
                }
            } else if (SelectFragment.this.mCheckBox == 1) {
                ImageUtils.showIcon(viewHolder.mRbForCh, R.drawable.cb_unselected);
            } else {
                ImageUtils.showNone(viewHolder.mRbForCh, R.drawable.cb_unselected);
            }
            if (SelectFragment.this.mCheckBox == 1 && SelectFragment.this.mSelectList.size() == SelectFragment.this.mAlDatas.size()) {
                ImageUtils.showIcon(SelectFragment.this.mTvAll, R.drawable.cb_selected);
            }
            viewHolder.mRbForCh.setText((CharSequence) this.mDataSource.get(i));
            viewHolder.mRbForCh.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SelectFragment.this.mCheckBox != 1) {
                        SelectFragment.this.mSelectList.clear();
                        SelectFragment.this.mSelectList.add(num);
                        SelectFragment.this.mAdapter.notifyDataSetChanged();
                        SelectFragment.this.saveSelect();
                        return;
                    }
                    if (SelectFragment.this.mSelectList.indexOf(num) >= 0) {
                        SelectFragment.this.mSelectList.remove(num);
                    } else {
                        SelectFragment.this.mSelectList.add(num);
                    }
                    if (SelectFragment.this.mSelectList.size() == SelectFragment.this.mAlDatas.size()) {
                        ImageUtils.showIcon(SelectFragment.this.mTvAll, R.drawable.cb_selected);
                    } else {
                        ImageUtils.showIcon(SelectFragment.this.mTvAll, R.drawable.cb_unselected);
                    }
                    SelectFragment.this.mAdapter.notifyDataSetChanged();
                }
            });
            return view;
        }

        class ViewHolder {
            VsTextView mRbForCh;

            public ViewHolder(View view) {
                this.mRbForCh = (VsTextView) view.findViewById(R.id.view_lv_item_tv);
                view.setTag(this);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnBack.setOnClickListener(this);
        this.mTvAll.setOnClickListener(this);
        this.mBtnSave.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void popFragment() {
        prePage();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.config_select_btn_save) {
            saveSelect();
        } else if (id == R.id.config_select_tv_all) {
            selectAll();
        } else if (id == R.id.config_title_btn_back) {
            popFragment();
        }
    }

    public void selectAll() {
        if (this.mSelectList.size() == this.mAlDatas.size()) {
            this.mSelectList.clear();
            ImageUtils.showIcon(this.mTvAll, R.drawable.cb_unselected);
        } else {
            for (int i = 0; i < this.mAlDatas.size(); i++) {
                Integer num = new Integer(i);
                if (this.mSelectList.indexOf(num) < 0) {
                    this.mSelectList.add(num);
                }
            }
            ImageUtils.showIcon(this.mTvAll, R.drawable.cb_selected);
        }
        CustomAdapter customAdapter = this.mAdapter;
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }

    public void saveSelect() {
        SelectInterface selectInterface = this.mInterface;
        if (selectInterface != null) {
            selectInterface.saveSelect(this.mFragName, this.mSelectList);
        }
        popFragment();
    }

    public void onPause() {
        super.onPause();
    }
}
