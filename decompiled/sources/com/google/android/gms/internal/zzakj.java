package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

final class zzakj implements Callable<String> {
    private /* synthetic */ Context val$context;
    private /* synthetic */ Context zzdhv;

    zzakj(zzaki zzaki, Context context, Context context2) {
        this.zzdhv = context;
        this.val$context = context2;
    }

    public final String call() throws Exception {
        SharedPreferences sharedPreferences;
        boolean z = false;
        if (this.zzdhv != null) {
            zzahw.v("Attempting to read user agent from Google Play Services.");
            sharedPreferences = this.zzdhv.getSharedPreferences("admob_user_agent", 0);
        } else {
            zzahw.v("Attempting to read user agent from local cache.");
            sharedPreferences = this.val$context.getSharedPreferences("admob_user_agent", 0);
            z = true;
        }
        String string = sharedPreferences.getString("user_agent", "");
        if (TextUtils.isEmpty(string)) {
            zzahw.v("Reading user agent from WebSettings");
            string = WebSettings.getDefaultUserAgent(this.val$context);
            if (z) {
                sharedPreferences.edit().putString("user_agent", string).apply();
                zzahw.v("Persisting user agent.");
            }
        }
        return string;
    }
}
