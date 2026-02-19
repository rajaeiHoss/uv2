package freemarker.debug.impl;

import freemarker.debug.DebugModel;
import freemarker.template.TemplateModelException;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteStub;
import java.util.Date;

public final class RmiDebugModelImpl_Stub extends RemoteStub implements DebugModel, Remote {
    private static Method $method_getAsBoolean_4 = null;
    private static Method $method_getAsDate_5 = null;
    private static Method $method_getAsNumber_6 = null;
    private static Method $method_getAsString_7 = null;
    private static Method $method_getCollection_8 = null;
    private static Method $method_getDateType_9 = null;
    private static Method $method_getModelTypes_10 = null;
    private static Method $method_get_0 = null;
    private static Method $method_get_1 = null;
    private static Method $method_get_2 = null;
    private static Method $method_get_3 = null;
    private static Method $method_keys_11 = null;
    private static Method $method_size_12 = null;
    static /* synthetic */ Class array$Ljava$lang$String = null;
    static /* synthetic */ Class class$freemarker$debug$DebugModel = null;
    static /* synthetic */ Class class$java$lang$String = null;
    private static final long serialVersionUID = 2;

    static {
        try {
            Class cls = class$freemarker$debug$DebugModel;
            if (cls == null) {
                cls = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls;
            }
            $method_get_0 = cls.getMethod("get", new Class[]{Integer.TYPE});
            Class cls2 = class$freemarker$debug$DebugModel;
            if (cls2 == null) {
                cls2 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls2;
            }
            $method_get_1 = cls2.getMethod("get", new Class[]{Integer.TYPE, Integer.TYPE});
            Class cls3 = class$freemarker$debug$DebugModel;
            if (cls3 == null) {
                cls3 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls3;
            }
            Class[] clsArr = new Class[1];
            Class cls4 = class$java$lang$String;
            if (cls4 == null) {
                cls4 = class$("java.lang.String");
                class$java$lang$String = cls4;
            }
            clsArr[0] = cls4;
            $method_get_2 = cls3.getMethod("get", clsArr);
            Class cls5 = class$freemarker$debug$DebugModel;
            if (cls5 == null) {
                cls5 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls5;
            }
            Class[] clsArr2 = new Class[1];
            Class cls6 = array$Ljava$lang$String;
            if (cls6 == null) {
                cls6 = class$("[Ljava.lang.String;");
                array$Ljava$lang$String = cls6;
            }
            clsArr2[0] = cls6;
            $method_get_3 = cls5.getMethod("get", clsArr2);
            Class cls7 = class$freemarker$debug$DebugModel;
            if (cls7 == null) {
                cls7 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls7;
            }
            $method_getAsBoolean_4 = cls7.getMethod("getAsBoolean", new Class[0]);
            Class cls8 = class$freemarker$debug$DebugModel;
            if (cls8 == null) {
                cls8 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls8;
            }
            $method_getAsDate_5 = cls8.getMethod("getAsDate", new Class[0]);
            Class cls9 = class$freemarker$debug$DebugModel;
            if (cls9 == null) {
                cls9 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls9;
            }
            $method_getAsNumber_6 = cls9.getMethod("getAsNumber", new Class[0]);
            Class cls10 = class$freemarker$debug$DebugModel;
            if (cls10 == null) {
                cls10 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls10;
            }
            $method_getAsString_7 = cls10.getMethod("getAsString", new Class[0]);
            Class cls11 = class$freemarker$debug$DebugModel;
            if (cls11 == null) {
                cls11 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls11;
            }
            $method_getCollection_8 = cls11.getMethod("getCollection", new Class[0]);
            Class cls12 = class$freemarker$debug$DebugModel;
            if (cls12 == null) {
                cls12 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls12;
            }
            $method_getDateType_9 = cls12.getMethod("getDateType", new Class[0]);
            Class cls13 = class$freemarker$debug$DebugModel;
            if (cls13 == null) {
                cls13 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls13;
            }
            $method_getModelTypes_10 = cls13.getMethod("getModelTypes", new Class[0]);
            Class cls14 = class$freemarker$debug$DebugModel;
            if (cls14 == null) {
                cls14 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls14;
            }
            $method_keys_11 = cls14.getMethod("keys", new Class[0]);
            Class cls15 = class$freemarker$debug$DebugModel;
            if (cls15 == null) {
                cls15 = class$("freemarker.debug.DebugModel");
                class$freemarker$debug$DebugModel = cls15;
            }
            $method_size_12 = cls15.getMethod("size", new Class[0]);
        } catch (NoSuchMethodException unused) {
            throw new NoSuchMethodError("stub class initialization failed");
        }
    }

    public RmiDebugModelImpl_Stub(RemoteRef remoteRef) {
        super(remoteRef);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public DebugModel get(int i) throws TemplateModelException, RemoteException {
        try {
            return (DebugModel) this.ref.invoke(this, $method_get_0, new Object[]{new Integer(i)}, -8133058733457407300L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }

    public DebugModel get(String str) throws TemplateModelException, RemoteException {
        try {
            return (DebugModel) this.ref.invoke(this, $method_get_2, new Object[]{str}, -724507235264020332L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }

    public DebugModel[] get(int i, int i2) throws TemplateModelException, RemoteException {
        try {
            return (DebugModel[]) this.ref.invoke(this, $method_get_1, new Object[]{new Integer(i), new Integer(i2)}, 2963274088089045739L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }

    public DebugModel[] get(String[] strArr) throws TemplateModelException, RemoteException {
        try {
            return (DebugModel[]) this.ref.invoke(this, $method_get_3, new Object[]{strArr}, -5400820492225333337L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }

    public boolean getAsBoolean() throws TemplateModelException, RemoteException {
        try {
            return ((Boolean) this.ref.invoke(this, $method_getAsBoolean_4, (Object[]) null, 315270873791227726L)).booleanValue();
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }

    public Date getAsDate() throws TemplateModelException, RemoteException {
        try {
            return (Date) this.ref.invoke(this, $method_getAsDate_5, (Object[]) null, -6762406881188215033L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }

    public Number getAsNumber() throws TemplateModelException, RemoteException {
        try {
            return (Number) this.ref.invoke(this, $method_getAsNumber_6, (Object[]) null, -6188010426576701549L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }

    public String getAsString() throws TemplateModelException, RemoteException {
        try {
            return (String) this.ref.invoke(this, $method_getAsString_7, (Object[]) null, -5749749291031241731L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }

    public DebugModel[] getCollection() throws TemplateModelException, RemoteException {
        try {
            return (DebugModel[]) this.ref.invoke(this, $method_getCollection_8, (Object[]) null, -1992223977663617938L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }

    public int getDateType() throws TemplateModelException, RemoteException {
        try {
            return ((Integer) this.ref.invoke(this, $method_getDateType_9, (Object[]) null, -3242981404503740604L)).intValue();
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }

    public int getModelTypes() throws RemoteException {
        try {
            return ((Integer) this.ref.invoke(this, $method_getModelTypes_10, (Object[]) null, -3673171458095957561L)).intValue();
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new UnexpectedException("undeclared checked exception", e3);
        }
    }

    public String[] keys() throws TemplateModelException, RemoteException {
        try {
            return (String[]) this.ref.invoke(this, $method_keys_11, (Object[]) null, 563174456558742983L);
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }

    public int size() throws TemplateModelException, RemoteException {
        try {
            return ((Integer) this.ref.invoke(this, $method_size_12, (Object[]) null, 4495240443643581991L)).intValue();
        } catch (RuntimeException e) {
            throw e;
        } catch (RemoteException e2) {
            throw e2;
        } catch (TemplateModelException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new UnexpectedException("undeclared checked exception", e4);
        }
    }
}
