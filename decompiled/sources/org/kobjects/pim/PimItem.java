package org.kobjects.pim;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public abstract class PimItem {
    public static final int TYPE_STRING = 0;
    public static final int TYPE_STRING_ARRAY = 1;
    Hashtable fields = new Hashtable();

    public abstract int getArraySize(String str);

    public abstract String getType();

    public PimItem() {
    }

    public PimItem(PimItem pimItem) {
        Enumeration fields2 = pimItem.fields();
        while (fields2.hasMoreElements()) {
            addField(new PimField((PimField) fields2.nextElement()));
        }
    }

    public Enumeration fieldNames() {
        return this.fields.keys();
    }

    public void addField(PimField pimField) {
        Vector vector = (Vector) this.fields.get(pimField.name);
        if (vector == null) {
            vector = new Vector();
            this.fields.put(pimField.name, vector);
        }
        vector.addElement(pimField);
    }

    public Enumeration fields() {
        Vector vector = new Vector();
        Enumeration fieldNames = fieldNames();
        while (fieldNames.hasMoreElements()) {
            Enumeration fields2 = fields((String) fieldNames.nextElement());
            while (fields2.hasMoreElements()) {
                vector.addElement(fields2.nextElement());
            }
        }
        return vector.elements();
    }

    public Enumeration fields(String str) {
        Vector vector = (Vector) this.fields.get(str);
        if (vector == null) {
            vector = new Vector();
        }
        return vector.elements();
    }

    public PimField getField(String str, int i) {
        return (PimField) ((Vector) this.fields.get(str)).elementAt(i);
    }

    public int getFieldCount(String str) {
        Vector vector = (Vector) this.fields.get(str);
        if (vector == null) {
            return 0;
        }
        return vector.size();
    }

    public int getType(String str) {
        return getArraySize(str) == -1 ? 0 : 1;
    }

    public void removeField(String str, int i) {
        ((Vector) this.fields.get(str)).removeElementAt(i);
    }

    public String toString() {
        return getType() + ":" + this.fields.toString();
    }
}
