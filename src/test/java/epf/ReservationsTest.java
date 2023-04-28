package epf;

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.utils.Reservations;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReservationsTest {
    private Reservations Reservations = new Reservations();
    @Test
    public void isLegal_should_return_true_rent_is_under_7_days(){
        Reservation legalReservation = new Reservation (1, 1, 1, LocalDate.of(2023, 1, 8), LocalDate.of(2023, 1, 12));
        assertTrue(Reservations.lessThan7Days(legalReservation));
    }
    @Test
    public void isLegal_should_return_false_rent_is_under_7_days(){
        Reservation illlegalReservation = new Reservation (1, 1, 1, LocalDate.of(2023, 1, 8), LocalDate.of(2023, 1, 23));
        assertFalse(Reservations.lessThan7Days(illlegalReservation));
    }
}
