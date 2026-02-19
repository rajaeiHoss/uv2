package org.aspectj.runtime.reflect;

import java.util.Stack;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.internal.AroundClosure;

class JoinPointImpl implements ProceedingJoinPoint {
    Object _this;
    private AroundClosure arc = null;
    private Stack<AroundClosure> arcs = null;
    Object[] args;
    JoinPoint.StaticPart staticPart;
    Object target;

    static class StaticPartImpl implements JoinPoint.StaticPart {
        private int id;
        String kind;
        Signature signature;
        SourceLocation sourceLocation;

        public StaticPartImpl(int i, String str, Signature signature2, SourceLocation sourceLocation2) {
            this.kind = str;
            this.signature = signature2;
            this.sourceLocation = sourceLocation2;
            this.id = i;
        }

        public int getId() {
            return this.id;
        }

        public String getKind() {
            return this.kind;
        }

        public Signature getSignature() {
            return this.signature;
        }

        public SourceLocation getSourceLocation() {
            return this.sourceLocation;
        }

        /* access modifiers changed from: package-private */
        public String toString(StringMaker stringMaker) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(stringMaker.makeKindName(getKind()));
            stringBuffer.append("(");
            stringBuffer.append(((SignatureImpl) getSignature()).toString(stringMaker));
            stringBuffer.append(")");
            return stringBuffer.toString();
        }

        public final String toString() {
            return toString(StringMaker.middleStringMaker);
        }

        public final String toShortString() {
            return toString(StringMaker.shortStringMaker);
        }

        public final String toLongString() {
            return toString(StringMaker.longStringMaker);
        }
    }

    static class EnclosingStaticPartImpl extends StaticPartImpl implements JoinPoint.EnclosingStaticPart {
        public EnclosingStaticPartImpl(int i, String str, Signature signature, SourceLocation sourceLocation) {
            super(i, str, signature, sourceLocation);
        }
    }

    public JoinPointImpl(JoinPoint.StaticPart staticPart2, Object obj, Object obj2, Object[] objArr) {
        this.staticPart = staticPart2;
        this._this = obj;
        this.target = obj2;
        this.args = objArr;
    }

    public Object getThis() {
        return this._this;
    }

    public Object getTarget() {
        return this.target;
    }

    public Object[] getArgs() {
        if (this.args == null) {
            this.args = new Object[0];
        }
        Object[] objArr = this.args;
        Object[] objArr2 = new Object[objArr.length];
        System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        return objArr2;
    }

    public JoinPoint.StaticPart getStaticPart() {
        return this.staticPart;
    }

    public String getKind() {
        return this.staticPart.getKind();
    }

    public Signature getSignature() {
        return this.staticPart.getSignature();
    }

    public SourceLocation getSourceLocation() {
        return this.staticPart.getSourceLocation();
    }

    public final String toString() {
        return this.staticPart.toString();
    }

    public final String toShortString() {
        return this.staticPart.toShortString();
    }

    public final String toLongString() {
        return this.staticPart.toLongString();
    }

    public void set$AroundClosure(AroundClosure aroundClosure) {
        this.arc = aroundClosure;
    }

    public void stack$AroundClosure(AroundClosure aroundClosure) {
        if (this.arcs == null) {
            this.arcs = new Stack<>();
        }
        if (aroundClosure == null) {
            this.arcs.pop();
        } else {
            this.arcs.push(aroundClosure);
        }
    }

    public Object proceed() throws Throwable {
        Stack<AroundClosure> stack = this.arcs;
        if (stack != null) {
            return stack.peek().run(this.arcs.peek().getState());
        }
        AroundClosure aroundClosure = this.arc;
        if (aroundClosure == null) {
            return null;
        }
        return aroundClosure.run(aroundClosure.getState());
    }

    public Object proceed(Object[] objArr) throws Throwable {
        AroundClosure aroundClosure;
        int i;
        Stack<AroundClosure> stack = this.arcs;
        if (stack == null) {
            aroundClosure = this.arc;
        } else {
            aroundClosure = stack.peek();
        }
        if (aroundClosure == null) {
            return null;
        }
        int flags = aroundClosure.getFlags();
        int i2 = 1048576 & flags;
        int i3 = 1;
        boolean z = (65536 & flags) != 0;
        int i4 = (flags & 4096) != 0 ? 1 : 0;
        int i5 = (flags & 256) != 0 ? 1 : 0;
        boolean z2 = (flags & 16) != 0;
        boolean z3 = (flags & 1) != 0;
        Object[] state = aroundClosure.getState();
        int i6 = i4 + 0 + ((!z2 || z) ? 0 : 1);
        if (i4 == 0 || i5 == 0) {
            i = 0;
        } else {
            state[0] = objArr[0];
            i = 1;
        }
        if (z2 && z3) {
            if (z) {
                i = i5 + 1;
                state[0] = objArr[i5];
            } else {
                char c = (i4 == 0 || i5 == 0) ? (char) 0 : 1;
                int i7 = (i4 == 0 || i5 == 0) ? 0 : 1;
                if (!z2 || !z3 || z) {
                    i3 = 0;
                }
                state[i4] = objArr[c];
                i = i7 + i3;
            }
        }
        for (int i8 = i; i8 < objArr.length; i8++) {
            state[(i8 - i) + i6] = objArr[i8];
        }
        return aroundClosure.run(state);
    }
}
