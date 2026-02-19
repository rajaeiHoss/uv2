package com.hjq.widget.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.hjq.widget.R;

public final class ClearEditText extends RegexEditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {
    private final Drawable mClearDrawable;
    private View.OnFocusChangeListener mFocusChangeListener;
    private View.OnTouchListener mTouchListener;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public ClearEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public ClearEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842862);
    }

    public ClearEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Drawable wrap = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.input_delete_ic));
        this.mClearDrawable = wrap;
        wrap.setBounds(0, 0, wrap.getIntrinsicWidth(), wrap.getIntrinsicHeight());
        setDrawableVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        super.addTextChangedListener(this);
    }

    private void setDrawableVisible(boolean z) {
        if (this.mClearDrawable.isVisible() != z) {
            this.mClearDrawable.setVisible(z, false);
            Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
            setCompoundDrawablesRelative(compoundDrawablesRelative[0], compoundDrawablesRelative[1], z ? this.mClearDrawable : null, compoundDrawablesRelative[3]);
        }
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
        if (layoutDirection != 0 ? layoutDirection != 1 || x <= getPaddingStart() || x >= getPaddingStart() + this.mClearDrawable.getIntrinsicWidth() : x <= (getWidth() - this.mClearDrawable.getIntrinsicWidth()) - getPaddingEnd() || x >= getWidth() - getPaddingEnd()) {
            z = false;
        } else {
            z = true;
        }
        if (!this.mClearDrawable.isVisible() || !z) {
            View.OnTouchListener onTouchListener = this.mTouchListener;
            if (onTouchListener == null || !onTouchListener.onTouch(view, motionEvent)) {
                return false;
            }
            return true;
        }
        if (motionEvent.getAction() == 1) {
            setText("");
        }
        return true;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (isFocused()) {
            setDrawableVisible(charSequence.length() > 0);
        }
    }
}
