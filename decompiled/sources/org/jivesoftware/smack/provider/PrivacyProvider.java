package org.jivesoftware.smack.provider;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.streamax.client.CommonUtilities;
import java.util.ArrayList;
import org.jivesoftware.smack.packet.DefaultPacketExtension;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Privacy;
import org.jivesoftware.smack.packet.PrivacyItem;
import org.xmlpull.v1.XmlPullParser;

public class PrivacyProvider implements IQProvider {
    public IQ parseIQ(XmlPullParser xmlPullParser) throws Exception {
        Privacy privacy = new Privacy();
        privacy.addExtension(new DefaultPacketExtension(xmlPullParser.getName(), xmlPullParser.getNamespace()));
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("active")) {
                    String attributeValue = xmlPullParser.getAttributeValue("", "name");
                    if (attributeValue == null) {
                        privacy.setDeclineActiveList(true);
                    } else {
                        privacy.setActiveName(attributeValue);
                    }
                } else if (xmlPullParser.getName().equals("default")) {
                    String attributeValue2 = xmlPullParser.getAttributeValue("", "name");
                    if (attributeValue2 == null) {
                        privacy.setDeclineDefaultList(true);
                    } else {
                        privacy.setDefaultName(attributeValue2);
                    }
                } else if (xmlPullParser.getName().equals("list")) {
                    parseList(xmlPullParser, privacy);
                }
            } else if (next == 3 && xmlPullParser.getName().equals(SearchIntents.EXTRA_QUERY)) {
                z = true;
            }
        }
        return privacy;
    }

    public void parseList(XmlPullParser xmlPullParser, Privacy privacy) throws Exception {
        String attributeValue = xmlPullParser.getAttributeValue("", "name");
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("item")) {
                    arrayList.add(parseItem(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("list")) {
                z = true;
            }
        }
        privacy.setPrivacyList(attributeValue, arrayList);
    }

    public PrivacyItem parseItem(XmlPullParser xmlPullParser) throws Exception {
        String attributeValue = xmlPullParser.getAttributeValue("", "action");
        boolean z = false;
        PrivacyItem privacyItem = new PrivacyItem(xmlPullParser.getAttributeValue("", AppMeasurement.Param.TYPE), "allow".equalsIgnoreCase(attributeValue) || !"deny".equalsIgnoreCase(attributeValue), Integer.parseInt(xmlPullParser.getAttributeValue("", "order")));
        privacyItem.setValue(xmlPullParser.getAttributeValue("", FirebaseAnalytics.Param.VALUE));
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("iq")) {
                    privacyItem.setFilterIQ(true);
                }
                if (xmlPullParser.getName().equals(CommonUtilities.EXTRA_MESSAGE)) {
                    privacyItem.setFilterMessage(true);
                }
                if (xmlPullParser.getName().equals("presence-in")) {
                    privacyItem.setFilterPresence_in(true);
                }
                if (xmlPullParser.getName().equals("presence-out")) {
                    privacyItem.setFilterPresence_out(true);
                }
            } else if (next == 3 && xmlPullParser.getName().equals("item")) {
                z = true;
            }
        }
        return privacyItem;
    }
}
