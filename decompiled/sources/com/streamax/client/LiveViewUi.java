package com.streamax.client;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.http.SslError;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import com.dvr.avstream.AVStream;
import com.dvr.avstream.AuTrack;
import com.dvr.avstream.MyCallInterface;
import com.dvr.calendar.DayStyle;
import com.dvr.net.DvrNet;
import com.google.android.gms.nearby.messages.Strategy;
import com.streamax.Configs;
import com.streamax.client.ui.devlist.ClickFullEvent;
import com.streamax.client.ui.devlist.DeleteDeviceEvent;
import com.streamax.client.ui.devlist.DevInfoEvent;
import com.streamax.client.ui.devlist.db.GroupBean;
import com.streamax.client.ui.devlist.db.GroupDao;
import com.streamax.client.ui.devlist.db.GroupDaoForNormal;
import com.streamax.client.ui.devlist.db.GroupDaoForServer;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.utils.AppProxy;
import com.streamax.utils.SpUtils;
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

public class LiveViewUi extends Activity implements Runnable, MyCallInterface, View.OnClickListener, PopupWindow.OnDismissListener, SurfaceCallBackInterface {
    public static final int CHANNEL_NUMBER = 32;
    public static final int GroupPlay = -1;
    private static final int PlayStatusPlaying = 0;
    private static final int PlayStatusStopped = 1;
    public static Object lock = new Object();
    public boolean bConfiguration;
    private int initMode;
    public boolean landscape = false;
    public AVStream[] mAVStream = new AVStream[32];
    public MyApp mApp;
    public AuTrack mAudioTrack = new AuTrack();
    private List<GroupBean> mBeanDatas;
    /* access modifiers changed from: private */
    public Button mBtnPlay;
    public Button mBtnSelect;
    /* access modifiers changed from: private */
    public int mChCount;
    /* access modifiers changed from: private */
    public int mChannel;
    public Context mContext;
    int mCount;
    private GroupDao mDao;
    private int mDevId;
    private DevInfoBean mDevInfo;
    private DevInfoEvent mDevInfoBean;
    public List<DevInfoBean> mDevLists;
    public List<GroupBean> mGroupLists;
    private String mGroupName;
    public List<String> mGroupNameDatas;
    public Handler mHandler = AppProxy.getHandler();
    public LayoutInflater mInflater;
    public View mLiveView;
    public PopupWindow mPopupCapture;
    public LinearLayout mPreviewLayout;
    public Map<Integer, String> mRecordPathMap = new Hashtable();
    public Stack<Integer> mRecordStack = new Stack<>();
    public ScrollView mScrollView;
    public int mSelectChannel;
    public int mSelectId;
    public Map<String, Object> mSelectMap = new HashMap();
    /* access modifiers changed from: private */
    public ProgressBar mStopPbLoading;
    private int mTime;
    public FrameLayout mTitlebar;
    private TextView mTvTitle;
    public String mUrl;
    public VideoGroup mVideoGroup;
    public LinearLayout mVideoGroupLayout;
    public FrameLayout mVideoInfo;
    /* access modifiers changed from: private */
    public List<String> mViewIndexDatas = new ArrayList();
    public SingleViewInfo[] mViewInfoArray = new SingleViewInfo[32];
    public WebView mWebView;
    public boolean mbMute;
    public boolean mbPreview;
    public boolean mbPtz;
    public boolean[] mbRecordArray = new boolean[32];
    public boolean mbState;
    /* access modifiers changed from: private */
    public boolean mbSwitch;
    public List<Map<String, Object>> mbmpList;
    public DbHelper mdbHelper;
    public int mnMaxView = 16;
    public View mpopViewer;
    public TextView mtvVideoInformation;
    public PopupWindow pop;

    public void SetPtzState(boolean z) {
    }

    public void onDismiss() {
    }

    public void run() {
    }

    public void setPlayStatus(int i) {
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
        View inflate = from.inflate(R.layout.previewpage, (ViewGroup) null);
        this.mLiveView = inflate;
        this.mStopPbLoading = (ProgressBar) inflate.findViewById(R.id.preview_pbloading);
        this.mdbHelper = new DbHelper(this.mContext, DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        setContentView(this.mLiveView);
        FindViews();
        this.mTvTitle.setText(MyApp.serverip);
        getLocalMacAddress();
        new Thread(new SwitchChannelRunnable()).start();
        new Thread(new Runnable() {
            public void run() {
                LiveViewUi.this.AutoPlay();
            }
        }).start();
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

    public void popMenu(View view, View view2) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        PopupWindow popupWindow = this.pop;
        if (popupWindow == null) {
            PopupWindow popupWindow2 = new PopupWindow(view, i / 2, i2 / 2, true);
            this.pop = popupWindow2;
            popupWindow2.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.select_device_bg));
            this.pop.setTouchInterceptor(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 4) {
                        return false;
                    }
                    LiveViewUi.this.pop.dismiss();
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
            popupWindow3.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.select_device_bg));
            this.pop.setOutsideTouchable(false);
            this.pop.showAsDropDown(view2, 1, 0);
            this.pop.update();
        }
    }

    public void FindViews() {
        this.mPreviewLayout = (LinearLayout) this.mLiveView.findViewById(R.id.preview_layout);
        this.mVideoGroupLayout = (LinearLayout) this.mLiveView.findViewById(R.id.videogroup_layout);
        VideoGroup videoGroup = (VideoGroup) this.mLiveView.findViewById(R.id.preview_videogroup);
        this.mVideoGroup = videoGroup;
        if (videoGroup != null) {
            videoGroup.SetActivity(this);
            this.mVideoGroup.setCanFull(true);
            this.mVideoGroup.LoadViews();
            initViewCounts(this.mApp.mDevInfo.mChCounts);
        }
        this.mTitlebar = (FrameLayout) this.mLiveView.findViewById(R.id.title_preview);
        this.mVideoInfo = (FrameLayout) this.mLiveView.findViewById(R.id.preview_videoinformation);
        this.mtvVideoInformation = (TextView) this.mLiveView.findViewById(R.id.preview_videoinformation_text);
        this.mTvTitle = (TextView) this.mLiveView.findViewById(R.id.preview_title_text);
        Button button = (Button) this.mLiveView.findViewById(R.id.preview_title_button_play);
        this.mBtnPlay = button;
        button.setOnClickListener(this);
        this.mScrollView = (ScrollView) this.mLiveView.findViewById(R.id.scroll_view);
        this.mUrl = "https://www.baidu.com/";
        this.mUrl = String.format("http://%s/RealInfo", new Object[]{MyApp.serverip});
        WebView webView = (WebView) this.mLiveView.findViewById(R.id.webview);
        this.mWebView = webView;
        webView.setHorizontalScrollBarEnabled(false);
        this.mWebView.setHorizontalScrollbarOverlay(false);
        this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setSupportZoom(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        try {
            WebSettings.class.getMethod("setAppCacheEnabled", Boolean.TYPE).invoke(this.mWebView.getSettings(), Boolean.TRUE);
        } catch (Exception unused) {
        }
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                if (sslErrorHandler != null) {
                    sslErrorHandler.proceed();
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (webView == null || str == null) {
                    return false;
                }
                if (!str.startsWith("http") && !str.startsWith("https")) {
                    return true;
                }
                webView.loadUrl(str);
                return true;
            }
        });
        this.mWebView.loadUrl(this.mUrl);
    }

    public void AutoPlay() {
        DevInfoBean devInfoBean;
        if (this.mApp.mbAutoPlay) {
            for (int i = 0; i < this.mnMaxView; i++) {
                SharedPreferences sharedPreferences = getSharedPreferences(String.format("preview%02d", new Object[]{Integer.valueOf(i)}), 0);
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
                                if (this.mApp.mWebService == null) {
                                    this.mApp.mWebService = new WebService(MyApp.LastServerHostName, MyApp.username, MyApp.password);
                                }
                                if (this.mApp.mWebService.mDevList != null && this.mApp.mWebService.mDevList.size() == 0) {
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
            this.mRecordStack.push(Integer.valueOf(i));
            this.mRecordPathMap.put(Integer.valueOf(i), format + format2);
            this.mAVStream[i].StartRecord(Configs.Key.RecordDirPath + format + format2);
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (singleViewInfoArr[i] != null && singleViewInfoArr[i].getNet() != null) {
                this.mViewInfoArray[i].getNet().RequestIFrame(this.mViewInfoArray[i].mChannel, 0);
                this.mVideoGroup.SetRecState(i, true);
            }
        }
    }

    public void StopRecord(final int i) {
        AVStream[] aVStreamArr = this.mAVStream;
        if (aVStreamArr[i] != null) {
            aVStreamArr[i].StopRecord();
            this.mVideoGroup.post(new Runnable() {
                public void run() {
                    LiveViewUi.this.mVideoGroup.SetRecState(i, false);
                }
            });
            this.mbRecordArray[i] = false;
        }
    }

    public List<Map<String, Object>> CaptureImage(int i, int i2) {
        String str = Environment.getExternalStorageDirectory() + "/streaming/capture/";
        Calendar instance = Calendar.getInstance();
        String format = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13))});
        ArrayList arrayList = new ArrayList();
        for (int i3 = i; i3 < i2 + i; i3++) {
            if (this.mAVStream[i3] != null) {
                HashMap hashMap = new HashMap();
                String str2 = str + format + String.format("%02d.bmp", new Object[]{Integer.valueOf(i3 + 1)});
                hashMap.put("channel", Integer.valueOf(i3));
                hashMap.put("path", str2);
                if (this.mAVStream[i3].Capture(str2) == 0) {
                    arrayList.add(hashMap);
                }
            }
        }
        return arrayList;
    }

    public void popImageViewer(List<Map<String, Object>> list) {
        List<Map<String, Object>> list2 = list;
        if (list.size() != 0) {
            this.mbmpList = list2;
            int size = list.size();
            BitmapFactory.Options options = new BitmapFactory.Options();
            boolean z = true;
            options.inSampleSize = 1;
            options.inTempStorage = new byte[829440];
            this.mpopViewer = this.mInflater.inflate(R.layout.captureimageviewer, (ViewGroup) null);
            LinearLayout linearLayout = new LinearLayout(this.mContext);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            linearLayout.setBackgroundColor(Color.argb(200, 40, 40, 40));
            linearLayout.setPadding(0, 0, 0, 0);
            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
            float f = 1.0f;
            layoutParams.weight = 1.0f;
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.setMargins(1, 1, 1, 1);
            int sqrt = (int) Math.sqrt((double) (size + 1));
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = 0;
            while (i < sqrt) {
                LinearLayout linearLayout2 = new LinearLayout(this.mContext);
                linearLayout2.setOrientation(0);
                linearLayout2.setPadding(0, 0, 0, 0);
                linearLayout2.setBackgroundColor(DayStyle.iColorBkg);
                TableLayout.LayoutParams layoutParams2 = new TableLayout.LayoutParams();
                layoutParams2.weight = f;
                layoutParams2.width = displayMetrics.widthPixels / sqrt;
                if (this.bConfiguration == z && this.mVideoGroup.GetCurArrayMode() != 1) {
                    layoutParams2.height = (displayMetrics.heightPixels / sqrt) - 60;
                } else if (this.bConfiguration == z && this.mVideoGroup.GetCurArrayMode() == 1) {
                    layoutParams2.height = (displayMetrics.heightPixels / sqrt) - 120;
                } else {
                    layoutParams2.height = (layoutParams2.width / 4) * 3;
                }
                for (int i2 = 0; i2 < sqrt; i2++) {
                    ImageView imageView = new ImageView(this.mContext);
                    int i3 = (i * sqrt) + i2;
                    if (i3 < size) {
                        String obj = list2.get(i3).get("path").toString();
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageView.setImageBitmap(BitmapFactory.decodeFile(obj));
                        linearLayout2.addView(imageView, layoutParams2);
                    } else {
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageView.setPadding(0, 0, 0, 0);
                        linearLayout2.addView(imageView, layoutParams2);
                    }
                }
                linearLayout.addView(linearLayout2, layoutParams);
                i++;
                z = true;
                f = 1.0f;
            }
            ((LinearLayout) this.mpopViewer.findViewById(R.id.preview_capture_imagegroup)).addView(linearLayout);
            this.mPopupCapture = null;
            PopupWindow popupWindow = new PopupWindow(this.mpopViewer, -1, -1, true);
            this.mPopupCapture = popupWindow;
            popupWindow.setOnDismissListener(this);
            this.mPopupCapture.setOutsideTouchable(false);
            this.mPopupCapture.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.select_device_bg));
            this.mPopupCapture.showAtLocation(this.mLiveView, 17, 0, 0);
            this.mPopupCapture.update();
            this.mpopViewer.findViewById(R.id.preview_capture_save).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    LiveViewUi.this.mtvVideoInformation.setText(LiveViewUi.this.mContext.getString(R.string.save_successful));
                    LiveViewUi.this.mPopupCapture.dismiss();
                }
            });
            this.mpopViewer.findViewById(R.id.preview_capture_cancel).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    for (int i = 0; i < LiveViewUi.this.mbmpList.size(); i++) {
                        String obj = LiveViewUi.this.mbmpList.get(i).get("path").toString();
                        if (obj != null) {
                            new File(obj).delete();
                        }
                    }
                    LiveViewUi.this.mtvVideoInformation.setText(LiveViewUi.this.mContext.getString(R.string.save_cancel));
                    LiveViewUi.this.mPopupCapture.dismiss();
                }
            });
        }
    }

    public void setVideoGroupMax(boolean z) {
        if (z) {
            getWindow().getDecorView().setSystemUiVisibility(4);
            this.mTitlebar.setVisibility(8);
            ViewGroup.LayoutParams layoutParams = this.mPreviewLayout.getLayoutParams();
            layoutParams.height = -1;
            this.mPreviewLayout.setLayoutParams(layoutParams);
            ViewGroup.LayoutParams layoutParams2 = this.mVideoGroupLayout.getLayoutParams();
            layoutParams2.height = -1;
            this.mVideoGroupLayout.setLayoutParams(layoutParams2);
            this.mVideoInfo.setVisibility(8);
            this.mScrollView.setVisibility(8);
            return;
        }
        getWindow().getDecorView().setSystemUiVisibility(0);
        this.mTitlebar.setVisibility(0);
        ViewGroup.LayoutParams layoutParams3 = this.mPreviewLayout.getLayoutParams();
        layoutParams3.height = -2;
        this.mPreviewLayout.setLayoutParams(layoutParams3);
        ViewGroup.LayoutParams layoutParams4 = this.mVideoGroupLayout.getLayoutParams();
        layoutParams4.height = (int) TypedValue.applyDimension(1, (float) Strategy.TTL_SECONDS_DEFAULT, getResources().getDisplayMetrics());
        this.mVideoGroupLayout.setLayoutParams(layoutParams4);
        this.mVideoInfo.setVisibility(0);
        this.mScrollView.setVisibility(0);
    }

    public void SetStreamDecodeState(int i, boolean z) {
        AVStream[] aVStreamArr = this.mAVStream;
        if (aVStreamArr[i] != null) {
            aVStreamArr[i].SetStreamDecodeState(z);
        }
    }

    public void StartPlay(int i, int i2, final int i3) {
        DevInfoBean devInfoBean;
        this.mbPreview = true;
        synchronized (lock) {
            int channel = i3;
            int i4 = -1;
            if (this.mDevId != -1) {
                devInfoBean = this.mDevInfo;
            } else if (MyApp.loginType == 0) {
                devInfoBean = this.mdbHelper.query(i);
            } else {
                devInfoBean = this.mApp.mWebService.query(i);
            }
            if (devInfoBean != null) {
                if (channel == -1) {
                    channel = this.mVideoGroup.GetFocusChannel();
                }
                SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
                if (singleViewInfoArr[channel] == null || !singleViewInfoArr[channel].check(devInfoBean) || i2 != this.mViewInfoArray[channel].mChannel) {
                    final int targetChannel = channel;
                    this.mVideoGroup.post(new Runnable() {
                        public void run() {
                            LiveViewUi.this.mVideoGroup.SetBusyState(targetChannel, true);
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
                    SingleViewInfo[] singleViewInfoArr2 = this.mViewInfoArray;
                    if (singleViewInfoArr2[channel] == null) {
                        singleViewInfoArr2[channel] = new SingleViewInfo();
                    }
                    if (channel > 0) {
                        SingleViewInfo[] singleViewInfoArr3 = this.mViewInfoArray;
                        int i5 = channel - 1;
                        if (!(singleViewInfoArr3[i5] == null || singleViewInfoArr3[i5].getDeviceInfoBean() == null || this.mViewInfoArray[i5].getDeviceInfoBean().mDevIp == null || !this.mViewInfoArray[i5].getDeviceInfoBean().mDevIp.equals(devInfoBean.mDevIp))) {
                            dvrNet = this.mViewInfoArray[i5].getNet();
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
                            this.mAVStream[channel].GetHandle(channel);
                            this.mVideoGroup.SetPlayState(channel, true);
                            this.mViewInfoArray[channel].setDeviceInfo(devInfoBean);
                            this.mViewInfoArray[channel].mChannel = i2;
                            this.mAudioTrack.mPlayer.play();
                            this.mAudioTrack.SetMute(this.mbMute);
                            if (channel == this.mVideoGroup.GetFocusChannel()) {
                                this.mAVStream[channel].SetMute(false);
                                this.mAudioTrack.SwitchChannels(channel);
                            } else {
                                this.mAVStream[channel].SetMute(true);
                            }
                            this.mAVStream[channel].StartPlay();
                            this.mAVStream[channel].SetVideoInterface(this);
                            this.mAVStream[channel].SetAudioInterface(this.mAudioTrack);
                            this.mViewInfoArray[channel].getNet().SetAVStream(i2, this.mAVStream[channel]);
                            this.mViewInfoArray[channel].getNet().StartRealAv(i2, 0);
                            this.mCount++;
                        } else {
                            if (i4 == 64) {
                                this.mtvVideoInformation.post(new Runnable() {
                                    public void run() {
                                        LiveViewUi.this.mtvVideoInformation.setText(LiveViewUi.this.mContext.getString(R.string.permissiondenied));
                                    }
                                });
                            } else if (i4 == 63) {
                                this.mtvVideoInformation.post(new Runnable() {
                                    public void run() {
                                        LiveViewUi.this.mtvVideoInformation.setText(LiveViewUi.this.mContext.getString(R.string.macillegal));
                                    }
                                });
                            } else if (i4 == 24) {
                                this.mtvVideoInformation.post(new Runnable() {
                                    public void run() {
                                        LiveViewUi.this.mtvVideoInformation.setText(LiveViewUi.this.mContext.getString(R.string.moreuseronline));
                                    }
                                });
                            } else if (i4 == 5) {
                                this.mtvVideoInformation.post(new Runnable() {
                                    public void run() {
                                        LiveViewUi.this.mtvVideoInformation.setText(LiveViewUi.this.mContext.getString(R.string.usernameorpassworderror));
                                    }
                                });
                            }
                            this.mViewInfoArray[channel].reset();
                            this.mViewInfoArray[channel].setConnState(1);
                        }
                    } else {
                        SingleViewInfo[] singleViewInfoArr4 = this.mViewInfoArray;
                        if (singleViewInfoArr4[channel] == null) {
                            singleViewInfoArr4[channel] = new SingleViewInfo();
                        }
                        this.mViewInfoArray[channel].setNet(dvrNet);
                        this.mAudioTrack.mPlayer.play();
                        this.mAudioTrack.SetMute(this.mbMute);
                        AVStream[] aVStreamArr3 = this.mAVStream;
                        if (aVStreamArr3[channel] == null) {
                            aVStreamArr3[channel] = new AVStream();
                        }
                        this.mAVStream[channel].GetHandle(channel);
                        this.mVideoGroup.SetPlayState(channel, true);
                        this.mViewInfoArray[channel].setDeviceInfo(devInfoBean);
                        this.mViewInfoArray[channel].mChannel = i2;
                        if (channel == this.mVideoGroup.GetFocusChannel()) {
                            this.mAudioTrack.SwitchChannels(channel);
                            this.mAVStream[channel].SetMute(true);
                        } else {
                            this.mAVStream[channel].SetMute(false);
                        }
                        this.mAVStream[channel].StartPlay();
                        this.mAVStream[channel].SetVideoInterface(this);
                        this.mAVStream[channel].SetAudioInterface(this.mAudioTrack);
                        this.mViewInfoArray[channel].getNet().SetAVStream(i2, this.mAVStream[channel]);
                        this.mViewInfoArray[channel].getNet().StartRealAv(i2, 0);
                    }
                    this.mVideoGroup.post(new Runnable() {
                        public void run() {
                            LiveViewUi.this.mVideoGroup.SetBusyState(targetChannel, false);
                        }
                    });
                }
            }
        }
    }

    public void StopPlay(final int i) {
        this.mbPreview = false;
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
                    this.mVideoGroup.post(new Runnable() {
                        public void run() {
                            LiveViewUi.this.mVideoGroup.SetPlayState(LiveViewUi.this.mVideoGroup.GetFocusChannel(), false);
                            LiveViewUi.this.mtvVideoInformation.setText(LiveViewUi.this.mContext.getString(R.string.closevideo));
                            LiveViewUi.this.mbRecordArray[i] = false;
                            LiveViewUi.this.setPlayStatus(1);
                            LiveViewUi.this.mVideoGroup.getVideoView(i).setVisibility(4);
                        }
                    });
                    this.mViewInfoArray[i].reset();
                }
            }
        }
    }

    public void openSound(int i) {
        if (this.mAVStream[i] != null) {
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (singleViewInfoArr[i] != null && singleViewInfoArr[i].getNet() != null) {
                this.mViewInfoArray[i].getNet().SetStreamSound(this.mViewInfoArray[i].mChannel, this.mApp.mStreamType, true);
                this.mAVStream[i].SetMute(false);
                this.mAudioTrack.SwitchChannels(i);
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

    public void fuc(int i, int i2, byte[] bArr, int i3, int i4, int i5) {
        VideoView videoView = this.mVideoGroup.getVideoView(i);
        if (videoView != null) {
            videoView.writeIn(bArr, i4, i5);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.mbState = false;
        this.mAudioTrack.SetMute(true);
        stopPlayVideo0();
        this.mBtnPlay.setBackgroundResource(R.drawable.live_preview);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.mbState = true;
        this.mAudioTrack.SetMute(this.mbMute);
        for (int i = 0; i < this.mnMaxView; i++) {
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (!(singleViewInfoArr[i] == null || singleViewInfoArr[i].mDevInfo == null || this.mViewInfoArray[i].getNet() == null)) {
                this.mViewInfoArray[i].getNet().RealPlayControl(this.mViewInfoArray[i].mChannel, 0, 1);
            }
        }
        super.onResume();
    }

    public void onEventMainThread(DevInfoBean devInfoBean) {
        this.mDevInfo = devInfoBean;
        initViewCounts(devInfoBean.mChCounts);
    }

    private void playDev() {
        stopPlayVideo0();
        startPlayVideo0();
    }

    private void stopPlayVideo0() {
        MyApp.CONNTYPES = -1;
        this.mHandler.post(new Runnable() {
            public void run() {
                LiveViewUi.this.mVideoGroup.setVisibility(8);
                LiveViewUi.this.mStopPbLoading.setVisibility(0);
            }
        });
        this.initMode = this.mVideoGroup.getInitMode();
        for (int i = 0; i < this.initMode; i++) {
            StopPlay(i);
        }
        this.mAudioTrack.mPlayer.Stop();
        this.mHandler.post(new Runnable() {
            public void run() {
                LiveViewUi.this.mVideoGroup.ClearViews();
                LiveViewUi.this.mStopPbLoading.setVisibility(8);
                LiveViewUi.this.mVideoGroup.setVisibility(0);
            }
        });
    }

    /* access modifiers changed from: private */
    public void stopPlayVideo() {
        MyApp.CONNTYPES = -1;
        this.mHandler.post(new Runnable() {
            public void run() {
                LiveViewUi.this.mVideoGroup.setVisibility(8);
                LiveViewUi.this.mStopPbLoading.setVisibility(0);
            }
        });
        this.initMode = this.mVideoGroup.getInitMode();
        for (int i = 0; i < this.initMode; i++) {
            StopPlay(i);
        }
        this.mAudioTrack.mPlayer.Stop();
        this.mHandler.post(new Runnable() {
            public void run() {
                LiveViewUi.this.mVideoGroup.ClearViews();
                LiveViewUi.this.mStopPbLoading.setVisibility(8);
                LiveViewUi.this.mVideoGroup.setVisibility(0);
                LiveViewUi.this.mRecordStack.clear();
            }
        });
    }

    private void startPlayVideo0() {
        this.mCount = 0;
        this.mDevId = 0;
        DevInfoBean devInfoBean = this.mDevInfo;
        if (devInfoBean != null) {
            this.mChannel = devInfoBean.mChCounts;
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                LiveViewUi liveViewUi = LiveViewUi.this;
                liveViewUi.initViewCounts(liveViewUi.mChannel);
                for (int i = 0; i < LiveViewUi.this.mChannel; i++) {
                    LiveViewUi.this.mVideoGroup.getVideoView(i).setVisibility(0);
                }
            }
        });
        for (int i = 0; i < this.mChannel; i++) {
            if (i > 0) {
                SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
                int i2 = i - 1;
                if (singleViewInfoArr[i2] != null && singleViewInfoArr[i2].getConnState() == 1) {
                    return;
                }
            }
            StartPlay(this.mDevId, i, i);
            if (this.mVideoGroup.getVideoFrameVisibility(i) == 0) {
                if (!this.mViewIndexDatas.contains("" + i)) {
                    this.mViewIndexDatas.add("" + i);
                }
            }
        }
        this.mtvVideoInformation.post(new Runnable() {
            public void run() {
                if (LiveViewUi.this.mCount == 0) {
                    LiveViewUi.this.mtvVideoInformation.setText(R.string.closevideo);
                } else {
                    LiveViewUi.this.mBtnPlay.setBackgroundResource(R.drawable.live_preview);
                }
            }
        });
    }

    private void startPlayVideo(DevInfoEvent devInfoEvent) {
        int i = 0;
        this.mCount = 0;
        this.mtvVideoInformation.post(new Runnable() {
            public void run() {
                LiveViewUi.this.mtvVideoInformation.setText(R.string.openvideo);
            }
        });
        this.mTime = -1;
        this.mDevId = devInfoEvent.mId;
        Log.e("mDevId", "" + this.mDevId);
        if (this.mDevId != -1) {
            if (MyApp.loginType == 0) {
                this.mDevInfo = this.mdbHelper.query(this.mDevId);
            } else {
                this.mDevInfo = this.mApp.mWebService.query(this.mDevId);
            }
            DevInfoBean devInfoBean = this.mDevInfo;
            if (devInfoBean != null) {
                this.mChannel = devInfoBean.mChCounts;
            }
            this.mHandler.post(new Runnable() {
                public void run() {
                    LiveViewUi liveViewUi = LiveViewUi.this;
                    liveViewUi.initViewCounts(liveViewUi.mChannel);
                    for (int i = 0; i < LiveViewUi.this.mChannel; i++) {
                        LiveViewUi.this.mVideoGroup.getVideoView(i).setVisibility(0);
                    }
                }
            });
            while (i < this.mChannel) {
                if (i > 0) {
                    SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
                    int i2 = i - 1;
                    if (singleViewInfoArr[i2] != null && singleViewInfoArr[i2].getConnState() == 1) {
                        return;
                    }
                }
                StartPlay(this.mDevId, i, i);
                if (this.mVideoGroup.getVideoFrameVisibility(i) == 0) {
                    if (!this.mViewIndexDatas.contains("" + i)) {
                        this.mViewIndexDatas.add("" + i);
                    }
                }
                i++;
            }
            this.mtvVideoInformation.post(new Runnable() {
                public void run() {
                    if (LiveViewUi.this.mCount == 0) {
                        LiveViewUi.this.mtvVideoInformation.setText(R.string.closevideo);
                        LiveViewUi.this.setPlayStatus(1);
                        return;
                    }
                    LiveViewUi.this.setPlayStatus(0);
                }
            });
            return;
        }
        this.mChCount = 0;
        this.mGroupName = devInfoEvent.mGroupName;
        if (MyApp.loginType == 0) {
            this.mDao = new GroupDaoForNormal();
        } else {
            this.mDao = new GroupDaoForServer();
        }
        this.mBeanDatas = this.mDao.getGroupDatasByName(this.mGroupName);
        for (int i3 = 0; i3 < this.mBeanDatas.size(); i3++) {
            if (this.mBeanDatas.get(i3).dbFlag == 1) {
                this.mChCount++;
            }
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                LiveViewUi liveViewUi = LiveViewUi.this;
                liveViewUi.initViewCounts(liveViewUi.mChCount);
            }
        });
        while (i < this.mBeanDatas.size()) {
            GroupBean groupBean = this.mBeanDatas.get(i);
            if (groupBean.dbFlag == 1) {
                int i4 = this.mTime + 1;
                this.mTime = i4;
                if (i4 < 32) {
                    StartPlay(groupBean.dbId, groupBean.dbChannel, this.mTime);
                } else {
                    int i5 = this.mCount;
                    if (i5 == 0) {
                        this.mtvVideoInformation.post(new Runnable() {
                            public void run() {
                                LiveViewUi.this.mtvVideoInformation.setText(R.string.closevideo);
                                LiveViewUi.this.setPlayStatus(1);
                            }
                        });
                    } else if (i5 > 0) {
                        this.mtvVideoInformation.post(new Runnable() {
                            public void run() {
                                LiveViewUi.this.setPlayStatus(0);
                            }
                        });
                    }
                }
            }
            i++;
        }
    }

    /* access modifiers changed from: private */
    public void initViewCounts(int i) {
        if (i == 1) {
            this.mVideoGroup.SetInitMode(1);
            setSurfaceListener(1);
        } else if (i <= 4) {
            this.mVideoGroup.SetInitMode(4);
            setSurfaceListener(4);
        } else if (i <= 9) {
            this.mVideoGroup.SetInitMode(9);
            setSurfaceListener(9);
        } else if (i <= 16) {
            this.mVideoGroup.SetInitMode(16);
            setSurfaceListener(16);
        } else if (i <= 32) {
            this.mVideoGroup.SetInitMode(32);
            setSurfaceListener(32);
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
                getSharedPreferences(String.format("preview%02d", new Object[]{Integer.valueOf(i)}), 0).edit().clear().commit();
            } else {
                if (this.mApp.mbAutoPlay) {
                    try {
                        this.mViewInfoArray[i].mDevInfo.PrintOut();
                        SharedPreferences sharedPreferences = getSharedPreferences(String.format("preview%02d", new Object[]{Integer.valueOf(i)}), 0);
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
        super.onDestroy();
    }

    public static List<String> removeDuplicate(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!arrayList.contains(list.get(i))) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    public void SwitchPlay() {
        for (int i = 0; i < this.mVideoGroup.getInitViewCount(); i++) {
            SingleViewInfo[] singleViewInfoArr = this.mViewInfoArray;
            if (!(singleViewInfoArr[i] == null || singleViewInfoArr[i].getNet() == null || this.mViewInfoArray[i].mDevInfo == null || this.mViewInfoArray[i].getNet().mNetType == 2 || this.mVideoGroup.getVideoFrameVisibility(i) != 0)) {
                List<String> list = this.mViewIndexDatas;
                if (!list.contains("" + i)) {
                    List<String> list2 = this.mViewIndexDatas;
                    list2.add("" + i);
                }
            }
        }
        this.mbSwitch = true;
    }

    public class SwitchChannelRunnable implements Runnable {
        public SwitchChannelRunnable() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
            if (r0 == 3) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x008a, code lost:
            if (r10.this$0.mViewInfoArray[r10.this$0.mVideoGroup.getFocusView()].getNet().nDevClass == 2) goto L_0x004e;
         */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0099  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            while (true) {
                while (!LiveViewUi.this.mbSwitch) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (LiveViewUi.this.mViewIndexDatas.size() == 0) {
                    continue;
                }
                LiveViewUi.this.mbSwitch = false;
                LiveViewUi.this.mHandler.post(new Runnable() {
                    public void run() {
                    }
                });
                int streamType = 0;
                if (LiveViewUi.this.mVideoGroup.getCurViewCount() == 1 && MyApp.smartStreamStatus == 1) {
                    int singleStreamType = MyApp.singleStreamType;
                    if (singleStreamType == 1) {
                        streamType = 1;
                    } else if (singleStreamType == 3) {
                        streamType = 2;
                    } else if (singleStreamType == 0) {
                        int focusView = LiveViewUi.this.mVideoGroup.getFocusView();
                        SingleViewInfo[] viewInfoArray = LiveViewUi.this.mViewInfoArray;
                        if (viewInfoArray[focusView] != null && viewInfoArray[focusView].getNet() != null && viewInfoArray[focusView].getNet().nDevClass == 2) {
                            streamType = 2;
                        } else {
                            streamType = 1;
                        }
                    }
                }
                for (int i = 0; i < LiveViewUi.this.mVideoGroup.getInitViewCount(); i++) {
                    if (!LiveViewUi.this.mViewIndexDatas.contains("" + i)) {
                        SingleViewInfo viewInfo = LiveViewUi.this.mViewInfoArray[i];
                        if (viewInfo == null || viewInfo.getNet() == null || viewInfo.mChannel < 0) {
                            continue;
                        }
                        if (viewInfo.getNet().getChannelState(viewInfo.mChannel) == 0) {
                            viewInfo.getNet().StopRealAv(viewInfo.mChannel);
                        }
                        if (LiveViewUi.this.mVideoGroup.getVideoFrameVisibility(i) != 8) {
                            continue;
                        }
                        AVStream[] avStreams = LiveViewUi.this.mAVStream;
                        if (avStreams[i] == null) {
                            continue;
                        }
                        avStreams[i].StopPlay();
                        viewInfo.getNet().SetAVStream(viewInfo.mChannel, null);
                        avStreams[i].SetVideoInterface(null);
                        avStreams[i].SetAudioInterface(null);
                        viewInfo.getNet().StopRealAv(viewInfo.mChannel);
                        avStreams[i].CloseHandle();
                        avStreams[i] = null;
                        continue;
                    }
                    SingleViewInfo viewInfo2 = LiveViewUi.this.mViewInfoArray[i];
                    if (viewInfo2 == null || viewInfo2.getNet() == null || viewInfo2.mChannel < 0) {
                        continue;
                    }
                    if (viewInfo2.getNet().getChannelState(viewInfo2.mChannel) == 0) {
                        if (LiveViewUi.this.mVideoGroup.getCurViewCount() == 1) {
                            if (i == LiveViewUi.this.mVideoGroup.getFocusView()) {
                                if (MyApp.CONNTYPES == 2) {
                                    continue;
                                }
                                DvrNet net = viewInfo2.getNet();
                                int channel = viewInfo2.mChannel;
                                net.RealPlayControl(channel, streamType, 3);
                                net.RealPlayControl(channel, streamType, 1);
                                net.RealPlayControl(channel, streamType, 4);
                                LiveViewUi.this.mVideoGroup.post(new Runnable() {
                                    public void run() {
                                    }
                                });
                                continue;
                            } else {
                                DvrNet net = viewInfo2.getNet();
                                int channel = viewInfo2.mChannel;
                                if (net.getChannelState(channel) == 0) {
                                    net.RealPlayControl(channel, 0, 2);
                                }
                                LiveViewUi.this.mVideoGroup.post(new Runnable() {
                                    public void run() {
                                    }
                                });
                                continue;
                            }
                        } else {
                            LiveViewUi.this.setSurfaceVisibility();
                            if (i == LiveViewUi.this.mVideoGroup.getFocusView()) {
                                viewInfo2.getNet().RealPlayControl(viewInfo2.mChannel, 0, 3);
                            } else {
                                viewInfo2.getNet().RealPlayControl(viewInfo2.mChannel, 0, 1);
                            }
                            viewInfo2.getNet().RealPlayControl(viewInfo2.mChannel, 0, 4);
                            continue;
                        }
                    }
                    if (LiveViewUi.this.mAVStream[i] == null) {
                        LiveViewUi.this.mAVStream[i] = new AVStream();
                    }
                    LiveViewUi.this.mAVStream[i].GetHandle(i);
                    if (LiveViewUi.this.mVideoGroup.getFocusView() == i) {
                        LiveViewUi.this.mAVStream[i].SetMute(false);
                        LiveViewUi.this.mAudioTrack.SwitchChannels(i);
                    } else {
                        LiveViewUi.this.mAVStream[i].SetMute(true);
                    }
                    LiveViewUi.this.mAVStream[i].StartPlay();
                    LiveViewUi.this.mAVStream[i].SetVideoInterface(LiveViewUi.this);
                    LiveViewUi.this.mAVStream[i].SetAudioInterface(LiveViewUi.this.mAudioTrack);
                    viewInfo2.getNet().SetAVStream(viewInfo2.mChannel, LiveViewUi.this.mAVStream[i]);
                    viewInfo2.getNet().StartRealAv(viewInfo2.mChannel, streamType);
                }
                LiveViewUi.this.mHandler.post(new Runnable() {
                    public void run() {
                    }
                });
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.preview_title_button_play) {
            playDev();
        }
    }

    private void selectDev() {
        LinearLayout linearLayout = (LinearLayout) this.mInflater.inflate(R.layout.popupwindow_device, (ViewGroup) null);
        DeviceSelect deviceSelect = (DeviceSelect) linearLayout.findViewById(R.id.device_select_device_view);
        deviceSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i < LiveViewUi.this.mDevLists.size()) {
                    EventBus.getDefault().post(new DevInfoEvent(((Integer) ((TextView) view).getTag()).intValue(), ""));
                } else {
                    EventBus.getDefault().post(new DevInfoEvent(-1, LiveViewUi.this.mGroupNameDatas.get(i - LiveViewUi.this.mDevLists.size())));
                }
                LiveViewUi.this.pop.dismiss();
                LiveViewUi.this.pop = null;
            }
        });
        if (MyApp.loginType == 0) {
            this.mDao = new GroupDaoForNormal();
            this.mDevLists = this.mdbHelper.getlist();
        } else {
            this.mDao = new GroupDaoForServer();
            this.mDevLists = this.mApp.mWebService.GetTerminalInfoAndroid(true);
        }
        this.mGroupLists = this.mDao.getGroupDatas();
        this.mGroupNameDatas = new ArrayList();
        List<GroupBean> list = this.mGroupLists;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.mGroupLists.size(); i++) {
                GroupBean groupBean = this.mGroupLists.get(i);
                if (!this.mGroupNameDatas.contains(groupBean.dbGroupName)) {
                    this.mGroupNameDatas.add(groupBean.dbGroupName);
                }
            }
        }
        deviceSelect.SetData(this.mDevLists, this.mGroupNameDatas);
        popMenu(linearLayout, this.mBtnSelect);
    }

    public void onEventMainThread(DeleteDeviceEvent deleteDeviceEvent) {
        if (deleteDeviceEvent.mDeviceId != 0) {
            new Thread(new Runnable() {
                public void run() {
                    LiveViewUi.this.stopPlayVideo();
                }
            }).start();
        }
    }

    public void setSurfaceListener(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.mVideoGroup.getVideoView(i2).setCallBack(this, i2);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3, int i4) {
        Log.e("surfaceChanged", "" + i4);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder, int i) {
        Log.e("surfaceCreated", "" + i);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder, int i) {
        Log.e("surfaceDestroyed", "" + i);
    }

    public void setSurfaceVisibility() {
        int initViewCount = this.mVideoGroup.getInitViewCount();
        int curViewCount = this.mVideoGroup.getCurViewCount();
        int firstIndex = this.mVideoGroup.getFirstIndex();
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
            this.mVideoGroup.post(new Runnable() {
                public void run() {
                    LiveViewUi.this.mVideoGroup.getVideoView(i).setVisibility(0);
                }
            });
        } else {
            this.mVideoGroup.post(new Runnable() {
                public void run() {
                    LiveViewUi.this.mVideoGroup.getVideoView(i).setVisibility(8);
                }
            });
        }
    }
}
