package org.jivesoftware.smack;

import com.google.android.gms.fitness.FitnessStatusCodes;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;

public final class SmackConfiguration {
    private static final String SMACK_VERSION = "3.1.0";
    private static Vector<String> defaultMechs = new Vector<>();
    private static int keepAliveInterval = 30000;
    private static boolean localSocks5ProxyEnabled = false;
    private static int localSocks5ProxyPort = 0;
    private static int packetReplyTimeout = 5000;

    public static String getVersion() {
        return SMACK_VERSION;
    }

    static {
        defaultMechs = new Vector<>();
        localSocks5ProxyEnabled = true;
        localSocks5ProxyPort = 7777;
        try {
            ClassLoader[] classLoaders = getClassLoaders();
            for (ClassLoader classLoader : classLoaders) {
                if (classLoader != null) {
                    java.util.Enumeration<java.net.URL> resources = classLoader.getResources("META-INF/smack-config.xml");
                    while (resources.hasMoreElements()) {
                        java.io.InputStream inputStream = null;
                        try {
                            java.net.URL url = resources.nextElement();
                            inputStream = url.openStream();
                            org.xmlpull.v1.XmlPullParser parser = org.xmlpull.v1.XmlPullParserFactory.newInstance().newPullParser();
                            parser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                            parser.setInput(inputStream, "UTF-8");
                            int eventType = parser.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                if (eventType == XmlPullParser.START_TAG) {
                                    String name = parser.getName();
                                    if ("className".equals(name)) {
                                        parseClassToLoad(parser);
                                    } else if ("packetReplyTimeout".equals(name)) {
                                        packetReplyTimeout = parseIntProperty(parser, packetReplyTimeout);
                                    } else if ("keepAliveInterval".equals(name)) {
                                        keepAliveInterval = parseIntProperty(parser, keepAliveInterval);
                                    } else if ("mechName".equals(name)) {
                                        defaultMechs.add(parser.nextText());
                                    } else if ("localSocks5ProxyEnabled".equals(name)) {
                                        localSocks5ProxyEnabled = Boolean.parseBoolean(parser.nextText());
                                    } else if ("localSocks5ProxyPort".equals(name)) {
                                        localSocks5ProxyPort = parseIntProperty(parser, localSocks5ProxyPort);
                                    }
                                }
                                eventType = parser.next();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception unused) {
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SmackConfiguration() {
    }

    public static int getPacketReplyTimeout() {
        if (packetReplyTimeout <= 0) {
            packetReplyTimeout = FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
        }
        return packetReplyTimeout;
    }

    public static void setPacketReplyTimeout(int i) {
        if (i > 0) {
            packetReplyTimeout = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public static int getKeepAliveInterval() {
        return keepAliveInterval;
    }

    public static void setKeepAliveInterval(int i) {
        keepAliveInterval = i;
    }

    public static void addSaslMech(String str) {
        if (!defaultMechs.contains(str)) {
            defaultMechs.add(str);
        }
    }

    public static void addSaslMechs(Collection<String> collection) {
        for (String addSaslMech : collection) {
            addSaslMech(addSaslMech);
        }
    }

    public static void removeSaslMech(String str) {
        if (defaultMechs.contains(str)) {
            defaultMechs.remove(str);
        }
    }

    public static void removeSaslMechs(Collection<String> collection) {
        for (String removeSaslMech : collection) {
            removeSaslMech(removeSaslMech);
        }
    }

    public static List<String> getSaslMechs() {
        return defaultMechs;
    }

    public static boolean isLocalSocks5ProxyEnabled() {
        return localSocks5ProxyEnabled;
    }

    public static void setLocalSocks5ProxyEnabled(boolean z) {
        localSocks5ProxyEnabled = z;
    }

    public static int getLocalSocks5ProxyPort() {
        return localSocks5ProxyPort;
    }

    public static void setLocalSocks5ProxyPort(int i) {
        localSocks5ProxyPort = i;
    }

    private static void parseClassToLoad(XmlPullParser xmlPullParser) throws Exception {
        String nextText = xmlPullParser.nextText();
        try {
            Class.forName(nextText);
        } catch (ClassNotFoundException unused) {
            PrintStream printStream = System.err;
            printStream.println("Error! A startup class specified in smack-config.xml could not be loaded: " + nextText);
        }
    }

    private static int parseIntProperty(XmlPullParser xmlPullParser, int i) throws Exception {
        try {
            return Integer.parseInt(xmlPullParser.nextText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return i;
        }
    }

    private static ClassLoader[] getClassLoaders() {
        ClassLoader[] classLoaderArr = {SmackConfiguration.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 2; i++) {
            ClassLoader classLoader = classLoaderArr[i];
            if (classLoader != null) {
                arrayList.add(classLoader);
            }
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }
}
