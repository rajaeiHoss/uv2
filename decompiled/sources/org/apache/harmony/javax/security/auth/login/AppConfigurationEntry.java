package org.apache.harmony.javax.security.auth.login;

import java.util.Collections;
import java.util.Map;

public class AppConfigurationEntry {
    private final LoginModuleControlFlag controlFlag;
    private final String loginModuleName;
    private final Map<String, ?> options;

    public AppConfigurationEntry(String str, LoginModuleControlFlag loginModuleControlFlag, Map<String, ?> map) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("auth.26");
        } else if (loginModuleControlFlag == null) {
            throw new IllegalArgumentException("auth.27");
        } else if (map != null) {
            this.loginModuleName = str;
            this.controlFlag = loginModuleControlFlag;
            this.options = Collections.unmodifiableMap(map);
        } else {
            throw new IllegalArgumentException("auth.1A");
        }
    }

    public String getLoginModuleName() {
        return this.loginModuleName;
    }

    public LoginModuleControlFlag getControlFlag() {
        return this.controlFlag;
    }

    public Map<String, ?> getOptions() {
        return this.options;
    }

    public static class LoginModuleControlFlag {
        public static final LoginModuleControlFlag OPTIONAL = new LoginModuleControlFlag("LoginModuleControlFlag: optional");
        public static final LoginModuleControlFlag REQUIRED = new LoginModuleControlFlag("LoginModuleControlFlag: required");
        public static final LoginModuleControlFlag REQUISITE = new LoginModuleControlFlag("LoginModuleControlFlag: requisite");
        public static final LoginModuleControlFlag SUFFICIENT = new LoginModuleControlFlag("LoginModuleControlFlag: sufficient");
        private final String flag;

        private LoginModuleControlFlag(String str) {
            this.flag = str;
        }

        public String toString() {
            return this.flag;
        }
    }
}
