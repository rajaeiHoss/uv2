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

    public void setPtz(int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
        this.mContext = AppProxy.getContext();
        this.mApp = (MyApp) getApplication();
        LayoutInflater from = LayoutInflater.from(this);
        this.mInflater = from;
        View inflate = from.inflate(R.layout.realplaypage, (ViewGroup) null);
        this.mRealPlay = inflate;
        this.mStopPbLoading = (ProgressBar) inflate.findViewById(R.id.realplay_pbloading);
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
            public void onOrientationChanged(int i) {
                try {
                    int i2 = Settings.System.getInt(RealPlayActivity.this.getApplication().getContentResolver(), "accelerometer_rotation");
                    if (i != -1) {
                        if (i != 360) {
                            if (i != 0) {
                                if (i == 90) {
                                    if (i2 != 0) {
                                        RealPlayActivity.this.setVideoGroupMax(true);
                                        return;
                                    }
                                    return;
                                } else if (i != 180) {
                                    if (i == 270 && i2 != 0) {
                                        RealPlayActivity.this.setVideoGroupMax(true);
                                        return;
                                    }
                                    return;
                                } else {
                                    return;
                                }
                            }
                        }
                        if (i2 != 0) {
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
                String str;
                String str2;
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
                                str = RealPlayActivity.this.mContext.getString(R.string.startrecording);
                                RealPlayActivity.this.mImageRecord.setImageResource(R.drawable.record_normal);
                                RealPlayActivity realPlayActivity2 = RealPlayActivity.this;
                                realPlayActivity2.StartRecord(realPlayActivity2.mVideoContainer.getFocusChannel());
                            } else {
                                str = RealPlayActivity.this.mContext.getString(R.string.stoprecording);
                                RealPlayActivity.this.mImageRecord.setImageResource(R.drawable.record_unuse);
                                RealPlayActivity realPlayActivity3 = RealPlayActivity.this;
                                realPlayActivity3.StopRecord(realPlayActivity3.mVideoContainer.getFocusChannel());
                            }
                            RealPlayActivity.this.mtvVideoInformation.setText(str);
                            return;
                        }
                        return;
                    case R.id.realplay_toolbar_sound /*2131362952*/:
                        ImageView imageView = (ImageView) RealPlayActivity.this.mToolbar.findViewById(R.id.realplay_toolbar_sound);
                        if (RealPlayActivity.this.mbMute) {
                            RealPlayActivity.this.mbMute = false;
                            imageView.setImageResource(R.drawable.soundopen);
                            str2 = RealPlayActivity.this.mContext.getString(R.string.opensound);
                        } else {
                            RealPlayActivity.this.mbMute = true;
                            imageView.setImageResource(R.drawable.soundclose);
                            str2 = RealPlayActivity.this.mContext.getString(R.string.closesound);
                        }
                        RealPlayActivity.this.mAudioTrack.SetMute(RealPlayActivity.this.mbMute);
                        RealPlayActivity.this.mtvVideoInformation.setText(str2);
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
            for (int i = 0; i < this.mnMaxView; i++) {
                SharedPreferences sharedPreferences = getSharedPreferences(String.format("realplay%02d", new Object[]{Integer.valueOf(i)}), 0);
                String string = sharedPreferences.getString("info", "");
                int i2 = sharedPreferences.getInt("channel", 0);
                int i3 = SpUtils.getInt(Configs.Key.LoginType, -1);
                if (!(string == null || string.length() == 0 || i3 != MyApp.loginType)) {
                    try {
                        try {
                            DevInfoBean devInfoBean2 = (DevInfoBean) new ObjectInputStream(new ByteArrayInputStream(Base64.decode(string, 0))).readObject();
                            devInfoBean2.PrintOut();
                            if (MyApp.loginType == 0) {
                                devInfoBean = this.mdbHelper.query(devInfoBean2.mDevName);
                            } else {
                                if (this.mApp.mWebService.mDevList.size() == 0) {
                                    this.mApp.mWebService.GetTerminalInfoAndroid(true);
                                }
                                devInfoBean = this.mApp.mWebService.query(devInfoBean2.mDevName);
                            }
                            if (devInfoBean2 != null) {
                                if (devInfoBean != null) {
                                    if (devInfoBean2.mDevIp.compareTo(devInfoBean.mDevIp) == 0 && devInfoBean2.mMediaPort == devInfoBean.mMediaPort) {
                                        StartPlay(devInfoBean.mDevId, i2, i);
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

    public void StartRecord(int i) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            this.mtvVideoInformation.setText(this.mContext.getString(R.string.ExternalStorageerror));
            return;
        }
        Calendar instance = Calendar.getInstance();
        String format = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13))});
        if (this.mAVStream[i] != null) {
            String format2 = String.format("%02d.264", new Object[]{Integer.valueOf(this.mViewInfoArray[i].mChannel + 1)});
            this.mRecordPathMap.put(Integer.valueOf(i), format + format2);
            this.mAVStream[i].StartRecord(Configs.Key.RecordDirPath + format + format2);
            this.mViewInfoArray[i].getNet().RequestIFrame(this.mViewInfoArray[i].mChannel, 0);
            this.mVideoContainer.SetRecState(i, true);
        }
    }

    public void StopRecord(final int i) {
        AVStream[] aVStreamArr = this.mAVStream;
        if (aVStreamArr[i] != null) {
            aVStreamArr[i].StopRecord();
            this.mVideoContainer.post(new Runnable() {
                public void run() {
                    RealPlayActivity.this.mVideoContainer.SetRecState(i, false);
                }
            });
            this.mbRecordArray[i] = false;
        }
    }

    public Stack<Map<String, Object>> CaptureImage(int i) {
        Calendar instance = Calendar.getInstance();
        String format = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13))});
        if (this.mAVStream[i] != null) {
            HashMap hashMap = new HashMap();
            String str = Configs.Key.CaptureDirPath + format + String.format("%02d.bmp", new Object[]{Integer.valueOf(this.mViewInfoArray[i].mChannel + 1)});
            hashMap.put("channel", Integer.valueOf(i));
            hashMap.put("path", str);
            if (this.mAVStream[i].Capture(str) == 0) {
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

    public void setVideoGroupMax(boolean z) {
        if (z) {
            this.mTitlebar.setVisibility(8);
            this.mVideoInfo.setVisibility(8);
            this.mToolbar.setVisibility(8);
            return;
        }
        this.mTitlebar.setVisibility(0);
        this.mVideoInfo.setVisibility(0);
        this.mToolbar.setVisibility(0);
    }

    public void SetPtzState(boolean z) {
        ImageView imageView = (ImageView) findViewById(R.id.realplay_toolbar_ptz);
        if (z) {
            imageView.setImageResource(R.drawable.ptz_active);
        } else {
            imageView.setImageResource(R.drawable.ptz_normal);
        }
    }

    public void SetStreamDecodeState(int i, boolean z) {
        AVStream[] aVStreamArr = this.mAVStream;
        if (aVStreamArr[i] != null) {
            aVStreamArr[i].SetStreamDecodeState(z);
        }
    }

    public void StartPlay(int i, int i2, final int i3) {
        DevInfoBean devInfoBean;
        synchronized (lock) {
            if (MyApp.loginType == 0) {
                devInfoBean = this.mdbHelper.query(i);
            } else {
                devInfoBean = this.mApp.mWebService.query(i);
            }
            if (devInfoBean != null) {
                int i4 = -1;
                int channel = i3;
                if (channel == -1) {
                    channel = this.mVideoContainer.getFocusChannel();
                }
                for (int i5 = 0; i5 < this.mnMaxView; i5++) {
                    SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
                    if (singleViewInfoArr[i5] != null) {
                        if (singleViewInfoArr[i5].check(devInfoBean) && i2 == this.mViewInfoArray[i5].mChannel) {
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
                for (int i6 = 0; i6 < this.mnMaxView; i6++) {
                    SingleViewInfo[] singleViewInfoArr2 = this.mViewInfoArray;
                    if (singleViewInfoArr2[i6] != null) {
                        if (singleViewInfoArr2[i6].check(devInfoBean)) {
                            dvrNet = this.mViewInfoArray[i6].getNet();
                        }
                    }
                }
                if (dvrNet == null) {
                    this.mViewInfoArray[channel] = new SingleViewInfo();
                    this.mViewInfoArray[channel].setNet(new DvrNet());
                    Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(this.mViewInfoArray[channel].getNet(), devInfoBean, this.mApp);
                    if (connDeviceProxy != null) {
                        i4 = ((Integer) connDeviceProxy.get("errorcode")).intValue();
                    }
                    if (i4 == 0) {
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
                        this.mViewInfoArray[channel].mChannel = i2;
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
                        this.mViewInfoArray[channel].getNet().SetAVStream(i2, this.mAVStream[channel]);
                        this.mViewInfoArray[channel].getNet().StartRealAv(i2, MyApp.multiStreamType);
                    } else {
                        if (i4 == 64) {
                            this.mtvVideoInformation.post(new Runnable() {
                                public void run() {
                                    RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.mContext.getString(R.string.permissiondenied));
                                }
                            });
                        } else if (i4 == 63) {
                            this.mtvVideoInformation.post(new Runnable() {
                                public void run() {
                                    RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.mContext.getString(R.string.macillegal));
                                }
                            });
                        } else if (i4 == 24) {
                            this.mtvVideoInformation.post(new Runnable() {
                                public void run() {
                                    RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.mContext.getString(R.string.moreuseronline));
                                }
                            });
                        } else if (i4 == 5) {
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
                    this.mViewInfoArray[channel].mChannel = i2;
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
                    this.mViewInfoArray[channel].getNet().SetAVStream(i2, this.mAVStream[channel]);
                    this.mViewInfoArray[channel].getNet().StartRealAv(i2, MyApp.multiStreamType);
                }
                this.mVideoContainer.post(new Runnable() {
                    public void run() {
                        RealPlayActivity.this.mVideoContainer.SetBusyState(targetChannel, false);
                    }
                });
            }
        }
    }

    public void StopPlay(final int i) {
        synchronized (lock) {
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (singleViewInfoArr[i] != null) {
                if (singleViewInfoArr[i].getNet() != null) {
                    this.mViewInfoArray[i].getNet().StopRealAv(this.mViewInfoArray[i].mChannel);
                    boolean z = true;
                    for (int i2 = 0; i2 < this.mnMaxView; i2++) {
                        if (i != i2) {
                            SingleViewInfo[] singleViewInfoArr2 = this.mViewInfoArray;
                            if (singleViewInfoArr2[i2] != null) {
                                if (singleViewInfoArr2[i2].getNet() != null) {
                                    if (this.mViewInfoArray[i2].getNet() == this.mViewInfoArray[i].getNet()) {
                                        z = false;
                                    }
                                }
                            }
                        }
                    }
                    if (z) {
                        this.mViewInfoArray[i].getNet().CloseDeviceHandle();
                    }
                    StopRecord(i);
                    AVStream[] aVStreamArr = this.mAVStream;
                    if (aVStreamArr[i] != null) {
                        aVStreamArr[i].StopPlay();
                        this.mAVStream[i].CloseHandle();
                        this.mAVStream[i] = null;
                    }
                    if (this.mCurFocus) {
                        this.mVideoContainer.post(new Runnable() {
                            public void run() {
                                RealPlayActivity.this.mVideoContainer.SetPlayState(RealPlayActivity.this.mVideoContainer.getFocusChannel(), false);
                                RealPlayActivity.this.mtvVideoInformation.setText(RealPlayActivity.this.mContext.getString(R.string.closevideo));
                                RealPlayActivity.this.mbRecordArray[i] = false;
                                RealPlayActivity.this.mImageRecord.setImageResource(R.drawable.record_unuse);
                                RealPlayActivity.this.mVideoContainer.getVideoView(i).setVisibility(4);
                            }
                        });
                    }
                    this.mCurFocus = true;
                    this.mViewInfoArray[i].reset();
                }
            }
        }
    }

    public void openSound(int i) {
        for (int i2 = 0; i2 < this.mnMaxView; i2++) {
            if (this.mAVStream[i2] != null) {
                SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
                if (!(singleViewInfoArr[i2] == null || singleViewInfoArr[i2].getNet() == null)) {
                    if (i2 == i) {
                        this.mViewInfoArray[i].getNet().SetStreamSound(this.mViewInfoArray[i].mChannel, this.mApp.mStreamType, true);
                        this.mAVStream[i].SetMute(false);
                        this.mAudioTrack.SwitchChannels(i);
                    } else {
                        this.mAVStream[i2].SetMute(true);
                        this.mViewInfoArray[i2].getNet().SetStreamSound(this.mViewInfoArray[i2].mChannel, this.mApp.mStreamType, false);
                    }
                }
            }
        }
    }

    public String getLocalMacAddress() {
        String str = new String("00-00-00-00-00-00");
        WifiManager wifiManager = (WifiManager) getSystemService(Configs.Key.WifiStatus);
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

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            this.mSelectId = intent.getIntExtra("id", -1);
            this.mSelectChannel = intent.getIntExtra("channel", 0);
            this.mSelectMaps.put(Integer.valueOf(this.mSelectId), Integer.valueOf(intent.getIntExtra("currentPosition", -1)));
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
        super.onActivityResult(i, i2, intent);
    }

    public void fuc(int i, int i2, byte[] bArr, int i3, int i4, int i5) {
        VideoView videoView = this.mVideoContainer.getVideoView(i);
        if (videoView != null) {
            videoView.writeIn(bArr, i4, i5);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.mbState = false;
        this.mAudioTrack.SetMute(true);
        for (int i = 0; i < this.mnMaxView; i++) {
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (!(singleViewInfoArr[i] == null || singleViewInfoArr[i].mDevInfo == null || this.mViewInfoArray[i].getNet() == null)) {
                this.mViewInfoArray[i].getNet().RealPlayControl(this.mViewInfoArray[i].mChannel, 0, 2);
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
        for (int i = 0; i < this.mnMaxView; i++) {
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (!(singleViewInfoArr[i] == null || singleViewInfoArr[i].mDevInfo == null || this.mViewInfoArray[i].getNet() == null)) {
                this.mViewInfoArray[i].getNet().RealPlayControl(this.mViewInfoArray[i].mChannel, 0, 1);
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
        for (int i = 0; i < this.mInitMode; i++) {
            StopPlay(i);
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
        for (int i = 0; i < this.mBeanDatas.size(); i++) {
            if (this.mBeanDatas.get(i).dbFlag == 1) {
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
                for (int i = 0; i < RealPlayActivity.this.mChCount; i++) {
                    RealPlayActivity.this.mVideoContainer.getVideoView(i).setVisibility(0);
                }
            }
        });
        for (int i2 = 0; i2 < this.mBeanDatas.size(); i2++) {
            GroupBean groupBean = this.mBeanDatas.get(i2);
            if (groupBean.dbFlag == 1) {
                int i3 = this.mK + 1;
                this.mK = i3;
                if (i3 < 32) {
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
        for (int i = 0; i < this.mnMaxView; i++) {
            if (this.mViewInfoArray[i] == null) {
                getSharedPreferences(String.format("realplay%02d", new Object[]{Integer.valueOf(i)}), 0).edit().clear().commit();
            } else {
                if (this.mApp.mbAutoPlay) {
                    try {
                        this.mViewInfoArray[i].mDevInfo.PrintOut();
                        SharedPreferences sharedPreferences = getSharedPreferences(String.format("realplay%02d", new Object[]{Integer.valueOf(i)}), 0);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        new ObjectOutputStream(byteArrayOutputStream).writeObject(this.mViewInfoArray[i].mDevInfo);
                        String str = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
                        sharedPreferences.edit().putInt("channel", this.mViewInfoArray[i].mChannel).commit();
                        sharedPreferences.edit().putString("info", str).commit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                StopPlay(i);
            }
        }
        this.mAudioTrack.mPlayer.Stop();
        this.mOEventListener.disable();
        super.onDestroy();
    }

    public void SwitchPlay() {
        for (int i = 0; i < this.mVideoContainer.getInitViewCount(); i++) {
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (!(singleViewInfoArr[i] == null || singleViewInfoArr[i].getNet() == null || this.mViewInfoArray[i].mDevInfo == null || this.mViewInfoArray[i].getNet().mNetType == 2 || this.mVideoContainer.getVideoFrameVisibility(i) != 0)) {
                List<String> list = this.mViewIndexDatas;
                if (!list.contains("" + i)) {
                    List<String> list2 = this.mViewIndexDatas;
                    list2.add("" + i);
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

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
            if (r0 == 3) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x008a, code lost:
            if (r10.this$0.mViewInfoArray[r10.this$0.mVideoContainer.getFocusChannel()].getNet().nDevClass == 2) goto L_0x004e;
         */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0099  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r10 = this;
            L_0x0000:
                com.streamax.client.RealPlayActivity r0 = com.streamax.client.RealPlayActivity.this
                boolean r0 = r0.mbSwitch
                if (r0 != 0) goto L_0x0013
                r0 = 500(0x1f4, double:2.47E-321)
                java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x000e }
                goto L_0x0000
            L_0x000e:
                r0 = move-exception
                r0.printStackTrace()
                goto L_0x0000
            L_0x0013:
                com.streamax.client.RealPlayActivity r0 = com.streamax.client.RealPlayActivity.this
                java.util.List r0 = r0.mViewIndexDatas
                int r0 = r0.size()
                if (r0 != 0) goto L_0x0020
                goto L_0x0000
            L_0x0020:
                com.streamax.client.RealPlayActivity r0 = com.streamax.client.RealPlayActivity.this
                r1 = 0
                boolean unused = r0.mbSwitch = r1
                com.streamax.client.RealPlayActivity r0 = com.streamax.client.RealPlayActivity.this
                android.os.Handler r0 = r0.mHandler
                com.streamax.client.RealPlayActivity$SwitchChannelRunnable$1 r2 = new com.streamax.client.RealPlayActivity$SwitchChannelRunnable$1
                r2.<init>()
                r0.post(r2)
                com.streamax.client.RealPlayActivity r0 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r0 = r0.mVideoContainer
                int r0 = r0.getCurViewCount()
                r2 = 3
                r3 = 2
                r4 = 1
                if (r0 != r4) goto L_0x008d
                int r0 = com.streamax.client.MyApp.smartStreamStatus
                if (r0 != r4) goto L_0x008d
                int r0 = com.streamax.client.MyApp.singleStreamType
                if (r0 == 0) goto L_0x0052
                if (r0 == r4) goto L_0x0050
                if (r0 == r3) goto L_0x008d
                if (r0 == r2) goto L_0x004e
                goto L_0x008d
            L_0x004e:
                r0 = 2
                goto L_0x008e
            L_0x0050:
                r0 = 1
                goto L_0x008e
            L_0x0052:
                com.streamax.client.RealPlayActivity r0 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r0 = r0.mViewInfoArray
                com.streamax.client.RealPlayActivity r5 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r5 = r5.mVideoContainer
                int r5 = r5.getFocusChannel()
                r0 = r0[r5]
                if (r0 == 0) goto L_0x0050
                com.streamax.client.RealPlayActivity r0 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r0 = r0.mViewInfoArray
                com.streamax.client.RealPlayActivity r5 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r5 = r5.mVideoContainer
                int r5 = r5.getFocusChannel()
                r0 = r0[r5]
                com.dvr.net.DvrNet r0 = r0.getNet()
                if (r0 == 0) goto L_0x0050
                com.streamax.client.RealPlayActivity r0 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r0 = r0.mViewInfoArray
                com.streamax.client.RealPlayActivity r5 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r5 = r5.mVideoContainer
                int r5 = r5.getFocusChannel()
                r0 = r0[r5]
                com.dvr.net.DvrNet r0 = r0.getNet()
                int r0 = r0.nDevClass
                if (r0 != r3) goto L_0x0050
                goto L_0x004e
            L_0x008d:
                r0 = 0
            L_0x008e:
                r5 = 0
            L_0x008f:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r6 = r6.mVideoContainer
                int r6 = r6.getInitViewCount()
                if (r5 >= r6) goto L_0x0393
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                java.util.List r6 = r6.mViewIndexDatas
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r8 = ""
                r7.append(r8)
                r7.append(r5)
                java.lang.String r7 = r7.toString()
                boolean r6 = r6.contains(r7)
                if (r6 != 0) goto L_0x0176
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                if (r6 != 0) goto L_0x00c0
                goto L_0x038f
            L_0x00c0:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                if (r6 != 0) goto L_0x00ce
                goto L_0x038f
            L_0x00ce:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                int r6 = r6.mChannel
                if (r6 >= 0) goto L_0x00da
                goto L_0x038f
            L_0x00da:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r7 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r7 = r7.mViewInfoArray
                r7 = r7[r5]
                int r7 = r7.mChannel
                int r6 = r6.getChannelState(r7)
                if (r6 != 0) goto L_0x0107
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r7 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r7 = r7.mViewInfoArray
                r7 = r7[r5]
                int r7 = r7.mChannel
                r6.StopRealAv(r7)
            L_0x0107:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r6 = r6.mVideoContainer
                int r6 = r6.getVideoFrameVisibility(r5)
                r7 = 8
                if (r6 == r7) goto L_0x0115
                goto L_0x038f
            L_0x0115:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                if (r6 != 0) goto L_0x011f
                goto L_0x038f
            L_0x011f:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                r6.StopPlay()
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r7 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r7 = r7.mViewInfoArray
                r7 = r7[r5]
                int r7 = r7.mChannel
                r8 = 0
                r6.SetAVStream(r7, r8)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                r6.SetVideoInterface(r8)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                r6.SetAudioInterface(r8)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r7 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r7 = r7.mViewInfoArray
                r7 = r7[r5]
                int r7 = r7.mChannel
                r6.StopRealAv(r7)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                r6.CloseHandle()
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6[r5] = r8
                goto L_0x038f
            L_0x0176:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                if (r6 != 0) goto L_0x0180
                goto L_0x038f
            L_0x0180:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                if (r6 != 0) goto L_0x018e
                goto L_0x038f
            L_0x018e:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                int r6 = r6.mChannel
                if (r6 >= 0) goto L_0x019a
                goto L_0x038f
            L_0x019a:
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                r6.append(r8)
                r6.append(r5)
                java.lang.String r6 = r6.toString()
                java.lang.String r7 = "i"
                android.util.Log.e(r7, r6)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r7 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r7 = r7.mViewInfoArray
                r7 = r7[r5]
                int r7 = r7.mChannel
                int r6 = r6.getChannelState(r7)
                if (r6 != 0) goto L_0x02fe
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r6 = r6.mVideoContainer
                int r6 = r6.getCurViewCount()
                r7 = 4
                if (r6 != r4) goto L_0x02a1
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r6 = r6.mVideoContainer
                int r6 = r6.getFocusChannel()
                if (r5 != r6) goto L_0x0261
                java.lang.String r6 = "FocusChannel"
                android.util.Log.e(r6, r6)
                int r6 = com.streamax.client.MyApp.CONNTYPES
                if (r6 == r3) goto L_0x038f
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                com.streamax.client.RealPlayActivity r8 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r8 = r8.mVideoContainer
                int r8 = r8.getFocusChannel()
                r6 = r6[r8]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r8 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r8 = r8.mViewInfoArray
                com.streamax.client.RealPlayActivity r9 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r9 = r9.mVideoContainer
                int r9 = r9.getFocusChannel()
                r8 = r8[r9]
                int r8 = r8.mChannel
                r6.RealPlayControl(r8, r0, r2)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                com.streamax.client.RealPlayActivity r8 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r8 = r8.mVideoContainer
                int r8 = r8.getFocusChannel()
                r6 = r6[r8]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r8 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r8 = r8.mViewInfoArray
                com.streamax.client.RealPlayActivity r9 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r9 = r9.mVideoContainer
                int r9 = r9.getFocusChannel()
                r8 = r8[r9]
                int r8 = r8.mChannel
                r6.RealPlayControl(r8, r0, r4)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                com.streamax.client.RealPlayActivity r8 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r8 = r8.mVideoContainer
                int r8 = r8.getFocusChannel()
                r6 = r6[r8]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r8 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r8 = r8.mViewInfoArray
                com.streamax.client.RealPlayActivity r9 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r9 = r9.mVideoContainer
                int r9 = r9.getFocusChannel()
                r8 = r8[r9]
                int r8 = r8.mChannel
                r6.RealPlayControl(r8, r0, r7)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r6 = r6.mVideoContainer
                com.streamax.client.RealPlayActivity$SwitchChannelRunnable$2 r7 = new com.streamax.client.RealPlayActivity$SwitchChannelRunnable$2
                r7.<init>(r5)
                r6.post(r7)
                goto L_0x038f
            L_0x0261:
                java.lang.String r6 = "others"
                android.util.Log.e(r6, r6)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r7 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r7 = r7.mViewInfoArray
                r7 = r7[r5]
                int r7 = r7.mChannel
                int r6 = r6.getChannelState(r7)
                if (r6 != 0) goto L_0x0293
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r7 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r7 = r7.mViewInfoArray
                r7 = r7[r5]
                int r7 = r7.mChannel
                r6.RealPlayControl(r7, r1, r3)
            L_0x0293:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r6 = r6.mVideoContainer
                com.streamax.client.RealPlayActivity$SwitchChannelRunnable$3 r7 = new com.streamax.client.RealPlayActivity$SwitchChannelRunnable$3
                r7.<init>(r5)
                r6.post(r7)
                goto L_0x038f
            L_0x02a1:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r6 = r6.mVideoContainer
                com.streamax.client.RealPlayActivity$SwitchChannelRunnable$4 r8 = new com.streamax.client.RealPlayActivity$SwitchChannelRunnable$4
                r8.<init>(r5)
                r6.post(r8)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                r6.setSurfaceVisibility()
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r6 = r6.mVideoContainer
                int r6 = r6.getFocusChannel()
                if (r5 != r6) goto L_0x02d2
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r8 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r8 = r8.mViewInfoArray
                r8 = r8[r5]
                int r8 = r8.mChannel
                r6.RealPlayControl(r8, r1, r2)
                goto L_0x02e7
            L_0x02d2:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r8 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r8 = r8.mViewInfoArray
                r8 = r8[r5]
                int r8 = r8.mChannel
                r6.RealPlayControl(r8, r1, r4)
            L_0x02e7:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r8 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r8 = r8.mViewInfoArray
                r8 = r8[r5]
                int r8 = r8.mChannel
                r6.RealPlayControl(r8, r1, r7)
                goto L_0x038f
            L_0x02fe:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                if (r6 != 0) goto L_0x0311
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                com.dvr.avstream.AVStream r7 = new com.dvr.avstream.AVStream
                r7.<init>()
                r6[r5] = r7
            L_0x0311:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                r6.GetHandle(r5)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.VideoContainer r6 = r6.mVideoContainer
                int r6 = r6.getFocusChannel()
                if (r6 != r5) goto L_0x0335
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                r6.SetMute(r1)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AuTrack r6 = r6.mAudioTrack
                r6.SwitchChannels(r5)
                goto L_0x033e
            L_0x0335:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                r6.SetMute(r4)
            L_0x033e:
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                r6.StartPlay()
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                com.streamax.client.RealPlayActivity r7 = com.streamax.client.RealPlayActivity.this
                r6.SetVideoInterface(r7)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r6 = r6.mAVStream
                r6 = r6[r5]
                com.streamax.client.RealPlayActivity r7 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AuTrack r7 = r7.mAudioTrack
                r6.SetAudioInterface(r7)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r7 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r7 = r7.mViewInfoArray
                r7 = r7[r5]
                int r7 = r7.mChannel
                com.streamax.client.RealPlayActivity r8 = com.streamax.client.RealPlayActivity.this
                com.dvr.avstream.AVStream[] r8 = r8.mAVStream
                r8 = r8[r5]
                r6.SetAVStream(r7, r8)
                com.streamax.client.RealPlayActivity r6 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r6 = r6.mViewInfoArray
                r6 = r6[r5]
                com.dvr.net.DvrNet r6 = r6.getNet()
                com.streamax.client.RealPlayActivity r7 = com.streamax.client.RealPlayActivity.this
                com.streamax.client.SingleViewInfo[] r7 = r7.mViewInfoArray
                r7 = r7[r5]
                int r7 = r7.mChannel
                r6.StartRealAv(r7, r0)
            L_0x038f:
                int r5 = r5 + 1
                goto L_0x008f
            L_0x0393:
                com.streamax.client.RealPlayActivity r0 = com.streamax.client.RealPlayActivity.this
                android.os.Handler r0 = r0.mHandler
                com.streamax.client.RealPlayActivity$SwitchChannelRunnable$5 r1 = new com.streamax.client.RealPlayActivity$SwitchChannelRunnable$5
                r1.<init>()
                r0.post(r1)
                goto L_0x0000
            */
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
                for (Integer intValue : this.mSelectMaps.keySet()) {
                    int intValue2 = intValue.intValue();
                    if (intValue2 == deleteDeviceEvent.mDeviceId) {
                        final int intValue3 = this.mSelectMaps.get(Integer.valueOf(intValue2)).intValue();
                        new Thread(new Runnable() {
                            public void run() {
                                RealPlayActivity.this.mCurFocus = false;
                                RealPlayActivity.this.StopPlay(intValue3);
                                RealPlayActivity.this.mHandler.post(new Runnable() {
                                    public void run() {
                                        if (RealPlayActivity.this.mVideoContainer != null) {
                                            for (int i = 0; i < RealPlayActivity.this.mVideoContainer.GetChannelList().size(); i++) {
                                                if (i == intValue3) {
                                                    RealPlayActivity.this.mVideoContainer.SetPlayState(i, false);
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

    public void setSurfaceListener(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.mVideoContainer.getVideoView(i2).setCallBack(this, i2);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3, int i4) {
        Log.e("surfaceChanged", "" + i4);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder, int i) {
        Log.e("surfaceCreated", "" + i);
        if (this.mVideoContainer.getVideoFrameVisibility(i) == 0) {
            List<String> list = this.mViewIndexDatas;
            if (!list.contains("" + i)) {
                List<String> list2 = this.mViewIndexDatas;
                list2.add("" + i);
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder, int i) {
        Log.e("surfaceDestroyed", "" + i);
    }

    public void setSurfaceVisibility() {
        int initViewCount = this.mVideoContainer.getInitViewCount();
        int curViewCount = this.mVideoContainer.getCurViewCount();
        int firstIndex = this.mVideoContainer.getFirstIndex();
        if (curViewCount == 4) {
            if (firstIndex == 0) {
                for (int i = 0; i < initViewCount; i++) {
                    if (i < 0 || i > 3) {
                        isVisible(i, false);
                    } else {
                        isVisible(i, true);
                    }
                }
            } else if (firstIndex == 4) {
                for (int i2 = 0; i2 < initViewCount; i2++) {
                    if (i2 < 4 || i2 > 7) {
                        isVisible(i2, false);
                    } else {
                        isVisible(i2, true);
                    }
                }
            } else if (firstIndex == 8) {
                for (int i3 = 0; i3 < initViewCount; i3++) {
                    if (i3 < 8 || i3 > 11) {
                        isVisible(i3, false);
                    } else {
                        isVisible(i3, true);
                    }
                }
            } else if (firstIndex == 12) {
                for (int i4 = 0; i4 < initViewCount; i4++) {
                    if (i4 < 12 || i4 > 15) {
                        isVisible(i4, false);
                    } else {
                        isVisible(i4, true);
                    }
                }
            } else if (firstIndex == 16) {
                for (int i5 = 0; i5 < initViewCount; i5++) {
                    if (i5 < 16 || i5 > 19) {
                        isVisible(i5, false);
                    } else {
                        isVisible(i5, true);
                    }
                }
            } else if (firstIndex == 20) {
                for (int i6 = 0; i6 < initViewCount; i6++) {
                    if (i6 < 20 || i6 > 23) {
                        isVisible(i6, false);
                    } else {
                        isVisible(i6, true);
                    }
                }
            } else if (firstIndex == 24) {
                for (int i7 = 0; i7 < initViewCount; i7++) {
                    if (i7 < 24 || i7 > 27) {
                        isVisible(i7, false);
                    } else {
                        isVisible(i7, true);
                    }
                }
            } else if (firstIndex == 28) {
                for (int i8 = 0; i8 < initViewCount; i8++) {
                    if (i8 < 28 || i8 > 31) {
                        isVisible(i8, false);
                    } else {
                        isVisible(i8, true);
                    }
                }
            }
        } else if (curViewCount == 9) {
            if (firstIndex == 0) {
                for (int i9 = 0; i9 < initViewCount; i9++) {
                    if (i9 < 0 || i9 > 8) {
                        isVisible(i9, false);
                    } else {
                        isVisible(i9, true);
                    }
                }
            } else if (firstIndex == 7) {
                for (int i10 = 0; i10 < initViewCount; i10++) {
                    if (i10 < 7 || i10 > 15) {
                        isVisible(i10, false);
                    } else {
                        isVisible(i10, true);
                    }
                }
            } else if (firstIndex == 9) {
                for (int i11 = 0; i11 < initViewCount; i11++) {
                    if (i11 < 9 || i11 > 17) {
                        isVisible(i11, false);
                    } else {
                        isVisible(i11, true);
                    }
                }
            } else if (firstIndex == 18) {
                for (int i12 = 0; i12 < initViewCount; i12++) {
                    if (i12 < 18 || i12 > 26) {
                        isVisible(i12, false);
                    } else {
                        isVisible(i12, true);
                    }
                }
            } else if (firstIndex == 27) {
                for (int i13 = 0; i13 < initViewCount; i13++) {
                    if ((i13 < 27 || i13 > 31) && (i13 < 0 || i13 > 3)) {
                        isVisible(i13, false);
                    } else {
                        isVisible(i13, true);
                    }
                }
            }
        } else if (curViewCount != 16) {
        } else {
            if (firstIndex == 0) {
                for (int i14 = 0; i14 < initViewCount; i14++) {
                    if (i14 < 0 || i14 > 15) {
                        isVisible(i14, false);
                    } else {
                        isVisible(i14, true);
                    }
                }
            } else if (firstIndex == 16) {
                for (int i15 = 0; i15 < initViewCount; i15++) {
                    if (i15 < 16 || i15 > 31) {
                        isVisible(i15, false);
                    } else {
                        isVisible(i15, true);
                    }
                }
            }
        }
    }

    public void isVisible(final int i, boolean z) {
        if (z) {
            this.mVideoContainer.post(new Runnable() {
                public void run() {
                    RealPlayActivity.this.mVideoContainer.getVideoView(i).setVisibility(0);
                }
            });
        } else {
            this.mVideoContainer.post(new Runnable() {
                public void run() {
                    RealPlayActivity.this.mVideoContainer.getVideoView(i).setVisibility(8);
                }
            });
        }
    }
}
