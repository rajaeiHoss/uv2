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
        int i;
        if (this.mDateTimeRes != null && this.mListTimeZoneLongName.size() == this.mListTimeZoneShortName.size() && this.mListTimeZoneLongName.size() == this.mListTimeZoneCase.size()) {
            try {
                JSONObject jSONObject = this.mDateTimeRes.getJSONObject("DEVEMM");
                JSONObject jSONObject2 = this.mDateTimeRes.getJSONObject("NWSM");
                if (jSONObject == null) {
                    return;
                }
                if (jSONObject2 != null) {
                    this.mTimePObj = jSONObject.getJSONObject("TIMEP");
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("NTP");
                    this.mNtpObj = jSONObject3;
                    JSONObject jSONObject4 = this.mTimePObj;
                    if (jSONObject4 == null) {
                        return;
                    }
                    if (jSONObject3 != null) {
                        int i2 = jSONObject4.getInt("DATEM");
                        this.mListStrDateFormat.clear();
                        this.mListIntDateFormat.clear();
                        List<String> strDatas = getStrDatas(R.array.config_sys_time_DateFormat);
                        if (i2 >= 0 && i2 < strDatas.size()) {
                            this.mTvDateFormat.setText(strDatas.get(i2));
                            this.mListStrDateFormat.addAll(strDatas);
                            this.mListIntDateFormat.add(new Integer(i2));
                        }
                        int i3 = this.mTimePObj.getInt("TIMEM");
                        this.mListStrTimeFormat.clear();
                        this.mListIntTimeFormat.clear();
                        List<String> strDatas2 = getStrDatas(R.array.config_sys_time_TimeFormat);
                        if (i3 >= 0 && i3 < strDatas2.size()) {
                            this.mTvTimeFormat.setText(strDatas2.get(i3));
                            this.mListStrTimeFormat.addAll(strDatas2);
                            this.mListIntTimeFormat.add(new Integer(i3));
                        }
                        String string = this.mTimePObj.getString("TIMEZ");
                        this.mListStrTimeZone.clear();
                        this.mListIntTimeZone.clear();
                        int i4 = 0;
                        int i5 = 0;
                        while (true) {
                            i = -1;
                            if (i5 >= this.mListTimeZoneCase.size()) {
                                i5 = -1;
                                break;
                            } else if (string.equalsIgnoreCase(this.mListTimeZoneCase.get(i5))) {
                                break;
                            } else {
                                i5++;
                            }
                        }
                        if (i5 >= 0) {
                            this.mTvTimeZone.setText(this.mListTimeZoneShortName.get(i5));
                            this.mListStrTimeZone.addAll(this.mListTimeZoneLongName);
                            this.mListIntTimeZone.add(new Integer(i5));
                        }
                        String string2 = this.mTimePObj.getString("ZONEOFFSET");
                        this.mListStrZoneOffset.clear();
                        this.mListIntZoneOffset.clear();
                        int i6 = 0;
                        while (true) {
                            if (i6 >= this.mListZoneOffsetCase.size()) {
                                i6 = -1;
                                break;
                            } else if (string2.equalsIgnoreCase(this.mListZoneOffsetCase.get(i6))) {
                                break;
                            } else {
                                i6++;
                            }
                        }
                        if (i6 >= 0) {
                            this.mTvZoneOffset.setText(this.mListZoneOffsetName.get(i6));
                            this.mListStrZoneOffset.addAll(this.mListZoneOffsetName);
                            this.mListIntZoneOffset.add(new Integer(i6));
                        }
                        int i7 = this.mTimePObj.getInt("GPSSYNC");
                        Button button = this.mBtnGpsStatus;
                        int i8 = R.drawable.switch_close;
                        button.setBackgroundResource(i7 == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                        int i9 = this.mNtpObj.getInt("NTPSWITCH");
                        Button button2 = this.mBtnNtpStatus;
                        if (i9 != 0) {
                            i8 = R.drawable.switch_open;
                        }
                        button2.setBackgroundResource(i8);
                        this.mListStrNtpServer.clear();
                        this.mListIntNtpServer.clear();
                        String string3 = this.mNtpObj.getString("SERVERNAME");
                        while (true) {
                            if (i4 >= this.mListNtpServer.size()) {
                                break;
                            } else if (string3.equalsIgnoreCase(this.mListNtpServer.get(i4))) {
                                i = i4;
                                break;
                            } else {
                                i4++;
                            }
                        }
                        if (i >= 0) {
                            this.mTvNtpServerName.setText(this.mListNtpServer.get(i));
                            this.mListStrNtpServer.addAll(this.mListNtpServer);
                            this.mListIntNtpServer.add(new Integer(i));
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

    public void getSuccess(String str) {
        try {
            this.mDateTimeRes = new JSONObject(str);
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

    public void updateDateForDateFormat(int i) {
        JSONObject jSONObject = this.mTimePObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("DATEM", i);
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForTimeFormat(int i) {
        JSONObject jSONObject = this.mTimePObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("TIMEM", i);
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForTimeZone(int i) {
        if (this.mTimePObj != null && i < this.mListTimeZoneCase.size()) {
            try {
                this.mTimePObj.put("TIMEZ", this.mListTimeZoneCase.get(i));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForZoneOffset(int i) {
        if (this.mTimePObj != null && i < this.mListZoneOffsetCase.size()) {
            try {
                this.mTimePObj.put("ZONEOFFSET", this.mListZoneOffsetCase.get(i));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForNtpServer(int i) {
        if (this.mNtpObj != null && i < this.mListNtpServer.size()) {
            try {
                this.mNtpObj.put("SERVERNAME", this.mListNtpServer.get(i));
                saveUi();
                refreshUi();
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForDateFormat")) {
            if (list.size() > 0) {
                updateDateForDateFormat(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForTimeFormat")) {
            if (list.size() > 0) {
                updateDateForTimeFormat(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForTimeZone")) {
            if (list.size() > 0) {
                updateDateForTimeZone(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForZoneOffset")) {
            if (list.size() > 0) {
                updateDateForZoneOffset(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForNtpServer") && list.size() > 0) {
            updateDateForNtpServer(list.get(0).intValue());
        }
    }
}
