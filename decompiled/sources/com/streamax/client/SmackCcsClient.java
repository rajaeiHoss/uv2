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

        public GcmPacketExtension(String jsonPayload) {
            super("gcm", SmackCcsClient.GCM_NAMESPACE);
            this.json = jsonPayload;
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

    public void send(String jsonPayload) {
        this.connection.sendPacket(new GcmPacketExtension(jsonPayload).toPacket());
    }

    public void handleIncomingDataMessage(Map<String, Object> messageMap) {
        String from = messageMap.get(PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM).toString();
        String category = messageMap.get("category").toString();
        Map dataPayload = (Map) messageMap.get(DataPacketExtension.ELEMENT_NAME);
        dataPayload.put("ECHO", "Application: " + category);
        send(createJsonMessage(from, getRandomMessageId(), dataPayload, "echo:CollapseKey", (Long) null, false));
    }

    public void handleAckReceipt(Map<String, Object> messageMap) {
        String messageId = messageMap.get("message_id").toString();
        String from = messageMap.get(PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM).toString();
        Logger clientLogger = this.logger;
        Level level = Level.INFO;
        clientLogger.log(level, "handleAckReceipt() from: " + from + ", messageId: " + messageId);
    }

    public void handleNackReceipt(Map<String, Object> messageMap) {
        String messageId = messageMap.get("message_id").toString();
        String from = messageMap.get(PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM).toString();
        Logger clientLogger = this.logger;
        Level level = Level.INFO;
        clientLogger.log(level, "handleNackReceipt() from: " + from + ", messageId: " + messageId);
    }

    public static String createJsonMessage(String to, String messageId, Map<String, String> dataPayload, String collapseKey, Long timeToLive, Boolean delayWhileIdle) {
        HashMap jsonMap = new HashMap();
        jsonMap.put("to", to);
        if (collapseKey != null) {
            jsonMap.put("collapse_key", collapseKey);
        }
        if (timeToLive != null) {
            jsonMap.put("time_to_live", timeToLive);
        }
        if (delayWhileIdle != null && delayWhileIdle.booleanValue()) {
            jsonMap.put("delay_while_idle", true);
        }
        jsonMap.put("message_id", messageId);
        jsonMap.put(DataPacketExtension.ELEMENT_NAME, dataPayload);
        return JSONValue.toJSONString(jsonMap);
    }

    public static String createJsonAck(String to, String messageId) {
        HashMap jsonMap = new HashMap();
        jsonMap.put("message_type", "ack");
        jsonMap.put("to", to);
        jsonMap.put("message_id", messageId);
        return JSONValue.toJSONString(jsonMap);
    }

    public void connect(String username, String password) throws XMPPException {
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

            public void reconnectingIn(int seconds) {
                SmackCcsClient.this.logger.log(Level.INFO, "Reconnecting in %d secs", Integer.valueOf(seconds));
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
                Logger clientLogger = SmackCcsClient.this.logger;
                Level level = Level.INFO;
                clientLogger.log(level, "Received: " + packet.toXML());
                String incomingJson = ((GcmPacketExtension) ((Message) packet).getExtension(SmackCcsClient.GCM_NAMESPACE)).getJson();
                try {
                    Map messageMap = (Map) JSONValue.parseWithException(incomingJson);
                    Object messageType = messageMap.get("message_type");
                    if (messageType == null) {
                        SmackCcsClient.this.handleIncomingDataMessage(messageMap);
                        SmackCcsClient.this.send(SmackCcsClient.createJsonAck(messageMap.get(PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM).toString(), messageMap.get("message_id").toString()));
                    } else if ("ack".equals(messageType.toString())) {
                        SmackCcsClient.this.handleAckReceipt(messageMap);
                    } else if ("nack".equals(messageType.toString())) {
                        SmackCcsClient.this.handleNackReceipt(messageMap);
                    } else {
                        SmackCcsClient.this.logger.log(Level.WARNING, "Unrecognized message type (%s)", messageType.toString());
                    }
                } catch (ParseException parseException) {
                    Level level2 = Level.SEVERE;
                    clientLogger.log(level2, "Error parsing JSON " + incomingJson, parseException);
                } catch (Exception sendException) {
                    SmackCcsClient.this.logger.log(Level.SEVERE, "Couldn't send echo.", sendException);
                }
            }
        }, new PacketTypeFilter(Message.class));
        this.connection.addPacketInterceptor(new PacketInterceptor() {
            public void interceptPacket(Packet packet) {
                SmackCcsClient.this.logger.log(Level.INFO, "Sent: {0}", packet.toXML());
            }
        }, new PacketTypeFilter(Message.class));
        this.connection.login(username, password);
    }

    public static void test() {
        SmackCcsClient smackCcsClient = new SmackCcsClient();
        try {
            smackCcsClient.connect("918132393478@gcm.googleapis.com", CommonUtilities.API_KEY);
            String randomMessageId = smackCcsClient.getRandomMessageId();
            HashMap dataPayload = new HashMap();
            dataPayload.put("Hello", "World");
            dataPayload.put("CCS", "Dummy Message");
            dataPayload.put("EmbeddedMessageId", randomMessageId);
            smackCcsClient.send(createJsonMessage("RegistrationIdOfTheTargetDevice", randomMessageId, dataPayload, "sample", Long.valueOf(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS), true));
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }
}
