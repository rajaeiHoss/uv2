package org.kobjects.pim;

import com.google.android.gms.measurement.AppMeasurement;
import java.util.Enumeration;
import java.util.Hashtable;

public class PimField {
    String name;
    Hashtable properties;
    Object value;

    public PimField(PimField pimField) {
        this(pimField.name);
        Object obj = pimField.value;
        if (obj instanceof String[]) {
            int length = ((String[]) obj).length;
            String[] strArr = new String[length];
            System.arraycopy((String[]) obj, 0, strArr, 0, length);
            this.value = strArr;
        } else {
            this.value = obj;
        }
        if (pimField.properties != null) {
            this.properties = new Hashtable();
            Enumeration keys = pimField.properties.keys();
            while (keys.hasMoreElements()) {
                String str = (String) keys.nextElement();
                this.properties.put(str, pimField.properties.get(str));
            }
        }
    }

    public PimField(String str) {
        this.name = str;
    }

    public Enumeration propertyNames() {
        return this.properties.keys();
    }

    public void setProperty(String str, String str2) {
        if (this.properties == null) {
            if (str2 != null) {
                this.properties = new Hashtable();
            } else {
                return;
            }
        }
        if (str2 == null) {
            this.properties.remove(str);
        } else {
            this.properties.put(str, str2);
        }
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public Object getValue() {
        return this.value;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        if (this.properties != null) {
            str = ";" + this.properties;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(":");
        sb.append(this.value);
        return sb.toString();
    }

    public String getProperty(String str) {
        Hashtable hashtable = this.properties;
        if (hashtable == null) {
            return null;
        }
        return (String) hashtable.get(str);
    }

    public boolean getAttribute(String str) {
        String property = getProperty(AppMeasurement.Param.TYPE);
        if (property == null || property.indexOf(str) == -1) {
            return false;
        }
        return true;
    }

    public void setAttribute(String str, boolean z) {
        if (getAttribute(str) != z) {
            String property = getProperty(AppMeasurement.Param.TYPE);
            if (!z) {
                int indexOf = property.indexOf(str);
                if (indexOf > 0) {
                    indexOf--;
                }
                if (indexOf != -1) {
                    str = property.substring(0, indexOf) + property.substring(indexOf + str.length() + 1);
                } else {
                    str = property;
                }
            } else if (!(property == null || property.length() == 0)) {
                str = property + str;
            }
            setProperty(AppMeasurement.Param.TYPE, str);
        }
    }
}
