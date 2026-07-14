package com.streamax.config.bean;

import com.streamax.config.base.ConfigFragment;

public class FragmentBean {
    public ConfigFragment mFragment;
    public String mTitle;
    public int mVisible = 0;

    public FragmentBean() {
    }

    public FragmentBean(String title, ConfigFragment configFragment) {
        this.mTitle = title;
        this.mFragment = configFragment;
    }

    public FragmentBean(String title, ConfigFragment configFragment, int visible) {
        this.mTitle = title;
        this.mFragment = configFragment;
        this.mVisible = visible;
    }
}
