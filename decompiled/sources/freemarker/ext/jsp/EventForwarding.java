package freemarker.ext.jsp;

import freemarker.log.Logger;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class EventForwarding implements ServletContextAttributeListener, ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    private static final String ATTR_NAME;
    static /* synthetic */ Class class$freemarker$ext$jsp$EventForwarding;
    private static final Logger logger = Logger.getLogger("freemarker.jsp");
    private final List httpSessionAttributeListeners = new ArrayList();
    private final List httpSessionListeners = new ArrayList();
    private final List servletContextAttributeListeners = new ArrayList();
    private final List servletContextListeners = new ArrayList();

    static {
        Class cls = class$freemarker$ext$jsp$EventForwarding;
        if (cls == null) {
            cls = class$("freemarker.ext.jsp.EventForwarding");
            class$freemarker$ext$jsp$EventForwarding = cls;
        }
        ATTR_NAME = cls.getName();
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public void addListeners(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            addListener((EventListener) it.next());
        }
    }

    private void addListener(EventListener eventListener) {
        boolean z;
        boolean z2 = true;
        if (eventListener instanceof ServletContextAttributeListener) {
            addListener(this.servletContextAttributeListeners, eventListener);
            z = true;
        } else {
            z = false;
        }
        if (eventListener instanceof ServletContextListener) {
            addListener(this.servletContextListeners, eventListener);
            z = true;
        }
        if (eventListener instanceof HttpSessionAttributeListener) {
            addListener(this.httpSessionAttributeListeners, eventListener);
            z = true;
        }
        if (eventListener instanceof HttpSessionListener) {
            addListener(this.httpSessionListeners, eventListener);
        } else {
            z2 = z;
        }
        if (!z2) {
            Logger logger2 = logger;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Listener of class ");
            stringBuffer.append(eventListener.getClass().getName());
            stringBuffer.append("wasn't registered as it doesn't implement any of the ");
            stringBuffer.append("recognized listener interfaces.");
            logger2.warn(stringBuffer.toString());
        }
    }

    static EventForwarding getInstance(ServletContext servletContext) {
        return (EventForwarding) servletContext.getAttribute(ATTR_NAME);
    }

    private void addListener(List list, EventListener eventListener) {
        synchronized (list) {
            list.add(eventListener);
        }
    }

    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        synchronized (this.servletContextAttributeListeners) {
            int size = this.servletContextAttributeListeners.size();
            for (int i = 0; i < size; i++) {
                ((ServletContextAttributeListener) this.servletContextAttributeListeners.get(i)).attributeAdded(servletContextAttributeEvent);
            }
        }
    }

    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        synchronized (this.servletContextAttributeListeners) {
            int size = this.servletContextAttributeListeners.size();
            for (int i = 0; i < size; i++) {
                ((ServletContextAttributeListener) this.servletContextAttributeListeners.get(i)).attributeRemoved(servletContextAttributeEvent);
            }
        }
    }

    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        synchronized (this.servletContextAttributeListeners) {
            int size = this.servletContextAttributeListeners.size();
            for (int i = 0; i < size; i++) {
                ((ServletContextAttributeListener) this.servletContextAttributeListeners.get(i)).attributeReplaced(servletContextAttributeEvent);
            }
        }
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute(ATTR_NAME, this);
        synchronized (this.servletContextListeners) {
            int size = this.servletContextListeners.size();
            for (int i = 0; i < size; i++) {
                ((ServletContextListener) this.servletContextListeners.get(i)).contextInitialized(servletContextEvent);
            }
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        synchronized (this.servletContextListeners) {
            for (int size = this.servletContextListeners.size() - 1; size >= 0; size--) {
                ((ServletContextListener) this.servletContextListeners.get(size)).contextDestroyed(servletContextEvent);
            }
        }
    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        synchronized (this.httpSessionListeners) {
            int size = this.httpSessionListeners.size();
            for (int i = 0; i < size; i++) {
                ((HttpSessionListener) this.httpSessionListeners.get(i)).sessionCreated(httpSessionEvent);
            }
        }
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        synchronized (this.httpSessionListeners) {
            for (int size = this.httpSessionListeners.size() - 1; size >= 0; size--) {
                ((HttpSessionListener) this.httpSessionListeners.get(size)).sessionDestroyed(httpSessionEvent);
            }
        }
    }

    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        synchronized (this.httpSessionAttributeListeners) {
            int size = this.httpSessionAttributeListeners.size();
            for (int i = 0; i < size; i++) {
                ((HttpSessionAttributeListener) this.httpSessionAttributeListeners.get(i)).attributeAdded(httpSessionBindingEvent);
            }
        }
    }

    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        synchronized (this.httpSessionAttributeListeners) {
            int size = this.httpSessionAttributeListeners.size();
            for (int i = 0; i < size; i++) {
                ((HttpSessionAttributeListener) this.httpSessionAttributeListeners.get(i)).attributeRemoved(httpSessionBindingEvent);
            }
        }
    }

    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        synchronized (this.httpSessionAttributeListeners) {
            int size = this.httpSessionAttributeListeners.size();
            for (int i = 0; i < size; i++) {
                ((HttpSessionAttributeListener) this.httpSessionAttributeListeners.get(i)).attributeReplaced(httpSessionBindingEvent);
            }
        }
    }
}
