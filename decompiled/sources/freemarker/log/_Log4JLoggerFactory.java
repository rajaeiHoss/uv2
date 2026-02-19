package freemarker.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class _Log4JLoggerFactory implements LoggerFactory {
    public Logger getLogger(String str) {
        return new Log4JLogger(Logger.getLogger(str));
    }

    private static class Log4JLogger extends Logger {
        private final Logger logger;

        Log4JLogger(Logger logger2) {
            this.logger = logger2;
        }

        public void debug(String str) {
            this.logger.debug(str);
        }

        public void debug(String str, Throwable th) {
            this.logger.debug(str, th);
        }

        public void error(String str) {
            this.logger.error(str);
        }

        public void error(String str, Throwable th) {
            this.logger.error(str, th);
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

        public boolean isDebugEnabled() {
            return this.logger.isDebugEnabled();
        }

        public boolean isInfoEnabled() {
            return this.logger.isInfoEnabled();
        }

        public boolean isWarnEnabled() {
            return this.logger.isEnabledFor(Level.WARN);
        }

        public boolean isErrorEnabled() {
            return this.logger.isEnabledFor(Level.ERROR);
        }

        public boolean isFatalEnabled() {
            return this.logger.isEnabledFor(Level.FATAL);
        }
    }
}
