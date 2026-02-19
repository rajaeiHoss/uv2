package com.hjq.toast;

import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

final class SafeHandler extends Handler {
    private final Handler mHandler;

    SafeHandler(Handler handler) {
        this.mHandler = handler;
    }

    public void handleMessage(Message message) {
        try {
            this.mHandler.handleMessage(message);
        } catch (WindowManager.BadTokenException | IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
