package com.hjq.base

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager

class FragmentPagerAdapter<F : Fragment>(fragmentManager: FragmentManager) :
    androidx.fragment.app.FragmentPagerAdapter(fragmentManager, 1) {
    private val mFragmentSet: MutableList<F> = ArrayList()
    private val mFragmentTitle: MutableList<CharSequence?> = ArrayList()
    private var mLazyMode: Boolean = true
    private var mShowFragment: F? = null
    private var mViewPager: ViewPager? = null

    constructor(fragmentActivity: FragmentActivity) : this(fragmentActivity.supportFragmentManager)

    constructor(fragment: Fragment) : this(fragment.childFragmentManager)

    override fun getItem(position: Int): F {
        return mFragmentSet[position]
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }

    override fun getCount(): Int {
        return mFragmentSet.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitle[position]
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, obj: Any) {
        super.setPrimaryItem(container, position, obj)
        if (getShowFragment() !== obj) {
            mShowFragment = obj as F
        }
    }

    fun addFragment(fragment: F) {
        addFragment(fragment, null)
    }

    fun addFragment(fragment: F, title: CharSequence?) {
        mFragmentSet.add(fragment)
        mFragmentTitle.add(title)
        if (mViewPager != null) {
            notifyDataSetChanged()
            if (mLazyMode) {
                mViewPager!!.offscreenPageLimit = getCount()
            } else {
                mViewPager!!.offscreenPageLimit = 1
            }
        }
    }

    fun getShowFragment(): F? {
        return mShowFragment
    }

    fun getFragmentIndex(fragmentClass: Class<out Fragment>?): Int {
        if (fragmentClass == null) {
            return -1
        }
        for (i in mFragmentSet.indices) {
            if (fragmentClass.name == mFragmentSet[i].javaClass.name) {
                return i
            }
        }
        return -1
    }

    override fun startUpdate(container: ViewGroup) {
        super.startUpdate(container)
        if (container is ViewPager) {
            mViewPager = container
            refreshLazyMode()
        }
    }

    fun setLazyMode(lazyMode: Boolean) {
        mLazyMode = lazyMode
        refreshLazyMode()
    }

    private fun refreshLazyMode() {
        val viewPager = mViewPager
        if (viewPager != null) {
            if (mLazyMode) {
                viewPager.offscreenPageLimit = getCount()
            } else {
                viewPager.offscreenPageLimit = 1
            }
        }
    }
}
