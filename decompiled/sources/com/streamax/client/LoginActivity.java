package com.streamax.client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.Zxing.app.CaptureActivity;
import com.streamax.Configs;
import com.streamax.client.ui.Constants;
import com.streamax.config.base.BaseUi;
import com.streamax.utils.SpUtils;
import com.streamax.utils.ToastSf;
import com.zycs.UView.R;
import de.greenrobot.event.EventBus;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends BaseUi {
    public boolean bConfiguration;
    public MyApp mApp;
    public DbHelper mDbHelper;
    public TextView mEtPassword;
    public TextView mEtServerIP;
    public TextView mEtUsername;
    public Button mLoginButton;
    public Button mLoginTypeButton;
    public String mOldServerType;
    public int mOldServerTypePosition;
    public ProgressBar mProgressBar;
    public ArrayAdapter<String> mServerAdpter;
    /* access modifiers changed from: private */
    public SharedPreferences mSp;
    public ImageView mivQR;
    public int mnLoginResult;
    public ToggleButton mtbRemmber;
    public Spinner mySpinner;

    /* access modifiers changed from: protected */
    public void initData() {
    }

    /* access modifiers changed from: protected */
    public void initEvent() {
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mDbHelper = new DbHelper(this.mContext, DbHelper.DATABASENAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.mSp = this.mContext.getSharedPreferences(Constants.SpName, 0);
        this.mApp = (MyApp) getApplication();
        EventBus.getDefault().register(this);
    }

    /* access modifiers changed from: protected */
    public void initView() {
        if (Build.VERSION.SDK_INT >= 9) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        }
        setContentView((int) R.layout.login);
        findViews();
    }

    public void findViews() {
        this.mEtUsername = (TextView) findViewById(R.id.login_username_edit);
        this.mEtPassword = (TextView) findViewById(R.id.login_password_edit);
        TextView textView = (TextView) findViewById(R.id.login_serverip_edit);
        this.mEtServerIP = textView;
        textView.setText(MyApp.LastServerHostName);
        this.mEtServerIP.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence text, int start, int before, int count) {
                if (text != null) {
                    MyApp.ServerHostNameDatas.set(MyApp.loginType, text.toString());
                    LoginActivity.this.mLoginTypeButton.setText(text.toString());
                }
            }
        });
        Button button = (Button) findViewById(R.id.Login_type_button);
        this.mLoginTypeButton = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LoginTypeUi.class);
                intent.putExtra("position", MyApp.ServerHostNameDatas.indexOf(LoginActivity.this.mLoginTypeButton.getText().toString().trim()));
                LoginActivity.this.startActivity(intent);
            }
        });
        this.mtbRemmber = (ToggleButton) findViewById(R.id.login_remember_toggleButton);
        ImageView imageView = (ImageView) findViewById(R.id.login_username_qr);
        this.mivQR = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, CaptureActivity.class);
                LoginActivity.this.startActivityForResult(intent, 0);
            }
        });
        this.mProgressBar = (ProgressBar) findViewById(R.id.loginprogress);
        Button button2 = (Button) findViewById(R.id.login_control_login);
        this.mLoginButton = button2;
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyApp.loginType == 0) {
                    SpUtils.putInt(Configs.Key.LoginType, MyApp.loginType);
                    SharedPreferences.Editor edit = LoginActivity.this.mSp.edit();
                    edit.putString("dbAccount", "admin");
                    edit.putString("dbPwd", "");
                    edit.commit();
                    LoginActivity.this.mApp.writeuser(LoginActivity.this.mtbRemmber.isChecked(), 0, "", "", "");
                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    LoginActivity.this.finish();
                    return;
                }
                final String serverUrl = LoginActivity.this.mEtServerIP.getText().toString();
                final String username = LoginActivity.this.mEtUsername.getText().toString();
                final String password = LoginActivity.this.mEtPassword.getText().toString();
                MyApp.username = username;
                MyApp.password = password;
                SharedPreferences.Editor edit2 = LoginActivity.this.mSp.edit();
                edit2.putString("dbAccount", username);
                edit2.putString("dbPwd", password);
                edit2.commit();
                final WebService webService = new WebService(serverUrl, username, password);
                LoginActivity.this.mtbRemmber.setVisibility(8);
                LoginActivity.this.mProgressBar.setVisibility(0);
                LoginActivity.this.mtbRemmber.setVisibility(0);
                new Thread(new Runnable() {
                    public void run() {
                        if (!LoginActivity.this.TestUrl(serverUrl)) {
                            LoginActivity.this.mHandler.post(new Runnable() {
                                public void run() {
                                    LoginActivity.this.mtbRemmber.setVisibility(8);
                                    LoginActivity.this.mProgressBar.setVisibility(8);
                                    LoginActivity.this.mtbRemmber.setVisibility(0);
                                }
                            });
                            return;
                        }
                        LoginActivity.this.mnLoginResult = webService.Login();
                        if (LoginActivity.this.mnLoginResult > 0) {
                            SpUtils.putInt(Configs.Key.LoginType, MyApp.loginType);
                            MyApp.setServerHostNames();
                            LoginActivity.this.mApp.mWebService = webService;
                            if (LoginActivity.this.mtbRemmber.isChecked()) {
                                LoginActivity.this.mApp.writeuser(true, MyApp.loginType, serverUrl, username, password);
                            } else {
                                LoginActivity.this.mApp.writeuser(false, MyApp.loginType, serverUrl, "", "");
                            }
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            LoginActivity.this.finish();
                        } else if (LoginActivity.this.mnLoginResult == 0) {
                            LoginActivity.this.mHandler.post(new Runnable() {
                                public void run() {
                                    ToastSf.toastSf((Activity) LoginActivity.this, LoginActivity.this.mResources.getString(R.string.serverconnectionerror));
                                    LoginActivity.this.mtbRemmber.setVisibility(8);
                                    LoginActivity.this.mProgressBar.setVisibility(8);
                                    LoginActivity.this.mtbRemmber.setVisibility(0);
                                }
                            });
                        } else {
                            LoginActivity.this.mHandler.post(new Runnable() {
                                public void run() {
                                    ToastSf.toastSf((Activity) LoginActivity.this, LoginActivity.this.mResources.getString(R.string.usernameorpassworderror));
                                    LoginActivity.this.mtbRemmber.setVisibility(8);
                                    LoginActivity.this.mProgressBar.setVisibility(8);
                                    LoginActivity.this.mtbRemmber.setVisibility(0);
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        if (this.mApp.mbRemember) {
            if (MyApp.loginType == 0) {
                this.mLoginTypeButton.setText(MyApp.ServerHostNameDatas.get(MyApp.loginType));
            } else {
                this.mLoginTypeButton.setText(MyApp.LastServerHostName);
            }
            this.mEtUsername.setText(MyApp.username);
            this.mEtPassword.setText(MyApp.password);
            this.mtbRemmber.setChecked(this.mApp.mbRemember);
        } else {
            MyApp.loginType = 0;
            this.mLoginTypeButton.setText(MyApp.ServerHostNameDatas.get(MyApp.loginType));
        }
        setVisible();
        if (this.mApp.mPushInfo != null) {
            this.mLoginButton.performClick();
            if (!isFinishing()) {
                toastSf((int) R.string.loginprompt);
            }
        }
    }

    public boolean TestUrl(String serverUrl) {
        if (!serverUrl.contains("http://")) {
            serverUrl = "http://" + serverUrl;
        }
        try {
            new URL(serverUrl).openStream().close();
            return true;
        } catch (MalformedURLException malformedUrlException) {
            malformedUrlException.printStackTrace();
            return false;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == -1) {
            this.mEtUsername.setText(data.getStringExtra("RESULT"));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            this.bConfiguration = true;
        } else if (configuration.orientation == 1) {
            this.bConfiguration = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEventMainThread(LoginTypeEvent loginTypeEvent) {
        MyApp.loginType = loginTypeEvent.mLoginType;
        this.mEtServerIP.setText(MyApp.ServerHostNameDatas.get(MyApp.loginType));
        this.mLoginTypeButton.setText(MyApp.ServerHostNameDatas.get(MyApp.loginType));
        setVisible();
    }

    public void setVisible() {
        if (MyApp.loginType == 0) {
            findViewById(R.id.login_username).setVisibility(8);
            findViewById(R.id.login_password).setVisibility(8);
            findViewById(R.id.login_serverip).setVisibility(8);
            findViewById(R.id.sign_1).setVisibility(8);
            findViewById(R.id.sign_2).setVisibility(8);
            findViewById(R.id.sign_3).setVisibility(8);
            findViewById(R.id.sign_4).setVisibility(8);
            return;
        }
        findViewById(R.id.login_username).setVisibility(0);
        findViewById(R.id.login_password).setVisibility(0);
        findViewById(R.id.login_serverip).setVisibility(0);
        findViewById(R.id.sign_1).setVisibility(0);
        findViewById(R.id.sign_2).setVisibility(0);
        findViewById(R.id.sign_3).setVisibility(0);
        findViewById(R.id.sign_4).setVisibility(0);
    }
}
