package freemarker.debug.impl;

import freemarker.debug.Breakpoint;
import freemarker.debug.Debugger;
import freemarker.debug.DebuggerListener;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteStub;
import java.util.Collection;
import java.util.List;

public final class RmiDebuggerImpl_Stub extends RemoteStub implements Debugger, Remote {
    private static Method $method_addBreakpoint_0 = null;
    private static Method $method_addDebuggerListener_1 = null;
    private static Method $method_getBreakpoints_2 = null;
    private static Method $method_getBreakpoints_3 = null;
    private static Method $method_getSuspendedEnvironments_4 = null;
    private static Method $method_removeBreakpoint_5 = null;
    private static Method $method_removeBreakpoints_6 = null;
    private static Method $method_removeBreakpoints_7 = null;
    private static Method $method_removeDebuggerListener_8 = null;
    static /* synthetic */ Class class$freemarker$debug$Breakpoint = null;
    static /* synthetic */ Class class$freemarker$debug$Debugger = null;
    static /* synthetic */ Class class$freemarker$debug$DebuggerListener = null;
    static /* synthetic */ Class class$java$lang$Object = null;
    static /* synthetic */ Class class$java$lang$String = null;
    private static final long serialVersionUID = 2;

    static {
        try {
            Class cls = class$freemarker$debug$Debugger;
            if (cls == null) {
                cls = class$("freemarker.debug.Debugger");
                class$freemarker$debug$Debugger = cls;
            }
            Class[] clsArr = new Class[1];
            Class cls2 = class$freemarker$debug$Breakpoint;
            if (cls2 == null) {
                cls2 = class$("freemarker.debug.Breakpoint");
                class$freemarker$debug$Breakpoint = cls2;
            }
            clsArr[0] = cls2;
            $method_addBreakpoint_0 = cls.getMethod("addBreakpoint", clsArr);
            Class cls3 = class$freemarker$debug$Debugger;
            if (cls3 == null) {
                cls3 = class$("freemarker.debug.Debugger");
                class$freemarker$debug$Debugger = cls3;
            }
            Class[] clsArr2 = new Class[1];
            Class cls4 = class$freemarker$debug$DebuggerListener;
            if (cls4 == null) {
                cls4 = class$("freemarker.debug.DebuggerListener");
                class$freemarker$debug$DebuggerListener = cls4;
            }
            clsArr2[0] = cls4;
            $method_addDebuggerListener_1 = cls3.getMethod("addDebuggerListener", clsArr2);
            Class cls5 = class$freemarker$debug$Debugger;
            if (cls5 == null) {
                cls5 = class$("freemarker.debug.Debugger");
                class$freemarker$debug$Debugger = cls5;
            }
            $method_getBreakpoints_2 = cls5.getMethod("getBreakpoints", new Class[0]);
            Class cls6 = class$freemarker$debug$Debugger;
            if (cls6 == null) {
                cls6 = class$("freemarker.debug.Debugger");
                class$freemarker$debug$Debugger = cls6;
            }
            Class[] clsArr3 = new Class[1];
            Class cls7 = class$java$lang$String;
            if (cls7 == null) {
                cls7 = class$("java.lang.String");
                class$java$lang$String = cls7;
            }
            clsArr3[0] = cls7;
            $method_getBreakpoints_3 = cls6.getMethod("getBreakpoints", clsArr3);
            Class cls8 = class$freemarker$debug$Debugger;
            if (cls8 == null) {
                cls8 = class$("freemarker.debug.Debugger");
                class$freemarker$debug$Debugger = cls8;
            }
            $method_getSuspendedEnvironments_4 = cls8.getMethod("getSuspendedEnvironments", new Class[0]);
            Class cls9 = class$freemarker$debug$Debugger;
            if (cls9 == null) {
                cls9 = class$("freemarker.debug.Debugger");
                class$freemarker$debug$Debugger = cls9;
            }
            Class[] clsArr4 = new Class[1];
            Class cls10 = class$freemarker$debug$Breakpoint;
            if (cls10 == null) {
                cls10 = class$("freemarker.debug.Breakpoint");
                class$freemarker$debug$Breakpoint = cls10;
            }
            clsArr4[0] = cls10;
            $method_removeBreakpoint_5 = cls9.getMethod("removeBreakpoint", clsArr4);
            Class cls11 = class$freemarker$debug$Debugger;
            if (cls11 == null) {
                cls11 = class$("freemarker.debug.Debugger");
                class$freemarker$debug$Debugger = cls11;
            }
            $method_removeBreakpoints_6 = cls11.getMethod("removeBreakpoints", new Class[0]);
            Class cls12 = class$freemarker$debug$Debugger;
            if (cls12 == null) {
                cls12 = class$("freemarker.debug.Debugger");
                class$freemarker$debug$Debugger = cls12;
            }
            Class[] clsArr5 = new Class[1];
            Class cls13 = class$java$lang$String;
            if (cls13 == null) {
                cls13 = class$("java.lang.String");
                class$java$lang$String = cls13;
            }
            clsArr5[0] = cls13;
            $method_removeBreakpoints_7 = cls12.getMethod("removeBreakpoints", clsArr5);
            Class cls14 = class$freemarker$debug$Debugger;
            if (cls14 == null) {
                cls14 = class$("freemarker.debug.Debugger");
                class$freemarker$debug$Debugger = cls14;
            }
            Class[] clsArr6 = new Class[1];
            Class cls15 = class$java$lang$Object;
            if (cls15 == null) {
                cls15 = class$("java.lang.Object");
                class$java$lang$Object = cls15;
            }
            clsArr6[0] = cls15;
            $method_removeDebuggerListener_8 = cls14.getMethod("removeDebuggerListener", clsArr6);
        } catch (NoSuchMethodException unused) {
            throw new NoSuchMethodError("stub class initialization failed");
        }
    }

    public RmiDebuggerImpl_Stub(RemoteRef remoteRef) {
        super(remoteRef);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public void addBreakpoint(Breakpoint breakpoint) throws RemoteException {
        try {
            this.ref.invoke(this, $method_addBreakpoint_0, new Object[]{breakpoint}, -7089035859976030762L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException("undeclared checked exception", e3);
        }
    }

    public Object addDebuggerListener(DebuggerListener debuggerListener) throws RemoteException {
        try {
            return this.ref.invoke(this, $method_addDebuggerListener_1, new Object[]{debuggerListener}, 3973888913376431645L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException("undeclared checked exception", e3);
        }
    }

    public List getBreakpoints() throws RemoteException {
        try {
            return (List) this.ref.invoke(this, $method_getBreakpoints_2, (Object[]) null, 2717170791450965365L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException("undeclared checked exception", e3);
        }
    }

    public List getBreakpoints(String str) throws RemoteException {
        try {
            return (List) this.ref.invoke(this, $method_getBreakpoints_3, new Object[]{str}, 2245868106496574916L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException("undeclared checked exception", e3);
        }
    }

    public Collection getSuspendedEnvironments() throws RemoteException {
        try {
            return (Collection) this.ref.invoke(this, $method_getSuspendedEnvironments_4, (Object[]) null, 6416507983008154583L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException("undeclared checked exception", e3);
        }
    }

    public void removeBreakpoint(Breakpoint breakpoint) throws RemoteException {
        try {
            this.ref.invoke(this, $method_removeBreakpoint_5, new Object[]{breakpoint}, -6894101526753771883L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException("undeclared checked exception", e3);
        }
    }

    public void removeBreakpoints() throws RemoteException {
        try {
            this.ref.invoke(this, $method_removeBreakpoints_6, (Object[]) null, -431815962995809519L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException("undeclared checked exception", e3);
        }
    }

    public void removeBreakpoints(String str) throws RemoteException {
        try {
            this.ref.invoke(this, $method_removeBreakpoints_7, new Object[]{str}, -4131389507095882284L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException("undeclared checked exception", e3);
        }
    }

    public void removeDebuggerListener(Object obj) throws RemoteException {
        try {
            this.ref.invoke(this, $method_removeDebuggerListener_8, new Object[]{obj}, 8368105080961049709L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException("undeclared checked exception", e3);
        }
    }
}
