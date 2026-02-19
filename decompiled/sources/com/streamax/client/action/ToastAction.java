package com.streamax.client.action;

import com.hjq.toast.ToastUtils;

public interface ToastAction {
    void toast(int i);

    void toast(CharSequence charSequence);

    void toast(Object obj);

    /* renamed from: com.streamax.client.action.ToastAction$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$toast(ToastAction _this, CharSequence charSequence) {
            ToastUtils.show(charSequence);
        }

        public static void $default$toast(ToastAction _this, int i) {
            ToastUtils.show(i);
        }

        public static void $default$toast(ToastAction _this, Object obj) {
            ToastUtils.show(obj);
        }
    }
}
