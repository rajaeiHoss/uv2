package org.jivesoftware.smackx.workgroup.ext.notes;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class ChatNotes extends IQ {
    public static final String ELEMENT_NAME = "chat-notes";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private String notes;
    private String sessionID;

    public String getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(String str) {
        this.sessionID = str;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String str) {
        this.notes = str;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=\"");
        sb.append("http://jivesoftware.com/protocol/workgroup");
        sb.append("\">");
        sb.append("<sessionID>");
        sb.append(getSessionID());
        sb.append("</sessionID>");
        if (getNotes() != null) {
            sb.append("<notes>");
            sb.append(getNotes());
            sb.append("</notes>");
        }
        sb.append("</");
        sb.append(ELEMENT_NAME);
        sb.append("> ");
        return sb.toString();
    }

    public static class Provider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            ChatNotes chatNotes = new ChatNotes();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("sessionID")) {
                        chatNotes.setSessionID(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals("text")) {
                        chatNotes.setNotes(xmlPullParser.nextText().replaceAll("\\\\n", "\n"));
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(ChatNotes.ELEMENT_NAME)) {
                    z = true;
                }
            }
            return chatNotes;
        }
    }

    public static final String replace(String str, String str2, String str3) {
        int indexOf;
        if (str == null) {
            return null;
        }
        if (str3 == null || (indexOf = str.indexOf(str2, 0)) < 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        char[] charArray2 = str3.toCharArray();
        int length = str2.length();
        StringBuilder sb = new StringBuilder(charArray.length);
        sb.append(charArray, 0, indexOf);
        sb.append(charArray2);
        int i = indexOf + length;
        while (true) {
            int indexOf2 = str.indexOf(str2, i);
            if (indexOf2 > 0) {
                sb.append(charArray, i, indexOf2 - i);
                sb.append(charArray2);
                i = indexOf2 + length;
            } else {
                sb.append(charArray, i, charArray.length - i);
                return sb.toString();
            }
        }
    }
}
