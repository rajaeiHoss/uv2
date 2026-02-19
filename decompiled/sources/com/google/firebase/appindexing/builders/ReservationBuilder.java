package com.google.firebase.appindexing.builders;

import com.google.android.gms.common.internal.zzbq;
import java.util.Date;

public final class ReservationBuilder extends IndexableBuilder<ReservationBuilder> {
    ReservationBuilder() {
        super("Reservation");
    }

    public final ReservationBuilder setPartySize(long j) {
        return (ReservationBuilder) put("partySize", j);
    }

    public final ReservationBuilder setReservationFor(LocalBusinessBuilder localBusinessBuilder) {
        return (ReservationBuilder) put("reservationFor", new LocalBusinessBuilder[]{localBusinessBuilder});
    }

    public final ReservationBuilder setStartDate(Date date) {
        zzbq.checkNotNull(date);
        return (ReservationBuilder) put("startDate", date.getTime());
    }
}
