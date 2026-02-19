package org.jivesoftware.smackx.workgroup.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;

public class TranscriptSearch extends IQ {
    public static final String ELEMENT_NAME = "transcript-search";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";

    public String getChildElementXML() {
        return "<" + ELEMENT_NAME + " xmlns=\"" + "http://jivesoftware.com/protocol/workgroup" + "\">" + getExtensionsXML() + "</" + ELEMENT_NAME + "> ";
    }

    public static class Provider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            TranscriptSearch transcriptSearch = new TranscriptSearch();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    transcriptSearch.addExtension(PacketParserUtils.parsePacketExtension(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                } else if (next == 3 && xmlPullParser.getName().equals(TranscriptSearch.ELEMENT_NAME)) {
                    z = true;
                }
            }
            return transcriptSearch;
        }
    }
}
