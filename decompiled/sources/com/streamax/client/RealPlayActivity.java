package com.streamax.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.dvr.avstream.AVStream;
import com.dvr.avstream.AuTrack;
import com.dvr.avstream.MyCallInterface;
import com.dvr.net.DvrNet;
import com.streamax.Configs;
import com.streamax.client.record.RecordStatus;
import com.streamax.client.ui.devlist.DeleteDeviceEvent;
import com.streamax.client.ui.devlist.DevInfoEvent;
import com.streamax.client.ui.devlist.db.GroupBean;
import com.streamax.client.ui.devlist.db.GroupDao;
import com.streamax.client.ui.devlist.db.GroupDaoForNormal;
import com.streamax.client.ui.devlist.db.GroupDaoForServer;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.utils.AppProxy;
import com.streamax.utils.SpUtils;
import com.streamax.utils.ToastSf;
import com.zycs.UView.R;
import de.greenrobot.event.EventBus;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// Live preview activity for real-time streams and controls.
public class RealPlayActivity extends Activity implements Runnable, MyCallInterface, PopupWindow.OnDismissListener, SurfaceCallBackInterface {
    public static final int CHANNEL_NUMBER = 32;
    private static Object lock = new Object();
    public boolean bConfiguration = false;
    public AVStream[] mAVStream = new AVStream[32];
    public MyApp mApp;
    public AuTrack mAudioTrack = new AuTrack();
    public List<GroupBean> mBeanDatas;
    public Stack<Map<String, Object>> mBitmapStack = new Stack<>();
    public int mChCount;
    public Context mContext;
    public boolean mCurFocus = true;
    public GroupDao mDao;
    public DevInfoEvent mDevInfoBean;
    public String mGroupName;
    public Handler mHandler = AppProxy.getHandler();
    public ImageView mImageCapture;
    public ImageView mImagePlay;
    public ImageView mImagePtz;
    public ImageView mImageRecord;
    public ImageView mImageSound;
    public LayoutInflater mInflater;
    public int mInitMode;
    public int mK;
    public Handler mMessageHandler;
    public OrientationEventListener mOEventListener;
    public View.OnClickListener mOnClickListener;
    public ImageView mPopImagePlay;
    public PopupWindow mPopupCapture;
    public View mRealPlay;
    public Map<Integer, String> mRecordPathMap = new Hashtable();
    public int mSelectChannel;
    public int mSelectId;
    public Map<String, Object> mSelectMap = new HashMap();
    public Map<Integer, Integer> mSelectMaps = new HashMap();
    public ProgressBar mStopPbLoading;
    public FrameLayout mTitlebar;
    public Handler mToastHandler;
    public LinearLayout mToolbar;
    public VideoContainer mVideoContainer;
    public FrameLayout mVideoInfo;
    /* access modifiers changed from: private */
    public List<String> mViewIndexDatas = new ArrayList();
    public SingleViewInfo[] mViewInfoArray = new SingleViewInfo[32];
    public boolean mbMute = false;
    public boolean mbPreview = false;
    public boolean mbPtz = false;
    public boolean[] mbRecordArray = new boolean[32];
    public boolean mbState = false;
    /* access modifiers changed from: private */
    public boolean mbSwitch;
    public List<Map<String, Object>> mbmpList;
    private DbHelper mdbHelper;
    public int mnMaxView = 16;
    public View mpopViewer;
    public TextView mtvVideoInformation;

    public void run() {
    }

    public void setPtz(int channel, int command) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        this.mContext = AppProxy.getContext();
        this.mApp = (MyApp) getApplication();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        this.mInflater = layoutInflater;
        View realPlayView = layoutInflater.inflate(R.layout.realplaypage, (ViewGroup) null);
        this.mRealPlay = realPlayView;
        this.mStopPbLoading = (ProgressBar) realPlayView.findViewById(R.id.realplay_pbloading);
        this.mdbHelper = new DbHelper(this.mContext, DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        setContentView(this.mRealPlay);
        FindViews();
        new Thread(new SwitchChannelRunnable()).start();
        new Thread(new Runnable() {
            public void run() {
                RealPlayActivity.this.AutoPlay();
            }
        }).start();
        OrientationEventListener orientationListener = new OrientationEventListener(this, 3) {
            public void onOrientationChanged(int orientation) {
                try {
                    int autoRotateEnabled = Settings.System.getInt(RealPlayActivity.this.getApplication().getContentResolver(), "accelerometer_rotation");
                    if (orientation != -1) {
                        if (orientation != 360) {
                            if (orientation != 0) {
                                if (orientation == 90) {
                                    if (autoRotateEnabled != 0) {
                                        RealPlayActivity.this.setVideoGroupMax(true);
                                        return;
                                    }
                                    return;
                                } else if (orientation != 180) {
                                    if (orientation == 270 && autoRotateEnabled != 0) {
                                        RealPlayActivity.this.setVideoGroupMax(true);
                                        return;
                                    }
                                    return;
                                } else {
                                    return;
                                }
                            }
                        }
                        if (autoRotateEnabled != 0) {
                            RealPlayActivity.this.setVideoGroupMax(false);
                        }
                    }
                } catch (Settings.SettingNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        this.mOEventListener = orientationListener;
        if (orientationListener.canDetectOrientation()) {
            this.mOEventListener.enable();
        } else {
            this.mOEventListener.disable();
        }
    }

    public void FindViews() {
        VideoContainer videoContainer = (VideoContainer) this.mRealPlay.findViewById(R.id.realplay_videogroup);
        this.mVideoContainer = videoContainer;
        if (videoContainer != null) {
            videoContainer.SetActivity(this);
            this.mVideoContainer.LoadViews();
            this.mVideoContainer.SetInitMode(32);
            setSurfaceListener(32);
        }
        this.mTitlebar = (FrameLayout) this.mRealPlay.findViewById(R.id.title_realplay);
        this.mVideoInfo = (FrameLayout) this.mRealPlay.findViewById(R.id.realplay_videoinformation);
        this.mToolbar = (LinearLayout) this.mRealPlay.findViewById(R.id.realplayvideocontrol);
        this.mtvVideoInformation = (TextView) this.mRealPlay.findViewById(R.id.realplay_videoinformation_text);
        this.mOnClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                String recordMessage;
                String soundMessage;
                switch (view.getId()) {
                    case R.id.realplay_toolbar_capture /*2131362949*/:
                        int focusChannel = RealPlayActivity.this.mVideoContainer.getFocusChannel();
                        if (RealPlayActivity.this.mViewInfoArray[focusChannel] != null && RealPlayActivity.this.mAVStream[focusChannel] != null) {
                            RealPlayActivity.this.mImageCapture.setClickable(false);
                            if (!Environment.getExternalStorageState().equals("mounted")) {
                                RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.mContext.getString(R.string.ExternalStorageerror));
                            }
                            MediaPlayer.create(RealPlayActivity.this.mContext, R.raw.capture).start();
                            RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.mContext.getString(R.string.capture_successful));
                            RealPlayActivity realPlayActivity = RealPlayActivity.this;
                            realPlayActivity.popImageViewer(realPlayActivity.CaptureImage(realPlayActivity.mVideoContainer.getFocusChannel()));
                            return;
                        }
                        return;
                    case R.id.realplay_toolbar_ptz /*2131362950*/:
                        RealPlayActivity.this.mToolbar.findViewById(R.id.realplay_toolbar_ptz);
                        int focusChannel2 = RealPlayActivity.this.mVideoContainer.getFocusChannel();
                        if (RealPlayActivity.this.mViewInfoArray[focusChannel2] != null && RealPlayActivity.this.mViewInfoArray[focusChannel2].getNet() != null) {
                            if (RealPlayActivity.this.mbPtz) {
                                if (RealPlayActivity.this.mVideoContainer.SetPtzMode(false, 0, RealPlayActivity.this.mViewInfoArray[focusChannel2].getNet())) {
                                    RealPlayActivity.this.mbPtz = false;
                                    RealPlayActivity.this.mtvVideoInformation.setText(R.string.closeptzinfo);
                                    return;
                                }
                                return;
                            } else if (RealPlayActivity.this.mVideoContainer.SetPtzMode(true, -1, RealPlayActivity.this.mViewInfoArray[focusChannel2].getNet())) {
                                RealPlayActivity.this.mbPtz = true;
                                RealPlayActivity.this.mtvVideoInformation.setText(R.string.openptzinfo);
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    case R.id.realplay_toolbar_record /*2131362951*/:
                        int focusChannel3 = RealPlayActivity.this.mVideoContainer.getFocusChannel();
                        if (RealPlayActivity.this.mAVStream[focusChannel3] != null) {
                            RealPlayActivity.this.mbRecordArray[focusChannel3] = !RealPlayActivity.this.mbRecordArray[focusChannel3];
                            if (RealPlayActivity.this.mbRecordArray[focusChannel3]) {
                                recordMessage = RealPlayActivity.this.mContext.getString(R.string.startrecording);
                                RealPlayActivity.this.mImageRecord.setImageResource(R.drawable.record_normal);
                                RealPlayActivity realPlayActivity2 = RealPlayActivity.this;
                                realPlayActivity2.StartRecord(realPlayActivity2.mVideoContainer.getFocusChannel());
                            } else {
                                recordMessage = RealPlayActivity.this.mContext.getString(R.string.stoprecording);
                                RealPlayActivity.this.mImageRecord.setImageResource(R.drawable.record_unuse);
                                RealPlayActivity realPlayActivity3 = RealPlayActivity.this;
                                realPlayActivity3.StopRecord(realPlayActivity3.mVideoContainer.getFocusChannel());
                            }
                            RealPlayActivity.this.mtvVideoInformation.setText(recordMessage);
                            return;
                        }
                        return;
                    case R.id.realplay_toolbar_sound /*2131362952*/:
                        ImageView imageView = (ImageView) RealPlayActivity.this.mToolbar.findViewById(R.id.realplay_toolbar_sound);
                        if (RealPlayActivity.this.mbMute) {
                            RealPlayActivity.this.mbMute = false;
                            imageView.setImageResource(R.drawable.soundopen);
                            soundMessage = RealPlayActivity.this.mContext.getString(R.string.opensound);
                        } else {
                            RealPlayActivity.this.mbMute = true;
                            imageView.setImageResource(R.drawable.soundclose);
                            soundMessage = RealPlayActivity.this.mContext.getString(R.string.closesound);
                        }
                        RealPlayActivity.this.mAudioTrack.SetMute(RealPlayActivity.this.mbMute);
                        RealPlayActivity.this.mtvVideoInformation.setText(soundMessage);
                        return;
                    case R.id.realplay_toolbar_stop /*2131362953*/:
                        new Thread(new Runnable() {
                            public void run() {
                                Log.e("stop", "stop");
                                RealPlayActivity.this.StopPlay(RealPlayActivity.this.mVideoContainer.getFocusChannel());
                            }
                        }).start();
                        return;
                    default:
                        return;
                }
            }
        };
        ImageView imageView = (ImageView) this.mToolbar.findViewById(R.id.realplay_toolbar_ptz);
        this.mImagePtz = imageView;
        imageView.setOnClickListener(this.mOnClickListener);
        ImageView imageView2 = (ImageView) this.mToolbar.findViewById(R.id.realplay_toolbar_record);
        this.mImageRecord = imageView2;
        imageView2.setOnClickListener(this.mOnClickListener);
        ImageView imageView3 = (ImageView) this.mToolbar.findViewById(R.id.realplay_toolbar_capture);
        this.mImageCapture = imageView3;
        imageView3.setOnClickListener(this.mOnClickListener);
        ImageView imageView4 = (ImageView) this.mToolbar.findViewById(R.id.realplay_toolbar_stop);
        this.mImagePlay = imageView4;
        imageView4.setOnClickListener(this.mOnClickListener);
        ImageView imageView5 = (ImageView) this.mToolbar.findViewById(R.id.realplay_toolbar_sound);
        this.mImageSound = imageView5;
        imageView5.setOnClickListener(this.mOnClickListener);
        this.mMessageHandler = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 0) {
                    RealPlayActivity.this.AutoPlay();
                }
                super.handleMessage(message);
            }
        };
    }

    public void AutoPlay() {
        DevInfoBean devInfoBean;
        if (this.mApp.mbAutoPlay) {
            for (int viewIndex = 0; viewIndex < this.mnMaxView; viewIndex++) {
                SharedPreferences sharedPreferences = getSharedPreferences(String.format("realplay%02d", new Object[]{Integer.valueOf(viewIndex)}), 0);
                String savedDeviceInfo = sharedPreferences.getString("info", "");
                int savedChannel = sharedPreferences.getInt("channel", 0);
                int savedLoginType = SpUtils.getInt(Configs.Key.LoginType, -1);
                if (!(savedDeviceInfo == null || savedDeviceInfo.length() == 0 || savedLoginType != MyApp.loginType)) {
                    try {
                        try {
                            DevInfoBean savedDevInfoBean = (DevInfoBean) new ObjectInputStream(new ByteArrayInputStream(Base64.decode(savedDeviceInfo, 0))).readObject();
                            savedDevInfoBean.PrintOut();
                            if (MyApp.loginType == 0) {
                                devInfoBean = this.mdbHelper.query(savedDevInfoBean.mDevName);
                            } else {
                                if (this.mApp.mWebService.mDevList.size() == 0) {
                                    this.mApp.mWebService.GetTerminalInfoAndroid(true);
                                }
                                devInfoBean = this.mApp.mWebService.query(savedDevInfoBean.mDevName);
                            }
                            if (savedDevInfoBean != null) {
                                if (devInfoBean != null) {
                                    if (savedDevInfoBean.mDevIp.compareTo(devInfoBean.mDevIp) == 0 && savedDevInfoBean.mMediaPort == devInfoBean.mMediaPort) {
                                        StartPlay(devInfoBean.mDevId, savedChannel, viewIndex);
                                    }
                                }
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } catch (StreamCorruptedException e2) {
                        e2.printStackTrace();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
    }

    public void StartRecord(int viewIndex) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            this.mtvVideoInformation.setText(this.mContext.getString(R.string.ExternalStorageerror));
            return;
        }
        Calendar timestamp = Calendar.getInstance();
        String recordPrefix = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(timestamp.get(1)), Integer.valueOf(timestamp.get(2) + 1), Integer.valueOf(timestamp.get(5)), Integer.valueOf(timestamp.get(11)), Integer.valueOf(timestamp.get(12)), Integer.valueOf(timestamp.get(13))});
        if (this.mAVStream[viewIndex] != null) {
            String channelSuffix = String.format("%02d.264", new Object[]{Integer.valueOf(this.mViewInfoArray[viewIndex].mChannel + 1)});
            this.mRecordPathMap.put(Integer.valueOf(viewIndex), recordPrefix + channelSuffix);
            this.mAVStream[viewIndex].StartRecord(Configs.Key.RecordDirPath + recordPrefix + channelSuffix);
            this.mViewInfoArray[viewIndex].getNet().RequestIFrame(this.mViewInfoArray[viewIndex].mChannel, 0);
            this.mVideoContainer.SetRecState(viewIndex, true);
        }
    }

    public void StopRecord(final int viewIndex) {
        AVStream[] aVStreamArr = this.mAVStream;
        if (aVStreamArr[viewIndex] != null) {
            aVStreamArr[viewIndex].StopRecord();
            this.mVideoContainer.post(new Runnable() {
                public void run() {
                    RealPlayActivity.this.mVideoContainer.SetRecState(viewIndex, false);
                }
            });
            this.mbRecordArray[viewIndex] = false;
        }
    }

    public Stack<Map<String, Object>> CaptureImage(int viewIndex) {
        Calendar timestamp = Calendar.getInstance();
        String capturePrefix = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(timestamp.get(1)), Integer.valueOf(timestamp.get(2) + 1), Integer.valueOf(timestamp.get(5)), Integer.valueOf(timestamp.get(11)), Integer.valueOf(timestamp.get(12)), Integer.valueOf(timestamp.get(13))});
        if (this.mAVStream[viewIndex] != null) {
            HashMap hashMap = new HashMap();
            String capturePath = Configs.Key.CaptureDirPath + capturePrefix + String.format("%02d.bmp", new Object[]{Integer.valueOf(this.mViewInfoArray[viewIndex].mChannel + 1)});
            hashMap.put("channel", Integer.valueOf(viewIndex));
            hashMap.put("path", capturePath);
            if (this.mAVStream[viewIndex].Capture(capturePath) == 0) {
                this.mBitmapStack.push(hashMap);
            }
        }
        return this.mBitmapStack;
    }

    public void popImageViewer(Stack<Map<String, Object>> stack) {
        if (stack.size() != 0) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            options.inTempStorage = new byte[829440];
            this.mpopViewer = this.mInflater.inflate(R.layout.captureimageviewer, (ViewGroup) null);
            LinearLayout linearLayout = new LinearLayout(this.mContext);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            linearLayout.setBackgroundColor(Color.argb(200, 40, 40, 40));
            linearLayout.setPadding(0, 0, 0, 0);
            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
            layoutParams.width = MyApp.getScreenWidth();
            layoutParams.height = (MyApp.getScreenWidth() * 3) / 4;
            final String obj = stack.pop().get("path").toString();
            LinearLayout linearLayout2 = new LinearLayout(this.mContext);
            linearLayout2.setOrientation(0);
            linearLayout2.setPadding(0, 0, 0, 0);
            ImageView imageView = new ImageView(this.mContext);
            imageView.setImageBitmap(BitmapFactory.decodeFile(obj));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new TableLayout.LayoutParams(MyApp.getScreenWidth(), (MyApp.getScreenWidth() * 3) / 4));
            linearLayout.addView(linearLayout2, layoutParams);
            linearLayout2.addView(imageView, layoutParams);
            ((LinearLayout) this.mpopViewer.findViewById(R.id.preview_capture_imagegroup)).addView(linearLayout);
            this.mPopupCapture = null;
            PopupWindow popupWindow = new PopupWindow(this.mpopViewer, -1, -2, true);
            this.mPopupCapture = popupWindow;
            popupWindow.setOnDismissListener(this);
            this.mpopViewer.measure(0, 0);
            this.mPopupCapture.setOutsideTouchable(false);
            this.mPopupCapture.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
            this.mPopupCapture.showAtLocation(this.mRealPlay, 17, 0, 0);
            this.mPopupCapture.update();
            this.mpopViewer.findViewById(R.id.preview_capture_save).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.getString(R.string.save_successful));
                    RealPlayActivity.this.mPopupCapture.dismiss();
                }
            });
            this.mpopViewer.findViewById(R.id.preview_capture_cancel).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (obj != null) {
                        new File(obj).delete();
                    }
                    RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.getString(R.string.save_cancel));
                    RealPlayActivity.this.mPopupCapture.dismiss();
                }
            });
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            this.bConfiguration = true;
            setVideoGroupMax(true);
        } else if (configuration.orientation == 1) {
            this.bConfiguration = false;
            setVideoGroupMax(false);
        }
    }

    public void setVideoGroupMax(boolean fullscreen) {
        if (fullscreen) {
            this.mTitlebar.setVisibility(8);
            this.mVideoInfo.setVisibility(8);
            this.mToolbar.setVisibility(8);
            return;
        }
        this.mTitlebar.setVisibility(0);
        this.mVideoInfo.setVisibility(0);
        this.mToolbar.setVisibility(0);
    }

    public void SetPtzState(boolean active) {
        ImageView imageView = (ImageView) findViewById(R.id.realplay_toolbar_ptz);
        if (active) {
            imageView.setImageResource(R.drawable.ptz_active);
        } else {
            imageView.setImageResource(R.drawable.ptz_normal);
        }
    }

    public void SetStreamDecodeState(int viewIndex, boolean paused) {
        AVStream[] aVStreamArr = this.mAVStream;
        if (aVStreamArr[viewIndex] != null) {
            aVStreamArr[viewIndex].SetStreamDecodeState(paused);
        }
    }

    public void StartPlay(int deviceId, int deviceChannel, final int requestedViewIndex) {
        DevInfoBean devInfoBean;
        synchronized (lock) {
            if (MyApp.loginType == 0) {
                devInfoBean = this.mdbHelper.query(deviceId);
            } else {
                devInfoBean = this.mApp.mWebService.query(deviceId);
            }
            if (devInfoBean != null) {
                int errorCode = -1;
                int channel = requestedViewIndex;
                if (channel == -1) {
                    channel = this.mVideoContainer.getFocusChannel();
                }
                for (int existingViewIndex = 0; existingViewIndex < this.mnMaxView; existingViewIndex++) {
                    SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
                    if (singleViewInfoArr[existingViewIndex] != null) {
                        if (singleViewInfoArr[existingViewIndex].check(devInfoBean) && deviceChannel == this.mViewInfoArray[existingViewIndex].mChannel) {
                            this.mHandler.post(new Runnable() {
                                public void run() {
                                    RealPlayActivity realPlayActivity = RealPlayActivity.this;
                                    Toast makeText = Toast.makeText(realPlayActivity, realPlayActivity.getString(R.string.openchannelexits), 0);
                                    makeText.setGravity(17, 0, 0);
                                    makeText.show();
                                }
                            });
                            return;
                        }
                    }
                }
                final int targetChannel = channel;
                this.mVideoContainer.post(new Runnable() {
                    public void run() {
                        RealPlayActivity.this.mVideoContainer.SetBusyState(targetChannel, true);
                        RealPlayActivity.this.mVideoContainer.getVideoView(targetChannel).setVisibility(0);
                    }
                });
                AVStream[] aVStreamArr = this.mAVStream;
                if (aVStreamArr[channel] != null) {
                    aVStreamArr[channel].StopRecord();
                    this.mAVStream[channel].StopPlay();
                    this.mAVStream[channel].CloseHandle();
                } else {
                    aVStreamArr[channel] = new AVStream();
                }
                DvrNet dvrNet = null;
                for (int existingViewIndex = 0; existingViewIndex < this.mnMaxView; existingViewIndex++) {
                    SingleViewInfo[] singleViewInfoArr2 = this.mViewInfoArray;
                    if (singleViewInfoArr2[existingViewIndex] != null) {
                        if (singleViewInfoArr2[existingViewIndex].check(devInfoBean)) {
                            dvrNet = this.mViewInfoArray[existingViewIndex].getNet();
                        }
                    }
                }
                if (dvrNet == null) {
                    this.mViewInfoArray[channel] = new SingleViewInfo();
                    this.mViewInfoArray[channel].setNet(new DvrNet());
                    Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(this.mViewInfoArray[channel].getNet(), devInfoBean, this.mApp);
                    if (connDeviceProxy != null) {
                        errorCode = ((Integer) connDeviceProxy.get("errorcode")).intValue();
                    }
                    if (errorCode == 0) {
                        AVStream[] aVStreamArr2 = this.mAVStream;
                        if (aVStreamArr2[channel] == null) {
                            aVStreamArr2[channel] = new AVStream();
                        }
                        if (this.mAudioTrack == null) {
                            this.mAudioTrack = new AuTrack();
                        }
                        this.mAVStream[channel].GetHandle(channel);
                        this.mVideoContainer.SetPlayState(channel, true);
                        this.mViewInfoArray[channel].setDeviceInfo(devInfoBean);
                        this.mViewInfoArray[channel].mChannel = deviceChannel;
                        this.mAudioTrack.mPlayer.play();
                        this.mAudioTrack.SetMute(this.mbMute);
                        if (channel == this.mVideoContainer.getFocusChannel()) {
                            this.mAVStream[channel].SetMute(false);
                            this.mAudioTrack.SwitchChannels(channel);
                        } else {
                            this.mAVStream[channel].SetMute(true);
                        }
                        this.mAVStream[channel].StartPlay();
                        this.mAVStream[channel].SetVideoInterface(this);
                        this.mAVStream[channel].SetAudioInterface(this.mAudioTrack);
                        this.mViewInfoArray[channel].getNet().SetAVStream(deviceChannel, this.mAVStream[channel]);
                        this.mViewInfoArray[channel].getNet().StartRealAv(deviceChannel, MyApp.multiStreamType);
                    } else {
                        if (errorCode == 64) {
                            this.mtvVideoInformation.post(new Runnable() {
                                public void run() {
                                    RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.mContext.getString(R.string.permissiondenied));
                                }
                            });
                        } else if (errorCode == 63) {
                            this.mtvVideoInformation.post(new Runnable() {
                                public void run() {
                                    RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.mContext.getString(R.string.macillegal));
                                }
                            });
                        } else if (errorCode == 24) {
                            this.mtvVideoInformation.post(new Runnable() {
                                public void run() {
                                    RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.mContext.getString(R.string.moreuseronline));
                                }
                            });
                        } else if (errorCode == 5) {
                            this.mtvVideoInformation.post(new Runnable() {
                                public void run() {
                                    RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.mContext.getString(R.string.usernameorpassworderror));
                                }
                            });
                        }
                        this.mViewInfoArray[channel].reset();
                    }
                } else {
                    SingleViewInfo[] singleViewInfoArr3 = this.mViewInfoArray;
                    if (singleViewInfoArr3[channel] == null) {
                        singleViewInfoArr3[channel] = new SingleViewInfo();
                    }
                    this.mViewInfoArray[channel].setNet(dvrNet);
                    this.mAudioTrack.mPlayer.play();
                    this.mAudioTrack.SetMute(this.mbMute);
                    AVStream[] aVStreamArr3 = this.mAVStream;
                    if (aVStreamArr3[channel] == null) {
                        aVStreamArr3[channel] = new AVStream();
                    }
                    this.mAVStream[channel].GetHandle(channel);
                    this.mViewInfoArray[channel].setDeviceInfo(devInfoBean);
                    this.mViewInfoArray[channel].mChannel = deviceChannel;
                    if (channel == this.mVideoContainer.getFocusChannel()) {
                        this.mAudioTrack.SwitchChannels(channel);
                        this.mAVStream[channel].SetMute(true);
                    } else {
                        this.mAVStream[channel].SetMute(false);
                    }
                    this.mVideoContainer.SetPlayState(channel, true);
                    this.mAVStream[channel].SetVideoInterface(this);
                    this.mAVStream[channel].SetAudioInterface(this.mAudioTrack);
                    this.mAVStream[channel].StartPlay();
                    this.mViewInfoArray[channel].getNet().SetAVStream(deviceChannel, this.mAVStream[channel]);
                    this.mViewInfoArray[channel].getNet().StartRealAv(deviceChannel, MyApp.multiStreamType);
                }
                this.mVideoContainer.post(new Runnable() {
                    public void run() {
                        RealPlayActivity.this.mVideoContainer.SetBusyState(targetChannel, false);
                    }
                });
            }
        }
    }

    public void StopPlay(final int viewIndex) {
        synchronized (lock) {
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (singleViewInfoArr[viewIndex] != null) {
                if (singleViewInfoArr[viewIndex].getNet() != null) {
                    this.mViewInfoArray[viewIndex].getNet().StopRealAv(this.mViewInfoArray[viewIndex].mChannel);
                    boolean lastViewUsingDevice = true;
                    for (int otherViewIndex = 0; otherViewIndex < this.mnMaxView; otherViewIndex++) {
                        if (viewIndex != otherViewIndex) {
                            SingleViewInfo[] singleViewInfoArr2 = this.mViewInfoArray;
                            if (singleViewInfoArr2[otherViewIndex] != null) {
                                if (singleViewInfoArr2[otherViewIndex].getNet() != null) {
                                    if (this.mViewInfoArray[otherViewIndex].getNet() == this.mViewInfoArray[viewIndex].getNet()) {
                                        lastViewUsingDevice = false;
                                    }
                                }
                            }
                        }
                    }
                    if (lastViewUsingDevice) {
                        this.mViewInfoArray[viewIndex].getNet().CloseDeviceHandle();
                    }
                    StopRecord(viewIndex);
                    AVStream[] aVStreamArr = this.mAVStream;
                    if (aVStreamArr[viewIndex] != null) {
                        aVStreamArr[viewIndex].StopPlay();
                        this.mAVStream[viewIndex].CloseHandle();
                        this.mAVStream[viewIndex] = null;
                    }
                    if (this.mCurFocus) {
                        this.mVideoContainer.post(new Runnable() {
                            public void run() {
                                RealPlayActivity.this.mVideoContainer.SetPlayState(RealPlayActivity.this.mVideoContainer.getFocusChannel(), false);
                                RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.mContext.getString(R.string.closevideo));
                                RealPlayActivity.this.mbRecordArray[viewIndex] = false;
                                RealPlayActivity.this.mImageRecord.setImageResource(R.drawable.record_unuse);
                                RealPlayActivity.this.mVideoContainer.getVideoView(viewIndex).setVisibility(4);
                            }
                        });
                    }
                    this.mCurFocus = true;
                    this.mViewInfoArray[viewIndex].reset();
                }
            }
        }
    }

    public void openSound(int activeViewIndex) {
        for (int viewIndex = 0; viewIndex < this.mnMaxView; viewIndex++) {
            if (this.mAVStream[viewIndex] != null) {
                SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
                if (!(singleViewInfoArr[viewIndex] == null || singleViewInfoArr[viewIndex].getNet() == null)) {
                    if (viewIndex == activeViewIndex) {
                        this.mViewInfoArray[activeViewIndex].getNet().SetStreamSound(this.mViewInfoArray[activeViewIndex].mChannel, this.mApp.mStreamType, true);
                        this.mAVStream[activeViewIndex].SetMute(false);
                        this.mAudioTrack.SwitchChannels(activeViewIndex);
                    } else {
                        this.mAVStream[viewIndex].SetMute(true);
                        this.mViewInfoArray[viewIndex].getNet().SetStreamSound(this.mViewInfoArray[viewIndex].mChannel, this.mApp.mStreamType, false);
                    }
                }
            }
        }
    }

    public String getLocalMacAddress() {
        String defaultMacAddress = new String("00-00-00-00-00-00");
        WifiManager wifiManager = (WifiManager) getSystemService(Configs.Key.WifiStatus);
        if (wifiManager == null) {
            return defaultMacAddress;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        String macAddress = connectionInfo.getMacAddress();
        if (macAddress == null) {
            return defaultMacAddress;
        }
        String replace = macAddress.replace(":", "-");
        return replace.length() > 0 ? replace : defaultMacAddress;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            this.mSelectId = data.getIntExtra("id", -1);
            this.mSelectChannel = data.getIntExtra("channel", 0);
            this.mSelectMaps.put(Integer.valueOf(this.mSelectId), Integer.valueOf(data.getIntExtra("currentPosition", -1)));
            if (MyApp.wifiStatus) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && "WIFI".equalsIgnoreCase(activeNetworkInfo.getTypeName())) {
                    new Thread(new Runnable() {
                        public void run() {
                            RealPlayActivity realPlayActivity = RealPlayActivity.this;
                            realPlayActivity.StartPlay(realPlayActivity.mSelectId, RealPlayActivity.this.mSelectChannel, -1);
                        }
                    }).start();
                }
            } else {
                new Thread(new Runnable() {
                    public void run() {
                        RealPlayActivity realPlayActivity = RealPlayActivity.this;
                        realPlayActivity.StartPlay(realPlayActivity.mSelectId, RealPlayActivity.this.mSelectChannel, -1);
                    }
                }).start();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void fuc(int channel, int streamType, byte[] frameData, int dataLength, int width, int height) {
        VideoView videoView = this.mVideoContainer.getVideoView(channel);
        if (videoView != null) {
            videoView.writeIn(frameData, width, height);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.mbState = false;
        this.mAudioTrack.SetMute(true);
        for (int viewIndex = 0; viewIndex < this.mnMaxView; viewIndex++) {
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (!(singleViewInfoArr[viewIndex] == null || singleViewInfoArr[viewIndex].mDevInfo == null || this.mViewInfoArray[viewIndex].getNet() == null)) {
                this.mViewInfoArray[viewIndex].getNet().RealPlayControl(this.mViewInfoArray[viewIndex].mChannel, 0, 2);
            }
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.mbState = true;
        AuTrack auTrack = this.mAudioTrack;
        if (auTrack != null) {
            auTrack.SetMute(this.mbMute);
        }
        for (int viewIndex = 0; viewIndex < this.mnMaxView; viewIndex++) {
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (!(singleViewInfoArr[viewIndex] == null || singleViewInfoArr[viewIndex].mDevInfo == null || this.mViewInfoArray[viewIndex].getNet() == null)) {
                this.mViewInfoArray[viewIndex].getNet().RealPlayControl(this.mViewInfoArray[viewIndex].mChannel, 0, 1);
            }
        }
        super.onResume();
    }

    public void onEventMainThread(DevInfoEvent devInfoEvent) {
        this.mDevInfoBean = devInfoEvent;
        if (MyApp.wifiStatus) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return;
            }
            if ("WIFI".equalsIgnoreCase(activeNetworkInfo.getTypeName())) {
                new Thread(new Runnable() {
                    public void run() {
                        RealPlayActivity.this.stopPlayVideo();
                        RealPlayActivity realPlayActivity = RealPlayActivity.this;
                        realPlayActivity.startPlayVideo(realPlayActivity.mDevInfoBean);
                    }
                }).start();
                return;
            }
            this.mVideoContainer.SetInitMode(4);
            ToastSf.toastSf((Activity) this, (int) R.string.wifinotconnect);
            return;
        }
        new Thread(new Runnable() {
            public void run() {
                RealPlayActivity.this.stopPlayVideo();
                Log.e("onEventMainThread", "startPlayVideo");
                RealPlayActivity realPlayActivity = RealPlayActivity.this;
                realPlayActivity.startPlayVideo(realPlayActivity.mDevInfoBean);
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void stopPlayVideo() {
        this.mHandler.post(new Runnable() {
            public void run() {
                RealPlayActivity.this.mVideoContainer.setVisibility(8);
                RealPlayActivity.this.mStopPbLoading.setVisibility(0);
            }
        });
        this.mInitMode = this.mVideoContainer.getInitMode();
        for (int viewIndex = 0; viewIndex < this.mInitMode; viewIndex++) {
            StopPlay(viewIndex);
        }
        this.mAudioTrack.mPlayer.Stop();
        this.mHandler.post(new Runnable() {
            public void run() {
                RealPlayActivity.this.mVideoContainer.ClearViews();
                RealPlayActivity.this.mVideoContainer.setVisibility(0);
                RealPlayActivity.this.mStopPbLoading.setVisibility(8);
                RealPlayActivity.this.mImageRecord.setImageResource(R.drawable.record_unuse);
                RealPlayActivity.this.mImagePlay.setClickable(true);
            }
        });
    }

    public void startPlayVideo(DevInfoEvent devInfoEvent) {
        this.mK = -1;
        this.mChCount = 0;
        this.mGroupName = devInfoEvent.mGroupName;
        if (MyApp.loginType == 0) {
            this.mDao = new GroupDaoForNormal();
        } else {
            this.mDao = new GroupDaoForServer();
        }
        this.mBeanDatas = this.mDao.getGroupDatasByName(this.mGroupName);
        for (int beanIndex = 0; beanIndex < this.mBeanDatas.size(); beanIndex++) {
            if (this.mBeanDatas.get(beanIndex).dbFlag == 1) {
                this.mChCount++;
            }
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                if (RealPlayActivity.this.mChCount <= 1) {
                    RealPlayActivity.this.mVideoContainer.SetInitMode(1);
                    RealPlayActivity.this.setSurfaceListener(1);
                } else if (RealPlayActivity.this.mChCount <= 4) {
                    RealPlayActivity.this.mVideoContainer.SetInitMode(4);
                    RealPlayActivity.this.setSurfaceListener(4);
                } else if (RealPlayActivity.this.mChCount <= 9) {
                    RealPlayActivity.this.mVideoContainer.SetInitMode(9);
                    RealPlayActivity.this.setSurfaceListener(9);
                } else if (RealPlayActivity.this.mChCount <= 16) {
                    RealPlayActivity.this.mVideoContainer.SetInitMode(16);
                    RealPlayActivity.this.setSurfaceListener(16);
                } else if (RealPlayActivity.this.mChCount <= 32) {
                    RealPlayActivity.this.mVideoContainer.SetInitMode(32);
                    RealPlayActivity.this.setSurfaceListener(32);
                }
                Log.e("mChCount", "" + RealPlayActivity.this.mChCount);
                for (int viewIndex = 0; viewIndex < RealPlayActivity.this.mChCount; viewIndex++) {
                    RealPlayActivity.this.mVideoContainer.getVideoView(viewIndex).setVisibility(0);
                }
            }
        });
        for (int beanIndex = 0; beanIndex < this.mBeanDatas.size(); beanIndex++) {
            GroupBean groupBean = this.mBeanDatas.get(beanIndex);
            if (groupBean.dbFlag == 1) {
                int nextViewIndex = this.mK + 1;
                this.mK = nextViewIndex;
                if (nextViewIndex < 32) {
                    StartPlay(groupBean.dbId, groupBean.dbChannel, this.mK);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        DbHelper dbhelper = this.mdbHelper;
        if (dbhelper != null) {
            dbhelper.close();
        }
        for (int viewIndex = 0; viewIndex < this.mnMaxView; viewIndex++) {
            if (this.mViewInfoArray[viewIndex] == null) {
                getSharedPreferences(String.format("realplay%02d", new Object[]{Integer.valueOf(viewIndex)}), 0).edit().clear().commit();
            } else {
                if (this.mApp.mbAutoPlay) {
                    try {
                        this.mViewInfoArray[viewIndex].mDevInfo.PrintOut();
                        SharedPreferences sharedPreferences = getSharedPreferences(String.format("realplay%02d", new Object[]{Integer.valueOf(viewIndex)}), 0);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        new ObjectOutputStream(byteArrayOutputStream).writeObject(this.mViewInfoArray[viewIndex].mDevInfo);
                        String encodedDeviceInfo = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
                        sharedPreferences.edit().putInt("channel", this.mViewInfoArray[viewIndex].mChannel).commit();
                        sharedPreferences.edit().putString("info", encodedDeviceInfo).commit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                StopPlay(viewIndex);
            }
        }
        this.mAudioTrack.mPlayer.Stop();
        this.mOEventListener.disable();
        super.onDestroy();
    }

    public void SwitchPlay() {
        for (int viewIndex = 0; viewIndex < this.mVideoContainer.getInitViewCount(); viewIndex++) {
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (!(singleViewInfoArr[viewIndex] == null || singleViewInfoArr[viewIndex].getNet() == null || this.mViewInfoArray[viewIndex].mDevInfo == null || this.mViewInfoArray[viewIndex].getNet().mNetType == 2 || this.mVideoContainer.getVideoFrameVisibility(viewIndex) != 0)) {
                List<String> list = this.mViewIndexDatas;
                if (!list.contains("" + viewIndex)) {
                    List<String> list2 = this.mViewIndexDatas;
                    list2.add("" + viewIndex);
                }
            }
        }
        this.mbSwitch = true;
        Log.e("SwitchPlay", "" + this.mbSwitch);
        Log.e("mViewIndexDatas", "" + this.mViewIndexDatas);
    }

    public class SwitchChannelRunnable implements Runnable {
        public SwitchChannelRunnable() {
        }

        public void run() {
            throw new UnsupportedOperationException("Method not decompiled: com.streamax.client.RealPlayActivity.SwitchChannelRunnable.run():void");
        }
    }

    public void onEventMainThread(RecordStatus recordStatus) {
        ImageView imageView = this.mImageRecord;
        if (imageView != null) {
            imageView.setImageResource(recordStatus.mRecordStatus ? R.drawable.record_normal : R.drawable.record_unuse);
        }
    }

    public void onDismiss() {
        this.mImageCapture.setClickable(true);
    }

    public void onEventMainThread(DeleteDeviceEvent deleteDeviceEvent) {
        synchronized (lock) {
            if (deleteDeviceEvent.mDeviceId != 0) {
                for (Integer deviceIdKey : this.mSelectMaps.keySet()) {
                    int deviceId = deviceIdKey.intValue();
                    if (deviceId == deleteDeviceEvent.mDeviceId) {
                        final int selectedViewIndex = this.mSelectMaps.get(Integer.valueOf(deviceId)).intValue();
                        new Thread(new Runnable() {
                            public void run() {
                                RealPlayActivity.this.mCurFocus = false;
                                RealPlayActivity.this.StopPlay(selectedViewIndex);
                                RealPlayActivity.this.mHandler.post(new Runnable() {
                                    public void run() {
                                        if (RealPlayActivity.this.mVideoContainer != null) {
                                            for (int viewIndex = 0; viewIndex < RealPlayActivity.this.mVideoContainer.GetChannelList().size(); viewIndex++) {
                                                if (viewIndex == selectedViewIndex) {
                                                    RealPlayActivity.this.mVideoContainer.SetPlayState(viewIndex, false);
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                }
            }
        }
    }

    public void setSurfaceListener(int viewCount) {
        for (int viewIndex = 0; viewIndex < viewCount; viewIndex++) {
            this.mVideoContainer.getVideoView(viewIndex).setCallBack(this, viewIndex);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height, int channel) {
        Log.e("surfaceChanged", "" + channel);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder, int viewIndex) {
        Log.e("surfaceCreated", "" + viewIndex);
        if (this.mVideoContainer.getVideoFrameVisibility(viewIndex) == 0) {
            List<String> list = this.mViewIndexDatas;
            if (!list.contains("" + viewIndex)) {
                List<String> list2 = this.mViewIndexDatas;
                list2.add("" + viewIndex);
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder, int viewIndex) {
        Log.e("surfaceDestroyed", "" + viewIndex);
    }

    public void setSurfaceVisibility() {
        int initViewCount = this.mVideoContainer.getInitViewCount();
        int curViewCount = this.mVideoContainer.getCurViewCount();
        int firstIndex = this.mVideoContainer.getFirstIndex();
        if (curViewCount == 4) {
            if (firstIndex == 0) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 0 || viewIndex > 3) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 4) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 4 || viewIndex > 7) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 8) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 8 || viewIndex > 11) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 12) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 12 || viewIndex > 15) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 16) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 16 || viewIndex > 19) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 20) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 20 || viewIndex > 23) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 24) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 24 || viewIndex > 27) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 28) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 28 || viewIndex > 31) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            }
        } else if (curViewCount == 9) {
            if (firstIndex == 0) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 0 || viewIndex > 8) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 7) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 7 || viewIndex > 15) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 9) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 9 || viewIndex > 17) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 18) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 18 || viewIndex > 26) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 27) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if ((viewIndex < 27 || viewIndex > 31) && (viewIndex < 0 || viewIndex > 3)) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            }
        } else if (curViewCount != 16) {
        } else {
            if (firstIndex == 0) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 0 || viewIndex > 15) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            } else if (firstIndex == 16) {
                for (int viewIndex = 0; viewIndex < initViewCount; viewIndex++) {
                    if (viewIndex < 16 || viewIndex > 31) {
                        isVisible(viewIndex, false);
                    } else {
                        isVisible(viewIndex, true);
                    }
                }
            }
        }
    }

    public void isVisible(final int viewIndex, boolean visible) {
        if (visible) {
            this.mVideoContainer.post(new Runnable() {
                public void run() {
                    RealPlayActivity.this.mVideoContainer.getVideoView(viewIndex).setVisibility(0);
                }
            });
        } else {
            this.mVideoContainer.post(new Runnable() {
                public void run() {
                    RealPlayActivity.this.mVideoContainer.getVideoView(viewIndex).setVisibility(8);
                }
            });
        }
    }
}
