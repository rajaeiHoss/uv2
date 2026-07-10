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

    private int[] parseFrameRateRange(String frameRateRange) {
        if (frameRateRange != null) {
            String[] parts = frameRateRange.split("-");
            if (parts.length > 1 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                int minFrameRate = Integer.parseInt(parts[0]);
                int maxFrameRate = Integer.parseInt(parts[1]);
                return new int[]{minFrameRate, maxFrameRate};
            }
        }
        return new int[]{1, 1};
    }

    public int frameRate2Index(int resolution, int frameRate) {
        JSONObject streamVpObj = this.mStreamVpObj;
        if (streamVpObj == null) {
            return -1;
        }
        try {
            JSONArray resolutionFrameRates = streamVpObj.getJSONArray("RSFR");
            if (resolutionFrameRates == null) {
                return -1;
            }
            for (int entryIndex = 0; entryIndex < resolutionFrameRates.length(); entryIndex++) {
                JSONObject frameRateObj = resolutionFrameRates.getJSONObject(entryIndex);
                if (frameRateObj != null && resolution == frameRateObj.getInt("RST")) {
                    int[] frameRateRange = parseFrameRateRange(frameRateObj.getString("FR"));
                    int minFrameRate = frameRateRange[0];
                    int maxFrameRate = frameRateRange[1];
                    for (int candidateFrameRate = minFrameRate; candidateFrameRate <= maxFrameRate; candidateFrameRate++) {
                        if (frameRate == candidateFrameRate) {
                            return candidateFrameRate - minFrameRate;
                        }
                    }
                }
            }
            return -1;
        } catch (JSONException unused) {
            return -1;
        }
    }

    public int index2FrameRate(int resolution, int frameRateIndex) {
        JSONObject streamVpObj = this.mStreamVpObj;
        if (streamVpObj == null || frameRateIndex < 0) {
            return -1;
        }
        try {
            JSONArray resolutionFrameRates = streamVpObj.getJSONArray("RSFR");
            if (resolutionFrameRates == null) {
                return -1;
            }
            for (int entryIndex = 0; entryIndex < resolutionFrameRates.length(); entryIndex++) {
                JSONObject frameRateObj = resolutionFrameRates.getJSONObject(entryIndex);
                if (frameRateObj != null && resolution == frameRateObj.getInt("RST")) {
                    int[] frameRateRange = parseFrameRateRange(frameRateObj.getString("FR"));
                    int minFrameRate = frameRateRange[0];
                    int maxFrameRate = frameRateRange[1];
                    int frameRate = minFrameRate + frameRateIndex;
                    return frameRate <= maxFrameRate ? frameRate : -1;
                }
            }
            return -1;
        } catch (JSONException unused) {
            return -1;
        }
    }

    public int encType2Index(int encType) {
        JSONObject streamVpObj = this.mStreamVpObj;
        if (streamVpObj == null) {
            return -1;
        }
        try {
            JSONArray encTypes = streamVpObj.getJSONArray("ENC");
            if (encTypes == null) {
                return -1;
            }
            for (int encIndex = 0; encIndex < encTypes.length(); encIndex++) {
                JSONObject encObj = encTypes.getJSONObject(encIndex);
                if (encObj != null) {
                    if (encType == encObj.getInt("ET")) {
                        return encIndex;
                    }
                }
            }
            return -1;
        } catch (JSONException unused) {
            return -1;
        }
    }

    public int index2EncType(int encIndex) {
        JSONObject streamVpObj = this.mStreamVpObj;
        if (streamVpObj == null) {
            return -1;
        }
        try {
            JSONArray encTypes = streamVpObj.getJSONArray("ENC");
            if (encTypes == null) {
                return -1;
            }
            if (encIndex >= encTypes.length()) {
                return -1;
            }
            JSONObject encObj = encTypes.getJSONObject(encIndex);
            if (encObj == null) {
                return -1;
            }
            return encObj.getInt("ET");
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
            int selectedIndex = -1;
            try {
                this.mListStrResolution.clear();
                this.mListIntResolution.clear();
                int currentResolution = this.mStreamObj.getInt("RST");
                JSONArray resolutionFrameRates = this.mStreamVpObj.getJSONArray("RSFR");
                if (resolutionFrameRates != null) {
                    List<String> resolutionLabels = getStrDatas(R.array.RstSelector);
                    for (int entryIndex = 0; entryIndex < resolutionFrameRates.length(); entryIndex++) {
                        JSONObject resolutionObj = resolutionFrameRates.getJSONObject(entryIndex);
                        if (resolutionObj != null) {
                            int resolution = resolutionObj.getInt("RST");
                            if (resolution >= 0 && resolution < resolutionLabels.size()) {
                                String resolutionLabel = resolutionLabels.get(resolution);
                                if (resolution == currentResolution) {
                                    selectedIndex = entryIndex;
                                }
                                this.mListStrResolution.add(resolutionLabel);
                            }
                        }
                    }
                    if (selectedIndex >= 0 && selectedIndex < this.mListStrResolution.size()) {
                        this.mTvResolution.setText(this.mListStrResolution.get(selectedIndex));
                        this.mListIntResolution.add(new Integer(selectedIndex));
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void configureFrameRate() {
        if (this.mStreamObj != null && this.mStreamVpObj != null) {
            try {
                int resolution = this.mStreamObj.getInt("RST");
                int currentFrameRate = this.mStreamObj.getInt("FR");
                JSONArray resolutionFrameRates = this.mStreamVpObj.getJSONArray("RSFR");
                if (resolutionFrameRates == null) {
                    return;
                }
                for (int entryIndex = 0; entryIndex < resolutionFrameRates.length(); entryIndex++) {
                    JSONObject frameRateObj = resolutionFrameRates.getJSONObject(entryIndex);
                    if (frameRateObj != null && resolution == frameRateObj.getInt("RST")) {
                        int selectedIndex = -1;
                        int[] frameRateRange = parseFrameRateRange(frameRateObj.getString("FR"));
                        int minFrameRate = frameRateRange[0];
                        int maxFrameRate = frameRateRange[1];
                        this.mListStrFrameRate.clear();
                        this.mListIntFrameRate.clear();
                        for (int frameRate = minFrameRate; frameRate <= maxFrameRate; frameRate++) {
                            if (currentFrameRate == frameRate) {
                                selectedIndex = frameRate - minFrameRate;
                            }
                            this.mListStrFrameRate.add("" + frameRate);
                        }
                        if (selectedIndex >= 0 && selectedIndex < this.mListStrFrameRate.size()) {
                            this.mTvFrameRate.setText(this.mListStrFrameRate.get(selectedIndex));
                            this.mListIntFrameRate.add(new Integer(selectedIndex));
                        }
                        return;
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void configureEncType() {
        if (this.mStreamObj != null && this.mStreamVpObj != null) {
            try {
                this.mListStrEncType.clear();
                this.mListIntEncType.clear();
                int encType = this.mStreamObj.getInt("ET");
                int encMask = this.mStreamVpObj.getInt("ENC");
                if (((encMask >> 0) & 1) == 1) {
                    this.mListStrEncType.add("H264");
                }
                if (((encMask >> 1) & 1) == 1) {
                    this.mListStrEncType.add("H265");
                }
                if (encType >= 0 && encType < this.mListStrEncType.size()) {
                    this.mTvEncType.setText(this.mListStrEncType.get(encType));
                    this.mListIntEncType.add(new Integer(encType));
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void refreshUi() {
        int length;
        JSONObject streamResponse = this.mStreamRes;
        if (streamResponse != null) {
            try {
                JSONObject avsmConfig = streamResponse.getJSONObject("AVSM");
                JSONObject specConfig = this.mStreamRes.getJSONObject("SPECP");
                if (avsmConfig == null) {
                    return;
                }
                if (specConfig != null) {
                    JSONObject vpchConfig = specConfig.getJSONObject("VPCH");
                    if (vpchConfig != null) {
                        int streamType = this.mStreamType;
                        if (streamType == 0) {
                            this.mStreamArr = avsmConfig.getJSONArray("MAIN");
                            this.mStreamVpArr = vpchConfig.getJSONArray("MAIN");
                        } else if (streamType == 1) {
                            this.mStreamArr = avsmConfig.getJSONArray("SUB");
                            this.mStreamVpArr = vpchConfig.getJSONArray("SUB");
                        } else if (streamType == 2) {
                            this.mStreamArr = avsmConfig.getJSONArray("MOB");
                            this.mStreamVpArr = vpchConfig.getJSONArray("MOB");
                        }
                        JSONArray streamArray = this.mStreamArr;
                        if (streamArray == null) {
                            return;
                        }
                        if (this.mStreamVpArr != null) {
                            if (streamArray.length() == this.mStreamVpArr.length() && (length = this.mStreamArr.length()) > 0) {
                                int currentChannel = this.mCurCh;
                                if (currentChannel < length) {
                                    this.mStreamObj = this.mStreamArr.getJSONObject(currentChannel);
                                    this.mStreamVpObj = this.mStreamVpArr.getJSONObject(this.mCurCh);
                                    if (this.mStreamObj == null) {
                                        return;
                                    }
                                    if (this.mStreamVpArr != null) {
                                        this.mTvCh.setText("CH" + (this.mCurCh + 1));
                                        this.mListStrChannel.clear();
                                        this.mListIntChannel.clear();
                                        int channelIndex = 0;
                                        while (channelIndex < length) {
                                            ArrayList<String> arrayList = this.mListStrChannel;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("CH");
                                            channelIndex++;
                                            sb.append(channelIndex);
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
                                            int bitRate = this.mStreamObj.getInt("BR");
                                            this.mTvBitRate.setText(bitRate + "kbps");
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
                                            int bitRateMode = this.mStreamObj.getInt("BRM");
                                            List<String> bitModeLabels = getStrDatas(R.array.BrmSelector);
                                            if (bitRateMode >= 0 && bitRateMode < bitModeLabels.size()) {
                                                this.mTvBitMode.setText(bitModeLabels.get(bitRateMode));
                                                this.mListStrBitMode.addAll(bitModeLabels);
                                                this.mListIntBitMode.add(new Integer(bitRateMode));
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
                                            int qualityIndex = this.mStreamObj.getInt("QLT") - 1;
                                            List<String> qualityLabels = getStrDatas(R.array.QltSelector);
                                            if (qualityIndex >= 0 && qualityIndex < qualityLabels.size()) {
                                                this.mTvQulity.setText(qualityLabels.get(qualityIndex));
                                                this.mListStrQulity.addAll(qualityLabels);
                                                this.mListIntQulity.add(new Integer(qualityIndex));
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
        int minBitRate;
        int maxBitRate;
        if (this.mStreamObj != null && this.mStreamVpObj != null) {
            String title = UiUtils.getString(R.string.config_BitRateMode);
            try {
                int currentBitRate = this.mStreamObj.getInt("BR");
                int currentResolution = this.mStreamObj.getInt("RST");
                JSONArray resolutionFrameRates = this.mStreamVpObj.getJSONArray("RSFR");
                if (resolutionFrameRates != null) {
                    int entryIndex = 0;
                    while (true) {
                        if (entryIndex >= resolutionFrameRates.length()) {
                            minBitRate = 1;
                            maxBitRate = 1;
                            break;
                        }
                        JSONObject rangeObj = resolutionFrameRates.getJSONObject(entryIndex);
                        if (rangeObj != null) {
                            if (currentResolution == rangeObj.getInt("RST")) {
                                String bitRateRange = rangeObj.getString("BR");
                                if (bitRateRange != null) {
                                    String[] rangeParts = bitRateRange.split("-");
                                    if (rangeParts.length > 1 && !rangeParts[0].isEmpty() && !rangeParts[1].isEmpty()) {
                                        minBitRate = Integer.parseInt(rangeParts[0]);
                                        maxBitRate = Integer.parseInt(rangeParts[1]);
                                        break;
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                        entryIndex++;
                    }
                    if (minBitRate < maxBitRate) {
                        pushOneNumberEditFragment(title, "OneNumberEditFragmentForBitRate", currentBitRate, minBitRate, maxBitRate);
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
            JSONObject request = new JSONObject();
            JSONObject vpchRequest = new JSONObject();
            int streamType = this.mStreamType;
            if (streamType == 0) {
                vpchRequest.put("MAIN", "?");
            } else if (streamType == 1) {
                vpchRequest.put("SUB", "?");
            } else if (streamType == 2) {
                vpchRequest.put("MOB", "?");
            }
            JSONObject specRequest = new JSONObject();
            specRequest.put("VPCH", vpchRequest);
            JSONObject avsmRequest = new JSONObject();
            avsmRequest.put("REP", "?");
            int currentStreamType = this.mStreamType;
            if (currentStreamType == 0) {
                avsmRequest.put("MAIN", "?");
            } else if (currentStreamType == 1) {
                avsmRequest.put("SUB", "?");
            } else if (currentStreamType == 2) {
                avsmRequest.put("MOB", "?");
            }
            request.put("SPECP", specRequest);
            request.put("AVSM", avsmRequest);
            return request.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public void getSuccess(String response) {
        try {
            this.mStreamRes = new JSONObject(response);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public String requestForSetConfig() {
        JSONObject streamResponse = this.mStreamRes;
        if (streamResponse == null) {
            return "";
        }
        try {
            JSONObject avsmConfig = streamResponse.getJSONObject("AVSM");
            if (avsmConfig == null) {
                return "";
            }
            JSONObject request = new JSONObject();
            request.put("AVSM", avsmConfig);
            return request.toString();
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
        JSONArray streamArr = this.mStreamArr;
        if (streamArr != null) {
            try {
                JSONObject sourceStream = streamArr.getJSONObject(this.mCurCh);
                if (sourceStream != null) {
                    int sourceResolution = sourceStream.getInt("RST");
                    int sourceFrameRate = sourceStream.getInt("FR");
                    int sourceBitRate = sourceStream.getInt("BR");
                    boolean configChanged = false;
                    for (int selectionIndex = 0; selectionIndex < list.size(); selectionIndex++) {
                        int targetChannel = list.get(selectionIndex).intValue();
                        if (targetChannel != this.mCurCh && copyStreamToChannel(sourceStream, sourceResolution, sourceFrameRate, sourceBitRate, targetChannel)) {
                            configChanged = true;
                        }
                    }
                    if (configChanged) {
                        NetPresenter.getDefault().setConfig(this);
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    private boolean copyStreamToChannel(JSONObject sourceStream, int sourceResolution, int sourceFrameRate, int sourceBitRate, int targetChannel) throws JSONException {
        JSONObject targetStream = this.mStreamArr.getJSONObject(targetChannel);
        JSONObject targetStreamVp = this.mStreamVpArr.getJSONObject(targetChannel);
        if (targetStream == null || targetStreamVp == null) {
            return false;
        }
        JSONArray supportedRanges = targetStreamVp.getJSONArray("RSFR");
        if (supportedRanges == null) {
            return false;
        }
        boolean bitRateSupported = false;
        boolean frameRateSupported = false;
        boolean resolutionSupported = false;
        for (int rangeIndex = 0; rangeIndex < supportedRanges.length(); rangeIndex++) {
            JSONObject rangeObj = supportedRanges.getJSONObject(rangeIndex);
            if (rangeObj != null) {
                if (sourceResolution == rangeObj.getInt("RST")) {
                    resolutionSupported = true;
                }
                if (isStrictRangeValue(rangeObj.getString("FR"), sourceFrameRate)) {
                    frameRateSupported = true;
                }
                if (isBitRateSupported(rangeObj.getString("BR"), sourceBitRate)) {
                    bitRateSupported = true;
                }
            }
        }
        JSONObject copiedStream = new JSONObject(sourceStream.toString());
        if (!bitRateSupported) {
            copiedStream.put("BR", targetStream.getInt("BR"));
        }
        if (!frameRateSupported) {
            copiedStream.put("FR", targetStream.getInt("FR"));
        }
        if (!resolutionSupported) {
            copiedStream.put("RST", targetStream.getInt("RST"));
        }
        this.mStreamArr.put(targetChannel, copiedStream);
        return true;
    }

    private boolean isStrictRangeValue(String rangeValue, int value) {
        if (rangeValue != null) {
            String[] rangeParts = rangeValue.split("-");
            if (rangeParts.length > 1 && !rangeParts[0].isEmpty() && !rangeParts[1].isEmpty()) {
                int minValue = Integer.parseInt(rangeParts[0]);
                int maxValue = Integer.parseInt(rangeParts[1]);
                return minValue > 0 && maxValue >= minValue && value >= minValue && value <= maxValue;
            }
        }
        return false;
    }

    private boolean isBitRateSupported(String bitRateRange, int bitRate) {
        if (bitRateRange != null) {
            String[] rangeParts = bitRateRange.split("-");
            if (rangeParts.length <= 1 || rangeParts[0].isEmpty() || rangeParts[1].isEmpty()) {
                return true;
            }
            int minBitRate = Integer.parseInt(rangeParts[0]);
            int maxBitRate = Integer.parseInt(rangeParts[1]);
            return minBitRate > 0 && maxBitRate >= minBitRate && bitRate >= minBitRate && bitRate <= maxBitRate;
        }
        return false;
    }

    public void saveSelect(String fragmentTag, List<Integer> selectedIndexes) {
        if (fragmentTag.equals("SelectFragmentForChannel")) {
            if (selectedIndexes.size() > 0) {
                updateDateForChannel(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForResolution")) {
            if (selectedIndexes.size() > 0) {
                updateDateForResolution(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForFrameRate")) {
            if (selectedIndexes.size() > 0) {
                updateDateForFrameRate(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForBitMode")) {
            if (selectedIndexes.size() > 0) {
                updateDateForBitMode(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForEncType")) {
            if (selectedIndexes.size() > 0) {
                updateDateForEncType(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForQulity")) {
            if (selectedIndexes.size() > 0) {
                updateDateForQulity(selectedIndexes.get(0).intValue());
            }
        } else if (fragmentTag.equals("SelectFragmentForCopy") && selectedIndexes.size() > 0) {
            updateDateForCopy(selectedIndexes);
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

    public void saveOneNumberEdit(String fragmentTag, int value) {
        if (fragmentTag.equals("OneNumberEditFragmentForBitRate")) {
            updateDateForBitRate(value);
        }
    }
}
