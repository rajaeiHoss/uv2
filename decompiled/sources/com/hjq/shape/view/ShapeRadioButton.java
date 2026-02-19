package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import com.hjq.shape.R;
import com.hjq.shape.builder.ButtonDrawableBuilder;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.builder.TextColorBuilder;
import com.hjq.shape.styleable.ShapeRadioButtonStyleable;

public class ShapeRadioButton extends AppCompatRadioButton {
    private static final ShapeRadioButtonStyleable STYLEABLE = new ShapeRadioButtonStyleable();
    private final ButtonDrawableBuilder mButtonDrawableBuilder;
    private final ShapeDrawableBuilder mShapeDrawableBuilder;
    private final TextColorBuilder mTextColorBuilder;

    public ShapeRadioButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public ShapeRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.radioButtonStyle);
    }

    public ShapeRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeRadioButton);
        ShapeRadioButtonStyleable shapeRadioButtonStyleable = STYLEABLE;
        ShapeDrawableBuilder shapeDrawableBuilder = new ShapeDrawableBuilder(this, obtainStyledAttributes, shapeRadioButtonStyleable);
        this.mShapeDrawableBuilder = shapeDrawableBuilder;
        TextColorBuilder textColorBuilder = new TextColorBuilder(this, obtainStyledAttributes, shapeRadioButtonStyleable);
        this.mTextColorBuilder = textColorBuilder;
        ButtonDrawableBuilder buttonDrawableBuilder = new ButtonDrawableBuilder(this, obtainStyledAttributes, shapeRadioButtonStyleable);
        this.mButtonDrawableBuilder = buttonDrawableBuilder;
        obtainStyledAttributes.recycle();
        shapeDrawableBuilder.intoBackground();
        if (textColorBuilder.isTextGradientColor()) {
            setText(getText());
        } else {
            textColorBuilder.intoTextColor();
        }
        buttonDrawableBuilder.intoButtonDrawable();
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

    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        ButtonDrawableBuilder buttonDrawableBuilder = this.mButtonDrawableBuilder;
        if (buttonDrawableBuilder != null) {
            buttonDrawableBuilder.setButtonDrawable(drawable);
        }
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return this.mShapeDrawableBuilder;
    }

    public TextColorBuilder getTextColorBuilder() {
        return this.mTextColorBuilder;
    }

    public ButtonDrawableBuilder getButtonDrawableBuilder() {
        return this.mButtonDrawableBuilder;
    }
}
