package com.streamax.config.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.base.CustomBaseAdapter;
import com.streamax.config.network.BaseListener;
import com.streamax.config.network.NetPresenter;
import com.streamax.utils.AppProxy;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemInfoFragment extends ConfigFragment implements BaseListener.GetListener {
    public List<String> mDeviceInfoKey;
    public List<String> mDeviceInfoValue;
    public ListView mLvContent;
    public JSONObject mSystemInfoObj;
    public String mSystemInfoReq;

    public BaseFragment getCurFragment() {
        return this;
    }

    public void getFailure() {
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
    }

    /* access modifiers changed from: protected */
    public void initConfigTitleView() {
        super.initConfigTitleView();
        this.mTvTitle.setText(R.string.config_systemInfo_Title);
        this.mBtnUpdate.setVisibility(8);
        this.mBtnBack.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public View initView() {
        this.mRootView = this.mInflater.inflate(R.layout.config_info_lv, (ViewGroup) null);
        this.mLvContent = (ListView) this.mRootView.findViewById(R.id.config_info_lv_content);
        return this.mRootView;
    }

    /* access modifiers changed from: protected */
    public void initData() {
        getConfig();
    }

    public void getConfig() {
        NetPresenter.getDefault().getConfig(this);
    }

    public void refreshUi() {
        if (this.mSystemInfoObj == null) {
            showErrorFragment();
            return;
        }
        this.mDeviceInfoKey = getStrDatas(R.array.config_systemInfo_InfoDatas);
        this.mDeviceInfoValue = new ArrayList();
        try {
            JSONObject jSONObject = this.mSystemInfoObj.getJSONObject("DEVEMM");
            if (jSONObject == null) {
                showErrorFragment();
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("SSP");
            if (jSONObject2 == null) {
                showErrorFragment();
                return;
            }
            JSONObject jSONObject3 = this.mSystemInfoObj.getJSONObject("NWSM");
            if (jSONObject3 == null) {
                showErrorFragment();
                return;
            }
            String string = jSONObject2.getString("DEVN");
            String string2 = jSONObject2.getString("UID");
            String string3 = jSONObject3.getString("SN");
            String string4 = jSONObject3.getString("FW");
            String string5 = jSONObject3.getString("MAC");
            this.mDeviceInfoValue.add(string);
            this.mDeviceInfoValue.add(string2);
            this.mDeviceInfoValue.add(string3);
            this.mDeviceInfoValue.add(string4);
            this.mDeviceInfoValue.add(string5);
            this.mLvContent.setAdapter(new SystemInfoAdapter(this.mDeviceInfoKey));
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }

    public class SystemInfoAdapter extends CustomBaseAdapter<String> {
        public SystemInfoAdapter(List<String> list) {
            super(list);
        }

        /* access modifiers changed from: protected */
        public View initConvertView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(AppProxy.getContext(), R.layout.config_info_lv_item_information, (ViewGroup) null);
                new ViewHolder(view);
            }
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.mTvDeviceInfoKey.setText((CharSequence) this.mDataSource.get(i));
            viewHolder.mTvDeviceInfoValue.setText(SystemInfoFragment.this.mDeviceInfoValue.get(i));
            return view;
        }

        public class ViewHolder {
            TextView mTvDeviceInfoKey;
            TextView mTvDeviceInfoValue;

            public ViewHolder(View view) {
                this.mTvDeviceInfoKey = (TextView) view.findViewById(R.id.config_info_item_up);
                this.mTvDeviceInfoValue = (TextView) view.findViewById(R.id.config_info_item_down);
                view.setTag(this);
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.config_title_btn_back) {
            prePage();
        }
    }

    public String requestForGetConfig() {
        this.mSystemInfoReq = "";
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("SSP", "?");
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("MAC", "?");
            jSONObject3.put("SN", "?");
            jSONObject3.put("FW", "?");
            jSONObject.put("DEVEMM", jSONObject2);
            jSONObject.put("NWSM", jSONObject3);
            this.mSystemInfoReq = jSONObject.toString();
        } catch (JSONException unused) {
        }
        return this.mSystemInfoReq;
    }

    public void getSuccess(String str) {
        try {
            this.mSystemInfoObj = new JSONObject(str);
            refreshUi();
        } catch (JSONException unused) {
            showErrorFragment();
        }
    }
}
