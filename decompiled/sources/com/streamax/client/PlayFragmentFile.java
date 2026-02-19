package com.streamax.client;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.dvr.net.RemoteFileInfo;
import com.streamax.client.bean.PlayFileInfo;
import com.streamax.client.fragment.FragmentBase;
import com.zycs.UView.R;
import java.util.List;
import org.xbill.DNS.Type;

// Fragment showing remote file list for playback.
public class PlayFragmentFile extends FragmentBase implements View.OnClickListener {
    public PlayActivity mActivity;
    public Button mBtnBack;
    public FileAdapter mFileAdapter;
    public List<PlayFileInfo> mFileList;
    public ListView mListView;
    public View mRootView;
    public String mTitle;

    public class FileInfo extends RemoteFileInfo {
        public boolean isPlay;

        public FileInfo() {
        }
    }

    public static PlayFragmentFile getInstance(String str, PlayActivity playActivity) {
        PlayFragmentFile playFragmentFile = new PlayFragmentFile();
        playFragmentFile.mTitle = str;
        playFragmentFile.mActivity = playActivity;
        return playFragmentFile;
    }

    public View initView() {
        View inflate = this.mInflater.inflate(R.layout.play_fragment_file, (ViewGroup) null);
        this.mRootView = inflate;
        this.mListView = (ListView) inflate.findViewById(R.id.file_list);
        FileAdapter fileAdapter = new FileAdapter();
        this.mFileAdapter = fileAdapter;
        this.mListView.setAdapter(fileAdapter);
        this.mBtnBack = (Button) this.mRootView.findViewById(R.id.play_file_button_back);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                PlayFragmentFile.this.onFileItemClick(i);
            }
        });
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void onFileItemClick(int i) {
        if (i <= this.mFileList.size()) {
            for (int i2 = 0; i2 < this.mFileList.size(); i2++) {
                this.mFileList.get(i2).mIsPlay = false;
            }
            this.mFileList.get(i).mIsPlay = true;
            this.mFileAdapter.notifyDataSetChanged();
            this.mActivity.playNewFile(this.mFileList.get(i));
        }
    }

    /* access modifiers changed from: private */
    public void onFileDownClick(int i) {
        if (Build.VERSION.SDK_INT >= 30 && !Environment.isExternalStorageManager()) {
            startActivity(new Intent("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION"));
        } else if (i <= this.mFileList.size()) {
            int downNewFile = this.mActivity.downNewFile(this.mFileList.get(i));
            if (1 == downNewFile) {
                toastSf((int) R.string.file_is_downed);
            } else if (2 == downNewFile) {
                toastSf((int) R.string.file_is_downing);
            } else if (3 == downNewFile) {
                toastSf((int) R.string.max_file_down);
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.play_file_button_back) {
            prePage();
        }
    }

    public class FileTag {
        public ImageView ivIcon;
        public ImageView ivOpt;
        public TextView tvSubTitle;
        public TextView tvTitle;

        public FileTag() {
        }
    }

    public class FileAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public FileAdapter() {
        }

        public int getCount() {
            if (PlayFragmentFile.this.mFileList == null) {
                return 0;
            }
            return PlayFragmentFile.this.mFileList.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            FileTag fileTag;
            if (view == null) {
                FileTag fileTag2 = new FileTag();
                View inflate = PlayFragmentFile.this.mInflater.inflate(R.layout.play_file_list_item, (ViewGroup) null);
                fileTag2.ivIcon = (ImageView) inflate.findViewById(R.id.icon_view);
                fileTag2.tvTitle = (TextView) inflate.findViewById(R.id.title_view);
                fileTag2.tvSubTitle = (TextView) inflate.findViewById(R.id.subtitle_view);
                fileTag2.ivOpt = (ImageView) inflate.findViewById(R.id.icon_opt);
                fileTag2.ivOpt.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        PlayFragmentFile.this.onFileDownClick(((Integer) view.getTag()).intValue());
                    }
                });
                fileTag2.ivOpt.setTag(Integer.valueOf(i));
                inflate.setTag(fileTag2);
                View view2 = inflate;
                fileTag = fileTag2;
                view = view2;
            } else {
                fileTag = (FileTag) view.getTag();
                fileTag.ivOpt.setTag(Integer.valueOf(i));
            }
            view.setBackgroundColor(PlayFragmentFile.this.mFileList.get(i).mIsPlay ? Color.rgb(226, 241, Type.TKEY) : Color.rgb(255, 255, 255));
            fileTag.tvTitle.setText(PlayFragmentFile.this.mFileList.get(i).mTitle);
            fileTag.tvSubTitle.setText(PlayFragmentFile.this.mFileList.get(i).mSubTitle);
            return view;
        }
    }
}
