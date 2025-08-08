package com.bookmenow.booking.service.impl;
import com.bookmenow.booking.exceptions.exceptions.BusinessRuleException;
import com.bookmenow.booking.feign.clients.CatalogClient;
import com.bookmenow.booking.feign.clients.UserClient;
import com.bookmenow.booking.model.Booking;
import com.bookmenow.booking.repository.BookingRepository;
import com.bookmenow.booking.service.BookingService;
import com.bookmenow.dto.BookingDTO;
import com.bookmenow.dto.CatalogDTO;
import com.bookmenow.dto.UserDTO;
import com.bookmenow.enums.BookingStatus;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private CatalogClient catalogClient;

    // DTO TO ENTITY ----------------------------------------------------------------------------------------------------
    public Booking toEntity(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setUserId(dto.getUserId());
        booking.setCatalogId(dto.getCatalogId());

        // Using Feign to retrieve user attributes
        try {
            UserDTO userDTO = userClient.getUserById(dto.getUserId());
            if (userDTO != null) {
                booking.setUserName(userDTO.getName());
                booking.setUserEmail(userDTO.getEmail());
                booking.setPhone(userDTO.getPhone());
                booking.setAddress(userDTO.getAddress());
                
            }
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("User with ID " + dto.getUserId() + " was not found");
        }

        booking.setDate(dto.getDate());
        booking.setBookingStatus(dto.getBookingStatus());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        // Using Feign to retrieve catalog attributes
        try {
            CatalogDTO catalogDTO = catalogClient.getCatalogById(dto.getCatalogId());
            if (catalogDTO != null) {
                booking.setCatalogName(catalogDTO.getName());
                booking.setCatalogDescription(catalogDTO.getDescription());
                booking.setDurationMinutes(catalogDTO.getDurationMinutes());
                booking.setPrice(catalogDTO.getPrice());
            }
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Catalog with ID " + dto.getCatalogId() + " was not found");
        }

        return booking;
    }

    // ENTITY TO DTO ----------------------------------------------------------------------------------------------------
    public BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUserId());
        dto.setCatalogId(booking.getCatalogId());

        // Using Feign to retrieve user attributes
        try {
            UserDTO userDTO = userClient.getUserById(dto.getUserId());
            if (userDTO != null) {
                dto.setUserName(userDTO.getName());
                dto.setUserEmail(userDTO.getEmail());
                dto.setPhone(userDTO.getPhone());
                dto.setAddress(userDTO.getAddress());
            }
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("User with ID " + dto.getUserId() + " was not found");
        }

        dto.setDate(booking.getDate());
        dto.setBookingStatus(booking.getBookingStatus());
        dto.setCreatedAt(booking.getCreatedAt());
        dto.setUpdatedAt(booking.getUpdatedAt());

        // Using Feign to retrieve catalog attributes
        try {
            CatalogDTO catalogDTO = catalogClient.getCatalogById(dto.getCatalogId());
            if (catalogDTO != null) {
                dto.setCatalogName(catalogDTO.getName());
                dto.setCatalogDescription(catalogDTO.getDescription());
                dto.setDurationMinutes(catalogDTO.getDurationMinutes());
                dto.setPrice(catalogDTO.getPrice());
            }
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Catalog with ID " + dto.getCatalogId() + " was not found");
        }

        return dto;
    }

    // Get all bookings --------------------------------------------------------------------------------------------------
    @Override
    public List<BookingDTO> getAllBooking() {
        List<Booking> bookingList = bookingRepository.findAll();
        return bookingList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Find booking by ID ------------------------------------------------------------------------------------------------
    @Override
    public BookingDTO findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("4005", "Booking with the ID " + id + " was not found", HttpStatus.BAD_REQUEST));
        return toDTO(booking);
    }

    // Post booking ------------------------------------------------------------------------------------------------------
    @Override
    public BookingDTO postBooking(BookingDTO bookingDTO) {
        Booking booking = toEntity(bookingDTO);
        booking.setBookingStatus(BookingStatus.RESERVED);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        Booking saved = bookingRepository.save(booking);
        return toDTO(saved);
    }

    // Put (update) booking ---------------------------------------------------------------------------------------------
    @Override
    public BookingDTO putBooking(Long id, BookingDTO bookingDTO) {
        Booking booking = toEntity(bookingDTO);
        return bookingRepository.findById(id).map(existing -> {
            existing.setDate(booking.getDate());
            existing.setBookingStatus(BookingStatus.RESERVED);
            existing.setUpdatedAt(LocalDateTime.now());
            Booking updated = bookingRepository.save(existing);
            return toDTO(updated);
        }).orElseThrow(() -> new BusinessRuleException("4005", "Booking with the ID " + id + " was not found", HttpStatus.BAD_REQUEST));
    }

    // Cancel booking ----------------------------------------------------------------------------------------------------
    @Override
    public BookingDTO cancelBooking(Long id, BookingDTO bookingStatus) {
        return bookingRepository.findById(id).map(existing -> {
            existing.setBookingStatus(BookingStatus.CANCELLED);
            Booking cancelled = bookingRepository.save(existing);
            return toDTO(cancelled);
        }).orElseThrow(() -> new BusinessRuleException("4005", "Booking with the ID " + id + " was not found", HttpStatus.BAD_REQUEST));
    }

    // Get bookings by user ID ---------------------------------------------------------------------------------------
    @Override
    public List<BookingDTO> getBookingsByUserId(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRuleException("4005", "Provider ID is invalid", HttpStatus.BAD_REQUEST);
        }

        List<Booking> bookings = bookingRepository.findAllByUserId(id);
        if (bookings == null || bookings.isEmpty()) {
            throw new BusinessRuleException("4008", "No bookings found for this provider", HttpStatus.NOT_FOUND);
        }
        return bookings.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Reactivate cancelled booking --------------------------------------------------------------------------------------
    @Override
    public BookingDTO reactivateBooking(Long id, BookingDTO bookingStatus) {
        return bookingRepository.findById(id).map(existing -> {
            if (existing.getBookingStatus().equals(BookingStatus.CANCELLED)) {
                existing.setBookingStatus(BookingStatus.RESERVED);
            }
            Booking reactivated = bookingRepository.save(existing);
            return toDTO(reactivated);
        }).orElseThrow(() -> new BusinessRuleException("4001", "Booking with the ID " + id + " was not found", HttpStatus.BAD_REQUEST));
    }
}
