package com.bookmenow.booking.controller;
import com.bookmenow.booking.service.BookingService;
import com.bookmenow.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> dto = bookingService.getAllBooking();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> findBookingById(@PathVariable("id") Long id) {
        BookingDTO dto = bookingService.findBookingById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<BookingDTO> postBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO dto = bookingService.postBooking(bookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> putBooking(@PathVariable("id") Long id, @RequestBody BookingDTO bookingDTO) {
        BookingDTO dto = bookingService.putBooking(id, bookingDTO);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<BookingDTO> cancelBooking (@PathVariable("id") Long id, @RequestBody BookingDTO bookingStatus){
        BookingDTO dto = bookingService.cancelBooking(id, bookingStatus);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<BookingDTO>> getBookingsByUserId(@PathVariable("id") Long id){
        List<BookingDTO> dto = bookingService.getBookingsByUserId(id);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<BookingDTO> reactivateBooking (@PathVariable("id") Long id, @RequestBody BookingDTO bookingStatus){
        BookingDTO dto = bookingService.reactivateBooking(id, bookingStatus);
        return ResponseEntity.ok(dto);
    }
}
