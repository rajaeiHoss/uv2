package com.gyf.immersionbar

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

internal class SpecialBarFontUtils private constructor() {
    companion object {
        private var SYSTEM_UI_FLAG_LIGHT_STATUS_BAR: Int = 0
        private var mSetStatusBarColorIcon: Method? = null
        private var mSetStatusBarDarkIcon: Method? = null
        private var mStatusBarColorFiled: Field? = null

        init {
            try {
                mSetStatusBarColorIcon = Activity::class.java.getMethod(
                    "setStatusBarDarkIcon",
                    Int::class.javaPrimitiveType
                )
            } catch (_: NoSuchMethodException) {
            }
            try {
                mSetStatusBarDarkIcon = Activity::class.java.getMethod(
                    "setStatusBarDarkIcon",
                    Boolean::class.javaPrimitiveType
                )
            } catch (_: NoSuchMethodException) {
            }
            try {
                mStatusBarColorFiled = WindowManager.LayoutParams::class.java.getField("statusBarColor")
            } catch (_: NoSuchFieldException) {
            }
            try {
                SYSTEM_UI_FLAG_LIGHT_STATUS_BAR =
                    View::class.java.getField("SYSTEM_UI_FLAG_LIGHT_STATUS_BAR").getInt(null)
            } catch (_: NoSuchFieldException) {
            } catch (_: IllegalAccessException) {
            }
        }

        fun toGrey(color: Int): Int {
            return (((((color and 0xFF0000) shr 16) * 38) + (((color and 0xFF00) shr 8) * 75)) +
                ((color and 0xFF) * 15)) shr 7
        }

        fun isBlackColor(color: Int, level: Int): Boolean {
            return toGrey(color) < level
        }

        @JvmStatic
        fun setStatusBarDarkIcon(activity: Activity, color: Int) {
            val method = mSetStatusBarColorIcon
            if (method != null) {
                try {
                    method.invoke(activity, color)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                }
            } else {
                val isBlackColor = isBlackColor(color, 50)
                if (mStatusBarColorFiled != null) {
                    setStatusBarDarkIcon(activity, isBlackColor, isBlackColor)
                    setStatusBarDarkIcon(activity.window, color)
                    return
                }
                setStatusBarDarkIcon(activity, isBlackColor)
            }
        }

        @JvmStatic
        fun setStatusBarDarkIcon(window: Window, color: Int) {
            try {
                setStatusBarColor(window, color)
                if (Build.VERSION.SDK_INT > 22) {
                    setStatusBarDarkIcon(window.decorView, true)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        fun setStatusBarDarkIcon(activity: Activity, dark: Boolean) {
            setStatusBarDarkIcon(activity, dark, true)
        }

        private fun changeMeizuFlag(
            layoutParams: WindowManager.LayoutParams,
            key: String,
            dark: Boolean
        ): Boolean {
            return try {
                val darkFlagField = layoutParams.javaClass.getDeclaredField(key)
                darkFlagField.isAccessible = true
                val darkFlag = darkFlagField.getInt(layoutParams)
                val meizuFlagsField = layoutParams.javaClass.getDeclaredField("meizuFlags")
                meizuFlagsField.isAccessible = true
                val meizuFlags = meizuFlagsField.getInt(layoutParams)
                val newFlags = if (dark) darkFlag or meizuFlags else darkFlag.inv() and meizuFlags
                if (meizuFlags == newFlags) {
                    false
                } else {
                    meizuFlagsField.setInt(layoutParams, newFlags)
                    true
                }
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
                false
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
                false
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
                false
            } catch (t: Throwable) {
                t.printStackTrace()
                false
            }
        }

        private fun setStatusBarDarkIcon(view: View, dark: Boolean) {
            val systemUiVisibility = view.systemUiVisibility
            val targetVisibility = if (dark) {
                SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or systemUiVisibility
            } else {
                SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() and systemUiVisibility
            }
            if (targetVisibility != systemUiVisibility) {
                view.systemUiVisibility = targetVisibility
            }
        }

        private fun setStatusBarColor(window: Window, color: Int) {
            val layoutParams = window.attributes
            val field = mStatusBarColorFiled
            if (field != null) {
                try {
                    if (field.getInt(layoutParams) != color) {
                        field.set(layoutParams, color)
                        window.attributes = layoutParams
                    }
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
            }
        }

        @JvmStatic
        fun setStatusBarDarkIcon(window: Window, dark: Boolean) {
            if (Build.VERSION.SDK_INT < 23) {
                changeMeizuFlag(window.attributes, "MEIZU_FLAG_DARK_STATUS_BAR_ICON", dark)
                return
            }
            setStatusBarDarkIcon(window.decorView, dark)
            setStatusBarColor(window, 0)
        }

        private fun setStatusBarDarkIcon(activity: Activity, dark: Boolean, applyWindow: Boolean) {
            val method = mSetStatusBarDarkIcon
            if (method != null) {
                try {
                    method.invoke(activity, dark)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                }
            } else if (applyWindow) {
                setStatusBarDarkIcon(activity.window, dark)
            }
        }

        @JvmStatic
        fun setMIUIBarDark(window: Window?, key: String, dark: Boolean) {
            if (window != null) {
                val windowClass = window.javaClass
                try {
                    val layoutParamsClass = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                    val darkModeFlag = layoutParamsClass.getField(key).getInt(layoutParamsClass)
                    val setExtraFlags = windowClass.getMethod(
                        "setExtraFlags",
                        Int::class.javaPrimitiveType,
                        Int::class.javaPrimitiveType
                    )
                    if (dark) {
                        setExtraFlags.invoke(window, darkModeFlag, darkModeFlag)
                    } else {
                        setExtraFlags.invoke(window, 0, darkModeFlag)
                    }
                } catch (_: Exception) {
                }
            }
        }
    }
}
