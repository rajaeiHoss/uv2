package com.streamax.config.fragment.video;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.UiUtils;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StreamOfVideo extends ConfigFragment implements BaseListener.GetListener, BaseListener.SetListener {
    public static final int STREAM_TYPE_MAIN = 0;
    public static final int STREAM_TYPE_MOBILE = 2;
    public static final int STREAM_TYPE_SUB = 1;
    public Button mBtnCopy;
    public Button mBtnEnable;
    public int mCurCh;
    public List<Integer> mListIntBitMode = new ArrayList();
    public List<Integer> mListIntChannel = new ArrayList();
    public List<Integer> mListIntEncType = new ArrayList();
    public List<Integer> mListIntFrameRate = new ArrayList();
    public List<Integer> mListIntQulity = new ArrayList();
    public List<Integer> mListIntResolution = new ArrayList();
    public ArrayList<String> mListStrBitMode = new ArrayList<>();
    public ArrayList<String> mListStrChannel = new ArrayList<>();
    public ArrayList<String> mListStrEncType = new ArrayList<>();
    public ArrayList<String> mListStrFrameRate = new ArrayList<>();
    public ArrayList<String> mListStrQulity = new ArrayList<>();
    public ArrayList<String> mListStrResolution = new ArrayList<>();
    public RelativeLayout mRlBitMode;
    public RelativeLayout mRlBitRate;
    public RelativeLayout mRlCh;
    public RelativeLayout mRlEnable;
    public RelativeLayout mRlEncType;
    public RelativeLayout mRlFrameRate;
    public RelativeLayout mRlQulity;
    public RelativeLayout mRlResolution;
    public JSONArray mStreamArr;
    public JSONObject mStreamObj;
    public JSONObject mStreamRes;
    public String mStreamTitle;
    public int mStreamType;
    public JSONArray mStreamVpArr;
    public JSONObject mStreamVpObj;
    public TextView mTvBitMode;
    public TextView mTvBitRate;
    public TextView mTvCh;
    public TextView mTvEncType;
    public TextView mTvFrameRate;
    public TextView mTvQulity;
    public TextView mTvResolution;
    public View mVlBitMode;
    public View mVlBitRate;
    public View mVlEnable;
    public View mVlEncType;
    public View mVlQulity;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    public int index2Resolution(int i) {
        JSONObject jSONObject = this.mStreamVpObj;
        if (jSONObject == null) {
            return -1;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("RSFR");
            if (jSONArray == null) {
                return -1;
            }
            if (i >= jSONArray.length()) {
                return -1;
            }
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                return -1;
            }
            return jSONObject2.getInt("RST");
        } catch (JSONException unused) {
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x006b A[EDGE_INSN: B:38:0x006b->B:35:0x006b ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0068 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0064 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int frameRate2Index(int r9, int r10) {
        /*
            r8 = this;
            org.json.JSONObject r0 = r8.mStreamVpObj
            r1 = -1
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.String r2 = "RSFR"
            org.json.JSONArray r0 = r0.getJSONArray(r2)     // Catch:{ JSONException -> 0x006b }
            if (r0 != 0) goto L_0x000f
            return r1
        L_0x000f:
            r2 = 0
            r3 = 0
        L_0x0011:
            int r4 = r0.length()     // Catch:{ JSONException -> 0x006b }
            if (r3 >= r4) goto L_0x006b
            org.json.JSONObject r4 = r0.getJSONObject(r3)     // Catch:{ JSONException -> 0x006b }
            if (r4 != 0) goto L_0x001e
            goto L_0x0068
        L_0x001e:
            java.lang.String r5 = "RST"
            int r5 = r4.getInt(r5)     // Catch:{ JSONException -> 0x006b }
            if (r9 == r5) goto L_0x0027
            goto L_0x0068
        L_0x0027:
            java.lang.String r5 = "FR"
            java.lang.String r4 = r4.getString(r5)     // Catch:{ JSONException -> 0x006b }
            r5 = 1
            if (r4 == 0) goto L_0x0056
            java.lang.String r6 = "-"
            java.lang.String[] r4 = r4.split(r6)     // Catch:{ JSONException -> 0x006b }
            int r6 = r4.length     // Catch:{ JSONException -> 0x006b }
            if (r6 <= r5) goto L_0x0056
            r6 = r4[r2]     // Catch:{ JSONException -> 0x006b }
            boolean r6 = r6.isEmpty()     // Catch:{ JSONException -> 0x006b }
            if (r6 != 0) goto L_0x0056
            r6 = r4[r5]     // Catch:{ JSONException -> 0x006b }
            boolean r6 = r6.isEmpty()     // Catch:{ JSONException -> 0x006b }
            if (r6 != 0) goto L_0x0056
            r6 = r4[r2]     // Catch:{ JSONException -> 0x006b }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ JSONException -> 0x006b }
            r4 = r4[r5]     // Catch:{ JSONException -> 0x006b }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ JSONException -> 0x006b }
            goto L_0x0058
        L_0x0056:
            r4 = 1
            r6 = 1
        L_0x0058:
            r7 = r6
        L_0x0059:
            if (r7 > r4) goto L_0x0064
            if (r10 != r7) goto L_0x0061
            int r7 = r7 - r6
            r1 = r7
            r4 = 1
            goto L_0x0065
        L_0x0061:
            int r7 = r7 + 1
            goto L_0x0059
        L_0x0064:
            r4 = 0
        L_0x0065:
            if (r4 != r5) goto L_0x0068
            goto L_0x006b
        L_0x0068:
            int r3 = r3 + 1
            goto L_0x0011
        L_0x006b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamax.config.fragment.video.StreamOfVideo.frameRate2Index(int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x006b A[EDGE_INSN: B:38:0x006b->B:35:0x006b ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0068 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0064 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int index2FrameRate(int r9, int r10) {
        /*
            r8 = this;
            org.json.JSONObject r0 = r8.mStreamVpObj
            r1 = -1
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.String r2 = "RSFR"
            org.json.JSONArray r0 = r0.getJSONArray(r2)     // Catch:{ JSONException -> 0x006b }
            if (r0 != 0) goto L_0x000f
            return r1
        L_0x000f:
            r2 = 0
            r3 = 0
        L_0x0011:
            int r4 = r0.length()     // Catch:{ JSONException -> 0x006b }
            if (r3 >= r4) goto L_0x006b
            org.json.JSONObject r4 = r0.getJSONObject(r3)     // Catch:{ JSONException -> 0x006b }
            if (r4 != 0) goto L_0x001e
            goto L_0x0068
        L_0x001e:
            java.lang.String r5 = "RST"
            int r5 = r4.getInt(r5)     // Catch:{ JSONException -> 0x006b }
            if (r9 == r5) goto L_0x0027
            goto L_0x0068
        L_0x0027:
            java.lang.String r5 = "FR"
            java.lang.String r4 = r4.getString(r5)     // Catch:{ JSONException -> 0x006b }
            r5 = 1
            if (r4 == 0) goto L_0x0056
            java.lang.String r6 = "-"
            java.lang.String[] r4 = r4.split(r6)     // Catch:{ JSONException -> 0x006b }
            int r6 = r4.length     // Catch:{ JSONException -> 0x006b }
            if (r6 <= r5) goto L_0x0056
            r6 = r4[r2]     // Catch:{ JSONException -> 0x006b }
            boolean r6 = r6.isEmpty()     // Catch:{ JSONException -> 0x006b }
            if (r6 != 0) goto L_0x0056
            r6 = r4[r5]     // Catch:{ JSONException -> 0x006b }
            boolean r6 = r6.isEmpty()     // Catch:{ JSONException -> 0x006b }
            if (r6 != 0) goto L_0x0056
            r6 = r4[r2]     // Catch:{ JSONException -> 0x006b }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ JSONException -> 0x006b }
            r4 = r4[r5]     // Catch:{ JSONException -> 0x006b }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ JSONException -> 0x006b }
            goto L_0x0058
        L_0x0056:
            r4 = 1
            r6 = 1
        L_0x0058:
            r7 = r6
        L_0x0059:
            if (r7 >= r4) goto L_0x0064
            if (r10 != r7) goto L_0x0061
            int r7 = r7 + r6
            r1 = r7
            r4 = 1
            goto L_0x0065
        L_0x0061:
            int r7 = r7 + 1
            goto L_0x0059
        L_0x0064:
            r4 = 0
        L_0x0065:
            if (r4 != r5) goto L_0x0068
            goto L_0x006b
        L_0x0068:
            int r3 = r3 + 1
            goto L_0x0011
        L_0x006b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamax.config.fragment.video.StreamOfVideo.index2FrameRate(int, int):int");
    }

    public int encType2Index(int i) {
        JSONObject jSONObject = this.mStreamVpObj;
        if (jSONObject == null) {
            return -1;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("ENC");
            if (jSONArray == null) {
                return -1;
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                if (jSONObject2 != null) {
                    if (i == jSONObject2.getInt("ET")) {
                        return i2;
                    }
                }
            }
            return -1;
        } catch (JSONException unused) {
            return -1;
        }
    }

    public int index2EncType(int i) {
        JSONObject jSONObject = this.mStreamVpObj;
        if (jSONObject == null) {
            return -1;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("ENC");
            if (jSONArray == null) {
                return -1;
            }
            if (i >= jSONArray.length()) {
                return -1;
            }
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                return -1;
            }
            return jSONObject2.getInt("ET");
        } catch (JSONException unused) {
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mCurCh = 0;
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(this.mStreamTitle);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_stream_video, (ViewGroup) null);
        this.mRlCh = (RelativeLayout) this.mRootView.findViewById(R.id.config_stream_video_rl_ch);
        this.mTvCh = (TextView) this.mRootView.findViewById(R.id.config_stream_video_tv_ch);
        this.mVlEnable = this.mRootView.findViewById(R.id.config_stream_video_view_enable);
        this.mRlEnable = (RelativeLayout) this.mRootView.findViewById(R.id.config_stream_video_rl_enable);
        this.mBtnEnable = (Button) this.mRootView.findViewById(R.id.config_stream_video_btn_enable);
        this.mRlResolution = (RelativeLayout) this.mRootView.findViewById(R.id.config_stream_video_rl_resolution);
        this.mTvResolution = (TextView) this.mRootView.findViewById(R.id.config_stream_video_tv_resolution);
        this.mRlFrameRate = (RelativeLayout) this.mRootView.findViewById(R.id.config_stream_video_rl_framerate);
        this.mTvFrameRate = (TextView) this.mRootView.findViewById(R.id.config_stream_video_tv_framerate);
        this.mVlBitRate = this.mRootView.findViewById(R.id.config_stream_video_view_bitrate);
        this.mRlBitRate = (RelativeLayout) this.mRootView.findViewById(R.id.config_stream_video_rl_bitrate);
        this.mTvBitRate = (TextView) this.mRootView.findViewById(R.id.config_stream_video_tv_bitrate);
        this.mVlBitMode = this.mRootView.findViewById(R.id.config_stream_video_view_bitmode);
        this.mRlBitMode = (RelativeLayout) this.mRootView.findViewById(R.id.config_stream_video_rl_bitmode);
        this.mTvBitMode = (TextView) this.mRootView.findViewById(R.id.config_stream_video_tv_bitmode);
        this.mVlEncType = this.mRootView.findViewById(R.id.config_stream_video_view_enctype);
        this.mRlEncType = (RelativeLayout) this.mRootView.findViewById(R.id.config_stream_video_rl_enctype);
        this.mTvEncType = (TextView) this.mRootView.findViewById(R.id.config_stream_video_tv_enctype);
        this.mVlQulity = this.mRootView.findViewById(R.id.config_stream_video_view_qulity);
        this.mRlQulity = (RelativeLayout) this.mRootView.findViewById(R.id.config_stream_video_rl_qulity);
        this.mTvQulity = (TextView) this.mRootView.findViewById(R.id.config_stream_video_tv_qulity);
        this.mBtnCopy = (Button) this.mRootView.findViewById(R.id.config_stream_video_btn_copy);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void configureResolution() {
        if (this.mStreamObj != null && this.mStreamVpObj != null) {
            int i = -1;
            try {
                this.mListStrResolution.clear();
                this.mListIntResolution.clear();
                int i2 = this.mStreamObj.getInt("RST");
                JSONArray jSONArray = this.mStreamVpObj.getJSONArray("RSFR");
                if (jSONArray != null) {
                    List<String> strDatas = getStrDatas(R.array.RstSelector);
                    for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i3);
                        if (jSONObject != null) {
                            int i4 = jSONObject.getInt("RST");
                            if (i4 >= 0 && i4 < strDatas.size()) {
                                String str = strDatas.get(i4);
                                if (i4 == i2) {
                                    i = i3;
                                }
                                this.mListStrResolution.add(str);
                            }
                        }
                    }
                    if (i >= 0 && i < this.mListStrResolution.size()) {
                        this.mTvResolution.setText(this.mListStrResolution.get(i));
                        this.mListIntResolution.add(new Integer(i));
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0079 A[Catch:{ JSONException -> 0x00bb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void configureFrameRate() {
        /*
            r14 = this;
            java.lang.String r0 = "FR"
            java.lang.String r1 = "RST"
            org.json.JSONObject r2 = r14.mStreamObj
            if (r2 == 0) goto L_0x00bb
            org.json.JSONObject r3 = r14.mStreamVpObj
            if (r3 != 0) goto L_0x000e
            goto L_0x00bb
        L_0x000e:
            r3 = -1
            int r2 = r2.getInt(r1)     // Catch:{ JSONException -> 0x00bb }
            org.json.JSONObject r4 = r14.mStreamObj     // Catch:{ JSONException -> 0x00bb }
            int r4 = r4.getInt(r0)     // Catch:{ JSONException -> 0x00bb }
            org.json.JSONObject r5 = r14.mStreamVpObj     // Catch:{ JSONException -> 0x00bb }
            java.lang.String r6 = "RSFR"
            org.json.JSONArray r5 = r5.getJSONArray(r6)     // Catch:{ JSONException -> 0x00bb }
            if (r5 != 0) goto L_0x0024
            return
        L_0x0024:
            r6 = 0
            r7 = 0
        L_0x0026:
            int r8 = r5.length()     // Catch:{ JSONException -> 0x00bb }
            if (r7 >= r8) goto L_0x00bb
            org.json.JSONObject r8 = r5.getJSONObject(r7)     // Catch:{ JSONException -> 0x00bb }
            if (r8 != 0) goto L_0x0034
            goto L_0x00b7
        L_0x0034:
            int r9 = r8.getInt(r1)     // Catch:{ JSONException -> 0x00bb }
            if (r2 == r9) goto L_0x003c
            goto L_0x00b7
        L_0x003c:
            java.lang.String r8 = r8.getString(r0)     // Catch:{ JSONException -> 0x00bb }
            r9 = 1
            if (r8 == 0) goto L_0x006b
            java.lang.String r10 = "-"
            java.lang.String[] r8 = r8.split(r10)     // Catch:{ JSONException -> 0x00bb }
            int r10 = r8.length     // Catch:{ JSONException -> 0x00bb }
            if (r10 <= r9) goto L_0x006b
            r10 = r8[r6]     // Catch:{ JSONException -> 0x00bb }
            boolean r10 = r10.isEmpty()     // Catch:{ JSONException -> 0x00bb }
            if (r10 != 0) goto L_0x006b
            r10 = r8[r9]     // Catch:{ JSONException -> 0x00bb }
            boolean r10 = r10.isEmpty()     // Catch:{ JSONException -> 0x00bb }
            if (r10 != 0) goto L_0x006b
            r10 = r8[r6]     // Catch:{ JSONException -> 0x00bb }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ JSONException -> 0x00bb }
            r8 = r8[r9]     // Catch:{ JSONException -> 0x00bb }
            int r9 = java.lang.Integer.parseInt(r8)     // Catch:{ JSONException -> 0x00bb }
            r8 = r9
            r9 = r10
            goto L_0x006c
        L_0x006b:
            r8 = 1
        L_0x006c:
            java.util.ArrayList<java.lang.String> r10 = r14.mListStrFrameRate     // Catch:{ JSONException -> 0x00bb }
            r10.clear()     // Catch:{ JSONException -> 0x00bb }
            java.util.List<java.lang.Integer> r10 = r14.mListIntFrameRate     // Catch:{ JSONException -> 0x00bb }
            r10.clear()     // Catch:{ JSONException -> 0x00bb }
            r10 = r9
        L_0x0077:
            if (r10 > r8) goto L_0x0096
            if (r4 != r10) goto L_0x007d
            int r3 = r10 - r9
        L_0x007d:
            java.util.ArrayList<java.lang.String> r11 = r14.mListStrFrameRate     // Catch:{ JSONException -> 0x00bb }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00bb }
            r12.<init>()     // Catch:{ JSONException -> 0x00bb }
            java.lang.String r13 = ""
            r12.append(r13)     // Catch:{ JSONException -> 0x00bb }
            r12.append(r10)     // Catch:{ JSONException -> 0x00bb }
            java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x00bb }
            r11.add(r12)     // Catch:{ JSONException -> 0x00bb }
            int r10 = r10 + 1
            goto L_0x0077
        L_0x0096:
            if (r3 < 0) goto L_0x00b7
            java.util.ArrayList<java.lang.String> r8 = r14.mListStrFrameRate     // Catch:{ JSONException -> 0x00bb }
            int r8 = r8.size()     // Catch:{ JSONException -> 0x00bb }
            if (r3 >= r8) goto L_0x00b7
            android.widget.TextView r8 = r14.mTvFrameRate     // Catch:{ JSONException -> 0x00bb }
            java.util.ArrayList<java.lang.String> r9 = r14.mListStrFrameRate     // Catch:{ JSONException -> 0x00bb }
            java.lang.Object r9 = r9.get(r3)     // Catch:{ JSONException -> 0x00bb }
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ JSONException -> 0x00bb }
            r8.setText(r9)     // Catch:{ JSONException -> 0x00bb }
            java.util.List<java.lang.Integer> r8 = r14.mListIntFrameRate     // Catch:{ JSONException -> 0x00bb }
            java.lang.Integer r9 = new java.lang.Integer     // Catch:{ JSONException -> 0x00bb }
            r9.<init>(r3)     // Catch:{ JSONException -> 0x00bb }
            r8.add(r9)     // Catch:{ JSONException -> 0x00bb }
        L_0x00b7:
            int r7 = r7 + 1
            goto L_0x0026
        L_0x00bb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamax.config.fragment.video.StreamOfVideo.configureFrameRate():void");
    }

    public void configureEncType() {
        if (this.mStreamObj != null && this.mStreamVpObj != null) {
            try {
                this.mListStrEncType.clear();
                this.mListIntEncType.clear();
                int i = this.mStreamObj.getInt("ET");
                int i2 = this.mStreamVpObj.getInt("ENC");
                if (((i2 >> 0) & 1) == 1) {
                    this.mListStrEncType.add("H264");
                }
                if (((i2 >> 1) & 1) == 1) {
                    this.mListStrEncType.add("H265");
                }
                if (i >= 0 && i < this.mListStrEncType.size()) {
                    this.mTvEncType.setText(this.mListStrEncType.get(i));
                    this.mListIntEncType.add(new Integer(i));
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void refreshUi() {
        int length;
        JSONObject jSONObject = this.mStreamRes;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("AVSM");
                JSONObject jSONObject3 = this.mStreamRes.getJSONObject("SPECP");
                if (jSONObject2 == null) {
                    return;
                }
                if (jSONObject3 != null) {
                    JSONObject jSONObject4 = jSONObject3.getJSONObject("VPCH");
                    if (jSONObject4 != null) {
                        int i = this.mStreamType;
                        if (i == 0) {
                            this.mStreamArr = jSONObject2.getJSONArray("MAIN");
                            this.mStreamVpArr = jSONObject4.getJSONArray("MAIN");
                        } else if (i == 1) {
                            this.mStreamArr = jSONObject2.getJSONArray("SUB");
                            this.mStreamVpArr = jSONObject4.getJSONArray("SUB");
                        } else if (i == 2) {
                            this.mStreamArr = jSONObject2.getJSONArray("MOB");
                            this.mStreamVpArr = jSONObject4.getJSONArray("MOB");
                        }
                        JSONArray jSONArray = this.mStreamArr;
                        if (jSONArray == null) {
                            return;
                        }
                        if (this.mStreamVpArr != null) {
                            if (jSONArray.length() == this.mStreamVpArr.length() && (length = this.mStreamArr.length()) > 0) {
                                int i2 = this.mCurCh;
                                if (i2 < length) {
                                    this.mStreamObj = this.mStreamArr.getJSONObject(i2);
                                    this.mStreamVpObj = this.mStreamVpArr.getJSONObject(this.mCurCh);
                                    if (this.mStreamObj == null) {
                                        return;
                                    }
                                    if (this.mStreamVpArr != null) {
                                        this.mTvCh.setText("CH" + (this.mCurCh + 1));
                                        this.mListStrChannel.clear();
                                        this.mListIntChannel.clear();
                                        int i3 = 0;
                                        while (i3 < length) {
                                            ArrayList<String> arrayList = this.mListStrChannel;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("CH");
                                            i3++;
                                            sb.append(i3);
                                            arrayList.add(sb.toString());
                                        }
                                        this.mListIntChannel.add(new Integer(this.mCurCh));
                                        if (this.mStreamType == 0) {
                                            this.mVlEnable.setVisibility(8);
                                            this.mRlEnable.setVisibility(8);
                                        } else {
                                            this.mVlEnable.setVisibility(0);
                                            this.mRlEnable.setVisibility(0);
                                            this.mBtnEnable.setBackgroundResource(this.mStreamObj.getInt("VEN") <= 0 ? R.drawable.switch_close : R.drawable.switch_open);
                                        }
                                        configureResolution();
                                        configureFrameRate();
                                        if (dvrNet.nDevClass == 1) {
                                            this.mVlBitRate.setVisibility(0);
                                            this.mRlBitRate.setVisibility(0);
                                            this.mTvBitRate.setVisibility(0);
                                            int i4 = this.mStreamObj.getInt("BR");
                                            this.mTvBitRate.setText(i4 + "kbps");
                                        } else {
                                            this.mVlBitRate.setVisibility(8);
                                            this.mRlBitRate.setVisibility(8);
                                            this.mTvBitRate.setVisibility(8);
                                        }
                                        this.mListStrBitMode.clear();
                                        this.mListIntBitMode.clear();
                                        if (dvrNet.nDevClass == 1) {
                                            this.mVlBitMode.setVisibility(0);
                                            this.mRlBitMode.setVisibility(0);
                                            this.mTvBitMode.setVisibility(0);
                                            int i5 = this.mStreamObj.getInt("BRM");
                                            List<String> strDatas = getStrDatas(R.array.BrmSelector);
                                            if (i5 >= 0 && i5 < strDatas.size()) {
                                                this.mTvBitMode.setText(strDatas.get(i5));
                                                this.mListStrBitMode.addAll(strDatas);
                                                this.mListIntBitMode.add(new Integer(i5));
                                            }
                                        } else {
                                            this.mVlBitMode.setVisibility(8);
                                            this.mRlBitMode.setVisibility(8);
                                            this.mTvBitMode.setVisibility(8);
                                        }
                                        if (dvrNet.nDevClass == 1) {
                                            this.mVlEncType.setVisibility(0);
                                            this.mRlEncType.setVisibility(0);
                                            this.mTvEncType.setVisibility(0);
                                            configureEncType();
                                        } else {
                                            this.mVlEncType.setVisibility(8);
                                            this.mRlEncType.setVisibility(8);
                                            this.mTvEncType.setVisibility(8);
                                        }
                                        this.mListStrQulity.clear();
                                        this.mListIntQulity.clear();
                                        if (dvrNet.nDevClass != 1) {
                                            this.mVlQulity.setVisibility(0);
                                            this.mRlQulity.setVisibility(0);
                                            this.mTvQulity.setVisibility(0);
                                            int i6 = this.mStreamObj.getInt("QLT") - 1;
                                            List<String> strDatas2 = getStrDatas(R.array.QltSelector);
                                            if (i6 >= 0 && i6 < strDatas2.size()) {
                                                this.mTvQulity.setText(strDatas2.get(i6));
                                                this.mListStrQulity.addAll(strDatas2);
                                                this.mListIntQulity.add(new Integer(i6));
                                                return;
                                            }
                                            return;
                                        }
                                        this.mVlQulity.setVisibility(8);
                                        this.mRlQulity.setVisibility(8);
                                        this.mTvQulity.setVisibility(8);
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
        this.mRlCh.setOnClickListener(this);
        this.mBtnEnable.setOnClickListener(this);
        this.mRlResolution.setOnClickListener(this);
        this.mRlFrameRate.setOnClickListener(this);
        this.mRlBitRate.setOnClickListener(this);
        this.mRlBitMode.setOnClickListener(this);
        this.mRlEncType.setOnClickListener(this);
        this.mRlQulity.setOnClickListener(this);
        this.mBtnEnable.setOnClickListener(this);
        this.mBtnCopy.setOnClickListener(this);
    }

    public void pushFragmentForChannel() {
        if (this.mStreamArr != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Channel), "SelectFragmentForChannel", 0, this.mListStrChannel, this.mListIntChannel);
        }
    }

    public void saveEnableStatus() {
        JSONObject jSONObject = this.mStreamObj;
        if (jSONObject != null) {
            try {
                this.mStreamObj.put("VEN", jSONObject.getInt("VEN") == 0 ? 1 : 0);
            } catch (JSONException unused) {
            }
            NetPresenter.getDefault().setConfig(this);
        }
    }

    public void pushFragmentForResolution() {
        if (this.mStreamObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Resolution), "SelectFragmentForResolution", 0, this.mListStrResolution, this.mListIntResolution);
        }
    }

    public void pushFragmentForFrameRate() {
        if (this.mStreamObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_FrameRate), "SelectFragmentForFrameRate", 0, this.mListStrFrameRate, this.mListIntFrameRate);
        }
    }

    public void pushFragmentForBitRate() {
        int i;
        int i2;
        if (this.mStreamObj != null && this.mStreamVpObj != null) {
            String string = UiUtils.getString(R.string.config_BitRateMode);
            try {
                int i3 = this.mStreamObj.getInt("BR");
                int i4 = this.mStreamObj.getInt("RST");
                JSONArray jSONArray = this.mStreamVpObj.getJSONArray("RSFR");
                if (jSONArray != null) {
                    int i5 = 0;
                    while (true) {
                        if (i5 >= jSONArray.length()) {
                            i = 1;
                            i2 = 1;
                            break;
                        }
                        JSONObject jSONObject = jSONArray.getJSONObject(i5);
                        if (jSONObject != null) {
                            if (i4 == jSONObject.getInt("RST")) {
                                String string2 = jSONObject.getString("BR");
                                if (string2 != null) {
                                    String[] split = string2.split("-");
                                    if (split.length > 1 && !split[0].isEmpty() && !split[1].isEmpty()) {
                                        i = Integer.parseInt(split[0]);
                                        i2 = Integer.parseInt(split[1]);
                                        break;
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                        i5++;
                    }
                    if (i < i2) {
                        pushOneNumberEditFragment(string, "OneNumberEditFragmentForBitRate", i3, i, i2);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void pushFragmentForBitMode() {
        if (this.mStreamObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_BitRateMode), "SelectFragmentForBitMode", 0, this.mListStrBitMode, this.mListIntBitMode);
        }
    }

    public void pushFragmentForEncType() {
        if (this.mStreamObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_EncodeType), "SelectFragmentForEncType", 0, this.mListStrEncType, this.mListIntEncType);
        }
    }

    public void pushFragmentForQulity() {
        if (this.mStreamObj != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_Qulity), "SelectFragmentForQulity", 0, this.mListStrQulity, this.mListIntQulity);
        }
    }

    public void pushFragmentForCopy() {
        if (this.mStreamArr != null) {
            pushSelectFragment(UiUtils.getString(R.string.config_CopyChToCh), "SelectFragmentForCopy", 1, this.mListStrChannel, (List<Integer>) null);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.config_title_btn_back) {
            switch (id) {
                case R.id.config_stream_video_btn_copy /*2131362168*/:
                    pushFragmentForCopy();
                    return;
                case R.id.config_stream_video_btn_enable /*2131362169*/:
                    saveEnableStatus();
                    return;
                case R.id.config_stream_video_rl_bitmode /*2131362170*/:
                    pushFragmentForBitMode();
                    return;
                case R.id.config_stream_video_rl_bitrate /*2131362171*/:
                    pushFragmentForBitRate();
                    return;
                case R.id.config_stream_video_rl_ch /*2131362172*/:
                    pushFragmentForChannel();
                    return;
                default:
                    switch (id) {
                        case R.id.config_stream_video_rl_enctype /*2131362174*/:
                            pushFragmentForEncType();
                            return;
                        case R.id.config_stream_video_rl_framerate /*2131362175*/:
                            pushFragmentForFrameRate();
                            return;
                        case R.id.config_stream_video_rl_qulity /*2131362176*/:
                            pushFragmentForQulity();
                            return;
                        case R.id.config_stream_video_rl_resolution /*2131362177*/:
                            pushFragmentForResolution();
                            return;
                        default:
                            return;
                    }
            }
        } else {
            prePage();
        }
    }

    public String requestForGetConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            int i = this.mStreamType;
            if (i == 0) {
                jSONObject2.put("MAIN", "?");
            } else if (i == 1) {
                jSONObject2.put("SUB", "?");
            } else if (i == 2) {
                jSONObject2.put("MOB", "?");
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("VPCH", jSONObject2);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("REP", "?");
            int i2 = this.mStreamType;
            if (i2 == 0) {
                jSONObject4.put("MAIN", "?");
            } else if (i2 == 1) {
                jSONObject4.put("SUB", "?");
            } else if (i2 == 2) {
                jSONObject4.put("MOB", "?");
            }
            jSONObject.put("SPECP", jSONObject3);
            jSONObject.put("AVSM", jSONObject4);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String str) {
        try {
            this.mStreamRes = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject jSONObject = this.mStreamRes;
        if (jSONObject == null) {
            return "";
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("AVSM");
            if (jSONObject2 == null) {
                return "";
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("AVSM", jSONObject2);
            return jSONObject3.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void setSuccess() {
        refreshUi();
    }

    public void setFailure() {
        toastFailure();
    }

    public void updateDateForChannel(int i) {
        if (this.mStreamArr != null && this.mCurCh != i) {
            this.mCurCh = i;
            refreshUi();
        }
    }

    public void updateDateForResolution(int i) {
        if (this.mStreamObj != null) {
            try {
                int index2Resolution = index2Resolution(i);
                if (index2Resolution >= 0) {
                    this.mStreamObj.put("RST", index2Resolution);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForFrameRate(int i) {
        JSONObject jSONObject = this.mStreamObj;
        if (jSONObject != null) {
            try {
                int index2FrameRate = index2FrameRate(jSONObject.getInt("RST"), i);
                if (index2FrameRate >= 0) {
                    this.mStreamObj.put("FR", index2FrameRate);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForBitMode(int i) {
        JSONObject jSONObject = this.mStreamObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("BRM", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForEncType(int i) {
        if (this.mStreamObj != null) {
            try {
                int index2EncType = index2EncType(i);
                if (index2EncType >= 0) {
                    this.mStreamObj.put("ET", index2EncType);
                    NetPresenter.getDefault().setConfig(this);
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForQulity(int i) {
        JSONObject jSONObject = this.mStreamObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("QLT", i + 1);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void updateDateForCopy(List<Integer> list) {
        int i;
        int i2;
        int i3;
        int i4;
        JSONArray jSONArray;
        int i5;
        int i6;
        int i7;
        int i8;
        JSONArray jSONArray2 = this.mStreamArr;
        if (jSONArray2 != null) {
            try {
                JSONObject jSONObject = jSONArray2.getJSONObject(this.mCurCh);
                if (jSONObject != null) {
                    int i9 = jSONObject.getInt("RST");
                    int i10 = jSONObject.getInt("FR");
                    int i11 = jSONObject.getInt("BR");
                    int i12 = 0;
                    boolean z = false;
                    while (i12 < list.size()) {
                        int intValue = list.get(i12).intValue();
                        if (intValue != this.mCurCh) {
                            JSONObject jSONObject2 = this.mStreamArr.getJSONObject(intValue);
                            JSONObject jSONObject3 = this.mStreamVpArr.getJSONObject(intValue);
                            if (jSONObject2 != null) {
                                if (jSONObject3 != null) {
                                    JSONArray jSONArray3 = jSONObject3.getJSONArray("RSFR");
                                    if (jSONArray3 != null) {
                                        int i13 = 0;
                                        boolean z2 = false;
                                        boolean z3 = false;
                                        boolean z4 = false;
                                        while (i13 < jSONArray3.length()) {
                                            JSONObject jSONObject4 = jSONArray3.getJSONObject(i13);
                                            if (jSONObject4 == null) {
                                                i4 = i9;
                                                jSONArray = jSONArray3;
                                                i3 = i12;
                                            } else {
                                                jSONArray = jSONArray3;
                                                if (i9 == jSONObject4.getInt("RST")) {
                                                    z4 = true;
                                                }
                                                String string = jSONObject4.getString("FR");
                                                i4 = i9;
                                                if (string != null) {
                                                    String[] split = string.split("-");
                                                    i3 = i12;
                                                    if (split.length <= 1 || split[0].isEmpty() || split[1].isEmpty()) {
                                                        i8 = -1;
                                                        i7 = -1;
                                                    } else {
                                                        i7 = Integer.parseInt(split[0]);
                                                        i8 = Integer.parseInt(split[1]);
                                                    }
                                                    if (i7 > 0 && i8 >= i7 && i10 >= i7 && i10 <= i8) {
                                                        z3 = true;
                                                    }
                                                } else {
                                                    i3 = i12;
                                                }
                                                String string2 = jSONObject4.getString("BR");
                                                if (string2 != null) {
                                                    String[] split2 = string2.split("-");
                                                    if (split2.length > 1) {
                                                        if (!split2[0].isEmpty() && !split2[1].isEmpty()) {
                                                            int parseInt = Integer.parseInt(split2[0]);
                                                            i5 = Integer.parseInt(split2[1]);
                                                            i6 = parseInt;
                                                            if (i6 > 0 && i5 >= i6 && i11 >= i6 && i11 <= i5) {
                                                                z2 = true;
                                                            }
                                                            i13++;
                                                            List<Integer> list2 = list;
                                                            jSONArray3 = jSONArray;
                                                            i9 = i4;
                                                            i12 = i3;
                                                        }
                                                    }
                                                    i6 = -1;
                                                    i5 = -1;
                                                    z2 = true;
                                                    i13++;
                                                    List<Integer> list22 = list;
                                                    jSONArray3 = jSONArray;
                                                    i9 = i4;
                                                    i12 = i3;
                                                }
                                            }
                                            i13++;
                                            List<Integer> list222 = list;
                                            jSONArray3 = jSONArray;
                                            i9 = i4;
                                            i12 = i3;
                                        }
                                        i2 = i9;
                                        i = i12;
                                        JSONObject jSONObject5 = new JSONObject(jSONObject.toString());
                                        if (!z2) {
                                            jSONObject5.put("BR", jSONObject2.getInt("BR"));
                                        }
                                        if (!z3) {
                                            jSONObject5.put("FR", jSONObject2.getInt("FR"));
                                        }
                                        if (!z4) {
                                            jSONObject5.put("RST", jSONObject2.getInt("RST"));
                                        }
                                        this.mStreamArr.put(intValue, jSONObject5);
                                        z = true;
                                        i12 = i + 1;
                                        i9 = i2;
                                    }
                                }
                            }
                        }
                        i2 = i9;
                        i = i12;
                        i12 = i + 1;
                        i9 = i2;
                    }
                    if (z) {
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForChannel")) {
            if (list.size() > 0) {
                updateDateForChannel(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForResolution")) {
            if (list.size() > 0) {
                updateDateForResolution(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForFrameRate")) {
            if (list.size() > 0) {
                updateDateForFrameRate(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForBitMode")) {
            if (list.size() > 0) {
                updateDateForBitMode(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForEncType")) {
            if (list.size() > 0) {
                updateDateForEncType(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForQulity")) {
            if (list.size() > 0) {
                updateDateForQulity(list.get(0).intValue());
            }
        } else if (str.equals("SelectFragmentForCopy") && list.size() > 0) {
            updateDateForCopy(list);
        }
    }

    public void updateDateForBitRate(int i) {
        JSONObject jSONObject = this.mStreamObj;
        if (jSONObject != null) {
            try {
                jSONObject.put("BR", i);
                NetPresenter.getDefault().setConfig(this);
            } catch (JSONException unused) {
            }
        }
    }

    public void saveOneNumberEdit(String str, int i) {
        if (str.equals("OneNumberEditFragmentForBitRate")) {
            updateDateForBitRate(i);
        }
    }
}
