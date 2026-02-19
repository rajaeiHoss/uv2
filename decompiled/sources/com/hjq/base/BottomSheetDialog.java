package com.hjq.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public final class BottomSheetDialog extends BaseDialog implements View.OnTouchListener, View.OnClickListener {
    private final BottomSheetBehavior<FrameLayout> mBottomSheetBehavior;
    /* access modifiers changed from: private */
    public boolean mCancelable;
    private boolean mCanceledOnTouchOutside;
    private boolean mCanceledOnTouchOutsideSet;

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

    public BottomSheetDialog(Context context) {
        this(context, R.style.BaseDialogTheme);
    }

    public BottomSheetDialog(Context context, int i) {
        super(context, i);
        this.mCancelable = true;
        this.mCanceledOnTouchOutside = true;
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = new BottomSheetBehavior<>(getContext(), (AttributeSet) null);
        this.mBottomSheetBehavior = bottomSheetBehavior;
        bottomSheetBehavior.addBottomSheetCallback(new MyBottomSheetCallback());
        bottomSheetBehavior.setHideable(this.mCancelable);
        supportRequestWindowFeature(1);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 2 | 4096);
            window.setLayout(-1, -1);
        }
    }

    public void setContentView(int i) {
        super.setContentView(wrapContentView(getLayoutInflater().inflate(i, (ViewGroup) null, false)));
    }

    public void setContentView(View view) {
        super.setContentView(wrapContentView(view));
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        view.setLayoutParams(layoutParams);
        super.setContentView(wrapContentView(view));
    }

    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.mCancelable != z) {
            this.mCancelable = z;
            BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.mBottomSheetBehavior;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.setHideable(z);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.mBottomSheetBehavior;
        if (bottomSheetBehavior != null && bottomSheetBehavior.getState() == 5) {
            this.mBottomSheetBehavior.setState(4);
        }
    }

    public void cancel() {
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.mBottomSheetBehavior;
        if (bottomSheetBehavior == null || bottomSheetBehavior.getState() == 5) {
            super.cancel();
        } else {
            this.mBottomSheetBehavior.setState(5);
        }
    }

    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.mCancelable) {
            this.mCancelable = true;
        }
        this.mCanceledOnTouchOutside = z;
        this.mCanceledOnTouchOutsideSet = true;
    }

    private boolean shouldWindowCloseOnTouchOutside() {
        if (!this.mCanceledOnTouchOutsideSet) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{16843611});
            this.mCanceledOnTouchOutside = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
            this.mCanceledOnTouchOutsideSet = true;
        }
        return this.mCanceledOnTouchOutside;
    }

    private View wrapContentView(View view) {
        CoordinatorLayout coordinatorLayout = new CoordinatorLayout(getContext());
        coordinatorLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        View view2 = new View(getContext());
        view2.setSoundEffectsEnabled(false);
        view2.setImportantForAccessibility(2);
        view2.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        FrameLayout frameLayout = new FrameLayout(getContext());
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 49;
        layoutParams.setBehavior(this.mBottomSheetBehavior);
        frameLayout.setLayoutParams(layoutParams);
        frameLayout.addView(view);
        coordinatorLayout.addView(view2);
        coordinatorLayout.addView(frameLayout);
        view2.setOnClickListener(this);
        ViewCompat.setAccessibilityDelegate(frameLayout, new BehaviorAccessibilityDelegate());
        frameLayout.setOnTouchListener(this);
        return coordinatorLayout;
    }

    public BottomSheetBehavior<FrameLayout> getBottomSheetBehavior() {
        return this.mBottomSheetBehavior;
    }

    public void onClick(View view) {
        if (this.mCancelable && isShowing() && shouldWindowCloseOnTouchOutside()) {
            cancel();
        }
    }

    private class MyBottomSheetCallback extends BottomSheetBehavior.BottomSheetCallback {
        public void onSlide(View view, float f) {
        }

        private MyBottomSheetCallback() {
        }

        public void onStateChanged(View view, int i) {
            if (i == 5) {
                BottomSheetDialog.this.cancel();
            }
        }
    }

    private class BehaviorAccessibilityDelegate extends AccessibilityDelegateCompat {
        private BehaviorAccessibilityDelegate() {
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (BottomSheetDialog.this.mCancelable) {
                accessibilityNodeInfoCompat.addAction(1048576);
                accessibilityNodeInfoCompat.setDismissable(true);
                return;
            }
            accessibilityNodeInfoCompat.setDismissable(false);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (i != 1048576 || !BottomSheetDialog.this.mCancelable) {
                return super.performAccessibilityAction(view, i, bundle);
            }
            BottomSheetDialog.this.cancel();
            return true;
        }
    }
}
