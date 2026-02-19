package com.hjq.shape.other;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.SpannableStringBuilder;
import android.text.style.ReplacementSpan;

public class LinearGradientFontSpan extends ReplacementSpan {
    public static final int GRADIENT_ORIENTATION_HORIZONTAL = 0;
    public static final int GRADIENT_ORIENTATION_VERTICAL = 1;
    private float mMeasureTextWidth;
    private int[] mTextGradientColors;
    private int mTextGradientOrientation;
    private float[] mTextGradientPositions;

    public static SpannableStringBuilder buildLinearGradientSpannable(CharSequence charSequence, int[] iArr, float[] fArr, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        LinearGradientFontSpan linearGradientFontSpan = new LinearGradientFontSpan();
        linearGradientFontSpan.setTextGradientColor(iArr);
        linearGradientFontSpan.setTextGradientOrientation(i);
        linearGradientFontSpan.setTextGradientPositions(fArr);
        spannableStringBuilder.setSpan(linearGradientFontSpan, 0, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        this.mMeasureTextWidth = paint.measureText(charSequence, i, i2);
        Paint.FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
        if (fontMetricsInt != null) {
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return (int) this.mMeasureTextWidth;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        LinearGradient linearGradient;
        Paint paint2 = paint;
        if (this.mTextGradientOrientation == 1) {
            linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, paint.descent() - paint.ascent(), this.mTextGradientColors, this.mTextGradientPositions, Shader.TileMode.REPEAT);
        } else {
            float f2 = f + this.mMeasureTextWidth;
            int[] iArr = this.mTextGradientColors;
            linearGradient = new LinearGradient(f, 0.0f, f2, 0.0f, iArr, this.mTextGradientPositions, Shader.TileMode.REPEAT);
        }
        paint2.setShader(linearGradient);
        int alpha = paint.getAlpha();
        if (alpha != 255) {
            paint2.setAlpha(255);
        }
        canvas.drawText(charSequence, i, i2, f, (float) i4, paint);
        paint2.setAlpha(alpha);
    }

    public LinearGradientFontSpan setTextGradientOrientation(int i) {
        this.mTextGradientOrientation = i;
        return this;
    }

    public LinearGradientFontSpan setTextGradientColor(int[] iArr) {
        this.mTextGradientColors = iArr;
        return this;
    }

    public LinearGradientFontSpan setTextGradientPositions(float[] fArr) {
        this.mTextGradientPositions = fArr;
        return this;
    }
}
