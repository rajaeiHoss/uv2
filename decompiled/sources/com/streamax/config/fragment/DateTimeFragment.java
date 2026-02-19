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
        int i;
        if (this.mDateTimeRes != null && this.mListTimeZoneLongName.size() == this.mListTimeZoneShortName.size() && this.mListTimeZoneLongName.size() == this.mListTimeZoneCase.size()) {
            try {
                JSONObject jSONObject = this.mDateTimeRes.getJSONObject("DEVEMM");
                JSONObject jSONObject2 = this.mDateTimeRes.getJSONObject("NWSM");
                JSONObject jSONObject3 = this.mDateTimeRes.getJSONObject("STATUS");
                if (jSONObject != null && jSONObject2 != null) {
                    if (jSONObject3 != null) {
                        this.mTimePObj = jSONObject.getJSONObject("TIMEP");
                        JSONObject jSONObject4 = jSONObject2.getJSONObject("NTP");
                        this.mNtpObj = jSONObject4;
                        if (this.mTimePObj == null) {
                            return;
                        }
                        if (jSONObject4 != null) {
                            String string = jSONObject3.getString("TIME");
                            int i2 = 0;
                            if (string.length() == 14) {
                                this.mTvDateTime.setText(String.format("%04d-%02d-%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(StringUtils.parse2Int(string.substring(0, 4))), Integer.valueOf(StringUtils.parse2Int(string.substring(4, 6))), Integer.valueOf(StringUtils.parse2Int(string.substring(6, 8))), Integer.valueOf(StringUtils.parse2Int(string.substring(8, 10))), Integer.valueOf(StringUtils.parse2Int(string.substring(10, 12))), Integer.valueOf(StringUtils.parse2Int(string.substring(12, 14)))}));
                            }
                            int i3 = this.mTimePObj.getInt("DATEM");
                            this.mListStrDateFormat.clear();
                            this.mListIntDateFormat.clear();
                            List<String> strDatas = getStrDatas(R.array.DateFormat);
                            if (i3 >= 0 && i3 < strDatas.size()) {
                                this.mTvDateFormat.setText(strDatas.get(i3));
                                this.mListStrDateFormat.addAll(strDatas);
                                this.mListIntDateFormat.add(new Integer(i3));
                            }
                            int i4 = this.mTimePObj.getInt("TIMEM");
                            this.mListStrTimeFormat.clear();
                            this.mListIntTimeFormat.clear();
                            List<String> strDatas2 = getStrDatas(R.array.TimeFormat);
                            if (i4 >= 0 && i4 < strDatas2.size()) {
                                this.mTvTimeFormat.setText(strDatas2.get(i4));
                                this.mListStrTimeFormat.addAll(strDatas2);
                                this.mListIntTimeFormat.add(new Integer(i4));
                            }
                            String string2 = this.mTimePObj.getString("TIMEZ");
                            this.mListStrTimeZone.clear();
                            this.mListIntTimeZone.clear();
                            int i5 = 0;
                            while (true) {
                                i = -1;
                                if (i5 >= this.mListTimeZoneCase.size()) {
                                    i5 = -1;
                                    break;
                                } else if (string2.equalsIgnoreCase(this.mListTimeZoneCase.get(i5))) {
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
                            this.mBtnNtpStatus.setBackgroundResource(this.mNtpObj.getInt("NTPSWITCH") == 0 ? R.drawable.switch_close : R.drawable.switch_open);
                            this.mListStrNtpServer.clear();
                            this.mListIntNtpServer.clear();
                            String string3 = this.mNtpObj.getString("SERVERNAME");
                            while (true) {
                                if (i2 >= this.mListNtpServer.size()) {
                                    break;
                                } else if (string3.equalsIgnoreCase(this.mListNtpServer.get(i2))) {
                                    i = i2;
                                    break;
                                } else {
                                    i2++;
                                }
                            }
                            if (i >= 0) {
                                this.mTvNtpServerName.setText(this.mListNtpServer.get(i));
                                this.mListStrNtpServer.addAll(this.mListNtpServer);
                                this.mListIntNtpServer.add(new Integer(i));
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
        JSONObject jSONObject = this.mNtpObj;
        if (jSONObject != null) {
            this.mSetConfig = 3;
            try {
                this.mNtpObj.put("NTPSWITCH", jSONObject.getInt("NTPSWITCH") == 0 ? 1 : 0);
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
            JSONObject jSONObject = this.mDateTimeRes.getJSONObject("STATUS");
            if (jSONObject != null) {
                jSONObject.put("TIME", time.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
                NetPresenter.getDefault().setConfig(this);
            }
        } catch (JSONException unused) {
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("TIMEP", "?");
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("NTP", "?");
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("TIME", "?");
            jSONObject.put("DEVEMM", jSONObject2);
            jSONObject.put("NWSM", jSONObject3);
            jSONObject.put("STATUS", jSONObject4);
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

    public String requestForSetConfig() {
        int i = this.mSetConfig;
        if (i == 1) {
            String replaceAll = getString(this.mTvDateTime).replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
            try {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("TIME", replaceAll);
                jSONObject.put("STATUS", jSONObject2);
                return jSONObject.toString();
            } catch (JSONException unused) {
                return "";
            }
        } else if (i == 2) {
            JSONObject jSONObject3 = this.mTimePObj;
            if (jSONObject3 == null) {
                return "";
            }
            try {
                if (jSONObject3.has("DST")) {
                    this.mTimePObj.remove("DST");
                }
                JSONObject jSONObject4 = new JSONObject();
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("TIMEP", this.mTimePObj);
                jSONObject4.put("DEVEMM", jSONObject5);
                return jSONObject4.toString();
            } catch (JSONException unused2) {
                return "";
            }
        } else if (i != 3 || this.mNtpObj == null) {
            return "";
        } else {
            try {
                JSONObject jSONObject6 = new JSONObject();
                JSONObject jSONObject7 = new JSONObject();
                jSONObject7.put("NTP", this.mNtpObj);
                jSONObject6.put("NWSM", jSONObject7);
                return jSONObject6.toString();
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

    public void updateDateForDateFormat(int i) {
        JSONObject jSONObject = this.mTimePObj;
        if (jSONObject != null) {
            this.mSetConfig = 2;
            try {
                jSONObject.put("DATEM", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForTimeFormat(int i) {
        JSONObject jSONObject = this.mTimePObj;
        if (jSONObject != null) {
            this.mSetConfig = 2;
            try {
                jSONObject.put("TIMEM", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForTimeZone(int i) {
        if (this.mTimePObj != null && i < this.mListTimeZoneCase.size()) {
            this.mSetConfig = 2;
            try {
                this.mTimePObj.put("TIMEZ", this.mListTimeZoneCase.get(i));
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForNtpServer(int i) {
        if (this.mNtpObj != null && i < this.mListNtpServer.size()) {
            this.mSetConfig = 3;
            try {
                this.mNtpObj.put("SERVERNAME", this.mListNtpServer.get(i));
                NetPresenter.getDefault().setConfig(this);
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
        } else if (str.equals("SelectFragmentForNtpServer") && list.size() > 0) {
            updateDateForNtpServer(list.get(0).intValue());
        }
    }
}
