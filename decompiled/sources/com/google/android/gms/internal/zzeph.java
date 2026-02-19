package com.google.android.gms.internal;

import android.util.Log;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.PropertyName;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

final class zzeph<T> {
    /* access modifiers changed from: private */
    public final Class<T> zznro;
    private final Constructor<T> zznrp;
    private final boolean zznrq;
    private final boolean zznrr;
    private final Map<String, String> zznrs = new HashMap();
    private final Map<String, Method> zznrt = new HashMap();
    private final Map<String, Method> zznru = new HashMap();
    private final Map<String, Field> zznrv = new HashMap();

    /* JADX WARNING: Removed duplicated region for block: B:104:0x029e  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x013d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzeph(java.lang.Class<T> r13) {
        /*
            r12 = this;
            r12.<init>()
            r12.zznro = r13
            java.lang.Class<com.google.firebase.database.ThrowOnExtraProperties> r0 = com.google.firebase.database.ThrowOnExtraProperties.class
            boolean r0 = r13.isAnnotationPresent(r0)
            r12.zznrq = r0
            java.lang.Class<com.google.firebase.database.IgnoreExtraProperties> r0 = com.google.firebase.database.IgnoreExtraProperties.class
            boolean r0 = r13.isAnnotationPresent(r0)
            r1 = 1
            r0 = r0 ^ r1
            r12.zznrr = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r12.zznrs = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r12.zznru = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r12.zznrt = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r12.zznrv = r0
            r0 = 0
            java.lang.Class[] r2 = new java.lang.Class[r0]     // Catch:{ NoSuchMethodException -> 0x003e }
            java.lang.reflect.Constructor r2 = r13.getDeclaredConstructor(r2)     // Catch:{ NoSuchMethodException -> 0x003e }
            r2.setAccessible(r1)     // Catch:{ NoSuchMethodException -> 0x003e }
            goto L_0x003f
        L_0x003e:
            r2 = 0
        L_0x003f:
            r12.zznrp = r2
            java.lang.reflect.Method[] r2 = r13.getMethods()
            int r3 = r2.length
            r4 = 0
        L_0x0047:
            if (r4 >= r3) goto L_0x00e5
            r5 = r2[r4]
            java.lang.String r6 = r5.getName()
            java.lang.String r7 = "get"
            boolean r6 = r6.startsWith(r7)
            if (r6 != 0) goto L_0x0064
            java.lang.String r6 = r5.getName()
            java.lang.String r7 = "is"
            boolean r6 = r6.startsWith(r7)
            if (r6 != 0) goto L_0x0064
            goto L_0x0070
        L_0x0064:
            java.lang.Class r6 = r5.getDeclaringClass()
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0072
        L_0x0070:
            r6 = 0
            goto L_0x00a7
        L_0x0072:
            int r6 = r5.getModifiers()
            boolean r6 = java.lang.reflect.Modifier.isPublic(r6)
            if (r6 != 0) goto L_0x007d
            goto L_0x0070
        L_0x007d:
            int r6 = r5.getModifiers()
            boolean r6 = java.lang.reflect.Modifier.isStatic(r6)
            if (r6 == 0) goto L_0x0088
            goto L_0x0070
        L_0x0088:
            java.lang.Class r6 = r5.getReturnType()
            java.lang.Class r7 = java.lang.Void.TYPE
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0095
            goto L_0x0070
        L_0x0095:
            java.lang.Class[] r6 = r5.getParameterTypes()
            int r6 = r6.length
            if (r6 == 0) goto L_0x009d
            goto L_0x0070
        L_0x009d:
            java.lang.Class<com.google.firebase.database.Exclude> r6 = com.google.firebase.database.Exclude.class
            boolean r6 = r5.isAnnotationPresent(r6)
            if (r6 == 0) goto L_0x00a6
            goto L_0x0070
        L_0x00a6:
            r6 = 1
        L_0x00a7:
            if (r6 == 0) goto L_0x00e1
            java.lang.String r6 = zza((java.lang.reflect.Method) r5)
            r12.zzqq(r6)
            r5.setAccessible(r1)
            java.util.Map<java.lang.String, java.lang.reflect.Method> r7 = r12.zznrt
            boolean r7 = r7.containsKey(r6)
            if (r7 == 0) goto L_0x00dc
            com.google.firebase.database.DatabaseException r13 = new com.google.firebase.database.DatabaseException
            java.lang.String r0 = "Found conflicting getters for name: "
            java.lang.String r1 = r5.getName()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x00d2
            java.lang.String r0 = r0.concat(r1)
            goto L_0x00d8
        L_0x00d2:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            r0 = r1
        L_0x00d8:
            r13.<init>(r0)
            throw r13
        L_0x00dc:
            java.util.Map<java.lang.String, java.lang.reflect.Method> r7 = r12.zznrt
            r7.put(r6, r5)
        L_0x00e1:
            int r4 = r4 + 1
            goto L_0x0047
        L_0x00e5:
            java.lang.reflect.Field[] r2 = r13.getFields()
            int r3 = r2.length
            r4 = 0
        L_0x00eb:
            if (r4 >= r3) goto L_0x0134
            r5 = r2[r4]
            java.lang.Class r6 = r5.getDeclaringClass()
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00fd
        L_0x00fb:
            r6 = 0
            goto L_0x0128
        L_0x00fd:
            int r6 = r5.getModifiers()
            boolean r6 = java.lang.reflect.Modifier.isPublic(r6)
            if (r6 != 0) goto L_0x0108
            goto L_0x00fb
        L_0x0108:
            int r6 = r5.getModifiers()
            boolean r6 = java.lang.reflect.Modifier.isStatic(r6)
            if (r6 == 0) goto L_0x0113
            goto L_0x00fb
        L_0x0113:
            int r6 = r5.getModifiers()
            boolean r6 = java.lang.reflect.Modifier.isTransient(r6)
            if (r6 == 0) goto L_0x011e
            goto L_0x00fb
        L_0x011e:
            java.lang.Class<com.google.firebase.database.Exclude> r6 = com.google.firebase.database.Exclude.class
            boolean r6 = r5.isAnnotationPresent(r6)
            if (r6 == 0) goto L_0x0127
            goto L_0x00fb
        L_0x0127:
            r6 = 1
        L_0x0128:
            if (r6 == 0) goto L_0x0131
            java.lang.String r5 = zza((java.lang.reflect.Field) r5)
            r12.zzqq(r5)
        L_0x0131:
            int r4 = r4 + 1
            goto L_0x00eb
        L_0x0134:
            r2 = r13
        L_0x0135:
            java.lang.reflect.Method[] r3 = r2.getDeclaredMethods()
            int r4 = r3.length
            r5 = 0
        L_0x013b:
            if (r5 >= r4) goto L_0x0296
            r6 = r3[r5]
            java.lang.String r7 = r6.getName()
            java.lang.String r8 = "set"
            boolean r7 = r7.startsWith(r8)
            if (r7 != 0) goto L_0x014d
        L_0x014b:
            r7 = 0
            goto L_0x0184
        L_0x014d:
            java.lang.Class r7 = r6.getDeclaringClass()
            java.lang.Class<java.lang.Object> r8 = java.lang.Object.class
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x015a
            goto L_0x014b
        L_0x015a:
            int r7 = r6.getModifiers()
            boolean r7 = java.lang.reflect.Modifier.isStatic(r7)
            if (r7 == 0) goto L_0x0165
            goto L_0x014b
        L_0x0165:
            java.lang.Class r7 = r6.getReturnType()
            java.lang.Class r8 = java.lang.Void.TYPE
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x0172
            goto L_0x014b
        L_0x0172:
            java.lang.Class[] r7 = r6.getParameterTypes()
            int r7 = r7.length
            if (r7 == r1) goto L_0x017a
            goto L_0x014b
        L_0x017a:
            java.lang.Class<com.google.firebase.database.Exclude> r7 = com.google.firebase.database.Exclude.class
            boolean r7 = r6.isAnnotationPresent(r7)
            if (r7 == 0) goto L_0x0183
            goto L_0x014b
        L_0x0183:
            r7 = 1
        L_0x0184:
            if (r7 == 0) goto L_0x0292
            java.lang.String r7 = zza((java.lang.reflect.Method) r6)
            java.util.Map<java.lang.String, java.lang.String> r8 = r12.zznrs
            java.lang.String r9 = r7.toLowerCase()
            java.lang.Object r8 = r8.get(r9)
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L_0x0292
            boolean r8 = r8.equals(r7)
            if (r8 != 0) goto L_0x01bf
            com.google.firebase.database.DatabaseException r13 = new com.google.firebase.database.DatabaseException
            java.lang.String r0 = "Found setter with invalid case-sensitive name: "
            java.lang.String r1 = r6.getName()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x01b5
            java.lang.String r0 = r0.concat(r1)
            goto L_0x01bb
        L_0x01b5:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            r0 = r1
        L_0x01bb:
            r13.<init>(r0)
            throw r13
        L_0x01bf:
            java.util.Map<java.lang.String, java.lang.reflect.Method> r8 = r12.zznru
            java.lang.Object r8 = r8.get(r7)
            java.lang.reflect.Method r8 = (java.lang.reflect.Method) r8
            if (r8 != 0) goto L_0x01d3
            r6.setAccessible(r1)
            java.util.Map<java.lang.String, java.lang.reflect.Method> r8 = r12.zznru
            r8.put(r7, r6)
            goto L_0x0292
        L_0x01d3:
            java.lang.Class r7 = r6.getDeclaringClass()
            java.lang.Class r9 = r8.getDeclaringClass()
            boolean r7 = r7.isAssignableFrom(r9)
            java.lang.String r9 = "Expected override from a base class"
            com.google.android.gms.internal.zzepd.zzb(r7, r9)
            java.lang.Class r7 = r6.getReturnType()
            java.lang.Class r9 = java.lang.Void.TYPE
            boolean r7 = r7.equals(r9)
            java.lang.String r9 = "Expected void return type"
            com.google.android.gms.internal.zzepd.zzb(r7, r9)
            java.lang.Class r7 = r8.getReturnType()
            java.lang.Class r10 = java.lang.Void.TYPE
            boolean r7 = r7.equals(r10)
            com.google.android.gms.internal.zzepd.zzb(r7, r9)
            java.lang.Class[] r7 = r6.getParameterTypes()
            java.lang.Class[] r9 = r8.getParameterTypes()
            int r10 = r7.length
            if (r10 != r1) goto L_0x020d
            r10 = 1
            goto L_0x020e
        L_0x020d:
            r10 = 0
        L_0x020e:
            java.lang.String r11 = "Expected exactly one parameter"
            com.google.android.gms.internal.zzepd.zzb(r10, r11)
            int r10 = r9.length
            if (r10 != r1) goto L_0x0218
            r10 = 1
            goto L_0x0219
        L_0x0218:
            r10 = 0
        L_0x0219:
            com.google.android.gms.internal.zzepd.zzb(r10, r11)
            java.lang.String r10 = r6.getName()
            java.lang.String r11 = r8.getName()
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x0236
            r7 = r7[r0]
            r9 = r9[r0]
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L_0x0236
            r7 = 1
            goto L_0x0237
        L_0x0236:
            r7 = 0
        L_0x0237:
            if (r7 == 0) goto L_0x023a
            goto L_0x0292
        L_0x023a:
            com.google.firebase.database.DatabaseException r13 = new com.google.firebase.database.DatabaseException
            java.lang.String r0 = r6.getName()
            java.lang.String r1 = r8.getName()
            java.lang.Class r2 = r8.getDeclaringClass()
            java.lang.String r2 = r2.getName()
            java.lang.String r3 = java.lang.String.valueOf(r0)
            int r3 = r3.length()
            int r3 = r3 + 69
            java.lang.String r4 = java.lang.String.valueOf(r1)
            int r4 = r4.length()
            int r3 = r3 + r4
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r4 = r4.length()
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Found a conflicting setters with name: "
            r4.append(r3)
            r4.append(r0)
            java.lang.String r0 = " (conflicts with "
            r4.append(r0)
            r4.append(r1)
            java.lang.String r0 = " defined on "
            r4.append(r0)
            r4.append(r2)
            java.lang.String r0 = ")"
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r13.<init>(r0)
            throw r13
        L_0x0292:
            int r5 = r5 + 1
            goto L_0x013b
        L_0x0296:
            java.lang.reflect.Field[] r3 = r2.getDeclaredFields()
            int r4 = r3.length
            r5 = 0
        L_0x029c:
            if (r5 >= r4) goto L_0x02c3
            r6 = r3[r5]
            java.lang.String r7 = zza((java.lang.reflect.Field) r6)
            java.util.Map<java.lang.String, java.lang.String> r8 = r12.zznrs
            java.lang.String r9 = r7.toLowerCase()
            boolean r8 = r8.containsKey(r9)
            if (r8 == 0) goto L_0x02c0
            java.util.Map<java.lang.String, java.lang.reflect.Field> r8 = r12.zznrv
            boolean r8 = r8.containsKey(r7)
            if (r8 != 0) goto L_0x02c0
            r6.setAccessible(r1)
            java.util.Map<java.lang.String, java.lang.reflect.Field> r8 = r12.zznrv
            r8.put(r7, r6)
        L_0x02c0:
            int r5 = r5 + 1
            goto L_0x029c
        L_0x02c3:
            java.lang.Class r2 = r2.getSuperclass()
            if (r2 == 0) goto L_0x02d1
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x0135
        L_0x02d1:
            java.util.Map<java.lang.String, java.lang.String> r0 = r12.zznrs
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x02f9
            com.google.firebase.database.DatabaseException r0 = new com.google.firebase.database.DatabaseException
            java.lang.String r1 = "No properties to serialize found on class "
            java.lang.String r13 = r13.getName()
            java.lang.String r13 = java.lang.String.valueOf(r13)
            int r2 = r13.length()
            if (r2 == 0) goto L_0x02f0
            java.lang.String r13 = r1.concat(r13)
            goto L_0x02f5
        L_0x02f0:
            java.lang.String r13 = new java.lang.String
            r13.<init>(r1)
        L_0x02f5:
            r0.<init>(r13)
            throw r0
        L_0x02f9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzeph.<init>(java.lang.Class):void");
    }

    private static String zza(AccessibleObject accessibleObject) {
        if (accessibleObject.isAnnotationPresent(PropertyName.class)) {
            return ((PropertyName) accessibleObject.getAnnotation(PropertyName.class)).value();
        }
        return null;
    }

    private static String zza(Field field) {
        String zza = zza((AccessibleObject) field);
        return zza != null ? zza : field.getName();
    }

    private static String zza(Method method) {
        String zza = zza((AccessibleObject) method);
        if (zza != null) {
            return zza;
        }
        String name = method.getName();
        String[] strArr = {"get", "set", "is"};
        String str = null;
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            String str2 = strArr[i2];
            if (name.startsWith(str2)) {
                str = str2;
            }
        }
        if (str == null) {
            String valueOf = String.valueOf(name);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unknown Bean prefix for method: ".concat(valueOf) : new String("Unknown Bean prefix for method: "));
        }
        char[] charArray = name.substring(str.length()).toCharArray();
        while (i < charArray.length && Character.isUpperCase(charArray[i])) {
            charArray[i] = Character.toLowerCase(charArray[i]);
            i++;
        }
        return new String(charArray);
    }

    private static Type zza(Type type, Map<TypeVariable<?>, Type> map) {
        if (!(type instanceof TypeVariable)) {
            return type;
        }
        Type type2 = map.get(type);
        if (type2 != null) {
            return type2;
        }
        String valueOf = String.valueOf(type);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
        sb.append("Could not resolve type ");
        sb.append(valueOf);
        throw new IllegalStateException(sb.toString());
    }

    private final void zzqq(String str) {
        String put = this.zznrs.put(str.toLowerCase(), str);
        if (put != null && !str.equals(put)) {
            String valueOf = String.valueOf(str.toLowerCase());
            throw new DatabaseException(valueOf.length() != 0 ? "Found two getters or fields with conflicting case sensitivity for property: ".concat(valueOf) : new String("Found two getters or fields with conflicting case sensitivity for property: "));
        }
    }

    public final Map<String, Object> zzcf(T t) {
        Object obj;
        if (this.zznro.isAssignableFrom(t.getClass())) {
            HashMap hashMap = new HashMap();
            for (String next : this.zznrs.values()) {
                if (this.zznrt.containsKey(next)) {
                    try {
                        obj = this.zznrt.get(next).invoke(t, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException(e2);
                    }
                } else {
                    Field field = this.zznrv.get(next);
                    if (field == null) {
                        String valueOf = String.valueOf(next);
                        throw new IllegalStateException(valueOf.length() != 0 ? "Bean property without field or getter:".concat(valueOf) : new String("Bean property without field or getter:"));
                    }
                    try {
                        obj = field.get(t);
                    } catch (IllegalAccessException e3) {
                        throw new RuntimeException(e3);
                    }
                }
                hashMap.put(next, zzepg.zzcb(obj));
            }
            return hashMap;
        }
        String valueOf2 = String.valueOf(t.getClass());
        String valueOf3 = String.valueOf(this.zznro);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 59 + String.valueOf(valueOf3).length());
        sb.append("Can't serialize object of class ");
        sb.append(valueOf2);
        sb.append(" with BeanMapper for class ");
        sb.append(valueOf3);
        throw new IllegalArgumentException(sb.toString());
    }

    public final T zze(Map<String, Object> map, Map<TypeVariable<?>, Type> map2) {
        Constructor<T> constructor = this.zznrp;
        if (constructor != null) {
            try {
                T newInstance = constructor.newInstance(new Object[0]);
                for (Map.Entry next : map.entrySet()) {
                    String str = (String) next.getKey();
                    if (this.zznru.containsKey(str)) {
                        Method method = this.zznru.get(str);
                        Type[] genericParameterTypes = method.getGenericParameterTypes();
                        if (genericParameterTypes.length == 1) {
                            try {
                                method.invoke(newInstance, new Object[]{zzepg.zza(next.getValue(), zza(genericParameterTypes[0], map2))});
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            } catch (InvocationTargetException e2) {
                                throw new RuntimeException(e2);
                            }
                        } else {
                            throw new IllegalStateException("Setter does not have exactly one parameter");
                        }
                    } else if (this.zznrv.containsKey(str)) {
                        Field field = this.zznrv.get(str);
                        try {
                            field.set(newInstance, zzepg.zza(next.getValue(), zza(field.getGenericType(), map2)));
                        } catch (IllegalAccessException e3) {
                            throw new RuntimeException(e3);
                        }
                    } else {
                        String name = this.zznro.getName();
                        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 36 + String.valueOf(name).length());
                        sb.append("No setter/field for ");
                        sb.append(str);
                        sb.append(" found on class ");
                        sb.append(name);
                        String sb2 = sb.toString();
                        if (this.zznrs.containsKey(str.toLowerCase())) {
                            sb2 = String.valueOf(sb2).concat(" (fields/setters are case sensitive!)");
                        }
                        if (this.zznrq) {
                            throw new DatabaseException(sb2);
                        } else if (this.zznrr) {
                            Log.w("ClassMapper", sb2);
                        }
                    }
                }
                return newInstance;
            } catch (InstantiationException e4) {
                throw new RuntimeException(e4);
            } catch (IllegalAccessException e5) {
                throw new RuntimeException(e5);
            } catch (InvocationTargetException e6) {
                throw new RuntimeException(e6);
            }
        } else {
            String name2 = this.zznro.getName();
            StringBuilder sb3 = new StringBuilder(String.valueOf(name2).length() + 123);
            sb3.append("Class ");
            sb3.append(name2);
            sb3.append(" does not define a no-argument constructor. If you are using ProGuard, make sure these constructors are not stripped.");
            throw new DatabaseException(sb3.toString());
        }
    }
}
