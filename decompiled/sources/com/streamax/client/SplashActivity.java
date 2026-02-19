package com.streamax.client;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import com.zycs.UView.R;

public class SplashActivity extends Activity {
    public MyApp mApp;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ShortcutUtil.createShortCut(this, R.drawable.icon, R.string.app_name);
        this.mApp = (MyApp) getApplication();
        if (Build.VERSION.SDK_INT >= 9) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        }
        onNewIntent(getIntent());
        ((NotificationManager) getSystemService("notification")).cancelAll();
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.splash);
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        int i = 2500;
        if (this.mApp.mPushInfo != null) {
            i = 50;
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (!SplashActivity.this.mApp.readuser()) {
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, Login2Activity.class));
                    SplashActivity.this.finish();
                } else if (MyApp.loginType == 0) {
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    SplashActivity.this.finish();
                } else {
                    WebService webservice = new WebService(MyApp.LastServerHostName, MyApp.username, MyApp.password);
                    if (webservice.Login() > 0) {
                        SplashActivity.this.mApp.mWebService = webservice;
                        SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        SplashActivity.this.finish();
                        return;
                    }
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    SplashActivity.this.finish();
                }
            }
        }, (long) i);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        if (intent == null) {
            super.onNewIntent(intent);
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            super.onNewIntent(intent);
            return;
        }
        int i = extras.getInt("alarmtype");
        String string = extras.getString(CommonUtilities.EXTRA_MESSAGE);
        if (string != null) {
            this.mApp.mPushInfo = new PushInfo(string, i);
            Log.e("mPushInfo", "" + this.mApp.mPushInfo);
            super.onNewIntent(intent);
            if (string != null) {
                super.onNewIntent(intent);
            }
        }
    }
}
