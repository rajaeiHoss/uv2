package com.streamax.client;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.streamax.utils.LogUtils;
import com.zycs.UView.R;

public class LocalRecordFileList extends ListView {
    public static boolean bEdit = false;
    public static PlaybackActivity playbackActivity;
    public float DownX;
    public float UpX;
    public RecordFileAdapter mAdapter;
    public Context mContext;
    public GestureDetector mGestureDetector;
    public ImageView mSelImageView;
    public ListView mlistView;

    public static class ViewHolder {
        public ImageView img;
        public CheckBox ivDel;
        public String path;
        public int position;
        public TextView tvFileName;
        public TextView tvFileSize;
        public TextView tvInfo;
    }

    public LocalRecordFileList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.DownX = 0.0f;
        this.UpX = 0.0f;
        this.mContext = context;
        this.mlistView = this;
    }

    public LocalRecordFileList(Context context) {
        this(context, (AttributeSet) null);
    }

    public void setEditState(boolean z) {
        bEdit = z;
        refreshAdapter();
    }

    public void deleteSelectFile() {
        FileUtils.deleteSelectItems();
    }

    public boolean getEditState() {
        return bEdit;
    }

    public void refreshFiles() {
        FileUtils.refreshFiles(this);
    }

    public void refreshAdapter() {
        if (this.mAdapter == null) {
            LogUtils.log(getClass(), "refreshAdapter1");
            RecordFileAdapter recordFileAdapter = new RecordFileAdapter(this.mContext);
            this.mAdapter = recordFileAdapter;
            setAdapter(recordFileAdapter);
            return;
        }
        int count = FileUtils.getCount();
        Class<?> cls = getClass();
        LogUtils.log(cls, "count:" + count + ",refreshAdapter2");
        this.mAdapter.notifyDataSetChanged();
    }

    public static class RecordFileAdapter extends BaseAdapter {
        public Context mContext;
        private LayoutInflater mInflater;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public RecordFileAdapter(Context context) {
            this.mContext = context;
            this.mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            Class<?> cls = getClass();
            LogUtils.log(cls, "getCount():" + FileUtils.getCount());
            return FileUtils.getCount();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = this.mInflater.inflate(R.layout.localplaybacklistitem, (ViewGroup) null);
                viewHolder.img = (ImageView) view2.findViewById(R.id.localplaybackitem_image);
                viewHolder.tvFileName = (TextView) view2.findViewById(R.id.localplaybackitem_text);
                viewHolder.tvInfo = (TextView) view2.findViewById(R.id.localplaybackitem_info);
                viewHolder.ivDel = (CheckBox) view2.findViewById(R.id.localplaybackitem_delete);
                viewHolder.tvFileSize = (TextView) view2.findViewById(R.id.localplaybackitem_size);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            FileUtils.getAt(i);
            viewHolder.position = i;
            viewHolder.img.setImageResource(R.drawable.record_file);
            viewHolder.tvFileName.setText(FileUtils.getAt(i).get("filename").toString());
            viewHolder.tvInfo.setText(FileUtils.getAt(i).get("info").toString());
            viewHolder.tvFileSize.setText(FileUtils.getAt(i).get("size").toString());
            viewHolder.path = FileUtils.getAt(i).get("path").toString();
            viewHolder.ivDel.setChecked(Boolean.parseBoolean(FileUtils.getAt(i).get("select").toString()));
            viewHolder.ivDel.setTag(Integer.valueOf(i));
            if (LocalRecordFileList.bEdit) {
                viewHolder.ivDel.setVisibility(0);
            } else {
                viewHolder.ivDel.setVisibility(8);
            }
            viewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FileUtils.setCheckState(((Integer) view.getTag()).intValue(), ((CheckBox) view).isChecked());
                }
            });
            return view2;
        }
    }

    public void setActivity(PlaybackActivity playbackActivity2) {
        playbackActivity = playbackActivity2;
    }
}
