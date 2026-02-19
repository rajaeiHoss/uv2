package com.streamax.config.bean;

import com.streamax.config.base.ConfigFragment;

public class FragmentBean {
    public ConfigFragment mFragment;
    public String mTitle;
    public int mVisible = 0;

    public FragmentBean() {
    }

    public FragmentBean(String str, ConfigFragment configFragment) {
        this.mTitle = str;
        this.mFragment = configFragment;
    }

    public FragmentBean(String str, ConfigFragment configFragment, int i) {
        this.mTitle = str;
        this.mFragment = configFragment;
        this.mVisible = i;
    }
}
