package com.streamax.client.ui.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hjq.base.BaseAdapter;
import com.hjq.base.BaseDialog;
import com.streamax.client.aop.SingleClick;
import com.streamax.client.app.AppAdapter;
import com.streamax.client.manager.PickerLayoutManager;
import com.streamax.client.ui.dialog.CommonDialog;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.Calendar;

public final class TimeDialog {

    public interface OnListener {

        /* renamed from: com.streamax.client.ui.dialog.TimeDialog$OnListener$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onCancel(OnListener onListener, BaseDialog baseDialog) {
            }
        }

        void onCancel(BaseDialog baseDialog);

        void onSelected(BaseDialog baseDialog, int hour, int minute, int second);
    }

    public static final class Builder extends CommonDialog.Builder<Builder> {
        private final PickerAdapter mHourAdapter;
        private final PickerLayoutManager mHourManager;
        private final RecyclerView mHourView = ((RecyclerView) findViewById(R.id.rv_time_hour));
        private OnListener mListener;
        private final PickerAdapter mMinuteAdapter;
        private final PickerLayoutManager mMinuteManager;
        private final RecyclerView mMinuteView = ((RecyclerView) findViewById(R.id.rv_time_minute));
        private final PickerAdapter mSecondAdapter;
        private final PickerLayoutManager mSecondManager;
        private final RecyclerView mSecondView = ((RecyclerView) findViewById(R.id.rv_time_second));

        public Builder(Context context) {
            super(context);
            String zeroPrefix;
            String secondPrefix;
            String minutePrefix;
            setCustomView((int) R.layout.time_dialog);
            setTitle((int) R.string.time_title);
            this.mHourAdapter = new PickerAdapter(context);
            this.mMinuteAdapter = new PickerAdapter(context);
            this.mSecondAdapter = new PickerAdapter(context);
            ArrayList<String> hours = new ArrayList<>(24);
            int hour = 0;
            while (true) {
                zeroPrefix = "0";
                if (hour > 23) {
                    break;
                }
                StringBuilder sb = new StringBuilder();
                if (hour >= 10) {
                    zeroPrefix = "";
                }
                sb.append(zeroPrefix);
                sb.append(hour);
                sb.append(" ");
                sb.append(getString(R.string.common_hour));
                hours.add(sb.toString());
                hour++;
            }
            ArrayList<String> minutes = new ArrayList<>(60);
            for (int minute = 0; minute <= 59; minute++) {
                StringBuilder sb2 = new StringBuilder();
                if (minute < 10) {
                    minutePrefix = zeroPrefix;
                } else {
                    minutePrefix = "";
                }
                sb2.append(minutePrefix);
                sb2.append(minute);
                sb2.append(" ");
                sb2.append(getString(R.string.common_minute));
                minutes.add(sb2.toString());
            }
            ArrayList<String> seconds = new ArrayList<>(60);
            for (int second = 0; second <= 59; second++) {
                StringBuilder sb3 = new StringBuilder();
                if (second < 10) {
                    secondPrefix = zeroPrefix;
                } else {
                    secondPrefix = "";
                }
                sb3.append(secondPrefix);
                sb3.append(second);
                sb3.append(" ");
                sb3.append(getString(R.string.common_second));
                seconds.add(sb3.toString());
            }
            this.mHourAdapter.setData(hours);
            this.mMinuteAdapter.setData(minutes);
            this.mSecondAdapter.setData(seconds);
            PickerLayoutManager build = new PickerLayoutManager.Builder(context).build();
            this.mHourManager = build;
            PickerLayoutManager build2 = new PickerLayoutManager.Builder(context).build();
            this.mMinuteManager = build2;
            PickerLayoutManager build3 = new PickerLayoutManager.Builder(context).build();
            this.mSecondManager = build3;
            this.mHourView.setLayoutManager(build);
            this.mMinuteView.setLayoutManager(build2);
            this.mSecondView.setLayoutManager(build3);
            this.mHourView.setAdapter(this.mHourAdapter);
            this.mMinuteView.setAdapter(this.mMinuteAdapter);
            this.mSecondView.setAdapter(this.mSecondAdapter);
            Calendar instance = Calendar.getInstance();
            setHour(instance.get(11));
            setMinute(instance.get(12));
            setSecond(instance.get(13));
        }

        public Builder setListener(OnListener onListener) {
            this.mListener = onListener;
            return this;
        }

        public Builder setIgnoreSecond() {
            this.mSecondView.setVisibility(8);
            return this;
        }

        public Builder setTime(String timeText) {
            if (timeText.matches("\\d{6}")) {
                setHour(timeText.substring(0, 2));
                setMinute(timeText.substring(2, 4));
                setSecond(timeText.substring(4, 6));
            } else if (timeText.matches("\\d{2}:\\d{2}:\\d{2}")) {
                setHour(timeText.substring(0, 2));
                setMinute(timeText.substring(3, 5));
                setSecond(timeText.substring(6, 8));
            }
            return this;
        }

        public Builder setHour(String hourText) {
            return setHour(Integer.parseInt(hourText));
        }

        public Builder setHour(int hour) {
            if (hour < 0 || hour == 24) {
                hour = 0;
            } else if (hour > this.mHourAdapter.getCount() - 1) {
                hour = this.mHourAdapter.getCount() - 1;
            }
            this.mHourView.scrollToPosition(hour);
            return this;
        }

        public Builder setMinute(String minuteText) {
            return setMinute(Integer.parseInt(minuteText));
        }

        public Builder setMinute(int minute) {
            if (minute < 0) {
                minute = 0;
            } else if (minute > this.mMinuteAdapter.getCount() - 1) {
                minute = this.mMinuteAdapter.getCount() - 1;
            }
            this.mMinuteView.scrollToPosition(minute);
            return this;
        }

        public Builder setSecond(String secondText) {
            return setSecond(Integer.parseInt(secondText));
        }

        public Builder setSecond(int second) {
            if (second < 0) {
                second = 0;
            } else if (second > this.mSecondAdapter.getCount() - 1) {
                second = this.mSecondAdapter.getCount() - 1;
            }
            this.mSecondView.scrollToPosition(second);
            return this;
        }

        @SingleClick
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.tv_ui_confirm) {
                autoDismiss();
                OnListener onListener = this.mListener;
                if (onListener != null) {
                    onListener.onSelected(getDialog(), this.mHourManager.getPickedPosition(), this.mMinuteManager.getPickedPosition(), this.mSecondManager.getPickedPosition());
                }
            } else if (id == R.id.tv_ui_cancel) {
                autoDismiss();
                OnListener onListener2 = this.mListener;
                if (onListener2 != null) {
                    onListener2.onCancel(getDialog());
                }
            }
        }
    }

    private static final class PickerAdapter extends AppAdapter<String> {
        private PickerAdapter(Context context) {
            super(context);
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            return new ViewHolder();
        }

        private final class ViewHolder extends BaseAdapter<BaseAdapter<?>.ViewHolder>.ViewHolder {
            private final TextView mPickerView = ((TextView) findViewById(R.id.tv_picker_name));

            ViewHolder() {
                super((BaseAdapter) PickerAdapter.this, (int) R.layout.picker_item);
            }

            public void onBindView(int position) {
                this.mPickerView.setText((CharSequence) PickerAdapter.this.getItem(position));
            }
        }
    }
}
