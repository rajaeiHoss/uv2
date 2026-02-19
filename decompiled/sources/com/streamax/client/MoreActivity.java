package com.streamax.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.streamax.Configs;
import com.streamax.adapter.SuperBaseAdapter;
import com.streamax.utils.SpUtils;
import com.streamax.utils.StringUtils;
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;

public class MoreActivity extends Activity implements View.OnClickListener {
    public static final String URL = "file:///android_asset/help/help_en.html";
    public static int serverTypeItem;
    public View mAboutView;
    /* access modifiers changed from: private */
    public ServerTypeAdapter mAdapter;
    public MyApp mApp;
    public String mAppName;
    public String mAppVersion;
    public ToggleButton mBtnAutoLogin;
    public ToggleButton mBtnAutoplay;
    private Button mBtnBack;
    public ToggleButton mBtnOnlyWIFI;
    public ToggleButton mBtnSingle;
    public ToggleButton mBtnSmartStream;
    private Button mBtnSmartStreamBack;
    public View mBusyView;
    public Context mContext;
    public View mHelpView;
    /* access modifiers changed from: private */
    public ImageView mIvSmartStreamSwitch;
    private LinearLayout mLlLoginType;
    private RelativeLayout mLlSmartStream;
    public View mLoginTypeView;
    private ListView mLvServerType;
    /* access modifiers changed from: private */
    public ListView mLvSmartStream;
    public View.OnClickListener mMoreClickListener;
    public View mMoreView;
    public View.OnClickListener mOnClickListener;
    public boolean mOption;
    public PushDeviceList mPushConfigList;
    public View mPushView;
    public SeekBar mSeekBar;
    public SegmentedRadioGroup mSegmentEndTime;
    public SegmentedRadioGroup mSegmentLiveMode;
    public SegmentedRadioGroup mSegmentStartTime;
    public SegmentedRadioGroup mSegmentStreamType;
    /* access modifiers changed from: private */
    public List<String> mServerTypeDatas = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> mSmartDatas;
    /* access modifiers changed from: private */
    public View mSmartStreamTypeView;
    public View mSystemView;
    public boolean mTracking = false;
    public TextView mTvAbout;
    public TextView mTvHelp;
    public TextView mTvPush;
    /* access modifiers changed from: private */
    public TextView mTvServerType;
    /* access modifiers changed from: private */
    public TextView mTvSmartStream;
    private TextView mTvSmartStreamText;
    public TextView mTvSystem;
    public TextView mTvUserManager;
    public View mUserView;
    private View mViewLoginType;
    public WebView mWebView;
    public DbHelper mdbHelper;
    public TextView mtvPushID;
    public TextView mtvPushServer;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mApp = (MyApp) getApplication();
        initDatas();
        this.mSmartDatas = StringUtils.getStrDatas(R.array.SmartStreamType);
        this.mContext = this;
        this.mdbHelper = new DbHelper(this, DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.mMoreView = LayoutInflater.from(this).inflate(R.layout.morepage, (ViewGroup) null);
        FindViews();
        setContentView(this.mAboutView);
    }

    public void initDatas() {
        if (MyApp.ServerHostNameDatas.size() > 0) {
            for (int i = 0; i < MyApp.ServerHostNameDatas.size(); i++) {
                if (i > 0) {
                    this.mServerTypeDatas.add(MyApp.ServerHostNameDatas.get(i));
                    serverTypeItem = this.mServerTypeDatas.indexOf(MyApp.LastServerHostName);
                }
            }
        }
    }

    public void initMoreView(View view) {
        this.mTvSystem = (TextView) view.findViewById(R.id.more_tv_system);
        this.mTvUserManager = (TextView) view.findViewById(R.id.more_tv_userManage);
        this.mTvPush = (TextView) view.findViewById(R.id.more_tv_push);
        this.mTvHelp = (TextView) view.findViewById(R.id.more_tv_help);
        this.mTvAbout = (TextView) view.findViewById(R.id.more_tv_about);
    }

    public void initSystemView(View view) {
        this.mTvSmartStream = (TextView) view.findViewById(R.id.systemsetting_smartstream_text);
        this.mViewLoginType = view.findViewById(R.id.systemSetting_view_loginType);
        this.mLlLoginType = (LinearLayout) view.findViewById(R.id.systemSetting_ll_loginType);
        this.mTvServerType = (TextView) view.findViewById(R.id.systemSetting_tv_loginType);
        if (MyApp.smartStreamStatus == 0) {
            this.mTvSmartStream.setText(R.string.smartStream_Close);
        } else if (MyApp.smartStreamStatus == 1) {
            this.mTvSmartStream.setText(StringUtils.getStrDatas(R.array.SmartStreamType).get(MyApp.singleStreamType));
        }
        this.mTvServerType.setText(MyApp.LastServerHostName);
        this.mLlLoginType.setOnClickListener(this);
        if (MyApp.loginType != 0) {
            this.mViewLoginType.setVisibility(8);
            this.mLlLoginType.setVisibility(8);
        }
        RelativeLayout relativeLayout = (RelativeLayout) this.mSystemView.findViewById(R.id.systemsetting_rl_smartstream);
        this.mLlSmartStream = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MoreActivity moreActivity = MoreActivity.this;
                moreActivity.setContentView(moreActivity.mSmartStreamTypeView);
                if (MyApp.smartStreamStatus == 0) {
                    MoreActivity.this.mLvSmartStream.setVisibility(8);
                } else if (MyApp.smartStreamStatus == 1) {
                    MoreActivity.this.mLvSmartStream.setVisibility(0);
                }
            }
        });
    }

    public void initServerTypeView(View view) {
        this.mBtnBack = (Button) view.findViewById(R.id.loginType_title_button_back);
        ListView listView = (ListView) view.findViewById(R.id.loginType_lv);
        this.mLvServerType = listView;
        ServerTypeAdapter serverTypeAdapter = new ServerTypeAdapter(this.mServerTypeDatas);
        this.mAdapter = serverTypeAdapter;
        listView.setAdapter(serverTypeAdapter);
        this.mBtnBack.setOnClickListener(this);
        this.mLvServerType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                MoreActivity.this.mAdapter.notifyDataSetChanged();
                List access$300 = MoreActivity.this.mServerTypeDatas;
                MoreActivity.serverTypeItem = i;
                MyApp.LastServerHostName = (String) access$300.get(i);
                MoreActivity.this.mTvServerType.setText(MyApp.LastServerHostName);
                MoreActivity.this.mtvPushServer.setText(MyApp.LastServerHostName);
                SpUtils.putString(Configs.Key.LastServerHostName, MyApp.LastServerHostName);
                MyApp myApp = MoreActivity.this.mApp;
                MyApp.setServerHostNames();
                MoreActivity moreActivity = MoreActivity.this;
                moreActivity.setContentView(moreActivity.mSystemView);
            }
        });
    }

    public void initSmartStreamTypeView(View view) {
        this.mLvSmartStream = (ListView) this.mSmartStreamTypeView.findViewById(R.id.smartstream_lv);
        ImageView imageView = (ImageView) this.mSmartStreamTypeView.findViewById(R.id.smartstream_iv_switch);
        this.mIvSmartStreamSwitch = imageView;
        imageView.setImageResource(MyApp.smartStreamStatus == 1 ? R.drawable.switch_open : R.drawable.switch_close);
        this.mBtnSmartStreamBack = (Button) this.mSmartStreamTypeView.findViewById(R.id.smartstream_title_btn_back);
        TextView textView = (TextView) this.mSmartStreamTypeView.findViewById(R.id.smartstream_title_tv_text);
        this.mTvSmartStreamText = textView;
        textView.setText(R.string.config_SmartStream);
        this.mLvSmartStream.setAdapter(new SmartAdapter(this.mSmartDatas));
        this.mBtnSmartStreamBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MoreActivity moreActivity = MoreActivity.this;
                moreActivity.setContentView(moreActivity.mSystemView);
                if (MyApp.smartStreamStatus != 0 && MyApp.smartStreamStatus == 1) {
                    MoreActivity.this.mTvSmartStream.setText(StringUtils.getStrDatas(R.array.SmartStreamType).get(MyApp.singleStreamType));
                }
            }
        });
        this.mIvSmartStreamSwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyApp.smartStreamStatus == 0) {
                    MyApp.smartStreamStatus = 1;
                    MoreActivity.this.mLvSmartStream.setVisibility(0);
                    MoreActivity.this.mIvSmartStreamSwitch.setImageResource(R.drawable.switch_open);
                } else if (MyApp.smartStreamStatus == 1) {
                    MyApp.smartStreamStatus = 0;
                    MoreActivity.this.mLvSmartStream.setVisibility(8);
                    MoreActivity.this.mIvSmartStreamSwitch.setImageResource(R.drawable.switch_close);
                }
            }
        });
    }

    public void FindViews() {
        initMoreView(this.mMoreView);
        this.mSystemView = LayoutInflater.from(this).inflate(R.layout.systemsetting, (ViewGroup) null);
        this.mAboutView = LayoutInflater.from(this).inflate(R.layout.about, (ViewGroup) null);
        this.mUserView = LayoutInflater.from(this).inflate(R.layout.usermanger, (ViewGroup) null);
        this.mHelpView = LayoutInflater.from(this).inflate(R.layout.help, (ViewGroup) null);
        this.mPushView = LayoutInflater.from(this).inflate(R.layout.push, (ViewGroup) null);
        this.mLoginTypeView = LayoutInflater.from(this).inflate(R.layout.view_logintype, (ViewGroup) null);
        initSystemView(this.mSystemView);
        initServerTypeView(this.mLoginTypeView);
        View inflate = LayoutInflater.from(this).inflate(R.layout.view_smartstream, (ViewGroup) null);
        this.mSmartStreamTypeView = inflate;
        initSmartStreamTypeView(inflate);
        this.mBusyView = this.mPushView.findViewById(R.id.pushconfig_busy);
        ((TextView) this.mPushView.findViewById(R.id.pushconfig_closeall)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new AlertDialog.Builder(MoreActivity.this.mContext).setTitle(MoreActivity.this.mContext.getString(R.string.app_name)).setIcon(MoreActivity.this.mContext.getResources().getDrawable(R.drawable.icon)).setMessage(MoreActivity.this.mContext.getString(R.string.isclose)).setPositiveButton(MoreActivity.this.mContext.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MoreActivity.this.mPushConfigList.UnregisterPushServiceAll();
                    }
                }).setNegativeButton(MoreActivity.this.mContext.getString(R.string.cancel), (DialogInterface.OnClickListener) null).show();
            }
        });
        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.more_tv_about /*2131362666*/:
                        MoreActivity moreActivity = MoreActivity.this;
                        moreActivity.setContentView(moreActivity.mAboutView);
                        return;
                    case R.id.more_tv_help /*2131362667*/:
                        MoreActivity moreActivity2 = MoreActivity.this;
                        moreActivity2.setContentView(moreActivity2.mHelpView);
                        return;
                    case R.id.more_tv_push /*2131362668*/:
                        if (MyApp.loginType == 0) {
                            MoreActivity.this.mPushConfigList.SetData(MoreActivity.this.mdbHelper.getlist());
                        } else {
                            MoreActivity.this.mPushConfigList.SetData(MoreActivity.this.mApp.mWebService.GetTerminalInfoAndroid(true));
                        }
                        MoreActivity.this.mPushConfigList.mPushDeviceAdapter.notifyDataSetChanged();
                        MoreActivity moreActivity3 = MoreActivity.this;
                        moreActivity3.setContentView(moreActivity3.mPushView);
                        return;
                    case R.id.more_tv_system /*2131362669*/:
                        MoreActivity moreActivity4 = MoreActivity.this;
                        moreActivity4.setContentView(moreActivity4.mSystemView);
                        return;
                    case R.id.more_tv_userManage /*2131362671*/:
                        MoreActivity moreActivity5 = MoreActivity.this;
                        moreActivity5.setContentView(moreActivity5.mUserView);
                        return;
                    default:
                        return;
                }
            }
        };
        this.mOnClickListener = clickListener;
        this.mTvSystem.setOnClickListener(clickListener);
        this.mTvAbout.setOnClickListener(this.mOnClickListener);
        this.mTvUserManager.setOnClickListener(this.mOnClickListener);
        this.mTvHelp.setOnClickListener(this.mOnClickListener);
        this.mTvPush.setOnClickListener(this.mOnClickListener);
        this.mMoreClickListener = new View.OnClickListener() {
            public void onClick(View view) {
                MoreActivity moreActivity = MoreActivity.this;
                moreActivity.setContentView(moreActivity.mMoreView);
            }
        };
        this.mUserView.findViewById(R.id.usermanager_title_button_logout).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MoreActivity.this.startActivity(new Intent(MoreActivity.this.mApp.mainActivity, LoginActivity.class));
                MoreActivity.this.mApp.mainActivity.finish();
            }
        });
        this.mSystemView.findViewById(R.id.system_title_button_more).setOnClickListener(this.mMoreClickListener);
        this.mAboutView.findViewById(R.id.about_title_button_more).setOnClickListener(this.mMoreClickListener);
        this.mUserView.findViewById(R.id.usermanager_title_button_more).setOnClickListener(this.mMoreClickListener);
        this.mHelpView.findViewById(R.id.help_title_button).setOnClickListener(this.mMoreClickListener);
        this.mPushView.findViewById(R.id.help_title_button_more).setOnClickListener(this.mMoreClickListener);
        this.mPushView.findViewById(R.id.help_title_button_test).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmackCcsClient.test();
            }
        });
        SeekBar seekBar = (SeekBar) this.mSystemView.findViewById(R.id.systemsetting_ptzspeed_progressbar);
        this.mSeekBar = seekBar;
        seekBar.setProgress(this.mApp.mPtzSpeed);
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                MoreActivity.this.mTracking = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                MoreActivity.this.mApp.mPtzSpeed = seekBar.getProgress();
                MoreActivity.this.mTracking = false;
            }
        });
        WebView webView = (WebView) this.mHelpView.findViewById(R.id.helpwebview);
        this.mWebView = webView;
        webView.setHorizontalScrollBarEnabled(false);
        this.mWebView.setHorizontalScrollbarOverlay(false);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(MoreActivity.URL);
                return false;
            }
        });
        this.mWebView.loadUrl(URL);
        ToggleButton toggleButton = (ToggleButton) this.mUserView.findViewById(R.id.usermanager_remember_radio_button);
        this.mBtnAutoLogin = toggleButton;
        toggleButton.setChecked(this.mApp.readuser());
        this.mBtnAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MyApp.autoLogin = z;
                SpUtils.putBoolean(Configs.Key.AutoLogin, z);
            }
        });
        ToggleButton toggleButton2 = (ToggleButton) this.mSystemView.findViewById(R.id.systemsetting_wifi_radio);
        this.mBtnOnlyWIFI = toggleButton2;
        toggleButton2.setChecked(MyApp.wifiStatus);
        this.mBtnOnlyWIFI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MyApp.wifiStatus = z;
                SpUtils.putBoolean(Configs.Key.WifiStatus, z);
            }
        });
        ToggleButton toggleButton3 = (ToggleButton) this.mSystemView.findViewById(R.id.systemsetting_autoplay_radio);
        this.mBtnAutoplay = toggleButton3;
        toggleButton3.setChecked(this.mApp.mbAutoPlay);
        this.mBtnAutoplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MoreActivity.this.mApp.mbAutoPlay = z;
            }
        });
        this.mSegmentLiveMode = (SegmentedRadioGroup) this.mSystemView.findViewById(R.id.livemode_segment_text);
        if (this.mApp.mbSingle) {
            this.mSegmentLiveMode.check(R.id.singlemode_text);
        } else {
            this.mSegmentLiveMode.check(R.id.multimode_text);
        }
        this.mSegmentLiveMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.multimode_text) {
                    MoreActivity.this.mApp.mbSingle = false;
                } else if (i == R.id.singlemode_text) {
                    MoreActivity.this.mApp.mbSingle = true;
                }
            }
        });
        this.mSegmentStreamType = (SegmentedRadioGroup) this.mSystemView.findViewById(R.id.streamtype_segment_text);
        if (this.mApp.mStreamType == 1) {
            this.mSegmentStreamType.check(R.id.mainstream_text);
        } else {
            this.mSegmentStreamType.check(R.id.substream_text);
        }
        this.mSegmentStreamType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.mainstream_text) {
                    MoreActivity.this.mApp.mStreamType = 1;
                } else if (i == R.id.substream_text) {
                    MoreActivity.this.mApp.mStreamType = 0;
                }
            }
        });
        this.mSegmentStartTime = (SegmentedRadioGroup) this.mSystemView.findViewById(R.id.starttime_segment_text);
        if (this.mApp.mStartTime == 0) {
            this.mSegmentStartTime.check(R.id.second30start_text);
        } else if (this.mApp.mStartTime == 1) {
            this.mSegmentStartTime.check(R.id.second60start_text);
        } else if (this.mApp.mStartTime == 2) {
            this.mSegmentStartTime.check(R.id.second120start_text);
        } else if (this.mApp.mStartTime == 3) {
            this.mSegmentStartTime.check(R.id.second180start_text);
        }
        this.mSegmentStartTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.second30start_text) {
                    MoreActivity.this.mApp.mStartTime = 0;
                } else if (i == R.id.second60start_text) {
                    MoreActivity.this.mApp.mStartTime = 1;
                } else if (i == R.id.second120start_text) {
                    MoreActivity.this.mApp.mStartTime = 2;
                } else if (i == R.id.second180start_text) {
                    MoreActivity.this.mApp.mStartTime = 3;
                }
            }
        });
        this.mSegmentEndTime = (SegmentedRadioGroup) this.mSystemView.findViewById(R.id.endtime_segment_text);
        if (this.mApp.mEndTime == 0) {
            this.mSegmentEndTime.check(R.id.second30end_text);
        } else if (this.mApp.mEndTime == 1) {
            this.mSegmentEndTime.check(R.id.second60end_text);
        } else if (this.mApp.mEndTime == 2) {
            this.mSegmentEndTime.check(R.id.second120end_text);
        } else if (this.mApp.mEndTime == 3) {
            this.mSegmentEndTime.check(R.id.second180end_text);
        }
        this.mSegmentEndTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.second30end_text) {
                    MoreActivity.this.mApp.mEndTime = 0;
                } else if (i == R.id.second60end_text) {
                    MoreActivity.this.mApp.mEndTime = 1;
                } else if (i == R.id.second120end_text) {
                    MoreActivity.this.mApp.mEndTime = 2;
                } else if (i == R.id.second180end_text) {
                    MoreActivity.this.mApp.mEndTime = 3;
                }
            }
        });
        getAppInfo();
        ((TextView) this.mAboutView.findViewById(R.id.about_version_text)).setText(this.mAppName + " " + this.mAppVersion);
        ((TextView) this.mAboutView.findViewById(R.id.about_date_text)).setText(R.string.datevalue);
        this.mtvPushID = (TextView) this.mAboutView.findViewById(R.id.about_pushid_text);
        TextView textView = (TextView) this.mAboutView.findViewById(R.id.about_pushserver_text);
        this.mtvPushServer = textView;
        textView.setText(MyApp.LastServerHostName);
        PushDeviceList pushDeviceList = (PushDeviceList) this.mPushView.findViewById(R.id.pushconfigdevlistview);
        this.mPushConfigList = pushDeviceList;
        pushDeviceList.setActivity(this);
        if (MyApp.loginType == 0) {
            this.mPushConfigList.SetData(this.mdbHelper.getlist());
        } else {
            this.mPushConfigList.SetData(this.mApp.mWebService.GetTerminalInfoAndroid(true));
        }
    }

    public void getAppInfo() {
        PackageInfo packageInfo;
        try {
            packageInfo = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        this.mAppName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
        this.mAppVersion = packageInfo.versionName;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public class SmartAdapter extends SuperBaseAdapter<String> {
        public SmartAdapter(List<String> list) {
            super(list);
        }

        /* access modifiers changed from: protected */
        public View initConvertView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(MoreActivity.this.mContext, R.layout.smartstream_lv_item, (ViewGroup) null);
                new ViewHolder(view);
            }
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (MyApp.singleStreamType == i) {
                viewHolder.mIv.setVisibility(0);
            } else {
                viewHolder.mIv.setVisibility(8);
            }
            viewHolder.mTv.setText((CharSequence) MoreActivity.this.mSmartDatas.get(i));
            viewHolder.mTv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MyApp.singleStreamType = i;
                    SmartAdapter.this.notifyDataSetChanged();
                    MoreActivity.this.setContentView(MoreActivity.this.mSystemView);
                    if (MyApp.smartStreamStatus != 0 && MyApp.smartStreamStatus == 1) {
                        MoreActivity.this.mTvSmartStream.setText(StringUtils.getStrDatas(R.array.SmartStreamType).get(i));
                    }
                }
            });
            return view;
        }

        public class ViewHolder {
            ImageView mIv;
            TextView mTv;

            public ViewHolder(View view) {
                this.mTv = (TextView) view.findViewById(R.id.smartstream_lv_item_tv);
                this.mIv = (ImageView) view.findViewById(R.id.smartstream_lv_item_iv);
                view.setTag(this);
            }
        }
    }

    public class ServerTypeAdapter extends SuperBaseAdapter<String> {
        public ServerTypeAdapter(List<String> list) {
            super(list);
        }

        /* access modifiers changed from: protected */
        public View initConvertView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(MoreActivity.this.mContext, R.layout.lv_item_logintype, (ViewGroup) null);
                new ViewHolder(view);
            }
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (MoreActivity.serverTypeItem == i) {
                viewHolder.mIv.setVisibility(0);
            } else {
                viewHolder.mIv.setVisibility(8);
            }
            viewHolder.mTv.setText((CharSequence) this.mDataSource.get(i));
            return view;
        }

        public class ViewHolder {
            ImageView mIv;
            RelativeLayout mRl;
            TextView mTv;

            public ViewHolder(View view) {
                this.mRl = (RelativeLayout) view.findViewById(R.id.loginType_lv_item_rl);
                this.mTv = (TextView) view.findViewById(R.id.loginType_lv_item_tv);
                this.mIv = (ImageView) view.findViewById(R.id.loginType_lv_item_iv);
                view.setTag(this);
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.loginType_title_button_back) {
            setContentView(this.mSystemView);
        } else if (id == R.id.systemSetting_ll_loginType) {
            setContentView(this.mLoginTypeView);
        }
    }
}
