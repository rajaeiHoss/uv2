package com.hjq.widget.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;

public final class SmartTextView extends AppCompatTextView {
    public SmartTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SmartTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public SmartTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        refreshVisibilityStatus();
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        refreshVisibilityStatus();
    }

    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        refreshVisibilityStatus();
    }

    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        refreshVisibilityStatus();
    }

    private void refreshVisibilityStatus() {
        if (isEmptyContent() && getVisibility() != 8) {
            setVisibility(8);
        } else if (getVisibility() != 0) {
            setVisibility(0);
        }
    }

    private boolean isEmptyContent() {
        if (!TextUtils.isEmpty(getText())) {
            return false;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
        for (Drawable drawable : compoundDrawables) {
            if (drawable != null) {
                return false;
            }
        }
        for (Drawable drawable2 : compoundDrawablesRelative) {
            if (drawable2 != null) {
                return false;
            }
        }
        return true;
    }
}
