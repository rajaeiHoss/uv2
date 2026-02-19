package com.pickview.lib;

import java.util.TimerTask;

final class InertiaTimerTask extends TimerTask {
    float a = 2.14748365E9f;
    final WheelView loopView;
    final float velocityY;

    InertiaTimerTask(WheelView wheelView, float f) {
        this.loopView = wheelView;
        this.velocityY = f;
    }

    public final void run() {
        if (this.a == 2.14748365E9f) {
            if (Math.abs(this.velocityY) <= 2000.0f) {
                this.a = this.velocityY;
            } else if (this.velocityY > 0.0f) {
                this.a = 2000.0f;
            } else {
                this.a = -2000.0f;
            }
        }
        if (Math.abs(this.a) < 0.0f || Math.abs(this.a) > 20.0f) {
            int i = (int) ((this.a * 10.0f) / 1000.0f);
            this.loopView.totalScrollY -= i;
            if (!this.loopView.isLoop) {
                float f = this.loopView.itemHeight;
                float f2 = ((float) (-this.loopView.initPosition)) * f;
                float itemsCount = ((float) ((this.loopView.getItemsCount() - 1) - this.loopView.initPosition)) * f;
                double d = ((double) f) * 0.3d;
                if (((double) this.loopView.totalScrollY) - d < ((double) f2)) {
                    f2 = (float) (this.loopView.totalScrollY + i);
                } else if (((double) this.loopView.totalScrollY) + d > ((double) itemsCount)) {
                    itemsCount = (float) (this.loopView.totalScrollY + i);
                }
                if (((float) this.loopView.totalScrollY) <= f2) {
                    this.a = 40.0f;
                    this.loopView.totalScrollY = (int) f2;
                } else if (((float) this.loopView.totalScrollY) >= itemsCount) {
                    this.loopView.totalScrollY = (int) itemsCount;
                    this.a = -40.0f;
                }
            }
            float f3 = this.a;
            if (f3 < 0.0f) {
                this.a = f3 + 20.0f;
            } else {
                this.a = f3 - 20.0f;
            }
            this.loopView.handler.sendEmptyMessage(1000);
            return;
        }
        this.loopView.cancelFuture();
        this.loopView.handler.sendEmptyMessage(2000);
    }
}
