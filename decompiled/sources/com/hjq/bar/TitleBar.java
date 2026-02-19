package com.hjq.bar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.hjq.bar.style.LightBarStyle;
import com.hjq.bar.style.NightBarStyle;
import com.hjq.bar.style.RippleBarStyle;
import com.hjq.bar.style.TransparentBarStyle;

public class TitleBar extends FrameLayout implements View.OnClickListener, View.OnLayoutChangeListener {
    private static final String LOG_TAG = "TitleBar";
    private static ITitleBarStyle sGlobalStyle;
    private final ITitleBarStyle mCurrentStyle;
    private int mHorizontalPadding;
    private int mLeftIconGravity;
    private int mLeftIconHeight;
    private int mLeftIconTint;
    private int mLeftIconWidth;
    private final TextView mLeftView;
    private final View mLineView;
    private OnTitleBarListener mListener;
    private int mRightIconGravity;
    private int mRightIconHeight;
    private int mRightIconTint;
    private int mRightIconWidth;
    private final TextView mRightView;
    private int mTitleIconGravity;
    private int mTitleIconHeight;
    private int mTitleIconTint;
    private int mTitleIconWidth;
    private final TextView mTitleView;
    private int mVerticalPadding;

    public TitleBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public TitleBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        float f;
        float f2;
        float f3;
        int i2;
        int i3;
        int i4;
        Drawable drawable;
        this.mRightIconTint = 0;
        if (sGlobalStyle == null) {
            sGlobalStyle = new LightBarStyle();
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TitleBar, 0, R.style.TitleBarStyle);
        int i5 = obtainStyledAttributes.getInt(R.styleable.TitleBar_barStyle, 0);
        if (i5 == 16) {
            this.mCurrentStyle = new LightBarStyle();
        } else if (i5 == 32) {
            this.mCurrentStyle = new NightBarStyle();
        } else if (i5 == 48) {
            this.mCurrentStyle = new TransparentBarStyle();
        } else if (i5 != 64) {
            this.mCurrentStyle = sGlobalStyle;
        } else {
            this.mCurrentStyle = new RippleBarStyle();
        }
        TextView createTitleView = this.mCurrentStyle.createTitleView(context);
        this.mTitleView = createTitleView;
        TextView createLeftView = this.mCurrentStyle.createLeftView(context);
        this.mLeftView = createLeftView;
        TextView createRightView = this.mCurrentStyle.createRightView(context);
        this.mRightView = createRightView;
        View createLineView = this.mCurrentStyle.createLineView(context);
        this.mLineView = createLineView;
        createTitleView.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 1));
        createLeftView.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        createRightView.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, GravityCompat.END));
        createLineView.setLayoutParams(new FrameLayout.LayoutParams(-1, this.mCurrentStyle.getLineSize(context), 80));
        setTitleIconGravity(obtainStyledAttributes.getInt(R.styleable.TitleBar_titleIconGravity, this.mCurrentStyle.getTitleIconGravity(context)));
        setLeftIconGravity(obtainStyledAttributes.getInt(R.styleable.TitleBar_leftIconGravity, this.mCurrentStyle.getLeftIconGravity(context)));
        setRightIconGravity(obtainStyledAttributes.getInt(R.styleable.TitleBar_rightIconGravity, this.mCurrentStyle.getRightIconGravity(context)));
        setTitleIconSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_titleIconWidth, this.mCurrentStyle.getTitleIconWidth(context)), obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_titleIconHeight, this.mCurrentStyle.getTitleIconHeight(context)));
        setLeftIconSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_leftIconWidth, this.mCurrentStyle.getLeftIconWidth(context)), obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_leftIconHeight, this.mCurrentStyle.getLeftIconHeight(context)));
        setRightIconSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_rightIconWidth, this.mCurrentStyle.getRightIconWidth(context)), obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_rightIconHeight, this.mCurrentStyle.getRightIconHeight(context)));
        setTitleIconPadding(obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_titleIconPadding, this.mCurrentStyle.getTitleIconPadding(context)));
        setLeftIconPadding(obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_leftIconPadding, this.mCurrentStyle.getLeftIconPadding(context)));
        setRightIconPadding(obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_rightIconPadding, this.mCurrentStyle.getRightIconPadding(context)));
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_title)) {
            setTitle(obtainStyledAttributes.getResourceId(R.styleable.TitleBar_title, 0) != R.string.bar_string_placeholder ? obtainStyledAttributes.getString(R.styleable.TitleBar_title) : this.mCurrentStyle.getTitle(context));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_leftTitle)) {
            setLeftTitle(obtainStyledAttributes.getResourceId(R.styleable.TitleBar_leftTitle, 0) != R.string.bar_string_placeholder ? obtainStyledAttributes.getString(R.styleable.TitleBar_leftTitle) : this.mCurrentStyle.getLeftTitle(context));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_rightTitle)) {
            setRightTitle(obtainStyledAttributes.getResourceId(R.styleable.TitleBar_rightTitle, 0) != R.string.bar_string_placeholder ? obtainStyledAttributes.getString(R.styleable.TitleBar_rightTitle) : this.mCurrentStyle.getRightTitle(context));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_titleIconTint)) {
            setTitleIconTint(obtainStyledAttributes.getColor(R.styleable.TitleBar_titleIconTint, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_leftIconTint)) {
            setLeftIconTint(obtainStyledAttributes.getColor(R.styleable.TitleBar_leftIconTint, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_rightIconTint)) {
            setRightIconTint(obtainStyledAttributes.getColor(R.styleable.TitleBar_rightIconTint, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_titleIcon)) {
            setTitleIcon(TitleBarSupport.getDrawable(context, obtainStyledAttributes.getResourceId(R.styleable.TitleBar_titleIcon, 0)));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_leftIcon)) {
            if (obtainStyledAttributes.getResourceId(R.styleable.TitleBar_leftIcon, 0) != R.drawable.bar_drawable_placeholder) {
                drawable = TitleBarSupport.getDrawable(context, obtainStyledAttributes.getResourceId(R.styleable.TitleBar_leftIcon, 0));
            } else {
                drawable = this.mCurrentStyle.getBackButtonDrawable(context);
            }
            setLeftIcon(drawable);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_rightIcon)) {
            setRightIcon(TitleBarSupport.getDrawable(context, obtainStyledAttributes.getResourceId(R.styleable.TitleBar_rightIcon, 0)));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_titleColor)) {
            colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.TitleBar_titleColor);
        } else {
            colorStateList = this.mCurrentStyle.getTitleColor(context);
        }
        setTitleColor(colorStateList);
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_leftTitleColor)) {
            colorStateList2 = obtainStyledAttributes.getColorStateList(R.styleable.TitleBar_leftTitleColor);
        } else {
            colorStateList2 = this.mCurrentStyle.getLeftTitleColor(context);
        }
        setLeftTitleColor(colorStateList2);
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_rightTitleColor)) {
            colorStateList3 = obtainStyledAttributes.getColorStateList(R.styleable.TitleBar_rightTitleColor);
        } else {
            colorStateList3 = this.mCurrentStyle.getRightTitleColor(context);
        }
        setRightTitleColor(colorStateList3);
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_titleSize)) {
            f = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_titleSize, 0);
        } else {
            f = this.mCurrentStyle.getTitleSize(context);
        }
        setTitleSize(0, f);
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_leftTitleSize)) {
            f2 = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_leftTitleSize, 0);
        } else {
            f2 = this.mCurrentStyle.getLeftTitleSize(context);
        }
        setLeftTitleSize(0, f2);
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_rightTitleSize)) {
            f3 = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_rightTitleSize, 0);
        } else {
            f3 = this.mCurrentStyle.getRightTitleSize(context);
        }
        setRightTitleSize(0, f3);
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_titleStyle)) {
            i2 = obtainStyledAttributes.getInt(R.styleable.TitleBar_titleStyle, 0);
        } else {
            i2 = this.mCurrentStyle.getTitleStyle(context);
        }
        setTitleStyle(i2);
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_leftTitleStyle)) {
            i3 = obtainStyledAttributes.getInt(R.styleable.TitleBar_leftTitleStyle, 0);
        } else {
            i3 = this.mCurrentStyle.getLeftTitleStyle(context);
        }
        setLeftTitleStyle(i3);
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_rightTitleStyle)) {
            i4 = obtainStyledAttributes.getInt(R.styleable.TitleBar_rightTitleStyle, 0);
        } else {
            i4 = this.mCurrentStyle.getRightTitleStyle(context);
        }
        setRightTitleStyle(i4);
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_titleGravity)) {
            setTitleGravity(obtainStyledAttributes.getInt(R.styleable.TitleBar_titleGravity, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_android_background) && obtainStyledAttributes.getResourceId(R.styleable.TitleBar_android_background, 0) == R.drawable.bar_drawable_placeholder) {
            TitleBarSupport.setBackground(this, this.mCurrentStyle.getTitleBarBackground(context));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_leftBackground)) {
            setLeftBackground(obtainStyledAttributes.getResourceId(R.styleable.TitleBar_leftBackground, 0) != R.drawable.bar_drawable_placeholder ? obtainStyledAttributes.getDrawable(R.styleable.TitleBar_leftBackground) : this.mCurrentStyle.getLeftTitleBackground(context));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_rightBackground)) {
            setRightBackground(obtainStyledAttributes.getResourceId(R.styleable.TitleBar_rightBackground, 0) != R.drawable.bar_drawable_placeholder ? obtainStyledAttributes.getDrawable(R.styleable.TitleBar_rightBackground) : this.mCurrentStyle.getRightTitleBackground(context));
        }
        setLineVisible(obtainStyledAttributes.getBoolean(R.styleable.TitleBar_lineVisible, this.mCurrentStyle.isLineVisible(context)));
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_lineDrawable)) {
            setLineDrawable(obtainStyledAttributes.getResourceId(R.styleable.TitleBar_lineDrawable, 0) != R.drawable.bar_drawable_placeholder ? obtainStyledAttributes.getDrawable(R.styleable.TitleBar_lineDrawable) : this.mCurrentStyle.getLineDrawable(context));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TitleBar_lineSize)) {
            setLineSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_lineSize, 0));
        }
        this.mHorizontalPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_childPaddingHorizontal, this.mCurrentStyle.getChildHorizontalPadding(context));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TitleBar_childPaddingVertical, this.mCurrentStyle.getChildVerticalPadding(context));
        this.mVerticalPadding = dimensionPixelSize;
        setChildPadding(this.mHorizontalPadding, dimensionPixelSize);
        obtainStyledAttributes.recycle();
        addView(createTitleView, 0);
        addView(createLeftView, 1);
        addView(createRightView, 2);
        addView(createLineView, 3);
        addOnLayoutChangeListener(this);
        if (isInEditMode()) {
            measure(0, 0);
            createTitleView.measure(0, 0);
            createLeftView.measure(0, 0);
            createRightView.measure(0, 0);
            int max = Math.max(createLeftView.getMeasuredWidth(), createRightView.getMeasuredWidth()) + this.mHorizontalPadding;
            ((ViewGroup.MarginLayoutParams) createTitleView.getLayoutParams()).setMargins(max, 0, max, 0);
        }
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        removeOnLayoutChangeListener(this);
        if (!(this.mLeftView.getMaxWidth() == Integer.MAX_VALUE || this.mTitleView.getMaxWidth() == Integer.MAX_VALUE || this.mRightView.getMaxWidth() == Integer.MAX_VALUE)) {
            this.mLeftView.setMaxWidth(Integer.MAX_VALUE);
            this.mTitleView.setMaxWidth(Integer.MAX_VALUE);
            this.mRightView.setMaxWidth(Integer.MAX_VALUE);
            this.mLeftView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mTitleView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.mRightView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        }
        int i9 = i3 - i;
        int max = Math.max(this.mLeftView.getMeasuredWidth(), this.mRightView.getMeasuredWidth());
        int i10 = max * 2;
        if (this.mTitleView.getMeasuredWidth() + i10 >= i9) {
            if (max > i9 / 3) {
                int i11 = i9 / 4;
                this.mLeftView.setMaxWidth(i11);
                this.mTitleView.setMaxWidth(i9 / 2);
                this.mRightView.setMaxWidth(i11);
            } else {
                this.mLeftView.setMaxWidth(max);
                this.mTitleView.setMaxWidth(i9 - i10);
                this.mRightView.setMaxWidth(max);
            }
        } else if (!(this.mLeftView.getMaxWidth() == Integer.MAX_VALUE || this.mTitleView.getMaxWidth() == Integer.MAX_VALUE || this.mRightView.getMaxWidth() == Integer.MAX_VALUE)) {
            this.mLeftView.setMaxWidth(Integer.MAX_VALUE);
            this.mTitleView.setMaxWidth(Integer.MAX_VALUE);
            this.mRightView.setMaxWidth(Integer.MAX_VALUE);
        }
        TextView textView = this.mLeftView;
        textView.setEnabled(TitleBarSupport.isContainContent(textView));
        TextView textView2 = this.mTitleView;
        textView2.setEnabled(TitleBarSupport.isContainContent(textView2));
        TextView textView3 = this.mRightView;
        textView3.setEnabled(TitleBarSupport.isContainContent(textView3));
        post(new Runnable() {
            public void run() {
                TitleBar titleBar = TitleBar.this;
                titleBar.addOnLayoutChangeListener(titleBar);
            }
        });
    }

    public void onClick(View view) {
        OnTitleBarListener onTitleBarListener = this.mListener;
        if (onTitleBarListener != null) {
            if (view == this.mLeftView) {
                onTitleBarListener.onLeftClick(view);
            } else if (view == this.mRightView) {
                onTitleBarListener.onRightClick(view);
            } else if (view == this.mTitleView) {
                onTitleBarListener.onTitleClick(view);
            }
        }
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams.width == -2) {
            layoutParams.width = -1;
        }
        int i = this.mHorizontalPadding;
        int i2 = 0;
        if (layoutParams.height == -2) {
            i2 = this.mVerticalPadding;
        }
        setChildPadding(i, i2);
        super.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return new FrameLayout.LayoutParams(-1, -2);
    }

    public TitleBar setOnTitleBarListener(OnTitleBarListener onTitleBarListener) {
        this.mListener = onTitleBarListener;
        this.mTitleView.setOnClickListener(this);
        this.mLeftView.setOnClickListener(this);
        this.mRightView.setOnClickListener(this);
        return this;
    }

    public TitleBar setTitle(int i) {
        return setTitle((CharSequence) getResources().getString(i));
    }

    public TitleBar setTitle(CharSequence charSequence) {
        this.mTitleView.setText(charSequence);
        return this;
    }

    public CharSequence getTitle() {
        return this.mTitleView.getText();
    }

    public TitleBar setLeftTitle(int i) {
        return setLeftTitle((CharSequence) getResources().getString(i));
    }

    public TitleBar setLeftTitle(CharSequence charSequence) {
        this.mLeftView.setText(charSequence);
        return this;
    }

    public CharSequence getLeftTitle() {
        return this.mLeftView.getText();
    }

    public TitleBar setRightTitle(int i) {
        return setRightTitle((CharSequence) getResources().getString(i));
    }

    public TitleBar setRightTitle(CharSequence charSequence) {
        this.mRightView.setText(charSequence);
        return this;
    }

    public CharSequence getRightTitle() {
        return this.mRightView.getText();
    }

    public TitleBar setTitleStyle(int i) {
        TitleBarSupport.setTextTypeface(this.mTitleView, i);
        return this;
    }

    public TitleBar setTitleStyle(Typeface typeface, int i) {
        this.mTitleView.setTypeface(typeface, i);
        return this;
    }

    public TitleBar setLeftTitleStyle(int i) {
        TitleBarSupport.setTextTypeface(this.mLeftView, i);
        return this;
    }

    public TitleBar setLeftTitleStyle(Typeface typeface, int i) {
        this.mLeftView.setTypeface(typeface);
        return this;
    }

    public TitleBar setRightTitleStyle(int i) {
        TitleBarSupport.setTextTypeface(this.mRightView, i);
        return this;
    }

    public TitleBar setRightTitleStyle(Typeface typeface, int i) {
        this.mRightView.setTypeface(typeface);
        return this;
    }

    public TitleBar setTitleColor(int i) {
        return setTitleColor(ColorStateList.valueOf(i));
    }

    public TitleBar setTitleColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.mTitleView.setTextColor(colorStateList);
        }
        return this;
    }

    public TitleBar setLeftTitleColor(int i) {
        return setLeftTitleColor(ColorStateList.valueOf(i));
    }

    public TitleBar setLeftTitleColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.mLeftView.setTextColor(colorStateList);
        }
        return this;
    }

    public TitleBar setRightTitleColor(int i) {
        return setRightTitleColor(ColorStateList.valueOf(i));
    }

    public TitleBar setRightTitleColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.mRightView.setTextColor(colorStateList);
        }
        return this;
    }

    public TitleBar setTitleSize(float f) {
        return setTitleSize(2, f);
    }

    public TitleBar setTitleSize(int i, float f) {
        this.mTitleView.setTextSize(i, f);
        return this;
    }

    public TitleBar setLeftTitleSize(float f) {
        return setLeftTitleSize(2, f);
    }

    public TitleBar setLeftTitleSize(int i, float f) {
        this.mLeftView.setTextSize(i, f);
        return this;
    }

    public TitleBar setRightTitleSize(float f) {
        return setRightTitleSize(2, f);
    }

    public TitleBar setRightTitleSize(int i, float f) {
        this.mRightView.setTextSize(i, f);
        return this;
    }

    public TitleBar setTitleIcon(int i) {
        return setTitleIcon(TitleBarSupport.getDrawable(getContext(), i));
    }

    public TitleBar setTitleIcon(Drawable drawable) {
        TitleBarSupport.setDrawableTint(drawable, this.mTitleIconTint);
        TitleBarSupport.setDrawableSize(drawable, this.mTitleIconWidth, this.mTitleIconHeight);
        TitleBarSupport.setTextCompoundDrawable(this.mTitleView, drawable, this.mTitleIconGravity);
        return this;
    }

    public Drawable getTitleIcon() {
        return TitleBarSupport.getTextCompoundDrawable(this.mTitleView, this.mTitleIconGravity);
    }

    public TitleBar setLeftIcon(int i) {
        return setLeftIcon(TitleBarSupport.getDrawable(getContext(), i));
    }

    public TitleBar setLeftIcon(Drawable drawable) {
        TitleBarSupport.setDrawableTint(drawable, this.mLeftIconTint);
        TitleBarSupport.setDrawableSize(drawable, this.mLeftIconWidth, this.mLeftIconHeight);
        TitleBarSupport.setTextCompoundDrawable(this.mLeftView, drawable, this.mLeftIconGravity);
        return this;
    }

    public Drawable getLeftIcon() {
        return TitleBarSupport.getTextCompoundDrawable(this.mLeftView, this.mLeftIconGravity);
    }

    public TitleBar setRightIcon(int i) {
        return setRightIcon(TitleBarSupport.getDrawable(getContext(), i));
    }

    public TitleBar setRightIcon(Drawable drawable) {
        TitleBarSupport.setDrawableTint(drawable, this.mRightIconTint);
        TitleBarSupport.setDrawableSize(drawable, this.mRightIconWidth, this.mRightIconHeight);
        TitleBarSupport.setTextCompoundDrawable(this.mRightView, drawable, this.mRightIconGravity);
        return this;
    }

    public Drawable getRightIcon() {
        return TitleBarSupport.getTextCompoundDrawable(this.mRightView, this.mRightIconGravity);
    }

    public TitleBar setTitleIconSize(int i, int i2) {
        this.mTitleIconWidth = i;
        this.mTitleIconHeight = i2;
        TitleBarSupport.setDrawableSize(getTitleIcon(), i, i2);
        return this;
    }

    public TitleBar setLeftIconSize(int i, int i2) {
        this.mLeftIconWidth = i;
        this.mLeftIconHeight = i2;
        TitleBarSupport.setDrawableSize(getLeftIcon(), i, i2);
        return this;
    }

    public TitleBar setRightIconSize(int i, int i2) {
        this.mRightIconWidth = i;
        this.mRightIconHeight = i2;
        TitleBarSupport.setDrawableSize(getRightIcon(), i, i2);
        return this;
    }

    public TitleBar setTitleIconPadding(int i) {
        this.mTitleView.setCompoundDrawablePadding(i);
        return this;
    }

    public TitleBar setLeftIconPadding(int i) {
        this.mLeftView.setCompoundDrawablePadding(i);
        return this;
    }

    public TitleBar setRightIconPadding(int i) {
        this.mRightView.setCompoundDrawablePadding(i);
        return this;
    }

    public TitleBar setTitleIconTint(int i) {
        this.mTitleIconTint = i;
        TitleBarSupport.setDrawableTint(getTitleIcon(), i);
        return this;
    }

    public TitleBar setLeftIconTint(int i) {
        this.mLeftIconTint = i;
        TitleBarSupport.setDrawableTint(getLeftIcon(), i);
        return this;
    }

    public TitleBar setRightIconTint(int i) {
        this.mRightIconTint = i;
        TitleBarSupport.setDrawableTint(getRightIcon(), i);
        return this;
    }

    public TitleBar clearTitleIconTint() {
        this.mTitleIconTint = 0;
        TitleBarSupport.clearDrawableTint(getTitleIcon());
        return this;
    }

    public TitleBar clearLeftIconTint() {
        this.mLeftIconTint = 0;
        TitleBarSupport.clearDrawableTint(getLeftIcon());
        return this;
    }

    public TitleBar clearRightIconTint() {
        this.mRightIconTint = 0;
        TitleBarSupport.clearDrawableTint(getRightIcon());
        return this;
    }

    public TitleBar setTitleIconGravity(int i) {
        Drawable titleIcon = getTitleIcon();
        this.mTitleIconGravity = i;
        if (titleIcon != null) {
            TitleBarSupport.setTextCompoundDrawable(this.mTitleView, titleIcon, i);
        }
        return this;
    }

    public TitleBar setLeftIconGravity(int i) {
        Drawable leftIcon = getLeftIcon();
        this.mLeftIconGravity = i;
        if (leftIcon != null) {
            TitleBarSupport.setTextCompoundDrawable(this.mLeftView, leftIcon, i);
        }
        return this;
    }

    public TitleBar setRightIconGravity(int i) {
        Drawable rightIcon = getRightIcon();
        this.mRightIconGravity = i;
        if (rightIcon != null) {
            TitleBarSupport.setTextCompoundDrawable(this.mRightView, rightIcon, i);
        }
        return this;
    }

    public TitleBar setLeftBackground(int i) {
        return setLeftBackground(TitleBarSupport.getDrawable(getContext(), i));
    }

    public TitleBar setLeftBackground(Drawable drawable) {
        TitleBarSupport.setBackground(this.mLeftView, drawable);
        return this;
    }

    public TitleBar setRightBackground(int i) {
        return setRightBackground(TitleBarSupport.getDrawable(getContext(), i));
    }

    public TitleBar setRightBackground(Drawable drawable) {
        TitleBarSupport.setBackground(this.mRightView, drawable);
        return this;
    }

    public TitleBar setLineVisible(boolean z) {
        this.mLineView.setVisibility(z ? 0 : 4);
        return this;
    }

    public TitleBar setLineColor(int i) {
        return setLineDrawable(new ColorDrawable(i));
    }

    public TitleBar setLineDrawable(Drawable drawable) {
        TitleBarSupport.setBackground(this.mLineView, drawable);
        return this;
    }

    public TitleBar setLineSize(int i) {
        ViewGroup.LayoutParams layoutParams = this.mLineView.getLayoutParams();
        layoutParams.height = i;
        this.mLineView.setLayoutParams(layoutParams);
        return this;
    }

    public TitleBar setTitleGravity(int i) {
        int absoluteGravity = TitleBarSupport.getAbsoluteGravity(this, i);
        if (absoluteGravity == 3) {
            if (TitleBarSupport.isContainContent(TitleBarSupport.isLayoutRtl(getContext()) ? this.mRightView : this.mLeftView)) {
                Log.e(LOG_TAG, "Title center of gravity for the left, the left title can not have content");
                return this;
            }
        }
        if (absoluteGravity == 5) {
            if (TitleBarSupport.isContainContent(TitleBarSupport.isLayoutRtl(getContext()) ? this.mLeftView : this.mRightView)) {
                Log.e(LOG_TAG, "Title center of gravity for the right, the right title can not have content");
                return this;
            }
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mTitleView.getLayoutParams();
        layoutParams.gravity = absoluteGravity;
        this.mTitleView.setLayoutParams(layoutParams);
        return this;
    }

    public TitleBar setChildPadding(int i, int i2) {
        this.mHorizontalPadding = i;
        this.mVerticalPadding = i2;
        this.mLeftView.setPadding(i, i2, i, i2);
        this.mTitleView.setPadding(i, i2, i, i2);
        this.mRightView.setPadding(i, i2, i, i2);
        return this;
    }

    public TextView getLeftView() {
        return this.mLeftView;
    }

    public TextView getTitleView() {
        return this.mTitleView;
    }

    public TextView getRightView() {
        return this.mRightView;
    }

    public View getLineView() {
        return this.mLineView;
    }

    public ITitleBarStyle getCurrentStyle() {
        return this.mCurrentStyle;
    }

    public static void setDefaultStyle(ITitleBarStyle iTitleBarStyle) {
        sGlobalStyle = iTitleBarStyle;
    }
}
