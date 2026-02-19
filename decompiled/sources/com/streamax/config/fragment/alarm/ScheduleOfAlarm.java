package com.streamax.config.fragment.alarm;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.nearby.messages.Strategy;
import com.pickview.TimePickerView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.fragment.AddPlanFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.StringUtils;
import com.streamax.utils.TimeUtils;
import com.streamax.utils.UiUtils;
import com.streamax.view.RlView;
import com.zycs.UView.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScheduleOfAlarm extends ConfigFragment implements TimeUtils.TimePickListener, AddPlanFragment.AddPlanInterface, BaseListener.SetListener {
    public static final String mTimePrefix = "2017-07-11 ";
    public JSONObject mAlarmRes;
    public Button mBtnAdd;
    public Button mBtnDelete;
    public int mDay;
    public byte[] mDayValue = new byte[Strategy.TTL_SECONDS_MAX];
    public List<Integer> mListIntDay = new ArrayList();
    public List<Integer> mListIntPIdx = new ArrayList();
    public ArrayList<String> mListStrDay = new ArrayList<>();
    public ArrayList<String> mListStrPIdx = new ArrayList<>();
    public LinearLayout mLlPlan;
    public int mPIdx;
    public JSONArray mRSIArr;
    public RelativeLayout mRlDay;
    public RlView mRvEnd;
    public RlView mRvPIdx;
    public RlView mRvStart;
    public int mTimeType;
    public TextView mTvDay;
    public TextView mTvEnd;
    public TextView mTvPIdx;
    public TextView mTvStart;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void setScheduleData(JSONObject jSONObject, JSONArray jSONArray) {
        this.mAlarmRes = jSONObject;
        this.mRSIArr = jSONArray;
    }

    public class PlanInfo {
        int end;
        int start;
        int type;

        public PlanInfo() {
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mDay = 0;
        this.mPIdx = 0;
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_alarm_schedule, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    private void initChildView() {
        this.mRlDay = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_schedule_rl_date);
        this.mTvDay = (TextView) this.mRootView.findViewById(R.id.config_alarm_schedule_tv_date);
        this.mLlPlan = (LinearLayout) this.mRootView.findViewById(R.id.config_alarm_schedule_ll_plan);
        this.mRvPIdx = (RlView) this.mRootView.findViewById(R.id.config_alarm_schedule_rl_plan);
        this.mTvPIdx = (TextView) this.mRootView.findViewById(R.id.config_alarm_schedule_tv_plan);
        this.mRvStart = (RlView) this.mRootView.findViewById(R.id.config_alarm_schedule_rl_starttime);
        this.mTvStart = (TextView) this.mRootView.findViewById(R.id.config_alarm_schedule_tv_starttime);
        this.mRvEnd = (RlView) this.mRootView.findViewById(R.id.config_alarm_schedule_rl_endTime);
        this.mTvEnd = (TextView) this.mRootView.findViewById(R.id.config_alarm_schedule_tv_endtime);
        this.mBtnAdd = (Button) this.mRootView.findViewById(R.id.config_alarm_schedule_btn_add);
        this.mBtnDelete = (Button) this.mRootView.findViewById(R.id.config_alarm_schedule_btn_delete);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        refreshUi(true);
    }

    public void refreshUi(boolean z) {
        JSONArray mergeDuplicates;
        if (this.mRSIArr != null) {
            LogUtils.e("ScheduleOfAlarm", "refreshUi 0 , mRSIArr: " + this.mRSIArr.toString());
            try {
                List<String> strDatas = getStrDatas(R.array.DaySelector);
                JSONArray jSONArray = this.mRSIArr;
                if (jSONArray == null) {
                    return;
                }
                if (jSONArray.length() == strDatas.size()) {
                    this.mListStrDay.clear();
                    this.mListIntDay.clear();
                    if (this.mDay < strDatas.size()) {
                        this.mTvDay.setText(strDatas.get(this.mDay));
                        this.mListStrDay.addAll(strDatas);
                        this.mListIntDay.add(new Integer(this.mDay));
                    }
                    JSONArray jSONArray2 = this.mRSIArr.getJSONArray(this.mDay);
                    if (jSONArray2 != null) {
                        if (z && (mergeDuplicates = mergeDuplicates(new JSONArray(jSONArray2.toString()))) != null) {
                            this.mRSIArr.put(this.mDay, mergeDuplicates);
                        }
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        int i = 0;
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            PlanInfo planInfo = new PlanInfo();
                            planInfo.start = jSONArray2.getJSONObject(i2).getInt("S");
                            planInfo.end = jSONArray2.getJSONObject(i2).getInt("E");
                            if (planInfo.start < planInfo.end) {
                                arrayList.add(planInfo);
                                StringBuilder sb = new StringBuilder();
                                sb.append("");
                                i++;
                                sb.append(i);
                                arrayList2.add(sb.toString());
                            }
                        }
                        this.mListStrPIdx.clear();
                        this.mListIntPIdx.clear();
                        int i3 = this.mPIdx;
                        if (i3 >= 0 && i3 < arrayList2.size()) {
                            TextView textView = this.mTvPIdx;
                            textView.setText("" + (this.mPIdx + 1));
                            this.mListStrPIdx.addAll(arrayList2);
                            this.mListIntPIdx.add(new Integer(this.mPIdx));
                        }
                        if (arrayList.size() > 0) {
                            this.mLlPlan.setVisibility(0);
                            int i4 = this.mPIdx;
                            if (i4 >= 0 && i4 < arrayList.size()) {
                                this.mTvStart.setText(parse2String_86399(((PlanInfo) arrayList.get(this.mPIdx)).start));
                                this.mTvEnd.setText(parse2String_86399(((PlanInfo) arrayList.get(this.mPIdx)).end));
                                return;
                            }
                            return;
                        }
                        this.mLlPlan.setVisibility(8);
                        this.mTvStart.setText("");
                        this.mTvEnd.setText("");
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mRlDay.setOnClickListener(this);
        this.mRvPIdx.setOnClickListener(this);
        this.mRvStart.setOnClickListener(this);
        this.mRvEnd.setOnClickListener(this);
        this.mBtnAdd.setOnClickListener(this);
        this.mBtnDelete.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_alarm_Schedule);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setOnClickListener(this);
    }

    public void pushFragmentForDay() {
        if (this.mRSIArr != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Date), "SelectFragmentForDay", 0, this.mListStrDay, this.mListIntDay);
        }
    }

    public void pushFragmentForPIdx() {
        if (this.mRSIArr != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Plan), "SelectFragmentForPIdx", 0, this.mListStrPIdx, this.mListIntPIdx);
        }
    }

    public void setTime(TextView textView) {
        if (this.mRSIArr != null) {
            try {
                TimeUtils.setTimePickListener(this.mConfigUi, textView, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(mTimePrefix + StringUtils.getString(textView)), TimePickerView.Type.HOURS_MINS_SECS, this);
            } catch (ParseException unused) {
            }
        }
    }

    public void addPlan() {
        JSONArray jSONArray = this.mRSIArr;
        if (jSONArray != null) {
            try {
                JSONArray jSONArray2 = jSONArray.getJSONArray(this.mDay);
                if (jSONArray2 != null) {
                    int i = 0;
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        if (jSONArray2.getJSONObject(i2).getInt("S") <= jSONArray2.getJSONObject(i2).getInt("E")) {
                            i++;
                        }
                    }
                    if (i >= 8) {
                        toastSf((int) R.string.planTotalMoreThanEight);
                        return;
                    }
                    AddPlanFragment addPlanFragment = new AddPlanFragment();
                    addPlanFragment.SetAddPlanInterface(this);
                    addPlanFragment.ShowType(false);
                    nextPage(addPlanFragment);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void deletePlan() {
        JSONArray jSONArray = this.mRSIArr;
        if (jSONArray != null) {
            try {
                JSONArray jSONArray2 = jSONArray.getJSONArray(this.mDay);
                if (jSONArray2 != null) {
                    int i = 0;
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        if (jSONArray2.getJSONObject(i2).getInt("S") <= jSONArray2.getJSONObject(i2).getInt("E")) {
                            i++;
                        }
                    }
                    if (i > 0) {
                        if (i == 1) {
                            this.mPIdx = 0;
                            int i3 = jSONArray2.getJSONObject(0).getInt("S");
                            if (i3 != jSONArray2.getJSONObject(0).getInt("E") || i3 != 0) {
                                jSONArray2.getJSONObject(0).put("S", 0);
                                jSONArray2.getJSONObject(0).put("E", 0);
                                NetPresenter.getDefault().setConfig(this);
                                return;
                            }
                            return;
                        }
                        int i4 = this.mPIdx;
                        if (i4 + 1 == i) {
                            this.mPIdx = i4 - 1;
                        }
                        jSONArray2.remove(i4);
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.config_title_btn_back) {
            switch (id) {
                case R.id.config_alarm_schedule_btn_add /*2131362030*/:
                    addPlan();
                    return;
                case R.id.config_alarm_schedule_btn_delete /*2131362031*/:
                    deletePlan();
                    return;
                default:
                    switch (id) {
                        case R.id.config_alarm_schedule_rl_date /*2131362034*/:
                            pushFragmentForDay();
                            return;
                        case R.id.config_alarm_schedule_rl_endTime /*2131362035*/:
                            this.mTimeType = 1;
                            setTime(this.mTvEnd);
                            return;
                        case R.id.config_alarm_schedule_rl_plan /*2131362036*/:
                            pushFragmentForPIdx();
                            return;
                        case R.id.config_alarm_schedule_rl_starttime /*2131362037*/:
                            this.mTimeType = 0;
                            setTime(this.mTvStart);
                            return;
                        default:
                            return;
                    }
            }
        } else {
            prePage();
        }
    }

    public void setTimePickListener(TextView textView, Date date) {
        JSONObject jSONObject;
        String string = StringUtils.getString(this.mTvStart);
        String string2 = StringUtils.getString(this.mTvEnd);
        String time = TimeUtils.getTime(date, TimeUtils.HMS);
        if (this.mTimeType == 0) {
            string = time;
        } else {
            string2 = time;
        }
        int parse2Int_86399 = parse2Int_86399(string);
        int parse2Int_863992 = parse2Int_86399(string2);
        if (parse2Int_86399 >= parse2Int_863992) {
            toastSf((int) R.string.time_is_error);
            return;
        }
        JSONArray jSONArray = this.mRSIArr;
        if (jSONArray != null) {
            try {
                JSONArray jSONArray2 = jSONArray.getJSONArray(this.mDay);
                if (jSONArray2 != null && (jSONObject = jSONArray2.getJSONObject(this.mPIdx)) != null) {
                    jSONObject.put("S", parse2Int_86399);
                    jSONObject.put("E", parse2Int_863992);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mAlarmRes;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
        LogUtils.e("ScheduleOfAlarm", "setSuccess 1");
        refreshUi(false);
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForDay(int i) {
        if (this.mRSIArr != null && this.mDay != i) {
            this.mDay = i;
            this.mPIdx = 0;
            refreshUi(false);
        }
    }

    public void updateDateForPlan(int i) {
        if (this.mRSIArr != null && this.mPIdx != i) {
            this.mPIdx = i;
            refreshUi(false);
        }
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForDay")) {
            if (list.size() > 0) {
                updateDateForDay(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForPIdx") && list.size() > 0) {
            updateDateForPlan(list.get(0).intValue());
        }
    }

    public JSONArray mergeDuplicates(JSONArray jSONArray) {
        JSONArray jSONArray2 = jSONArray;
        if (jSONArray2 == null) {
            return null;
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.mDayValue;
            if (i < bArr.length) {
                bArr[i] = -1;
                i++;
            } else {
                break;
            }
        }
        int length = jSONArray.length();
        if (length <= 1) {
            return jSONArray2;
        }
        try {
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject jSONObject = jSONArray2.getJSONObject(i2);
                int i3 = jSONObject.getInt("S");
                int i4 = jSONObject.getInt("E");
                LogUtils.e("ScheduleOfAlarm", "mergeDuplicates 3, find 0, s: " + i3 + ", e: " + i4);
                while (i3 <= i4) {
                    if (i3 < 86400) {
                        this.mDayValue[i3] = 1;
                    }
                    i3++;
                }
            }
        } catch (JSONException unused) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        int i6 = -1;
        byte b = 0;
        while (true) {
            if (i5 >= 86400) {
                break;
            }
            if (i6 == -1) {
                byte[] bArr2 = this.mDayValue;
                if (bArr2[i5] == 1) {
                    b = bArr2[i5];
                    LogUtils.e("ScheduleOfAlarm", "mergeDuplicates 3, find 1, start: " + i5);
                    i6 = i5;
                }
            }
            if (i6 != -1) {
                if (this.mDayValue[i5] != b) {
                    int i7 = i5 - 1;
                    if (i6 < i7) {
                        PlanInfo planInfo = new PlanInfo();
                        planInfo.start = i6;
                        planInfo.end = i7;
                        arrayList.add(planInfo);
                        LogUtils.e("ScheduleOfAlarm", "mergeDuplicates 3, find 2, start: " + i6 + ", end: " + i7 + ", mDayValue[m]: " + this.mDayValue[i5]);
                    }
                    b = this.mDayValue[i5];
                    i6 = -1;
                }
            }
            int i8 = i5 + 1;
            if (i8 != 86400 || i6 == -1) {
                i5 = i8;
            } else if (i6 < i5) {
                PlanInfo planInfo2 = new PlanInfo();
                planInfo2.start = i6;
                planInfo2.end = i5;
                arrayList.add(planInfo2);
                LogUtils.e("ScheduleOfAlarm", "mergeDuplicates 3, find 3, start: " + i6 + ", end: " + i5 + ", mDayValue[m]: " + this.mDayValue[i5]);
            }
        }
        LogUtils.e("ScheduleOfAlarm", "mergeDuplicates 5, size: " + arrayList.size());
        if (arrayList.size() <= 0) {
            return null;
        }
        JSONArray jSONArray3 = new JSONArray();
        for (int i9 = 0; i9 < arrayList.size(); i9++) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("S", ((PlanInfo) arrayList.get(i9)).start);
                jSONObject2.put("E", ((PlanInfo) arrayList.get(i9)).end);
                jSONArray3.put(jSONObject2);
            } catch (JSONException unused2) {
                return null;
            }
        }
        return jSONArray3;
    }

    public void savePlan(int i, int i2, int i3) {
        int i4;
        JSONArray jSONArray = this.mRSIArr;
        if (jSONArray != null) {
            try {
                JSONArray jSONArray2 = jSONArray.getJSONArray(this.mDay);
                if (jSONArray2 != null) {
                    int i5 = 0;
                    for (int i6 = 0; i6 < jSONArray2.length(); i6++) {
                        if (jSONArray2.getJSONObject(i6).getInt("S") <= jSONArray2.getJSONObject(i6).getInt("E")) {
                            i5++;
                        }
                    }
                    LogUtils.e("ScheduleOfAlarm", "savePlan 1, count: " + i5 + ", start: " + i + ", end: " + i2);
                    if (i5 == 1 && (i4 = jSONArray2.getJSONObject(0).getInt("S")) == jSONArray2.getJSONObject(0).getInt("E") && i4 == 0) {
                        JSONObject jSONObject = jSONArray2.getJSONObject(0);
                        jSONObject.put("S", i);
                        jSONObject.put("E", i2);
                        NetPresenter.getDefault().setConfig(this);
                        return;
                    }
                    JSONArray jSONArray3 = new JSONArray(jSONArray2.toString());
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("S", i);
                    jSONObject2.put("E", i2);
                    jSONArray3.put(jSONObject2);
                    JSONArray mergeDuplicates = mergeDuplicates(jSONArray3);
                    if (mergeDuplicates != null) {
                        if (mergeDuplicates.length() > 8) {
                            toastSf((int) R.string.planTotalMoreThanEight);
                            return;
                        }
                        this.mRSIArr.put(this.mDay, mergeDuplicates);
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }
}
