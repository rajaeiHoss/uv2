package com.google.firebase.database;

import com.google.android.gms.internal.zzenn;

public class Transaction {

    public interface Handler {
        Result doTransaction(MutableData mutableData);

        void onComplete(DatabaseError databaseError, boolean z, DataSnapshot dataSnapshot);
    }

    public static class Result {
        private boolean zzmxl;
        private zzenn zzmxm;

        private Result(boolean z, zzenn zzenn) {
            this.zzmxl = z;
            this.zzmxm = zzenn;
        }

        public boolean isSuccess() {
            return this.zzmxl;
        }

        public final zzenn zzbve() {
            return this.zzmxm;
        }
    }

    public static Result abort() {
        return new Result(false, (zzenn) null);
    }

    public static Result success(MutableData mutableData) {
        return new Result(true, mutableData.zzbve());
    }
}
