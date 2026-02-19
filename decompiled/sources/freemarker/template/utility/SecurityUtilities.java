package freemarker.template.utility;

import freemarker.log.Logger;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class SecurityUtilities {
    private static final Logger logger = Logger.getLogger("freemarker.security");

    private SecurityUtilities() {
    }

    public static String getSystemProperty(final String str) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return System.getProperty(str);
            }
        });
    }

    public static String getSystemProperty(final String str, final String str2) {
        try {
            return (String) AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    return System.getProperty(str, str2);
                }
            });
        } catch (AccessControlException unused) {
            Logger logger2 = logger;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Insufficient permissions to read system property ");
            stringBuffer.append(StringUtil.jQuoteNoXSS(str));
            stringBuffer.append(", using default value ");
            stringBuffer.append(StringUtil.jQuoteNoXSS(str2));
            logger2.warn(stringBuffer.toString());
            return str2;
        }
    }

    public static Integer getSystemProperty(final String str, final int i) {
        try {
            return (Integer) AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    return Integer.getInteger(str, i);
                }
            });
        } catch (AccessControlException unused) {
            Logger logger2 = logger;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Insufficient permissions to read system property ");
            stringBuffer.append(StringUtil.jQuote(str));
            stringBuffer.append(", using default value ");
            stringBuffer.append(i);
            logger2.warn(stringBuffer.toString());
            return new Integer(i);
        }
    }
}
