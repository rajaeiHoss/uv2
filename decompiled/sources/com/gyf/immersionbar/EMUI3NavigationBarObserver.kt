package com.gyf.immersionbar

import android.app.Application
import android.database.ContentObserver
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings

internal class EMUI3NavigationBarObserver private constructor() : ContentObserver(Handler(Looper.getMainLooper())) {
    private var mApplication: Application? = null
    private var mCallbacks: ArrayList<ImmersionCallback>? = null
    private var mIsRegister: Boolean = false

    fun register(application: Application) {
        mApplication = application
        if (Build.VERSION.SDK_INT >= 17) {
            val app = mApplication
            val uri = Settings.System.getUriFor("navigationbar_is_min")
            if (app != null && app.contentResolver != null && !mIsRegister && uri != null) {
                app.contentResolver.registerContentObserver(uri, true, this)
                mIsRegister = true
            }
        }
    }

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        if (Build.VERSION.SDK_INT >= 17) {
            val app = mApplication
            val callbacks = mCallbacks
            if (app != null && app.contentResolver != null && callbacks != null && callbacks.isNotEmpty()) {
                val navBarState = Settings.System.getInt(app.contentResolver, "navigationbar_is_min", 0)
                for (callback in callbacks) {
                    callback.onNavigationBarChange(navBarState != 1)
                }
            }
        }
    }

    fun addOnNavigationBarListener(immersionCallback: ImmersionCallback?) {
        if (immersionCallback == null) {
            return
        }
        if (mCallbacks == null) {
            mCallbacks = ArrayList()
        }
        if (!mCallbacks!!.contains(immersionCallback)) {
            mCallbacks!!.add(immersionCallback)
        }
    }

    fun removeOnNavigationBarListener(immersionCallback: ImmersionCallback?) {
        if (immersionCallback != null) {
            mCallbacks?.remove(immersionCallback)
        }
    }

    companion object {
        private val INSTANCE: EMUI3NavigationBarObserver = EMUI3NavigationBarObserver()

        @JvmStatic
        fun getInstance(): EMUI3NavigationBarObserver {
            return INSTANCE
        }
    }
}
