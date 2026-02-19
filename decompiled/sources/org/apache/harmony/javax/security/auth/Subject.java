package org.apache.harmony.javax.security.auth;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.DomainCombiner;
import java.security.Permission;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.ProtectionDomain;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

public final class Subject implements Serializable {
    private static final AuthPermission _AS = new AuthPermission("doAs");
    private static final AuthPermission _AS_PRIVILEGED = new AuthPermission("doAsPrivileged");
    /* access modifiers changed from: private */
    public static final AuthPermission _PRINCIPALS = new AuthPermission("modifyPrincipals");
    /* access modifiers changed from: private */
    public static final AuthPermission _PRIVATE_CREDENTIALS = new AuthPermission("modifyPrivateCredentials");
    /* access modifiers changed from: private */
    public static final AuthPermission _PUBLIC_CREDENTIALS = new AuthPermission("modifyPublicCredentials");
    private static final AuthPermission _READ_ONLY = new AuthPermission("setReadOnly");
    private static final AuthPermission _SUBJECT = new AuthPermission("getSubject");
    private static final long serialVersionUID = -8308522755600156056L;
    /* access modifiers changed from: private */
    public final Set<Principal> principals;
    private transient SecureSet<Object> privateCredentials;
    private transient SecureSet<Object> publicCredentials;
    private boolean readOnly;

    public Subject() {
        this.principals = new SecureSet(_PRINCIPALS);
        this.publicCredentials = new SecureSet<>(_PUBLIC_CREDENTIALS);
        this.privateCredentials = new SecureSet<>(_PRIVATE_CREDENTIALS);
        this.readOnly = false;
    }

    public Subject(boolean z, Set<? extends Principal> set, Set<?> set2, Set<?> set3) {
        if (set == null || set2 == null || set3 == null) {
            throw null;
        }
        this.principals = new SecureSet(this, _PRINCIPALS, set);
        this.publicCredentials = new SecureSet<>(this, _PUBLIC_CREDENTIALS, set2);
        this.privateCredentials = new SecureSet<>(this, _PRIVATE_CREDENTIALS, set3);
        this.readOnly = z;
    }

    public static Object doAs(Subject subject, PrivilegedAction privilegedAction) {
        checkPermission(_AS);
        return doAs_PrivilegedAction(subject, privilegedAction, AccessController.getContext());
    }

    public static Object doAsPrivileged(Subject subject, PrivilegedAction privilegedAction, AccessControlContext accessControlContext) {
        checkPermission(_AS_PRIVILEGED);
        if (accessControlContext == null) {
            return doAs_PrivilegedAction(subject, privilegedAction, new AccessControlContext(new ProtectionDomain[0]));
        }
        return doAs_PrivilegedAction(subject, privilegedAction, accessControlContext);
    }

    private static Object doAs_PrivilegedAction(Subject subject, PrivilegedAction privilegedAction, final AccessControlContext accessControlContext) {
        final SubjectDomainCombiner subjectDomainCombiner = subject == null ? null : new SubjectDomainCombiner(subject);
        return AccessController.doPrivileged(privilegedAction, (AccessControlContext) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return new AccessControlContext(accessControlContext, subjectDomainCombiner);
            }
        }));
    }

    public static Object doAs(Subject subject, PrivilegedExceptionAction privilegedExceptionAction) throws PrivilegedActionException {
        checkPermission(_AS);
        return doAs_PrivilegedExceptionAction(subject, privilegedExceptionAction, AccessController.getContext());
    }

    public static Object doAsPrivileged(Subject subject, PrivilegedExceptionAction privilegedExceptionAction, AccessControlContext accessControlContext) throws PrivilegedActionException {
        checkPermission(_AS_PRIVILEGED);
        if (accessControlContext == null) {
            return doAs_PrivilegedExceptionAction(subject, privilegedExceptionAction, new AccessControlContext(new ProtectionDomain[0]));
        }
        return doAs_PrivilegedExceptionAction(subject, privilegedExceptionAction, accessControlContext);
    }

    private static Object doAs_PrivilegedExceptionAction(Subject subject, PrivilegedExceptionAction privilegedExceptionAction, final AccessControlContext accessControlContext) throws PrivilegedActionException {
        final SubjectDomainCombiner subjectDomainCombiner = subject == null ? null : new SubjectDomainCombiner(subject);
        return AccessController.doPrivileged(privilegedExceptionAction, (AccessControlContext) AccessController.doPrivileged(new PrivilegedAction<AccessControlContext>() {
            public AccessControlContext run() {
                return new AccessControlContext(accessControlContext, subjectDomainCombiner);
            }
        }));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Subject subject = (Subject) obj;
            return this.principals.equals(subject.principals) && this.publicCredentials.equals(subject.publicCredentials) && this.privateCredentials.equals(subject.privateCredentials);
        }
        return false;
    }

    public Set<Principal> getPrincipals() {
        return this.principals;
    }

    public <T extends Principal> Set<T> getPrincipals(Class<T> cls) {
        return ((SecureSet) this.principals).get(cls);
    }

    public Set<Object> getPrivateCredentials() {
        return this.privateCredentials;
    }

    public <T> Set<T> getPrivateCredentials(Class<T> cls) {
        return this.privateCredentials.get(cls);
    }

    public Set<Object> getPublicCredentials() {
        return this.publicCredentials;
    }

    public <T> Set<T> getPublicCredentials(Class<T> cls) {
        return this.publicCredentials.get(cls);
    }

    public int hashCode() {
        return this.principals.hashCode() + this.privateCredentials.hashCode() + this.publicCredentials.hashCode();
    }

    public void setReadOnly() {
        checkPermission(_READ_ONLY);
        this.readOnly = true;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Subject:\n");
        for (Principal append : this.principals) {
            sb.append("\tPrincipal: ");
            sb.append(append);
            sb.append(10);
        }
        Iterator<Object> it = this.publicCredentials.iterator();
        while (it.hasNext()) {
            sb.append("\tPublic Credential: ");
            sb.append(it.next());
            sb.append(10);
        }
        int length = sb.length() - 1;
        Iterator<Object> it2 = this.privateCredentials.iterator();
        while (it2.hasNext()) {
            try {
                sb.append("\tPrivate Credential: ");
                sb.append(it2.next());
                sb.append(10);
            } catch (SecurityException unused) {
                sb.delete(length, sb.length());
                sb.append("\tPrivate Credentials: no accessible information\n");
            }
        }
        return sb.toString();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.publicCredentials = new SecureSet<>(_PUBLIC_CREDENTIALS);
        this.privateCredentials = new SecureSet<>(_PRIVATE_CREDENTIALS);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public static Subject getSubject(final AccessControlContext accessControlContext) {
        checkPermission(_SUBJECT);
        Objects.requireNonNull(accessControlContext, "auth.09");
        DomainCombiner domainCombiner = (DomainCombiner) AccessController.doPrivileged(new PrivilegedAction<DomainCombiner>() {
            public DomainCombiner run() {
                return accessControlContext.getDomainCombiner();
            }
        });
        if (domainCombiner == null || !(domainCombiner instanceof SubjectDomainCombiner)) {
            return null;
        }
        return ((SubjectDomainCombiner) domainCombiner).getSubject();
    }

    /* access modifiers changed from: private */
    public static void checkPermission(Permission permission) {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(permission);
        }
    }

    /* access modifiers changed from: private */
    public void checkState() {
        if (this.readOnly) {
            throw new IllegalStateException("auth.0A");
        }
    }

    private final class SecureSet<SST> extends AbstractSet<SST> implements Serializable {
        private static final int SET_Principal = 0;
        private static final int SET_PrivCred = 1;
        private static final int SET_PubCred = 2;
        private static final long serialVersionUID = 7911754171111800359L;
        private LinkedList<SST> elements;
        /* access modifiers changed from: private */
        public transient AuthPermission permission;
        private int setType;

        protected SecureSet(AuthPermission authPermission) {
            this.permission = authPermission;
            this.elements = new LinkedList<>();
        }

        protected SecureSet(Subject subject, AuthPermission authPermission, Collection<? extends SST> collection) {
            this(authPermission);
            boolean z = collection.getClass().getClassLoader() == null;
            for (SST next : collection) {
                verifyElement(next);
                if (z || !this.elements.contains(next)) {
                    this.elements.add(next);
                }
            }
        }

        private void verifyElement(Object obj) {
            Objects.requireNonNull(obj);
            if (this.permission == Subject._PRINCIPALS && !Principal.class.isAssignableFrom(obj.getClass())) {
                throw new IllegalArgumentException("auth.0B");
            }
        }

        public boolean add(SST sst) {
            verifyElement(sst);
            Subject.this.checkState();
            Subject.checkPermission(this.permission);
            if (this.elements.contains(sst)) {
                return false;
            }
            this.elements.add(sst);
            return true;
        }

        public Iterator<SST> iterator() {
            return this.permission == Subject._PRIVATE_CREDENTIALS ? new SecureIterator(this.elements.iterator()) {
                public SST next() {
                    SST next = this.iterator.next();
                    Subject.checkPermission(new PrivateCredentialPermission(next.getClass().getName(), (Set<Principal>) Subject.this.principals));
                    return next;
                }
            } : new SecureIterator(this.elements.iterator());
        }

        public boolean retainAll(Collection<?> collection) {
            Objects.requireNonNull(collection);
            return super.retainAll(collection);
        }

        public int size() {
            return this.elements.size();
        }

        /* access modifiers changed from: protected */
        public final <E> Set<E> get(final Class<E> cls) {
            Objects.requireNonNull(cls);
            Set<E> typedSet = new AbstractSet<E>() {
                private LinkedList<E> elements = new LinkedList<>();

                public boolean add(E e) {
                    if (!cls.isAssignableFrom(e.getClass())) {
                        throw new IllegalArgumentException("auth.0C " + cls.getName());
                    } else if (this.elements.contains(e)) {
                        return false;
                    } else {
                        this.elements.add(e);
                        return true;
                    }
                }

                public Iterator<E> iterator() {
                    return this.elements.iterator();
                }

                public boolean retainAll(Collection<?> collection) {
                    Objects.requireNonNull(collection);
                    return super.retainAll(collection);
                }

                public int size() {
                    return this.elements.size();
                }
            };
            Iterator it = iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (cls.isAssignableFrom(next.getClass())) {
                    typedSet.add(cls.cast(next));
                }
            }
            return typedSet;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            int i = this.setType;
            if (i == 0) {
                this.permission = Subject._PRINCIPALS;
            } else if (i == 1) {
                this.permission = Subject._PRIVATE_CREDENTIALS;
            } else if (i == 2) {
                this.permission = Subject._PUBLIC_CREDENTIALS;
            } else {
                throw new IllegalArgumentException();
            }
            Iterator it = this.elements.iterator();
            while (it.hasNext()) {
                verifyElement(it.next());
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            if (this.permission == Subject._PRIVATE_CREDENTIALS) {
                Iterator it = iterator();
                while (it.hasNext()) {
                    it.next();
                }
                this.setType = 1;
            } else if (this.permission == Subject._PRINCIPALS) {
                this.setType = 0;
            } else {
                this.setType = 2;
            }
            objectOutputStream.defaultWriteObject();
        }

        private class SecureIterator implements Iterator<SST> {
            protected Iterator<SST> iterator;

            protected SecureIterator(Iterator<SST> it) {
                this.iterator = it;
            }

            public boolean hasNext() {
                return this.iterator.hasNext();
            }

            public SST next() {
                return this.iterator.next();
            }

            public void remove() {
                Subject.this.checkState();
                Subject.checkPermission(SecureSet.this.permission);
                this.iterator.remove();
            }
        }
    }
}
