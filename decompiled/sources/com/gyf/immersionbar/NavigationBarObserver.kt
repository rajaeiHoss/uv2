package com.gyf.immersionbar

import android.app.Application
import android.database.ContentObserver
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings

internal class NavigationBarObserver private constructor() : ContentObserver(Handler(Looper.getMainLooper())) {
    private var mApplication: Application? = null
    private var mIsRegister: Boolean = false
    private var mListeners: ArrayList<OnNavigationBarListener>? = null

    fun register(application: Application) {
        mApplication = application
        if (Build.VERSION.SDK_INT >= 17) {
            val app = mApplication
            if (app != null && app.contentResolver != null && !mIsRegister) {
                var uri: Uri? = null
                if (OSUtils.isMIUI()) {
                    uri = Settings.Global.getUriFor("force_fsg_nav_bar")
                } else if (OSUtils.isEMUI()) {
                    uri = if (OSUtils.isEMUI3_x() || Build.VERSION.SDK_INT < 21) {
                        Settings.System.getUriFor("navigationbar_is_min")
                    } else {
                        Settings.Global.getUriFor("navigationbar_is_min")
                    }
                }
                if (uri != null) {
                    app.contentResolver.registerContentObserver(uri, true, this)
                    mIsRegister = true
                }
            }
        }
    }

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        if (Build.VERSION.SDK_INT >= 17) {
            val app = mApplication
            val listeners = mListeners
            if (app != null && app.contentResolver != null && listeners != null && listeners.isNotEmpty()) {
                val navBarState = if (OSUtils.isMIUI()) {
                    Settings.Global.getInt(app.contentResolver, "force_fsg_nav_bar", 0)
                } else if (OSUtils.isEMUI()) {
                    if (OSUtils.isEMUI3_x() || Build.VERSION.SDK_INT < 21) {
                        Settings.System.getInt(app.contentResolver, "navigationbar_is_min", 0)
                    } else {
                        Settings.Global.getInt(app.contentResolver, "navigationbar_is_min", 0)
                    }
                } else {
                    0
                }
                for (listener in listeners) {
                    listener.onNavigationBarChange(navBarState != 1)
                }
            }
        }
    }

    fun addOnNavigationBarListener(onNavigationBarListener: OnNavigationBarListener?) {
        if (onNavigationBarListener == null) {
            return
        }
        if (mListeners == null) {
            mListeners = ArrayList()
        }
        if (!mListeners!!.contains(onNavigationBarListener)) {
            mListeners!!.add(onNavigationBarListener)
        }
    }

    fun removeOnNavigationBarListener(onNavigationBarListener: OnNavigationBarListener?) {
        if (onNavigationBarListener != null) {
            mListeners?.remove(onNavigationBarListener)
        }
    }

    companion object {
        private val INSTANCE: NavigationBarObserver = NavigationBarObserver()

        @JvmStatic
        fun getInstance(): NavigationBarObserver {
            return INSTANCE
        }
    }
}
