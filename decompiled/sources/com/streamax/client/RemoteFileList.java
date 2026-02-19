package com.streamax.client;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.amo.demo.wheelview.NumericWheelAdapter;
import com.amo.demo.wheelview.OnWheelChangedListener;
import com.amo.demo.wheelview.WheelView;
import com.dvr.calendar.CalendarView;
import com.dvr.calendar.DayStyle;
import com.dvr.net.RemoteFileInfo;
import com.streamax.utils.AppProxy;
import com.zycs.UView.R;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RemoteFileList extends ListView {
    private static int END_YEAR = 2100;
    /* access modifiers changed from: private */
    public static int START_YEAR = 1990;
    public Handler AlertDialogHandler;
    private Button btn;
    /* access modifiers changed from: private */
    public Dialog dialog;
    public CalendarView mCalendarview;
    public Context mContext;
    public String mDateText;
    public FileAdapter mFileAdapter;
    public RemoteFileInfo[] mRemoteFileInfo;
    public List<RemoteFileInfo> mRemoteFileList;
    public RemotePlayback mRemotePlayback;
    public remoteplayinterface mRemoteplayInterface;
    public Button mbtnSelectRecTime;
    public TextView mtvHeader;

    public RemoteFileList(Context context) {
        this(context, (AttributeSet) null);
    }

    public RemoteFileList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRemoteplayInterface = null;
        this.mRemoteFileList = new ArrayList();
        this.mContext = context;
        FindViews();
        addHeaderView(BuildHeader());
    }

    public class FileComparator implements Comparator<Object> {
        public FileComparator() {
        }

        public int compare(Object obj, Object obj2) {
            RemoteFileInfo remoteFileInfo = (RemoteFileInfo) obj;
            RemoteFileInfo remoteFileInfo2 = (RemoteFileInfo) obj2;
            int compareTo = remoteFileInfo.FileTime.substring(0, 14).compareTo(remoteFileInfo2.FileTime.substring(0, 14));
            if (compareTo != 0) {
                return compareTo;
            }
            return remoteFileInfo.nChannel - remoteFileInfo2.nChannel;
        }
    }

    public void FindViews() {
        this.AlertDialogHandler = new Handler() {
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    new AlertDialog.Builder(RemoteFileList.this.mContext).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.norecord).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
                } else if (i == 2) {
                    new AlertDialog.Builder(RemoteFileList.this.mContext).setIcon(R.drawable.icon).setTitle(R.string.app_name).setMessage(R.string.pleaseselectdevice).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
                }
                super.handleMessage(message);
            }
        };
    }

    public View BuildHeader() {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setOrientation(1);
        CalendarView calendarView = new CalendarView(this.mContext);
        this.mCalendarview = calendarView;
        calendarView.LoadViews();
        linearLayout.addView(this.mCalendarview);
        Button button = new Button(this.mContext);
        this.mbtnSelectRecTime = button;
        button.setText(R.string.customplaybacktime);
        this.mbtnSelectRecTime.setGravity(17);
        this.mbtnSelectRecTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (RemoteFileList.this.mRemotePlayback.mDvrNet == null) {
                    Message message = new Message();
                    message.what = 2;
                    message.setTarget(RemoteFileList.this.AlertDialogHandler);
                    message.sendToTarget();
                    return;
                }
                RemoteFileList.this.showDateTimePicker();
            }
        });
        linearLayout.addView(this.mbtnSelectRecTime);
        TextView textView = new TextView(this.mContext);
        this.mtvHeader = textView;
        textView.setBackgroundColor(DayStyle.iColorTextHeader);
        this.mtvHeader.setGravity(17);
        this.mtvHeader.setHeight(100);
        this.mtvHeader.setWidth(getResources().getDisplayMetrics().widthPixels);
        this.mtvHeader.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mtvHeader.getPaint().setFakeBoldText(true);
        this.mtvHeader.setTextSize(24.0f);
        linearLayout.addView(this.mtvHeader);
        return linearLayout;
    }

    public void SetRemoteplayInterface(remoteplayinterface remoteplayinterface) {
        this.mRemoteplayInterface = remoteplayinterface;
    }

    public View GetCalendarView() {
        return this.mCalendarview;
    }

    public void SetHeader(String str) {
        this.mDateText = str;
        this.mtvHeader.post(new Runnable() {
            public void run() {
                RemoteFileList.this.mtvHeader.setText(RemoteFileList.this.mDateText);
            }
        });
    }

    public void setRemotePlayback(RemotePlayback remotePlayback) {
        this.mRemotePlayback = remotePlayback;
    }

    public void clearAdapter() {
        if (this.mRemoteFileInfo != null) {
            this.mRemoteFileInfo = null;
        }
        List<RemoteFileInfo> list = this.mRemoteFileList;
        if (list != null && !list.isEmpty()) {
            this.mRemoteFileList.clear();
        }
        FileAdapter fileAdapter = this.mFileAdapter;
        if (fileAdapter == null) {
            FileAdapter fileAdapter2 = new FileAdapter(AppProxy.getContext());
            this.mFileAdapter = fileAdapter2;
            setAdapter(fileAdapter2);
            return;
        }
        fileAdapter.notifyDataSetChanged();
    }

    public void updateAdapter() {
        this.mRemoteFileList.clear();
        RemoteFileInfo[] remoteFileInfoArr = this.mRemoteFileInfo;
        if (remoteFileInfoArr != null && remoteFileInfoArr.length > 0) {
            int i = 0;
            while (true) {
                RemoteFileInfo[] remoteFileInfoArr2 = this.mRemoteFileInfo;
                if (i >= remoteFileInfoArr2.length) {
                    break;
                }
                this.mRemoteFileList.add(remoteFileInfoArr2[i]);
                i++;
            }
            Collections.sort(this.mRemoteFileList, new FileComparator());
        }
        FileAdapter fileAdapter = this.mFileAdapter;
        if (fileAdapter == null) {
            FileAdapter fileAdapter2 = new FileAdapter(AppProxy.getContext());
            this.mFileAdapter = fileAdapter2;
            setAdapter(fileAdapter2);
            return;
        }
        fileAdapter.notifyDataSetChanged();
    }

    public class ViewHolder {
        ImageView img;
        TextView tv;

        public ViewHolder() {
        }
    }

    public class FileAdapter extends BaseAdapter {
        public LayoutInflater mInflater;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public FileAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            if (RemoteFileList.this.mRemoteFileList == null) {
                return 0;
            }
            return RemoteFileList.this.mRemoteFileList.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = this.mInflater.inflate(R.layout.remotefilelistitem, (ViewGroup) null);
                viewHolder.img = (ImageView) view2.findViewById(R.id.remotefilelistitem_img);
                viewHolder.tv = (TextView) view2.findViewById(R.id.remotefilelistitem_text);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.img.setBackgroundResource(R.drawable.record_file);
            RemoteFileList remoteFileList = RemoteFileList.this;
            char[] access$100 = remoteFileList.getChars(remoteFileList.mRemoteFileList.get(i).FileTime.getBytes());
            viewHolder.tv.setText(String.format("%d\t%s:%s:%s- %s:%s:%s CHN:%d", new Object[]{Integer.valueOf(i + 1), String.valueOf(access$100, 8, 2), String.valueOf(access$100, 10, 2), String.valueOf(access$100, 12, 2), String.valueOf(access$100, 23, 2), String.valueOf(access$100, 25, 2), String.valueOf(access$100, 27, 2), Integer.valueOf(RemoteFileList.this.mRemoteFileList.get(i).nChannel + 1)}));
            return view2;
        }
    }

    /* access modifiers changed from: private */
    public char[] getChars(byte[] bArr) {
        Charset forName = Charset.forName("UTF-8");
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length);
        allocate.put(bArr);
        allocate.flip();
        return forName.decode(allocate).array();
    }

    /* access modifiers changed from: private */
    public void showDateTimePicker() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(1);
        int i2 = instance.get(2);
        int i3 = instance.get(5);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        List asList = Arrays.asList(new String[]{"1", "3", "5", "7", "8", "10", "12"});
        List asList2 = Arrays.asList(new String[]{"4", "6", "9", "11"});
        Dialog dialog2 = new Dialog(this.mContext);
        this.dialog = dialog2;
        dialog2.setTitle(R.string.pleaseselectdateandtime);
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.timeselect, (ViewGroup) null);
        WheelView wheelView = (WheelView) inflate.findViewById(R.id.year);
        wheelView.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));
        wheelView.setCyclic(true);
        wheelView.setLabel("Y");
        wheelView.setCurrentItem(i - START_YEAR);
        WheelView wheelView2 = (WheelView) inflate.findViewById(R.id.month);
        wheelView2.setAdapter(new NumericWheelAdapter(1, 12));
        wheelView2.setCyclic(true);
        wheelView2.setLabel("M");
        wheelView2.setCurrentItem(i2);
        WheelView wheelView3 = (WheelView) inflate.findViewById(R.id.day);
        wheelView3.setCyclic(true);
        int i7 = i2 + 1;
        if (asList.contains(String.valueOf(i7))) {
            wheelView3.setAdapter(new NumericWheelAdapter(1, 31));
        } else if (asList2.contains(String.valueOf(i7))) {
            wheelView3.setAdapter(new NumericWheelAdapter(1, 30));
        } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
            wheelView3.setAdapter(new NumericWheelAdapter(1, 28));
        } else {
            wheelView3.setAdapter(new NumericWheelAdapter(1, 29));
        }
        wheelView3.setLabel("D");
        wheelView3.setCurrentItem(i3 - 1);
        WheelView wheelView4 = (WheelView) inflate.findViewById(R.id.hour);
        wheelView4.setAdapter(new NumericWheelAdapter(0, 23));
        wheelView4.setCyclic(true);
        wheelView4.setCurrentItem(i4);
        WheelView wheelView5 = (WheelView) inflate.findViewById(R.id.mins);
        wheelView5.setAdapter(new NumericWheelAdapter(0, 59, "%02d"));
        wheelView5.setCyclic(true);
        wheelView5.setCurrentItem(i5);
        final WheelView wheelView6 = (WheelView) inflate.findViewById(R.id.secs);
        wheelView6.setAdapter(new NumericWheelAdapter(0, 59, "%02d"));
        wheelView6.setCyclic(true);
        wheelView6.setCurrentItem(i6);
        WheelView wheelView7 = (WheelView) inflate.findViewById(R.id.chn);
        wheelView7.setAdapter(new NumericWheelAdapter(1, this.mRemotePlayback.mDevInfo.mChCounts, "%d"));
        wheelView7.setCyclic(true);
        wheelView7.setCurrentItem(0);
        final List list = asList;
        final List list2 = asList2;
        final WheelView yearView = wheelView;
        final WheelView monthView = wheelView2;
        final WheelView dayView = wheelView3;
        final WheelView hourView = wheelView4;
        final WheelView minView = wheelView5;
        final WheelView chnView = wheelView7;
        OnWheelChangedListener yearChangedListener = new OnWheelChangedListener() {
            public void onChanged(WheelView wheelView, int i, int i2) {
                int access$200 = i2 + RemoteFileList.START_YEAR;
                if (list.contains(String.valueOf(monthView.getCurrentItem() + 1))) {
                    dayView.setAdapter(new NumericWheelAdapter(1, 31));
                } else if (list2.contains(String.valueOf(monthView.getCurrentItem() + 1))) {
                    dayView.setAdapter(new NumericWheelAdapter(1, 30));
                } else if ((access$200 % 4 != 0 || access$200 % 100 == 0) && access$200 % 400 != 0) {
                    dayView.setAdapter(new NumericWheelAdapter(1, 28));
                } else {
                    dayView.setAdapter(new NumericWheelAdapter(1, 29));
                }
            }
        };
        OnWheelChangedListener monthChangedListener = new OnWheelChangedListener() {
            public void onChanged(WheelView wheelView, int i, int i2) {
                int i3 = i2 + 1;
                if (list.contains(String.valueOf(i3))) {
                    dayView.setAdapter(new NumericWheelAdapter(1, 31));
                } else if (list2.contains(String.valueOf(i3))) {
                    dayView.setAdapter(new NumericWheelAdapter(1, 30));
                } else if (((yearView.getCurrentItem() + RemoteFileList.START_YEAR) % 4 != 0 || (yearView.getCurrentItem() + RemoteFileList.START_YEAR) % 100 == 0) && (yearView.getCurrentItem() + RemoteFileList.START_YEAR) % 400 != 0) {
                    dayView.setAdapter(new NumericWheelAdapter(1, 28));
                } else {
                    dayView.setAdapter(new NumericWheelAdapter(1, 29));
                }
            }
        };
        yearView.addChangingListener(yearChangedListener);
        monthView.addChangingListener(monthChangedListener);
        int i8 = getResources().getDisplayMetrics().widthPixels;
        int i9 = (i8 >= 480 && (i8 < 480 || i8 >= 720)) ? i8 >= 720 ? 32 : 0 : 12;
        wheelView3.TEXT_SIZE = i9;
        wheelView4.TEXT_SIZE = i9;
        wheelView5.TEXT_SIZE = i9;
        wheelView6.TEXT_SIZE = i9;
        wheelView7.TEXT_SIZE = i9;
        wheelView2.TEXT_SIZE = i9;
        wheelView.TEXT_SIZE = i9;
        View view2 = inflate;
        Button button = (Button) view2.findViewById(R.id.btn_datetime_cancel);
        View.OnClickListener confirmClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                new DecimalFormat("00");
                RemoteFileList.this.dialog.dismiss();
                if (RemoteFileList.this.mRemoteplayInterface != null) {
                    RemoteFileList.this.mRemoteplayInterface.startRemotePlay(yearView.getCurrentItem() + RemoteFileList.START_YEAR, monthView.getCurrentItem() + 1, dayView.getCurrentItem() + 1, hourView.getCurrentItem(), minView.getCurrentItem(), wheelView6.getCurrentItem(), chnView.getCurrentItem());
                }
            }
        };
        ((Button) view2.findViewById(R.id.btn_datetime_sure)).setOnClickListener(confirmClickListener);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RemoteFileList.this.dialog.dismiss();
            }
        });
        this.dialog.setContentView(view2);
        this.dialog.show();
    }
}
