package com.bratkov.booking;

import com.lab.booking.Booking;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class BookingRepository {
    public Booking create(String firstName, String lastName, Integer flightId) {
        Assert.notNull(firstName, "The country's name must not be null");
        Assert.notNull(lastName, "The country's name must not be null");
        Assert.notNull(flightId, "The country's name must not be null");

        Booking newBooking = new Booking();
        newBooking.setFirstName(firstName);
        newBooking.setLastName(lastName);
        newBooking.setFlightId(flightId);

        return newBooking;
    };

}
