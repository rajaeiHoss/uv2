package freemarker.debug;

import freemarker.debug.impl.RmiDebuggerListenerImpl;
import freemarker.template.utility.UndeclaredThrowableException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.List;

public class DebuggerClient {
    private DebuggerClient() {
    }

    public static Debugger getDebugger(InetAddress inetAddress, int i, String str) throws IOException {
        Socket socket;
        try {
            socket = new Socket(inetAddress, i);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            int readInt = objectInputStream.readInt();
            if (readInt <= 220) {
                MessageDigest instance = MessageDigest.getInstance("SHA");
                instance.update(str.getBytes("UTF-8"));
                instance.update((byte[]) objectInputStream.readObject());
                objectOutputStream.writeObject(instance.digest());
                LocalDebuggerProxy localDebuggerProxy = new LocalDebuggerProxy((Debugger) objectInputStream.readObject());
                socket.close();
                return localDebuggerProxy;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Incompatible protocol version ");
            stringBuffer.append(readInt);
            stringBuffer.append(". At most 220 was expected.");
            throw new IOException(stringBuffer.toString());
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            throw new UndeclaredThrowableException(e2);
        } catch (Throwable th) {
            socket.close();
            throw th;
        }
    }

    private static class LocalDebuggerProxy implements Debugger {
        private final Debugger remoteDebugger;

        LocalDebuggerProxy(Debugger debugger) {
            this.remoteDebugger = debugger;
        }

        public void addBreakpoint(Breakpoint breakpoint) throws RemoteException {
            this.remoteDebugger.addBreakpoint(breakpoint);
        }

        public Object addDebuggerListener(DebuggerListener debuggerListener) throws RemoteException {
            if (debuggerListener instanceof RemoteObject) {
                return this.remoteDebugger.addDebuggerListener(debuggerListener);
            }
            return this.remoteDebugger.addDebuggerListener(new RmiDebuggerListenerImpl(debuggerListener));
        }

        public List getBreakpoints() throws RemoteException {
            return this.remoteDebugger.getBreakpoints();
        }

        public List getBreakpoints(String str) throws RemoteException {
            return this.remoteDebugger.getBreakpoints(str);
        }

        public Collection getSuspendedEnvironments() throws RemoteException {
            return this.remoteDebugger.getSuspendedEnvironments();
        }

        public void removeBreakpoint(Breakpoint breakpoint) throws RemoteException {
            this.remoteDebugger.removeBreakpoint(breakpoint);
        }

        public void removeBreakpoints(String str) throws RemoteException {
            this.remoteDebugger.removeBreakpoints(str);
        }

        public void removeBreakpoints() throws RemoteException {
            this.remoteDebugger.removeBreakpoints();
        }

        public void removeDebuggerListener(Object obj) throws RemoteException {
            this.remoteDebugger.removeDebuggerListener(obj);
        }
    }
}
