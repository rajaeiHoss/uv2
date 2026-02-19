package com.hjq.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public final class CountdownView extends AppCompatTextView implements Runnable {
    private static final String TIME_UNIT = "S";
    private int mCurrentSecond;
    private CharSequence mRecordText;
    private int mTotalSecond = 60;

    public CountdownView(Context context) {
        super(context);
    }

    public CountdownView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CountdownView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setTotalTime(int i) {
        this.mTotalSecond = i;
    }

    public void start() {
        this.mRecordText = getText();
        setEnabled(false);
        this.mCurrentSecond = this.mTotalSecond;
        post(this);
    }

    public void stop() {
        this.mCurrentSecond = 0;
        setText(this.mRecordText);
        setEnabled(true);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this);
    }

    public void run() {
        int i = this.mCurrentSecond;
        if (i == 0) {
            stop();
            return;
        }
        this.mCurrentSecond = i - 1;
        setText(this.mCurrentSecond + " " + TIME_UNIT);
        postDelayed(this, 1000);
    }
}
