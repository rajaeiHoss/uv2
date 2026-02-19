package com.hjq.shape.builder

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.widget.CompoundButton
import androidx.core.widget.CompoundButtonCompat
import com.hjq.shape.styleable.ICompoundButtonStyleable

class ButtonDrawableBuilder(
    private val mCompoundButton: CompoundButton,
    typedArray: TypedArray,
    iCompoundButtonStyleable: ICompoundButtonStyleable
) {
    private var mButtonDrawable: Drawable?
    private var mDrawableCheckedDrawable: Drawable? = null
    private var mDrawableDisabledDrawable: Drawable? = null
    private var mDrawableFocusedDrawable: Drawable? = null
    private var mDrawablePressedDrawable: Drawable? = null
    private var mDrawableSelectedDrawable: Drawable? = null

    init {
        mButtonDrawable =
            if (typedArray.hasValue(iCompoundButtonStyleable.getButtonDrawableStyleable())) {
                typedArray.getDrawable(iCompoundButtonStyleable.getButtonDrawableStyleable())
            } else {
                CompoundButtonCompat.getButtonDrawable(mCompoundButton)
            }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonPressedDrawableStyleable())) {
            mDrawablePressedDrawable =
                typedArray.getDrawable(iCompoundButtonStyleable.getButtonPressedDrawableStyleable())
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonCheckedDrawableStyleable())) {
            mDrawableCheckedDrawable =
                typedArray.getDrawable(iCompoundButtonStyleable.getButtonCheckedDrawableStyleable())
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonDisabledDrawableStyleable())) {
            mDrawableDisabledDrawable =
                typedArray.getDrawable(iCompoundButtonStyleable.getButtonDisabledDrawableStyleable())
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonFocusedDrawableStyleable())) {
            mDrawableFocusedDrawable =
                typedArray.getDrawable(iCompoundButtonStyleable.getButtonFocusedDrawableStyleable())
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonSelectedDrawableStyleable())) {
            mDrawableSelectedDrawable =
                typedArray.getDrawable(iCompoundButtonStyleable.getButtonSelectedDrawableStyleable())
        }
    }

    fun setButtonDrawable(drawable: Drawable?): ButtonDrawableBuilder {
        val oldButtonDrawable = mButtonDrawable
        if (mDrawablePressedDrawable === oldButtonDrawable) {
            mDrawablePressedDrawable = drawable
        }
        if (mDrawableCheckedDrawable === oldButtonDrawable) {
            mDrawableCheckedDrawable = drawable
        }
        if (mDrawableDisabledDrawable === oldButtonDrawable) {
            mDrawableDisabledDrawable = drawable
        }
        if (mDrawableFocusedDrawable === oldButtonDrawable) {
            mDrawableFocusedDrawable = drawable
        }
        if (mDrawableSelectedDrawable === oldButtonDrawable) {
            mDrawableSelectedDrawable = drawable
        }
        mButtonDrawable = drawable
        return this
    }

    fun getButtonDrawable(): Drawable? {
        return mButtonDrawable
    }

    fun setDrawablePressedDrawable(drawable: Drawable?): ButtonDrawableBuilder {
        mDrawablePressedDrawable = drawable
        return this
    }

    fun getDrawablePressedDrawable(): Drawable? {
        return mDrawablePressedDrawable
    }

    fun setDrawableCheckedDrawable(drawable: Drawable?): ButtonDrawableBuilder {
        mDrawableCheckedDrawable = drawable
        return this
    }

    fun getDrawableCheckedDrawable(): Drawable? {
        return mDrawableCheckedDrawable
    }

    fun setDrawableDisabledDrawable(drawable: Drawable?): ButtonDrawableBuilder {
        mDrawableDisabledDrawable = drawable
        return this
    }

    fun getDrawableDisabledDrawable(): Drawable? {
        return mDrawableDisabledDrawable
    }

    fun setDrawableFocusedDrawable(drawable: Drawable?): ButtonDrawableBuilder {
        mDrawableFocusedDrawable = drawable
        return this
    }

    fun getDrawableFocusedDrawable(): Drawable? {
        return mDrawableFocusedDrawable
    }

    fun setDrawableSelectedDrawable(drawable: Drawable?): ButtonDrawableBuilder {
        mDrawableSelectedDrawable = drawable
        return this
    }

    fun getDrawableSelectedDrawable(): Drawable? {
        return mDrawableSelectedDrawable
    }

    fun intoButtonDrawable() {
        val buttonDrawable = mButtonDrawable ?: return
        if (
            mDrawablePressedDrawable == null &&
            mDrawableCheckedDrawable == null &&
            mDrawableDisabledDrawable == null &&
            mDrawableFocusedDrawable == null &&
            mDrawableSelectedDrawable == null
        ) {
            mCompoundButton.setButtonDrawable(buttonDrawable)
            return
        }
        val stateListDrawable = StateListDrawable()
        mDrawablePressedDrawable?.let {
            stateListDrawable.addState(intArrayOf(16842919), it)
        }
        mDrawableCheckedDrawable?.let {
            stateListDrawable.addState(intArrayOf(16842912), it)
        }
        mDrawableDisabledDrawable?.let {
            stateListDrawable.addState(intArrayOf(-16842910), it)
        }
        mDrawableFocusedDrawable?.let {
            stateListDrawable.addState(intArrayOf(16842908), it)
        }
        mDrawableSelectedDrawable?.let {
            stateListDrawable.addState(intArrayOf(16842913), it)
        }
        stateListDrawable.addState(intArrayOf(), buttonDrawable)
        mCompoundButton.setButtonDrawable(stateListDrawable)
    }
}
