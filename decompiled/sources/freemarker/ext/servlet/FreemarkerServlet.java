package freemarker.ext.servlet;

import com.google.android.gms.wallet.WalletConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.cache.WebappTemplateLoader;
import freemarker.core.Configurable;
import freemarker.log.Logger;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModel;
import freemarker.template.utility.StringUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FreemarkerServlet extends HttpServlet {
    private static final String ATTR_APPLICATION_MODEL = ".freemarker.Application";
    private static final String ATTR_JSP_TAGLIBS_MODEL = ".freemarker.JspTaglibs";
    private static final String ATTR_REQUEST_MODEL = ".freemarker.Request";
    private static final String ATTR_REQUEST_PARAMETERS_MODEL = ".freemarker.RequestParameters";
    private static final String ATTR_SESSION_MODEL = ".freemarker.Session";
    private static final String DEFAULT_CONTENT_TYPE = "text/html";
    private static final String DEPR_INITPARAM_DEBUG = "debug";
    private static final String DEPR_INITPARAM_ENCODING = "DefaultEncoding";
    private static final String DEPR_INITPARAM_OBJECT_WRAPPER = "ObjectWrapper";
    private static final String DEPR_INITPARAM_TEMPLATE_DELAY = "TemplateDelay";
    private static final String DEPR_INITPARAM_TEMPLATE_EXCEPTION_HANDLER = "TemplateExceptionHandler";
    private static final String DEPR_INITPARAM_TEMPLATE_EXCEPTION_HANDLER_DEBUG = "debug";
    private static final String DEPR_INITPARAM_TEMPLATE_EXCEPTION_HANDLER_HTML_DEBUG = "htmlDebug";
    private static final String DEPR_INITPARAM_TEMPLATE_EXCEPTION_HANDLER_IGNORE = "ignore";
    private static final String DEPR_INITPARAM_TEMPLATE_EXCEPTION_HANDLER_RETHROW = "rethrow";
    private static final String DEPR_INITPARAM_WRAPPER_BEANS = "beans";
    private static final String DEPR_INITPARAM_WRAPPER_JYTHON = "jython";
    private static final String DEPR_INITPARAM_WRAPPER_SIMPLE = "simple";
    private static final String EXPIRATION_DATE;
    private static final String INITPARAM_CONTENT_TYPE = "ContentType";
    private static final String INITPARAM_DEBUG = "Debug";
    private static final String INITPARAM_NOCACHE = "NoCache";
    private static final String INITPARAM_TEMPLATE_PATH = "TemplatePath";
    public static final String KEY_APPLICATION = "Application";
    public static final String KEY_APPLICATION_PRIVATE = "__FreeMarkerServlet.Application__";
    public static final String KEY_INCLUDE = "include_page";
    public static final String KEY_JSP_TAGLIBS = "JspTaglibs";
    public static final String KEY_REQUEST = "Request";
    public static final String KEY_REQUEST_PARAMETERS = "RequestParameters";
    public static final String KEY_REQUEST_PRIVATE = "__FreeMarkerServlet.Request__";
    public static final String KEY_SESSION = "Session";
    static /* synthetic */ Class class$java$lang$Throwable = null;
    private static final Logger logger = Logger.getLogger("freemarker.servlet");
    public static final long serialVersionUID = -2440216393145762479L;
    private Configuration config;
    private String contentType;
    protected boolean debug;
    private boolean noCharsetInContentType;
    private boolean nocache;
    private String templatePath;
    private ObjectWrapper wrapper;

    /* access modifiers changed from: protected */
    public void initializeServletContext(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
    }

    /* access modifiers changed from: protected */
    public void initializeSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
    }

    /* access modifiers changed from: protected */
    public void postTemplateProcess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Template template, TemplateModel templateModel) throws ServletException, IOException {
    }

    /* access modifiers changed from: protected */
    public boolean preTemplateProcess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Template template, TemplateModel templateModel) throws ServletException, IOException {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean preprocessRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        return false;
    }

    static {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.roll(1, -1);
        EXPIRATION_DATE = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).format(gregorianCalendar.getTime());
    }

    public void init() throws ServletException {
        try {
            Configuration createConfiguration = createConfiguration();
            this.config = createConfiguration;
            createConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
            this.contentType = DEFAULT_CONTENT_TYPE;
            this.wrapper = createObjectWrapper();
            Logger logger2 = logger;
            if (logger2.isDebugEnabled()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Using object wrapper of class ");
                stringBuffer.append(this.wrapper.getClass().getName());
                logger2.debug(stringBuffer.toString());
            }
            this.config.setObjectWrapper(this.wrapper);
            String initParameter = getInitParameter(INITPARAM_TEMPLATE_PATH);
            this.templatePath = initParameter;
            if (initParameter == null) {
                this.templatePath = "class://";
            }
            this.config.setTemplateLoader(createTemplateLoader(this.templatePath));
            Enumeration initParameterNames = getServletConfig().getInitParameterNames();
            while (initParameterNames.hasMoreElements()) {
                String str = (String) initParameterNames.nextElement();
                String initParameter2 = getInitParameter(str);
                if (str == null) {
                    throw new ServletException("init-param without param-name. Maybe the web.xml is not well-formed?");
                } else if (initParameter2 == null) {
                    throw new ServletException("init-param without param-value. Maybe the web.xml is not well-formed?");
                } else if (!str.equals(DEPR_INITPARAM_OBJECT_WRAPPER) && !str.equals(Configurable.OBJECT_WRAPPER_KEY)) {
                    if (!str.equals(INITPARAM_TEMPLATE_PATH)) {
                        if (str.equals(DEPR_INITPARAM_ENCODING)) {
                            if (getInitParameter(Configuration.DEFAULT_ENCODING_KEY) == null) {
                                this.config.setDefaultEncoding(initParameter2);
                            } else {
                                throw new ServletException("Conflicting init-params: default_encoding and DefaultEncoding");
                            }
                        } else if (str.equals(DEPR_INITPARAM_TEMPLATE_DELAY)) {
                            if (getInitParameter(Configuration.TEMPLATE_UPDATE_DELAY_KEY) == null) {
                                try {
                                    this.config.setTemplateUpdateDelay(Integer.parseInt(initParameter2));
                                } catch (NumberFormatException unused) {
                                }
                            } else {
                                throw new ServletException("Conflicting init-params: template_update_delay and TemplateDelay");
                            }
                        } else if (str.equals(DEPR_INITPARAM_TEMPLATE_EXCEPTION_HANDLER)) {
                            if (getInitParameter(Configurable.TEMPLATE_EXCEPTION_HANDLER_KEY) != null) {
                                throw new ServletException("Conflicting init-params: template_exception_handler and TemplateExceptionHandler");
                            } else if (DEPR_INITPARAM_TEMPLATE_EXCEPTION_HANDLER_RETHROW.equals(initParameter2)) {
                                this.config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                            } else if ("debug".equals(initParameter2)) {
                                this.config.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
                            } else if (DEPR_INITPARAM_TEMPLATE_EXCEPTION_HANDLER_HTML_DEBUG.equals(initParameter2)) {
                                this.config.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
                            } else if (DEPR_INITPARAM_TEMPLATE_EXCEPTION_HANDLER_IGNORE.equals(initParameter2)) {
                                this.config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
                            } else {
                                StringBuffer stringBuffer2 = new StringBuffer();
                                stringBuffer2.append("Invalid value for servlet init-param TemplateExceptionHandler: ");
                                stringBuffer2.append(initParameter2);
                                throw new ServletException(stringBuffer2.toString());
                            }
                        } else if (str.equals(INITPARAM_NOCACHE)) {
                            this.nocache = StringUtil.getYesNo(initParameter2);
                        } else if (str.equals("debug")) {
                            if (getInitParameter(INITPARAM_DEBUG) == null) {
                                this.debug = StringUtil.getYesNo(initParameter2);
                            } else {
                                throw new ServletException("Conflicting init-params: Debug and debug");
                            }
                        } else if (str.equals(INITPARAM_DEBUG)) {
                            this.debug = StringUtil.getYesNo(initParameter2);
                        } else if (str.equals(INITPARAM_CONTENT_TYPE)) {
                            this.contentType = initParameter2;
                        } else {
                            this.config.setSetting(str, initParameter2);
                        }
                    }
                }
            }
            this.noCharsetInContentType = true;
            int indexOf = this.contentType.toLowerCase().indexOf("charset=");
            if (indexOf != -1) {
                char c = ' ';
                int i = indexOf - 1;
                while (true) {
                    if (i < 0) {
                        break;
                    }
                    c = this.contentType.charAt(i);
                    if (!Character.isWhitespace(c)) {
                        break;
                    }
                    i--;
                }
                if (i == -1 || c == ';') {
                    this.noCharsetInContentType = false;
                }
            }
        } catch (ServletException e) {
            throw e;
        } catch (Exception e2) {
            throw new ServletException(e2);
        }
    }

    /* access modifiers changed from: protected */
    public TemplateLoader createTemplateLoader(String str) throws IOException {
        if (str.startsWith("class://")) {
            return new ClassTemplateLoader(getClass(), str.substring(7));
        }
        if (str.startsWith("file://")) {
            return new FileTemplateLoader(new File(str.substring(7)));
        }
        return new WebappTemplateLoader(getServletContext(), str);
    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        process(httpServletRequest, httpServletResponse);
    }

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        process(httpServletRequest, httpServletResponse);
    }

    private void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        TemplateModel createModel;
        if (!preprocessRequest(httpServletRequest, httpServletResponse)) {
            String requestUrlToTemplatePath = requestUrlToTemplatePath(httpServletRequest);
            if (this.debug) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Requested template: ");
                stringBuffer.append(StringUtil.jQuoteNoXSS(requestUrlToTemplatePath));
                log(stringBuffer.toString());
            }
            try {
                Template template = this.config.getTemplate(requestUrlToTemplatePath, deduceLocale(requestUrlToTemplatePath, httpServletRequest, httpServletResponse));
                Object customAttribute = template.getCustomAttribute(FirebaseAnalytics.Param.CONTENT_TYPE);
                if (customAttribute != null) {
                    httpServletResponse.setContentType(customAttribute.toString());
                } else if (this.noCharsetInContentType) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(this.contentType);
                    stringBuffer2.append("; charset=");
                    stringBuffer2.append(template.getEncoding());
                    httpServletResponse.setContentType(stringBuffer2.toString());
                } else {
                    httpServletResponse.setContentType(this.contentType);
                }
                setBrowserCachingPolicy(httpServletResponse);
                try {
                    createModel = createModel(this.wrapper, getServletContext(), httpServletRequest, httpServletResponse);
                    if (preTemplateProcess(httpServletRequest, httpServletResponse, template, createModel)) {
                        template.process(createModel, httpServletResponse.getWriter());
                        postTemplateProcess(httpServletRequest, httpServletResponse, template, createModel);
                    }
                } catch (TemplateException e) {
                    if (this.config.getTemplateExceptionHandler().getClass().getName().indexOf(INITPARAM_DEBUG) != -1) {
                        log("Error executing FreeMarker template", e);
                        return;
                    }
                    ServletException servletException = new ServletException("Error executing FreeMarker template", e);
                    try {
                        Class<?> cls = servletException.getClass();
                        Class[] clsArr = new Class[1];
                        Class cls2 = class$java$lang$Throwable;
                        if (cls2 == null) {
                            cls2 = class$("java.lang.Throwable");
                            class$java$lang$Throwable = cls2;
                        }
                        clsArr[0] = cls2;
                        cls.getMethod("initCause", clsArr).invoke(servletException, new Object[]{e});
                    } catch (Exception unused) {
                    }
                    throw servletException;
                } catch (Throwable th) {
                    postTemplateProcess(httpServletRequest, httpServletResponse, template, createModel);
                    throw th;
                }
            } catch (FileNotFoundException unused2) {
                httpServletResponse.sendError(WalletConstants.ERROR_CODE_INVALID_PARAMETERS);
            }
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public Locale deduceLocale(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return this.config.getLocale();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        if (r7.getRequest() != r8) goto L_0x0075;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public freemarker.template.TemplateModel createModel(freemarker.template.ObjectWrapper r6, javax.servlet.ServletContext r7, javax.servlet.http.HttpServletRequest r8, javax.servlet.http.HttpServletResponse r9) throws freemarker.template.TemplateModelException {
        /*
            r5 = this;
            java.lang.String r0 = ".freemarker.Request"
            java.lang.String r1 = ".freemarker.Application"
            freemarker.ext.servlet.AllHttpScopesHashModel r2 = new freemarker.ext.servlet.AllHttpScopesHashModel     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r2.<init>(r6, r7, r8)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            java.lang.Object r3 = r7.getAttribute(r1)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            freemarker.ext.servlet.ServletContextHashModel r3 = (freemarker.ext.servlet.ServletContextHashModel) r3     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            java.lang.String r4 = ".freemarker.JspTaglibs"
            if (r3 != 0) goto L_0x0026
            freemarker.ext.servlet.ServletContextHashModel r3 = new freemarker.ext.servlet.ServletContextHashModel     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r3.<init>((javax.servlet.GenericServlet) r5, (freemarker.template.ObjectWrapper) r6)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r7.setAttribute(r1, r3)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            freemarker.ext.jsp.TaglibFactory r1 = new freemarker.ext.jsp.TaglibFactory     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r1.<init>(r7)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r7.setAttribute(r4, r1)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r5.initializeServletContext(r8, r9)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
        L_0x0026:
            java.lang.String r1 = "Application"
            r2.putUnlistedModel(r1, r3)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            java.lang.String r1 = "__FreeMarkerServlet.Application__"
            r2.putUnlistedModel(r1, r3)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            java.lang.String r1 = "JspTaglibs"
            java.lang.Object r7 = r7.getAttribute(r4)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            freemarker.template.TemplateModel r7 = (freemarker.template.TemplateModel) r7     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r2.putUnlistedModel(r1, r7)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r7 = 0
            javax.servlet.http.HttpSession r7 = r8.getSession(r7)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            if (r7 == 0) goto L_0x005b
            java.lang.String r1 = ".freemarker.Session"
            java.lang.Object r1 = r7.getAttribute(r1)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            freemarker.ext.servlet.HttpSessionHashModel r1 = (freemarker.ext.servlet.HttpSessionHashModel) r1     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            if (r1 == 0) goto L_0x0052
            boolean r3 = r1.isOrphaned(r7)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            if (r3 == 0) goto L_0x0060
        L_0x0052:
            freemarker.ext.servlet.HttpSessionHashModel r1 = new freemarker.ext.servlet.HttpSessionHashModel     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r1.<init>(r7, r6)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r5.initializeSessionAndInstallModel(r8, r9, r1, r7)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            goto L_0x0060
        L_0x005b:
            freemarker.ext.servlet.HttpSessionHashModel r1 = new freemarker.ext.servlet.HttpSessionHashModel     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r1.<init>(r5, r8, r9, r6)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
        L_0x0060:
            java.lang.String r7 = "Session"
            r2.putUnlistedModel(r7, r1)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            java.lang.Object r7 = r8.getAttribute(r0)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            freemarker.ext.servlet.HttpRequestHashModel r7 = (freemarker.ext.servlet.HttpRequestHashModel) r7     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            java.lang.String r1 = ".freemarker.RequestParameters"
            if (r7 == 0) goto L_0x0075
            javax.servlet.http.HttpServletRequest r3 = r7.getRequest()     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            if (r3 == r8) goto L_0x0084
        L_0x0075:
            freemarker.ext.servlet.HttpRequestHashModel r7 = new freemarker.ext.servlet.HttpRequestHashModel     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r7.<init>(r8, r9, r6)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r8.setAttribute(r0, r7)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            freemarker.ext.servlet.HttpRequestParametersHashModel r6 = r5.createRequestParametersHashModel(r8)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r8.setAttribute(r1, r6)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
        L_0x0084:
            java.lang.String r6 = "Request"
            r2.putUnlistedModel(r6, r7)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            java.lang.String r6 = "include_page"
            freemarker.ext.servlet.IncludePage r0 = new freemarker.ext.servlet.IncludePage     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r0.<init>(r8, r9)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            r2.putUnlistedModel(r6, r0)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            java.lang.String r6 = "__FreeMarkerServlet.Request__"
            r2.putUnlistedModel(r6, r7)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            java.lang.Object r6 = r8.getAttribute(r1)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            freemarker.ext.servlet.HttpRequestParametersHashModel r6 = (freemarker.ext.servlet.HttpRequestParametersHashModel) r6     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            java.lang.String r7 = "RequestParameters"
            r2.putUnlistedModel(r7, r6)     // Catch:{ ServletException -> 0x00ab, IOException -> 0x00a4 }
            return r2
        L_0x00a4:
            r6 = move-exception
            freemarker.template.TemplateModelException r7 = new freemarker.template.TemplateModelException
            r7.<init>((java.lang.Exception) r6)
            throw r7
        L_0x00ab:
            r6 = move-exception
            freemarker.template.TemplateModelException r7 = new freemarker.template.TemplateModelException
            r7.<init>((java.lang.Exception) r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.servlet.FreemarkerServlet.createModel(freemarker.template.ObjectWrapper, javax.servlet.ServletContext, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse):freemarker.template.TemplateModel");
    }

    /* access modifiers changed from: package-private */
    public void initializeSessionAndInstallModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSessionHashModel httpSessionHashModel, HttpSession httpSession) throws ServletException, IOException {
        httpSession.setAttribute(ATTR_SESSION_MODEL, httpSessionHashModel);
        initializeSession(httpServletRequest, httpServletResponse);
    }

    /* access modifiers changed from: protected */
    public String requestUrlToTemplatePath(HttpServletRequest httpServletRequest) {
        String str = (String) httpServletRequest.getAttribute("javax.servlet.include.servlet_path");
        if (str != null) {
            String str2 = (String) httpServletRequest.getAttribute("javax.servlet.include.path_info");
            return str2 == null ? str : str2;
        }
        String pathInfo = httpServletRequest.getPathInfo();
        if (pathInfo != null) {
            return pathInfo;
        }
        String servletPath = httpServletRequest.getServletPath();
        return servletPath != null ? servletPath : "";
    }

    /* access modifiers changed from: protected */
    public Configuration createConfiguration() {
        return new Configuration();
    }

    /* access modifiers changed from: protected */
    public ObjectWrapper createObjectWrapper() {
        String initParameter = getServletConfig().getInitParameter(DEPR_INITPARAM_OBJECT_WRAPPER);
        if (initParameter == null) {
            String initParameter2 = getInitParameter(Configurable.OBJECT_WRAPPER_KEY);
            if (initParameter2 == null) {
                return ObjectWrapper.DEFAULT_WRAPPER;
            }
            try {
                this.config.setSetting(Configurable.OBJECT_WRAPPER_KEY, initParameter2);
                return this.config.getObjectWrapper();
            } catch (TemplateException e) {
                throw new RuntimeException(e.toString());
            }
        } else if (getInitParameter(Configurable.OBJECT_WRAPPER_KEY) != null) {
            throw new RuntimeException("Conflicting init-params: object_wrapper and ObjectWrapper");
        } else if (DEPR_INITPARAM_WRAPPER_BEANS.equals(initParameter)) {
            return ObjectWrapper.BEANS_WRAPPER;
        } else {
            if (DEPR_INITPARAM_WRAPPER_SIMPLE.equals(initParameter)) {
                return ObjectWrapper.SIMPLE_WRAPPER;
            }
            if (!DEPR_INITPARAM_WRAPPER_JYTHON.equals(initParameter)) {
                return ObjectWrapper.DEFAULT_WRAPPER;
            }
            try {
                return (ObjectWrapper) Class.forName("freemarker.ext.jython.JythonWrapper").newInstance();
            } catch (InstantiationException e2) {
                throw new InstantiationError(e2.getMessage());
            } catch (IllegalAccessException e3) {
                throw new IllegalAccessError(e3.getMessage());
            } catch (ClassNotFoundException e4) {
                throw new NoClassDefFoundError(e4.getMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public ObjectWrapper getObjectWrapper() {
        return this.wrapper;
    }

    /* access modifiers changed from: protected */
    public final String getTemplatePath() {
        return this.templatePath;
    }

    /* access modifiers changed from: protected */
    public HttpRequestParametersHashModel createRequestParametersHashModel(HttpServletRequest httpServletRequest) {
        return new HttpRequestParametersHashModel(httpServletRequest);
    }

    /* access modifiers changed from: protected */
    public Configuration getConfiguration() {
        return this.config;
    }

    private void setBrowserCachingPolicy(HttpServletResponse httpServletResponse) {
        if (this.nocache) {
            httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setHeader("Expires", EXPIRATION_DATE);
        }
    }
}
