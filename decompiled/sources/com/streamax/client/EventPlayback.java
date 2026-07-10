package com.streamax.client;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.dvr.net.DvrNet;
import com.streamax.Configs;
import com.streamax.config.db.DatabaseConfig;
import com.streamax.utils.LogUtils;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Event list UI for alarm/event playback entries.
public class EventPlayback extends LinearLayout {
    public PlaybackActivity a;
    public AlarmAdapter mAlarmAdapter;
    public MyApp mApp;
    public Button mBtnSelect;
    public Context mContext;
    public List<Map<String, Object>> mData;
    public DevInfoBean mDevInfo;
    public Handler mHandlerMessage;
    public LayoutInflater mInflater;
    public ListView mListView;
    public ThreadGetEventList mThreadGetEventList;
    public TextView mTitle;
    public View mbusyView;
    /* access modifiers changed from: private */
    public DbHelper mdbHelper;
    public View mpopViewer;
    public PopupWindow pop;

    public EventPlayback(Context context) {
        this(context, (AttributeSet) null);
    }

    public EventPlayback(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LogUtils.log(getClass(), "EventPlayback()");
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mdbHelper = new DbHelper(context, DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void FindViews() {
        this.mListView = (ListView) findViewById(R.id.eventlist);
        AlarmAdapter alarmAdapter = new AlarmAdapter();
        this.mAlarmAdapter = alarmAdapter;
        this.mListView.setAdapter(alarmAdapter);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ViewHolder viewholder = (ViewHolder) view.getTag();
                String playbackTime = viewholder.playbacktime;
                int playbackChannel = viewholder.playbackchannel;
                Intent intent = new Intent(EventPlayback.this.mContext, EventPlaybackActivity.class);
                Calendar instance = Calendar.getInstance();
                instance.set(1, Integer.valueOf(playbackTime.substring(0, 4)).intValue());
                instance.set(2, Integer.valueOf(playbackTime.substring(5, 7)).intValue() - 1);
                instance.set(5, Integer.valueOf(playbackTime.substring(8, 10)).intValue());
                instance.set(11, Integer.valueOf(playbackTime.substring(11, 13)).intValue());
                instance.set(12, Integer.valueOf(playbackTime.substring(14, 16)).intValue());
                instance.set(13, Integer.valueOf(playbackTime.substring(17, 19)).intValue());
                intent.putExtra("year", Integer.valueOf(instance.get(1)));
                intent.putExtra("month", Integer.valueOf(instance.get(2) + 1));
                intent.putExtra("day", Integer.valueOf(instance.get(5)));
                intent.putExtra("hour", Integer.valueOf(instance.get(11)));
                intent.putExtra("minute", Integer.valueOf(instance.get(12)));
                intent.putExtra("second", Integer.valueOf(instance.get(13)));
                intent.putExtra("channel", playbackChannel);
                intent.putExtra("alarmtype", viewholder.alarmtype);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DatabaseConfig.TableName, EventPlayback.this.mDevInfo);
                intent.putExtras(bundle);
                EventPlayback.this.mContext.startActivity(intent);
            }
        });
        this.mTitle = (TextView) findViewById(R.id.event_playback_title_text);
        Button button = (Button) findViewById(R.id.event_playback_title_button_select);
        this.mBtnSelect = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LinearLayout linearLayout = (LinearLayout) EventPlayback.this.mInflater.inflate(R.layout.popupwindow_device, (ViewGroup) null);
                DeviceSelect deviceSelect = (DeviceSelect) linearLayout.findViewById(R.id.device_select_device_view);
                deviceSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Integer num = (Integer) ((TextView) view).getTag();
                        if (EventPlayback.this.mThreadGetEventList != null) {
                            EventPlayback.this.mThreadGetEventList.StopThread();
                            EventPlayback.this.mThreadGetEventList = null;
                        }
                        EventPlayback.this.mThreadGetEventList = new ThreadGetEventList(num.intValue());
                        EventPlayback.this.mThreadGetEventList.start();
                        EventPlayback.this.pop.dismiss();
                        EventPlayback.this.pop = null;
                    }
                });
                if (MyApp.loginType == 0) {
                    deviceSelect.SetData(EventPlayback.this.mdbHelper.getlist(), (List<String>) null);
                } else {
                    deviceSelect.SetData(EventPlayback.this.mApp.mWebService.GetTerminalInfoAndroid(true), (List<String>) null);
                }
                EventPlayback eventPlayback = EventPlayback.this;
                eventPlayback.popMenu(linearLayout, eventPlayback.mBtnSelect);
            }
        });
        this.mbusyView = findViewById(R.id.eventplayback_busy);
        this.mHandlerMessage = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 0) {
                    new AlertDialog.Builder(EventPlayback.this.mContext).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.deviceoffline).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
                }
                super.handleMessage(message);
            }
        };
    }

    public void SetActivity(PlaybackActivity playbackActivity) {
        this.a = playbackActivity;
        this.mApp = (MyApp) playbackActivity.getApplication();
    }

    /* access modifiers changed from: private */
    public List<Map<String, Object>> getData(String serialNumber) {
        ArrayList arrayList = new ArrayList();
        if (this.mApp.mWebService == null) {
            this.mApp.mWebService = new WebService(MyApp.LastServerHostName, MyApp.username, MyApp.password);
        }
        List<AlarmInfo> list = null;
        try {
            list = this.mApp.mWebService.getAlarmBySerialNum(serialNumber, 20);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list == null) {
            return arrayList;
        }
        for (int i = 0; i < list.size(); i++) {
            AlarmInfo alarmInfo = list.get(i);
            HashMap hashMap = new HashMap();
            int intValue = Integer.valueOf(alarmInfo.alarmType).intValue();
            if (intValue == 0) {
                hashMap.put("name", this.mContext.getString(R.string.videoloss));
            } else if (intValue == 1) {
                hashMap.put("name", this.mContext.getString(R.string.camerablock));
            } else if (intValue == 2) {
                hashMap.put("name", this.mContext.getString(R.string.motiondetection));
            } else if (intValue == 3) {
                hashMap.put("name", this.mContext.getString(R.string.hddalarm));
            } else if (intValue == 4) {
                hashMap.put("name", this.mContext.getString(R.string.customalarm));
            } else if (intValue != 5) {
                hashMap.put("name", this.mContext.getString(R.string.undefinedalarm));
            } else {
                hashMap.put("name", this.mContext.getString(R.string.gatetrigger));
            }
            String channelLabel = this.mContext.getString(R.string.channel) + ":";
            alarmInfo.alarmChannel.trim();
            int intValue2 = (alarmInfo.alarmChannel == null || alarmInfo.alarmChannel.length() <= 0) ? 0 : Integer.valueOf(alarmInfo.alarmChannel).intValue();
            int firstPlaybackChannel = -1;
            for (int channelIndex = 0; channelIndex < 32; channelIndex++) {
                if ((((1 << channelIndex) & intValue2) >> channelIndex) == 1) {
                    channelLabel = channelLabel + Integer.valueOf(channelIndex + 1).toString() + " ";
                    if (firstPlaybackChannel == -1) {
                        firstPlaybackChannel = channelIndex;
                    }
                }
            }
            hashMap.put("channel", channelLabel);
            hashMap.put("time", this.mContext.getString(R.string.time) + ":" + alarmInfo.alarmTime);
            hashMap.put("playbacktime", alarmInfo.alarmTime);
            hashMap.put("alarmtype", alarmInfo.alarmType);
            hashMap.put("playbackchannel", Integer.valueOf(firstPlaybackChannel));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private class ThreadGetEventList extends Thread {
        public DvrNet mDvrNet;

        public ThreadGetEventList(int i) {
            if (MyApp.loginType == 0) {
                EventPlayback.this.mDevInfo = EventPlayback.this.mdbHelper.query(i);
            } else {
                EventPlayback.this.mDevInfo = EventPlayback.this.mApp.mWebService.query(i);
            }
            if (EventPlayback.this.mData != null) {
                EventPlayback.this.mData.clear();
                EventPlayback.this.mData = null;
            }
            EventPlayback.this.mListView.post(new Runnable() {
                public void run() {
                    EventPlayback.this.mAlarmAdapter.notifyDataSetChanged();
                }
            });
        }

        public void StopThread() {
            EventPlayback.this.mbusyView.post(new Runnable() {
                public void run() {
                    EventPlayback.this.mbusyView.setVisibility(4);
                }
            });
            DvrNet dvrNet = this.mDvrNet;
            if (dvrNet != null) {
                dvrNet.CloseDeviceHandle();
                this.mDvrNet = null;
            }
        }

        public void run() {
            String serialNumber;
            EventPlayback.this.mbusyView.post(new Runnable() {
                public void run() {
                    EventPlayback.this.mbusyView.setVisibility(0);
                }
            });
            if (EventPlayback.this.mData != null) {
                EventPlayback.this.mData.clear();
                EventPlayback.this.mData = null;
            }
            EventPlayback.this.mListView.post(new Runnable() {
                public void run() {
                    EventPlayback.this.mAlarmAdapter.notifyDataSetChanged();
                }
            });
            DvrNet dvrNet = this.mDvrNet;
            if (dvrNet != null) {
                dvrNet.CloseDeviceHandle();
                this.mDvrNet = null;
            }
            this.mDvrNet = new DvrNet();
            if (MyApp.loginType != 0) {
                serialNumber = EventPlayback.this.mDevInfo.mRemark;
            } else if (!EventPlayback.this.mDevInfo.mDevIp.contains(".") || EventPlayback.this.mDevInfo.mMediaPort <= 0) {
                serialNumber = EventPlayback.this.mDevInfo.mDevIp;
            } else {
                Map<String, Object> GetDeviceHandle = this.mDvrNet.GetDeviceHandle(EventPlayback.this.mDevInfo.mDevIp, EventPlayback.this.mDevInfo.mMediaPort, EventPlayback.this.mDevInfo.mUsername, EventPlayback.this.mDevInfo.mPwd, EventPlayback.this.getLocalMacAddress());
                ((Integer) GetDeviceHandle.get("errorcode")).intValue();
                int intValue = ((Integer) GetDeviceHandle.get("errorcode")).intValue();
                serialNumber = (String) GetDeviceHandle.get("serialnum");
                this.mDvrNet.CloseDeviceHandle();
                this.mDvrNet = null;
                if (intValue != 0) {
                    if (EventPlayback.this.mData != null) {
                        EventPlayback.this.mData.clear();
                        EventPlayback.this.mData = null;
                    }
                    EventPlayback.this.mListView.post(new Runnable() {
                        public void run() {
                            EventPlayback.this.mAlarmAdapter.notifyDataSetChanged();
                        }
                    });
                    EventPlayback.this.mbusyView.post(new Runnable() {
                        public void run() {
                            EventPlayback.this.mbusyView.setVisibility(4);
                        }
                    });
                    Message message = new Message();
                    message.what = 0;
                    message.setTarget(EventPlayback.this.mHandlerMessage);
                    message.sendToTarget();
                    return;
                }
            }
            EventPlayback.this.mTitle.post(new Runnable() {
                public void run() {
                    EventPlayback.this.mTitle.setText(EventPlayback.this.mDevInfo.mDevName);
                }
            });
            if (EventPlayback.this.mData != null) {
                EventPlayback.this.mData.clear();
            }
            EventPlayback eventPlayback = EventPlayback.this;
            eventPlayback.mData = eventPlayback.getData(serialNumber);
            EventPlayback.this.mListView.post(new Runnable() {
                public void run() {
                    EventPlayback.this.mAlarmAdapter.notifyDataSetChanged();
                }
            });
            EventPlayback.this.mbusyView.post(new Runnable() {
                public void run() {
                    EventPlayback.this.mbusyView.setVisibility(4);
                }
            });
            super.run();
        }
    }

    public void popMenu(View view, View view2) {
        new DisplayMetrics();
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        PopupWindow popupWindow = this.pop;
        if (popupWindow == null) {
            PopupWindow popupWindow2 = new PopupWindow(view, screenWidth / 2, screenHeight / 2, true);
            this.pop = popupWindow2;
            popupWindow2.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
            this.pop.setTouchInterceptor(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 4) {
                        return false;
                    }
                    EventPlayback.this.pop.dismiss();
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
            PopupWindow popupWindow3 = new PopupWindow(view, screenWidth / 2, screenHeight / 2, true);
            this.pop = popupWindow3;
            popupWindow3.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_device_bg));
            this.pop.setOutsideTouchable(false);
            this.pop.showAsDropDown(view2, 1, 0);
            this.pop.update();
        }
    }

    public String getLocalMacAddress() {
        String fallbackMacAddress = new String("00-00-00-00-00-00");
        WifiManager wifiManager = (WifiManager) this.mContext.getApplicationContext().getSystemService(Configs.Key.WifiStatus);
        if (wifiManager == null) {
            return fallbackMacAddress;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        String macAddress = connectionInfo.getMacAddress();
        if (macAddress == null) {
            return fallbackMacAddress;
        }
        String replace = macAddress.replace(":", "-");
        return replace.length() > 0 ? replace : fallbackMacAddress;
    }

    public class ViewHolder {
        public int alarmtype;
        public int playbackchannel;
        public String playbacktime;
        public TextView tvChannel;
        public TextView tvName;
        public TextView tvTime;

        public ViewHolder() {
        }
    }

    public class AlarmAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public AlarmAdapter() {
        }

        public int getCount() {
            if (EventPlayback.this.mData == null) {
                return 0;
            }
            return EventPlayback.this.mData.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewholder;
            if (view == null) {
                view = EventPlayback.this.mInflater.inflate(R.layout.alarminfoitem, (ViewGroup) null);
                viewholder = new ViewHolder();
                view.setTag(viewholder);
                viewholder.tvName = (TextView) view.findViewById(R.id.alarminfoitem_name);
                viewholder.tvChannel = (TextView) view.findViewById(R.id.alarminfoitem_channel);
                viewholder.tvTime = (TextView) view.findViewById(R.id.alarminfoitem_time);
            } else {
                viewholder = (ViewHolder) view.getTag();
            }
            viewholder.tvName.setText(EventPlayback.this.mData.get(i).get("name").toString());
            viewholder.tvChannel.setText(EventPlayback.this.mData.get(i).get("channel").toString());
            viewholder.tvTime.setText(EventPlayback.this.mData.get(i).get("time").toString());
            viewholder.playbacktime = EventPlayback.this.mData.get(i).get("playbacktime").toString();
            viewholder.playbackchannel = Integer.valueOf(EventPlayback.this.mData.get(i).get("playbackchannel").toString()).intValue();
            viewholder.alarmtype = Integer.valueOf(EventPlayback.this.mData.get(i).get("alarmtype").toString()).intValue();
            return view;
        }
    }
}
