package org.jivesoftware.smackx.packet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.GroupChatInvitation;

public class DelayInformation implements PacketExtension {
    public static final DateFormat XEP_0091_UTC_FORMAT;
    private String from;
    private String reason;
    private Date stamp;

    public String getElementName() {
        return GroupChatInvitation.ELEMENT_NAME;
    }

    public String getNamespace() {
        return "jabber:x:delay";
    }

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
        XEP_0091_UTC_FORMAT = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public DelayInformation(Date date) {
        this.stamp = date;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public Date getStamp() {
        return this.stamp;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getElementName());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\"");
        sb.append(" stamp=\"");
        DateFormat dateFormat = XEP_0091_UTC_FORMAT;
        synchronized (dateFormat) {
            sb.append(dateFormat.format(this.stamp));
        }
        sb.append("\"");
        String str = this.from;
        if (str != null && str.length() > 0) {
            sb.append(" from=\"");
            sb.append(this.from);
            sb.append("\"");
        }
        sb.append(">");
        String str2 = this.reason;
        if (str2 != null && str2.length() > 0) {
            sb.append(this.reason);
        }
        sb.append("</");
        sb.append(getElementName());
        sb.append(">");
        return sb.toString();
    }
}
