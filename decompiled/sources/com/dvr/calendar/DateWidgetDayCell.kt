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

class DateWidgetDayCell(context: Context, width: Int, height: Int) : View(context) {
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
        layoutParams = LinearLayout.LayoutParams(width, height)
    }

    fun getSelected(): Boolean = bSelected

    override fun setSelected(selected: Boolean) {
        if (bSelected != selected) {
            bSelected = selected
            invalidate()
        }
    }

    fun SetRecordDay(hasRecord: Boolean) {
        bRecordFileDay = hasRecord
        invalidate()
    }

    fun setData(year: Int, month: Int, day: Int, isToday: Boolean, isHoliday: Boolean, activeMonth: Int) {
        iDateYear = year
        iDateMonth = month
        iDateDay = day
        sDate = day.toString()
        bIsActiveMonth = iDateMonth == activeMonth
        bToday = isToday
        bHoliday = isHoliday
    }

    fun setItemClick(onItemClick: OnItemClick?) {
        itemClick = onItemClick
    }

    private fun getTextHeight(): Int = (-pt.ascent() + pt.descent()).toInt()

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val onKeyDown = super.onKeyDown(keyCode, event)
        if (keyCode == 23 || keyCode == 66) {
            doItemClick()
        }
        return onKeyDown
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean = super.onKeyUp(keyCode, event)

    fun doItemClick() {
        itemClick?.OnClick(this)
    }

    override fun onFocusChanged(hasFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(hasFocus, direction, previouslyFocusedRect)
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

    private fun DrawRecordInfo(canvas: Canvas, hasRecord: Boolean) {
        if (hasRecord) {
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

    fun drawDayNumber(canvas: Canvas, focused: Boolean) {
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
        val selected = bSelected
        if (selected || focused) {
            if (selected) {
                pt.color = DayStyle.iColorTextSelected
            }
            if (focused) {
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
        var handled = false
        if (motionEvent.action == 0) {
            bTouchedDown = true
            invalidate()
            startAlphaAnimIn(this)
            handled = true
        }
        if (motionEvent.action == 3) {
            bTouchedDown = false
            invalidate()
            handled = true
        }
        if (motionEvent.action != 1) {
            return handled
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
