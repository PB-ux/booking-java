package com.bratkov.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.lab.booking.Booking;
import com.lab.booking.CreateBookingResponse;
import com.lab.booking.CreateBookingRequest;

@Endpoint
public class BookingEndpoint {
    private static final String NAMESPACE_URI = "http://lab.com/booking";

    private BookingRepository bookingRepository;

    @Autowired
    public BookingEndpoint(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createBookingRequest")
    @ResponsePayload
    public CreateBookingResponse createBooking(@RequestPayload CreateBookingRequest request) {
        CreateBookingResponse response = new CreateBookingResponse();
        Booking newBooking = bookingRepository.create(request.getFirstName(), request.getLastName(), request.getFlightId());
        response.setBooking(newBooking);

        return response;
    }

}
