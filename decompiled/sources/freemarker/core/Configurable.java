package freemarker.core;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.utility.ClassUtil;
import freemarker.template.utility.NullArgumentException;
import freemarker.template.utility.StringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

public class Configurable {
    private static final String ALLOWED_CLASSES = "allowed_classes";
    public static final String ARITHMETIC_ENGINE_KEY = "arithmetic_engine";
    public static final String AUTO_FLUSH_KEY = "auto_flush";
    public static final String BOOLEAN_FORMAT_KEY = "boolean_format";
    public static final String CLASSIC_COMPATIBLE_KEY = "classic_compatible";
    static final String C_TRUE_FALSE = "true,false";
    public static final String DATETIME_FORMAT_KEY = "datetime_format";
    public static final String DATE_FORMAT_KEY = "date_format";
    public static final String LOCALE_KEY = "locale";
    public static final String NEW_BUILTIN_CLASS_RESOLVER_KEY = "new_builtin_class_resolver";
    public static final String NUMBER_FORMAT_KEY = "number_format";
    public static final String OBJECT_WRAPPER_KEY = "object_wrapper";
    public static final String OUTPUT_ENCODING_KEY = "output_encoding";
    public static final String STRICT_BEAN_MODELS = "strict_bean_models";
    public static final String TEMPLATE_EXCEPTION_HANDLER_KEY = "template_exception_handler";
    public static final String TIME_FORMAT_KEY = "time_format";
    public static final String TIME_ZONE_KEY = "time_zone";
    private static final String TRUSTED_TEMPLATES = "trusted_templates";
    public static final String URL_ESCAPING_CHARSET_KEY = "url_escaping_charset";
    static /* synthetic */ Class class$freemarker$ext$beans$BeansWrapper;
    private ArithmeticEngine arithmeticEngine;
    private Boolean autoFlush;
    private String booleanFormat;
    private Integer classicCompatible;
    private HashMap customAttributes;
    private String dateFormat;
    private String dateTimeFormat;
    private String falseStringValue;
    private Locale locale;
    private TemplateClassResolver newBuiltinClassResolver;
    private String numberFormat;
    private ObjectWrapper objectWrapper;
    private String outputEncoding;
    private boolean outputEncodingSet;
    private Configurable parent;
    private Properties properties;
    private TemplateExceptionHandler templateExceptionHandler;
    private String timeFormat;
    private TimeZone timeZone;
    private String trueStringValue;
    private String urlEscapingCharset;
    private boolean urlEscapingCharsetSet;

    public Configurable() {
        this.parent = null;
        this.locale = Locale.getDefault();
        this.timeZone = TimeZone.getDefault();
        this.numberFormat = "number";
        this.timeFormat = "";
        this.dateFormat = "";
        this.dateTimeFormat = "";
        this.classicCompatible = new Integer(0);
        this.templateExceptionHandler = TemplateExceptionHandler.DEBUG_HANDLER;
        this.arithmeticEngine = ArithmeticEngine.BIGDECIMAL_ENGINE;
        this.objectWrapper = ObjectWrapper.DEFAULT_WRAPPER;
        this.autoFlush = Boolean.TRUE;
        this.newBuiltinClassResolver = TemplateClassResolver.UNRESTRICTED_RESOLVER;
        Properties properties2 = new Properties();
        this.properties = properties2;
        properties2.setProperty(LOCALE_KEY, this.locale.toString());
        this.properties.setProperty(TIME_FORMAT_KEY, this.timeFormat);
        this.properties.setProperty(DATE_FORMAT_KEY, this.dateFormat);
        this.properties.setProperty(DATETIME_FORMAT_KEY, this.dateTimeFormat);
        this.properties.setProperty(TIME_ZONE_KEY, this.timeZone.getID());
        this.properties.setProperty(NUMBER_FORMAT_KEY, this.numberFormat);
        this.properties.setProperty(CLASSIC_COMPATIBLE_KEY, this.classicCompatible.toString());
        this.properties.setProperty(TEMPLATE_EXCEPTION_HANDLER_KEY, this.templateExceptionHandler.getClass().getName());
        this.properties.setProperty(ARITHMETIC_ENGINE_KEY, this.arithmeticEngine.getClass().getName());
        this.properties.setProperty(AUTO_FLUSH_KEY, this.autoFlush.toString());
        this.properties.setProperty(NEW_BUILTIN_CLASS_RESOLVER_KEY, this.newBuiltinClassResolver.getClass().getName());
        setBooleanFormat(C_TRUE_FALSE);
        this.customAttributes = new HashMap();
    }

    public Configurable(Configurable configurable) {
        this.parent = configurable;
        this.locale = null;
        this.numberFormat = null;
        this.classicCompatible = null;
        this.templateExceptionHandler = null;
        this.properties = new Properties(configurable.properties);
        this.customAttributes = new HashMap();
    }

    /* access modifiers changed from: protected */
    public Object clone() throws CloneNotSupportedException {
        Configurable configurable = (Configurable) super.clone();
        configurable.properties = new Properties(this.properties);
        configurable.customAttributes = (HashMap) this.customAttributes.clone();
        return configurable;
    }

    public final Configurable getParent() {
        return this.parent;
    }

    /* access modifiers changed from: package-private */
    public final void setParent(Configurable configurable) {
        this.parent = configurable;
    }

    public void setClassicCompatible(boolean z) {
        Integer num = new Integer(z ? 1 : 0);
        this.classicCompatible = num;
        this.properties.setProperty(CLASSIC_COMPATIBLE_KEY, classicCompatibilityIntToString(num));
    }

    public void setClassicCompatibleAsInt(int i) {
        if (i < 0 || i > 2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unsupported \"classicCompatibility\": ");
            stringBuffer.append(i);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        this.classicCompatible = new Integer(i);
    }

    private String classicCompatibilityIntToString(Integer num) {
        if (num == null) {
            return null;
        }
        if (num.intValue() == 0) {
            return "false";
        }
        if (num.intValue() == 1) {
            return "true";
        }
        return num.toString();
    }

    public boolean isClassicCompatible() {
        Integer num = this.classicCompatible;
        if (num != null) {
            return num.intValue() != 0;
        }
        return this.parent.isClassicCompatible();
    }

    public int getClassicCompatibleAsInt() {
        Integer num = this.classicCompatible;
        return num != null ? num.intValue() : this.parent.getClassicCompatibleAsInt();
    }

    public void setLocale(Locale locale2) {
        NullArgumentException.check(LOCALE_KEY, locale2);
        this.locale = locale2;
        this.properties.setProperty(LOCALE_KEY, locale2.toString());
    }

    public TimeZone getTimeZone() {
        TimeZone timeZone2 = this.timeZone;
        return timeZone2 != null ? timeZone2 : this.parent.getTimeZone();
    }

    public void setTimeZone(TimeZone timeZone2) {
        NullArgumentException.check("timeZone", timeZone2);
        this.timeZone = timeZone2;
        this.properties.setProperty(TIME_ZONE_KEY, timeZone2.getID());
    }

    public Locale getLocale() {
        Locale locale2 = this.locale;
        return locale2 != null ? locale2 : this.parent.getLocale();
    }

    public void setNumberFormat(String str) {
        NullArgumentException.check("numberFormat", str);
        this.numberFormat = str;
        this.properties.setProperty(NUMBER_FORMAT_KEY, str);
    }

    public String getNumberFormat() {
        String str = this.numberFormat;
        return str != null ? str : this.parent.getNumberFormat();
    }

    public void setBooleanFormat(String str) {
        NullArgumentException.check("booleanFormat", str);
        int indexOf = str.indexOf(44);
        if (indexOf != -1) {
            this.booleanFormat = str;
            this.properties.setProperty(BOOLEAN_FORMAT_KEY, str);
            if (str.equals(C_TRUE_FALSE)) {
                this.trueStringValue = null;
                this.falseStringValue = null;
                return;
            }
            this.trueStringValue = str.substring(0, indexOf);
            this.falseStringValue = str.substring(indexOf + 1);
            return;
        }
        throw new IllegalArgumentException("Setting \"boolean_format\" must consist of two comma-separated values for true and false,respectively.");
    }

    public String getBooleanFormat() {
        String str = this.booleanFormat;
        return str != null ? str : this.parent.getBooleanFormat();
    }

    /* access modifiers changed from: package-private */
    public String formatBoolean(boolean z, boolean z2) throws TemplateException {
        if (z) {
            String trueStringValue2 = getTrueStringValue();
            if (trueStringValue2 != null) {
                return trueStringValue2;
            }
            if (z2) {
                return "true";
            }
            throw new _MiscTemplateException(getNullBooleanFormatErrorDescription());
        }
        String falseStringValue2 = getFalseStringValue();
        if (falseStringValue2 != null) {
            return falseStringValue2;
        }
        if (z2) {
            return "false";
        }
        throw new _MiscTemplateException(getNullBooleanFormatErrorDescription());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private freemarker.core._ErrorDescriptionBuilder getNullBooleanFormatErrorDescription() {
        /*
            r9 = this;
            freemarker.core._ErrorDescriptionBuilder r0 = new freemarker.core._ErrorDescriptionBuilder
            r1 = 5
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "Can't convert boolean to string automatically, because the \""
            r3 = 0
            r1[r3] = r2
            r2 = 1
            java.lang.String r4 = "boolean_format"
            r1[r2] = r4
            java.lang.String r5 = "\" setting was "
            r6 = 2
            r1[r6] = r5
            freemarker.core._DelayedJQuote r5 = new freemarker.core._DelayedJQuote
            java.lang.String r7 = r9.getBooleanFormat()
            r5.<init>(r7)
            r7 = 3
            r1[r7] = r5
            java.lang.String r5 = r9.getBooleanFormat()
            java.lang.String r8 = "true,false"
            boolean r5 = r5.equals(r8)
            if (r5 == 0) goto L_0x002f
            java.lang.String r5 = ", which is the legacy default computer-language format, and hence isn't accepted."
            goto L_0x0031
        L_0x002f:
            java.lang.String r5 = "."
        L_0x0031:
            r8 = 4
            r1[r8] = r5
            r0.<init>((java.lang.Object[]) r1)
            java.lang.Object[] r1 = new java.lang.Object[r7]
            java.lang.String r5 = "If you just want \"true\"/\"false\" result as you are generting computer-language output, use \"?c\", like ${myBool?c}."
            r1[r3] = r5
            java.lang.String r5 = "You can write myBool?string('yes', 'no') and like to specify boolean formatting in place."
            r1[r2] = r5
            java.lang.Object[] r5 = new java.lang.Object[r7]
            java.lang.String r7 = "If you need the same two values on most places, the programmers should set the \""
            r5[r3] = r7
            r5[r2] = r4
            java.lang.String r2 = "\" setting to something like \"yes,no\"."
            r5[r6] = r2
            r1[r6] = r5
            freemarker.core._ErrorDescriptionBuilder r0 = r0.tips((java.lang.Object[]) r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.Configurable.getNullBooleanFormatErrorDescription():freemarker.core._ErrorDescriptionBuilder");
    }

    /* access modifiers changed from: package-private */
    public String getTrueStringValue() {
        if (this.booleanFormat != null) {
            return this.trueStringValue;
        }
        Configurable configurable = this.parent;
        if (configurable != null) {
            return configurable.getTrueStringValue();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getFalseStringValue() {
        if (this.booleanFormat != null) {
            return this.falseStringValue;
        }
        Configurable configurable = this.parent;
        if (configurable != null) {
            return configurable.getFalseStringValue();
        }
        return null;
    }

    public void setTimeFormat(String str) {
        NullArgumentException.check("timeFormat", str);
        this.timeFormat = str;
        this.properties.setProperty(TIME_FORMAT_KEY, str);
    }

    public String getTimeFormat() {
        String str = this.timeFormat;
        return str != null ? str : this.parent.getTimeFormat();
    }

    public void setDateFormat(String str) {
        NullArgumentException.check("dateFormat", str);
        this.dateFormat = str;
        this.properties.setProperty(DATE_FORMAT_KEY, str);
    }

    public String getDateFormat() {
        String str = this.dateFormat;
        return str != null ? str : this.parent.getDateFormat();
    }

    public void setDateTimeFormat(String str) {
        NullArgumentException.check("dateTimeFormat", str);
        this.dateTimeFormat = str;
        this.properties.setProperty(DATETIME_FORMAT_KEY, str);
    }

    public String getDateTimeFormat() {
        String str = this.dateTimeFormat;
        return str != null ? str : this.parent.getDateTimeFormat();
    }

    public void setTemplateExceptionHandler(TemplateExceptionHandler templateExceptionHandler2) {
        NullArgumentException.check("templateExceptionHandler", templateExceptionHandler2);
        this.templateExceptionHandler = templateExceptionHandler2;
        this.properties.setProperty(TEMPLATE_EXCEPTION_HANDLER_KEY, templateExceptionHandler2.getClass().getName());
    }

    public TemplateExceptionHandler getTemplateExceptionHandler() {
        TemplateExceptionHandler templateExceptionHandler2 = this.templateExceptionHandler;
        return templateExceptionHandler2 != null ? templateExceptionHandler2 : this.parent.getTemplateExceptionHandler();
    }

    public void setArithmeticEngine(ArithmeticEngine arithmeticEngine2) {
        NullArgumentException.check("arithmeticEngine", arithmeticEngine2);
        this.arithmeticEngine = arithmeticEngine2;
        this.properties.setProperty(ARITHMETIC_ENGINE_KEY, arithmeticEngine2.getClass().getName());
    }

    public ArithmeticEngine getArithmeticEngine() {
        ArithmeticEngine arithmeticEngine2 = this.arithmeticEngine;
        return arithmeticEngine2 != null ? arithmeticEngine2 : this.parent.getArithmeticEngine();
    }

    public void setObjectWrapper(ObjectWrapper objectWrapper2) {
        NullArgumentException.check("objectWrapper", objectWrapper2);
        this.objectWrapper = objectWrapper2;
        this.properties.setProperty(OBJECT_WRAPPER_KEY, objectWrapper2.getClass().getName());
    }

    public ObjectWrapper getObjectWrapper() {
        ObjectWrapper objectWrapper2 = this.objectWrapper;
        return objectWrapper2 != null ? objectWrapper2 : this.parent.getObjectWrapper();
    }

    public void setOutputEncoding(String str) {
        this.outputEncoding = str;
        if (str != null) {
            this.properties.setProperty(OUTPUT_ENCODING_KEY, str);
        } else {
            this.properties.remove(OUTPUT_ENCODING_KEY);
        }
        this.outputEncodingSet = true;
    }

    public String getOutputEncoding() {
        if (this.outputEncodingSet) {
            return this.outputEncoding;
        }
        Configurable configurable = this.parent;
        if (configurable != null) {
            return configurable.getOutputEncoding();
        }
        return null;
    }

    public void setURLEscapingCharset(String str) {
        this.urlEscapingCharset = str;
        if (str != null) {
            this.properties.setProperty(URL_ESCAPING_CHARSET_KEY, str);
        } else {
            this.properties.remove(URL_ESCAPING_CHARSET_KEY);
        }
        this.urlEscapingCharsetSet = true;
    }

    public String getURLEscapingCharset() {
        if (this.urlEscapingCharsetSet) {
            return this.urlEscapingCharset;
        }
        Configurable configurable = this.parent;
        if (configurable != null) {
            return configurable.getURLEscapingCharset();
        }
        return null;
    }

    public void setNewBuiltinClassResolver(TemplateClassResolver templateClassResolver) {
        NullArgumentException.check("newBuiltinClassResolver", templateClassResolver);
        this.newBuiltinClassResolver = templateClassResolver;
        this.properties.setProperty(NEW_BUILTIN_CLASS_RESOLVER_KEY, templateClassResolver.getClass().getName());
    }

    public TemplateClassResolver getNewBuiltinClassResolver() {
        TemplateClassResolver templateClassResolver = this.newBuiltinClassResolver;
        return templateClassResolver != null ? templateClassResolver : this.parent.getNewBuiltinClassResolver();
    }

    public void setAutoFlush(boolean z) {
        this.autoFlush = z ? Boolean.TRUE : Boolean.FALSE;
        this.properties.setProperty(AUTO_FLUSH_KEY, String.valueOf(z));
    }

    public boolean getAutoFlush() {
        Boolean bool = this.autoFlush;
        if (bool != null) {
            return bool.booleanValue();
        }
        Configurable configurable = this.parent;
        if (configurable != null) {
            return configurable.getAutoFlush();
        }
        return true;
    }

    public void setSetting(String str, String str2) throws TemplateException {
        try {
            if (LOCALE_KEY.equals(str)) {
                setLocale(StringUtil.deduceLocale(str2));
            } else if (NUMBER_FORMAT_KEY.equals(str)) {
                setNumberFormat(str2);
            } else if (TIME_FORMAT_KEY.equals(str)) {
                setTimeFormat(str2);
            } else if (DATE_FORMAT_KEY.equals(str)) {
                setDateFormat(str2);
            } else if (DATETIME_FORMAT_KEY.equals(str)) {
                setDateTimeFormat(str2);
            } else if (TIME_ZONE_KEY.equals(str)) {
                setTimeZone(TimeZone.getTimeZone(str2));
            } else if (CLASSIC_COMPATIBLE_KEY.equals(str)) {
                char charAt = (str2 == null || str2.length() <= 0) ? 0 : str2.charAt(0);
                if (!Character.isDigit(charAt) && charAt != '+') {
                    if (charAt != '-') {
                        setClassicCompatible(StringUtil.getYesNo(str2));
                        return;
                    }
                }
                setClassicCompatibleAsInt(Integer.parseInt(str2));
            } else if (TEMPLATE_EXCEPTION_HANDLER_KEY.equals(str)) {
                if (str2.indexOf(46) != -1) {
                    setTemplateExceptionHandler((TemplateExceptionHandler) ClassUtil.forName(str2).newInstance());
                } else if ("debug".equalsIgnoreCase(str2)) {
                    setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
                } else if ("html_debug".equalsIgnoreCase(str2)) {
                    setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
                } else if ("ignore".equalsIgnoreCase(str2)) {
                    setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
                } else if ("rethrow".equalsIgnoreCase(str2)) {
                    setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                } else {
                    throw invalidSettingValueException(str, str2);
                }
            } else if (!ARITHMETIC_ENGINE_KEY.equals(str)) {
                HashSet hashSet = null;
                if (OBJECT_WRAPPER_KEY.equals(str)) {
                    if (str2.indexOf(46) != -1) {
                        setObjectWrapper((ObjectWrapper) ClassUtil.forName(str2).newInstance());
                    } else if ("default".equalsIgnoreCase(str2)) {
                        setObjectWrapper(ObjectWrapper.DEFAULT_WRAPPER);
                    } else if ("simple".equalsIgnoreCase(str2)) {
                        setObjectWrapper(ObjectWrapper.SIMPLE_WRAPPER);
                    } else if ("beans".equalsIgnoreCase(str2)) {
                        setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
                    } else if ("jython".equalsIgnoreCase(str2)) {
                        setObjectWrapper((ObjectWrapper) Class.forName("freemarker.ext.jython.JythonWrapper").getField("INSTANCE").get((Object) null));
                    } else {
                        throw invalidSettingValueException(str, str2);
                    }
                } else if (BOOLEAN_FORMAT_KEY.equals(str)) {
                    setBooleanFormat(str2);
                } else if (OUTPUT_ENCODING_KEY.equals(str)) {
                    setOutputEncoding(str2);
                } else if (URL_ESCAPING_CHARSET_KEY.equals(str)) {
                    setURLEscapingCharset(str2);
                } else if (STRICT_BEAN_MODELS.equals(str)) {
                    setStrictBeanModels(StringUtil.getYesNo(str2));
                } else if (AUTO_FLUSH_KEY.equals(str)) {
                    setAutoFlush(StringUtil.getYesNo(str2));
                } else if (!NEW_BUILTIN_CLASS_RESOLVER_KEY.equals(str)) {
                    throw unknownSettingException(str);
                } else if ("unrestricted".equals(str2)) {
                    setNewBuiltinClassResolver(TemplateClassResolver.UNRESTRICTED_RESOLVER);
                } else if ("safer".equals(str2)) {
                    setNewBuiltinClassResolver(TemplateClassResolver.SAFER_RESOLVER);
                } else if ("allows_nothing".equals(str2)) {
                    setNewBuiltinClassResolver(TemplateClassResolver.ALLOWS_NOTHING_RESOLVER);
                } else if (str2.indexOf(":") != -1) {
                    ArrayList parseAsSegmentedList = parseAsSegmentedList(str2);
                    List list = null;
                    for (int i = 0; i < parseAsSegmentedList.size(); i++) {
                        KeyValuePair keyValuePair = (KeyValuePair) parseAsSegmentedList.get(i);
                        String str3 = (String) keyValuePair.getKey();
                        List list2 = (List) keyValuePair.getValue();
                        if (str3.equals(ALLOWED_CLASSES)) {
                            hashSet = new HashSet(list2);
                        } else if (str3.equals(TRUSTED_TEMPLATES)) {
                            list = list2;
                        } else {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("Unrecognized list segment key: ");
                            stringBuffer.append(StringUtil.jQuote(str3));
                            stringBuffer.append(". Supported keys are: \"");
                            stringBuffer.append(ALLOWED_CLASSES);
                            stringBuffer.append("\", \"");
                            stringBuffer.append(TRUSTED_TEMPLATES);
                            stringBuffer.append("\"");
                            throw new ParseException(stringBuffer.toString(), 0, 0);
                        }
                    }
                    setNewBuiltinClassResolver(new OptInTemplateClassResolver(hashSet, list));
                } else if (str2.indexOf(46) == -1) {
                    setNewBuiltinClassResolver((TemplateClassResolver) ClassUtil.forName(str2).newInstance());
                } else {
                    throw invalidSettingValueException(str, str2);
                }
            } else if (str2.indexOf(46) != -1) {
                setArithmeticEngine((ArithmeticEngine) ClassUtil.forName(str2).newInstance());
            } else if ("bigdecimal".equalsIgnoreCase(str2)) {
                setArithmeticEngine(ArithmeticEngine.BIGDECIMAL_ENGINE);
            } else if ("conservative".equalsIgnoreCase(str2)) {
                setArithmeticEngine(ArithmeticEngine.CONSERVATIVE_ENGINE);
            } else {
                throw invalidSettingValueException(str, str2);
            }
        } catch (Exception e) {
            throw new _MiscTemplateException((Throwable) e, getEnvironment(), new Object[]{"Failed to set setting ", new _DelayedJQuote(str), " to value ", new _DelayedJQuote(str2), "; see cause exception."});
        }
    }

    public void setStrictBeanModels(boolean z) {
        ObjectWrapper objectWrapper2 = this.objectWrapper;
        if (!(objectWrapper2 instanceof BeansWrapper)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("The value of the object_wrapper setting isn't a ");
            Class cls = class$freemarker$ext$beans$BeansWrapper;
            if (cls == null) {
                cls = class$("freemarker.ext.beans.BeansWrapper");
                class$freemarker$ext$beans$BeansWrapper = cls;
            }
            stringBuffer.append(cls.getName());
            stringBuffer.append(".");
            throw new IllegalStateException(stringBuffer.toString());
        }
        ((BeansWrapper) objectWrapper2).setStrict(z);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public String getSetting(String str) {
        return this.properties.getProperty(str);
    }

    public Map getSettings() {
        return Collections.unmodifiableMap(this.properties);
    }

    /* access modifiers changed from: protected */
    public Environment getEnvironment() {
        return this instanceof Environment ? (Environment) this : Environment.getCurrentEnvironment();
    }

    /* access modifiers changed from: protected */
    public TemplateException unknownSettingException(String str) {
        return new UnknownSettingException(str, getEnvironment());
    }

    /* access modifiers changed from: protected */
    public TemplateException invalidSettingValueException(String str, String str2) {
        return new _MiscTemplateException(getEnvironment(), new Object[]{"Invalid value for setting ", new _DelayedJQuote(str), ": ", new _DelayedJQuote(str2)});
    }

    public static class UnknownSettingException extends _MiscTemplateException {
        private UnknownSettingException(String str, Environment environment) {
            super(environment, new Object[]{"Unknown setting: ", new _DelayedJQuote(str)});
        }
    }

    public void setSettings(Properties properties2) throws TemplateException {
        for (String str : properties2.stringPropertyNames()) {
            setSetting(str, properties2.getProperty(str).trim());
        }
    }

    public void setSettings(InputStream inputStream) throws TemplateException, IOException {
        Properties properties2 = new Properties();
        properties2.load(inputStream);
        setSettings(properties2);
    }

    /* access modifiers changed from: package-private */
    public void setCustomAttribute(Object obj, Object obj2) {
        synchronized (this.customAttributes) {
            this.customAttributes.put(obj, obj2);
        }
    }

    /* access modifiers changed from: package-private */
    public Object getCustomAttribute(Object obj, CustomAttribute customAttribute) {
        Object obj2;
        synchronized (this.customAttributes) {
            obj2 = this.customAttributes.get(obj);
            if (obj2 == null && !this.customAttributes.containsKey(obj)) {
                obj2 = customAttribute.create();
                this.customAttributes.put(obj, obj2);
            }
        }
        return obj2;
    }

    public void setCustomAttribute(String str, Object obj) {
        synchronized (this.customAttributes) {
            this.customAttributes.put(str, obj);
        }
    }

    public String[] getCustomAttributeNames() {
        String[] strArr;
        synchronized (this.customAttributes) {
            LinkedList linkedList = new LinkedList(this.customAttributes.keySet());
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                if (!(it.next() instanceof String)) {
                    it.remove();
                }
            }
            strArr = (String[]) linkedList.toArray(new String[linkedList.size()]);
        }
        return strArr;
    }

    public void removeCustomAttribute(String str) {
        synchronized (this.customAttributes) {
            this.customAttributes.remove(str);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        if (r1 != null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        r0 = r3.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        if (r0 == null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        return r0.getCustomAttribute(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0022, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getCustomAttribute(java.lang.String r4) {
        /*
            r3 = this;
            java.util.HashMap r0 = r3.customAttributes
            monitor-enter(r0)
            java.util.HashMap r1 = r3.customAttributes     // Catch:{ all -> 0x0023 }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x0016
            java.util.HashMap r2 = r3.customAttributes     // Catch:{ all -> 0x0023 }
            boolean r2 = r2.containsKey(r4)     // Catch:{ all -> 0x0023 }
            if (r2 == 0) goto L_0x0016
            r4 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            return r4
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x0022
            freemarker.core.Configurable r0 = r3.parent
            if (r0 == 0) goto L_0x0022
            java.lang.Object r4 = r0.getCustomAttribute(r4)
            return r4
        L_0x0022:
            return r1
        L_0x0023:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.Configurable.getCustomAttribute(java.lang.String):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public void doAutoImportsAndIncludes(Environment environment) throws TemplateException, IOException {
        Configurable configurable = this.parent;
        if (configurable != null) {
            configurable.doAutoImportsAndIncludes(environment);
        }
    }

    /* access modifiers changed from: protected */
    public ArrayList parseAsList(String str) throws ParseException {
        return new SettingStringParser(str).parseAsList();
    }

    /* access modifiers changed from: protected */
    public ArrayList parseAsSegmentedList(String str) throws ParseException {
        return new SettingStringParser(str).parseAsSegmentedList();
    }

    /* access modifiers changed from: protected */
    public HashMap parseAsImportList(String str) throws ParseException {
        return new SettingStringParser(str).parseAsImportList();
    }

    private static class KeyValuePair {
        private final Object key;
        private final Object value;

        KeyValuePair(Object obj, Object obj2) {
            this.key = obj;
            this.value = obj2;
        }

        /* access modifiers changed from: package-private */
        public Object getKey() {
            return this.key;
        }

        /* access modifiers changed from: package-private */
        public Object getValue() {
            return this.value;
        }
    }

    private static class SettingStringParser {
        private int ln;
        private int p;
        private String text;

        private SettingStringParser(String str) {
            this.text = str;
            this.p = 0;
            this.ln = str.length();
        }

        /* access modifiers changed from: package-private */
        public ArrayList parseAsSegmentedList() throws ParseException {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = null;
            while (skipWS() != ' ') {
                String fetchStringValue = fetchStringValue();
                char skipWS = skipWS();
                if (skipWS == ':') {
                    arrayList2 = new ArrayList();
                    arrayList.add(new KeyValuePair(fetchStringValue, arrayList2));
                } else if (arrayList2 != null) {
                    arrayList2.add(fetchStringValue);
                } else {
                    throw new ParseException("The very first list item must be followed by \":\" so it will be the key for the following sub-list.", 0, 0);
                }
                if (skipWS == ' ') {
                    break;
                } else if (skipWS == ',' || skipWS == ':') {
                    this.p++;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Expected \",\" or \":\" or the end of text but found \"");
                    stringBuffer.append(skipWS);
                    stringBuffer.append("\"");
                    throw new ParseException(stringBuffer.toString(), 0, 0);
                }
            }
            return arrayList;
        }

        /* access modifiers changed from: package-private */
        public ArrayList parseAsList() throws ParseException {
            ArrayList arrayList = new ArrayList();
            while (skipWS() != ' ') {
                arrayList.add(fetchStringValue());
                char skipWS = skipWS();
                if (skipWS == ' ') {
                    break;
                } else if (skipWS == ',') {
                    this.p++;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Expected \",\" or the end of text but found \"");
                    stringBuffer.append(skipWS);
                    stringBuffer.append("\"");
                    throw new ParseException(stringBuffer.toString(), 0, 0);
                }
            }
            return arrayList;
        }

        /* access modifiers changed from: package-private */
        public HashMap parseAsImportList() throws ParseException {
            HashMap hashMap = new HashMap();
            while (skipWS() != ' ') {
                String fetchStringValue = fetchStringValue();
                if (skipWS() != ' ') {
                    String fetchKeyword = fetchKeyword();
                    if (!fetchKeyword.equalsIgnoreCase("as")) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Expected \"as\", but found ");
                        stringBuffer.append(StringUtil.jQuote(fetchKeyword));
                        throw new ParseException(stringBuffer.toString(), 0, 0);
                    } else if (skipWS() != ' ') {
                        hashMap.put(fetchStringValue(), fetchStringValue);
                        char skipWS = skipWS();
                        if (skipWS == ' ') {
                            break;
                        } else if (skipWS == ',') {
                            this.p++;
                        } else {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("Expected \",\" or the end of text but found \"");
                            stringBuffer2.append(skipWS);
                            stringBuffer2.append("\"");
                            throw new ParseException(stringBuffer2.toString(), 0, 0);
                        }
                    } else {
                        throw new ParseException("Unexpected end of text: expected gate hash name", 0, 0);
                    }
                } else {
                    throw new ParseException("Unexpected end of text: expected \"as\"", 0, 0);
                }
            }
            return hashMap;
        }

        /* access modifiers changed from: package-private */
        public String fetchStringValue() throws ParseException {
            String fetchWord = fetchWord();
            if (fetchWord.startsWith("'") || fetchWord.startsWith("\"")) {
                fetchWord = fetchWord.substring(1, fetchWord.length() - 1);
            }
            return StringUtil.FTLStringLiteralDec(fetchWord);
        }

        /* access modifiers changed from: package-private */
        public String fetchKeyword() throws ParseException {
            String fetchWord = fetchWord();
            if (!fetchWord.startsWith("'") && !fetchWord.startsWith("\"")) {
                return fetchWord;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Keyword expected, but a string value found: ");
            stringBuffer.append(fetchWord);
            throw new ParseException(stringBuffer.toString(), 0, 0);
        }

        /* access modifiers changed from: package-private */
        public char skipWS() {
            while (true) {
                int i = this.p;
                if (i >= this.ln) {
                    return ' ';
                }
                char charAt = this.text.charAt(i);
                if (!Character.isWhitespace(charAt)) {
                    return charAt;
                }
                this.p++;
            }
        }

        private String fetchWord() throws ParseException {
            char charAt;
            int i;
            int i2 = this.p;
            if (i2 != this.ln) {
                char charAt2 = this.text.charAt(i2);
                int i3 = this.p;
                if (charAt2 == '\'' || charAt2 == '\"') {
                    this.p = i3 + 1;
                    boolean z = false;
                    while (true) {
                        int i4 = this.p;
                        if (i4 >= this.ln) {
                            break;
                        }
                        char charAt3 = this.text.charAt(i4);
                        if (z) {
                            z = false;
                        } else if (charAt3 == '\\') {
                            z = true;
                        } else if (charAt3 == charAt2) {
                            break;
                        }
                        this.p++;
                    }
                    int i5 = this.p;
                    if (i5 != this.ln) {
                        int i6 = i5 + 1;
                        this.p = i6;
                        return this.text.substring(i3, i6);
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Missing ");
                    stringBuffer.append(charAt2);
                    throw new ParseException(stringBuffer.toString(), 0, 0);
                }
                do {
                    charAt = this.text.charAt(this.p);
                    if (!Character.isLetterOrDigit(charAt) && charAt != '/' && charAt != '\\' && charAt != '_' && charAt != '.' && charAt != '-' && charAt != '!' && charAt != '*' && charAt != '?') {
                        break;
                    }
                    i = this.p + 1;
                    this.p = i;
                } while (i < this.ln);
                int i7 = this.p;
                if (i3 != i7) {
                    return this.text.substring(i3, i7);
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Unexpected character: ");
                stringBuffer2.append(charAt);
                throw new ParseException(stringBuffer2.toString(), 0, 0);
            }
            throw new ParseException("Unexpeced end of text", 0, 0);
        }
    }
}
