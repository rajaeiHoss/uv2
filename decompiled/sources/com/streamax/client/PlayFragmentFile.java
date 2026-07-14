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

    public static PlayFragmentFile getInstance(String title, PlayActivity playActivity) {
        PlayFragmentFile playFragmentFile = new PlayFragmentFile();
        playFragmentFile.mTitle = title;
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
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                PlayFragmentFile.this.onFileItemClick(position);
            }
        });
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void onFileItemClick(int position) {
        if (position < this.mFileList.size()) {
            for (int fileIndex = 0; fileIndex < this.mFileList.size(); fileIndex++) {
                this.mFileList.get(fileIndex).mIsPlay = false;
            }
            this.mFileList.get(position).mIsPlay = true;
            this.mFileAdapter.notifyDataSetChanged();
            this.mActivity.playNewFile(this.mFileList.get(position));
        }
    }

    /* access modifiers changed from: private */
    public void onFileDownClick(int position) {
        if (Build.VERSION.SDK_INT >= 30 && !Environment.isExternalStorageManager()) {
            startActivity(new Intent("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION"));
        } else if (position < this.mFileList.size()) {
            int downNewFile = this.mActivity.downNewFile(this.mFileList.get(position));
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
        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
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

        public View getView(int position, View convertView, ViewGroup parent) {
            FileTag fileTag;
            if (convertView == null) {
                FileTag newFileTag = new FileTag();
                View inflate = PlayFragmentFile.this.mInflater.inflate(R.layout.play_file_list_item, (ViewGroup) null);
                newFileTag.ivIcon = (ImageView) inflate.findViewById(R.id.icon_view);
                newFileTag.tvTitle = (TextView) inflate.findViewById(R.id.title_view);
                newFileTag.tvSubTitle = (TextView) inflate.findViewById(R.id.subtitle_view);
                newFileTag.ivOpt = (ImageView) inflate.findViewById(R.id.icon_opt);
                newFileTag.ivOpt.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        PlayFragmentFile.this.onFileDownClick(((Integer) view.getTag()).intValue());
                    }
                });
                newFileTag.ivOpt.setTag(Integer.valueOf(position));
                inflate.setTag(newFileTag);
                fileTag = newFileTag;
                convertView = inflate;
            } else {
                fileTag = (FileTag) convertView.getTag();
                fileTag.ivOpt.setTag(Integer.valueOf(position));
            }
            convertView.setBackgroundColor(PlayFragmentFile.this.mFileList.get(position).mIsPlay ? Color.rgb(226, 241, Type.TKEY) : Color.rgb(255, 255, 255));
            fileTag.tvTitle.setText(PlayFragmentFile.this.mFileList.get(position).mTitle);
            fileTag.tvSubTitle.setText(PlayFragmentFile.this.mFileList.get(position).mSubTitle);
            return convertView;
        }
    }
}
