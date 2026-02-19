package com.hjq.bar.style

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import com.hjq.bar.R
import com.hjq.bar.SelectorDrawable
import com.hjq.bar.TitleBarSupport

open class TransparentBarStyle : CommonBarStyle() {
    override fun getBackButtonDrawable(context: Context): Drawable? =
        TitleBarSupport.getDrawable(context, R.drawable.bar_arrows_left_white)

    override fun getLeftTitleBackground(context: Context): Drawable =
        SelectorDrawable.Builder()
            .setDefault(ColorDrawable(0))
            .setFocused(ColorDrawable(570425344))
            .setPressed(ColorDrawable(570425344))
            .build()

    override fun getRightTitleBackground(context: Context): Drawable =
        SelectorDrawable.Builder()
            .setDefault(ColorDrawable(0))
            .setFocused(ColorDrawable(570425344))
            .setPressed(ColorDrawable(570425344))
            .build()

    override fun getTitleBarBackground(context: Context): Drawable = ColorDrawable(0)

    override fun getTitleColor(context: Context): ColorStateList = ColorStateList.valueOf(-1)

    override fun getLeftTitleColor(context: Context): ColorStateList = ColorStateList.valueOf(-1)

    override fun getRightTitleColor(context: Context): ColorStateList = ColorStateList.valueOf(-1)

    override fun getLineDrawable(context: Context): Drawable = ColorDrawable(0)
}
