package freemarker.core;

import freemarker.template.utility.ClassUtil;
import freemarker.template.utility.UndeclaredThrowableException;
import java.util.Collections;
import java.util.Map;

public class _ConcurrentMapFactory {
    private static final Class bestHashMapClass = getBestHashMapClass();
    static /* synthetic */ Class class$java$util$HashMap;
    private static final Class concurrentMapClass = getConcurrentMapClass();

    public static Map newMaybeConcurrentHashMap() {
        try {
            return (Map) bestHashMapClass.newInstance();
        } catch (Exception e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    public static Map newThreadSafeMap() {
        Map newMaybeConcurrentHashMap = newMaybeConcurrentHashMap();
        return isConcurrent(newMaybeConcurrentHashMap) ? newMaybeConcurrentHashMap : Collections.synchronizedMap(newMaybeConcurrentHashMap);
    }

    public static boolean concurrentMapsAvailable() {
        return concurrentMapClass != null;
    }

    public static boolean isConcurrent(Map map) {
        Class cls = concurrentMapClass;
        return cls != null && cls.isInstance(map);
    }

    private static Class getConcurrentMapClass() {
        try {
            return ClassUtil.forName("java.util.concurrent.ConcurrentMap");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    private static Class getBestHashMapClass() {
        try {
            return ClassUtil.forName("java.util.concurrent.ConcurrentHashMap");
        } catch (ClassNotFoundException unused) {
            Class cls = class$java$util$HashMap;
            if (cls != null) {
                return cls;
            }
            Class class$ = class$("java.util.HashMap");
            class$java$util$HashMap = class$;
            return class$;
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }
}
