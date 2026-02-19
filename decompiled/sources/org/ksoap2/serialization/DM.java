package org.ksoap2.serialization;

import java.io.IOException;
import org.jivesoftware.smackx.FormField;
import org.ksoap2.SoapEnvelope;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

class DM implements Marshal {
    DM() {
    }

    public Object readInstance(XmlPullParser xmlPullParser, String str, String str2, PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        String nextText = xmlPullParser.nextText();
        char charAt = str2.charAt(0);
        if (charAt == 'b') {
            return new Boolean(SoapEnvelope.stringToBoolean(nextText));
        }
        if (charAt == 'i') {
            return new Integer(Integer.parseInt(nextText));
        }
        if (charAt == 'l') {
            return new Long(Long.parseLong(nextText));
        }
        if (charAt == 's') {
            return nextText;
        }
        throw new RuntimeException();
    }

    public void writeInstance(XmlSerializer xmlSerializer, Object obj) throws IOException {
        if (obj instanceof AttributeContainer) {
            AttributeContainer attributeContainer = (AttributeContainer) obj;
            int attributeCount = attributeContainer.getAttributeCount();
            for (int i = 0; i < attributeCount; i++) {
                AttributeInfo attributeInfo = new AttributeInfo();
                attributeContainer.getAttributeInfo(i, attributeInfo);
                xmlSerializer.attribute(attributeInfo.getNamespace(), attributeInfo.getName(), attributeInfo.getValue().toString());
            }
        }
        xmlSerializer.text(obj.toString());
    }

    public void register(SoapSerializationEnvelope soapSerializationEnvelope) {
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "int", PropertyInfo.INTEGER_CLASS, this);
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "long", PropertyInfo.LONG_CLASS, this);
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "string", PropertyInfo.STRING_CLASS, this);
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, FormField.TYPE_BOOLEAN, PropertyInfo.BOOLEAN_CLASS, this);
    }
}
