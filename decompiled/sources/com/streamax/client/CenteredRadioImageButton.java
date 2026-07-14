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
        float scale;
        super.onDraw(canvas);
        Drawable drawable = this.image;
        if (drawable != null) {
            drawable.setState(getDrawableState());
            int intrinsicHeight = this.image.getIntrinsicHeight();
            int intrinsicWidth = this.image.getIntrinsicWidth();
            int width = getWidth();
            int height = getHeight();
            if (intrinsicWidth > width || intrinsicHeight > height) {
                scale = Math.min(((float) width) / ((float) intrinsicWidth), ((float) height) / ((float) intrinsicHeight));
            } else {
                scale = 1.0f;
            }
            float scaledWidth = ((float) intrinsicWidth) * scale;
            int left = (int) (((((float) width) - scaledWidth) * 0.5f) + 0.5f);
            float scaledHeight = ((float) intrinsicHeight) * scale;
            int top = (int) (((((float) height) - scaledHeight) * 0.5f) + 0.5f);
            this.image.setBounds(left, top, (int) (((float) left) + scaledWidth), (int) (((float) top) + scaledHeight));
            this.image.draw(canvas);
        }
    }
}
