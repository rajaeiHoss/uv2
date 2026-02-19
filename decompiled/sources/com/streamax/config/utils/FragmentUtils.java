package com.streamax.config.utils;

import androidx.fragment.app.Fragment;
import com.streamax.config.FragmentContainer;
import com.streamax.config.base.BaseFragment;
import com.streamax.config.base.ConfigFragment;
import com.zycs.UView.R;

public class FragmentUtils {
    public static void fragmentReplace(BaseFragment baseFragment, BaseFragment baseFragment2) {
        baseFragment.getFragmentManager().beginTransaction().remove(baseFragment).replace(R.id.config_framelayout, baseFragment2).commit();
    }

    public static void fragmentReplace(BaseFragment baseFragment, BaseFragment baseFragment2, int i) {
        baseFragment.getFragmentManager().beginTransaction().remove(baseFragment).replace(i, baseFragment2).commit();
    }

    public static void showAndHide(BaseFragment baseFragment, BaseFragment baseFragment2) {
        baseFragment.getFragmentManager().beginTransaction().add((int) R.id.config_framelayout, (Fragment) baseFragment2).hide(baseFragment).commit();
    }

    public static void showAndRemove(BaseFragment baseFragment, BaseFragment baseFragment2) {
        baseFragment.getFragmentManager().beginTransaction().remove(baseFragment).hide(baseFragment).show(baseFragment2).commit();
    }

    public static void showTargetFragment(ConfigFragment configFragment, ConfigFragment configFragment2) {
        ConfigFragment configFragment3 = FragmentContainer.fragmentContainer.get(configFragment.getClass());
        if (configFragment3 == null) {
            FragmentContainer.fragmentContainer.put(configFragment.getClass(), configFragment);
            configFragment.getFragmentManager().beginTransaction().add((int) R.id.config_framelayout, (Fragment) configFragment2).hide(configFragment).commit();
            return;
        }
        configFragment.getFragmentManager().beginTransaction().show(configFragment3).hide(configFragment).commit();
    }
}
