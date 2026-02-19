package com.streamax.config;

import com.streamax.config.base.ConfigFragment;
import java.util.HashMap;
import java.util.Map;

public class FragmentContainer {
    public static Map<Class<?>, ConfigFragment> fragmentContainer = new HashMap();
}
