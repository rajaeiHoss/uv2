package de.greenrobot.event;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class EventBus {
    private static /* synthetic */ int[] $SWITCH_TABLE$de$greenrobot$event$ThreadMode;
    public static String TAG = "Event";
    private static volatile EventBus defaultInstance;
    private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new HashMap();
    static ExecutorService executorService = Executors.newCachedThreadPool();
    private final AsyncPoster asyncPoster = new AsyncPoster(this);
    private final BackgroundPoster backgroundPoster = new BackgroundPoster(this);
    private final ThreadLocal<List<Object>> currentThreadEventQueue = new ThreadLocal<List<Object>>() {
        /* access modifiers changed from: protected */
        public List<Object> initialValue() {
            return new ArrayList();
        }
    };
    private final ThreadLocal<BooleanWrapper> currentThreadIsPosting = new ThreadLocal<BooleanWrapper>() {
        /* access modifiers changed from: protected */
        public BooleanWrapper initialValue() {
            return new BooleanWrapper();
        }
    };
    private String defaultMethodName = "onEvent";
    private boolean logSubscriberExceptions = true;
    private final HandlerPoster mainThreadPoster = new HandlerPoster(this, Looper.getMainLooper(), 10);
    private final Map<Class<?>, Object> stickyEvents = new ConcurrentHashMap();
    private boolean subscribed;
    private final SubscriberMethodFinder subscriberMethodFinder = new SubscriberMethodFinder();
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType = new HashMap();
    private final Map<Object, List<Class<?>>> typesBySubscriber = new HashMap();

    interface PostCallback {
        void onPostCompleted(List<SubscriberExceptionEvent> list);
    }

    static /* synthetic */ int[] $SWITCH_TABLE$de$greenrobot$event$ThreadMode() {
        int[] iArr = $SWITCH_TABLE$de$greenrobot$event$ThreadMode;
        if (iArr == null) {
            iArr = new int[ThreadMode.values().length];
            try {
                iArr[ThreadMode.Async.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ThreadMode.BackgroundThread.ordinal()] = 3;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ThreadMode.MainThread.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ThreadMode.PostThread.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            $SWITCH_TABLE$de$greenrobot$event$ThreadMode = iArr;
        }
        return iArr;
    }

    public static EventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }
        return defaultInstance;
    }

    public static void clearCaches() {
        SubscriberMethodFinder.clearCaches();
        eventTypesCache.clear();
    }

    public static void skipMethodNameVerificationFor(Class<?> cls) {
        SubscriberMethodFinder.skipMethodNameVerificationFor(cls);
    }

    public static void clearSkipMethodNameVerifications() {
        SubscriberMethodFinder.clearSkipMethodNameVerifications();
    }

    public void configureLogSubscriberExceptions(boolean z) {
        if (!this.subscribed) {
            this.logSubscriberExceptions = z;
            return;
        }
        throw new EventBusException("This method must be called before any registration");
    }

    public void register(Object obj) {
        register(obj, this.defaultMethodName, false);
    }

    public void register(Object obj, String str) {
        register(obj, str, false);
    }

    public void registerSticky(Object obj) {
        register(obj, this.defaultMethodName, true);
    }

    public void registerSticky(Object obj, String str) {
        register(obj, str, true);
    }

    private void register(Object obj, String str, boolean z) {
        for (SubscriberMethod subscribe : this.subscriberMethodFinder.findSubscriberMethods(obj.getClass(), str)) {
            subscribe(obj, subscribe, z);
        }
    }

    public void register(Object obj, Class<?> cls, Class<?>... clsArr) {
        register(obj, this.defaultMethodName, false, cls, clsArr);
    }

    public synchronized void register(Object obj, String str, Class<?> cls, Class<?>... clsArr) {
        register(obj, str, false, cls, clsArr);
    }

    public void registerSticky(Object obj, Class<?> cls, Class<?>... clsArr) {
        register(obj, this.defaultMethodName, true, cls, clsArr);
    }

    public synchronized void registerSticky(Object obj, String str, Class<?> cls, Class<?>... clsArr) {
        register(obj, str, true, cls, clsArr);
    }

    private synchronized void register(Object obj, String str, boolean z, Class<?> cls, Class<?>... clsArr) {
        for (SubscriberMethod next : this.subscriberMethodFinder.findSubscriberMethods(obj.getClass(), str)) {
            if (cls == next.eventType) {
                subscribe(obj, next, z);
            } else if (clsArr != null) {
                int length = clsArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (clsArr[i] == next.eventType) {
                        subscribe(obj, next, z);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    private void subscribe(Object obj, SubscriberMethod subscriberMethod, boolean z) {
        Object obj2;
        boolean z2 = true;
        this.subscribed = true;
        Class<?> cls = subscriberMethod.eventType;
        CopyOnWriteArrayList copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        Subscription subscription = new Subscription(obj, subscriberMethod);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.subscriptionsByEventType.put(cls, copyOnWriteArrayList);
        } else {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                if (((Subscription) it.next()).equals(subscription)) {
                    throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
                }
            }
        }
        subscriberMethod.method.setAccessible(true);
        copyOnWriteArrayList.add(subscription);
        List list = this.typesBySubscriber.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.typesBySubscriber.put(obj, list);
        }
        list.add(cls);
        if (z) {
            synchronized (this.stickyEvents) {
                obj2 = this.stickyEvents.get(cls);
            }
            if (obj2 != null) {
                if (Looper.getMainLooper() != Looper.myLooper()) {
                    z2 = false;
                }
                postToSubscription(subscription, obj2, z2);
            }
        }
    }

    public synchronized void unregister(Object obj, Class<?>... clsArr) {
        if (clsArr.length != 0) {
            List list = this.typesBySubscriber.get(obj);
            if (list != null) {
                for (Class<?> cls : clsArr) {
                    unubscribeByEventType(obj, cls);
                    list.remove(cls);
                }
                if (list.isEmpty()) {
                    this.typesBySubscriber.remove(obj);
                }
            } else {
                Log.w(TAG, "Subscriber to unregister was not registered before: " + obj.getClass());
            }
        } else {
            throw new IllegalArgumentException("Provide at least one event class");
        }
    }

    private void unubscribeByEventType(Object obj, Class<?> cls) {
        List list = this.subscriptionsByEventType.get(cls);
        if (list != null) {
            int size = list.size();
            int i = 0;
            while (i < size) {
                if (((Subscription) list.get(i)).subscriber == obj) {
                    list.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
        }
    }

    public synchronized void unregister(Object obj) {
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list != null) {
            for (Class<?> eventType : list) {
                unubscribeByEventType(obj, eventType);
            }
            this.typesBySubscriber.remove(obj);
        } else {
            String str = TAG;
            Log.w(str, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public void post(Object obj) {
        List list = this.currentThreadEventQueue.get();
        list.add(obj);
        BooleanWrapper booleanWrapper = this.currentThreadIsPosting.get();
        if (!booleanWrapper.value) {
            boolean z = Looper.getMainLooper() == Looper.myLooper();
            booleanWrapper.value = true;
            while (!list.isEmpty()) {
                try {
                    postSingleEvent(list.remove(0), z);
                } finally {
                    booleanWrapper.value = false;
                }
            }
        }
    }

    public void postSticky(Object obj) {
        post(obj);
        synchronized (this.stickyEvents) {
            this.stickyEvents.put(obj.getClass(), obj);
        }
    }

    public Object getStickyEvent(Class<?> cls) {
        Object obj;
        synchronized (this.stickyEvents) {
            obj = this.stickyEvents.get(cls);
        }
        return obj;
    }

    public Object removeStickyEvent(Class<?> cls) {
        Object remove;
        synchronized (this.stickyEvents) {
            remove = this.stickyEvents.remove(cls);
        }
        return remove;
    }

    public boolean removeStickyEvent(Object obj) {
        synchronized (this.stickyEvents) {
            Class<?> cls = obj.getClass();
            if (!obj.equals(this.stickyEvents.get(cls))) {
                return false;
            }
            this.stickyEvents.remove(cls);
            return true;
        }
    }

    private void postSingleEvent(Object obj, boolean z) throws Error {
        CopyOnWriteArrayList copyOnWriteArrayList;
        Class<?> cls = obj.getClass();
        List<Class<?>> findEventTypes = findEventTypes(cls);
        int size = findEventTypes.size();
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            Class cls2 = findEventTypes.get(i);
            synchronized (this) {
                copyOnWriteArrayList = this.subscriptionsByEventType.get(cls2);
            }
            if (copyOnWriteArrayList != null) {
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    postToSubscription((Subscription) it.next(), obj, z);
                }
                z2 = true;
            }
        }
        if (!z2) {
            String str = TAG;
            Log.d(str, "No subscripers registered for event " + cls);
            if (cls != NoSubscriberEvent.class && cls != SubscriberExceptionEvent.class) {
                post(new NoSubscriberEvent(this, obj));
            }
        }
    }

    private void postToSubscription(Subscription subscription, Object obj, boolean z) {
        int i = $SWITCH_TABLE$de$greenrobot$event$ThreadMode()[subscription.subscriberMethod.threadMode.ordinal()];
        if (i == 1) {
            invokeSubscriber(subscription, obj);
        } else if (i != 2) {
            if (i != 3) {
                if (i == 4) {
                    this.asyncPoster.enqueue(subscription, obj);
                    return;
                }
                throw new IllegalStateException("Unknown thread mode: " + subscription.subscriberMethod.threadMode);
            } else if (z) {
                this.backgroundPoster.enqueue(subscription, obj);
            } else {
                invokeSubscriber(subscription, obj);
            }
        } else if (z) {
            invokeSubscriber(subscription, obj);
        } else {
            this.mainThreadPoster.enqueue(subscription, obj);
        }
    }

    private List<Class<?>> findEventTypes(Class<?> cls) {
        List<Class<?>> list;
        Map<Class<?>, List<Class<?>>> map = eventTypesCache;
        synchronized (map) {
            list = map.get(cls);
            if (list == null) {
                list = new ArrayList<>();
                for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    addInterfaces(list, cls2.getInterfaces());
                }
                eventTypesCache.put(cls, list);
            }
        }
        return list;
    }

    static void addInterfaces(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                addInterfaces(list, cls.getInterfaces());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void invokeSubscriber(PendingPost pendingPost) {
        Object obj = pendingPost.event;
        Subscription subscription = pendingPost.subscription;
        PendingPost.releasePendingPost(pendingPost);
        invokeSubscriber(subscription, obj);
    }

    /* access modifiers changed from: package-private */
    public void invokeSubscriber(Subscription subscription, Object obj) throws Error {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, new Object[]{obj});
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (obj instanceof SubscriberExceptionEvent) {
                String str = TAG;
                Log.e(str, "SubscriberExceptionEvent subscriber " + subscription.subscriber.getClass() + " threw an exception", cause);
                SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
                String str2 = TAG;
                Log.e(str2, "Initial event " + subscriberExceptionEvent.causingEvent + " caused exception in " + subscriberExceptionEvent.causingSubscriber, subscriberExceptionEvent.throwable);
                return;
            }
            if (this.logSubscriberExceptions) {
                String str3 = TAG;
                Log.e(str3, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + subscription.subscriber.getClass(), cause);
            }
            post(new SubscriberExceptionEvent(this, cause, obj, subscription.subscriber));
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    static final class BooleanWrapper {
        boolean value;

        BooleanWrapper() {
        }
    }
}
