package freemarker.ext.jython;

import freemarker.template.utility.StringUtil;

class JythonVersionAdapterHolder {
    static final JythonVersionAdapter INSTANCE;
    static /* synthetic */ Class class$freemarker$ext$jython$JythonVersionAdapter;
    static /* synthetic */ Class class$org$python$core$PySystemState;

    JythonVersionAdapterHolder() {
    }

    static {
        try {
            Class cls = class$org$python$core$PySystemState;
            if (cls == null) {
                cls = class$("org.python.core.PySystemState");
                class$org$python$core$PySystemState = cls;
            }
            int versionStringToInt = StringUtil.versionStringToInt(cls.getField("version").get((Object) null).toString());
            Class cls2 = class$freemarker$ext$jython$JythonVersionAdapter;
            if (cls2 == null) {
                cls2 = class$("freemarker.ext.jython.JythonVersionAdapter");
                class$freemarker$ext$jython$JythonVersionAdapter = cls2;
            }
            ClassLoader classLoader = cls2.getClassLoader();
            if (versionStringToInt >= 2005000) {
                try {
                    INSTANCE = (JythonVersionAdapter) classLoader.loadClass("freemarker.ext.jython._Jython25VersionAdapter").newInstance();
                } catch (ClassNotFoundException e) {
                    throw adapterCreationException(e);
                } catch (IllegalAccessException e2) {
                    throw adapterCreationException(e2);
                } catch (InstantiationException e3) {
                    throw adapterCreationException(e3);
                }
            } else if (versionStringToInt >= 2002000) {
                INSTANCE = (JythonVersionAdapter) classLoader.loadClass("freemarker.ext.jython._Jython22VersionAdapter").newInstance();
            } else {
                INSTANCE = (JythonVersionAdapter) classLoader.loadClass("freemarker.ext.jython._Jython20And21VersionAdapter").newInstance();
            }
        } catch (Exception e4) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Failed to get Jython version: ");
            stringBuffer.append(e4);
            throw new RuntimeException(stringBuffer.toString());
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static RuntimeException adapterCreationException(Exception exc) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unexpected exception when creating JythonVersionAdapter: ");
        stringBuffer.append(exc);
        return new RuntimeException(stringBuffer.toString());
    }
}
