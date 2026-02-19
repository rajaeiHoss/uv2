package com.hjq.toast.config;

import android.view.View;
import android.widget.TextView;

public interface IToast {
    void cancel();

    TextView findMessageView(View view);

    int getDuration();

    int getGravity();

    float getHorizontalMargin();

    float getVerticalMargin();

    View getView();

    int getXOffset();

    int getYOffset();

    void setDuration(int i);

    void setGravity(int i, int i2, int i3);

    void setMargin(float f, float f2);

    void setText(int i);

    void setText(CharSequence charSequence);

    void setView(View view);

    void show();

    /* renamed from: com.hjq.toast.config.IToast$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static TextView $default$findMessageView(IToast _this, View view) {
            if (view instanceof TextView) {
                if (view.getId() == -1) {
                    view.setId(16908299);
                } else if (view.getId() != 16908299) {
                    throw new IllegalArgumentException("You must set the ID value of TextView to android.R.id.message");
                }
                return (TextView) view;
            } else if (view.findViewById(16908299) instanceof TextView) {
                return (TextView) view.findViewById(16908299);
            } else {
                throw new IllegalArgumentException("You must include a TextView with an ID value of android.R.id.message");
            }
        }
    }
}
