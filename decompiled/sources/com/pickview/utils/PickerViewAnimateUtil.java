package com.pickview.utils;

import com.zycs.UView.R;

public class PickerViewAnimateUtil {
    private static final int INVALID = -1;

    public static int getAnimationResource(int i, boolean z) {
        if (i != 80) {
            return -1;
        }
        return z ? R.anim.slide_in_bottom : R.anim.slide_out_bottom;
    }
}
