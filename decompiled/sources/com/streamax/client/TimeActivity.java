package com.streamax.client;

import android.app.Activity;
import android.os.Bundle;
import com.streamax.TimeBar.TimeBar;

public class TimeActivity extends Activity {
    TimeBar timebar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FindViews();
        setContentView(this.timebar);
    }

    public void FindViews() {
        this.timebar = new TimeBar(this);
    }
}
