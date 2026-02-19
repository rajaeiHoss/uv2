package com.hjq.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import android.view.KeyEvent
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.hjq.base.action.ActivityAction
import com.hjq.base.action.BundleAction
import com.hjq.base.action.ClickAction
import com.hjq.base.action.HandlerAction
import com.hjq.base.action.KeyboardAction
import java.util.Random

abstract class BaseActivity : AppCompatActivity(),
    ActivityAction,
    ClickAction,
    HandlerAction,
    BundleAction,
    KeyboardAction {
    private var mActivityCallbacks: SparseArray<OnActivityCallback>? = null

    interface OnActivityCallback {
        fun onActivityResult(resultCode: Int, data: Intent?)
    }

    override fun getContext(): Context {
        return this
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()

    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
    }

    protected fun initActivity() {
        initLayout()
        initView()
        initData()
    }

    protected fun initLayout() {
        if (getLayoutId() > 0) {
            setContentView(getLayoutId())
            initSoftKeyboard()
        }
    }

    protected fun initSoftKeyboard() {
        getContentView().setOnClickListener {
            hideKeyboard(currentFocus)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        removeCallbacks()
    }

    override fun finish() {
        super.finish()
        hideKeyboard(currentFocus)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun getBundle(): Bundle? {
        return intent.extras
    }

    fun getContentView(): ViewGroup {
        return findViewById(16908290)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is BaseFragment<*> &&
                fragment.lifecycle.currentState == Lifecycle.State.RESUMED &&
                fragment.dispatchKeyEvent(event)
            ) {
                return true
            }
        }
        return super.dispatchKeyEvent(event)
    }

    override fun startActivity(intent: Intent) {
        super<AppCompatActivity>.startActivity(intent)
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?) {
        hideKeyboard(currentFocus)
        super.startActivityForResult(intent, requestCode, options)
    }

    fun startActivityForResult(
        activityClass: Class<out Activity>,
        callback: OnActivityCallback
    ) {
        startActivityForResult(Intent(this, activityClass), null, callback)
    }

    fun startActivityForResult(intent: Intent, callback: OnActivityCallback) {
        startActivityForResult(intent, null, callback)
    }

    fun startActivityForResult(
        intent: Intent,
        options: Bundle?,
        callback: OnActivityCallback
    ) {
        if (mActivityCallbacks == null) {
            mActivityCallbacks = SparseArray(1)
        }
        val requestCode = Random().nextInt(Math.pow(2.0, 16.0).toInt())
        mActivityCallbacks!!.put(requestCode, callback)
        startActivityForResult(intent, requestCode, options)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = mActivityCallbacks?.get(requestCode)
        if (callback == null) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        callback.onActivityResult(resultCode, data)
        mActivityCallbacks!!.remove(requestCode)
    }

    companion object {
        const val RESULT_ERROR: Int = -2
    }
}
