package com.hjq.bar.style

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import com.dvr.calendar.DayStyle
import com.hjq.bar.R
import com.hjq.bar.SelectorDrawable
import com.hjq.bar.TitleBarSupport

class LightBarStyle : CommonBarStyle() {
    override fun getTitleBarBackground(context: Context): Drawable = ColorDrawable(-1)

    override fun getLeftTitleBackground(context: Context): Drawable =
        SelectorDrawable.Builder()
            .setDefault(ColorDrawable(0))
            .setFocused(ColorDrawable(201326592))
            .setPressed(ColorDrawable(201326592))
            .build()

    override fun getRightTitleBackground(context: Context): Drawable =
        SelectorDrawable.Builder()
            .setDefault(ColorDrawable(0))
            .setFocused(ColorDrawable(201326592))
            .setPressed(ColorDrawable(201326592))
            .build()

    override fun getBackButtonDrawable(context: Context): Drawable? =
        TitleBarSupport.getDrawable(context, R.drawable.bar_arrows_left_black)

    override fun getTitleColor(context: Context): ColorStateList = ColorStateList.valueOf(-14540254)

    override fun getLeftTitleColor(context: Context): ColorStateList =
        ColorStateList.valueOf(DayStyle.iColorFrameHeader)

    override fun getRightTitleColor(context: Context): ColorStateList = ColorStateList.valueOf(-5987164)

    override fun getLineDrawable(context: Context): Drawable = ColorDrawable(-1250068)
}
