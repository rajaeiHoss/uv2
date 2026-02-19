package com.hjq.widget.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.hjq.widget.R;

public final class SettingBar extends FrameLayout {
    public static final int NO_COLOR = 0;
    private int mLeftDrawableSize;
    private int mLeftDrawableTint;
    private final TextView mLeftView;
    private final View mLineView;
    private final LinearLayout mMainLayout;
    private int mRightDrawableSize;
    private int mRightDrawableTint;
    private final TextView mRightView;

    public SettingBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public SettingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SettingBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SettingBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        int i3;
        int i4;
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.mMainLayout = linearLayout;
        TextView textView = new TextView(getContext());
        this.mLeftView = textView;
        TextView textView2 = new TextView(getContext());
        this.mRightView = textView2;
        View view = new View(getContext());
        this.mLineView = view;
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 16));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.gravity = 16;
        layoutParams.weight = 1.0f;
        textView.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 16;
        textView2.setLayoutParams(layoutParams2);
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, 1, 80));
        textView.setGravity(8388627);
        textView2.setGravity(8388629);
        textView.setSingleLine(true);
        textView2.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView.setLineSpacing(getResources().getDimension(R.dimen.dp_5), textView.getLineSpacingMultiplier());
        textView2.setLineSpacing(getResources().getDimension(R.dimen.dp_5), textView2.getLineSpacingMultiplier());
        textView.setPaddingRelative((int) getResources().getDimension(R.dimen.dp_15), (int) getResources().getDimension(R.dimen.dp_12), (int) getResources().getDimension(R.dimen.dp_15), (int) getResources().getDimension(R.dimen.dp_12));
        textView2.setPaddingRelative((int) getResources().getDimension(R.dimen.dp_15), (int) getResources().getDimension(R.dimen.dp_12), (int) getResources().getDimension(R.dimen.dp_15), (int) getResources().getDimension(R.dimen.dp_12));
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SettingBar);
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_leftText)) {
            setLeftText((CharSequence) obtainStyledAttributes.getString(R.styleable.SettingBar_bar_leftText));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_rightText)) {
            setRightText((CharSequence) obtainStyledAttributes.getString(R.styleable.SettingBar_bar_rightText));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_leftTextHint)) {
            setLeftTextHint((CharSequence) obtainStyledAttributes.getString(R.styleable.SettingBar_bar_leftTextHint));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_rightTextHint)) {
            setRightTextHint((CharSequence) obtainStyledAttributes.getString(R.styleable.SettingBar_bar_rightTextHint));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_leftDrawableSize)) {
            setLeftDrawableSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.SettingBar_bar_leftDrawableSize, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_rightDrawableSize)) {
            setRightDrawableSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.SettingBar_bar_rightDrawableSize, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_leftDrawableTint)) {
            setLeftDraawbleTint(obtainStyledAttributes.getColor(R.styleable.SettingBar_bar_leftDrawableTint, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_rightDrawableTint)) {
            setRightDrawableTint(obtainStyledAttributes.getColor(R.styleable.SettingBar_bar_rightDrawableTint, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_leftDrawablePadding)) {
            i3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SettingBar_bar_leftDrawablePadding, 0);
        } else {
            i3 = (int) getResources().getDimension(R.dimen.dp_10);
        }
        setLeftDrawablePadding(i3);
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_rightDrawablePadding)) {
            i4 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SettingBar_bar_rightDrawablePadding, 0);
        } else {
            i4 = (int) getResources().getDimension(R.dimen.dp_10);
        }
        setRightDrawablePadding(i4);
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_leftDrawable)) {
            setLeftDrawable(obtainStyledAttributes.getDrawable(R.styleable.SettingBar_bar_leftDrawable));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_rightDrawable)) {
            setRightDrawable(obtainStyledAttributes.getDrawable(R.styleable.SettingBar_bar_rightDrawable));
        }
        setLeftTextColor(obtainStyledAttributes.getColor(R.styleable.SettingBar_bar_leftTextColor, ContextCompat.getColor(getContext(), R.color.black80)));
        setRightTextColor(obtainStyledAttributes.getColor(R.styleable.SettingBar_bar_rightTextColor, ContextCompat.getColor(getContext(), R.color.black60)));
        setLeftTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.SettingBar_bar_leftTextSize, (int) getResources().getDimension(R.dimen.sp_15)));
        setRightTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.SettingBar_bar_rightTextSize, (int) getResources().getDimension(R.dimen.sp_14)));
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_lineDrawable)) {
            setLineDrawable(obtainStyledAttributes.getDrawable(R.styleable.SettingBar_bar_lineDrawable));
        } else {
            setLineDrawable(new ColorDrawable(-1250068));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_lineVisible)) {
            setLineVisible(obtainStyledAttributes.getBoolean(R.styleable.SettingBar_bar_lineVisible, true));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_lineSize)) {
            setLineSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.SettingBar_bar_lineSize, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SettingBar_bar_lineMargin)) {
            setLineMargin(obtainStyledAttributes.getDimensionPixelSize(R.styleable.SettingBar_bar_lineMargin, 0));
        }
        if (getBackground() == null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(ContextCompat.getColor(getContext(), R.color.black5)));
            stateListDrawable.addState(new int[]{16842913}, new ColorDrawable(ContextCompat.getColor(getContext(), R.color.black5)));
            stateListDrawable.addState(new int[]{16842908}, new ColorDrawable(ContextCompat.getColor(getContext(), R.color.black5)));
            stateListDrawable.addState(new int[0], new ColorDrawable(ContextCompat.getColor(getContext(), R.color.white)));
            setBackground(stateListDrawable);
            setFocusable(true);
            setClickable(true);
        }
        obtainStyledAttributes.recycle();
        linearLayout.addView(textView);
        linearLayout.addView(textView2);
        addView(linearLayout, 0);
        addView(view, 1);
    }

    public SettingBar setLeftText(int i) {
        return setLeftText((CharSequence) getResources().getString(i));
    }

    public SettingBar setLeftText(CharSequence charSequence) {
        this.mLeftView.setText(charSequence);
        return this;
    }

    public CharSequence getLeftText() {
        return this.mLeftView.getText();
    }

    public SettingBar setLeftTextHint(int i) {
        return setLeftTextHint((CharSequence) getResources().getString(i));
    }

    public SettingBar setLeftTextHint(CharSequence charSequence) {
        this.mLeftView.setHint(charSequence);
        return this;
    }

    public SettingBar setRightText(int i) {
        setRightText((CharSequence) getResources().getString(i));
        return this;
    }

    public SettingBar setRightText(CharSequence charSequence) {
        this.mRightView.setText(charSequence);
        return this;
    }

    public CharSequence getRightText() {
        return this.mRightView.getText();
    }

    public SettingBar setRightTextHint(int i) {
        return setRightTextHint((CharSequence) getResources().getString(i));
    }

    public SettingBar setRightTextHint(CharSequence charSequence) {
        this.mRightView.setHint(charSequence);
        return this;
    }

    public SettingBar setLeftDrawable(int i) {
        setLeftDrawable(ContextCompat.getDrawable(getContext(), i));
        return this;
    }

    public SettingBar setLeftDrawable(Drawable drawable) {
        this.mLeftView.setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        setLeftDrawableSize(this.mLeftDrawableSize);
        setLeftDraawbleTint(this.mLeftDrawableTint);
        return this;
    }

    public Drawable getLeftDrawable() {
        return this.mLeftView.getCompoundDrawables()[0];
    }

    public SettingBar setRightDrawable(int i) {
        setRightDrawable(ContextCompat.getDrawable(getContext(), i));
        return this;
    }

    public SettingBar setRightDrawable(Drawable drawable) {
        this.mRightView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
        setRightDrawableSize(this.mRightDrawableSize);
        setRightDrawableTint(this.mRightDrawableTint);
        return this;
    }

    public Drawable getRightDrawable() {
        return this.mRightView.getCompoundDrawables()[2];
    }

    public SettingBar setLeftDrawablePadding(int i) {
        this.mLeftView.setCompoundDrawablePadding(i);
        return this;
    }

    public SettingBar setRightDrawablePadding(int i) {
        this.mRightView.setCompoundDrawablePadding(i);
        return this;
    }

    public SettingBar setLeftDrawableSize(int i) {
        this.mLeftDrawableSize = i;
        Drawable leftDrawable = getLeftDrawable();
        if (leftDrawable != null) {
            if (i > 0) {
                leftDrawable.setBounds(0, 0, i, i);
            } else {
                leftDrawable.setBounds(0, 0, leftDrawable.getIntrinsicWidth(), leftDrawable.getIntrinsicHeight());
            }
            this.mLeftView.setCompoundDrawables(leftDrawable, (Drawable) null, (Drawable) null, (Drawable) null);
        }
        return this;
    }

    public SettingBar setRightDrawableSize(int i) {
        this.mRightDrawableSize = i;
        Drawable rightDrawable = getRightDrawable();
        if (rightDrawable != null) {
            if (i > 0) {
                rightDrawable.setBounds(0, 0, i, i);
            } else {
                rightDrawable.setBounds(0, 0, rightDrawable.getIntrinsicWidth(), rightDrawable.getIntrinsicHeight());
            }
            this.mRightView.setCompoundDrawables((Drawable) null, (Drawable) null, rightDrawable, (Drawable) null);
        }
        return this;
    }

    public SettingBar setLeftDraawbleTint(int i) {
        this.mLeftDrawableTint = i;
        Drawable leftDrawable = getLeftDrawable();
        if (!(leftDrawable == null || i == 0)) {
            leftDrawable.mutate();
            leftDrawable.setColorFilter(i, PorterDuff.Mode.SRC_IN);
        }
        return this;
    }

    public SettingBar setRightDrawableTint(int i) {
        this.mRightDrawableTint = i;
        Drawable rightDrawable = getRightDrawable();
        if (!(rightDrawable == null || i == 0)) {
            rightDrawable.mutate();
            rightDrawable.setColorFilter(i, PorterDuff.Mode.SRC_IN);
        }
        return this;
    }

    public SettingBar setLeftTextColor(int i) {
        this.mLeftView.setTextColor(i);
        return this;
    }

    public SettingBar setRightTextColor(int i) {
        this.mRightView.setTextColor(i);
        return this;
    }

    public SettingBar setLeftTextSize(int i, float f) {
        this.mLeftView.setTextSize(i, f);
        return this;
    }

    public SettingBar setRightTextSize(int i, float f) {
        this.mRightView.setTextSize(i, f);
        return this;
    }

    public SettingBar setLineVisible(boolean z) {
        this.mLineView.setVisibility(z ? 0 : 8);
        return this;
    }

    public SettingBar setLineColor(int i) {
        return setLineDrawable(new ColorDrawable(i));
    }

    public SettingBar setLineDrawable(Drawable drawable) {
        this.mLineView.setBackground(drawable);
        return this;
    }

    public SettingBar setLineSize(int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mLineView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        layoutParams.height = i;
        this.mLineView.setLayoutParams(layoutParams);
        return this;
    }

    public SettingBar setLineMargin(int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mLineView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        layoutParams.leftMargin = i;
        layoutParams.rightMargin = i;
        this.mLineView.setLayoutParams(layoutParams);
        return this;
    }

    public LinearLayout getMainLayout() {
        return this.mMainLayout;
    }

    public TextView getLeftView() {
        return this.mLeftView;
    }

    public TextView getRightView() {
        return this.mRightView;
    }

    public View getLineView() {
        return this.mLineView;
    }
}
