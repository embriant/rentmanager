package com.epf.rentmanager.utils;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Clients {

    @Autowired
    ClientService clientService;

    @Autowired
    ReservationService reservationService;

    public Clients() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    /**
     * Renvoie true si l’utilisateur passé en paramètre a un âge >= 18 ans
     * @param client L'instance d’utilisateur à tester
     * @return Résultat du test (>= 18 ans)
     */

    public boolean isLegal(Client client) {
        return
            (Period.between(client.getNaissance(), LocalDate.now()).getYears() >= 18);
    }

    /**
     * Renvoie true si l’utilisateur passé a un nom et un prénom supérieurs à 3 lettres
     * @param client L'instance d’utilisateur à tester
     * @return Résultat du test (> 3 lettres)
     */
    public static boolean namesLengthOK(Client client) {
        return
                (client.getNom().length() >= 3 && client.getPrenom().length() >= 3);
    }

    /**
     * Vérifie que le mail du client n'est pas utilisé
     * @param client
     * @return
     */
    public boolean emailNotInDB(Client client) throws ServiceException {
        boolean notInDB = true;
        for (Client client_db:clientService.findAll()) {
            if (Objects.equals(client_db.getEmail(), client.getEmail())) {
                notInDB = false;
                break;
            }
        }
        return notInDB;
    }

    /**
     * Supprime les réservations du client supprimé
     * @param client
     * @return
     */
    public long delete(Client client) throws ServiceException {
        for (Reservation reservation : reservationService.findAll()) {
            if (client.getId() == reservation.getClient_id()) {
                reservationService.delete(reservation);
            }
        }
        return clientService.delete(client);
    }

    /**
     * Vérifie qu'une date est entrée
     * @param client
     * @return
     */
    public boolean validDate(Client client) {
        return
                client.getNaissance() != null;
    }
}
