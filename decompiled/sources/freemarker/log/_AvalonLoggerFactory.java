package freemarker.log;

import org.apache.log.Hierarchy;
import org.apache.log.Logger;

public class _AvalonLoggerFactory implements LoggerFactory {
    public Logger getLogger(String str) {
        return new AvalonLogger(Hierarchy.getDefaultHierarchy().getLoggerFor(str));
    }

    private static class AvalonLogger extends Logger {
        private final Logger logger;

        AvalonLogger(Logger logger2) {
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
            return this.logger.isWarnEnabled();
        }

        public boolean isErrorEnabled() {
            return this.logger.isErrorEnabled();
        }

        public boolean isFatalEnabled() {
            return this.logger.isFatalErrorEnabled();
        }
    }
}
