package com.hjq.permissions

import android.content.Context
import android.os.Build

object AndroidVersion {
    const val ANDROID_4_0: Int = 14
    const val ANDROID_4_1: Int = 16
    const val ANDROID_4_2: Int = 17
    const val ANDROID_4_3: Int = 18
    const val ANDROID_4_4: Int = 19
    const val ANDROID_5: Int = 21
    const val ANDROID_5_1: Int = 22
    const val ANDROID_6: Int = 23
    const val ANDROID_7: Int = 24
    const val ANDROID_7_1: Int = 25
    const val ANDROID_8: Int = 26
    const val ANDROID_8_1: Int = 27
    const val ANDROID_9: Int = 28
    const val ANDROID_10: Int = 29
    const val ANDROID_11: Int = 30
    const val ANDROID_12: Int = 31
    const val ANDROID_12_L: Int = 32
    const val ANDROID_13: Int = 33

    @JvmStatic
    fun getAndroidVersionCode(): Int = Build.VERSION.SDK_INT

    @JvmStatic
    fun getTargetSdkVersionCode(context: Context): Int = context.applicationInfo.targetSdkVersion

    @JvmStatic
    fun isAndroid13(): Boolean = Build.VERSION.SDK_INT >= ANDROID_13

    @JvmStatic
    fun isAndroid12(): Boolean = Build.VERSION.SDK_INT >= ANDROID_12

    @JvmStatic
    fun isAndroid11(): Boolean = Build.VERSION.SDK_INT >= ANDROID_11

    @JvmStatic
    fun isAndroid10(): Boolean = Build.VERSION.SDK_INT >= ANDROID_10

    @JvmStatic
    fun isAndroid9(): Boolean = Build.VERSION.SDK_INT >= ANDROID_9

    @JvmStatic
    fun isAndroid8(): Boolean = Build.VERSION.SDK_INT >= ANDROID_8

    @JvmStatic
    fun isAndroid6(): Boolean = Build.VERSION.SDK_INT >= ANDROID_6

    @JvmStatic
    fun isAndroid5_1(): Boolean = Build.VERSION.SDK_INT >= ANDROID_5_1

    @JvmStatic
    fun isAndroid5(): Boolean = Build.VERSION.SDK_INT >= ANDROID_5

    @JvmStatic
    fun isAndroid4_3(): Boolean = Build.VERSION.SDK_INT >= ANDROID_4_3

    @JvmStatic
    fun isAndroid4_2(): Boolean = Build.VERSION.SDK_INT >= ANDROID_4_2

    @JvmStatic
    fun isAndroid4(): Boolean = Build.VERSION.SDK_INT >= ANDROID_4_0
}
