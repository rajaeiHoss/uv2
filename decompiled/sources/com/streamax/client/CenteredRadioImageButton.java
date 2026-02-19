package com.streamax.client;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.zycs.UView.R;

public class CenteredRadioImageButton extends RadioButton {
    Drawable image;

    public CenteredRadioImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CompoundButton, 0, 0);
        this.image = obtainStyledAttributes.getDrawable(1);
        setButtonDrawable(17170445);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        Drawable drawable = this.image;
        if (drawable != null) {
            drawable.setState(getDrawableState());
            int intrinsicHeight = this.image.getIntrinsicHeight();
            int intrinsicWidth = this.image.getIntrinsicWidth();
            int width = getWidth();
            int height = getHeight();
            if (intrinsicWidth > width || intrinsicHeight > height) {
                f = Math.min(((float) width) / ((float) intrinsicWidth), ((float) height) / ((float) intrinsicHeight));
            } else {
                f = 1.0f;
            }
            float f2 = ((float) intrinsicWidth) * f;
            int i = (int) (((((float) width) - f2) * 0.5f) + 0.5f);
            float f3 = ((float) intrinsicHeight) * f;
            int i2 = (int) (((((float) height) - f3) * 0.5f) + 0.5f);
            this.image.setBounds(i, i2, (int) (((float) i) + f2), (int) (((float) i2) + f3));
            this.image.draw(canvas);
        }
    }
}
