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

    public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long id) {
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
            for (int groupBeanIndex = 0; groupBeanIndex < this.mGroupBeanDatas.size(); groupBeanIndex++) {
                if (!this.mGroupNameDatas.contains(this.mGroupBeanDatas.get(groupBeanIndex).dbGroupName)) {
                    this.mGroupNameDatas.add(this.mGroupBeanDatas.get(groupBeanIndex).dbGroupName);
                }
            }
            this.mDatas = new ArrayList<>();
            for (int groupNameIndex = 0; groupNameIndex < this.mGroupNameDatas.size(); groupNameIndex++) {
                ArrayList<GroupBean> enabledGroupChannels = new ArrayList<>();
                for (int groupBeanIndex = 0; groupBeanIndex < this.mGroupBeanDatas.size(); groupBeanIndex++) {
                    if (this.mGroupNameDatas.get(groupNameIndex).equals(this.mGroupBeanDatas.get(groupBeanIndex).dbGroupName) && this.mGroupBeanDatas.get(groupBeanIndex).dbFlag == 1) {
                        enabledGroupChannels.add(this.mGroupBeanDatas.get(groupBeanIndex));
                    }
                }
                this.mDatas.add(enabledGroupChannels);
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
            for (int deviceIndex = 0; deviceIndex < this.mDevInfoDatas.size(); deviceIndex++) {
                this.mGroupDatas.add(this.mDevInfoDatas.get(deviceIndex).mDevName);
                ArrayList<String> channelNames = new ArrayList<>();
                int channelIndex = 0;
                while (channelIndex < this.mDevInfoDatas.get(deviceIndex).mChCounts) {
                    StringBuilder channelLabel = new StringBuilder();
                    channelLabel.append(this.mResources.getString(R.string.channel));
                    channelLabel.append(" ");
                    channelIndex++;
                    channelLabel.append(channelIndex);
                    channelNames.add(channelLabel.toString());
                }
                this.mChildDatas.add(channelNames);
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
        public Object getChild(int groupPosition, int childPosition) {
            return "childPosition";
        }

        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        public Object getGroup(int groupPosition) {
            return "groupPosition";
        }

        public long getGroupId(int groupPosition) {
            return 0;
        }

        public boolean hasStableIds() {
            return false;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        CustomAdapter() {
        }

        public int getGroupCount() {
            int groupCount = 0;
            if (DevSelectUi.this.mGroupDatas != null) {
                groupCount = DevSelectUi.this.mGroupDatas.size();
            }
            return (DevSelectUi.this.mGroupBeanDatas == null || DevSelectUi.this.mDatas == null) ? groupCount : groupCount + DevSelectUi.this.mDatas.size();
        }

        public int getGroupTypeCount() {
            return DevSelectUi.this.mGroupBeanDatas == null ? 1 : 2;
        }

        public int getChildrenCount(int groupPosition) {
            if (DevSelectUi.this.mChildDatas == null) {
                return 0;
            }
            if (groupPosition < DevSelectUi.this.mChildDatas.size()) {
                return DevSelectUi.this.mChildDatas.get(groupPosition).size();
            }
            return ((ArrayList) DevSelectUi.this.mDatas.get(groupPosition - DevSelectUi.this.mChildDatas.size())).size();
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(DevSelectUi.this.mContext, R.layout.exp_group, (ViewGroup) null);
                new GroupHolder(view);
            }
            GroupHolder groupHolder = (GroupHolder) view.getTag();
            groupHolder.mGroupIv.setImageResource(isExpanded ? R.drawable.arrow_down : R.drawable.arrow_right);
            if (DevSelectUi.this.mGroupDatas != null && DevSelectUi.this.mGroupDatas.size() > 0) {
                if (groupPosition < DevSelectUi.this.mGroupDatas.size()) {
                    groupHolder.mGroupTv.setText(DevSelectUi.this.mGroupDatas.get(groupPosition));
                } else {
                    groupHolder.mGroupTv.setText((CharSequence) DevSelectUi.this.mGroupNameDatas.get(groupPosition - DevSelectUi.this.mGroupDatas.size()));
                }
            }
            return view;
        }

        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(DevSelectUi.this.mContext, R.layout.exp_child, (ViewGroup) null);
                new ChildHolder(view);
            }
            ChildHolder childHolder = (ChildHolder) view.getTag();
            if (DevSelectUi.this.mGroupDatas != null && DevSelectUi.this.mGroupDatas.size() > 0) {
                if (groupPosition < DevSelectUi.this.mGroupDatas.size()) {
                    childHolder.mChildTv.setText((CharSequence) DevSelectUi.this.mChildDatas.get(groupPosition).get(childPosition));
                } else {
                    int groupOffset = groupPosition - DevSelectUi.this.mGroupDatas.size();
                    GroupBean groupChannel = (GroupBean) ((ArrayList) DevSelectUi.this.mDatas.get(groupOffset)).get(childPosition);
                    TextView textView = childHolder.mChildTv;
                    textView.setText(DevSelectUi.this.mResources.getString(R.string.channel) + " " + (groupChannel.dbChannel + 1));
                }
            }
            childHolder.mChildLl.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(DevSelectUi.this, RealPlayActivity.class);
                    if (groupPosition < DevSelectUi.this.mGroupDatas.size()) {
                        intent.putExtra("id", ((DevInfoBean) DevSelectUi.this.mDevInfoDatas.get(groupPosition)).mDevId);
                        intent.putExtra("channel", childPosition);
                        intent.putExtra("currentPosition", DevSelectUi.this.currentPosition);
                    } else {
                        int groupOffset = groupPosition - DevSelectUi.this.mGroupDatas.size();
                        GroupBean groupChannel = (GroupBean) ((ArrayList) DevSelectUi.this.mDatas.get(groupOffset)).get(childPosition);
                        intent.putExtra("id", groupChannel.dbId);
                        intent.putExtra("channel", groupChannel.dbChannel);
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
