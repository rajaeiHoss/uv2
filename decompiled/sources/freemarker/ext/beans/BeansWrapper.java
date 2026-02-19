package freemarker.ext.beans;

import freemarker.core._ConcurrentMapFactory;
import freemarker.ext.util.IdentityHashMap;
import freemarker.ext.util.ModelCache;
import freemarker.ext.util.ModelFactory;
import freemarker.ext.util.WrapperTemplateModel;
import freemarker.log.Logger;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.ClassUtil;
import freemarker.template.utility.Collections12;
import freemarker.template.utility.SecurityUtilities;
import freemarker.template.utility.UndeclaredThrowableException;
import java.beans.BeanInfo;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import org.jivesoftware.smackx.FormField;

public class BeansWrapper implements ObjectWrapper {
    private static final Object ARGTYPES = new Object();
    private static final Class BIGINTEGER_CLASS;
    private static final Class BOOLEAN_CLASS;
    static final Object CAN_NOT_UNWRAP = new Object();
    private static final Class CHARACTER_CLASS;
    private static final Class COLLECTION_CLASS;
    private static final Object CONSTRUCTORS = new Object();
    private static final Class DATE_CLASS;
    private static final boolean DEVELOPMENT = "true".equals(SecurityUtilities.getSystemProperty("freemarker.development"));
    private static final ModelFactory ENUMERATION_FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new EnumerationModel((Enumeration) obj, (BeansWrapper) objectWrapper);
        }
    };
    private static final Constructor ENUMS_MODEL_CTOR = enumsModelCtor();
    public static final int EXPOSE_ALL = 0;
    public static final int EXPOSE_NOTHING = 3;
    public static final int EXPOSE_PROPERTIES_ONLY = 2;
    public static final int EXPOSE_SAFE = 1;
    static final Object GENERIC_GET_KEY = new Object();
    private static final Class HASHADAPTER_CLASS;
    private static final BeansWrapper INSTANCE = new BeansWrapper();
    private static final Class ITERABLE_CLASS;
    private static final ModelFactory ITERATOR_FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new IteratorModel((Iterator) obj, (BeansWrapper) objectWrapper);
        }
    };
    private static final Class LIST_CLASS;
    private static final Class MAP_CLASS;
    private static final Class NUMBER_CLASS;
    /* access modifiers changed from: private */
    public static final Class OBJECT_CLASS;
    private static final Class SEQUENCEADAPTER_CLASS;
    private static final Class SETADAPTER_CLASS;
    private static final Class SET_CLASS;
    /* access modifiers changed from: private */
    public static final Class STRING_CLASS;
    private static final Set UNSAFE_METHODS = createUnsafeMethodsSet();
    static /* synthetic */ Class class$freemarker$ext$beans$BeansWrapper;
    static /* synthetic */ Class class$freemarker$ext$beans$HashAdapter;
    static /* synthetic */ Class class$freemarker$ext$beans$SequenceAdapter;
    static /* synthetic */ Class class$freemarker$ext$beans$SetAdapter;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Number;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$math$BigDecimal;
    static /* synthetic */ Class class$java$math$BigInteger;
    static /* synthetic */ Class class$java$util$Collection;
    static /* synthetic */ Class class$java$util$Date;
    static /* synthetic */ Class class$java$util$Enumeration;
    static /* synthetic */ Class class$java$util$Iterator;
    static /* synthetic */ Class class$java$util$List;
    static /* synthetic */ Class class$java$util$Map;
    static /* synthetic */ Class class$java$util$ResourceBundle;
    static /* synthetic */ Class class$java$util$Set;
    private static final boolean javaRebelAvailable = isJavaRebelAvailable();
    private static final Logger logger = Logger.getLogger("freemarker.beans");
    private final ModelFactory BOOLEAN_FACTORY;
    /* access modifiers changed from: private */
    public final BooleanModel FALSE;
    /* access modifiers changed from: private */
    public final BooleanModel TRUE;
    private int defaultDateType;
    private final ClassBasedModelFactory enumModels;
    private boolean exposeFields;
    private int exposureLevel;
    private final Map genericClassIntrospectionCache;
    private final Set genericClassIntrospectionCacheClassNames;
    private final Set genericClassIntrospectionsInProgress;
    private final boolean isGenericClassIntrospectionCacheConcurrentMap;
    private boolean methodsShadowItems;
    private final ModelCache modelCache;
    private TemplateModel nullModel;
    private ObjectWrapper outerIdentity;
    private final Object sharedClassIntrospectionCacheLock = new Object();
    private boolean simpleMapWrapper;
    private final StaticModels staticModels;
    private boolean strict;

    /* access modifiers changed from: protected */
    public void finetuneMethodAppearance(Class cls, Method method, MethodAppearanceDecision methodAppearanceDecision) {
    }

    static {
        Class<?> cls;
        Class cls2 = class$java$math$BigInteger;
        if (cls2 == null) {
            cls2 = class$("java.math.BigInteger");
            class$java$math$BigInteger = cls2;
        }
        BIGINTEGER_CLASS = cls2;
        Class cls3 = class$java$lang$Boolean;
        if (cls3 == null) {
            cls3 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls3;
        }
        BOOLEAN_CLASS = cls3;
        Class cls4 = class$java$lang$Character;
        if (cls4 == null) {
            cls4 = class$("java.lang.Character");
            class$java$lang$Character = cls4;
        }
        CHARACTER_CLASS = cls4;
        Class cls5 = class$java$util$Collection;
        if (cls5 == null) {
            cls5 = class$("java.util.Collection");
            class$java$util$Collection = cls5;
        }
        COLLECTION_CLASS = cls5;
        Class cls6 = class$java$util$Date;
        if (cls6 == null) {
            cls6 = class$("java.util.Date");
            class$java$util$Date = cls6;
        }
        DATE_CLASS = cls6;
        Class cls7 = class$freemarker$ext$beans$HashAdapter;
        if (cls7 == null) {
            cls7 = class$("freemarker.ext.beans.HashAdapter");
            class$freemarker$ext$beans$HashAdapter = cls7;
        }
        HASHADAPTER_CLASS = cls7;
        Class cls8 = class$java$util$List;
        if (cls8 == null) {
            cls8 = class$("java.util.List");
            class$java$util$List = cls8;
        }
        LIST_CLASS = cls8;
        Class cls9 = class$java$util$Map;
        if (cls9 == null) {
            cls9 = class$("java.util.Map");
            class$java$util$Map = cls9;
        }
        MAP_CLASS = cls9;
        Class cls10 = class$java$lang$Number;
        if (cls10 == null) {
            cls10 = class$("java.lang.Number");
            class$java$lang$Number = cls10;
        }
        NUMBER_CLASS = cls10;
        Class cls11 = class$java$lang$Object;
        if (cls11 == null) {
            cls11 = class$("java.lang.Object");
            class$java$lang$Object = cls11;
        }
        OBJECT_CLASS = cls11;
        Class cls12 = class$freemarker$ext$beans$SequenceAdapter;
        if (cls12 == null) {
            cls12 = class$("freemarker.ext.beans.SequenceAdapter");
            class$freemarker$ext$beans$SequenceAdapter = cls12;
        }
        SEQUENCEADAPTER_CLASS = cls12;
        Class cls13 = class$java$util$Set;
        if (cls13 == null) {
            cls13 = class$("java.util.Set");
            class$java$util$Set = cls13;
        }
        SET_CLASS = cls13;
        Class cls14 = class$freemarker$ext$beans$SetAdapter;
        if (cls14 == null) {
            cls14 = class$("freemarker.ext.beans.SetAdapter");
            class$freemarker$ext$beans$SetAdapter = cls14;
        }
        SETADAPTER_CLASS = cls14;
        Class cls15 = class$java$lang$String;
        if (cls15 == null) {
            cls15 = class$("java.lang.String");
            class$java$lang$String = cls15;
        }
        STRING_CLASS = cls15;
        try {
            cls = Class.forName("java.lang.Iterable");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        ITERABLE_CLASS = cls;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public BeansWrapper() {
        Map newMaybeConcurrentHashMap = _ConcurrentMapFactory.newMaybeConcurrentHashMap();
        this.genericClassIntrospectionCache = newMaybeConcurrentHashMap;
        this.isGenericClassIntrospectionCacheConcurrentMap = _ConcurrentMapFactory.isConcurrent(newMaybeConcurrentHashMap);
        this.genericClassIntrospectionCacheClassNames = new HashSet();
        this.genericClassIntrospectionsInProgress = new HashSet();
        this.staticModels = new StaticModels(this);
        this.enumModels = createEnumModels(this);
        this.modelCache = new BeansModelCache(this);
        this.FALSE = new BooleanModel(Boolean.FALSE, this);
        this.TRUE = new BooleanModel(Boolean.TRUE, this);
        this.exposureLevel = 1;
        this.nullModel = null;
        this.methodsShadowItems = true;
        this.exposeFields = false;
        this.defaultDateType = 0;
        this.outerIdentity = this;
        this.strict = false;
        this.BOOLEAN_FACTORY = new ModelFactory() {
            public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
                return ((Boolean) obj).booleanValue() ? BeansWrapper.this.TRUE : BeansWrapper.this.FALSE;
            }
        };
        if (javaRebelAvailable) {
            JavaRebelIntegration.registerWrapper(this);
        }
    }

    public boolean isStrict() {
        return this.strict;
    }

    public void setStrict(boolean z) {
        this.strict = z;
    }

    public void setOuterIdentity(ObjectWrapper objectWrapper) {
        this.outerIdentity = objectWrapper;
    }

    public ObjectWrapper getOuterIdentity() {
        return this.outerIdentity;
    }

    public void setSimpleMapWrapper(boolean z) {
        this.simpleMapWrapper = z;
    }

    public boolean isSimpleMapWrapper() {
        return this.simpleMapWrapper;
    }

    public void setExposureLevel(int i) {
        if (i < 0 || i > 3) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Illegal exposure level ");
            stringBuffer.append(i);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        this.exposureLevel = i;
    }

    /* access modifiers changed from: package-private */
    public int getExposureLevel() {
        return this.exposureLevel;
    }

    public void setExposeFields(boolean z) {
        this.exposeFields = z;
    }

    public boolean isExposeFields() {
        return this.exposeFields;
    }

    public synchronized void setMethodsShadowItems(boolean z) {
        this.methodsShadowItems = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isMethodsShadowItems() {
        return this.methodsShadowItems;
    }

    public synchronized void setDefaultDateType(int i) {
        this.defaultDateType = i;
    }

    /* access modifiers changed from: protected */
    public int getDefaultDateType() {
        return this.defaultDateType;
    }

    public void setUseCache(boolean z) {
        this.modelCache.setUseCache(z);
    }

    public void setNullModel(TemplateModel templateModel) {
        this.nullModel = templateModel;
    }

    public static final BeansWrapper getDefaultInstance() {
        return INSTANCE;
    }

    public TemplateModel wrap(Object obj) throws TemplateModelException {
        if (obj == null) {
            return this.nullModel;
        }
        return this.modelCache.getInstance(obj);
    }

    /* access modifiers changed from: protected */
    public TemplateModel getInstance(Object obj, ModelFactory modelFactory) {
        return modelFactory.create(obj, this);
    }

    /* access modifiers changed from: protected */
    public ModelFactory getModelFactory(Class cls) {
        Class cls2 = class$java$util$Map;
        if (cls2 == null) {
            cls2 = class$("java.util.Map");
            class$java$util$Map = cls2;
        }
        if (cls2.isAssignableFrom(cls)) {
            return this.simpleMapWrapper ? SimpleMapModel.FACTORY : MapModel.FACTORY;
        }
        Class cls3 = class$java$util$Collection;
        if (cls3 == null) {
            cls3 = class$("java.util.Collection");
            class$java$util$Collection = cls3;
        }
        if (cls3.isAssignableFrom(cls)) {
            return CollectionModel.FACTORY;
        }
        Class cls4 = class$java$lang$Number;
        if (cls4 == null) {
            cls4 = class$("java.lang.Number");
            class$java$lang$Number = cls4;
        }
        if (cls4.isAssignableFrom(cls)) {
            return NumberModel.FACTORY;
        }
        Class cls5 = class$java$util$Date;
        if (cls5 == null) {
            cls5 = class$("java.util.Date");
            class$java$util$Date = cls5;
        }
        if (cls5.isAssignableFrom(cls)) {
            return DateModel.FACTORY;
        }
        Class cls6 = class$java$lang$Boolean;
        if (cls6 == null) {
            cls6 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls6;
        }
        if (cls6 == cls) {
            return this.BOOLEAN_FACTORY;
        }
        Class cls7 = class$java$util$ResourceBundle;
        if (cls7 == null) {
            cls7 = class$("java.util.ResourceBundle");
            class$java$util$ResourceBundle = cls7;
        }
        if (cls7.isAssignableFrom(cls)) {
            return ResourceBundleModel.FACTORY;
        }
        Class cls8 = class$java$util$Iterator;
        if (cls8 == null) {
            cls8 = class$("java.util.Iterator");
            class$java$util$Iterator = cls8;
        }
        if (cls8.isAssignableFrom(cls)) {
            return ITERATOR_FACTORY;
        }
        Class cls9 = class$java$util$Enumeration;
        if (cls9 == null) {
            cls9 = class$("java.util.Enumeration");
            class$java$util$Enumeration = cls9;
        }
        if (cls9.isAssignableFrom(cls)) {
            return ENUMERATION_FACTORY;
        }
        if (cls.isArray()) {
            return ArrayModel.FACTORY;
        }
        return StringModel.FACTORY;
    }

    public Object unwrap(TemplateModel templateModel) throws TemplateModelException {
        return unwrap(templateModel, OBJECT_CLASS);
    }

    public Object unwrap(TemplateModel templateModel, Class cls) throws TemplateModelException {
        Object unwrapInternal = unwrapInternal(templateModel, cls);
        if (unwrapInternal != CAN_NOT_UNWRAP) {
            return unwrapInternal;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Can not unwrap model of type ");
        stringBuffer.append(templateModel.getClass().getName());
        stringBuffer.append(" to type ");
        stringBuffer.append(cls.getName());
        throw new TemplateModelException(stringBuffer.toString());
    }

    /* access modifiers changed from: package-private */
    public Object unwrapInternal(TemplateModel templateModel, Class cls) throws TemplateModelException {
        return unwrap(templateModel, cls, (Map) null);
    }

    private Object unwrap(TemplateModel templateModel, Class cls, Map map) throws TemplateModelException {
        Class cls2;
        Number convertUnwrappedNumber;
        Number convertUnwrappedNumber2;
        Number convertUnwrappedNumber3;
        if (templateModel == null || templateModel == this.nullModel) {
            return null;
        }
        boolean z = Boolean.TYPE == cls;
        boolean z2 = Character.TYPE == cls;
        if (templateModel instanceof AdapterTemplateModel) {
            Object adaptedObject = ((AdapterTemplateModel) templateModel).getAdaptedObject(cls);
            if (cls.isInstance(adaptedObject)) {
                return adaptedObject;
            }
            if ((adaptedObject instanceof Number) && (((cls.isPrimitive() && !z2 && !z) || NUMBER_CLASS.isAssignableFrom(cls)) && (convertUnwrappedNumber3 = convertUnwrappedNumber(cls, (Number) adaptedObject)) != null)) {
                return convertUnwrappedNumber3;
            }
        }
        if (templateModel instanceof WrapperTemplateModel) {
            Object wrappedObject = ((WrapperTemplateModel) templateModel).getWrappedObject();
            if (cls.isInstance(wrappedObject)) {
                return wrappedObject;
            }
            if ((wrappedObject instanceof Number) && (((cls.isPrimitive() && !z2 && !z) || NUMBER_CLASS.isAssignableFrom(cls)) && (convertUnwrappedNumber2 = convertUnwrappedNumber(cls, (Number) wrappedObject)) != null)) {
                return convertUnwrappedNumber2;
            }
        }
        Class cls3 = STRING_CLASS;
        if (cls3 == cls) {
            if (templateModel instanceof TemplateScalarModel) {
                return ((TemplateScalarModel) templateModel).getAsString();
            }
            return CAN_NOT_UNWRAP;
        } else if (((cls.isPrimitive() && !z2 && !z) || NUMBER_CLASS.isAssignableFrom(cls)) && (templateModel instanceof TemplateNumberModel) && (convertUnwrappedNumber = convertUnwrappedNumber(cls, ((TemplateNumberModel) templateModel).getAsNumber())) != null) {
            return convertUnwrappedNumber;
        } else {
            if (z || (cls2 = BOOLEAN_CLASS) == cls) {
                if (templateModel instanceof TemplateBooleanModel) {
                    return ((TemplateBooleanModel) templateModel).getAsBoolean() ? Boolean.TRUE : Boolean.FALSE;
                }
                return CAN_NOT_UNWRAP;
            } else if (MAP_CLASS == cls && (templateModel instanceof TemplateHashModel)) {
                return new HashAdapter((TemplateHashModel) templateModel, this);
            } else {
                if (LIST_CLASS == cls && (templateModel instanceof TemplateSequenceModel)) {
                    return new SequenceAdapter((TemplateSequenceModel) templateModel, this);
                }
                if (SET_CLASS == cls && (templateModel instanceof TemplateCollectionModel)) {
                    return new SetAdapter((TemplateCollectionModel) templateModel, this);
                }
                if (COLLECTION_CLASS == cls || ITERABLE_CLASS == cls) {
                    if (templateModel instanceof TemplateCollectionModel) {
                        return new CollectionAdapter((TemplateCollectionModel) templateModel, this);
                    }
                    if (templateModel instanceof TemplateSequenceModel) {
                        return new SequenceAdapter((TemplateSequenceModel) templateModel, this);
                    }
                }
                if (cls.isArray()) {
                    if (!(templateModel instanceof TemplateSequenceModel)) {
                        return CAN_NOT_UNWRAP;
                    }
                    if (map != null) {
                        Object obj = map.get(templateModel);
                        if (obj != null) {
                            return obj;
                        }
                    } else {
                        map = new IdentityHashMap();
                    }
                    TemplateSequenceModel templateSequenceModel = (TemplateSequenceModel) templateModel;
                    Class<?> componentType = cls.getComponentType();
                    Object newInstance = Array.newInstance(componentType, templateSequenceModel.size());
                    map.put(templateModel, newInstance);
                    try {
                        int size = templateSequenceModel.size();
                        for (int i = 0; i < size; i++) {
                            Object unwrap = unwrap(templateSequenceModel.get(i), componentType, map);
                            Object obj2 = CAN_NOT_UNWRAP;
                            if (unwrap == obj2) {
                                return obj2;
                            }
                            Array.set(newInstance, i, unwrap);
                        }
                        map.remove(templateModel);
                        return newInstance;
                    } finally {
                        map.remove(templateModel);
                    }
                } else if (z2 || cls == CHARACTER_CLASS) {
                    if (templateModel instanceof TemplateScalarModel) {
                        String asString = ((TemplateScalarModel) templateModel).getAsString();
                        if (asString.length() == 1) {
                            return new Character(asString.charAt(0));
                        }
                    }
                    return CAN_NOT_UNWRAP;
                } else {
                    if (DATE_CLASS.isAssignableFrom(cls) && (templateModel instanceof TemplateDateModel)) {
                        Date asDate = ((TemplateDateModel) templateModel).getAsDate();
                        if (cls.isInstance(asDate)) {
                            return asDate;
                        }
                    }
                    if (templateModel instanceof TemplateNumberModel) {
                        Number asNumber = ((TemplateNumberModel) templateModel).getAsNumber();
                        if (cls.isInstance(asNumber)) {
                            return asNumber;
                        }
                    }
                    if (templateModel instanceof TemplateDateModel) {
                        Date asDate2 = ((TemplateDateModel) templateModel).getAsDate();
                        if (cls.isInstance(asDate2)) {
                            return asDate2;
                        }
                    }
                    if ((templateModel instanceof TemplateScalarModel) && cls.isAssignableFrom(cls3)) {
                        return ((TemplateScalarModel) templateModel).getAsString();
                    }
                    if ((templateModel instanceof TemplateBooleanModel) && cls.isAssignableFrom(cls2)) {
                        return ((TemplateBooleanModel) templateModel).getAsBoolean() ? Boolean.TRUE : Boolean.FALSE;
                    }
                    if ((templateModel instanceof TemplateHashModel) && cls.isAssignableFrom(HASHADAPTER_CLASS)) {
                        return new HashAdapter((TemplateHashModel) templateModel, this);
                    }
                    if ((templateModel instanceof TemplateSequenceModel) && cls.isAssignableFrom(SEQUENCEADAPTER_CLASS)) {
                        return new SequenceAdapter((TemplateSequenceModel) templateModel, this);
                    }
                    if ((templateModel instanceof TemplateCollectionModel) && cls.isAssignableFrom(SETADAPTER_CLASS)) {
                        return new SetAdapter((TemplateCollectionModel) templateModel, this);
                    }
                    if (cls.isInstance(templateModel)) {
                        return templateModel;
                    }
                    return CAN_NOT_UNWRAP;
                }
            }
        }
    }

    private static Number convertUnwrappedNumber(Class cls, Number number) {
        if (cls != Integer.TYPE) {
            Class cls2 = class$java$lang$Integer;
            if (cls2 == null) {
                cls2 = class$("java.lang.Integer");
                class$java$lang$Integer = cls2;
            }
            if (cls != cls2) {
                if (cls != Long.TYPE) {
                    Class cls3 = class$java$lang$Long;
                    if (cls3 == null) {
                        cls3 = class$("java.lang.Long");
                        class$java$lang$Long = cls3;
                    }
                    if (cls != cls3) {
                        if (cls != Float.TYPE) {
                            Class cls4 = class$java$lang$Float;
                            if (cls4 == null) {
                                cls4 = class$("java.lang.Float");
                                class$java$lang$Float = cls4;
                            }
                            if (cls != cls4) {
                                if (cls != Double.TYPE) {
                                    Class cls5 = class$java$lang$Double;
                                    if (cls5 == null) {
                                        cls5 = class$("java.lang.Double");
                                        class$java$lang$Double = cls5;
                                    }
                                    if (cls != cls5) {
                                        if (cls != Byte.TYPE) {
                                            Class cls6 = class$java$lang$Byte;
                                            if (cls6 == null) {
                                                cls6 = class$("java.lang.Byte");
                                                class$java$lang$Byte = cls6;
                                            }
                                            if (cls != cls6) {
                                                if (cls != Short.TYPE) {
                                                    Class cls7 = class$java$lang$Short;
                                                    if (cls7 == null) {
                                                        cls7 = class$("java.lang.Short");
                                                        class$java$lang$Short = cls7;
                                                    }
                                                    if (cls != cls7) {
                                                        Class cls8 = class$java$math$BigInteger;
                                                        if (cls8 == null) {
                                                            cls8 = class$("java.math.BigInteger");
                                                            class$java$math$BigInteger = cls8;
                                                        }
                                                        if (cls == cls8) {
                                                            return number instanceof BigInteger ? number : new BigInteger(number.toString());
                                                        }
                                                        Class cls9 = class$java$math$BigDecimal;
                                                        if (cls9 == null) {
                                                            cls9 = class$("java.math.BigDecimal");
                                                            class$java$math$BigDecimal = cls9;
                                                        }
                                                        if (cls == cls9) {
                                                            if (number instanceof BigDecimal) {
                                                                return number;
                                                            }
                                                            if (number instanceof BigInteger) {
                                                                return new BigDecimal((BigInteger) number);
                                                            }
                                                            if (number instanceof Long) {
                                                                return new BigDecimal(number.toString());
                                                            }
                                                            return new BigDecimal(number.doubleValue());
                                                        } else if (cls.isInstance(number)) {
                                                            return number;
                                                        } else {
                                                            return null;
                                                        }
                                                    }
                                                }
                                                return number instanceof Short ? (Short) number : new Short(number.shortValue());
                                            }
                                        }
                                        return number instanceof Byte ? (Byte) number : new Byte(number.byteValue());
                                    }
                                }
                                return number instanceof Double ? (Double) number : new Double(number.doubleValue());
                            }
                        }
                        return number instanceof Float ? (Float) number : new Float(number.floatValue());
                    }
                }
                return number instanceof Long ? (Long) number : new Long(number.longValue());
            }
        }
        return number instanceof Integer ? (Integer) number : new Integer(number.intValue());
    }

    /* access modifiers changed from: package-private */
    public TemplateModel invokeMethod(Object obj, Method method, Object[] objArr) throws InvocationTargetException, IllegalAccessException, TemplateModelException {
        return method.getReturnType() == Void.TYPE ? TemplateModel.NOTHING : getOuterIdentity().wrap(method.invoke(obj, objArr));
    }

    public TemplateHashModel getStaticModels() {
        return this.staticModels;
    }

    public TemplateHashModel getEnumModels() {
        ClassBasedModelFactory classBasedModelFactory = this.enumModels;
        if (classBasedModelFactory != null) {
            return classBasedModelFactory;
        }
        throw new UnsupportedOperationException("Enums not supported before J2SE 5.");
    }

    /* JADX WARNING: type inference failed for: r5v6, types: [java.lang.reflect.Member] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object newInstance(java.lang.Class r4, java.util.List r5) throws freemarker.template.TemplateModelException {
        /*
            r3 = this;
            java.util.Map r0 = r3.getClassIntrospectionData(r4)     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.Object r1 = CONSTRUCTORS     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            if (r0 == 0) goto L_0x003e
            boolean r1 = r0 instanceof freemarker.ext.beans.SimpleMemberModel     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            if (r1 == 0) goto L_0x001d
            freemarker.ext.beans.SimpleMemberModel r0 = (freemarker.ext.beans.SimpleMemberModel) r0     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.reflect.Member r1 = r0.getMember()     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.reflect.Constructor r1 = (java.lang.reflect.Constructor) r1     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.Object[] r5 = r0.unwrapArguments(r5, r3)     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            goto L_0x0033
        L_0x001d:
            boolean r1 = r0 instanceof freemarker.ext.beans.OverloadedMethods     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            if (r1 == 0) goto L_0x0038
            freemarker.ext.beans.OverloadedMethods r0 = (freemarker.ext.beans.OverloadedMethods) r0     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            freemarker.ext.beans.MemberAndArguments r5 = r0.getMemberAndArguments(r5)     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.Object[] r0 = r5.getArgs()     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.reflect.Member r5 = r5.getMember()     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            r1 = r5
            java.lang.reflect.Constructor r1 = (java.lang.reflect.Constructor) r1     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            r5 = r0
        L_0x0033:
            java.lang.Object r4 = r1.newInstance(r5)     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            return r4
        L_0x0038:
            java.lang.Error r5 = new java.lang.Error     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            r5.<init>()     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            throw r5     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
        L_0x003e:
            freemarker.template.TemplateModelException r5 = new freemarker.template.TemplateModelException     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            r0.<init>()     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.String r1 = "Class "
            r0.append(r1)     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.String r1 = r4.getName()     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            r0.append(r1)     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.String r1 = " has no public constructors."
            r0.append(r1)     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            java.lang.String r0 = r0.toString()     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            r5.<init>((java.lang.String) r0)     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
            throw r5     // Catch:{ TemplateModelException -> 0x007a, Exception -> 0x005e }
        L_0x005e:
            r5 = move-exception
            freemarker.template.TemplateModelException r0 = new freemarker.template.TemplateModelException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Could not create instance of class "
            r1.append(r2)
            java.lang.String r4 = r4.getName()
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>((java.lang.String) r4, (java.lang.Exception) r5)
            throw r0
        L_0x007a:
            r4 = move-exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.beans.BeansWrapper.newInstance(java.lang.Class, java.util.List):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public Map getClassIntrospectionData(Class cls) {
        Map map;
        if (this.isGenericClassIntrospectionCacheConcurrentMap && (map = (Map) this.genericClassIntrospectionCache.get(cls)) != null) {
            return map;
        }
        synchronized (this.sharedClassIntrospectionCacheLock) {
            Map map2 = (Map) this.genericClassIntrospectionCache.get(cls);
            if (map2 != null) {
                return map2;
            }
            String name = cls.getName();
            if (this.genericClassIntrospectionCacheClassNames.contains(name)) {
                onSameNameClassesDetected(name);
            }
            while (map2 == null && this.genericClassIntrospectionsInProgress.contains(cls)) {
                try {
                    this.sharedClassIntrospectionCacheLock.wait();
                    map2 = (Map) this.genericClassIntrospectionCache.get(cls);
                } catch (InterruptedException e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Class inrospection data lookup aborded: ");
                    stringBuffer.append(e);
                    throw new RuntimeException(stringBuffer.toString());
                }
            }
            if (map2 != null) {
                return map2;
            }
            this.genericClassIntrospectionsInProgress.add(cls);
            try {
                Map createClassIntrospectionData = createClassIntrospectionData(cls);
                synchronized (this.sharedClassIntrospectionCacheLock) {
                    this.genericClassIntrospectionCache.put(cls, createClassIntrospectionData);
                    this.genericClassIntrospectionCacheClassNames.add(name);
                }
                synchronized (this.sharedClassIntrospectionCacheLock) {
                    this.genericClassIntrospectionsInProgress.remove(cls);
                    this.sharedClassIntrospectionCacheLock.notifyAll();
                }
                return createClassIntrospectionData;
            } catch (Throwable th) {
                synchronized (this.sharedClassIntrospectionCacheLock) {
                    this.genericClassIntrospectionsInProgress.remove(cls);
                    this.sharedClassIntrospectionCacheLock.notifyAll();
                    throw th;
                }
            }
        }
    }

    public void removeFromClassIntrospectionCache(Class cls) {
        synchronized (this.sharedClassIntrospectionCacheLock) {
            removeFromGenericClassIntrospectionCache(cls);
            this.staticModels.removeFromCache(cls);
            ClassBasedModelFactory classBasedModelFactory = this.enumModels;
            if (classBasedModelFactory != null) {
                classBasedModelFactory.removeFromCache(cls);
            }
        }
    }

    public void clearClassIntrospecitonCache() {
        synchronized (this.sharedClassIntrospectionCacheLock) {
            clearGenericClassIntrospectionCache();
            this.staticModels.clearCache();
            ClassBasedModelFactory classBasedModelFactory = this.enumModels;
            if (classBasedModelFactory != null) {
                classBasedModelFactory.clearCache();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onSameNameClassesDetected(String str) {
        Logger logger2 = logger;
        if (logger2.isInfoEnabled()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Detected multiple classes with the same name, \"");
            stringBuffer.append(str);
            stringBuffer.append("\". Assuming it was a class-reloading. Clearing BeansWrapper ");
            stringBuffer.append("caches to release old data.");
            logger2.info(stringBuffer.toString());
        }
        clearClassIntrospecitonCache();
    }

    /* access modifiers changed from: package-private */
    public Object getSharedClassIntrospectionCacheLock() {
        return this.sharedClassIntrospectionCacheLock;
    }

    private void removeFromGenericClassIntrospectionCache(Class cls) {
        synchronized (this.sharedClassIntrospectionCacheLock) {
            this.genericClassIntrospectionCache.remove(cls);
            this.genericClassIntrospectionCacheClassNames.remove(cls.getName());
            this.modelCache.clearCache();
        }
    }

    private void clearGenericClassIntrospectionCache() {
        synchronized (this.sharedClassIntrospectionCacheLock) {
            this.genericClassIntrospectionCache.clear();
            this.genericClassIntrospectionCacheClassNames.clear();
            this.modelCache.clearCache();
        }
    }

    /* access modifiers changed from: package-private */
    public int keyCount(Class cls) {
        Map classIntrospectionData = getClassIntrospectionData(cls);
        int size = classIntrospectionData.size();
        if (classIntrospectionData.containsKey(CONSTRUCTORS)) {
            size--;
        }
        if (classIntrospectionData.containsKey(GENERIC_GET_KEY)) {
            size--;
        }
        return classIntrospectionData.containsKey(ARGTYPES) ? size - 1 : size;
    }

    /* access modifiers changed from: package-private */
    public Set keySet(Class cls) {
        HashSet hashSet = new HashSet(getClassIntrospectionData(cls).keySet());
        hashSet.remove(CONSTRUCTORS);
        hashSet.remove(GENERIC_GET_KEY);
        hashSet.remove(ARGTYPES);
        return hashSet;
    }

    private Map createClassIntrospectionData(Class cls) {
        HashMap hashMap = new HashMap();
        if (this.exposeFields) {
            addFieldsToClassIntrospectionData(hashMap, cls);
        }
        Map discoverAccessibleMethods = discoverAccessibleMethods(cls);
        addGenericGetToClassIntrospectionData(hashMap, discoverAccessibleMethods);
        if (this.exposureLevel != 3) {
            try {
                addBeanInfoToClassInrospectionData(hashMap, cls, discoverAccessibleMethods);
            } catch (IntrospectionException e) {
                Logger logger2 = logger;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Couldn't properly perform introspection for class ");
                stringBuffer.append(cls);
                logger2.warn(stringBuffer.toString(), e);
                hashMap.clear();
            }
        }
        addConstructorsToClassIntrospectionData(hashMap, cls);
        if (hashMap.size() > 1) {
            return hashMap;
        }
        if (hashMap.size() == 0) {
            return Collections12.EMPTY_MAP;
        }
        Map.Entry entry = (Map.Entry) hashMap.entrySet().iterator().next();
        return Collections12.singletonMap(entry.getKey(), entry.getValue());
    }

    private void addFieldsToClassIntrospectionData(Map map, Class cls) throws SecurityException {
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            if ((field.getModifiers() & 8) == 0) {
                map.put(field.getName(), field);
            }
        }
    }

    private void addBeanInfoToClassInrospectionData(Map map, Class cls, Map map2) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(cls);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        int i = 0;
        for (int length = (propertyDescriptors != null ? propertyDescriptors.length : 0) - 1; length >= 0; length--) {
            addPropertyDescriptorToClassIntrospectionData(propertyDescriptors[length], cls, map2, map);
        }
        if (this.exposureLevel < 2) {
            MethodAppearanceDecision methodAppearanceDecision = new MethodAppearanceDecision();
            MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
            if (methodDescriptors != null) {
                i = methodDescriptors.length;
            }
            for (int i2 = i - 1; i2 >= 0; i2--) {
                Method accessibleMethod = getAccessibleMethod(methodDescriptors[i2].getMethod(), map2);
                if (accessibleMethod != null && isSafeMethod(accessibleMethod)) {
                    methodAppearanceDecision.setDefaults(accessibleMethod);
                    finetuneMethodAppearance(cls, accessibleMethod, methodAppearanceDecision);
                    PropertyDescriptor exposeAsProperty = methodAppearanceDecision.getExposeAsProperty();
                    if (exposeAsProperty != null && !(map.get(exposeAsProperty.getName()) instanceof PropertyDescriptor)) {
                        addPropertyDescriptorToClassIntrospectionData(exposeAsProperty, cls, map2, map);
                    }
                    String exposeMethodAs = methodAppearanceDecision.getExposeMethodAs();
                    if (exposeMethodAs != null) {
                        Object obj = map.get(exposeMethodAs);
                        if (obj instanceof Method) {
                            OverloadedMethods overloadedMethods = new OverloadedMethods(this);
                            overloadedMethods.addMember((Method) obj);
                            overloadedMethods.addMember(accessibleMethod);
                            map.put(exposeMethodAs, overloadedMethods);
                            getArgTypes(map).remove(obj);
                        } else if (obj instanceof OverloadedMethods) {
                            ((OverloadedMethods) obj).addMember(accessibleMethod);
                        } else if (methodAppearanceDecision.getMethodShadowsProperty() || !(obj instanceof PropertyDescriptor)) {
                            map.put(exposeMethodAs, accessibleMethod);
                            getArgTypes(map).put(accessibleMethod, accessibleMethod.getParameterTypes());
                        }
                    }
                }
            }
        }
    }

    private void addPropertyDescriptorToClassIntrospectionData(PropertyDescriptor propertyDescriptor, Class cls, Map map, Map map2) {
        Throwable e;
        PropertyDescriptor propertyDescriptor2;
        if (propertyDescriptor instanceof IndexedPropertyDescriptor) {
            IndexedPropertyDescriptor indexedPropertyDescriptor = (IndexedPropertyDescriptor) propertyDescriptor;
            Method indexedReadMethod = indexedPropertyDescriptor.getIndexedReadMethod();
            Method accessibleMethod = getAccessibleMethod(indexedReadMethod, map);
            if (accessibleMethod != null && isSafeMethod(accessibleMethod)) {
                if (indexedReadMethod != accessibleMethod) {
                    try {
                        indexedPropertyDescriptor = new IndexedPropertyDescriptor(indexedPropertyDescriptor.getName(), indexedPropertyDescriptor.getReadMethod(), (Method) null, accessibleMethod, (Method) null);
                    } catch (IntrospectionException e2) {
                        Logger logger2 = logger;
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Failed creating a publicly-accessible property descriptor for ");
                        stringBuffer.append(cls.getName());
                        stringBuffer.append(" indexed property ");
                        stringBuffer.append(propertyDescriptor.getName());
                        stringBuffer.append(", read method ");
                        stringBuffer.append(accessibleMethod);
                        logger2.warn(stringBuffer.toString(), e2);
                        return;
                    }
                }
                map2.put(indexedPropertyDescriptor.getName(), indexedPropertyDescriptor);
                getArgTypes(map2).put(accessibleMethod, accessibleMethod.getParameterTypes());
                return;
            }
            return;
        }
        Method readMethod = propertyDescriptor.getReadMethod();
        Method accessibleMethod2 = getAccessibleMethod(readMethod, map);
        if (accessibleMethod2 != null && isSafeMethod(accessibleMethod2)) {
            if (readMethod != accessibleMethod2) {
                try {
                    propertyDescriptor2 = new PropertyDescriptor(propertyDescriptor.getName(), accessibleMethod2, (Method) null);
                    try {
                        propertyDescriptor2.setReadMethod(accessibleMethod2);
                        propertyDescriptor = propertyDescriptor2;
                    } catch (IntrospectionException e3) {
                        e = e3;
                        Logger logger3 = logger;
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("Failed creating a publicly-accessible property descriptor for ");
                        stringBuffer2.append(cls.getName());
                        stringBuffer2.append(" property ");
                        stringBuffer2.append(propertyDescriptor2.getName());
                        stringBuffer2.append(", read method ");
                        stringBuffer2.append(accessibleMethod2);
                        logger3.warn(stringBuffer2.toString(), e);
                        return;
                    }
                } catch (IntrospectionException e4) {
                    propertyDescriptor2 = propertyDescriptor;
                    e = e4;
                }
            }
            map2.put(propertyDescriptor.getName(), propertyDescriptor);
        }
    }

    private void addGenericGetToClassIntrospectionData(Map map, Map map2) {
        Method firstAccessibleMethod = getFirstAccessibleMethod(MethodSignature.GET_STRING_SIGNATURE, map2);
        if (firstAccessibleMethod == null) {
            firstAccessibleMethod = getFirstAccessibleMethod(MethodSignature.GET_OBJECT_SIGNATURE, map2);
        }
        if (firstAccessibleMethod != null) {
            map.put(GENERIC_GET_KEY, firstAccessibleMethod);
        }
    }

    private void addConstructorsToClassIntrospectionData(Map map, Class cls) {
        try {
            Constructor[] constructors = cls.getConstructors();
            if (constructors.length == 1) {
                Constructor constructor = constructors[0];
                map.put(CONSTRUCTORS, new SimpleMemberModel(constructor, constructor.getParameterTypes()));
            } else if (constructors.length > 1) {
                OverloadedMethods overloadedMethods = new OverloadedMethods(this);
                for (Constructor addMember : constructors) {
                    overloadedMethods.addMember(addMember);
                }
                map.put(CONSTRUCTORS, overloadedMethods);
            }
        } catch (SecurityException e) {
            Logger logger2 = logger;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Canont discover constructors for class ");
            stringBuffer.append(cls.getName());
            logger2.warn(stringBuffer.toString(), e);
        }
    }

    private static Map getArgTypes(Map map) {
        Object obj = ARGTYPES;
        Map map2 = (Map) map.get(obj);
        if (map2 != null) {
            return map2;
        }
        HashMap hashMap = new HashMap();
        map.put(obj, hashMap);
        return hashMap;
    }

    static Class[] getArgTypes(Map map, AccessibleObject accessibleObject) {
        return (Class[]) ((Map) map.get(ARGTYPES)).get(accessibleObject);
    }

    private static Method getFirstAccessibleMethod(MethodSignature methodSignature, Map map) {
        List list = (List) map.get(methodSignature);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (Method) list.iterator().next();
    }

    private static Method getAccessibleMethod(Method method, Map map) {
        List<Method> list;
        if (method == null || (list = (List) map.get(new MethodSignature(method))) == null) {
            return null;
        }
        for (Method method2 : list) {
            if (method2.getReturnType() == method.getReturnType()) {
                return method2;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isSafeMethod(Method method) {
        return this.exposureLevel < 1 || !UNSAFE_METHODS.contains(method);
    }

    private static Map discoverAccessibleMethods(Class cls) {
        HashMap hashMap = new HashMap();
        discoverAccessibleMethods(cls, hashMap);
        return hashMap;
    }

    private static void discoverAccessibleMethods(Class cls, Map map) {
        if (Modifier.isPublic(cls.getModifiers())) {
            try {
                Method[] methods = cls.getMethods();
                for (Method method : methods) {
                    MethodSignature methodSignature = new MethodSignature(method);
                    List list = (List) map.get(methodSignature);
                    if (list == null) {
                        list = new LinkedList();
                        map.put(methodSignature, list);
                    }
                    list.add(method);
                }
                return;
            } catch (SecurityException e) {
                Logger logger2 = logger;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Could not discover accessible methods of class ");
                stringBuffer.append(cls.getName());
                stringBuffer.append(", attemping superclasses/interfaces.");
                logger2.warn(stringBuffer.toString(), e);
            }
        }
        Class[] interfaces = cls.getInterfaces();
        for (Class discoverAccessibleMethods : interfaces) {
            discoverAccessibleMethods(discoverAccessibleMethods, map);
        }
        Class superclass = cls.getSuperclass();
        if (superclass != null) {
            discoverAccessibleMethods(superclass, map);
        }
    }

    private static final class MethodSignature {
        /* access modifiers changed from: private */
        public static final MethodSignature GET_OBJECT_SIGNATURE = new MethodSignature("get", new Class[]{BeansWrapper.OBJECT_CLASS});
        /* access modifiers changed from: private */
        public static final MethodSignature GET_STRING_SIGNATURE = new MethodSignature("get", new Class[]{BeansWrapper.STRING_CLASS});
        private final Class[] args;
        private final String name;

        private MethodSignature(String str, Class[] clsArr) {
            this.name = str;
            this.args = clsArr;
        }

        MethodSignature(Method method) {
            this(method.getName(), method.getParameterTypes());
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MethodSignature)) {
                return false;
            }
            MethodSignature methodSignature = (MethodSignature) obj;
            if (!methodSignature.name.equals(this.name) || !Arrays.equals(this.args, methodSignature.args)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.name.hashCode() ^ this.args.length;
        }
    }

    private static final Set createUnsafeMethodsSet() {
        Properties properties = new Properties();
        Class cls = class$freemarker$ext$beans$BeansWrapper;
        if (cls == null) {
            cls = class$("freemarker.ext.beans.BeansWrapper");
            class$freemarker$ext$beans$BeansWrapper = cls;
        }
        InputStream resourceAsStream = cls.getResourceAsStream("unsafeMethods.txt");
        if (resourceAsStream == null) {
            return Collections.EMPTY_SET;
        }
        String str = null;
        try {
            properties.load(resourceAsStream);
            resourceAsStream.close();
            HashSet hashSet = new HashSet((properties.size() * 4) / 3, 0.75f);
            Map createPrimitiveClassesMap = createPrimitiveClassesMap();
            for (String str2 : properties.keySet()) {
                try {
                    hashSet.add(parseMethodSpec(str2, createPrimitiveClassesMap));
                } catch (ClassNotFoundException e) {
                    if (DEVELOPMENT) {
                        throw e;
                    }
                } catch (NoSuchMethodException e2) {
                    if (DEVELOPMENT) {
                        throw e2;
                    }
                } catch (Exception e3) {
                    e = e3;
                    str = str2;
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Could not load unsafe method ");
                    stringBuffer.append(str);
                    stringBuffer.append(" ");
                    stringBuffer.append(e.getClass().getName());
                    stringBuffer.append(" ");
                    stringBuffer.append(e.getMessage());
                    throw new RuntimeException(stringBuffer.toString());
                }
                String str3 = str2;
            }
            return hashSet;
        } catch (Exception e4) {
            e = e4;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not load unsafe method ");
            stringBuffer2.append(str);
            stringBuffer2.append(" ");
            stringBuffer2.append(e.getClass().getName());
            stringBuffer2.append(" ");
            stringBuffer2.append(e.getMessage());
            throw new RuntimeException(stringBuffer2.toString());
        } catch (Throwable th) {
            resourceAsStream.close();
            throw th;
        }
    }

    private static Method parseMethodSpec(String str, Map map) throws ClassNotFoundException, NoSuchMethodException {
        int indexOf = str.indexOf(40);
        int lastIndexOf = str.lastIndexOf(46, indexOf);
        Class forName = ClassUtil.forName(str.substring(0, lastIndexOf));
        String substring = str.substring(lastIndexOf + 1, indexOf);
        StringTokenizer stringTokenizer = new StringTokenizer(str.substring(indexOf + 1, str.length() - 1), ",");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i = 0; i < countTokens; i++) {
            String nextToken = stringTokenizer.nextToken();
            clsArr[i] = (Class) map.get(nextToken);
            if (clsArr[i] == null) {
                clsArr[i] = ClassUtil.forName(nextToken);
            }
        }
        return forName.getMethod(substring, clsArr);
    }

    private static Map createPrimitiveClassesMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(FormField.TYPE_BOOLEAN, Boolean.TYPE);
        hashMap.put("byte", Byte.TYPE);
        hashMap.put("char", Character.TYPE);
        hashMap.put("short", Short.TYPE);
        hashMap.put("int", Integer.TYPE);
        hashMap.put("long", Long.TYPE);
        hashMap.put("float", Float.TYPE);
        hashMap.put("double", Double.TYPE);
        return hashMap;
    }

    public static void coerceBigDecimals(AccessibleObject accessibleObject, Object[] objArr) {
        Class[] clsArr = null;
        for (int i = 0; i < objArr.length; i++) {
            BigDecimal bigDecimal = objArr[i];
            if (bigDecimal instanceof BigDecimal) {
                if (clsArr == null) {
                    if (accessibleObject instanceof Method) {
                        clsArr = ((Method) accessibleObject).getParameterTypes();
                    } else if (accessibleObject instanceof Constructor) {
                        clsArr = ((Constructor) accessibleObject).getParameterTypes();
                    } else {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Expected method or  constructor; callable is ");
                        stringBuffer.append(accessibleObject.getClass().getName());
                        throw new IllegalArgumentException(stringBuffer.toString());
                    }
                }
                objArr[i] = coerceBigDecimal(bigDecimal, clsArr[i]);
            }
        }
    }

    public static void coerceBigDecimals(Class[] clsArr, Object[] objArr) {
        int length = clsArr.length;
        int length2 = objArr.length;
        int min = Math.min(length, length2);
        for (int i = 0; i < min; i++) {
            BigDecimal bigDecimal = objArr[i];
            if (bigDecimal instanceof BigDecimal) {
                objArr[i] = coerceBigDecimal(bigDecimal, clsArr[i]);
            }
        }
        if (length2 > length) {
            Class cls = clsArr[length - 1];
            while (length < length2) {
                BigDecimal bigDecimal2 = objArr[length];
                if (bigDecimal2 instanceof BigDecimal) {
                    objArr[length] = coerceBigDecimal(bigDecimal2, cls);
                }
                length++;
            }
        }
    }

    public static Object coerceBigDecimal(BigDecimal bigDecimal, Class cls) {
        if (cls != Integer.TYPE) {
            Class cls2 = class$java$lang$Integer;
            if (cls2 == null) {
                cls2 = class$("java.lang.Integer");
                class$java$lang$Integer = cls2;
            }
            if (cls != cls2) {
                if (cls != Double.TYPE) {
                    Class cls3 = class$java$lang$Double;
                    if (cls3 == null) {
                        cls3 = class$("java.lang.Double");
                        class$java$lang$Double = cls3;
                    }
                    if (cls != cls3) {
                        if (cls != Long.TYPE) {
                            Class cls4 = class$java$lang$Long;
                            if (cls4 == null) {
                                cls4 = class$("java.lang.Long");
                                class$java$lang$Long = cls4;
                            }
                            if (cls != cls4) {
                                if (cls != Float.TYPE) {
                                    Class cls5 = class$java$lang$Float;
                                    if (cls5 == null) {
                                        cls5 = class$("java.lang.Float");
                                        class$java$lang$Float = cls5;
                                    }
                                    if (cls != cls5) {
                                        if (cls != Short.TYPE) {
                                            Class cls6 = class$java$lang$Short;
                                            if (cls6 == null) {
                                                cls6 = class$("java.lang.Short");
                                                class$java$lang$Short = cls6;
                                            }
                                            if (cls != cls6) {
                                                if (cls != Byte.TYPE) {
                                                    Class cls7 = class$java$lang$Byte;
                                                    if (cls7 == null) {
                                                        cls7 = class$("java.lang.Byte");
                                                        class$java$lang$Byte = cls7;
                                                    }
                                                    if (cls != cls7) {
                                                        return BIGINTEGER_CLASS.isAssignableFrom(cls) ? bigDecimal.toBigInteger() : bigDecimal;
                                                    }
                                                }
                                                return new Byte(bigDecimal.byteValue());
                                            }
                                        }
                                        return new Short(bigDecimal.shortValue());
                                    }
                                }
                                return new Float(bigDecimal.floatValue());
                            }
                        }
                        return new Long(bigDecimal.longValue());
                    }
                }
                return new Double(bigDecimal.doubleValue());
            }
        }
        return new Integer(bigDecimal.intValue());
    }

    private static ClassBasedModelFactory createEnumModels(BeansWrapper beansWrapper) {
        Constructor constructor = ENUMS_MODEL_CTOR;
        if (constructor == null) {
            return null;
        }
        try {
            return (ClassBasedModelFactory) constructor.newInstance(new Object[]{beansWrapper});
        } catch (Exception e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    private static Constructor enumsModelCtor() {
        try {
            Class.forName("java.lang.Enum");
            Class<?> cls = Class.forName("freemarker.ext.beans._EnumModels");
            Class[] clsArr = new Class[1];
            Class cls2 = class$freemarker$ext$beans$BeansWrapper;
            if (cls2 == null) {
                cls2 = class$("freemarker.ext.beans.BeansWrapper");
                class$freemarker$ext$beans$BeansWrapper = cls2;
            }
            clsArr[0] = cls2;
            return cls.getDeclaredConstructor(clsArr);
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean isJavaRebelAvailable() {
        try {
            JavaRebelIntegration.testAvailability();
            return true;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    public static final class MethodAppearanceDecision {
        private PropertyDescriptor exposeAsProperty;
        private String exposeMethodAs;
        private boolean methodShadowsProperty;

        /* access modifiers changed from: package-private */
        public void setDefaults(Method method) {
            this.exposeAsProperty = null;
            this.exposeMethodAs = method.getName();
            this.methodShadowsProperty = true;
        }

        public PropertyDescriptor getExposeAsProperty() {
            return this.exposeAsProperty;
        }

        public void setExposeAsProperty(PropertyDescriptor propertyDescriptor) {
            this.exposeAsProperty = propertyDescriptor;
        }

        public String getExposeMethodAs() {
            return this.exposeMethodAs;
        }

        public void setExposeMethodAs(String str) {
            this.exposeMethodAs = str;
        }

        public boolean getMethodShadowsProperty() {
            return this.methodShadowsProperty;
        }

        public void setMethodShadowsProperty(boolean z) {
            this.methodShadowsProperty = z;
        }
    }
}
