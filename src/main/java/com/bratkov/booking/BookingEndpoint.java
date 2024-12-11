package com.bratkov.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.lab.booking.*;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Endpoint
public class BookingEndpoint {
    private static final String NAMESPACE_URI = "http://lab.com/booking";

    private BookingRepository bookingRepository;

    @Autowired
    public BookingEndpoint(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookingsRequest")
    @ResponsePayload
    public GetBookingsResponse getBookings(@RequestPayload GetBookingsRequest request) {
        GetBookingsResponse response = new GetBookingsResponse();
        Booking[] bookings = bookingRepository.getBookings();
        response.getBooking().addAll(Arrays.asList(bookings));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "cancelBookingRequest")
    @ResponsePayload
    public CancelBookingResponse cancelBooking(@RequestPayload CancelBookingRequest request) {
        CancelBookingResponse response = new CancelBookingResponse();
        Booking booking = bookingRepository.cancelBooking(request.getBookingId());
        response.setBooking(booking);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "cancelBookingAsyncRequest")
    @ResponsePayload
    public CompletableFuture<CancelBookingResponse> cancelBookingAsync(@RequestPayload CancelBookingRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            CancelBookingResponse response = new CancelBookingResponse();
            Booking booking = bookingRepository.cancelBooking(request.getBookingId());
            response.setBooking(booking);
            return response;
        });
    }
}
