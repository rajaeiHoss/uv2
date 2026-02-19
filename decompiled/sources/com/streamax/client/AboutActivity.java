package com.streamax.client;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.zycs.UView.R;

public class AboutActivity extends Activity {
    public String mAppName;
    public String mAppVersion;
    private Button mBtnExit;
    public View mRootView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = LayoutInflater.from(this).inflate(R.layout.about_page, (ViewGroup) null);
        this.mRootView = inflate;
        setContentView(inflate);
        InitView();
    }

    public void InitView() {
        getAppInfo();
        ((TextView) this.mRootView.findViewById(R.id.about_version_text)).setText(this.mAppName + " " + this.mAppVersion);
        ((TextView) this.mRootView.findViewById(R.id.about_date_text)).setText(R.string.datevalue);
        Button button = (Button) this.mRootView.findViewById(R.id.about_button_exit);
        this.mBtnExit = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AboutActivity.this.restartApp();
            }
        });
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

    public void restartApp() {
        Intent launchIntentForPackage = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        launchIntentForPackage.addFlags(67108864);
        startActivity(launchIntentForPackage);
        Process.killProcess(Process.myPid());
    }
}
