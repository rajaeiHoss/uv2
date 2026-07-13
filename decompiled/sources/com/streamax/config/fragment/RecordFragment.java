package com.streamax.config.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.fragment.record.PlanOfRecord;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.UiUtils;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecordFragment extends ConfigFragment implements BaseListener.GetListener, BaseListener.SetListener {
    public Button mBtnCopy;
    public Button mBtnEnable;
    public int mCurCh;
    public int mCurChTotal;
    public List<Integer> mListIntChannel = new ArrayList();
    public List<Integer> mListIntMode = new ArrayList();
    public ArrayList<String> mListStrChannel = new ArrayList<>();
    public ArrayList<String> mListStrMode = new ArrayList<>();
    public JSONArray mRecArr;
    public JSONObject mRecObj;
    public JSONObject mRecRes;
    public RelativeLayout mRlChSelector;
    public RelativeLayout mRlPlan;
    public RelativeLayout mRlRmSelector;
    public TextView mTvCurCh;
    public VsTextView mTvRecordMode;
    public View mViewToHide;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_record_Title);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mCurCh = 0;
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_record, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    public void initChildView() {
        this.mTvCurCh = (TextView) this.mRootView.findViewById(R.id.config_record_tv_ch);
        this.mRlChSelector = (RelativeLayout) this.mRootView.findViewById(R.id.config_record_rl_ch_selector);
        this.mBtnEnable = (Button) this.mRootView.findViewById(R.id.config_record_btn_enable);
        this.mTvRecordMode = (VsTextView) this.mRootView.findViewById(R.id.config_record_tv_record_mode);
        this.mRlRmSelector = (RelativeLayout) this.mRootView.findViewById(R.id.config_record_rl_rm_selector);
        this.mViewToHide = this.mRootView.findViewById(R.id.config_record_view_to_hide);
        this.mRlPlan = (RelativeLayout) this.mRootView.findViewById(R.id.config_record_rl_plan);
        this.mBtnCopy = (Button) this.mRootView.findViewById(R.id.config_btn_copy_to);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void refreshUi() {
        JSONObject recordResponse = this.mRecRes;
        if (recordResponse != null) {
            try {
                JSONObject avSettings = recordResponse.getJSONObject("AVSM");
                if (avSettings != null) {
                    JSONArray recordEntries = avSettings.getJSONArray("REP");
                    this.mRecArr = recordEntries;
                    if (recordEntries != null) {
                        int channelCount = recordEntries.length();
                        this.mCurChTotal = channelCount;
                        if (channelCount > 0) {
                            int currentChannel = this.mCurCh;
                            if (currentChannel < channelCount) {
                                JSONObject currentRecordConfig = this.mRecArr.getJSONObject(currentChannel);
                                this.mRecObj = currentRecordConfig;
                                if (currentRecordConfig != null) {
                                    TextView textView = this.mTvCurCh;
                                    textView.setText("CH" + (this.mCurCh + 1));
                                    this.mListStrChannel.clear();
                                    this.mListIntChannel.clear();
                                    for (int channelIndex = 0; channelIndex < this.mCurChTotal; channelIndex++) {
                                        ArrayList<String> arrayList = this.mListStrChannel;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("CH");
                                        sb.append(channelIndex + 1);
                                        arrayList.add(sb.toString());
                                    }
                                    this.mListIntChannel.add(new Integer(this.mCurCh));
                                    this.mBtnEnable.setBackgroundResource(this.mRecObj.getInt("EN") <= 0 ? R.drawable.switch_close : R.drawable.switch_open);
                                    int recordMode = this.mRecObj.getInt("RM");
                                    this.mListStrMode.clear();
                                    this.mListIntMode.clear();
                                    List<String> strDatas = getStrDatas(R.array.config_record_rm_rmSelector);
                                    if (recordMode >= 0 && recordMode < strDatas.size()) {
                                        this.mTvRecordMode.setText(strDatas.get(recordMode));
                                        this.mListStrMode.addAll(strDatas);
                                        this.mListIntMode.add(new Integer(recordMode));
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
    public void initEvent() {
        this.mRlChSelector.setOnClickListener(this);
        this.mBtnEnable.setOnClickListener(this);
        this.mRlPlan.setOnClickListener(this);
        this.mRlRmSelector.setOnClickListener(this);
        this.mBtnCopy.setOnClickListener(this);
    }

    public void pushFragmentForChannel() {
        if (this.mRecArr != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Channel), "SelectFragmentForChannel", 0, this.mListStrChannel, this.mListIntChannel);
        }
    }

    public void pushFragmentForMode() {
        if (this.mRecObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_record_RecordType), "SelectFragmentForMode", 0, this.mListStrMode, this.mListIntMode);
        }
    }

    public void pushFragmentForPlan() {
        JSONObject jSONObject;
        if (this.mRecRes != null && (jSONObject = this.mRecObj) != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("VPLAN");
                if (jSONObject2 != null) {
                    PlanOfRecord planOfRecord = new PlanOfRecord();
                    planOfRecord.setPlanData(this.mRecRes, jSONObject2);
                    nextPage(planOfRecord);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForCopy() {
        if (this.mRecArr != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_CopyChToCh), "SelectFragmentForCopy", 1, this.mListStrChannel, (List<Integer>) null);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.config_btn_copy_to /*2131362065*/:
                pushFragmentForCopy();
                return;
            case R.id.config_record_btn_enable /*2131362141*/:
                saveRecordStatus();
                return;
            case R.id.config_record_rl_ch_selector /*2131362143*/:
                pushFragmentForChannel();
                return;
            case R.id.config_record_rl_plan /*2131362145*/:
                pushFragmentForPlan();
                return;
            case R.id.config_record_rl_rm_selector /*2131362146*/:
                pushFragmentForMode();
                return;
            case R.id.config_title_btn_back /*2131362205*/:
                prePage();
                return;
            default:
                return;
        }
    }

    public void saveRecordStatus() {
        JSONObject jSONObject = this.mRecObj;
        if (jSONObject != null) {
            try {
                this.mRecObj.put("EN", jSONObject.getInt("EN") == 0 ? 1 : 0);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("REP", "?");
            jSONObject.put("AVSM", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String responseJson) {
        try {
            this.mRecRes = new JSONObject(responseJson);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
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
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForChannel(int channelIndex) {
        if (this.mRecArr != null && this.mCurCh != channelIndex) {
            this.mCurCh = channelIndex;
            refreshUi();
        }
    }

    public void updateDateForMode(int recordMode) {
        JSONObject jSONObject = this.mRecObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("RM", recordMode);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForCopy(List<Integer> list) {
        JSONArray recordEntries = this.mRecArr;
        if (recordEntries != null) {
            try {
                JSONObject sourceRecordConfig = recordEntries.getJSONObject(this.mCurCh);
                if (sourceRecordConfig != null) {
                    boolean hasCopiedConfig = false;
                    for (int selectedIndex = 0; selectedIndex < list.size(); selectedIndex++) {
                        int targetChannel = list.get(selectedIndex).intValue();
                        if (targetChannel >= 0 && targetChannel != this.mCurCh && targetChannel < this.mCurChTotal) {
                            this.mRecArr.put(targetChannel, new JSONObject(sourceRecordConfig.toString()));
                            hasCopiedConfig = true;
                        }
                    }
                    if (hasCopiedConfig) {
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String selectorTag, List<Integer> list) {
        if (selectorTag.equals("SelectFragmentForChannel")) {
            if (list.size() > 0) {
                updateDateForChannel(list.get(0).intValue());
            }
        } else if (selectorTag.equals("SelectFragmentForMode")) {
            if (list.size() > 0) {
                updateDateForMode(list.get(0).intValue());
            }
        } else if (selectorTag.equals("SelectFragmentForCopy") && list.size() > 0) {
            updateDateForCopy(list);
        }
    }

    public void saveRecordPlan() {
        NetPresenter.getDefault().setConfig(this);
    }
}
