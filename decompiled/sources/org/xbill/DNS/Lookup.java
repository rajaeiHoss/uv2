package org.xbill.DNS;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class Lookup {
    public static final int HOST_NOT_FOUND = 3;
    public static final int SUCCESSFUL = 0;
    public static final int TRY_AGAIN = 2;
    public static final int TYPE_NOT_FOUND = 4;
    public static final int UNRECOVERABLE = 1;
    private static Map defaultCaches;
    private static Resolver defaultResolver;
    private static Name[] defaultSearchPath;
    private static final Name[] noAliases = new Name[0];
    private List aliases;
    private Record[] answers;
    private boolean badresponse;
    private String badresponse_error;
    private Cache cache;
    private int credibility;
    private int dclass;
    private boolean done;
    private boolean doneCurrent;
    private String error;
    private boolean foundAlias;
    private int iterations;
    private Name name;
    private boolean nametoolong;
    private boolean networkerror;
    private boolean nxdomain;
    private boolean referral;
    private Resolver resolver;
    private int result;
    private Name[] searchPath;
    private boolean temporary_cache;
    private boolean timedout;
    private int type;
    private boolean verbose;

    static {
        refreshDefault();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:9|10|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        throw new java.lang.RuntimeException("Failed to initialize resolver");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void refreshDefault() {
        /*
            java.lang.Class<org.xbill.DNS.Lookup> r0 = org.xbill.DNS.Lookup.class
            monitor-enter(r0)
            org.xbill.DNS.ExtendedResolver r1 = new org.xbill.DNS.ExtendedResolver     // Catch:{ UnknownHostException -> 0x001f }
            r1.<init>()     // Catch:{ UnknownHostException -> 0x001f }
            defaultResolver = r1     // Catch:{ UnknownHostException -> 0x001f }
            org.xbill.DNS.ResolverConfig r1 = org.xbill.DNS.ResolverConfig.getCurrentConfig()     // Catch:{ all -> 0x001d }
            org.xbill.DNS.Name[] r1 = r1.searchPath()     // Catch:{ all -> 0x001d }
            defaultSearchPath = r1     // Catch:{ all -> 0x001d }
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x001d }
            r1.<init>()     // Catch:{ all -> 0x001d }
            defaultCaches = r1     // Catch:{ all -> 0x001d }
            monitor-exit(r0)
            return
        L_0x001d:
            r1 = move-exception
            goto L_0x0027
        L_0x001f:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x001d }
            java.lang.String r2 = "Failed to initialize resolver"
            r1.<init>(r2)     // Catch:{ all -> 0x001d }
            throw r1     // Catch:{ all -> 0x001d }
        L_0x0027:
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Lookup.refreshDefault():void");
    }

    public static synchronized Resolver getDefaultResolver() {
        Resolver resolver2;
        synchronized (Lookup.class) {
            resolver2 = defaultResolver;
        }
        return resolver2;
    }

    public static synchronized void setDefaultResolver(Resolver resolver2) {
        synchronized (Lookup.class) {
            defaultResolver = resolver2;
        }
    }

    public static synchronized Cache getDefaultCache(int i) {
        Cache cache2;
        synchronized (Lookup.class) {
            DClass.check(i);
            cache2 = (Cache) defaultCaches.get(Mnemonic.toInteger(i));
            if (cache2 == null) {
                cache2 = new Cache(i);
                defaultCaches.put(Mnemonic.toInteger(i), cache2);
            }
        }
        return cache2;
    }

    public static synchronized void setDefaultCache(Cache cache2, int i) {
        synchronized (Lookup.class) {
            DClass.check(i);
            defaultCaches.put(Mnemonic.toInteger(i), cache2);
        }
    }

    public static synchronized Name[] getDefaultSearchPath() {
        Name[] nameArr;
        synchronized (Lookup.class) {
            nameArr = defaultSearchPath;
        }
        return nameArr;
    }

    public static synchronized void setDefaultSearchPath(Name[] nameArr) {
        synchronized (Lookup.class) {
            defaultSearchPath = nameArr;
        }
    }

    public static synchronized void setDefaultSearchPath(String[] strArr) throws TextParseException {
        synchronized (Lookup.class) {
            if (strArr == null) {
                defaultSearchPath = null;
                return;
            }
            Name[] nameArr = new Name[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                nameArr[i] = Name.fromString(strArr[i], Name.root);
            }
            defaultSearchPath = nameArr;
        }
    }

    private final void reset() {
        this.iterations = 0;
        this.foundAlias = false;
        this.done = false;
        this.doneCurrent = false;
        this.aliases = null;
        this.answers = null;
        this.result = -1;
        this.error = null;
        this.nxdomain = false;
        this.badresponse = false;
        this.badresponse_error = null;
        this.networkerror = false;
        this.timedout = false;
        this.nametoolong = false;
        this.referral = false;
        if (this.temporary_cache) {
            this.cache.clearCache();
        }
    }

    public Lookup(Name name2, int i, int i2) {
        Type.check(i);
        DClass.check(i2);
        if (Type.isRR(i) || i == 255) {
            this.name = name2;
            this.type = i;
            this.dclass = i2;
            synchronized (Lookup.class) {
                this.resolver = getDefaultResolver();
                this.searchPath = getDefaultSearchPath();
                this.cache = getDefaultCache(i2);
            }
            this.credibility = 3;
            this.verbose = Options.check("verbose");
            this.result = -1;
            return;
        }
        throw new IllegalArgumentException("Cannot query for meta-types other than ANY");
    }

    public Lookup(Name name2, int i) {
        this(name2, i, 1);
    }

    public Lookup(Name name2) {
        this(name2, 1, 1);
    }

    public Lookup(String str, int i, int i2) throws TextParseException {
        this(Name.fromString(str), i, i2);
    }

    public Lookup(String str, int i) throws TextParseException {
        this(Name.fromString(str), i, 1);
    }

    public Lookup(String str) throws TextParseException {
        this(Name.fromString(str), 1, 1);
    }

    public void setResolver(Resolver resolver2) {
        this.resolver = resolver2;
    }

    public void setSearchPath(Name[] nameArr) {
        this.searchPath = nameArr;
    }

    public void setSearchPath(String[] strArr) throws TextParseException {
        if (strArr == null) {
            this.searchPath = null;
            return;
        }
        Name[] nameArr = new Name[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            nameArr[i] = Name.fromString(strArr[i], Name.root);
        }
        this.searchPath = nameArr;
    }

    public void setCache(Cache cache2) {
        if (cache2 == null) {
            this.cache = new Cache(this.dclass);
            this.temporary_cache = true;
            return;
        }
        this.cache = cache2;
        this.temporary_cache = false;
    }

    public void setCredibility(int i) {
        this.credibility = i;
    }

    private void follow(Name name2, Name name3) {
        this.foundAlias = true;
        this.badresponse = false;
        this.networkerror = false;
        this.timedout = false;
        this.nxdomain = false;
        this.referral = false;
        int i = this.iterations + 1;
        this.iterations = i;
        if (i >= 6 || name2.equals(name3)) {
            this.result = 1;
            this.error = "CNAME loop";
            this.done = true;
            return;
        }
        if (this.aliases == null) {
            this.aliases = new ArrayList();
        }
        this.aliases.add(name3);
        lookup(name2);
    }

    private void processResponse(Name name2, SetResponse setResponse) {
        if (setResponse.isSuccessful()) {
            RRset[] answers2 = setResponse.answers();
            ArrayList arrayList = new ArrayList();
            for (RRset rrs : answers2) {
                Iterator rrs2 = rrs.rrs();
                while (rrs2.hasNext()) {
                    arrayList.add(rrs2.next());
                }
            }
            this.result = 0;
            this.answers = (Record[]) arrayList.toArray(new Record[arrayList.size()]);
            this.done = true;
        } else if (setResponse.isNXDOMAIN()) {
            this.nxdomain = true;
            this.doneCurrent = true;
            if (this.iterations > 0) {
                this.result = 3;
                this.done = true;
            }
        } else if (setResponse.isNXRRSET()) {
            this.result = 4;
            this.answers = null;
            this.done = true;
        } else if (setResponse.isCNAME()) {
            follow(setResponse.getCNAME().getTarget(), name2);
        } else if (setResponse.isDNAME()) {
            try {
                follow(name2.fromDNAME(setResponse.getDNAME()), name2);
            } catch (NameTooLongException unused) {
                this.result = 1;
                this.error = "Invalid DNAME target";
                this.done = true;
            }
        } else if (setResponse.isDelegation()) {
            this.referral = true;
        }
    }

    private void lookup(Name name2) {
        SetResponse lookupRecords = this.cache.lookupRecords(name2, this.type, this.credibility);
        if (this.verbose) {
            PrintStream printStream = System.err;
            printStream.println("lookup " + name2 + " " + Type.string(this.type));
            System.err.println(lookupRecords);
        }
        processResponse(name2, lookupRecords);
        if (!this.done && !this.doneCurrent) {
            Message newQuery = Message.newQuery(Record.newRecord(name2, this.type, this.dclass));
            try {
                Message send = this.resolver.send(newQuery);
                int rcode = send.getHeader().getRcode();
                if (rcode != 0 && rcode != 3) {
                    this.badresponse = true;
                    this.badresponse_error = Rcode.string(rcode);
                } else if (!newQuery.getQuestion().equals(send.getQuestion())) {
                    this.badresponse = true;
                    this.badresponse_error = "response does not match query";
                } else {
                    SetResponse addMessage = this.cache.addMessage(send);
                    if (addMessage == null) {
                        addMessage = this.cache.lookupRecords(name2, this.type, this.credibility);
                    }
                    if (this.verbose) {
                        PrintStream printStream2 = System.err;
                        printStream2.println("queried " + name2 + " " + Type.string(this.type));
                        System.err.println(addMessage);
                    }
                    processResponse(name2, addMessage);
                }
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    this.timedout = true;
                } else {
                    this.networkerror = true;
                }
            }
        }
    }

    private void resolve(Name name2, Name name3) {
        this.doneCurrent = false;
        if (name3 != null) {
            try {
                name2 = Name.concatenate(name2, name3);
            } catch (NameTooLongException unused) {
                this.nametoolong = true;
                return;
            }
        }
        lookup(name2);
    }

    public Record[] run() {
        if (this.done) {
            reset();
        }
        if (!this.name.isAbsolute()) {
            if (this.searchPath != null) {
                if (this.name.labels() > 1) {
                    resolve(this.name, Name.root);
                }
                if (!this.done) {
                    int i = 0;
                    while (true) {
                        Name[] nameArr = this.searchPath;
                        if (i >= nameArr.length) {
                            break;
                        }
                        resolve(this.name, nameArr[i]);
                        if (this.done) {
                            return this.answers;
                        }
                        if (this.foundAlias) {
                            break;
                        }
                        i++;
                    }
                } else {
                    return this.answers;
                }
            } else {
                resolve(this.name, Name.root);
            }
        } else {
            resolve(this.name, (Name) null);
        }
        if (!this.done) {
            if (this.badresponse) {
                this.result = 2;
                this.error = this.badresponse_error;
                this.done = true;
            } else if (this.timedout) {
                this.result = 2;
                this.error = "timed out";
                this.done = true;
            } else if (this.networkerror) {
                this.result = 2;
                this.error = "network error";
                this.done = true;
            } else if (this.nxdomain) {
                this.result = 3;
                this.done = true;
            } else if (this.referral) {
                this.result = 1;
                this.error = "referral";
                this.done = true;
            } else if (this.nametoolong) {
                this.result = 1;
                this.error = "name too long";
                this.done = true;
            }
        }
        return this.answers;
    }

    private void checkDone() {
        if (!this.done || this.result == -1) {
            StringBuffer stringBuffer = new StringBuffer("Lookup of " + this.name + " ");
            if (this.dclass != 1) {
                stringBuffer.append(DClass.string(this.dclass) + " ");
            }
            stringBuffer.append(Type.string(this.type) + " isn't done");
            throw new IllegalStateException(stringBuffer.toString());
        }
    }

    public Record[] getAnswers() {
        checkDone();
        return this.answers;
    }

    public Name[] getAliases() {
        checkDone();
        List list = this.aliases;
        if (list == null) {
            return noAliases;
        }
        return (Name[]) list.toArray(new Name[list.size()]);
    }

    public int getResult() {
        checkDone();
        return this.result;
    }

    public String getErrorString() {
        checkDone();
        String str = this.error;
        if (str != null) {
            return str;
        }
        int i = this.result;
        if (i == 0) {
            return "successful";
        }
        if (i == 1) {
            return "unrecoverable error";
        }
        if (i == 2) {
            return "try again";
        }
        if (i == 3) {
            return "host not found";
        }
        if (i == 4) {
            return "type not found";
        }
        throw new IllegalStateException("unknown result");
    }
}
