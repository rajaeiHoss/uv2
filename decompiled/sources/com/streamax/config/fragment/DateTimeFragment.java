package com.streamax.config.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pickview.TimePickerView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.fragment.datetime.DstOfDt;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.StringUtils;
import com.streamax.utils.TimeUtils;
import com.streamax.utils.UiUtils;
import com.zycs.UView.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class DateTimeFragment extends ConfigFragment implements BaseListener.SetListener, BaseListener.GetListener, TimeUtils.TimePickListener {
    public static final int SetConfig_DateTime = 2;
    public static final int SetConfig_Ntp = 3;
    public static final int SetConfig_Status = 1;
    public Button mBtnNtpStatus;
    public JSONObject mDateTimeRes;
    public List<Integer> mListIntDateFormat = new ArrayList();
    public List<Integer> mListIntNtpServer = new ArrayList();
    public List<Integer> mListIntTimeFormat = new ArrayList();
    public List<Integer> mListIntTimeZone = new ArrayList();
    public ArrayList<String> mListNtpServer = new ArrayList<>();
    public ArrayList<String> mListStrDateFormat = new ArrayList<>();
    public ArrayList<String> mListStrNtpServer = new ArrayList<>();
    public ArrayList<String> mListStrTimeFormat = new ArrayList<>();
    public ArrayList<String> mListStrTimeZone = new ArrayList<>();
    public ArrayList<String> mListTimeZoneCase = new ArrayList<>();
    public ArrayList<String> mListTimeZoneLongName = new ArrayList<>();
    public ArrayList<String> mListTimeZoneShortName = new ArrayList<>();
    public LinearLayout mLlDateTime;
    public JSONObject mNtpObj;
    public RelativeLayout mRlDataFormat;
    public RelativeLayout mRlDst;
    public RelativeLayout mRlNtpServer;
    public RelativeLayout mRlTimeFormat;
    public RelativeLayout mRlTimeZone;
    public int mSetConfig;
    public JSONObject mTimePObj;
    public TextView mTvDateFormat;
    public TextView mTvDateTime;
    public TextView mTvNtpServerName;
    public TextView mTvTimeFormat;
    public TextView mTvTimeZone;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mListTimeZoneLongName.addAll(getStrDatas(R.array.TimeZoneLongName));
        this.mListTimeZoneShortName.addAll(getStrDatas(R.array.TimeZoneShortName));
        this.mListTimeZoneCase.addAll(getStrDatas(R.array.TimeZoneCase));
        this.mListNtpServer.addAll(getStrDatas(R.array.NtpServerName));
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_dateTime_Title);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_datetime, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    public void initChildView() {
        this.mLlDateTime = (LinearLayout) this.mRootView.findViewById(R.id.config_datetime_ll_datetime);
        this.mTvDateTime = (TextView) this.mRootView.findViewById(R.id.config_datetime_tv_datetime);
        this.mRlDataFormat = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_rl_dateFormat);
        this.mTvDateFormat = (TextView) this.mRootView.findViewById(R.id.config_datetime_tv_dateformat);
        this.mRlTimeFormat = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_rl_timeFormat);
        this.mTvTimeFormat = (TextView) this.mRootView.findViewById(R.id.config_datetime_tv_timeFormat);
        this.mRlTimeZone = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_rl_timeZone);
        this.mTvTimeZone = (TextView) this.mRootView.findViewById(R.id.config_datetime_tv_timeZone);
        this.mBtnNtpStatus = (Button) this.mRootView.findViewById(R.id.config_datetime_btn_ntpStatus);
        this.mRlNtpServer = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_rl_ntpServer);
        this.mTvNtpServerName = (TextView) this.mRootView.findViewById(R.id.config_datetime_tv_ntp_server_name);
        this.mRlDst = (RelativeLayout) this.mRootView.findViewById(R.id.config_datetime_rl_dst);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void refreshUi() {
        int selectedNtpServerIndex;
        if (this.mDateTimeRes != null && this.mListTimeZoneLongName.size() == this.mListTimeZoneShortName.size() && this.mListTimeZoneLongName.size() == this.mListTimeZoneCase.size()) {
            try {
                JSONObject deviceManagerConfig = this.mDateTimeRes.getJSONObject("DEVEMM");
                JSONObject networkConfig = this.mDateTimeRes.getJSONObject("NWSM");
                JSONObject statusConfig = this.mDateTimeRes.getJSONObject("STATUS");
                if (deviceManagerConfig != null && networkConfig != null) {
                    if (statusConfig != null) {
                        this.mTimePObj = deviceManagerConfig.getJSONObject("TIMEP");
                        JSONObject ntpConfig = networkConfig.getJSONObject("NTP");
                        this.mNtpObj = ntpConfig;
                        if (this.mTimePObj == null) {
                            return;
                        }
                        if (ntpConfig != null) {
                            String deviceTime = statusConfig.getString("TIME");
                            int ntpServerIndex = 0;
                            if (deviceTime.length() == 14) {
                                this.mTvDateTime.setText(String.format("%04d-%02d-%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(StringUtils.parse2Int(deviceTime.substring(0, 4))), Integer.valueOf(StringUtils.parse2Int(deviceTime.substring(4, 6))), Integer.valueOf(StringUtils.parse2Int(deviceTime.substring(6, 8))), Integer.valueOf(StringUtils.parse2Int(deviceTime.substring(8, 10))), Integer.valueOf(StringUtils.parse2Int(deviceTime.substring(10, 12))), Integer.valueOf(StringUtils.parse2Int(deviceTime.substring(12, 14)))}));
                            }
                            int dateFormatIndex = this.mTimePObj.getInt("DATEM");
                            this.mListStrDateFormat.clear();
                            this.mListIntDateFormat.clear();
                            List<String> dateFormatLabels = getStrDatas(R.array.DateFormat);
                            if (dateFormatIndex >= 0 && dateFormatIndex < dateFormatLabels.size()) {
                                this.mTvDateFormat.setText(dateFormatLabels.get(dateFormatIndex));
                                this.mListStrDateFormat.addAll(dateFormatLabels);
                                this.mListIntDateFormat.add(new Integer(dateFormatIndex));
                            }
                            int timeFormatIndex = this.mTimePObj.getInt("TIMEM");
                            this.mListStrTimeFormat.clear();
                            this.mListIntTimeFormat.clear();
                            List<String> timeFormatLabels = getStrDatas(R.array.TimeFormat);
                            if (timeFormatIndex >= 0 && timeFormatIndex < timeFormatLabels.size()) {
                                this.mTvTimeFormat.setText(timeFormatLabels.get(timeFormatIndex));
                                this.mListStrTimeFormat.addAll(timeFormatLabels);
                                this.mListIntTimeFormat.add(new Integer(timeFormatIndex));
                            }
                            String timeZoneValue = this.mTimePObj.getString("TIMEZ");
                            this.mListStrTimeZone.clear();
                            this.mListIntTimeZone.clear();
                            int timeZoneIndex = 0;
                            while (true) {
                                selectedNtpServerIndex = -1;
                                if (timeZoneIndex >= this.mListTimeZoneCase.size()) {
                                    timeZoneIndex = -1;
                                    break;
                                } else if (timeZoneValue.equalsIgnoreCase(this.mListTimeZoneCase.get(timeZoneIndex))) {
                                    break;
                                } else {
                                    timeZoneIndex++;
                                }
                            }
                            if (timeZoneIndex >= 0) {
                                this.mTvTimeZone.setText(this.mListTimeZoneShortName.get(timeZoneIndex));
                                this.mListStrTimeZone.addAll(this.mListTimeZoneLongName);
                                this.mListIntTimeZone.add(new Integer(timeZoneIndex));
                            }
                            this.mBtnNtpStatus.setBackgroundResource(this.mNtpObj.getInt("NTPSWITCH") == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                            this.mListStrNtpServer.clear();
                            this.mListIntNtpServer.clear();
                            String ntpServerName = this.mNtpObj.getString("SERVERNAME");
                            while (true) {
                                if (ntpServerIndex >= this.mListNtpServer.size()) {
                                    break;
                                } else if (ntpServerName.equalsIgnoreCase(this.mListNtpServer.get(ntpServerIndex))) {
                                    selectedNtpServerIndex = ntpServerIndex;
                                    break;
                                } else {
                                    ntpServerIndex++;
                                }
                            }
                            if (selectedNtpServerIndex >= 0) {
                                this.mTvNtpServerName.setText(this.mListNtpServer.get(selectedNtpServerIndex));
                                this.mListStrNtpServer.addAll(this.mListNtpServer);
                                this.mListIntNtpServer.add(new Integer(selectedNtpServerIndex));
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
        this.mLlDateTime.setOnClickListener(this);
        this.mRlDataFormat.setOnClickListener(this);
        this.mRlTimeFormat.setOnClickListener(this);
        this.mRlTimeZone.setOnClickListener(this);
        this.mBtnNtpStatus.setOnClickListener(this);
        this.mRlNtpServer.setOnClickListener(this);
        this.mRlDst.setOnClickListener(this);
    }

    public void setTime(TextView textView) {
        String string = getString(textView);
        if (!string.isEmpty() && string.length() == 19) {
            try {
                TimeUtils.setTimePickListener(this.mConfigUi, textView, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(string), TimePickerView.Type.ALL, this);
            } catch (ParseException unused) {
            }
        }
    }

    public void pushFragmentForDateFormat() {
        if (this.mTimePObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_dateTime_DateFormat), "SelectFragmentForDateFormat", 0, this.mListStrDateFormat, this.mListIntDateFormat);
        }
    }

    public void pushFragmentForTimeFormat() {
        if (this.mTimePObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_dateTime_TimeFormat), "SelectFragmentForTimeFormat", 0, this.mListStrTimeFormat, this.mListIntTimeFormat);
        }
    }

    public void pushFragmentForTimeZone() {
        if (this.mTimePObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_dateTime_TimeZone), "SelectFragmentForTimeZone", 0, this.mListStrTimeZone, this.mListIntTimeZone);
        }
    }

    public void saveNtpStatus() {
        JSONObject ntpConfig = this.mNtpObj;
        if (ntpConfig != null) {
            this.mSetConfig = 3;
            try {
                this.mNtpObj.put("NTPSWITCH", ntpConfig.getInt("NTPSWITCH") == 0 ? 1 : 0);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForNtpServer() {
        if (this.mNtpObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_dateTime_TimeServer), "SelectFragmentForNtpServer", 0, this.mListStrNtpServer, this.mListIntNtpServer);
        }
    }

    public void loadDstOfDt() {
        if (this.mDateTimeRes != null) {
            nextPage(new DstOfDt());
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.config_datetime_btn_ntpStatus) {
            saveNtpStatus();
        } else if (id != R.id.config_title_btn_back) {
            switch (id) {
                case R.id.config_datetime_ll_datetime /*2131362106*/:
                    setTime(this.mTvDateTime);
                    return;
                case R.id.config_datetime_rl_dateFormat /*2131362107*/:
                    pushFragmentForDateFormat();
                    return;
                case R.id.config_datetime_rl_dst /*2131362108*/:
                    loadDstOfDt();
                    return;
                case R.id.config_datetime_rl_ntpServer /*2131362109*/:
                    pushFragmentForNtpServer();
                    return;
                case R.id.config_datetime_rl_timeFormat /*2131362110*/:
                    pushFragmentForTimeFormat();
                    return;
                case R.id.config_datetime_rl_timeZone /*2131362111*/:
                    pushFragmentForTimeZone();
                    return;
                default:
                    return;
            }
        } else {
            prePage();
        }
    }

    public void setTimePickListener(TextView textView, Date date) {
        String time = TimeUtils.getTime(date, "yyyy-MM-dd HH:mm:ss");
        textView.setText(time);
        this.mSetConfig = 1;
        try {
            JSONObject statusConfig = this.mDateTimeRes.getJSONObject("STATUS");
            if (statusConfig != null) {
                statusConfig.put("TIME", time.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
                NetPresenter.getDefault().setConfig(this);
            }
        } catch (JSONException unused) {
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject requestRoot = new JSONObject();
            JSONObject deviceManagerConfig = new JSONObject();
            deviceManagerConfig.put("TIMEP", "?");
            JSONObject networkConfig = new JSONObject();
            networkConfig.put("NTP", "?");
            JSONObject statusConfig = new JSONObject();
            statusConfig.put("TIME", "?");
            requestRoot.put("DEVEMM", deviceManagerConfig);
            requestRoot.put("NWSM", networkConfig);
            requestRoot.put("STATUS", statusConfig);
            return requestRoot.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String jsonResponse) {
        try {
            this.mDateTimeRes = new JSONObject(jsonResponse);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        int setConfigMode = this.mSetConfig;
        if (setConfigMode == 1) {
            String deviceTime = getString(this.mTvDateTime).replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
            try {
                JSONObject requestRoot = new JSONObject();
                JSONObject statusConfig = new JSONObject();
                statusConfig.put("TIME", deviceTime);
                requestRoot.put("STATUS", statusConfig);
                return requestRoot.toString();
            } catch (JSONException unused) {
                return "";
            }
        } else if (setConfigMode == 2) {
            JSONObject timeParamConfig = this.mTimePObj;
            if (timeParamConfig == null) {
                return "";
            }
            try {
                if (timeParamConfig.has("DST")) {
                    this.mTimePObj.remove("DST");
                }
                JSONObject requestRoot = new JSONObject();
                JSONObject deviceManagerConfig = new JSONObject();
                deviceManagerConfig.put("TIMEP", this.mTimePObj);
                requestRoot.put("DEVEMM", deviceManagerConfig);
                return requestRoot.toString();
            } catch (JSONException unused2) {
                return "";
            }
        } else if (setConfigMode != 3 || this.mNtpObj == null) {
            return "";
        } else {
            try {
                JSONObject requestRoot = new JSONObject();
                JSONObject networkConfig = new JSONObject();
                networkConfig.put("NTP", this.mNtpObj);
                requestRoot.put("NWSM", networkConfig);
                return requestRoot.toString();
            } catch (JSONException unused3) {
                return "";
            }
        }
    }

    public void setSuccess() {
        LogUtils.e("DateTimeFragment", "setSuccess 1");
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForDateFormat(int dateFormatIndex) {
        JSONObject timeParamConfig = this.mTimePObj;
        if (timeParamConfig != null) {
            this.mSetConfig = 2;
            try {
                timeParamConfig.put("DATEM", dateFormatIndex);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForTimeFormat(int timeFormatIndex) {
        JSONObject timeParamConfig = this.mTimePObj;
        if (timeParamConfig != null) {
            this.mSetConfig = 2;
            try {
                timeParamConfig.put("TIMEM", timeFormatIndex);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForTimeZone(int timeZoneIndex) {
        if (this.mTimePObj != null && timeZoneIndex < this.mListTimeZoneCase.size()) {
            this.mSetConfig = 2;
            try {
                this.mTimePObj.put("TIMEZ", this.mListTimeZoneCase.get(timeZoneIndex));
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForNtpServer(int ntpServerIndex) {
        if (this.mNtpObj != null && ntpServerIndex < this.mListNtpServer.size()) {
            this.mSetConfig = 3;
            try {
                this.mNtpObj.put("SERVERNAME", this.mListNtpServer.get(ntpServerIndex));
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String fragmentTag, List<Integer> selectedIndexes) {
        if (fragmentTag.equals("SelectFragmentForDateFormat")) {
            if (selectedIndexes.size() > 0) {
                updateDateForDateFormat(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForTimeFormat")) {
            if (selectedIndexes.size() > 0) {
                updateDateForTimeFormat(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForTimeZone")) {
            if (selectedIndexes.size() > 0) {
                updateDateForTimeZone(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForNtpServer") && selectedIndexes.size() > 0) {
            updateDateForNtpServer(selectedIndexes.get(0).intValue());
        }
    }
}
