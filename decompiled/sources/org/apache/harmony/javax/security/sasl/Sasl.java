package org.apache.harmony.javax.security.sasl;

import java.security.Provider;
import java.security.Security;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;

public class Sasl {
    private static final String CLIENTFACTORYSRV = "SaslClientFactory";
    public static final String MAX_BUFFER = "javax.security.sasl.maxbuffer";
    public static final String POLICY_FORWARD_SECRECY = "javax.security.sasl.policy.forward";
    public static final String POLICY_NOACTIVE = "javax.security.sasl.policy.noactive";
    public static final String POLICY_NOANONYMOUS = "javax.security.sasl.policy.noanonymous";
    public static final String POLICY_NODICTIONARY = "javax.security.sasl.policy.nodictionary";
    public static final String POLICY_NOPLAINTEXT = "javax.security.sasl.policy.noplaintext";
    public static final String POLICY_PASS_CREDENTIALS = "javax.security.sasl.policy.credentials";
    public static final String QOP = "javax.security.sasl.qop";
    public static final String RAW_SEND_SIZE = "javax.security.sasl.rawsendsize";
    public static final String REUSE = "javax.security.sasl.reuse";
    private static final String SERVERFACTORYSRV = "SaslServerFactory";
    public static final String SERVER_AUTH = "javax.security.sasl.server.authentication";
    public static final String STRENGTH = "javax.security.sasl.strength";

    private Sasl() {
    }

    private static Object newInstance(String str, Provider provider) throws SaslException {
        ClassLoader classLoader = provider.getClass().getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        try {
            return Class.forName(str, true, classLoader).newInstance();
        } catch (IllegalAccessException e) {
            throw new SaslException("auth.31" + str, e);
        } catch (ClassNotFoundException e2) {
            throw new SaslException("auth.31" + str, e2);
        } catch (InstantiationException e3) {
            throw new SaslException("auth.31" + str, e3);
        }
    }

    private static Collection<?> findFactories(String str) {
        HashSet hashSet = new HashSet();
        Provider[] providers = Security.getProviders();
        if (!(providers == null || providers.length == 0)) {
            HashSet hashSet2 = new HashSet();
            for (int i = 0; i < providers.length; i++) {
                String name = providers[i].getName();
                Enumeration<Object> keys = providers[i].keys();
                while (keys.hasMoreElements()) {
                    String str2 = (String) keys.nextElement();
                    if (str2.startsWith(str)) {
                        String property = providers[i].getProperty(str2);
                        try {
                            if (hashSet2.add(name.concat(property))) {
                                hashSet.add(newInstance(property, providers[i]));
                            }
                        } catch (SaslException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return hashSet;
    }

    public static Enumeration<SaslClientFactory> getSaslClientFactories() {
        return Collections.enumeration((Collection) findFactories(CLIENTFACTORYSRV));
    }

    public static Enumeration<SaslServerFactory> getSaslServerFactories() {
        return Collections.enumeration((Collection) findFactories(SERVERFACTORYSRV));
    }

    public static SaslServer createSaslServer(String str, String str2, String str3, Map<String, ?> map, CallbackHandler callbackHandler) throws SaslException {
        SaslServer createSaslServer;
        Objects.requireNonNull(str, "auth.32");
        Collection<?> findFactories = findFactories(SERVERFACTORYSRV);
        if (findFactories.isEmpty()) {
            return null;
        }
        Iterator<?> it = findFactories.iterator();
        while (it.hasNext()) {
            SaslServerFactory saslServerFactory = (SaslServerFactory) it.next();
            String[] mechanismNames = saslServerFactory.getMechanismNames((Map<String, ?>) null);
            boolean z = false;
            if (mechanismNames != null) {
                int i = 0;
                while (true) {
                    if (i >= mechanismNames.length) {
                        break;
                    } else if (mechanismNames[i].equals(str)) {
                        z = true;
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (z && (createSaslServer = saslServerFactory.createSaslServer(str, str2, str3, map, callbackHandler)) != null) {
                return createSaslServer;
            }
        }
        return null;
    }

    public static SaslClient createSaslClient(String[] strArr, String str, String str2, String str3, Map<String, ?> map, CallbackHandler callbackHandler) throws SaslException {
        SaslClient createSaslClient;
        Objects.requireNonNull(strArr, "auth.33");
        Collection<?> findFactories = findFactories(CLIENTFACTORYSRV);
        if (findFactories.isEmpty()) {
            return null;
        }
        Iterator<?> it = findFactories.iterator();
        while (it.hasNext()) {
            SaslClientFactory saslClientFactory = (SaslClientFactory) it.next();
            String[] mechanismNames = saslClientFactory.getMechanismNames((Map<String, ?>) null);
            boolean z = false;
            if (mechanismNames != null) {
                boolean z2 = false;
                for (int i = 0; i < mechanismNames.length; i++) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= strArr.length) {
                            break;
                        } else if (mechanismNames[i].equals(strArr[i2])) {
                            z2 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                z = z2;
            }
            if (z && (createSaslClient = saslClientFactory.createSaslClient(strArr, str, str2, str3, map, callbackHandler)) != null) {
                return createSaslClient;
            }
        }
        return null;
    }
}
