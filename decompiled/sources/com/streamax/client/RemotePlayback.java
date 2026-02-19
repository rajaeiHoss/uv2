package com.streamax.client;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import com.dvr.avstream.AuTrack;
import com.dvr.calendar.CalendarView;
import com.dvr.calendar.UpdateCalendarInterface;
import com.dvr.net.BitmapFileInfo;
import com.dvr.net.CalendarData;
import com.dvr.net.DvrNet;
import com.streamax.Configs;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.utils.AppProxy;
import com.streamax.utils.LogUtils;
import com.zycs.UView.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Remote playback UI: calendar search, file list, and playback controls.
public class RemotePlayback extends LinearLayout implements UpdateCalendarInterface, remoteplayinterface {
    public MyApp mApp;
    public AuTrack mAudioTrack;
    public Button mBtnSelect;
    public View mBusyView;
    public CalendarRunnable mCalendarRunnable;
    public CalendarView mCalendarView;
    public ConnectRunnable mConnect;
    public Context mContext;
    public int mDay;
    public DevInfoBean mDevInfo;
    public DvrNet mDvrNet;
    /* access modifiers changed from: private */
    public FrameLayout mFileLayout;
    public RemoteFileList mFileList;
    public Handler mHandler;
    public LayoutInflater mInflater;
    public int mMonth;
    public int mMultiplayChannelArray;
    public String mMultiplayTime;
    public View.OnClickListener mOnClickListener;
    public PlaybackActivity mPlaybackActivity;
    public PopupWindow mPopupCapture;
    public ProgressDialog mProgressDialog;
    public RemotePlayback mRemotePlayback;
    public SearchFileListRunnable mSearchFileList;
    public SeekBar mSeekBar;
    public int mSpeed;
    public int mTimeValues;
    public TextView mTitle;
    public boolean mTracking;
    public UpdateCalendarRunnable mUpdateCalendar;
    public int mYear;
    public boolean mbMute;
    List<Map<String, Object>> mbmpList;
    /* access modifiers changed from: private */
    public DbHelper mdbHelper;
    public View mpopViewer;
    public TextView mtvSpeed;
    public TextView mtvTime;
    public PopupWindow pop;

    public RemotePlayback(Context context) {
        this(context, (AttributeSet) null);
    }

    public RemotePlayback(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTracking = false;
        this.mAudioTrack = new AuTrack();
        this.mbMute = false;
        this.mSpeed = 1;
        this.mContext = context;
        this.mdbHelper = new DbHelper(context, DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.mInflater = LayoutInflater.from(context);
        this.mRemotePlayback = this;
        this.mHandler = AppProxy.getHandler();
    }

    public void SetActivity(PlaybackActivity playbackActivity) {
        this.mPlaybackActivity = playbackActivity;
        this.mApp = (MyApp) playbackActivity.getApplication();
    }

    public void showProgressDialog() {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (RemotePlayback.this.mProgressDialog == null) {
                    RemotePlayback.this.mProgressDialog = new ProgressDialog(RemotePlayback.this.mPlaybackActivity);
                }
                RemotePlayback.this.mProgressDialog.setTitle(R.string.dialog_title_Loading);
                RemotePlayback.this.mProgressDialog.show();
                RemotePlayback.this.mProgressDialog.setCanceledOnTouchOutside(false);
            }
        });
    }

    public void hideProgressDialog() {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (RemotePlayback.this.mProgressDialog != null) {
                    RemotePlayback.this.mProgressDialog.hide();
                    RemotePlayback.this.mProgressDialog = null;
                }
            }
        });
    }

    public void FindViews() {
        this.mFileList = (RemoteFileList) findViewById(R.id.remotefilelist);
        this.mFileLayout = (FrameLayout) findViewById(R.id.remotefile);
        this.mFileList.setRemotePlayback(this);
        this.mFileList.SetRemoteplayInterface(this);
        CalendarView calendarView = (CalendarView) this.mFileList.GetCalendarView();
        this.mCalendarView = calendarView;
        calendarView.SetUpdateCalendarInterface(this);
        this.mBtnSelect = (Button) findViewById(R.id.remote_playback_title_button_select);
        this.mTitle = (TextView) findViewById(R.id.remote_playback_title_text);
        this.mBusyView = findViewById(R.id.remote_busy);
        this.mBtnSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LinearLayout linearLayout = (LinearLayout) RemotePlayback.this.mInflater.inflate(R.layout.popupwindow_device, (ViewGroup) null);
                DeviceSelect deviceSelect = (DeviceSelect) linearLayout.findViewById(R.id.device_select_device_view);
                deviceSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        RemotePlayback.this.OpenDevice(((Integer) ((TextView) view).getTag()).intValue());
                        RemotePlayback.this.pop.dismiss();
                        RemotePlayback.this.pop = null;
                        RemotePlayback.this.mFileLayout.setVisibility(0);
                    }
                });
                if (MyApp.loginType == 0) {
                    deviceSelect.SetData(RemotePlayback.this.mdbHelper.getlist(), (List<String>) null);
                } else {
                    deviceSelect.SetData(RemotePlayback.this.mApp.mWebService.GetTerminalInfoAndroid(true), (List<String>) null);
                }
                RemotePlayback remotePlayback = RemotePlayback.this;
                remotePlayback.popMenu(linearLayout, remotePlayback.mBtnSelect);
            }
        });
        this.mFileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = i - 1;
                String str = RemotePlayback.this.mFileList.mRemoteFileList.get(i2).FileTime;
                int i3 = RemotePlayback.this.mFileList.mRemoteFileList.get(i2).nChannel;
                Intent intent = new Intent(RemotePlayback.this.mContext, RemotePlaybackActivity.class);
                intent.putExtra("year", str.substring(0, 4));
                intent.putExtra("month", str.substring(4, 6));
                intent.putExtra("day", str.substring(6, 8));
                intent.putExtra("hour", str.substring(8, 10));
                intent.putExtra("minute", str.substring(10, 12));
                intent.putExtra("second", str.substring(12, 14));
                intent.putExtra("channel", i3);
                RemotePlayback.this.mContext.startActivity(intent);
            }
        });
    }

    public void popImageViewer(List<Map<String, Object>> list) {
        List<Map<String, Object>> list2 = list;
        if (list.size() != 0) {
            this.mbmpList = list2;
            int size = list.size();
            new BitmapFactory();
            this.mpopViewer = this.mInflater.inflate(R.layout.captureimageviewer, (ViewGroup) null);
            LinearLayout linearLayout = new LinearLayout(this.mContext);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            linearLayout.setBackgroundColor(Color.argb(200, 40, 40, 40));
            int i = 0;
            linearLayout.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.weight = 1.0f;
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
                        linearLayout2.addView(imageView, layoutParams);
                        i3++;
                    }
                    linearLayout.addView(linearLayout2, layoutParams);
                    i2++;
                    i = 0;
                } else {
                    ((LinearLayout) this.mpopViewer.findViewById(R.id.preview_capture_imagegroup)).addView(linearLayout);
                    this.mPopupCapture = null;
                    PopupWindow popupWindow = new PopupWindow(this.mpopViewer, -1, -1, true);
                    this.mPopupCapture = popupWindow;
                    popupWindow.setOutsideTouchable(false);
                    this.mPopupCapture.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
                    this.mPopupCapture.showAtLocation(this, 17, 0, 0);
                    this.mPopupCapture.update();
                    this.mpopViewer.findViewById(R.id.preview_capture_save).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            RemotePlayback.this.mPopupCapture.dismiss();
                        }
                    });
                    this.mpopViewer.findViewById(R.id.preview_capture_cancel).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            for (int i = 0; i < RemotePlayback.this.mbmpList.size(); i++) {
                                String obj = RemotePlayback.this.mbmpList.get(i).get("path").toString();
                                if (obj != null) {
                                    new File(obj).delete();
                                }
                            }
                            RemotePlayback.this.mPopupCapture.dismiss();
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
                    RemotePlayback.this.pop.dismiss();
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

    public void OpenDevice(int i) {
        DevInfoBean devInfoBean;
        showProgressDialog();
        if (this.mdbHelper == null) {
            hideProgressDialog();
            return;
        }
        if (MyApp.loginType == 0) {
            devInfoBean = this.mdbHelper.query(i);
        } else {
            devInfoBean = this.mApp.mWebService.query(i);
        }
        if (devInfoBean == null) {
            hideProgressDialog();
            return;
        }
        if (this.mUpdateCalendar == null) {
            this.mUpdateCalendar = new UpdateCalendarRunnable();
        }
        this.mFileList.mRemoteFileInfo = null;
        this.mFileList.mRemoteFileList.clear();
        this.mFileList.post(new UpdateFileListRunable());
        this.mCalendarView.mRecordTimeInfo.init();
        this.mCalendarView.post(this.mUpdateCalendar);
        this.mDevInfo = devInfoBean;
        this.mConnect = new ConnectRunnable(devInfoBean);
        new Thread(this.mConnect).start();
    }

    public class ConnectRunnable implements Runnable {
        public DevInfoBean info;

        ConnectRunnable(DevInfoBean devInfoBean) {
            this.info = devInfoBean;
        }

        public void run() {
            DevInfoBean devInfoBean = this.info;
            if (devInfoBean != null) {
                devInfoBean.PrintOut();
                RemotePlayback.this.OpenDevice(this.info);
            }
        }
    }

    public class CalendarRunnable implements Runnable {
        public CalendarRunnable() {
        }

        public void run() {
            if (RemotePlayback.this.mDvrNet != null) {
                RemotePlayback remotePlayback = RemotePlayback.this;
                remotePlayback.mYear = remotePlayback.mCalendarView.GetCurrentYear();
                RemotePlayback remotePlayback2 = RemotePlayback.this;
                remotePlayback2.mMonth = remotePlayback2.mCalendarView.GetCurrentMonth();
                CalendarData[] SearchMonth = RemotePlayback.this.mDvrNet.SearchMonth(RemotePlayback.this.mYear, RemotePlayback.this.mMonth, -1, 1, 3);
                if (SearchMonth == null || SearchMonth.length == 0) {
                    RemotePlayback.this.hideProgressDialog();
                    RemotePlayback.this.mHandler.post(new Runnable() {
                        public void run() {
                            new AlertDialog.Builder(RemotePlayback.this.mContext).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.norecord).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).show();
                        }
                    });
                    return;
                }
                int i = 0;
                for (CalendarData calendarData : SearchMonth) {
                    i |= 1 << (calendarData.nDay - 1);
                }
                RemotePlayback.this.mCalendarView.mRecordTimeInfo.nYear = RemotePlayback.this.mYear;
                RemotePlayback.this.mCalendarView.mRecordTimeInfo.nMonth = RemotePlayback.this.mMonth;
                RemotePlayback.this.mCalendarView.mRecordTimeInfo.nDayBits = i;
                if (RemotePlayback.this.mUpdateCalendar == null) {
                    RemotePlayback remotePlayback3 = RemotePlayback.this;
                    remotePlayback3.mUpdateCalendar = new UpdateCalendarRunnable();
                }
                RemotePlayback.this.mCalendarView.post(RemotePlayback.this.mUpdateCalendar);
                RemotePlayback.this.hideProgressDialog();
            }
        }
    }

    public class UpdateCalendarRunnable implements Runnable {
        public UpdateCalendarRunnable() {
        }

        public void run() {
            RemotePlayback.this.mCalendarView.updateCalendar();
        }
    }

    public class UpdateFileListRunable implements Runnable {
        public UpdateFileListRunable() {
        }

        public void run() {
            RemotePlayback.this.mFileList.updateAdapter();
        }
    }

    public class SearchFileListRunnable implements Runnable {
        public SearchFileListRunnable() {
        }

        public void run() {
            if (RemotePlayback.this.mDvrNet == null) {
                new Thread(new Runnable() {
                    public void run() {
                        RemotePlayback.this.mHandler.post(new Runnable() {
                            public void run() {
                                new AlertDialog.Builder(RemotePlayback.this.mContext).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.pleaseselectdevice).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                }).show();
                            }
                        });
                    }
                }).start();
                return;
            }
            RemotePlayback.this.mBusyView.post(new Runnable() {
                public void run() {
                    RemotePlayback.this.mBusyView.setVisibility(0);
                }
            });
            String format = String.format("%04d%02d%02d000000", new Object[]{Integer.valueOf(RemotePlayback.this.mYear), Integer.valueOf(RemotePlayback.this.mMonth), Integer.valueOf(RemotePlayback.this.mDay)});
            String format2 = String.format("%04d%02d%02d235959", new Object[]{Integer.valueOf(RemotePlayback.this.mYear), Integer.valueOf(RemotePlayback.this.mMonth), Integer.valueOf(RemotePlayback.this.mDay)});
            RemotePlayback.this.mFileList.mRemoteFileInfo = RemotePlayback.this.mDvrNet.SearchVideoFileList(0, 3, 1, -1, format, format2);
            RemotePlayback.this.mBusyView.post(new Runnable() {
                public void run() {
                    RemotePlayback.this.mBusyView.setVisibility(4);
                }
            });
            if (RemotePlayback.this.mFileList.mRemoteFileInfo != null && RemotePlayback.this.mFileList.mRemoteFileInfo.length != 0) {
                LogUtils.log(getClass(), "myUpdateFileListRunable->1");
                RemotePlayback.this.mFileList.post(new UpdateFileListRunable());
            }
        }
    }

    public void Exit() {
        this.mFileLayout.setVisibility(8);
        DvrNet dvrNet = this.mDvrNet;
        if (dvrNet != null) {
            dvrNet.CloseDeviceHandle();
            this.mDvrNet = null;
        }
        this.mFileList.mRemoteFileInfo = null;
        this.mFileList.mRemoteFileList.clear();
        this.mTitle.setText("");
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

    public void OpenDevice(DevInfoBean devInfoBean) {
        DvrNet dvrNet = this.mDvrNet;
        if (dvrNet != null) {
            dvrNet.CloseDeviceHandle();
            this.mDvrNet = null;
        }
        DvrNet dvrNet2 = new DvrNet();
        this.mDvrNet = dvrNet2;
        int i = -1;
        Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(dvrNet2, this.mDevInfo, this.mApp);
        if (connDeviceProxy != null) {
            i = ((Integer) connDeviceProxy.get("errorcode")).intValue();
        }
        if (i != 0) {
            this.mFileList.mRemoteFileInfo = null;
            this.mFileList.mRemoteFileList.clear();
            this.mFileList.post(new UpdateFileListRunable());
            hideProgressDialog();
            return;
        }
        MyApp.dvrNet = this.mDvrNet;
        this.mTitle.post(new Runnable() {
            public void run() {
                RemotePlayback.this.mTitle.setText(RemotePlayback.this.mDevInfo.mDevName);
            }
        });
        if (this.mCalendarRunnable == null) {
            this.mCalendarRunnable = new CalendarRunnable();
        }
        new Thread(this.mCalendarRunnable).start();
    }

    public void UpdateCalendar(int i, int i2) {
        this.mYear = i;
        this.mMonth = i2;
        this.mFileList.mRemoteFileInfo = null;
        this.mFileList.mRemoteFileList.clear();
        if (this.mCalendarRunnable == null) {
            this.mCalendarRunnable = new CalendarRunnable();
        }
        new Thread(this.mCalendarRunnable).start();
        this.mFileList.post(new Runnable() {
            public void run() {
                RemotePlayback.this.mFileList.mRemoteFileInfo = null;
                RemotePlayback.this.mFileList.updateAdapter();
            }
        });
    }

    public void SearchDay(int i, int i2, int i3) {
        if (this.mBusyView.getVisibility() != 0) {
            this.mYear = i;
            this.mMonth = i2;
            this.mDay = i3;
            this.mFileList.SetHeader(String.format("%04d-%02d-%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay)}));
            if (this.mSearchFileList == null) {
                this.mSearchFileList = new SearchFileListRunnable();
            }
            new Thread(this.mSearchFileList).start();
        }
    }

    public void Seek(int i, int i2) {
        if (this.mDvrNet != null) {
            this.mDvrNet.MultiPlaySeek(String.format("%04d%02d%02d%02d%02d00", new Object[]{Integer.valueOf(this.mYear), Integer.valueOf(this.mMonth), Integer.valueOf(this.mDay), Integer.valueOf(i2 / 60), Integer.valueOf(i2 % 60)}));
        }
    }

    public void startRemotePlay(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        Intent intent = new Intent(this.mContext, RemotePlaybackActivity.class);
        intent.putExtra("year", Integer.valueOf(i).toString());
        intent.putExtra("month", Integer.valueOf(i2).toString());
        intent.putExtra("day", Integer.valueOf(i3).toString());
        intent.putExtra("hour", Integer.valueOf(i4).toString());
        intent.putExtra("minute", Integer.valueOf(i5).toString());
        intent.putExtra("second", Integer.valueOf(i6).toString());
        intent.putExtra("channel", i7);
        this.mContext.startActivity(intent);
    }
}
