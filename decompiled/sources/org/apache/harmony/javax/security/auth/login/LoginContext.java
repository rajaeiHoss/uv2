package org.apache.harmony.javax.security.auth.login;

import com.google.android.gms.fitness.FitnessActivities;
import java.io.IOException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import org.apache.harmony.javax.security.auth.AuthPermission;
import org.apache.harmony.javax.security.auth.Subject;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.harmony.javax.security.auth.login.AppConfigurationEntry;
import org.apache.harmony.javax.security.auth.spi.LoginModule;

public class LoginContext {
    private static final String DEFAULT_CALLBACK_HANDLER_PROPERTY = "auth.login.defaultCallbackHandler";
    private static final int OPTIONAL = 0;
    private static final int REQUIRED = 1;
    private static final int REQUISITE = 2;
    private static final int SUFFICIENT = 3;
    /* access modifiers changed from: private */
    public CallbackHandler callbackHandler;
    /* access modifiers changed from: private */
    public ClassLoader contextClassLoader;
    private boolean loggedIn;
    private Module[] modules;
    private Map<String, ?> sharedState;
    private Subject subject;
    /* access modifiers changed from: private */
    public AccessControlContext userContext;
    private boolean userProvidedConfig;
    private boolean userProvidedSubject;

    public LoginContext(String str) throws LoginException {
        init(str, (Subject) null, (CallbackHandler) null, (Configuration) null);
    }

    public LoginContext(String str, CallbackHandler callbackHandler2) throws LoginException {
        if (callbackHandler2 != null) {
            init(str, (Subject) null, callbackHandler2, (Configuration) null);
            return;
        }
        throw new LoginException("auth.34");
    }

    public LoginContext(String str, Subject subject2) throws LoginException {
        if (subject2 != null) {
            init(str, subject2, (CallbackHandler) null, (Configuration) null);
            return;
        }
        throw new LoginException("auth.03");
    }

    public LoginContext(String str, Subject subject2, CallbackHandler callbackHandler2) throws LoginException {
        if (subject2 == null) {
            throw new LoginException("auth.03");
        } else if (callbackHandler2 != null) {
            init(str, subject2, callbackHandler2, (Configuration) null);
        } else {
            throw new LoginException("auth.34");
        }
    }

    public LoginContext(String str, Subject subject2, CallbackHandler callbackHandler2, Configuration configuration) throws LoginException {
        init(str, subject2, callbackHandler2, configuration);
    }

    private void init(String str, Subject subject2, final CallbackHandler callbackHandler2, Configuration configuration) throws LoginException {
        this.subject = subject2;
        int i = 0;
        this.userProvidedSubject = subject2 != null;
        if (str != null) {
            if (configuration == null) {
                configuration = Configuration.getAccessibleConfiguration();
            } else {
                this.userProvidedConfig = true;
            }
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null && !this.userProvidedConfig) {
                securityManager.checkPermission(new AuthPermission("createLoginContext." + str));
            }
            AppConfigurationEntry[] appConfigurationEntry = configuration.getAppConfigurationEntry(str);
            if (appConfigurationEntry == null) {
                if (securityManager != null && !this.userProvidedConfig) {
                    securityManager.checkPermission(new AuthPermission("createLoginContext.other"));
                }
                appConfigurationEntry = configuration.getAppConfigurationEntry(FitnessActivities.OTHER);
                if (appConfigurationEntry == null) {
                    throw new LoginException("auth.35 " + str);
                }
            }
            this.modules = new Module[appConfigurationEntry.length];
            while (true) {
                Module[] moduleArr = this.modules;
                if (i < moduleArr.length) {
                    moduleArr[i] = new Module(appConfigurationEntry[i]);
                    i++;
                } else {
                    break;
                }
            }
            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                    public Void run() throws Exception {
                        ClassLoader unused = LoginContext.this.contextClassLoader = Thread.currentThread().getContextClassLoader();
                        if (LoginContext.this.contextClassLoader == null) {
                            ClassLoader unused2 = LoginContext.this.contextClassLoader = ClassLoader.getSystemClassLoader();
                        }
                        CallbackHandler callbackHandler = callbackHandler2;
                        if (callbackHandler == null) {
                            String property = Security.getProperty(LoginContext.DEFAULT_CALLBACK_HANDLER_PROPERTY);
                            if (property == null || property.length() == 0) {
                                return null;
                            }
                            CallbackHandler unused3 = LoginContext.this.callbackHandler = (CallbackHandler) Class.forName(property, true, LoginContext.this.contextClassLoader).newInstance();
                        } else {
                            CallbackHandler unused4 = LoginContext.this.callbackHandler = callbackHandler;
                        }
                        return null;
                    }
                });
            } catch (PrivilegedActionException e) {
                throw ((LoginException) new LoginException("auth.36").initCause(e.getCause()));
            }
            if (this.userProvidedConfig) {
                this.userContext = AccessController.getContext();
            } else if (this.callbackHandler != null) {
                this.userContext = AccessController.getContext();
                this.callbackHandler = new ContextedCallbackHandler(this.callbackHandler);
            }
        } else {
            throw new LoginException("auth.00");
        }
    }

    public Subject getSubject() {
        if (this.userProvidedSubject || this.loggedIn) {
            return this.subject;
        }
        return null;
    }

    public void login() throws LoginException {
        PrivilegedExceptionAction<Void> action = new PrivilegedExceptionAction<Void>() {
            public Void run() throws LoginException {
                LoginContext.this.loginImpl();
                return null;
            }
        };
        try {
            if (this.userProvidedConfig) {
                AccessController.doPrivileged(action, this.userContext);
            } else {
                AccessController.doPrivileged(action);
            }
        } catch (PrivilegedActionException e) {
            throw ((LoginException) e.getException());
        }
    }

    /* access modifiers changed from: private */
    public void loginImpl() throws LoginException {
        if (this.subject == null) {
            this.subject = new Subject();
        }
        if (this.sharedState == null) {
            this.sharedState = new HashMap();
        }
        Throwable th = null;
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        Module[] moduleArr = this.modules;
        int length = moduleArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Module module = moduleArr[i];
            try {
                module.create(this.subject, this.callbackHandler, this.sharedState);
                if (module.module.login()) {
                    int flag = module.getFlag();
                    iArr2[flag] = iArr2[flag] + 1;
                    int flag2 = module.getFlag();
                    iArr[flag2] = iArr[flag2] + 1;
                    if (module.getFlag() == 3) {
                        break;
                    }
                } else {
                    continue;
                }
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                }
                if (module.klass == null) {
                    iArr2[1] = iArr2[1] + 1;
                    break;
                }
                int flag3 = module.getFlag();
                iArr2[flag3] = iArr2[flag3] + 1;
                if (module.getFlag() == 2) {
                    break;
                }
            }
            i++;
        }
        boolean z = (iArr[1] == iArr2[1] && iArr[2] == iArr2[2] && (iArr2[1] != 0 || iArr2[2] != 0 || iArr[0] != 0 || iArr[3] != 0)) ? false : true;
        int[] iArr3 = new int[4];
        iArr2[3] = 0;
        iArr2[2] = 0;
        iArr2[1] = 0;
        iArr2[0] = 0;
        if (!z) {
            for (Module module2 : this.modules) {
                if (module2.klass != null) {
                    int flag4 = module2.getFlag();
                    iArr2[flag4] = iArr2[flag4] + 1;
                    try {
                        module2.module.commit();
                        int flag5 = module2.getFlag();
                        iArr3[flag5] = iArr3[flag5] + 1;
                    } catch (Throwable th3) {
                        if (th == null) {
                            th = th3;
                        }
                    }
                }
            }
        }
        if ((iArr3[1] == iArr2[1] && iArr3[2] == iArr2[2] && (iArr2[1] != 0 || iArr2[2] != 0 || iArr3[0] != 0 || iArr3[3] != 0)) ? false : true) {
            for (Module module3 : this.modules) {
                try {
                    module3.module.abort();
                } catch (Throwable th4) {
                    if (th == null) {
                        th = th4;
                    }
                }
            }
            if ((th instanceof PrivilegedActionException) && th.getCause() != null) {
                th = th.getCause();
            }
            if (th instanceof LoginException) {
                throw ((LoginException) th);
            }
            throw ((LoginException) new LoginException("auth.37").initCause(th));
        }
        this.loggedIn = true;
    }

    public void logout() throws LoginException {
        PrivilegedExceptionAction<Void> action = new PrivilegedExceptionAction<Void>() {
            public Void run() throws LoginException {
                LoginContext.this.logoutImpl();
                return null;
            }
        };
        try {
            if (this.userProvidedConfig) {
                AccessController.doPrivileged(action, this.userContext);
            } else {
                AccessController.doPrivileged(action);
            }
        } catch (PrivilegedActionException e) {
            throw ((LoginException) e.getException());
        }
    }

    /* access modifiers changed from: private */
    public void logoutImpl() throws LoginException {
        if (this.subject != null) {
            this.loggedIn = false;
            Throwable th = null;
            int i = 0;
            for (Module module : this.modules) {
                try {
                    module.module.logout();
                    i++;
                } catch (Throwable th2) {
                    if (th == null) {
                        th = th2;
                    }
                }
            }
            if (th != null || i == 0) {
                if ((th instanceof PrivilegedActionException) && th.getCause() != null) {
                    th = th.getCause();
                }
                if (th instanceof LoginException) {
                    throw ((LoginException) th);
                }
                throw ((LoginException) new LoginException("auth.37").initCause(th));
            }
            return;
        }
        throw new LoginException("auth.38");
    }

    private class ContextedCallbackHandler implements CallbackHandler {
        /* access modifiers changed from: private */
        public final CallbackHandler hiddenHandlerRef;

        ContextedCallbackHandler(CallbackHandler callbackHandler) {
            this.hiddenHandlerRef = callbackHandler;
        }

        public void handle(final Callback[] callbackArr) throws IOException, UnsupportedCallbackException {
            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                    public Void run() throws IOException, UnsupportedCallbackException {
                        ContextedCallbackHandler.this.hiddenHandlerRef.handle(callbackArr);
                        return null;
                    }
                }, LoginContext.this.userContext);
            } catch (PrivilegedActionException e) {
                if (e.getCause() instanceof UnsupportedCallbackException) {
                    throw ((UnsupportedCallbackException) e.getCause());
                }
                throw ((IOException) e.getCause());
            }
        }
    }

    private final class Module {
        AppConfigurationEntry entry;
        int flag;
        Class<?> klass;
        LoginModule module;

        Module(AppConfigurationEntry appConfigurationEntry) {
            this.entry = appConfigurationEntry;
            AppConfigurationEntry.LoginModuleControlFlag controlFlag = appConfigurationEntry.getControlFlag();
            if (controlFlag == AppConfigurationEntry.LoginModuleControlFlag.OPTIONAL) {
                this.flag = 0;
            } else if (controlFlag == AppConfigurationEntry.LoginModuleControlFlag.REQUISITE) {
                this.flag = 2;
            } else if (controlFlag == AppConfigurationEntry.LoginModuleControlFlag.SUFFICIENT) {
                this.flag = 3;
            } else {
                this.flag = 1;
            }
        }

        /* access modifiers changed from: package-private */
        public int getFlag() {
            return this.flag;
        }

        /* access modifiers changed from: package-private */
        public void create(Subject subject, CallbackHandler callbackHandler, Map<String, ?> map) throws LoginException {
            String loginModuleName = this.entry.getLoginModuleName();
            if (this.klass == null) {
                try {
                    this.klass = Class.forName(loginModuleName, false, LoginContext.this.contextClassLoader);
                } catch (ClassNotFoundException e) {
                    throw ((LoginException) new LoginException("auth.39 " + loginModuleName).initCause(e));
                }
            }
            if (this.module == null) {
                try {
                    LoginModule loginModule = (LoginModule) this.klass.newInstance();
                    this.module = loginModule;
                    loginModule.initialize(subject, callbackHandler, map, this.entry.getOptions());
                } catch (IllegalAccessException e2) {
                    throw ((LoginException) new LoginException("auth.3A " + loginModuleName).initCause(e2));
                } catch (InstantiationException e3) {
                    throw ((LoginException) new LoginException("auth.3A" + loginModuleName).initCause(e3));
                }
            }
        }
    }
}
