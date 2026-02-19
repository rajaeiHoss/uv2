package com.streamax.client.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.streamax.config.base.CustomBaseAdapter;
import com.streamax.config.fragment.SelectInterface;
import com.streamax.config.utils.ImageUtils;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;

public class FragmentSelect extends FragmentBase implements View.OnClickListener {
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
    public View initView() {
        this.mChildView = this.mInflater.inflate(R.layout.play_fragment_select, (ViewGroup) null);
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
                view = View.inflate(FragmentSelect.this.mContext, R.layout.view_lv_item, (ViewGroup) null);
                new ViewHolder(view);
            }
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (FragmentSelect.this.mSelectList.contains(num)) {
                if (FragmentSelect.this.mCheckBox == 1) {
                    ImageUtils.showIcon(viewHolder.mRbForCh, R.drawable.cb_selected);
                } else {
                    ImageUtils.showIcon(viewHolder.mRbForCh, R.drawable.rb_signle_checked);
                }
            } else if (FragmentSelect.this.mCheckBox == 1) {
                ImageUtils.showIcon(viewHolder.mRbForCh, R.drawable.cb_unselected);
            } else {
                ImageUtils.showNone(viewHolder.mRbForCh, R.drawable.cb_unselected);
            }
            if (FragmentSelect.this.mCheckBox == 1 && FragmentSelect.this.mSelectList.size() == FragmentSelect.this.mAlDatas.size()) {
                ImageUtils.showIcon(FragmentSelect.this.mTvAll, R.drawable.cb_selected);
            }
            viewHolder.mRbForCh.setText((CharSequence) this.mDataSource.get(i));
            viewHolder.mRbForCh.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FragmentSelect.this.mCheckBox != 1) {
                        FragmentSelect.this.mSelectList.clear();
                        FragmentSelect.this.mSelectList.add(num);
                        FragmentSelect.this.mAdapter.notifyDataSetChanged();
                        FragmentSelect.this.saveSelect();
                        return;
                    }
                    if (FragmentSelect.this.mSelectList.indexOf(num) >= 0) {
                        FragmentSelect.this.mSelectList.remove(num);
                    } else {
                        FragmentSelect.this.mSelectList.add(num);
                    }
                    if (FragmentSelect.this.mSelectList.size() == FragmentSelect.this.mAlDatas.size()) {
                        ImageUtils.showIcon(FragmentSelect.this.mTvAll, R.drawable.cb_selected);
                    } else {
                        ImageUtils.showIcon(FragmentSelect.this.mTvAll, R.drawable.cb_unselected);
                    }
                    FragmentSelect.this.mAdapter.notifyDataSetChanged();
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
