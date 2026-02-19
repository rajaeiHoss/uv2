package com.hjq.base;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

public final class FragmentPagerAdapter<F extends Fragment> extends androidx.fragment.app.FragmentPagerAdapter {
    private final List<F> mFragmentSet;
    private final List<CharSequence> mFragmentTitle;
    private boolean mLazyMode;
    private F mShowFragment;
    private ViewPager mViewPager;

    public FragmentPagerAdapter(FragmentActivity fragmentActivity) {
        this(fragmentActivity.getSupportFragmentManager());
    }

    public FragmentPagerAdapter(Fragment fragment) {
        this(fragment.getChildFragmentManager());
    }

    public FragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager, 1);
        this.mFragmentSet = new ArrayList();
        this.mFragmentTitle = new ArrayList();
        this.mLazyMode = true;
    }

    public F getItem(int i) {
        return this.mFragmentSet.get(i);
    }

    public long getItemId(int i) {
        return (long) getItem(i).hashCode();
    }

    public int getCount() {
        return this.mFragmentSet.size();
    }

    public CharSequence getPageTitle(int i) {
        return this.mFragmentTitle.get(i);
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        super.setPrimaryItem(viewGroup, i, obj);
        if (getShowFragment() != obj) {
            this.mShowFragment = (F) obj;
        }
    }

    public void addFragment(F f) {
        addFragment(f, (CharSequence) null);
    }

    public void addFragment(F f, CharSequence charSequence) {
        this.mFragmentSet.add(f);
        this.mFragmentTitle.add(charSequence);
        if (this.mViewPager != null) {
            notifyDataSetChanged();
            if (this.mLazyMode) {
                this.mViewPager.setOffscreenPageLimit(getCount());
            } else {
                this.mViewPager.setOffscreenPageLimit(1);
            }
        }
    }

    public F getShowFragment() {
        return this.mShowFragment;
    }

    public int getFragmentIndex(Class<? extends Fragment> cls) {
        if (cls == null) {
            return -1;
        }
        for (int i = 0; i < this.mFragmentSet.size(); i++) {
            if (cls.getName().equals(((Fragment) this.mFragmentSet.get(i)).getClass().getName())) {
                return i;
            }
        }
        return -1;
    }

    public void startUpdate(ViewGroup viewGroup) {
        super.startUpdate(viewGroup);
        if (viewGroup instanceof ViewPager) {
            this.mViewPager = (ViewPager) viewGroup;
            refreshLazyMode();
        }
    }

    public void setLazyMode(boolean z) {
        this.mLazyMode = z;
        refreshLazyMode();
    }

    private void refreshLazyMode() {
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            if (this.mLazyMode) {
                viewPager.setOffscreenPageLimit(getCount());
            } else {
                viewPager.setOffscreenPageLimit(1);
            }
        }
    }
}
