package com.streamax.client;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.dvr.avstream.AuTrack;
import com.dvr.net.DvrNet;
import com.dvr.net.MessageCallback;
import com.dvr.net.MultiplaybackInterface;
import com.flyco.tablayout.SlidingTabLayout;
import com.hjq.toast.ToastUtils;
import com.streamax.client.bean.PlayFileInfo;
import com.streamax.client.ui.devlist.ClickFullEvent;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.utils.AppProxy;
import com.streamax.utils.StringUtils;
import com.streamax.utils.UiUtils;
import com.zycs.UView.R;
import de.greenrobot.event.EventBus;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

// Remote playback timeline player with search, download, and control tabs.
public class PlayActivity extends FragmentActivity implements View.OnClickListener, MultiplaybackInterface {
    private static final int REQUEST_CODE_MANAGE_EXTERNAL_STORAGE = 1;
    public static final String TAG = "PlayActivity";
    public boolean landscape = false;
    private PlayPagerAdapter mAdapter;
    public MyApp mApp;
    public AuTrack mAudioTrack = new AuTrack();
    public int mChannel;
    public PlayConnectRunnable mConnectRunnable = null;
    public LinearLayout mControlLayout;
    public int mDay;
    public DvrNet mDvrNet = null;
    public String mEndTime;
    public int mFileEndSec = 0;
    public String mFileEndTime;
    public PlayFileInfo mFileInfo;
    public int mFileStartSec = 0;
    public String mFileStartTime;
    public int mFileTimeSec = 1800;
    private PlayFragmentDown mFragmentDown;
    public Handler mHandler = AppProxy.getHandler();
    public Handler mHandlerMessage;
    public int mHour;
    public ImageView mImageFast;
    public ImageView mImageFull;
    public ImageView mImagePlay;
    public ImageView mImageSlow;
    public ImageView mImageSound;
    public ImageView mImageStop;
    public Date mLastActive = new Date();
    private List<Fragment> mListFragments;
    /* access modifiers changed from: private */
    public List<String> mListTitles;
    public MessageCallback mMessageCallback;
    public int mMinute;
    public int mMonth;
    public PlayFileRunnable mPlayFileRunnable = null;
    public LinearLayout mPlayLayout;
    public PlayPauseRunnable mPlayPauseRunnable = null;
    public PlaySeekRunnable mPlaySeekRunnable = null;
    public PlayStartRunnable mPlayStartRunnable = null;
    public PlayStopRunnable mPlayStopRunnable = null;
    public LinearLayout mProgressLayout;
    public View mRootView;
    public int mSecond;
    public SeekBar mSeekBar;
    public int mSpeed = 1;
    public String mStartTime;
    public RelativeLayout mTabLayout;
    public TimerTask mTask;
    public int mTimeValues;
    public Timer mTimer = new Timer();
    public FrameLayout mTitleLayout;
    public boolean mTracking;
    /* access modifiers changed from: private */
    public VideoGroup mVideoGroup;
    private ViewPager mViewPager;
    public int mYear;
    public boolean mbMute = false;
    public boolean mbPause = false;
    public boolean mbPlay = false;
    public TextView mtvEndTime;
    public TextView mtvPlayTime;
    public TextView mtvSpeed;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mApp = (MyApp) getApplication();
        View inflate = LayoutInflater.from(this).inflate(R.layout.playpage, (ViewGroup) null);
        this.mRootView = inflate;
        setContentView(inflate);
        VideoGroup videoGroup = (VideoGroup) this.mRootView.findViewById(R.id.play_videogroup);
        this.mVideoGroup = videoGroup;
        if (videoGroup != null) {
            videoGroup.setCanFull(true);
            this.mVideoGroup.LoadViews();
            this.mVideoGroup.SetInitMode(1);
            this.mVideoGroup.showFull(false);
        }
        this.mVideoGroup.setFocusState(0, false);
        this.mVideoGroup.setOsdState(0, false);
        this.mVideoGroup.setOnClickListener(this);
        this.mtvSpeed = (TextView) this.mRootView.findViewById(R.id.remote_playback_playrate_text);
        this.mtvPlayTime = (TextView) this.mRootView.findViewById(R.id.remote_playback_progress_start_text);
        this.mtvEndTime = (TextView) this.mRootView.findViewById(R.id.remote_playback_progress_end_text);
        SeekBar seekBar = (SeekBar) this.mRootView.findViewById(R.id.remote_playback_progressbar);
        this.mSeekBar = seekBar;
        seekBar.setMax(1440);
        this.mSeekBar.setProgress(0);
        this.mSeekBar.setSecondaryProgress(0);
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                PlayActivity.this.mTracking = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (PlayActivity.this.mbPlay) {
                    if (!PlayActivity.this.mbPause) {
                        PlayActivity playActivity = PlayActivity.this;
                        playActivity.seekTime(playActivity.mSeekBar.getMax(), PlayActivity.this.mSeekBar.getProgress());
                    } else {
                        return;
                    }
                }
                PlayActivity.this.mTracking = false;
            }
        });
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.multiplay_controlbar_play);
        this.mImagePlay = imageView;
        imageView.setOnClickListener(this);
        this.mImagePlay.setVisibility(8);
        this.mImagePlay.bringToFront();
        ImageView imageView2 = (ImageView) this.mRootView.findViewById(R.id.multiplay_controlbar_sound);
        this.mImageSound = imageView2;
        imageView2.setOnClickListener(this);
        ImageView imageView3 = (ImageView) this.mRootView.findViewById(R.id.multiplay_controlbar_slow);
        this.mImageSlow = imageView3;
        imageView3.setOnClickListener(this);
        ImageView imageView4 = (ImageView) this.mRootView.findViewById(R.id.multiplay_controlbar_stop);
        this.mImageStop = imageView4;
        imageView4.setOnClickListener(this);
        ImageView imageView5 = (ImageView) this.mRootView.findViewById(R.id.multiplay_controlbar_fast);
        this.mImageFast = imageView5;
        imageView5.setOnClickListener(this);
        ImageView imageView6 = (ImageView) this.mRootView.findViewById(R.id.multiplay_controlbar_full);
        this.mImageFull = imageView6;
        imageView6.setOnClickListener(this);
        this.mListTitles = new ArrayList();
        this.mListFragments = new ArrayList();
        String string = UiUtils.getString(R.string.file);
        this.mListTitles.add(string);
        this.mListFragments.add(PlayFragmentSearch.getInstance(string, this));
        String string2 = UiUtils.getString(R.string.download);
        this.mListTitles.add(string2);
        PlayFragmentDown instance = PlayFragmentDown.getInstance(string2, this);
        this.mFragmentDown = instance;
        this.mListFragments.add(instance);
        ViewPager viewPager = (ViewPager) this.mRootView.findViewById(R.id.view_pager);
        this.mViewPager = viewPager;
        PlayPagerAdapter playPagerAdapter = new PlayPagerAdapter(getSupportFragmentManager(), this.mListFragments);
        this.mAdapter = playPagerAdapter;
        viewPager.setAdapter(playPagerAdapter);
        ((SlidingTabLayout) this.mRootView.findViewById(R.id.tab_layout)).setViewPager(viewPager);
        this.mMessageCallback = new MessageCallback() {
            public void sendMessage(int i) {
                if (i == 0) {
                    Message message = new Message();
                    message.what = 0;
                    message.setTarget(PlayActivity.this.mHandlerMessage);
                    message.sendToTarget();
                }
            }
        };
        this.mHandlerMessage = new Handler() {
            public void handleMessage(Message message) {
                int i = message.what;
                super.handleMessage(message);
            }
        };
        TimerTask task = new TimerTask() {
            public void run() {
                PlayActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (new Date().getTime() - PlayActivity.this.mLastActive.getTime() >= 3000) {
                            PlayActivity.this.mImagePlay.setVisibility(8);
                        }
                    }
                });
            }
        };
        this.mTask = task;
        this.mTimer.schedule(task, 0, 1000);
        EventBus.getDefault().register(this);
        this.mTitleLayout = (FrameLayout) this.mRootView.findViewById(R.id.play_title);
        this.mPlayLayout = (LinearLayout) this.mRootView.findViewById(R.id.play_layout);
        this.mProgressLayout = (LinearLayout) this.mRootView.findViewById(R.id.play_progress);
        this.mControlLayout = (LinearLayout) this.mRootView.findViewById(R.id.play_control);
        this.mTabLayout = (RelativeLayout) this.mRootView.findViewById(R.id.play_tab);
        startConnect();
    }

    public void onEventMainThread(ClickGroupEvent clickGroupEvent) {
        if (this.mbPlay) {
            this.mLastActive = new Date();
            if (this.mImagePlay.getVisibility() == 0) {
                this.mImagePlay.setVisibility(8);
            } else {
                this.mImagePlay.setVisibility(0);
            }
        }
    }

    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.multiplay_controlbar_fast /*2131362748*/:
                if (this.mbPlay) {
                    int i = this.mSpeed;
                    if (i == 1) {
                        this.mSpeed = 2;
                    } else if (i == 2) {
                        this.mSpeed = 4;
                    } else if (i == 4) {
                        this.mSpeed = 8;
                    } else if (i == 8) {
                        this.mSpeed = 16;
                    } else if (i == 16) {
                        this.mSpeed = 32;
                    }
                    this.mDvrNet.MuitiPlaySetSpeed(this.mSpeed);
                    this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(this.mSpeed)}));
                    if (this.mSpeed != 1) {
                        this.mtvSpeed.setVisibility(0);
                        return;
                    } else {
                        this.mtvSpeed.setVisibility(8);
                        return;
                    }
                } else {
                    return;
                }
            case R.id.multiplay_controlbar_full /*2131362749*/:
                EventBus.getDefault().post(new ClickFullEvent(0));
                return;
            case R.id.multiplay_controlbar_play /*2131362750*/:
                if (this.mbPlay) {
                    startPause();
                    return;
                } else {
                    startPlay();
                    return;
                }
            case R.id.multiplay_controlbar_slow /*2131362751*/:
                if (this.mbPlay) {
                    int i2 = this.mSpeed;
                    if (i2 == 2) {
                        this.mSpeed = 1;
                    } else if (i2 == 4) {
                        this.mSpeed = 2;
                    } else if (i2 == 8) {
                        this.mSpeed = 4;
                    } else if (i2 == 16) {
                        this.mSpeed = 8;
                    } else if (i2 == 32) {
                        this.mSpeed = 16;
                    }
                    this.mDvrNet.MuitiPlaySetSpeed(this.mSpeed);
                    this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(this.mSpeed)}));
                    if (this.mSpeed != 1) {
                        this.mtvSpeed.setVisibility(0);
                        return;
                    } else {
                        this.mtvSpeed.setVisibility(8);
                        return;
                    }
                } else {
                    return;
                }
            case R.id.multiplay_controlbar_sound /*2131362752*/:
                if (this.mbPlay) {
                    if (this.mbMute) {
                        this.mbMute = false;
                        this.mImageSound.setImageResource(R.drawable.play_sound);
                    } else {
                        this.mbMute = true;
                        this.mImageSound.setImageResource(R.drawable.play_sound_mute);
                    }
                    this.mAudioTrack.SetMute(this.mbMute);
                    return;
                }
                return;
            case R.id.multiplay_controlbar_stop /*2131362753*/:
                endPlay();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void toastSf(int i) {
        ToastUtils.show((CharSequence) StringUtils.getString(Integer.valueOf(i)));
    }

    /* access modifiers changed from: protected */
    public void toastSf(String str) {
        ToastUtils.show((CharSequence) str);
    }

    public void startConnect() {
        this.mConnectRunnable = new PlayConnectRunnable(this.mApp.mDevInfo);
        new Thread(this.mConnectRunnable).start();
    }

    public void refreshPager(Fragment fragment) {
        this.mAdapter.replaceFragment(0, fragment);
    }

    public void setViewPager(int i) {
        this.mViewPager.setCurrentItem(i);
    }

    private class PlayPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragments;

        public int getItemPosition(Object obj) {
            return -2;
        }

        public PlayPagerAdapter(FragmentManager fragmentManager, List<Fragment> list) {
            super(fragmentManager);
            this.mFragments = list;
        }

        public int getCount() {
            return this.mFragments.size();
        }

        public CharSequence getPageTitle(int i) {
            return (CharSequence) PlayActivity.this.mListTitles.get(i);
        }

        public Fragment getItem(int i) {
            return this.mFragments.get(i);
        }

        public void replaceFragment(int i, Fragment fragment) {
            this.mFragments.set(i, fragment);
            notifyDataSetChanged();
        }
    }

    public class PlayConnectRunnable implements Runnable {
        public DevInfoBean info;

        PlayConnectRunnable(DevInfoBean devInfoBean) {
            this.info = devInfoBean;
        }

        public void run() {
            if (PlayActivity.this.mDvrNet == null) {
                DvrNet dvrNet = new DvrNet();
                Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(dvrNet, PlayActivity.this.mApp.mDevInfo, PlayActivity.this.mApp);
                int i = -1;
                if (connDeviceProxy != null) {
                    i = ((Integer) connDeviceProxy.get("errorcode")).intValue();
                }
                if (i != 0) {
                    PlayActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            PlayActivity.this.mConnectRunnable = null;
                        }
                    });
                    return;
                }
                PlayActivity.this.mDvrNet = dvrNet;
                PlayActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        PlayActivity.this.mConnectRunnable = null;
                    }
                });
            }
        }
    }

    private boolean checkPlayRunnable() {
        if (this.mDvrNet == null) {
            toastSf((int) R.string.connect_error);
            return false;
        } else if (this.mPlayFileRunnable != null) {
            toastSf((int) R.string.is_change_play);
            return false;
        } else if (this.mPlayStartRunnable != null) {
            toastSf((int) R.string.is_start_play);
            return false;
        } else if (this.mPlaySeekRunnable != null) {
            toastSf((int) R.string.is_seek_play);
            return false;
        } else if (this.mPlayPauseRunnable != null) {
            toastSf((int) R.string.is_pause_play);
            return false;
        } else if (this.mPlayStopRunnable == null) {
            return true;
        } else {
            toastSf((int) R.string.is_stop_play);
            return false;
        }
    }

    public class PlayStartRunnable implements Runnable {
        PlayStartRunnable() {
        }

        public void run() {
            if (PlayActivity.this.mDvrNet.MultiPlay(PlayActivity.this.mFileInfo.nDiskType, 1 << PlayActivity.this.mChannel, 1, PlayActivity.this.mStartTime, PlayActivity.this.mEndTime, PlayActivity.this.mFileInfo.name) != 0) {
                PlayActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        PlayActivity.this.mPlayStartRunnable = null;
                    }
                });
            } else {
                PlayActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        PlayActivity.this.mbPlay = true;
                        PlayActivity.this.mbPause = false;
                        PlayActivity.this.mSpeed = 1;
                        PlayActivity.this.mbMute = false;
                        PlayActivity.this.mDvrNet.MuitiPlaySetSpeed(1);
                        PlayActivity.this.mtvPlayTime.setText(PlayActivity.this.mFileStartTime);
                        PlayActivity.this.mSeekBar.setMax(PlayActivity.this.mFileTimeSec);
                        PlayActivity.this.mSeekBar.setProgress(0);
                        PlayActivity.this.mtvEndTime.setText(PlayActivity.this.mFileEndTime);
                        PlayActivity.this.mtvSpeed.setText("1X");
                        PlayActivity.this.mtvSpeed.setVisibility(8);
                        PlayActivity.this.mImagePlay.setImageResource(R.drawable.play_pause);
                        PlayActivity.this.mLastActive = new Date();
                        PlayActivity.this.mPlayStartRunnable = null;
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void startPlay() {
        this.mAudioTrack.mPlayer.play();
        this.mAudioTrack.SetMute(this.mbMute);
        this.mDvrNet.SetMultiplayInterface(this);
        this.mDvrNet.SetAudioInterface(this.mAudioTrack);
        this.mAudioTrack.SwitchChannels(this.mChannel);
        this.mDvrNet.setMessageCallback(this.mMessageCallback);
        this.mPlayStartRunnable = new PlayStartRunnable();
        new Thread(this.mPlayStartRunnable).start();
    }

    public char[] getChars(byte[] bArr) {
        Charset forName = Charset.forName("UTF-8");
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length);
        allocate.put(bArr);
        allocate.flip();
        return forName.decode(allocate).array();
    }

    public class PlayFileRunnable implements Runnable {
        PlayFileInfo mFile;
        boolean mPlay;

        PlayFileRunnable(boolean z, PlayFileInfo playFileInfo) {
            this.mPlay = z;
            this.mFile = playFileInfo;
        }

        public void run() {
            if (this.mPlay) {
                PlayActivity.this.mDvrNet.MultiPlayStop();
            }
            PlayActivity.this.mHandler.post(new Runnable() {
                public void run() {
                    PlayActivity.this.mFileInfo = PlayFileRunnable.this.mFile;
                    PlayActivity.this.mChannel = PlayFileRunnable.this.mFile.nChannel - 1;
                    char[] chars = PlayActivity.this.getChars(PlayFileRunnable.this.mFile.FileTime.getBytes());
                    PlayActivity.this.mYear = Integer.parseInt(String.valueOf(chars, 0, 4));
                    PlayActivity.this.mMonth = Integer.parseInt(String.valueOf(chars, 4, 2));
                    PlayActivity.this.mDay = Integer.parseInt(String.valueOf(chars, 6, 2));
                    PlayActivity.this.mHour = Integer.parseInt(String.valueOf(chars, 8, 2));
                    PlayActivity.this.mMinute = Integer.parseInt(String.valueOf(chars, 10, 2));
                    PlayActivity.this.mSecond = Integer.parseInt(String.valueOf(chars, 12, 2));
                    PlayActivity.this.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(PlayActivity.this.mYear), Integer.valueOf(PlayActivity.this.mMonth), Integer.valueOf(PlayActivity.this.mDay), Integer.valueOf(PlayActivity.this.mHour), Integer.valueOf(PlayActivity.this.mMinute), Integer.valueOf(PlayActivity.this.mSecond)});
                    PlayActivity.this.mEndTime = String.valueOf(chars, 15, 14);
                    PlayActivity.this.mFileStartTime = String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(PlayActivity.this.mHour), Integer.valueOf(PlayActivity.this.mMinute), Integer.valueOf(PlayActivity.this.mSecond)});
                    char[] chars2 = PlayActivity.this.getChars(PlayActivity.this.mEndTime.getBytes());
                    int parseInt = Integer.parseInt(String.valueOf(chars2, 8, 2));
                    int parseInt2 = Integer.parseInt(String.valueOf(chars2, 10, 2));
                    int parseInt3 = Integer.parseInt(String.valueOf(chars2, 12, 2));
                    PlayActivity.this.mFileEndTime = String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(parseInt), Integer.valueOf(parseInt2), Integer.valueOf(parseInt3)});
                    PlayActivity.this.mFileStartSec = (PlayActivity.this.mHour * 3600) + (PlayActivity.this.mMinute * 60) + PlayActivity.this.mSecond;
                    PlayActivity.this.mFileEndSec = (parseInt * 3600) + (parseInt2 * 60) + parseInt3;
                    PlayActivity.this.mFileTimeSec = 0;
                    if (PlayActivity.this.mFileEndSec > PlayActivity.this.mFileStartSec) {
                        PlayActivity.this.mFileTimeSec = PlayActivity.this.mFileEndSec - PlayActivity.this.mFileStartSec;
                    }
                    if (PlayFileRunnable.this.mPlay) {
                        PlayActivity.this.mbPlay = false;
                        PlayActivity.this.mbPause = false;
                        PlayActivity.this.mSpeed = 1;
                        PlayActivity.this.mbMute = false;
                        PlayActivity.this.mDvrNet.MuitiPlaySetSpeed(1);
                        PlayActivity.this.mtvPlayTime.setText(PlayActivity.this.mFileStartTime);
                        PlayActivity.this.mtvEndTime.setText(PlayActivity.this.mFileEndTime);
                        PlayActivity.this.mSeekBar.setMax(PlayActivity.this.mFileTimeSec);
                        PlayActivity.this.mSeekBar.setProgress(0);
                        PlayActivity.this.mtvSpeed.setText("1X");
                        PlayActivity.this.mtvSpeed.setVisibility(8);
                        PlayActivity.this.mImagePlay.setImageResource(R.drawable.play_start);
                    }
                    PlayActivity.this.startPlay();
                    PlayActivity.this.mPlayFileRunnable = null;
                }
            });
        }
    }

    public void playNewFile(PlayFileInfo playFileInfo) {
        if (playFileInfo.FileTime.length() >= 29 && checkPlayRunnable()) {
            this.mPlayFileRunnable = new PlayFileRunnable(this.mbPlay, playFileInfo);
            new Thread(this.mPlayFileRunnable).start();
        }
    }

    public int downNewFile(PlayFileInfo playFileInfo) {
        return this.mFragmentDown.downNewFile(playFileInfo);
    }

    public class PlaySeekRunnable implements Runnable {
        public String mSeekTime;

        PlaySeekRunnable(String str) {
            this.mSeekTime = str;
        }

        public void run() {
            PlayActivity.this.mDvrNet.MultiPlaySeek(this.mSeekTime);
            PlayActivity.this.mHandler.post(new Runnable() {
                public void run() {
                    PlayActivity.this.mPlaySeekRunnable = null;
                }
            });
        }
    }

    public void seekTime(int i, int i2) {
        if (checkPlayRunnable()) {
            int i3 = this.mFileStartSec + i2;
            int i4 = i3 / 3600;
            this.mPlaySeekRunnable = new PlaySeekRunnable(String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(i4), Integer.valueOf((i3 - (i4 * 3600)) / 60), Integer.valueOf(i3 % 60)}));
            new Thread(this.mPlaySeekRunnable).start();
        }
    }

    public class PlayPauseRunnable implements Runnable {
        public boolean mPause;

        PlayPauseRunnable(boolean z) {
            this.mPause = z;
        }

        public void run() {
            PlayActivity.this.mDvrNet.MultiPlayPause(this.mPause);
            PlayActivity.this.mHandler.post(new Runnable() {
                public void run() {
                    PlayActivity.this.mPlayPauseRunnable = null;
                }
            });
        }
    }

    public void startPause() {
        if (checkPlayRunnable()) {
            this.mLastActive = new Date();
            this.mImagePlay.setVisibility(0);
            boolean z = !this.mbPause;
            this.mbPause = z;
            if (z) {
                this.mImagePlay.setImageResource(R.drawable.play_start);
            } else {
                this.mImagePlay.setImageResource(R.drawable.play_pause);
            }
            this.mPlayPauseRunnable = new PlayPauseRunnable(this.mbPause);
            new Thread(this.mPlayPauseRunnable).start();
        }
    }

    public class PlayStopRunnable implements Runnable {
        PlayStopRunnable() {
        }

        public void run() {
            PlayActivity.this.mDvrNet.MultiPlayStop();
            PlayActivity.this.mHandler.post(new Runnable() {
                public void run() {
                    PlayActivity.this.mAudioTrack.mPlayer.Stop();
                    PlayActivity.this.mSeekBar.setProgress(0);
                    PlayActivity.this.mtvPlayTime.setText(PlayActivity.this.mFileEndTime);
                    PlayActivity.this.mtvEndTime.setText(PlayActivity.this.mFileEndTime);
                    PlayActivity.this.mSeekBar.setMax(PlayActivity.this.mFileTimeSec);
                    PlayActivity.this.mSeekBar.setProgress(0);
                    PlayActivity.this.mtvSpeed.setText("1X");
                    PlayActivity.this.mtvSpeed.setVisibility(8);
                    PlayActivity.this.mbPlay = false;
                    PlayActivity.this.mbPause = false;
                    PlayActivity.this.mbMute = false;
                    PlayActivity.this.mImagePlay.setVisibility(0);
                    PlayActivity.this.mImagePlay.setImageResource(R.drawable.play_start);
                    PlayActivity.this.mVideoGroup.ClearViews();
                    PlayActivity.this.mPlayStopRunnable = null;
                }
            });
        }
    }

    private void stopPlay() {
        if (checkPlayRunnable()) {
            this.mPlayStopRunnable = new PlayStopRunnable();
            new Thread(this.mPlayStopRunnable).start();
        }
    }

    public void WriteIn(int i, int i2, byte[] bArr, int i3, int i4) {
        VideoView videoView;
        if (this.mVideoGroup.GetLayoutMode() == 1) {
            videoView = this.mVideoGroup.getVideoView(0);
        } else {
            videoView = this.mVideoGroup.getVideoView(i);
        }
        if (videoView != null) {
            videoView.writeIn(bArr, i3, i4);
        }
    }

    public void MultiplayCallback(long j, int i, int i2, int i3, byte[] bArr, int i4, int i5, int i6) {
        WriteIn(i, i3, bArr, i4, i5);
        if (i6 != 0) {
            this.mTimeValues = i6;
            this.mtvPlayTime.post(new Runnable() {
                public void run() {
                    if (PlayActivity.this.mTimeValues >= PlayActivity.this.mFileStartSec && PlayActivity.this.mTimeValues <= PlayActivity.this.mFileEndSec) {
                        int i = PlayActivity.this.mTimeValues / 3600;
                        PlayActivity.this.mtvPlayTime.setText(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf((PlayActivity.this.mTimeValues - (i * 3600)) / 60), Integer.valueOf(PlayActivity.this.mTimeValues % 60)}));
                        int i2 = PlayActivity.this.mTimeValues - PlayActivity.this.mFileStartSec;
                        if (i2 != PlayActivity.this.mSeekBar.getProgress() && !PlayActivity.this.mTracking) {
                            PlayActivity.this.mSeekBar.setProgress(i2);
                        }
                    }
                }
            });
        }
    }

    public void setVideoGroupMax(boolean z) {
        if (z) {
            getWindow().getDecorView().setSystemUiVisibility(4);
            this.mTitleLayout.setVisibility(8);
            this.mPlayLayout.getLayoutParams().height = -1;
            this.mVideoGroup.showFull(true);
            this.mProgressLayout.setVisibility(8);
            this.mControlLayout.setVisibility(8);
            this.mTabLayout.setVisibility(8);
            return;
        }
        getWindow().getDecorView().setSystemUiVisibility(0);
        this.mTitleLayout.setVisibility(0);
        ViewGroup.LayoutParams layoutParams = this.mPlayLayout.getLayoutParams();
        layoutParams.height = (int) TypedValue.applyDimension(1, (float) 220, getResources().getDisplayMetrics());
        this.mPlayLayout.setLayoutParams(layoutParams);
        this.mVideoGroup.showFull(false);
        this.mProgressLayout.setVisibility(0);
        this.mControlLayout.setVisibility(0);
        this.mTabLayout.setVisibility(0);
    }

    public void onEventMainThread(ClickFullEvent clickFullEvent) {
        boolean z = !this.landscape;
        this.landscape = z;
        if (z) {
            setRequestedOrientation(0);
            setVideoGroupMax(true);
            return;
        }
        setRequestedOrientation(1);
        setVideoGroupMax(false);
    }

    public void endPlay() {
        if (this.mbPlay) {
            stopPlay();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        endPlay();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        endPlay();
        super.onStop();
    }
}
