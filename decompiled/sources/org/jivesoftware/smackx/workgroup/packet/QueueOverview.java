package org.jivesoftware.smackx.workgroup.packet;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smackx.workgroup.agent.WorkgroupQueue;
import org.xmlpull.v1.XmlPullParser;

public class QueueOverview implements PacketExtension {
    /* access modifiers changed from: private */
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
    public static String ELEMENT_NAME = "notify-queue";
    public static String NAMESPACE = "http://jabber.org/protocol/workgroup";
    private int averageWaitTime = -1;
    private Date oldestEntry = null;
    private WorkgroupQueue.Status status = null;
    private int userCount = -1;

    QueueOverview() {
    }

    /* access modifiers changed from: package-private */
    public void setAverageWaitTime(int i) {
        this.averageWaitTime = i;
    }

    public int getAverageWaitTime() {
        return this.averageWaitTime;
    }

    /* access modifiers changed from: package-private */
    public void setOldestEntry(Date date) {
        this.oldestEntry = date;
    }

    public Date getOldestEntry() {
        return this.oldestEntry;
    }

    /* access modifiers changed from: package-private */
    public void setUserCount(int i) {
        this.userCount = i;
    }

    public int getUserCount() {
        return this.userCount;
    }

    public WorkgroupQueue.Status getStatus() {
        return this.status;
    }

    /* access modifiers changed from: package-private */
    public void setStatus(WorkgroupQueue.Status status2) {
        this.status = status2;
    }

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=\"");
        sb.append(NAMESPACE);
        sb.append("\">");
        if (this.userCount != -1) {
            sb.append("<count>");
            sb.append(this.userCount);
            sb.append("</count>");
        }
        if (this.oldestEntry != null) {
            sb.append("<oldest>");
            sb.append(DATE_FORMATTER.format(this.oldestEntry));
            sb.append("</oldest>");
        }
        if (this.averageWaitTime != -1) {
            sb.append("<time>");
            sb.append(this.averageWaitTime);
            sb.append("</time>");
        }
        if (this.status != null) {
            sb.append("<status>");
            sb.append(this.status);
            sb.append("</status>");
        }
        sb.append("</");
        sb.append(ELEMENT_NAME);
        sb.append(">");
        return sb.toString();
    }

    public static class Provider implements PacketExtensionProvider {
        public PacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
            xmlPullParser.getEventType();
            QueueOverview queueOverview = new QueueOverview();
            int next = xmlPullParser.next();
            while (true) {
                if (next == 3 && QueueOverview.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                    return queueOverview;
                }
                if ("count".equals(xmlPullParser.getName())) {
                    queueOverview.setUserCount(Integer.parseInt(xmlPullParser.nextText()));
                } else if ("time".equals(xmlPullParser.getName())) {
                    queueOverview.setAverageWaitTime(Integer.parseInt(xmlPullParser.nextText()));
                } else if ("oldest".equals(xmlPullParser.getName())) {
                    queueOverview.setOldestEntry(QueueOverview.DATE_FORMATTER.parse(xmlPullParser.nextText()));
                } else if ("status".equals(xmlPullParser.getName())) {
                    queueOverview.setStatus(WorkgroupQueue.Status.fromString(xmlPullParser.nextText()));
                }
                next = xmlPullParser.next();
            }
        }
    }
}
