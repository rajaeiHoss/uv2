package com.streamax.client;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.dlong.rep.dlroundmenuview.DLRoundMenuView;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuClickListener;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuTouchListener;
import com.dvr.avstream.AVStream;
import com.dvr.avstream.AuTrack;
import com.dvr.avstream.MyCallInterface;
import com.dvr.net.DvrNet;
import com.hjq.toast.ToastUtils;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.utils.AppProxy;
import com.streamax.utils.StringUtils;
import com.streamax.view.VsEditView;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.Map;
import org.xbill.DNS.SimpleResolver;

public class CalibrationActivity extends Activity implements MyCallInterface {
    public static int MENU_ACTION_DOWN = 2;
    public static int MENU_ACTION_LEFT = 3;
    public static int MENU_ACTION_RIGHT = 1;
    public static int MENU_ACTION_UP = 0;
    public static final String TAG = "CalibrationActivity";
    public static Object lock = new Object();
    private DLRoundMenuView adasCtrl;
    /* access modifiers changed from: private */
    public AVStream mAVStream = new AVStream();
    /* access modifiers changed from: private */
    public VsEditView mAdasCamToVehicleHeadTv;
    /* access modifiers changed from: private */
    public VsEditView mAdasCameraHeightTv;
    /* access modifiers changed from: private */
    public int mAdasHorizon = 0;
    private VsTextView mAdasHorizonTv;
    /* access modifiers changed from: private */
    public VsEditView mAdasVehicleWidthTv;
    /* access modifiers changed from: private */
    public int mAdasVertical = 0;
    private VsTextView mAdasVerticalTv;
    public MyApp mApp;
    public AuTrack mAudioTrack = new AuTrack();
    private Button mBtnSave;
    /* access modifiers changed from: private */
    public Button mBtnSelect;
    /* access modifiers changed from: private */
    public int mChannel = -1;
    /* access modifiers changed from: private */
    public DvrNet mDvrNet = null;
    public Handler mHandler = AppProxy.getHandler();
    public LayoutInflater mInflater;
    public MyCallInterface mInterface;
    public int mMenuHeight = 100;
    public View mRootView;
    public TextView mTitleTv;
    /* access modifiers changed from: private */
    public VideoGroup mVideoGroup;
    private int mVideoHeight = 720;
    private int mVideoWidth = SimpleResolver.DEFAULT_EDNS_PAYLOADSIZE;
    private Boolean mbAdas = true;
    public PopupWindow popChannel;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mApp = (MyApp) getApplication();
        this.mInterface = this;
        LayoutInflater from = LayoutInflater.from(this);
        this.mInflater = from;
        View inflate = from.inflate(R.layout.calibration, (ViewGroup) null);
        this.mRootView = inflate;
        setContentView(inflate);
        this.mTitleTv = (TextView) this.mRootView.findViewById(R.id.ai_cal_title_text);
        Button button = (Button) this.mRootView.findViewById(R.id.ai_cal_title_button_select);
        this.mBtnSelect = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LinearLayout linearLayout = (LinearLayout) CalibrationActivity.this.mInflater.inflate(R.layout.popupwindow_channel, (ViewGroup) null);
                ChannelSelect channelSelect = (ChannelSelect) linearLayout.findViewById(R.id.device_select_channel_view);
                channelSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        TextView textView = (TextView) view;
                        CalibrationActivity.this.startPlay(StringUtils.getString(textView), ((Integer) textView.getTag()).intValue());
                        CalibrationActivity.this.popChannel.dismiss();
                        CalibrationActivity.this.popChannel = null;
                    }
                });
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < CalibrationActivity.this.mApp.mDevInfo.mChCounts; i++) {
                    arrayList.add(Integer.valueOf(i));
                }
                channelSelect.setMenuHeight(CalibrationActivity.this.mMenuHeight);
                channelSelect.SetData(arrayList);
                CalibrationActivity calibrationActivity = CalibrationActivity.this;
                calibrationActivity.popMenu(linearLayout, calibrationActivity.mBtnSelect);
            }
        });
        VideoGroup videoGroup = (VideoGroup) this.mRootView.findViewById(R.id.preview_videogroup);
        this.mVideoGroup = videoGroup;
        if (videoGroup != null) {
            videoGroup.LoadViews();
            this.mVideoGroup.SetInitMode(1);
        }
        this.mVideoGroup.setFocusState(0, false);
        this.mVideoGroup.setOsdState(0, false);
        this.mVideoGroup.setVideoInfo(0, this.mVideoWidth, this.mVideoHeight);
        this.mVideoGroup.setAdas(0, this.mbAdas.booleanValue());
        DLRoundMenuView dLRoundMenuView = (DLRoundMenuView) this.mRootView.findViewById(R.id.dl_rmv);
        this.adasCtrl = dLRoundMenuView;
        dLRoundMenuView.setIsDrawLineToCenter(true);
        this.adasCtrl.setCoreMenuRoundRadius(100.0f);
        this.adasCtrl.setCoreMenuStrokeSize(20.0f);
        this.adasCtrl.setCoreMenuStrokeColor(-657931);
        this.adasCtrl.setCoreMenuSelectedBackgroundColor(-26624);
        this.adasCtrl.setRoundMenuStrokeSize(20.0f);
        this.adasCtrl.setRoundMenuStrokeColor(-657931);
        this.adasCtrl.setRoundMenuSelectedBackgroundColor(-26624);
        this.adasCtrl.setOnMenuClickListener(new OnMenuClickListener() {
            public final void OnMenuClick(int i) {
                CalibrationActivity.this.lambda$onCreate$0$CalibrationActivity(i);
            }
        });
        this.adasCtrl.setOnMenuTouchListener(new OnMenuTouchListener() {
            public final void OnTouch(MotionEvent motionEvent, int i) {
                CalibrationActivity.this.lambda$onCreate$1$CalibrationActivity(motionEvent, i);
            }
        });
        this.mAdasHorizonTv = (VsTextView) this.mRootView.findViewById(R.id.adas_cal_vehicle_horizon);
        this.mAdasVerticalTv = (VsTextView) this.mRootView.findViewById(R.id.adas_cal_vehicle_vertical);
        this.mAdasHorizon = this.mVideoHeight / 2;
        this.mAdasVertical = this.mVideoWidth / 2;
        setAdasCalText(true, true);
        this.mAdasCameraHeightTv = (VsEditView) this.mRootView.findViewById(R.id.adas_cal_camera_height);
        this.mAdasVehicleWidthTv = (VsEditView) this.mRootView.findViewById(R.id.adas_cal_vehicle_width);
        this.mAdasCamToVehicleHeadTv = (VsEditView) this.mRootView.findViewById(R.id.adas_cal_cam_to_vehicle_head);
        Button button2 = (Button) this.mRootView.findViewById(R.id.ai_cal_button_save);
        this.mBtnSave = button2;
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int access$200;
                synchronized (CalibrationActivity.lock) {
                    access$200 = CalibrationActivity.this.mChannel;
                }
                if (-1 == access$200) {
                    CalibrationActivity.this.toastSf(R.string.ai_cal_set_adas_channel_invalid);
                } else {
                    CalibrationActivity.this.saveAdasCali(access$200);
                }
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$CalibrationActivity(int i) {
        if (i >= 0) {
            moveAdasCalLine(i);
        }
    }

    public /* synthetic */ void lambda$onCreate$1$CalibrationActivity(MotionEvent motionEvent, int i) {
        if (i >= 0) {
            moveAdasCalLine(i);
        }
    }

    public void popMenu(View view, View view2) {
        new DisplayMetrics();
        int i = (getResources().getDisplayMetrics().widthPixels / 3) + 40;
        int i2 = this.mApp.mDevInfo.mChCounts * this.mMenuHeight;
        PopupWindow popupWindow = this.popChannel;
        if (popupWindow == null) {
            PopupWindow popupWindow2 = new PopupWindow(view, i, i2, true);
            this.popChannel = popupWindow2;
            popupWindow2.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
            this.popChannel.setTouchInterceptor(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 4) {
                        return false;
                    }
                    CalibrationActivity.this.popChannel.dismiss();
                    return false;
                }
            });
            this.popChannel.setOutsideTouchable(true);
            this.popChannel.showAsDropDown(view2, 1, 0);
            this.popChannel.update();
        } else if (popupWindow.isShowing()) {
            this.popChannel.dismiss();
            this.popChannel = null;
        } else {
            this.popChannel = null;
            PopupWindow popupWindow3 = new PopupWindow(view, i, i2, true);
            this.popChannel = popupWindow3;
            popupWindow3.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
            this.popChannel.setOutsideTouchable(false);
            this.popChannel.showAsDropDown(view2, 1, 0);
            this.popChannel.update();
        }
    }

    public void fuc(int i, int i2, byte[] bArr, int i3, int i4, int i5) {
        VideoView videoView = this.mVideoGroup.getVideoView(0);
        if (videoView != null) {
            videoView.writeIn(bArr, i4, i5);
        }
    }

    /* access modifiers changed from: private */
    public void stopChannel(int i) {
        DvrNet dvrNet = this.mDvrNet;
        if (dvrNet != null) {
            dvrNet.StopRealAv(i);
            this.mDvrNet.CloseDeviceHandle();
            AVStream aVStream = this.mAVStream;
            if (aVStream != null) {
                aVStream.StopPlay();
                this.mAVStream.CloseHandle();
                this.mAVStream = null;
            }
            this.mDvrNet = null;
            this.mAudioTrack.mPlayer.Stop();
        }
    }

    /* access modifiers changed from: private */
    public void startPlay(final String str, final int i) {
        new Thread(new Runnable() {
            public void run() {
                synchronized (CalibrationActivity.lock) {
                    int i = -1;
                    if (-1 != CalibrationActivity.this.mChannel) {
                        CalibrationActivity calibrationActivity = CalibrationActivity.this;
                        calibrationActivity.stopChannel(calibrationActivity.mChannel);
                    }
                    if (CalibrationActivity.this.mDvrNet == null) {
                        DvrNet dvrNet = new DvrNet();
                        Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(dvrNet, CalibrationActivity.this.mApp.mDevInfo, CalibrationActivity.this.mApp);
                        if (connDeviceProxy != null) {
                            i = ((Integer) connDeviceProxy.get("errorcode")).intValue();
                        }
                        if (i != 0) {
                            CalibrationActivity.this.mHandler.post(new Runnable() {
                                public void run() {
                                    CalibrationActivity.this.toastSf(R.string.ai_cal_get_adas_config_fail);
                                }
                            });
                            return;
                        }
                        DvrNet unused = CalibrationActivity.this.mDvrNet = dvrNet;
                    }
                    CalibrationActivity.this.mAudioTrack.mPlayer.play();
                    CalibrationActivity.this.mAudioTrack.SetMute(false);
                    AVStream unused2 = CalibrationActivity.this.mAVStream = new AVStream();
                    CalibrationActivity.this.mAVStream.GetHandle(i);
                    CalibrationActivity.this.mAVStream.SetMute(false);
                    CalibrationActivity.this.mAVStream.StartPlay();
                    CalibrationActivity.this.mAVStream.SetVideoInterface(CalibrationActivity.this.mInterface);
                    CalibrationActivity.this.mAVStream.SetAudioInterface(CalibrationActivity.this.mAudioTrack);
                    CalibrationActivity.this.mDvrNet.SetAVStream(i, CalibrationActivity.this.mAVStream);
                    CalibrationActivity.this.mDvrNet.StartRealAv(i, 0);
                    int unused3 = CalibrationActivity.this.mChannel = i;
                    CalibrationActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            CalibrationActivity.this.mTitleTv.setText(str);
                        }
                    });
                    CalibrationActivity.this.getAdasCali(i);
                }
            }
        }).start();
    }

    /* access modifiers changed from: protected */
    public void toastSf(int i) {
        ToastUtils.show((CharSequence) StringUtils.getString(Integer.valueOf(i)));
    }

    /* access modifiers changed from: private */
    public void getAdasCali(final int i) {
        new Thread(new Runnable() {
            public void run() {
                DvrNet dvrNet = new DvrNet();
                Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(dvrNet, CalibrationActivity.this.mApp.mDevInfo, CalibrationActivity.this.mApp);
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                int[] iArr3 = new int[1];
                int[] iArr4 = new int[1];
                int[] iArr5 = new int[1];
                int result;
                if ((connDeviceProxy != null ? ((Integer) connDeviceProxy.get("errorcode")).intValue() : -1) == 0) {
                    int GetAdasCali = dvrNet.GetAdasCali(i, iArr, iArr2, iArr3, iArr4, iArr5);
                    dvrNet.CloseDeviceHandle();
                    result = GetAdasCali;
                } else {
                    result = -1;
                }
                final int resultFinal = result;
                final int[] iArr6 = iArr;
                final int[] iArr7 = iArr2;
                final int[] iArr8 = iArr3;
                final int[] iArr9 = iArr4;
                final int[] iArr10 = iArr5;
                CalibrationActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (resultFinal == 0) {
                            int unused = CalibrationActivity.this.mAdasHorizon = iArr6[0];
                            int unused2 = CalibrationActivity.this.mAdasVertical = iArr7[0];
                            CalibrationActivity.this.setAdasCalText(true, true);
                            VsEditView access$1100 = CalibrationActivity.this.mAdasCameraHeightTv;
                            access$1100.setText("" + iArr8[0]);
                            VsEditView access$1200 = CalibrationActivity.this.mAdasVehicleWidthTv;
                            access$1200.setText("" + iArr9[0]);
                            VsEditView access$1300 = CalibrationActivity.this.mAdasCamToVehicleHeadTv;
                            access$1300.setText("" + iArr10[0]);
                            CalibrationActivity.this.toastSf(R.string.ai_cal_get_adas_config_success);
                            return;
                        }
                        CalibrationActivity.this.toastSf(R.string.ai_cal_get_adas_config_fail);
                    }
                });
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void saveAdasCali(int i) {
        final int i2 = this.mAdasHorizon;
        final int i3 = this.mAdasVertical;
        final int parseInt = Integer.parseInt(StringUtils.getString(this.mAdasCameraHeightTv));
        final int parseInt2 = Integer.parseInt(StringUtils.getString(this.mAdasVehicleWidthTv));
        final int parseInt3 = Integer.parseInt(StringUtils.getString(this.mAdasCamToVehicleHeadTv));
        if (parseInt < 0 || parseInt > 500) {
            toastSf(R.string.ai_cal_set_adas_camera_height_range_error);
        } else if (parseInt2 < 0 || parseInt2 > 255) {
            toastSf(R.string.ai_cal_set_adas_vehicle_width_range_error);
        } else if (parseInt3 < -255 || parseInt3 > 255) {
            toastSf(R.string.ai_cal_set_adas_cam_to_vehicle_head_range_error);
        } else {
            final int i4 = i;
            new Thread(new Runnable() {
                public void run() {
                    DvrNet dvrNet = new DvrNet();
                    Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(dvrNet, CalibrationActivity.this.mApp.mDevInfo, CalibrationActivity.this.mApp);
                    final int[] result = new int[]{-1};
                    if ((connDeviceProxy != null ? ((Integer) connDeviceProxy.get("errorcode")).intValue() : -1) == 0) {
                        result[0] = CalibrationActivity.this.mDvrNet.SetAdasCali(i4, i2, i3, parseInt, parseInt2, parseInt3);
                        dvrNet.CloseDeviceHandle();
                    }
                    CalibrationActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            if (result[0] == 0) {
                                CalibrationActivity.this.toastSf(R.string.ai_cal_set_adas_config_success);
                            } else {
                                CalibrationActivity.this.toastSf(R.string.ai_cal_set_adas_config_fail);
                            }
                        }
                    });
                }
            }).start();
        }
    }

    /* access modifiers changed from: private */
    public void setAdasCalText(boolean z, boolean z2) {
        if (z) {
            VsTextView vsTextView = this.mAdasHorizonTv;
            vsTextView.setText("" + this.mAdasHorizon);
            this.mVideoGroup.setAdasHorizon(0, this.mAdasHorizon);
        }
        if (z2) {
            VsTextView vsTextView2 = this.mAdasVerticalTv;
            vsTextView2.setText("" + this.mAdasVertical);
            this.mVideoGroup.setAdasVertical(0, this.mAdasVertical);
        }
    }

    private boolean checkAdasHorizon(int i) {
        int i2 = this.mVideoHeight;
        if (i2 <= 0) {
            return false;
        }
        int i3 = this.mAdasHorizon;
        if (MENU_ACTION_UP == i) {
            i3--;
        } else if (MENU_ACTION_DOWN == i) {
            i3++;
        }
        float f = (((float) i2) * 2.0f) / 3.0f;
        float f2 = (float) i3;
        if (f2 < ((float) i2) / 3.0f || f2 > f) {
            return false;
        }
        return true;
    }

    private boolean checkAdasVertical(int i) {
        int i2 = this.mVideoWidth;
        if (i2 <= 0) {
            return false;
        }
        int i3 = this.mAdasVertical;
        if (MENU_ACTION_LEFT == i) {
            i3--;
        } else if (MENU_ACTION_RIGHT == i) {
            i3++;
        }
        float f = (((float) i2) * 3.0f) / 4.0f;
        float f2 = (float) i3;
        if (f2 < ((float) i2) / 4.0f || f2 > f) {
            return false;
        }
        return true;
    }

    private void moveAdasCalLine(int i) {
        boolean z = false;
        boolean z2 = true;
        if (MENU_ACTION_UP == i) {
            if (checkAdasHorizon(i)) {
                this.mAdasHorizon--;
            } else {
                return;
            }
        } else if (MENU_ACTION_DOWN != i) {
            if (MENU_ACTION_LEFT != i) {
                if (MENU_ACTION_RIGHT == i) {
                    if (checkAdasVertical(i)) {
                        this.mAdasVertical++;
                    } else {
                        return;
                    }
                }
                z2 = false;
            } else if (checkAdasVertical(i)) {
                this.mAdasVertical--;
            } else {
                return;
            }
            setAdasCalText(z, z2);
        } else if (checkAdasHorizon(i)) {
            this.mAdasHorizon++;
        } else {
            return;
        }
        z = true;
        z2 = false;
        setAdasCalText(z, z2);
    }

    private void stopPlay() {
        new Thread(new Runnable() {
            public void run() {
                synchronized (CalibrationActivity.lock) {
                    if (-1 != CalibrationActivity.this.mChannel) {
                        CalibrationActivity calibrationActivity = CalibrationActivity.this;
                        calibrationActivity.stopChannel(calibrationActivity.mChannel);
                        int unused = CalibrationActivity.this.mChannel = -1;
                    }
                }
                CalibrationActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        CalibrationActivity.this.mTitleTv.setText("");
                        CalibrationActivity.this.mVideoGroup.ClearViews();
                    }
                });
            }
        }).start();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        stopPlay();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        stopPlay();
        super.onDestroy();
    }
}
