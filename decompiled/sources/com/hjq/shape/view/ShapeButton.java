package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.builder.TextColorBuilder;
import com.hjq.shape.styleable.ShapeButtonStyleable;

public class ShapeButton extends AppCompatButton {
    private static final ShapeButtonStyleable STYLEABLE = new ShapeButtonStyleable();
    private final ShapeDrawableBuilder mShapeDrawableBuilder;
    private final TextColorBuilder mTextColorBuilder;

    public ShapeButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public ShapeButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShapeButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeButton);
        ShapeButtonStyleable shapeButtonStyleable = STYLEABLE;
        ShapeDrawableBuilder shapeDrawableBuilder = new ShapeDrawableBuilder(this, obtainStyledAttributes, shapeButtonStyleable);
        this.mShapeDrawableBuilder = shapeDrawableBuilder;
        TextColorBuilder textColorBuilder = new TextColorBuilder(this, obtainStyledAttributes, shapeButtonStyleable);
        this.mTextColorBuilder = textColorBuilder;
        obtainStyledAttributes.recycle();
        shapeDrawableBuilder.intoBackground();
        if (textColorBuilder.isTextGradientColor()) {
            setText(getText());
        } else {
            textColorBuilder.intoTextColor();
        }
    }

    public void setTextColor(int i) {
        super.setTextColor(i);
        TextColorBuilder textColorBuilder = this.mTextColorBuilder;
        if (textColorBuilder != null) {
            textColorBuilder.setTextColor(Integer.valueOf(i));
            this.mTextColorBuilder.clearTextGradientColor();
        }
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        TextColorBuilder textColorBuilder = this.mTextColorBuilder;
        if (textColorBuilder == null || !textColorBuilder.isTextGradientColor()) {
            super.setText(charSequence, bufferType);
        } else {
            super.setText(this.mTextColorBuilder.buildLinearGradientSpannable(charSequence), bufferType);
        }
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return this.mShapeDrawableBuilder;
    }

    public TextColorBuilder getTextColorBuilder() {
        return this.mTextColorBuilder;
    }
}
