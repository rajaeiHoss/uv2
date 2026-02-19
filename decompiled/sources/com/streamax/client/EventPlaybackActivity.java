package com.streamax.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.dvr.avstream.AuTrack;
import com.dvr.net.BitmapFileInfo;
import com.dvr.net.DvrNet;
import com.dvr.net.MessageCallback;
import com.dvr.net.MultiplaybackInterface;
import com.streamax.Configs;
import com.streamax.config.db.DatebaseConfig;
import com.streamax.proxy.ConnDeviceProxy;
import com.zycs.UView.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Playback activity for event recordings.
public class EventPlaybackActivity extends Activity implements MultiplaybackInterface {
    private final String TAG = "EventPlaybackActivity";
    /* access modifiers changed from: private */
    public ImageView image;
    public int mAlarmType;
    public MyApp mApp = ((MyApp) getApplication());
    public AuTrack mAudioTrack = new AuTrack();
    public int mBaseValues = 0;
    public ProgressBar mBusyProgressBar;
    public int mChannel;
    public Context mContext;
    public int mDay;
    public DevInfoBean mDevInfo;
    public DvrNet mDvrNet;
    public Handler mHandlerMessage;
    public int mHour;
    public ImageView mImageCapture;
    public ImageView mImageFast;
    public ImageView mImagePlay;
    public ImageView mImageSlow;
    public ImageView mImageSound;
    public ImageView mImageStop;
    public LayoutInflater mInflater;
    public MessageCallback mMessageCallback;
    public int mMinute;
    public int mMonth;
    public int mMultiplayChannelArray;
    public String mMultiplayTime;
    public View.OnClickListener mOnClickListener;
    public PopupWindow mPopupCapture;
    public LinearLayout mProcessbar;
    public RemotePlayback mRemotePlayback;
    public int mSecond;
    public SeekBar mSeekBar;
    public int mSpeed = 1;
    public String mStartTime;
    public int mTimeValues;
    public boolean mTracking = false;
    public VideoGroup mVideoGroup;
    public int mYear;
    public boolean mbMute = true;
    public boolean mbPause = false;
    public boolean mbPlay = false;
    List<Map<String, Object>> mbmpList;
    public View mpopViewer;
    public TextView mtvSpeed;
    public TextView mtvStartTime;
    public TextView mtvTime;
    public PopupWindow pop;
    private String szTime;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mApp = (MyApp) getApplication();
        this.mInflater = LayoutInflater.from(this);
        this.mContext = this;
        setContentView(R.layout.eventplaybackview);
        LoadViews();
        if (this.mApp.mPushInfo != null) {
            this.mBaseValues = (this.mApp.mPushInfo.mHour * 3600) + (this.mApp.mPushInfo.mMinute * 60) + this.mApp.mPushInfo.mSecond;
            new Thread(new RemoteRunnable()).start();
            return;
        }
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        this.mDevInfo = (DevInfoBean) intent.getSerializableExtra(DatebaseConfig.TableName);
        if (extras != null) {
            this.mYear = extras.getInt("year");
            this.mMonth = extras.getInt("month");
            this.mDay = extras.getInt("day");
            this.mHour = extras.getInt("hour");
            this.mMinute = extras.getInt("minute");
            this.mSecond = extras.getInt("second");
            this.mChannel = extras.getInt("channel");
            this.mAlarmType = extras.getInt("alarmtype");
            this.mBaseValues = (this.mHour * 3600) + (this.mMinute * 60) + this.mSecond;
            if (this.mApp.mStartTime == 0) {
                if (this.mHour == 0 && this.mMinute == 0 && this.mSecond < 30) {
                    this.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(this.mHour), Integer.valueOf(this.mMinute), 0});
                } else {
                    this.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(this.mHour), Integer.valueOf(this.mMinute), Integer.valueOf(this.mSecond - 30)});
                }
            } else if (this.mApp.mStartTime == 1) {
                if (this.mHour != 0 || this.mMinute >= 1) {
                    this.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(this.mHour), Integer.valueOf(this.mMinute - 1), Integer.valueOf(this.mSecond)});
                } else {
                    this.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(this.mHour), 0, 0});
                }
            } else if (this.mApp.mStartTime == 2) {
                if (this.mHour != 0 || this.mMinute >= 2) {
                    this.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(this.mHour), Integer.valueOf(this.mMinute - 2), Integer.valueOf(this.mSecond)});
                } else {
                    this.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(this.mHour), 0, 0});
                }
            } else if (this.mApp.mStartTime == 3) {
                if (this.mHour != 0 || this.mMinute >= 3) {
                    this.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(this.mHour), Integer.valueOf(this.mMinute - 3), Integer.valueOf(this.mSecond)});
                } else {
                    this.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(this.mHour), 0, 0});
                }
            }
            new Thread(new RemoteRunnable()).start();
        }
    }

    public void LoadViews() {
        VideoGroup videoGroup = (VideoGroup) findViewById(R.id.eventplayback_videogroup);
        this.mVideoGroup = videoGroup;
        videoGroup.LoadViews();
        this.mVideoGroup.SetInitMode(1);
        this.mVideoGroup.getVideoView(0).setMax(true);
        this.mVideoGroup.getVideoView(0).setOsdState(false);
        this.mBusyProgressBar = (ProgressBar) this.mVideoGroup.findViewById(R.id.eventplayback_busyprogress);
        this.mProcessbar = (LinearLayout) findViewById(R.id.eventplayback_progress);
        this.mtvSpeed = (TextView) findViewById(R.id.eventplayback_playrate_text);
        this.mtvTime = (TextView) findViewById(R.id.eventplayback_progress_text);
        this.mtvStartTime = (TextView) findViewById(R.id.eventplayback_starttime_text);
        this.mSeekBar = (SeekBar) findViewById(R.id.eventplayback_progressbar);
        this.image = (ImageView) findViewById(R.id.eventplayback_controlbar_sound);
        int i2 = 60;
        int i = this.mApp.mStartTime;
        if (this.mApp.mStartTime == 0) {
            if (this.mApp.mEndTime != 0) {
                i2 = this.mApp.mEndTime;
            }
            this.mSeekBar.setMax(i2);
            this.mSeekBar.setProgress(0);
            this.mSeekBar.setSecondaryProgress(0);
            this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    EventPlaybackActivity.this.mTracking = true;
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (EventPlaybackActivity.this.mbPlay) {
                        if (!EventPlaybackActivity.this.mbPause) {
                            EventPlaybackActivity eventPlaybackActivity = EventPlaybackActivity.this;
                            eventPlaybackActivity.Seek(eventPlaybackActivity.mSeekBar.getMax(), EventPlaybackActivity.this.mSeekBar.getProgress() + EventPlaybackActivity.this.mBaseValues);
                        } else {
                            return;
                        }
                    }
                    EventPlaybackActivity.this.mTracking = false;
                }
            });
            this.mOnClickListener = new View.OnClickListener() {
                public void onClick(View view) {
                    int id = view.getId();
                    if (id != R.id.eventplayback_title_button_cancel) {
                        switch (id) {
                            case R.id.eventplayback_controlbar_capture /*2131362382*/:
                                if (EventPlaybackActivity.this.mDvrNet != null) {
                                    if (!Environment.getExternalStorageState().equals("mounted")) {
                                        Toast.makeText(EventPlaybackActivity.this, EventPlaybackActivity.this.mContext.getString(R.string.ExternalStorageerror), 0).show();
                                        return;
                                    }
                                    MediaPlayer.create(EventPlaybackActivity.this.mContext, R.raw.capture).start();
                                    EventPlaybackActivity eventPlaybackActivity = EventPlaybackActivity.this;
                                    eventPlaybackActivity.popImageViewer(eventPlaybackActivity.CaptureImage());
                                    return;
                                }
                                return;
                            case R.id.eventplayback_controlbar_fast /*2131362383*/:
                                int i = EventPlaybackActivity.this.mSpeed;
                                if (i == -4) {
                                    EventPlaybackActivity.this.mSpeed = -2;
                                } else if (i == -2) {
                                    EventPlaybackActivity.this.mSpeed = 1;
                                } else if (i == 4) {
                                    EventPlaybackActivity.this.mSpeed = 1;
                                } else if (i == 1) {
                                    EventPlaybackActivity.this.mSpeed = 2;
                                } else if (i != 2) {
                                    EventPlaybackActivity.this.mSpeed = 1;
                                } else {
                                    EventPlaybackActivity.this.mSpeed = 4;
                                }
                                if (EventPlaybackActivity.this.mDvrNet != null) {
                                    EventPlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(EventPlaybackActivity.this.mSpeed);
                                }
                                EventPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(EventPlaybackActivity.this.mSpeed)}));
                                return;
                            case R.id.eventplayback_controlbar_play /*2131362384*/:
                                ImageView imageView = (ImageView) EventPlaybackActivity.this.findViewById(R.id.eventplayback_controlbar_play);
                                if (!EventPlaybackActivity.this.mbPlay) {
                                    imageView.setImageResource(R.drawable.pause);
                                    new Thread(new RemoteRunnable()).start();
                                    return;
                                }
                                EventPlaybackActivity eventPlaybackActivity2 = EventPlaybackActivity.this;
                                eventPlaybackActivity2.mbPause = !eventPlaybackActivity2.mbPause;
                                if (EventPlaybackActivity.this.mbPause) {
                                    imageView.setImageResource(R.drawable.play);
                                } else {
                                    imageView.setImageResource(R.drawable.pause);
                                }
                                if (EventPlaybackActivity.this.mDvrNet != null) {
                                    EventPlaybackActivity.this.mDvrNet.MultiPlayPause(EventPlaybackActivity.this.mbPause);
                                    EventPlaybackActivity.this.mSpeed = 1;
                                    EventPlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(EventPlaybackActivity.this.mSpeed);
                                    EventPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(EventPlaybackActivity.this.mSpeed)}));
                                    return;
                                }
                                return;
                            case R.id.eventplayback_controlbar_slow /*2131362385*/:
                                if (EventPlaybackActivity.this.mDvrNet != null) {
                                    int i2 = EventPlaybackActivity.this.mSpeed;
                                    if (i2 == -4) {
                                        EventPlaybackActivity.this.mSpeed = 1;
                                    } else if (i2 == -2) {
                                        EventPlaybackActivity.this.mSpeed = -4;
                                    } else if (i2 == 4) {
                                        EventPlaybackActivity.this.mSpeed = 2;
                                    } else if (i2 == 1) {
                                        EventPlaybackActivity.this.mSpeed = -2;
                                    } else if (i2 != 2) {
                                        EventPlaybackActivity.this.mSpeed = -2;
                                    } else {
                                        EventPlaybackActivity.this.mSpeed = 1;
                                    }
                                    if (EventPlaybackActivity.this.mDvrNet != null) {
                                        EventPlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(EventPlaybackActivity.this.mSpeed);
                                    }
                                    EventPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(EventPlaybackActivity.this.mSpeed)}));
                                    return;
                                }
                                return;
                            case R.id.eventplayback_controlbar_sound /*2131362386*/:
                                if (EventPlaybackActivity.this.mbMute) {
                                    EventPlaybackActivity.this.mbMute = false;
                                    EventPlaybackActivity.this.image.setImageResource(R.drawable.soundopen);
                                } else {
                                    EventPlaybackActivity.this.mbMute = true;
                                    EventPlaybackActivity.this.image.setImageResource(R.drawable.soundclose);
                                }
                                if (EventPlaybackActivity.this.mDvrNet != null) {
                                    EventPlaybackActivity.this.mAudioTrack.SetMute(EventPlaybackActivity.this.mbMute);
                                    return;
                                }
                                return;
                            case R.id.eventplayback_controlbar_stop /*2131362387*/:
                                EventPlaybackActivity.this.StopMultiPlay();
                                return;
                            default:
                                return;
                        }
                    } else {
                        EventPlaybackActivity.this.StopMultiPlay();
                        EventPlaybackActivity.this.finish();
                    }
                }
            };
            findViewById(R.id.eventplayback_title_button_cancel).setOnClickListener(this.mOnClickListener);
            ImageView imageView = (ImageView) findViewById(R.id.eventplayback_controlbar_stop);
            this.mImageStop = imageView;
            imageView.setOnClickListener(this.mOnClickListener);
            ImageView imageView2 = (ImageView) findViewById(R.id.eventplayback_controlbar_slow);
            this.mImageSlow = imageView2;
            imageView2.setOnClickListener(this.mOnClickListener);
            ImageView imageView3 = (ImageView) findViewById(R.id.eventplayback_controlbar_play);
            this.mImagePlay = imageView3;
            imageView3.setOnClickListener(this.mOnClickListener);
            ImageView imageView4 = (ImageView) findViewById(R.id.eventplayback_controlbar_fast);
            this.mImageFast = imageView4;
            imageView4.setOnClickListener(this.mOnClickListener);
            ImageView imageView5 = (ImageView) findViewById(R.id.eventplayback_controlbar_capture);
            this.mImageCapture = imageView5;
            imageView5.setOnClickListener(this.mOnClickListener);
            ImageView imageView6 = (ImageView) findViewById(R.id.eventplayback_controlbar_sound);
            this.mImageSound = imageView6;
            imageView6.setOnClickListener(this.mOnClickListener);
            this.mHandlerMessage = new Handler() {
                public void handleMessage(Message message) {
                    int i = message.what;
                    if (i != 0) {
                        if (i == 1) {
                            if (EventPlaybackActivity.this.mbPlay) {
                                EventPlaybackActivity.this.StopMultiPlay();
                            } else {
                                return;
                            }
                        }
                    } else if (EventPlaybackActivity.this.mbPlay) {
                        new AlertDialog.Builder(EventPlaybackActivity.this.mContext).setTitle(EventPlaybackActivity.this.mContext.getString(R.string.app_name)).setIcon(EventPlaybackActivity.this.mContext.getResources().getDrawable(R.drawable.icon)).setMessage(EventPlaybackActivity.this.mContext.getString(R.string.remoteplaybackfailed)).setPositiveButton(EventPlaybackActivity.this.mContext.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
                    } else {
                        return;
                    }
                    super.handleMessage(message);
                }
            };
            this.mMessageCallback = new MessageCallback() {
                public void sendMessage(int i) {
                    if (i == 0) {
                        Message message = new Message();
                        message.what = 0;
                        message.setTarget(EventPlaybackActivity.this.mHandlerMessage);
                        message.sendToTarget();
                    }
                }
            };
        } else if (this.mApp.mEndTime == 0) {
            i = this.mApp.mStartTime;
        } else {
            i2 = (this.mApp.mStartTime * 60) + (this.mApp.mEndTime * 60);
            this.mSeekBar.setMax(i2);
            this.mSeekBar.setProgress(0);
            this.mSeekBar.setSecondaryProgress(0);
            this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    EventPlaybackActivity.this.mTracking = true;
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (EventPlaybackActivity.this.mbPlay) {
                        if (!EventPlaybackActivity.this.mbPause) {
                            EventPlaybackActivity eventPlaybackActivity = EventPlaybackActivity.this;
                            eventPlaybackActivity.Seek(eventPlaybackActivity.mSeekBar.getMax(), EventPlaybackActivity.this.mSeekBar.getProgress() + EventPlaybackActivity.this.mBaseValues);
                        } else {
                            return;
                        }
                    }
                    EventPlaybackActivity.this.mTracking = false;
                }
            });
            this.mOnClickListener = new View.OnClickListener() {
                public void onClick(View view) {
                    int id = view.getId();
                    if (id != R.id.eventplayback_title_button_cancel) {
                        switch (id) {
                            case R.id.eventplayback_controlbar_capture /*2131362382*/:
                                if (EventPlaybackActivity.this.mDvrNet != null) {
                                    if (!Environment.getExternalStorageState().equals("mounted")) {
                                        Toast.makeText(EventPlaybackActivity.this, EventPlaybackActivity.this.mContext.getString(R.string.ExternalStorageerror), 0).show();
                                        return;
                                    }
                                    MediaPlayer.create(EventPlaybackActivity.this.mContext, R.raw.capture).start();
                                    EventPlaybackActivity eventPlaybackActivity = EventPlaybackActivity.this;
                                    eventPlaybackActivity.popImageViewer(eventPlaybackActivity.CaptureImage());
                                    return;
                                }
                                return;
                            case R.id.eventplayback_controlbar_fast /*2131362383*/:
                                int i = EventPlaybackActivity.this.mSpeed;
                                if (i == -4) {
                                    EventPlaybackActivity.this.mSpeed = -2;
                                } else if (i == -2) {
                                    EventPlaybackActivity.this.mSpeed = 1;
                                } else if (i == 4) {
                                    EventPlaybackActivity.this.mSpeed = 1;
                                } else if (i == 1) {
                                    EventPlaybackActivity.this.mSpeed = 2;
                                } else if (i != 2) {
                                    EventPlaybackActivity.this.mSpeed = 1;
                                } else {
                                    EventPlaybackActivity.this.mSpeed = 4;
                                }
                                if (EventPlaybackActivity.this.mDvrNet != null) {
                                    EventPlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(EventPlaybackActivity.this.mSpeed);
                                }
                                EventPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(EventPlaybackActivity.this.mSpeed)}));
                                return;
                            case R.id.eventplayback_controlbar_play /*2131362384*/:
                                ImageView imageView = (ImageView) EventPlaybackActivity.this.findViewById(R.id.eventplayback_controlbar_play);
                                if (!EventPlaybackActivity.this.mbPlay) {
                                    imageView.setImageResource(R.drawable.pause);
                                    new Thread(new RemoteRunnable()).start();
                                    return;
                                }
                                EventPlaybackActivity eventPlaybackActivity2 = EventPlaybackActivity.this;
                                eventPlaybackActivity2.mbPause = !eventPlaybackActivity2.mbPause;
                                if (EventPlaybackActivity.this.mbPause) {
                                    imageView.setImageResource(R.drawable.play);
                                } else {
                                    imageView.setImageResource(R.drawable.pause);
                                }
                                if (EventPlaybackActivity.this.mDvrNet != null) {
                                    EventPlaybackActivity.this.mDvrNet.MultiPlayPause(EventPlaybackActivity.this.mbPause);
                                    EventPlaybackActivity.this.mSpeed = 1;
                                    EventPlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(EventPlaybackActivity.this.mSpeed);
                                    EventPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(EventPlaybackActivity.this.mSpeed)}));
                                    return;
                                }
                                return;
                            case R.id.eventplayback_controlbar_slow /*2131362385*/:
                                if (EventPlaybackActivity.this.mDvrNet != null) {
                                    int i2 = EventPlaybackActivity.this.mSpeed;
                                    if (i2 == -4) {
                                        EventPlaybackActivity.this.mSpeed = 1;
                                    } else if (i2 == -2) {
                                        EventPlaybackActivity.this.mSpeed = -4;
                                    } else if (i2 == 4) {
                                        EventPlaybackActivity.this.mSpeed = 2;
                                    } else if (i2 == 1) {
                                        EventPlaybackActivity.this.mSpeed = -2;
                                    } else if (i2 != 2) {
                                        EventPlaybackActivity.this.mSpeed = -2;
                                    } else {
                                        EventPlaybackActivity.this.mSpeed = 1;
                                    }
                                    if (EventPlaybackActivity.this.mDvrNet != null) {
                                        EventPlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(EventPlaybackActivity.this.mSpeed);
                                    }
                                    EventPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(EventPlaybackActivity.this.mSpeed)}));
                                    return;
                                }
                                return;
                            case R.id.eventplayback_controlbar_sound /*2131362386*/:
                                if (EventPlaybackActivity.this.mbMute) {
                                    EventPlaybackActivity.this.mbMute = false;
                                    EventPlaybackActivity.this.image.setImageResource(R.drawable.soundopen);
                                } else {
                                    EventPlaybackActivity.this.mbMute = true;
                                    EventPlaybackActivity.this.image.setImageResource(R.drawable.soundclose);
                                }
                                if (EventPlaybackActivity.this.mDvrNet != null) {
                                    EventPlaybackActivity.this.mAudioTrack.SetMute(EventPlaybackActivity.this.mbMute);
                                    return;
                                }
                                return;
                            case R.id.eventplayback_controlbar_stop /*2131362387*/:
                                EventPlaybackActivity.this.StopMultiPlay();
                                return;
                            default:
                                return;
                        }
                    } else {
                        EventPlaybackActivity.this.StopMultiPlay();
                        EventPlaybackActivity.this.finish();
                    }
                }
            };
            findViewById(R.id.eventplayback_title_button_cancel).setOnClickListener(this.mOnClickListener);
            ImageView imageView7 = (ImageView) findViewById(R.id.eventplayback_controlbar_stop);
            this.mImageStop = imageView7;
            imageView7.setOnClickListener(this.mOnClickListener);
            ImageView imageView22 = (ImageView) findViewById(R.id.eventplayback_controlbar_slow);
            this.mImageSlow = imageView22;
            imageView22.setOnClickListener(this.mOnClickListener);
            ImageView imageView32 = (ImageView) findViewById(R.id.eventplayback_controlbar_play);
            this.mImagePlay = imageView32;
            imageView32.setOnClickListener(this.mOnClickListener);
            ImageView imageView42 = (ImageView) findViewById(R.id.eventplayback_controlbar_fast);
            this.mImageFast = imageView42;
            imageView42.setOnClickListener(this.mOnClickListener);
            ImageView imageView52 = (ImageView) findViewById(R.id.eventplayback_controlbar_capture);
            this.mImageCapture = imageView52;
            imageView52.setOnClickListener(this.mOnClickListener);
            ImageView imageView62 = (ImageView) findViewById(R.id.eventplayback_controlbar_sound);
            this.mImageSound = imageView62;
            imageView62.setOnClickListener(this.mOnClickListener);
            this.mHandlerMessage = new Handler() {
                public void handleMessage(Message message) {
                    int i = message.what;
                    if (i != 0) {
                        if (i == 1) {
                            if (EventPlaybackActivity.this.mbPlay) {
                                EventPlaybackActivity.this.StopMultiPlay();
                            } else {
                                return;
                            }
                        }
                    } else if (EventPlaybackActivity.this.mbPlay) {
                        new AlertDialog.Builder(EventPlaybackActivity.this.mContext).setTitle(EventPlaybackActivity.this.mContext.getString(R.string.app_name)).setIcon(EventPlaybackActivity.this.mContext.getResources().getDrawable(R.drawable.icon)).setMessage(EventPlaybackActivity.this.mContext.getString(R.string.remoteplaybackfailed)).setPositiveButton(EventPlaybackActivity.this.mContext.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
                    } else {
                        return;
                    }
                    super.handleMessage(message);
                }
            };
            this.mMessageCallback = new MessageCallback() {
                public void sendMessage(int i) {
                    if (i == 0) {
                        Message message = new Message();
                        message.what = 0;
                        message.setTarget(EventPlaybackActivity.this.mHandlerMessage);
                        message.sendToTarget();
                    }
                }
            };
        }
        i2 = (i * 60) + 30;
        this.mSeekBar.setMax(i2);
        this.mSeekBar.setProgress(0);
        this.mSeekBar.setSecondaryProgress(0);
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                EventPlaybackActivity.this.mTracking = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (EventPlaybackActivity.this.mbPlay) {
                    if (!EventPlaybackActivity.this.mbPause) {
                        EventPlaybackActivity eventPlaybackActivity = EventPlaybackActivity.this;
                        eventPlaybackActivity.Seek(eventPlaybackActivity.mSeekBar.getMax(), EventPlaybackActivity.this.mSeekBar.getProgress() + EventPlaybackActivity.this.mBaseValues);
                    } else {
                        return;
                    }
                }
                EventPlaybackActivity.this.mTracking = false;
            }
        });
        this.mOnClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                int id = view.getId();
                if (id != R.id.eventplayback_title_button_cancel) {
                    switch (id) {
                        case R.id.eventplayback_controlbar_capture /*2131362382*/:
                            if (EventPlaybackActivity.this.mDvrNet != null) {
                                if (!Environment.getExternalStorageState().equals("mounted")) {
                                    Toast.makeText(EventPlaybackActivity.this, EventPlaybackActivity.this.mContext.getString(R.string.ExternalStorageerror), 0).show();
                                    return;
                                }
                                MediaPlayer.create(EventPlaybackActivity.this.mContext, R.raw.capture).start();
                                EventPlaybackActivity eventPlaybackActivity = EventPlaybackActivity.this;
                                eventPlaybackActivity.popImageViewer(eventPlaybackActivity.CaptureImage());
                                return;
                            }
                            return;
                        case R.id.eventplayback_controlbar_fast /*2131362383*/:
                            int i = EventPlaybackActivity.this.mSpeed;
                            if (i == -4) {
                                EventPlaybackActivity.this.mSpeed = -2;
                            } else if (i == -2) {
                                EventPlaybackActivity.this.mSpeed = 1;
                            } else if (i == 4) {
                                EventPlaybackActivity.this.mSpeed = 1;
                            } else if (i == 1) {
                                EventPlaybackActivity.this.mSpeed = 2;
                            } else if (i != 2) {
                                EventPlaybackActivity.this.mSpeed = 1;
                            } else {
                                EventPlaybackActivity.this.mSpeed = 4;
                            }
                            if (EventPlaybackActivity.this.mDvrNet != null) {
                                EventPlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(EventPlaybackActivity.this.mSpeed);
                            }
                            EventPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(EventPlaybackActivity.this.mSpeed)}));
                            return;
                        case R.id.eventplayback_controlbar_play /*2131362384*/:
                            ImageView imageView = (ImageView) EventPlaybackActivity.this.findViewById(R.id.eventplayback_controlbar_play);
                            if (!EventPlaybackActivity.this.mbPlay) {
                                imageView.setImageResource(R.drawable.pause);
                                new Thread(new RemoteRunnable()).start();
                                return;
                            }
                            EventPlaybackActivity eventPlaybackActivity2 = EventPlaybackActivity.this;
                            eventPlaybackActivity2.mbPause = !eventPlaybackActivity2.mbPause;
                            if (EventPlaybackActivity.this.mbPause) {
                                imageView.setImageResource(R.drawable.play);
                            } else {
                                imageView.setImageResource(R.drawable.pause);
                            }
                            if (EventPlaybackActivity.this.mDvrNet != null) {
                                EventPlaybackActivity.this.mDvrNet.MultiPlayPause(EventPlaybackActivity.this.mbPause);
                                EventPlaybackActivity.this.mSpeed = 1;
                                EventPlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(EventPlaybackActivity.this.mSpeed);
                                EventPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(EventPlaybackActivity.this.mSpeed)}));
                                return;
                            }
                            return;
                        case R.id.eventplayback_controlbar_slow /*2131362385*/:
                            if (EventPlaybackActivity.this.mDvrNet != null) {
                                int i2 = EventPlaybackActivity.this.mSpeed;
                                if (i2 == -4) {
                                    EventPlaybackActivity.this.mSpeed = 1;
                                } else if (i2 == -2) {
                                    EventPlaybackActivity.this.mSpeed = -4;
                                } else if (i2 == 4) {
                                    EventPlaybackActivity.this.mSpeed = 2;
                                } else if (i2 == 1) {
                                    EventPlaybackActivity.this.mSpeed = -2;
                                } else if (i2 != 2) {
                                    EventPlaybackActivity.this.mSpeed = -2;
                                } else {
                                    EventPlaybackActivity.this.mSpeed = 1;
                                }
                                if (EventPlaybackActivity.this.mDvrNet != null) {
                                    EventPlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(EventPlaybackActivity.this.mSpeed);
                                }
                                EventPlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(EventPlaybackActivity.this.mSpeed)}));
                                return;
                            }
                            return;
                        case R.id.eventplayback_controlbar_sound /*2131362386*/:
                            if (EventPlaybackActivity.this.mbMute) {
                                EventPlaybackActivity.this.mbMute = false;
                                EventPlaybackActivity.this.image.setImageResource(R.drawable.soundopen);
                            } else {
                                EventPlaybackActivity.this.mbMute = true;
                                EventPlaybackActivity.this.image.setImageResource(R.drawable.soundclose);
                            }
                            if (EventPlaybackActivity.this.mDvrNet != null) {
                                EventPlaybackActivity.this.mAudioTrack.SetMute(EventPlaybackActivity.this.mbMute);
                                return;
                            }
                            return;
                        case R.id.eventplayback_controlbar_stop /*2131362387*/:
                            EventPlaybackActivity.this.StopMultiPlay();
                            return;
                        default:
                            return;
                    }
                } else {
                    EventPlaybackActivity.this.StopMultiPlay();
                    EventPlaybackActivity.this.finish();
                }
            }
        };
        findViewById(R.id.eventplayback_title_button_cancel).setOnClickListener(this.mOnClickListener);
        ImageView imageView72 = (ImageView) findViewById(R.id.eventplayback_controlbar_stop);
        this.mImageStop = imageView72;
        imageView72.setOnClickListener(this.mOnClickListener);
        ImageView imageView222 = (ImageView) findViewById(R.id.eventplayback_controlbar_slow);
        this.mImageSlow = imageView222;
        imageView222.setOnClickListener(this.mOnClickListener);
        ImageView imageView322 = (ImageView) findViewById(R.id.eventplayback_controlbar_play);
        this.mImagePlay = imageView322;
        imageView322.setOnClickListener(this.mOnClickListener);
        ImageView imageView422 = (ImageView) findViewById(R.id.eventplayback_controlbar_fast);
        this.mImageFast = imageView422;
        imageView422.setOnClickListener(this.mOnClickListener);
        ImageView imageView522 = (ImageView) findViewById(R.id.eventplayback_controlbar_capture);
        this.mImageCapture = imageView522;
        imageView522.setOnClickListener(this.mOnClickListener);
        ImageView imageView622 = (ImageView) findViewById(R.id.eventplayback_controlbar_sound);
        this.mImageSound = imageView622;
        imageView622.setOnClickListener(this.mOnClickListener);
        this.mHandlerMessage = new Handler() {
            public void handleMessage(Message message) {
                int i = message.what;
                if (i != 0) {
                    if (i == 1) {
                        if (EventPlaybackActivity.this.mbPlay) {
                            EventPlaybackActivity.this.StopMultiPlay();
                        } else {
                            return;
                        }
                    }
                } else if (EventPlaybackActivity.this.mbPlay) {
                    new AlertDialog.Builder(EventPlaybackActivity.this.mContext).setTitle(EventPlaybackActivity.this.mContext.getString(R.string.app_name)).setIcon(EventPlaybackActivity.this.mContext.getResources().getDrawable(R.drawable.icon)).setMessage(EventPlaybackActivity.this.mContext.getString(R.string.remoteplaybackfailed)).setPositiveButton(EventPlaybackActivity.this.mContext.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
                } else {
                    return;
                }
                super.handleMessage(message);
            }
        };
        this.mMessageCallback = new MessageCallback() {
            public void sendMessage(int i) {
                if (i == 0) {
                    Message message = new Message();
                    message.what = 0;
                    message.setTarget(EventPlaybackActivity.this.mHandlerMessage);
                    message.sendToTarget();
                }
            }
        };
    }

    public void popImageViewer(List<Map<String, Object>> list) {
        List<Map<String, Object>> list2 = list;
        if (list.size() != 0) {
            this.mbmpList = list2;
            int size = list.size();
            this.mpopViewer = this.mInflater.inflate(R.layout.captureimageviewer, (ViewGroup) null);
            LinearLayout linearLayout = new LinearLayout(this.mContext);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            linearLayout.setBackgroundColor(Color.argb(200, 40, 40, 40));
            int i = 0;
            linearLayout.setPadding(0, 0, 0, 0);
            new LinearLayout.LayoutParams(-2, -2).weight = 1.0f;
            int i2 = 0;
            while (true) {
                double d = (double) i2;
                double d2 = (double) size;
                if (d < Math.sqrt(d2)) {
                    LinearLayout linearLayout2 = new LinearLayout(this.mContext);
                    linearLayout2.setOrientation(i);
                    linearLayout2.setPadding(i, i, i, i);
                    int i3 = 0;
                    while (true) {
                        double d3 = (double) i3;
                        if (d3 >= Math.sqrt(d2)) {
                            break;
                        }
                        String obj = list2.get((int) ((Math.sqrt(d2) * d) + d3)).get("path").toString();
                        ImageView imageView = new ImageView(this.mContext);
                        imageView.setImageBitmap(BitmapFactory.decodeFile(obj));
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        linearLayout2.addView(imageView);
                        i3++;
                    }
                    linearLayout.addView(linearLayout2);
                    i2++;
                    i = 0;
                } else {
                    ((LinearLayout) this.mpopViewer.findViewById(R.id.preview_capture_imagegroup)).addView(linearLayout);
                    this.mPopupCapture = null;
                    PopupWindow popupWindow = new PopupWindow(this.mpopViewer, -1, -1, true);
                    this.mPopupCapture = popupWindow;
                    popupWindow.setOutsideTouchable(false);
                    this.mPopupCapture.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
                    this.mPopupCapture.showAtLocation(findViewById(R.id.remoteplay_mainwnd), 17, 0, 0);
                    this.mPopupCapture.update();
                    this.mpopViewer.findViewById(R.id.preview_capture_save).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            EventPlaybackActivity.this.mPopupCapture.dismiss();
                        }
                    });
                    this.mpopViewer.findViewById(R.id.preview_capture_cancel).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            for (int i = 0; i < EventPlaybackActivity.this.mbmpList.size(); i++) {
                                String obj = EventPlaybackActivity.this.mbmpList.get(i).get("path").toString();
                                if (obj != null) {
                                    new File(obj).delete();
                                }
                            }
                            EventPlaybackActivity.this.mPopupCapture.dismiss();
                        }
                    });
                    return;
                }
            }
        }
    }

    public void popMenu(View view, View view2) {
        new DisplayMetrics();
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        PopupWindow popupWindow = this.pop;
        if (popupWindow == null) {
            PopupWindow popupWindow2 = new PopupWindow(view, i / 2, i2 / 2, true);
            this.pop = popupWindow2;
            popupWindow2.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
            this.pop.setTouchInterceptor(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 4) {
                        return false;
                    }
                    EventPlaybackActivity.this.pop.dismiss();
                    return false;
                }
            });
            this.pop.setOutsideTouchable(true);
            this.pop.showAsDropDown(view2, 1, 0);
            this.pop.update();
        } else if (popupWindow.isShowing()) {
            this.pop.dismiss();
            this.pop = null;
        } else {
            this.pop = null;
            PopupWindow popupWindow3 = new PopupWindow(view, i / 2, i2 / 2, true);
            this.pop = popupWindow3;
            popupWindow3.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
            this.pop.setOutsideTouchable(false);
            this.pop.showAsDropDown(view2, 1, 0);
            this.pop.update();
        }
    }

    public List<Map<String, Object>> CaptureImage() {
        String str = Environment.getExternalStorageDirectory() + "/streaming/capture/";
        ArrayList arrayList = new ArrayList();
        DvrNet dvrNet = this.mDvrNet;
        if (dvrNet != null) {
            BitmapFileInfo[] MultiPlayCaptureBitmap = dvrNet.MultiPlayCaptureBitmap(str);
            if (MultiPlayCaptureBitmap == null) {
                return null;
            }
            for (int i = 0; i < MultiPlayCaptureBitmap.length; i++) {
                HashMap hashMap = new HashMap();
                hashMap.put("channel", Integer.valueOf(MultiPlayCaptureBitmap[i].nChannel));
                hashMap.put("path", MultiPlayCaptureBitmap[i].FilePath);
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

    public void SetConfiguration(Configuration configuration) {
        if (configuration.orientation == 2) {
            findViewById(R.id.eventplayback_title).setVisibility(8);
            findViewById(R.id.eventplayback_controlbar).setVisibility(8);
            this.mProcessbar.setVisibility(8);
        } else if (configuration.orientation == 1) {
            findViewById(R.id.eventplayback_title).setVisibility(0);
            findViewById(R.id.eventplayback_controlbar).setVisibility(0);
            this.mProcessbar.setVisibility(0);
        }
    }

    public class MultiplayRemoteRunnable implements Runnable {
        public MultiplayRemoteRunnable() {
        }

        public void run() {
            EventPlaybackActivity.this.mDvrNet.MultiPlayStop();
            EventPlaybackActivity.this.mAudioTrack.mPlayer.play();
            EventPlaybackActivity.this.mAudioTrack.SetMute(EventPlaybackActivity.this.mbMute);
            EventPlaybackActivity.this.mDvrNet.SetMultiplayInterface((MultiplaybackInterface) this);
            EventPlaybackActivity.this.mDvrNet.SetAudioInterface(EventPlaybackActivity.this.mAudioTrack);
            EventPlaybackActivity.this.mDvrNet.MultiPlay(0, EventPlaybackActivity.this.mMultiplayChannelArray, 1, EventPlaybackActivity.this.mMultiplayTime, "", "");
        }
    }

    public class RemoteRunnable implements Runnable {
        public RemoteRunnable() {
        }

        public void run() {
            DevInfoBean devInfoBean;
            Map<String, Object> map = null;
            int i = -1;
            if (EventPlaybackActivity.this.mApp.mPushInfo != null) {
                if (EventPlaybackActivity.this.mDvrNet != null) {
                    EventPlaybackActivity.this.mDvrNet.CloseDeviceHandle();
                    EventPlaybackActivity.this.mDvrNet = null;
                }
                EventPlaybackActivity.this.mDvrNet = new DvrNet();
                if (MyApp.loginType == 0) {
                    devInfoBean = EventPlaybackActivity.this.mApp.mdbHelper.query(EventPlaybackActivity.this.mApp.mPushInfo.devicename);
                } else {
                    devInfoBean = EventPlaybackActivity.this.mApp.mWebService.query(EventPlaybackActivity.this.mApp.mPushInfo.devicename);
                }
                if (devInfoBean != null) {
                    if (MyApp.loginType == 0) {
                        if (devInfoBean.mDevIp.contains(".") && devInfoBean.mMediaPort > 0 && (map = ConnDeviceProxy.connDeviceByC2C(EventPlaybackActivity.this.mDvrNet, devInfoBean, EventPlaybackActivity.this.mApp)) != null) {
                            i = ((Integer) map.get("errorcode")).intValue();
                        }
                        if (i != 0) {
                            int i2 = 0;
                            while (EventPlaybackActivity.this.mApp != null && !TextUtils.isEmpty(EventPlaybackActivity.this.mApp.mUdtServerIp) && (EventPlaybackActivity.this.mApp.mUdtServerIp.isEmpty() || EventPlaybackActivity.this.mApp.mUdtServerPort == 0)) {
                                int i3 = i2 + 1;
                                if (i2 >= 50) {
                                    break;
                                }
                                SystemClock.sleep(100);
                                i2 = i3;
                            }
                            map = ConnDeviceProxy.connDeviceByNAT(EventPlaybackActivity.this.mDvrNet, devInfoBean, EventPlaybackActivity.this.mApp);
                            if (map != null) {
                                i = ((Integer) map.get("errorcode")).intValue();
                            }
                        }
                        if (i != 0) {
                            int i4 = 0;
                            while (EventPlaybackActivity.this.mApp != null && !TextUtils.isEmpty(EventPlaybackActivity.this.mApp.mMsgServerIp) && (EventPlaybackActivity.this.mApp.mMsgServerIp.isEmpty() || EventPlaybackActivity.this.mApp.mMsgServerPort == 0)) {
                                int i5 = i4 + 1;
                                if (i4 >= 50) {
                                    break;
                                }
                                SystemClock.sleep(100);
                                i4 = i5;
                            }
                            map = EventPlaybackActivity.this.mDvrNet.GetDeviceHandle(EventPlaybackActivity.this.mApp.mMsgServerIp, EventPlaybackActivity.this.mApp.mMsgServerPort, EventPlaybackActivity.this.mApp.mGtServerIp, 17891, 124, devInfoBean.mDevIp, devInfoBean.mUsername, devInfoBean.mPwd);
                            ((Integer) map.get("errorcode")).intValue();
                        }
                    } else {
                        map = ConnDeviceProxy.connDeviceByMSG(EventPlaybackActivity.this.mDvrNet, devInfoBean, EventPlaybackActivity.this.mApp);
                        if (map != null) {
                            ((Integer) map.get("errorcode")).intValue();
                        }
                    }
                    if (map == null || map.get("errorcode").toString().compareTo("0") == 0) {
                        if (EventPlaybackActivity.this.mApp.mPushInfo.nAlarmType == 4) {
                            int[] iArr = new int[1];
                            EventPlaybackActivity.this.mDvrNet.GetIOLinkageAlarmChannel(EventPlaybackActivity.this.mApp.mPushInfo.channel, iArr);
                            int i6 = 0;
                            while (true) {
                                if (i6 >= 32) {
                                    break;
                                } else if ((iArr[0] & (1 << i6)) > 0) {
                                    EventPlaybackActivity.this.mChannel = i6;
                                    break;
                                } else {
                                    i6++;
                                }
                            }
                        } else {
                            EventPlaybackActivity eventPlaybackActivity = EventPlaybackActivity.this;
                            eventPlaybackActivity.mChannel = eventPlaybackActivity.mApp.mPushInfo.channel;
                        }
                        if (EventPlaybackActivity.this.mApp.mStartTime == 0) {
                            if (EventPlaybackActivity.this.mApp.mPushInfo.mHour == 0 && EventPlaybackActivity.this.mApp.mPushInfo.mMinute == 0 && EventPlaybackActivity.this.mApp.mPushInfo.mSecond < 30) {
                                EventPlaybackActivity eventPlaybackActivity2 = EventPlaybackActivity.this;
                                eventPlaybackActivity2.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(eventPlaybackActivity2.mApp.mPushInfo.mYear), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMonth), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mDay), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mHour), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMinute), 0});
                            } else {
                                EventPlaybackActivity eventPlaybackActivity3 = EventPlaybackActivity.this;
                                eventPlaybackActivity3.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(eventPlaybackActivity3.mApp.mPushInfo.mYear), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMonth), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mDay), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mHour), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMinute), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mSecond - 30)});
                            }
                        } else if (EventPlaybackActivity.this.mApp.mStartTime == 1) {
                            if (EventPlaybackActivity.this.mApp.mPushInfo.mHour != 0 || EventPlaybackActivity.this.mApp.mPushInfo.mMinute >= 1) {
                                EventPlaybackActivity eventPlaybackActivity4 = EventPlaybackActivity.this;
                                eventPlaybackActivity4.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(eventPlaybackActivity4.mApp.mPushInfo.mYear), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMonth), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mDay), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mHour), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMinute - 1), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mSecond)});
                            } else {
                                EventPlaybackActivity eventPlaybackActivity5 = EventPlaybackActivity.this;
                                eventPlaybackActivity5.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(eventPlaybackActivity5.mApp.mPushInfo.mYear), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMonth), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mDay), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mHour), 0, 0});
                            }
                        } else if (EventPlaybackActivity.this.mApp.mStartTime == 2) {
                            if (EventPlaybackActivity.this.mApp.mPushInfo.mHour != 0 || EventPlaybackActivity.this.mApp.mPushInfo.mMinute >= 2) {
                                EventPlaybackActivity eventPlaybackActivity6 = EventPlaybackActivity.this;
                                eventPlaybackActivity6.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(eventPlaybackActivity6.mApp.mPushInfo.mYear), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMonth), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mDay), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mHour), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMinute - 2), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mSecond)});
                            } else {
                                EventPlaybackActivity eventPlaybackActivity7 = EventPlaybackActivity.this;
                                eventPlaybackActivity7.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(eventPlaybackActivity7.mApp.mPushInfo.mYear), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMonth), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mDay), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mHour), 0, 0});
                            }
                        } else if (EventPlaybackActivity.this.mApp.mStartTime == 3) {
                            if (EventPlaybackActivity.this.mApp.mPushInfo.mHour != 0 || EventPlaybackActivity.this.mApp.mPushInfo.mMinute >= 3) {
                                EventPlaybackActivity eventPlaybackActivity8 = EventPlaybackActivity.this;
                                eventPlaybackActivity8.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(eventPlaybackActivity8.mApp.mPushInfo.mYear), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMonth), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mDay), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mHour), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMinute - 3), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mSecond)});
                            } else {
                                EventPlaybackActivity eventPlaybackActivity9 = EventPlaybackActivity.this;
                                eventPlaybackActivity9.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(eventPlaybackActivity9.mApp.mPushInfo.mYear), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mMonth), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mDay), Integer.valueOf(EventPlaybackActivity.this.mApp.mPushInfo.mHour), 0, 0});
                            }
                        }
                    } else {
                        Message message = new Message();
                        message.what = 0;
                        message.setTarget(EventPlaybackActivity.this.mHandlerMessage);
                        message.sendToTarget();
                        return;
                    }
                } else {
                    return;
                }
            } else {
                if (EventPlaybackActivity.this.mDvrNet != null) {
                    EventPlaybackActivity.this.mDvrNet.CloseDeviceHandle();
                    EventPlaybackActivity.this.mDvrNet = null;
                }
                EventPlaybackActivity.this.mDvrNet = new DvrNet();
                Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(EventPlaybackActivity.this.mDvrNet, EventPlaybackActivity.this.mDevInfo, EventPlaybackActivity.this.mApp);
                if ((connDeviceProxy != null ? ((Integer) connDeviceProxy.get("errorcode")).intValue() : -1) == -1) {
                    Message message2 = new Message();
                    message2.what = 0;
                    message2.setTarget(EventPlaybackActivity.this.mHandlerMessage);
                    message2.sendToTarget();
                    return;
                } else if (EventPlaybackActivity.this.mAlarmType == 4) {
                    int[] iArr2 = new int[1];
                    EventPlaybackActivity.this.mDvrNet.GetIOLinkageAlarmChannel(EventPlaybackActivity.this.mChannel, iArr2);
                    int i7 = 0;
                    while (true) {
                        if (i7 >= 32) {
                            break;
                        } else if ((iArr2[0] & (1 << i7)) > 0) {
                            EventPlaybackActivity.this.mChannel = i7;
                            break;
                        } else {
                            i7++;
                        }
                    }
                }
            }
            EventPlaybackActivity.this.mAudioTrack.mPlayer.play();
            EventPlaybackActivity.this.mAudioTrack.SetMute(false);
            if (EventPlaybackActivity.this.mDvrNet != null) {
                EventPlaybackActivity.this.mDvrNet.SetMultiplayInterface(EventPlaybackActivity.this);
                EventPlaybackActivity.this.mDvrNet.SetAudioInterface(EventPlaybackActivity.this.mAudioTrack);
            }
            EventPlaybackActivity.this.mAudioTrack.SwitchChannels(EventPlaybackActivity.this.mChannel);
            EventPlaybackActivity.this.mDvrNet.setMessageCallback(EventPlaybackActivity.this.mMessageCallback);
            if (EventPlaybackActivity.this.mDvrNet.MultiPlay(0, 1 << EventPlaybackActivity.this.mChannel, 1, EventPlaybackActivity.this.mStartTime, "", "") == 0) {
                EventPlaybackActivity.this.mbPlay = true;
                EventPlaybackActivity.this.mSpeed = 1;
                EventPlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(1);
                EventPlaybackActivity.this.mtvSpeed.post(new Runnable() {
                    public void run() {
                        EventPlaybackActivity.this.mtvSpeed.setText("1X");
                        EventPlaybackActivity.this.mImagePlay.setImageResource(R.drawable.pause);
                        if (EventPlaybackActivity.this.mbMute) {
                            EventPlaybackActivity.this.image.setImageResource(R.drawable.soundopen);
                        } else {
                            EventPlaybackActivity.this.image.setImageResource(R.drawable.soundclose);
                        }
                    }
                });
                return;
            }
            Message message3 = new Message();
            message3.what = 0;
            message3.setTarget(EventPlaybackActivity.this.mHandlerMessage);
            message3.sendToTarget();
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

    public void Seek(int i, int i2) {
        if (this.mDvrNet != null) {
            if (this.mApp.mStartTime == 0) {
                if (this.mHour == 0 && this.mMinute == 0 && this.mSecond < 30) {
                    this.szTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(i2 / 3600), Integer.valueOf((i2 % 3600) / 60), 0});
                } else {
                    this.szTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(i2 / 3600), Integer.valueOf((i2 % 3600) / 60), Integer.valueOf((i2 % 60) - 30)});
                }
            } else if (this.mApp.mStartTime == 1) {
                if (this.mHour != 0 || this.mMinute >= 1) {
                    this.szTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(i2 / 3600), Integer.valueOf(((i2 % 3600) / 60) - 1), Integer.valueOf(i2 % 60)});
                } else {
                    this.szTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(i2 / 3600), 0, 0});
                }
            } else if (this.mApp.mStartTime == 2) {
                if (this.mHour != 0 || this.mMinute >= 2) {
                    this.szTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(i2 / 3600), Integer.valueOf(((i2 % 3600) / 60) - 2), Integer.valueOf(i2 % 60)});
                } else {
                    this.szTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(i2 / 3600), 0, 0});
                }
            } else if (this.mApp.mStartTime == 3) {
                if (this.mHour != 0 || this.mMinute >= 3) {
                    this.szTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(i2 / 3600), Integer.valueOf(((i2 % 3600) / 60) - 3), Integer.valueOf(i2 % 60)});
                } else {
                    this.szTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(i2 / 3600), 0, 0});
                }
            }
            this.mDvrNet.MultiPlaySeek(this.szTime);
        }
    }

    public void StopMultiPlay() {
        DvrNet dvrNet = this.mDvrNet;
        if (dvrNet != null) {
            dvrNet.MultiPlayStop();
            this.mAudioTrack.mPlayer.Stop();
            this.mProcessbar.post(new Runnable() {
                public void run() {
                    EventPlaybackActivity.this.mtvStartTime.setText("00:00:00");
                    EventPlaybackActivity.this.mtvTime.setText("00:00:00");
                    EventPlaybackActivity.this.mSeekBar.setProgress(0);
                    EventPlaybackActivity.this.mtvSpeed.setText("1X");
                }
            });
            this.mbPlay = false;
            this.mImagePlay.setImageResource(R.drawable.play);
            this.mVideoGroup.ClearViews();
        }
    }

    public String getLocalMacAddress() {
        String str = new String("00-00-00-00-00-00");
        WifiManager wifiManager = (WifiManager) this.mContext.getApplicationContext().getSystemService(Configs.Key.WifiStatus);
        if (wifiManager == null) {
            return str;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        String macAddress = connectionInfo.getMacAddress();
        if (macAddress == null) {
            return str;
        }
        String replace = macAddress.replace(":", "-");
        return replace.length() > 0 ? replace : str;
    }

    public void MultiplayCallback(long j, int i, int i2, int i3, byte[] bArr, int i4, int i5, int i6) {
        WriteIn(i, i3, bArr, i4, i5);
        if (i6 != 0) {
            this.mTimeValues = i6;
            this.mtvTime.post(new Runnable() {
                public void run() {
                    int i;
                    int i2 = EventPlaybackActivity.this.mTimeValues / 3600;
                    int i3 = (EventPlaybackActivity.this.mTimeValues - (i2 * 3600)) / 60;
                    int i4 = EventPlaybackActivity.this.mTimeValues % 60;
                    int i5 = 30;
                    if (EventPlaybackActivity.this.mApp.mStartTime == 0) {
                        i = 30;
                    } else {
                        i = EventPlaybackActivity.this.mApp.mStartTime * 60;
                    }
                    int i6 = EventPlaybackActivity.this.mBaseValues - i;
                    String format = String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i6 / 3600), Integer.valueOf((i6 % 3600) / 60), Integer.valueOf(i6 % 60)});
                    String format2 = String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
                    EventPlaybackActivity.this.mtvStartTime.setText(format);
                    EventPlaybackActivity.this.mtvTime.setText(format2);
                    int i7 = EventPlaybackActivity.this.mTimeValues - i6;
                    if (i7 != EventPlaybackActivity.this.mSeekBar.getProgress() && !EventPlaybackActivity.this.mTracking) {
                        EventPlaybackActivity.this.mSeekBar.setProgress(i7);
                    }
                    if (EventPlaybackActivity.this.mApp.mEndTime != 0) {
                        i5 = EventPlaybackActivity.this.mApp.mEndTime * 60;
                    }
                    if (EventPlaybackActivity.this.mTimeValues - EventPlaybackActivity.this.mBaseValues >= i5) {
                        Message message = new Message();
                        message.what = 1;
                        message.setTarget(EventPlaybackActivity.this.mHandlerMessage);
                        message.sendToTarget();
                    }
                }
            });
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        SetConfiguration(configuration);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        StopMultiPlay();
        DvrNet dvrNet = this.mDvrNet;
        if (dvrNet != null) {
            dvrNet.CloseDeviceHandle();
            this.mDvrNet = null;
        }
        if (this.mApp.mPushInfo != null) {
            this.mApp.mPushInfo = null;
        }
        super.onStop();
    }
}
