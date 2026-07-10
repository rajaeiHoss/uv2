package com.streamax.config.fragment.alarm;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.media2.MediaPlayer2;
import com.google.android.gms.nearby.messages.Strategy;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.UiUtils;
import com.streamax.view.IvView;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class TriggerOfAlarm extends ConfigFragment implements BaseListener.SetListener {
    private JSONObject mAlarmRes;
    private JSONObject mAproObj;
    private int mCurChTotal;
    public IvView mIvAlarmOutput;
    public IvView mIvEmail;
    public IvView mIvFtpUpload;
    public IvView mIvMsgPush;
    public List<Integer> mListIntBuzzer = new ArrayList();
    public List<Integer> mListIntDelayRecord = new ArrayList();
    public List<Integer> mListIntFullScreen = new ArrayList();
    public List<Integer> mListIntOutputDuration = new ArrayList();
    public List<Integer> mListIntPreRecord = new ArrayList();
    public List<Integer> mListIntRecordChannel = new ArrayList();
    public List<Integer> mListIntSnapChannel = new ArrayList();
    public ArrayList<String> mListStrBuzzer = new ArrayList<>();
    public ArrayList<String> mListStrDelayRecord = new ArrayList<>();
    public ArrayList<String> mListStrFullScreen = new ArrayList<>();
    public ArrayList<String> mListStrOutputDuration = new ArrayList<>();
    public ArrayList<String> mListStrPreRecord = new ArrayList<>();
    public ArrayList<String> mListStrRecordChannel = new ArrayList<>();
    public ArrayList<String> mListStrSnapChannel = new ArrayList<>();
    public RelativeLayout mRlBuzzer;
    public RelativeLayout mRlDelayRecord;
    public RelativeLayout mRlFullScreenCh;
    public RelativeLayout mRlOutputDuration;
    public RelativeLayout mRlPreRecord;
    public RelativeLayout mRlRecordCh;
    public RelativeLayout mRlSnapCh;
    public TextView mTvBuzzer;
    public TextView mTvDelayRecord;
    public TextView mTvFullScreenCh;
    public TextView mTvOutputDuration;
    public TextView mTvPreRecord;
    public TextView mTvRecordCh;
    public TextView mTvSnapCh;

    public BaseFragment getCurFragment() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void init() {
    }

    public void setTriggerData(JSONObject jSONObject, JSONObject jSONObject2, int i) {
        this.mAlarmRes = jSONObject;
        this.mAproObj = jSONObject2;
        this.mCurChTotal = i;
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_alarm_trigger, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    private void initChildView() {
        this.mRlRecordCh = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_trigger_rl_record_channel);
        this.mTvRecordCh = (TextView) this.mRootView.findViewById(R.id.config_alarm_trigger_tv_record_channel);
        this.mRlSnapCh = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_trigger_rl_snap_channel);
        this.mTvSnapCh = (TextView) this.mRootView.findViewById(R.id.config_alarm_trigger_tv_snap_channel);
        this.mIvEmail = (IvView) this.mRootView.findViewById(R.id.config_alarm_trigger_iv_email);
        this.mIvAlarmOutput = (IvView) this.mRootView.findViewById(R.id.config_alarm_trigger_iv_alarm_output);
        this.mIvFtpUpload = (IvView) this.mRootView.findViewById(R.id.config_alarm_trigger_iv_ftp_upload);
        this.mIvMsgPush = (IvView) this.mRootView.findViewById(R.id.config_alarm_trigger_iv_msg_push);
        this.mRlOutputDuration = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_trigger_rl_alarm_output_duration);
        this.mTvOutputDuration = (TextView) this.mRootView.findViewById(R.id.config_alarm_trigger_tv_alarm_output_duration);
        this.mRlBuzzer = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_trigger_rl_buzzer);
        this.mTvBuzzer = (TextView) this.mRootView.findViewById(R.id.config_alarm_trigger_tv_buzzer);
        this.mRlFullScreenCh = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_trigger_rl_fullscreen_channel);
        this.mTvFullScreenCh = (TextView) this.mRootView.findViewById(R.id.config_alarm_trigger_tv_fullscreen_channel);
        this.mRlPreRecord = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_trigger_rl_prerecord);
        this.mTvPreRecord = (TextView) this.mRootView.findViewById(R.id.config_alarm_trigger_tv_prerecord);
        this.mRlDelayRecord = (RelativeLayout) this.mRootView.findViewById(R.id.config_alarm_trigger_rl_delay_record);
        this.mTvDelayRecord = (TextView) this.mRootView.findViewById(R.id.config_alarm_trigger_tv_delay_record);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        refreshUi();
    }

    public void refreshUi() {
        if (this.mAproObj != null) {
            LogUtils.e("TriggerOfAlarm", "refreshUi1 , mAproObj: " + this.mAproObj.toString());
            try {
                JSONObject recordObj = this.mAproObj.getJSONObject("AR");
                if (recordObj != null) {
                    configureChannelChoices(recordObj.getInt("CH"), this.mListStrRecordChannel, this.mListIntRecordChannel);
                    JSONObject snapObj = this.mAproObj.getJSONObject("SPS");
                    if (snapObj != null) {
                        configureChannelChoices(snapObj.getInt("CH"), this.mListStrSnapChannel, this.mListIntSnapChannel);
                        JSONObject emailObj = this.mAproObj.getJSONObject("AE");
                        if (emailObj != null) {
                            this.mIvEmail.SetImageResource(getSwitchResource(emailObj.getInt("BSE")));
                            JSONObject outputObj = this.mAproObj.getJSONObject("AS");
                            if (outputObj != null) {
                                this.mIvAlarmOutput.SetImageResource(getSwitchResource(outputObj.getInt("SOID")));
                                JSONObject ftpObj = this.mAproObj.getJSONObject("FTPUP");
                                if (ftpObj != null) {
                                    this.mIvFtpUpload.SetImageResource(getSwitchResource(ftpObj.getInt("EN")));
                                    JSONObject pushObj = this.mAproObj.getJSONObject("PUS");
                                    if (pushObj != null) {
                                        this.mIvMsgPush.SetImageResource(getSwitchResource(pushObj.getInt("EN")));
                                        applyChoice(outputDurationToIndex(outputObj.getInt("SOT")), getStrDatas(R.array.AlarmOutputDurationSelector), this.mTvOutputDuration, this.mListStrOutputDuration, this.mListIntOutputDuration);
                                        JSONObject buzzerObj = this.mAproObj.getJSONObject("AB");
                                        if (buzzerObj != null) {
                                            applyChoice(buzzerToIndex(buzzerObj.getInt("BT")), getStrDatas(R.array.BuzzerSelector), this.mTvBuzzer, this.mListStrBuzzer, this.mListIntBuzzer);
                                            JSONObject fullScreenObj = this.mAproObj.getJSONObject("AFS");
                                            if (fullScreenObj != null) {
                                                configureFullScreenChoices(fullScreenObj.getInt("CHL"));
                                                applyChoice(preRecordToIndex(recordObj.getInt("PRS")), getStrDatas(R.array.PreRecordSelector), this.mTvPreRecord, this.mListStrPreRecord, this.mListIntPreRecord);
                                                applyChoice(delayRecordToIndex(recordObj.getInt("DRS")), getStrDatas(R.array.DelayRecordSelector), this.mTvDelayRecord, this.mListStrDelayRecord, this.mListIntDelayRecord);
                                            }
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

    private void configureChannelChoices(int channelMask, ArrayList<String> labelList, List<Integer> selectedList) {
        labelList.clear();
        selectedList.clear();
        for (int channelIndex = 0; channelIndex < this.mCurChTotal; channelIndex++) {
            labelList.add("CH" + (channelIndex + 1));
            if (((channelMask >> channelIndex) & 1) == 1) {
                selectedList.add(new Integer(channelIndex));
            }
        }
    }

    private int getSwitchResource(int enabled) {
        return enabled == 0 ? R.drawable.switch_close : R.drawable.switch_open;
    }

    private void applyChoice(int selectedIndex, List<String> labelSource, TextView textView, ArrayList<String> labelList, List<Integer> selectedList) {
        labelList.clear();
        selectedList.clear();
        if (selectedIndex >= 0 && selectedIndex < labelSource.size()) {
            textView.setText(labelSource.get(selectedIndex));
            labelList.addAll(labelSource);
            selectedList.add(new Integer(selectedIndex));
        }
    }

    private void configureFullScreenChoices(int selectedChannel) {
        this.mListStrFullScreen.clear();
        this.mListIntFullScreen.clear();
        this.mListStrFullScreen.add(UiUtils.getString(R.string.config_none));
        for (int channelIndex = 0; channelIndex < this.mCurChTotal; channelIndex++) {
            this.mListStrFullScreen.add("CH" + (channelIndex + 1));
        }
        if (selectedChannel >= 0 && selectedChannel < this.mListStrFullScreen.size()) {
            this.mTvFullScreenCh.setText(this.mListStrFullScreen.get(selectedChannel));
            this.mListIntFullScreen.add(new Integer(selectedChannel));
        }
    }

    private int outputDurationToIndex(int duration) {
        return duration == 5 ? 0 : duration == 10 ? 1 : duration == 30 ? 2 : duration == 60 ? 3 : duration == 180 ? 4 : duration == 300 ? 5 : duration == 600 ? 6 : duration == 900 ? 7 : duration == 1800 ? 8 : -1;
    }

    private int buzzerToIndex(int duration) {
        return duration == 0 ? 0 : duration == 10 ? 1 : duration == 30 ? 2 : duration == 60 ? 3 : duration == 180 ? 4 : duration == 300 ? 5 : duration == 600 ? 6 : duration == 900 ? 7 : duration == 1800 ? 8 : -1;
    }

    private int preRecordToIndex(int duration) {
        return duration == 0 ? 0 : duration == 5 ? 1 : duration == 10 ? 2 : -1;
    }

    private int delayRecordToIndex(int duration) {
        return duration == 10 ? 0 : duration == 30 ? 1 : duration == 60 ? 2 : duration == 180 ? 3 : duration == 300 ? 4 : duration == 600 ? 5 : duration == 900 ? 6 : duration == 1800 ? 7 : -1;
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(getString(R.string.config_alarm_trigger_Title));
        this.mBtnBack.setOnClickListener(this);
        this.mBtnUpdate.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mRlRecordCh.setOnClickListener(this);
        this.mRlSnapCh.setOnClickListener(this);
        this.mIvEmail.setOnClickListener(this);
        this.mIvAlarmOutput.setOnClickListener(this);
        this.mIvFtpUpload.setOnClickListener(this);
        this.mIvMsgPush.setOnClickListener(this);
        this.mRlOutputDuration.setOnClickListener(this);
        this.mRlBuzzer.setOnClickListener(this);
        this.mRlFullScreenCh.setOnClickListener(this);
        this.mRlPreRecord.setOnClickListener(this);
        this.mRlDelayRecord.setOnClickListener(this);
    }

    public void pushFragmentForRecordChannel() {
        if (this.mAproObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_alarm_trigger_Record), "SelectFragmentForRecordChannel", 1, this.mListStrRecordChannel, this.mListIntRecordChannel);
        }
    }

    public void pushFragmentForSnapChannel() {
        if (this.mAproObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_alarm_trigger_Snapshot), "SelectFragmentForSnapChannel", 1, this.mListStrSnapChannel, this.mListIntSnapChannel);
        }
    }

    public void saveEmailStatus() {
        JSONObject jSONObject = this.mAproObj;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("AE");
                if (jSONObject2 != null) {
                    jSONObject2.put("BSE", jSONObject2.getInt("BSE") == 0 ? 1 : 0);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void saveOutputStatus() {
        JSONObject jSONObject = this.mAproObj;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("AS");
                if (jSONObject2 != null) {
                    jSONObject2.put("SOID", jSONObject2.getInt("SOID") == 0 ? 1 : 0);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void saveFtpUpStatus() {
        JSONObject jSONObject = this.mAproObj;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("FTPUP");
                if (jSONObject2 != null) {
                    jSONObject2.put("EN", jSONObject2.getInt("EN") == 0 ? 1 : 0);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void saveMsgPushStatus() {
        JSONObject jSONObject = this.mAproObj;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("PUS");
                if (jSONObject2 != null) {
                    jSONObject2.put("EN", jSONObject2.getInt("EN") == 0 ? 1 : 0);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForOutputDuration() {
        if (this.mAproObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_alarm_trigger_AlarmOutputDuration), "SelectFragmentForOutputDuration", 0, this.mListStrOutputDuration, this.mListIntOutputDuration);
        }
    }

    public void pushFragmentForOutputBuzzer() {
        if (this.mAproObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_alarm_trigger_Buzzer), "SelectFragmentForBuzzer", 0, this.mListStrBuzzer, this.mListIntBuzzer);
        }
    }

    public void pushFragmentForFullScreen() {
        if (this.mAproObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_alarm_trigger_PopupScreen), "SelectFragmentForFullScreen", 0, this.mListStrFullScreen, this.mListIntFullScreen);
        }
    }

    public void pushFragmentForPreRecord() {
        if (this.mAproObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_alarm_trigger_PreRecord), "SelectFragmentForPreRecord", 0, this.mListStrPreRecord, this.mListIntPreRecord);
        }
    }

    public void pushFragmentForDelayRecord() {
        if (this.mAproObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_alarm_trigger_PostRecord), "SelectFragmentForDelayRecord", 0, this.mListStrDelayRecord, this.mListIntDelayRecord);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.config_title_btn_back) {
            switch (id) {
                case R.id.config_alarm_trigger_iv_alarm_output /*2131362042*/:
                    saveOutputStatus();
                    return;
                case R.id.config_alarm_trigger_iv_email /*2131362043*/:
                    saveEmailStatus();
                    return;
                case R.id.config_alarm_trigger_iv_ftp_upload /*2131362044*/:
                    saveFtpUpStatus();
                    return;
                case R.id.config_alarm_trigger_iv_msg_push /*2131362045*/:
                    saveMsgPushStatus();
                    return;
                case R.id.config_alarm_trigger_rl_alarm_output_duration /*2131362046*/:
                    pushFragmentForOutputDuration();
                    return;
                case R.id.config_alarm_trigger_rl_buzzer /*2131362047*/:
                    pushFragmentForOutputBuzzer();
                    return;
                case R.id.config_alarm_trigger_rl_delay_record /*2131362048*/:
                    pushFragmentForDelayRecord();
                    return;
                case R.id.config_alarm_trigger_rl_fullscreen_channel /*2131362049*/:
                    pushFragmentForFullScreen();
                    return;
                case R.id.config_alarm_trigger_rl_prerecord /*2131362050*/:
                    pushFragmentForPreRecord();
                    return;
                case R.id.config_alarm_trigger_rl_record_channel /*2131362051*/:
                    pushFragmentForRecordChannel();
                    return;
                case R.id.config_alarm_trigger_rl_snap_channel /*2131362052*/:
                    pushFragmentForSnapChannel();
                    return;
                default:
                    return;
            }
        } else {
            prePage();
        }
    }

    public void setConfig() {
        NetPresenter.getDefault().setConfig(this);
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

    public void updateDateForRecordChannel(List<Integer> selectedChannels) {
        JSONObject aproObj = this.mAproObj;
        if (aproObj != null) {
            try {
                JSONObject recordObj = aproObj.getJSONObject("AR");
                if (recordObj != null) {
                    int channelMask = 0;
                    for (int selectionIndex = 0; selectionIndex < selectedChannels.size(); selectionIndex++) {
                        channelMask |= 1 << selectedChannels.get(selectionIndex).intValue();
                    }
                    recordObj.put("CH", channelMask);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForSnapChannel(List<Integer> selectedChannels) {
        JSONObject aproObj = this.mAproObj;
        if (aproObj != null) {
            try {
                JSONObject snapObj = aproObj.getJSONObject("SPS");
                if (snapObj != null) {
                    int channelMask = 0;
                    for (int selectionIndex = 0; selectionIndex < selectedChannels.size(); selectionIndex++) {
                        channelMask |= 1 << selectedChannels.get(selectionIndex).intValue();
                    }
                    snapObj.put("CH", channelMask);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForOutputDuration(int selectedIndex) {
        JSONObject aproObj = this.mAproObj;
        if (aproObj != null) {
            try {
                JSONObject outputObj = aproObj.getJSONObject("AS");
                if (outputObj != null) {
                    int durationSeconds = getOutputDurationSeconds(selectedIndex);
                    if (durationSeconds >= 0) {
                        outputObj.put("SOT", durationSeconds);
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForBuzzer(int selectedIndex) {
        JSONObject aproObj = this.mAproObj;
        if (aproObj != null) {
            try {
                JSONObject buzzerObj = aproObj.getJSONObject("AB");
                if (buzzerObj != null) {
                    int buzzerSeconds = getBuzzerSeconds(selectedIndex);
                    if (buzzerSeconds >= 0) {
                        buzzerObj.put("BT", buzzerSeconds);
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForFullScreen(int channelIndex) {
        JSONObject aproObj = this.mAproObj;
        if (aproObj != null) {
            try {
                JSONObject fullScreenObj = aproObj.getJSONObject("AFS");
                if (fullScreenObj != null) {
                    fullScreenObj.put("CHL", channelIndex);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForPreRecord(int selectedIndex) {
        JSONObject aproObj = this.mAproObj;
        if (aproObj != null) {
            try {
                JSONObject recordObj = aproObj.getJSONObject("AR");
                if (recordObj != null) {
                    int preRecordSeconds = getPreRecordSeconds(selectedIndex);
                    if (preRecordSeconds >= 0) {
                        recordObj.put("PRS", preRecordSeconds);
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForDelayRecord(int selectedIndex) {
        JSONObject aproObj = this.mAproObj;
        if (aproObj != null) {
            try {
                JSONObject recordObj = aproObj.getJSONObject("AR");
                if (recordObj != null) {
                    int delaySeconds = getDelayRecordSeconds(selectedIndex);
                    if (delaySeconds >= 0) {
                        recordObj.put("DRS", delaySeconds);
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    private int getOutputDurationSeconds(int selectedIndex) {
        int[] durations = {5, 10, 30, 60, 180, Strategy.TTL_SECONDS_DEFAULT, 600, MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR, 1800};
        if (selectedIndex < 0 || selectedIndex >= durations.length) {
            return -1;
        }
        return durations[selectedIndex];
    }

    private int getBuzzerSeconds(int selectedIndex) {
        int[] durations = {0, 10, 30, 60, 180, Strategy.TTL_SECONDS_DEFAULT, 600, MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR, 1800};
        if (selectedIndex < 0 || selectedIndex >= durations.length) {
            return -1;
        }
        return durations[selectedIndex];
    }

    private int getPreRecordSeconds(int selectedIndex) {
        int[] durations = {0, 5, 10};
        if (selectedIndex < 0 || selectedIndex >= durations.length) {
            return -1;
        }
        return durations[selectedIndex];
    }

    private int getDelayRecordSeconds(int selectedIndex) {
        int[] durations = {10, 30, 60, 180, Strategy.TTL_SECONDS_DEFAULT, 600, MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR, 1800};
        if (selectedIndex < 0 || selectedIndex >= durations.length) {
            return -1;
        }
        return durations[selectedIndex];
    }

    public void saveSelect(String fragmentTag, List<Integer> selectedIndexes) {
        if (fragmentTag.equals("SelectFragmentForRecordChannel")) {
            updateDateForRecordChannel(selectedIndexes);
        } else if (fragmentTag.equals("SelectFragmentForSnapChannel")) {
            updateDateForSnapChannel(selectedIndexes);
        } else if (fragmentTag.equals("SelectFragmentForOutputDuration")) {
            if (selectedIndexes.size() > 0) {
                updateDateForOutputDuration(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForBuzzer")) {
            if (selectedIndexes.size() > 0) {
                updateDateForBuzzer(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForFullScreen")) {
            if (selectedIndexes.size() > 0) {
                updateDateForFullScreen(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForPreRecord")) {
            if (selectedIndexes.size() > 0) {
                updateDateForPreRecord(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForDelayRecord") && selectedIndexes.size() > 0) {
            updateDateForDelayRecord(selectedIndexes.get(0).intValue());
        }
    }
}
