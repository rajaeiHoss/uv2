package com.streamax.client;

import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.streamax.Configs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class WebService {
    public static final int LOGIN_ALIAS = 2;
    public static final int LOGIN_FAILED = -1;
    public static final int LOGIN_LOCAL = 0;
    public static final int LOGIN_SERIALNUM = 3;
    public static final int LOGIN_USER = 1;
    public List<DevInfoBean> mDevList;
    private int mLoginType = 0;
    private String mPassword;
    private String mRegId;
    private String mServerIP;
    private String mUsername;

    public WebService(String serverHost, String username, String password) {
        this.mUsername = username;
        this.mPassword = password;
        if (TextUtils.isEmpty(serverHost)) {
            return;
        }
        if (serverHost.contains("http://")) {
            this.mServerIP = serverHost;
            return;
        }
        this.mServerIP = "http://" + serverHost;
    }

    public void SetRegisterId(String registerId) {
        this.mRegId = registerId;
    }

    public int GetLoginType() {
        return this.mLoginType;
    }

    public synchronized List<DevInfoBean> GetTerminalInfoAndroid(boolean forceRefresh) {
        if (forceRefresh) {
            List<DevInfoBean> list = this.mDevList;
            if (list != null) {
                list.clear();
                this.mDevList = null;
            }
        }
        List<DevInfoBean> list2 = this.mDevList;
        if (list2 == null) {
            this.mDevList = new ArrayList();
        } else if (list2.size() > 0) {
            return this.mDevList;
        }
        Log.e("serverIP", "" + this.mServerIP);
        Log.e("username", "" + this.mUsername);
        Log.e("userpassword", "" + this.mPassword);
        SoapObject soapObject = new SoapObject("cq-video.cn", "GetTerminalInfoAndroid");
        soapObject.addProperty("username", this.mUsername);
        soapObject.addProperty("userpassword", this.mPassword);
        soapObject.addProperty("androidid", this.mRegId);
        soapObject.addProperty(Configs.Key.LoginType, Integer.toString(this.mLoginType));
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(this.mServerIP + "/ddns_service.asmx");
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/GetTerminalInfoAndroid", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                String[] rows = soapSerializationEnvelope.getResponse().toString().split(";");
                sortRowsByLengthDescending(rows);
                String[] headers = rows[0].split(",");
                for (int rowIndex = 1; rowIndex < rows.length; rowIndex++) {
                    String[] fields = rows[rowIndex].split(",");
                    DevInfoBean devInfoBean = new DevInfoBean();
                    int fieldCount = Math.min(fields.length, headers.length);
                    for (int fieldIndex = 0; fieldIndex < fieldCount; fieldIndex++) {
                        applyTerminalField(devInfoBean, headers[fieldIndex], fields[fieldIndex]);
                    }
                    this.mDevList.add(devInfoBean);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
        return this.mDevList;
    }

    private void sortRowsByLengthDescending(String[] rows) {
        for (int startIndex = 0; startIndex < rows.length; startIndex++) {
            for (int scanIndex = startIndex; scanIndex < rows.length; scanIndex++) {
                if (rows[scanIndex].length() > rows[startIndex].length()) {
                    String row = rows[startIndex];
                    rows[startIndex] = rows[scanIndex];
                    rows[scanIndex] = row;
                }
            }
        }
    }

    private void applyTerminalField(DevInfoBean devInfoBean, String header, String value) {
        if (header.compareTo("TerminalID") == 0) {
            devInfoBean.mDevId = Integer.parseInt(value);
        } else if (header.compareTo("SIMCardID") == 0) {
            devInfoBean.mDevName = value;
        } else if (header.compareTo("WANIp") == 0) {
            devInfoBean.mDevIp = value;
        } else if (header.compareTo("TerminalPort") == 0) {
            if (!"".equals(value)) {
                devInfoBean.mMediaPort = Integer.parseInt(value);
            }
        } else if (header.compareTo("TransmitPort") == 0) {
            devInfoBean.mWebPort = Integer.parseInt(value);
        } else if (header.compareTo("User") == 0) {
            devInfoBean.mUsername = value;
        } else if (header.compareTo("Key") == 0) {
            if (this.mLoginType == 3) {
                devInfoBean.mPwd = this.mPassword;
            } else {
                devInfoBean.mPwd = value;
            }
        } else if (header.compareTo("ChannelCount") == 0) {
            devInfoBean.mChCounts = Integer.parseInt(value);
        } else if (header.compareTo("Remark") == 0) {
            devInfoBean.mRemark = value;
        } else if (header.compareTo("Push") == 0 && !"".equals(value)) {
            devInfoBean.mPush = Integer.valueOf(value).intValue();
        }
    }

    public DevInfoBean query(int deviceId) {
        DevInfoBean devInfoBean = null;
        for (int deviceIndex = 0; deviceIndex < this.mDevList.size(); deviceIndex++) {
            if (this.mDevList.get(deviceIndex).mDevId == deviceId) {
                devInfoBean = this.mDevList.get(deviceIndex);
            }
        }
        return devInfoBean;
    }

    public DevInfoBean query(String deviceName) {
        DevInfoBean devInfoBean = null;
        for (int deviceIndex = 0; deviceIndex < this.mDevList.size(); deviceIndex++) {
            if (this.mDevList.get(deviceIndex).mDevName.compareTo(deviceName) == 0) {
                devInfoBean = this.mDevList.get(deviceIndex);
            }
        }
        return devInfoBean;
    }

    public int Login() {
        SoapObject soapObject = new SoapObject("cq-video.cn", "Login");
        soapObject.addProperty("_userName", this.mUsername);
        soapObject.addProperty("_newPassword", this.mPassword);
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(this.mServerIP + "/ddns_service.asmx");
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/Login", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                this.mLoginType = Integer.valueOf(soapSerializationEnvelope.getResponse().toString()).intValue();
                return Integer.valueOf(soapSerializationEnvelope.getResponse().toString()).intValue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
        this.mLoginType = 0;
        return 0;
    }

    public List<AlarmInfo> getAlarmBySerialNum(String serialNum, int count) {
        ArrayList alarmList = new ArrayList();
        SoapObject soapObject = new SoapObject("cq-video.cn", "Dvr_AlarmBySerialNum");
        soapObject.addProperty("serialnum", serialNum);
        soapObject.addProperty("count", Integer.valueOf(count));
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(this.mServerIP + "/ddns_service.asmx");
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/Dvr_AlarmBySerialNum", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                String[] rows = soapSerializationEnvelope.getResponse().toString().split(";");
                if (rows.length == 1) {
                    return alarmList;
                }
                String[] headers = rows[0].split(",");
                for (int rowIndex = 1; rowIndex < rows.length; rowIndex++) {
                    String[] fields = rows[rowIndex].split(",");
                    AlarmInfo alarmInfo = new AlarmInfo();
                    int fieldCount = Math.min(fields.length, headers.length);
                    for (int fieldIndex = 0; fieldIndex < fieldCount; fieldIndex++) {
                        applyAlarmField(alarmInfo, headers[fieldIndex], fields[fieldIndex]);
                    }
                    alarmList.add(alarmInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
        return alarmList;
    }

    private void applyAlarmField(AlarmInfo alarmInfo, String header, String value) {
        if (header.compareTo("AlarmTime") == 0) {
            alarmInfo.alarmTime = value;
        } else if (header.compareTo("AlarmType") == 0) {
            alarmInfo.alarmType = value;
        } else if (header.compareTo("AlarmSubType") == 0) {
            alarmInfo.alarmSubType = value;
        } else if (header.compareTo("AlarmChannel") == 0) {
            alarmInfo.alarmChannel = value;
        } else if (header.compareTo("AlarmContent") == 0) {
            alarmInfo.alarmContent = value;
        }
    }

    public boolean Login_Status_Android(String androidId, String unusedPassword, String username) {
        SoapObject soapObject = new SoapObject("cq-video.cn", "Login_Status_Android");
        soapObject.addProperty("androidid", androidId);
        soapObject.addProperty("username", username);
        soapObject.addProperty(Configs.Key.LoginType, Integer.toString(this.mLoginType));
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(this.mServerIP + "/ddns_service.asmx");
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/Login_Status_Android", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                return Boolean.parseBoolean(soapSerializationEnvelope.getResponse().toString());
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public boolean Android_AddForMultiApp(String androidId, String serialNum, String deviceName, String username, String unusedPassword, String deviceId, String appIdentification) {
        String serviceUrl = this.mServerIP + "/ddns_service.asmx";
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_AddForMultiApp");
        soapObject.addProperty("androidid", androidId);
        soapObject.addProperty("serialnum", serialNum);
        soapObject.addProperty("devicename", deviceName);
        soapObject.addProperty("username", username);
        soapObject.addProperty("appIdentification", appIdentification);
        soapObject.addProperty(Configs.Key.LoginType, Integer.toString(this.mLoginType));
        if (this.mLoginType == 0) {
            soapObject.addProperty("deviceid", "0");
        } else {
            soapObject.addProperty("deviceid", deviceId);
        }
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(serviceUrl);
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/Android_AddForMultiApp", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                return Boolean.parseBoolean(soapSerializationEnvelope.getResponse().toString());
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public boolean Android_Add(String androidId, String serialNum, String deviceName, String username, String unusedPassword, String deviceId) {
        String serviceUrl = this.mServerIP + "/ddns_service.asmx";
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_Add");
        soapObject.addProperty("androidid", androidId);
        soapObject.addProperty("serialnum", serialNum);
        soapObject.addProperty("devicename", deviceName);
        soapObject.addProperty("username", username);
        soapObject.addProperty(Configs.Key.LoginType, Integer.toString(this.mLoginType));
        if (this.mLoginType == 0) {
            soapObject.addProperty("deviceid", "0");
        } else {
            soapObject.addProperty("deviceid", deviceId);
        }
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(serviceUrl);
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/Android_Add", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                return Boolean.parseBoolean(soapSerializationEnvelope.getResponse().toString());
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Android_Delete(String androidId, String deviceName, String username, String unusedPassword, String deviceId) {
        String serviceUrl = this.mServerIP + "/ddns_service.asmx";
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_Delete");
        soapObject.addProperty("androidid", androidId);
        soapObject.addProperty("devicename", deviceName);
        soapObject.addProperty("username", username);
        soapObject.addProperty(Configs.Key.LoginType, Integer.toString(this.mLoginType));
        if (this.mLoginType == 0) {
            soapObject.addProperty("deviceid", "0");
        } else {
            soapObject.addProperty("deviceid", deviceId);
        }
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(serviceUrl);
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/Android_Delete", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                return Boolean.parseBoolean(soapSerializationEnvelope.getResponse().toString());
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean Android_DeleteAllPush(String androidId, String username) {
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_DeleteAll_User");
        soapObject.addProperty("androidid", androidId);
        soapObject.addProperty("username", username);
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(this.mServerIP + "/ddns_service.asmx");
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/Android_DeleteAll_User", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                return Boolean.parseBoolean(soapSerializationEnvelope.getResponse().toString());
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean Android_DeleteAll(String androidId) {
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_DeleteAll");
        soapObject.addProperty("androidid", androidId);
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(this.mServerIP + "/ddns_service.asmx");
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/Android_DeleteAll", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                return Boolean.parseBoolean(soapSerializationEnvelope.getResponse().toString());
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean Android_Unregister(String androidId) {
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_Unregister");
        soapObject.addProperty("androidid", androidId);
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(this.mServerIP + "/ddns_service.asmx");
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/Android_Unregister", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                return Boolean.parseBoolean(soapSerializationEnvelope.getResponse().toString());
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public Map<String, String> GetServer() {
        String serverUrl = this.mServerIP + "/serversfordevice/NatServer.ashx?did=";
        HashMap hashMap = new HashMap();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(serverUrl).openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String responseBody = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                responseBody = responseBody + readLine;
            }
            inputStream.close();
            bufferedReader.close();
            httpURLConnection.disconnect();
            if (responseBody.length() == 0) {
                return hashMap;
            }
            try {
                JSONObject jSONObject = (JSONObject) new JSONTokener(responseBody).nextValue();
                JSONObject jSONObject2 = jSONObject.getJSONObject("nat");
                String string = jSONObject2.getString("ip");
                String string2 = jSONObject2.getString("port");
                hashMap.put("nat_ip", string);
                hashMap.put("nat_port", string2);
                JSONObject jSONObject3 = jSONObject.getJSONObject("event");
                String string3 = jSONObject3.getString("ip");
                String string4 = jSONObject3.getString("port");
                hashMap.put("event_ip", string3);
                hashMap.put("event_port", string4);
                JSONObject jSONObject4 = jSONObject.getJSONObject("gt");
                String string5 = jSONObject4.getString("ip");
                String string6 = jSONObject4.getString("port");
                hashMap.put("gt_ip", string5);
                hashMap.put("gt_port", string6);
                JSONObject jSONObject5 = jSONObject.getJSONObject(NotificationCompat.CATEGORY_MESSAGE);
                String string7 = jSONObject5.getString("ip");
                String string8 = jSONObject5.getString("port");
                hashMap.put("msg_ip", string7);
                hashMap.put("msg_port", string8);
                return hashMap;
            } catch (JSONException e) {
                e.printStackTrace();
                return hashMap;
            }
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            return hashMap;
        } catch (IOException e3) {
            e3.printStackTrace();
            return hashMap;
        }
    }

    public int CheckTerminalPw(String serial, String username, String userPassword) {
        SoapObject soapObject = new SoapObject("cq-video.cn", "CheckTerminalPw");
        soapObject.addProperty("serial", serial);
        soapObject.addProperty("username", username);
        soapObject.addProperty("userpassword", userPassword);
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(this.mServerIP + "/ddns_service.asmx");
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/CheckTerminalPw", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                return Integer.valueOf(soapSerializationEnvelope.getResponse().toString()).intValue();
            }
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
