package org.jivesoftware.smackx.workgroup.settings;

import kotlin.text.Typography;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.workgroup.util.ModelUtil;
import org.xmlpull.v1.XmlPullParser;

public class WorkgroupProperties extends IQ {
    public static final String ELEMENT_NAME = "workgroup-properties";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private boolean authRequired;
    private String email;
    private String fullName;
    private String jid;

    public boolean isAuthRequired() {
        return this.authRequired;
    }

    public void setAuthRequired(boolean z) {
        this.authRequired = z;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String str) {
        this.fullName = str;
    }

    public String getJid() {
        return this.jid;
    }

    public void setJid(String str) {
        this.jid = str;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=");
        sb.append(Typography.quote);
        sb.append("http://jivesoftware.com/protocol/workgroup");
        sb.append(Typography.quote);
        if (ModelUtil.hasLength(getJid())) {
            sb.append("jid=\"" + getJid() + "\" ");
        }
        sb.append("></");
        sb.append(ELEMENT_NAME);
        sb.append("> ");
        return sb.toString();
    }

    public static class InternalProvider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            if (xmlPullParser.getEventType() == 2) {
                WorkgroupProperties workgroupProperties = new WorkgroupProperties();
                boolean z = false;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2 && "authRequired".equals(xmlPullParser.getName())) {
                        workgroupProperties.setAuthRequired(new Boolean(xmlPullParser.nextText()).booleanValue());
                    } else if (next == 2 && "email".equals(xmlPullParser.getName())) {
                        workgroupProperties.setEmail(xmlPullParser.nextText());
                    } else if (next == 2 && "name".equals(xmlPullParser.getName())) {
                        workgroupProperties.setFullName(xmlPullParser.nextText());
                    } else if (next == 3 && WorkgroupProperties.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                        z = true;
                    }
                }
                return workgroupProperties;
            }
            throw new IllegalStateException("Parser not in proper position, or bad XML.");
        }
    }
}
