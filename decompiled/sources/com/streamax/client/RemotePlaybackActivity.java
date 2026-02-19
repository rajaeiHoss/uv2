package com.streamax.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.zycs.UView.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Legacy remote playback activity for recorded files.
public class RemotePlaybackActivity extends Activity implements MultiplaybackInterface {
    private final String TAG = "RemotePlaybackActivity";
    public MyApp mApp = ((MyApp) getApplication());
    public AuTrack mAudioTrack = new AuTrack();
    public ProgressBar mBusyProgressBar;
    public int mChannel;
    public Context mContext;
    public int mDay;
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
    public boolean mTracking;
    public VideoGroup mVideoGroup;
    public int mYear;
    public boolean mbMute;
    public boolean mbPause;
    public boolean mbPlay;
    List<Map<String, Object>> mbmpList;
    public View mpopViewer;
    public TextView mtvSpeed;
    public TextView mtvTime;
    public PopupWindow pop;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mApp = (MyApp) getApplication();
        this.mInflater = LayoutInflater.from(this);
        this.mContext = this;
        setContentView(R.layout.multiplay);
        LoadViews();
        if (this.mApp.mPushInfo != null) {
            new Thread(new RemoteRunnable()).start();
            return;
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mYear = Integer.parseInt(extras.getString("year"));
            this.mMonth = Integer.parseInt(extras.getString("month"));
            this.mDay = Integer.parseInt(extras.getString("day"));
            this.mHour = Integer.parseInt(extras.getString("hour"));
            this.mMinute = Integer.parseInt(extras.getString("minute"));
            this.mSecond = Integer.parseInt(extras.getString("second"));
            this.mChannel = extras.getInt("channel");
            this.mStartTime = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(this.mHour), Integer.valueOf(this.mMinute), Integer.valueOf(this.mSecond)});
            new Thread(new RemoteRunnable()).start();
        }
    }

    public void LoadViews() {
        VideoGroup videoGroup = (VideoGroup) findViewById(R.id.multiplay_videogroup);
        this.mVideoGroup = videoGroup;
        videoGroup.LoadViews();
        this.mVideoGroup.SetInitMode(1);
        this.mVideoGroup.getVideoView(0).setMax(true);
        this.mVideoGroup.getVideoView(0).setOsdState(false);
        this.mBusyProgressBar = (ProgressBar) this.mVideoGroup.findViewById(R.id.multiplay_busyprogress);
        this.mProcessbar = (LinearLayout) findViewById(R.id.remote_playback_progress);
        this.mtvSpeed = (TextView) findViewById(R.id.remote_playback_playrate_text);
        this.mtvTime = (TextView) findViewById(R.id.remote_playback_progress_text);
        SeekBar seekBar = (SeekBar) findViewById(R.id.remote_playback_progressbar);
        this.mSeekBar = seekBar;
        seekBar.setMax(1440);
        this.mSeekBar.setProgress(0);
        this.mSeekBar.setSecondaryProgress(0);
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                RemotePlaybackActivity.this.mTracking = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (RemotePlaybackActivity.this.mbPlay) {
                    if (!RemotePlaybackActivity.this.mbPause) {
                        RemotePlaybackActivity remotePlaybackActivity = RemotePlaybackActivity.this;
                        remotePlaybackActivity.Seek(remotePlaybackActivity.mSeekBar.getMax(), RemotePlaybackActivity.this.mSeekBar.getProgress());
                    } else {
                        return;
                    }
                }
                RemotePlaybackActivity.this.mTracking = false;
            }
        });
        this.mOnClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.multiplay_controlbar_capture /*2131362747*/:
                        if (RemotePlaybackActivity.this.mDvrNet != null) {
                            if (!Environment.getExternalStorageState().equals("mounted")) {
                                Toast.makeText(RemotePlaybackActivity.this, RemotePlaybackActivity.this.mContext.getString(R.string.ExternalStorageerror), 0).show();
                                return;
                            }
                            MediaPlayer.create(RemotePlaybackActivity.this.mContext, R.raw.capture).start();
                            RemotePlaybackActivity remotePlaybackActivity = RemotePlaybackActivity.this;
                            remotePlaybackActivity.popImageViewer(remotePlaybackActivity.CaptureImage());
                            return;
                        }
                        return;
                    case R.id.multiplay_controlbar_fast /*2131362748*/:
                        int i = RemotePlaybackActivity.this.mSpeed;
                        if (i == -4) {
                            RemotePlaybackActivity.this.mSpeed = -2;
                        } else if (i == -2) {
                            RemotePlaybackActivity.this.mSpeed = 1;
                        } else if (i == 4) {
                            RemotePlaybackActivity.this.mSpeed = 1;
                        } else if (i == 1) {
                            RemotePlaybackActivity.this.mSpeed = 2;
                        } else if (i != 2) {
                            RemotePlaybackActivity.this.mSpeed = 1;
                        } else {
                            RemotePlaybackActivity.this.mSpeed = 4;
                        }
                        if (RemotePlaybackActivity.this.mDvrNet != null) {
                            RemotePlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(RemotePlaybackActivity.this.mSpeed);
                        }
                        RemotePlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(RemotePlaybackActivity.this.mSpeed)}));
                        return;
                    case R.id.multiplay_controlbar_play /*2131362750*/:
                        ImageView imageView = (ImageView) RemotePlaybackActivity.this.findViewById(R.id.multiplay_controlbar_play);
                        if (!RemotePlaybackActivity.this.mbPlay) {
                            imageView.setImageResource(R.drawable.pause);
                            int progress = RemotePlaybackActivity.this.mSeekBar.getProgress();
                            if (progress != 0) {
                                RemotePlaybackActivity remotePlaybackActivity2 = RemotePlaybackActivity.this;
                                remotePlaybackActivity2.mStartTime = String.format("%04d%02d%02d%02d%02d00", new Object[]{Integer.valueOf(remotePlaybackActivity2.mYear), Integer.valueOf(RemotePlaybackActivity.this.mMonth), Integer.valueOf(RemotePlaybackActivity.this.mDay), Integer.valueOf(progress / 60), Integer.valueOf(progress % 60)});
                            }
                            new Thread(new RemoteRunnable()).start();
                            return;
                        }
                        RemotePlaybackActivity remotePlaybackActivity3 = RemotePlaybackActivity.this;
                        remotePlaybackActivity3.mbPause = !remotePlaybackActivity3.mbPause;
                        if (RemotePlaybackActivity.this.mbPause) {
                            imageView.setImageResource(R.drawable.play);
                        } else {
                            imageView.setImageResource(R.drawable.pause);
                        }
                        if (RemotePlaybackActivity.this.mDvrNet != null) {
                            RemotePlaybackActivity.this.mDvrNet.MultiPlayPause(RemotePlaybackActivity.this.mbPause);
                            RemotePlaybackActivity.this.mSpeed = 1;
                            RemotePlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(RemotePlaybackActivity.this.mSpeed);
                            RemotePlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(RemotePlaybackActivity.this.mSpeed)}));
                            return;
                        }
                        return;
                    case R.id.multiplay_controlbar_slow /*2131362751*/:
                        if (RemotePlaybackActivity.this.mDvrNet != null) {
                            int i2 = RemotePlaybackActivity.this.mSpeed;
                            if (i2 == -4) {
                                RemotePlaybackActivity.this.mSpeed = 1;
                            } else if (i2 == -2) {
                                RemotePlaybackActivity.this.mSpeed = -4;
                            } else if (i2 == 4) {
                                RemotePlaybackActivity.this.mSpeed = 2;
                            } else if (i2 == 1) {
                                RemotePlaybackActivity.this.mSpeed = -2;
                            } else if (i2 != 2) {
                                RemotePlaybackActivity.this.mSpeed = -2;
                            } else {
                                RemotePlaybackActivity.this.mSpeed = 1;
                            }
                            if (RemotePlaybackActivity.this.mDvrNet != null) {
                                RemotePlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(RemotePlaybackActivity.this.mSpeed);
                            }
                            RemotePlaybackActivity.this.mtvSpeed.setText(String.format("%dX", new Object[]{Integer.valueOf(RemotePlaybackActivity.this.mSpeed)}));
                            return;
                        }
                        return;
                    case R.id.multiplay_controlbar_sound /*2131362752*/:
                        ImageView imageView2 = (ImageView) RemotePlaybackActivity.this.findViewById(R.id.multiplay_controlbar_sound);
                        if (RemotePlaybackActivity.this.mbMute) {
                            RemotePlaybackActivity.this.mbMute = false;
                            imageView2.setImageResource(R.drawable.soundopen);
                        } else {
                            RemotePlaybackActivity.this.mbMute = true;
                            imageView2.setImageResource(R.drawable.soundclose);
                        }
                        if (RemotePlaybackActivity.this.mDvrNet != null) {
                            RemotePlaybackActivity.this.mAudioTrack.SetMute(RemotePlaybackActivity.this.mbMute);
                            return;
                        }
                        return;
                    case R.id.multiplay_controlbar_stop /*2131362753*/:
                        RemotePlaybackActivity.this.StopMultiPlay();
                        return;
                    case R.id.multiplay_title_button_cancel /*2131362755*/:
                        RemotePlaybackActivity.this.StopMultiPlay();
                        RemotePlaybackActivity.this.finish();
                        return;
                    default:
                        return;
                }
            }
        };
        findViewById(R.id.multiplay_title_button_cancel).setOnClickListener(this.mOnClickListener);
        ImageView imageView = (ImageView) findViewById(R.id.multiplay_controlbar_stop);
        this.mImageStop = imageView;
        imageView.setOnClickListener(this.mOnClickListener);
        ImageView imageView2 = (ImageView) findViewById(R.id.multiplay_controlbar_slow);
        this.mImageSlow = imageView2;
        imageView2.setOnClickListener(this.mOnClickListener);
        ImageView imageView3 = (ImageView) findViewById(R.id.multiplay_controlbar_play);
        this.mImagePlay = imageView3;
        imageView3.setOnClickListener(this.mOnClickListener);
        ImageView imageView4 = (ImageView) findViewById(R.id.multiplay_controlbar_fast);
        this.mImageFast = imageView4;
        imageView4.setOnClickListener(this.mOnClickListener);
        ImageView imageView5 = (ImageView) findViewById(R.id.multiplay_controlbar_capture);
        this.mImageCapture = imageView5;
        imageView5.setOnClickListener(this.mOnClickListener);
        ImageView imageView6 = (ImageView) findViewById(R.id.multiplay_controlbar_sound);
        this.mImageSound = imageView6;
        imageView6.setOnClickListener(this.mOnClickListener);
        this.mHandlerMessage = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 0) {
                    if (RemotePlaybackActivity.this.mbPlay) {
                        new AlertDialog.Builder(RemotePlaybackActivity.this.mContext).setTitle(RemotePlaybackActivity.this.mContext.getString(R.string.app_name)).setIcon(RemotePlaybackActivity.this.mContext.getResources().getDrawable(R.drawable.icon)).setMessage(RemotePlaybackActivity.this.mContext.getString(R.string.remoteplaybackfailed)).setPositiveButton(RemotePlaybackActivity.this.mContext.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
                    } else {
                        return;
                    }
                }
                super.handleMessage(message);
            }
        };
        this.mMessageCallback = new MessageCallback() {
            public void sendMessage(int i) {
                if (i == 0) {
                    Message message = new Message();
                    message.what = 0;
                    message.setTarget(RemotePlaybackActivity.this.mHandlerMessage);
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
            int i = 0;
            linearLayout.setBackgroundColor(Color.argb(200, 40, 0, 0));
            linearLayout.setPadding(0, 0, 0, 0);
            new LinearLayout.LayoutParams(-2, -2);
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
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        imageView.setAdjustViewBounds(true);
                        linearLayout2.addView(imageView);
                        i3++;
                    }
                    linearLayout.addView(linearLayout2);
                    i2++;
                    i = 0;
                } else {
                    ((LinearLayout) this.mpopViewer.findViewById(R.id.preview_capture_imagegroup)).addView(linearLayout);
                    this.mPopupCapture = null;
                    PopupWindow popupWindow = new PopupWindow(this.mpopViewer, -2, -2, true);
                    this.mPopupCapture = popupWindow;
                    popupWindow.setOutsideTouchable(false);
                    this.mPopupCapture.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
                    this.mPopupCapture.showAtLocation(findViewById(R.id.remoteplay_mainwnd), 17, 0, 0);
                    this.mPopupCapture.update();
                    this.mpopViewer.findViewById(R.id.preview_capture_save).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            RemotePlaybackActivity.this.mPopupCapture.dismiss();
                        }
                    });
                    this.mpopViewer.findViewById(R.id.preview_capture_cancel).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            for (int i = 0; i < RemotePlaybackActivity.this.mbmpList.size(); i++) {
                                String obj = RemotePlaybackActivity.this.mbmpList.get(i).get("path").toString();
                                if (obj != null) {
                                    new File(obj).delete();
                                }
                            }
                            RemotePlaybackActivity.this.mPopupCapture.dismiss();
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
                    RemotePlaybackActivity.this.pop.dismiss();
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
            findViewById(R.id.multiplay_title).setVisibility(8);
            findViewById(R.id.multiplay_controlbar).setVisibility(8);
            this.mProcessbar.setVisibility(8);
        } else if (configuration.orientation == 1) {
            findViewById(R.id.multiplay_title).setVisibility(0);
            findViewById(R.id.multiplay_controlbar).setVisibility(0);
            this.mProcessbar.setVisibility(0);
        }
    }

    public class MultiplayRemoteRunnable implements Runnable {
        public MultiplayRemoteRunnable() {
        }

        public void run() {
            RemotePlaybackActivity.this.mDvrNet.MultiPlayStop();
            RemotePlaybackActivity.this.mAudioTrack.mPlayer.play();
            RemotePlaybackActivity.this.mAudioTrack.SetMute(RemotePlaybackActivity.this.mbMute);
            RemotePlaybackActivity.this.mDvrNet.SetMultiplayInterface((MultiplaybackInterface) this);
            RemotePlaybackActivity.this.mDvrNet.SetAudioInterface(RemotePlaybackActivity.this.mAudioTrack);
            RemotePlaybackActivity.this.mDvrNet.MultiPlay(0, RemotePlaybackActivity.this.mMultiplayChannelArray, 1, RemotePlaybackActivity.this.mMultiplayTime, "", "");
        }
    }

    public class RemoteRunnable implements Runnable {
        public RemoteRunnable() {
        }

        public void run() {
            if (RemotePlaybackActivity.this.mDvrNet == null) {
                RemotePlaybackActivity.this.mDvrNet = MyApp.dvrNet;
            }
            RemotePlaybackActivity.this.mAudioTrack.mPlayer.play();
            RemotePlaybackActivity.this.mAudioTrack.SetMute(RemotePlaybackActivity.this.mbMute);
            RemotePlaybackActivity.this.mDvrNet.SetMultiplayInterface(RemotePlaybackActivity.this);
            RemotePlaybackActivity.this.mDvrNet.SetAudioInterface(RemotePlaybackActivity.this.mAudioTrack);
            RemotePlaybackActivity.this.mAudioTrack.SwitchChannels(RemotePlaybackActivity.this.mChannel);
            RemotePlaybackActivity.this.mDvrNet.setMessageCallback(RemotePlaybackActivity.this.mMessageCallback);
            if (RemotePlaybackActivity.this.mDvrNet.MultiPlay(0, 1 << RemotePlaybackActivity.this.mChannel, 1, RemotePlaybackActivity.this.mStartTime, "", "") == 0) {
                RemotePlaybackActivity.this.mbPlay = true;
                RemotePlaybackActivity.this.mSpeed = 1;
                RemotePlaybackActivity.this.mDvrNet.MuitiPlaySetSpeed(1);
                RemotePlaybackActivity.this.mtvSpeed.post(new Runnable() {
                    public void run() {
                        RemotePlaybackActivity.this.mtvSpeed.setText("1X");
                        RemotePlaybackActivity.this.mImagePlay.setImageResource(R.drawable.pause);
                    }
                });
                return;
            }
            Message message = new Message();
            message.what = 0;
            message.setTarget(RemotePlaybackActivity.this.mHandlerMessage);
            message.sendToTarget();
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
            this.mDvrNet.MultiPlaySeek(String.format("%04d%02d%02d%02d%02d00", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(i2 / 60), Integer.valueOf(i2 % 60)}));
        }
    }

    public void StopMultiPlay() {
        DvrNet dvrNet = this.mDvrNet;
        if (dvrNet != null) {
            dvrNet.MultiPlayStop();
            this.mAudioTrack.mPlayer.Stop();
            this.mtvTime.setText("00:00:00");
            this.mSeekBar.setProgress(0);
            this.mtvSpeed.setText("1X");
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
                    int i = RemotePlaybackActivity.this.mTimeValues / 3600;
                    RemotePlaybackActivity.this.mtvTime.setText(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf((RemotePlaybackActivity.this.mTimeValues - (i * 3600)) / 60), Integer.valueOf(RemotePlaybackActivity.this.mTimeValues % 60)}));
                    int i2 = RemotePlaybackActivity.this.mTimeValues / 60;
                    if (i2 != RemotePlaybackActivity.this.mSeekBar.getProgress() && !RemotePlaybackActivity.this.mTracking) {
                        RemotePlaybackActivity.this.mSeekBar.setProgress(i2);
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
        if (this.mApp.mPushInfo != null) {
            DvrNet dvrNet = this.mDvrNet;
            if (dvrNet != null) {
                dvrNet.CloseDeviceHandle();
                this.mDvrNet = null;
            }
            this.mApp.mPushInfo = null;
        }
        super.onStop();
    }
}
