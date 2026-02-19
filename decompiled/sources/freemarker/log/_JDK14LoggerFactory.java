package freemarker.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class _JDK14LoggerFactory implements LoggerFactory {
    public Logger getLogger(String str) {
        return new JDK14Logger(Logger.getLogger(str));
    }

    private static class JDK14Logger extends Logger {
        private final Logger logger;

        JDK14Logger(Logger logger2) {
            this.logger = logger2;
        }

        public void debug(String str) {
            this.logger.log(Level.FINE, str);
        }

        public void debug(String str, Throwable th) {
            this.logger.log(Level.FINE, str, th);
        }

        public void error(String str) {
            this.logger.log(Level.SEVERE, str);
        }

        public void error(String str, Throwable th) {
            this.logger.log(Level.SEVERE, str, th);
        }

        public void info(String str) {
            this.logger.log(Level.INFO, str);
        }

        public void info(String str, Throwable th) {
            this.logger.log(Level.INFO, str, th);
        }

        public void warn(String str) {
            this.logger.log(Level.WARNING, str);
        }

        public void warn(String str, Throwable th) {
            this.logger.log(Level.WARNING, str, th);
        }

        public boolean isDebugEnabled() {
            return this.logger.isLoggable(Level.FINE);
        }

        public boolean isInfoEnabled() {
            return this.logger.isLoggable(Level.INFO);
        }

        public boolean isWarnEnabled() {
            return this.logger.isLoggable(Level.WARNING);
        }

        public boolean isErrorEnabled() {
            return this.logger.isLoggable(Level.SEVERE);
        }

        public boolean isFatalEnabled() {
            return this.logger.isLoggable(Level.SEVERE);
        }
    }
}
