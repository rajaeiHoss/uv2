package com.dvr.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.view.View
import android.widget.LinearLayout

class DateWidgetDayHeader(context: Context, width: Int, height: Int) : View(context) {
    private var bHoliday = false
    private var iWeekDay = -1

    @JvmField
    var mContext: Context = context

    private val pt = Paint()
    private val rect = RectF()

    init {
        layoutParams = LinearLayout.LayoutParams(width, height)
    }

    fun setData(weekDay: Int) {
        iWeekDay = weekDay
        bHoliday = false
        if (weekDay == 7 || weekDay == 1) {
            bHoliday = true
        }
    }

    private fun drawDayHeader(canvas: Canvas) {
        if (iWeekDay != -1) {
            pt.color = DayStyle.getColorFrameHeader(bHoliday)
            canvas.drawRect(rect, pt)
            pt.typeface = null as Typeface?
            pt.textSize = 16.0f
            pt.isAntiAlias = true
            pt.isFakeBoldText = true
            pt.color = DayStyle.getColorTextHeader(bHoliday)
            val textHeight = getTextHeight()
            val weekDayName = DayStyle.getWeekDayName(iWeekDay) ?: ""
            canvas.drawText(
                weekDayName,
                (((rect.left.toInt() + (rect.width().toInt() shr 1)) - (pt.measureText(weekDayName).toInt() shr 1)).toFloat()),
                rect.top + textHeight.toFloat() + 2.0f,
                pt
            )
        }
    }

    private fun getTextHeight(): Int = (-pt.ascent() + pt.descent()).toInt()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rect.set(0.0f, 0.0f, width.toFloat(), height.toFloat())
        rect.inset(1.0f, 1.0f)
        drawDayHeader(canvas)
    }
}
