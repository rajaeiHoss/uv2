package com.dlong.rep.dlroundmenuview.Interface;

import android.view.MotionEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0007H\u0016J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0007H\u0016J\u001a\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\n\u001a\u00020\u0007H\u0016J)\u0010\u0014\u001a\u00020\u000b2!\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006J)\u0010\u0016\u001a\u00020\u000b2!\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006J@\u0010\u0017\u001a\u00020\u000b28\u0010\u0015\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u000eR)\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006X.¢\u0006\u0002\n\u0000R)\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006X.¢\u0006\u0002\n\u0000R@\u0010\r\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u000eX.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/dlong/rep/dlroundmenuview/Interface/OnMenuListener;", "Lcom/dlong/rep/dlroundmenuview/Interface/OnMenuClickListener;", "Lcom/dlong/rep/dlroundmenuview/Interface/OnMenuLongClickListener;", "Lcom/dlong/rep/dlroundmenuview/Interface/OnMenuTouchListener;", "()V", "clickListener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "position", "", "longClickListener", "touchListener", "Lkotlin/Function2;", "Landroid/view/MotionEvent;", "event", "OnMenuClick", "OnMenuLongClick", "OnTouch", "onMenuClick", "listener", "onMenuLongClick", "onTouch", "dlroundmenuview_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: OnMenuListener.kt */
public final class OnMenuListener implements OnMenuClickListener, OnMenuLongClickListener, OnMenuTouchListener {
    private Function1<? super Integer, Unit> clickListener;
    private Function1<? super Integer, Unit> longClickListener;
    private Function2<? super MotionEvent, ? super Integer, Unit> touchListener;

    public final void onMenuClick(Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "listener");
        this.clickListener = function1;
    }

    public void OnMenuClick(int i) {
        Function1<? super Integer, Unit> function1 = this.clickListener;
        if (function1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clickListener");
        }
        function1.invoke(Integer.valueOf(i));
    }

    public final void onMenuLongClick(Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "listener");
        this.longClickListener = function1;
    }

    public void OnMenuLongClick(int i) {
        Function1<? super Integer, Unit> function1 = this.longClickListener;
        if (function1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("longClickListener");
        }
        function1.invoke(Integer.valueOf(i));
    }

    public final void onTouch(Function2<? super MotionEvent, ? super Integer, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "listener");
        this.touchListener = function2;
    }

    public void OnTouch(MotionEvent motionEvent, int i) {
        Function2<? super MotionEvent, ? super Integer, Unit> function2 = this.touchListener;
        if (function2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("touchListener");
        }
        function2.invoke(motionEvent, Integer.valueOf(i));
    }
}
