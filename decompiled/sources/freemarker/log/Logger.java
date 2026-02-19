package freemarker.log;

import freemarker.template.utility.ClassUtil;
import java.util.HashMap;
import java.util.Map;

public abstract class Logger {
    private static final String[] LIBINIT = {"freemarker.log.Logger", "_Null", "java.util.logging.Logger", "_JDK14", "org.apache.log.Logger", "_Avalon", "org.apache.log4j.Logger", "_Log4J", "org.apache.commons.logging.Log", "CommonsLogging", "org.slf4j.Logger", "SLF4J"};
    public static final int LIBRARY_AUTO = -1;
    public static final int LIBRARY_AVALON = 2;
    public static final int LIBRARY_COMMONS = 4;
    public static final int LIBRARY_JAVA = 1;
    public static final int LIBRARY_LOG4J = 3;
    public static final int LIBRARY_NONE = 0;
    public static final int LIBRARY_SLF4J = 5;
    private static String categoryPrefix = "";
    static /* synthetic */ Class class$freemarker$log$Logger;
    private static LoggerFactory factory;
    private static int logLibrary;
    private static final Map loggers = new HashMap();

    public abstract void debug(String str);

    public abstract void debug(String str, Throwable th);

    public abstract void error(String str);

    public abstract void error(String str, Throwable th);

    public abstract void info(String str);

    public abstract void info(String str, Throwable th);

    public abstract boolean isDebugEnabled();

    public abstract boolean isErrorEnabled();

    public abstract boolean isFatalEnabled();

    public abstract boolean isInfoEnabled();

    public abstract boolean isWarnEnabled();

    public abstract void warn(String str);

    public abstract void warn(String str, Throwable th);

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static void selectLoggerLibrary(int i) throws ClassNotFoundException {
        Class cls = class$freemarker$log$Logger;
        if (cls == null) {
            cls = class$("freemarker.log.Logger");
            class$freemarker$log$Logger = cls;
        }
        synchronized (cls) {
            if (i >= -1) {
                if (i * 2 < LIBINIT.length) {
                    logLibrary = i;
                    factory = createFactory();
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public static void setCategoryPrefix(String str) {
        Class cls = class$freemarker$log$Logger;
        if (cls == null) {
            cls = class$("freemarker.log.Logger");
            class$freemarker$log$Logger = cls;
        }
        synchronized (cls) {
            if (str != null) {
                try {
                    categoryPrefix = str;
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public static Logger getLogger(String str) {
        Logger logger;
        if (factory == null) {
            Class cls = class$freemarker$log$Logger;
            if (cls == null) {
                cls = class$("freemarker.log.Logger");
                class$freemarker$log$Logger = cls;
            }
            synchronized (cls) {
                if (factory == null) {
                    try {
                        selectLoggerLibrary(-1);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(categoryPrefix);
        stringBuffer.append(str);
        String stringBuffer2 = stringBuffer.toString();
        Map map = loggers;
        synchronized (map) {
            logger = (Logger) map.get(stringBuffer2);
            if (logger == null) {
                logger = factory.getLogger(stringBuffer2);
                map.put(stringBuffer2, logger);
            }
        }
        return logger;
    }

    private static LoggerFactory createFactory() throws ClassNotFoundException {
        int i = logLibrary;
        if (i != -1) {
            return createFactory(i);
        }
        for (int length = (LIBINIT.length / 2) - 1; length > 0; length--) {
            if (!(length == 5 || length == 4)) {
                try {
                    return createFactory(length);
                } catch (ClassNotFoundException unused) {
                }
            }
        }
        System.err.println("*** WARNING: FreeMarker logging suppressed.");
        return new _NullLoggerFactory();
    }

    private static LoggerFactory createFactory(int i) throws ClassNotFoundException {
        String[] strArr = LIBINIT;
        int i2 = i * 2;
        String str = strArr[i2];
        String str2 = strArr[i2 + 1];
        try {
            ClassUtil.forName(str);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("freemarker.log.");
            stringBuffer.append(str2);
            stringBuffer.append("LoggerFactory");
            return (LoggerFactory) Class.forName(stringBuffer.toString()).newInstance();
        } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
        } catch (InstantiationException e2) {
            throw new InstantiationError(e2.getMessage());
        }
    }
}
