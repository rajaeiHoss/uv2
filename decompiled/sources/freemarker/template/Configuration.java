package freemarker.template;

import freemarker.cache.CacheStorage;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MruCacheStorage;
import freemarker.cache.TemplateCache;
import freemarker.cache.TemplateLoader;
import freemarker.core.Configurable;
import freemarker.core.Environment;
import freemarker.core._ConcurrentMapFactory;
import freemarker.core._CoreAPI;
import freemarker.core._DelayedJQuote;
import freemarker.core._MiscTemplateException;
import freemarker.template.utility.CaptureOutput;
import freemarker.template.utility.ClassUtil;
import freemarker.template.utility.HtmlEscape;
import freemarker.template.utility.NormalizeNewlines;
import freemarker.template.utility.SecurityUtilities;
import freemarker.template.utility.StandardCompress;
import freemarker.template.utility.StringUtil;
import freemarker.template.utility.XmlEscape;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Configuration extends Configurable implements Cloneable {
    public static final int ANGLE_BRACKET_TAG_SYNTAX = 1;
    public static final int AUTO_DETECT_TAG_SYNTAX = 0;
    public static final String AUTO_IMPORT_KEY = "auto_import";
    public static final String AUTO_INCLUDE_KEY = "auto_include";
    public static final String CACHE_STORAGE_KEY = "cache_storage";
    public static final String DEFAULT_ENCODING_KEY = "default_encoding";
    public static final String DEFAULT_INCOMPATIBLE_ENHANCEMENTS;
    public static final Version DEFAULT_INCOMPATIBLE_IMPROVEMENTS;
    public static final String INCOMPATIBLE_ENHANCEMENTS = "incompatible_enhancements";
    public static final String INCOMPATIBLE_IMPROVEMENTS = "incompatible_improvements";
    public static final String LOCALIZED_LOOKUP_KEY = "localized_lookup";
    public static final int PARSED_DEFAULT_INCOMPATIBLE_ENHANCEMENTS;
    public static final int SQUARE_BRACKET_TAG_SYNTAX = 2;
    public static final String STRICT_SYNTAX_KEY = "strict_syntax";
    public static final String TAG_SYNTAX_KEY = "tag_syntax";
    public static final String TEMPLATE_UPDATE_DELAY_KEY = "template_update_delay";
    public static final String WHITESPACE_STRIPPING_KEY = "whitespace_stripping";
    static /* synthetic */ Class class$freemarker$template$Configuration;
    static /* synthetic */ Class class$java$lang$String;
    private static Configuration defaultConfig = new Configuration();
    private static Version version;
    private static String versionNumber;
    private static boolean versionPropertiesLoaded;
    private Map autoImportNsToTmpMap = new HashMap();
    private ArrayList autoImports = new ArrayList();
    private ArrayList autoIncludes = new ArrayList();
    private TemplateCache cache;
    private String defaultEncoding = SecurityUtilities.getSystemProperty("file.encoding");
    private Version incompatibleImprovements = DEFAULT_INCOMPATIBLE_IMPROVEMENTS;
    private Map localeToCharsetMap = _ConcurrentMapFactory.newThreadSafeMap();
    private volatile boolean localizedLookup = true;
    private HashMap sharedVariables = new HashMap();
    private boolean strictSyntax = true;
    private int tagSyntax = 1;
    private boolean whitespaceStripping = true;

    static {
        Version version2 = new Version(2, 3, 0);
        DEFAULT_INCOMPATIBLE_IMPROVEMENTS = version2;
        DEFAULT_INCOMPATIBLE_ENHANCEMENTS = version2.toString();
        PARSED_DEFAULT_INCOMPATIBLE_ENHANCEMENTS = version2.intValue();
    }

    public Configuration() {
        TemplateCache templateCache = new TemplateCache();
        this.cache = templateCache;
        templateCache.setConfiguration(this);
        this.cache.setDelay(5000);
        loadBuiltInSharedVariables();
    }

    public Object clone() {
        try {
            Configuration configuration = (Configuration) super.clone();
            configuration.sharedVariables = new HashMap(this.sharedVariables);
            configuration.localeToCharsetMap = new HashMap(this.localeToCharsetMap);
            configuration.autoImportNsToTmpMap = new HashMap(this.autoImportNsToTmpMap);
            configuration.autoImports = (ArrayList) this.autoImports.clone();
            configuration.autoIncludes = (ArrayList) this.autoIncludes.clone();
            configuration.createTemplateCache(this.cache.getTemplateLoader(), this.cache.getCacheStorage());
            return configuration;
        } catch (CloneNotSupportedException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Clone is not supported, but it should be: ");
            stringBuffer.append(e.getMessage());
            throw new RuntimeException(stringBuffer.toString());
        }
    }

    private void loadBuiltInSharedVariables() {
        this.sharedVariables.put("capture_output", new CaptureOutput());
        this.sharedVariables.put("compress", StandardCompress.INSTANCE);
        this.sharedVariables.put("html_escape", new HtmlEscape());
        this.sharedVariables.put("normalize_newlines", new NormalizeNewlines());
        this.sharedVariables.put("xml_escape", new XmlEscape());
    }

    public void loadBuiltInEncodingMap() {
        this.localeToCharsetMap.clear();
        this.localeToCharsetMap.put("ar", "ISO-8859-6");
        this.localeToCharsetMap.put("be", "ISO-8859-5");
        this.localeToCharsetMap.put("bg", "ISO-8859-5");
        this.localeToCharsetMap.put("ca", "ISO-8859-1");
        this.localeToCharsetMap.put("cs", "ISO-8859-2");
        this.localeToCharsetMap.put("da", "ISO-8859-1");
        this.localeToCharsetMap.put("de", "ISO-8859-1");
        this.localeToCharsetMap.put("el", "ISO-8859-7");
        this.localeToCharsetMap.put("en", "ISO-8859-1");
        this.localeToCharsetMap.put("es", "ISO-8859-1");
        this.localeToCharsetMap.put("et", "ISO-8859-1");
        this.localeToCharsetMap.put("fi", "ISO-8859-1");
        this.localeToCharsetMap.put("fr", "ISO-8859-1");
        this.localeToCharsetMap.put("hr", "ISO-8859-2");
        this.localeToCharsetMap.put("hu", "ISO-8859-2");
        this.localeToCharsetMap.put("is", "ISO-8859-1");
        this.localeToCharsetMap.put("it", "ISO-8859-1");
        this.localeToCharsetMap.put("iw", "ISO-8859-8");
        this.localeToCharsetMap.put("ja", "Shift_JIS");
        this.localeToCharsetMap.put("ko", "EUC-KR");
        this.localeToCharsetMap.put("lt", "ISO-8859-2");
        this.localeToCharsetMap.put("lv", "ISO-8859-2");
        this.localeToCharsetMap.put("mk", "ISO-8859-5");
        this.localeToCharsetMap.put("nl", "ISO-8859-1");
        this.localeToCharsetMap.put("no", "ISO-8859-1");
        this.localeToCharsetMap.put("pl", "ISO-8859-2");
        this.localeToCharsetMap.put("pt", "ISO-8859-1");
        this.localeToCharsetMap.put("ro", "ISO-8859-2");
        this.localeToCharsetMap.put("ru", "ISO-8859-5");
        this.localeToCharsetMap.put("sh", "ISO-8859-5");
        this.localeToCharsetMap.put("sk", "ISO-8859-2");
        this.localeToCharsetMap.put("sl", "ISO-8859-2");
        this.localeToCharsetMap.put("sq", "ISO-8859-2");
        this.localeToCharsetMap.put("sr", "ISO-8859-5");
        this.localeToCharsetMap.put("sv", "ISO-8859-1");
        this.localeToCharsetMap.put("tr", "ISO-8859-9");
        this.localeToCharsetMap.put("uk", "ISO-8859-5");
        this.localeToCharsetMap.put("zh", "GB2312");
        this.localeToCharsetMap.put("zh_TW", "Big5");
    }

    public void clearEncodingMap() {
        this.localeToCharsetMap.clear();
    }

    public static Configuration getDefaultConfiguration() {
        return defaultConfig;
    }

    public static void setDefaultConfiguration(Configuration configuration) {
        defaultConfig = configuration;
    }

    public synchronized void setTemplateLoader(TemplateLoader templateLoader) {
        createTemplateCache(templateLoader, this.cache.getCacheStorage());
    }

    private void createTemplateCache(TemplateLoader templateLoader, CacheStorage cacheStorage) {
        TemplateCache templateCache = this.cache;
        TemplateCache templateCache2 = new TemplateCache(templateLoader, cacheStorage);
        this.cache = templateCache2;
        templateCache2.setDelay(templateCache.getDelay());
        this.cache.setConfiguration(this);
        this.cache.setLocalizedLookup(this.localizedLookup);
    }

    public TemplateLoader getTemplateLoader() {
        return this.cache.getTemplateLoader();
    }

    public synchronized void setCacheStorage(CacheStorage cacheStorage) {
        createTemplateCache(this.cache.getTemplateLoader(), cacheStorage);
    }

    public synchronized CacheStorage getCacheStorage() {
        return this.cache.getCacheStorage();
    }

    public void setDirectoryForTemplateLoading(File file) throws IOException {
        TemplateLoader templateLoader = getTemplateLoader();
        if (!(templateLoader instanceof FileTemplateLoader) || !((FileTemplateLoader) templateLoader).baseDir.getCanonicalPath().equals(file.getCanonicalPath())) {
            setTemplateLoader(new FileTemplateLoader(file));
        }
    }

    public void setServletContextForTemplateLoading(Object obj, String str) {
        Class[] clsArr;
        Object[] objArr;
        try {
            Class forName = ClassUtil.forName("freemarker.cache.WebappTemplateLoader");
            Class forName2 = ClassUtil.forName("javax.servlet.ServletContext");
            if (str == null) {
                clsArr = new Class[]{forName2};
                objArr = new Object[]{obj};
            } else {
                Class[] clsArr2 = new Class[2];
                clsArr2[0] = forName2;
                Class cls = class$java$lang$String;
                if (cls == null) {
                    cls = class$("java.lang.String");
                    class$java$lang$String = cls;
                }
                clsArr2[1] = cls;
                objArr = new Object[]{obj, str};
                clsArr = clsArr2;
            }
            setTemplateLoader((TemplateLoader) forName.getConstructor(clsArr).newInstance(objArr));
        } catch (Exception e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Internal FreeMarker error: ");
            stringBuffer.append(e);
            throw new RuntimeException(stringBuffer.toString());
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public void setClassForTemplateLoading(Class cls, String str) {
        setTemplateLoader(new ClassTemplateLoader(cls, str));
    }

    public void setTemplateUpdateDelay(int i) {
        this.cache.setDelay(((long) i) * 1000);
    }

    public void setStrictSyntaxMode(boolean z) {
        this.strictSyntax = z;
    }

    public boolean getStrictSyntaxMode() {
        return this.strictSyntax;
    }

    public void setIncompatibleImprovements(Version version2) {
        this.incompatibleImprovements = version2;
    }

    public Version getIncompatibleImprovements() {
        return this.incompatibleImprovements;
    }

    public void setIncompatibleEnhancements(String str) {
        setIncompatibleImprovements(new Version(str));
    }

    public String getIncompatibleEnhancements() {
        return this.incompatibleImprovements.toString();
    }

    public int getParsedIncompatibleEnhancements() {
        return getIncompatibleImprovements().intValue();
    }

    public void setWhitespaceStripping(boolean z) {
        this.whitespaceStripping = z;
    }

    public boolean getWhitespaceStripping() {
        return this.whitespaceStripping;
    }

    public void setTagSyntax(int i) {
        if (i == 0 || i == 2 || i == 1) {
            this.tagSyntax = i;
            return;
        }
        throw new IllegalArgumentException("\"tag_syntax\" can only be set to one of these: Configuration.AUTO_DETECT_TAG_SYNTAX, Configuration.ANGLE_BRACKET_SYNTAX, or Configuration.SQAUARE_BRACKET_SYNTAX");
    }

    public int getTagSyntax() {
        return this.tagSyntax;
    }

    public Template getTemplate(String str) throws IOException {
        Locale locale = getLocale();
        return getTemplate(str, locale, getEncoding(locale), true);
    }

    public Template getTemplate(String str, Locale locale) throws IOException {
        return getTemplate(str, locale, getEncoding(locale), true);
    }

    public Template getTemplate(String str, String str2) throws IOException {
        return getTemplate(str, getLocale(), str2, true);
    }

    public Template getTemplate(String str, Locale locale, String str2) throws IOException {
        return getTemplate(str, locale, str2, true);
    }

    public Template getTemplate(String str, Locale locale, String str2, boolean z) throws IOException {
        Template template = this.cache.getTemplate(str, locale, str2, z);
        if (template != null) {
            return template;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Template ");
        stringBuffer.append(StringUtil.jQuote(str));
        stringBuffer.append(" not found.");
        throw new FileNotFoundException(stringBuffer.toString());
    }

    public void setDefaultEncoding(String str) {
        this.defaultEncoding = str;
    }

    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }

    public String getEncoding(Locale locale) {
        if (this.localeToCharsetMap.isEmpty()) {
            return this.defaultEncoding;
        }
        String str = (String) this.localeToCharsetMap.get(locale.toString());
        if (str == null) {
            if (locale.getVariant().length() > 0) {
                String str2 = (String) this.localeToCharsetMap.get(new Locale(locale.getLanguage(), locale.getCountry()).toString());
                if (str2 != null) {
                    this.localeToCharsetMap.put(locale.toString(), str2);
                }
            }
            str = (String) this.localeToCharsetMap.get(locale.getLanguage());
            if (str != null) {
                this.localeToCharsetMap.put(locale.toString(), str);
            }
        }
        return str != null ? str : this.defaultEncoding;
    }

    public void setEncoding(Locale locale, String str) {
        this.localeToCharsetMap.put(locale.toString(), str);
    }

    public void setSharedVariable(String str, TemplateModel templateModel) {
        this.sharedVariables.put(str, templateModel);
    }

    public Set getSharedVariableNames() {
        return new HashSet(this.sharedVariables.keySet());
    }

    public void setSharedVariable(String str, Object obj) throws TemplateModelException {
        setSharedVariable(str, getObjectWrapper().wrap(obj));
    }

    public void setAllSharedVariables(TemplateHashModelEx templateHashModelEx) throws TemplateModelException {
        TemplateModelIterator it = templateHashModelEx.keys().iterator();
        TemplateModelIterator it2 = templateHashModelEx.values().iterator();
        while (it.hasNext()) {
            setSharedVariable(((TemplateScalarModel) it.next()).getAsString(), it2.next());
        }
    }

    public TemplateModel getSharedVariable(String str) {
        return (TemplateModel) this.sharedVariables.get(str);
    }

    public void clearSharedVariables() {
        this.sharedVariables.clear();
        loadBuiltInSharedVariables();
    }

    public void clearTemplateCache() {
        this.cache.clear();
    }

    public void removeTemplateFromCache(String str) throws IOException {
        Locale locale = getLocale();
        removeTemplateFromCache(str, locale, getEncoding(locale), true);
    }

    public void removeTemplateFromCache(String str, Locale locale) throws IOException {
        removeTemplateFromCache(str, locale, getEncoding(locale), true);
    }

    public void removeTemplateFromCache(String str, String str2) throws IOException {
        removeTemplateFromCache(str, getLocale(), str2, true);
    }

    public void removeTemplateFromCache(String str, Locale locale, String str2) throws IOException {
        removeTemplateFromCache(str, locale, str2, true);
    }

    public void removeTemplateFromCache(String str, Locale locale, String str2, boolean z) throws IOException {
        this.cache.removeTemplate(str, locale, str2, z);
    }

    public boolean getLocalizedLookup() {
        return this.cache.getLocalizedLookup();
    }

    public void setLocalizedLookup(boolean z) {
        this.localizedLookup = z;
        this.cache.setLocalizedLookup(z);
    }

    public void setSetting(String str, String str2) throws TemplateException {
        try {
            if ("TemplateUpdateInterval".equalsIgnoreCase(str)) {
                str = TEMPLATE_UPDATE_DELAY_KEY;
            } else if ("DefaultEncoding".equalsIgnoreCase(str)) {
                str = DEFAULT_ENCODING_KEY;
            }
            if (DEFAULT_ENCODING_KEY.equals(str)) {
                setDefaultEncoding(str2);
            } else if (LOCALIZED_LOOKUP_KEY.equals(str)) {
                setLocalizedLookup(StringUtil.getYesNo(str2));
            } else if (STRICT_SYNTAX_KEY.equals(str)) {
                setStrictSyntaxMode(StringUtil.getYesNo(str2));
            } else if (WHITESPACE_STRIPPING_KEY.equals(str)) {
                setWhitespaceStripping(StringUtil.getYesNo(str2));
            } else if (CACHE_STORAGE_KEY.equals(str)) {
                if (str2.indexOf(46) == -1) {
                    int i = 0;
                    int i2 = 0;
                    for (Map.Entry entry : StringUtil.parseNameValuePairList(str2, String.valueOf(Integer.MAX_VALUE)).entrySet()) {
                        String str3 = (String) entry.getKey();
                        int parseInt = Integer.parseInt((String) entry.getValue());
                        if ("soft".equalsIgnoreCase(str3)) {
                            i = parseInt;
                        } else if ("strong".equalsIgnoreCase(str3)) {
                            i2 = parseInt;
                        } else {
                            throw invalidSettingValueException(str, str2);
                        }
                    }
                    if (i == 0) {
                        if (i2 == 0) {
                            throw invalidSettingValueException(str, str2);
                        }
                    }
                    setCacheStorage(new MruCacheStorage(i2, i));
                    return;
                }
                setCacheStorage((CacheStorage) ClassUtil.forName(str2).newInstance());
            } else if (TEMPLATE_UPDATE_DELAY_KEY.equals(str)) {
                setTemplateUpdateDelay(Integer.parseInt(str2));
            } else if (AUTO_INCLUDE_KEY.equals(str)) {
                setAutoIncludes(parseAsList(str2));
            } else if (AUTO_IMPORT_KEY.equals(str)) {
                setAutoImports(parseAsImportList(str2));
            } else if (TAG_SYNTAX_KEY.equals(str)) {
                if ("auto_detect".equals(str2)) {
                    setTagSyntax(0);
                } else if ("angle_bracket".equals(str2)) {
                    setTagSyntax(1);
                } else if ("square_bracket".equals(str2)) {
                    setTagSyntax(2);
                } else {
                    throw invalidSettingValueException(str, str2);
                }
            } else if (INCOMPATIBLE_IMPROVEMENTS.equals(str)) {
                setIncompatibleImprovements(new Version(str2));
            } else if (INCOMPATIBLE_ENHANCEMENTS.equals(str)) {
                setIncompatibleEnhancements(str2);
            } else {
                super.setSetting(str, str2);
            }
        } catch (NumberFormatException unused) {
            throw invalidSettingValueException(str, str2);
        } catch (Exception e) {
            throw new _MiscTemplateException((Throwable) e, getEnvironment(), new Object[]{"Failed to set setting ", new _DelayedJQuote(str), " to value ", new _DelayedJQuote(str2), "; see cause exception."});
        }
    }

    public synchronized void addAutoImport(String str, String str2) {
        this.autoImports.remove(str);
        this.autoImports.add(str);
        this.autoImportNsToTmpMap.put(str, str2);
    }

    public synchronized void removeAutoImport(String str) {
        this.autoImports.remove(str);
        this.autoImportNsToTmpMap.remove(str);
    }

    public synchronized void setAutoImports(Map map) {
        this.autoImports = new ArrayList(map.keySet());
        if (map instanceof HashMap) {
            this.autoImportNsToTmpMap = (Map) ((HashMap) map).clone();
        } else if (map instanceof SortedMap) {
            this.autoImportNsToTmpMap = new TreeMap(map);
        } else {
            this.autoImportNsToTmpMap = new HashMap(map);
        }
    }

    /* access modifiers changed from: protected */
    public void doAutoImportsAndIncludes(Environment environment) throws TemplateException, IOException {
        for (int i = 0; i < this.autoImports.size(); i++) {
            String str = (String) this.autoImports.get(i);
            environment.importLib((String) this.autoImportNsToTmpMap.get(str), str);
        }
        for (int i2 = 0; i2 < this.autoIncludes.size(); i2++) {
            environment.include(getTemplate((String) this.autoIncludes.get(i2), environment.getLocale()));
        }
    }

    public synchronized void addAutoInclude(String str) {
        this.autoIncludes.remove(str);
        this.autoIncludes.add(str);
    }

    public synchronized void setAutoIncludes(List list) {
        this.autoIncludes.clear();
        for (Object next : list) {
            if (next instanceof String) {
                this.autoIncludes.add(next);
            } else {
                throw new IllegalArgumentException("List items must be String-s.");
            }
        }
    }

    public synchronized void removeAutoInclude(String str) {
        this.autoIncludes.remove(str);
    }

    public static String getVersionNumber() {
        if (!versionPropertiesLoaded) {
            loadVersionProperties();
        }
        return versionNumber;
    }

    public static Version getVersion() {
        if (!versionPropertiesLoaded) {
            loadVersionProperties();
        }
        return version;
    }

    private static void loadVersionProperties() {
        InputStream resourceAsStream;
        Date date;
        try {
            Properties properties = new Properties();
            Class cls = class$freemarker$template$Configuration;
            if (cls == null) {
                cls = class$("freemarker.template.Configuration");
                class$freemarker$template$Configuration = cls;
            }
            resourceAsStream = cls.getClassLoader().getResourceAsStream("freemarker/version.properties");
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                resourceAsStream.close();
                String requiredVersionProperty = getRequiredVersionProperty(properties, "version");
                versionNumber = requiredVersionProperty;
                String requiredVersionProperty2 = getRequiredVersionProperty(properties, "buildTimestamp");
                if (requiredVersionProperty2.endsWith("Z")) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(requiredVersionProperty2.substring(0, requiredVersionProperty2.length() - 1));
                    stringBuffer.append("+0000");
                    requiredVersionProperty2 = stringBuffer.toString();
                }
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).parse(requiredVersionProperty2);
                } catch (ParseException unused) {
                    date = null;
                }
                version = new Version(requiredVersionProperty, Boolean.valueOf(getRequiredVersionProperty(properties, "isGAECompliant")), date);
                versionPropertiesLoaded = true;
                return;
            }
            throw new RuntimeException("Version file is missing.");
        } catch (IOException e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Failed to load version file: ");
            stringBuffer2.append(e);
            throw new RuntimeException(stringBuffer2.toString());
        } catch (Throwable th) {
            resourceAsStream.close();
            throw th;
        }
    }

    public Set getSupportedBuiltInNames() {
        return _CoreAPI.getSupportedBuiltInNames();
    }

    private static String getRequiredVersionProperty(Properties properties, String str) {
        String property = properties.getProperty(str);
        if (property != null) {
            return property;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Version file is corrupt: \"");
        stringBuffer.append(str);
        stringBuffer.append("\" property is missing.");
        throw new RuntimeException(stringBuffer.toString());
    }
}
