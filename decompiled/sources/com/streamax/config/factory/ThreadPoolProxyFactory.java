package com.streamax.config.factory;

import com.streamax.config.proxy.ThreadPoolProxy;

public class ThreadPoolProxyFactory {
    private static ThreadPoolProxy mDevConfigThreadPoolProxy;
    private static ThreadPoolProxy mVideoPlayThreadPoolProxy;

    public static ThreadPoolProxy getDevConfigThreadPoolProxy() {
        if (mDevConfigThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mDevConfigThreadPoolProxy == null) {
                    mDevConfigThreadPoolProxy = new ThreadPoolProxy(1, 1, 7);
                }
            }
        }
        return mDevConfigThreadPoolProxy;
    }

    public static ThreadPoolProxy getVideoPlayThreadPoolProxy() {
        if (mVideoPlayThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mVideoPlayThreadPoolProxy == null) {
                    mVideoPlayThreadPoolProxy = new ThreadPoolProxy(16, 16, 5);
                }
            }
        }
        return mVideoPlayThreadPoolProxy;
    }
}
