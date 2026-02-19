package freemarker.debug.impl;

import freemarker.debug.DebuggerListener;
import freemarker.debug.EnvironmentSuspendedEvent;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteStub;

public final class RmiDebuggerListenerImpl_Stub extends RemoteStub implements DebuggerListener, Remote {
    private static Method $method_environmentSuspended_0 = null;
    static /* synthetic */ Class class$freemarker$debug$DebuggerListener = null;
    static /* synthetic */ Class class$freemarker$debug$EnvironmentSuspendedEvent = null;
    private static final long serialVersionUID = 2;

    static {
        try {
            Class cls = class$freemarker$debug$DebuggerListener;
            if (cls == null) {
                cls = class$("freemarker.debug.DebuggerListener");
                class$freemarker$debug$DebuggerListener = cls;
            }
            Class[] clsArr = new Class[1];
            Class cls2 = class$freemarker$debug$EnvironmentSuspendedEvent;
            if (cls2 == null) {
                cls2 = class$("freemarker.debug.EnvironmentSuspendedEvent");
                class$freemarker$debug$EnvironmentSuspendedEvent = cls2;
            }
            clsArr[0] = cls2;
            $method_environmentSuspended_0 = cls.getMethod("environmentSuspended", clsArr);
        } catch (NoSuchMethodException unused) {
            throw new NoSuchMethodError("stub class initialization failed");
        }
    }

    public RmiDebuggerListenerImpl_Stub(RemoteRef remoteRef) {
        super(remoteRef);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public void environmentSuspended(EnvironmentSuspendedEvent environmentSuspendedEvent) throws RemoteException {
        try {
            this.ref.invoke(this, $method_environmentSuspended_0, new Object[]{environmentSuspendedEvent}, -2541155567719209082L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException("undeclared checked exception", e3);
        }
    }
}
