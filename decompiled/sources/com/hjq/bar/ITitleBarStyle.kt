package com.hjq.bar

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView

interface ITitleBarStyle {
    fun createLeftView(context: Context): TextView

    fun createLineView(context: Context): View

    fun createRightView(context: Context): TextView

    fun createTitleView(context: Context): TextView

    fun getBackButtonDrawable(context: Context): Drawable?

    fun getChildHorizontalPadding(context: Context): Int

    fun getChildVerticalPadding(context: Context): Int

    fun getLeftIconGravity(context: Context): Int

    fun getLeftIconHeight(context: Context): Int

    fun getLeftIconPadding(context: Context): Int

    fun getLeftIconWidth(context: Context): Int

    fun getLeftTitle(context: Context): CharSequence?

    fun getLeftTitleBackground(context: Context): Drawable?

    fun getLeftTitleColor(context: Context): ColorStateList?

    fun getLeftTitleSize(context: Context): Float

    fun getLeftTitleStyle(context: Context): Int

    fun getLineDrawable(context: Context): Drawable?

    fun getLineSize(context: Context): Int

    fun getRightIconGravity(context: Context): Int

    fun getRightIconHeight(context: Context): Int

    fun getRightIconPadding(context: Context): Int

    fun getRightIconWidth(context: Context): Int

    fun getRightTitle(context: Context): CharSequence?

    fun getRightTitleBackground(context: Context): Drawable?

    fun getRightTitleColor(context: Context): ColorStateList?

    fun getRightTitleSize(context: Context): Float

    fun getRightTitleStyle(context: Context): Int

    fun getTitle(context: Context): CharSequence?

    fun getTitleBarBackground(context: Context): Drawable?

    fun getTitleColor(context: Context): ColorStateList?

    fun getTitleIconGravity(context: Context): Int

    fun getTitleIconHeight(context: Context): Int

    fun getTitleIconPadding(context: Context): Int

    fun getTitleIconWidth(context: Context): Int

    fun getTitleSize(context: Context): Float

    fun getTitleStyle(context: Context): Int

    fun isLineVisible(context: Context): Boolean
}
