package freemarker.debug.impl;

import freemarker.core.DebugBreak;
import freemarker.core.TemplateElement;
import freemarker.debug.Breakpoint;
import freemarker.debug.DebuggerListener;
import freemarker.template.Template;
import freemarker.template.utility.UndeclaredThrowableException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class RmiDebuggerService extends DebuggerService {
    private final RmiDebuggerImpl debugger;
    private final Map listeners = new HashMap();
    private final ReferenceQueue refQueue = new ReferenceQueue();
    private DebuggerServer server;
    private final HashSet suspendedEnvironments = new HashSet();
    private final Map templateDebugInfos = new HashMap();

    RmiDebuggerService() {
        try {
            RmiDebuggerImpl rmiDebuggerImpl = new RmiDebuggerImpl(this);
            this.debugger = rmiDebuggerImpl;
            DebuggerServer debuggerServer = new DebuggerServer(RemoteObject.toStub(rmiDebuggerImpl));
            this.server = debuggerServer;
            debuggerServer.start();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new UndeclaredThrowableException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public List getBreakpointsSpi(String str) {
        List list;
        synchronized (this.templateDebugInfos) {
            TemplateDebugInfo findTemplateDebugInfo = findTemplateDebugInfo(str);
            list = findTemplateDebugInfo == null ? Collections.EMPTY_LIST : findTemplateDebugInfo.breakpoints;
        }
        return list;
    }

    /* access modifiers changed from: package-private */
    public List getBreakpointsSpi() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.templateDebugInfos) {
            for (TemplateDebugInfo templateDebugInfo : this.templateDebugInfos.values()) {
                arrayList.addAll(templateDebugInfo.breakpoints);
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    boolean suspendEnvironmentSpi(freemarker.core.Environment r3, java.lang.String r4, int r5) throws java.rmi.RemoteException {
        /*
            r2 = this;
            java.lang.Object r3 = freemarker.debug.impl.RmiDebuggedEnvironmentImpl.getCachedWrapperFor(r3)
            freemarker.debug.impl.RmiDebuggedEnvironmentImpl r3 = (freemarker.debug.impl.RmiDebuggedEnvironmentImpl) r3
            java.util.HashSet r0 = r2.suspendedEnvironments
            monitor-enter(r0)
            java.util.HashSet r1 = r2.suspendedEnvironments     // Catch:{ all -> 0x005e }
            r1.add(r3)     // Catch:{ all -> 0x005e }
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            freemarker.debug.EnvironmentSuspendedEvent r0 = new freemarker.debug.EnvironmentSuspendedEvent     // Catch:{ all -> 0x0050 }
            r0.<init>(r2, r4, r5, r3)     // Catch:{ all -> 0x0050 }
            java.util.Map r4 = r2.listeners     // Catch:{ all -> 0x0050 }
            monitor-enter(r4)     // Catch:{ all -> 0x0050 }
            java.util.Map r5 = r2.listeners     // Catch:{ all -> 0x004d }
            java.util.Collection r5 = r5.values()     // Catch:{ all -> 0x004d }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x004d }
        L_0x0021:
            boolean r1 = r5.hasNext()     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x0031
            java.lang.Object r1 = r5.next()     // Catch:{ all -> 0x004d }
            freemarker.debug.DebuggerListener r1 = (freemarker.debug.DebuggerListener) r1     // Catch:{ all -> 0x004d }
            r1.environmentSuspended(r0)     // Catch:{ all -> 0x004d }
            goto L_0x0021
        L_0x0031:
            monitor-exit(r4)     // Catch:{ all -> 0x004d }
            monitor-enter(r3)     // Catch:{ all -> 0x0050 }
            r3.wait()     // Catch:{ InterruptedException -> 0x0039 }
            goto L_0x0039
        L_0x0037:
            r4 = move-exception
            goto L_0x004b
        L_0x0039:
            monitor-exit(r3)     // Catch:{ all -> 0x0037 }
            boolean r4 = r3.isStopped()     // Catch:{ all -> 0x0050 }
            java.util.HashSet r5 = r2.suspendedEnvironments
            monitor-enter(r5)
            java.util.HashSet r0 = r2.suspendedEnvironments     // Catch:{ all -> 0x0048 }
            r0.remove(r3)     // Catch:{ all -> 0x0048 }
            monitor-exit(r5)     // Catch:{ all -> 0x0048 }
            return r4
        L_0x0048:
            r3 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0048 }
            throw r3
        L_0x004b:
            monitor-exit(r3)     // Catch:{ all -> 0x0037 }
            throw r4     // Catch:{ all -> 0x0050 }
        L_0x004d:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x004d }
            throw r5     // Catch:{ all -> 0x0050 }
        L_0x0050:
            r4 = move-exception
            java.util.HashSet r5 = r2.suspendedEnvironments
            monitor-enter(r5)
            java.util.HashSet r0 = r2.suspendedEnvironments     // Catch:{ all -> 0x005b }
            r0.remove(r3)     // Catch:{ all -> 0x005b }
            monitor-exit(r5)     // Catch:{ all -> 0x005b }
            throw r4
        L_0x005b:
            r3 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x005b }
            throw r3
        L_0x005e:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.debug.impl.RmiDebuggerService.suspendEnvironmentSpi(freemarker.core.Environment, java.lang.String, int):boolean");
    }

    /* access modifiers changed from: package-private */
    public void registerTemplateSpi(Template template) {
        String name = template.getName();
        synchronized (this.templateDebugInfos) {
            TemplateDebugInfo createTemplateDebugInfo = createTemplateDebugInfo(name);
            createTemplateDebugInfo.templates.add(new TemplateReference(name, template, this.refQueue));
            for (Breakpoint insertDebugBreak : createTemplateDebugInfo.breakpoints) {
                insertDebugBreak(template, insertDebugBreak);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Collection getSuspendedEnvironments() {
        return (Collection) this.suspendedEnvironments.clone();
    }

    /* access modifiers changed from: package-private */
    public Object addDebuggerListener(DebuggerListener debuggerListener) {
        Long l;
        synchronized (this.listeners) {
            l = new Long(System.currentTimeMillis());
            this.listeners.put(l, debuggerListener);
        }
        return l;
    }

    /* access modifiers changed from: package-private */
    public void removeDebuggerListener(Object obj) {
        synchronized (this.listeners) {
            this.listeners.remove(obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void addBreakpoint(Breakpoint breakpoint) {
        String templateName = breakpoint.getTemplateName();
        synchronized (this.templateDebugInfos) {
            TemplateDebugInfo createTemplateDebugInfo = createTemplateDebugInfo(templateName);
            List list = createTemplateDebugInfo.breakpoints;
            int binarySearch = Collections.binarySearch(list, breakpoint);
            if (binarySearch < 0) {
                list.add((-binarySearch) - 1, breakpoint);
                Iterator it = createTemplateDebugInfo.templates.iterator();
                while (it.hasNext()) {
                    Template template = ((TemplateReference) it.next()).getTemplate();
                    if (template == null) {
                        it.remove();
                    } else {
                        insertDebugBreak(template, breakpoint);
                    }
                }
            }
        }
    }

    private static void insertDebugBreak(Template template, Breakpoint breakpoint) {
        TemplateElement findTemplateElement = findTemplateElement(template.getRootTreeNode(), breakpoint.getLine());
        if (findTemplateElement != null) {
            TemplateElement parent = findTemplateElement.getParent();
            parent.setChildAt(parent.getIndex(findTemplateElement), new DebugBreak(findTemplateElement));
        }
    }

    private static TemplateElement findTemplateElement(TemplateElement templateElement, int i) {
        TemplateElement templateElement2 = null;
        if (templateElement.getBeginLine() > i || templateElement.getEndLine() < i) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Enumeration children = templateElement.children();
        while (children.hasMoreElements()) {
            TemplateElement findTemplateElement = findTemplateElement((TemplateElement) children.nextElement(), i);
            if (findTemplateElement != null) {
                arrayList.add(findTemplateElement);
            }
        }
        int i2 = 0;
        while (true) {
            if (i2 >= arrayList.size()) {
                break;
            }
            TemplateElement templateElement3 = (TemplateElement) arrayList.get(i2);
            if (templateElement2 == null) {
                templateElement2 = templateElement3;
            }
            if (templateElement3.getBeginLine() == i && templateElement3.getEndLine() > i) {
                templateElement2 = templateElement3;
            }
            if (templateElement3.getBeginLine() == templateElement3.getEndLine() && templateElement3.getBeginLine() == i) {
                templateElement2 = templateElement3;
                break;
            }
            i2++;
        }
        return templateElement2 != null ? templateElement2 : templateElement;
    }

    private TemplateDebugInfo findTemplateDebugInfo(String str) {
        processRefQueue();
        return (TemplateDebugInfo) this.templateDebugInfos.get(str);
    }

    private TemplateDebugInfo createTemplateDebugInfo(String str) {
        TemplateDebugInfo findTemplateDebugInfo = findTemplateDebugInfo(str);
        if (findTemplateDebugInfo != null) {
            return findTemplateDebugInfo;
        }
        TemplateDebugInfo templateDebugInfo = new TemplateDebugInfo();
        this.templateDebugInfos.put(str, templateDebugInfo);
        return templateDebugInfo;
    }

    /* access modifiers changed from: package-private */
    public void removeBreakpoint(Breakpoint breakpoint) {
        String templateName = breakpoint.getTemplateName();
        synchronized (this.templateDebugInfos) {
            TemplateDebugInfo findTemplateDebugInfo = findTemplateDebugInfo(templateName);
            if (findTemplateDebugInfo != null) {
                List list = findTemplateDebugInfo.breakpoints;
                int binarySearch = Collections.binarySearch(list, breakpoint);
                if (binarySearch >= 0) {
                    list.remove(binarySearch);
                    Iterator it = findTemplateDebugInfo.templates.iterator();
                    while (it.hasNext()) {
                        Template template = ((TemplateReference) it.next()).getTemplate();
                        if (template == null) {
                            it.remove();
                        } else {
                            removeDebugBreak(template, breakpoint);
                        }
                    }
                }
                if (findTemplateDebugInfo.isEmpty()) {
                    this.templateDebugInfos.remove(templateName);
                }
            }
        }
    }

    private void removeDebugBreak(Template template, Breakpoint breakpoint) {
        TemplateElement findTemplateElement = findTemplateElement(template.getRootTreeNode(), breakpoint.getLine());
        if (findTemplateElement != null) {
            DebugBreak debugBreak = null;
            while (true) {
                if (findTemplateElement == null) {
                    break;
                } else if (findTemplateElement instanceof DebugBreak) {
                    debugBreak = (DebugBreak) findTemplateElement;
                    break;
                } else {
                    findTemplateElement = (TemplateElement) findTemplateElement.getParent();
                }
            }
            if (debugBreak != null) {
                TemplateElement parent = debugBreak.getParent();
                parent.setChildAt(parent.getIndex(debugBreak), debugBreak.getChildAt(0));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void removeBreakpoints(String str) {
        synchronized (this.templateDebugInfos) {
            TemplateDebugInfo findTemplateDebugInfo = findTemplateDebugInfo(str);
            if (findTemplateDebugInfo != null) {
                removeBreakpoints(findTemplateDebugInfo);
                if (findTemplateDebugInfo.isEmpty()) {
                    this.templateDebugInfos.remove(str);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void removeBreakpoints() {
        synchronized (this.templateDebugInfos) {
            Iterator it = this.templateDebugInfos.values().iterator();
            while (it.hasNext()) {
                TemplateDebugInfo templateDebugInfo = (TemplateDebugInfo) it.next();
                removeBreakpoints(templateDebugInfo);
                if (templateDebugInfo.isEmpty()) {
                    it.remove();
                }
            }
        }
    }

    private void removeBreakpoints(TemplateDebugInfo templateDebugInfo) {
        templateDebugInfo.breakpoints.clear();
        Iterator it = templateDebugInfo.templates.iterator();
        while (it.hasNext()) {
            Template template = ((TemplateReference) it.next()).getTemplate();
            if (template == null) {
                it.remove();
            } else {
                removeDebugBreaks(template.getRootTreeNode());
            }
        }
    }

    private void removeDebugBreaks(TemplateElement templateElement) {
        int childCount = templateElement.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TemplateElement childAt = templateElement.getChildAt(i);
            while (childAt instanceof DebugBreak) {
                childAt = (TemplateElement) childAt.getChildAt(0);
                templateElement.setChildAt(i, childAt);
            }
            removeDebugBreaks(childAt);
        }
    }

    private static final class TemplateDebugInfo {
        final List breakpoints;
        final List templates;

        private TemplateDebugInfo() {
            this.templates = new ArrayList();
            this.breakpoints = new ArrayList();
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            return this.templates.isEmpty() && this.breakpoints.isEmpty();
        }
    }

    private static final class TemplateReference extends WeakReference {
        final String templateName;

        TemplateReference(String str, Template template, ReferenceQueue referenceQueue) {
            super(template, referenceQueue);
            this.templateName = str;
        }

        /* access modifiers changed from: package-private */
        public Template getTemplate() {
            return (Template) get();
        }
    }

    private void processRefQueue() {
        while (true) {
            TemplateReference templateReference = (TemplateReference) this.refQueue.poll();
            if (templateReference != null) {
                TemplateDebugInfo findTemplateDebugInfo = findTemplateDebugInfo(templateReference.templateName);
                if (findTemplateDebugInfo != null) {
                    findTemplateDebugInfo.templates.remove(templateReference);
                    if (findTemplateDebugInfo.isEmpty()) {
                        this.templateDebugInfos.remove(templateReference.templateName);
                    }
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void shutdownSpi() {
        this.server.stop();
        try {
            UnicastRemoteObject.unexportObject(this.debugger, true);
        } catch (Exception unused) {
        }
        RmiDebuggedEnvironmentImpl.cleanup();
    }
}
