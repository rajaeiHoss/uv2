package com.pickview.lib;

import android.os.Handler;
import android.os.Message;
import com.pickview.lib.WheelView;

final class MessageHandler extends Handler {
    public static final int WHAT_INVALIDATE_LOOP_VIEW = 1000;
    public static final int WHAT_ITEM_SELECTED = 3000;
    public static final int WHAT_SMOOTH_SCROLL = 2000;
    final WheelView loopview;

    MessageHandler(WheelView wheelView) {
        this.loopview = wheelView;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1000) {
            this.loopview.invalidate();
        } else if (i == 2000) {
            this.loopview.smoothScroll(WheelView.ACTION.FLING);
        } else if (i == 3000) {
            this.loopview.onItemSelected();
        }
    }
}
