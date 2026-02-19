package com.streamax.config.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.dvr.net.DvrNet;
import com.google.gson.Gson;
import com.streamax.client.DevInfoBean;
import com.streamax.client.MyApp;
import com.streamax.config.ConfigUi;
import com.streamax.config.db.DeviceInfoDao;
import com.streamax.utils.AppProxy;
import com.streamax.utils.StringUtils;
import com.streamax.utils.TimeUtils;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    public static int currentDeviceId;
    public static DeviceInfoDao deviceInfoDao;
    /* access modifiers changed from: protected */
    public MyApp mApp;
    protected Button mBtnBack;
    protected Button mBtnUpdate;
    protected View mChildView;
    /* access modifiers changed from: protected */
    public ConfigUi mConfigUi;
    /* access modifiers changed from: protected */
    public Context mContext;
    public int mCurCh;
    public DevInfoBean mDevInfoBean;
    protected DvrNet mDvrNet;
    protected Gson mGson;
    /* access modifiers changed from: protected */
    public Handler mHandler;
    protected LayoutInflater mInflater;
    public ProgressDialog mLoadingDialog;
    public BaseFragment mPopFragment;
    protected RadioButton mRbToSaveAndDisplay;
    protected Resources mResources;
    protected VsTextView mTvTitle;

    /* access modifiers changed from: protected */
    public void init() {
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
    }

    /* access modifiers changed from: protected */
    public void initData() {
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
    }

    /* access modifiers changed from: protected */
    public abstract View initView();

    public void showLoading(Activity activity) {
        if (this.mLoadingDialog == null) {
            this.mLoadingDialog = ProgressDialog.show(activity, (CharSequence) null, getString(R.string.PleaseWait), true, false);
        }
        this.mLoadingDialog.show();
    }

    public void showLoading() {
        if (this.mLoadingDialog == null) {
            this.mLoadingDialog = ProgressDialog.show(this.mConfigUi, (CharSequence) null, getString(R.string.PleaseWait), true, false);
        }
        if (!this.mLoadingDialog.isShowing()) {
            this.mLoadingDialog.show();
        }
    }

    public void hideLoading() {
        ProgressDialog progressDialog = this.mLoadingDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = AppProxy.getContext();
        this.mResources = AppProxy.getResources();
        this.mHandler = AppProxy.getHandler();
        this.mGson = new Gson();
        deviceInfoDao = new DeviceInfoDao(this.mContext);
        this.mDevInfoBean = new DevInfoBean();
        init();
        initConfigTitleView();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mInflater = layoutInflater;
        return initView();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initData();
        initEvent();
    }

    /* access modifiers changed from: protected */
    public int getStrLen(Object obj) {
        return StringUtils.getStrLen(obj);
    }

    /* access modifiers changed from: protected */
    public String getString(Object obj) {
        return StringUtils.getString(obj);
    }

    /* access modifiers changed from: protected */
    public Integer parse2Int(Object obj) {
        return Integer.valueOf(StringUtils.parse2Int(obj));
    }

    /* access modifiers changed from: protected */
    public List<String> getStrDatas(int i) {
        return StringUtils.getStrDatas(i);
    }

    public static List<Integer> getIntDatas(int i) {
        return StringUtils.getIntDatas(i);
    }

    public static int getInt(int i) {
        return StringUtils.getInt(i);
    }

    /* access modifiers changed from: protected */
    public Integer str2Int(TextView textView) {
        if (TextUtils.isEmpty(textView.getText().toString().trim())) {
            return -1;
        }
        return Integer.valueOf(textView.getText().toString().trim().replace(":", ""));
    }

    /* access modifiers changed from: protected */
    public String parse2String_86399(int i) {
        try {
            return TimeUtils.parse2String_86399(i);
        } catch (Exception unused) {
            return "00:00:00";
        }
    }

    /* access modifiers changed from: protected */
    public int parse2Int_86399(String str) {
        try {
            return TimeUtils.parse2Int_86399(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public int parse2Int_86399(TextView textView) {
        try {
            return TimeUtils.parse2Int_86399(textView);
        } catch (Exception unused) {
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public String timeLong2String(String str, String str2, long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(str2));
        return simpleDateFormat.format(new Date(j));
    }

    /* access modifiers changed from: protected */
    public String parseTime2String(String str, String str2, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(str2));
        return simpleDateFormat.format(date);
    }

    /* access modifiers changed from: protected */
    public long parseString2Long(String str, String str2, String str3) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(str2));
        try {
            return simpleDateFormat.parse(str3).getTime();
        } catch (ParseException unused) {
            return 0;
        }
    }
}
