package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.GenericTypeIndicator;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class zzepg {
    private static final ConcurrentMap<Class<?>, zzeph<?>> zznrn = new ConcurrentHashMap();

    public static <T> T zza(Object obj, GenericTypeIndicator<T> genericTypeIndicator) {
        Type genericSuperclass = genericTypeIndicator.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            if (parameterizedType.getRawType().equals(GenericTypeIndicator.class)) {
                return zza(obj, parameterizedType.getActualTypeArguments()[0]);
            }
            String valueOf = String.valueOf(genericSuperclass);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
            sb.append("Not a direct subclass of GenericTypeIndicator: ");
            sb.append(valueOf);
            throw new DatabaseException(sb.toString());
        }
        String valueOf2 = String.valueOf(genericSuperclass);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 47);
        sb2.append("Not a direct subclass of GenericTypeIndicator: ");
        sb2.append(valueOf2);
        throw new DatabaseException(sb2.toString());
    }

    public static <T> T zza(Object obj, Class<T> cls) {
        return zzb(obj, cls);
    }

    /* access modifiers changed from: private */
    public static <T> T zza(Object obj, Type type) {
        if (obj == null) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class cls = (Class) parameterizedType.getRawType();
            if (List.class.isAssignableFrom(cls)) {
                Type type2 = parameterizedType.getActualTypeArguments()[0];
                if (obj instanceof List) {
                    List<Object> list = (List) obj;
                    List<Object> arrayList = new ArrayList(list.size());
                    for (Object zza : list) {
                        arrayList.add(zza(zza, type2));
                    }
                    return (T) arrayList;
                }
                String valueOf = String.valueOf(obj.getClass());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
                sb.append("Expected a List while deserializing, but got a ");
                sb.append(valueOf);
                throw new DatabaseException(sb.toString());
            } else if (Map.class.isAssignableFrom(cls)) {
                Type type3 = parameterizedType.getActualTypeArguments()[0];
                Type type4 = parameterizedType.getActualTypeArguments()[1];
                if (type3.equals(String.class)) {
                    Map<String, Object> zzcc = zzcc(obj);
                    Map<String, Object> hashMap = new HashMap<>();
                    for (Map.Entry next : zzcc.entrySet()) {
                        hashMap.put((String) next.getKey(), zza(next.getValue(), type4));
                    }
                    return (T) hashMap;
                }
                String valueOf2 = String.valueOf(type3);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 70);
                sb2.append("Only Maps with string keys are supported, but found Map with key type ");
                sb2.append(valueOf2);
                throw new DatabaseException(sb2.toString());
            } else if (!Collection.class.isAssignableFrom(cls)) {
                Map<String, Object> zzcc2 = zzcc(obj);
                zzeph zzf = zzf(cls);
                HashMap hashMap2 = new HashMap();
                TypeVariable[] typeParameters = zzf.zznro.getTypeParameters();
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments.length == typeParameters.length) {
                    for (int i = 0; i < typeParameters.length; i++) {
                        hashMap2.put(typeParameters[i], actualTypeArguments[i]);
                    }
                    return (T) zzf.zze(zzcc2, hashMap2);
                }
                throw new IllegalStateException("Mismatched lengths for type variables and actual types");
            } else {
                throw new DatabaseException("Collections are not supported, please use Lists instead");
            }
        } else if (type instanceof Class) {
            return (T) zzb(obj, (Class) type);
        } else {
            if (type instanceof WildcardType) {
                throw new DatabaseException("Generic wildcard types are not supported");
            } else if (type instanceof GenericArrayType) {
                throw new DatabaseException("Generic Arrays are not supported, please use Lists instead");
            } else {
                String valueOf3 = String.valueOf(type);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 26);
                sb3.append("Unknown type encountered: ");
                sb3.append(valueOf3);
                throw new IllegalStateException(sb3.toString());
            }
        }
    }

    public static Map<String, Object> zzap(Map<String, Object> map) {
        Object zzcb = zzcb(map);
        zzepd.zzcw(zzcb instanceof Map);
        return (Map) zzcb;
    }

    private static <T> T zzb(Object obj, Class<T> cls) {
        if (obj == null) {
            return null;
        }
        if (cls.isPrimitive() || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls) || Character.class.isAssignableFrom(cls)) {
            if (Integer.class.isAssignableFrom(cls) || Integer.TYPE.isAssignableFrom(cls)) {
                if (obj instanceof Integer) {
                    return (T) obj;
                }
                if ((obj instanceof Long) || (obj instanceof Double)) {
                    Number number = (Number) obj;
                    double doubleValue = number.doubleValue();
                    if (doubleValue >= -2.147483648E9d && doubleValue <= 2.147483647E9d) {
                        return (T) Integer.valueOf(number.intValue());
                    }
                    StringBuilder sb = new StringBuilder(124);
                    sb.append("Numeric value out of 32-bit integer range: ");
                    sb.append(doubleValue);
                    sb.append(". Did you mean to use a long or double instead of an int?");
                    throw new DatabaseException(sb.toString());
                }
                String name = obj.getClass().getName();
                StringBuilder sb2 = new StringBuilder(String.valueOf(name).length() + 41);
                sb2.append("Failed to convert a value of type ");
                sb2.append(name);
                sb2.append(" to int");
                throw new DatabaseException(sb2.toString());
            } else if (Boolean.class.isAssignableFrom(cls) || Boolean.TYPE.isAssignableFrom(cls)) {
                if (obj instanceof Boolean) {
                    return (T) obj;
                }
                String name2 = obj.getClass().getName();
                StringBuilder sb3 = new StringBuilder(String.valueOf(name2).length() + 43);
                sb3.append("Failed to convert value of type ");
                sb3.append(name2);
                sb3.append(" to boolean");
                throw new DatabaseException(sb3.toString());
            } else if (Double.class.isAssignableFrom(cls) || Double.TYPE.isAssignableFrom(cls)) {
                return (T) zzcd(obj);
            } else {
                if (Long.class.isAssignableFrom(cls) || Long.TYPE.isAssignableFrom(cls)) {
                    if (obj instanceof Integer) {
                        return (T) Long.valueOf(((Integer) obj).longValue());
                    }
                    if (obj instanceof Long) {
                        return (T) obj;
                    }
                    if (obj instanceof Double) {
                        Double d = (Double) obj;
                        if (d.doubleValue() >= -9.223372036854776E18d && d.doubleValue() <= 9.223372036854776E18d) {
                            return (T) Long.valueOf(d.longValue());
                        }
                        String valueOf = String.valueOf(d);
                        StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf).length() + 89);
                        sb4.append("Numeric value out of 64-bit long range: ");
                        sb4.append(valueOf);
                        sb4.append(". Did you mean to use a double instead of a long?");
                        throw new DatabaseException(sb4.toString());
                    }
                    String name3 = obj.getClass().getName();
                    StringBuilder sb5 = new StringBuilder(String.valueOf(name3).length() + 42);
                    sb5.append("Failed to convert a value of type ");
                    sb5.append(name3);
                    sb5.append(" to long");
                    throw new DatabaseException(sb5.toString());
                } else if (Float.class.isAssignableFrom(cls) || Float.TYPE.isAssignableFrom(cls)) {
                    return (T) Float.valueOf(zzcd(obj).floatValue());
                } else {
                    if (Short.class.isAssignableFrom(cls) || Short.TYPE.isAssignableFrom(cls)) {
                        throw new DatabaseException("Deserializing to shorts is not supported");
                    } else if (Byte.class.isAssignableFrom(cls) || Byte.TYPE.isAssignableFrom(cls)) {
                        throw new DatabaseException("Deserializing to bytes is not supported");
                    } else if (Character.class.isAssignableFrom(cls) || Character.TYPE.isAssignableFrom(cls)) {
                        throw new DatabaseException("Deserializing to char is not supported");
                    } else {
                        String valueOf2 = String.valueOf(cls);
                        StringBuilder sb6 = new StringBuilder(String.valueOf(valueOf2).length() + 24);
                        sb6.append("Unknown primitive type: ");
                        sb6.append(valueOf2);
                        throw new IllegalArgumentException(sb6.toString());
                    }
                }
            }
        } else if (String.class.isAssignableFrom(cls)) {
            if (obj instanceof String) {
                return (T) obj;
            }
            String name4 = obj.getClass().getName();
            StringBuilder sb7 = new StringBuilder(String.valueOf(name4).length() + 42);
            sb7.append("Failed to convert value of type ");
            sb7.append(name4);
            sb7.append(" to String");
            throw new DatabaseException(sb7.toString());
        } else if (cls.isArray()) {
            throw new DatabaseException("Converting to Arrays is not supported, please use Listsinstead");
        } else if (cls.getTypeParameters().length > 0) {
            String name5 = cls.getName();
            StringBuilder sb8 = new StringBuilder(String.valueOf(name5).length() + 75);
            sb8.append("Class ");
            sb8.append(name5);
            sb8.append(" has generic type parameters, please use GenericTypeIndicator instead");
            throw new DatabaseException(sb8.toString());
        } else if (cls.equals(Object.class)) {
            return (T) obj;
        } else {
            if (cls.isEnum()) {
                return zzc(obj, cls);
            }
            zzeph<T> zzf = zzf(cls);
            if (obj instanceof Map) {
                return (T) zzf.zze(zzcc(obj), Collections.emptyMap());
            }
            String name6 = obj.getClass().getName();
            String name7 = cls.getName();
            StringBuilder sb9 = new StringBuilder(String.valueOf(name6).length() + 38 + String.valueOf(name7).length());
            sb9.append("Can't convert object of type ");
            sb9.append(name6);
            sb9.append(" to type ");
            sb9.append(name7);
            throw new DatabaseException(sb9.toString());
        }
    }

    private static <T> T zzc(Object obj, Class<T> cls) {
        if (obj instanceof String) {
            String str = (String) obj;
            try {
                return (T) Enum.valueOf((Class) cls, str);
            } catch (IllegalArgumentException unused) {
                String name = cls.getName();
                StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 42 + String.valueOf(str).length());
                sb.append("Could not find enum value of ");
                sb.append(name);
                sb.append(" for value \"");
                sb.append(str);
                sb.append("\"");
                throw new DatabaseException(sb.toString());
            }
        } else {
            String valueOf = String.valueOf(cls);
            String valueOf2 = String.valueOf(obj.getClass());
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 57 + String.valueOf(valueOf2).length());
            sb2.append("Expected a String while deserializing to enum ");
            sb2.append(valueOf);
            sb2.append(" but got a ");
            sb2.append(valueOf2);
            throw new DatabaseException(sb2.toString());
        }
    }

    public static Object zzca(Object obj) {
        return zzcb(obj);
    }

    /* access modifiers changed from: private */
    public static <T> Object zzcb(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Number) {
            if ((t instanceof Float) || (t instanceof Double)) {
                Number number = (Number) t;
                double doubleValue = number.doubleValue();
                return (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d || Math.floor(doubleValue) != doubleValue) ? Double.valueOf(doubleValue) : Long.valueOf(number.longValue());
            } else if (t instanceof Short) {
                throw new DatabaseException("Shorts are not supported, please use int or long");
            } else if (!(t instanceof Byte)) {
                return t;
            } else {
                throw new DatabaseException("Bytes are not supported, please use int or long");
            }
        } else if ((t instanceof String) || (t instanceof Boolean)) {
            return t;
        } else {
            if (t instanceof Character) {
                throw new DatabaseException("Characters are not supported, please use Strings");
            } else if (t instanceof Map) {
                HashMap hashMap = new HashMap();
                for (Map.Entry<?, ?> entry : ((Map<?, ?>) t).entrySet()) {
                    Object key = entry.getKey();
                    if (key instanceof String) {
                        hashMap.put((String) key, zzcb(entry.getValue()));
                    } else {
                        throw new DatabaseException("Maps with non-string keys are not supported");
                    }
                }
                return hashMap;
            } else if (t instanceof Collection) {
                if (t instanceof List) {
                    List<Object> list = (List) t;
                    ArrayList arrayList = new ArrayList(list.size());
                    for (Object zzcb : list) {
                        arrayList.add(zzcb(zzcb));
                    }
                    return arrayList;
                }
                throw new DatabaseException("Serializing Collections is not supported, please use Lists instead");
            } else if (!t.getClass().isArray()) {
                return t instanceof Enum ? ((Enum) t).name() : zzf((Class) t.getClass()).zzcf(t);
            } else {
                throw new DatabaseException("Serializing Arrays is not supported, please use Lists instead");
            }
        }
    }

    private static Map<String, Object> zzcc(Object obj) {
        if (obj instanceof Map) {
            return (Map) obj;
        }
        String valueOf = String.valueOf(obj.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46);
        sb.append("Expected a Map while deserializing, but got a ");
        sb.append(valueOf);
        throw new DatabaseException(sb.toString());
    }

    private static Double zzcd(Object obj) {
        if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        }
        if (obj instanceof Long) {
            Long l = (Long) obj;
            Double valueOf = Double.valueOf(l.doubleValue());
            if (valueOf.longValue() == l.longValue()) {
                return valueOf;
            }
            String valueOf2 = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 97);
            sb.append("Loss of precision while converting number to double: ");
            sb.append(valueOf2);
            sb.append(". Did you mean to use a 64-bit long instead?");
            throw new DatabaseException(sb.toString());
        } else if (obj instanceof Double) {
            return (Double) obj;
        } else {
            String name = obj.getClass().getName();
            StringBuilder sb2 = new StringBuilder(String.valueOf(name).length() + 44);
            sb2.append("Failed to convert a value of type ");
            sb2.append(name);
            sb2.append(" to double");
            throw new DatabaseException(sb2.toString());
        }
    }

    private static <T> zzeph<T> zzf(Class<T> cls) {
        ConcurrentMap<Class<?>, zzeph<?>> concurrentMap = zznrn;
        zzeph<T> zzeph = (zzeph) concurrentMap.get(cls);
        if (zzeph != null) {
            return zzeph;
        }
        zzeph<T> zzeph2 = new zzeph<>(cls);
        concurrentMap.put(cls, zzeph2);
        return zzeph2;
    }
}
