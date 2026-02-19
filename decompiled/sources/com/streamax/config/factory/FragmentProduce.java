package com.streamax.config.factory;

import com.streamax.config.base.ConfigFragment;
import com.streamax.config.fragment.FragmentCMS;
import com.streamax.config.fragment.FragmentDevInfo;
import com.streamax.config.fragment.FragmentSysTime;
import com.streamax.config.fragment.FragmentXGNet;

public class FragmentProduce {
    public static final int PositionCMS = 3;
    public static final int PositionDevInfo = 0;
    public static final int PositionSysTime = 1;
    public static final int PositionXGNet = 2;

    public static ConfigFragment createFragment(int i) {
        if (i == 0) {
            return new FragmentDevInfo();
        }
        if (i == 1) {
            return new FragmentSysTime();
        }
        if (i == 2) {
            return new FragmentXGNet();
        }
        if (i != 3) {
            return null;
        }
        return new FragmentCMS();
    }
}
