package com.hjq.toast;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import com.hjq.toast.config.IToast;

public final class ActivityToast implements IToast {
    private int mDuration;
    private int mGravity;
    private float mHorizontalMargin;
    private TextView mMessageView;
    private final ToastImpl mToastImpl;
    private float mVerticalMargin;
    private View mView;
    private int mXOffset;
    private int mYOffset;

    public TextView findMessageView(View view) {
        if (view instanceof TextView) {
            int id = view.getId();
            if (id == -1) {
                view.setId(16908299);
            } else if (id != 16908299) {
                throw new IllegalArgumentException("You must set the ID value of TextView to android.R.id.message");
            }
            return (TextView) view;
        }
        View findViewById = view.findViewById(16908299);
        if (findViewById instanceof TextView) {
            return (TextView) findViewById;
        }
        throw new IllegalArgumentException("You must include a TextView with an ID value of android.R.id.message");
    }

    public ActivityToast(Activity activity) {
        this.mToastImpl = new ToastImpl(activity, this);
    }

    public void show() {
        this.mToastImpl.show();
    }

    public void cancel() {
        this.mToastImpl.cancel();
    }

    public void setText(int i) {
        View view = this.mView;
        if (view != null) {
            setText((CharSequence) view.getResources().getString(i));
        }
    }

    public void setText(CharSequence charSequence) {
        TextView textView = this.mMessageView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void setView(View view) {
        this.mView = view;
        if (view == null) {
            this.mMessageView = null;
        } else {
            this.mMessageView = findMessageView(view);
        }
    }

    public View getView() {
        return this.mView;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setGravity(int i, int i2, int i3) {
        this.mGravity = i;
        this.mXOffset = i2;
        this.mYOffset = i3;
    }

    public int getGravity() {
        return this.mGravity;
    }

    public int getXOffset() {
        return this.mXOffset;
    }

    public int getYOffset() {
        return this.mYOffset;
    }

    public void setMargin(float f, float f2) {
        this.mHorizontalMargin = f;
        this.mVerticalMargin = f2;
    }

    public float getHorizontalMargin() {
        return this.mHorizontalMargin;
    }

    public float getVerticalMargin() {
        return this.mVerticalMargin;
    }
}
