package freemarker.ext.servlet;

import freemarker.core.Environment;
import freemarker.core._DelayedFTLTypeDescription;
import freemarker.core._MiscTemplateException;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.utility.Collections12;
import freemarker.template.utility.DeepUnwrap;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class IncludePage implements TemplateDirectiveModel {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public IncludePage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.request = httpServletRequest;
        this.response = httpServletResponse;
    }

    public void execute(Environment environment, Map map, TemplateModel[] templateModelArr, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        ServletResponse servletResponse;
        boolean z;
        ServletRequest servletRequest;
        Map map2;
        TemplateModel templateModel = (TemplateModel) map.get("path");
        if (templateModel == null) {
            throw new _MiscTemplateException(environment, "Missing required parameter \"path\"");
        } else if (templateModel instanceof TemplateScalarModel) {
            String asString = ((TemplateScalarModel) templateModel).getAsString();
            if (asString != null) {
                Writer out = environment.getOut();
                if (out == this.response.getWriter()) {
                    servletResponse = this.response;
                } else {
                    final PrintWriter printWriter = out instanceof PrintWriter ? (PrintWriter) out : new PrintWriter(out);
                    servletResponse = new HttpServletResponseWrapper(this, this.response) {
                        private final /* synthetic */ IncludePage this$0;

                        {
                            this.this$0 = r1;
                        }

                        public PrintWriter getWriter() {
                            return printWriter;
                        }
                    };
                }
                TemplateModel templateModel2 = (TemplateModel) map.get("inherit_params");
                if (templateModel2 == null) {
                    z = true;
                } else if (templateModel2 instanceof TemplateBooleanModel) {
                    z = ((TemplateBooleanModel) templateModel2).getAsBoolean();
                } else {
                    throw new _MiscTemplateException(environment, new Object[]{"\"inherit_params\" should be a boolean but it's a(n) ", templateModel2.getClass().getName(), " instead"});
                }
                TemplateModel templateModel3 = (TemplateModel) map.get("params");
                if (templateModel3 != null || !z) {
                    if (templateModel3 != null) {
                        Object unwrap = DeepUnwrap.unwrap(templateModel3);
                        if (unwrap instanceof Map) {
                            map2 = (Map) unwrap;
                        } else {
                            throw new _MiscTemplateException(environment, new Object[]{"Expected \"params\" to unwrap into a java.util.Map. It unwrapped into ", unwrap.getClass().getName(), " instead."});
                        }
                    } else {
                        map2 = Collections12.EMPTY_MAP;
                    }
                    servletRequest = new CustomParamsRequest(this.request, map2, z);
                } else {
                    servletRequest = this.request;
                }
                try {
                    this.request.getRequestDispatcher(asString).include(servletRequest, servletResponse);
                } catch (ServletException e) {
                    throw new _MiscTemplateException((Throwable) e, environment);
                }
            } else {
                throw new _MiscTemplateException(environment, "String value of \"path\" parameter is null");
            }
        } else {
            throw new _MiscTemplateException(environment, new Object[]{"Expected a scalar model. \"path\" is instead ", new _DelayedFTLTypeDescription(templateModel)});
        }
    }

    private static final class CustomParamsRequest extends HttpServletRequestWrapper {
        private final HashMap paramsMap;

        private CustomParamsRequest(HttpServletRequest httpServletRequest, Map map, boolean z) {
            super(httpServletRequest);
            String[] strArr;
            int i;
            String[] strArr2;
            this.paramsMap = z ? new HashMap(httpServletRequest.getParameterMap()) : new HashMap();
            for (Map.Entry entry : map.entrySet()) {
                String valueOf = String.valueOf(entry.getKey());
                Object value = entry.getValue();
                if (value == null) {
                    strArr = new String[]{null};
                } else if (value instanceof String[]) {
                    strArr = (String[]) value;
                } else {
                    if (value instanceof Collection) {
                        Collection<Object> collection = (Collection) value;
                        strArr2 = new String[collection.size()];
                        int i2 = 0;
                        for (Object valueOf2 : collection) {
                            strArr2[i2] = String.valueOf(valueOf2);
                            i2++;
                        }
                    } else if (value.getClass().isArray()) {
                        int length = Array.getLength(value);
                        String[] strArr3 = new String[length];
                        for (int i3 = 0; i3 < length; i3++) {
                            strArr3[i3] = String.valueOf(Array.get(value, i3));
                        }
                        strArr = strArr3;
                    } else {
                        strArr2 = new String[]{String.valueOf(value)};
                    }
                    strArr = strArr2;
                }
                String[] strArr4 = (String[]) this.paramsMap.get(valueOf);
                if (strArr4 == null) {
                    i = 0;
                } else {
                    i = strArr4.length;
                }
                if (i == 0) {
                    this.paramsMap.put(valueOf, strArr);
                } else {
                    int length2 = strArr.length;
                    if (length2 > 0) {
                        String[] strArr5 = new String[(i + length2)];
                        System.arraycopy(strArr, 0, strArr5, 0, length2);
                        System.arraycopy(strArr4, 0, strArr5, length2, i);
                        this.paramsMap.put(valueOf, strArr5);
                    }
                }
            }
        }

        public String[] getParameterValues(String str) {
            String[] strArr = (String[]) this.paramsMap.get(str);
            if (strArr != null) {
                return (String[]) strArr.clone();
            }
            return null;
        }

        public String getParameter(String str) {
            String[] strArr = (String[]) this.paramsMap.get(str);
            if (strArr == null || strArr.length <= 0) {
                return null;
            }
            return strArr[0];
        }

        public Enumeration getParameterNames() {
            return Collections.enumeration(this.paramsMap.keySet());
        }

        public Map getParameterMap() {
            HashMap hashMap = (HashMap) this.paramsMap.clone();
            for (Map.Entry entry : hashMap.entrySet()) {
                entry.setValue(((String[]) entry.getValue()).clone());
            }
            return Collections.unmodifiableMap(hashMap);
        }
    }
}
