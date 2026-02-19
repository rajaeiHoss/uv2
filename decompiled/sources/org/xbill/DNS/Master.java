package org.xbill.DNS;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.xbill.DNS.Tokenizer;

public class Master {
    private int currentDClass;
    private long currentTTL;
    private int currentType;
    private long defaultTTL;
    private File file;
    private Generator generator;
    private List generators;
    private Master included;
    private Record last;
    private boolean needSOATTL;
    private boolean noExpandGenerate;
    private Name origin;
    private Tokenizer st;

    Master(File file2, Name name, long j) throws IOException {
        this.last = null;
        this.included = null;
        if (name == null || name.isAbsolute()) {
            this.file = file2;
            this.st = new Tokenizer(file2);
            this.origin = name;
            this.defaultTTL = j;
            return;
        }
        throw new RelativeNameException(name);
    }

    public Master(String str, Name name, long j) throws IOException {
        this(new File(str), name, j);
    }

    public Master(String str, Name name) throws IOException {
        this(new File(str), name, -1);
    }

    public Master(String str) throws IOException {
        this(new File(str), (Name) null, -1);
    }

    public Master(InputStream inputStream, Name name, long j) {
        this.last = null;
        this.included = null;
        if (name == null || name.isAbsolute()) {
            this.st = new Tokenizer(inputStream);
            this.origin = name;
            this.defaultTTL = j;
            return;
        }
        throw new RelativeNameException(name);
    }

    public Master(InputStream inputStream, Name name) {
        this(inputStream, name, -1);
    }

    public Master(InputStream inputStream) {
        this(inputStream, (Name) null, -1);
    }

    private Name parseName(String str, Name name) throws TextParseException {
        try {
            return Name.fromString(str, name);
        } catch (TextParseException e) {
            throw this.st.exception(e.getMessage());
        }
    }

    private void parseTTLClassAndType() throws IOException {
        boolean z;
        String string = this.st.getString();
        int value = DClass.value(string);
        this.currentDClass = value;
        if (value >= 0) {
            string = this.st.getString();
            z = true;
        } else {
            z = false;
        }
        this.currentTTL = -1;
        try {
            this.currentTTL = TTL.parseTTL(string);
            string = this.st.getString();
        } catch (NumberFormatException unused) {
            long j = this.defaultTTL;
            if (j >= 0) {
                this.currentTTL = j;
            } else {
                Record record = this.last;
                if (record != null) {
                    this.currentTTL = record.getTTL();
                }
            }
        }
        if (!z) {
            int value2 = DClass.value(string);
            this.currentDClass = value2;
            if (value2 >= 0) {
                string = this.st.getString();
            } else {
                this.currentDClass = 1;
            }
        }
        int value3 = Type.value(string);
        this.currentType = value3;
        if (value3 < 0) {
            Tokenizer tokenizer = this.st;
            throw tokenizer.exception("Invalid type '" + string + "'");
        } else if (this.currentTTL >= 0) {
        } else {
            if (value3 == 6) {
                this.needSOATTL = true;
                this.currentTTL = 0;
                return;
            }
            throw this.st.exception("missing TTL");
        }
    }

    private long parseUInt32(String str) {
        if (!Character.isDigit(str.charAt(0))) {
            return -1;
        }
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong < 0 || parseLong > 4294967295L) {
                return -1;
            }
            return parseLong;
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private void startGenerate() throws IOException {
        String identifier = this.st.getIdentifier();
        int indexOf = identifier.indexOf("-");
        if (indexOf >= 0) {
            String substring = identifier.substring(0, indexOf);
            String substring2 = identifier.substring(indexOf + 1);
            String str = null;
            int indexOf2 = substring2.indexOf("/");
            if (indexOf2 >= 0) {
                str = substring2.substring(indexOf2 + 1);
                substring2 = substring2.substring(0, indexOf2);
            }
            long parseUInt32 = parseUInt32(substring);
            long parseUInt322 = parseUInt32(substring2);
            long parseUInt323 = str != null ? parseUInt32(str) : 1;
            if (parseUInt32 < 0 || parseUInt322 < 0 || parseUInt32 > parseUInt322 || parseUInt323 <= 0) {
                Tokenizer tokenizer = this.st;
                throw tokenizer.exception("Invalid $GENERATE range specifier: " + identifier);
            }
            String identifier2 = this.st.getIdentifier();
            parseTTLClassAndType();
            if (Generator.supportedType(this.currentType)) {
                String identifier3 = this.st.getIdentifier();
                this.st.getEOL();
                this.st.unget();
                this.generator = new Generator(parseUInt32, parseUInt322, parseUInt323, identifier2, this.currentType, this.currentDClass, this.currentTTL, identifier3, this.origin);
                if (this.generators == null) {
                    this.generators = new ArrayList(1);
                }
                this.generators.add(this.generator);
                return;
            }
            Tokenizer tokenizer2 = this.st;
            throw tokenizer2.exception("$GENERATE does not support " + Type.string(this.currentType) + " records");
        }
        Tokenizer tokenizer3 = this.st;
        throw tokenizer3.exception("Invalid $GENERATE range specifier: " + identifier);
    }

    private void endGenerate() throws IOException {
        this.st.getEOL();
        this.generator = null;
    }

    private Record nextGenerated() throws IOException {
        try {
            return this.generator.nextRecord();
        } catch (Tokenizer.TokenizerException e) {
            Tokenizer tokenizer = this.st;
            throw tokenizer.exception("Parsing $GENERATE: " + e.getBaseMessage());
        } catch (TextParseException e2) {
            Tokenizer tokenizer2 = this.st;
            throw tokenizer2.exception("Parsing $GENERATE: " + e2.getMessage());
        }
    }

    public Record _nextRecord() throws IOException {
        Name name;
        File file2;
        Master master = this.included;
        if (master != null) {
            Record nextRecord = master.nextRecord();
            if (nextRecord != null) {
                return nextRecord;
            }
            this.included = null;
        }
        if (this.generator != null) {
            Record nextGenerated = nextGenerated();
            if (nextGenerated != null) {
                return nextGenerated;
            }
            endGenerate();
        }
        while (true) {
            Tokenizer.Token token = this.st.get(true, false);
            if (token.type == 2) {
                Tokenizer.Token token2 = this.st.get();
                if (token2.type == 1) {
                    continue;
                } else if (token2.type == 0) {
                    return null;
                } else {
                    this.st.unget();
                    Record record = this.last;
                    if (record != null) {
                        name = record.getName();
                        break;
                    } else {
                        throw this.st.exception("no owner");
                    }
                }
            } else if (token.type == 1) {
                continue;
            } else if (token.type == 0) {
                return null;
            } else {
                if (token.value.charAt(0) == '$') {
                    String str = token.value;
                    if (str.equalsIgnoreCase("$ORIGIN")) {
                        this.origin = this.st.getName(Name.root);
                        this.st.getEOL();
                    } else if (str.equalsIgnoreCase("$TTL")) {
                        this.defaultTTL = this.st.getTTL();
                        this.st.getEOL();
                    } else if (str.equalsIgnoreCase("$INCLUDE")) {
                        String string = this.st.getString();
                        File file3 = this.file;
                        if (file3 != null) {
                            file2 = new File(file3.getParent(), string);
                        } else {
                            file2 = new File(string);
                        }
                        Name name2 = this.origin;
                        Tokenizer.Token token3 = this.st.get();
                        if (token3.isString()) {
                            name2 = parseName(token3.value, Name.root);
                            this.st.getEOL();
                        }
                        this.included = new Master(file2, name2, this.defaultTTL);
                        return nextRecord();
                    } else if (!str.equalsIgnoreCase("$GENERATE")) {
                        Tokenizer tokenizer = this.st;
                        throw tokenizer.exception("Invalid directive: " + str);
                    } else if (this.generator == null) {
                        startGenerate();
                        if (!this.noExpandGenerate) {
                            return nextGenerated();
                        }
                        endGenerate();
                    } else {
                        throw new IllegalStateException("cannot nest $GENERATE");
                    }
                    continue;
                } else {
                    name = parseName(token.value, this.origin);
                    Record record2 = this.last;
                    if (record2 != null && name.equals(record2.getName())) {
                        name = this.last.getName();
                    }
                    break;
                }
            }
        }
        parseTTLClassAndType();
        Record fromString = Record.fromString(name, this.currentType, this.currentDClass, this.currentTTL, this.st, this.origin);
        this.last = fromString;
        if (this.needSOATTL) {
            long minimum = ((SOARecord) fromString).getMinimum();
            this.last.setTTL(minimum);
            this.defaultTTL = minimum;
            this.needSOATTL = false;
        }
        return this.last;
    }

    public Record nextRecord() throws IOException {
        try {
            Record _nextRecord = _nextRecord();
            if (_nextRecord == null) {
            }
            return _nextRecord;
        } finally {
            this.st.close();
        }
    }

    public void expandGenerate(boolean z) {
        this.noExpandGenerate = !z;
    }

    public Iterator generators() {
        List list = this.generators;
        if (list != null) {
            return Collections.unmodifiableList(list).iterator();
        }
        return Collections.EMPTY_LIST.iterator();
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        this.st.close();
    }
}
