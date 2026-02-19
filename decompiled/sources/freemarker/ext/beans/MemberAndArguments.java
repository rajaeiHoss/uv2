package freemarker.ext.beans;

import java.lang.reflect.Member;

class MemberAndArguments {
    private final Object[] args;
    private final Member member;

    MemberAndArguments(Member member2, Object[] objArr) {
        this.member = member2;
        this.args = objArr;
    }

    /* access modifiers changed from: package-private */
    public Object[] getArgs() {
        return this.args;
    }

    public Member getMember() {
        return this.member;
    }
}
