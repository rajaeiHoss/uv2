package com.streamax.client;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocketFactory;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.DefaultPacketExtension;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.packet.PrivacyItem;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.xmlpull.v1.XmlPullParser;

public class SmackCcsClient {
    public static final String GCM_ELEMENT_NAME = "gcm";
    public static final String GCM_NAMESPACE = "google:mobile:data";
    public static final int GCM_PORT = 5235;
    public static final String GCM_SERVER = "gcm.googleapis.com";
    static Random random = new Random();
    ConnectionConfiguration config;
    XMPPConnection connection;
    Logger logger = Logger.getLogger("SmackCcsClient");

    class GcmPacketExtension extends DefaultPacketExtension {
        String json;

        public GcmPacketExtension(String str) {
            super("gcm", SmackCcsClient.GCM_NAMESPACE);
            this.json = str;
        }

        public String getJson() {
            return this.json;
        }

        public String toXML() {
            return String.format("<%s xmlns=\"%s\">%s</%s>", new Object[]{"gcm", SmackCcsClient.GCM_NAMESPACE, this.json, "gcm"});
        }

        public Packet toPacket() {
            return new Message() {
                public String toXML() {
                    StringBuilder sb = new StringBuilder();
                    sb.append("<message");
                    if (getXmlns() != null) {
                        sb.append(" xmlns=\"");
                        sb.append(getXmlns());
                        sb.append("\"");
                    }
                    if (getLanguage() != null) {
                        sb.append(" xml:lang=\"");
                        sb.append(getLanguage());
                        sb.append("\"");
                    }
                    if (getPacketID() != null) {
                        sb.append(" id=\"");
                        sb.append(getPacketID());
                        sb.append("\"");
                    }
                    if (getTo() != null) {
                        sb.append(" to=\"");
                        sb.append(StringUtils.escapeForXML(getTo()));
                        sb.append("\"");
                    }
                    if (getFrom() != null) {
                        sb.append(" from=\"");
                        sb.append(StringUtils.escapeForXML(getFrom()));
                        sb.append("\"");
                    }
                    sb.append(">");
                    sb.append(GcmPacketExtension.this.toXML());
                    sb.append("</message>");
                    return sb.toString();
                }
            };
        }
    }

    public SmackCcsClient() {
        ProviderManager.getInstance().addExtensionProvider("gcm", GCM_NAMESPACE, new PacketExtensionProvider() {
            public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
                return new GcmPacketExtension(xmlPullParser.nextText());
            }
        });
    }

    public String getRandomMessageId() {
        return "m-" + Long.toString(random.nextLong());
    }

    public void send(String str) {
        this.connection.sendPacket(new GcmPacketExtension(str).toPacket());
    }

    public void handleIncomingDataMessage(Map<String, Object> map) {
        String obj = map.get(PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM).toString();
        String obj2 = map.get("category").toString();
        Map map2 = (Map) map.get(DataPacketExtension.ELEMENT_NAME);
        map2.put("ECHO", "Application: " + obj2);
        send(createJsonMessage(obj, getRandomMessageId(), map2, "echo:CollapseKey", (Long) null, false));
    }

    public void handleAckReceipt(Map<String, Object> map) {
        String obj = map.get("message_id").toString();
        String obj2 = map.get(PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM).toString();
        Logger logger2 = this.logger;
        Level level = Level.INFO;
        logger2.log(level, "handleAckReceipt() from: " + obj2 + ", messageId: " + obj);
    }

    public void handleNackReceipt(Map<String, Object> map) {
        String obj = map.get("message_id").toString();
        String obj2 = map.get(PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM).toString();
        Logger logger2 = this.logger;
        Level level = Level.INFO;
        logger2.log(level, "handleNackReceipt() from: " + obj2 + ", messageId: " + obj);
    }

    public static String createJsonMessage(String str, String str2, Map<String, String> map, String str3, Long l, Boolean bool) {
        HashMap hashMap = new HashMap();
        hashMap.put("to", str);
        if (str3 != null) {
            hashMap.put("collapse_key", str3);
        }
        if (l != null) {
            hashMap.put("time_to_live", l);
        }
        if (bool != null && bool.booleanValue()) {
            hashMap.put("delay_while_idle", true);
        }
        hashMap.put("message_id", str2);
        hashMap.put(DataPacketExtension.ELEMENT_NAME, map);
        return JSONValue.toJSONString(hashMap);
    }

    public static String createJsonAck(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("message_type", "ack");
        hashMap.put("to", str);
        hashMap.put("message_id", str2);
        return JSONValue.toJSONString(hashMap);
    }

    public void connect(String str, String str2) throws XMPPException {
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(GCM_SERVER, (int) GCM_PORT);
        this.config = connectionConfiguration;
        connectionConfiguration.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
        this.config.setReconnectionAllowed(true);
        this.config.setRosterLoadedAtLogin(false);
        this.config.setSendPresence(false);
        this.config.setSocketFactory(SSLSocketFactory.getDefault());
        this.config.setDebuggerEnabled(true);
        XMPPConnection.DEBUG_ENABLED = true;
        XMPPConnection xMPPConnection = new XMPPConnection(this.config);
        this.connection = xMPPConnection;
        xMPPConnection.connect();
        this.connection.addConnectionListener(new ConnectionListener() {
            public void reconnectionSuccessful() {
                SmackCcsClient.this.logger.info("Reconnecting..");
            }

            public void reconnectionFailed(Exception exc) {
                SmackCcsClient.this.logger.log(Level.INFO, "Reconnection failed.. ", exc);
            }

            public void reconnectingIn(int i) {
                SmackCcsClient.this.logger.log(Level.INFO, "Reconnecting in %d secs", Integer.valueOf(i));
            }

            public void connectionClosedOnError(Exception exc) {
                SmackCcsClient.this.logger.log(Level.INFO, "Connection closed on error.");
            }

            public void connectionClosed() {
                SmackCcsClient.this.logger.info("Connection closed.");
            }
        });
        this.connection.addPacketListener(new PacketListener() {
            public void processPacket(Packet packet) {
                Logger logger = SmackCcsClient.this.logger;
                Level level = Level.INFO;
                logger.log(level, "Received: " + packet.toXML());
                String json = ((GcmPacketExtension) ((Message) packet).getExtension(SmackCcsClient.GCM_NAMESPACE)).getJson();
                try {
                    Map map = (Map) JSONValue.parseWithException(json);
                    Object obj = map.get("message_type");
                    if (obj == null) {
                        SmackCcsClient.this.handleIncomingDataMessage(map);
                        SmackCcsClient.this.send(SmackCcsClient.createJsonAck(map.get(PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM).toString(), map.get("message_id").toString()));
                    } else if ("ack".equals(obj.toString())) {
                        SmackCcsClient.this.handleAckReceipt(map);
                    } else if ("nack".equals(obj.toString())) {
                        SmackCcsClient.this.handleNackReceipt(map);
                    } else {
                        SmackCcsClient.this.logger.log(Level.WARNING, "Unrecognized message type (%s)", obj.toString());
                    }
                } catch (ParseException e) {
                    Logger logger2 = SmackCcsClient.this.logger;
                    Level level2 = Level.SEVERE;
                    logger2.log(level2, "Error parsing JSON " + json, e);
                } catch (Exception e2) {
                    SmackCcsClient.this.logger.log(Level.SEVERE, "Couldn't send echo.", e2);
                }
            }
        }, new PacketTypeFilter(Message.class));
        this.connection.addPacketInterceptor(new PacketInterceptor() {
            public void interceptPacket(Packet packet) {
                SmackCcsClient.this.logger.log(Level.INFO, "Sent: {0}", packet.toXML());
            }
        }, new PacketTypeFilter(Message.class));
        this.connection.login(str, str2);
    }

    public static void test() {
        SmackCcsClient smackCcsClient = new SmackCcsClient();
        try {
            smackCcsClient.connect("918132393478@gcm.googleapis.com", CommonUtilities.API_KEY);
            String randomMessageId = smackCcsClient.getRandomMessageId();
            HashMap hashMap = new HashMap();
            hashMap.put("Hello", "World");
            hashMap.put("CCS", "Dummy Message");
            hashMap.put("EmbeddedMessageId", randomMessageId);
            smackCcsClient.send(createJsonMessage("RegistrationIdOfTheTargetDevice", randomMessageId, hashMap, "sample", Long.valueOf(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS), true));
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }
}
