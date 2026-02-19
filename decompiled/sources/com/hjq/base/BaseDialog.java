package com.hjq.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.hjq.base.BaseDialog;
import com.hjq.base.action.ActivityAction;
import com.hjq.base.action.AnimAction;
import com.hjq.base.action.ClickAction;
import com.hjq.base.action.HandlerAction;
import com.hjq.base.action.KeyboardAction;
import com.hjq.base.action.ResourcesAction;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class BaseDialog extends AppCompatDialog implements LifecycleOwner, ActivityAction, ResourcesAction, HandlerAction, ClickAction, AnimAction, KeyboardAction, DialogInterface.OnShowListener, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private List<OnCancelListener> mCancelListeners;
    private List<OnDismissListener> mDismissListeners;
    private final LifecycleRegistry mLifecycle;
    private final ListenersWrapper<BaseDialog> mListeners;
    private List<OnShowListener> mShowListeners;

    public interface OnCancelListener {
        void onCancel(BaseDialog baseDialog);
    }

    public interface OnClickListener<V extends View> {
        void onClick(BaseDialog baseDialog, V v);
    }

    public interface OnCreateListener {
        void onCreate(BaseDialog baseDialog);
    }

    public interface OnDismissListener {
        void onDismiss(BaseDialog baseDialog);
    }

    public interface OnKeyListener {
        boolean onKey(BaseDialog baseDialog, KeyEvent keyEvent);
    }

    public interface OnShowListener {
        void onShow(BaseDialog baseDialog);
    }

    public /* synthetic */ Activity getActivity() {
        return ActivityAction.CC.$default$getActivity(this);
    }

    public /* synthetic */ int getColor(int i) {
        return ResourcesAction.CC.$default$getColor(this, i);
    }

    public /* synthetic */ Drawable getDrawable(int i) {
        return ResourcesAction.CC.$default$getDrawable(this, i);
    }

    public /* synthetic */ Handler getHandler() {
        return HandlerAction.CC.$default$getHandler(this);
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

    public BaseDialog(Context context) {
        this(context, R.style.BaseDialogTheme);
    }

    public BaseDialog(Context context, int i) {
        super(context, i);
        this.mListeners = new ListenersWrapper<>(this);
        this.mLifecycle = new LifecycleRegistry(this);
    }

    public View getContentView() {
        View findViewById = findViewById(16908290);
        if (!(findViewById instanceof ViewGroup)) {
            return findViewById;
        }
        ViewGroup viewGroup = (ViewGroup) findViewById;
        return viewGroup.getChildCount() == 1 ? viewGroup.getChildAt(0) : findViewById;
    }

    public void setWidth(int i) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = i;
            window.setAttributes(attributes);
        }
    }

    public void setHeight(int i) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.height = i;
            window.setAttributes(attributes);
        }
    }

    public void setXOffset(int i) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.x = i;
            window.setAttributes(attributes);
        }
    }

    public void setYOffset(int i) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.y = i;
            window.setAttributes(attributes);
        }
    }

    public int getGravity() {
        Window window = getWindow();
        if (window == null) {
            return 0;
        }
        return window.getAttributes().gravity;
    }

    public void setGravity(int i) {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(i);
        }
    }

    public void setWindowAnimations(int i) {
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(i);
        }
    }

    public int getWindowAnimations() {
        Window window = getWindow();
        if (window == null) {
            return -1;
        }
        return window.getAttributes().windowAnimations;
    }

    public void setBackgroundDimEnabled(boolean z) {
        Window window = getWindow();
        if (window != null) {
            if (z) {
                window.addFlags(2);
            } else {
                window.clearFlags(2);
            }
        }
    }

    public void setBackgroundDimAmount(float f) {
        Window window = getWindow();
        if (window != null) {
            window.setDimAmount(f);
        }
    }

    public void dismiss() {
        removeCallbacks();
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService(InputMethodManager.class)).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        super.dismiss();
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        if (onShowListener != null) {
            addOnShowListener(new ShowListenerWrapper(onShowListener));
        }
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        if (onCancelListener != null) {
            addOnCancelListener(new CancelListenerWrapper(onCancelListener));
        }
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        if (onDismissListener != null) {
            addOnDismissListener(new DismissListenerWrapper(onDismissListener));
        }
    }

    public void setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        super.setOnKeyListener(onKeyListener);
    }

    public void setOnKeyListener(OnKeyListener onKeyListener) {
        super.setOnKeyListener(new KeyListenerWrapper(onKeyListener));
    }

    public void addOnShowListener(OnShowListener onShowListener) {
        if (this.mShowListeners == null) {
            this.mShowListeners = new ArrayList();
            super.setOnShowListener(this.mListeners);
        }
        this.mShowListeners.add(onShowListener);
    }

    public void addOnCancelListener(OnCancelListener onCancelListener) {
        if (this.mCancelListeners == null) {
            this.mCancelListeners = new ArrayList();
            super.setOnCancelListener(this.mListeners);
        }
        this.mCancelListeners.add(onCancelListener);
    }

    public void addOnDismissListener(OnDismissListener onDismissListener) {
        if (this.mDismissListeners == null) {
            this.mDismissListeners = new ArrayList();
            super.setOnDismissListener(this.mListeners);
        }
        this.mDismissListeners.add(onDismissListener);
    }

    public void removeOnShowListener(OnShowListener onShowListener) {
        List<OnShowListener> list = this.mShowListeners;
        if (list != null) {
            list.remove(onShowListener);
        }
    }

    public void removeOnCancelListener(OnCancelListener onCancelListener) {
        List<OnCancelListener> list = this.mCancelListeners;
        if (list != null) {
            list.remove(onCancelListener);
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
        super.setOnShowListener(this.mListeners);
        this.mShowListeners = list;
    }

    /* access modifiers changed from: private */
    public void setOnCancelListeners(List<OnCancelListener> list) {
        super.setOnCancelListener(this.mListeners);
        this.mCancelListeners = list;
    }

    /* access modifiers changed from: private */
    public void setOnDismissListeners(List<OnDismissListener> list) {
        super.setOnDismissListener(this.mListeners);
        this.mDismissListeners = list;
    }

    public void onShow(DialogInterface dialogInterface) {
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        if (this.mShowListeners != null) {
            for (int i = 0; i < this.mShowListeners.size(); i++) {
                this.mShowListeners.get(i).onShow(this);
            }
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.mCancelListeners != null) {
            for (int i = 0; i < this.mCancelListeners.size(); i++) {
                this.mCancelListeners.get(i).onCancel(this);
            }
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        if (this.mDismissListeners != null) {
            for (int i = 0; i < this.mDismissListeners.size(); i++) {
                this.mDismissListeners.get(i).onDismiss(this);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    public static class Builder<B extends Builder<?>> implements ActivityAction, ResourcesAction, ClickAction, KeyboardAction {
        private final Activity mActivity;
        private int mAnimStyle;
        private float mBackgroundDimAmount;
        private boolean mBackgroundDimEnabled;
        private final List<OnCancelListener> mCancelListeners;
        private boolean mCancelable;
        private boolean mCanceledOnTouchOutside;
        private SparseArray<OnClickListener<?>> mClickArray;
        private View mContentView;
        private final Context mContext;
        private OnCreateListener mCreateListener;
        private BaseDialog mDialog;
        private final List<OnDismissListener> mDismissListeners;
        private int mGravity;
        private int mHeight;
        private OnKeyListener mKeyListener;
        private final List<OnShowListener> mShowListeners;
        private int mThemeId;
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
            this.mThemeId = R.style.BaseDialogTheme;
            this.mAnimStyle = -1;
            this.mWidth = -2;
            this.mHeight = -2;
            this.mGravity = 0;
            this.mCancelable = true;
            this.mCanceledOnTouchOutside = true;
            this.mBackgroundDimEnabled = true;
            this.mBackgroundDimAmount = 0.5f;
            this.mShowListeners = new ArrayList();
            this.mCancelListeners = new ArrayList();
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
                    this.mDialog.setContentView(view);
                    return self();
                }
                ViewGroup.LayoutParams layoutParams = this.mContentView.getLayoutParams();
                if (layoutParams != null && this.mWidth == -2 && this.mHeight == -2) {
                    setWidth(layoutParams.width);
                    setHeight(layoutParams.height);
                }
                if (this.mGravity == 0) {
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

        public B setThemeStyle(int i) {
            this.mThemeId = i;
            if (!isCreated()) {
                return self();
            }
            throw new IllegalStateException("are you ok?");
        }

        public B setAnimStyle(int i) {
            this.mAnimStyle = i;
            if (isCreated()) {
                this.mDialog.setWindowAnimations(i);
            }
            return self();
        }

        public B setWidth(int i) {
            this.mWidth = i;
            if (isCreated()) {
                this.mDialog.setWidth(i);
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
                this.mDialog.setHeight(i);
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
            if (isCreated()) {
                this.mDialog.setGravity(i);
            }
            return self();
        }

        public B setXOffset(int i) {
            this.mXOffset = i;
            if (isCreated()) {
                this.mDialog.setXOffset(i);
            }
            return self();
        }

        public B setYOffset(int i) {
            this.mYOffset = i;
            if (isCreated()) {
                this.mDialog.setYOffset(i);
            }
            return self();
        }

        public B setCancelable(boolean z) {
            this.mCancelable = z;
            if (isCreated()) {
                this.mDialog.setCancelable(z);
            }
            return self();
        }

        public B setCanceledOnTouchOutside(boolean z) {
            this.mCanceledOnTouchOutside = z;
            if (isCreated() && this.mCancelable) {
                this.mDialog.setCanceledOnTouchOutside(z);
            }
            return self();
        }

        public B setBackgroundDimEnabled(boolean z) {
            this.mBackgroundDimEnabled = z;
            if (isCreated()) {
                this.mDialog.setBackgroundDimEnabled(z);
            }
            return self();
        }

        public B setBackgroundDimAmount(float f) {
            this.mBackgroundDimAmount = f;
            if (isCreated()) {
                this.mDialog.setBackgroundDimAmount(f);
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

        public B addOnCancelListener(OnCancelListener onCancelListener) {
            this.mCancelListeners.add(onCancelListener);
            return self();
        }

        public B addOnDismissListener(OnDismissListener onDismissListener) {
            this.mDismissListeners.add(onDismissListener);
            return self();
        }

        public B setOnKeyListener(OnKeyListener onKeyListener) {
            this.mKeyListener = onKeyListener;
            if (isCreated()) {
                this.mDialog.setOnKeyListener(onKeyListener);
            }
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
            if (isCreated() && (findViewById = this.mDialog.findViewById(i)) != null) {
                findViewById.setOnClickListener(new ViewClickWrapper(this.mDialog, onClickListener));
            }
            return self();
        }

        public BaseDialog create() {
            if (this.mContentView != null) {
                if (isShowing()) {
                    dismiss();
                }
                if (this.mGravity == 0) {
                    this.mGravity = 17;
                }
                if (this.mAnimStyle == -1) {
                    int i = this.mGravity;
                    if (i == 3) {
                        this.mAnimStyle = BaseDialog.ANIM_LEFT;
                    } else if (i == 5) {
                        this.mAnimStyle = BaseDialog.ANIM_RIGHT;
                    } else if (i == 48) {
                        this.mAnimStyle = BaseDialog.ANIM_TOP;
                    } else if (i != 80) {
                        this.mAnimStyle = -1;
                    } else {
                        this.mAnimStyle = BaseDialog.ANIM_BOTTOM;
                    }
                }
                BaseDialog createDialog = createDialog(this.mContext, this.mThemeId);
                this.mDialog = createDialog;
                createDialog.setContentView(this.mContentView);
                this.mDialog.setCancelable(this.mCancelable);
                if (this.mCancelable) {
                    this.mDialog.setCanceledOnTouchOutside(this.mCanceledOnTouchOutside);
                }
                this.mDialog.setOnShowListeners(this.mShowListeners);
                this.mDialog.setOnCancelListeners(this.mCancelListeners);
                this.mDialog.setOnDismissListeners(this.mDismissListeners);
                this.mDialog.setOnKeyListener(this.mKeyListener);
                Window window = this.mDialog.getWindow();
                if (window != null) {
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    attributes.width = this.mWidth;
                    attributes.height = this.mHeight;
                    attributes.gravity = this.mGravity;
                    attributes.x = this.mXOffset;
                    attributes.y = this.mYOffset;
                    attributes.windowAnimations = this.mAnimStyle;
                    if (this.mBackgroundDimEnabled) {
                        window.addFlags(2);
                        window.setDimAmount(this.mBackgroundDimAmount);
                    } else {
                        window.clearFlags(2);
                    }
                    window.setAttributes(attributes);
                }
                int i2 = 0;
                SparseArray<OnClickListener<?>> sparseArray = this.mClickArray;
                while (sparseArray != null && i2 < sparseArray.size()) {
                    View findViewById = this.mContentView.findViewById(this.mClickArray.keyAt(i2));
                    if (findViewById != null) {
                        findViewById.setOnClickListener(new ViewClickWrapper(this.mDialog, this.mClickArray.valueAt(i2)));
                    }
                    i2++;
                }
                Activity activity2 = this.mActivity;
                if (activity2 != null) {
                    DialogLifecycle.with(activity2, this.mDialog);
                }
                OnCreateListener onCreateListener = this.mCreateListener;
                if (onCreateListener != null) {
                    onCreateListener.onCreate(this.mDialog);
                }
                return this.mDialog;
            }
            throw new IllegalArgumentException("are you ok?");
        }

        public void show() {
            Activity activity = this.mActivity;
            if (activity != null && !activity.isFinishing() && !this.mActivity.isDestroyed()) {
                if (!isCreated()) {
                    create();
                }
                if (!isShowing()) {
                    this.mDialog.show();
                }
            }
        }

        public void dismiss() {
            BaseDialog baseDialog;
            Activity activity = this.mActivity;
            if (activity != null && !activity.isFinishing() && !this.mActivity.isDestroyed() && (baseDialog = this.mDialog) != null) {
                baseDialog.dismiss();
            }
        }

        public Context getContext() {
            return this.mContext;
        }

        public boolean isCreated() {
            return this.mDialog != null;
        }

        public boolean isShowing() {
            return isCreated() && this.mDialog.isShowing();
        }

        /* access modifiers changed from: protected */
        public BaseDialog createDialog(Context context, int i) {
            return new BaseDialog(context, i);
        }

        public final void post(Runnable runnable) {
            if (isShowing()) {
                this.mDialog.post(runnable);
            } else {
                addOnShowListener(new ShowPostWrapper(runnable));
            }
        }

        public final void postDelayed(Runnable runnable, long j) {
            if (isShowing()) {
                this.mDialog.postDelayed(runnable, j);
            } else {
                addOnShowListener(new ShowPostDelayedWrapper(runnable, j));
            }
        }

        public final void postAtTime(Runnable runnable, long j) {
            if (isShowing()) {
                this.mDialog.postAtTime(runnable, j);
            } else {
                addOnShowListener(new ShowPostAtTimeWrapper(runnable, j));
            }
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

        public BaseDialog getDialog() {
            return this.mDialog;
        }
    }

    private static final class DialogLifecycle implements Application.ActivityLifecycleCallbacks, OnShowListener, OnDismissListener {
        private Activity mActivity;
        private BaseDialog mDialog;
        private int mDialogAnim;

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        /* access modifiers changed from: private */
        public static void with(Activity activity, BaseDialog baseDialog) {
            new DialogLifecycle(activity, baseDialog);
        }

        private DialogLifecycle(Activity activity, BaseDialog baseDialog) {
            this.mActivity = activity;
            baseDialog.addOnShowListener(this);
            baseDialog.addOnDismissListener(this);
        }

        public void onActivityResumed(Activity activity) {
            BaseDialog baseDialog;
            if (this.mActivity == activity && (baseDialog = this.mDialog) != null && baseDialog.isShowing()) {
                this.mDialog.postDelayed(new Runnable() {
                    public final void run() {
                        BaseDialog.DialogLifecycle.this.lambda$onActivityResumed$0$BaseDialog$DialogLifecycle();
                    }
                }, 100);
            }
        }

        public /* synthetic */ void lambda$onActivityResumed$0$BaseDialog$DialogLifecycle() {
            BaseDialog baseDialog = this.mDialog;
            if (baseDialog != null && baseDialog.isShowing()) {
                this.mDialog.setWindowAnimations(this.mDialogAnim);
            }
        }

        public void onActivityPaused(Activity activity) {
            BaseDialog baseDialog;
            if (this.mActivity == activity && (baseDialog = this.mDialog) != null && baseDialog.isShowing()) {
                this.mDialogAnim = this.mDialog.getWindowAnimations();
                this.mDialog.setWindowAnimations(0);
            }
        }

        public void onActivityDestroyed(Activity activity) {
            if (this.mActivity == activity) {
                unregisterActivityLifecycleCallbacks();
                this.mActivity = null;
                BaseDialog baseDialog = this.mDialog;
                if (baseDialog != null) {
                    baseDialog.removeOnShowListener(this);
                    this.mDialog.removeOnDismissListener(this);
                    if (this.mDialog.isShowing()) {
                        this.mDialog.dismiss();
                    }
                    this.mDialog = null;
                }
            }
        }

        public void onShow(BaseDialog baseDialog) {
            this.mDialog = baseDialog;
            registerActivityLifecycleCallbacks();
        }

        public void onDismiss(BaseDialog baseDialog) {
            this.mDialog = null;
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

    private static final class ListenersWrapper<T extends DialogInterface.OnShowListener & DialogInterface.OnCancelListener & DialogInterface.OnDismissListener> extends SoftReference<T> implements DialogInterface.OnShowListener, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
        private ListenersWrapper(T t) {
            super(t);
        }

        public void onShow(DialogInterface dialogInterface) {
            if (get() != null) {
                ((DialogInterface.OnShowListener) get()).onShow(dialogInterface);
            }
        }

        public void onCancel(DialogInterface dialogInterface) {
            if (get() != null) {
                ((DialogInterface.OnCancelListener) ((DialogInterface.OnShowListener) get())).onCancel(dialogInterface);
            }
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (get() != null) {
                ((DialogInterface.OnDismissListener) ((DialogInterface.OnShowListener) get())).onDismiss(dialogInterface);
            }
        }
    }

    private static final class ViewClickWrapper implements View.OnClickListener {
        private final BaseDialog mDialog;
        private final OnClickListener mListener;

        private ViewClickWrapper(BaseDialog baseDialog, OnClickListener onClickListener) {
            this.mDialog = baseDialog;
            this.mListener = onClickListener;
        }

        public final void onClick(View view) {
            OnClickListener onClickListener = this.mListener;
            if (onClickListener != null) {
                onClickListener.onClick(this.mDialog, view);
            }
        }
    }

    private static final class ShowListenerWrapper extends SoftReference<DialogInterface.OnShowListener> implements OnShowListener {
        private ShowListenerWrapper(DialogInterface.OnShowListener onShowListener) {
            super(onShowListener);
        }

        public void onShow(BaseDialog baseDialog) {
            if (get() != null) {
                ((DialogInterface.OnShowListener) get()).onShow(baseDialog);
            }
        }
    }

    private static final class CancelListenerWrapper extends SoftReference<DialogInterface.OnCancelListener> implements OnCancelListener {
        private CancelListenerWrapper(DialogInterface.OnCancelListener onCancelListener) {
            super(onCancelListener);
        }

        public void onCancel(BaseDialog baseDialog) {
            if (get() != null) {
                ((DialogInterface.OnCancelListener) get()).onCancel(baseDialog);
            }
        }
    }

    private static final class DismissListenerWrapper extends SoftReference<DialogInterface.OnDismissListener> implements OnDismissListener {
        private DismissListenerWrapper(DialogInterface.OnDismissListener onDismissListener) {
            super(onDismissListener);
        }

        public void onDismiss(BaseDialog baseDialog) {
            if (get() != null) {
                ((DialogInterface.OnDismissListener) get()).onDismiss(baseDialog);
            }
        }
    }

    private static final class KeyListenerWrapper implements DialogInterface.OnKeyListener {
        private final OnKeyListener mListener;

        private KeyListenerWrapper(OnKeyListener onKeyListener) {
            this.mListener = onKeyListener;
        }

        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            OnKeyListener onKeyListener = this.mListener;
            if (onKeyListener == null || !(dialogInterface instanceof BaseDialog)) {
                return false;
            }
            return onKeyListener.onKey((BaseDialog) dialogInterface, keyEvent);
        }
    }

    private static final class ShowPostWrapper implements OnShowListener {
        private final Runnable mRunnable;

        private ShowPostWrapper(Runnable runnable) {
            this.mRunnable = runnable;
        }

        public void onShow(BaseDialog baseDialog) {
            if (this.mRunnable != null) {
                baseDialog.removeOnShowListener(this);
                baseDialog.post(this.mRunnable);
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

        public void onShow(BaseDialog baseDialog) {
            if (this.mRunnable != null) {
                baseDialog.removeOnShowListener(this);
                baseDialog.postDelayed(this.mRunnable, this.mDelayMillis);
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

        public void onShow(BaseDialog baseDialog) {
            if (this.mRunnable != null) {
                baseDialog.removeOnShowListener(this);
                baseDialog.postAtTime(this.mRunnable, this.mUptimeMillis);
            }
        }
    }
}
