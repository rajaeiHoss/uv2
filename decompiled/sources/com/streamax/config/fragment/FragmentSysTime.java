package com.streamax.config.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.LogUtils;
import com.streamax.utils.UiUtils;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentSysTime extends ConfigFragment implements BaseListener.SetListener, BaseListener.GetListener {
    public Button mBtnGpsStatus;
    public Button mBtnNtpStatus;
    public JSONObject mDateTimeRes;
    public List<Integer> mListIntDateFormat = new ArrayList();
    public List<Integer> mListIntNtpServer = new ArrayList();
    public List<Integer> mListIntTimeFormat = new ArrayList();
    public List<Integer> mListIntTimeZone = new ArrayList();
    public List<Integer> mListIntZoneOffset = new ArrayList();
    public ArrayList<String> mListNtpServer = new ArrayList<>();
    public ArrayList<String> mListStrDateFormat = new ArrayList<>();
    public ArrayList<String> mListStrNtpServer = new ArrayList<>();
    public ArrayList<String> mListStrTimeFormat = new ArrayList<>();
    public ArrayList<String> mListStrTimeZone = new ArrayList<>();
    public ArrayList<String> mListStrZoneOffset = new ArrayList<>();
    public ArrayList<String> mListTimeZoneCase = new ArrayList<>();
    public ArrayList<String> mListTimeZoneLongName = new ArrayList<>();
    public ArrayList<String> mListTimeZoneShortName = new ArrayList<>();
    public ArrayList<String> mListZoneOffsetCase = new ArrayList<>();
    public ArrayList<String> mListZoneOffsetName = new ArrayList<>();
    public JSONObject mNtpObj;
    public RelativeLayout mRlDataFormat;
    public RelativeLayout mRlNtpServer;
    public RelativeLayout mRlTimeFormat;
    public RelativeLayout mRlTimeZone;
    public RelativeLayout mRlZoneOffset;
    public JSONObject mTimePObj;
    public TextView mTvDateFormat;
    public TextView mTvNtpServerName;
    public TextView mTvTimeFormat;
    public TextView mTvTimeZone;
    public TextView mTvZoneOffset;

    public BaseFragment getCurFragment() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mListTimeZoneLongName.addAll(getStrDatas(R.array.config_sys_time_TimeZoneLongName));
        this.mListTimeZoneShortName.addAll(getStrDatas(R.array.config_sys_time_TimeZoneShortName));
        this.mListTimeZoneCase.addAll(getStrDatas(R.array.config_sys_time_TimeZoneCase));
        this.mListZoneOffsetName.addAll(getStrDatas(R.array.config_sys_time_ZoneOffsetName));
        this.mListZoneOffsetCase.addAll(getStrDatas(R.array.config_sys_time_ZoneOffsetCase));
        this.mListNtpServer.addAll(getStrDatas(R.array.config_sys_time_NtpServerName));
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_sys_time_Title);
        this.mBtnBack.setVisibility(0);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_sys_time, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    public void initChildView() {
        this.mRlDataFormat = (RelativeLayout) this.mRootView.findViewById(R.id.config_sys_time_rl_dateFormat);
        this.mTvDateFormat = (TextView) this.mRootView.findViewById(R.id.config_sys_time_tv_dateformat);
        this.mRlTimeFormat = (RelativeLayout) this.mRootView.findViewById(R.id.config_sys_time_rl_timeFormat);
        this.mTvTimeFormat = (TextView) this.mRootView.findViewById(R.id.config_sys_time_tv_timeFormat);
        this.mRlTimeZone = (RelativeLayout) this.mRootView.findViewById(R.id.config_sys_time_rl_timeZone);
        this.mTvTimeZone = (TextView) this.mRootView.findViewById(R.id.config_sys_time_tv_timeZone);
        this.mRlZoneOffset = (RelativeLayout) this.mRootView.findViewById(R.id.config_sys_time_rl_zoneOffset);
        this.mTvZoneOffset = (TextView) this.mRootView.findViewById(R.id.config_sys_time_tv_zoneOffset);
        this.mBtnNtpStatus = (Button) this.mRootView.findViewById(R.id.config_sys_time_btn_ntpStatus);
        this.mBtnGpsStatus = (Button) this.mRootView.findViewById(R.id.config_sys_time_btn_gpsStatus);
        this.mRlNtpServer = (RelativeLayout) this.mRootView.findViewById(R.id.config_sys_time_rl_ntpServer);
        this.mTvNtpServerName = (TextView) this.mRootView.findViewById(R.id.config_sys_time_tv_ntp_server_name);
        this.mBtnSave = (Button) this.mRootView.findViewById(R.id.config_sys_time_btn_save);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getSuccess("{\n\t\"DEVEMM\": \n\t{\n\t\t\"TIMEP\": \n\t\t{\n\t\t\t\"DATEM\": 1,\n\t\t\t\"TIMEM\": 1,\n\t\t\t\"TIMEZ\": 480,\n\t\t\t\"ZONEOFFSET\": 0,\n\t\t\t\"GPSSYNC\": 0\n\t\t}\n\t},\n\t\"NWSM\": \n\t{\n\t\t\"NTP\": \n\t\t{\n\t\t\t\"NTPSWITCH\": \"1\",\n\t\t\t\"SERVERNAME\": \"time.windows.com\"\n\t\t}\n\t}\n}");
        getConfig();
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void refreshUi() {
        if (this.mDateTimeRes != null && this.mListTimeZoneLongName.size() == this.mListTimeZoneShortName.size() && this.mListTimeZoneLongName.size() == this.mListTimeZoneCase.size()) {
            try {
                JSONObject deviceManagementObj = this.mDateTimeRes.getJSONObject("DEVEMM");
                JSONObject networkSettingsObj = this.mDateTimeRes.getJSONObject("NWSM");
                if (deviceManagementObj == null) {
                    return;
                }
                if (networkSettingsObj != null) {
                    this.mTimePObj = deviceManagementObj.getJSONObject("TIMEP");
                    JSONObject ntpObj = networkSettingsObj.getJSONObject("NTP");
                    this.mNtpObj = ntpObj;
                    JSONObject timePolicyObj = this.mTimePObj;
                    if (timePolicyObj == null) {
                        return;
                    }
                    if (ntpObj != null) {
                        int dateFormatIndex = timePolicyObj.getInt("DATEM");
                        this.mListStrDateFormat.clear();
                        this.mListIntDateFormat.clear();
                        List<String> dateFormats = getStrDatas(R.array.config_sys_time_DateFormat);
                        if (dateFormatIndex >= 0 && dateFormatIndex < dateFormats.size()) {
                            this.mTvDateFormat.setText(dateFormats.get(dateFormatIndex));
                            this.mListStrDateFormat.addAll(dateFormats);
                            this.mListIntDateFormat.add(new Integer(dateFormatIndex));
                        }
                        int timeFormatIndex = this.mTimePObj.getInt("TIMEM");
                        this.mListStrTimeFormat.clear();
                        this.mListIntTimeFormat.clear();
                        List<String> timeFormats = getStrDatas(R.array.config_sys_time_TimeFormat);
                        if (timeFormatIndex >= 0 && timeFormatIndex < timeFormats.size()) {
                            this.mTvTimeFormat.setText(timeFormats.get(timeFormatIndex));
                            this.mListStrTimeFormat.addAll(timeFormats);
                            this.mListIntTimeFormat.add(new Integer(timeFormatIndex));
                        }
                        String timeZoneValue = this.mTimePObj.getString("TIMEZ");
                        this.mListStrTimeZone.clear();
                        this.mListIntTimeZone.clear();
                        int timeZoneIndex = 0;
                        while (true) {
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
                        String zoneOffsetValue = this.mTimePObj.getString("ZONEOFFSET");
                        this.mListStrZoneOffset.clear();
                        this.mListIntZoneOffset.clear();
                        int zoneOffsetIndex = 0;
                        while (true) {
                            if (zoneOffsetIndex >= this.mListZoneOffsetCase.size()) {
                                zoneOffsetIndex = -1;
                                break;
                            } else if (zoneOffsetValue.equalsIgnoreCase(this.mListZoneOffsetCase.get(zoneOffsetIndex))) {
                                break;
                            } else {
                                zoneOffsetIndex++;
                            }
                        }
                        if (zoneOffsetIndex >= 0) {
                            this.mTvZoneOffset.setText(this.mListZoneOffsetName.get(zoneOffsetIndex));
                            this.mListStrZoneOffset.addAll(this.mListZoneOffsetName);
                            this.mListIntZoneOffset.add(new Integer(zoneOffsetIndex));
                        }
                        int gpsSync = this.mTimePObj.getInt("GPSSYNC");
                        Button button = this.mBtnGpsStatus;
                        int switchBackground = R.drawable.switch_close;
                        button.setBackgroundResource(gpsSync == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                        int ntpSwitch = this.mNtpObj.getInt("NTPSWITCH");
                        Button button2 = this.mBtnNtpStatus;
                        if (ntpSwitch != 0) {
                            switchBackground = R.drawable.switch_open;
                        }
                        button2.setBackgroundResource(switchBackground);
                        this.mListStrNtpServer.clear();
                        this.mListIntNtpServer.clear();
                        String ntpServerName = this.mNtpObj.getString("SERVERNAME");
                        int ntpServerIndex = -1;
                        for (int serverIndex = 0; serverIndex < this.mListNtpServer.size(); serverIndex++) {
                            if (ntpServerName.equalsIgnoreCase(this.mListNtpServer.get(serverIndex))) {
                                ntpServerIndex = serverIndex;
                                break;
                            }
                        }
                        if (ntpServerIndex >= 0) {
                            this.mTvNtpServerName.setText(this.mListNtpServer.get(ntpServerIndex));
                            this.mListStrNtpServer.addAll(this.mListNtpServer);
                            this.mListIntNtpServer.add(new Integer(ntpServerIndex));
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mRlDataFormat.setOnClickListener(this);
        this.mRlTimeFormat.setOnClickListener(this);
        this.mRlTimeZone.setOnClickListener(this);
        this.mRlZoneOffset.setOnClickListener(this);
        this.mBtnNtpStatus.setOnClickListener(this);
        this.mBtnGpsStatus.setOnClickListener(this);
        this.mRlNtpServer.setOnClickListener(this);
        this.mBtnSave.setOnClickListener(this);
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

    public void pushFragmentForZoneOffset() {
        if (this.mTimePObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_dateTime_TimeZone), "SelectFragmentForZoneOffset", 0, this.mListStrZoneOffset, this.mListIntZoneOffset);
        }
    }

    public boolean saveUi() {
        return this.mTimePObj != null;
    }

    public void saveGpsStatus() {
        JSONObject jSONObject = this.mTimePObj;
        if (jSONObject != null) {
            try {
                this.mTimePObj.put("GPSSYNC", jSONObject.getInt("GPSSYNC") == 0 ? 1 : 0);
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void saveNtpStatus() {
        JSONObject jSONObject = this.mNtpObj;
        if (jSONObject != null) {
            try {
                this.mNtpObj.put("NTPSWITCH", jSONObject.getInt("NTPSWITCH") == 0 ? 1 : 0);
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForNtpServer() {
        if (this.mNtpObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_dateTime_TimeServer), "SelectFragmentForNtpServer", 0, this.mListStrNtpServer, this.mListIntNtpServer);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.config_title_btn_back) {
            switch (id) {
                case R.id.config_sys_time_btn_gpsStatus /*2131362190*/:
                    saveGpsStatus();
                    return;
                case R.id.config_sys_time_btn_ntpStatus /*2131362191*/:
                    saveNtpStatus();
                    return;
                case R.id.config_sys_time_btn_save /*2131362192*/:
                    saveData();
                    return;
                case R.id.config_sys_time_rl_dateFormat /*2131362193*/:
                    pushFragmentForDateFormat();
                    return;
                case R.id.config_sys_time_rl_ntpServer /*2131362194*/:
                    pushFragmentForNtpServer();
                    return;
                case R.id.config_sys_time_rl_timeFormat /*2131362195*/:
                    pushFragmentForTimeFormat();
                    return;
                case R.id.config_sys_time_rl_timeZone /*2131362196*/:
                    pushFragmentForTimeZone();
                    return;
                case R.id.config_sys_time_rl_zoneOffset /*2131362197*/:
                    pushFragmentForZoneOffset();
                    return;
                default:
                    return;
            }
        } else {
            prePage();
        }
    }

    public void prePage() {
        this.mBtnBack.setVisibility(8);
        super.prePage();
    }

    public void saveData() {
        if (saveUi()) {
            NetPresenter.getDefault().setConfig(this);
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("TIMEP", "?");
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("NTP", "?");
            jSONObject.put("DEVEMM", jSONObject2);
            jSONObject.put("NWSM", jSONObject3);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String responseJson) {
        try {
            this.mDateTimeRes = new JSONObject(responseJson);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public void getFailure() {
        toastSf((int) R.string.config_info_get_failure);
    }

    public String requestForSetConfig() {
        if (!(this.mTimePObj == null || this.mNtpObj == null)) {
            try {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("TIMEP", this.mTimePObj);
                jSONObject.put("DEVEMM", jSONObject2);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("NTP", this.mNtpObj);
                jSONObject.put("NWSM", jSONObject3);
                return jSONObject.toString();
            } catch (JSONException unused) {
            }
        }
        return "";
    }

    public void setSuccess() {
        LogUtils.e("DateTimeFragment", "setSuccess 1");
        refreshUi();
        toastSf((int) R.string.config_info_set_success);
    }

    public void setFailure() {
        toastSf((int) R.string.config_info_set_failure);
    }

    public void updateDateForDateFormat(int selectedIndex) {
        JSONObject jSONObject = this.mTimePObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("DATEM", selectedIndex);
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForTimeFormat(int selectedIndex) {
        JSONObject jSONObject = this.mTimePObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("TIMEM", selectedIndex);
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForTimeZone(int selectedIndex) {
        if (this.mTimePObj != null && selectedIndex < this.mListTimeZoneCase.size()) {
            try {
                this.mTimePObj.put("TIMEZ", this.mListTimeZoneCase.get(selectedIndex));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForZoneOffset(int selectedIndex) {
        if (this.mTimePObj != null && selectedIndex < this.mListZoneOffsetCase.size()) {
            try {
                this.mTimePObj.put("ZONEOFFSET", this.mListZoneOffsetCase.get(selectedIndex));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForNtpServer(int selectedIndex) {
        if (this.mNtpObj != null && selectedIndex < this.mListNtpServer.size()) {
            try {
                this.mNtpObj.put("SERVERNAME", this.mListNtpServer.get(selectedIndex));
                saveUi();
                refreshUi();
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
        } else if (fragmentTag.equals("SelectFragmentForZoneOffset")) {
            if (selectedIndexes.size() > 0) {
                updateDateForZoneOffset(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForNtpServer") && selectedIndexes.size() > 0) {
            updateDateForNtpServer(selectedIndexes.get(0).intValue());
        }
    }
}
