package com.hjq.base.action;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

public interface HandlerAction {
    public static final Handler HANDLER = new Handler(Looper.getMainLooper());

    Handler getHandler();

    boolean post(Runnable runnable);

    boolean postAtTime(Runnable runnable, long j);

    boolean postDelayed(Runnable runnable, long j);

    void removeCallbacks();

    void removeCallbacks(Runnable runnable);

    /* renamed from: com.hjq.base.action.HandlerAction$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Handler $default$getHandler(HandlerAction _this) {
            return HandlerAction.HANDLER;
        }

        public static boolean $default$post(HandlerAction _this, Runnable runnable) {
            return _this.postDelayed(runnable, 0);
        }

        public static boolean $default$postDelayed(HandlerAction _this, Runnable runnable, long j) {
            if (j < 0) {
                j = 0;
            }
            return _this.postAtTime(runnable, SystemClock.uptimeMillis() + j);
        }

        public static boolean $default$postAtTime(HandlerAction _this, Runnable runnable, long j) {
            return HandlerAction.HANDLER.postAtTime(runnable, _this, j);
        }

        public static void $default$removeCallbacks(HandlerAction _this, Runnable runnable) {
            HandlerAction.HANDLER.removeCallbacks(runnable);
        }

        public static void $default$removeCallbacks(HandlerAction _this) {
            HandlerAction.HANDLER.removeCallbacksAndMessages(_this);
        }
    }
}
