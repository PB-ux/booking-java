package com.bratkov.booking;

import com.lab.booking.Booking;

import com.lab.booking.Status;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import jakarta.annotation.PostConstruct;

@Component
public class BookingRepository {

    private Booking[] bookings;

    @PostConstruct
    public void initData() {
        bookings = new Booking[2];

        Booking booking1 = create(1, "John", "Doe", 1, Status.CREATED);
        bookings[0] = booking1;

        Booking booking2 = create(2 , "Jane", "Smith", 2, Status.CREATED);
        bookings[1] = booking2;
    }

    public Booking create(Integer bookingId, String firstName, String lastName, Integer flightId, Status status) {
        Assert.notNull(firstName, "The first name must not be null");
        Assert.notNull(lastName, "The last name must not be null");
        Assert.notNull(flightId, "The flight ID must not be null");
        Assert.notNull(status, "The status must not be null");

        Booking newBooking = new Booking();
        newBooking.setBookingId(bookingId);
        newBooking.setFirstName(firstName);
        newBooking.setLastName(lastName);
        newBooking.setFlightId(flightId);
        newBooking.setStatus(status);

        return newBooking;
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
