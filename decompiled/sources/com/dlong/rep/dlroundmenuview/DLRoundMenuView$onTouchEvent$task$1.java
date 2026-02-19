package com.dlong.rep.dlroundmenuview;

import android.view.MotionEvent;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuListener;
import com.dlong.rep.dlroundmenuview.Interface.OnMenuTouchListener;
import java.util.TimerTask;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/dlong/rep/dlroundmenuview/DLRoundMenuView$onTouchEvent$task$1", "Ljava/util/TimerTask;", "run", "", "dlroundmenuview_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DLRoundMenuView.kt */
public final class DLRoundMenuView$onTouchEvent$task$1 extends TimerTask {
    final /* synthetic */ MotionEvent $event;
    final /* synthetic */ DLRoundMenuView this$0;

    DLRoundMenuView$onTouchEvent$task$1(DLRoundMenuView dLRoundMenuView, MotionEvent motionEvent) {
        this.this$0 = dLRoundMenuView;
        this.$event = motionEvent;
    }

    public void run() {
        OnMenuTouchListener access$getMTouchListener$p = this.this$0.mTouchListener;
        if (access$getMTouchListener$p != null) {
            access$getMTouchListener$p.OnTouch(this.$event, this.this$0.mTouchState);
        }
        OnMenuListener access$getMMenuListener$p = this.this$0.mMenuListener;
        if (access$getMMenuListener$p != null) {
            access$getMMenuListener$p.OnTouch(this.$event, this.this$0.mTouchState);
        }
    }
}
