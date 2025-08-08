package com.bookmenow.booking.service;

import com.bookmenow.dto.BookingDTO;

import java.util.List;

public interface BookingService {

    public List<BookingDTO> getAllBooking ();

    public BookingDTO findBookingById (Long id);

    public BookingDTO postBooking(BookingDTO bookingDTO);

    public BookingDTO putBooking(Long id, BookingDTO bookingDTO);

    public BookingDTO cancelBooking (Long id, BookingDTO bookingStatus);

    //for the providers to see their bookings
    public List<BookingDTO> getBookingsByUserId(Long id);

    //to reactivate a cancelled booking
    public BookingDTO reactivateBooking (Long id, BookingDTO bookingStatus);
}
