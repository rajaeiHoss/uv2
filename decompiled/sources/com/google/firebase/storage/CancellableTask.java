package com.google.firebase.storage;

import android.app.Activity;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executor;

public abstract class CancellableTask<TState> extends Task<TState> {
    public abstract CancellableTask<TState> addOnProgressListener(Activity activity, OnProgressListener<? super TState> onProgressListener);

    public abstract CancellableTask<TState> addOnProgressListener(OnProgressListener<? super TState> onProgressListener);

    public abstract CancellableTask<TState> addOnProgressListener(Executor executor, OnProgressListener<? super TState> onProgressListener);

    public abstract boolean cancel();

    public abstract boolean isCanceled();

    public abstract boolean isInProgress();
}
