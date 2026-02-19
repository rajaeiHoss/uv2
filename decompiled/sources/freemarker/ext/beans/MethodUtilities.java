package freemarker.ext.beans;

import freemarker.template.utility.UndeclaredThrowableException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class MethodUtilities {
    private static final Method CONSTRUCTOR_IS_VARARGS;
    private static final Method METHOD_IS_VARARGS;
    static final Class OBJECT_CLASS;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$reflect$Constructor;
    static /* synthetic */ Class class$java$lang$reflect$Method;

    MethodUtilities() {
    }

    static {
        Class cls = class$java$lang$Object;
        if (cls == null) {
            cls = class$("java.lang.Object");
            class$java$lang$Object = cls;
        }
        OBJECT_CLASS = cls;
        Class cls2 = class$java$lang$reflect$Method;
        if (cls2 == null) {
            cls2 = class$("java.lang.reflect.Method");
            class$java$lang$reflect$Method = cls2;
        }
        METHOD_IS_VARARGS = getIsVarArgsMethod(cls2);
        Class cls3 = class$java$lang$reflect$Constructor;
        if (cls3 == null) {
            cls3 = class$("java.lang.reflect.Constructor");
            class$java$lang$reflect$Constructor = cls3;
        }
        CONSTRUCTOR_IS_VARARGS = getIsVarArgsMethod(cls3);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static Class getMostSpecificCommonType(Class cls, Class cls2) {
        if (cls == cls2) {
            return cls;
        }
        if (cls2.isPrimitive()) {
            if (cls2 == Byte.TYPE) {
                cls2 = class$java$lang$Byte;
                if (cls2 == null) {
                    cls2 = class$("java.lang.Byte");
                    class$java$lang$Byte = cls2;
                }
            } else if (cls2 == Short.TYPE) {
                cls2 = class$java$lang$Short;
                if (cls2 == null) {
                    cls2 = class$("java.lang.Short");
                    class$java$lang$Short = cls2;
                }
            } else if (cls2 == Character.TYPE) {
                cls2 = class$java$lang$Character;
                if (cls2 == null) {
                    cls2 = class$("java.lang.Character");
                    class$java$lang$Character = cls2;
                }
            } else if (cls2 == Integer.TYPE) {
                cls2 = class$java$lang$Integer;
                if (cls2 == null) {
                    cls2 = class$("java.lang.Integer");
                    class$java$lang$Integer = cls2;
                }
            } else if (cls2 == Float.TYPE) {
                cls2 = class$java$lang$Float;
                if (cls2 == null) {
                    cls2 = class$("java.lang.Float");
                    class$java$lang$Float = cls2;
                }
            } else if (cls2 == Long.TYPE) {
                cls2 = class$java$lang$Long;
                if (cls2 == null) {
                    cls2 = class$("java.lang.Long");
                    class$java$lang$Long = cls2;
                }
            } else if (cls2 == Double.TYPE && (cls2 = class$java$lang$Double) == null) {
                cls2 = class$("java.lang.Double");
                class$java$lang$Double = cls2;
            }
        }
        Set<Class> assignables = getAssignables(cls, cls2);
        assignables.retainAll(getAssignables(cls2, cls));
        if (assignables.isEmpty()) {
            Class cls3 = class$java$lang$Object;
            if (cls3 != null) {
                return cls3;
            }
            Class class$ = class$("java.lang.Object");
            class$java$lang$Object = class$;
            return class$;
        }
        ArrayList arrayList = new ArrayList();
        for (Class cls4 : assignables) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    arrayList.add(cls4);
                    break;
                }
                Class cls5 = (Class) it.next();
                if (isMoreSpecific(cls5, cls4)) {
                    break;
                } else if (isMoreSpecific(cls4, cls5)) {
                    it.remove();
                }
            }
        }
        if (arrayList.size() > 1) {
            return OBJECT_CLASS;
        }
        return (Class) arrayList.get(0);
    }

    static boolean isMoreSpecific(Class cls, Class cls2) {
        if (cls2.isAssignableFrom(cls)) {
            return true;
        }
        if (!cls2.isPrimitive()) {
            return false;
        }
        if (cls2 == Short.TYPE && cls == Byte.TYPE) {
            return true;
        }
        if (cls2 == Integer.TYPE && (cls == Short.TYPE || cls == Byte.TYPE)) {
            return true;
        }
        if (cls2 == Long.TYPE && (cls == Integer.TYPE || cls == Short.TYPE || cls == Byte.TYPE)) {
            return true;
        }
        if (cls2 == Float.TYPE && (cls == Long.TYPE || cls == Integer.TYPE || cls == Short.TYPE || cls == Byte.TYPE)) {
            return true;
        }
        if (cls2 != Double.TYPE) {
            return false;
        }
        if (cls == Float.TYPE || cls == Long.TYPE || cls == Integer.TYPE || cls == Short.TYPE || cls == Byte.TYPE) {
            return true;
        }
        return false;
    }

    private static Set getAssignables(Class cls, Class cls2) {
        HashSet hashSet = new HashSet();
        collectAssignables(cls, cls2, hashSet);
        return hashSet;
    }

    private static void collectAssignables(Class cls, Class cls2, Set set) {
        if (cls.isAssignableFrom(cls2)) {
            set.add(cls);
        }
        Class superclass = cls.getSuperclass();
        if (superclass != null) {
            collectAssignables(superclass, cls2, set);
        }
        Class[] interfaces = cls.getInterfaces();
        for (Class collectAssignables : interfaces) {
            collectAssignables(collectAssignables, cls2, set);
        }
    }

    static Class[] getParameterTypes(Member member) {
        if (member instanceof Method) {
            return ((Method) member).getParameterTypes();
        }
        if (member instanceof Constructor) {
            return ((Constructor) member).getParameterTypes();
        }
        throw new RuntimeException();
    }

    static boolean isVarArgs(Member member) {
        if (member instanceof Method) {
            return isVarArgs(member, METHOD_IS_VARARGS);
        }
        if (member instanceof Constructor) {
            return isVarArgs(member, CONSTRUCTOR_IS_VARARGS);
        }
        throw new RuntimeException();
    }

    private static boolean isVarArgs(Member member, Method method) {
        if (method == null) {
            return false;
        }
        try {
            return ((Boolean) method.invoke(member, (Object[]) null)).booleanValue();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new UndeclaredThrowableException(e2);
        }
    }

    private static Method getIsVarArgsMethod(Class cls) {
        try {
            return cls.getMethod("isVarArgs", (Class[]) null);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }
}
