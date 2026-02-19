package com.pickview.view;

import android.view.View;
import com.pickview.TimePickerView;
import com.pickview.adapter.NumericWheelAdapter;
import com.pickview.lib.WheelView;
import com.pickview.listener.OnItemSelectedListener;
import com.zycs.UView.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class WheelTime {
    public static final int DEFULT_END_YEAR = 2036;
    public static final int DEFULT_START_YEAR = 2001;
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int endYear = DEFULT_END_YEAR;
    /* access modifiers changed from: private */
    public int startYear = 2001;
    private TimePickerView.Type type;
    private View view;
    /* access modifiers changed from: private */
    public WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_mins;
    /* access modifiers changed from: private */
    public WheelView wv_month;
    private WheelView wv_secs;
    /* access modifiers changed from: private */
    public WheelView wv_year;

    public WheelTime(View view2) {
        this.view = view2;
        this.type = TimePickerView.Type.ALL;
        setView(view2);
    }

    public WheelTime(View view2, TimePickerView.Type type2) {
        this.view = view2;
        this.type = type2;
        setView(view2);
    }

    public void setPicker(int i, int i2, int i3) {
        setPicker(i, i2, i3, 0, 0, 0);
    }

    public void setPicker(int i, int i2, int i3, int i4, int i5, int i6) {
        final List asList = Arrays.asList(new String[]{"1", "3", "5", "7", "8", "10", "12"});
        final List asList2 = Arrays.asList(new String[]{"4", "6", "9", "11"});
        this.view.getContext();
        WheelView wheelView = (WheelView) this.view.findViewById(R.id.year);
        this.wv_year = wheelView;
        wheelView.setAdapter(new NumericWheelAdapter(this.startYear, this.endYear));
        this.wv_year.setCurrentItem(i - this.startYear);
        WheelView wheelView2 = (WheelView) this.view.findViewById(R.id.month);
        this.wv_month = wheelView2;
        wheelView2.setAdapter(new NumericWheelAdapter(1, 12));
        this.wv_month.setCurrentItem(i2);
        this.wv_day = (WheelView) this.view.findViewById(R.id.day);
        int i7 = i2 + 1;
        if (asList.contains(String.valueOf(i7))) {
            this.wv_day.setAdapter(new NumericWheelAdapter(1, 31));
        } else if (asList2.contains(String.valueOf(i7))) {
            this.wv_day.setAdapter(new NumericWheelAdapter(1, 30));
        } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
            this.wv_day.setAdapter(new NumericWheelAdapter(1, 28));
        } else {
            this.wv_day.setAdapter(new NumericWheelAdapter(1, 29));
        }
        this.wv_day.setCurrentItem(i3 - 1);
        WheelView wheelView3 = (WheelView) this.view.findViewById(R.id.hour);
        this.wv_hours = wheelView3;
        wheelView3.setAdapter(new NumericWheelAdapter(0, 23));
        this.wv_hours.setCurrentItem(i4);
        WheelView wheelView4 = (WheelView) this.view.findViewById(R.id.min);
        this.wv_mins = wheelView4;
        wheelView4.setAdapter(new NumericWheelAdapter(0, 59));
        this.wv_mins.setCurrentItem(i5);
        WheelView wheelView5 = (WheelView) this.view.findViewById(R.id.sec);
        this.wv_secs = wheelView5;
        wheelView5.setAdapter(new NumericWheelAdapter(0, 59));
        this.wv_secs.setCurrentItem(i6);
        OnItemSelectedListener yearListener = new OnItemSelectedListener() {
            public void onItemSelected(int i) {
                int access$000 = i + WheelTime.this.startYear;
                int i2 = 28;
                if (asList.contains(String.valueOf(WheelTime.this.wv_month.getCurrentItem() + 1))) {
                    WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                    i2 = 31;
                } else if (asList2.contains(String.valueOf(WheelTime.this.wv_month.getCurrentItem() + 1))) {
                    WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                    i2 = 30;
                } else if ((access$000 % 4 != 0 || access$000 % 100 == 0) && access$000 % 400 != 0) {
                    WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                } else {
                    WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                    i2 = 29;
                }
                int i3 = i2 - 1;
                if (WheelTime.this.wv_day.getCurrentItem() > i3) {
                    WheelTime.this.wv_day.setCurrentItem(i3);
                }
            }
        };
        OnItemSelectedListener monthListener = new OnItemSelectedListener() {
            public void onItemSelected(int i) {
                int i2 = i + 1;
                int i3 = 28;
                if (asList.contains(String.valueOf(i2))) {
                    WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                    i3 = 31;
                } else if (asList2.contains(String.valueOf(i2))) {
                    WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                    i3 = 30;
                } else if (((WheelTime.this.wv_year.getCurrentItem() + WheelTime.this.startYear) % 4 != 0 || (WheelTime.this.wv_year.getCurrentItem() + WheelTime.this.startYear) % 100 == 0) && (WheelTime.this.wv_year.getCurrentItem() + WheelTime.this.startYear) % 400 != 0) {
                    WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                } else {
                    WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                    i3 = 29;
                }
                int i4 = i3 - 1;
                if (WheelTime.this.wv_day.getCurrentItem() > i4) {
                    WheelTime.this.wv_day.setCurrentItem(i4);
                }
            }
        };
        this.wv_year.setOnItemSelectedListener(yearListener);
        this.wv_month.setOnItemSelectedListener(monthListener);
        int i8 = 6;
        int i9 = AnonymousClass3.$SwitchMap$com$pickview$TimePickerView$Type[this.type.ordinal()];
        if (i9 != 1) {
            if (i9 == 2) {
                this.wv_hours.setVisibility(8);
                this.wv_mins.setVisibility(8);
                this.wv_secs.setVisibility(8);
            } else if (i9 == 3) {
                this.wv_year.setVisibility(8);
                this.wv_month.setVisibility(8);
                this.wv_day.setVisibility(8);
            } else if (i9 != 4) {
                if (i9 == 5) {
                    this.wv_day.setVisibility(8);
                    this.wv_hours.setVisibility(8);
                    this.wv_mins.setVisibility(8);
                    this.wv_secs.setVisibility(8);
                }
                float f = (float) i8;
                this.wv_day.setTextSize(f);
                this.wv_month.setTextSize(f);
                this.wv_year.setTextSize(f);
                this.wv_hours.setTextSize(f);
                this.wv_mins.setTextSize(f);
                this.wv_secs.setTextSize(f);
            } else {
                this.wv_year.setVisibility(8);
            }
            i8 = 24;
            float f2 = (float) i8;
            this.wv_day.setTextSize(f2);
            this.wv_month.setTextSize(f2);
            this.wv_year.setTextSize(f2);
            this.wv_hours.setTextSize(f2);
            this.wv_mins.setTextSize(f2);
            this.wv_secs.setTextSize(f2);
        }
        i8 = 18;
        float f22 = (float) i8;
        this.wv_day.setTextSize(f22);
        this.wv_month.setTextSize(f22);
        this.wv_year.setTextSize(f22);
        this.wv_hours.setTextSize(f22);
        this.wv_mins.setTextSize(f22);
        this.wv_secs.setTextSize(f22);
    }

    /* renamed from: com.pickview.view.WheelTime$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$pickview$TimePickerView$Type;

        static {
            int[] iArr = new int[com.pickview.TimePickerView.Type.values().length];
            $SwitchMap$com$pickview$TimePickerView$Type = iArr;
            try {
                iArr[com.pickview.TimePickerView.Type.ALL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.pickview.TimePickerView.Type.YEAR_MONTH_DAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.pickview.TimePickerView.Type.HOURS_MINS_SECS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[com.pickview.TimePickerView.Type.MONTH_DAY_HOUR_MIN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[com.pickview.TimePickerView.Type.YEAR_MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public void setCyclic(boolean z) {
        this.wv_year.setCyclic(z);
        this.wv_month.setCyclic(z);
        this.wv_day.setCyclic(z);
        this.wv_hours.setCyclic(z);
        this.wv_mins.setCyclic(z);
        this.wv_secs.setCyclic(z);
    }

    public String getTime() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.wv_year.getCurrentItem() + this.startYear);
        stringBuffer.append("-");
        stringBuffer.append(this.wv_month.getCurrentItem() + 1);
        stringBuffer.append("-");
        stringBuffer.append(this.wv_day.getCurrentItem() + 1);
        stringBuffer.append(" ");
        stringBuffer.append(this.wv_hours.getCurrentItem());
        stringBuffer.append(":");
        stringBuffer.append(this.wv_mins.getCurrentItem());
        stringBuffer.append(":");
        stringBuffer.append(this.wv_secs.getCurrentItem());
        return stringBuffer.toString();
    }

    public View getView() {
        return this.view;
    }

    public void setView(View view2) {
        this.view = view2;
    }

    public int getStartYear() {
        return this.startYear;
    }

    public void setStartYear(int i) {
        this.startYear = i;
    }

    public int getEndYear() {
        return this.endYear;
    }

    public void setEndYear(int i) {
        this.endYear = i;
    }
}
