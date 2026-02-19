package com.pickview.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.pickview.listener.OnDismissListener;
import com.pickview.utils.PickerViewAnimateUtil;
import com.zycs.UView.R;

public class BasePickerView {
    protected ViewGroup contentContainer;
    private Context context;
    /* access modifiers changed from: private */
    public ViewGroup decorView;
    private int gravity = 80;
    private Animation inAnim;
    /* access modifiers changed from: private */
    public boolean isDismissing;
    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0) {
                return false;
            }
            BasePickerView.this.dismiss();
            return false;
        }
    };
    /* access modifiers changed from: private */
    public OnDismissListener onDismissListener;
    private Animation outAnim;
    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -2, 80);
    /* access modifiers changed from: private */
    public ViewGroup rootView;

    /* access modifiers changed from: protected */
    public void initEvents() {
    }

    public BasePickerView(Context context2) {
        this.context = context2;
        initViews();
        init();
        initEvents();
    }

    /* access modifiers changed from: protected */
    public void initViews() {
        LayoutInflater from = LayoutInflater.from(this.context);
        ViewGroup viewGroup = (ViewGroup) ((Activity) this.context).getWindow().getDecorView().findViewById(16908290);
        this.decorView = viewGroup;
        ViewGroup viewGroup2 = (ViewGroup) from.inflate(R.layout.layout_basepickerview, viewGroup, false);
        this.rootView = viewGroup2;
        viewGroup2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        ViewGroup viewGroup3 = (ViewGroup) this.rootView.findViewById(R.id.content_container);
        this.contentContainer = viewGroup3;
        viewGroup3.setLayoutParams(this.params);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.inAnim = getInAnimation();
        this.outAnim = getOutAnimation();
    }

    private void onAttached(View view) {
        this.decorView.addView(view);
        this.contentContainer.startAnimation(this.inAnim);
    }

    public void show() {
        if (!isShowing()) {
            onAttached(this.rootView);
        }
    }

    public boolean isShowing() {
        return this.decorView.findViewById(R.id.outmost_container) != null;
    }

    public void dismiss() {
        if (!this.isDismissing) {
            this.outAnim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    BasePickerView.this.decorView.post(new Runnable() {
                        public void run() {
                            BasePickerView.this.decorView.removeView(BasePickerView.this.rootView);
                            boolean unused = BasePickerView.this.isDismissing = false;
                            if (BasePickerView.this.onDismissListener != null) {
                                BasePickerView.this.onDismissListener.onDismiss(BasePickerView.this);
                            }
                        }
                    });
                }
            });
            this.contentContainer.startAnimation(this.outAnim);
            this.isDismissing = true;
        }
    }

    public Animation getInAnimation() {
        return AnimationUtils.loadAnimation(this.context, PickerViewAnimateUtil.getAnimationResource(this.gravity, true));
    }

    public Animation getOutAnimation() {
        return AnimationUtils.loadAnimation(this.context, PickerViewAnimateUtil.getAnimationResource(this.gravity, false));
    }

    public BasePickerView setOnDismissListener(OnDismissListener onDismissListener2) {
        this.onDismissListener = onDismissListener2;
        return this;
    }

    public BasePickerView setCancelable(boolean z) {
        View findViewById = this.rootView.findViewById(R.id.outmost_container);
        if (z) {
            findViewById.setOnTouchListener(this.onCancelableTouchListener);
        } else {
            findViewById.setOnTouchListener((View.OnTouchListener) null);
        }
        return this;
    }

    public View findViewById(int i) {
        return this.contentContainer.findViewById(i);
    }
}
