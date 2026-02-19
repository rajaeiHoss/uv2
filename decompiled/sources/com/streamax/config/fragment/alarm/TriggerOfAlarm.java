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
                JSONObject jSONObject = this.mAproObj.getJSONObject("AR");
                if (jSONObject != null) {
                    int i = jSONObject.getInt("CH");
                    this.mListStrRecordChannel.clear();
                    this.mListIntRecordChannel.clear();
                    int i2 = 0;
                    while (i2 < this.mCurChTotal) {
                        ArrayList<String> arrayList = this.mListStrRecordChannel;
                        StringBuilder sb = new StringBuilder();
                        sb.append("CH");
                        int i3 = i2 + 1;
                        sb.append(i3);
                        arrayList.add(sb.toString());
                        if (((i >> i2) & 1) == 1) {
                            this.mListIntRecordChannel.add(new Integer(i2));
                        }
                        i2 = i3;
                    }
                    JSONObject jSONObject2 = this.mAproObj.getJSONObject("SPS");
                    if (jSONObject2 != null) {
                        int i4 = jSONObject2.getInt("CH");
                        this.mListStrSnapChannel.clear();
                        this.mListIntSnapChannel.clear();
                        int i5 = 0;
                        while (i5 < this.mCurChTotal) {
                            ArrayList<String> arrayList2 = this.mListStrSnapChannel;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("CH");
                            int i6 = i5 + 1;
                            sb2.append(i6);
                            arrayList2.add(sb2.toString());
                            if (((i4 >> i5) & 1) == 1) {
                                this.mListIntSnapChannel.add(new Integer(i5));
                            }
                            i5 = i6;
                        }
                        JSONObject jSONObject3 = this.mAproObj.getJSONObject("AE");
                        if (jSONObject3 != null) {
                            int i7 = jSONObject3.getInt("BSE");
                            IvView ivView = this.mIvEmail;
                            int i8 = R.drawable.switch_close;
                            ivView.SetImageResource(i7 == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                            JSONObject jSONObject4 = this.mAproObj.getJSONObject("AS");
                            if (jSONObject4 != null) {
                                this.mIvAlarmOutput.SetImageResource(jSONObject4.getInt("SOID") == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                                JSONObject jSONObject5 = this.mAproObj.getJSONObject("FTPUP");
                                if (jSONObject5 != null) {
                                    this.mIvFtpUpload.SetImageResource(jSONObject5.getInt("EN") == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                                    JSONObject jSONObject6 = this.mAproObj.getJSONObject("PUS");
                                    if (jSONObject6 != null) {
                                        int i9 = jSONObject6.getInt("EN");
                                        IvView ivView2 = this.mIvMsgPush;
                                        if (i9 != 0) {
                                            i8 = R.drawable.switch_open;
                                        }
                                        ivView2.SetImageResource(i8);
                                        int i10 = jSONObject4.getInt("SOT");
                                        this.mListStrOutputDuration.clear();
                                        this.mListIntOutputDuration.clear();
                                        int i11 = i10 == 5 ? 0 : i10 == 10 ? 1 : i10 == 30 ? 2 : i10 == 60 ? 3 : i10 == 180 ? 4 : i10 == 300 ? 5 : i10 == 600 ? 6 : i10 == 900 ? 7 : i10 == 1800 ? 8 : -1;
                                        List<String> strDatas = getStrDatas(R.array.AlarmOutputDurationSelector);
                                        if (i11 >= 0 && i11 < strDatas.size()) {
                                            this.mTvOutputDuration.setText(strDatas.get(i11));
                                            this.mListStrOutputDuration.addAll(strDatas);
                                            this.mListIntOutputDuration.add(new Integer(i11));
                                        }
                                        JSONObject jSONObject7 = this.mAproObj.getJSONObject("AB");
                                        if (jSONObject7 != null) {
                                            int i12 = jSONObject7.getInt("BT");
                                            this.mListStrBuzzer.clear();
                                            this.mListIntBuzzer.clear();
                                            int i13 = i12 == 0 ? 0 : i12 == 10 ? 1 : i12 == 30 ? 2 : i12 == 60 ? 3 : i12 == 180 ? 4 : i12 == 300 ? 5 : i12 == 600 ? 6 : i12 == 900 ? 7 : i12 == 1800 ? 8 : -1;
                                            List<String> strDatas2 = getStrDatas(R.array.BuzzerSelector);
                                            if (i13 >= 0 && i13 < strDatas2.size()) {
                                                this.mTvBuzzer.setText(strDatas2.get(i13));
                                                this.mListStrBuzzer.addAll(strDatas2);
                                                this.mListIntBuzzer.add(new Integer(i13));
                                            }
                                            JSONObject jSONObject8 = this.mAproObj.getJSONObject("AFS");
                                            if (jSONObject8 != null) {
                                                int i14 = jSONObject8.getInt("CHL");
                                                this.mListStrFullScreen.clear();
                                                this.mListIntFullScreen.clear();
                                                this.mListStrFullScreen.add(UiUtils.getString(R.string.config_none));
                                                int i15 = 0;
                                                while (i15 < this.mCurChTotal) {
                                                    ArrayList<String> arrayList3 = this.mListStrFullScreen;
                                                    StringBuilder sb3 = new StringBuilder();
                                                    sb3.append("CH");
                                                    i15++;
                                                    sb3.append(i15);
                                                    arrayList3.add(sb3.toString());
                                                }
                                                if (i14 >= 0 && i14 < this.mListStrFullScreen.size()) {
                                                    this.mTvFullScreenCh.setText(this.mListStrFullScreen.get(i14));
                                                    this.mListIntFullScreen.add(new Integer(i14));
                                                }
                                                int i16 = jSONObject.getInt("PRS");
                                                this.mListStrPreRecord.clear();
                                                this.mListIntPreRecord.clear();
                                                int i17 = i16 == 0 ? 0 : i16 == 5 ? 1 : i16 == 10 ? 2 : -1;
                                                List<String> strDatas3 = getStrDatas(R.array.PreRecordSelector);
                                                if (i17 >= 0 && i17 < strDatas3.size()) {
                                                    this.mTvPreRecord.setText(strDatas3.get(i17));
                                                    this.mListStrPreRecord.addAll(strDatas3);
                                                    this.mListIntPreRecord.add(new Integer(i17));
                                                }
                                                int i18 = jSONObject.getInt("DRS");
                                                this.mListStrDelayRecord.clear();
                                                this.mListIntDelayRecord.clear();
                                                int i19 = i18 == 10 ? 0 : i18 == 30 ? 1 : i18 == 60 ? 2 : i18 == 180 ? 3 : i18 == 300 ? 4 : i18 == 600 ? 5 : i18 == 900 ? 6 : i18 == 1800 ? 7 : -1;
                                                List<String> strDatas4 = getStrDatas(R.array.DelayRecordSelector);
                                                if (i19 >= 0 && i19 < strDatas4.size()) {
                                                    this.mTvDelayRecord.setText(strDatas4.get(i19));
                                                    this.mListStrDelayRecord.addAll(strDatas4);
                                                    this.mListIntDelayRecord.add(new Integer(i19));
                                                }
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

    public void updateDateForRecordChannel(List<Integer> list) {
        JSONObject jSONObject = this.mAproObj;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("AR");
                if (jSONObject2 != null) {
                    int i = 0;
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        i |= 1 << list.get(i2).intValue();
                    }
                    jSONObject2.put("CH", i);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForSnapChannel(List<Integer> list) {
        JSONObject jSONObject = this.mAproObj;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("SPS");
                if (jSONObject2 != null) {
                    int i = 0;
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        i |= 1 << list.get(i2).intValue();
                    }
                    jSONObject2.put("CH", i);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForOutputDuration(int i) {
        JSONObject jSONObject = this.mAproObj;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("AS");
                if (jSONObject2 != null) {
                    int i2 = -1;
                    if (i == 0) {
                        i2 = 5;
                    } else if (i == 1) {
                        i2 = 10;
                    } else if (i == 2) {
                        i2 = 30;
                    } else if (i == 3) {
                        i2 = 60;
                    } else if (i == 4) {
                        i2 = 180;
                    } else if (i == 5) {
                        i2 = Strategy.TTL_SECONDS_DEFAULT;
                    } else if (i == 6) {
                        i2 = 600;
                    } else if (i == 7) {
                        i2 = MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR;
                    } else if (i == 8) {
                        i2 = 1800;
                    }
                    if (i2 >= 0) {
                        jSONObject2.put("SOT", i2);
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForBuzzer(int i) {
        JSONObject jSONObject = this.mAproObj;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("AB");
                if (jSONObject2 != null) {
                    int i2 = -1;
                    if (i == 0) {
                        i2 = 0;
                    } else if (i == 1) {
                        i2 = 10;
                    } else if (i == 2) {
                        i2 = 30;
                    } else if (i == 3) {
                        i2 = 60;
                    } else if (i == 4) {
                        i2 = 180;
                    } else if (i == 5) {
                        i2 = Strategy.TTL_SECONDS_DEFAULT;
                    } else if (i == 6) {
                        i2 = 600;
                    } else if (i == 7) {
                        i2 = MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR;
                    } else if (i == 8) {
                        i2 = 1800;
                    }
                    if (i2 >= 0) {
                        jSONObject2.put("BT", i2);
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForFullScreen(int i) {
        JSONObject jSONObject = this.mAproObj;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("AFS");
                if (jSONObject2 != null) {
                    jSONObject2.put("CHL", i);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForPreRecord(int i) {
        JSONObject jSONObject = this.mAproObj;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("AR");
                if (jSONObject2 != null) {
                    int i2 = -1;
                    if (i == 0) {
                        i2 = 0;
                    } else if (i == 1) {
                        i2 = 5;
                    } else if (i == 2) {
                        i2 = 10;
                    }
                    if (i2 >= 0) {
                        jSONObject2.put("PRS", i2);
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForDelayRecord(int i) {
        JSONObject jSONObject = this.mAproObj;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("AR");
                if (jSONObject2 != null) {
                    int i2 = -1;
                    if (i == 0) {
                        i2 = 10;
                    } else if (i == 1) {
                        i2 = 30;
                    } else if (i == 2) {
                        i2 = 60;
                    } else if (i == 3) {
                        i2 = 180;
                    } else if (i == 4) {
                        i2 = Strategy.TTL_SECONDS_DEFAULT;
                    } else if (i == 5) {
                        i2 = 600;
                    } else if (i == 6) {
                        i2 = MediaPlayer2.MEDIA_INFO_TIMED_TEXT_ERROR;
                    } else if (i == 7) {
                        i2 = 1800;
                    }
                    if (i2 >= 0) {
                        jSONObject2.put("DRS", i2);
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForRecordChannel")) {
            updateDateForRecordChannel(list);
        } else if (str.equals("SelectFragmentForSnapChannel")) {
            updateDateForSnapChannel(list);
        } else if (str.equals("SelectFragmentForOutputDuration")) {
            if (list.size() > 0) {
                updateDateForOutputDuration(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForBuzzer")) {
            if (list.size() > 0) {
                updateDateForBuzzer(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForFullScreen")) {
            if (list.size() > 0) {
                updateDateForFullScreen(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForPreRecord")) {
            if (list.size() > 0) {
                updateDateForPreRecord(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForDelayRecord") && list.size() > 0) {
            updateDateForDelayRecord(list.get(0).intValue());
        }
    }
}
