package com.hjq.widget.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.hjq.widget.R;

public final class PasswordEditText extends RegexEditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {
    private Drawable mCurrentDrawable;
    private View.OnFocusChangeListener mFocusChangeListener;
    private final Drawable mInvisibleDrawable;
    private View.OnTouchListener mTouchListener;
    private final Drawable mVisibleDrawable;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public PasswordEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public PasswordEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842862);
    }

    public PasswordEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Drawable wrap = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.password_off_ic));
        this.mVisibleDrawable = wrap;
        wrap.setBounds(0, 0, wrap.getIntrinsicWidth(), wrap.getIntrinsicHeight());
        Drawable wrap2 = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.password_on_ic));
        this.mInvisibleDrawable = wrap2;
        wrap2.setBounds(0, 0, wrap2.getIntrinsicWidth(), wrap2.getIntrinsicHeight());
        this.mCurrentDrawable = wrap;
        addInputType(128);
        if (getInputRegex() == null) {
            setInputRegex(RegexEditText.REGEX_NONNULL);
        }
        setDrawableVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        super.addTextChangedListener(this);
    }

    private void setDrawableVisible(boolean z) {
        if (this.mCurrentDrawable.isVisible() != z) {
            this.mCurrentDrawable.setVisible(z, false);
            Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
            setCompoundDrawablesRelative(compoundDrawablesRelative[0], compoundDrawablesRelative[1], z ? this.mCurrentDrawable : null, compoundDrawablesRelative[3]);
        }
    }

    private void refreshDrawableStatus() {
        Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
        setCompoundDrawablesRelative(compoundDrawablesRelative[0], compoundDrawablesRelative[1], this.mCurrentDrawable, compoundDrawablesRelative[3]);
    }

    public void setOnFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.mFocusChangeListener = onFocusChangeListener;
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.mTouchListener = onTouchListener;
    }

    public void onFocusChange(View view, boolean z) {
        boolean z2 = false;
        if (!z || getText() == null) {
            setDrawableVisible(false);
        } else {
            if (getText().length() > 0) {
                z2 = true;
            }
            setDrawableVisible(z2);
        }
        View.OnFocusChangeListener onFocusChangeListener = this.mFocusChangeListener;
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(view, z);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        int x = (int) motionEvent.getX();
        int layoutDirection = getLayoutDirection();
        if (layoutDirection != 0 ? layoutDirection != 1 || x <= getPaddingStart() || x >= getPaddingStart() + this.mCurrentDrawable.getIntrinsicWidth() : x <= (getWidth() - this.mCurrentDrawable.getIntrinsicWidth()) - getPaddingEnd() || x >= getWidth() - getPaddingEnd()) {
            z = false;
        } else {
            z = true;
        }
        if (!this.mCurrentDrawable.isVisible() || !z) {
            View.OnTouchListener onTouchListener = this.mTouchListener;
            if (onTouchListener == null || !onTouchListener.onTouch(view, motionEvent)) {
                return false;
            }
            return true;
        }
        if (motionEvent.getAction() == 1) {
            Drawable drawable = this.mCurrentDrawable;
            Drawable drawable2 = this.mVisibleDrawable;
            if (drawable == drawable2) {
                this.mCurrentDrawable = this.mInvisibleDrawable;
                setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                refreshDrawableStatus();
            } else if (drawable == this.mInvisibleDrawable) {
                this.mCurrentDrawable = drawable2;
                setTransformationMethod(PasswordTransformationMethod.getInstance());
                refreshDrawableStatus();
            }
            Editable text = getText();
            if (text != null) {
                setSelection(text.toString().length());
            }
        }
        return true;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (isFocused()) {
            setDrawableVisible(charSequence.length() > 0);
        }
    }
}
