package org.apache.harmony.javax.security.auth;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Principal;
import java.util.Objects;
import java.util.Set;

public final class PrivateCredentialPermission extends Permission {
    private static final String READ = "read";
    private static final long serialVersionUID = 5284372143517237068L;
    private String credentialClass;
    private transient int offset;
    private transient CredOwner[] set;

    public String getActions() {
        return READ;
    }

    public PermissionCollection newPermissionCollection() {
        return null;
    }

    public PrivateCredentialPermission(String str, String str2) {
        super(str);
        if (READ.equalsIgnoreCase(str2)) {
            initTargetName(str);
            return;
        }
        throw new IllegalArgumentException("auth.11");
    }

    PrivateCredentialPermission(String str, Set<Principal> set2) {
        super(str);
        this.credentialClass = str;
        this.set = new CredOwner[set2.size()];
        for (Principal next : set2) {
            CredOwner credOwner = new CredOwner(next.getClass().getName(), next.getName());
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= this.offset) {
                    break;
                } else if (this.set[i].equals(credOwner)) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                CredOwner[] credOwnerArr = this.set;
                int i2 = this.offset;
                this.offset = i2 + 1;
                credOwnerArr[i2] = credOwner;
            }
        }
    }

    private void initTargetName(String str) {
        boolean z;
        Objects.requireNonNull(str, "auth.0E");
        String trim = str.trim();
        if (trim.length() != 0) {
            int indexOf = trim.indexOf(32);
            if (indexOf != -1) {
                this.credentialClass = trim.substring(0, indexOf);
                int i = indexOf + 1;
                int length = trim.length();
                int i2 = 0;
                while (i < length) {
                    int indexOf2 = trim.indexOf(32, i);
                    int indexOf3 = trim.indexOf(34, indexOf2 + 2);
                    if (indexOf2 == -1 || indexOf3 == -1 || trim.charAt(indexOf2 + 1) != '\"') {
                        throw new IllegalArgumentException("auth.10");
                    }
                    i = indexOf3 + 2;
                    i2++;
                }
                if (i2 >= 1) {
                    int indexOf4 = trim.indexOf(32) + 1;
                    this.set = new CredOwner[i2];
                    for (int i3 = 0; i3 < i2; i3++) {
                        int indexOf5 = trim.indexOf(32, indexOf4);
                        int i4 = indexOf5 + 2;
                        int indexOf6 = trim.indexOf(34, i4);
                        CredOwner credOwner = new CredOwner(trim.substring(indexOf4, indexOf5), trim.substring(i4, indexOf6));
                        int i5 = 0;
                        while (true) {
                            if (i5 >= this.offset) {
                                z = false;
                                break;
                            } else if (this.set[i5].equals(credOwner)) {
                                z = true;
                                break;
                            } else {
                                i5++;
                            }
                        }
                        if (!z) {
                            CredOwner[] credOwnerArr = this.set;
                            int i6 = this.offset;
                            this.offset = i6 + 1;
                            credOwnerArr[i6] = credOwner;
                        }
                        indexOf4 = indexOf6 + 2;
                    }
                    return;
                }
                throw new IllegalArgumentException("auth.10");
            }
            throw new IllegalArgumentException("auth.10");
        }
        throw new IllegalArgumentException("auth.0F");
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        initTargetName(getName());
    }

    public String[][] getPrincipals() {
        int i = this.offset;
        int[] iArr = new int[2];
        iArr[1] = 2;
        iArr[0] = i;
        String[][] strArr = (String[][]) Array.newInstance(String.class, iArr);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            strArr[i2][0] = this.set[i2].principalClass;
            strArr[i2][1] = this.set[i2].principalName;
        }
        return strArr;
    }

    public String getCredentialClass() {
        return this.credentialClass;
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < this.offset; i2++) {
            i += this.set[i2].hashCode();
        }
        return getCredentialClass().hashCode() + i;
    }

    public boolean equals(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PrivateCredentialPermission privateCredentialPermission = (PrivateCredentialPermission) obj;
        if (!this.credentialClass.equals(privateCredentialPermission.credentialClass) || (i = this.offset) != privateCredentialPermission.offset || !sameMembers(this.set, privateCredentialPermission.set, i)) {
            return false;
        }
        return true;
    }

    public boolean implies(Permission permission) {
        if (permission == null || getClass() != permission.getClass()) {
            return false;
        }
        PrivateCredentialPermission privateCredentialPermission = (PrivateCredentialPermission) permission;
        if (!"*".equals(this.credentialClass) && !this.credentialClass.equals(privateCredentialPermission.getCredentialClass())) {
            return false;
        }
        int i = privateCredentialPermission.offset;
        if (i == 0) {
            return true;
        }
        CredOwner[] credOwnerArr = this.set;
        CredOwner[] credOwnerArr2 = privateCredentialPermission.set;
        int i2 = this.offset;
        int i3 = 0;
        while (i3 < i2) {
            int i4 = 0;
            while (i4 < i && !credOwnerArr[i3].implies(credOwnerArr2[i4])) {
                i4++;
            }
            if (i4 == credOwnerArr2.length) {
                return false;
            }
            i3++;
        }
        return true;
    }

    private boolean sameMembers(Object[] objArr, Object[] objArr2, int i) {
        boolean z;
        if (objArr == null && objArr2 == null) {
            return true;
        }
        if (objArr == null || objArr2 == null) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = 0;
            while (true) {
                if (i3 >= i) {
                    z = false;
                    break;
                } else if (objArr[i2].equals(objArr2[i3])) {
                    z = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    private static final class CredOwner implements Serializable {
        private static final long serialVersionUID = -5607449830436408266L;
        private transient boolean isClassWildcard;
        private transient boolean isPNameWildcard;
        String principalClass;
        String principalName;

        CredOwner(String str, String str2) {
            if ("*".equals(str)) {
                this.isClassWildcard = true;
            }
            if ("*".equals(str2)) {
                this.isPNameWildcard = true;
            }
            if (!this.isClassWildcard || this.isPNameWildcard) {
                this.principalClass = str;
                this.principalName = str2;
                return;
            }
            throw new IllegalArgumentException("auth.12");
        }

        /* access modifiers changed from: package-private */
        public boolean implies(Object obj) {
            if (obj == this) {
                return true;
            }
            CredOwner credOwner = (CredOwner) obj;
            if (!this.isClassWildcard && !this.principalClass.equals(credOwner.principalClass)) {
                return false;
            }
            if (this.isPNameWildcard || this.principalName.equals(credOwner.principalName)) {
                return true;
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CredOwner)) {
                return false;
            }
            CredOwner credOwner = (CredOwner) obj;
            if (!this.principalClass.equals(credOwner.principalClass) || !this.principalName.equals(credOwner.principalName)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.principalClass.hashCode() + this.principalName.hashCode();
        }
    }
}
