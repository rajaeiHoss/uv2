package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

class FullLifecycleObserverAdapter implements LifecycleEventObserver {
    private final FullLifecycleObserver mFullLifecycleObserver;
    private final LifecycleEventObserver mLifecycleEventObserver;

    FullLifecycleObserverAdapter(FullLifecycleObserver fullLifecycleObserver, LifecycleEventObserver lifecycleEventObserver) {
        this.mFullLifecycleObserver = fullLifecycleObserver;
        this.mLifecycleEventObserver = lifecycleEventObserver;
    }

    /* renamed from: androidx.lifecycle.FullLifecycleObserverAdapter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$Event;

        static {
            Lifecycle.Event[] values = Lifecycle.Event.values();
            int[] iArr = new int[values.length];
            for (int i = 0; i < values.length; i++) {
                iArr[i] = i + 1;
            }
            $SwitchMap$androidx$lifecycle$Lifecycle$Event = iArr;
        }
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        switch (AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$Event[event.ordinal()]) {
            case 1:
                this.mFullLifecycleObserver.onCreate(lifecycleOwner);
                break;
            case 2:
                this.mFullLifecycleObserver.onStart(lifecycleOwner);
                break;
            case 3:
                this.mFullLifecycleObserver.onResume(lifecycleOwner);
                break;
            case 4:
                this.mFullLifecycleObserver.onPause(lifecycleOwner);
                break;
            case 5:
                this.mFullLifecycleObserver.onStop(lifecycleOwner);
                break;
            case 6:
                this.mFullLifecycleObserver.onDestroy(lifecycleOwner);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        LifecycleEventObserver lifecycleEventObserver = this.mLifecycleEventObserver;
        if (lifecycleEventObserver != null) {
            lifecycleEventObserver.onStateChanged(lifecycleOwner, event);
        }
    }
}
