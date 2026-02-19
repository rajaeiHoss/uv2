package com.hjq.bar.style

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import com.hjq.bar.ITitleBarStyle

abstract class CommonBarStyle : ITitleBarStyle {
    override fun getLeftIconGravity(context: Context): Int = 8388611

    override fun getLeftIconHeight(context: Context): Int = 0

    override fun getLeftIconWidth(context: Context): Int = 0

    override fun getLeftTitle(context: Context): CharSequence = ""

    override fun getLeftTitleStyle(context: Context): Int = 0

    override fun getLineSize(context: Context): Int = 1

    override fun getRightIconGravity(context: Context): Int = GravityCompat.END

    override fun getRightIconHeight(context: Context): Int = 0

    override fun getRightIconWidth(context: Context): Int = 0

    override fun getRightTitle(context: Context): CharSequence = ""

    override fun getRightTitleStyle(context: Context): Int = 0

    override fun getTitleIconGravity(context: Context): Int = GravityCompat.END

    override fun getTitleIconHeight(context: Context): Int = 0

    override fun getTitleIconWidth(context: Context): Int = 0

    override fun getTitleStyle(context: Context): Int = 0

    override fun isLineVisible(context: Context): Boolean = true

    override fun createTitleView(context: Context): TextView {
        val newTitleView = newTitleView(context)
        newTitleView.gravity = 16
        newTitleView.isFocusable = true
        newTitleView.setSingleLine()
        newTitleView.ellipsize = TextUtils.TruncateAt.MARQUEE
        newTitleView.marqueeRepeatLimit = -1
        newTitleView.isSelected = true
        return newTitleView
    }

    open fun newTitleView(context: Context): TextView = TextView(context)

    override fun createLeftView(context: Context): TextView {
        val newLeftView = newLeftView(context)
        newLeftView.gravity = 16
        newLeftView.isFocusable = true
        newLeftView.setSingleLine()
        newLeftView.ellipsize = TextUtils.TruncateAt.END
        return newLeftView
    }

    open fun newLeftView(context: Context): TextView = TextView(context)

    override fun createRightView(context: Context): TextView {
        val newRightView = newRightView(context)
        newRightView.gravity = 16
        newRightView.isFocusable = true
        newRightView.setSingleLine()
        newRightView.ellipsize = TextUtils.TruncateAt.END
        return newRightView
    }

    open fun newRightView(context: Context): TextView = TextView(context)

    override fun createLineView(context: Context): View = View(context)

    override fun getChildHorizontalPadding(context: Context): Int =
        TypedValue.applyDimension(1, 12.0f, context.resources.displayMetrics).toInt()

    override fun getChildVerticalPadding(context: Context): Int =
        TypedValue.applyDimension(1, 15.0f, context.resources.displayMetrics).toInt()

    override fun getTitle(context: Context): CharSequence {
        if (context !is Activity) {
            return ""
        }
        val title = context.title
        if (TextUtils.isEmpty(title)) {
            return ""
        }
        return try {
            val packageManager = context.packageManager
            if (!title.toString().equals(
                    packageManager.getPackageInfo(context.packageName, 0).applicationInfo
                        .loadLabel(packageManager).toString()
                )
            ) {
                title
            } else {
                ""
            }
        } catch (_: PackageManager.NameNotFoundException) {
            ""
        }
    }

    override fun getTitleSize(context: Context): Float =
        TypedValue.applyDimension(2, 16.0f, context.resources.displayMetrics)

    override fun getLeftTitleSize(context: Context): Float =
        TypedValue.applyDimension(2, 14.0f, context.resources.displayMetrics)

    override fun getRightTitleSize(context: Context): Float =
        TypedValue.applyDimension(2, 14.0f, context.resources.displayMetrics)

    override fun getTitleIconPadding(context: Context): Int =
        TypedValue.applyDimension(1, 2.0f, context.resources.displayMetrics).toInt()

    override fun getLeftIconPadding(context: Context): Int =
        TypedValue.applyDimension(1, 2.0f, context.resources.displayMetrics).toInt()

    override fun getRightIconPadding(context: Context): Int =
        TypedValue.applyDimension(1, 2.0f, context.resources.displayMetrics).toInt()
}
