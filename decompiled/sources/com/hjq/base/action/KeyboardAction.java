package com.hjq.base.action;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

public interface KeyboardAction {
    void hideKeyboard(View view);

    void showKeyboard(View view);

    void toggleSoftInput(View view);

    /* renamed from: com.hjq.base.action.KeyboardAction$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$showKeyboard(KeyboardAction _this, View view) {
            InputMethodManager inputMethodManager;
            if (view != null && (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) != null) {
                inputMethodManager.showSoftInput(view, 2);
            }
        }

        public static void $default$hideKeyboard(KeyboardAction _this, View view) {
            InputMethodManager inputMethodManager;
            if (view != null && (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
            }
        }

        public static void $default$toggleSoftInput(KeyboardAction _this, View view) {
            InputMethodManager inputMethodManager;
            if (view != null && (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) != null) {
                inputMethodManager.toggleSoftInput(0, 0);
            }
        }
    }
}
