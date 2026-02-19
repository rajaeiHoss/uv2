package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.builder.TextColorBuilder;
import com.hjq.shape.styleable.ShapeTextViewStyleable;

public class ShapeTextView extends AppCompatTextView {
    private static final ShapeTextViewStyleable STYLEABLE = new ShapeTextViewStyleable();
    private final ShapeDrawableBuilder mShapeDrawableBuilder;
    private final TextColorBuilder mTextColorBuilder;

    public ShapeTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ShapeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public ShapeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeTextView);
        ShapeTextViewStyleable shapeTextViewStyleable = STYLEABLE;
        ShapeDrawableBuilder shapeDrawableBuilder = new ShapeDrawableBuilder(this, obtainStyledAttributes, shapeTextViewStyleable);
        this.mShapeDrawableBuilder = shapeDrawableBuilder;
        TextColorBuilder textColorBuilder = new TextColorBuilder(this, obtainStyledAttributes, shapeTextViewStyleable);
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
