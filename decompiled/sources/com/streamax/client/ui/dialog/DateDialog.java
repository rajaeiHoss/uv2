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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateDialog {

    public interface OnListener {

        /* renamed from: com.streamax.client.ui.dialog.DateDialog$OnListener$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onCancel(OnListener onListener, BaseDialog baseDialog) {
            }
        }

        void onCancel(BaseDialog baseDialog);

        void onSelected(BaseDialog baseDialog, int i, int i2, int i3);
    }

    public static final class Builder extends CommonDialog.Builder<Builder> implements Runnable, PickerLayoutManager.OnPickerListener {
        private final PickerAdapter mDayAdapter;
        private final PickerLayoutManager mDayManager;
        private final RecyclerView mDayView;
        private OnListener mListener;
        private final PickerAdapter mMonthAdapter;
        private final PickerLayoutManager mMonthManager;
        private final RecyclerView mMonthView;
        private final int mStartYear;
        private final PickerAdapter mYearAdapter;
        private final PickerLayoutManager mYearManager;
        private final RecyclerView mYearView;

        public Builder(Context context) {
            this(context, Calendar.getInstance(Locale.CHINA).get(1) - 100);
        }

        public Builder(Context context, int i) {
            this(context, i, Calendar.getInstance(Locale.CHINA).get(1));
        }

        public Builder(Context context, int i, int i2) {
            super(context);
            this.mStartYear = i;
            setCustomView((int) R.layout.date_dialog);
            setTitle((int) R.string.time_title);
            this.mYearView = (RecyclerView) findViewById(R.id.rv_date_year);
            this.mMonthView = (RecyclerView) findViewById(R.id.rv_date_month);
            this.mDayView = (RecyclerView) findViewById(R.id.rv_date_day);
            this.mYearAdapter = new PickerAdapter(context);
            this.mMonthAdapter = new PickerAdapter(context);
            this.mDayAdapter = new PickerAdapter(context);
            ArrayList arrayList = new ArrayList(10);
            while (i <= i2) {
                arrayList.add(i + " " + getString(R.string.common_year));
                i++;
            }
            ArrayList arrayList2 = new ArrayList(12);
            for (int i3 = 1; i3 <= 12; i3++) {
                arrayList2.add(i3 + " " + getString(R.string.common_month));
            }
            Calendar instance = Calendar.getInstance(Locale.CHINA);
            int actualMaximum = instance.getActualMaximum(5);
            ArrayList arrayList3 = new ArrayList(actualMaximum);
            for (int i4 = 1; i4 <= actualMaximum; i4++) {
                arrayList3.add(i4 + " " + getString(R.string.common_day));
            }
            this.mYearAdapter.setData(arrayList);
            this.mMonthAdapter.setData(arrayList2);
            this.mDayAdapter.setData(arrayList3);
            PickerLayoutManager build = new PickerLayoutManager.Builder(context).build();
            this.mYearManager = build;
            PickerLayoutManager build2 = new PickerLayoutManager.Builder(context).build();
            this.mMonthManager = build2;
            PickerLayoutManager build3 = new PickerLayoutManager.Builder(context).build();
            this.mDayManager = build3;
            this.mYearView.setLayoutManager(build);
            this.mMonthView.setLayoutManager(build2);
            this.mDayView.setLayoutManager(build3);
            this.mYearView.setAdapter(this.mYearAdapter);
            this.mMonthView.setAdapter(this.mMonthAdapter);
            this.mDayView.setAdapter(this.mDayAdapter);
            setYear(instance.get(1));
            setMonth(instance.get(2) + 1);
            setDay(instance.get(5));
            build.setOnPickerListener(this);
            build2.setOnPickerListener(this);
        }

        public Builder setListener(OnListener onListener) {
            this.mListener = onListener;
            return this;
        }

        public Builder setIgnoreDay() {
            this.mDayView.setVisibility(8);
            return this;
        }

        public Builder setDate(long j) {
            if (j > 0) {
                setDate(new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date(j)));
            }
            return this;
        }

        public Builder setDate(String str) {
            if (str.matches("\\d{8}")) {
                setYear(str.substring(0, 4));
                setMonth(str.substring(4, 6));
                setDay(str.substring(6, 8));
            } else if (str.matches("\\d{4}-\\d{2}-\\d{2}")) {
                setYear(str.substring(0, 4));
                setMonth(str.substring(5, 7));
                setDay(str.substring(8, 10));
            }
            return this;
        }

        public Builder setYear(String str) {
            return setYear(Integer.parseInt(str));
        }

        public Builder setYear(int i) {
            int i2 = i - this.mStartYear;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 > this.mYearAdapter.getCount() - 1) {
                i2 = this.mYearAdapter.getCount() - 1;
            }
            this.mYearView.scrollToPosition(i2);
            refreshMonthMaximumDay();
            return this;
        }

        public Builder setMonth(String str) {
            return setMonth(Integer.parseInt(str));
        }

        public Builder setMonth(int i) {
            int i2 = i - 1;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 > this.mMonthAdapter.getCount() - 1) {
                i2 = this.mMonthAdapter.getCount() - 1;
            }
            this.mMonthView.scrollToPosition(i2);
            refreshMonthMaximumDay();
            return this;
        }

        public Builder setDay(String str) {
            return setDay(Integer.parseInt(str));
        }

        public Builder setDay(int i) {
            int i2 = i - 1;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 > this.mDayAdapter.getCount() - 1) {
                i2 = this.mDayAdapter.getCount() - 1;
            }
            this.mDayView.scrollToPosition(i2);
            refreshMonthMaximumDay();
            return this;
        }

        @SingleClick
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.tv_ui_confirm) {
                autoDismiss();
                OnListener onListener = this.mListener;
                if (onListener != null) {
                    onListener.onSelected(getDialog(), this.mStartYear + this.mYearManager.getPickedPosition(), this.mMonthManager.getPickedPosition() + 1, this.mDayManager.getPickedPosition() + 1);
                }
            } else if (id == R.id.tv_ui_cancel) {
                autoDismiss();
                OnListener onListener2 = this.mListener;
                if (onListener2 != null) {
                    onListener2.onCancel(getDialog());
                }
            }
        }

        public void onPicked(RecyclerView recyclerView, int i) {
            refreshMonthMaximumDay();
        }

        public void run() {
            Calendar instance = Calendar.getInstance(Locale.CHINA);
            instance.set(this.mStartYear + this.mYearManager.getPickedPosition(), this.mMonthManager.getPickedPosition(), 1);
            int actualMaximum = instance.getActualMaximum(5);
            if (this.mDayAdapter.getCount() != actualMaximum) {
                ArrayList arrayList = new ArrayList(actualMaximum);
                for (int i = 1; i <= actualMaximum; i++) {
                    arrayList.add(i + " " + getString(R.string.common_day));
                }
                this.mDayAdapter.setData(arrayList);
            }
        }

        private void refreshMonthMaximumDay() {
            this.mYearView.removeCallbacks(this);
            this.mYearView.post(this);
        }

        private static final class PickerAdapter extends AppAdapter<String> {
            private PickerAdapter(Context context) {
                super(context);
            }

            public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new ViewHolder();
            }

            private final class ViewHolder extends BaseAdapter<BaseAdapter<?>.ViewHolder>.ViewHolder {
                private final TextView mPickerView = ((TextView) findViewById(R.id.tv_picker_name));

                ViewHolder() {
                    super((BaseAdapter) PickerAdapter.this, (int) R.layout.picker_item);
                }

                public void onBindView(int i) {
                    this.mPickerView.setText((CharSequence) PickerAdapter.this.getItem(i));
                }
            }
        }
    }
}
