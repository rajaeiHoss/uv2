package freemarker.debug.impl;

import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.utility.SecurityUtilities;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

public abstract class DebuggerService {
    private static final DebuggerService instance = createInstance();

    /* access modifiers changed from: package-private */
    public abstract List getBreakpointsSpi(String str);

    /* access modifiers changed from: package-private */
    public abstract void registerTemplateSpi(Template template);

    /* access modifiers changed from: package-private */
    public abstract void shutdownSpi();

    /* access modifiers changed from: package-private */
    public abstract boolean suspendEnvironmentSpi(Environment environment, String str, int i) throws RemoteException;

    private static DebuggerService createInstance() {
        return SecurityUtilities.getSystemProperty("freemarker.debug.password") == null ? new NoOpDebuggerService() : new RmiDebuggerService();
    }

    public static List getBreakpoints(String str) {
        return instance.getBreakpointsSpi(str);
    }

    public static void registerTemplate(Template template) {
        instance.registerTemplateSpi(template);
    }

    public static boolean suspendEnvironment(Environment environment, String str, int i) throws RemoteException {
        return instance.suspendEnvironmentSpi(environment, str, i);
    }

    public static void shutdown() {
        instance.shutdownSpi();
    }

    private static class NoOpDebuggerService extends DebuggerService {
        /* access modifiers changed from: package-private */
        public void registerTemplateSpi(Template template) {
        }

        /* access modifiers changed from: package-private */
        public void shutdownSpi() {
        }

        private NoOpDebuggerService() {
        }

        /* access modifiers changed from: package-private */
        public List getBreakpointsSpi(String str) {
            return Collections.EMPTY_LIST;
        }

        /* access modifiers changed from: package-private */
        public boolean suspendEnvironmentSpi(Environment environment, String str, int i) {
            throw new UnsupportedOperationException();
        }
    }
}
