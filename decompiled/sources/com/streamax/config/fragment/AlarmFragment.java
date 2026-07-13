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
        JSONObject motionDetectionConfig = this.mMdpObj;
        if (motionDetectionConfig != null) {
            try {
                this.mMdpObj.put("EN", motionDetectionConfig.getInt("EN") == 0 ? 1 : 0);
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
        JSONObject alarmPlan;
        JSONArray alarmScheduleRows;
        JSONArray motionDetectionAlarmArray = this.mMdaArr;
        if (motionDetectionAlarmArray != null) {
            try {
                JSONObject channelAlarmConfig = motionDetectionAlarmArray.getJSONObject(this.mCurCh);
                if (channelAlarmConfig != null && (alarmPlan = channelAlarmConfig.getJSONObject("APLAN")) != null && (alarmScheduleRows = alarmPlan.getJSONArray("RSI")) != null) {
                    ScheduleOfAlarm scheduleOfAlarm = new ScheduleOfAlarm();
                    scheduleOfAlarm.setScheduleData(this.mAlarmRes, alarmScheduleRows);
                    nextPage(scheduleOfAlarm);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForTrigger() {
        JSONObject alarmProcessConfig;
        if (this.mMdpArr != null) {
            try {
                JSONObject channelAlarmConfig = this.mMdaArr.getJSONObject(this.mCurCh);
                if (channelAlarmConfig != null && (alarmProcessConfig = channelAlarmConfig.getJSONObject("APRO")) != null) {
                    TriggerOfAlarm triggerOfAlarm = new TriggerOfAlarm();
                    triggerOfAlarm.setTriggerData(this.mAlarmRes, alarmProcessConfig, this.mCurChTotal);
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
        JSONObject alarmConfig = this.mAlarmRes;
        if (alarmConfig != null) {
            try {
                JSONObject eventManagerConfig = alarmConfig.getJSONObject("EVEM");
                if (eventManagerConfig != null) {
                    this.mMdaArr = eventManagerConfig.getJSONArray("MDA");
                    JSONArray motionDetectionParamArray = eventManagerConfig.getJSONArray("MDP");
                    this.mMdpArr = motionDetectionParamArray;
                    JSONArray motionDetectionAlarmArray = this.mMdaArr;
                    if (motionDetectionAlarmArray == null) {
                        return;
                    }
                    if (motionDetectionParamArray != null) {
                        if (motionDetectionAlarmArray.length() == this.mMdpArr.length()) {
                            int length = this.mMdpArr.length();
                            this.mCurChTotal = length;
                            if (length > 0) {
                                int currentChannel = this.mCurCh;
                                if (currentChannel < length) {
                                    this.mMdaObj = this.mMdaArr.getJSONObject(currentChannel);
                                    JSONObject motionDetectionConfig = this.mMdpArr.getJSONObject(this.mCurCh);
                                    this.mMdpObj = motionDetectionConfig;
                                    if (this.mMdaObj == null) {
                                        return;
                                    }
                                    if (motionDetectionConfig != null) {
                                        TextView textView = this.mTvCh;
                                        textView.setText("CH" + (this.mCurCh + 1));
                                        this.mListStrChannel.clear();
                                        this.mListIntChannel.clear();
                                        int channelDisplayIndex = 0;
                                        while (channelDisplayIndex < this.mCurChTotal) {
                                            ArrayList<String> arrayList = this.mListStrChannel;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("CH");
                                            channelDisplayIndex++;
                                            sb.append(channelDisplayIndex);
                                            arrayList.add(sb.toString());
                                        }
                                        this.mListIntChannel.add(new Integer(this.mCurCh));
                                        this.mBtnEnable.setBackgroundResource(this.mMdpObj.getInt("EN") <= 0 ? R.drawable.switch_close : R.drawable.switch_open);
                                        int sensitivityIndex = this.mMdpObj.getInt("SST");
                                        this.mListStrSST.clear();
                                        this.mListIntSST.clear();
                                        List<String> sensitivityLabels = getStrDatas(R.array.SensitivitySelector);
                                        if (sensitivityIndex >= 0 && sensitivityIndex < sensitivityLabels.size()) {
                                            this.mTvSst.setText(sensitivityLabels.get(sensitivityIndex));
                                            this.mListStrSST.addAll(sensitivityLabels);
                                            this.mListIntSST.add(new Integer(sensitivityIndex));
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
            JSONObject requestRoot = new JSONObject();
            JSONObject eventManagerConfig = new JSONObject();
            eventManagerConfig.put("MDA", "?");
            eventManagerConfig.put("MDP", "?");
            requestRoot.put("EVEM", eventManagerConfig);
            return requestRoot.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String jsonResponse) {
        try {
            LogUtils.e("AlarmFragment", "getSuccess 1, result: " + jsonResponse);
            this.mAlarmRes = new JSONObject(jsonResponse);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject alarmConfig = this.mAlarmRes;
        if (alarmConfig == null) {
            return "";
        }
        return alarmConfig.toString();
    }

    public void setSuccess() {
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForChannel(int channelIndex) {
        if (this.mMdpArr != null && this.mCurCh != channelIndex) {
            this.mCurCh = channelIndex;
            refreshUi();
        }
    }

    public void updateDateForSST(int sensitivityIndex) {
        JSONObject motionDetectionConfig = this.mMdpObj;
        if (motionDetectionConfig != null) {
            try {
                motionDetectionConfig.put("SST", sensitivityIndex);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForCopy(List<Integer> selectedChannels) {
        String alarmProcessKey = "APRO";
        LogUtils.e("DstOfDt", "AlarmFragment 0 ");
        if (this.mAlarmRes != null) {
            try {
                JSONArray motionDetectionAlarmArray = this.mMdaArr;
                if (motionDetectionAlarmArray == null) {
                    return;
                }
                if (this.mMdpArr != null) {
                    JSONObject sourceAlarmConfig = motionDetectionAlarmArray.getJSONObject(this.mCurCh);
                    JSONObject sourceParamConfig = this.mMdpArr.getJSONObject(this.mCurCh);
                    if (sourceAlarmConfig == null) {
                        return;
                    }
                    if (sourceParamConfig != null) {
                        int selectedIndex = 0;
                        boolean copiedAnyChannel = false;
                        while (selectedIndex < selectedChannels.size()) {
                            int targetChannel = selectedChannels.get(selectedIndex).intValue();
                            if (targetChannel != this.mCurCh || targetChannel <= this.mCurChTotal) {
                                JSONObject copiedAlarmConfig = new JSONObject(sourceAlarmConfig.toString());
                                JSONObject copiedParamConfig = new JSONObject(sourceParamConfig.toString());
                                JSONObject copiedProcessConfig = copiedAlarmConfig.getJSONObject(alarmProcessKey);
                                if (copiedProcessConfig != null) {
                                    JSONObject copiedAlarmRule = copiedProcessConfig.getJSONObject("AR");
                                    JSONObject copiedSnapshotRule = copiedProcessConfig.getJSONObject("SPS");
                                    if (copiedAlarmRule != null) {
                                        if (copiedSnapshotRule != null) {
                                            JSONObject targetAlarmConfig = this.mMdaArr.getJSONObject(targetChannel);
                                            if (targetAlarmConfig != null) {
                                                JSONObject targetProcessConfig = targetAlarmConfig.getJSONObject(alarmProcessKey);
                                                if (targetProcessConfig != null) {
                                                    JSONObject targetAlarmRule = targetProcessConfig.getJSONObject("AR");
                                                    JSONObject targetSnapshotRule = targetProcessConfig.getJSONObject("SPS");
                                                    if (targetAlarmRule != null) {
                                                        if (targetSnapshotRule != null) {
                                                            copiedAlarmRule.put("CH", targetAlarmRule.get("CH"));
                                                            copiedSnapshotRule.put("CH", targetSnapshotRule.get("CH"));
                                                            this.mMdaArr.put(targetChannel, copiedAlarmConfig);
                                                            this.mMdpArr.put(targetChannel, copiedParamConfig);
                                                            copiedAnyChannel = true;
                                                        }
                                                    }
                                                    selectedIndex++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            selectedIndex++;
                        }
                        if (copiedAnyChannel) {
                            NetPresenter.getDefault().setConfig(this);
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String fragmentTag, List<Integer> selectedIndexes) {
        if (fragmentTag.equals("SelectFragmentForChannel")) {
            if (selectedIndexes.size() > 0) {
                updateDateForChannel(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForSST")) {
            if (selectedIndexes.size() > 0) {
                updateDateForSST(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForCopy") && selectedIndexes.size() > 0) {
            updateDateForCopy(selectedIndexes);
        }
    }
}
