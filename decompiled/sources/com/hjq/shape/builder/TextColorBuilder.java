package com.hjq.shape.builder;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.widget.TextView;
import com.hjq.shape.other.LinearGradientFontSpan;
import com.hjq.shape.styleable.ITextColorStyleable;

public final class TextColorBuilder {
    private Integer mTextCheckedColor;
    private int mTextColor;
    private Integer mTextDisabledColor;
    private Integer mTextFocusedColor;
    private int[] mTextGradientColor;
    private int mTextGradientOrientation;
    private Integer mTextPressedColor;
    private Integer mTextSelectedColor;
    private final TextView mTextView;

    public TextColorBuilder(TextView textView, TypedArray typedArray, ITextColorStyleable iTextColorStyleable) {
        this.mTextView = textView;
        this.mTextColor = typedArray.getColor(iTextColorStyleable.getTextColorStyleable(), textView.getTextColors().getDefaultColor());
        if (typedArray.hasValue(iTextColorStyleable.getTextPressedColorStyleable())) {
            this.mTextPressedColor = Integer.valueOf(typedArray.getColor(iTextColorStyleable.getTextPressedColorStyleable(), this.mTextColor));
        }
        if (iTextColorStyleable.getTextCheckedColorStyleable() > 0 && typedArray.hasValue(iTextColorStyleable.getTextCheckedColorStyleable())) {
            this.mTextCheckedColor = Integer.valueOf(typedArray.getColor(iTextColorStyleable.getTextCheckedColorStyleable(), this.mTextColor));
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextDisabledColorStyleable())) {
            this.mTextDisabledColor = Integer.valueOf(typedArray.getColor(iTextColorStyleable.getTextDisabledColorStyleable(), this.mTextColor));
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextFocusedColorStyleable())) {
            this.mTextFocusedColor = Integer.valueOf(typedArray.getColor(iTextColorStyleable.getTextFocusedColorStyleable(), this.mTextColor));
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextSelectedColorStyleable())) {
            this.mTextSelectedColor = Integer.valueOf(typedArray.getColor(iTextColorStyleable.getTextSelectedColorStyleable(), this.mTextColor));
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextStartColorStyleable()) && typedArray.hasValue(iTextColorStyleable.getTextEndColorStyleable())) {
            if (typedArray.hasValue(iTextColorStyleable.getTextCenterColorStyleable())) {
                this.mTextGradientColor = new int[]{typedArray.getColor(iTextColorStyleable.getTextStartColorStyleable(), this.mTextColor), typedArray.getColor(iTextColorStyleable.getTextCenterColorStyleable(), this.mTextColor), typedArray.getColor(iTextColorStyleable.getTextEndColorStyleable(), this.mTextColor)};
            } else {
                this.mTextGradientColor = new int[]{typedArray.getColor(iTextColorStyleable.getTextStartColorStyleable(), this.mTextColor), typedArray.getColor(iTextColorStyleable.getTextEndColorStyleable(), this.mTextColor)};
            }
        }
        this.mTextGradientOrientation = typedArray.getColor(iTextColorStyleable.getTextGradientOrientationStyleable(), 0);
    }

    public TextColorBuilder setTextColor(Integer num) {
        this.mTextColor = num.intValue();
        clearTextGradientColor();
        return this;
    }

    public Integer getTextColor() {
        return Integer.valueOf(this.mTextColor);
    }

    public TextColorBuilder setTextPressedColor(Integer num) {
        this.mTextPressedColor = num;
        return this;
    }

    public Integer getTextPressedColor() {
        return this.mTextPressedColor;
    }

    public TextColorBuilder setTextCheckedColor(Integer num) {
        this.mTextCheckedColor = num;
        return this;
    }

    public Integer getTextCheckedColor() {
        return this.mTextCheckedColor;
    }

    public TextColorBuilder setTextDisabledColor(Integer num) {
        this.mTextDisabledColor = num;
        return this;
    }

    public Integer getTextDisabledColor() {
        return this.mTextDisabledColor;
    }

    public TextColorBuilder setTextFocusedColor(Integer num) {
        this.mTextFocusedColor = num;
        return this;
    }

    public Integer getTextFocusedColor() {
        return this.mTextFocusedColor;
    }

    public TextColorBuilder setTextSelectedColor(Integer num) {
        this.mTextSelectedColor = num;
        return this;
    }

    public Integer getTextSelectedColor() {
        return this.mTextSelectedColor;
    }

    public TextColorBuilder setTextGradientColor(int[] iArr) {
        this.mTextGradientColor = iArr;
        return this;
    }

    public int[] getTextGradientColor() {
        return this.mTextGradientColor;
    }

    public TextColorBuilder setTextGradientOrientation(int i) {
        this.mTextGradientOrientation = i;
        return this;
    }

    public Integer getTextGradientOrientation() {
        return Integer.valueOf(this.mTextGradientOrientation);
    }

    public boolean isTextGradientColor() {
        int[] iArr = this.mTextGradientColor;
        return iArr != null && iArr.length > 0;
    }

    public void clearTextGradientColor() {
        this.mTextGradientColor = null;
    }

    public SpannableStringBuilder buildLinearGradientSpannable(CharSequence charSequence) {
        return LinearGradientFontSpan.buildLinearGradientSpannable(charSequence, this.mTextGradientColor, (float[]) null, this.mTextGradientOrientation);
    }

    public ColorStateList buildColorState() {
        int i;
        Integer num = this.mTextPressedColor;
        if (num == null && this.mTextCheckedColor == null && this.mTextDisabledColor == null && this.mTextFocusedColor == null && this.mTextSelectedColor == null) {
            return ColorStateList.valueOf(this.mTextColor);
        }
        int[][] iArr = new int[6][];
        int[] iArr2 = new int[6];
        if (num != null) {
            iArr[0] = new int[]{16842919};
            iArr2[0] = num.intValue();
            i = 1;
        } else {
            i = 0;
        }
        Integer num2 = this.mTextCheckedColor;
        if (num2 != null) {
            iArr[i] = new int[]{16842912};
            iArr2[i] = num2.intValue();
            i++;
        }
        Integer num3 = this.mTextDisabledColor;
        if (num3 != null) {
            iArr[i] = new int[]{-16842910};
            iArr2[i] = num3.intValue();
            i++;
        }
        Integer num4 = this.mTextFocusedColor;
        if (num4 != null) {
            iArr[i] = new int[]{16842908};
            iArr2[i] = num4.intValue();
            i++;
        }
        Integer num5 = this.mTextSelectedColor;
        if (num5 != null) {
            iArr[i] = new int[]{16842913};
            iArr2[i] = num5.intValue();
            i++;
        }
        iArr[i] = new int[0];
        iArr2[i] = this.mTextColor;
        int i2 = i + 1;
        if (i2 != 6) {
            int[][] iArr3 = new int[i2][];
            int[] iArr4 = new int[i2];
            System.arraycopy(iArr, 0, iArr3, 0, i2);
            System.arraycopy(iArr2, 0, iArr4, 0, i2);
            iArr = iArr3;
            iArr2 = iArr4;
        }
        return new ColorStateList(iArr, iArr2);
    }

    public void intoTextColor() {
        if (isTextGradientColor()) {
            TextView textView = this.mTextView;
            textView.setText(buildLinearGradientSpannable(textView.getText()));
            return;
        }
        this.mTextView.setTextColor(buildColorState());
    }
}
