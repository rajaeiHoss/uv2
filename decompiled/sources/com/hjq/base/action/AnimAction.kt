package com.hjq.base.action

import com.hjq.base.R

interface AnimAction {
    companion object {
        @JvmField
        val ANIM_BOTTOM: Int = R.style.BottomAnimStyle

        @JvmField
        val ANIM_DEFAULT: Int = -1

        @JvmField
        val ANIM_EMPTY: Int = 0

        @JvmField
        val ANIM_IOS: Int = R.style.IOSAnimStyle

        @JvmField
        val ANIM_LEFT: Int = R.style.LeftAnimStyle

        @JvmField
        val ANIM_RIGHT: Int = R.style.RightAnimStyle

        @JvmField
        val ANIM_SCALE: Int = R.style.ScaleAnimStyle

        @JvmField
        val ANIM_TOAST: Int = 16973828

        @JvmField
        val ANIM_TOP: Int = R.style.TopAnimStyle
    }
}
