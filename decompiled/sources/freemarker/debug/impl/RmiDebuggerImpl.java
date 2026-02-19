package freemarker.debug.impl;

import freemarker.debug.Breakpoint;
import freemarker.debug.Debugger;
import freemarker.debug.DebuggerListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.List;

class RmiDebuggerImpl extends UnicastRemoteObject implements Debugger {
    private static final long serialVersionUID = 1;
    private final RmiDebuggerService service;

    protected RmiDebuggerImpl(RmiDebuggerService rmiDebuggerService) throws RemoteException {
        this.service = rmiDebuggerService;
    }

    public void addBreakpoint(Breakpoint breakpoint) {
        this.service.addBreakpoint(breakpoint);
    }

    public Object addDebuggerListener(DebuggerListener debuggerListener) {
        return this.service.addDebuggerListener(debuggerListener);
    }

    public List getBreakpoints() {
        return this.service.getBreakpointsSpi();
    }

    public List getBreakpoints(String str) {
        return this.service.getBreakpointsSpi(str);
    }

    public Collection getSuspendedEnvironments() {
        return this.service.getSuspendedEnvironments();
    }

    public void removeBreakpoint(Breakpoint breakpoint) {
        this.service.removeBreakpoint(breakpoint);
    }

    public void removeDebuggerListener(Object obj) {
        this.service.removeDebuggerListener(obj);
    }

    public void removeBreakpoints() {
        this.service.removeBreakpoints();
    }

    public void removeBreakpoints(String str) {
        this.service.removeBreakpoints(str);
    }
}
