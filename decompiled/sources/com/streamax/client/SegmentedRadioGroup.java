package com.streamax.client;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import com.zycs.UView.R;

public class SegmentedRadioGroup extends RadioGroup {
    private static final String TAG = "SegmentedRadioGroup";

    public SegmentedRadioGroup(Context context) {
        super(context);
    }

    public SegmentedRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        changeButtonsImages();
    }

    private void changeButtonsImages() {
        int childCount = super.getChildCount();
        if (childCount > 1) {
            super.getChildAt(0).setBackgroundResource(R.drawable.segment_radio_left);
            int i = 1;
            while (true) {
                int i2 = childCount - 1;
                if (i < i2) {
                    super.getChildAt(i).setBackgroundResource(R.drawable.segment_radio_middle);
                    i++;
                } else {
                    super.getChildAt(i2).setBackgroundResource(R.drawable.segment_radio_right);
                    return;
                }
            }
        } else if (childCount == 1) {
            super.getChildAt(0).setBackgroundResource(R.drawable.segment_button);
        }
    }
}
