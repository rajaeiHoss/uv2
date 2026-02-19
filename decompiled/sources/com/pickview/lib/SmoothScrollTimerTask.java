package com.pickview.lib;

import java.util.TimerTask;

final class SmoothScrollTimerTask extends TimerTask {
    final WheelView loopView;
    int offset;
    int realOffset = 0;
    int realTotalOffset = Integer.MAX_VALUE;

    SmoothScrollTimerTask(WheelView wheelView, int i) {
        this.loopView = wheelView;
        this.offset = i;
    }

    public final void run() {
        if (this.realTotalOffset == Integer.MAX_VALUE) {
            this.realTotalOffset = this.offset;
        }
        int i = this.realTotalOffset;
        int i2 = (int) (((float) i) * 0.1f);
        this.realOffset = i2;
        if (i2 == 0) {
            if (i < 0) {
                this.realOffset = -1;
            } else {
                this.realOffset = 1;
            }
        }
        if (Math.abs(i) <= 1) {
            this.loopView.cancelFuture();
            this.loopView.handler.sendEmptyMessage(3000);
            return;
        }
        this.loopView.totalScrollY += this.realOffset;
        if (!this.loopView.isLoop) {
            float f = this.loopView.itemHeight;
            float f2 = ((float) (-this.loopView.initPosition)) * f;
            float itemsCount = ((float) ((this.loopView.getItemsCount() - 1) - this.loopView.initPosition)) * f;
            if (((float) this.loopView.totalScrollY) <= f2 || ((float) this.loopView.totalScrollY) >= itemsCount) {
                this.loopView.totalScrollY -= this.realOffset;
                this.loopView.cancelFuture();
                this.loopView.handler.sendEmptyMessage(3000);
                return;
            }
        }
        this.loopView.handler.sendEmptyMessage(1000);
        this.realTotalOffset -= this.realOffset;
    }
}
