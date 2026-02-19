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

    private void refreshUi(boolean z) {
        JSONArray mergeDuplicates;
        JSONObject jSONObject = this.mVPlanObj;
        if (jSONObject != null) {
            try {
                this.mRSIArr = jSONObject.getJSONArray("RSI");
                List<String> strDatas = getStrDatas(R.array.DaySelector);
                JSONArray jSONArray = this.mRSIArr;
                if (jSONArray == null) {
                    return;
                }
                if (jSONArray.length() == strDatas.size()) {
                    this.mListStrDay.clear();
                    this.mListIntDay.clear();
                    int i = this.mDay;
                    if (i >= 0 && i < strDatas.size()) {
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
                        int i2 = 0;
                        for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                            PlanInfo planInfo = new PlanInfo();
                            planInfo.start = jSONArray2.getJSONObject(i3).getInt("S");
                            planInfo.end = jSONArray2.getJSONObject(i3).getInt("E");
                            planInfo.type = jSONArray2.getJSONObject(i3).getInt("T");
                            if (planInfo.start < planInfo.end) {
                                arrayList.add(planInfo);
                                StringBuilder sb = new StringBuilder();
                                sb.append("");
                                i2++;
                                sb.append(i2);
                                arrayList2.add(sb.toString());
                            }
                        }
                        this.mListStrPIdx.clear();
                        this.mListIntPIdx.clear();
                        int i4 = this.mPIdx;
                        if (i4 >= 0 && i4 < arrayList2.size()) {
                            TextView textView = this.mTvPIdx;
                            textView.setText("" + (this.mPIdx + 1));
                            this.mListStrPIdx.addAll(arrayList2);
                            this.mListIntPIdx.add(new Integer(this.mPIdx));
                        }
                        this.mListStrType.clear();
                        this.mListIntType.clear();
                        if (arrayList.size() > 0) {
                            this.mLlPlan.setVisibility(0);
                            int i5 = this.mPIdx;
                            if (i5 >= 0 && i5 < arrayList.size()) {
                                this.mTvStart.setText(parse2String_86399(((PlanInfo) arrayList.get(this.mPIdx)).start));
                                this.mTvEnd.setText(parse2String_86399(((PlanInfo) arrayList.get(this.mPIdx)).end));
                                int i6 = ((PlanInfo) arrayList.get(this.mPIdx)).type;
                                List<String> strDatas2 = getStrDatas(R.array.config_record_schedule_rtSelector);
                                if (i6 >= 0 && i6 < strDatas2.size()) {
                                    this.mTvType.setText(strDatas2.get(i6));
                                    this.mListStrType.addAll(strDatas2);
                                    this.mListIntType.add(new Integer(i6));
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
                    addPlanFragment.ShowType(true);
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

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForDay")) {
            if (list.size() > 0) {
                updateDateForDay(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForPIdx")) {
            if (list.size() > 0) {
                updateDateForPlan(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForType") && list.size() > 0) {
            updateDateForType(list.get(0).intValue());
        }
    }

    public JSONArray mergeDuplicates(JSONArray jSONArray) {
        if (jSONArray == null) {
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
            return jSONArray;
        }
        try {
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                int i3 = jSONObject.getInt("E");
                int i4 = jSONObject.getInt("T");
                for (int i5 = jSONObject.getInt("S"); i5 <= i3; i5++) {
                    if (i5 < 86400) {
                        byte[] bArr2 = this.mDayValue;
                        if (bArr2[i5] != 1) {
                            bArr2[i5] = (byte) i4;
                        }
                    }
                }
            }
        } catch (JSONException unused) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i6 = 0;
        int i7 = -1;
        byte b = 0;
        while (true) {
            if (i6 >= 86400) {
                break;
            }
            byte[] bArr3 = this.mDayValue;
            if (bArr3[i6] != -1 && i7 == -1) {
                b = bArr3[i6];
                i7 = i6;
            }
            if (!(i7 == -1 || bArr3[i6] == b)) {
                int i8 = i6 - 1;
                if (i7 < i8) {
                    PlanInfo planInfo = new PlanInfo();
                    planInfo.start = i7;
                    planInfo.end = i8;
                    planInfo.type = b;
                    arrayList.add(planInfo);
                }
                b = this.mDayValue[i6];
                i7 = i6;
            }
            int i9 = i6 + 1;
            if (i9 != 86400 || i7 == -1) {
                i6 = i9;
            } else if (i7 < i6) {
                PlanInfo planInfo2 = new PlanInfo();
                planInfo2.start = i7;
                planInfo2.end = i6;
                planInfo2.type = b;
                arrayList.add(planInfo2);
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("S", ((PlanInfo) arrayList.get(i10)).start);
                jSONObject2.put("E", ((PlanInfo) arrayList.get(i10)).end);
                jSONObject2.put("T", ((PlanInfo) arrayList.get(i10)).type);
                jSONArray2.put(jSONObject2);
            } catch (JSONException unused2) {
                return null;
            }
        }
        return jSONArray2;
    }

    public void savePlan(int i, int i2, int i3) {
        JSONArray jSONArray = this.mRSIArr;
        if (jSONArray != null) {
            try {
                JSONArray jSONArray2 = jSONArray.getJSONArray(this.mDay);
                if (jSONArray2 != null) {
                    int i4 = 0;
                    for (int i5 = 0; i5 < jSONArray2.length(); i5++) {
                        if (jSONArray2.getJSONObject(i5).getInt("S") <= jSONArray2.getJSONObject(i5).getInt("E")) {
                            i4++;
                        }
                    }
                    LogUtils.e("PlanOfRecord", "savePlan 1, count: " + i4);
                    if (i4 == 1) {
                        int i6 = jSONArray2.getJSONObject(0).getInt("S");
                        if (i6 == jSONArray2.getJSONObject(0).getInt("E") && i6 == 0) {
                            JSONObject jSONObject = jSONArray2.getJSONObject(0);
                            jSONObject.put("S", i);
                            jSONObject.put("E", i2);
                            jSONObject.put("T", i3);
                            NetPresenter.getDefault().setConfig(this);
                            return;
                        }
                    }
                    JSONArray jSONArray3 = new JSONArray(jSONArray2.toString());
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("S", i);
                    jSONObject2.put("E", i2);
                    jSONObject2.put("T", i3);
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
