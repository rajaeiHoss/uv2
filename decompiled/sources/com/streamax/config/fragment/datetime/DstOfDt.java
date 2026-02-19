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
        JSONObject jSONObject;
        JSONObject jSONObject2 = this.mDstRes;
        if (jSONObject2 != null) {
            try {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("DEVEMM");
                if (jSONObject3 != null && (jSONObject = jSONObject3.getJSONObject("TIMEP")) != null) {
                    JSONObject jSONObject4 = jSONObject.getJSONObject("DST");
                    this.mDstObj = jSONObject4;
                    if (jSONObject4 != null) {
                        this.mIvEnable.SetImageResource(jSONObject4.getInt("SW") == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                        int i = this.mDstObj.getInt("DSTM");
                        this.mListStrMode.clear();
                        this.mListIntMode.clear();
                        List<String> strDatas = getStrDatas(R.array.ModeSelector);
                        int i2 = i - 1;
                        if (i2 >= 0 && i2 < strDatas.size()) {
                            this.mTvMode.setText(strDatas.get(i2));
                            this.mListStrMode.addAll(strDatas);
                            this.mListIntMode.add(new Integer(i2));
                        }
                        int i3 = this.mDstObj.getInt("DSTS");
                        this.mListStrOffset.clear();
                        this.mListIntOffset.clear();
                        List<String> strDatas2 = getStrDatas(R.array.OffsetSelector);
                        int i4 = i3 - 1;
                        if (i4 >= 0 && i4 < strDatas2.size()) {
                            this.mTvOffset.setText(strDatas2.get(i4));
                            this.mListStrOffset.addAll(strDatas2);
                            this.mListIntOffset.add(new Integer(i4));
                        }
                        if (i == 1) {
                            this.mLlStart1.setVisibility(0);
                            this.mLlStart2.setVisibility(8);
                            int i5 = this.mDstObj.getInt("SMON");
                            this.mListStrStartMonth.clear();
                            this.mListIntStartMonth.clear();
                            List<String> strDatas3 = getStrDatas(R.array.MonthSelector);
                            if (i5 >= 0 && i5 < strDatas3.size()) {
                                this.mTvStartMonth.setText(strDatas3.get(i5));
                                this.mListStrStartMonth.addAll(strDatas3);
                                this.mListIntStartMonth.add(new Integer(i5));
                            }
                            int i6 = this.mDstObj.getInt("SWEEK");
                            this.mListStrStartWeek.clear();
                            this.mListIntStartWeek.clear();
                            List<String> strDatas4 = getStrDatas(R.array.WeekSelector);
                            if (i6 >= 0 && i6 < strDatas4.size()) {
                                this.mTvStartWeek.setText(strDatas4.get(i6));
                                this.mListStrStartWeek.addAll(strDatas4);
                                this.mListIntStartWeek.add(new Integer(i6));
                            }
                            int i7 = this.mDstObj.getInt("SWIND");
                            this.mListStrStartDay.clear();
                            this.mListIntStartDay.clear();
                            List<String> strDatas5 = getStrDatas(R.array.DaySelector);
                            if (i7 >= 0 && i7 < strDatas5.size()) {
                                this.mTvStartDay.setText(strDatas5.get(i7));
                                this.mListStrStartDay.addAll(strDatas5);
                                this.mListIntStartDay.add(new Integer(i7));
                            }
                            this.mTvStartTime.setText(TimeUtils.parse2Str_86399(this.mDstObj.getInt("SH"), this.mDstObj.getInt("SM"), this.mDstObj.getInt("SS")));
                        } else {
                            this.mLlStart1.setVisibility(8);
                            this.mLlStart2.setVisibility(0);
                            this.mTvStartDateTime.setText(timeLong2String("yyyy-MM-dd HH:mm:ss", "GMT", this.mDstObj.getLong("STARTTIME") * 1000));
                        }
                        if (i == 1) {
                            this.mLlEnd1.setVisibility(0);
                            this.mLlEnd2.setVisibility(8);
                            int i8 = this.mDstObj.getInt("EMON");
                            this.mListStrEndMonth.clear();
                            this.mListIntEndMonth.clear();
                            List<String> strDatas6 = getStrDatas(R.array.MonthSelector);
                            if (i8 >= 0 && i8 < strDatas6.size()) {
                                this.mTvEndMonth.setText(strDatas6.get(i8));
                                this.mListStrEndMonth.addAll(strDatas6);
                                this.mListIntEndMonth.add(new Integer(i8));
                            }
                            int i9 = this.mDstObj.getInt("EWEEK");
                            this.mListStrEndWeek.clear();
                            this.mListIntEndWeek.clear();
                            List<String> strDatas7 = getStrDatas(R.array.WeekSelector);
                            if (i9 >= 0 && i9 < strDatas7.size()) {
                                this.mTvEndWeek.setText(strDatas7.get(i9));
                                this.mListStrEndWeek.addAll(strDatas7);
                                this.mListIntEndWeek.add(new Integer(i9));
                            }
                            int i10 = this.mDstObj.getInt("EWIND");
                            this.mListStrEndDay.clear();
                            this.mListIntEndDay.clear();
                            List<String> strDatas8 = getStrDatas(R.array.DaySelector);
                            if (i10 >= 0 && i10 < strDatas8.size()) {
                                this.mTvEndDay.setText(strDatas8.get(i10));
                                this.mListStrEndDay.addAll(strDatas8);
                                this.mListIntEndDay.add(new Integer(i10));
                            }
                            this.mTvEndTime.setText(TimeUtils.parse2Str_86399(this.mDstObj.getInt("EH"), this.mDstObj.getInt("EM"), this.mDstObj.getInt("ES")));
                            return;
                        }
                        this.mLlEnd1.setVisibility(8);
                        this.mLlEnd2.setVisibility(0);
                        this.mTvEndDateTime.setText(timeLong2String("yyyy-MM-dd HH:mm:ss", "GMT", this.mDstObj.getLong("ENDTIME") * 1000));
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

    public void getSuccess(String str) {
        try {
            LogUtils.e("DstOfDt", "getSuccess 1, result: " + str);
            this.mDstRes = new JSONObject(str);
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

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001e, code lost:
        if (r11.mTimeType == 2) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0035, code lost:
        if (r11.mTimeType == 1) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0039, code lost:
        r2 = r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setTimePickListener(android.widget.TextView r12, java.util.Date r13) {
        /*
            r11 = this;
            int r12 = r11.mTimeType
            r0 = 3
            r1 = 1
            if (r12 == r1) goto L_0x0021
            if (r12 != r0) goto L_0x0009
            goto L_0x0021
        L_0x0009:
            com.streamax.view.VsTextView r12 = r11.mTvStartDateTime
            java.lang.String r12 = com.streamax.utils.StringUtils.getString(r12)
            com.streamax.view.VsTextView r2 = r11.mTvEndDateTime
            java.lang.String r2 = com.streamax.utils.StringUtils.getString(r2)
            java.lang.String r3 = com.streamax.utils.TimeUtils.YMDHMS
            java.lang.String r13 = com.streamax.utils.TimeUtils.getTime(r13, r3)
            int r3 = r11.mTimeType
            r4 = 2
            if (r3 != r4) goto L_0x0039
            goto L_0x0037
        L_0x0021:
            android.widget.TextView r12 = r11.mTvStartTime
            java.lang.String r12 = com.streamax.utils.StringUtils.getString(r12)
            android.widget.TextView r2 = r11.mTvEndTime
            java.lang.String r2 = com.streamax.utils.StringUtils.getString(r2)
            java.lang.String r3 = com.streamax.utils.TimeUtils.HMS
            java.lang.String r13 = com.streamax.utils.TimeUtils.getTime(r13, r3)
            int r3 = r11.mTimeType
            if (r3 != r1) goto L_0x0039
        L_0x0037:
            r12 = r13
            goto L_0x003a
        L_0x0039:
            r2 = r13
        L_0x003a:
            int r13 = r11.mTimeType
            r3 = 2131755790(0x7f10030e, float:1.914247E38)
            if (r13 == r1) goto L_0x005c
            if (r13 != r0) goto L_0x0044
            goto L_0x005c
        L_0x0044:
            java.lang.String r13 = "yyyy-MM-dd HH:mm:ss"
            java.lang.String r4 = "GMT"
            long r5 = r11.parseString2Long(r13, r4, r12)
            r7 = 1000(0x3e8, double:4.94E-321)
            long r5 = r5 / r7
            long r12 = r11.parseString2Long(r13, r4, r2)
            long r12 = r12 / r7
            int r2 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r2 < 0) goto L_0x007c
            r11.toastSf((int) r3)
            return
        L_0x005c:
            int r12 = r11.parse2Int_86399((java.lang.String) r12)
            long r5 = (long) r12
            int r12 = r11.parse2Int_86399((java.lang.String) r2)
            long r12 = (long) r12
            int r2 = r11.mTimeType
            if (r2 != r1) goto L_0x006c
            r4 = 3
            goto L_0x006d
        L_0x006c:
            r4 = 7
        L_0x006d:
            if (r2 != r1) goto L_0x0071
            int r2 = (int) r5
            goto L_0x0072
        L_0x0071:
            int r2 = (int) r12
        L_0x0072:
            boolean r2 = r11.isValidWeekDateTime(r4, r2)
            if (r2 != 0) goto L_0x007c
            r11.toastSf((int) r3)
            return
        L_0x007c:
            org.json.JSONObject r2 = r11.mDstObj
            if (r2 != 0) goto L_0x0081
            return
        L_0x0081:
            int r3 = r11.mTimeType     // Catch:{ JSONException -> 0x00e0 }
            if (r3 == r1) goto L_0x0095
            if (r3 != r0) goto L_0x0088
            goto L_0x0095
        L_0x0088:
            java.lang.String r0 = "STARTTIME"
            r2.put(r0, r5)     // Catch:{ JSONException -> 0x00e0 }
            org.json.JSONObject r0 = r11.mDstObj     // Catch:{ JSONException -> 0x00e0 }
            java.lang.String r1 = "ENDTIME"
            r0.put(r1, r12)     // Catch:{ JSONException -> 0x00e0 }
            goto L_0x00d9
        L_0x0095:
            r0 = 3600(0xe10, double:1.7786E-320)
            long r3 = r5 / r0
            int r4 = (int) r3     // Catch:{ JSONException -> 0x00e0 }
            int r3 = r4 * 3600
            long r7 = (long) r3     // Catch:{ JSONException -> 0x00e0 }
            long r5 = r5 - r7
            r7 = 60
            long r9 = r5 / r7
            int r3 = (int) r9     // Catch:{ JSONException -> 0x00e0 }
            long r5 = r5 % r7
            int r6 = (int) r5     // Catch:{ JSONException -> 0x00e0 }
            java.lang.String r5 = "SH"
            r2.put(r5, r4)     // Catch:{ JSONException -> 0x00e0 }
            org.json.JSONObject r2 = r11.mDstObj     // Catch:{ JSONException -> 0x00e0 }
            java.lang.String r4 = "SM"
            r2.put(r4, r3)     // Catch:{ JSONException -> 0x00e0 }
            org.json.JSONObject r2 = r11.mDstObj     // Catch:{ JSONException -> 0x00e0 }
            java.lang.String r3 = "SS"
            r2.put(r3, r6)     // Catch:{ JSONException -> 0x00e0 }
            long r0 = r12 / r0
            int r1 = (int) r0     // Catch:{ JSONException -> 0x00e0 }
            int r0 = r1 * 3600
            long r2 = (long) r0     // Catch:{ JSONException -> 0x00e0 }
            long r12 = r12 - r2
            long r2 = r12 / r7
            int r0 = (int) r2     // Catch:{ JSONException -> 0x00e0 }
            long r12 = r12 % r7
            int r13 = (int) r12     // Catch:{ JSONException -> 0x00e0 }
            org.json.JSONObject r12 = r11.mDstObj     // Catch:{ JSONException -> 0x00e0 }
            java.lang.String r2 = "EH"
            r12.put(r2, r1)     // Catch:{ JSONException -> 0x00e0 }
            org.json.JSONObject r12 = r11.mDstObj     // Catch:{ JSONException -> 0x00e0 }
            java.lang.String r1 = "EM"
            r12.put(r1, r0)     // Catch:{ JSONException -> 0x00e0 }
            org.json.JSONObject r12 = r11.mDstObj     // Catch:{ JSONException -> 0x00e0 }
            java.lang.String r0 = "ES"
            r12.put(r0, r13)     // Catch:{ JSONException -> 0x00e0 }
        L_0x00d9:
            com.streamax.config.network.NetPresenter r12 = com.streamax.config.network.NetPresenter.getDefault()
            r12.setConfig(r11)
        L_0x00e0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamax.config.fragment.datetime.DstOfDt.setTimePickListener(android.widget.TextView, java.util.Date):void");
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

    private boolean isValidWeekDateTime(int i, int i2) {
        try {
            int i3 = this.mDstObj.getInt("SMON");
            int i4 = this.mDstObj.getInt("SWEEK");
            int i5 = this.mDstObj.getInt("EMON");
            int i6 = this.mDstObj.getInt("EWEEK");
            if (i != 0) {
                if (i == 1) {
                    i4 = i2;
                } else if (i == 4) {
                    i5 = i2;
                } else if (i == 5) {
                    i6 = i2;
                }
                i2 = i3;
            }
            if (i2 == i5 && i4 == i6) {
                return false;
            }
            return true;
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

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForMode")) {
            if (list.size() > 0) {
                updateDateForMode(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForOffset")) {
            if (list.size() > 0) {
                updateDateForOffset(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForStartMonth")) {
            if (list.size() > 0) {
                updateDateForStartMonth(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForStartWeek")) {
            if (list.size() > 0) {
                updateDateForStartWeek(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForStartDay")) {
            if (list.size() > 0) {
                updateDateForStartDay(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForEndMonth")) {
            if (list.size() > 0) {
                updateDateForEndMonth(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForEndWeek")) {
            if (list.size() > 0) {
                updateDateForEndWeek(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForEndDay") && list.size() > 0) {
            updateDateForEndDay(list.get(0).intValue());
        }
    }
}
