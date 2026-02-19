package org.jivesoftware.smackx.workgroup.settings;

import kotlin.text.Typography;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.StringUtils;
import org.xmlpull.v1.XmlPullParser;

public class SoundSettings extends IQ {
    public static final String ELEMENT_NAME = "sound-settings";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private String incomingSound;
    private String outgoingSound;

    public void setOutgoingSound(String str) {
        this.outgoingSound = str;
    }

    public void setIncomingSound(String str) {
        this.incomingSound = str;
    }

    public byte[] getIncomingSoundBytes() {
        return StringUtils.decodeBase64(this.incomingSound);
    }

    public byte[] getOutgoingSoundBytes() {
        return StringUtils.decodeBase64(this.outgoingSound);
    }

    public String getChildElementXML() {
        return "<" + ELEMENT_NAME + " xmlns=" + Typography.quote + "http://jivesoftware.com/protocol/workgroup" + Typography.quote + "></" + ELEMENT_NAME + "> ";
    }

    public static class InternalProvider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            if (xmlPullParser.getEventType() == 2) {
                SoundSettings soundSettings = new SoundSettings();
                boolean z = false;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2 && "outgoingSound".equals(xmlPullParser.getName())) {
                        soundSettings.setOutgoingSound(xmlPullParser.nextText());
                    } else if (next == 2 && "incomingSound".equals(xmlPullParser.getName())) {
                        soundSettings.setIncomingSound(xmlPullParser.nextText());
                    } else if (next == 3 && SoundSettings.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                        z = true;
                    }
                }
                return soundSettings;
            }
            throw new IllegalStateException("Parser not in proper position, or bad XML.");
        }
    }
}
