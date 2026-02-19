package freemarker.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;

public class SLF4JLoggerFactory implements LoggerFactory {
    static /* synthetic */ Class class$freemarker$log$SLF4JLoggerFactory$LocationAwareSLF4JLogger;

    public Logger getLogger(String str) {
        LocationAwareLogger logger = LoggerFactory.getLogger(str);
        if (logger instanceof LocationAwareLogger) {
            return new LocationAwareSLF4JLogger(logger);
        }
        return new LocationUnawareSLF4JLogger(logger);
    }

    private static final class LocationAwareSLF4JLogger extends Logger {
        private static final String ADAPTER_FQCN;
        private final LocationAwareLogger logger;

        static {
            Class cls;
            if (SLF4JLoggerFactory.class$freemarker$log$SLF4JLoggerFactory$LocationAwareSLF4JLogger == null) {
                cls = SLF4JLoggerFactory.class$("freemarker.log.SLF4JLoggerFactory$LocationAwareSLF4JLogger");
                SLF4JLoggerFactory.class$freemarker$log$SLF4JLoggerFactory$LocationAwareSLF4JLogger = cls;
            } else {
                cls = SLF4JLoggerFactory.class$freemarker$log$SLF4JLoggerFactory$LocationAwareSLF4JLogger;
            }
            ADAPTER_FQCN = cls.getName();
        }

        LocationAwareSLF4JLogger(LocationAwareLogger locationAwareLogger) {
            this.logger = locationAwareLogger;
        }

        public void debug(String str) {
            debug(str, (Throwable) null);
        }

        public void debug(String str, Throwable th) {
            this.logger.log((Marker) null, ADAPTER_FQCN, 10, str, (Object[]) null, th);
        }

        public void info(String str) {
            info(str, (Throwable) null);
        }

        public void info(String str, Throwable th) {
            this.logger.log((Marker) null, ADAPTER_FQCN, 20, str, (Object[]) null, th);
        }

        public void warn(String str) {
            warn(str, (Throwable) null);
        }

        public void warn(String str, Throwable th) {
            this.logger.log((Marker) null, ADAPTER_FQCN, 30, str, (Object[]) null, th);
        }

        public void error(String str) {
            error(str, (Throwable) null);
        }

        public void error(String str, Throwable th) {
            this.logger.log((Marker) null, ADAPTER_FQCN, 40, str, (Object[]) null, th);
        }

        public boolean isDebugEnabled() {
            return this.logger.isDebugEnabled();
        }

        public boolean isInfoEnabled() {
            return this.logger.isInfoEnabled();
        }

        public boolean isWarnEnabled() {
            return this.logger.isWarnEnabled();
        }

        public boolean isErrorEnabled() {
            return this.logger.isErrorEnabled();
        }

        public boolean isFatalEnabled() {
            return this.logger.isErrorEnabled();
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static class LocationUnawareSLF4JLogger extends Logger {
        private final Logger logger;

        LocationUnawareSLF4JLogger(Logger logger2) {
            this.logger = logger2;
        }

        public void debug(String str) {
            this.logger.debug(str);
        }

        public void debug(String str, Throwable th) {
            this.logger.debug(str, th);
        }

        public void info(String str) {
            this.logger.info(str);
        }

        public void info(String str, Throwable th) {
            this.logger.info(str, th);
        }

        public void warn(String str) {
            this.logger.warn(str);
        }

        public void warn(String str, Throwable th) {
            this.logger.warn(str, th);
        }

        public void error(String str) {
            this.logger.error(str);
        }

        public void error(String str, Throwable th) {
            this.logger.error(str, th);
        }

        public boolean isDebugEnabled() {
            return this.logger.isDebugEnabled();
        }

        public boolean isInfoEnabled() {
            return this.logger.isInfoEnabled();
        }

        public boolean isWarnEnabled() {
            return this.logger.isWarnEnabled();
        }

        public boolean isErrorEnabled() {
            return this.logger.isErrorEnabled();
        }

        public boolean isFatalEnabled() {
            return this.logger.isErrorEnabled();
        }
    }
}
