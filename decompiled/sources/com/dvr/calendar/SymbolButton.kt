package com.dvr.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.widget.Button

class SymbolButton(context: Context, symbol2: symbol) : Button(context) {
    private val pt = Paint()
    private val rect = RectF()
    private val rectDraw = RectF()
    private var symbolType: symbol = symbol.none

    enum class symbol {
        none,
        arrowLeft,
        arrowRight
    }

    init {
        symbolType = symbol2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        pt.isAntiAlias = true
        pt.strokeCap = Paint.Cap.ROUND
        rectDraw.set(0.0f, 0.0f, width.toFloat(), height.toFloat())
        rectDraw.left += 6.0f
        rectDraw.right -= 6.0f
        rectDraw.top += 4.0f
        rectDraw.bottom -= 8.0f
        if (symbolType != symbol.none) {
            pt.strokeWidth = 5.0f
            pt.color = iColor
            if (isPressed || isFocused) {
                pt.color = iColorActive
            }
            drawArrow(canvas)
        }
    }

    private fun drawArrow(canvas: Canvas) {
        rect.set(rectDraw)
        rect.inset(15.0f, 5.0f)
        canvas.drawLine(rect.left, rect.centerY(), rect.right, rect.centerY(), pt)
        if (symbolType == symbol.arrowRight) {
            canvas.drawLine(rect.right, rect.centerY(), rect.right - 6.0f, rect.top, pt)
            canvas.drawLine(rect.right, rect.centerY(), rect.right - 6.0f, rect.bottom, pt)
        }
        if (symbolType == symbol.arrowLeft) {
            canvas.drawLine(rect.left, rect.centerY(), rect.left + 6.0f, rect.top, pt)
            canvas.drawLine(rect.left, rect.centerY(), rect.left + 6.0f, rect.bottom, pt)
        }
    }

    companion object {
        private const val iColor = -5592406
        private const val iColorActive = -12312064
    }
}
