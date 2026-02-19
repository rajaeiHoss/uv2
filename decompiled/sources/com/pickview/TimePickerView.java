package com.pickview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.pickview.view.BasePickerView;
import com.pickview.view.WheelTime;
import com.zycs.UView.R;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class TimePickerView extends BasePickerView implements View.OnClickListener {
    private static final String TAG_CANCEL = "cancel";
    private static final String TAG_SUBMIT = "submit";
    private View btnCancel;
    private View btnSubmit;
    private OnTimeSelectListener timeSelectListener;
    private TextView tvTitle = ((TextView) findViewById(R.id.tvTitle));
    WheelTime wheelTime;

    public interface OnTimeSelectListener {
        void onTimeSelect(Date date);
    }

    public enum Type {
        ALL,
        YEAR_MONTH_DAY,
        HOURS_MINS_SECS,
        MONTH_DAY_HOUR_MIN,
        YEAR_MONTH
    }

    public TimePickerView(Context context, Type type) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.pickerview_time, this.contentContainer);
        View findViewById = findViewById(R.id.btnSubmit);
        this.btnSubmit = findViewById;
        findViewById.setTag("submit");
        View findViewById2 = findViewById(R.id.btnCancel);
        this.btnCancel = findViewById2;
        findViewById2.setTag("cancel");
        this.btnSubmit.setOnClickListener(this);
        this.btnCancel.setOnClickListener(this);
        this.wheelTime = new WheelTime(findViewById(R.id.timepicker), type);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        this.wheelTime.setPicker(instance.get(1), instance.get(2), instance.get(5), instance.get(11), instance.get(12), instance.get(13));
    }

    public void setRange(int i, int i2) {
        this.wheelTime.setStartYear(i);
        this.wheelTime.setEndYear(i2);
    }

    public void setTime(Date date) {
        Calendar instance = Calendar.getInstance();
        if (date == null) {
            instance.setTimeInMillis(System.currentTimeMillis());
        } else {
            instance.setTime(date);
        }
        this.wheelTime.setPicker(instance.get(1), instance.get(2), instance.get(5), instance.get(11), instance.get(12), instance.get(13));
    }

    public void setCyclic(boolean z) {
        this.wheelTime.setCyclic(z);
    }

    public void onClick(View view) {
        if (((String) view.getTag()).equals("cancel")) {
            dismiss();
            return;
        }
        if (this.timeSelectListener != null) {
            try {
                this.timeSelectListener.onTimeSelect(WheelTime.dateFormat.parse(this.wheelTime.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        dismiss();
    }

    public void setOnTimeSelectListener(OnTimeSelectListener onTimeSelectListener) {
        this.timeSelectListener = onTimeSelectListener;
    }

    public void setTitle(String str) {
        this.tvTitle.setText(str);
    }
}
