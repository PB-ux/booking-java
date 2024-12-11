package com.bratkov.booking;

import com.lab.booking.Booking;

import com.lab.booking.Status;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class BookingRepository {

    private static Booking[] bookings;

    @PostConstruct
    public void initData() {
        bookings = new Booking[2];

        Booking booking1 = new Booking();
        booking1.setBookingId(1);
        booking1.setFirstName("John");
        booking1.setLastName("Doe");
        booking1.setFlightId(1);
        booking1.setStatus(Status.CREATED);
        bookings[0] = booking1;
    }

    public Booking[] getBookings() {
        return bookings;
    }

    public Booking cancelBooking(Integer bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                booking.setStatus(Status.CANCELED);
                return booking;
            }
        }

        return null;
    }
}
