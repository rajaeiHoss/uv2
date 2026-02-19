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

    public static PlayFragmentDown getInstance(String str, PlayActivity playActivity) {
        PlayFragmentDown playFragmentDown = new PlayFragmentDown();
        playFragmentDown.mTitle = str;
        playFragmentDown.mActivity = playActivity;
        return playFragmentDown;
    }

    public View initView() {
        View inflate = this.mInflater.inflate(R.layout.play_fragment_down, (ViewGroup) null);
        this.mRootView = inflate;
        this.mListView = (ListView) inflate.findViewById(R.id.down_list);
        DownAdapter downAdapter = new DownAdapter();
        this.mDownAdapter = downAdapter;
        this.mListView.setAdapter(downAdapter);
        this.mBtnBack = (Button) this.mRootView.findViewById(R.id.play_file_button_back);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
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
        int i;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= this.mFileList.size()) {
                i = 0;
                break;
            } else if (playFileInfo.name == this.mFileList.get(i2).name) {
                i = this.mFileList.get(i2).mIsDown ? 1 : 2;
            } else {
                if (!this.mFileList.get(i2).mIsDown) {
                    i3++;
                }
                if (i3 >= 5) {
                    i = 3;
                    break;
                }
                i2++;
            }
        }
        if (i != 0) {
            return i;
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
        return i;
    }

    public void DownFileCallback(int i, String str) {
        if (i == 0) {
            this.mDownAdapter.notifyDataSetChanged();
        } else if (1 != i && 2 != i && 3 == i) {
            int i2 = -1;
            int i3 = 0;
            while (true) {
                if (i3 >= this.mFileList.size()) {
                    break;
                } else if (str == this.mFileList.get(i3).name) {
                    i2 = i3;
                    break;
                } else {
                    i3++;
                }
            }
            if (i2 >= 0) {
                this.mFileList.remove(i2);
                this.mDownAdapter.notifyDataSetChanged();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onFileOptClick(int i) {
        if (i <= this.mFileList.size()) {
            if (!this.mFileList.get(i).mIsDown) {
                this.mFileList.get(i).stopDown(true);
                return;
            }
            String format = String.format("%s(%s)", new Object[]{this.mFileList.get(i).mTitle, this.mFileList.get(i).mSubTitle});
            new VideoPlayActivity.Builder().setVideoTitle(format).setVideoSource(this.mFileList.get(i).mDstFile).setActivityOrientation(1).start(this.mActivity);
        }
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
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
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

        public View getView(int i, View view, ViewGroup viewGroup) {
            DownTag downTag;
            View view2;
            int i2 = 100;
            int i3 = R.drawable.icon_cancel;
            if (view == null) {
                downTag = new DownTag();
                view2 = PlayFragmentDown.this.mInflater.inflate(R.layout.play_down_list_item, (ViewGroup) null);
                downTag.tvTitle = (TextView) view2.findViewById(R.id.title_view);
                downTag.tvSubTitle = (TextView) view2.findViewById(R.id.subtitle_view);
                downTag.ivOpt = (ImageView) view2.findViewById(R.id.icon_opt);
                downTag.ivOpt.setImageResource(R.drawable.icon_cancel);
                downTag.ivOpt.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        PlayFragmentDown.this.onFileOptClick(((DownTag) view.getTag()).pos);
                    }
                });
                downTag.seekBar = (SeekBar) view2.findViewById(R.id.remote_playback_progressbar);
                downTag.seekBar.setMax(100);
                downTag.seekBar.setProgress(0);
                downTag.seekBar.setSecondaryProgress(0);
                view2.setTag(downTag);
            } else {
                view2 = view;
                downTag = (DownTag) view.getTag();
            }
            downTag.pos = i;
            downTag.tvTitle.setText(PlayFragmentDown.this.mFileList.get(i).mTitle);
            downTag.tvSubTitle.setText(PlayFragmentDown.this.mFileList.get(i).mSubTitle);
            long j = (long) PlayFragmentDown.this.mFileList.get(i).mCur;
            long j2 = (long) PlayFragmentDown.this.mFileList.get(i).mTotal;
            if (j < 0 || j2 <= 0) {
                i2 = 0;
            } else if (j < j2) {
                i2 = (int) ((j * 100) / j2);
            }
            downTag.seekBar.setProgress(i2);
            if (PlayFragmentDown.this.mFileList.get(i).mIsDown) {
                i3 = R.drawable.icon_play;
            }
            downTag.ivOpt.setTag(downTag);
            downTag.ivOpt.setImageResource(i3);
            return view2;
        }
    }
}
