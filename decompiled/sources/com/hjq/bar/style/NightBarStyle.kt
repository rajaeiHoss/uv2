package com.hjq.bar.style

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.view.ViewCompat
import com.hjq.bar.R
import com.hjq.bar.SelectorDrawable
import com.hjq.bar.TitleBarSupport

class NightBarStyle : CommonBarStyle() {
    override fun getBackButtonDrawable(context: Context): Drawable? =
        TitleBarSupport.getDrawable(context, R.drawable.bar_arrows_left_white)

    override fun getLeftTitleBackground(context: Context): Drawable =
        SelectorDrawable.Builder()
            .setDefault(ColorDrawable(0))
            .setFocused(ColorDrawable(1728053247))
            .setPressed(ColorDrawable(1728053247))
            .build()

    override fun getRightTitleBackground(context: Context): Drawable =
        SelectorDrawable.Builder()
            .setDefault(ColorDrawable(0))
            .setFocused(ColorDrawable(1728053247))
            .setPressed(ColorDrawable(1728053247))
            .build()

    override fun getTitleBarBackground(context: Context): Drawable = ColorDrawable(ViewCompat.MEASURED_STATE_MASK)

    override fun getTitleColor(context: Context): ColorStateList = ColorStateList.valueOf(-285212673)

    override fun getLeftTitleColor(context: Context): ColorStateList = ColorStateList.valueOf(-855638017)

    override fun getRightTitleColor(context: Context): ColorStateList = ColorStateList.valueOf(-855638017)

    override fun getLineDrawable(context: Context): Drawable = ColorDrawable(-1)
}
