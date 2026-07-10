package com.streamax.config.proxy;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolProxy {
    private int mCorePoolSize;
    private ThreadPoolExecutor mExecutor;
    private long mKeepAliveTime;
    private int mMaximumPoolSize;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.mCorePoolSize = corePoolSize;
        this.mMaximumPoolSize = maximumPoolSize;
        this.mKeepAliveTime = keepAliveTime;
    }

    public synchronized void initThreadPoolExecutor() {
        if (this.mExecutor == null || this.mExecutor.isShutdown() || this.mExecutor.isTerminated()) {
            this.mExecutor = new ThreadPoolExecutor(this.mCorePoolSize, this.mMaximumPoolSize, this.mKeepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        }
    }

    public Future<?> submit(Runnable runnable) {
        initThreadPoolExecutor();
        return this.mExecutor.submit(runnable);
    }

    public void execute(Runnable runnable) {
        initThreadPoolExecutor();
        this.mExecutor.execute(runnable);
    }

    public void remove(Runnable runnable) {
        initThreadPoolExecutor();
        this.mExecutor.remove(runnable);
    }
}
