package com.streamax.config.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.dvr.calendar.DayStyle;
import com.dvr.net.DvrNet;
import com.hjq.toast.ToastUtils;
import com.streamax.client.MyApp;
import com.streamax.config.ConfigUi;
import com.streamax.config.bean.FragmentBean;
import com.streamax.config.fragment.OneNumberEditFragment;
import com.streamax.config.fragment.OneNumberEditInterface;
import com.streamax.config.fragment.SelectFragment;
import com.streamax.config.fragment.SelectInterface;
import com.streamax.config.utils.FragmentUtils;
import com.streamax.utils.SingleFragment;
import com.streamax.utils.StringUtils;
import com.streamax.utils.UiUtils;
import com.streamax.view.VsEditView;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ConfigFragment extends BaseFragment implements SelectInterface, OneNumberEditInterface {
    public static int curChannel;
    public static int currentDay;
    public static int currentPlay;
    public static String deviceIp;
    public static DvrNet dvrNet;
    public static FragmentBean fragmentBean;
    public static LinkedList<FragmentBean> fragmentPop = new LinkedList<>();
    public static int fragmentTag;
    public static int itemPosition = -1;
    public static PopupWindow mPopupWindow;
    public static String title;
    public static String username;
    protected Button mBtnSave;
    protected ConfigFragment mConfigFragment;
    private LinearLayout mContentLl;
    public String mIpString;
    public OneNumberEditFragment mOneNumberEditFragment;
    public String mOperateMaskStr;
    private View mPopView;
    public View mRootView;
    public SelectFragment mSelectFragment;
    public SingleFragment mSingleFragment;
    private TextView mSureTv;

    public void getConfig() {
    }

    /* access modifiers changed from: protected */
    public void init() {
    }

    public void saveOneNumberEdit(String str, int i) {
    }

    public void saveSelect(String str, List<Integer> list) {
    }

    public void setConfig() {
    }

    public void showErrorFragment() {
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        this.mConfigUi = (ConfigUi) getActivity();
        this.mApp = (MyApp) this.mConfigUi.getApplication();
        this.mTvTitle = (VsTextView) this.mConfigUi.findViewById(R.id.config_title_tv_name);
        this.mBtnBack = (Button) this.mConfigUi.findViewById(R.id.config_title_btn_back);
        this.mBtnUpdate = (Button) this.mConfigUi.findViewById(R.id.config_title_btn_update);
        initPop();
        createDialog(this.mContext, this.mConfigUi);
    }

    /* access modifiers changed from: protected */
    public void toastSuccess() {
        toastSf((int) R.string.success);
    }

    /* access modifiers changed from: protected */
    public void toastFailure() {
        toastSf((int) R.string.failure);
    }

    /* access modifiers changed from: protected */
    public void toastSf(String str) {
        ToastUtils.show((CharSequence) str);
    }

    /* access modifiers changed from: protected */
    public void toastSf(int i) {
        ToastUtils.show((CharSequence) StringUtils.getString(Integer.valueOf(i)));
    }

    /* access modifiers changed from: protected */
    public void prePage() {
        FragmentBean pop = fragmentPop.pop();
        fragmentBean = pop;
        FragmentUtils.showAndRemove(this, pop.mFragment);
    }

    /* access modifiers changed from: protected */
    public void nextPage(BaseFragment baseFragment) {
        fragmentPop.push(new FragmentBean(getString(this.mTvTitle), this));
        FragmentUtils.showAndHide(this, baseFragment);
    }

    /* access modifiers changed from: protected */
    public void nextPage(BaseFragment baseFragment, int i) {
        fragmentPop.push(new FragmentBean(getString(this.mTvTitle), this, i));
        FragmentUtils.showAndHide(this, baseFragment);
    }

    public void pushSingleFragment(SingleFragment singleFragment) {
        fragmentPop.push(new FragmentBean(getString(this.mTvTitle), this));
        FragmentUtils.showAndHide(this, singleFragment);
    }

    public void popSingleFragment(SingleFragment singleFragment) {
        fragmentPop.push(new FragmentBean(getString(this.mTvTitle), this));
        FragmentUtils.showAndHide(this, singleFragment);
    }

    public void pushFragment(BaseFragment baseFragment) {
        fragmentPop.push(new FragmentBean(getString(this.mTvTitle), this));
        FragmentUtils.showAndHide(this, baseFragment);
    }

    public void popFragment(BaseFragment baseFragment) {
        fragmentPop.push(new FragmentBean(getString(this.mTvTitle), this));
        FragmentUtils.showAndHide(this, baseFragment);
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            showUnHiddenView();
        }
    }

    public JSONObject getJSONObject(JSONObject jSONObject) {
        try {
            return new JSONObject(jSONObject.toString());
        } catch (JSONException unused) {
            return jSONObject;
        }
    }

    public void onPause() {
        super.onPause();
        title = getString(this.mTvTitle);
    }

    public void showUnHiddenView() {
        this.mBtnBack.setOnClickListener(this);
        this.mBtnBack.setVisibility(fragmentBean.mVisible);
        this.mTvTitle.setText(fragmentBean.mTitle);
    }

    public void pushSelectFragment(String str, String str2, int i, List<String> list, List<Integer> list2) {
        SelectFragment selectFragment = new SelectFragment();
        this.mSelectFragment = selectFragment;
        selectFragment.mPopFragment = this;
        this.mSelectFragment.SetSelectInterface(this);
        this.mSelectFragment.mFragTitle = str;
        this.mSelectFragment.mFragName = str2;
        this.mSelectFragment.SetTextViewData(UiUtils.getString(R.string.config_All));
        this.mSelectFragment.SetListViewData(list);
        this.mSelectFragment.SetListViewSelect(list2);
        this.mSelectFragment.mCheckBox = i;
        pushFragment(this.mSelectFragment);
    }

    public void pushOneNumberEditFragment(String str, String str2, int i, int i2, int i3) {
        OneNumberEditFragment oneNumberEditFragment = new OneNumberEditFragment();
        this.mOneNumberEditFragment = oneNumberEditFragment;
        oneNumberEditFragment.mPopFragment = this;
        this.mOneNumberEditFragment.SetOneNumberEditInterface(this);
        this.mOneNumberEditFragment.mFragTitle = str;
        this.mOneNumberEditFragment.mFragName = str2;
        this.mOneNumberEditFragment.curValue = i;
        this.mOneNumberEditFragment.minValue = i2;
        this.mOneNumberEditFragment.maxValue = i3;
        pushFragment(this.mOneNumberEditFragment);
    }

    public void setTvEnableAndContent(VsEditView vsEditView, int i, String str) {
        vsEditView.SetText((CharSequence) str).SetEnable(i != 1).SetTextColor(i == 1 ? DayStyle.iColorBkg : ViewCompat.MEASURED_STATE_MASK);
    }

    public void setTvEnable(VsEditView vsEditView, int i) {
        vsEditView.SetEnable(i != 1).SetTextColor(i == 1 ? DayStyle.iColorBkg : ViewCompat.MEASURED_STATE_MASK);
    }

    public void initPop() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.authority_dialog, (ViewGroup) null);
        this.mPopView = inflate;
        this.mContentLl = (LinearLayout) inflate.findViewById(R.id.dialog_content_ll);
        this.mSureTv = (TextView) this.mPopView.findViewById(R.id.authority_dialog_ok);
    }

    public void createDialog(Context context, Activity activity) {
        PopupWindow popupWindow = new PopupWindow(context);
        mPopupWindow = popupWindow;
        popupWindow.setContentView(this.mPopView);
        mPopupWindow.setWidth(-1);
        mPopupWindow.setHeight(-1);
        MyApp myApp = this.mApp;
        this.mContentLl.setLayoutParams(new LinearLayout.LayoutParams((MyApp.getScreenWidth() * 8) / 10, -2));
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(90000000));
        mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        mPopupWindow.update();
        this.mSureTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConfigFragment.mPopupWindow.dismiss();
            }
        });
    }

    public void showPopupWindow() {
        mPopupWindow.showAtLocation(this.mConfigUi.getWindow().getDecorView(), 17, 0, 0);
    }
}
