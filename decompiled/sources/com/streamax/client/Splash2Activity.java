package com.streamax.client;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import com.airbnb.lottie.LottieAnimationView;
import com.zycs.UView.R;

public class Splash2Activity extends Activity {
    public MyApp mApp;
    /* access modifiers changed from: private */
    public LottieAnimationView mLottieView;

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
        setContentView(R.layout.splash2);
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.lav_splash_lottie);
        this.mLottieView = lottieAnimationView;
        if (true) { // Temporary: bypass splash animation to reach login.
            startActivity(new Intent(this, Login2Activity.class));
            finish();
            return;
        }
        lottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                Splash2Activity.this.mLottieView.removeAnimatorListener(this);
                if (!Splash2Activity.this.mApp.readuser()) {
                    Splash2Activity.this.startActivity(new Intent(Splash2Activity.this, Login2Activity.class));
                    Splash2Activity.this.finish();
                } else if (MyApp.loginType == 0) {
                    Splash2Activity.this.startActivity(new Intent(Splash2Activity.this, MainActivity.class));
                    Splash2Activity.this.finish();
                }
                Splash2Activity.this.finish();
            }
        });
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
