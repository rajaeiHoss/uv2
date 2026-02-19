package com.streamax.client;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.utils.FragmentUtils;
import com.streamax.utils.AppProxy;
import com.zycs.UView.R;
import de.greenrobot.event.EventBus;
import java.util.List;

public class LoginTypeListFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public LoginTypeUi mActivity;
    public Button mBtnLeft;
    public Button mBtnRight;
    /* access modifiers changed from: private */
    public CustomAdapter mCustomAdapter;
    /* access modifiers changed from: private */
    public boolean mFlag;
    /* access modifiers changed from: private */
    public boolean mIsAdd;
    public ListView mListView;
    public TextView mTvAdd;

    /* access modifiers changed from: protected */
    public void init() {
        EventBus.getDefault().register(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mChildView = this.mInflater.inflate(R.layout.lt_list, (ViewGroup) null);
        initChildView();
        return this.mChildView;
    }

    private void initChildView() {
        this.mListView = (ListView) this.mChildView.findViewById(R.id.lt_lv);
        this.mTvAdd = (TextView) this.mChildView.findViewById(R.id.tvadd);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.mActivity = (LoginTypeUi) getActivity();
        getWidget();
        CustomAdapter customAdapter = new CustomAdapter(MyApp.ServerHostNameDatas);
        this.mCustomAdapter = customAdapter;
        this.mListView.setAdapter(customAdapter);
    }

    private void getWidget() {
        this.mBtnLeft = (Button) this.mActivity.findViewById(R.id.lt_title_btn_left);
        this.mBtnRight = (Button) this.mActivity.findViewById(R.id.lt_title_btn_right);
        this.mBtnLeft.setText(R.string.returnback);
        this.mBtnRight.setText(R.string.edit);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnLeft.setOnClickListener(this);
        this.mBtnRight.setOnClickListener(this);
        this.mTvAdd.setOnClickListener(this);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!LoginTypeListFragment.this.mFlag) {
                    EventBus.getDefault().post(new LoginTypeEvent(i));
                    LoginTypeListFragment.this.mActivity.finish();
                } else if (i != 0) {
                    boolean unused = LoginTypeListFragment.this.mIsAdd = false;
                    AddLoginTypeFragment addLoginTypeFragment = new AddLoginTypeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", i);
                    bundle.putBoolean("isAdd", LoginTypeListFragment.this.mIsAdd);
                    bundle.putString("servername", MyApp.ServerHostNameDatas.get(i));
                    addLoginTypeFragment.setArguments(bundle);
                    FragmentUtils.fragmentReplace(LoginTypeListFragment.this, addLoginTypeFragment, R.id.lt_fl);
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lt_title_btn_left /*2131362594*/:
                this.mActivity.finish();
                return;
            case R.id.lt_title_btn_right /*2131362595*/:
                doEdit();
                return;
            case R.id.tvadd /*2131363255*/:
                this.mIsAdd = true;
                AddLoginTypeFragment addLoginTypeFragment = new AddLoginTypeFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isAdd", this.mIsAdd);
                addLoginTypeFragment.setArguments(bundle);
                FragmentUtils.fragmentReplace(this, addLoginTypeFragment, R.id.lt_fl);
                return;
            default:
                return;
        }
    }

    private void doEdit() {
        if (this.mFlag) {
            this.mFlag = false;
            this.mBtnRight.setText(R.string.edit);
            Log.e("edit", "edit");
        } else {
            this.mFlag = true;
            this.mBtnRight.setText(R.string.done);
            Log.e("done", "done");
        }
        CustomAdapter customAdapter = this.mCustomAdapter;
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }

    public class CustomAdapter extends BaseAdapter {
        /* access modifiers changed from: private */
        public List<String> mServersList;

        public long getItemId(int i) {
            return (long) i;
        }

        public CustomAdapter(List<String> list) {
            this.mServersList = list;
        }

        public int getCount() {
            return this.mServersList.size();
        }

        public Object getItem(int i) {
            List<String> list = this.mServersList;
            if (list == null) {
                return null;
            }
            return list.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(AppProxy.getContext(), R.layout.lt_lv_item, (ViewGroup) null);
                new ViewHolder(view);
            }
            final ViewHolder viewHolder = (ViewHolder) view.getTag();
            List<String> list = this.mServersList;
            if (list != null && list.size() > 0) {
                if (i == 0) {
                    viewHolder.mTvType.setText(MyApp.LastServerHostName);
                } else {
                    viewHolder.mTvType.setText("");
                }
            }
            viewHolder.mTvName.setText(this.mServersList.get(i));
            if (i == LoginTypeListFragment.this.mActivity.mPosition) {
                viewHolder.mIvChecked.setVisibility(0);
            } else {
                viewHolder.mIvChecked.setVisibility(8);
            }
            if (!LoginTypeListFragment.this.mFlag) {
                viewHolder.mIvDelete.setVisibility(8);
            } else if (i != 0) {
                viewHolder.mIvDelete.setVisibility(0);
            }
            viewHolder.mIvDelete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (i == CustomAdapter.this.mServersList.indexOf(MyApp.LastServerHostName)) {
                        if (MyApp.ServerHostNameDatas.size() >= 2) {
                            viewHolder.mTvType.setText(MyApp.ServerHostNameDatas.get(1));
                            MyApp.LastServerHostName = MyApp.ServerHostNameDatas.get(1);
                        } else if (MyApp.ServerHostNameDatas.size() == 1) {
                            viewHolder.mTvType.setText(MyApp.ServerHostNameDatas.get(0));
                            MyApp.LastServerHostName = LoginTypeListFragment.this.getString(R.string.normaluser);
                        }
                        MyApp.setLastServerHostName();
                    }
                    MyApp.ServerHostNameDatas.remove(i);
                    MyApp.setServerHostNames();
                    LoginTypeListFragment.this.mCustomAdapter.notifyDataSetChanged();
                }
            });
            return view;
        }

        class ViewHolder {
            public int mId;
            public ImageView mIvChecked;
            public ImageView mIvDelete;
            public TextView mTvName;
            public TextView mTvType;

            public ViewHolder(View view) {
                this.mTvName = (TextView) view.findViewById(R.id.lt_item_tv_servername);
                this.mTvType = (TextView) view.findViewById(R.id.lt_item_tv_servertype);
                this.mIvDelete = (ImageView) view.findViewById(R.id.lt_item_iv_delete);
                this.mIvChecked = (ImageView) view.findViewById(R.id.lt_item_iv_checked);
                view.setTag(this);
            }
        }
    }

    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEventMainThread(EditLoginTypeEvent editLoginTypeEvent) {
        if (editLoginTypeEvent != null) {
            this.mCustomAdapter.notifyDataSetChanged();
        }
    }
}
