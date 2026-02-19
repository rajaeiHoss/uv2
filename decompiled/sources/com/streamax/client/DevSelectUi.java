package com.streamax.client;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.streamax.client.ui.devlist.db.GroupBean;
import com.streamax.client.ui.devlist.db.GroupDao;
import com.streamax.client.ui.devlist.db.GroupDaoForNormal;
import com.streamax.client.ui.devlist.db.GroupDaoForServer;
import com.streamax.config.base.BaseUi;
import com.streamax.utils.AppProxy;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;

public class DevSelectUi extends BaseUi implements ExpandableListView.OnGroupClickListener, View.OnClickListener {
    /* access modifiers changed from: private */
    public int currentPosition;
    public MyApp mApp;
    private Button mBtnBack;
    public List<ArrayList<String>> mChildDatas = new ArrayList();
    /* access modifiers changed from: private */
    public Context mContext;
    private GroupDao mDao;
    /* access modifiers changed from: private */
    public ArrayList<ArrayList<GroupBean>> mDatas;
    /* access modifiers changed from: private */
    public List<DevInfoBean> mDevInfoDatas;
    /* access modifiers changed from: private */
    public List<GroupBean> mGroupBeanDatas;
    public List<String> mGroupDatas = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> mGroupNameDatas;
    private ExpLv mLv;
    /* access modifiers changed from: private */
    public Resources mResources;
    private TextView mTvTitle;
    private DbHelper mdbHelper;

    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mContext = AppProxy.getContext();
        this.mResources = AppProxy.getResources();
        this.mdbHelper = new DbHelper(this.mContext, DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.mApp = (MyApp) getApplication();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        setContentView((int) R.layout.exp_ui);
        this.mLv = (ExpLv) findViewById(R.id.exp_lv);
        this.mBtnBack = (Button) findViewById(R.id.exp_title_btn_back);
        this.mTvTitle = (TextView) findViewById(R.id.exp_title_tv_name);
        this.mLv.setGroupIndicator((Drawable) null);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        this.mTvTitle.setText(R.string.preview);
        getDevInfoDatas();
        this.mGroupBeanDatas = this.mDao.getGroupDatas();
        this.mGroupNameDatas = new ArrayList();
        List<GroupBean> list = this.mGroupBeanDatas;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.mGroupBeanDatas.size(); i++) {
                if (!this.mGroupNameDatas.contains(this.mGroupBeanDatas.get(i).dbGroupName)) {
                    this.mGroupNameDatas.add(this.mGroupBeanDatas.get(i).dbGroupName);
                }
            }
            this.mDatas = new ArrayList<>();
            for (int i2 = 0; i2 < this.mGroupNameDatas.size(); i2++) {
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < this.mGroupBeanDatas.size(); i3++) {
                    if (this.mGroupNameDatas.get(i2).equals(this.mGroupBeanDatas.get(i3).dbGroupName) && this.mGroupBeanDatas.get(i3).dbFlag == 1) {
                        arrayList.add(this.mGroupBeanDatas.get(i3));
                    }
                }
                this.mDatas.add(arrayList);
            }
        }
        this.mLv.setAdapter(new CustomAdapter());
        this.currentPosition = getIntent().getIntExtra("currentPosition", -1);
    }

    private void getDevInfoDatas() {
        if (MyApp.loginType == 0) {
            this.mDevInfoDatas = this.mdbHelper.getlist();
            this.mDao = new GroupDaoForNormal();
        } else {
            this.mDevInfoDatas = this.mApp.mWebService.GetTerminalInfoAndroid(true);
            this.mDao = new GroupDaoForServer();
        }
        List<DevInfoBean> list = this.mDevInfoDatas;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.mDevInfoDatas.size(); i++) {
                this.mGroupDatas.add(this.mDevInfoDatas.get(i).mDevName);
                ArrayList arrayList = new ArrayList();
                int i2 = 0;
                while (i2 < this.mDevInfoDatas.get(i).mChCounts) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.mResources.getString(R.string.channel));
                    sb.append(" ");
                    i2++;
                    sb.append(i2);
                    arrayList.add(sb.toString());
                }
                this.mChildDatas.add(arrayList);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mLv.setOnGroupClickListener(this);
        this.mBtnBack.setOnClickListener(this);
    }

    class ViewHolder {
        ImageView icon;
        TextView text;

        ViewHolder() {
        }
    }

    class CustomAdapter extends BaseExpandableListAdapter {
        public Object getChild(int i, int i2) {
            return "childPosition";
        }

        public long getChildId(int i, int i2) {
            return 0;
        }

        public Object getGroup(int i) {
            return "groupPosition";
        }

        public long getGroupId(int i) {
            return 0;
        }

        public boolean hasStableIds() {
            return false;
        }

        public boolean isChildSelectable(int i, int i2) {
            return false;
        }

        CustomAdapter() {
        }

        public int getGroupCount() {
            int i = 0;
            if (DevSelectUi.this.mGroupDatas != null) {
                i = 0 + DevSelectUi.this.mGroupDatas.size();
            }
            return (DevSelectUi.this.mGroupBeanDatas == null || DevSelectUi.this.mDatas == null) ? i : i + DevSelectUi.this.mDatas.size();
        }

        public int getGroupTypeCount() {
            return DevSelectUi.this.mGroupBeanDatas == null ? 1 : 2;
        }

        public int getChildrenCount(int i) {
            if (DevSelectUi.this.mChildDatas == null) {
                return 0;
            }
            if (i < DevSelectUi.this.mChildDatas.size()) {
                return DevSelectUi.this.mChildDatas.get(i).size();
            }
            return ((ArrayList) DevSelectUi.this.mDatas.get(i - DevSelectUi.this.mChildDatas.size())).size();
        }

        public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(DevSelectUi.this.mContext, R.layout.exp_group, (ViewGroup) null);
                new GroupHolder(view);
            }
            GroupHolder groupHolder = (GroupHolder) view.getTag();
            groupHolder.mGroupIv.setImageResource(z ? R.drawable.arrow_down : R.drawable.arrow_right);
            if (DevSelectUi.this.mGroupDatas != null && DevSelectUi.this.mGroupDatas.size() > 0) {
                if (i < DevSelectUi.this.mGroupDatas.size()) {
                    groupHolder.mGroupTv.setText(DevSelectUi.this.mGroupDatas.get(i));
                } else {
                    groupHolder.mGroupTv.setText((CharSequence) DevSelectUi.this.mGroupNameDatas.get(i - DevSelectUi.this.mGroupDatas.size()));
                }
            }
            return view;
        }

        public View getChildView(final int i, final int i2, boolean z, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(DevSelectUi.this.mContext, R.layout.exp_child, (ViewGroup) null);
                new ChildHolder(view);
            }
            ChildHolder childHolder = (ChildHolder) view.getTag();
            if (DevSelectUi.this.mGroupDatas != null && DevSelectUi.this.mGroupDatas.size() > 0) {
                if (i < DevSelectUi.this.mGroupDatas.size()) {
                    childHolder.mChildTv.setText((CharSequence) DevSelectUi.this.mChildDatas.get(i).get(i2));
                } else {
                    TextView textView = childHolder.mChildTv;
                    textView.setText(DevSelectUi.this.mResources.getString(R.string.channel) + " " + (((GroupBean) ((ArrayList) DevSelectUi.this.mDatas.get(i - DevSelectUi.this.mGroupDatas.size())).get(i2)).dbChannel + 1));
                }
            }
            childHolder.mChildLl.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(DevSelectUi.this, RealPlayActivity.class);
                    if (i < DevSelectUi.this.mGroupDatas.size()) {
                        intent.putExtra("id", ((DevInfoBean) DevSelectUi.this.mDevInfoDatas.get(i)).mDevId);
                        intent.putExtra("channel", i2);
                        intent.putExtra("currentPosition", DevSelectUi.this.currentPosition);
                    } else {
                        intent.putExtra("id", ((GroupBean) ((ArrayList) DevSelectUi.this.mDatas.get(i - DevSelectUi.this.mGroupDatas.size())).get(i2)).dbId);
                        intent.putExtra("channel", ((GroupBean) ((ArrayList) DevSelectUi.this.mDatas.get(i - DevSelectUi.this.mGroupDatas.size())).get(i2)).dbChannel);
                        intent.putExtra("currentPosition", DevSelectUi.this.currentPosition);
                    }
                    DevSelectUi.this.setResult(-1, intent);
                    DevSelectUi.this.finish();
                }
            });
            return view;
        }

        class ChildHolder {
            ImageView mChildIv;
            LinearLayout mChildLl;
            TextView mChildTv;

            public ChildHolder(View view) {
                this.mChildIv = (ImageView) view.findViewById(R.id.exp_child_iv);
                this.mChildTv = (TextView) view.findViewById(R.id.exp_child_tv);
                this.mChildLl = (LinearLayout) view.findViewById(R.id.exp_child_ll);
                view.setTag(this);
                view.setTag(this);
            }
        }

        class GroupHolder {
            ImageView mGroupIv;
            TextView mGroupTv;

            public GroupHolder(View view) {
                this.mGroupIv = (ImageView) view.findViewById(R.id.exp_group_iv);
                this.mGroupTv = (TextView) view.findViewById(R.id.exp_group_tv);
                view.setTag(this);
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.exp_title_btn_back) {
            finish();
        }
    }
}
