package org.xbill.DNS;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class ResolverConfig {
    private static ResolverConfig currentConfig;
    private Name[] searchlist = null;
    private String[] servers = null;

    static {
        refresh();
    }

    public ResolverConfig() {
        if (findProperty() || findSunJVM()) {
            return;
        }
        if (this.servers == null || this.searchlist == null) {
            String property = System.getProperty("os.name");
            String property2 = System.getProperty("java.vendor");
            if (property.indexOf("Windows") != -1) {
                if (property.indexOf("95") == -1 && property.indexOf("98") == -1 && property.indexOf("ME") == -1) {
                    findNT();
                } else {
                    find95();
                }
            } else if (property.indexOf("NetWare") != -1) {
                findNetware();
            } else if (property2.indexOf("Android") != -1) {
                findAndroid();
            } else {
                findUnix();
            }
        }
    }

    private void addServer(String str, List list) {
        if (!list.contains(str)) {
            if (Options.check("verbose")) {
                PrintStream printStream = System.out;
                printStream.println("adding server " + str);
            }
            list.add(str);
        }
    }

    private void addSearch(String str, List list) {
        if (Options.check("verbose")) {
            PrintStream printStream = System.out;
            printStream.println("adding search " + str);
        }
        try {
            Name fromString = Name.fromString(str, Name.root);
            if (!list.contains(fromString)) {
                list.add(fromString);
            }
        } catch (TextParseException unused) {
        }
    }

    private void configureFromLists(List list, List list2) {
        if (this.servers == null && list.size() > 0) {
            this.servers = (String[]) list.toArray(new String[0]);
        }
        if (this.searchlist == null && list2.size() > 0) {
            this.searchlist = (Name[]) list2.toArray(new Name[0]);
        }
    }

    private boolean findProperty() {
        ArrayList arrayList = new ArrayList(0);
        ArrayList arrayList2 = new ArrayList(0);
        String property = System.getProperty("dns.server");
        if (property != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(property, ",");
            while (stringTokenizer.hasMoreTokens()) {
                addServer(stringTokenizer.nextToken(), arrayList);
            }
        }
        String property2 = System.getProperty("dns.search");
        if (property2 != null) {
            StringTokenizer stringTokenizer2 = new StringTokenizer(property2, ",");
            while (stringTokenizer2.hasMoreTokens()) {
                addSearch(stringTokenizer2.nextToken(), arrayList2);
            }
        }
        configureFromLists(arrayList, arrayList2);
        if (this.servers == null || this.searchlist == null) {
            return false;
        }
        return true;
    }

    private boolean findSunJVM() {
        ArrayList arrayList = new ArrayList(0);
        ArrayList arrayList2 = new ArrayList(0);
        try {
            Class[] clsArr = new Class[0];
            Object[] objArr = new Object[0];
            Class<?> cls = Class.forName("sun.net.dns.ResolverConfiguration");
            Object invoke = cls.getDeclaredMethod("open", clsArr).invoke((Object) null, objArr);
            List<String> list = (List) cls.getMethod("nameservers", clsArr).invoke(invoke, objArr);
            List<String> list2 = (List) cls.getMethod("searchlist", clsArr).invoke(invoke, objArr);
            if (list.size() == 0) {
                return false;
            }
            if (list.size() > 0) {
                for (String addServer : list) {
                    addServer(addServer, arrayList);
                }
            }
            if (list2.size() > 0) {
                for (String addSearch : list2) {
                    addSearch(addSearch, arrayList2);
                }
            }
            configureFromLists(arrayList, arrayList2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private void findResolvConf(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str)));
            ArrayList arrayList = new ArrayList(0);
            ArrayList arrayList2 = new ArrayList(0);
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.startsWith("nameserver")) {
                        StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                        stringTokenizer.nextToken();
                        addServer(stringTokenizer.nextToken(), arrayList);
                    } else if (readLine.startsWith("domain")) {
                        StringTokenizer stringTokenizer2 = new StringTokenizer(readLine);
                        stringTokenizer2.nextToken();
                        if (stringTokenizer2.hasMoreTokens()) {
                            if (arrayList2.isEmpty()) {
                                addSearch(stringTokenizer2.nextToken(), arrayList2);
                            }
                        }
                    } else if (readLine.startsWith(FirebaseAnalytics.Event.SEARCH)) {
                        if (!arrayList2.isEmpty()) {
                            arrayList2.clear();
                        }
                        StringTokenizer stringTokenizer3 = new StringTokenizer(readLine);
                        stringTokenizer3.nextToken();
                        while (stringTokenizer3.hasMoreTokens()) {
                            addSearch(stringTokenizer3.nextToken(), arrayList2);
                        }
                    }
                } catch (IOException unused) {
                }
            }
            try {
                bufferedReader.close();
            } catch (IOException unused3) {
            }
            configureFromLists(arrayList, arrayList2);
        } catch (FileNotFoundException unused2) {
        }
    }

    private void findUnix() {
        findResolvConf("/etc/resolv.conf");
    }

    private void findNetware() {
        findResolvConf("sys:/etc/resolv.cfg");
    }

    private void findWin(InputStream inputStream, Locale locale) {
        ResourceBundle resourceBundle;
        Locale locale2 = locale;
        String str = ResolverConfig.class.getPackage().getName() + ".windows.DNSServer";
        if (locale2 != null) {
            resourceBundle = ResourceBundle.getBundle(str, locale2);
        } else {
            resourceBundle = ResourceBundle.getBundle(str);
        }
        String string = resourceBundle.getString("host_name");
        String string2 = resourceBundle.getString("primary_dns_suffix");
        String string3 = resourceBundle.getString("dns_suffix");
        String string4 = resourceBundle.getString("dns_servers");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            while (true) {
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                        if (!stringTokenizer.hasMoreTokens()) {
                            break;
                        }
                        String nextToken = stringTokenizer.nextToken();
                        if (readLine.indexOf(":") != -1) {
                            z = false;
                            z2 = false;
                        }
                        String str2 = string;
                        if (readLine.indexOf(string) != -1) {
                            while (stringTokenizer.hasMoreTokens()) {
                                nextToken = stringTokenizer.nextToken();
                            }
                            try {
                                if (Name.fromString(nextToken, (Name) null).labels() != 1) {
                                    addSearch(nextToken, arrayList2);
                                }
                            } catch (TextParseException unused) {
                            }
                        } else {
                            if (readLine.indexOf(string2) != -1) {
                                while (stringTokenizer.hasMoreTokens()) {
                                    nextToken = stringTokenizer.nextToken();
                                }
                                if (!nextToken.equals(":")) {
                                    addSearch(nextToken, arrayList2);
                                }
                            } else {
                                if (!z) {
                                    if (readLine.indexOf(string3) == -1) {
                                        if (z2 || readLine.indexOf(string4) != -1) {
                                            while (stringTokenizer.hasMoreTokens()) {
                                                nextToken = stringTokenizer.nextToken();
                                            }
                                            if (!nextToken.equals(":")) {
                                                addServer(nextToken, arrayList);
                                                z2 = true;
                                            }
                                        }
                                    }
                                }
                                while (stringTokenizer.hasMoreTokens()) {
                                    nextToken = stringTokenizer.nextToken();
                                }
                                if (!nextToken.equals(":")) {
                                    addSearch(nextToken, arrayList2);
                                }
                            }
                            z = true;
                        }
                        string = str2;
                    } else {
                        configureFromLists(arrayList, arrayList2);
                        return;
                    }
                }
            }
        } catch (IOException unused2) {
        }
    }

    private void findWin(InputStream inputStream) {
        int intValue = Integer.getInteger("org.xbill.DNS.windows.parse.buffer", 8192).intValue();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, intValue);
        bufferedInputStream.mark(intValue);
        findWin(bufferedInputStream, (Locale) null);
        if (this.servers == null) {
            try {
                bufferedInputStream.reset();
                findWin(bufferedInputStream, new Locale("", ""));
            } catch (IOException unused) {
            }
        }
    }

    private void find95() {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("winipcfg /all /batch " + "winipcfg.out").waitFor();
            findWin(new FileInputStream(new File("winipcfg.out")));
            new File("winipcfg.out").delete();
        } catch (Exception unused) {
        }
    }

    private void findNT() {
        try {
            Process exec = Runtime.getRuntime().exec("ipconfig /all");
            findWin(exec.getInputStream());
            exec.destroy();
        } catch (Exception unused) {
        }
    }

    private void findAndroid() {
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop").getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine, ":");
                    if (stringTokenizer.nextToken().indexOf("net.dns") > -1) {
                        String replaceAll = stringTokenizer.nextToken().replaceAll("[ \\[\\]]", "");
                        if ((replaceAll.matches("^\\d+(\\.\\d+){3}$") || replaceAll.matches("^[0-9a-f]+(:[0-9a-f]*)+:[0-9a-f]+$")) && !arrayList.contains(replaceAll)) {
                            arrayList.add(replaceAll);
                        }
                    }
                } else {
                    configureFromLists(arrayList, arrayList2);
                    return;
                }
            }
        } catch (Exception unused) {
        }
    }

    public String[] servers() {
        return this.servers;
    }

    public String server() {
        String[] strArr = this.servers;
        if (strArr == null) {
            return null;
        }
        return strArr[0];
    }

    public Name[] searchPath() {
        return this.searchlist;
    }

    public static synchronized ResolverConfig getCurrentConfig() {
        ResolverConfig resolverConfig;
        synchronized (ResolverConfig.class) {
            resolverConfig = currentConfig;
        }
        return resolverConfig;
    }

    public static void refresh() {
        ResolverConfig resolverConfig = new ResolverConfig();
        synchronized (ResolverConfig.class) {
            currentConfig = resolverConfig;
        }
    }
}
