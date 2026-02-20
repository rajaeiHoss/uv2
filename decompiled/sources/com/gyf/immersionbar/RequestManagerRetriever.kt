package com.gyf.immersionbar

import android.app.Activity
import android.app.Dialog
import android.app.FragmentManager
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

internal class RequestManagerRetriever private constructor() : Handler.Callback {
    private var mHandler: Handler = Handler(Looper.getMainLooper(), this)
    private val mPendingFragments: MutableMap<FragmentManager, RequestManagerFragment> = HashMap()
    private val mPendingSupportFragments: MutableMap<androidx.fragment.app.FragmentManager, SupportRequestManagerFragment> =
        HashMap()
    private var mTag: String = ImmersionBar::class.java.name

    fun get(activity: Activity): ImmersionBar {
        checkNotNull(activity, "activity is null")
        val tag = mTag + System.identityHashCode(activity)
        return if (activity is FragmentActivity) {
            getSupportFragment(activity.supportFragmentManager, tag).get(activity)
        } else {
            getFragment(activity.fragmentManager, tag).get(activity)
        }
    }

    fun get(fragment: Fragment, isOnly: Boolean): ImmersionBar {
        val tag: String
        checkNotNull(fragment, "fragment is null")
        checkNotNull(fragment.activity, "fragment.getActivity() is null")
        if (fragment is DialogFragment) {
            checkNotNull(fragment.dialog, "fragment.getDialog() is null")
        }
        val defaultTag = mTag
        tag = if (isOnly) {
            defaultTag + fragment.javaClass.name
        } else {
            defaultTag + System.identityHashCode(fragment)
        }
        return getSupportFragment(fragment.childFragmentManager, tag).get(fragment)
    }

    fun get(fragment: android.app.Fragment, isOnly: Boolean): ImmersionBar {
        val tag: String
        checkNotNull(fragment, "fragment is null")
        checkNotNull(fragment.activity, "fragment.getActivity() is null")
        if (fragment is android.app.DialogFragment) {
            checkNotNull(fragment.dialog, "fragment.getDialog() is null")
        }
        val defaultTag = mTag
        tag = if (isOnly) {
            defaultTag + fragment.javaClass.name
        } else {
            defaultTag + System.identityHashCode(fragment)
        }
        return getFragment(fragment.childFragmentManager, tag).get(fragment)
    }

    fun get(activity: Activity, dialog: Dialog): ImmersionBar {
        checkNotNull(activity, "activity is null")
        checkNotNull(dialog, "dialog is null")
        val tag = mTag + System.identityHashCode(dialog)
        return if (activity is FragmentActivity) {
            getSupportFragment(activity.supportFragmentManager, tag).get(activity, dialog)
        } else {
            getFragment(activity.fragmentManager, tag).get(activity, dialog)
        }
    }

    fun destroy(fragment: Fragment?, isOnly: Boolean) {
        if (fragment != null) {
            val defaultTag = mTag
            val tag = if (isOnly) {
                defaultTag + fragment.javaClass.name
            } else {
                defaultTag + System.identityHashCode(fragment)
            }
            getSupportFragment(fragment.childFragmentManager, tag, true)
        }
    }

    fun destroy(activity: Activity?, dialog: Dialog?) {
        if (activity != null && dialog != null) {
            val tag = mTag + System.identityHashCode(dialog)
            if (activity is FragmentActivity) {
                val supportFragment = getSupportFragment(activity.supportFragmentManager, tag, true)
                supportFragment?.get(activity, dialog)?.onDestroy()
                return
            }
            val fragment = getFragment(activity.fragmentManager, tag, true)
            fragment?.get(activity, dialog)?.onDestroy()
        }
    }

    override fun handleMessage(message: Message): Boolean {
        return when (message.what) {
            ID_REMOVE_FRAGMENT_MANAGER -> {
                mPendingFragments.remove(message.obj as FragmentManager)
                true
            }

            ID_REMOVE_SUPPORT_FRAGMENT_MANAGER -> {
                mPendingSupportFragments.remove(message.obj as androidx.fragment.app.FragmentManager)
                true
            }

            else -> false
        }
    }

    private fun getFragment(fragmentManager: FragmentManager, tag: String): RequestManagerFragment {
        return getFragment(fragmentManager, tag, false)!!
    }

    private fun getFragment(
        fragmentManager: FragmentManager,
        tag: String,
        isRemove: Boolean
    ): RequestManagerFragment? {
        var requestManagerFragment = fragmentManager.findFragmentByTag(tag) as? RequestManagerFragment
        if (requestManagerFragment == null) {
            requestManagerFragment = mPendingFragments[fragmentManager]
            if (requestManagerFragment == null) {
                if (isRemove) {
                    return null
                }
                requestManagerFragment = RequestManagerFragment()
                mPendingFragments[fragmentManager] = requestManagerFragment
                fragmentManager.beginTransaction().add(requestManagerFragment, tag).commitAllowingStateLoss()
                mHandler.obtainMessage(ID_REMOVE_FRAGMENT_MANAGER, fragmentManager).sendToTarget()
            }
        }
        if (!isRemove) {
            return requestManagerFragment
        }
        fragmentManager.beginTransaction().remove(requestManagerFragment).commitAllowingStateLoss()
        return null
    }

    private fun getSupportFragment(
        fragmentManager: androidx.fragment.app.FragmentManager,
        tag: String
    ): SupportRequestManagerFragment {
        return getSupportFragment(fragmentManager, tag, false)!!
    }

    private fun getSupportFragment(
        fragmentManager: androidx.fragment.app.FragmentManager,
        tag: String,
        isRemove: Boolean
    ): SupportRequestManagerFragment? {
        var supportRequestManagerFragment =
            fragmentManager.findFragmentByTag(tag) as? SupportRequestManagerFragment
        if (supportRequestManagerFragment == null) {
            supportRequestManagerFragment = mPendingSupportFragments[fragmentManager]
            if (supportRequestManagerFragment == null) {
                if (isRemove) {
                    return null
                }
                supportRequestManagerFragment = SupportRequestManagerFragment()
                mPendingSupportFragments[fragmentManager] = supportRequestManagerFragment
                fragmentManager.beginTransaction().add(supportRequestManagerFragment as Fragment, tag)
                    .commitAllowingStateLoss()
                mHandler.obtainMessage(ID_REMOVE_SUPPORT_FRAGMENT_MANAGER, fragmentManager).sendToTarget()
            }
        }
        if (!isRemove) {
            return supportRequestManagerFragment
        }
        fragmentManager.beginTransaction().remove(supportRequestManagerFragment).commitAllowingStateLoss()
        return null
    }

    companion object {
        private const val ID_REMOVE_FRAGMENT_MANAGER: Int = 1
        private const val ID_REMOVE_SUPPORT_FRAGMENT_MANAGER: Int = 2

        private val INSTANCE: RequestManagerRetriever = RequestManagerRetriever()

        @JvmStatic
        fun getInstance(): RequestManagerRetriever {
            return INSTANCE
        }

        private fun <T> checkNotNull(value: T?, message: String) {
            if (value == null) {
                throw NullPointerException(message)
            }
        }
    }
}
