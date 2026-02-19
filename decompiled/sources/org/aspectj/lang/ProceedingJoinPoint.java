package org.aspectj.lang;

import org.aspectj.runtime.internal.AroundClosure;

public interface ProceedingJoinPoint extends JoinPoint {
    Object proceed() throws Throwable;

    Object proceed(Object[] objArr) throws Throwable;

    void set$AroundClosure(AroundClosure aroundClosure);

    void stack$AroundClosure(AroundClosure aroundClosure);

    /* renamed from: org.aspectj.lang.ProceedingJoinPoint$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$stack$AroundClosure(ProceedingJoinPoint _this, AroundClosure aroundClosure) {
            throw new UnsupportedOperationException();
        }
    }
}
