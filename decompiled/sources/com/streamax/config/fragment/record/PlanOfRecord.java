package com.streamax.config.fragment.record;

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

public class PlanOfRecord extends ConfigFragment implements TimeUtils.TimePickListener, AddPlanFragment.AddPlanInterface, BaseListener.SetListener {
    public static final String mTimePrefix = "2017-07-06 ";
    public Button mBtnAdd;
    public Button mBtnDelete;
    public int mDay;
    public byte[] mDayValue = new byte[Strategy.TTL_SECONDS_MAX];
    public List<Integer> mListIntDay = new ArrayList();
    public List<Integer> mListIntPIdx = new ArrayList();
    public List<Integer> mListIntType = new ArrayList();
    public ArrayList<String> mListStrDay = new ArrayList<>();
    public ArrayList<String> mListStrPIdx = new ArrayList<>();
    public ArrayList<String> mListStrType = new ArrayList<>();
    public LinearLayout mLlPlan;
    public int mPIdx;
    public JSONArray mRSIArr;
    public JSONObject mRecRes;
    public RelativeLayout mRlDay;
    public RlView mRvEnd;
    public RlView mRvPIdx;
    public RlView mRvStart;
    public RlView mRvType;
    public int mTimeType;
    public TextView mTvDay;
    public TextView mTvEnd;
    public TextView mTvPIdx;
    public TextView mTvStart;
    public TextView mTvType;
    public JSONObject mVPlanObj;

    public BaseFragment getCurFragment() {
        return this;
    }

    public class PlanInfo {
        int end;
        int start;
        int type;

        public PlanInfo() {
        }
    }

    public void setPlanData(JSONObject jSONObject, JSONObject jSONObject2) {
        this.mRecRes = jSONObject;
        this.mVPlanObj = jSONObject2;
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mDay = 0;
        this.mPIdx = 0;
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.SetText((int) R.string.config_record_schedule_Title);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_record_plan, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    private void initChildView() {
        this.mRlDay = (RelativeLayout) this.mRootView.findViewById(R.id.config_record_schedule_rl_dayPick);
        this.mTvDay = (TextView) this.mRootView.findViewById(R.id.config_record_schedule_tv_date);
        this.mLlPlan = (LinearLayout) this.mRootView.findViewById(R.id.config_record_ll_plan);
        this.mRvPIdx = (RlView) this.mRootView.findViewById(R.id.config_record_schedule_rl_planPick);
        this.mTvPIdx = (TextView) this.mRootView.findViewById(R.id.config_record_schedule_tv_plan);
        this.mRvStart = (RlView) this.mRootView.findViewById(R.id.config_record_schedule_rl_stPick);
        this.mTvStart = (TextView) this.mRootView.findViewById(R.id.config_record_schedule_tv_startTime);
        this.mRvEnd = (RlView) this.mRootView.findViewById(R.id.config_record_schedule_rl_etPick);
        this.mTvEnd = (TextView) this.mRootView.findViewById(R.id.config_record_schedule_tv_endTime);
        this.mRvType = (RlView) this.mRootView.findViewById(R.id.config_record_schedule_rl_rtPick);
        this.mTvType = (TextView) this.mRootView.findViewById(R.id.config_record_schedule_tvRecordtype);
        this.mBtnAdd = (Button) this.mRootView.findViewById(R.id.config_record_schedule_btn_add);
        this.mBtnDelete = (Button) this.mRootView.findViewById(R.id.config_record_schedule_btn_delete);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        refreshUi(true);
    }

    private void refreshUi(boolean mergeDuplicatePlans) {
        JSONArray mergeDuplicates;
        JSONObject planObj = this.mVPlanObj;
        if (planObj != null) {
            try {
                this.mRSIArr = planObj.getJSONArray("RSI");
                List<String> dayLabels = getStrDatas(R.array.DaySelector);
                JSONArray dayPlans = this.mRSIArr;
                if (dayPlans == null) {
                    return;
                }
                if (dayPlans.length() == dayLabels.size()) {
                    this.mListStrDay.clear();
                    this.mListIntDay.clear();
                    int currentDay = this.mDay;
                    if (currentDay >= 0 && currentDay < dayLabels.size()) {
                        this.mTvDay.setText(dayLabels.get(this.mDay));
                        this.mListStrDay.addAll(dayLabels);
                        this.mListIntDay.add(new Integer(this.mDay));
                    }
                    JSONArray currentDayPlans = this.mRSIArr.getJSONArray(this.mDay);
                    if (currentDayPlans != null) {
                        if (mergeDuplicatePlans && (mergeDuplicates = mergeDuplicates(new JSONArray(currentDayPlans.toString()))) != null) {
                            this.mRSIArr.put(this.mDay, mergeDuplicates);
                        }
                        ArrayList planList = new ArrayList();
                        ArrayList planLabels = new ArrayList();
                        int visiblePlanCount = 0;
                        for (int planIndex = 0; planIndex < currentDayPlans.length(); planIndex++) {
                            PlanInfo planInfo = new PlanInfo();
                            planInfo.start = currentDayPlans.getJSONObject(planIndex).getInt("S");
                            planInfo.end = currentDayPlans.getJSONObject(planIndex).getInt("E");
                            planInfo.type = currentDayPlans.getJSONObject(planIndex).getInt("T");
                            if (planInfo.start < planInfo.end) {
                                planList.add(planInfo);
                                visiblePlanCount++;
                                planLabels.add("" + visiblePlanCount);
                            }
                        }
                        this.mListStrPIdx.clear();
                        this.mListIntPIdx.clear();
                        int currentPlanIndex = this.mPIdx;
                        if (currentPlanIndex >= 0 && currentPlanIndex < planLabels.size()) {
                            TextView textView = this.mTvPIdx;
                            textView.setText("" + (this.mPIdx + 1));
                            this.mListStrPIdx.addAll(planLabels);
                            this.mListIntPIdx.add(new Integer(this.mPIdx));
                        }
                        this.mListStrType.clear();
                        this.mListIntType.clear();
                        if (planList.size() > 0) {
                            this.mLlPlan.setVisibility(0);
                            int selectedPlanIndex = this.mPIdx;
                            if (selectedPlanIndex >= 0 && selectedPlanIndex < planList.size()) {
                                PlanInfo selectedPlan = (PlanInfo) planList.get(this.mPIdx);
                                this.mTvStart.setText(parse2String_86399(selectedPlan.start));
                                this.mTvEnd.setText(parse2String_86399(selectedPlan.end));
                                int selectedType = selectedPlan.type;
                                List<String> typeLabels = getStrDatas(R.array.config_record_schedule_rtSelector);
                                if (selectedType >= 0 && selectedType < typeLabels.size()) {
                                    this.mTvType.setText(typeLabels.get(selectedType));
                                    this.mListStrType.addAll(typeLabels);
                                    this.mListIntType.add(new Integer(selectedType));
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        this.mLlPlan.setVisibility(8);
                        this.mTvStart.setText("");
                        this.mTvEnd.setText("");
                        this.mTvType.setText("");
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
        this.mRvType.setOnClickListener(this);
        this.mBtnAdd.setOnClickListener(this);
        this.mBtnDelete.setOnClickListener(this);
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
                TimeUtils.setTimePickListener(this.mConfigUi, textView, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-07-06 " + StringUtils.getString(textView)), TimePickerView.Type.HOURS_MINS_SECS, this);
            } catch (ParseException unused) {
            }
        }
    }

    public void pushFragmentForType() {
        if (this.mRSIArr != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_record_schedule_Rt), "SelectFragmentForType", 0, this.mListStrType, this.mListIntType);
        }
    }

    public void addPlan() {
        JSONArray dayPlanArray = this.mRSIArr;
        if (dayPlanArray != null) {
            try {
                JSONArray currentDayPlans = dayPlanArray.getJSONArray(this.mDay);
                if (currentDayPlans != null) {
                    int validPlanCount = countValidPlans(currentDayPlans);
                    if (validPlanCount >= 8) {
                        toastSf((int) R.string.planTotalMoreThanEight);
                        return;
                    }
                    AddPlanFragment addPlanFragment = new AddPlanFragment();
                    addPlanFragment.SetAddPlanInterface(this);
                    addPlanFragment.ShowType(true);
                    nextPage(addPlanFragment);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void deletePlan() {
        JSONArray dayPlanArray = this.mRSIArr;
        if (dayPlanArray != null) {
            try {
                JSONArray currentDayPlans = dayPlanArray.getJSONArray(this.mDay);
                if (currentDayPlans != null) {
                    int validPlanCount = countValidPlans(currentDayPlans);
                    if (validPlanCount > 0) {
                        if (validPlanCount == 1) {
                            this.mPIdx = 0;
                            int startTime = currentDayPlans.getJSONObject(0).getInt("S");
                            if (startTime != currentDayPlans.getJSONObject(0).getInt("E") || startTime != 0) {
                                currentDayPlans.getJSONObject(0).put("S", 0);
                                currentDayPlans.getJSONObject(0).put("E", 0);
                                NetPresenter.getDefault().setConfig(this);
                                return;
                            }
                            return;
                        }
                        int selectedPlanIndex = this.mPIdx;
                        if (selectedPlanIndex + 1 == validPlanCount) {
                            this.mPIdx = selectedPlanIndex - 1;
                        }
                        currentDayPlans.remove(selectedPlanIndex);
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
                case R.id.config_record_schedule_btn_add /*2131362147*/:
                    addPlan();
                    return;
                case R.id.config_record_schedule_btn_delete /*2131362148*/:
                    deletePlan();
                    return;
                default:
                    switch (id) {
                        case R.id.config_record_schedule_rl_dayPick /*2131362151*/:
                            pushFragmentForDay();
                            return;
                        case R.id.config_record_schedule_rl_etPick /*2131362152*/:
                            this.mTimeType = 1;
                            setTime(this.mTvEnd);
                            return;
                        case R.id.config_record_schedule_rl_planPick /*2131362153*/:
                            pushFragmentForPIdx();
                            return;
                        case R.id.config_record_schedule_rl_rtPick /*2131362154*/:
                            pushFragmentForType();
                            return;
                        case R.id.config_record_schedule_rl_stPick /*2131362155*/:
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
        JSONObject jSONObject = this.mRecRes;
        if (jSONObject == null) {
            return "";
        }
        return jSONObject.toString();
    }

    public void setSuccess() {
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

    public void updateDateForType(int i) {
        JSONObject jSONObject;
        JSONArray jSONArray = this.mRSIArr;
        if (jSONArray != null) {
            try {
                JSONArray jSONArray2 = jSONArray.getJSONArray(this.mDay);
                if (jSONArray2 != null && (jSONObject = jSONArray2.getJSONObject(this.mPIdx)) != null) {
                    jSONObject.put("T", i);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    private int countValidPlans(JSONArray plans) throws JSONException {
        int count = 0;
        for (int planIndex = 0; planIndex < plans.length(); planIndex++) {
            if (plans.getJSONObject(planIndex).getInt("S") <= plans.getJSONObject(planIndex).getInt("E")) {
                count++;
            }
        }
        return count;
    }

    public void saveSelect(String tag, List<Integer> list) {
        if (tag.equals("SelectFragmentForDay")) {
            if (list.size() > 0) {
                updateDateForDay(list.get(0).intValue());
            }
        } else if (tag.equals("SelectFragmentForPIdx")) {
            if (list.size() > 0) {
                updateDateForPlan(list.get(0).intValue());
            }
        } else if (tag.equals("SelectFragmentForType") && list.size() > 0) {
            updateDateForType(list.get(0).intValue());
        }
    }

    public JSONArray mergeDuplicates(JSONArray plans) {
        if (plans == null) {
            return null;
        }
        for (int second = 0; second < this.mDayValue.length; second++) {
            this.mDayValue[second] = -1;
        }
        int planCount = plans.length();
        if (planCount <= 1) {
            return plans;
        }
        try {
            for (int planIndex = 0; planIndex < planCount; planIndex++) {
                JSONObject plan = plans.getJSONObject(planIndex);
                int endTime = plan.getInt("E");
                int planType = plan.getInt("T");
                for (int second = plan.getInt("S"); second <= endTime; second++) {
                    if (second < 86400) {
                        byte[] dayValue = this.mDayValue;
                        if (dayValue[second] != 1) {
                            dayValue[second] = (byte) planType;
                        }
                    }
                }
            }
        } catch (JSONException unused) {
            return null;
        }
        ArrayList mergedPlans = new ArrayList();
        int second = 0;
        int segmentStart = -1;
        byte segmentType = 0;
        while (true) {
            if (second >= 86400) {
                break;
            }
            byte[] dayValue = this.mDayValue;
            if (dayValue[second] != -1 && segmentStart == -1) {
                segmentType = dayValue[second];
                segmentStart = second;
            }
            if (!(segmentStart == -1 || dayValue[second] == segmentType)) {
                int segmentEnd = second - 1;
                if (segmentStart < segmentEnd) {
                    PlanInfo planInfo = new PlanInfo();
                    planInfo.start = segmentStart;
                    planInfo.end = segmentEnd;
                    planInfo.type = segmentType;
                    mergedPlans.add(planInfo);
                }
                segmentType = this.mDayValue[second];
                segmentStart = second;
            }
            int nextSecond = second + 1;
            if (nextSecond != 86400 || segmentStart == -1) {
                second = nextSecond;
            } else {
                if (segmentStart < second) {
                    PlanInfo planInfo2 = new PlanInfo();
                    planInfo2.start = segmentStart;
                    planInfo2.end = second;
                    planInfo2.type = segmentType;
                    mergedPlans.add(planInfo2);
                }
                break;
            }
        }
        if (mergedPlans.size() <= 0) {
            return null;
        }
        JSONArray mergedArray = new JSONArray();
        for (int planIndex = 0; planIndex < mergedPlans.size(); planIndex++) {
            JSONObject planObj = new JSONObject();
            try {
                PlanInfo planInfo = (PlanInfo) mergedPlans.get(planIndex);
                planObj.put("S", planInfo.start);
                planObj.put("E", planInfo.end);
                planObj.put("T", planInfo.type);
                mergedArray.put(planObj);
            } catch (JSONException unused2) {
                return null;
            }
        }
        return mergedArray;
    }

    public void savePlan(int startTime, int endTime, int recordType) {
        JSONArray dayPlanArray = this.mRSIArr;
        if (dayPlanArray != null) {
            try {
                JSONArray currentDayPlans = dayPlanArray.getJSONArray(this.mDay);
                if (currentDayPlans != null) {
                    int validPlanCount = countValidPlans(currentDayPlans);
                    LogUtils.e("PlanOfRecord", "savePlan 1, count: " + validPlanCount);
                    if (validPlanCount == 1) {
                        int existingStart = currentDayPlans.getJSONObject(0).getInt("S");
                        if (existingStart == currentDayPlans.getJSONObject(0).getInt("E") && existingStart == 0) {
                            JSONObject existingPlan = currentDayPlans.getJSONObject(0);
                            existingPlan.put("S", startTime);
                            existingPlan.put("E", endTime);
                            existingPlan.put("T", recordType);
                            NetPresenter.getDefault().setConfig(this);
                            return;
                        }
                    }
                    JSONArray updatedPlans = new JSONArray(currentDayPlans.toString());
                    JSONObject newPlan = new JSONObject();
                    newPlan.put("S", startTime);
                    newPlan.put("E", endTime);
                    newPlan.put("T", recordType);
                    updatedPlans.put(newPlan);
                    JSONArray mergeDuplicates = mergeDuplicates(updatedPlans);
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
