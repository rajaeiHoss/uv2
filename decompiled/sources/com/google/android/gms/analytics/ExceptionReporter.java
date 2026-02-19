package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.internal.zzatc;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Objects;

public class ExceptionReporter implements Thread.UncaughtExceptionHandler {
    private final Context mContext;
    private final Thread.UncaughtExceptionHandler zzduf;
    private final Tracker zzdug;
    private ExceptionParser zzduh;
    private GoogleAnalytics zzdui;

    public ExceptionReporter(Tracker tracker, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        Objects.requireNonNull(tracker, "tracker cannot be null");
        Objects.requireNonNull(context, "context cannot be null");
        this.zzduf = uncaughtExceptionHandler;
        this.zzdug = tracker;
        this.zzduh = new StandardExceptionParser(context, new ArrayList());
        this.mContext = context.getApplicationContext();
        String valueOf = String.valueOf(uncaughtExceptionHandler == null ? "null" : uncaughtExceptionHandler.getClass().getName());
        zzatc.v(valueOf.length() != 0 ? "ExceptionReporter created, original handler is ".concat(valueOf) : new String("ExceptionReporter created, original handler is "));
    }

    public ExceptionParser getExceptionParser() {
        return this.zzduh;
    }

    public void setExceptionParser(ExceptionParser exceptionParser) {
        this.zzduh = exceptionParser;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        String str;
        if (this.zzduh != null) {
            str = this.zzduh.getDescription(thread != null ? thread.getName() : null, th);
        } else {
            str = "UncaughtException";
        }
        String valueOf = String.valueOf(str);
        zzatc.v(valueOf.length() != 0 ? "Reporting uncaught exception: ".concat(valueOf) : new String("Reporting uncaught exception: "));
        this.zzdug.send(new HitBuilders.ExceptionBuilder().setDescription(str).setFatal(true).build());
        if (this.zzdui == null) {
            this.zzdui = GoogleAnalytics.getInstance(this.mContext);
        }
        GoogleAnalytics googleAnalytics = this.zzdui;
        googleAnalytics.dispatchLocalHits();
        googleAnalytics.zzvr().zzyc().zzxt();
        if (this.zzduf != null) {
            zzatc.v("Passing exception to the original handler");
            this.zzduf.uncaughtException(thread, th);
        }
    }

    /* access modifiers changed from: package-private */
    public final Thread.UncaughtExceptionHandler zzvv() {
        return this.zzduf;
    }
}
