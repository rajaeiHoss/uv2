package freemarker.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonsLoggingLoggerFactory implements LoggerFactory {
    public Logger getLogger(String str) {
        return new CommonsLoggingLogger(LogFactory.getLog(str));
    }

    private static class CommonsLoggingLogger extends Logger {
        private final Log logger;

        CommonsLoggingLogger(Log log) {
            this.logger = log;
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
            return this.logger.isFatalEnabled();
        }
    }
}
