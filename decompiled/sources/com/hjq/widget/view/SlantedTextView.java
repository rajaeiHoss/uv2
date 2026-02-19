package com.hjq.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import androidx.core.view.GravityCompat;
import com.hjq.widget.R;

public final class SlantedTextView extends View {
    public static final int ROTATE_ANGLE = 45;
    private final Paint mBackgroundPaint;
    private int mColorBackground;
    private int mGravity;
    private String mText;
    private final Rect mTextBounds;
    private int mTextHeight;
    private final TextPaint mTextPaint;
    private boolean mTriangle;

    public SlantedTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SlantedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlantedTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mText = "";
        this.mTextBounds = new Rect();
        Paint paint = new Paint();
        this.mBackgroundPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        paint.setAntiAlias(true);
        TextPaint textPaint = new TextPaint(1);
        this.mTextPaint = textPaint;
        textPaint.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SlantedTextView);
        setText(obtainStyledAttributes.getString(R.styleable.SlantedTextView_android_text));
        setTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.SlantedTextView_android_textSize, (int) getResources().getDimension(R.dimen.sp_12)));
        setTextColor(obtainStyledAttributes.getColor(R.styleable.SlantedTextView_android_textColor, -1));
        setTextStyle(Typeface.defaultFromStyle(obtainStyledAttributes.getInt(R.styleable.SlantedTextView_android_textStyle, 0)));
        setGravity(obtainStyledAttributes.getInt(R.styleable.SlantedTextView_android_gravity, GravityCompat.END));
        setColorBackground(obtainStyledAttributes.getColor(R.styleable.SlantedTextView_android_colorBackground, getAccentColor()));
        setTriangle(obtainStyledAttributes.getBoolean(R.styleable.SlantedTextView_triangle, false));
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        TextPaint textPaint = this.mTextPaint;
        String str = this.mText;
        int i4 = 0;
        textPaint.getTextBounds(str, 0, str.length(), this.mTextBounds);
        this.mTextHeight = this.mTextBounds.height() + getPaddingTop() + getPaddingBottom();
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            i3 = this.mTextBounds.width() + getPaddingLeft() + getPaddingRight();
        } else if (mode != 1073741824) {
            i3 = 0;
        } else {
            i3 = View.MeasureSpec.getSize(i);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            i4 = this.mTextBounds.height() + getPaddingTop() + getPaddingBottom();
        } else if (mode2 == 1073741824) {
            i4 = View.MeasureSpec.getSize(i2);
        }
        setMeasuredDimension(Math.max(i3, i4), Math.max(i3, i4));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawText(canvas);
    }

    private void drawBackground(Canvas canvas) {
        Path path = new Path();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int i = this.mGravity;
        if (i != 0) {
            if (i != 3) {
                if (i != 5) {
                    if (i != 51) {
                        if (i != 53) {
                            if (i == 80 || i == 83) {
                                if (this.mTriangle) {
                                    float f = (float) height;
                                    path.lineTo((float) width, f);
                                    path.lineTo(0.0f, f);
                                } else {
                                    float f2 = (float) height;
                                    path.lineTo((float) width, f2);
                                    path.lineTo((float) (width - this.mTextHeight), f2);
                                    path.lineTo(0.0f, (float) this.mTextHeight);
                                }
                                path.close();
                                canvas.drawPath(path, this.mBackgroundPaint);
                                canvas.save();
                            } else if (i == 85) {
                                if (this.mTriangle) {
                                    float f3 = (float) height;
                                    path.moveTo(0.0f, f3);
                                    float f4 = (float) width;
                                    path.lineTo(f4, f3);
                                    path.lineTo(f4, 0.0f);
                                } else {
                                    float f5 = (float) height;
                                    path.moveTo(0.0f, f5);
                                    path.lineTo(((float) this.mTextHeight) * 1.0f, f5);
                                    float f6 = (float) width;
                                    path.lineTo(f6, (float) this.mTextHeight);
                                    path.lineTo(f6, 0.0f);
                                }
                                path.close();
                                canvas.drawPath(path, this.mBackgroundPaint);
                                canvas.save();
                            } else {
                                throw new IllegalArgumentException("are you ok?");
                            }
                        }
                    }
                }
            }
            if (this.mTriangle) {
                path.lineTo(0.0f, (float) height);
                path.lineTo((float) width, 0.0f);
            } else {
                path.moveTo((float) width, 0.0f);
                path.lineTo(0.0f, (float) height);
                path.lineTo(0.0f, (float) (height - this.mTextHeight));
                path.lineTo((float) (width - this.mTextHeight), 0.0f);
            }
            path.close();
            canvas.drawPath(path, this.mBackgroundPaint);
            canvas.save();
        }
        if (this.mTriangle) {
            float f7 = (float) width;
            path.lineTo(f7, 0.0f);
            path.lineTo(f7, (float) height);
        } else {
            float f8 = (float) width;
            path.lineTo(f8, (float) height);
            path.lineTo(f8, (float) (height - this.mTextHeight));
            path.lineTo(((float) this.mTextHeight) * 1.0f, 0.0f);
        }
        path.close();
        canvas.drawPath(path, this.mBackgroundPaint);
        canvas.save();
    }

    private void drawText(Canvas canvas) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        int width = canvas.getWidth() - (this.mTextHeight / 2);
        int height = canvas.getHeight();
        int i = this.mTextHeight;
        int i2 = height - (i / 2);
        int i3 = i / 2;
        int i4 = this.mGravity;
        float f6 = -45.0f;
        if (i4 != 0) {
            if (i4 != 3) {
                if (i4 != 5) {
                    if (i4 != 51) {
                        if (i4 != 53) {
                            if (i4 == 80 || i4 == 83) {
                                Rect rect = new Rect(0, i3, width, i2 + i3);
                                RectF rectF = new RectF(rect);
                                TextPaint textPaint = this.mTextPaint;
                                String str = this.mText;
                                rectF.right = textPaint.measureText(str, 0, str.length());
                                rectF.bottom = this.mTextPaint.descent() - this.mTextPaint.ascent();
                                rectF.left += (((float) rect.width()) - rectF.right) / 2.0f;
                                rectF.top += (((float) rect.height()) - rectF.bottom) / 2.0f;
                                f2 = rectF.left;
                                f5 = rectF.top - this.mTextPaint.ascent();
                                f4 = ((float) width) / 2.0f;
                                f3 = (((float) i2) / 2.0f) + ((float) i3);
                                f = f5;
                                f6 = 45.0f;
                                canvas.rotate(f6, f4, f3);
                                canvas.drawText(this.mText, f2, f, this.mTextPaint);
                            } else if (i4 == 85) {
                                Rect rect2 = new Rect(i3, i3, width + i3, i2 + i3);
                                RectF rectF2 = new RectF(rect2);
                                TextPaint textPaint2 = this.mTextPaint;
                                String str2 = this.mText;
                                rectF2.right = textPaint2.measureText(str2, 0, str2.length());
                                rectF2.bottom = this.mTextPaint.descent() - this.mTextPaint.ascent();
                                rectF2.left += (((float) rect2.width()) - rectF2.right) / 2.0f;
                                rectF2.top += (((float) rect2.height()) - rectF2.bottom) / 2.0f;
                                f2 = rectF2.left;
                                f = rectF2.top - this.mTextPaint.ascent();
                                float f7 = (float) i3;
                                f4 = (((float) width) / 2.0f) + f7;
                                f3 = (((float) i2) / 2.0f) + f7;
                                canvas.rotate(f6, f4, f3);
                                canvas.drawText(this.mText, f2, f, this.mTextPaint);
                            } else {
                                throw new IllegalArgumentException("are you ok?");
                            }
                        }
                    }
                }
            }
            Rect rect3 = new Rect(0, 0, width, i2);
            RectF rectF3 = new RectF(rect3);
            TextPaint textPaint3 = this.mTextPaint;
            String str3 = this.mText;
            rectF3.right = textPaint3.measureText(str3, 0, str3.length());
            rectF3.bottom = this.mTextPaint.descent() - this.mTextPaint.ascent();
            rectF3.left += (((float) rect3.width()) - rectF3.right) / 2.0f;
            rectF3.top += (((float) rect3.height()) - rectF3.bottom) / 2.0f;
            float f8 = rectF3.left;
            f = rectF3.top - this.mTextPaint.ascent();
            f4 = ((float) width) / 2.0f;
            f3 = ((float) i2) / 2.0f;
            f2 = f8;
            canvas.rotate(f6, f4, f3);
            canvas.drawText(this.mText, f2, f, this.mTextPaint);
        }
        Rect rect4 = new Rect(i3, 0, width + i3, i2);
        RectF rectF4 = new RectF(rect4);
        TextPaint textPaint4 = this.mTextPaint;
        String str4 = this.mText;
        rectF4.right = textPaint4.measureText(str4, 0, str4.length());
        rectF4.bottom = this.mTextPaint.descent() - this.mTextPaint.ascent();
        rectF4.left += (((float) rect4.width()) - rectF4.right) / 2.0f;
        rectF4.top += (((float) rect4.height()) - rectF4.bottom) / 2.0f;
        f2 = rectF4.left;
        f5 = rectF4.top - this.mTextPaint.ascent();
        f4 = (((float) width) / 2.0f) + ((float) i3);
        f3 = ((float) i2) / 2.0f;
        f = f5;
        f6 = 45.0f;
        canvas.rotate(f6, f4, f3);
        canvas.drawText(this.mText, f2, f, this.mTextPaint);
    }

    public String getText() {
        return this.mText;
    }

    public void setText(int i) {
        setText(getResources().getString(i));
    }

    public void setText(String str) {
        if (!TextUtils.isEmpty(str) && !getText().equals(str)) {
            this.mText = str;
            invalidate();
        }
    }

    public int getTextColor() {
        return this.mTextPaint.getColor();
    }

    public void setTextColor(int i) {
        if (getTextColor() != i) {
            this.mTextPaint.setColor(i);
            invalidate();
        }
    }

    public float getTextSize() {
        return this.mTextPaint.getTextSize();
    }

    public void setTextSize(float f) {
        setTextSize(2, f);
    }

    public void setTextSize(int i, float f) {
        float applyDimension = TypedValue.applyDimension(i, f, getResources().getDisplayMetrics());
        if (getTextSize() != applyDimension) {
            this.mTextPaint.setTextSize(applyDimension);
            invalidate();
        }
    }

    public Typeface getTextStyle() {
        return this.mTextPaint.getTypeface();
    }

    public void setTextStyle(Typeface typeface) {
        if (getTextStyle() != typeface) {
            this.mTextPaint.setTypeface(typeface);
            invalidate();
        }
    }

    public int getColorBackground() {
        return this.mColorBackground;
    }

    public void setColorBackground(int i) {
        if (getColorBackground() != i) {
            this.mColorBackground = i;
            this.mBackgroundPaint.setColor(i);
            invalidate();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            this.mGravity = Gravity.getAbsoluteGravity(i, getResources().getConfiguration().getLayoutDirection());
            invalidate();
        }
    }

    public boolean isTriangle() {
        return this.mTriangle;
    }

    public void setTriangle(boolean z) {
        if (isTriangle() != z) {
            this.mTriangle = z;
            invalidate();
        }
    }

    private int getAccentColor() {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        return typedValue.data;
    }
}
