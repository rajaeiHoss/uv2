package com.hjq.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.hjq.base.action.BundleAction
import com.hjq.base.action.ClickAction
import com.hjq.base.action.HandlerAction
import com.hjq.base.action.KeyboardAction

abstract class BaseFragment<A : BaseActivity> : Fragment(),
    HandlerAction,
    ClickAction,
    BundleAction,
    KeyboardAction {
    private var mActivity: A? = null
    private var mLoading: Boolean = false
    private var mRootView: View? = null

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()

    protected abstract fun initData()

    protected open fun onActivityResume() {
    }

    protected open fun onFragmentResume(firstLoad: Boolean) {
    }

    open fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return false
    }

    open fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return false
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = requireActivity() as A
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (getLayoutId() <= 0) {
            return null
        }
        mLoading = false
        mRootView = inflater.inflate(getLayoutId(), container, false)
        initView()
        return mRootView
    }

    override fun onResume() {
        super.onResume()
        if (!mLoading) {
            mLoading = true
            initData()
            onFragmentResume(true)
            return
        }
        val activity = mActivity
        if (activity == null || activity.lifecycle.currentState != Lifecycle.State.STARTED) {
            onFragmentResume(false)
        } else {
            onActivityResume()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mRootView = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mLoading = false
        removeCallbacks()
    }

    override fun onDetach() {
        super.onDetach()
        mActivity = null
    }

    fun isLoading(): Boolean {
        return mLoading
    }

    override fun getView(): View? {
        return mRootView
    }

    fun getAttachActivity(): A {
        return mActivity!!
    }

    fun getApplication(): Application? {
        val activity = mActivity
        if (activity != null) {
            return activity.application
        }
        return null
    }

    override fun <V : View> findViewById(id: Int): V {
        return mRootView!!.findViewById(id)
    }

    override fun getBundle(): Bundle? {
        return arguments
    }

    fun startActivity(activityClass: Class<out Activity>) {
        startActivity(Intent(requireContext(), activityClass))
    }

    fun getColor(id: Int): Int {
        return ContextCompat.getColor(requireContext(), id)
    }

    fun getDrawable(id: Int) = ContextCompat.getDrawable(requireContext(), id)

    fun <S> getSystemService(serviceClass: Class<S>): S? {
        return ContextCompat.getSystemService(requireContext(), serviceClass)
    }

    fun startActivityForResult(
        activityClass: Class<out Activity>,
        callback: BaseActivity.OnActivityCallback
    ) {
        getAttachActivity().startActivityForResult(activityClass, callback)
    }

    fun startActivityForResult(intent: Intent, callback: BaseActivity.OnActivityCallback) {
        getAttachActivity().startActivityForResult(intent, null, callback)
    }

    fun startActivityForResult(
        intent: Intent,
        options: Bundle?,
        callback: BaseActivity.OnActivityCallback
    ) {
        getAttachActivity().startActivityForResult(intent, options, callback)
    }

    fun finish() {
        val activity = mActivity
        if (activity != null && !activity.isFinishing && !activity.isDestroyed) {
            activity.finish()
        }
    }

    fun dispatchKeyEvent(event: KeyEvent): Boolean {
        for (fragment in childFragmentManager.fragments) {
            if (fragment is BaseFragment<*> &&
                fragment.lifecycle.currentState == Lifecycle.State.RESUMED &&
                fragment.dispatchKeyEvent(event)
            ) {
                return true
            }
        }
        return when (event.action) {
            KeyEvent.ACTION_DOWN -> onKeyDown(event.keyCode, event)
            KeyEvent.ACTION_UP -> onKeyUp(event.keyCode, event)
            else -> false
        }
    }
}
