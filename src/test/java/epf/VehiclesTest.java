package epf;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.utils.Vehicles;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VehiclesTest {
    private Vehicles Vehicles = new Vehicles();

    @Test
    public void isValid_should_return_true_when_seats_are_between_2_and_9_and_there_is_a_manifacturer() {
        Vehicle legalVehicle = new Vehicle(1, "Suzuki", 4);
        assertTrue(Vehicles.validVehicle(legalVehicle));
    }
@Test
    public void isValid_should_return_false_when_seats_are_under_2() {
        Vehicle illegalVehicle = new Vehicle(1, "Suzuki", 1);
        assertFalse(Vehicles.validVehicle(illegalVehicle));
    }
@Test
    public void isValid_should_return_false_when_seats_are_over_9() {
        Vehicle illegalVehicle = new Vehicle(1, "Suzuki", 11);
        assertFalse(Vehicles.validVehicle(illegalVehicle));
    }
@Test
    public void isValid_should_return_false_when_there_is_no_constructor() {
        Vehicle illegalVehicle = new Vehicle(1, null, 4);
        assertFalse(Vehicles.validVehicle(illegalVehicle));
    }
}