package com.dvr.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.Typeface
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.LinearLayout
import java.util.Calendar

class DateWidgetDayCell(context: Context, i: Int, i2: Int) : View(context) {
    private var bHoliday = false
    private var bIsActiveMonth = false
    private var bRecordFileDay = false
    private var bSelected = false
    private var bToday = false
    private var bTouchedDown = false
    private var iDateDay = 0
    private var iDateMonth = 0
    private var iDateYear = 0
    private var itemClick: OnItemClick? = null
    private val pt = Paint()
    private val rect = RectF()
    private var sDate = ""

    interface OnItemClick {
        fun OnClick(dateWidgetDayCell: DateWidgetDayCell)
    }

    init {
        isFocusable = true
        layoutParams = LinearLayout.LayoutParams(i, i2)
    }

    fun getSelected(): Boolean = bSelected

    override fun setSelected(z: Boolean) {
        if (bSelected != z) {
            bSelected = z
            invalidate()
        }
    }

    fun SetRecordDay(z: Boolean) {
        bRecordFileDay = z
        invalidate()
    }

    fun setData(i: Int, i2: Int, i3: Int, z: Boolean, z2: Boolean, i4: Int) {
        iDateYear = i
        iDateMonth = i2
        iDateDay = i3
        sDate = i3.toString()
        bIsActiveMonth = iDateMonth == i4
        bToday = z
        bHoliday = z2
    }

    fun setItemClick(onItemClick: OnItemClick?) {
        itemClick = onItemClick
    }

    private fun getTextHeight(): Int = (-pt.ascent() + pt.descent()).toInt()

    override fun onKeyDown(i: Int, keyEvent: KeyEvent): Boolean {
        val onKeyDown = super.onKeyDown(i, keyEvent)
        if (i == 23 || i == 66) {
            doItemClick()
        }
        return onKeyDown
    }

    override fun onKeyUp(i: Int, keyEvent: KeyEvent): Boolean = super.onKeyUp(i, keyEvent)

    fun doItemClick() {
        itemClick?.OnClick(this)
    }

    override fun onFocusChanged(z: Boolean, i: Int, rect2: Rect?) {
        super.onFocusChanged(z, i, rect2)
        invalidate()
    }

    fun getDate(): Calendar {
        val instance = Calendar.getInstance()
        instance.clear()
        instance.set(1, iDateYear)
        instance.set(2, iDateMonth)
        instance.set(5, iDateDay)
        return instance
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rect.set(0.0f, 0.0f, width.toFloat(), height.toFloat())
        rect.inset(1.0f, 1.0f)
        val isViewFocused = IsViewFocused()
        DrawRecordInfo(canvas, bRecordFileDay)
        drawDayNumber(canvas, isViewFocused)
    }

    private fun DrawRecordInfo(canvas: Canvas, z: Boolean) {
        if (z) {
            pt.shader = LinearGradient(
                rect.left,
                0.0f,
                rect.right,
                0.0f,
                DayStyle.iColorBkgSelectedDark,
                DayStyle.iColorBkgSelectedLight,
                Shader.TileMode.CLAMP
            )
            canvas.drawRect(rect, pt)
            pt.shader = null
            return
        }
        pt.color = DayStyle.getColorBkg(bHoliday, bToday)
        if (!bIsActiveMonth) {
            pt.alpha = 136
        }
        canvas.drawRect(rect, pt)
    }

    fun drawDayNumber(canvas: Canvas, z: Boolean) {
        pt.typeface = null as Typeface?
        pt.isAntiAlias = true
        pt.shader = null
        pt.isFakeBoldText = true
        pt.textSize = fTextSize
        pt.isUnderlineText = false
        if (bToday) {
            pt.isUnderlineText = true
        }
        val measureText = rect.right.toInt() - pt.measureText(sDate).toInt()
        val textHeight = rect.bottom.toInt() + (-pt.ascent()).toInt() - getTextHeight()
        val width = measureText - ((rect.width().toInt() shr 1) - (pt.measureText(sDate).toInt() shr 1))
        val height = textHeight - ((rect.height().toInt() shr 1) - (getTextHeight() shr 1))
        val z2 = bSelected
        if (z2 || z) {
            if (z2) {
                pt.color = DayStyle.iColorTextSelected
            }
            if (z) {
                pt.color = DayStyle.iColorTextFocused
            }
        } else {
            pt.color = DayStyle.getColorText(bHoliday, bToday)
        }
        if (!bIsActiveMonth) {
            pt.alpha = 136
        }
        canvas.drawText(sDate, width.toFloat(), (height + 1).toFloat(), pt)
        pt.isUnderlineText = false
    }

    fun IsViewFocused(): Boolean = isFocused || bTouchedDown

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        var z = false
        if (motionEvent.action == 0) {
            bTouchedDown = true
            invalidate()
            startAlphaAnimIn(this)
            z = true
        }
        if (motionEvent.action == 3) {
            bTouchedDown = false
            invalidate()
            z = true
        }
        if (motionEvent.action != 1) {
            return z
        }
        bTouchedDown = false
        invalidate()
        doItemClick()
        return true
    }

    companion object {
        @JvmField
        var ANIM_ALPHA_DURATION: Int = 100

        private const val fTextSize = 24.0f

        @JvmStatic
        fun startAlphaAnimIn(view: View) {
            val alphaAnimation = AlphaAnimation(0.5f, 1.0f)
            alphaAnimation.duration = ANIM_ALPHA_DURATION.toLong()
            alphaAnimation.startNow()
            view.startAnimation(alphaAnimation)
        }
    }
}
