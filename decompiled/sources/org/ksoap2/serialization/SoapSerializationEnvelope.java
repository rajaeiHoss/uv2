package org.ksoap2.serialization;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.SoapFault12;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class SoapSerializationEnvelope extends SoapEnvelope {
    private static final String ANY_TYPE_LABEL = "anyType";
    private static final String ARRAY_MAPPING_NAME = "Array";
    private static final String ARRAY_TYPE_LABEL = "arrayType";
    static final Marshal DEFAULT_MARSHAL = new DM();
    private static final String HREF_LABEL = "href";
    private static final String ID_LABEL = "id";
    private static final String ITEM_LABEL = "item";
    private static final String NIL_LABEL = "nil";
    private static final String NULL_LABEL = "null";
    protected static final int QNAME_MARSHAL = 3;
    protected static final int QNAME_NAMESPACE = 0;
    protected static final int QNAME_TYPE = 1;
    private static final String ROOT_LABEL = "root";
    private static final String TYPE_LABEL = "type";
    static /* synthetic */ Class class$org$ksoap2$serialization$SoapObject;
    protected boolean addAdornments = true;
    public boolean avoidExceptionForUnknownProperty;
    protected Hashtable classToQName = new Hashtable();
    public boolean dotNet;
    Hashtable idMap = new Hashtable();
    public boolean implicitTypes;
    Vector multiRef;
    public Hashtable properties = new Hashtable();
    protected Hashtable qNameToClass = new Hashtable();

    public SoapSerializationEnvelope(int i) {
        super(i);
        addMapping(this.enc, ARRAY_MAPPING_NAME, PropertyInfo.VECTOR_CLASS);
        DEFAULT_MARSHAL.register(this);
    }

    public boolean isAddAdornments() {
        return this.addAdornments;
    }

    public void setAddAdornments(boolean z) {
        this.addAdornments = z;
    }

    public void setBodyOutEmpty(boolean z) {
        if (z) {
            this.bodyOut = null;
        }
    }

    public void parseBody(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        SoapFault soapFault;
        this.bodyIn = null;
        xmlPullParser.nextTag();
        if (xmlPullParser.getEventType() != 2 || !xmlPullParser.getNamespace().equals(this.env) || !xmlPullParser.getName().equals("Fault")) {
            while (xmlPullParser.getEventType() == 2) {
                String attributeValue = xmlPullParser.getAttributeValue(this.enc, ROOT_LABEL);
                Object read = read(xmlPullParser, (Object) null, -1, xmlPullParser.getNamespace(), xmlPullParser.getName(), PropertyInfo.OBJECT_TYPE);
                if ("1".equals(attributeValue) || this.bodyIn == null) {
                    this.bodyIn = read;
                }
                xmlPullParser.nextTag();
            }
            return;
        }
        if (this.version < 120) {
            soapFault = new SoapFault(this.version);
        } else {
            soapFault = new SoapFault12(this.version);
        }
        soapFault.parse(xmlPullParser);
        this.bodyIn = soapFault;
    }

    /* access modifiers changed from: protected */
    public void readSerializable(XmlPullParser xmlPullParser, SoapObject soapObject) throws IOException, XmlPullParserException {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            soapObject.addAttribute(xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
        }
        readSerializable(xmlPullParser, (KvmSerializable) soapObject);
    }

    /* access modifiers changed from: protected */
    public void readSerializable(XmlPullParser xmlPullParser, KvmSerializable kvmSerializable) throws IOException, XmlPullParserException {
        KvmSerializable kvmSerializable2 = kvmSerializable;
        while (xmlPullParser.nextTag() != 3) {
            String name = xmlPullParser.getName();
            if (!this.implicitTypes || !(kvmSerializable2 instanceof SoapObject)) {
                PropertyInfo propertyInfo = new PropertyInfo();
                int propertyCount = kvmSerializable.getPropertyCount();
                boolean z = false;
                for (int i = 0; i < propertyCount && !z; i++) {
                    propertyInfo.clear();
                    kvmSerializable2.getPropertyInfo(i, this.properties, propertyInfo);
                    if ((name.equals(propertyInfo.name) && propertyInfo.namespace == null) || (name.equals(propertyInfo.name) && xmlPullParser.getNamespace().equals(propertyInfo.namespace))) {
                        kvmSerializable2.setProperty(i, read(xmlPullParser, kvmSerializable, i, (String) null, (String) null, propertyInfo));
                        z = true;
                    }
                }
                if (z) {
                    continue;
                } else if (this.avoidExceptionForUnknownProperty) {
                    while (true) {
                        if (xmlPullParser.next() == 3 && name.equals(xmlPullParser.getName())) {
                            break;
                        }
                    }
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unknown Property: ");
                    stringBuffer.append(name);
                    throw new RuntimeException(stringBuffer.toString());
                }
            } else {
                SoapObject soapObject = (SoapObject) kvmSerializable2;
                soapObject.addProperty(xmlPullParser.getName(), read(xmlPullParser, kvmSerializable, kvmSerializable.getPropertyCount(), soapObject.getNamespace(), name, PropertyInfo.OBJECT_TYPE));
            }
        }
        XmlPullParser xmlPullParser2 = xmlPullParser;
        xmlPullParser.require(3, (String) null, (String) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: org.ksoap2.serialization.SoapObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: org.ksoap2.serialization.SoapObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: org.ksoap2.serialization.SoapPrimitive} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: org.ksoap2.serialization.SoapObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: org.ksoap2.serialization.SoapObject} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object readUnknown(org.xmlpull.v1.XmlPullParser r14, java.lang.String r15, java.lang.String r16) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
            r13 = this;
            r7 = r14
            r0 = r15
            r1 = r16
            java.lang.String r8 = r14.getName()
            java.lang.String r9 = r14.getNamespace()
            java.util.Vector r2 = new java.util.Vector
            r2.<init>()
            r3 = 0
            r4 = 0
        L_0x0013:
            int r5 = r14.getAttributeCount()
            if (r4 >= r5) goto L_0x0040
            org.ksoap2.serialization.AttributeInfo r5 = new org.ksoap2.serialization.AttributeInfo
            r5.<init>()
            java.lang.String r6 = r14.getAttributeName(r4)
            r5.setName(r6)
            java.lang.String r6 = r14.getAttributeValue(r4)
            r5.setValue(r6)
            java.lang.String r6 = r14.getAttributeNamespace(r4)
            r5.setNamespace(r6)
            java.lang.String r6 = r14.getAttributeType(r4)
            r5.setType(r6)
            r2.addElement(r5)
            int r4 = r4 + 1
            goto L_0x0013
        L_0x0040:
            r14.next()
            int r4 = r14.getEventType()
            r5 = 4
            r6 = 0
            r10 = 3
            if (r4 != r5) goto L_0x006c
            java.lang.String r6 = r14.getText()
            org.ksoap2.serialization.SoapPrimitive r4 = new org.ksoap2.serialization.SoapPrimitive
            r4.<init>(r15, r1, r6)
            r5 = 0
        L_0x0056:
            int r11 = r2.size()
            if (r5 >= r11) goto L_0x0068
            java.lang.Object r11 = r2.elementAt(r5)
            org.ksoap2.serialization.AttributeInfo r11 = (org.ksoap2.serialization.AttributeInfo) r11
            r4.addAttribute(r11)
            int r5 = r5 + 1
            goto L_0x0056
        L_0x0068:
            r14.next()
            goto L_0x008b
        L_0x006c:
            int r4 = r14.getEventType()
            if (r4 != r10) goto L_0x008a
            org.ksoap2.serialization.SoapObject r4 = new org.ksoap2.serialization.SoapObject
            r4.<init>(r15, r1)
            r5 = 0
        L_0x0078:
            int r11 = r2.size()
            if (r5 >= r11) goto L_0x008b
            java.lang.Object r11 = r2.elementAt(r5)
            org.ksoap2.serialization.AttributeInfo r11 = (org.ksoap2.serialization.AttributeInfo) r11
            r4.addAttribute(r11)
            int r5 = r5 + 1
            goto L_0x0078
        L_0x008a:
            r4 = r6
        L_0x008b:
            int r5 = r14.getEventType()
            r11 = 2
            if (r5 != r11) goto L_0x00df
            if (r6 == 0) goto L_0x00a7
            java.lang.String r4 = r6.trim()
            int r4 = r4.length()
            if (r4 != 0) goto L_0x009f
            goto L_0x00a7
        L_0x009f:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Malformed input: Mixed content"
            r0.<init>(r1)
            throw r0
        L_0x00a7:
            org.ksoap2.serialization.SoapObject r11 = new org.ksoap2.serialization.SoapObject
            r11.<init>(r15, r1)
        L_0x00ac:
            int r0 = r2.size()
            if (r3 >= r0) goto L_0x00be
            java.lang.Object r0 = r2.elementAt(r3)
            org.ksoap2.serialization.AttributeInfo r0 = (org.ksoap2.serialization.AttributeInfo) r0
            r11.addAttribute(r0)
            int r3 = r3 + 1
            goto L_0x00ac
        L_0x00be:
            int r0 = r14.getEventType()
            if (r0 == r10) goto L_0x00de
            java.lang.String r12 = r14.getName()
            int r3 = r11.getPropertyCount()
            r4 = 0
            r5 = 0
            org.ksoap2.serialization.PropertyInfo r6 = org.ksoap2.serialization.PropertyInfo.OBJECT_TYPE
            r0 = r13
            r1 = r14
            r2 = r11
            java.lang.Object r0 = r0.read(r1, r2, r3, r4, r5, r6)
            r11.addProperty(r12, r0)
            r14.nextTag()
            goto L_0x00be
        L_0x00de:
            r4 = r11
        L_0x00df:
            r14.require(r10, r9, r8)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ksoap2.serialization.SoapSerializationEnvelope.readUnknown(org.xmlpull.v1.XmlPullParser, java.lang.String, java.lang.String):java.lang.Object");
    }

    private int getIndex(String str, int i, int i2) {
        return (str != null && str.length() - i >= 3) ? Integer.parseInt(str.substring(i + 1, str.length() - 1)) : i2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readVector(org.xmlpull.v1.XmlPullParser r18, java.util.Vector r19, org.ksoap2.serialization.PropertyInfo r20) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
            r17 = this;
            r7 = r17
            r8 = r18
            r9 = r19
            int r0 = r19.size()
            java.lang.String r1 = r7.enc
            java.lang.String r2 = "arrayType"
            java.lang.String r1 = r8.getAttributeValue(r1, r2)
            r2 = 1
            r10 = 0
            r11 = 0
            if (r1 == 0) goto L_0x0049
            r0 = 58
            int r0 = r1.indexOf(r0)
            java.lang.String r3 = "["
            int r3 = r1.indexOf(r3, r0)
            int r4 = r0 + 1
            java.lang.String r4 = r1.substring(r4, r3)
            r5 = -1
            if (r0 != r5) goto L_0x002f
            java.lang.String r0 = ""
            goto L_0x0033
        L_0x002f:
            java.lang.String r0 = r1.substring(r11, r0)
        L_0x0033:
            java.lang.String r0 = r8.getNamespace(r0)
            int r1 = r7.getIndex(r1, r3, r5)
            if (r1 == r5) goto L_0x0045
            r9.setSize(r1)
            r12 = r0
            r0 = r1
            r14 = r4
            r13 = 0
            goto L_0x004c
        L_0x0045:
            r12 = r0
            r0 = r1
            r14 = r4
            goto L_0x004b
        L_0x0049:
            r12 = r10
            r14 = r12
        L_0x004b:
            r13 = 1
        L_0x004c:
            if (r20 != 0) goto L_0x0052
            org.ksoap2.serialization.PropertyInfo r1 = org.ksoap2.serialization.PropertyInfo.OBJECT_TYPE
            r15 = r1
            goto L_0x0054
        L_0x0052:
            r15 = r20
        L_0x0054:
            r18.nextTag()
            java.lang.String r1 = r7.enc
            java.lang.String r2 = "offset"
            java.lang.String r1 = r8.getAttributeValue(r1, r2)
            int r1 = r7.getIndex(r1, r11, r11)
        L_0x0063:
            int r2 = r18.getEventType()
            r3 = 3
            if (r2 == r3) goto L_0x009c
            java.lang.String r2 = r7.enc
            java.lang.String r3 = "position"
            java.lang.String r2 = r8.getAttributeValue(r2, r3)
            int r6 = r7.getIndex(r2, r11, r1)
            if (r13 == 0) goto L_0x007f
            if (r6 < r0) goto L_0x007f
            int r0 = r6 + 1
            r9.setSize(r0)
        L_0x007f:
            r16 = r0
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r6
            r4 = r12
            r5 = r14
            r11 = r6
            r6 = r15
            java.lang.Object r0 = r0.read(r1, r2, r3, r4, r5, r6)
            r9.setElementAt(r0, r11)
            int r1 = r11 + 1
            r18.nextTag()
            r0 = r16
            r11 = 0
            goto L_0x0063
        L_0x009c:
            r8.require(r3, r10, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ksoap2.serialization.SoapSerializationEnvelope.readVector(org.xmlpull.v1.XmlPullParser, java.util.Vector, org.ksoap2.serialization.PropertyInfo):void");
    }

    public Object read(XmlPullParser xmlPullParser, Object obj, int i, String str, String str2, PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        Object obj2;
        Object obj3;
        String str3;
        String name = xmlPullParser.getName();
        String attributeValue = xmlPullParser.getAttributeValue((String) null, HREF_LABEL);
        if (attributeValue == null) {
            String attributeValue2 = xmlPullParser.getAttributeValue(this.xsi, NIL_LABEL);
            String attributeValue3 = xmlPullParser.getAttributeValue((String) null, ID_LABEL);
            if (attributeValue2 == null) {
                attributeValue2 = xmlPullParser.getAttributeValue(this.xsi, NULL_LABEL);
            }
            if (attributeValue2 == null || !SoapEnvelope.stringToBoolean(attributeValue2)) {
                String attributeValue4 = xmlPullParser.getAttributeValue(this.xsi, "type");
                if (attributeValue4 != null) {
                    int indexOf = attributeValue4.indexOf(58);
                    str2 = attributeValue4.substring(indexOf + 1);
                    if (indexOf == -1) {
                        str3 = "";
                    } else {
                        str3 = attributeValue4.substring(0, indexOf);
                    }
                    str = xmlPullParser.getNamespace(str3);
                } else if (str2 == null && str == null) {
                    if (xmlPullParser.getAttributeValue(this.enc, ARRAY_TYPE_LABEL) != null) {
                        str = this.enc;
                        str2 = ARRAY_MAPPING_NAME;
                    } else {
                        Object[] info = getInfo(propertyInfo.type, (Object) null);
                        String str4 = (String) info[0];
                        str2 = (String) info[1];
                        str = str4;
                    }
                }
                if (attributeValue4 == null) {
                    this.implicitTypes = true;
                }
                Object readInstance = readInstance(xmlPullParser, str, str2, propertyInfo);
                if (readInstance == null) {
                    readInstance = readUnknown(xmlPullParser, str, str2);
                }
                obj3 = readInstance;
            } else {
                xmlPullParser.nextTag();
                xmlPullParser.require(3, (String) null, name);
                obj3 = null;
            }
            obj2 = obj3;
            if (attributeValue3 != null) {
                Object obj4 = this.idMap.get(attributeValue3);
                if (obj4 instanceof FwdRef) {
                    FwdRef fwdRef = (FwdRef) obj4;
                    do {
                        if (fwdRef.obj instanceof KvmSerializable) {
                            ((KvmSerializable) fwdRef.obj).setProperty(fwdRef.index, obj2);
                        } else {
                            ((Vector) fwdRef.obj).setElementAt(obj2, fwdRef.index);
                        }
                        fwdRef = fwdRef.next;
                    } while (fwdRef != null);
                } else if (obj4 == null) {
                    this.idMap.put(attributeValue3, obj2);
                } else {
                    throw new RuntimeException("double ID");
                }
                this.idMap.put(attributeValue3, obj2);
            }
        } else if (obj != null) {
            String substring = attributeValue.substring(1);
            obj2 = this.idMap.get(substring);
            if (obj2 == null || (obj2 instanceof FwdRef)) {
                FwdRef fwdRef2 = new FwdRef();
                fwdRef2.next = (FwdRef) obj2;
                fwdRef2.obj = obj;
                fwdRef2.index = i;
                this.idMap.put(substring, fwdRef2);
                obj2 = null;
            }
            xmlPullParser.nextTag();
            xmlPullParser.require(3, (String) null, name);
        } else {
            throw new RuntimeException("href at root level?!?");
        }
        xmlPullParser.require(3, (String) null, name);
        return obj2;
    }

    public Object readInstance(XmlPullParser xmlPullParser, String str, String str2, PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        Object obj;
        Object obj2 = this.qNameToClass.get(new SoapPrimitive(str, str2, (String) null));
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Marshal) {
            return ((Marshal) obj2).readInstance(xmlPullParser, str, str2, propertyInfo);
        }
        if (obj2 instanceof SoapObject) {
            obj = ((SoapObject) obj2).newInstance();
        } else {
            Class cls = class$org$ksoap2$serialization$SoapObject;
            if (cls == null) {
                cls = class$("org.ksoap2.serialization.SoapObject");
                class$org$ksoap2$serialization$SoapObject = cls;
            }
            if (obj2 == cls) {
                obj = new SoapObject(str, str2);
            } else {
                try {
                    obj = ((Class) obj2).newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e.toString());
                }
            }
        }
        if (obj instanceof SoapObject) {
            readSerializable(xmlPullParser, (SoapObject) obj);
        } else if (obj instanceof KvmSerializable) {
            readSerializable(xmlPullParser, (KvmSerializable) obj);
        } else if (obj instanceof Vector) {
            readVector(xmlPullParser, (Vector) obj, propertyInfo.elementType);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("no deserializer for ");
            stringBuffer.append(obj.getClass());
            throw new RuntimeException(stringBuffer.toString());
        }
        return obj;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Object[] getInfo(Object obj, Object obj2) {
        Object[] objArr;
        if (obj == null) {
            obj = ((obj2 instanceof SoapObject) || (obj2 instanceof SoapPrimitive)) ? obj2 : obj2.getClass();
        }
        if (obj instanceof SoapObject) {
            SoapObject soapObject = (SoapObject) obj;
            return new Object[]{soapObject.getNamespace(), soapObject.getName(), null, null};
        } else if (obj instanceof SoapPrimitive) {
            SoapPrimitive soapPrimitive = (SoapPrimitive) obj;
            return new Object[]{soapPrimitive.getNamespace(), soapPrimitive.getName(), null, DEFAULT_MARSHAL};
        } else if ((obj instanceof Class) && obj != PropertyInfo.OBJECT_CLASS && (objArr = (Object[]) this.classToQName.get(((Class) obj).getName())) != null) {
            return objArr;
        } else {
            return new Object[]{this.xsd, ANY_TYPE_LABEL, null, null};
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.Class} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Class} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Class} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addMapping(java.lang.String r5, java.lang.String r6, java.lang.Class r7, org.ksoap2.serialization.Marshal r8) {
        /*
            r4 = this;
            java.util.Hashtable r0 = r4.qNameToClass
            org.ksoap2.serialization.SoapPrimitive r1 = new org.ksoap2.serialization.SoapPrimitive
            r2 = 0
            r1.<init>(r5, r6, r2)
            if (r8 != 0) goto L_0x000c
            r3 = r7
            goto L_0x000d
        L_0x000c:
            r3 = r8
        L_0x000d:
            r0.put(r1, r3)
            java.util.Hashtable r0 = r4.classToQName
            java.lang.String r7 = r7.getName()
            r1 = 4
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r3 = 0
            r1[r3] = r5
            r5 = 1
            r1[r5] = r6
            r5 = 2
            r1[r5] = r2
            r5 = 3
            r1[r5] = r8
            r0.put(r7, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ksoap2.serialization.SoapSerializationEnvelope.addMapping(java.lang.String, java.lang.String, java.lang.Class, org.ksoap2.serialization.Marshal):void");
    }

    public void addMapping(String str, String str2, Class cls) {
        addMapping(str, str2, cls, (Marshal) null);
    }

    public void addTemplate(SoapObject soapObject) {
        this.qNameToClass.put(new SoapPrimitive(soapObject.namespace, soapObject.name, (String) null), soapObject);
    }

    public Object getResponse() throws SoapFault {
        if (!(this.bodyIn instanceof SoapFault)) {
            KvmSerializable kvmSerializable = (KvmSerializable) this.bodyIn;
            if (kvmSerializable.getPropertyCount() == 0) {
                return null;
            }
            if (kvmSerializable.getPropertyCount() == 1) {
                return kvmSerializable.getProperty(0);
            }
            Vector vector = new Vector();
            for (int i = 0; i < kvmSerializable.getPropertyCount(); i++) {
                vector.add(kvmSerializable.getProperty(i));
            }
            return vector;
        }
        throw ((SoapFault) this.bodyIn);
    }

    public void writeBody(XmlSerializer xmlSerializer) throws IOException {
        if (this.bodyOut != null) {
            Vector vector = new Vector();
            this.multiRef = vector;
            vector.addElement(this.bodyOut);
            Object[] info = getInfo((Object) null, this.bodyOut);
            String str = "";
            xmlSerializer.startTag(this.dotNet ? str : (String) info[0], (String) info[1]);
            if (this.dotNet) {
                xmlSerializer.attribute((String) null, "xmlns", (String) info[0]);
            }
            if (this.addAdornments) {
                xmlSerializer.attribute((String) null, ID_LABEL, info[2] == null ? "o0" : (String) info[2]);
                xmlSerializer.attribute(this.enc, ROOT_LABEL, "1");
            }
            writeElement(xmlSerializer, this.bodyOut, (PropertyInfo) null, info[3]);
            if (!this.dotNet) {
                str = (String) info[0];
            }
            xmlSerializer.endTag(str, (String) info[1]);
        }
    }

    public void writeObjectBody(XmlSerializer xmlSerializer, SoapObject soapObject) throws IOException {
        int attributeCount = soapObject.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            AttributeInfo attributeInfo = new AttributeInfo();
            soapObject.getAttributeInfo(i, attributeInfo);
            xmlSerializer.attribute(attributeInfo.getNamespace(), attributeInfo.getName(), attributeInfo.getValue().toString());
        }
        writeObjectBody(xmlSerializer, (KvmSerializable) soapObject);
    }

    public void writeObjectBody(XmlSerializer xmlSerializer, KvmSerializable kvmSerializable) throws IOException {
        String str;
        String str2;
        int propertyCount = kvmSerializable.getPropertyCount();
        PropertyInfo propertyInfo = new PropertyInfo();
        for (int i = 0; i < propertyCount; i++) {
            Object property = kvmSerializable.getProperty(i);
            kvmSerializable.getPropertyInfo(i, this.properties, propertyInfo);
            if (property instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) property;
                Object[] info = getInfo((Object) null, soapObject);
                String str3 = (String) info[0];
                String str4 = (String) info[1];
                if (propertyInfo.name == null || propertyInfo.name.length() <= 0) {
                    str = (String) info[1];
                } else {
                    str = propertyInfo.name;
                }
                if (propertyInfo.namespace == null || propertyInfo.namespace.length() <= 0) {
                    str2 = (String) info[0];
                } else {
                    str2 = propertyInfo.namespace;
                }
                xmlSerializer.startTag(str2, str);
                if (!this.implicitTypes) {
                    String prefix = xmlSerializer.getPrefix(str2, true);
                    String str5 = this.xsi;
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(prefix);
                    stringBuffer.append(":");
                    stringBuffer.append(str4);
                    xmlSerializer.attribute(str5, "type", stringBuffer.toString());
                }
                writeObjectBody(xmlSerializer, soapObject);
                xmlSerializer.endTag(str2, str);
            } else if ((propertyInfo.flags & 1) == 0) {
                xmlSerializer.startTag(propertyInfo.namespace, propertyInfo.name);
                writeProperty(xmlSerializer, kvmSerializable.getProperty(i), propertyInfo);
                xmlSerializer.endTag(propertyInfo.namespace, propertyInfo.name);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void writeProperty(XmlSerializer xmlSerializer, Object obj, PropertyInfo propertyInfo) throws IOException {
        StringBuffer stringBuffer;
        if (obj == null) {
            xmlSerializer.attribute(this.xsi, this.version >= 120 ? NIL_LABEL : NULL_LABEL, "true");
            return;
        }
        Object[] info = getInfo((Object) null, obj);
        if (propertyInfo.multiRef || info[2] != null) {
            int indexOf = this.multiRef.indexOf(obj);
            if (indexOf == -1) {
                indexOf = this.multiRef.size();
                this.multiRef.addElement(obj);
            }
            if (info[2] == null) {
                stringBuffer = new StringBuffer();
                stringBuffer.append("#o");
                stringBuffer.append(indexOf);
            } else {
                stringBuffer = new StringBuffer();
                stringBuffer.append("#");
                stringBuffer.append(info[2]);
            }
            xmlSerializer.attribute((String) null, HREF_LABEL, stringBuffer.toString());
            return;
        }
        if (!this.implicitTypes || obj.getClass() != propertyInfo.type) {
            String prefix = xmlSerializer.getPrefix((String) info[0], true);
            String str = this.xsi;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(prefix);
            stringBuffer2.append(":");
            stringBuffer2.append(info[1]);
            xmlSerializer.attribute(str, "type", stringBuffer2.toString());
        }
        writeElement(xmlSerializer, obj, propertyInfo, info[3]);
    }

    private void writeElement(XmlSerializer xmlSerializer, Object obj, PropertyInfo propertyInfo, Object obj2) throws IOException {
        if (obj2 != null) {
            ((Marshal) obj2).writeInstance(xmlSerializer, obj);
        } else if (obj instanceof SoapObject) {
            writeObjectBody(xmlSerializer, (SoapObject) obj);
        } else if (obj instanceof KvmSerializable) {
            writeObjectBody(xmlSerializer, (KvmSerializable) obj);
        } else if (obj instanceof Vector) {
            writeVectorBody(xmlSerializer, (Vector) obj, propertyInfo.elementType);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot serialize: ");
            stringBuffer.append(obj);
            throw new RuntimeException(stringBuffer.toString());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object[]} */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeVectorBody(org.xmlpull.v1.XmlSerializer r12, java.util.Vector r13, org.ksoap2.serialization.PropertyInfo r14) throws java.io.IOException {
        /*
            r11 = this;
            r0 = 0
            java.lang.String r1 = "item"
            if (r14 != 0) goto L_0x0008
            org.ksoap2.serialization.PropertyInfo r14 = org.ksoap2.serialization.PropertyInfo.OBJECT_TYPE
            goto L_0x0015
        L_0x0008:
            boolean r2 = r14 instanceof org.ksoap2.serialization.PropertyInfo
            if (r2 == 0) goto L_0x0015
            java.lang.String r2 = r14.name
            if (r2 == 0) goto L_0x0015
            java.lang.String r1 = r14.name
            java.lang.String r2 = r14.namespace
            goto L_0x0016
        L_0x0015:
            r2 = r0
        L_0x0016:
            int r3 = r13.size()
            java.lang.Object r4 = r14.type
            java.lang.Object[] r0 = r11.getInfo(r4, r0)
            boolean r4 = r11.implicitTypes
            java.lang.String r5 = "]"
            java.lang.String r6 = "["
            r7 = 1
            r8 = 0
            if (r4 != 0) goto L_0x0059
            java.lang.String r4 = r11.enc
            java.lang.StringBuffer r9 = new java.lang.StringBuffer
            r9.<init>()
            r10 = r0[r8]
            java.lang.String r10 = (java.lang.String) r10
            java.lang.String r10 = r12.getPrefix(r10, r8)
            r9.append(r10)
            java.lang.String r10 = ":"
            r9.append(r10)
            r0 = r0[r7]
            r9.append(r0)
            r9.append(r6)
            r9.append(r3)
            r9.append(r5)
            java.lang.String r0 = r9.toString()
            java.lang.String r9 = "arrayType"
            r12.attribute(r4, r9, r0)
            goto L_0x0060
        L_0x0059:
            if (r2 != 0) goto L_0x0060
            r0 = r0[r8]
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2
        L_0x0060:
            r0 = 0
            r4 = 0
        L_0x0062:
            if (r0 >= r3) goto L_0x0098
            java.lang.Object r9 = r13.elementAt(r0)
            if (r9 != 0) goto L_0x006c
            r4 = 1
            goto L_0x0095
        L_0x006c:
            r12.startTag(r2, r1)
            if (r4 == 0) goto L_0x008b
            java.lang.String r4 = r11.enc
            java.lang.StringBuffer r9 = new java.lang.StringBuffer
            r9.<init>()
            r9.append(r6)
            r9.append(r0)
            r9.append(r5)
            java.lang.String r9 = r9.toString()
            java.lang.String r10 = "position"
            r12.attribute(r4, r10, r9)
            r4 = 0
        L_0x008b:
            java.lang.Object r9 = r13.elementAt(r0)
            r11.writeProperty(r12, r9, r14)
            r12.endTag(r2, r1)
        L_0x0095:
            int r0 = r0 + 1
            goto L_0x0062
        L_0x0098:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ksoap2.serialization.SoapSerializationEnvelope.writeVectorBody(org.xmlpull.v1.XmlSerializer, java.util.Vector, org.ksoap2.serialization.PropertyInfo):void");
    }
}
