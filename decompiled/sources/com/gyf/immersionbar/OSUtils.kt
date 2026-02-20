package com.gyf.immersionbar

import android.text.TextUtils

class OSUtils private constructor() {
    companion object {
        private const val KEY_DISPLAY: String = "ro.build.display.id"
        private const val KEY_EMUI_VERSION_NAME: String = "ro.build.version.emui"
        private const val KEY_MIUI_VERSION_NAME: String = "ro.miui.ui.version.name"

        @JvmStatic
        fun isMIUI(): Boolean {
            return !TextUtils.isEmpty(getSystemProperty(KEY_MIUI_VERSION_NAME, ""))
        }

        @JvmStatic
        fun isMIUI6Later(): Boolean {
            val miuiVersion = getMIUIVersion()
            if (miuiVersion.isEmpty()) {
                return false
            }
            return try {
                miuiVersion.substring(1).toInt() >= 6
            } catch (_: NumberFormatException) {
                false
            }
        }

        @JvmStatic
        fun getMIUIVersion(): String {
            return if (isMIUI()) getSystemProperty(KEY_MIUI_VERSION_NAME, "") else ""
        }

        @JvmStatic
        fun isEMUI(): Boolean {
            return !TextUtils.isEmpty(getSystemProperty(KEY_EMUI_VERSION_NAME, ""))
        }

        @JvmStatic
        fun getEMUIVersion(): String {
            return if (isEMUI()) getSystemProperty(KEY_EMUI_VERSION_NAME, "") else ""
        }

        @JvmStatic
        fun isEMUI3_1(): Boolean {
            val emuiVersion = getEMUIVersion()
            return "EmotionUI 3" == emuiVersion || emuiVersion.contains("EmotionUI_3.1")
        }

        @JvmStatic
        fun isEMUI3_0(): Boolean {
            return getEMUIVersion().contains("EmotionUI_3.0")
        }

        @JvmStatic
        fun isEMUI3_x(): Boolean {
            return isEMUI3_0() || isEMUI3_1()
        }

        @JvmStatic
        fun isFlymeOS(): Boolean {
            return getFlymeOSFlag().lowercase().contains("flyme")
        }

        @JvmStatic
        fun isFlymeOS4Later(): Boolean {
            val flymeOSVersion = getFlymeOSVersion()
            if (flymeOSVersion.isEmpty()) {
                return false
            }
            return try {
                val majorVersion = if (flymeOSVersion.lowercase().contains("os")) {
                    flymeOSVersion.substring(9, 10).toInt()
                } else {
                    flymeOSVersion.substring(6, 7).toInt()
                }
                majorVersion >= 4
            } catch (_: NumberFormatException) {
                false
            }
        }

        @JvmStatic
        fun isFlymeOS5(): Boolean {
            val flymeOSVersion = getFlymeOSVersion()
            if (flymeOSVersion.isEmpty()) {
                return false
            }
            return try {
                val majorVersion = if (flymeOSVersion.lowercase().contains("os")) {
                    flymeOSVersion.substring(9, 10).toInt()
                } else {
                    flymeOSVersion.substring(6, 7).toInt()
                }
                majorVersion == 5
            } catch (_: NumberFormatException) {
                false
            }
        }

        @JvmStatic
        fun getFlymeOSVersion(): String {
            return if (isFlymeOS()) getSystemProperty(KEY_DISPLAY, "") else ""
        }

        private fun getFlymeOSFlag(): String {
            return getSystemProperty(KEY_DISPLAY, "")
        }

        private fun getSystemProperty(key: String, defaultValue: String): String {
            return try {
                val clazz = Class.forName("android.os.SystemProperties")
                clazz.getMethod("get", String::class.java, String::class.java)
                    .invoke(clazz, key, defaultValue) as String
            } catch (e: Exception) {
                e.printStackTrace()
                defaultValue
            }
        }
    }
}
