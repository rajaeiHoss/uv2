package com.hjq.base.action

import android.view.View
import android.view.inputmethod.InputMethodManager

interface KeyboardAction {
    fun showKeyboard(view: View?) {
        if (view == null) {
            return
        }
        val inputMethodManager =
            view.context.getSystemService("input_method") as? InputMethodManager ?: return
        inputMethodManager.showSoftInput(view, 2)
    }

    fun hideKeyboard(view: View?) {
        if (view == null) {
            return
        }
        val inputMethodManager =
            view.context.getSystemService("input_method") as? InputMethodManager ?: return
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 2)
    }

    fun toggleSoftInput(view: View?) {
        if (view == null) {
            return
        }
        val inputMethodManager =
            view.context.getSystemService("input_method") as? InputMethodManager ?: return
        inputMethodManager.toggleSoftInput(0, 0)
    }

    object CC {
        @JvmStatic
        fun `$default$showKeyboard`(keyboardAction: KeyboardAction, view: View?) {
            if (view == null) {
                return
            }
            val inputMethodManager =
                view.context.getSystemService("input_method") as? InputMethodManager ?: return
            inputMethodManager.showSoftInput(view, 2)
        }

        @JvmStatic
        fun `$default$hideKeyboard`(keyboardAction: KeyboardAction, view: View?) {
            if (view == null) {
                return
            }
            val inputMethodManager =
                view.context.getSystemService("input_method") as? InputMethodManager ?: return
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 2)
        }

        @JvmStatic
        fun `$default$toggleSoftInput`(keyboardAction: KeyboardAction, view: View?) {
            if (view == null) {
                return
            }
            val inputMethodManager =
                view.context.getSystemService("input_method") as? InputMethodManager ?: return
            inputMethodManager.toggleSoftInput(0, 0)
        }
    }
}
