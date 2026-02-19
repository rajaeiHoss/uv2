package org.jivesoftware.smackx.workgroup.packet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smackx.workgroup.QueueUser;
import org.xmlpull.v1.XmlPullParser;

public class QueueDetails implements PacketExtension {
    /* access modifiers changed from: private */
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
    public static final String ELEMENT_NAME = "notify-queue-details";
    public static final String NAMESPACE = "http://jabber.org/protocol/workgroup";
    private Set<QueueUser> users;

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return "http://jabber.org/protocol/workgroup";
    }

    private QueueDetails() {
        this.users = new HashSet();
    }

    public int getUserCount() {
        return this.users.size();
    }

    public Set<QueueUser> getUsers() {
        Set<QueueUser> set;
        synchronized (this.users) {
            set = this.users;
        }
        return set;
    }

    /* access modifiers changed from: private */
    public void addUser(QueueUser queueUser) {
        synchronized (this.users) {
            this.users.add(queueUser);
        }
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=\"");
        sb.append("http://jabber.org/protocol/workgroup");
        sb.append("\">");
        synchronized (this.users) {
            for (QueueUser next : this.users) {
                int queuePosition = next.getQueuePosition();
                int estimatedRemainingTime = next.getEstimatedRemainingTime();
                Date queueJoinTimestamp = next.getQueueJoinTimestamp();
                sb.append("<user jid=\"");
                sb.append(next.getUserID());
                sb.append("\">");
                if (queuePosition != -1) {
                    sb.append("<position>");
                    sb.append(queuePosition);
                    sb.append("</position>");
                }
                if (estimatedRemainingTime != -1) {
                    sb.append("<time>");
                    sb.append(estimatedRemainingTime);
                    sb.append("</time>");
                }
                if (queueJoinTimestamp != null) {
                    sb.append("<join-time>");
                    sb.append(DATE_FORMATTER.format(queueJoinTimestamp));
                    sb.append("</join-time>");
                }
                sb.append("</user>");
            }
        }
        sb.append("</");
        sb.append(ELEMENT_NAME);
        sb.append(">");
        return sb.toString();
    }

    public static class Provider implements PacketExtensionProvider {
        public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
            QueueDetails queueDetails = new QueueDetails();
            int eventType = xmlPullParser.getEventType();
            while (eventType != 3 && QueueDetails.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                eventType = xmlPullParser.next();
                while (eventType == 2 && UserID.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                    String attributeValue = xmlPullParser.getAttributeValue("", "jid");
                    int next = xmlPullParser.next();
                    int i = -1;
                    Date date = null;
                    int i2 = -1;
                    while (true) {
                        if (next == 3 && UserID.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                            break;
                        }
                        if ("position".equals(xmlPullParser.getName())) {
                            i = Integer.parseInt(xmlPullParser.nextText());
                        } else if ("time".equals(xmlPullParser.getName())) {
                            i2 = Integer.parseInt(xmlPullParser.nextText());
                        } else if ("join-time".equals(xmlPullParser.getName())) {
                            date = QueueDetails.DATE_FORMATTER.parse(xmlPullParser.nextText());
                        } else if (xmlPullParser.getName().equals("waitTime")) {
                            System.out.println(QueueDetails.DATE_FORMATTER.parse(xmlPullParser.nextText()));
                        }
                        next = xmlPullParser.next();
                    }
                    queueDetails.addUser(new QueueUser(attributeValue, i, i2, date));
                    eventType = xmlPullParser.next();
                }
            }
            return queueDetails;
        }
    }
}
