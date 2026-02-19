package freemarker.debug.impl;

import freemarker.debug.DebuggerListener;
import freemarker.debug.EnvironmentSuspendedEvent;
import freemarker.log.Logger;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;

public class RmiDebuggerListenerImpl extends UnicastRemoteObject implements DebuggerListener, Unreferenced {
    private static final Logger logger = Logger.getLogger("freemarker.debug.client");
    private static final long serialVersionUID = 1;
    private final DebuggerListener listener;

    public void unreferenced() {
        try {
            UnicastRemoteObject.unexportObject(this, false);
        } catch (NoSuchObjectException e) {
            logger.warn("Failed to unexport RMI debugger listener", e);
        }
    }

    public RmiDebuggerListenerImpl(DebuggerListener debuggerListener) throws RemoteException {
        this.listener = debuggerListener;
    }

    public void environmentSuspended(EnvironmentSuspendedEvent environmentSuspendedEvent) throws RemoteException {
        this.listener.environmentSuspended(environmentSuspendedEvent);
    }
}
