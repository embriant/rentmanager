package com.epf.rentmanager.utils;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.Objects;

public class Vehicles {

    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    public Vehicles() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    /**
     * Supprime les réservations du véhicule supprimé
     * @param vehicle
     * @return
     */
    public long delete(Vehicle vehicle) throws ServiceException {
        for (Reservation reservation : reservationService.findAll()) {
            if (vehicle.getId() == reservation.getVehicle_id()) {
                reservationService.delete(reservation);
            }
        }
        return vehicleService.delete(vehicle);
    }
    public boolean validVehicle(Vehicle vehicle) {
        boolean manufacturerExists = true;
        if (vehicle.getConstructeur()!=null || !Objects.equals(vehicle.getConstructeur(), "")) {
            manufacturerExists = false;
        }
        return
                (manufacturerExists && (2 <= vehicle.getNb_places() && vehicle.getNb_places() <= 9));
    }
}
