package com.hjq.base;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.PopupWindowCompat;
import com.hjq.base.action.ActivityAction;
import com.hjq.base.action.AnimAction;
import com.hjq.base.action.ClickAction;
import com.hjq.base.action.HandlerAction;
import com.hjq.base.action.KeyboardAction;
import com.hjq.base.action.ResourcesAction;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class BasePopupWindow extends PopupWindow implements ActivityAction, HandlerAction, ClickAction, AnimAction, KeyboardAction, PopupWindow.OnDismissListener {
    private final Context mContext;
    private List<OnDismissListener> mDismissListeners;
    private PopupBackground mPopupBackground;
    private List<OnShowListener> mShowListeners;

    public interface OnClickListener<V extends View> {
        void onClick(BasePopupWindow basePopupWindow, V v);
    }

    public interface OnCreateListener {
        void onCreate(BasePopupWindow basePopupWindow);
    }

    public interface OnDismissListener {
        void onDismiss(BasePopupWindow basePopupWindow);
    }

    public interface OnShowListener {
        void onShow(BasePopupWindow basePopupWindow);
    }

    public /* synthetic */ Activity getActivity() {
        return ActivityAction.CC.$default$getActivity(this);
    }

    public /* synthetic */ Handler getHandler() {
        return HandlerAction.CC.$default$getHandler(this);
    }

    public /* synthetic */ void hideKeyboard(View view) {
        KeyboardAction.CC.$default$hideKeyboard(this, view);
    }

    public /* synthetic */ void onClick(View view) {
        ClickAction.CC.$default$onClick(this, view);
    }

    public /* synthetic */ boolean post(Runnable runnable) {
        return HandlerAction.CC.$default$post(this, runnable);
    }

    public /* synthetic */ boolean postAtTime(Runnable runnable, long j) {
        return HandlerAction.CC.$default$postAtTime(this, runnable, j);
    }

    public /* synthetic */ boolean postDelayed(Runnable runnable, long j) {
        return HandlerAction.CC.$default$postDelayed(this, runnable, j);
    }

    public /* synthetic */ void removeCallbacks() {
        HandlerAction.CC.$default$removeCallbacks(this);
    }

    public /* synthetic */ void removeCallbacks(Runnable runnable) {
        HandlerAction.CC.$default$removeCallbacks(this, runnable);
    }

    public /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener, int... iArr) {
        ClickAction.CC.$default$setOnClickListener((ClickAction) this, onClickListener, iArr);
    }

    public /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener, View... viewArr) {
        ClickAction.CC.$default$setOnClickListener((ClickAction) this, onClickListener, viewArr);
    }

    public /* synthetic */ void setOnClickListener(int... iArr) {
        ClickAction.CC.$default$setOnClickListener((ClickAction) this, iArr);
    }

    public /* synthetic */ void setOnClickListener(View... viewArr) {
        ClickAction.CC.$default$setOnClickListener((ClickAction) this, viewArr);
    }

    public /* synthetic */ void showKeyboard(View view) {
        KeyboardAction.CC.$default$showKeyboard(this, view);
    }

    public /* synthetic */ void startActivity(Intent intent) {
        ActivityAction.CC.$default$startActivity((ActivityAction) this, intent);
    }

    public /* synthetic */ void startActivity(Class cls) {
        ActivityAction.CC.$default$startActivity((ActivityAction) this, cls);
    }

    public /* synthetic */ void toggleSoftInput(View view) {
        KeyboardAction.CC.$default$toggleSoftInput(this, view);
    }

    public BasePopupWindow(Context context) {
        super(context);
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        if (onDismissListener != null) {
            addOnDismissListener(new DismissListenerWrapper(onDismissListener));
        }
    }

    public void addOnShowListener(OnShowListener onShowListener) {
        if (this.mShowListeners == null) {
            this.mShowListeners = new ArrayList();
        }
        this.mShowListeners.add(onShowListener);
    }

    public void addOnDismissListener(OnDismissListener onDismissListener) {
        if (this.mDismissListeners == null) {
            this.mDismissListeners = new ArrayList();
            super.setOnDismissListener(this);
        }
        this.mDismissListeners.add(onDismissListener);
    }

    public void removeOnShowListener(OnShowListener onShowListener) {
        List<OnShowListener> list = this.mShowListeners;
        if (list != null) {
            list.remove(onShowListener);
        }
    }

    public void removeOnDismissListener(OnDismissListener onDismissListener) {
        List<OnDismissListener> list = this.mDismissListeners;
        if (list != null) {
            list.remove(onDismissListener);
        }
    }

    /* access modifiers changed from: private */
    public void setOnShowListeners(List<OnShowListener> list) {
        this.mShowListeners = list;
    }

    /* access modifiers changed from: private */
    public void setOnDismissListeners(List<OnDismissListener> list) {
        super.setOnDismissListener(this);
        this.mDismissListeners = list;
    }

    public void onDismiss() {
        List<OnDismissListener> list = this.mDismissListeners;
        if (list != null) {
            for (OnDismissListener onDismiss : list) {
                onDismiss.onDismiss(this);
            }
        }
    }

    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (!isShowing() && getContentView() != null) {
            List<OnShowListener> list = this.mShowListeners;
            if (list != null) {
                for (OnShowListener onShow : list) {
                    onShow.onShow(this);
                }
            }
            super.showAsDropDown(view, i, i2, i3);
        }
    }

    public void showAtLocation(View view, int i, int i2, int i3) {
        if (!isShowing() && getContentView() != null) {
            List<OnShowListener> list = this.mShowListeners;
            if (list != null) {
                for (OnShowListener onShow : list) {
                    onShow.onShow(this);
                }
            }
            super.showAtLocation(view, i, i2, i3);
        }
    }

    public void dismiss() {
        super.dismiss();
        removeCallbacks();
    }

    public <V extends View> V findViewById(int i) {
        return getContentView().findViewById(i);
    }

    public void setWindowLayoutType(int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            super.setWindowLayoutType(i);
        } else {
            PopupWindowCompat.setWindowLayoutType(this, i);
        }
    }

    public int getWindowLayoutType() {
        if (Build.VERSION.SDK_INT >= 23) {
            return super.getWindowLayoutType();
        }
        return PopupWindowCompat.getWindowLayoutType(this);
    }

    public void setOverlapAnchor(boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            super.setOverlapAnchor(z);
        } else {
            PopupWindowCompat.setOverlapAnchor(this, z);
        }
    }

    public void setBackgroundDimAmount(float f) {
        float f2 = 1.0f - f;
        if (isShowing()) {
            setActivityAlpha(f2);
        }
        if (this.mPopupBackground == null && f2 != 1.0f) {
            PopupBackground popupBackground = new PopupBackground();
            this.mPopupBackground = popupBackground;
            addOnShowListener(popupBackground);
            addOnDismissListener(this.mPopupBackground);
        }
        PopupBackground popupBackground2 = this.mPopupBackground;
        if (popupBackground2 != null) {
            popupBackground2.setAlpha(f2);
        }
    }

    /* access modifiers changed from: private */
    public void setActivityAlpha(float f) {
        Activity activity = getActivity();
        if (activity != null) {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{attributes.alpha, f});
            ofFloat.setDuration(300);
            final WindowManager.LayoutParams params = attributes;
            final Activity targetActivity = activity;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BasePopupWindow.lambda$setActivityAlpha$0(params, targetActivity, valueAnimator);
                }
            });
            ofFloat.start();
        }
    }

    static /* synthetic */ void lambda$setActivityAlpha$0(WindowManager.LayoutParams layoutParams, Activity activity, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (floatValue != layoutParams.alpha) {
            layoutParams.alpha = floatValue;
            activity.getWindow().setAttributes(layoutParams);
        }
    }

    public static class Builder<B extends Builder<?>> implements ActivityAction, ResourcesAction, ClickAction, KeyboardAction {
        private static final int DEFAULT_ANCHORED_GRAVITY = 8388659;
        private final Activity mActivity;
        private int mAnimations;
        private float mBackgroundDimAmount;
        private SparseArray<OnClickListener<? extends View>> mClickArray;
        private View mContentView;
        private final Context mContext;
        private OnCreateListener mCreateListener;
        private final List<OnDismissListener> mDismissListeners;
        private boolean mFocusable;
        private int mGravity;
        private int mHeight;
        private boolean mOutsideTouchable;
        private BasePopupWindow mPopupWindow;
        private final List<OnShowListener> mShowListeners;
        private boolean mTouchable;
        private int mWidth;
        private int mXOffset;
        private int mYOffset;

        public /* synthetic */ Activity getActivity() {
            return ActivityAction.CC.$default$getActivity(this);
        }

        public /* synthetic */ int getColor(int i) {
            return ResourcesAction.CC.$default$getColor(this, i);
        }

        public /* synthetic */ Drawable getDrawable(int i) {
            return ResourcesAction.CC.$default$getDrawable(this, i);
        }

        public /* synthetic */ Resources getResources() {
            return ResourcesAction.CC.$default$getResources(this);
        }

        public /* synthetic */ String getString(int i) {
            return ResourcesAction.CC.$default$getString(this, i);
        }

        public /* synthetic */ String getString(int i, Object... objArr) {
            return ResourcesAction.CC.$default$getString(this, i, objArr);
        }

        public /* synthetic */ Object getSystemService(Class cls) {
            return ResourcesAction.CC.$default$getSystemService(this, cls);
        }

        public /* synthetic */ void hideKeyboard(View view) {
            KeyboardAction.CC.$default$hideKeyboard(this, view);
        }

        public /* synthetic */ void onClick(View view) {
            ClickAction.CC.$default$onClick(this, view);
        }

        public /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener, int... iArr) {
            ClickAction.CC.$default$setOnClickListener((ClickAction) this, onClickListener, iArr);
        }

        public /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener, View... viewArr) {
            ClickAction.CC.$default$setOnClickListener((ClickAction) this, onClickListener, viewArr);
        }

        public /* synthetic */ void setOnClickListener(int... iArr) {
            ClickAction.CC.$default$setOnClickListener((ClickAction) this, iArr);
        }

        public /* synthetic */ void setOnClickListener(View... viewArr) {
            ClickAction.CC.$default$setOnClickListener((ClickAction) this, viewArr);
        }

        public /* synthetic */ void showKeyboard(View view) {
            KeyboardAction.CC.$default$showKeyboard(this, view);
        }

        public /* synthetic */ void startActivity(Intent intent) {
            ActivityAction.CC.$default$startActivity((ActivityAction) this, intent);
        }

        public /* synthetic */ void startActivity(Class cls) {
            ActivityAction.CC.$default$startActivity((ActivityAction) this, cls);
        }

        public /* synthetic */ void toggleSoftInput(View view) {
            KeyboardAction.CC.$default$toggleSoftInput(this, view);
        }

        public Builder(Activity activity) {
            this((Context) activity);
        }

        public Builder(Context context) {
            this.mAnimations = -1;
            this.mWidth = -2;
            this.mHeight = -2;
            this.mGravity = 8388659;
            this.mTouchable = true;
            this.mFocusable = true;
            this.mOutsideTouchable = false;
            this.mShowListeners = new ArrayList();
            this.mDismissListeners = new ArrayList();
            this.mContext = context;
            this.mActivity = getActivity();
        }

        @SuppressWarnings("unchecked")
        private B self() {
            return (B) this;
        }

        public B setContentView(int i) {
            return setContentView(LayoutInflater.from(this.mContext).inflate(i, new FrameLayout(this.mContext), false));
        }

        public B setContentView(View view) {
            int i;
            if (view != null) {
                this.mContentView = view;
                if (isCreated()) {
                    this.mPopupWindow.setContentView(view);
                    return self();
                }
                ViewGroup.LayoutParams layoutParams = this.mContentView.getLayoutParams();
                if (layoutParams != null && this.mWidth == -2 && this.mHeight == -2) {
                    setWidth(layoutParams.width);
                    setHeight(layoutParams.height);
                }
                if (this.mGravity == 8388659) {
                    if (layoutParams instanceof FrameLayout.LayoutParams) {
                        int i2 = ((FrameLayout.LayoutParams) layoutParams).gravity;
                        if (i2 != -1) {
                            setGravity(i2);
                        }
                    } else if ((layoutParams instanceof LinearLayout.LayoutParams) && (i = ((LinearLayout.LayoutParams) layoutParams).gravity) != -1) {
                        setGravity(i);
                    }
                    if (this.mGravity == 0) {
                        setGravity(17);
                    }
                }
                return self();
            }
            throw new IllegalArgumentException("are you ok?");
        }

        public B setAnimStyle(int i) {
            this.mAnimations = i;
            if (isCreated()) {
                this.mPopupWindow.setAnimationStyle(i);
            }
            return self();
        }

        public B setWidth(int i) {
            this.mWidth = i;
            if (isCreated()) {
                this.mPopupWindow.setWidth(i);
                return self();
            }
            View view = this.mContentView;
            ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
            if (layoutParams != null) {
                layoutParams.width = i;
                this.mContentView.setLayoutParams(layoutParams);
            }
            return self();
        }

        public B setHeight(int i) {
            this.mHeight = i;
            if (isCreated()) {
                this.mPopupWindow.setHeight(i);
                return self();
            }
            View view = this.mContentView;
            ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
            if (layoutParams != null) {
                layoutParams.height = i;
                this.mContentView.setLayoutParams(layoutParams);
            }
            return self();
        }

        public B setGravity(int i) {
            this.mGravity = Gravity.getAbsoluteGravity(i, getResources().getConfiguration().getLayoutDirection());
            return self();
        }

        public B setXOffset(int i) {
            this.mXOffset = i;
            return self();
        }

        public B setYOffset(int i) {
            this.mYOffset = i;
            return self();
        }

        public B setTouchable(boolean z) {
            this.mTouchable = z;
            if (isCreated()) {
                this.mPopupWindow.setTouchable(z);
            }
            return self();
        }

        public B setFocusable(boolean z) {
            this.mFocusable = z;
            if (isCreated()) {
                this.mPopupWindow.setFocusable(z);
            }
            return self();
        }

        public B setOutsideTouchable(boolean z) {
            this.mOutsideTouchable = z;
            if (isCreated()) {
                this.mPopupWindow.setOutsideTouchable(z);
            }
            return self();
        }

        public B setBackgroundDimAmount(float f) {
            this.mBackgroundDimAmount = f;
            if (isCreated()) {
                this.mPopupWindow.setBackgroundDimAmount(f);
            }
            return self();
        }

        public B setOnCreateListener(OnCreateListener onCreateListener) {
            this.mCreateListener = onCreateListener;
            return self();
        }

        public B addOnShowListener(OnShowListener onShowListener) {
            this.mShowListeners.add(onShowListener);
            return self();
        }

        public B addOnDismissListener(OnDismissListener onDismissListener) {
            this.mDismissListeners.add(onDismissListener);
            return self();
        }

        public B setText(int i, int i2) {
            return setText(i, (CharSequence) getString(i2));
        }

        public B setText(int i, CharSequence charSequence) {
            ((TextView) findViewById(i)).setText(charSequence);
            return self();
        }

        public B setTextColor(int i, int i2) {
            ((TextView) findViewById(i)).setTextColor(i2);
            return self();
        }

        public B setHint(int i, int i2) {
            return setHint(i, (CharSequence) getString(i2));
        }

        public B setHint(int i, CharSequence charSequence) {
            ((TextView) findViewById(i)).setHint(charSequence);
            return self();
        }

        public B setVisibility(int i, int i2) {
            findViewById(i).setVisibility(i2);
            return self();
        }

        public B setBackground(int i, int i2) {
            return setBackground(i, ContextCompat.getDrawable(this.mContext, i2));
        }

        public B setBackground(int i, Drawable drawable) {
            findViewById(i).setBackground(drawable);
            return self();
        }

        public B setImageDrawable(int i, int i2) {
            return setBackground(i, ContextCompat.getDrawable(this.mContext, i2));
        }

        public B setImageDrawable(int i, Drawable drawable) {
            ((ImageView) findViewById(i)).setImageDrawable(drawable);
            return self();
        }

        public B setOnClickListener(int i, OnClickListener<?> onClickListener) {
            View findViewById;
            if (this.mClickArray == null) {
                this.mClickArray = new SparseArray<>();
            }
            this.mClickArray.put(i, onClickListener);
            if (isCreated() && (findViewById = this.mPopupWindow.findViewById(i)) != null) {
                findViewById.setOnClickListener(new ViewClickWrapper(this.mPopupWindow, onClickListener));
            }
            return self();
        }

        public BasePopupWindow create() {
            if (this.mContentView != null) {
                if (isShowing()) {
                    dismiss();
                }
                if (this.mGravity == 8388659) {
                    this.mGravity = 17;
                }
                if (this.mAnimations == -1) {
                    int i = this.mGravity;
                    if (i == 3) {
                        this.mAnimations = BasePopupWindow.ANIM_LEFT;
                    } else if (i == 5) {
                        this.mAnimations = BasePopupWindow.ANIM_RIGHT;
                    } else if (i == 48) {
                        this.mAnimations = BasePopupWindow.ANIM_TOP;
                    } else if (i != 80) {
                        this.mAnimations = -1;
                    } else {
                        this.mAnimations = BasePopupWindow.ANIM_BOTTOM;
                    }
                }
                BasePopupWindow createPopupWindow = createPopupWindow(this.mContext);
                this.mPopupWindow = createPopupWindow;
                createPopupWindow.setContentView(this.mContentView);
                this.mPopupWindow.setWidth(this.mWidth);
                this.mPopupWindow.setHeight(this.mHeight);
                this.mPopupWindow.setAnimationStyle(this.mAnimations);
                this.mPopupWindow.setFocusable(this.mFocusable);
                this.mPopupWindow.setTouchable(this.mTouchable);
                this.mPopupWindow.setOutsideTouchable(this.mOutsideTouchable);
                int i2 = 0;
                this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
                this.mPopupWindow.setOnShowListeners(this.mShowListeners);
                this.mPopupWindow.setOnDismissListeners(this.mDismissListeners);
                this.mPopupWindow.setBackgroundDimAmount(this.mBackgroundDimAmount);
                SparseArray<OnClickListener<? extends View>> sparseArray = this.mClickArray;
                while (sparseArray != null && i2 < sparseArray.size()) {
                    View findViewById = this.mContentView.findViewById(this.mClickArray.keyAt(i2));
                    if (findViewById != null) {
                        findViewById.setOnClickListener(new ViewClickWrapper(this.mPopupWindow, this.mClickArray.valueAt(i2)));
                    }
                    i2++;
                }
                Activity activity2 = this.mActivity;
                if (activity2 != null) {
                    PopupWindowLifecycle.with(activity2, this.mPopupWindow);
                }
                OnCreateListener onCreateListener = this.mCreateListener;
                if (onCreateListener != null) {
                    onCreateListener.onCreate(this.mPopupWindow);
                }
                return this.mPopupWindow;
            }
            throw new IllegalArgumentException("are you ok?");
        }

        public void showAsDropDown(View view) {
            Activity activity = this.mActivity;
            if (activity != null && !activity.isFinishing() && !this.mActivity.isDestroyed()) {
                if (!isCreated()) {
                    create();
                }
                this.mPopupWindow.showAsDropDown(view, this.mXOffset, this.mYOffset, this.mGravity);
            }
        }

        public void showAtLocation(View view) {
            Activity activity = this.mActivity;
            if (activity != null && !activity.isFinishing() && !this.mActivity.isDestroyed()) {
                if (!isCreated()) {
                    create();
                }
                this.mPopupWindow.showAtLocation(view, this.mGravity, this.mXOffset, this.mYOffset);
            }
        }

        public Context getContext() {
            return this.mContext;
        }

        public boolean isCreated() {
            return this.mPopupWindow != null;
        }

        public boolean isShowing() {
            return isCreated() && this.mPopupWindow.isShowing();
        }

        public void dismiss() {
            BasePopupWindow basePopupWindow;
            Activity activity = this.mActivity;
            if (activity != null && !activity.isFinishing() && !this.mActivity.isDestroyed() && (basePopupWindow = this.mPopupWindow) != null) {
                basePopupWindow.dismiss();
            }
        }

        /* access modifiers changed from: protected */
        public BasePopupWindow createPopupWindow(Context context) {
            return new BasePopupWindow(context);
        }

        public View getContentView() {
            return this.mContentView;
        }

        public <V extends View> V findViewById(int i) {
            View view = this.mContentView;
            if (view != null) {
                return view.findViewById(i);
            }
            throw new IllegalStateException("are you ok?");
        }

        public BasePopupWindow getPopupWindow() {
            return this.mPopupWindow;
        }

        public final void post(Runnable runnable) {
            if (isShowing()) {
                this.mPopupWindow.post(runnable);
            } else {
                addOnShowListener(new ShowPostWrapper(runnable));
            }
        }

        public final void postDelayed(Runnable runnable, long j) {
            if (isShowing()) {
                this.mPopupWindow.postDelayed(runnable, j);
            } else {
                addOnShowListener(new ShowPostDelayedWrapper(runnable, j));
            }
        }

        public final void postAtTime(Runnable runnable, long j) {
            if (isShowing()) {
                this.mPopupWindow.postAtTime(runnable, j);
            } else {
                addOnShowListener(new ShowPostAtTimeWrapper(runnable, j));
            }
        }
    }

    private static final class PopupWindowLifecycle implements Application.ActivityLifecycleCallbacks, OnShowListener, OnDismissListener {
        private Activity mActivity;
        private BasePopupWindow mPopupWindow;

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        /* access modifiers changed from: private */
        public static void with(Activity activity, BasePopupWindow basePopupWindow) {
            new PopupWindowLifecycle(activity, basePopupWindow);
        }

        private PopupWindowLifecycle(Activity activity, BasePopupWindow basePopupWindow) {
            this.mActivity = activity;
            basePopupWindow.addOnShowListener(this);
            basePopupWindow.addOnDismissListener(this);
        }

        public void onActivityDestroyed(Activity activity) {
            if (this.mActivity == activity) {
                unregisterActivityLifecycleCallbacks();
                this.mActivity = null;
                BasePopupWindow basePopupWindow = this.mPopupWindow;
                if (basePopupWindow != null) {
                    basePopupWindow.removeOnShowListener(this);
                    this.mPopupWindow.removeOnDismissListener(this);
                    if (this.mPopupWindow.isShowing()) {
                        this.mPopupWindow.dismiss();
                    }
                    this.mPopupWindow = null;
                }
            }
        }

        public void onShow(BasePopupWindow basePopupWindow) {
            this.mPopupWindow = basePopupWindow;
            registerActivityLifecycleCallbacks();
        }

        public void onDismiss(BasePopupWindow basePopupWindow) {
            this.mPopupWindow = null;
            unregisterActivityLifecycleCallbacks();
        }

        private void registerActivityLifecycleCallbacks() {
            if (this.mActivity != null) {
                if (Build.VERSION.SDK_INT >= 29) {
                    this.mActivity.registerActivityLifecycleCallbacks(this);
                } else {
                    this.mActivity.getApplication().registerActivityLifecycleCallbacks(this);
                }
            }
        }

        private void unregisterActivityLifecycleCallbacks() {
            if (this.mActivity != null) {
                if (Build.VERSION.SDK_INT >= 29) {
                    this.mActivity.unregisterActivityLifecycleCallbacks(this);
                } else {
                    this.mActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
                }
            }
        }
    }

    private static class PopupBackground implements OnShowListener, OnDismissListener {
        private float mAlpha;

        private PopupBackground() {
        }

        /* access modifiers changed from: private */
        public void setAlpha(float f) {
            this.mAlpha = f;
        }

        public void onShow(BasePopupWindow basePopupWindow) {
            basePopupWindow.setActivityAlpha(this.mAlpha);
        }

        public void onDismiss(BasePopupWindow basePopupWindow) {
            basePopupWindow.setActivityAlpha(1.0f);
        }
    }

    private static final class DismissListenerWrapper extends SoftReference<PopupWindow.OnDismissListener> implements OnDismissListener {
        private DismissListenerWrapper(PopupWindow.OnDismissListener onDismissListener) {
            super(onDismissListener);
        }

        public void onDismiss(BasePopupWindow basePopupWindow) {
            if (get() != null) {
                ((PopupWindow.OnDismissListener) get()).onDismiss();
            }
        }
    }

    private static final class ViewClickWrapper implements View.OnClickListener {
        private final BasePopupWindow mBasePopupWindow;
        private final OnClickListener mListener;

        private ViewClickWrapper(BasePopupWindow basePopupWindow, OnClickListener onClickListener) {
            this.mBasePopupWindow = basePopupWindow;
            this.mListener = onClickListener;
        }

        public final void onClick(View view) {
            OnClickListener onClickListener = this.mListener;
            if (onClickListener != null) {
                onClickListener.onClick(this.mBasePopupWindow, view);
            }
        }
    }

    private static final class ShowPostWrapper implements OnShowListener {
        private final Runnable mRunnable;

        private ShowPostWrapper(Runnable runnable) {
            this.mRunnable = runnable;
        }

        public void onShow(BasePopupWindow basePopupWindow) {
            if (this.mRunnable != null) {
                basePopupWindow.removeOnShowListener(this);
                basePopupWindow.post(this.mRunnable);
            }
        }
    }

    private static final class ShowPostDelayedWrapper implements OnShowListener {
        private final long mDelayMillis;
        private final Runnable mRunnable;

        private ShowPostDelayedWrapper(Runnable runnable, long j) {
            this.mRunnable = runnable;
            this.mDelayMillis = j;
        }

        public void onShow(BasePopupWindow basePopupWindow) {
            if (this.mRunnable != null) {
                basePopupWindow.removeOnShowListener(this);
                basePopupWindow.postDelayed(this.mRunnable, this.mDelayMillis);
            }
        }
    }

    private static final class ShowPostAtTimeWrapper implements OnShowListener {
        private final Runnable mRunnable;
        private final long mUptimeMillis;

        private ShowPostAtTimeWrapper(Runnable runnable, long j) {
            this.mRunnable = runnable;
            this.mUptimeMillis = j;
        }

        public void onShow(BasePopupWindow basePopupWindow) {
            if (this.mRunnable != null) {
                basePopupWindow.removeOnShowListener(this);
                basePopupWindow.postAtTime(this.mRunnable, this.mUptimeMillis);
            }
        }
    }
}
