package org.jivesoftware.smackx;

import com.google.android.gms.actions.SearchIntents;
import java.util.Hashtable;
import java.util.Map;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.packet.DefaultPrivateData;
import org.jivesoftware.smackx.packet.PrivateData;
import org.jivesoftware.smackx.provider.PrivateDataProvider;
import org.xmlpull.v1.XmlPullParser;

public class PrivateDataManager {
    private static Map privateDataProviders = new Hashtable();
    private Connection connection;
    private String user;

    public static PrivateDataProvider getPrivateDataProvider(String str, String str2) {
        return (PrivateDataProvider) privateDataProviders.get(getProviderKey(str, str2));
    }

    public static void addPrivateDataProvider(String str, String str2, PrivateDataProvider privateDataProvider) {
        privateDataProviders.put(getProviderKey(str, str2), privateDataProvider);
    }

    public static void removePrivateDataProvider(String str, String str2) {
        privateDataProviders.remove(getProviderKey(str, str2));
    }

    public PrivateDataManager(Connection connection2) {
        if (connection2.isAuthenticated()) {
            this.connection = connection2;
            return;
        }
        throw new IllegalStateException("Must be logged in to XMPP server.");
    }

    public PrivateDataManager(Connection connection2, String str) {
        if (connection2.isAuthenticated()) {
            this.connection = connection2;
            this.user = str;
            return;
        }
        throw new IllegalStateException("Must be logged in to XMPP server.");
    }

    public PrivateData getPrivateData(final String str, final String str2) throws XMPPException {
        IQ request = new IQ() {
            public String getChildElementXML() {
                return "<query xmlns=\"jabber:iq:private\">" + "<" + str + " xmlns=\"" + str2 + "\"/>" + "</query>";
            }
        };
        request.setType(IQ.Type.GET);
        String str3 = this.user;
        if (str3 != null) {
            request.setTo(str3);
        }
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(request.getPacketID()));
        this.connection.sendPacket(request);
        IQ iq = (IQ) createPacketCollector.nextResult((long) SmackConfiguration.getPacketReplyTimeout());
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from the server.");
        } else if (iq.getType() != IQ.Type.ERROR) {
            return ((PrivateDataResult) iq).getPrivateData();
        } else {
            throw new XMPPException(iq.getError());
        }
    }

    public void setPrivateData(final PrivateData privateData) throws XMPPException {
        IQ request = new IQ() {
            public String getChildElementXML() {
                return "<query xmlns=\"jabber:iq:private\">" + privateData.toXML() + "</query>";
            }
        };
        request.setType(IQ.Type.SET);
        String str = this.user;
        if (str != null) {
            request.setTo(str);
        }
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new PacketIDFilter(request.getPacketID()));
        this.connection.sendPacket(request);
        IQ iq = (IQ) createPacketCollector.nextResult(5000);
        createPacketCollector.cancel();
        if (iq == null) {
            throw new XMPPException("No response from the server.");
        } else if (iq.getType() == IQ.Type.ERROR) {
            throw new XMPPException(iq.getError());
        }
    }

    private static String getProviderKey(String str, String str2) {
        return "<" + str + "/><" + str2 + "/>";
    }

    public static class PrivateDataIQProvider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            PrivateData privateData = null;
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    PrivateDataProvider privateDataProvider = PrivateDataManager.getPrivateDataProvider(name, namespace);
                    if (privateDataProvider != null) {
                        privateData = privateDataProvider.parsePrivateData(xmlPullParser);
                    } else {
                        DefaultPrivateData defaultPrivateData = new DefaultPrivateData(name, namespace);
                        boolean z2 = false;
                        while (!z2) {
                            int next2 = xmlPullParser.next();
                            if (next2 == 2) {
                                String name2 = xmlPullParser.getName();
                                if (xmlPullParser.isEmptyElementTag()) {
                                    defaultPrivateData.setValue(name2, "");
                                } else if (xmlPullParser.next() == 4) {
                                    defaultPrivateData.setValue(name2, xmlPullParser.getText());
                                }
                            } else if (next2 == 3 && xmlPullParser.getName().equals(name)) {
                                z2 = true;
                            }
                        }
                        privateData = defaultPrivateData;
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                    z = true;
                }
            }
            return new PrivateDataResult(privateData);
        }
    }

    private static class PrivateDataResult extends IQ {
        private PrivateData privateData;

        PrivateDataResult(PrivateData privateData2) {
            this.privateData = privateData2;
        }

        public PrivateData getPrivateData() {
            return this.privateData;
        }

        public String getChildElementXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<query xmlns=\"jabber:iq:private\">");
            PrivateData privateData2 = this.privateData;
            if (privateData2 != null) {
                privateData2.toXML();
            }
            sb.append("</query>");
            return sb.toString();
        }
    }
}
