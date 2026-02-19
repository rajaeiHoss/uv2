package com.streamax.config.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.fragment.alarm.ScheduleOfAlarm;
import com.streamax.config.fragment.alarm.TriggerOfAlarm;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.UiUtils;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmFragment extends ConfigFragment implements BaseListener.GetListener, BaseListener.SetListener {
    public JSONObject mAlarmRes;
    public Button mBtnCopy;
    public Button mBtnEnable;
    public int mCurCh;
    public int mCurChTotal;
    public List<Integer> mListIntChannel = new ArrayList();
    public List<Integer> mListIntSST = new ArrayList();
    public ArrayList<String> mListStrChannel = new ArrayList<>();
    public ArrayList<String> mListStrSST = new ArrayList<>();
    public JSONArray mMdaArr;
    public JSONObject mMdaObj;
    public JSONArray mMdpArr;
    public JSONObject mMdpObj;
    public RelativeLayout mRlCh;
    public RelativeLayout mRlSchedule;
    public RelativeLayout mRlSst;
    public RelativeLayout mRlTrigger;
    public TextView mTvCh;
    public TextView mTvSst;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mCurCh = 0;
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_alarm, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    public void initChildView() {
        this.mRlCh = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_rl_ch);
        this.mTvCh = (TextView) this.mRootView.findViewById(R.id.config_alarm_tv_ch);
        this.mBtnEnable = (Button) this.mRootView.findViewById(R.id.config_alarm_btn_enable);
        this.mRlSst = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_rl_sst);
        this.mTvSst = (TextView) this.mRootView.findViewById(R.id.config_alarm_tv_sst);
        this.mRlSchedule = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_rl_schedule);
        this.mRlTrigger = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_rl_trigger);
        this.mBtnCopy = (Button) this.mRootView.findViewById(R.id.config_alarm_btn_copy);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_alarm_Title);
        this.mBtnBack.setOnClickListener(this);
        this.mBtnUpdate.setVisibility(8);
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mRlCh.setOnClickListener(this);
        this.mBtnEnable.setOnClickListener(this);
        this.mRlSst.setOnClickListener(this);
        this.mRlSchedule.setOnClickListener(this);
        this.mRlTrigger.setOnClickListener(this);
        this.mBtnCopy.setOnClickListener(this);
    }

    public void pushFragmentForChannel() {
        if (this.mMdpArr != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Channel), "SelectFragmentForChannel", 0, this.mListStrChannel, this.mListIntChannel);
        }
    }

    public void saveBtnEnable() {
        JSONObject jSONObject = this.mMdpObj;
        if (jSONObject != null) {
            try {
                this.mMdpObj.put("EN", jSONObject.getInt("EN") == 0 ? 1 : 0);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForSST() {
        if (this.mMdpObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_alarm_Sensitivity), "SelectFragmentForSST", 0, this.mListStrSST, this.mListIntSST);
        }
    }

    public void pushFragmentForPlan() {
        JSONObject jSONObject;
        JSONArray jSONArray;
        JSONArray jSONArray2 = this.mMdaArr;
        if (jSONArray2 != null) {
            try {
                JSONObject jSONObject2 = jSONArray2.getJSONObject(this.mCurCh);
                if (jSONObject2 != null && (jSONObject = jSONObject2.getJSONObject("APLAN")) != null && (jSONArray = jSONObject.getJSONArray("RSI")) != null) {
                    ScheduleOfAlarm scheduleOfAlarm = new ScheduleOfAlarm();
                    scheduleOfAlarm.setScheduleData(this.mAlarmRes, jSONArray);
                    nextPage(scheduleOfAlarm);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForTrigger() {
        JSONObject jSONObject;
        if (this.mMdpArr != null) {
            try {
                JSONObject jSONObject2 = this.mMdaArr.getJSONObject(this.mCurCh);
                if (jSONObject2 != null && (jSONObject = jSONObject2.getJSONObject("APRO")) != null) {
                    TriggerOfAlarm triggerOfAlarm = new TriggerOfAlarm();
                    triggerOfAlarm.setTriggerData(this.mAlarmRes, jSONObject, this.mCurChTotal);
                    nextPage(triggerOfAlarm);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForCopy() {
        if (this.mAlarmRes != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_CopyChToCh), "SelectFragmentForCopy", 1, this.mListStrChannel, (List<Integer>) null);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.config_title_btn_back) {
            switch (id) {
                case R.id.config_alarm_btn_copy /*2131362023*/:
                    pushFragmentForCopy();
                    return;
                case R.id.config_alarm_btn_enable /*2131362024*/:
                    saveBtnEnable();
                    return;
                case R.id.config_alarm_rl_ch /*2131362025*/:
                    pushFragmentForChannel();
                    return;
                default:
                    switch (id) {
                        case R.id.config_alarm_rl_schedule /*2131362027*/:
                            pushFragmentForPlan();
                            return;
                        case R.id.config_alarm_rl_sst /*2131362028*/:
                            pushFragmentForSST();
                            return;
                        case R.id.config_alarm_rl_trigger /*2131362029*/:
                            pushFragmentForTrigger();
                            return;
                        default:
                            return;
                    }
            }
        } else {
            prePage();
        }
    }

    public void refreshUi() {
        JSONObject jSONObject = this.mAlarmRes;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("EVEM");
                if (jSONObject2 != null) {
                    this.mMdaArr = jSONObject2.getJSONArray("MDA");
                    JSONArray jSONArray = jSONObject2.getJSONArray("MDP");
                    this.mMdpArr = jSONArray;
                    JSONArray jSONArray2 = this.mMdaArr;
                    if (jSONArray2 == null) {
                        return;
                    }
                    if (jSONArray != null) {
                        if (jSONArray2.length() == this.mMdpArr.length()) {
                            int length = this.mMdpArr.length();
                            this.mCurChTotal = length;
                            if (length > 0) {
                                int i = this.mCurCh;
                                if (i < length) {
                                    this.mMdaObj = this.mMdaArr.getJSONObject(i);
                                    JSONObject jSONObject3 = this.mMdpArr.getJSONObject(this.mCurCh);
                                    this.mMdpObj = jSONObject3;
                                    if (this.mMdaObj == null) {
                                        return;
                                    }
                                    if (jSONObject3 != null) {
                                        TextView textView = this.mTvCh;
                                        textView.setText("CH" + (this.mCurCh + 1));
                                        this.mListStrChannel.clear();
                                        this.mListIntChannel.clear();
                                        int i2 = 0;
                                        while (i2 < this.mCurChTotal) {
                                            ArrayList<String> arrayList = this.mListStrChannel;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("CH");
                                            i2++;
                                            sb.append(i2);
                                            arrayList.add(sb.toString());
                                        }
                                        this.mListIntChannel.add(new Integer(this.mCurCh));
                                        this.mBtnEnable.setBackgroundResource(this.mMdpObj.getInt("EN") <= 0 ? R.drawable.switch_close : R.drawable.switch_open);
                                        int i3 = this.mMdpObj.getInt("SST");
                                        this.mListStrSST.clear();
                                        this.mListIntSST.clear();
                                        List<String> strDatas = getStrDatas(R.array.SensitivitySelector);
                                        if (i3 >= 0 && i3 < strDatas.size()) {
                                            this.mTvSst.setText(strDatas.get(i3));
                                            this.mListStrSST.addAll(strDatas);
                                            this.mListIntSST.add(new Integer(i3));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("MDA", "?");
            jSONObject2.put("MDP", "?");
            jSONObject.put("EVEM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String str) {
        try {
            LogUtils.e("AlarmFragment", "getSuccess 1, result: " + str);
            this.mAlarmRes = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
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
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForChannel(int i) {
        if (this.mMdpArr != null && this.mCurCh != i) {
            this.mCurCh = i;
            refreshUi();
        }
    }

    public void updateDateForSST(int i) {
        JSONObject jSONObject = this.mMdpObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("SST", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForCopy(List<Integer> list) {
        String str;
        String str2 = "APRO";
        LogUtils.e("DstOfDt", "AlarmFragment 0 ");
        if (this.mAlarmRes != null) {
            try {
                JSONArray jSONArray = this.mMdaArr;
                if (jSONArray == null) {
                    return;
                }
                if (this.mMdpArr != null) {
                    JSONObject jSONObject = jSONArray.getJSONObject(this.mCurCh);
                    JSONObject jSONObject2 = this.mMdpArr.getJSONObject(this.mCurCh);
                    if (jSONObject == null) {
                        return;
                    }
                    if (jSONObject2 != null) {
                        int i = 0;
                        boolean z = false;
                        while (i < list.size()) {
                            int intValue = list.get(i).intValue();
                            if (intValue != this.mCurCh || intValue <= this.mCurChTotal) {
                                JSONObject jSONObject3 = new JSONObject(jSONObject.toString());
                                JSONObject jSONObject4 = new JSONObject(jSONObject2.toString());
                                JSONObject jSONObject5 = jSONObject3.getJSONObject(str2);
                                if (jSONObject5 != null) {
                                    JSONObject jSONObject6 = jSONObject5.getJSONObject("AR");
                                    JSONObject jSONObject7 = jSONObject5.getJSONObject("SPS");
                                    if (jSONObject6 != null) {
                                        if (jSONObject7 != null) {
                                            JSONObject jSONObject8 = this.mMdaArr.getJSONObject(intValue);
                                            if (jSONObject8 != null) {
                                                JSONObject jSONObject9 = jSONObject8.getJSONObject(str2);
                                                if (jSONObject9 != null) {
                                                    str = str2;
                                                    JSONObject jSONObject10 = jSONObject9.getJSONObject("AR");
                                                    JSONObject jSONObject11 = jSONObject9.getJSONObject("SPS");
                                                    if (jSONObject10 != null) {
                                                        if (jSONObject11 != null) {
                                                            jSONObject6.put("CH", jSONObject10.get("CH"));
                                                            jSONObject7.put("CH", jSONObject11.get("CH"));
                                                            this.mMdaArr.put(intValue, jSONObject3);
                                                            this.mMdpArr.put(intValue, jSONObject4);
                                                            z = true;
                                                        }
                                                    }
                                                    i++;
                                                    str2 = str;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            str = str2;
                            i++;
                            str2 = str;
                        }
                        if (z) {
                            NetPresenter.getDefault().setConfig(this);
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForChannel")) {
            if (list.size() > 0) {
                updateDateForChannel(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForSST")) {
            if (list.size() > 0) {
                updateDateForSST(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForCopy") && list.size() > 0) {
            updateDateForCopy(list);
        }
    }
}
