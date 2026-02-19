package org.jivesoftware.smack.util;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.streamax.client.CommonUtilities;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.packet.Authentication;
import org.jivesoftware.smack.packet.Bind;
import org.jivesoftware.smack.packet.DefaultPacketExtension;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.PrivacyItem;
import org.jivesoftware.smack.packet.Registration;
import org.jivesoftware.smack.packet.RosterPacket;
import org.jivesoftware.smack.packet.StreamError;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smackx.FormField;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PacketParserUtils {
    private static final String PROPERTIES_NAMESPACE = "http://www.jivesoftware.com/xmlns/xmpp/properties";

    public static Packet parseMessage(XmlPullParser xmlPullParser) throws Exception {
        Message message = new Message();
        String attributeValue = xmlPullParser.getAttributeValue("", "id");
        if (attributeValue == null) {
            attributeValue = Packet.ID_NOT_AVAILABLE;
        }
        message.setPacketID(attributeValue);
        message.setTo(xmlPullParser.getAttributeValue("", "to"));
        message.setFrom(xmlPullParser.getAttributeValue("", PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM));
        message.setType(Message.Type.fromString(xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE)));
        String languageAttribute = getLanguageAttribute(xmlPullParser);
        if (languageAttribute == null || "".equals(languageAttribute.trim())) {
            languageAttribute = Packet.getDefaultLanguage();
        } else {
            message.setLanguage(languageAttribute);
        }
        boolean z = false;
        String str = null;
        Map<String, Object> map = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("subject")) {
                    String languageAttribute2 = getLanguageAttribute(xmlPullParser);
                    if (languageAttribute2 == null) {
                        languageAttribute2 = languageAttribute;
                    }
                    String parseContent = parseContent(xmlPullParser);
                    if (message.getSubject(languageAttribute2) == null) {
                        message.addSubject(languageAttribute2, parseContent);
                    }
                } else if (name.equals("body")) {
                    String languageAttribute3 = getLanguageAttribute(xmlPullParser);
                    if (languageAttribute3 == null) {
                        languageAttribute3 = languageAttribute;
                    }
                    String parseContent2 = parseContent(xmlPullParser);
                    if (message.getBody(languageAttribute3) == null) {
                        message.addBody(languageAttribute3, parseContent2);
                    }
                } else if (name.equals("thread")) {
                    if (str == null) {
                        str = xmlPullParser.nextText();
                    }
                } else if (name.equals("error")) {
                    message.setError(parseError(xmlPullParser));
                } else if (!name.equals("properties") || !namespace.equals(PROPERTIES_NAMESPACE)) {
                    message.addExtension(parsePacketExtension(name, namespace, xmlPullParser));
                } else {
                    map = parseProperties(xmlPullParser);
                }
            } else if (next == 3 && xmlPullParser.getName().equals(CommonUtilities.EXTRA_MESSAGE)) {
                z = true;
            }
        }
        message.setThread(str);
        if (map != null) {
            for (String next2 : map.keySet()) {
                message.setProperty(next2, map.get(next2));
            }
        }
        return message;
    }

    private static String parseContent(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        String str = "";
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return str;
            }
            str = str + xmlPullParser.getText();
        }
    }

    public static Presence parsePresence(XmlPullParser xmlPullParser) throws Exception {
        Presence.Type type = Presence.Type.available;
        String attributeValue = xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE);
        if (attributeValue != null && !attributeValue.equals("")) {
            try {
                type = Presence.Type.valueOf(attributeValue);
            } catch (IllegalArgumentException unused) {
                PrintStream printStream = System.err;
                printStream.println("Found invalid presence type " + attributeValue);
            }
        }
        Presence presence = new Presence(type);
        presence.setTo(xmlPullParser.getAttributeValue("", "to"));
        presence.setFrom(xmlPullParser.getAttributeValue("", PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM));
        String attributeValue2 = xmlPullParser.getAttributeValue("", "id");
        presence.setPacketID(attributeValue2 == null ? Packet.ID_NOT_AVAILABLE : attributeValue2);
        String languageAttribute = getLanguageAttribute(xmlPullParser);
        if (languageAttribute != null && !"".equals(languageAttribute.trim())) {
            presence.setLanguage(languageAttribute);
        }
        if (attributeValue2 == null) {
            attributeValue2 = Packet.ID_NOT_AVAILABLE;
        }
        presence.setPacketID(attributeValue2);
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("status")) {
                    presence.setStatus(xmlPullParser.nextText());
                } else if (name.equals("priority")) {
                    try {
                        presence.setPriority(Integer.parseInt(xmlPullParser.nextText()));
                    } catch (NumberFormatException unused2) {
                    } catch (IllegalArgumentException unused3) {
                        presence.setPriority(0);
                    }
                } else if (name.equals("show")) {
                    String nextText = xmlPullParser.nextText();
                    try {
                        presence.setMode(Presence.Mode.valueOf(nextText));
                    } catch (IllegalArgumentException unused4) {
                        PrintStream printStream2 = System.err;
                        printStream2.println("Found invalid presence mode " + nextText);
                    }
                } else if (name.equals("error")) {
                    presence.setError(parseError(xmlPullParser));
                } else if (!name.equals("properties") || !namespace.equals(PROPERTIES_NAMESPACE)) {
                    presence.addExtension(parsePacketExtension(name, namespace, xmlPullParser));
                } else {
                    Map<String, Object> parseProperties = parseProperties(xmlPullParser);
                    for (String next2 : parseProperties.keySet()) {
                        presence.setProperty(next2, parseProperties.get(next2));
                    }
                }
            } else if (next == 3 && xmlPullParser.getName().equals("presence")) {
                z = true;
            }
        }
        return presence;
    }

    public static IQ parseIQ(XmlPullParser xmlPullParser, Connection connection) throws Exception {
        String attributeValue = xmlPullParser.getAttributeValue("", "id");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "to");
        String attributeValue3 = xmlPullParser.getAttributeValue("", PrivacyItem.PrivacyRule.SUBSCRIPTION_FROM);
        IQ.Type fromString = IQ.Type.fromString(xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE));
        boolean z = false;
        IQ iq = null;
        XMPPError xMPPError = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("error")) {
                    xMPPError = parseError(xmlPullParser);
                } else if (name.equals(SearchIntents.EXTRA_QUERY) && namespace.equals("jabber:iq:auth")) {
                    iq = parseAuthentication(xmlPullParser);
                } else if (name.equals(SearchIntents.EXTRA_QUERY) && namespace.equals("jabber:iq:roster")) {
                    iq = parseRoster(xmlPullParser);
                } else if (name.equals(SearchIntents.EXTRA_QUERY) && namespace.equals("jabber:iq:register")) {
                    iq = parseRegistration(xmlPullParser);
                } else if (!name.equals("bind") || !namespace.equals("urn:ietf:params:xml:ns:xmpp-bind")) {
                    Object iQProvider = ProviderManager.getInstance().getIQProvider(name, namespace);
                    if (iQProvider != null) {
                        if (iQProvider instanceof IQProvider) {
                            iq = ((IQProvider) iQProvider).parseIQ(xmlPullParser);
                        } else if (iQProvider instanceof Class) {
                            iq = (IQ) parseWithIntrospection(name, (Class) iQProvider, xmlPullParser);
                        }
                    }
                } else {
                    iq = parseResourceBinding(xmlPullParser);
                }
            } else if (next == 3 && xmlPullParser.getName().equals("iq")) {
                z = true;
            }
        }
        if (iq == null) {
            if (IQ.Type.GET == fromString || IQ.Type.SET == fromString) {
                IQ errorIq = new IQ() {
                    public String getChildElementXML() {
                        return null;
                    }
                };
                errorIq.setPacketID(attributeValue);
                errorIq.setTo(attributeValue3);
                errorIq.setFrom(attributeValue2);
                errorIq.setType(IQ.Type.ERROR);
                errorIq.setError(new XMPPError(XMPPError.Condition.feature_not_implemented));
                connection.sendPacket(errorIq);
                return null;
            }
            iq = new IQ() {
                public String getChildElementXML() {
                    return null;
                }
            };
        }
        iq.setPacketID(attributeValue);
        iq.setTo(attributeValue2);
        iq.setFrom(attributeValue3);
        iq.setType(fromString);
        iq.setError(xMPPError);
        return iq;
    }

    private static Authentication parseAuthentication(XmlPullParser xmlPullParser) throws Exception {
        Authentication authentication = new Authentication();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("username")) {
                    authentication.setUsername(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("password")) {
                    authentication.setPassword(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("digest")) {
                    authentication.setDigest(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("resource")) {
                    authentication.setResource(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                z = true;
            }
        }
        return authentication;
    }

    private static RosterPacket parseRoster(XmlPullParser xmlPullParser) throws Exception {
        String nextText;
        RosterPacket rosterPacket = new RosterPacket();
        boolean z = false;
        RosterPacket.Item item = null;
        while (!z) {
            if (xmlPullParser.getEventType() == 2 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                rosterPacket.setVersion(xmlPullParser.getAttributeValue((String) null, "ver"));
            }
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("item")) {
                    RosterPacket.Item item2 = new RosterPacket.Item(xmlPullParser.getAttributeValue("", "jid"), xmlPullParser.getAttributeValue("", "name"));
                    item2.setItemStatus(RosterPacket.ItemStatus.fromString(xmlPullParser.getAttributeValue("", "ask")));
                    String attributeValue = xmlPullParser.getAttributeValue("", "subscription");
                    if (attributeValue == null) {
                        attributeValue = PrivacyItem.PrivacyRule.SUBSCRIPTION_NONE;
                    }
                    item2.setItemType(RosterPacket.ItemType.valueOf(attributeValue));
                    item = item2;
                }
                if (xmlPullParser.getName().equals("group") && item != null && (nextText = xmlPullParser.nextText()) != null && nextText.trim().length() > 0) {
                    item.addGroupName(nextText);
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("item")) {
                    rosterPacket.addRosterItem(item);
                }
                if (xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                    z = true;
                }
            }
        }
        return rosterPacket;
    }

    private static Registration parseRegistration(XmlPullParser xmlPullParser) throws Exception {
        Registration registration = new Registration();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getNamespace().equals("jabber:iq:register")) {
                    String name = xmlPullParser.getName();
                    if (xmlPullParser.next() == 4) {
                        String text = xmlPullParser.getText();
                        if (name.equals("instructions")) {
                            registration.setInstructions(text);
                        } else {
                            registration.addAttribute(name, text);
                        }
                    } else if (name.equals("registered")) {
                        registration.setRegistered(true);
                    } else {
                        registration.addRequiredField(name);
                    }
                } else {
                    registration.addExtension(parsePacketExtension(xmlPullParser.getName(), xmlPullParser.getNamespace(), xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                z = true;
            }
        }
        return registration;
    }

    private static Bind parseResourceBinding(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        Bind bind = new Bind();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("resource")) {
                    bind.setResource(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals("jid")) {
                    bind.setJid(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("bind")) {
                z = true;
            }
        }
        return bind;
    }

    public static Collection<String> parseMechanisms(XmlPullParser xmlPullParser) throws Exception {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("mechanism")) {
                    arrayList.add(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("mechanisms")) {
                z = true;
            }
        }
        return arrayList;
    }

    public static Collection<String> parseCompressionMethods(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(FirebaseAnalytics.Param.METHOD)) {
                    arrayList.add(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("compression")) {
                z = true;
            }
        }
        return arrayList;
    }

    public static Map<String, Object> parseProperties(XmlPullParser xmlPullParser) throws Exception {
        Object readObject = null;
        HashMap hashMap = new HashMap();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && xmlPullParser.getName().equals("property")) {
                boolean z = false;
                String str = null;
                String str2 = null;
                String str3 = null;
                Object obj = null;
                while (!z) {
                    int next2 = xmlPullParser.next();
                    if (next2 == 2) {
                        String name = xmlPullParser.getName();
                        if (name.equals("name")) {
                            str3 = xmlPullParser.nextText();
                        } else if (name.equals(FirebaseAnalytics.Param.VALUE)) {
                            str = xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE);
                            str2 = xmlPullParser.nextText();
                        }
                    } else if (next2 == 3 && xmlPullParser.getName().equals("property")) {
                        if ("integer".equals(str)) {
                            readObject = Integer.valueOf(str2);
                        } else if ("long".equals(str)) {
                            readObject = Long.valueOf(str2);
                        } else if ("float".equals(str)) {
                            readObject = Float.valueOf(str2);
                        } else if ("double".equals(str)) {
                            readObject = Double.valueOf(str2);
                        } else if (FormField.TYPE_BOOLEAN.equals(str)) {
                            readObject = Boolean.valueOf(str2);
                        } else {
                            if ("string".equals(str)) {
                                obj = str2;
                            } else if ("java-object".equals(str)) {
                                try {
                                    readObject = new ObjectInputStream(new ByteArrayInputStream(StringUtils.decodeBase64(str2))).readObject();
                                    obj = readObject;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            readObject = obj;
                            if (!(str3 == null || obj == null)) {
                                hashMap.put(str3, obj);
                            }
                            z = true;
                        }
                        obj = readObject;
                        hashMap.put(str3, obj);
                        z = true;
                    }
                }
            } else if (next == 3 && xmlPullParser.getName().equals("properties")) {
                return hashMap;
            }
        }
    }

    public static SASLMechanism.Failure parseSASLFailure(XmlPullParser xmlPullParser) throws Exception {
        String str = null;
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (!xmlPullParser.getName().equals("failure")) {
                    str = xmlPullParser.getName();
                }
            } else if (next == 3 && xmlPullParser.getName().equals("failure")) {
                z = true;
            }
        }
        return new SASLMechanism.Failure(str);
    }

    public static StreamError parseStreamError(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        StreamError streamError = null;
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                streamError = new StreamError(xmlPullParser.getName());
            } else if (next == 3 && xmlPullParser.getName().equals("error")) {
                z = true;
            }
        }
        return streamError;
    }

    public static XMPPError parseError(XmlPullParser xmlPullParser) throws Exception {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        String str = "-1";
        String str2 = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equals("code")) {
                str = xmlPullParser.getAttributeValue("", "code");
            }
            if (xmlPullParser.getAttributeName(i).equals(AppMeasurement.Param.TYPE)) {
                str2 = xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE);
            }
        }
        String str3 = null;
        String str4 = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("text")) {
                    str4 = xmlPullParser.nextText();
                } else {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(namespace)) {
                        str3 = name;
                    } else {
                        arrayList.add(parsePacketExtension(name, namespace, xmlPullParser));
                    }
                }
            } else if (next == 3 && xmlPullParser.getName().equals("error")) {
                z = true;
            }
        }
        XMPPError.Type type = XMPPError.Type.CANCEL;
        if (str2 != null) {
            try {
                type = XMPPError.Type.valueOf(str2.toUpperCase());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return new XMPPError(Integer.parseInt(str), type, str3, str4, arrayList);
    }

    public static PacketExtension parsePacketExtension(String str, String str2, XmlPullParser xmlPullParser) throws Exception {
        Object extensionProvider = ProviderManager.getInstance().getExtensionProvider(str, str2);
        if (extensionProvider != null) {
            if (extensionProvider instanceof PacketExtensionProvider) {
                return ((PacketExtensionProvider) extensionProvider).parseExtension(xmlPullParser);
            }
            if (extensionProvider instanceof Class) {
                return (PacketExtension) parseWithIntrospection(str, (Class) extensionProvider, xmlPullParser);
            }
        }
        DefaultPacketExtension defaultPacketExtension = new DefaultPacketExtension(str, str2);
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                if (xmlPullParser.isEmptyElementTag()) {
                    defaultPacketExtension.setValue(name, "");
                } else if (xmlPullParser.next() == 4) {
                    defaultPacketExtension.setValue(name, xmlPullParser.getText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals(str)) {
                z = true;
            }
        }
        return defaultPacketExtension;
    }

    private static String getLanguageAttribute(XmlPullParser xmlPullParser) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            if ("xml:lang".equals(attributeName) || ("lang".equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i)))) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }

    public static Object parseWithIntrospection(String str, Class cls, XmlPullParser xmlPullParser) throws Exception {
        Object newInstance = cls.newInstance();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String nextText = xmlPullParser.nextText();
                Class<?> cls2 = newInstance.getClass();
                Class<?> returnType = cls2.getMethod("get" + Character.toUpperCase(name.charAt(0)) + name.substring(1), new Class[0]).getReturnType();
                Object decode = decode(returnType, nextText);
                Class<?> cls3 = newInstance.getClass();
                cls3.getMethod("set" + Character.toUpperCase(name.charAt(0)) + name.substring(1), new Class[]{returnType}).invoke(newInstance, new Object[]{decode});
            } else if (next == 3 && xmlPullParser.getName().equals(str)) {
                z = true;
            }
        }
        return newInstance;
    }

    private static Object decode(Class cls, String str) throws Exception {
        if (cls.getName().equals("java.lang.String")) {
            return str;
        }
        if (cls.getName().equals(FormField.TYPE_BOOLEAN)) {
            return Boolean.valueOf(str);
        }
        if (cls.getName().equals("int")) {
            return Integer.valueOf(str);
        }
        if (cls.getName().equals("long")) {
            return Long.valueOf(str);
        }
        if (cls.getName().equals("float")) {
            return Float.valueOf(str);
        }
        if (cls.getName().equals("double")) {
            return Double.valueOf(str);
        }
        if (cls.getName().equals("java.lang.Class")) {
            return Class.forName(str);
        }
        return null;
    }
}
