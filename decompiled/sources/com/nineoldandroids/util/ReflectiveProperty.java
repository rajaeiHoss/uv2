package com.nineoldandroids.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ReflectiveProperty<T, V> extends Property<T, V> {
    private static final String PREFIX_GET = "get";
    private static final String PREFIX_IS = "is";
    private static final String PREFIX_SET = "set";
    private Field mField;
    private Method mGetter;
    private Method mSetter;

    /* JADX WARNING: Can't wrap try/catch for region: R(3:15|16|(1:18)(2:19|20)) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r11 = r11.getField(r13);
        r10.mField = r11;
        r11 = r11.getType();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00b4, code lost:
        if (typesMatch(r12, r11) != false) goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00b6, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00d7, code lost:
        throw new com.nineoldandroids.util.NoSuchPropertyException("Underlying type (" + r11 + ") " + "does not match Property type (" + r12 + ")");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x00a6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ReflectiveProperty(java.lang.Class<T> r11, java.lang.Class<V> r12, java.lang.String r13) {
        super(r12, r13);
        /*
            r10 = this;
            java.lang.String r0 = ")"
            java.lang.String r1 = "does not match Property type ("
            java.lang.String r2 = ") "
            java.lang.String r3 = "Underlying type ("
            r10.<init>(r12, r13)
            r4 = 0
            char r5 = r13.charAt(r4)
            char r5 = java.lang.Character.toUpperCase(r5)
            r6 = 1
            java.lang.String r7 = r13.substring(r6)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r5)
            r8.append(r7)
            java.lang.String r5 = r8.toString()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "get"
            r7.append(r8)
            r7.append(r5)
            java.lang.String r7 = r7.toString()
            r8 = 0
            r9 = r8
            java.lang.Class[] r9 = (java.lang.Class[]) r9     // Catch:{ NoSuchMethodException -> 0x0044 }
            java.lang.reflect.Method r7 = r11.getMethod(r7, r9)     // Catch:{ NoSuchMethodException -> 0x0044 }
            r10.mGetter = r7     // Catch:{ NoSuchMethodException -> 0x0044 }
            goto L_0x005d
        L_0x0044:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "is"
            r7.append(r9)
            r7.append(r5)
            java.lang.String r7 = r7.toString()
            java.lang.Class[] r8 = (java.lang.Class[]) r8     // Catch:{ NoSuchMethodException -> 0x00a6 }
            java.lang.reflect.Method r7 = r11.getMethod(r7, r8)     // Catch:{ NoSuchMethodException -> 0x00a6 }
            r10.mGetter = r7     // Catch:{ NoSuchMethodException -> 0x00a6 }
        L_0x005d:
            java.lang.reflect.Method r13 = r10.mGetter
            java.lang.Class r13 = r13.getReturnType()
            boolean r7 = r10.typesMatch(r12, r13)
            if (r7 == 0) goto L_0x0085
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "set"
            r12.append(r0)
            r12.append(r5)
            java.lang.String r12 = r12.toString()
            java.lang.Class[] r0 = new java.lang.Class[r6]     // Catch:{ NoSuchMethodException -> 0x0084 }
            r0[r4] = r13     // Catch:{ NoSuchMethodException -> 0x0084 }
            java.lang.reflect.Method r11 = r11.getMethod(r12, r0)     // Catch:{ NoSuchMethodException -> 0x0084 }
            r10.mSetter = r11     // Catch:{ NoSuchMethodException -> 0x0084 }
        L_0x0084:
            return
        L_0x0085:
            com.nineoldandroids.util.NoSuchPropertyException r11 = new com.nineoldandroids.util.NoSuchPropertyException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            r4.append(r13)
            r4.append(r2)
            r4.append(r1)
            r4.append(r12)
            r4.append(r0)
            java.lang.String r12 = r4.toString()
            r11.<init>(r12)
            throw r11
        L_0x00a6:
            java.lang.reflect.Field r11 = r11.getField(r13)     // Catch:{ NoSuchFieldException -> 0x00d8 }
            r10.mField = r11     // Catch:{ NoSuchFieldException -> 0x00d8 }
            java.lang.Class r11 = r11.getType()     // Catch:{ NoSuchFieldException -> 0x00d8 }
            boolean r4 = r10.typesMatch(r12, r11)     // Catch:{ NoSuchFieldException -> 0x00d8 }
            if (r4 == 0) goto L_0x00b7
            return
        L_0x00b7:
            com.nineoldandroids.util.NoSuchPropertyException r4 = new com.nineoldandroids.util.NoSuchPropertyException     // Catch:{ NoSuchFieldException -> 0x00d8 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ NoSuchFieldException -> 0x00d8 }
            r5.<init>()     // Catch:{ NoSuchFieldException -> 0x00d8 }
            r5.append(r3)     // Catch:{ NoSuchFieldException -> 0x00d8 }
            r5.append(r11)     // Catch:{ NoSuchFieldException -> 0x00d8 }
            r5.append(r2)     // Catch:{ NoSuchFieldException -> 0x00d8 }
            r5.append(r1)     // Catch:{ NoSuchFieldException -> 0x00d8 }
            r5.append(r12)     // Catch:{ NoSuchFieldException -> 0x00d8 }
            r5.append(r0)     // Catch:{ NoSuchFieldException -> 0x00d8 }
            java.lang.String r11 = r5.toString()     // Catch:{ NoSuchFieldException -> 0x00d8 }
            r4.<init>(r11)     // Catch:{ NoSuchFieldException -> 0x00d8 }
            throw r4     // Catch:{ NoSuchFieldException -> 0x00d8 }
        L_0x00d8:
            com.nineoldandroids.util.NoSuchPropertyException r11 = new com.nineoldandroids.util.NoSuchPropertyException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "No accessor method or field found for property with name "
            r12.append(r0)
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nineoldandroids.util.ReflectiveProperty.<init>(java.lang.Class, java.lang.Class, java.lang.String):void");
    }

    private boolean typesMatch(Class<V> cls, Class cls2) {
        if (cls2 == cls) {
            return true;
        }
        if (!cls2.isPrimitive()) {
            return false;
        }
        if (cls2 == Float.TYPE && cls == Float.class) {
            return true;
        }
        if (cls2 == Integer.TYPE && cls == Integer.class) {
            return true;
        }
        if (cls2 == Boolean.TYPE && cls == Boolean.class) {
            return true;
        }
        if (cls2 == Long.TYPE && cls == Long.class) {
            return true;
        }
        if (cls2 == Double.TYPE && cls == Double.class) {
            return true;
        }
        if (cls2 == Short.TYPE && cls == Short.class) {
            return true;
        }
        if (cls2 == Byte.TYPE && cls == Byte.class) {
            return true;
        }
        if (cls2 == Character.TYPE && cls == Character.class) {
            return true;
        }
        return false;
    }

    public void set(T t, V v) {
        Method method = this.mSetter;
        if (method != null) {
            try {
                method.invoke(t, new Object[]{v});
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getCause());
            }
        } else {
            Field field = this.mField;
            if (field != null) {
                try {
                    field.set(t, v);
                } catch (IllegalAccessException unused2) {
                    throw new AssertionError();
                }
            } else {
                throw new UnsupportedOperationException("Property " + getName() + " is read-only");
            }
        }
    }

    public V get(T t) {
        Method method = this.mGetter;
        if (method != null) {
            try {
                return (V) method.invoke(t, (Object[]) null);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getCause());
            }
        } else {
            Field field = this.mField;
            if (field != null) {
                try {
                    return (V) field.get(t);
                } catch (IllegalAccessException unused2) {
                    throw new AssertionError();
                }
            } else {
                throw new AssertionError();
            }
        }
    }

    public boolean isReadOnly() {
        return this.mSetter == null && this.mField == null;
    }
}
