package com.pickview.lib;

final class OnItemSelectedRunnable implements Runnable {
    final WheelView loopView;

    OnItemSelectedRunnable(WheelView wheelView) {
        this.loopView = wheelView;
    }

    public final void run() {
        this.loopView.onItemSelectedListener.onItemSelected(this.loopView.getCurrentItem());
    }
}
