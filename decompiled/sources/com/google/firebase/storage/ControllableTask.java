package com.google.firebase.storage;

import android.app.Activity;
import java.util.concurrent.Executor;

public abstract class ControllableTask<TState> extends CancellableTask<TState> {
    public abstract ControllableTask<TState> addOnPausedListener(Activity activity, OnPausedListener<? super TState> onPausedListener);

    public abstract ControllableTask<TState> addOnPausedListener(OnPausedListener<? super TState> onPausedListener);

    public abstract ControllableTask<TState> addOnPausedListener(Executor executor, OnPausedListener<? super TState> onPausedListener);

    public abstract boolean isPaused();

    public abstract boolean pause();

    public abstract boolean resume();
}
