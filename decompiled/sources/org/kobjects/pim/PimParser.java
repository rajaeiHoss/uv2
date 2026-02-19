package org.kobjects.pim;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Vector;
import org.kobjects.io.LookAheadReader;

public class PimParser {
    LookAheadReader reader;
    Class type;

    public PimParser(Reader reader2, Class cls) {
        this.reader = new LookAheadReader(reader2);
        this.type = cls;
    }

    public PimItem readItem() throws IOException {
        Object obj;
        String readName = readName();
        if (readName == null) {
            return null;
        }
        if (readName.equals("begin")) {
            try {
                PimItem pimItem = (PimItem) this.type.newInstance();
                this.reader.read();
                if (pimItem.getType().equals(readStringValue().toLowerCase())) {
                    while (true) {
                        String readName2 = readName();
                        if (readName2.equals("end")) {
                            this.reader.read();
                            PrintStream printStream = System.out;
                            printStream.println("end:" + readStringValue());
                            return pimItem;
                        }
                        PimField pimField = new PimField(readName2);
                        readProperties(pimField);
                        if (pimItem.getType(readName2) != 1) {
                            obj = readStringValue();
                        } else {
                            obj = readArrayValue(pimItem.getArraySize(readName2));
                        }
                        pimField.setValue(obj);
                        PrintStream printStream2 = System.out;
                        printStream2.println("value:" + obj);
                        pimItem.addField(pimField);
                    }
                } else {
                    throw new RuntimeException("item types do not match!");
                }
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        } else {
            throw new RuntimeException("'begin:' expected");
        }
    }

    /* access modifiers changed from: package-private */
    public String readName() throws IOException {
        String lowerCase = this.reader.readTo(":;").trim().toLowerCase();
        PrintStream printStream = System.out;
        printStream.println("name:" + lowerCase);
        if (this.reader.peek(0) == -1) {
            return null;
        }
        return lowerCase;
    }

    /* access modifiers changed from: package-private */
    public String[] readArrayValue(int i) throws IOException {
        int i2;
        Vector vector = new Vector();
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        do {
            stringBuffer.append(this.reader.readTo(";\n\r"));
            int read = this.reader.read();
            if (read != 10) {
                if (read != 13) {
                    if (read != 59) {
                        continue;
                    } else {
                        vector.addElement(stringBuffer.toString());
                        stringBuffer.setLength(0);
                        continue;
                    }
                } else if (this.reader.peek(0) == 10) {
                    this.reader.read();
                }
            }
            if (this.reader.peek(0) != 32) {
                z = false;
                continue;
            } else {
                this.reader.read();
                continue;
            }
        } while (z);
        if (stringBuffer.length() != 0) {
            vector.addElement(stringBuffer.toString());
        }
        String[] strArr = new String[i];
        for (i2 = 0; i2 < Math.min(i, vector.size()); i2++) {
            strArr[i2] = (String) vector.elementAt(i2);
        }
        return strArr;
    }

    /* access modifiers changed from: package-private */
    public String readStringValue() throws IOException {
        String readLine = this.reader.readLine();
        while (this.reader.peek(0) == 32) {
            this.reader.read();
            readLine = readLine + this.reader.readLine();
        }
        return readLine;
    }

    /* access modifiers changed from: package-private */
    public void readProperties(PimField pimField) throws IOException {
        int i = this.reader.read();
        while (i == 32) {
            i = this.reader.read();
        }
        while (i != 58) {
            String lowerCase = this.reader.readTo(":;=").trim().toLowerCase();
            int read2 = this.reader.read();
            if (read2 == 61) {
                pimField.setProperty(lowerCase, this.reader.readTo(":;").trim().toLowerCase());
                i = this.reader.read();
            } else {
                pimField.setAttribute(lowerCase, true);
                i = read2;
            }
        }
    }
}
