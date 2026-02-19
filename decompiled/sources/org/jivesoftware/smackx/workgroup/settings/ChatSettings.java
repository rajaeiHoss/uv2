package org.jivesoftware.smackx.workgroup.settings;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.text.Typography;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class ChatSettings extends IQ {
    public static final int BOT_SETTINGS = 2;
    public static final String ELEMENT_NAME = "chat-settings";
    public static final int IMAGE_SETTINGS = 0;
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    public static final int TEXT_SETTINGS = 1;
    private String key;
    private List settings;
    private int type;

    public ChatSettings() {
        this.type = -1;
        this.settings = new ArrayList();
    }

    public ChatSettings(String str) {
        this.type = -1;
        setKey(str);
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void addSetting(ChatSetting chatSetting) {
        this.settings.add(chatSetting);
    }

    public Collection getSettings() {
        return this.settings;
    }

    public ChatSetting getChatSetting(String str) {
        Collection<ChatSetting> settings2 = getSettings();
        if (settings2 == null) {
            return null;
        }
        for (ChatSetting chatSetting : settings2) {
            if (chatSetting.getKey().equals(str)) {
                return chatSetting;
            }
        }
        return null;
    }

    public ChatSetting getFirstEntry() {
        if (this.settings.size() > 0) {
            return (ChatSetting) this.settings.get(0);
        }
        return null;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=");
        sb.append(Typography.quote);
        sb.append("http://jivesoftware.com/protocol/workgroup");
        sb.append(Typography.quote);
        if (this.key != null) {
            sb.append(" key=\"" + this.key + "\"");
        }
        if (this.type != -1) {
            sb.append(" type=\"" + this.type + "\"");
        }
        sb.append("></");
        sb.append(ELEMENT_NAME);
        sb.append("> ");
        return sb.toString();
    }

    public static class InternalProvider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            if (xmlPullParser.getEventType() == 2) {
                ChatSettings chatSettings = new ChatSettings();
                boolean z = false;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2 && "chat-setting".equals(xmlPullParser.getName())) {
                        chatSettings.addSetting(parseChatSetting(xmlPullParser));
                    } else if (next == 3 && ChatSettings.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                        z = true;
                    }
                }
                return chatSettings;
            }
            throw new IllegalStateException("Parser not in proper position, or bad XML.");
        }

        private ChatSetting parseChatSetting(XmlPullParser xmlPullParser) throws Exception {
            String str = null;
            String str2 = null;
            boolean z = false;
            int i = 0;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2 && "key".equals(xmlPullParser.getName())) {
                    str = xmlPullParser.nextText();
                } else if (next == 2 && FirebaseAnalytics.Param.VALUE.equals(xmlPullParser.getName())) {
                    str2 = xmlPullParser.nextText();
                } else if (next == 2 && AppMeasurement.Param.TYPE.equals(xmlPullParser.getName())) {
                    i = Integer.parseInt(xmlPullParser.nextText());
                } else if (next == 3 && "chat-setting".equals(xmlPullParser.getName())) {
                    z = true;
                }
            }
            return new ChatSetting(str, str2, i);
        }
    }
}
