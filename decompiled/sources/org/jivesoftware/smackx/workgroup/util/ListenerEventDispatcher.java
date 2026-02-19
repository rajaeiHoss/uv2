package org.jivesoftware.smackx.workgroup.util;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.ListIterator;

public class ListenerEventDispatcher implements Runnable {
    protected transient boolean hasFinishedDispatching = false;
    protected transient boolean isRunning = false;
    protected transient ArrayList triplets = new ArrayList();

    public void addListenerTriplet(Object obj, Method method, Object[] objArr) {
        if (!this.isRunning) {
            this.triplets.add(new TripletContainer(obj, method, objArr));
        }
    }

    public boolean hasFinished() {
        return this.hasFinishedDispatching;
    }

    public void run() {
        this.isRunning = true;
        ListIterator listIterator = this.triplets.listIterator();
        while (listIterator.hasNext()) {
            TripletContainer tripletContainer = (TripletContainer) listIterator.next();
            try {
                tripletContainer.getListenerMethod().invoke(tripletContainer.getListenerInstance(), tripletContainer.getMethodArguments());
            } catch (Exception e) {
                PrintStream printStream = System.err;
                printStream.println("Exception dispatching an event: " + e);
                e.printStackTrace();
            }
        }
        this.hasFinishedDispatching = true;
    }

    protected class TripletContainer {
        protected Object listenerInstance;
        protected Method listenerMethod;
        protected Object[] methodArguments;

        protected TripletContainer(Object obj, Method method, Object[] objArr) {
            this.listenerInstance = obj;
            this.listenerMethod = method;
            this.methodArguments = objArr;
        }

        /* access modifiers changed from: protected */
        public Object getListenerInstance() {
            return this.listenerInstance;
        }

        /* access modifiers changed from: protected */
        public Method getListenerMethod() {
            return this.listenerMethod;
        }

        /* access modifiers changed from: protected */
        public Object[] getMethodArguments() {
            return this.methodArguments;
        }
    }
}
