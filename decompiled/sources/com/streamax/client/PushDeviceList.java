package com.streamax.client;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.dvr.net.DvrNet;
import com.streamax.proxy.ConnDeviceProxy;
import com.streamax.proxy.RegisterPushProxy;
import com.streamax.utils.LogUtils;
import com.zycs.UView.R;
import java.util.List;
import java.util.Map;

public class PushDeviceList extends ListView {
    public static final String TAG = "PushDeviceList";
    public Context mContext;
    List<DevInfoBean> mData;
    public Handler mHandlerMessage;
    public MoreActivity mMoreUi;
    public PushDeviceAdapter mPushDeviceAdapter;

    public PushDeviceList(Context context) {
        super(context);
        this.mContext = context;
        PushDeviceAdapter pushDeviceAdapter = new PushDeviceAdapter(this.mContext);
        this.mPushDeviceAdapter = pushDeviceAdapter;
        setAdapter(pushDeviceAdapter);
        FindViews();
    }

    public PushDeviceList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        PushDeviceAdapter pushDeviceAdapter = new PushDeviceAdapter(this.mContext);
        this.mPushDeviceAdapter = pushDeviceAdapter;
        setAdapter(pushDeviceAdapter);
        FindViews();
    }

    public PushDeviceList(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        PushDeviceAdapter pushDeviceAdapter = new PushDeviceAdapter(this.mContext);
        this.mPushDeviceAdapter = pushDeviceAdapter;
        setAdapter(pushDeviceAdapter);
        FindViews();
    }

    public void setActivity(MoreActivity moreActivity) {
        this.mMoreUi = moreActivity;
    }

    public void SetData(List<DevInfoBean> list) {
        this.mData = list;
    }

    public void FindViews() {
        this.mHandlerMessage = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 0) {
                    Log.v(PushDeviceList.TAG, "[msg.arg1]" + message.arg1 + "[msg.arg2]" + message.arg2);
                    PushDeviceList.this.mMoreUi.mBusyView.setVisibility(4);
                    if (message.arg1 == message.arg2) {
                        new AlertDialog.Builder(PushDeviceList.this.mContext).setTitle(PushDeviceList.this.mContext.getString(R.string.app_name)).setIcon(PushDeviceList.this.mContext.getResources().getDrawable(R.drawable.icon)).setMessage(PushDeviceList.this.mContext.getString(R.string.operationfailed)).setPositiveButton(PushDeviceList.this.mContext.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
                    } else {
                        new AlertDialog.Builder(PushDeviceList.this.mContext).setTitle(PushDeviceList.this.mContext.getString(R.string.app_name)).setIcon(PushDeviceList.this.mContext.getResources().getDrawable(R.drawable.icon)).setMessage(PushDeviceList.this.mContext.getString(R.string.operationsuccess)).setPositiveButton(PushDeviceList.this.mContext.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                    }
                }
                PushDeviceList.this.mPushDeviceAdapter.notifyDataSetChanged();
                super.handleMessage(message);
            }
        };
    }

    public class ViewHolder {
        public ToggleButton tb;
        public TextView tv;

        public ViewHolder() {
        }
    }

    public class PushDeviceAdapter extends BaseAdapter {
        public LayoutInflater mInflater;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public PushDeviceAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            if (PushDeviceList.this.mData == null) {
                return 0;
            }
            return PushDeviceList.this.mData.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewholder;
            if (view == null) {
                Log.v(PushDeviceList.TAG, "[getView]position =" + i);
                view = this.mInflater.inflate(R.layout.pushconfigitem, (ViewGroup) null);
                viewholder = new ViewHolder();
                viewholder.tv = (TextView) view.findViewById(R.id.pushconfigtv);
                viewholder.tb = (ToggleButton) view.findViewById(R.id.pushitemswitch);
                viewholder.tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        Log.v(PushDeviceList.TAG, "[onCheckedChanged]CheckState:" + z);
                        if (PushDeviceList.this.mData != null && compoundButton.getId() <= PushDeviceList.this.mData.size()) {
                            DevInfoBean devInfoBean = PushDeviceList.this.mData.get(compoundButton.getId());
                            if (z && devInfoBean.mPush == 0) {
                                PushDeviceList.this.RegisterPushService(compoundButton.getId());
                            } else if (!z && devInfoBean.mPush == 1) {
                                PushDeviceList.this.UnregisterPushService(compoundButton.getId());
                            }
                        }
                    }
                });
                view.setTag(viewholder);
            } else {
                viewholder = (ViewHolder) view.getTag();
            }
            viewholder.tv.setText(PushDeviceList.this.mData.get(i).mDevName);
            viewholder.tb.setId(i);
            if (PushDeviceList.this.mData.get(i).mPush == 0) {
                viewholder.tb.setChecked(false);
            } else {
                viewholder.tb.setChecked(true);
            }
            return view;
        }
    }

    public void RegisterPushService(int i) {
        new Thread(new RegisterRunnable(i)).start();
    }

    public class RegisterRunnable implements Runnable {
        private DevInfoBean mDevinfo = null;
        private int nId;

        public RegisterRunnable(int i) {
            this.mDevinfo = PushDeviceList.this.mData.get(i);
            this.nId = i;
        }

        public void run() {
            if (this.mDevinfo != null && PushDeviceList.this.mMoreUi != null) {
                PushDeviceList.this.mMoreUi.mBusyView.post(new Runnable() {
                    public void run() {
                        PushDeviceList.this.mMoreUi.mBusyView.setVisibility(0);
                    }
                });
                if (this.mDevinfo.mRemark.length() == 0) {
                    DvrNet dvrNet = new DvrNet();
                    Map<String, Object> connDeviceProxy = ConnDeviceProxy.connDeviceProxy(dvrNet, this.mDevinfo, PushDeviceList.this.mMoreUi.mApp);
                    int i = -1;
                    if (connDeviceProxy != null) {
                        i = ((Integer) connDeviceProxy.get("errorcode")).intValue();
                    }
                    Class<?> cls = getClass();
                    LogUtils.log(cls, "errorcode:" + i);
                    if (i != 0) {
                        Message message = new Message();
                        message.what = 0;
                        message.arg1 = 0;
                        message.arg2 = 0;
                        message.setTarget(PushDeviceList.this.mHandlerMessage);
                        message.sendToTarget();
                        return;
                    }
                    String obj = connDeviceProxy.get("serialnum").toString();
                    Log.v(PushDeviceList.TAG, "errorcode:" + connDeviceProxy.get("errorcode").toString());
                    dvrNet.CloseDeviceHandle();
                    if (obj.length() == 0 || obj.compareTo("0") == 0) {
                        Message message2 = new Message();
                        message2.what = 0;
                        message2.arg1 = 0;
                        message2.arg2 = 0;
                        message2.setTarget(PushDeviceList.this.mHandlerMessage);
                        message2.sendToTarget();
                        return;
                    }
                    this.mDevinfo.mRemark = obj;
                }
                if (PushDeviceList.this.mMoreUi.mApp.mRegId != null) {
                    this.mDevinfo.PrintOut();
                    if (PushDeviceList.this.mMoreUi.mApp.mWebService == null) {
                        PushDeviceList.this.mMoreUi.mApp.mWebService = new WebService(MyApp.LastServerHostName, MyApp.username, MyApp.password);
                    }
                    if (RegisterPushProxy.registerPush(1, PushDeviceList.this.mMoreUi.mApp.mWebService, this.mDevinfo, PushDeviceList.this.mMoreUi.mApp, PushDeviceList.this.mContext)) {
                        this.mDevinfo.mPush = 1;
                        PushDeviceList.this.mData.get(this.nId).mPush = 1;
                    } else {
                        PushDeviceList.this.mData.get(this.nId).mPush = 0;
                        this.mDevinfo.mPush = 0;
                    }
                    if (MyApp.loginType == 0) {
                        PushDeviceList.this.mMoreUi.mApp.mdbHelper.Update(this.mDevinfo);
                    }
                    Message message3 = new Message();
                    message3.what = 0;
                    message3.arg1 = 0;
                    message3.arg2 = this.mDevinfo.mPush;
                    message3.setTarget(PushDeviceList.this.mHandlerMessage);
                    message3.sendToTarget();
                }
            }
        }
    }

    public void UnregisterPushService(int i) {
        new Thread(new UnregisterRunnable(i)).start();
    }

    public class UnregisterRunnable implements Runnable {
        private DevInfoBean devinfo;
        private int nId;

        public UnregisterRunnable(int i) {
            this.devinfo = PushDeviceList.this.mData.get(i);
            this.nId = i;
        }

        public void run() {
            if (this.devinfo != null && PushDeviceList.this.mMoreUi != null) {
                PushDeviceList.this.mMoreUi.mApp.mRegId = PushDeviceList.this.mMoreUi.mApp.mRegId.trim();
                PushDeviceList.this.mMoreUi.mBusyView.post(new Runnable() {
                    public void run() {
                        PushDeviceList.this.mMoreUi.mBusyView.setVisibility(0);
                    }
                });
                if (PushDeviceList.this.mMoreUi.mApp.mRegId != null && PushDeviceList.this.mMoreUi.mApp.mRegId.length() > 0) {
                    if (PushDeviceList.this.mMoreUi.mApp.mWebService == null) {
                        PushDeviceList.this.mMoreUi.mApp.mWebService = new WebService(MyApp.LastServerHostName, MyApp.username, MyApp.password);
                    }
                    if (PushDeviceList.this.mMoreUi.mApp.mWebService.Android_Delete(PushDeviceList.this.mMoreUi.mApp.mRegId, this.devinfo.mDevName, MyApp.username, Integer.valueOf(MyApp.loginType).toString(), Integer.valueOf(this.devinfo.mDevId).toString())) {
                        this.devinfo.mPush = 0;
                        PushDeviceList.this.mData.get(this.nId).mPush = 0;
                    }
                    if (MyApp.loginType == 0) {
                        PushDeviceList.this.mMoreUi.mApp.mdbHelper.Update(this.devinfo);
                    }
                }
                Message message = new Message();
                message.what = 0;
                message.arg1 = 1;
                message.arg2 = this.devinfo.mPush;
                message.setTarget(PushDeviceList.this.mHandlerMessage);
                message.sendToTarget();
            }
        }
    }

    public void UnregisterPushServiceAll() {
        new Thread(new UnregisterAllRunnable()).start();
    }

    public class UnregisterAllRunnable implements Runnable {
        private DevInfoBean devinfo;
        private int nId;

        public UnregisterAllRunnable() {
            DevInfoBean devInfoBean = new DevInfoBean();
            this.devinfo = devInfoBean;
            devInfoBean.mPush = 1;
        }

        public void run() {
            if (this.devinfo != null && PushDeviceList.this.mMoreUi != null) {
                PushDeviceList.this.mMoreUi.mApp.mRegId = PushDeviceList.this.mMoreUi.mApp.mRegId.trim();
                PushDeviceList.this.mMoreUi.mBusyView.post(new Runnable() {
                    public void run() {
                        PushDeviceList.this.mMoreUi.mBusyView.setVisibility(0);
                    }
                });
                if (PushDeviceList.this.mMoreUi.mApp.mRegId != null && PushDeviceList.this.mMoreUi.mApp.mRegId.length() > 0) {
                    if (PushDeviceList.this.mMoreUi.mApp.mWebService == null) {
                        PushDeviceList.this.mMoreUi.mApp.mWebService = new WebService(MyApp.LastServerHostName, MyApp.username, MyApp.password);
                    }
                    if (PushDeviceList.this.mMoreUi.mApp.mWebService.Android_DeleteAllPush(PushDeviceList.this.mMoreUi.mApp.mRegId, MyApp.username)) {
                        this.devinfo.mPush = 0;
                        PushDeviceList.this.mData.get(this.nId).mPush = 0;
                        for (int i = 0; i != PushDeviceList.this.mData.size(); i++) {
                            PushDeviceList.this.mData.get(i).mPush = 0;
                            if (MyApp.loginType == 0) {
                                PushDeviceList.this.mMoreUi.mApp.mdbHelper.Update(PushDeviceList.this.mData.get(i));
                            }
                        }
                    }
                }
                Message message = new Message();
                message.what = 0;
                message.arg1 = 1;
                message.arg2 = this.devinfo.mPush;
                message.setTarget(PushDeviceList.this.mHandlerMessage);
                message.sendToTarget();
            }
        }
    }
}
