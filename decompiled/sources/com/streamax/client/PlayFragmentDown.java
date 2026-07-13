package com.streamax.client;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.streamax.client.bean.PlayFileInfo;
import com.streamax.client.fragment.FragmentBase;
import com.streamax.client.ui.VideoPlayActivity;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// Fragment showing download list and progress for playback files.
public class PlayFragmentDown extends FragmentBase implements DownFileInterface {
    public PlayActivity mActivity;
    public DownAdapter mDownAdapter;
    public List<DownFileInfo> mFileList = new ArrayList();
    public ListView mListView;
    public View mRootView;
    public TimerTask mTask;
    public Timer mTimer = new Timer();
    public String mTitle;

    public static PlayFragmentDown getInstance(String title, PlayActivity playActivity) {
        PlayFragmentDown playFragmentDown = new PlayFragmentDown();
        playFragmentDown.mTitle = title;
        playFragmentDown.mActivity = playActivity;
        return playFragmentDown;
    }

    public View initView() {
        View rootView = this.mInflater.inflate(R.layout.play_fragment_down, (ViewGroup) null);
        this.mRootView = rootView;
        this.mListView = (ListView) rootView.findViewById(R.id.down_list);
        DownAdapter downAdapter = new DownAdapter();
        this.mDownAdapter = downAdapter;
        this.mListView.setAdapter(downAdapter);
        this.mBtnBack = (Button) this.mRootView.findViewById(R.id.play_file_button_back);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            }
        });
        TimerTask task = new TimerTask() {
            public void run() {
                PlayFragmentDown.this.mFileList.size();
            }
        };
        this.mTask = task;
        this.mTimer.schedule(task, 0, 1000);
    }

    public int downNewFile(PlayFileInfo playFileInfo) {
        int downloadState = 0;
        int pendingDownloadCount = 0;
        for (int fileIndex = 0; fileIndex < this.mFileList.size(); fileIndex++) {
            DownFileInfo existingFile = this.mFileList.get(fileIndex);
            if (isSameFileName(playFileInfo.name, existingFile.name)) {
                downloadState = existingFile.mIsDown ? 1 : 2;
                break;
            }
            if (!existingFile.mIsDown) {
                pendingDownloadCount++;
            }
            if (pendingDownloadCount >= 5) {
                downloadState = 3;
                break;
            }
        }
        if (downloadState != 0) {
            return downloadState;
        }
        DownFileInfo downFileInfo = new DownFileInfo();
        downFileInfo.mActivity = this.mActivity;
        downFileInfo.mApp = this.mActivity.mApp;
        downFileInfo.mHandler = this.mActivity.mHandler;
        downFileInfo.mTitle = playFileInfo.mTitle;
        downFileInfo.mSubTitle = playFileInfo.mSubTitle;
        downFileInfo.mIsDown = false;
        downFileInfo.mCur = 0;
        downFileInfo.mTotal = 0;
        downFileInfo.nDiskType = playFileInfo.nDiskType;
        downFileInfo.FileTime = playFileInfo.FileTime;
        downFileInfo.name = playFileInfo.name;
        downFileInfo.nFileSize = playFileInfo.nFileSize;
        downFileInfo.nChannel = playFileInfo.nChannel;
        downFileInfo.nType = playFileInfo.nType;
        downFileInfo.mChannel = playFileInfo.nChannel - 1;
        char[] chars = this.mActivity.getChars(playFileInfo.FileTime.getBytes());
        downFileInfo.mYear = Integer.parseInt(String.valueOf(chars, 0, 4));
        downFileInfo.mMonth = Integer.parseInt(String.valueOf(chars, 4, 2));
        downFileInfo.mDay = Integer.parseInt(String.valueOf(chars, 6, 2));
        downFileInfo.mHour = Integer.parseInt(String.valueOf(chars, 8, 2));
        downFileInfo.mMinute = Integer.parseInt(String.valueOf(chars, 10, 2));
        downFileInfo.mSecond = Integer.parseInt(String.valueOf(chars, 12, 2));
        downFileInfo.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(downFileInfo.mYear), Integer.valueOf(downFileInfo.mMonth), Integer.valueOf(downFileInfo.mDay), Integer.valueOf(downFileInfo.mHour), Integer.valueOf(downFileInfo.mMinute), Integer.valueOf(downFileInfo.mSecond)});
        downFileInfo.mEndTime = String.valueOf(chars, 15, 14);
        downFileInfo.SetDownFileInterface(this);
        this.mFileList.add(downFileInfo);
        this.mDownAdapter.notifyDataSetChanged();
        this.mActivity.setViewPager(1);
        downFileInfo.startDown();
        return downloadState;
    }

    public void DownFileCallback(int status, String fileName) {
        if (status == 0) {
            this.mDownAdapter.notifyDataSetChanged();
        } else if (1 != status && 2 != status && 3 == status) {
            int targetIndex = -1;
            int index = 0;
            while (true) {
                if (index >= this.mFileList.size()) {
                    break;
                } else if (isSameFileName(fileName, this.mFileList.get(index).name)) {
                    targetIndex = index;
                    break;
                } else {
                    index++;
                }
            }
            if (targetIndex >= 0) {
                this.mFileList.remove(targetIndex);
                this.mDownAdapter.notifyDataSetChanged();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onFileOptClick(int fileIndex) {
        if (fileIndex >= 0 && fileIndex < this.mFileList.size()) {
            if (!this.mFileList.get(fileIndex).mIsDown) {
                this.mFileList.get(fileIndex).stopDown(true);
                return;
            }
            String videoTitle = String.format("%s(%s)", new Object[]{this.mFileList.get(fileIndex).mTitle, this.mFileList.get(fileIndex).mSubTitle});
            new VideoPlayActivity.Builder().setVideoTitle(videoTitle).setVideoSource(this.mFileList.get(fileIndex).mDstFile).setActivityOrientation(1).start(this.mActivity);
        }
    }

    private boolean isSameFileName(String leftFileName, String rightFileName) {
        return leftFileName == null ? rightFileName == null : leftFileName.equals(rightFileName);
    }

    public class DownTag {
        public ImageView ivOpt;
        public int pos;
        public SeekBar seekBar;
        public TextView tvSubTitle;
        public TextView tvTitle;

        public DownTag() {
        }
    }

    public class DownAdapter extends BaseAdapter {
        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public DownAdapter() {
        }

        public int getCount() {
            if (PlayFragmentDown.this.mFileList == null) {
                return 0;
            }
            return PlayFragmentDown.this.mFileList.size();
        }

        public View getView(int position, View view, ViewGroup viewGroup) {
            DownTag downTag;
            View itemView;
            int downloadPercent = 100;
            int optionIcon = R.drawable.icon_cancel;
            if (view == null) {
                downTag = new DownTag();
                itemView = PlayFragmentDown.this.mInflater.inflate(R.layout.play_down_list_item, (ViewGroup) null);
                downTag.tvTitle = (TextView) itemView.findViewById(R.id.title_view);
                downTag.tvSubTitle = (TextView) itemView.findViewById(R.id.subtitle_view);
                downTag.ivOpt = (ImageView) itemView.findViewById(R.id.icon_opt);
                downTag.ivOpt.setImageResource(R.drawable.icon_cancel);
                downTag.ivOpt.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        PlayFragmentDown.this.onFileOptClick(((DownTag) view.getTag()).pos);
                    }
                });
                downTag.seekBar = (SeekBar) itemView.findViewById(R.id.remote_playback_progressbar);
                downTag.seekBar.setMax(100);
                downTag.seekBar.setProgress(0);
                downTag.seekBar.setSecondaryProgress(0);
                itemView.setTag(downTag);
            } else {
                itemView = view;
                downTag = (DownTag) view.getTag();
            }
            downTag.pos = position;
            downTag.tvTitle.setText(PlayFragmentDown.this.mFileList.get(position).mTitle);
            downTag.tvSubTitle.setText(PlayFragmentDown.this.mFileList.get(position).mSubTitle);
            long downloadedBytes = (long) PlayFragmentDown.this.mFileList.get(position).mCur;
            long totalBytes = (long) PlayFragmentDown.this.mFileList.get(position).mTotal;
            if (downloadedBytes < 0 || totalBytes <= 0) {
                downloadPercent = 0;
            } else if (downloadedBytes < totalBytes) {
                downloadPercent = (int) ((downloadedBytes * 100) / totalBytes);
            }
            downTag.seekBar.setProgress(downloadPercent);
            if (PlayFragmentDown.this.mFileList.get(position).mIsDown) {
                optionIcon = R.drawable.icon_play;
            }
            downTag.ivOpt.setTag(downTag);
            downTag.ivOpt.setImageResource(optionIcon);
            return itemView;
        }
    }
}
