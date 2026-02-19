package org.jivesoftware.smackx.workgroup.ext.history;

import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.workgroup.MetaData;
import org.jivesoftware.smackx.workgroup.util.MetaDataUtils;
import org.xmlpull.v1.XmlPullParser;

public class ChatMetadata extends IQ {
    public static final String ELEMENT_NAME = "chat-metadata";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private Map map = new HashMap();
    private String sessionID;

    public String getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(String str) {
        this.sessionID = str;
    }

    public void setMetadata(Map map2) {
        this.map = map2;
    }

    public Map getMetadata() {
        return this.map;
    }

    public String getChildElementXML() {
        return "<" + ELEMENT_NAME + " xmlns=\"" + "http://jivesoftware.com/protocol/workgroup" + "\">" + "<sessionID>" + getSessionID() + "</sessionID>" + "</" + ELEMENT_NAME + "> ";
    }

    public static class Provider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            ChatMetadata chatMetadata = new ChatMetadata();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("sessionID")) {
                        chatMetadata.setSessionID(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals(MetaData.ELEMENT_NAME)) {
                        chatMetadata.setMetadata(MetaDataUtils.parseMetaData(xmlPullParser));
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(ChatMetadata.ELEMENT_NAME)) {
                    z = true;
                }
            }
            return chatMetadata;
        }
    }
}
