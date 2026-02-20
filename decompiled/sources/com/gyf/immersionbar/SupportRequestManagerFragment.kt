package com.gyf.immersionbar

import android.app.Activity
import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment

class SupportRequestManagerFragment : Fragment() {
    private var mDelegate: ImmersionDelegate? = null

    fun get(obj: Any): ImmersionBar {
        if (mDelegate == null) {
            mDelegate = ImmersionDelegate(obj)
        }
        return mDelegate!!.get()
    }

    fun get(activity: Activity, dialog: Dialog): ImmersionBar {
        if (mDelegate == null) {
            mDelegate = ImmersionDelegate(activity, dialog)
        }
        return mDelegate!!.get()
    }

    override fun onActivityCreated(bundle: Bundle?) {
        super.onActivityCreated(bundle)
        mDelegate?.onActivityCreated(resources.configuration)
    }

    override fun onResume() {
        super.onResume()
        mDelegate?.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDelegate?.onDestroy()
        mDelegate = null
    }

    override fun onConfigurationChanged(configuration: Configuration) {
        super.onConfigurationChanged(configuration)
        mDelegate?.onConfigurationChanged(configuration)
    }
}
