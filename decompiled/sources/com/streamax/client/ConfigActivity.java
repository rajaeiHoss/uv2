package com.streamax.client;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.plus.PlusShare;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigActivity extends Activity {
    public static final String TAG = "ConfigActivity";
    public DeviceAdapter mAdapter;
    public MyApp mApp;
    public View mConfig;
    public ConfigPageActivity mConfigPage;
    public List<Map<String, Object>> mData;
    public float mDistance;
    public LayoutInflater mInflater;
    public ListView mListView;
    /* access modifiers changed from: private */
    public DbHelper mdbHelper;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mApp = (MyApp) getApplication();
        this.mInflater = LayoutInflater.from(this);
        this.mdbHelper = new DbHelper(this, DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.mConfigPage = (ConfigPageActivity) this.mInflater.inflate(R.layout.configwebpage, (ViewGroup) null);
        View inflate = this.mInflater.inflate(R.layout.configpage, (ViewGroup) null);
        this.mConfig = inflate;
        setContentView(inflate);
        FindViews();
        this.mConfigPage.FindViews();
        this.mConfigPage.SetActivity(this);
    }

    /* access modifiers changed from: package-private */
    public void FindViews() {
        this.mListView = (ListView) findViewById(R.id.devlistview);
        this.mData = getData();
        DeviceAdapter deviceAdapter = new DeviceAdapter(this);
        this.mAdapter = deviceAdapter;
        this.mListView.setAdapter(deviceAdapter);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                DevInfoBean devInfoBean;
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (MyApp.loginType == 0) {
                    devInfoBean = ConfigActivity.this.mdbHelper.query(viewHolder.id);
                } else {
                    devInfoBean = ConfigActivity.this.mApp.mWebService.query(viewHolder.id);
                }
                Bundle bundle = new Bundle();
                bundle.putString("ip", devInfoBean.mDevIp);
                bundle.putInt("webport", devInfoBean.mWebPort);
                bundle.putInt("mediaport", devInfoBean.mMediaPort);
                bundle.putString("username", devInfoBean.mUsername);
                bundle.putString("password", devInfoBean.mPwd);
                bundle.putString("remark", devInfoBean.mRemark);
                ConfigActivity.this.mConfigPage.SetData(bundle);
                ConfigActivity.this.mConfigPage.setVisibility(0);
                ConfigActivity configActivity = ConfigActivity.this;
                configActivity.setContentView(configActivity.mConfigPage);
            }
        });
        ((Button) this.mConfigPage.findViewById(R.id.configwebpage_title_settings)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConfigActivity configActivity = ConfigActivity.this;
                configActivity.setContentView(configActivity.mConfig);
                ConfigActivity configActivity2 = ConfigActivity.this;
                configActivity2.mData = configActivity2.getData();
                ConfigActivity.this.mAdapter.notifyDataSetChanged();
            }
        });
    }

    /* access modifiers changed from: private */
    public List<Map<String, Object>> getData() {
        List<DevInfoBean> list;
        if (MyApp.loginType == 0) {
            list = this.mdbHelper.getlist();
        } else {
            list = this.mApp.mWebService.GetTerminalInfoAndroid(true);
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            DevInfoBean devInfoBean = list.get(i);
            HashMap hashMap = new HashMap();
            hashMap.put("id", Integer.valueOf(devInfoBean.mDevId));
            hashMap.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, devInfoBean.mDevName);
            hashMap.put("info", String.format("%s:%s,%s:%s", new Object[]{getString(R.string.deviceip), devInfoBean.mDevIp, getString(R.string.mediaport), Integer.valueOf(devInfoBean.mMediaPort).toString()}));
            hashMap.put("img", Integer.valueOf(R.drawable.dvronline));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public final class ViewHolder {
        public ImageView deviceEdit;
        public ImageView deviceLogo;
        public TextView deviceinfo;
        public TextView devicename;
        public int id;
        public ImageView imageDelete;

        public ViewHolder() {
        }
    }

    public class DeviceAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public DeviceAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return ConfigActivity.this.mData.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = this.mInflater.inflate(R.layout.devicemanager, (ViewGroup) null);
                viewHolder.deviceEdit = (ImageView) view2.findViewById(R.id.foredit);
                viewHolder.deviceLogo = (ImageView) view2.findViewById(R.id.img);
                viewHolder.devicename = (TextView) view2.findViewById(R.id.devicename);
                viewHolder.deviceinfo = (TextView) view2.findViewById(R.id.deviceinfo);
                viewHolder.imageDelete = (ImageView) view2.findViewById(R.id.img_delete);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.deviceLogo.setBackgroundResource(((Integer) ConfigActivity.this.mData.get(i).get("img")).intValue());
            viewHolder.devicename.setText((String) ConfigActivity.this.mData.get(i).get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
            viewHolder.deviceinfo.setText((String) ConfigActivity.this.mData.get(i).get("info"));
            viewHolder.id = ((Integer) ConfigActivity.this.mData.get(i).get("id")).intValue();
            viewHolder.deviceEdit.setVisibility(8);
            viewHolder.imageDelete.setVisibility(8);
            return view2;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mdbHelper.close();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.mData = getData();
        this.mAdapter.notifyDataSetChanged();
        super.onResume();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation != 2) {
            int i = configuration.orientation;
        }
    }
}
