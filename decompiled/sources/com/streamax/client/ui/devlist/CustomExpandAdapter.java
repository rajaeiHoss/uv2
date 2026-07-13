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

    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    public long getGroupId(int groupPosition) {
        return 0;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public CustomExpandAdapter(ArrayList<ArrayList<String>> arrayList, List<Map<String, Object>> list, CustomExpandLv customExpandLv, List<ArrayList<String>> list2) {
        this.mGroupData = list;
        this.mChildData = arrayList;
        this.mListView = customExpandLv;
        this.mFlagData02 = list2;
        this.mChs = new int[list.size()][];
        for (int groupIndex = 0; groupIndex < list.size(); groupIndex++) {
            this.mChs[groupIndex] = new int[arrayList.get(groupIndex).size()];
        }
    }

    public Object getChild(int groupPosition, int childPosition) {
        return this.mChildData.get(groupPosition).get(childPosition);
    }

    public int getChildrenCount(int groupPosition) {
        return this.mChildData.get(groupPosition).size();
    }

    public Object getGroup(int groupPosition) {
        return this.mGroupData.get(groupPosition);
    }

    public int getGroupCount() {
        return this.mGroupData.size();
    }

    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
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
        childHolder.mTvChName.setText((CharSequence) this.mChildData.get(groupPosition).get(childPosition));
        if (this.mFlagData02.get(groupPosition).size() == this.mChs[groupPosition].length) {
            childHolder.mIvCb.setImageResource(R.drawable.checkbox_check);
        } else {
            if (this.mFlagData02.get(groupPosition).contains("" + childPosition)) {
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
                    groupClick(groupPosition);
                } else {
                    childClick(groupPosition, childPosition);
                }
                CustomExpandAdapter.this.notifyDataSetChanged();
            }

            private void groupClick(int groupPosition) {
                if (((ArrayList) CustomExpandAdapter.this.mFlagData02.get(groupPosition)).size() == CustomExpandAdapter.this.mChs[groupPosition].length) {
                    ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(groupPosition)).clear();
                    return;
                }
                for (int childIndex = 0; childIndex < CustomExpandAdapter.this.mChs[groupPosition].length; childIndex++) {
                    if (!((ArrayList) CustomExpandAdapter.this.mFlagData02.get(groupPosition)).contains("" + childIndex)) {
                        ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(groupPosition)).add("" + childIndex);
                    }
                }
            }

            private void childClick(int groupPosition, int childPosition) {
                if (((ArrayList) CustomExpandAdapter.this.mFlagData02.get(groupPosition)).contains("" + childPosition)) {
                    ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(groupPosition)).remove("" + childPosition);
                    return;
                }
                ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(groupPosition)).add("" + childPosition);
            }
        });
        return view;
    }

    public View getGroupView(final int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
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
        groupHolder.mTvDevName.setText((String) this.mGroupData.get(groupPosition).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
        groupHolder.mTvDevInfo.setText((String) this.mGroupData.get(groupPosition).get("info"));
        if (isExpanded) {
            groupHolder.mIvArrow.setImageResource(R.drawable.arrow_down);
        } else {
            groupHolder.mIvArrow.setImageResource(R.drawable.arrow_right);
        }
        if (this.mFlagData02.get(groupPosition).size() == this.mChs[groupPosition].length) {
            groupHolder.mIvCb.setImageResource(R.drawable.checkbox_check);
        } else {
            groupHolder.mIvCb.setImageResource(R.drawable.checkbox_uncheck);
        }
        groupHolder.mIvCb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (((ArrayList) CustomExpandAdapter.this.mFlagData02.get(groupPosition)).size() == CustomExpandAdapter.this.mChs[groupPosition].length) {
                    ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(groupPosition)).clear();
                } else {
                    for (int childIndex = 0; childIndex < CustomExpandAdapter.this.mChs[groupPosition].length; childIndex++) {
                        if (!((ArrayList) CustomExpandAdapter.this.mFlagData02.get(groupPosition)).contains("" + childIndex)) {
                            ((ArrayList) CustomExpandAdapter.this.mFlagData02.get(groupPosition)).add("" + childIndex);
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

    public int getHeaderState(int groupPosition, int childPosition) {
        if (childPosition == getChildrenCount(groupPosition) - 1) {
            return 2;
        }
        if (childPosition != -1 || this.mListView.isGroupExpanded(groupPosition)) {
            return 1;
        }
        return 0;
    }

    public void configHeader(View view, int groupPosition, int childPosition, int alpha) {
        ((TextView) view.findViewById(R.id.dev_head_tv_devname)).setText((String) this.mGroupData.get(groupPosition).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
        ((TextView) view.findViewById(R.id.dev_head_tv_devinfo)).setText((String) this.mGroupData.get(groupPosition).get("info"));
        ImageView imageView = (ImageView) view.findViewById(R.id.dev_head_iv_checkicon);
        if (this.mFlagData02.get(groupPosition).size() == this.mChs[groupPosition].length) {
            imageView.setImageResource(R.drawable.checkbox_check);
        } else {
            imageView.setImageResource(R.drawable.checkbox_uncheck);
        }
    }

    public List<ArrayList<String>> getDatas() {
        return this.mFlagData02;
    }

    public void setGroupClickState(int groupPosition, int status) {
        this.groupStatusMap.put(groupPosition, status);
    }

    public int getGroupClickState(int groupPosition) {
        if (this.groupStatusMap.keyAt(groupPosition) >= 0) {
            return this.groupStatusMap.get(groupPosition);
        }
        return 0;
    }
}
