package com.hjq.base.action

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import java.util.ArrayList

interface BundleAction {
    fun getBundle(): Bundle?

    fun getInt(key: String): Int = getInt(key, 0)

    fun getInt(key: String, defaultValue: Int): Int {
        val bundle = getBundle()
        return bundle?.getInt(key, defaultValue) ?: defaultValue
    }

    fun getLong(key: String): Long = getLong(key, 0)

    fun getLong(key: String, defaultValue: Int): Long {
        val bundle = getBundle()
        return bundle?.getLong(key, defaultValue.toLong()) ?: defaultValue.toLong()
    }

    fun getFloat(key: String): Float = getFloat(key, 0)

    fun getFloat(key: String, defaultValue: Int): Float {
        val bundle = getBundle()
        return bundle?.getFloat(key, defaultValue.toFloat()) ?: defaultValue.toFloat()
    }

    fun getDouble(key: String): Double = getDouble(key, 0)

    fun getDouble(key: String, defaultValue: Int): Double {
        val bundle = getBundle()
        return bundle?.getDouble(key, defaultValue.toDouble()) ?: defaultValue.toDouble()
    }

    fun getBoolean(key: String): Boolean = getBoolean(key, false)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val bundle = getBundle()
        return bundle?.getBoolean(key, defaultValue) ?: defaultValue
    }

    fun getString(key: String): String? {
        val bundle = getBundle()
        return bundle?.getString(key)
    }

    fun <P : Parcelable> getParcelable(key: String): P? {
        val bundle = getBundle()
        return bundle?.getParcelable(key) as P?
    }

    fun <S : Serializable> getSerializable(key: String): S? {
        val bundle = getBundle()
        return bundle?.getSerializable(key) as S?
    }

    fun getStringArrayList(key: String): ArrayList<String>? {
        val bundle = getBundle()
        return bundle?.getStringArrayList(key)
    }

    fun getIntegerArrayList(key: String): ArrayList<Int>? {
        val bundle = getBundle()
        return bundle?.getIntegerArrayList(key)
    }

    object CC {
        @JvmStatic
        fun `$default$getInt`(bundleAction: BundleAction, key: String): Int {
            return bundleAction.getInt(key, 0)
        }

        @JvmStatic
        fun `$default$getInt`(bundleAction: BundleAction, key: String, defaultValue: Int): Int {
            val bundle = bundleAction.getBundle()
            return bundle?.getInt(key, defaultValue) ?: defaultValue
        }

        @JvmStatic
        fun `$default$getLong`(bundleAction: BundleAction, key: String): Long {
            return bundleAction.getLong(key, 0)
        }

        @JvmStatic
        fun `$default$getLong`(bundleAction: BundleAction, key: String, defaultValue: Int): Long {
            val bundle = bundleAction.getBundle()
            return bundle?.getLong(key, defaultValue.toLong()) ?: defaultValue.toLong()
        }

        @JvmStatic
        fun `$default$getFloat`(bundleAction: BundleAction, key: String): Float {
            return bundleAction.getFloat(key, 0)
        }

        @JvmStatic
        fun `$default$getFloat`(bundleAction: BundleAction, key: String, defaultValue: Int): Float {
            val bundle = bundleAction.getBundle()
            return bundle?.getFloat(key, defaultValue.toFloat()) ?: defaultValue.toFloat()
        }

        @JvmStatic
        fun `$default$getDouble`(bundleAction: BundleAction, key: String): Double {
            return bundleAction.getDouble(key, 0)
        }

        @JvmStatic
        fun `$default$getDouble`(
            bundleAction: BundleAction,
            key: String,
            defaultValue: Int
        ): Double {
            val bundle = bundleAction.getBundle()
            return bundle?.getDouble(key, defaultValue.toDouble()) ?: defaultValue.toDouble()
        }

        @JvmStatic
        fun `$default$getBoolean`(bundleAction: BundleAction, key: String): Boolean {
            return bundleAction.getBoolean(key, false)
        }

        @JvmStatic
        fun `$default$getBoolean`(
            bundleAction: BundleAction,
            key: String,
            defaultValue: Boolean
        ): Boolean {
            val bundle = bundleAction.getBundle()
            return bundle?.getBoolean(key, defaultValue) ?: defaultValue
        }

        @JvmStatic
        fun `$default$getString`(bundleAction: BundleAction, key: String): String? {
            val bundle = bundleAction.getBundle()
            return bundle?.getString(key)
        }

        @JvmStatic
        fun `$default$getParcelable`(bundleAction: BundleAction, key: String): Parcelable? {
            val bundle = bundleAction.getBundle()
            return bundle?.getParcelable(key)
        }

        @JvmStatic
        fun `$default$getSerializable`(bundleAction: BundleAction, key: String): Serializable? {
            val bundle = bundleAction.getBundle()
            return bundle?.getSerializable(key)
        }

        @JvmStatic
        fun `$default$getStringArrayList`(
            bundleAction: BundleAction,
            key: String
        ): ArrayList<String>? {
            val bundle = bundleAction.getBundle()
            return bundle?.getStringArrayList(key)
        }

        @JvmStatic
        fun `$default$getIntegerArrayList`(
            bundleAction: BundleAction,
            key: String
        ): ArrayList<Int>? {
            val bundle = bundleAction.getBundle()
            return bundle?.getIntegerArrayList(key)
        }
    }
}
