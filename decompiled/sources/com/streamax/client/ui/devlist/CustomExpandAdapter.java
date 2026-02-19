package com.streamax.client.ui.devlist;

import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.plus.PlusShare;
import com.streamax.client.ui.devlist.CustomExpandLv;
import com.streamax.utils.AppProxy;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomExpandAdapter extends BaseExpandableListAdapter implements CustomExpandLv.HeaderAdapter {
    protected static final String TAG = "001";
    private SparseIntArray groupStatusMap = new SparseIntArray();
    private ArrayList<ArrayList<String>> mChildData = new ArrayList<>();
    /* access modifiers changed from: private */
    public int[][] mChs;
    /* access modifiers changed from: private */
    public List<ArrayList<String>> mFlagData02;
    private List<Map<String, Object>> mGroupData = new ArrayList();
    /* access modifiers changed from: private */
    public CustomExpandLv mListView;
    public int mRawY;
    public int mT;

    public long getChildId(int i, int i2) {
        return 0;
    }

    public long getGroupId(int i) {
        return 0;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public CustomExpandAdapter(ArrayList<ArrayList<String>> arrayList, List<Map<String, Object>> list, CustomExpandLv customExpandLv, List<ArrayList<String>> list2) {
        this.mGroupData = list;
        this.mChildData = arrayList;
        this.mListView = customExpandLv;
        this.mFlagData02 = list2;
        this.mChs = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            this.mChs[i] = new int[arrayList.get(i).size()];
        }
    }

    public Object getChild(int i, int i2) {
        return this.mChildData.get(i).get(i2);
    }

    public int getChildrenCount(int i) {
        return this.mChildData.get(i).size();
    }

    public Object getGroup(int i) {
        return this.mGroupData.get(i);
    }

    public int getGroupCount() {
        return this.mGroupData.size();
    }

    public View getChildView(final int i, final int i2, boolean z, View view, ViewGroup viewGroup) {
        ChildHolder childHolder;
        if (view == null) {
            view = View.inflate(AppProxy.getContext(), R.layout.dev_child, (ViewGroup) null);
            childHolder = new ChildHolder();
            childHolder.mTvChName = (TextView) view.findViewById(R.id.dev_child_tv_name);
            childHolder.mIvCb = (ImageView) view.findViewById(R.id.dev_child_iv_cb);
            view.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) view.getTag();
        }
        childHolder.mTvChName.setText((CharSequence) this.mChildData.get(i).get(i2));
        if (this.mFlagData02.get(i).size() == this.mChs[i].length) {
            childHolder.mIvCb.setImageResource(R.drawable.checkbox_check);
        } else {
            if (this.mFlagData02.get(i).contains("" + i2)) {
                childHolder.mIvCb.setImageResource(R.drawable.checkbox_check);
            } else {
                childHolder.mIvCb.setImageResource(R.drawable.checkbox_uncheck);
            }
        }
        childHolder.mIvCb.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return false;
                }
                CustomExpandAdapter.this.mRawY = (int) motionEvent.getRawY();
                return false;
            }
        });
        childHolder.mIvCb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CustomExpandAdapter customExpandAdapter = CustomExpandAdapter.this;
                customExpandAdapter.mT = customExpandAdapter.mListView.mT;
                if (CustomExpandAdapter.this.mRawY <= CustomExpandAdapter.this.mT) {
                    gourpClick(i);
                } else {
                    childClick(i, i2);
                }
                CustomExpandAdapter.this.notifyDataSetChanged();
            }

            private void gourpClick(int i) {
                if (((ArrayList) CustomExpandAdapter.this.mFlagData02.get(i)).size() == CustomExpandAdapter.this.mChs[i].length) {
                    ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(i)).clear();
                    return;
                }
                for (int i2 = 0; i2 < CustomExpandAdapter.this.mChs[i].length; i2++) {
                    if (!((ArrayList) CustomExpandAdapter.this.mFlagData02.get(i)).contains("" + i2)) {
                        ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(i)).add("" + i2);
                    }
                }
            }

            private void childClick(int i, int i2) {
                if (((ArrayList) CustomExpandAdapter.this.mFlagData02.get(i)).contains("" + i2)) {
                    ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(i)).remove("" + i2);
                    return;
                }
                ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(i)).add("" + i2);
            }
        });
        return view;
    }

    public View getGroupView(final int i, boolean z, View view, ViewGroup viewGroup) {
        GroupHolder groupHolder;
        if (view == null) {
            view = View.inflate(AppProxy.getContext(), R.layout.dev_group, (ViewGroup) null);
            groupHolder = new GroupHolder();
            groupHolder.mIvArrow = (ImageView) view.findViewById(R.id.dev_group_iv_arrow);
            groupHolder.mIvCb = (ImageView) view.findViewById(R.id.dev_group_iv_cb);
            groupHolder.mTvDevName = (TextView) view.findViewById(R.id.dev_group_tv_devname);
            groupHolder.mTvDevInfo = (TextView) view.findViewById(R.id.dev_group_tv_devinfo);
            view.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) view.getTag();
        }
        groupHolder.mTvDevName.setText((String) this.mGroupData.get(i).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
        groupHolder.mTvDevInfo.setText((String) this.mGroupData.get(i).get("info"));
        if (z) {
            groupHolder.mIvArrow.setImageResource(R.drawable.arrow_down);
        } else {
            groupHolder.mIvArrow.setImageResource(R.drawable.arrow_right);
        }
        if (this.mFlagData02.get(i).size() == this.mChs[i].length) {
            groupHolder.mIvCb.setImageResource(R.drawable.checkbox_check);
        } else {
            groupHolder.mIvCb.setImageResource(R.drawable.checkbox_uncheck);
        }
        groupHolder.mIvCb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (((ArrayList) CustomExpandAdapter.this.mFlagData02.get(i)).size() == CustomExpandAdapter.this.mChs[i].length) {
                    ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(i)).clear();
                } else {
                    for (int i = 0; i < CustomExpandAdapter.this.mChs[i].length; i++) {
                        if (!((ArrayList) CustomExpandAdapter.this.mFlagData02.get(i)).contains("" + i)) {
                            ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(i)).add("" + i);
                        }
                    }
                }
                CustomExpandAdapter.this.notifyDataSetChanged();
            }
        });
        return view;
    }

    class GroupHolder {
        public ImageView mIvArrow;
        public ImageView mIvCb;
        public TextView mTvDevInfo;
        public TextView mTvDevName;

        GroupHolder() {
        }
    }

    class ChildHolder {
        public ImageView mIvCb;
        public TextView mTvChName;

        ChildHolder() {
        }
    }

    public int getHeaderState(int i, int i2) {
        if (i2 == getChildrenCount(i) - 1) {
            return 2;
        }
        if (i2 != -1 || this.mListView.isGroupExpanded(i)) {
            return 1;
        }
        return 0;
    }

    public void configHeader(View view, int i, int i2, int i3) {
        ((TextView) view.findViewById(R.id.dev_head_tv_devname)).setText((String) this.mGroupData.get(i).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
        ((TextView) view.findViewById(R.id.dev_head_tv_devinfo)).setText((String) this.mGroupData.get(i).get("info"));
        ImageView imageView = (ImageView) view.findViewById(R.id.dev_head_iv_checkicon);
        if (this.mFlagData02.get(i).size() == this.mChs[i].length) {
            imageView.setImageResource(R.drawable.checkbox_check);
        } else {
            imageView.setImageResource(R.drawable.checkbox_uncheck);
        }
    }

    public List<ArrayList<String>> getDatas() {
        return this.mFlagData02;
    }

    public void setGroupClickState(int i, int i2) {
        this.groupStatusMap.put(i, i2);
    }

    public int getGroupClickState(int i) {
        if (this.groupStatusMap.keyAt(i) >= 0) {
            return this.groupStatusMap.get(i);
        }
        return 0;
    }
}
