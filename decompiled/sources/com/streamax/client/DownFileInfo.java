package com.streamax.client;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.os.Handler;
import com.dvr.net.DownVideoInterface;
import com.dvr.net.DvrNet;
import com.dvr.net.RemoteFileInfo;
import com.hjq.toast.ToastUtils;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.utils.StringUtils;
import com.zycs.UView.R;
import java.util.Map;

public class DownFileInfo extends RemoteFileInfo implements DownVideoInterface {
    public DownFileInterface df;
    public Activity mActivity;
    public MyApp mApp;
    public int mChannel;
    public int mCur = 0;
    public int mDay;
    public DownStartRunnable mDownStartRunnable = null;
    public DownStopRunnable mDownStopRunnable = null;
    public String mDstFile;
    public DvrNet mDvrNet = null;
    public String mEndTime;
    public Handler mHandler;
    public int mHour;
    public boolean mIsDown = false;
    public int mMinute;
    public int mMonth;
    public int mSecond;
    public String mStartTime;
    public String mSubTitle;
    public String mTitle;
    public String mTmpFile;
    public int mTotal = 0;
    public int mYear;

    public void SetDownFileInterface(DownFileInterface downFileInterface) {
        this.df = downFileInterface;
    }

    public void DownVideoCallback(long j, int i, final int i2, final int i3) {
        this.mHandler.post(new Runnable() {
            public void run() {
                DownFileInfo.this.mCur = i3;
                DownFileInfo.this.mTotal = i2;
                if (DownFileInfo.this.mCur > 0 && DownFileInfo.this.mCur >= DownFileInfo.this.mTotal) {
                    DownFileInfo.this.mIsDown = true;
                    DownFileInfo.this.stopDown(false);
                }
                DownFileInfo.this.df.DownFileCallback(0, "");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void toastSf(int i) {
        ToastUtils.show((CharSequence) StringUtils.getString(Integer.valueOf(i)));
    }

    /* access modifiers changed from: protected */
    public void toastSf(String str) {
        ToastUtils.show((CharSequence) str);
    }

    private boolean checkDownRunnable() {
        if (this.mDownStartRunnable != null) {
            toastSf((int) R.string.is_down_start);
            return false;
        } else if (this.mDownStopRunnable == null) {
            return true;
        } else {
            toastSf((int) R.string.is_down_stop);
            return false;
        }
    }

    public class DownStartRunnable implements Runnable {
        DownStartRunnable() {
        }

        public void run() {
            if (DownFileInfo.this.mDvrNet == null) {
                DvrNet dvrNet = new DvrNet();
                Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(dvrNet, DownFileInfo.this.mApp.mDevInfo, DownFileInfo.this.mApp);
                int i = -1;
                if (connDeviceProxy != null) {
                    i = ((Integer) connDeviceProxy.get("errorcode")).intValue();
                }
                if (i != 0) {
                    DownFileInfo.this.mHandler.post(new Runnable() {
                        public void run() {
                            DownFileInfo.this.mDownStartRunnable = null;
                        }
                    });
                    return;
                }
                DownFileInfo.this.mDvrNet = dvrNet;
            }
            if (DownFileInfo.this.mDvrNet != null) {
                DownFileInfo.this.mDvrNet.SetDownVideoInterface(DownFileInfo.this);
                String path = Environment.getExternalStorageDirectory().getPath();
                String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
                DownFileInfo downFileInfo = DownFileInfo.this;
                downFileInfo.mTmpFile = String.format("%s/uview/%s_%s.tmp", new Object[]{path, downFileInfo.name, valueOf});
                DownFileInfo downFileInfo2 = DownFileInfo.this;
                downFileInfo2.mDstFile = String.format("%s/DCIM/Camera/%s_%s.mp4", new Object[]{path, downFileInfo2.name, valueOf});
                if (DownFileInfo.this.mDvrNet.DownVideoStart(DownFileInfo.this.mDstFile, DownFileInfo.this.mTmpFile, DownFileInfo.this.nDiskType, 1 << DownFileInfo.this.mChannel, 1, DownFileInfo.this.mStartTime, DownFileInfo.this.mEndTime, DownFileInfo.this.name) != 0) {
                    DownFileInfo.this.mHandler.post(new Runnable() {
                        public void run() {
                            DownFileInfo.this.mDownStartRunnable = null;
                        }
                    });
                } else {
                    DownFileInfo.this.mHandler.post(new Runnable() {
                        public void run() {
                            DownFileInfo.this.mDownStartRunnable = null;
                        }
                    });
                }
            }
        }
    }

    public void startDown() {
        this.mDownStartRunnable = new DownStartRunnable();
        new Thread(this.mDownStartRunnable).start();
    }

    public class DownStopRunnable implements Runnable {
        public boolean cancel;

        DownStopRunnable(boolean z) {
            this.cancel = z;
        }

        public void run() {
            DownFileInfo.this.mDvrNet.DownVideoStop(this.cancel);
            DownFileInfo.this.mHandler.post(new Runnable() {
                public void run() {
                    DownFileInfo.this.mDownStopRunnable = null;
                    if (DownStopRunnable.this.cancel) {
                        DownFileInfo.this.df.DownFileCallback(3, DownFileInfo.this.name);
                        return;
                    }
                    MediaScannerConnection.scanFile(DownFileInfo.this.mActivity, new String[]{DownFileInfo.this.mDstFile}, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
                }
            });
        }
    }

    public void stopDown(boolean z) {
        if (checkDownRunnable()) {
            this.mDownStopRunnable = new DownStopRunnable(z);
            new Thread(this.mDownStopRunnable).start();
        }
    }
}
