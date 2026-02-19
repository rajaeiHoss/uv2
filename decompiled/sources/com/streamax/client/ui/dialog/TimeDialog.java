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

        void onSelected(BaseDialog baseDialog, int i, int i2, int i3);
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
            String str;
            String str2;
            String str3;
            setCustomView((int) R.layout.time_dialog);
            setTitle((int) R.string.time_title);
            this.mHourAdapter = new PickerAdapter(context);
            this.mMinuteAdapter = new PickerAdapter(context);
            this.mSecondAdapter = new PickerAdapter(context);
            ArrayList arrayList = new ArrayList(24);
            int i = 0;
            while (true) {
                str = "0";
                if (i > 23) {
                    break;
                }
                StringBuilder sb = new StringBuilder();
                if (i >= 10) {
                    str = "";
                }
                sb.append(str);
                sb.append(i);
                sb.append(" ");
                sb.append(getString(R.string.common_hour));
                arrayList.add(sb.toString());
                i++;
            }
            ArrayList arrayList2 = new ArrayList(60);
            for (int i2 = 0; i2 <= 59; i2++) {
                StringBuilder sb2 = new StringBuilder();
                if (i2 < 10) {
                    str3 = str;
                } else {
                    str3 = "";
                }
                sb2.append(str3);
                sb2.append(i2);
                sb2.append(" ");
                sb2.append(getString(R.string.common_minute));
                arrayList2.add(sb2.toString());
            }
            ArrayList arrayList3 = new ArrayList(60);
            for (int i3 = 0; i3 <= 59; i3++) {
                StringBuilder sb3 = new StringBuilder();
                if (i3 < 10) {
                    str2 = str;
                } else {
                    str2 = "";
                }
                sb3.append(str2);
                sb3.append(i3);
                sb3.append(" ");
                sb3.append(getString(R.string.common_second));
                arrayList3.add(sb3.toString());
            }
            this.mHourAdapter.setData(arrayList);
            this.mMinuteAdapter.setData(arrayList2);
            this.mSecondAdapter.setData(arrayList3);
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

        public Builder setTime(String str) {
            if (str.matches("\\d{6}")) {
                setHour(str.substring(0, 2));
                setMinute(str.substring(2, 4));
                setSecond(str.substring(4, 6));
            } else if (str.matches("\\d{2}:\\d{2}:\\d{2}")) {
                setHour(str.substring(0, 2));
                setMinute(str.substring(3, 5));
                setSecond(str.substring(6, 8));
            }
            return this;
        }

        public Builder setHour(String str) {
            return setHour(Integer.parseInt(str));
        }

        public Builder setHour(int i) {
            if (i < 0 || i == 24) {
                i = 0;
            } else if (i > this.mHourAdapter.getCount() - 1) {
                i = this.mHourAdapter.getCount() - 1;
            }
            this.mHourView.scrollToPosition(i);
            return this;
        }

        public Builder setMinute(String str) {
            return setMinute(Integer.parseInt(str));
        }

        public Builder setMinute(int i) {
            if (i < 0) {
                i = 0;
            } else if (i > this.mMinuteAdapter.getCount() - 1) {
                i = this.mMinuteAdapter.getCount() - 1;
            }
            this.mMinuteView.scrollToPosition(i);
            return this;
        }

        public Builder setSecond(String str) {
            return setSecond(Integer.parseInt(str));
        }

        public Builder setSecond(int i) {
            if (i < 0) {
                i = 0;
            } else if (i > this.mSecondAdapter.getCount() - 1) {
                i = this.mSecondAdapter.getCount() - 1;
            }
            this.mSecondView.scrollToPosition(i);
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
