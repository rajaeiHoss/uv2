package com.hjq.base.action;

import android.view.View;

public interface ClickAction extends View.OnClickListener {
    <V extends View> V findViewById(int i);

    void onClick(View view);

    void setOnClickListener(View.OnClickListener onClickListener, int... iArr);

    void setOnClickListener(View.OnClickListener onClickListener, View... viewArr);

    void setOnClickListener(int... iArr);

    void setOnClickListener(View... viewArr);

    /* renamed from: com.hjq.base.action.ClickAction$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onClick(ClickAction clickAction, View view) {
        }

        public static void $default$setOnClickListener(ClickAction _this, int... iArr) {
            _this.setOnClickListener((View.OnClickListener) _this, iArr);
        }

        public static void $default$setOnClickListener(ClickAction _this, View.OnClickListener onClickListener, int... iArr) {
            for (int findViewById : iArr) {
                _this.findViewById(findViewById).setOnClickListener(onClickListener);
            }
        }

        public static void $default$setOnClickListener(ClickAction _this, View... viewArr) {
            _this.setOnClickListener((View.OnClickListener) _this, viewArr);
        }

        public static void $default$setOnClickListener(ClickAction _this, View.OnClickListener onClickListener, View... viewArr) {
            for (View onClickListener2 : viewArr) {
                onClickListener2.setOnClickListener(onClickListener);
            }
        }
    }
}
