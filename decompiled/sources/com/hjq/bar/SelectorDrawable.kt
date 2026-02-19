package com.hjq.bar

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable

class SelectorDrawable : StateListDrawable() {
    class Builder {
        private var mChecked: Drawable? = null
        private var mDefault: Drawable? = null
        private var mEnabled: Drawable? = null
        private var mFocused: Drawable? = null
        private var mHovered: Drawable? = null
        private var mPressed: Drawable? = null
        private var mSelected: Drawable? = null

        fun setDefault(drawable: Drawable?): Builder {
            mDefault = drawable
            return this
        }

        fun setFocused(drawable: Drawable?): Builder {
            mFocused = drawable
            return this
        }

        fun setPressed(drawable: Drawable?): Builder {
            mPressed = drawable
            return this
        }

        fun setChecked(drawable: Drawable?): Builder {
            mChecked = drawable
            return this
        }

        fun setEnabled(drawable: Drawable?): Builder {
            mEnabled = drawable
            return this
        }

        fun setSelected(drawable: Drawable?): Builder {
            mSelected = drawable
            return this
        }

        fun setHovered(drawable: Drawable?): Builder {
            mHovered = drawable
            return this
        }

        fun build(): SelectorDrawable {
            val selectorDrawable = SelectorDrawable()
            mPressed?.let { selectorDrawable.addState(intArrayOf(16842919), it) }
            mFocused?.let { selectorDrawable.addState(intArrayOf(16842908), it) }
            mChecked?.let { selectorDrawable.addState(intArrayOf(16842912), it) }
            mEnabled?.let { selectorDrawable.addState(intArrayOf(16842910), it) }
            mSelected?.let { selectorDrawable.addState(intArrayOf(16842913), it) }
            mHovered?.let { selectorDrawable.addState(intArrayOf(16843623), it) }
            mDefault?.let { selectorDrawable.addState(IntArray(0), it) }
            return selectorDrawable
        }
    }
}
