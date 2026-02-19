package com.hjq.shape.builder;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.CompoundButton;
import androidx.core.widget.CompoundButtonCompat;
import com.hjq.shape.styleable.ICompoundButtonStyleable;

public final class ButtonDrawableBuilder {
    private Drawable mButtonDrawable;
    private final CompoundButton mCompoundButton;
    private Drawable mDrawableCheckedDrawable;
    private Drawable mDrawableDisabledDrawable;
    private Drawable mDrawableFocusedDrawable;
    private Drawable mDrawablePressedDrawable;
    private Drawable mDrawableSelectedDrawable;

    public ButtonDrawableBuilder(CompoundButton compoundButton, TypedArray typedArray, ICompoundButtonStyleable iCompoundButtonStyleable) {
        this.mCompoundButton = compoundButton;
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonDrawableStyleable())) {
            this.mButtonDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonDrawableStyleable());
        } else {
            this.mButtonDrawable = CompoundButtonCompat.getButtonDrawable(compoundButton);
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonPressedDrawableStyleable())) {
            this.mDrawablePressedDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonPressedDrawableStyleable());
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonCheckedDrawableStyleable())) {
            this.mDrawableCheckedDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonCheckedDrawableStyleable());
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonDisabledDrawableStyleable())) {
            this.mDrawableDisabledDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonDisabledDrawableStyleable());
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonFocusedDrawableStyleable())) {
            this.mDrawableFocusedDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonFocusedDrawableStyleable());
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonSelectedDrawableStyleable())) {
            this.mDrawableSelectedDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonSelectedDrawableStyleable());
        }
    }

    public ButtonDrawableBuilder setButtonDrawable(Drawable drawable) {
        Drawable drawable2 = this.mDrawablePressedDrawable;
        Drawable drawable3 = this.mButtonDrawable;
        if (drawable2 == drawable3) {
            this.mDrawablePressedDrawable = drawable;
        }
        if (this.mDrawableCheckedDrawable == drawable3) {
            this.mDrawableCheckedDrawable = drawable;
        }
        if (this.mDrawableDisabledDrawable == drawable3) {
            this.mDrawableDisabledDrawable = drawable;
        }
        if (this.mDrawableFocusedDrawable == drawable3) {
            this.mDrawableFocusedDrawable = drawable;
        }
        if (this.mDrawableSelectedDrawable == drawable3) {
            this.mDrawableSelectedDrawable = drawable;
        }
        this.mButtonDrawable = drawable;
        return this;
    }

    public Drawable getButtonDrawable() {
        return this.mButtonDrawable;
    }

    public ButtonDrawableBuilder setDrawablePressedDrawable(Drawable drawable) {
        this.mDrawablePressedDrawable = drawable;
        return this;
    }

    public Drawable getDrawablePressedDrawable() {
        return this.mDrawablePressedDrawable;
    }

    public ButtonDrawableBuilder setDrawableCheckedDrawable(Drawable drawable) {
        this.mDrawableCheckedDrawable = drawable;
        return this;
    }

    public Drawable getDrawableCheckedDrawable() {
        return this.mDrawableCheckedDrawable;
    }

    public ButtonDrawableBuilder setDrawableDisabledDrawable(Drawable drawable) {
        this.mDrawableDisabledDrawable = drawable;
        return this;
    }

    public Drawable getDrawableDisabledDrawable() {
        return this.mDrawableDisabledDrawable;
    }

    public ButtonDrawableBuilder setDrawableFocusedDrawable(Drawable drawable) {
        this.mDrawableFocusedDrawable = drawable;
        return this;
    }

    public Drawable getDrawableFocusedDrawable() {
        return this.mDrawableFocusedDrawable;
    }

    public ButtonDrawableBuilder setDrawableSelectedDrawable(Drawable drawable) {
        this.mDrawableSelectedDrawable = drawable;
        return this;
    }

    public Drawable getDrawableSelectedDrawable() {
        return this.mDrawableSelectedDrawable;
    }

    public void intoButtonDrawable() {
        Drawable drawable = this.mButtonDrawable;
        if (drawable != null) {
            if (this.mDrawablePressedDrawable == null && this.mDrawableCheckedDrawable == null && this.mDrawableDisabledDrawable == null && this.mDrawableFocusedDrawable == null && this.mDrawableSelectedDrawable == null) {
                this.mCompoundButton.setButtonDrawable(drawable);
                return;
            }
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable drawable2 = this.mDrawablePressedDrawable;
            if (drawable2 != null) {
                stateListDrawable.addState(new int[]{16842919}, drawable2);
            }
            Drawable drawable3 = this.mDrawableCheckedDrawable;
            if (drawable3 != null) {
                stateListDrawable.addState(new int[]{16842912}, drawable3);
            }
            Drawable drawable4 = this.mDrawableDisabledDrawable;
            if (drawable4 != null) {
                stateListDrawable.addState(new int[]{-16842910}, drawable4);
            }
            Drawable drawable5 = this.mDrawableFocusedDrawable;
            if (drawable5 != null) {
                stateListDrawable.addState(new int[]{16842908}, drawable5);
            }
            Drawable drawable6 = this.mDrawableSelectedDrawable;
            if (drawable6 != null) {
                stateListDrawable.addState(new int[]{16842913}, drawable6);
            }
            stateListDrawable.addState(new int[0], this.mButtonDrawable);
            this.mCompoundButton.setButtonDrawable(stateListDrawable);
        }
    }
}
