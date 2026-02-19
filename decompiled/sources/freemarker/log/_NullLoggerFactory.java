package freemarker.log;

public class _NullLoggerFactory implements LoggerFactory {
    private static final Logger INSTANCE = new Logger() {
        public void debug(String str) {
        }

        public void debug(String str, Throwable th) {
        }

        public void error(String str) {
        }

        public void error(String str, Throwable th) {
        }

        public void info(String str) {
        }

        public void info(String str, Throwable th) {
        }

        public boolean isDebugEnabled() {
            return false;
        }

        public boolean isErrorEnabled() {
            return false;
        }

        public boolean isFatalEnabled() {
            return false;
        }

        public boolean isInfoEnabled() {
            return false;
        }

        public boolean isWarnEnabled() {
            return false;
        }

        public void warn(String str) {
        }

        public void warn(String str, Throwable th) {
        }
    };

    _NullLoggerFactory() {
    }

    public Logger getLogger(String str) {
        return INSTANCE;
    }
}
