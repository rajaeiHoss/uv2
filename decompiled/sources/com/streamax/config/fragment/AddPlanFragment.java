package com.streamax.config.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pickview.TimePickerView;
import com.streamax.config.base.ConfigFragment;
import com.streamax.utils.StringUtils;
import com.streamax.utils.TimeUtils;
import com.streamax.utils.UiUtils;
import com.zycs.UView.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddPlanFragment extends ConfigFragment implements TimeUtils.TimePickListener {
    public static final String mTimePrefix = "2017-07-06 ";
    public Button mBtnSave;
    private int mEnd;
    private AddPlanInterface mInterface;
    public List<Integer> mListIntType = new ArrayList();
    public ArrayList<String> mListStrType = new ArrayList<>();
    public RelativeLayout mRlEnd;
    public RelativeLayout mRlStart;
    public RelativeLayout mRlType;
    private boolean mShowType;
    private int mStart;
    public TextView mTvEnd;
    public TextView mTvStart;
    public TextView mTvType;
    private int mType;
    public View mViewLine;

    public interface AddPlanInterface {
        void savePlan(int i, int i2, int i3);
    }

    public void SetAddPlanInterface(AddPlanInterface addPlanInterface) {
        this.mInterface = addPlanInterface;
    }

    public void ShowType(boolean z) {
        this.mShowType = z;
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mStart = 0;
        this.mEnd = 86399;
        this.mType = 0;
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.SetText((int) R.string.config_record_schedule_Title);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_addplan, (ViewGroup) null);
        initChildView();
        return this.mRootView;
    }

    private void initChildView() {
        this.mRlStart = (RelativeLayout) this.mRootView.findViewById(R.id.config_addplan_rl_starttime);
        this.mTvStart = (TextView) this.mRootView.findViewById(R.id.config_addplan_tv_starttime);
        this.mRlEnd = (RelativeLayout) this.mRootView.findViewById(R.id.config_addplan_rl_endtime);
        this.mTvEnd = (TextView) this.mRootView.findViewById(R.id.config_addplan_tv_endtime);
        this.mViewLine = this.mRootView.findViewById(R.id.config_addplan_line_above_recordtype);
        this.mRlType = (RelativeLayout) this.mRootView.findViewById(R.id.config_addplan_rl_recordtype);
        this.mTvType = (TextView) this.mRootView.findViewById(R.id.config_addplan_tv_recordtype);
        this.mBtnSave = (Button) this.mRootView.findViewById(R.id.config_addplan_btn_save);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        refreshUi();
    }

    private void refreshUi() {
        this.mTvStart.setText(parse2String_86399(this.mStart));
        this.mTvEnd.setText(parse2String_86399(this.mEnd));
        if (this.mShowType) {
            this.mViewLine.setVisibility(0);
            this.mRlType.setVisibility(0);
            this.mListStrType.clear();
            this.mListIntType.clear();
            List<String> strDatas = getStrDatas(R.array.config_record_schedule_rtSelector);
            int i = this.mType;
            if (i >= 0 && i < strDatas.size()) {
                this.mTvType.setText(strDatas.get(this.mType));
                this.mListStrType.addAll(strDatas);
                this.mListIntType.add(new Integer(this.mType));
                return;
            }
            return;
        }
        this.mViewLine.setVisibility(8);
        this.mRlType.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
        this.mRlStart.setOnClickListener(this);
        this.mRlEnd.setOnClickListener(this);
        this.mRlType.setOnClickListener(this);
        this.mBtnSave.setOnClickListener(this);
    }

    public void pushFragmentForType() {
        pushSelectFragment(UiUtils.getString(R.string.config_record_schedule_Rt), "SelectFragmentForType", 0, this.mListStrType, this.mListIntType);
    }

    public void setTime(TextView textView) {
        try {
            TimeUtils.setTimePickListener(this.mConfigUi, textView, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-07-06 " + StringUtils.getString(textView)), TimePickerView.Type.HOURS_MINS_SECS, this);
        } catch (ParseException unused) {
        }
    }

    public void saveData() {
        int i = this.mStart;
        int i2 = this.mEnd;
        if (i >= i2) {
            toastSf((int) R.string.time_is_error);
            return;
        }
        AddPlanInterface addPlanInterface = this.mInterface;
        if (addPlanInterface != null) {
            addPlanInterface.savePlan(i, i2, this.mType);
        }
        prePage();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.config_addplan_btn_save) {
            saveData();
        } else if (id != R.id.config_title_btn_back) {
            switch (id) {
                case R.id.config_addplan_rl_endtime /*2131362017*/:
                    setTime(this.mTvEnd);
                    return;
                case R.id.config_addplan_rl_recordtype /*2131362018*/:
                    pushFragmentForType();
                    return;
                case R.id.config_addplan_rl_starttime /*2131362019*/:
                    setTime(this.mTvStart);
                    return;
                default:
                    return;
            }
        } else {
            prePage();
        }
    }

    public void setTimePickListener(TextView textView, Date date) {
        textView.setText(TimeUtils.getTime(date, TimeUtils.HMS));
        String string = StringUtils.getString(this.mTvStart);
        String string2 = StringUtils.getString(this.mTvEnd);
        int parse2Int_86399 = parse2Int_86399(string);
        int parse2Int_863992 = parse2Int_86399(string2);
        if (parse2Int_86399 < parse2Int_863992) {
            this.mStart = parse2Int_86399;
            this.mEnd = parse2Int_863992;
        }
    }

    public void updateDateForType(int i) {
        if (this.mType != i) {
            this.mType = i;
            refreshUi();
        }
    }

    public void saveSelect(String str, List<Integer> list) {
        if (str.equals("SelectFragmentForType") && list.size() > 0) {
            updateDateForType(list.get(0).intValue());
        }
    }
}
