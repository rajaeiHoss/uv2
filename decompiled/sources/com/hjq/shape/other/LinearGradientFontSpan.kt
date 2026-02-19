package com.hjq.shape.other

import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.text.SpannableStringBuilder
import android.text.style.ReplacementSpan

class LinearGradientFontSpan : ReplacementSpan() {
    private var mMeasureTextWidth = 0f
    private var mTextGradientColors: IntArray? = null
    private var mTextGradientOrientation = 0
    private var mTextGradientPositions: FloatArray? = null

    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        mMeasureTextWidth = paint.measureText(text, start, end)
        val fontMetrics = paint.fontMetricsInt
        if (fm != null) {
            fm.top = fontMetrics.top
            fm.ascent = fontMetrics.ascent
            fm.descent = fontMetrics.descent
            fm.bottom = fontMetrics.bottom
        }
        return mMeasureTextWidth.toInt()
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val linearGradient =
            if (mTextGradientOrientation == GRADIENT_ORIENTATION_VERTICAL) {
                LinearGradient(
                    0f,
                    0f,
                    0f,
                    paint.descent() - paint.ascent(),
                    mTextGradientColors!!,
                    mTextGradientPositions,
                    Shader.TileMode.REPEAT
                )
            } else {
                LinearGradient(
                    x,
                    0f,
                    x + mMeasureTextWidth,
                    0f,
                    mTextGradientColors!!,
                    mTextGradientPositions,
                    Shader.TileMode.REPEAT
                )
            }
        paint.shader = linearGradient
        val alpha = paint.alpha
        if (alpha != 255) {
            paint.alpha = 255
        }
        canvas.drawText(text, start, end, x, y.toFloat(), paint)
        paint.alpha = alpha
    }

    fun setTextGradientOrientation(orientation: Int): LinearGradientFontSpan {
        mTextGradientOrientation = orientation
        return this
    }

    fun setTextGradientColor(colors: IntArray?): LinearGradientFontSpan {
        mTextGradientColors = colors
        return this
    }

    fun setTextGradientPositions(positions: FloatArray?): LinearGradientFontSpan {
        mTextGradientPositions = positions
        return this
    }

    companion object {
        const val GRADIENT_ORIENTATION_HORIZONTAL = 0
        const val GRADIENT_ORIENTATION_VERTICAL = 1

        @JvmStatic
        fun buildLinearGradientSpannable(
            text: CharSequence,
            colors: IntArray?,
            positions: FloatArray?,
            orientation: Int
        ): SpannableStringBuilder {
            val spannable = SpannableStringBuilder(text)
            val span =
                LinearGradientFontSpan()
                    .setTextGradientColor(colors)
                    .setTextGradientOrientation(orientation)
                    .setTextGradientPositions(positions)
            spannable.setSpan(span, 0, spannable.length, 33)
            return spannable
        }
    }
}
