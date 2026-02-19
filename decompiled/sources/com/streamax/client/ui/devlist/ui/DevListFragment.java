package com.streamax.client.ui.devlist.ui;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.plus.PlusShare;
import com.streamax.adapter.SuperBaseAdapter;
import com.streamax.client.DevInfoBean;
import com.streamax.client.MyApp;
import com.streamax.client.DbHelper;
import com.streamax.client.ui.devlist.DeleteDeviceEvent;
import com.streamax.client.ui.devlist.DevInfoEvent;
import com.streamax.client.ui.devlist.db.GroupBean;
import com.streamax.client.ui.devlist.db.GroupDao;
import com.streamax.client.ui.devlist.db.GroupDaoForNormal;
import com.streamax.client.ui.devlist.db.GroupDaoForServer;
import com.streamax.client.WebService;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.utils.FragmentUtils;
import com.streamax.utils.AppProxy;
import com.zycs.UView.R;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DevListFragment extends BaseFragment {
    private FragmentActivity mActivity;
    /* access modifiers changed from: private */
    public CustomAdapter mAdapter;
    /* access modifiers changed from: private */
    public MyApp mApp;
    public Button mBtnLeft;
    public Button mBtnRight;
    public ProgressBar mBusyBar;
    public LinearLayout mBusyLayout;
    private View mChildView;
    public GroupDao mDao;
    /* access modifiers changed from: private */
    public List<ArrayList<GroupBean>> mDatas = new ArrayList();
    /* access modifiers changed from: private */
    public DbHelper mDbHelper;
    /* access modifiers changed from: private */
    public List<Map<String, Object>> mDevDatas;
    private List<GroupBean> mDevGroupDatas;
    /* access modifiers changed from: private */
    public List<String> mDevIdDatas = new ArrayList();
    public boolean mFlag;
    private int mGroupCount;
    private ArrayList<GroupBean> mGroupDatas;
    private List<String> mGroupNameDatas = new ArrayList();
    /* access modifiers changed from: private */
    public int mId;
    /* access modifiers changed from: private */
    public ListView mListview;
    private LinearLayout mPopupView;
    /* access modifiers changed from: private */
    public PopupWindow mPopupWindow;
    private int mScreenWidth;
    private TextView mTvDown;
    private TextView mTvUp;
    public TextView mtvBusyInfo;

    /* access modifiers changed from: protected */
    public void init() {
        this.mDbHelper = new DbHelper(AppProxy.getContext(), DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    /* access modifiers changed from: private */
    public void initNativeDb() {
        this.mGroupCount = 0;
        this.mDatas.clear();
        this.mDevIdDatas.clear();
        this.mGroupNameDatas.clear();
        List<Map<String, Object>> list = this.mDevDatas;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.mDevDatas.size(); i++) {
                this.mDevIdDatas.add("" + this.mDevDatas.get(i).get("id"));
            }
        }
        if (MyApp.loginType == 0) {
            this.mDao = new GroupDaoForNormal();
        } else {
            this.mDao = new GroupDaoForServer();
        }
        List<GroupBean> groupDatas = this.mDao.getGroupDatas();
        this.mDevGroupDatas = groupDatas;
        if (groupDatas != null && groupDatas.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.mDevGroupDatas.size(); i2++) {
                GroupBean groupBean = this.mDevGroupDatas.get(i2);
                if (!this.mGroupNameDatas.contains(groupBean.dbGroupName)) {
                    this.mGroupCount++;
                    this.mGroupNameDatas.add(groupBean.dbGroupName);
                }
            }
            if (MyApp.loginType != 0) {
                List<GroupBean> groupDatasByName = this.mDao.getGroupDatasByName(this.mGroupNameDatas.get(0));
                for (int i3 = 0; i3 < groupDatasByName.size(); i3++) {
                    if (!arrayList.contains("" + groupDatasByName.get(i3).dbId)) {
                        arrayList.add("" + groupDatasByName.get(i3).dbId);
                    }
                }
                if (arrayList.size() > 0) {
                    for (int i4 = 0; i4 < this.mDevIdDatas.size(); i4++) {
                        if (!arrayList.contains("" + this.mDevIdDatas.get(i4))) {
                            for (int i5 = 0; i5 < this.mGroupNameDatas.size(); i5++) {
                                for (int i6 = 0; i6 < 4; i6++) {
                                    this.mDao.add(this.mGroupNameDatas.get(i5), ((Integer) this.mDevDatas.get(i4).get("id")).intValue(), (String) this.mDevDatas.get(i4).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE), i6, 0);
                                }
                            }
                        }
                    }
                    for (int i7 = 0; i7 < arrayList.size(); i7++) {
                        if (!this.mDevIdDatas.contains("" + ((String) arrayList.get(i7)))) {
                            this.mDao.delete(Integer.valueOf((String) arrayList.get(i7)).intValue());
                        }
                    }
                    for (int i8 = 0; i8 < this.mDevIdDatas.size(); i8++) {
                        this.mDao.updateDevNameById(((Integer) this.mDevDatas.get(i8).get("id")).intValue(), "" + this.mDevDatas.get(i8).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
                    }
                }
                this.mDevGroupDatas = this.mDao.getGroupDatas();
            }
            for (int i9 = 0; i9 < this.mGroupCount; i9++) {
                this.mGroupDatas = new ArrayList<>();
                for (int i10 = 0; i10 < this.mDevGroupDatas.size(); i10++) {
                    if (this.mDevGroupDatas.get(i10).dbGroupName.equals(this.mGroupNameDatas.get(i9))) {
                        this.mGroupDatas.add(this.mDevGroupDatas.get(i10));
                    }
                }
                this.mDatas.add(this.mGroupDatas);
            }
        }
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mChildView = this.mInflater.inflate(R.layout.dev_list, (ViewGroup) null);
        initChildView();
        return this.mChildView;
    }

    private void initChildView() {
        this.mListview = (ListView) this.mChildView.findViewById(R.id.dev_lv);
        this.mBusyLayout = (LinearLayout) this.mChildView.findViewById(R.id.devlist_busy);
        this.mBusyBar = (ProgressBar) this.mChildView.findViewById(R.id.devlist_pb_loading);
        TextView textView = (TextView) this.mChildView.findViewById(R.id.devlist_tv_loading);
        this.mtvBusyInfo = textView;
        textView.setText(R.string.updating);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        FragmentActivity activity = getActivity();
        this.mActivity = activity;
        this.mApp = (MyApp) activity.getApplication();
        getWidget();
        showLoading(getActivity());
        new Thread(new Runnable() {
            public void run() {
                DevListFragment devListFragment = DevListFragment.this;
                List unused = devListFragment.mDevDatas = devListFragment.getData();
                DevListFragment.this.initNativeDb();
                DevListFragment.this.mHandler.post(new Runnable() {
                    public void run() {
                        DevListFragment.this.hideLoading();
                        CustomAdapter unused = DevListFragment.this.mAdapter = new CustomAdapter(DevListFragment.this.mDevDatas);
                        DevListFragment.this.mListview.setAdapter(DevListFragment.this.mAdapter);
                    }
                });
            }
        }).start();
        initPopupWindow();
    }

    private void getWidget() {
        this.mBtnLeft = (Button) this.mActivity.findViewById(R.id.dev_title_btn_left);
        this.mBtnRight = (Button) this.mActivity.findViewById(R.id.dev_title_btn_right);
        if (MyApp.loginType == 0) {
            this.mBtnLeft.setText(R.string.edit);
            this.mBtnRight.setText(R.string.add);
            return;
        }
        this.mBtnLeft.setText(R.string.config_update);
        this.mBtnRight.setText(R.string.edit);
        this.mBtnRight.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mBtnLeft.setOnClickListener(this);
        this.mBtnRight.setOnClickListener(this);
        this.mTvUp.setOnClickListener(this);
        this.mTvDown.setOnClickListener(this);
        this.mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long j) {
                int itemViewType = DevListFragment.this.mAdapter.getItemViewType(i);
                if (itemViewType == 0) {
                    if (DevListFragment.this.mFlag) {
                        DevAddFragment devAddFragment = new DevAddFragment();
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("flag", true);
                        bundle.putString("devName", (String) ((Map) DevListFragment.this.mDevDatas.get(i)).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
                        devAddFragment.setArguments(bundle);
                        FragmentUtils.fragmentReplace(DevListFragment.this, devAddFragment, R.id.dev_fl);
                    } else if (!DevListFragment.this.mApp.mbSingle) {
                        DevListFragment devListFragment = DevListFragment.this;
                        int unused = devListFragment.mId = ((Integer) ((Map) devListFragment.mDevDatas.get(i)).get("id")).intValue();
                        EventBus.getDefault().post(new DevInfoEvent(DevListFragment.this.mId, ""));
                        DevListFragment.this.mApp.mainActivity.getTabHost().setCurrentTab(1);
                    }
                } else if (itemViewType != 1) {
                } else {
                    if (DevListFragment.this.mFlag) {
                        new Thread(new Runnable() {
                            public void run() {
                                DevListFragment.this.mBusyLayout.post(new Runnable() {
                                    public void run() {
                                        DevListFragment.this.mBusyLayout.setVisibility(0);
                                    }
                                });
                                List unused = DevListFragment.this.mDevDatas = DevListFragment.this.getData();
                                DevListFragment.this.mDao = new GroupDaoForServer();
                                DevListFragment.this.mDevIdDatas.clear();
                                if (DevListFragment.this.mDevDatas != null && DevListFragment.this.mDevDatas.size() > 0) {
                                    for (int i = 0; i < DevListFragment.this.mDevDatas.size(); i++) {
                                        List access$800 = DevListFragment.this.mDevIdDatas;
                                        access$800.add("" + ((Map) DevListFragment.this.mDevDatas.get(i)).get("id"));
                                    }
                                }
                                DevListFragment.this.initNativeDb();
                                DevListFragment.this.mBusyLayout.post(new Runnable() {
                                    public void run() {
                                        DevListFragment.this.mBusyLayout.setVisibility(8);
                                        String str = ((GroupBean) ((ArrayList) DevListFragment.this.mDatas.get(i - DevListFragment.this.mDevDatas.size())).get(0)).dbGroupName;
                                        DevGroupFragment devGroupFragment = new DevGroupFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putBoolean("flag", true);
                                        bundle.putString("groupName", str);
                                        devGroupFragment.setArguments(bundle);
                                        FragmentUtils.fragmentReplace(DevListFragment.this, devGroupFragment, R.id.dev_fl);
                                    }
                                });
                            }
                        }).start();
                        return;
                    }
                    EventBus.getDefault().post(new DevInfoEvent(-1, ((GroupBean) ((ArrayList) DevListFragment.this.mDatas.get(i - DevListFragment.this.mDevDatas.size())).get(0)).dbGroupName));
                    DevListFragment.this.mApp.mainActivity.getTabHost().setCurrentTab(1);
                }
            }
        });
    }

    private void initPopupWindow() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.mScreenWidth = displayMetrics.widthPixels;
        this.mPopupView = (LinearLayout) View.inflate(AppProxy.getContext(), R.layout.dev_popup_add, (ViewGroup) null);
        PopupWindow popupWindow = new PopupWindow(this.mPopupView, this.mScreenWidth / 3, -2, true);
        this.mPopupWindow = popupWindow;
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
        this.mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 4) {
                    return false;
                }
                DevListFragment.this.mPopupWindow.dismiss();
                return false;
            }
        });
        this.mPopupWindow.setTouchable(true);
        this.mPopupWindow.setOutsideTouchable(true);
        this.mTvUp = (TextView) this.mPopupView.findViewById(R.id.popup_tv_up);
        this.mTvDown = (TextView) this.mPopupView.findViewById(R.id.popup_tv_down);
        if (MyApp.loginType == 0) {
            this.mTvUp.setText(R.string.group_AddDevice);
            this.mTvDown.setText(R.string.group_AddGroup);
            return;
        }
        this.mTvUp.setText(R.string.group_Edit);
        this.mTvDown.setText(R.string.group_AddGroup);
    }

    /* access modifiers changed from: private */
    public List<Map<String, Object>> getData() {
        List<DevInfoBean> list;
        if (MyApp.loginType == 0) {
            list = this.mDbHelper.getlist();
        } else {
            list = this.mApp.mWebService.GetTerminalInfoAndroid(true);
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            DevInfoBean devInfoBean = list.get(i);
            HashMap hashMap = new HashMap();
            hashMap.put("id", Integer.valueOf(devInfoBean.mDevId));
            hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, devInfoBean.mDevName);
            hashMap.put("info", String.format("%s:%s,%s:%s", new Object[]{getString(R.string.deviceip), devInfoBean.mDevIp, getString(R.string.mediaport), Integer.valueOf(devInfoBean.mMediaPort).toString()}));
            hashMap.put("img", Integer.valueOf(R.drawable.dvronline));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private class CustomAdapter extends SuperBaseAdapter<Map<String, Object>> {
        public CustomAdapter(List<Map<String, Object>> list) {
            super(list);
        }

        public int getCount() {
            if (DevListFragment.this.mDevDatas == null) {
                return 0;
            }
            int size = DevListFragment.this.mDevDatas.size();
            if (DevListFragment.this.mDatas == null) {
                return size;
            }
            return size + DevListFragment.this.mDatas.size();
        }

        public int getItemViewType(int i) {
            return (DevListFragment.this.mDevDatas == null || i < DevListFragment.this.mDevDatas.size()) ? 0 : 1;
        }

        public int getViewTypeCount() {
            return (DevListFragment.this.mDatas == null || DevListFragment.this.mDatas.size() == 0) ? 1 : 2;
        }

        /* access modifiers changed from: protected */
        public View initConvertView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(AppProxy.getContext(), R.layout.dev_lv_item, (ViewGroup) null);
                new ViewHolder(view);
            }
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (i < DevListFragment.this.mDevDatas.size()) {
                viewHolder.mTvDevName.setText((String) ((Map) DevListFragment.this.mDevDatas.get(i)).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
                viewHolder.mTvDevInfo.setText((String) ((Map) DevListFragment.this.mDevDatas.get(i)).get("info"));
                viewHolder.mIvDevIcon.setImageResource(R.drawable.dvronline);
                viewHolder.mId = ((Integer) ((Map) DevListFragment.this.mDevDatas.get(i)).get("id")).intValue();
                viewHolder.mIvDevDelete.setTag(Integer.valueOf(viewHolder.mId));
                if (MyApp.loginType == 0 || !DevListFragment.this.mFlag) {
                    viewHolder.mTvDevName.setVisibility(0);
                    viewHolder.mTvDevInfo.setVisibility(0);
                    viewHolder.mIvDevIcon.setVisibility(0);
                    viewHolder.mIvDevDelete.setVisibility(0);
                } else {
                    viewHolder.mTvDevName.setVisibility(8);
                    viewHolder.mTvDevInfo.setVisibility(8);
                    viewHolder.mIvDevIcon.setVisibility(8);
                    viewHolder.mIvDevDelete.setVisibility(8);
                }
            } else {
                ArrayList arrayList = new ArrayList();
                if (DevListFragment.this.mDatas != null && DevListFragment.this.mDatas.size() > 0) {
                    viewHolder.mTvDevName.setText(((GroupBean) ((ArrayList) DevListFragment.this.mDatas.get(i - DevListFragment.this.mDevDatas.size())).get(0)).dbGroupName);
                    int size = ((ArrayList) DevListFragment.this.mDatas.get(i - DevListFragment.this.mDevDatas.size())).size();
                    for (int i2 = 0; i2 < size; i2++) {
                        GroupBean groupBean = (GroupBean) ((ArrayList) DevListFragment.this.mDatas.get(i - DevListFragment.this.mDevDatas.size())).get(i2);
                        if (groupBean != null && DevListFragment.this.mDevDatas != null && DevListFragment.this.mDevDatas.size() > 0 && groupBean.dbFlag == 1) {
                            arrayList.add(groupBean.dbDevName + "-" + (groupBean.dbChannel + 1));
                        }
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        if (i3 == arrayList.size() - 1) {
                            stringBuffer.append((String) arrayList.get(i3));
                        } else {
                            stringBuffer.append((String) arrayList.get(i3));
                            stringBuffer.append(", ");
                        }
                    }
                    viewHolder.mTvDevInfo.setText(stringBuffer.toString());
                    viewHolder.mIvDevIcon.setImageResource(R.drawable.arrow_right);
                }
            }
            if (MyApp.loginType == 0) {
                if (DevListFragment.this.mFlag) {
                    viewHolder.mIvDevDelete.setVisibility(0);
                } else {
                    viewHolder.mIvDevDelete.setVisibility(8);
                }
            } else if (!DevListFragment.this.mFlag || i < DevListFragment.this.mDevDatas.size()) {
                viewHolder.mIvDevDelete.setVisibility(8);
            } else {
                viewHolder.mIvDevDelete.setVisibility(0);
            }
            viewHolder.mIvDevDelete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (i < DevListFragment.this.mDevDatas.size()) {
                        new Thread(new Runnable() {
                            public void run() {
                                DevListFragment.this.mBusyLayout.post(new Runnable() {
                                    public void run() {
                                        DevListFragment.this.mBusyLayout.setVisibility(0);
                                    }
                                });
                                int unused = DevListFragment.this.mId = ((Integer) ((Map) DevListFragment.this.mDevDatas.get(i)).get("id")).intValue();
                                DevInfoBean query = DevListFragment.this.mDbHelper.query(DevListFragment.this.mId);
                                if (query.mPush == 1 && DevListFragment.this.mApp.mRegId != null && DevListFragment.this.mApp.mRegId.length() > 0) {
                                    if (DevListFragment.this.mApp.mWebService == null) {
                                        DevListFragment.this.mApp.mWebService = new WebService(MyApp.LastServerHostName, MyApp.username, MyApp.password);
                                    }
                                    DevListFragment.this.mApp.mWebService.Android_Delete(DevListFragment.this.mApp.mRegId, query.mDevName, MyApp.username, "0", Integer.valueOf(query.mDevId).toString());
                                }
                                DevListFragment.this.mDbHelper.delete(DevListFragment.this.mId);
                                List unused2 = DevListFragment.this.mDevDatas = DevListFragment.this.getData();
                                DevListFragment.this.mDao.delete(DevListFragment.this.mId);
                                DevListFragment.this.initNativeDb();
                                DevListFragment.this.mBusyLayout.post(new Runnable() {
                                    public void run() {
                                        DevListFragment.this.mAdapter.notifyDataSetChanged();
                                        DevListFragment.this.mBusyLayout.setVisibility(4);
                                        EventBus.getDefault().post(new DeleteDeviceEvent(DevListFragment.this.mId));
                                    }
                                });
                            }
                        }).start();
                        return;
                    }
                    DevListFragment.this.mDao.delete(((GroupBean) ((ArrayList) DevListFragment.this.mDatas.get(i - DevListFragment.this.mDevDatas.size())).get(0)).dbGroupName);
                    DevListFragment.this.initNativeDb();
                    DevListFragment.this.mAdapter.notifyDataSetChanged();
                }
            });
            return view;
        }

        class ViewHolder {
            public int mId;
            public ImageView mIvDevDelete;
            public ImageView mIvDevIcon;
            public TextView mTvDevInfo;
            public TextView mTvDevName;

            public ViewHolder(View view) {
                this.mIvDevIcon = (ImageView) view.findViewById(R.id.dev_item_iv_icon);
                this.mTvDevName = (TextView) view.findViewById(R.id.dev_item_tv_devname);
                this.mTvDevInfo = (TextView) view.findViewById(R.id.dev_item_tv_devinfo);
                this.mIvDevDelete = (ImageView) view.findViewById(R.id.dev_item_iv_delete);
                view.setTag(this);
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dev_title_btn_left /*2131362303*/:
                if (MyApp.loginType == 0) {
                    doEdit();
                    return;
                } else {
                    updateList();
                    return;
                }
            case R.id.dev_title_btn_right /*2131362304*/:
                addDevOrGroup();
                return;
            case R.id.popup_tv_down /*2131362894*/:
                this.mPopupWindow.dismiss();
                List<Map<String, Object>> data = getData();
                if (data != null && data.size() > 0) {
                    DevGroupFragment devGroupFragment = new DevGroupFragment();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("flag", false);
                    bundle.putString("groupName", "");
                    devGroupFragment.setArguments(bundle);
                    FragmentUtils.fragmentReplace(this, devGroupFragment, R.id.dev_fl);
                    return;
                }
                return;
            case R.id.popup_tv_up /*2131362895*/:
                this.mPopupWindow.dismiss();
                if (MyApp.loginType == 0) {
                    FragmentUtils.fragmentReplace(this, new DevAddFragment(), R.id.dev_fl);
                    return;
                }
                if (this.mFlag) {
                    this.mFlag = false;
                    this.mBtnLeft.setText(R.string.config_update);
                } else {
                    this.mFlag = true;
                    this.mBtnLeft.setText(R.string.done);
                }
                CustomAdapter customAdapter = this.mAdapter;
                if (customAdapter != null) {
                    customAdapter.notifyDataSetChanged();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void updateList() {
        if (!this.mFlag) {
            new Thread(new Runnable() {
                public void run() {
                    DevListFragment.this.mBusyLayout.post(new Runnable() {
                        public void run() {
                            DevListFragment.this.mBusyLayout.setVisibility(0);
                        }
                    });
                    DevListFragment devListFragment = DevListFragment.this;
                    List unused = devListFragment.mDevDatas = devListFragment.getData();
                    DevListFragment.this.mDao = new GroupDaoForServer();
                    DevListFragment.this.mDevIdDatas.clear();
                    if (DevListFragment.this.mDevDatas != null && DevListFragment.this.mDevDatas.size() > 0) {
                        for (int i = 0; i < DevListFragment.this.mDevDatas.size(); i++) {
                            List access$800 = DevListFragment.this.mDevIdDatas;
                            access$800.add("" + ((Map) DevListFragment.this.mDevDatas.get(i)).get("id"));
                        }
                    }
                    DevListFragment.this.initNativeDb();
                    DevListFragment.this.mBusyLayout.post(new Runnable() {
                        public void run() {
                            DevListFragment.this.mBusyLayout.setVisibility(8);
                            CustomAdapter unused = DevListFragment.this.mAdapter = new CustomAdapter(DevListFragment.this.mDevDatas);
                            DevListFragment.this.mListview.setAdapter(DevListFragment.this.mAdapter);
                        }
                    });
                }
            }).start();
            return;
        }
        this.mFlag = false;
        this.mAdapter.notifyDataSetChanged();
    }

    private void doEdit() {
        if (this.mFlag) {
            this.mFlag = false;
            this.mBtnLeft.setText(R.string.edit);
        } else {
            this.mFlag = true;
            this.mBtnLeft.setText(R.string.done);
        }
        CustomAdapter customAdapter = this.mAdapter;
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }

    private void addDevOrGroup() {
        if (this.mPopupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
        } else {
            this.mPopupWindow.showAsDropDown(this.mBtnRight);
        }
    }
}
