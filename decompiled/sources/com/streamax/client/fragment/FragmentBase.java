package com.streamax.client.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.hjq.toast.ToastUtils;
import com.streamax.client.MyApp;
import com.streamax.client.PlayActivity;
import com.streamax.config.fragment.SelectInterface;
import com.streamax.utils.AppProxy;
import com.streamax.utils.StringUtils;
import com.streamax.utils.UiUtils;
import com.streamax.view.VsTextView;
import com.zycs.UView.R;
import java.util.List;

public class FragmentBase extends Fragment implements SelectInterface {
    protected MyApp mApp;
    protected Button mBtnBack;
    protected Button mBtnUpdate;
    protected View mChildView;
    protected Context mContext;
    /* access modifiers changed from: protected */
    public LayoutInflater mInflater;
    public FragmentBase mPopFragment;
    protected VsTextView mTvTitle;

    /* access modifiers changed from: protected */
    public void init() {
    }

    /* access modifiers changed from: protected */
    public void initData() {
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
    }

    /* access modifiers changed from: protected */
    public void initTitleView() {
    }

    /* access modifiers changed from: protected */
    public View initView() {
        return null;
    }

    public void saveSelect(String str, List<Integer> list) {
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

    /* access modifiers changed from: protected */
    public List<Integer> getIntDatas(int i) {
        return StringUtils.getIntDatas(i);
    }

    public void pushSelectFragment(String str, String str2, int i, List<String> list, List<Integer> list2) {
        FragmentSelect fragmentSelect = new FragmentSelect();
        fragmentSelect.mPopFragment = this;
        fragmentSelect.SetSelectInterface(this);
        fragmentSelect.mFragTitle = str;
        fragmentSelect.mFragName = str2;
        fragmentSelect.SetTextViewData(UiUtils.getString(R.string.config_All));
        fragmentSelect.SetListViewData(list);
        fragmentSelect.SetListViewSelect(list2);
        fragmentSelect.mCheckBox = i;
        ((PlayActivity) getActivity()).refreshPager(fragmentSelect);
    }

    /* access modifiers changed from: protected */
    public void prePage() {
        if (this.mPopFragment != null) {
            ((PlayActivity) getActivity()).refreshPager(this.mPopFragment);
            this.mPopFragment = null;
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = AppProxy.getContext();
        init();
        initTitleView();
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
    public void toastSf(int i) {
        ToastUtils.show((CharSequence) StringUtils.getString(Integer.valueOf(i)));
    }

    /* access modifiers changed from: protected */
    public void toastSf(String str) {
        ToastUtils.show((CharSequence) str);
    }
}
