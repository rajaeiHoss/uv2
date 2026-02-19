package org.jivesoftware.smackx.workgroup.settings;

import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.workgroup.util.ModelUtil;
import org.xmlpull.v1.XmlPullParser;

public class GenericSettings extends IQ {
    public static final String ELEMENT_NAME = "generic-metadata";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private Map map = new HashMap();
    private String query;

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public Map getMap() {
        return this.map;
    }

    public void setMap(Map map2) {
        this.map = map2;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=");
        sb.append(Typography.quote);
        sb.append("http://jivesoftware.com/protocol/workgroup");
        sb.append(Typography.quote);
        sb.append(">");
        if (ModelUtil.hasLength(getQuery())) {
            sb.append("<query>" + getQuery() + "</query>");
        }
        sb.append("</");
        sb.append(ELEMENT_NAME);
        sb.append("> ");
        return sb.toString();
    }

    public static class InternalProvider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            if (xmlPullParser.getEventType() == 2) {
                GenericSettings genericSettings = new GenericSettings();
                boolean z = false;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2 && "entry".equals(xmlPullParser.getName())) {
                        xmlPullParser.next();
                        String nextText = xmlPullParser.nextText();
                        xmlPullParser.next();
                        genericSettings.getMap().put(nextText, xmlPullParser.nextText());
                    } else if (next == 3 && GenericSettings.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                        z = true;
                    }
                }
                return genericSettings;
            }
            throw new IllegalStateException("Parser not in proper position, or bad XML.");
        }
    }
}
