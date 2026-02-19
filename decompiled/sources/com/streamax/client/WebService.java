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

    public WebService(String str, String str2, String str3) {
        this.mUsername = str2;
        this.mPassword = str3;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.contains("http://")) {
            this.mServerIP = str;
            return;
        }
        this.mServerIP = "http://" + str;
    }

    public void SetRegisterId(String str) {
        this.mRegId = str;
    }

    public int GetLoginType() {
        return this.mLoginType;
    }

    public synchronized List<DevInfoBean> GetTerminalInfoAndroid(boolean z) {
        if (z) {
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
                String[] split = soapSerializationEnvelope.getResponse().toString().split(";");
                for (int i = 0; i < split.length; i++) {
                    for (int i2 = i; i2 < split.length; i2++) {
                        if (split[i2].length() > split[i].length()) {
                            String str = split[i];
                            split[i] = split[i2];
                            split[i2] = str;
                        }
                    }
                }
                String[] split2 = split[0].split(",");
                for (int i3 = 1; i3 < split.length; i3++) {
                    String[] split3 = split[i3].split(",");
                    DevInfoBean devInfoBean = new DevInfoBean();
                    for (int i4 = 0; i4 < split3.length; i4++) {
                        if (split2[i4].compareTo("TerminalID") == 0) {
                            devInfoBean.mDevId = Integer.parseInt(split3[i4]);
                        } else if (split2[i4].compareTo("SIMCardID") == 0) {
                            devInfoBean.mDevName = split3[i4];
                        } else if (split2[i4].compareTo("WANIp") == 0) {
                            devInfoBean.mDevIp = split3[i4];
                        } else if (split2[i4].compareTo("TerminalPort") == 0) {
                            if (!"".equals(split3[i4])) {
                                devInfoBean.mMediaPort = Integer.parseInt(split3[i4]);
                            }
                        } else if (split2[i4].compareTo("TransmitPort") == 0) {
                            devInfoBean.mWebPort = Integer.parseInt(split3[i4]);
                        } else if (split2[i4].compareTo("User") == 0) {
                            devInfoBean.mUsername = split3[i4];
                        } else if (split2[i4].compareTo("Key") == 0) {
                            if (this.mLoginType == 3) {
                                devInfoBean.mPwd = this.mPassword;
                            } else {
                                devInfoBean.mPwd = split3[i4];
                            }
                        } else if (split2[i4].compareTo("ChannelCount") == 0) {
                            devInfoBean.mChCounts = Integer.parseInt(split3[i4]);
                        } else if (split2[i4].compareTo("Remark") == 0) {
                            devInfoBean.mRemark = split3[i4];
                        } else if (split2[i4].compareTo("Push") == 0 && !"".equals(split3[i4])) {
                            devInfoBean.mPush = Integer.valueOf(split3[i4]).intValue();
                        }
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

    public DevInfoBean query(int i) {
        DevInfoBean devInfoBean = null;
        for (int i2 = 0; i2 < this.mDevList.size(); i2++) {
            if (this.mDevList.get(i2).mDevId == i) {
                devInfoBean = this.mDevList.get(i2);
            }
        }
        return devInfoBean;
    }

    public DevInfoBean query(String str) {
        DevInfoBean devInfoBean = null;
        for (int i = 0; i < this.mDevList.size(); i++) {
            if (this.mDevList.get(i).mDevName.compareTo(str) == 0) {
                devInfoBean = this.mDevList.get(i);
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

    public List<AlarmInfo> getAlarmBySerialNum(String str, int i) {
        ArrayList arrayList = new ArrayList();
        SoapObject soapObject = new SoapObject("cq-video.cn", "Dvr_AlarmBySerialNum");
        soapObject.addProperty("serialnum", str);
        soapObject.addProperty("count", Integer.valueOf(i));
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(this.mServerIP + "/ddns_service.asmx");
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call("cq-video.cn/Dvr_AlarmBySerialNum", soapSerializationEnvelope);
            if (soapSerializationEnvelope.getResponse() != null) {
                String[] split = soapSerializationEnvelope.getResponse().toString().split(";");
                if (split.length == 1) {
                    return arrayList;
                }
                String[] split2 = split[0].split(",");
                for (int i2 = 1; i2 < split.length; i2++) {
                    String[] split3 = split[i2].split(",");
                    AlarmInfo alarmInfo = new AlarmInfo();
                    for (int i3 = 0; i3 < split3.length; i3++) {
                        if (split2[i3].compareTo("AlarmTime") == 0) {
                            alarmInfo.alarmTime = split3[i3];
                        } else if (split2[i3].compareTo("AlarmType") == 0) {
                            alarmInfo.alarmType = split3[i3];
                        } else if (split2[i3].compareTo("AlarmSubType") == 0) {
                            alarmInfo.alarmSubType = split3[i3];
                        } else if (split2[i3].compareTo("AlarmChannel") == 0) {
                            alarmInfo.alarmChannel = split3[i3];
                        } else if (split2[i3].compareTo("AlarmContent") == 0) {
                            alarmInfo.alarmContent = split3[i3];
                        }
                    }
                    arrayList.add(alarmInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public boolean Login_Status_Android(String str, String str2, String str3) {
        SoapObject soapObject = new SoapObject("cq-video.cn", "Login_Status_Android");
        soapObject.addProperty("androidid", str);
        soapObject.addProperty("username", str3);
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

    public boolean Android_AddForMultiApp(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String str8 = this.mServerIP + "/ddns_service.asmx";
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_AddForMultiApp");
        soapObject.addProperty("androidid", str);
        soapObject.addProperty("serialnum", str2);
        soapObject.addProperty("devicename", str3);
        soapObject.addProperty("username", str4);
        soapObject.addProperty("appIdentification", str7);
        soapObject.addProperty(Configs.Key.LoginType, Integer.toString(this.mLoginType));
        if (this.mLoginType == 0) {
            soapObject.addProperty("deviceid", "0");
        } else {
            soapObject.addProperty("deviceid", str6);
        }
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(str8);
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

    public boolean Android_Add(String str, String str2, String str3, String str4, String str5, String str6) {
        String str7 = this.mServerIP + "/ddns_service.asmx";
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_Add");
        soapObject.addProperty("androidid", str);
        soapObject.addProperty("serialnum", str2);
        soapObject.addProperty("devicename", str3);
        soapObject.addProperty("username", str4);
        soapObject.addProperty(Configs.Key.LoginType, Integer.toString(this.mLoginType));
        if (this.mLoginType == 0) {
            soapObject.addProperty("deviceid", "0");
        } else {
            soapObject.addProperty("deviceid", str6);
        }
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(str7);
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

    public boolean Android_Delete(String str, String str2, String str3, String str4, String str5) {
        String str6 = this.mServerIP + "/ddns_service.asmx";
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_Delete");
        soapObject.addProperty("androidid", str);
        soapObject.addProperty("devicename", str2);
        soapObject.addProperty("username", str3);
        soapObject.addProperty(Configs.Key.LoginType, Integer.toString(this.mLoginType));
        if (this.mLoginType == 0) {
            soapObject.addProperty("deviceid", "0");
        } else {
            soapObject.addProperty("deviceid", str5);
        }
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(str6);
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

    public boolean Android_DeleteAllPush(String str, String str2) {
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_DeleteAll_User");
        soapObject.addProperty("androidid", str);
        soapObject.addProperty("username", str2);
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

    public boolean Android_DeleteAll(String str) {
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_DeleteAll");
        soapObject.addProperty("androidid", str);
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

    public boolean Android_Unregister(String str) {
        SoapObject soapObject = new SoapObject("cq-video.cn", "Android_Unregister");
        soapObject.addProperty("androidid", str);
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
        String str = this.mServerIP + "/serversfordevice/NatServer.ashx?did=";
        HashMap hashMap = new HashMap();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str2 = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str2 = str2 + readLine;
            }
            inputStream.close();
            bufferedReader.close();
            httpURLConnection.disconnect();
            if (str2.length() == 0) {
                return hashMap;
            }
            try {
                JSONObject jSONObject = (JSONObject) new JSONTokener(str2).nextValue();
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

    public int CheckTerminalPw(String str, String str2, String str3) {
        SoapObject soapObject = new SoapObject("cq-video.cn", "CheckTerminalPw");
        soapObject.addProperty("serial", str);
        soapObject.addProperty("username", str2);
        soapObject.addProperty("userpassword", str3);
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
