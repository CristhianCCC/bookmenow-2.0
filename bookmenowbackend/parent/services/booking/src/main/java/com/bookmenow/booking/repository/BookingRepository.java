package com.bookmenow.booking.repository;
import com.bookmenow.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository <Booking, Long> {

    List<Booking> findAllByUserId(Long userId);

}
