package org.jivesoftware.smackx.workgroup.ext.macros;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.plus.PlusShare;
import java.io.StringReader;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.StringUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class Macros extends IQ {
    public static final String ELEMENT_NAME = "macros";
    public static final String NAMESPACE = "http://jivesoftware.com/protocol/workgroup";
    private boolean personal;
    private MacroGroup personalMacroGroup;
    private MacroGroup rootGroup;

    public MacroGroup getRootGroup() {
        return this.rootGroup;
    }

    public void setRootGroup(MacroGroup macroGroup) {
        this.rootGroup = macroGroup;
    }

    public boolean isPersonal() {
        return this.personal;
    }

    public void setPersonal(boolean z) {
        this.personal = z;
    }

    public MacroGroup getPersonalMacroGroup() {
        return this.personalMacroGroup;
    }

    public void setPersonalMacroGroup(MacroGroup macroGroup) {
        this.personalMacroGroup = macroGroup;
    }

    public String getChildElementXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(ELEMENT_NAME);
        sb.append(" xmlns=\"");
        sb.append("http://jivesoftware.com/protocol/workgroup");
        sb.append("\">");
        if (isPersonal()) {
            sb.append("<personal>true</personal>");
        }
        if (getPersonalMacroGroup() != null) {
            sb.append("<personalMacro>");
            sb.append(StringUtils.escapeForXML(getPersonalMacroGroup().toXML()));
            sb.append("</personalMacro>");
        }
        sb.append("</");
        sb.append(ELEMENT_NAME);
        sb.append("> ");
        return sb.toString();
    }

    public static class InternalProvider implements IQProvider {
        public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
            Macros macros = new Macros();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("model")) {
                        macros.setRootGroup(parseMacroGroups(xmlPullParser.nextText()));
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(Macros.ELEMENT_NAME)) {
                    z = true;
                }
            }
            return macros;
        }

        public Macro parseMacro(XmlPullParser xmlPullParser) throws Exception {
            Macro macro = new Macro();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE)) {
                        xmlPullParser.next();
                        macro.setTitle(xmlPullParser.getText());
                    } else if (xmlPullParser.getName().equals(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION)) {
                        macro.setDescription(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals("response")) {
                        macro.setResponse(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals(AppMeasurement.Param.TYPE)) {
                        macro.setType(Integer.valueOf(xmlPullParser.nextText()).intValue());
                    }
                } else if (next == 3 && xmlPullParser.getName().equals("macro")) {
                    z = true;
                }
            }
            return macro;
        }

        public MacroGroup parseMacroGroup(XmlPullParser xmlPullParser) throws Exception {
            MacroGroup macroGroup = new MacroGroup();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("macrogroup")) {
                        macroGroup.addMacroGroup(parseMacroGroup(xmlPullParser));
                    }
                    if (xmlPullParser.getName().equals(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE)) {
                        macroGroup.setTitle(xmlPullParser.nextText());
                    }
                    if (xmlPullParser.getName().equals("macro")) {
                        macroGroup.addMacro(parseMacro(xmlPullParser));
                    }
                } else if (next == 3 && xmlPullParser.getName().equals("macrogroup")) {
                    z = true;
                }
            }
            return macroGroup;
        }

        public MacroGroup parseMacroGroups(String str) throws Exception {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(new StringReader(str));
            int eventType = newPullParser.getEventType();
            MacroGroup macroGroup = null;
            while (eventType != 1) {
                eventType = newPullParser.next();
                if (eventType == 2 && newPullParser.getName().equals("macrogroup")) {
                    macroGroup = parseMacroGroup(newPullParser);
                }
            }
            return macroGroup;
        }
    }
}
