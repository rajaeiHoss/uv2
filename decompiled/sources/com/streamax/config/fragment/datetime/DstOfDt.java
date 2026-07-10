package com.streamax.config.fragment.datetime;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pickview.TimePickerView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.StringUtils;
import com.streamax.utils.TimeUtils;
import com.streamax.utils.UiUtils;
import com.streamax.view.IvView;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class DstOfDt extends ConfigFragment implements BaseListener.GetListener, BaseListener.SetListener, TimeUtils.TimePickListener {
    public static final int INDEX_EMON = 4;
    public static final int INDEX_ETIME = 7;
    public static final int INDEX_EWEEK = 5;
    public static final int INDEX_EWIND = 6;
    public static final int INDEX_SMON = 0;
    public static final int INDEX_STIME = 3;
    public static final int INDEX_SWEEK = 1;
    public static final int INDEX_SWIND = 2;
    public static final int TIMETYPE_ENDDATETIME = 4;
    public static final int TIMETYPE_ENDTIME = 3;
    public static final int TIMETYPE_STARTDATETIME = 2;
    public static final int TIMETYPE_STARTTIME = 1;
    public static final String mTimePrefix = "2017-07-07 ";
    private JSONObject mDstObj;
    private JSONObject mDstRes;
    public IvView mIvEnable;
    public List<Integer> mListIntEndDay = new ArrayList();
    public List<Integer> mListIntEndMonth = new ArrayList();
    public List<Integer> mListIntEndWeek = new ArrayList();
    public List<Integer> mListIntMode = new ArrayList();
    public List<Integer> mListIntOffset = new ArrayList();
    public List<Integer> mListIntStartDay = new ArrayList();
    public List<Integer> mListIntStartMonth = new ArrayList();
    public List<Integer> mListIntStartWeek = new ArrayList();
    public ArrayList<String> mListStrEndDay = new ArrayList<>();
    public ArrayList<String> mListStrEndMonth = new ArrayList<>();
    public ArrayList<String> mListStrEndWeek = new ArrayList<>();
    public ArrayList<String> mListStrMode = new ArrayList<>();
    public ArrayList<String> mListStrOffset = new ArrayList<>();
    public ArrayList<String> mListStrStartDay = new ArrayList<>();
    public ArrayList<String> mListStrStartMonth = new ArrayList<>();
    public ArrayList<String> mListStrStartWeek = new ArrayList<>();
    public LinearLayout mLlEnd1;
    public LinearLayout mLlEnd2;
    public LinearLayout mLlStart1;
    public LinearLayout mLlStart2;
    public RelativeLayout mRlEndDay;
    public RelativeLayout mRlEndMonth;
    public RelativeLayout mRlEndWeek;
    public RelativeLayout mRlMode;
    public RelativeLayout mRlOffset;
    public RelativeLayout mRlStartDay;
    public RelativeLayout mRlStartMonth;
    public RelativeLayout mRlStartWeek;
    public int mTimeType;
    public VsTextView mTvEndDateTime;
    public TextView mTvEndDay;
    public TextView mTvEndMonth;
    public TextView mTvEndTime;
    public TextView mTvEndWeek;
    public TextView mTvMode;
    public TextView mTvOffset;
    public VsTextView mTvStartDateTime;
    public TextView mTvStartDay;
    public TextView mTvStartMonth;
    public TextView mTvStartTime;
    public TextView mTvStartWeek;

    public BaseFragment getCurFragment() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_dateTime_dst_Title);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_datetime_dst, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    private void initChildView() {
        this.mIvEnable = (IvView) this.mRootView.findViewById(R.id.config_datetime_dst_iv_enable);
        this.mRlMode = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_dst_rl_mode);
        this.mTvMode = (TextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_mode);
        this.mRlOffset = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_dst_rl_offset);
        this.mTvOffset = (TextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_offset);
        this.mLlStart1 = (LinearLayout) this.mRootView.findViewById(R.id.config_datetime_dst_ll_startmwdhms);
        this.mRlStartMonth = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_dst_rl_startmonth);
        this.mTvStartMonth = (TextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_startmonth);
        this.mRlStartWeek = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_dst_rl_startweek);
        this.mTvStartWeek = (TextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_startweek);
        this.mRlStartDay = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_dst_rl_startday);
        this.mTvStartDay = (TextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_startday);
        this.mTvStartTime = (TextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_starttime);
        this.mLlStart2 = (LinearLayout) this.mRootView.findViewById(R.id.config_datetime_dst_ll_startdatetime);
        this.mTvStartDateTime = (VsTextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_startdatetime);
        this.mLlEnd1 = (LinearLayout) this.mRootView.findViewById(R.id.config_datetime_dst_ll_endmwdhms);
        this.mRlEndMonth = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_dst_rl_endmonth);
        this.mTvEndMonth = (TextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_endmonth);
        this.mRlEndWeek = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_dst_rl_endweek);
        this.mTvEndWeek = (TextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_endweek);
        this.mRlEndDay = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_dst_rl_endday);
        this.mTvEndDay = (TextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_endday);
        this.mTvEndTime = (TextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_endtime);
        this.mLlEnd2 = (LinearLayout) this.mRootView.findViewById(R.id.config_datetime_dst_ll_enddatetime);
        this.mTvEndDateTime = (VsTextView) this.mRootView.findViewById(R.id.config_datetime_dst_tv_enddatetime);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void refreshUi() {
        JSONObject dstResponse = this.mDstRes;
        if (dstResponse != null) {
            try {
                JSONObject deviceObj = dstResponse.getJSONObject("DEVEMM");
                if (deviceObj != null) {
                    JSONObject timeObj = deviceObj.getJSONObject("TIMEP");
                    if (timeObj != null) {
                        this.mDstObj = timeObj.getJSONObject("DST");
                        if (this.mDstObj != null) {
                            this.mIvEnable.SetImageResource(this.mDstObj.getInt("SW") == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                            int mode = this.mDstObj.getInt("DSTM");
                            this.mListStrMode.clear();
                            this.mListIntMode.clear();
                            List<String> modeOptions = getStrDatas(R.array.ModeSelector);
                            int modeIndex = mode - 1;
                            if (modeIndex >= 0 && modeIndex < modeOptions.size()) {
                                this.mTvMode.setText(modeOptions.get(modeIndex));
                                this.mListStrMode.addAll(modeOptions);
                                this.mListIntMode.add(new Integer(modeIndex));
                            }
                            int offset = this.mDstObj.getInt("DSTS");
                            this.mListStrOffset.clear();
                            this.mListIntOffset.clear();
                            List<String> offsetOptions = getStrDatas(R.array.OffsetSelector);
                            int offsetIndex = offset - 1;
                            if (offsetIndex >= 0 && offsetIndex < offsetOptions.size()) {
                                this.mTvOffset.setText(offsetOptions.get(offsetIndex));
                                this.mListStrOffset.addAll(offsetOptions);
                                this.mListIntOffset.add(new Integer(offsetIndex));
                            }
                            if (mode == 1) {
                                this.mLlStart1.setVisibility(0);
                                this.mLlStart2.setVisibility(8);
                                int startMonth = this.mDstObj.getInt("SMON");
                                this.mListStrStartMonth.clear();
                                this.mListIntStartMonth.clear();
                                List<String> monthOptions = getStrDatas(R.array.MonthSelector);
                                if (startMonth >= 0 && startMonth < monthOptions.size()) {
                                    this.mTvStartMonth.setText(monthOptions.get(startMonth));
                                    this.mListStrStartMonth.addAll(monthOptions);
                                    this.mListIntStartMonth.add(new Integer(startMonth));
                                }
                                int startWeek = this.mDstObj.getInt("SWEEK");
                                this.mListStrStartWeek.clear();
                                this.mListIntStartWeek.clear();
                                List<String> weekOptions = getStrDatas(R.array.WeekSelector);
                                if (startWeek >= 0 && startWeek < weekOptions.size()) {
                                    this.mTvStartWeek.setText(weekOptions.get(startWeek));
                                    this.mListStrStartWeek.addAll(weekOptions);
                                    this.mListIntStartWeek.add(new Integer(startWeek));
                                }
                                int startDay = this.mDstObj.getInt("SWIND");
                                this.mListStrStartDay.clear();
                                this.mListIntStartDay.clear();
                                List<String> dayOptions = getStrDatas(R.array.DaySelector);
                                if (startDay >= 0 && startDay < dayOptions.size()) {
                                    this.mTvStartDay.setText(dayOptions.get(startDay));
                                    this.mListStrStartDay.addAll(dayOptions);
                                    this.mListIntStartDay.add(new Integer(startDay));
                                }
                                this.mTvStartTime.setText(TimeUtils.parse2Str_86399(this.mDstObj.getInt("SH"), this.mDstObj.getInt("SM"), this.mDstObj.getInt("SS")));
                            } else {
                                this.mLlStart1.setVisibility(8);
                                this.mLlStart2.setVisibility(0);
                                this.mTvStartDateTime.setText(timeLong2String("yyyy-MM-dd HH:mm:ss", "GMT", this.mDstObj.getLong("STARTTIME") * 1000));
                            }
                            if (mode == 1) {
                                this.mLlEnd1.setVisibility(0);
                                this.mLlEnd2.setVisibility(8);
                                int endMonth = this.mDstObj.getInt("EMON");
                                this.mListStrEndMonth.clear();
                                this.mListIntEndMonth.clear();
                                List<String> monthOptions = getStrDatas(R.array.MonthSelector);
                                if (endMonth >= 0 && endMonth < monthOptions.size()) {
                                    this.mTvEndMonth.setText(monthOptions.get(endMonth));
                                    this.mListStrEndMonth.addAll(monthOptions);
                                    this.mListIntEndMonth.add(new Integer(endMonth));
                                }
                                int endWeek = this.mDstObj.getInt("EWEEK");
                                this.mListStrEndWeek.clear();
                                this.mListIntEndWeek.clear();
                                List<String> weekOptions = getStrDatas(R.array.WeekSelector);
                                if (endWeek >= 0 && endWeek < weekOptions.size()) {
                                    this.mTvEndWeek.setText(weekOptions.get(endWeek));
                                    this.mListStrEndWeek.addAll(weekOptions);
                                    this.mListIntEndWeek.add(new Integer(endWeek));
                                }
                                int endDay = this.mDstObj.getInt("EWIND");
                                this.mListStrEndDay.clear();
                                this.mListIntEndDay.clear();
                                List<String> dayOptions = getStrDatas(R.array.DaySelector);
                                if (endDay >= 0 && endDay < dayOptions.size()) {
                                    this.mTvEndDay.setText(dayOptions.get(endDay));
                                    this.mListStrEndDay.addAll(dayOptions);
                                    this.mListIntEndDay.add(new Integer(endDay));
                                }
                                this.mTvEndTime.setText(TimeUtils.parse2Str_86399(this.mDstObj.getInt("EH"), this.mDstObj.getInt("EM"), this.mDstObj.getInt("ES")));
                                return;
                            }
                            this.mLlEnd1.setVisibility(8);
                            this.mLlEnd2.setVisibility(0);
                            this.mTvEndDateTime.setText(timeLong2String("yyyy-MM-dd HH:mm:ss", "GMT", this.mDstObj.getLong("ENDTIME") * 1000));
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mIvEnable.setOnClickListener(this);
        this.mRlMode.setOnClickListener(this);
        this.mRlOffset.setOnClickListener(this);
        this.mRlStartMonth.setOnClickListener(this);
        this.mRlStartWeek.setOnClickListener(this);
        this.mRlStartDay.setOnClickListener(this);
        this.mTvStartTime.setOnClickListener(this);
        this.mLlStart2.setOnClickListener(this);
        this.mRlEndMonth.setOnClickListener(this);
        this.mRlEndWeek.setOnClickListener(this);
        this.mRlEndDay.setOnClickListener(this);
        this.mTvEndTime.setOnClickListener(this);
        this.mLlEnd2.setOnClickListener(this);
    }

    public void saveEnable() {
        JSONObject jSONObject = this.mDstObj;
        if (jSONObject != null) {
            try {
                this.mDstObj.put("SW", jSONObject.getInt("SW") == 0 ? 1 : 0);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForMode() {
        if (this.mDstObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Mode), "SelectFragmentForMode", 0, this.mListStrMode, this.mListIntMode);
        }
    }

    public void pushFragmentForOffset() {
        if (this.mDstObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_dateTime_dst_Offset), "SelectFragmentForOffset", 0, this.mListStrOffset, this.mListIntOffset);
        }
    }

    public void pushFragmentForStartMonth() {
        if (this.mDstObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Month), "SelectFragmentForStartMonth", 0, this.mListStrStartMonth, this.mListIntStartMonth);
        }
    }

    public void pushFragmentForStartWeek() {
        if (this.mDstObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Week), "SelectFragmentForStartWeek", 0, this.mListStrStartWeek, this.mListIntStartWeek);
        }
    }

    public void pushFragmentForStartDay() {
        if (this.mDstObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Day), "SelectFragmentForStartDay", 0, this.mListStrStartDay, this.mListIntStartDay);
        }
    }

    public void setTime(TextView textView) {
        if (this.mDstObj != null) {
            String string = StringUtils.getString(textView);
            TimePickerView.Type type = TimePickerView.Type.ALL;
            int i = this.mTimeType;
            if (i == 1 || i == 3) {
                type = TimePickerView.Type.HOURS_MINS_SECS;
                string = mTimePrefix + string;
            }
            try {
                TimeUtils.setTimePickListener(this.mConfigUi, textView, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(string), type, this);
            } catch (ParseException unused) {
            }
        }
    }

    public void pushFragmentForEndMonth() {
        if (this.mDstObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Month), "SelectFragmentForEndMonth", 0, this.mListStrEndMonth, this.mListIntEndMonth);
        }
    }

    public void pushFragmentForEndWeek() {
        if (this.mDstObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Week), "SelectFragmentForEndWeek", 0, this.mListStrEndWeek, this.mListIntEndWeek);
        }
    }

    public void pushFragmentForEndDay() {
        if (this.mDstObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Day), "SelectFragmentForEndDay", 0, this.mListStrEndDay, this.mListIntEndDay);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.config_datetime_dst_iv_enable /*2131362080*/:
                saveEnable();
                return;
            case R.id.config_datetime_dst_ll_enddatetime /*2131362081*/:
                this.mTimeType = 4;
                setTime(this.mTvEndDateTime);
                return;
            default:
                switch (id) {
                    case R.id.config_datetime_dst_ll_startdatetime /*2131362083*/:
                        this.mTimeType = 2;
                        setTime(this.mTvStartDateTime);
                        return;
                    case R.id.config_datetime_dst_tv_endtime /*2131362097*/:
                        this.mTimeType = 3;
                        setTime(this.mTvEndTime);
                        return;
                    case R.id.config_datetime_dst_tv_starttime /*2131362104*/:
                        this.mTimeType = 1;
                        setTime(this.mTvStartTime);
                        return;
                    case R.id.config_title_btn_back /*2131362205*/:
                        prePage();
                        return;
                    default:
                        switch (id) {
                            case R.id.config_datetime_dst_rl_endday /*2131362086*/:
                                pushFragmentForEndDay();
                                return;
                            case R.id.config_datetime_dst_rl_endmonth /*2131362087*/:
                                pushFragmentForEndMonth();
                                return;
                            case R.id.config_datetime_dst_rl_endweek /*2131362088*/:
                                pushFragmentForEndWeek();
                                return;
                            case R.id.config_datetime_dst_rl_mode /*2131362089*/:
                                pushFragmentForMode();
                                return;
                            case R.id.config_datetime_dst_rl_offset /*2131362090*/:
                                pushFragmentForOffset();
                                return;
                            case R.id.config_datetime_dst_rl_startday /*2131362091*/:
                                pushFragmentForStartDay();
                                return;
                            case R.id.config_datetime_dst_rl_startmonth /*2131362092*/:
                                pushFragmentForStartMonth();
                                return;
                            case R.id.config_datetime_dst_rl_startweek /*2131362093*/:
                                pushFragmentForStartWeek();
                                return;
                            default:
                                return;
                        }
                }
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("TIMEP", "?");
            jSONObject.put("DEVEMM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String response) {
        try {
            LogUtils.e("DstOfDt", "getSuccess 1, result: " + response);
            this.mDstRes = new JSONObject(response);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public void getFailure() {
        LogUtils.e("DstOfDt", "getFailure 1");
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mDstRes;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }

    public void setTimePickListener(TextView textView, Date date) {
        String startValue;
        String endValue;
        long startTime;
        long endTime;
        int timeType = this.mTimeType;
        if (timeType == TIMETYPE_STARTTIME || timeType == TIMETYPE_ENDTIME) {
            startValue = StringUtils.getString(this.mTvStartTime);
            endValue = StringUtils.getString(this.mTvEndTime);
            String selectedTime = TimeUtils.getTime(date, TimeUtils.HMS);
            if (timeType == TIMETYPE_STARTTIME) {
                startValue = selectedTime;
            } else {
                endValue = selectedTime;
            }
            startTime = (long) parse2Int_86399(startValue);
            endTime = (long) parse2Int_86399(endValue);
            int validationIndex = timeType == TIMETYPE_STARTTIME ? INDEX_STIME : INDEX_ETIME;
            int selectedSeconds = (int) (timeType == TIMETYPE_STARTTIME ? startTime : endTime);
            if (!isValidWeekDateTime(validationIndex, selectedSeconds)) {
                toastSf((int) R.string.time_is_error);
                return;
            }
        } else {
            startValue = StringUtils.getString(this.mTvStartDateTime);
            endValue = StringUtils.getString(this.mTvEndDateTime);
            String selectedDateTime = TimeUtils.getTime(date, TimeUtils.YMDHMS);
            if (timeType == TIMETYPE_STARTDATETIME) {
                startValue = selectedDateTime;
            } else {
                endValue = selectedDateTime;
            }
            startTime = parseString2Long(TimeUtils.YMDHMS, "GMT", startValue) / 1000;
            endTime = parseString2Long(TimeUtils.YMDHMS, "GMT", endValue) / 1000;
            if (startTime >= endTime) {
                toastSf((int) R.string.time_is_error);
                return;
            }
        }
        if (this.mDstObj == null) {
            return;
        }
        try {
            if (timeType == TIMETYPE_STARTTIME || timeType == TIMETYPE_ENDTIME) {
                int startHour = (int) (startTime / 3600);
                long startRemainder = startTime - ((long) (startHour * 3600));
                int startMinute = (int) (startRemainder / 60);
                int startSecond = (int) (startRemainder % 60);
                this.mDstObj.put("SH", startHour);
                this.mDstObj.put("SM", startMinute);
                this.mDstObj.put("SS", startSecond);

                int endHour = (int) (endTime / 3600);
                long endRemainder = endTime - ((long) (endHour * 3600));
                int endMinute = (int) (endRemainder / 60);
                int endSecond = (int) (endRemainder % 60);
                this.mDstObj.put("EH", endHour);
                this.mDstObj.put("EM", endMinute);
                this.mDstObj.put("ES", endSecond);
            } else {
                this.mDstObj.put("STARTTIME", startTime);
                this.mDstObj.put("ENDTIME", endTime);
            }
            NetPresenter.getDefault().setConfig(this);
        } catch (JSONException unused) {
        }
    }

    public void updateDateForMode(int i) {
        JSONObject jSONObject = this.mDstObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("DSTM", i + 1);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForOffset(int i) {
        JSONObject jSONObject = this.mDstObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("DSTS", i + 1);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    private boolean isValidWeekDateTime(int fieldIndex, int selectedValue) {
        try {
            int startMonth = this.mDstObj.getInt("SMON");
            int startWeek = this.mDstObj.getInt("SWEEK");
            int endMonth = this.mDstObj.getInt("EMON");
            int endWeek = this.mDstObj.getInt("EWEEK");
            if (fieldIndex != INDEX_SMON) {
                if (fieldIndex == INDEX_SWEEK) {
                    startWeek = selectedValue;
                } else if (fieldIndex == INDEX_EMON) {
                    endMonth = selectedValue;
                } else if (fieldIndex == INDEX_EWEEK) {
                    endWeek = selectedValue;
                }
                selectedValue = startMonth;
            }
            return selectedValue != endMonth || startWeek != endWeek;
        } catch (JSONException unused) {
            return false;
        }
    }

    public void updateDateForStartMonth(int i) {
        if (this.mDstObj != null) {
            if (!isValidWeekDateTime(0, i)) {
                toastSf((int) R.string.time_is_error);
                return;
            }
            try {
                this.mDstObj.put("SMON", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForStartWeek(int i) {
        if (this.mDstObj != null) {
            if (!isValidWeekDateTime(1, i)) {
                toastSf((int) R.string.time_is_error);
                return;
            }
            try {
                this.mDstObj.put("SWEEK", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForStartDay(int i) {
        if (this.mDstObj != null) {
            if (!isValidWeekDateTime(2, i)) {
                toastSf((int) R.string.time_is_error);
                return;
            }
            try {
                this.mDstObj.put("SWIND", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForEndMonth(int i) {
        if (this.mDstObj != null) {
            if (!isValidWeekDateTime(4, i)) {
                toastSf((int) R.string.time_is_error);
                return;
            }
            try {
                this.mDstObj.put("EMON", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForEndWeek(int i) {
        if (this.mDstObj != null) {
            if (!isValidWeekDateTime(5, i)) {
                toastSf((int) R.string.time_is_error);
                return;
            }
            try {
                this.mDstObj.put("EWEEK", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForEndDay(int i) {
        if (this.mDstObj != null) {
            if (!isValidWeekDateTime(6, i)) {
                toastSf((int) R.string.time_is_error);
                return;
            }
            try {
                this.mDstObj.put("EWIND", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String fragmentName, List<Integer> list) {
        if (fragmentName.equals("SelectFragmentForMode")) {
            if (list.size() > 0) {
                updateDateForMode(list.get(0).intValue());
            }
        } else if (fragmentName.equals("SelectFragmentForOffset")) {
            if (list.size() > 0) {
                updateDateForOffset(list.get(0).intValue());
            }
        } else if (fragmentName.equals("SelectFragmentForStartMonth")) {
            if (list.size() > 0) {
                updateDateForStartMonth(list.get(0).intValue());
            }
        } else if (fragmentName.equals("SelectFragmentForStartWeek")) {
            if (list.size() > 0) {
                updateDateForStartWeek(list.get(0).intValue());
            }
        } else if (fragmentName.equals("SelectFragmentForStartDay")) {
            if (list.size() > 0) {
                updateDateForStartDay(list.get(0).intValue());
            }
        } else if (fragmentName.equals("SelectFragmentForEndMonth")) {
            if (list.size() > 0) {
                updateDateForEndMonth(list.get(0).intValue());
            }
        } else if (fragmentName.equals("SelectFragmentForEndWeek")) {
            if (list.size() > 0) {
                updateDateForEndWeek(list.get(0).intValue());
            }
        } else if (fragmentName.equals("SelectFragmentForEndDay") && list.size() > 0) {
            updateDateForEndDay(list.get(0).intValue());
        }
    }
}
