package com.kenai.jbosh;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ServiceLib {
    private static final Logger LOG = Logger.getLogger(ServiceLib.class.getName());

    private ServiceLib() {
    }

    static <T> T loadService(Class<T> cls) {
        for (String attemptLoad : loadServicesImplementations(cls)) {
            T attemptLoad2 = attemptLoad(cls, attemptLoad);
            if (attemptLoad2 != null) {
                Logger logger = LOG;
                if (logger.isLoggable(Level.FINEST)) {
                    logger.finest("Selected " + cls.getSimpleName() + " implementation: " + attemptLoad2.getClass().getName());
                }
                return attemptLoad2;
            }
        }
        throw new IllegalStateException("Could not load " + cls.getName() + " implementation");
    }

    private static List<String> loadServicesImplementations(Class cls) {
        ArrayList<String> arrayList = new ArrayList<>();
        String property = System.getProperty(cls.getName());
        if (property != null) {
            arrayList.add(property);
        }
        URL resource = ServiceLib.class.getClassLoader().getResource("META-INF/services/" + cls.getName());
        if (resource == null) {
            return arrayList;
        }
        try (InputStream inputStream = resource.openStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                if (!readLine.matches("\\s*(#.*)?")) {
                    arrayList.add(readLine.trim());
                }
            }
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Could not load services descriptor: " + resource.toString(), e);
        }
        return arrayList;
    }

    private static <T> T attemptLoad(Class<T> cls, String str) {
        Logger logger = LOG;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest("Attempting service load: " + str);
        }
        try {
            Class<?> cls2 = Class.forName(str);
            if (cls.isAssignableFrom(cls2)) {
                return cls.cast(cls2.newInstance());
            }
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(cls2.getName() + " is not assignable to " + cls.getName());
            }
            return null;
        } catch (LinkageError e) {
            LOG.log(Level.FINEST, "Could not load " + cls.getSimpleName() + " instance: " + str, e);
            return null;
        } catch (ClassNotFoundException e) {
            LOG.log(Level.FINEST, "Could not load " + cls.getSimpleName() + " instance: " + str, e);
            return null;
        } catch (InstantiationException e) {
            LOG.log(Level.WARNING, "Could not load " + cls.getSimpleName() + " instance: " + str, e);
            return null;
        } catch (IllegalAccessException e) {
            LOG.log(Level.WARNING, "Could not load " + cls.getSimpleName() + " instance: " + str, e);
            return null;
        }
    }

    private static void finalClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Logger logger = LOG;
                Level level = Level.FINEST;
                logger.log(level, "Could not close: " + closeable, e);
            }
        }
    }
}
